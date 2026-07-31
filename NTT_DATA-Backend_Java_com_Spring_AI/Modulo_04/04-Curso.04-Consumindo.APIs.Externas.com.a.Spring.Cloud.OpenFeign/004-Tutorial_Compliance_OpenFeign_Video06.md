# Tutorial de Estudos — Consumindo APIs Externas com o Spring Cloud OpenFeign

**Configurando Cenários de Exceção: fallback e Circuit Breaker no Feign Client — Vídeo 06**

- Curso: NTT Data — Jornada Tech (DIO) · Módulo 04 — Java/Spring com IA
- Curso 4 do módulo: "Consumindo APIs Externas com a Spring Cloud OpenFeign"
- Instrutor: Thiago Poiani (Principal Engineer at Skip)
- Projeto: `compliance`
- Documento de referência pessoal — nível iniciante em Java
- **Continuação direta de** `003-Tutorial_Compliance_OpenFeign_Video05.md`

---

## Sobre este documento

Este é o quarto tutorial da série. Ele parte exatamente de onde o anterior parou (fim do Vídeo 05: Feign Client configurado externamente, `SanctionResult` já modelado — mas ainda não conectado ao fluxo — e autenticação por `X-API-Key` funcionando de ponta a ponta) e documenta o **Vídeo 06**, com base:

- no README atualizado (anotações de aula até o Vídeo 06);
- no código-fonte real do projeto `compliance`, no estado correspondente ao `compliance_ate_o_video06.zip`.

O mesmo compromisso dos documentos anteriores vale aqui: cada bloco de código é apresentado na ordem em que foi escrito na aula, seguido de explicação em nível iniciante — o que cada instrução faz, por que foi escrita daquela forma, e qual conceito de Java, Spring ou de arquitetura ela representa. Sempre que aparece uma anotação, propriedade, classe da biblioteca padrão ou padrão de projeto ainda não visto nos tutoriais anteriores, ele é explicado do zero.

> **Sobre o título da etapa**
> O README chama o Vídeo 06 de "Configurando Cenários de Exceção", e o conteúdo é fiel a esse título: a aula provoca deliberadamente uma falha na API externa (erro 500) e, a partir dela, constrói duas camadas de proteção — um método de **fallback** no Feign Client e o **Circuit Breaker** do Resilience4j, que precisa estar habilitado para que esse fallback seja de fato acionado. O vídeo termina validando três cenários possíveis de resposta da API mocada: erro, sucesso sem sanções e sucesso com uma sanção encontrada.

---

## Parte 6 — Vídeo 06: Configurando Cenários de Exceção

### 6.1. Elevando o nível de log da infraestrutura para `DEBUG`

Antes de simular qualquer falha, o `application.properties` ganha uma nova linha:

```properties
spring.application.name=compliance

spring.cloud.openfeign.client.config.sanction-client.url=http://localhost:3001
spring.cloud.openfeign.client.config.sanction-client.logger-level=full
spring.cloud.openfeign.client.config.sanction-client.default-request-headers.x-api-key=kyc-secret-123

logging.level.dio.compliance.infrastructure.rest=DEBUG
```

- **`logging.level.<pacote>=DEBUG`** — esta é uma propriedade padrão do Spring Boot (não é específica do OpenFeign) que controla, pacote por pacote, o quanto de detalhe aparece no console. Por padrão, o Spring Boot só exibe logs de nível `INFO` para cima (`INFO`, `WARN`, `ERROR`). Ao declarar `DEBUG` especificamente para o pacote `dio.compliance.infrastructure.rest` — que é onde vive o `SanctionClient` — a aplicação passa a exibir também mensagens de nível `DEBUG` originadas **apenas** desse pacote, sem "poluir" o console com o `DEBUG` de todas as outras bibliotecas do projeto (Spring, Hibernate, etc., que continuam só em `INFO` para cima).
- **Como isso se relaciona com o `logger-level=full` do Vídeo 05?** São dois mecanismos complementares, não o mesmo: `logger-level=full` (visto na seção 5.1 do tutorial anterior) é uma propriedade **do OpenFeign**, que decide **o que** o Feign registra sobre cada chamada (URL, headers, corpo). Já `logging.level.*` é a propriedade **do Spring Boot/SLF4J** que decide **se** essas mensagens (que o Feign já está preparado para gerar, no nível `DEBUG`) realmente aparecem no console ou são descartadas. Sem essa segunda linha, mesmo com `logger-level=full`, boa parte do detalhamento das requisições Feign continuaria invisível — os dois precisam estar configurados juntos para o efeito completo aparecer nos logs (isso fica visível na prática na seção 6.4).

### 6.2. Simulando uma falha 500 na API mocada (Mockoon)

Sem alterar nenhum código Java, a aula volta ao Mockoon e cadastra uma **segunda resposta possível** para a mesma rota já existente (`GET /sanctions/companies/:registrationNumber`), desta vez com status `500`:

```json
{
  "error": "Internal Server Error"
}
```

- **Múltiplas respostas para a mesma rota** — o Mockoon permite que uma única rota tenha várias respostas cadastradas (já era o caso desde o Vídeo 04, que tinha a resposta "Empresa sem Riscos"); a diferença é que, entre elas, é preciso marcar **qual é a resposta padrão** (a que é usada quando a rota é chamada normalmente, sem nenhuma regra especial de seleção). A aula alterna esse marcador entre as respostas disponíveis para simular, de forma controlada, diferentes comportamentos da "mesma" API externa — sem precisar de uma segunda ferramenta ou de escrever qualquer lógica adicional no mock.
- O objetivo desse passo é puramente **experimental**: forçar, de forma reproduzível, o mesmo tipo de falha que uma API real poderia apresentar (indisponibilidade, erro interno, instabilidade), para então poder observar e corrigir a reação da aplicação `compliance` a esse cenário.

### 6.3. Reproduzindo a falha 500 pelo fluxo real da aplicação

Com a resposta padrão da rota marcada como `500`, uma nova empresa é criada via `POST /companies` (o mesmo endpoint gerado pelo Spring Data REST desde o Vídeo 03), disparando a cadeia já conhecida: `handleAfterCreateEvent` → `AnalyzeCompanyRiskUseCase.execute` → `sanctionClient.getCompanyRisk(...)`.

Assim como já havia acontecido no Vídeo 05 com a falha de autenticação (401), a chamada ao Mockoon falha e o erro se propaga de volta até quem chamou o endpoint `/companies`, resultando em **`500 Internal Server Error`** — o comportamento padrão do Spring Boot quando uma exceção não tratada sobe até o controller sem ser capturada em nenhum ponto do caminho.

### 6.4. Lendo o stack trace com o log em `DEBUG`

Com a configuração de log ampliada na seção 6.1, o console passa a exibir, além da confirmação de que o `SanctionClient` recebeu o erro, os detalhes completos da chamada HTTP que falhou: o verbo e a URL usados (`GET .../sanctions/companies/REG-1234`), o header `x-api-key` enviado, o tempo de resposta e o status `500` retornado — o mesmo tipo de informação que já havia aparecido no Vídeo 05 para o erro `401`, mas agora relacionado a um erro do "lado do servidor" (5xx) em vez de um erro de autenticação (4xx).

Esse é o comportamento **padrão** do `ErrorDecoder` do Feign (conceito já apresentado, mas ainda não customizado, desde o Vídeo 05, seção 5.1): qualquer status de erro (4xx ou 5xx) vira uma exceção da família `feign.FeignException`, que sobe pela pilha de chamadas até encontrar algo que a trate — e, até este ponto do curso, nada trata.

> **Por que a aula não implementa um `ErrorDecoder` customizado aqui?**
> O Vídeo 05 havia deixado em aberto a possibilidade de um `ErrorDecoder` próprio para "traduzir" erros HTTP em exceções de domínio. O Vídeo 06 segue por um caminho diferente e mais simples para este cenário: em vez de customizar como o erro é decodificado, a aula ensina como **evitar que o erro se propague de qualquer forma**, usando um mecanismo de fallback (seção 6.5) combinado a um circuit breaker (seção 6.7). São estratégias complementares — um `ErrorDecoder` continua sendo útil quando se precisa saber **qual** erro ocorreu; um fallback é útil quando o objetivo é a aplicação **seguir funcionando mesmo sem saber exatamente por quê**.

### 6.5. Criando o fallback do `SanctionClient`

Para tratar o erro de forma controlada, uma classe interna `Fallback` é criada dentro da própria interface `SanctionClient`, e referenciada no atributo `fallback` da anotação `@FeignClient`:

```java
package dio.compliance.infrastructure.rest.client;

import dio.compliance.infrastructure.rest.dto.SanctionResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "sanction-client", fallback = SanctionClient.Fallback.class)
public interface SanctionClient {

    @GetMapping("/sanctions/companies/{registrationNumber}")
    SanctionResult getCompanyRisk(@PathVariable String registrationNumber);

    @Component
    class Fallback implements SanctionClient {

        @Override
        public SanctionResult getCompanyRisk(String registrationNumber) {
            return new SanctionResult(List.of());
        }
    }
}
```

- **`fallback = SanctionClient.Fallback.class`** — este é um atributo opcional da anotação `@FeignClient`, que recebe **uma classe** (não um objeto, e sim o próprio `.class`, uma referência ao tipo em tempo de compilação) responsável por fornecer um comportamento alternativo sempre que a chamada HTTP real falhar — seja por erro de rede (timeout, conexão recusada) ou por um status de erro HTTP (4xx/5xx, incluindo o `500` simulado nesta etapa). Em vez de deixar a exceção subir, o Feign passa a invocar automaticamente o método correspondente da classe de fallback.
- **`class Fallback implements SanctionClient`** — a classe de fallback precisa **implementar a mesma interface** do Feign Client (`SanctionClient`), justamente para garantir que ela tenha, com a assinatura idêntica, todos os métodos que a interface declara — é assim que o Feign sabe qual método do fallback corresponde a qual método da chamada original que falhou.
- **Classe aninhada (`Fallback` dentro de `SanctionClient`)** — assim como os records aninhados vistos no Vídeo 05 (`SanctionMatch` dentro de `SanctionResult`), declarar `Fallback` *dentro* de `SanctionClient` comunica que essa classe só faz sentido no contexto desse client específico — ela não seria reutilizada em nenhum outro lugar do projeto.
- **`@Component`** — anotação do Spring (já vista em outras classes do projeto, como `InMemoryCompanyRepository`) que registra a classe como um **bean** gerenciado pelo container do Spring. Isso é necessário aqui porque o Feign precisa conseguir localizar e instanciar essa classe através do mecanismo de injeção de dependência do Spring — sem essa anotação, o Spring não saberia que `Fallback` deveria existir como um objeto gerenciado.
- **`@Override`** — anotação padrão do Java (não é do Spring) que marca um método como estando **sobrescrevendo** um método herdado de uma superclasse ou declarado em uma interface implementada. Ela não é estritamente obrigatória para o código funcionar, mas é uma boa prática: se o método marcado com `@Override` não corresponder exatamente a nenhum método da interface (por exemplo, por um erro de digitação no nome ou nos tipos dos parâmetros), o **compilador** acusa erro imediatamente, em vez de deixar passar silenciosamente um método novo e desconectado que nunca seria chamado pelo Feign.
- **`public SanctionResult getCompanyRisk(String registrationNumber)`** — o modificador `public` é obrigatório aqui: métodos declarados em uma `interface` (como `getCompanyRisk` em `SanctionClient`) são implicitamente `public` e `abstract`, e qualquer classe que implemente essa interface precisa declarar sua versão do método com visibilidade **igual ou mais permissiva** — nunca mais restrita. Uma classe que tentasse implementar esse método sem o `public` não compilaria.
- **`return new SanctionResult(List.of());`** — o valor devolvido em caso de falha: um `SanctionResult` (o DTO criado no Vídeo 05) cujo componente `matches` é uma lista vazia (`List.of()`, já visto no Vídeo 05). Semanticamente, isso significa **tratar uma falha de comunicação com a API de sanções como se a empresa não tivesse nenhuma sanção encontrada** — uma decisão de design que prioriza manter a aplicação funcionando (*fail open*) em vez de bloquear todo o fluxo de cadastro de empresas só porque um serviço de terceiros está indisponível. É uma escolha razoável para este contexto didático, mas vale registrar que, em um cenário real de compliance, "não encontrei porque a API caiu" e "não encontrei porque realmente não há sanção" são informações com peso de risco muito diferente — algo que uma implementação de produção provavelmente trataria de forma mais explícita (por exemplo, sinalizando que a checagem falhou, em vez de devolver silenciosamente uma lista vazia).

> **Um detalhe de processo, registrado no README: o primeiro código escrito tinha um bug**
> O README relata que a primeira versão do método `getCompanyRisk` dentro de `Fallback` foi escrita **sem** o modificador `public` (e, implicitamente, sem `@Override` funcionando corretamente) — o que não compila, já que, como explicado acima, a visibilidade do método não pode ser mais restrita do que a da interface. A aula corrige isso ajustando a visibilidade antes de seguir adiante. O código-fonte real, já corrigido, é o que está reproduzido acima e confirmado no checkpoint desta etapa (seção "Estado atual do projeto").

### 6.6. Por que o fallback, sozinho, não é suficiente

Depois de criar a classe `Fallback` e reiniciar a aplicação, o erro `500` **continua** sendo lançado normalmente — o fallback não é acionado. Isso acontece porque o **suporte a circuit breaker do OpenFeign vem desabilitado por padrão** no Spring Cloud: o atributo `fallback` da anotação `@FeignClient` só tem efeito quando esse suporte está ativo. Ou seja, declarar uma classe de fallback não é suficiente por si só — é preciso ligar, de forma explícita, o mecanismo que efetivamente vai *decidir* usar esse fallback em vez de deixar a exceção subir.

### 6.7. Habilitando o Circuit Breaker do OpenFeign

```properties
spring.application.name=compliance

spring.cloud.openfeign.client.config.sanction-client.url=http://localhost:3001
spring.cloud.openfeign.client.config.sanction-client.logger-level=full
spring.cloud.openfeign.client.config.sanction-client.default-request-headers.x-api-key=kyc-secret-123
spring.cloud.openfeign.circuitbreaker.enabled=true

logging.level.dio.compliance.infrastructure.rest=DEBUG
```

- **`spring.cloud.openfeign.circuitbreaker.enabled=true`** — diferente das propriedades vistas até aqui (que usavam o prefixo `spring.cloud.openfeign.client.config.<nome>.*`, específico de **um** client), esta propriedade é **global**: ela liga o suporte a circuit breaker para **todos** os Feign Clients do projeto (neste momento, só existe um, `SanctionClient`, mas a configuração valeria para quantos outros clients viessem a ser criados).

> **O que é um *Circuit Breaker* (disjuntor)?**
> É um padrão de projeto usado para tolerância a falhas em chamadas a serviços externos, inspirado no disjuntor elétrico de uma casa. A ideia central: em vez de deixar cada chamada falhar (e cada erro se propagar) uma por uma, o circuit breaker **observa** os resultados das chamadas recentes e pode assumir diferentes "estados" — em termos simplificados:
> - **fechado** (*closed*) — estado normal; as chamadas acontecem normalmente, e o circuit breaker só está contando falhas e sucessos;
> - **aberto** (*open*) — depois de detectar falhas suficientes, o circuit breaker "abre o circuito": novas chamadas **nem chegam** a ser feitas ao serviço externo, e o fallback é acionado imediatamente — economizando tempo e evitando sobrecarregar ainda mais um serviço que já está com problema;
> - **semiaberto** (*half-open*) — depois de um tempo, o circuit breaker deixa passar algumas chamadas de teste para verificar se o serviço externo já se recuperou, decidindo então se volta ao estado fechado ou se permanece aberto.
>
> Neste vídeo, o projeto se limita a **habilitar** o mecanismo e a validar que o fallback é acionado quando a chamada falha — a configuração fina de quantas falhas abrem o circuito, por quanto tempo ele permanece aberto, etc. (os detalhes do padrão acima) não é explorada ainda; o README já sinaliza esse aprofundamento como assunto do Vídeo 08 ("Estratégias de Tolerância a Falhas").

### 6.8. Adicionando a dependência do Resilience4j

Habilitar a propriedade sozinha não é suficiente: o Spring Cloud OpenFeign delega a implementação **real** do circuit breaker a uma biblioteca própria para isso, que precisa estar no classpath do projeto.

```gradle
plugins {
    id 'java'
    id 'org.springframework.boot' version '4.0.0'
    id 'io.spring.dependency-management' version '1.1.7'

    id("io.freefair.lombok") version "9.2.0"

}

group = 'dio'
version = '0.0.1-SNAPSHOT'
description = 'compliance'

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

ext {
    set('springCloudVersion', "2025.1.1")
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'

    implementation 'org.springframework.data:spring-data-keyvalue'
    implementation 'org.springframework.boot:spring-boot-starter-data-rest'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-actuator'

    implementation 'org.springframework.cloud:spring-cloud-starter-openfeign'

    implementation 'org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j'

}

dependencyManagement {
    imports {
        mavenBom "org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"
    }
}

tasks.named('test') {
    useJUnitPlatform()
}
```

- **`implementation 'org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j'`** — única linha nova em relação ao `build.gradle` do Vídeo 04. É um *starter* do Spring Cloud (o mesmo padrão de nomenclatura já visto em `spring-cloud-starter-openfeign`) que traz, prontas para uso, as classes necessárias para o Spring Cloud "conversar" com o **Resilience4j**.
- **Resilience4j** — uma biblioteca Java de tolerância a falhas (*fault tolerance*), leve e independente de qualquer framework específico, que oferece implementações prontas para vários padrões usados ao integrar com serviços externos: **circuit breaker** (o padrão explicado na seção anterior), **rate limiter** (limita quantas chamadas podem ser feitas em um intervalo de tempo), **time limiter** (define um tempo máximo de espera por uma resposta) e **retry** (tenta novamente uma chamada que falhou, antes de desistir). Neste vídeo, apenas o circuit breaker é efetivamente exercitado; os demais recursos ficam como conhecimento de contexto para os próximos vídeos do curso.
- Como essa dependência já vem gerenciada pelo BOM `spring-cloud-dependencies` (o mecanismo de `dependencyManagement` visto desde o Vídeo 04), não é necessário informar manualmente nenhuma versão — o Gradle resolve a versão compatível com `springCloudVersion = "2025.1.1"` automaticamente.

Depois dessa dependência ser adicionada, o projeto precisa ser recarregado (*reload* do Gradle) para que as novas classes fiquem disponíveis, e a aplicação é reiniciada.

### 6.9. Validando o fallback: da lista vazia por erro à lista vazia por sucesso

Com o circuit breaker habilitado e o Resilience4j no classpath, o mesmo teste da seção 6.3 é repetido: uma nova empresa é criada via `POST /companies`, com a rota de sanções do Mockoon ainda respondendo `500` por padrão. Desta vez, a requisição **não falha mais** — a criação da empresa é concluída normalmente, e o fallback do `SanctionClient` entra em ação silenciosamente por trás da cena.

Para confirmar isso na prática, um **breakpoint** é adicionado logo após a chamada ao `SanctionClient`, dentro de `AnalyzeCompanyRiskUseCase`:

```java
public void execute(Company domain) {
    var sanctions = sanctionClient.getCompanyRisk(domain.getRegistrationNumber());

}
```

Ao pausar a execução nesse ponto, o depurador (*debugger*) da IDE confirma que a variável local `sanctions` é, de fato, um objeto `SanctionResult` cujo componente `matches` é uma lista vazia — o mesmo resultado que o método `Fallback.getCompanyRisk` foi escrito para devolver (seção 6.5). Um segundo breakpoint, posicionado diretamente dentro do método `getCompanyRisk` da classe `Fallback`, confirma ainda mais diretamente que o fluxo realmente passa por ali quando a chamada original falha — inclusive mostrando que o parâmetro `registrationNumber` recebido pelo fallback é o mesmo `"REG-1234"` da chamada original que falhou.

> **Duas causas diferentes para o mesmo resultado**
> A partir daqui, a aula alterna a resposta padrão do Mockoon entre as três opções já cadastradas — erro (`500`), sucesso sem sanções (`200`, com `matches: []`, criada no Vídeo 04) e sucesso com sanção (nova nesta etapa, seção 6.10) — reiniciando a aplicação e repetindo o mesmo teste com breakpoint a cada troca. O objetivo didático é evidenciar que **o mesmo resultado observável** (`sanctions.matches()` vazio) pode ter **duas causas completamente diferentes**: a API respondeu normalmente informando que não há sanções, **ou** a API falhou e o fallback assumiu silenciosamente. Do ponto de vista de quem só olha o valor de `sanctions` dentro do `AnalyzeCompanyRiskUseCase`, essas duas situações são, neste momento do curso, **indistinguíveis** — um ponto de atenção já levantado na seção 6.5 sobre a limitação semântica dessa estratégia de fallback.

### 6.10. Configurando o cenário de empresa sancionada no Mockoon

Por fim, uma terceira resposta é cadastrada na rota de sanções do Mockoon, desta vez simulando uma empresa que **é** encontrada em uma lista de sanções:

```json
{
  "matches": [
    {
      "entity": "{{urlParam 'registrationNumber'}}",
      "list": "OFAC SDN List",
      "reason": "Financing of Prohibited Entities",
      "confidenceScore": 0.98
    }
  ]
}
```

- Essa é exatamente a estrutura de JSON que já havia sido esboçada como comentário de "rascunho de contrato" na criação do record `SanctionResult`, no Vídeo 05 (seção 5.2) — o que confirma que o DTO modelado naquela etapa estava correto desde o início, sem precisar de ajustes agora que um payload real (mesmo que mocado) com dados preenchidos é recebido pela primeira vez.
- **`{{urlParam 'registrationNumber'}}`** — sintaxe de template do próprio Mockoon (já explicada no Vídeo 05), usada aqui para "ecoar" de volta, no campo `entity` da resposta, o número de registro que foi efetivamente consultado na URL da requisição.

Com essa resposta marcada como padrão e a aplicação reiniciada, o mesmo teste com breakpoint é repetido uma última vez. Desta vez, a variável `sanctions` chega ao `AnalyzeCompanyRiskUseCase` com `matches` contendo **um** elemento — um objeto `SanctionMatch` com `entity = "REG-1234"`, `list = "OFAC SDN List"` e os demais campos preenchidos —, comprovando, na prática, que o Feign Client fez todo o trabalho de desserialização (conversão do JSON de resposta para os objetos Java `SanctionResult`/`SanctionMatch`) automaticamente, sem que nenhuma linha de código de parsing manual precisasse ser escrita em nenhum momento do projeto.

> **Onde esse resultado é usado a partir daqui?**
> Assim como já registrado no tutorial do Vídeo 05, `AnalyzeCompanyRiskUseCase.execute` continua apenas atribuindo a resposta a uma variável local (`var sanctions = ...`) e não fazendo mais nada com ela — nem chamando `toDomain()` (o método de conversão criado no Vídeo 05), nem passando o resultado para `CompliancePolicy.evaluate(...)` (criado no Vídeo 02). Conectar essas peças — já todas prontas, mas ainda espalhadas e não integradas — é o assunto anunciado para o Vídeo 07, "Consumindo Dados Complexos".

---

## Pontos de atenção: divergências entre a aula e o seu projeto

Comparando o README e as capturas de tela com o `.zip` real desta etapa, valem os seguintes registros — nenhum deles impede a aplicação de compilar ou de subir:

1. **`localhost` no código, IP de rede local nas capturas de tela — divergência que persiste** — assim como já registrado nos dois tutoriais anteriores, o README mostra as propriedades e os logs com `192.168.64.1:3001` nas capturas de tela, enquanto o `.zip` real usa `url=http://localhost:3001` na propriedade externalizada do `sanction-client`. A explicação mais provável continua a mesma: `192.168.64.1` é um endereço de rede local da máquina onde a aula foi gravada, enquanto `localhost` é a opção mais portável para quem reproduz o projeto em uma única máquina.
2. **Bug inicial no `Fallback` sem `public`, já corrigido no código real** — o README narra que a primeira versão do método `getCompanyRisk` dentro de `Fallback` foi escrita sem o modificador `public`, o que não compilaria. O `.zip` desta etapa já reflete a versão corrigida, com `public` e `@Override` presentes (ver seção 6.5).
3. **Import estático não utilizado em `SanctionResult.java` — ainda presente** — já registrado no tutorial do Vídeo 05, o arquivo real continua com `import static java.util.regex.Pattern.matches;`, sem nenhuma referência a ele no restante do código. Nenhuma mudança nesta etapa.
4. **`getCompanyRisk` retorna `SanctionResult`, mas o valor ainda não é usado** — diferente do que uma leitura apressada do README poderia sugerir (o primeiro trecho de código do README, na seção "Criando a classe de Fallback", mostra a assinatura da interface **antes** de qualquer alteração nesta etapa, com o método já retornando `SanctionResult`), a assinatura de `getCompanyRisk` já havia sido alterada de `void` para `SanctionResult` no próprio Vídeo 05 nos bastidores — o `.zip` do Vídeo 05 já trazia essa assinatura. O que muda de fato nesta etapa é a criação do `Fallback` e a habilitação do circuit breaker, não a assinatura do método em si. `AnalyzeCompanyRiskUseCase.execute` continua sem repassar o valor de `sanctions` adiante (ver seção 6.10).
5. **Pacote `appicantion` continua com o erro de digitação** — já sinalizado nos três tutoriais anteriores; nenhuma classe nova foi adicionada a esse pacote nesta etapa, e a correção continua pendente.
6. **Toolchain Java sem mudanças** — confirmado no `.zip`: o projeto continua declarando `JavaLanguageVersion.of(21)`, divergência com o JDK 25 mencionado na criação do projeto (Vídeo 02) que segue sem impacto prático.

---

## Glossário de conceitos Java e Spring — apenas os termos novos desta etapa

> Este glossário complementa (não substitui) os glossários dos tutoriais anteriores. Aqui aparecem só os termos que ainda não haviam sido explicados nos Vídeos 01 a 05.

### Estrutura da linguagem Java

| Termo | Significado |
|---|---|
| `@Override` | Anotação padrão do Java que marca um método como sobrescrevendo um método de uma superclasse ou de uma interface implementada. Não é obrigatória para o código funcionar, mas faz o compilador acusar erro caso o método não corresponda de fato a nenhum método herdado — evitando bugs silenciosos de assinatura incorreta. |
| Classe aninhada (*nested class*) | Uma `class` declarada dentro de outra `class` ou `interface`, usada quando o tipo aninhado só faz sentido no contexto do tipo que o contém. Mesmo princípio já visto com records aninhados (Vídeo 05), aplicado aqui à classe `Fallback` dentro da interface `SanctionClient`. |

### Anotações, propriedades e bibliotecas

| Termo | Significado |
|---|---|
| `logging.level.<pacote>=<nível>` | Propriedade padrão do Spring Boot que controla, por pacote, o nível mínimo de log exibido no console (`TRACE`, `DEBUG`, `INFO`, `WARN`, `ERROR`). Diferente das propriedades `logger-level` do Feign, que controlam *o que* é registrado; esta controla *se* esse registro chega a ser exibido. |
| `fallback` (atributo de `@FeignClient`) | Atributo da anotação `@FeignClient` que recebe uma classe implementando a mesma interface do client, usada como comportamento alternativo automático sempre que a chamada HTTP real falhar (erro de rede ou status HTTP de erro) — desde que o suporte a circuit breaker do OpenFeign esteja habilitado. |
| `spring.cloud.openfeign.circuitbreaker.enabled` | Propriedade global do Spring Cloud OpenFeign que habilita, para todos os Feign Clients do projeto, o suporte a circuit breaker — pré-requisito para que o atributo `fallback` de `@FeignClient` tenha efeito. |
| Circuit Breaker (disjuntor) | Padrão de tolerância a falhas que monitora o resultado de chamadas a um serviço externo e pode "abrir o circuito" (parar de tentar chamar o serviço e acionar um fallback imediatamente) após falhas sucessivas, testando periodicamente se o serviço já se recuperou antes de voltar ao normal. |
| Resilience4j | Biblioteca Java de tolerância a falhas, independente de framework, usada pelo Spring Cloud como implementação concreta de circuit breaker (e também de rate limiter, time limiter e retry) para os Feign Clients do projeto. |
| `spring-cloud-starter-circuitbreaker-resilience4j` | Dependência (*starter*) do Spring Cloud que integra o Resilience4j ao ecossistema Spring Cloud, fornecendo a implementação de circuit breaker usada pelo OpenFeign quando `spring.cloud.openfeign.circuitbreaker.enabled=true`. |

---

## Estado atual do projeto (checkpoint do Vídeo 06)

> Conferido diretamente contra o código-fonte real em `compliance_ate_o_video06.zip` — não apenas contra a narrativa do README.

### Estrutura de pastas (pacotes alterados nesta etapa)

```
dio.compliance
├── appicantion
│   └── AnalyzeCompanyRiskUseCase.java      (sem mudanças desde o Vídeo 05)
├── domain
│   └── ... (sem mudanças desde o Vídeo 02)
└── infrastructure
    ├── persistence
    │   └── ... (sem mudanças desde o Vídeo 04)
    └── rest
        ├── client
        │   └── SanctionClient.java          (ALTERADO nesta etapa: fallback + @Component)
        └── dto
            └── SanctionResult.java           (sem mudanças desde o Vídeo 05)
```

### `application.properties` (alterado nesta etapa)

```properties
spring.application.name=compliance

spring.cloud.openfeign.client.config.sanction-client.url=http://localhost:3001
spring.cloud.openfeign.client.config.sanction-client.logger-level=full
spring.cloud.openfeign.client.config.sanction-client.default-request-headers.x-api-key=kyc-secret-123

spring.cloud.openfeign.circuitbreaker.enabled=true

logging.level.dio.compliance.infrastructure.rest=DEBUG
```

### `build.gradle` (alterado nesta etapa)

```gradle
plugins {
    id 'java'
    id 'org.springframework.boot' version '4.0.0'
    id 'io.spring.dependency-management' version '1.1.7'

    id("io.freefair.lombok") version "9.2.0"

}

group = 'dio'
version = '0.0.1-SNAPSHOT'
description = 'compliance'

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

ext {
    set('springCloudVersion', "2025.1.1")
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'

    implementation 'org.springframework.data:spring-data-keyvalue'
    implementation 'org.springframework.boot:spring-boot-starter-data-rest'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-actuator'

    implementation 'org.springframework.cloud:spring-cloud-starter-openfeign'

    implementation 'org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j'

}

dependencyManagement {
    imports {
        mavenBom "org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"
    }
}

tasks.named('test') {
    useJUnitPlatform()
}
```

### `infrastructure/rest/client/SanctionClient.java` (alterado nesta etapa)

```java
package dio.compliance.infrastructure.rest.client;

import dio.compliance.infrastructure.rest.dto.SanctionResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "sanction-client", fallback = SanctionClient.Fallback.class)
public interface SanctionClient {

    @GetMapping("/sanctions/companies/{registrationNumber}")
    SanctionResult getCompanyRisk(@PathVariable String registrationNumber);

    @Component
    class Fallback implements SanctionClient {

        @Override
        public SanctionResult getCompanyRisk(String registrationNumber) {
            return new SanctionResult(List.of());
        }
    }
}
```

> Repare que a anotação `@FeignClient` desta etapa **não** declara mais o atributo `url` (diferente do trecho reproduzido no tutorial do Vídeo 05, seção "Estado atual do projeto"). Isso resolve, neste arquivo, a duplicação de URL apontada como pendência nos "Pontos de atenção" dos dois tutoriais anteriores: agora a única fonte da URL do `sanction-client` é a propriedade externa `spring.cloud.openfeign.client.config.sanction-client.url`, no `application.properties`.

### `infrastructure/rest/dto/SanctionResult.java` (confirmado sem mudanças desde o Vídeo 05)

```java
package dio.compliance.infrastructure.rest.dto;

import dio.compliance.domain.ComplianceScreening;

import java.util.List;

import static java.util.regex.Pattern.matches;

public record SanctionResult(List<SanctionMatch> matches) {
    public record SanctionMatch(
            String entity,
            String list,
            String reason,
            Double confidenceScore
    ) {}


    public List<ComplianceScreening.SanctionIdentity> toDomain() {
        if (matches() == null) {
            return List.of();
        }

        return matches().stream()
                .map(match -> new ComplianceScreening.SanctionIdentity(
                        match.entity(),
                        match.list(),
                        match.reason(),
                        match.confidenceScore() != null ? match.confidenceScore() : 0.0
                ))
                .toList();
    }
}
```

### `appicantion/AnalyzeCompanyRiskUseCase.java` (confirmado sem mudanças desde o Vídeo 05)

```java
package dio.compliance.appicantion;

import dio.compliance.domain.Company;
import dio.compliance.infrastructure.rest.client.SanctionClient;
import org.springframework.stereotype.Service;

@Service
public class AnalyzeCompanyRiskUseCase {

    private final SanctionClient sanctionClient;

    public AnalyzeCompanyRiskUseCase(SanctionClient sanctionClient) {
        this.sanctionClient = sanctionClient;
    }

    public void execute(Company domain) {
        var sanctions = sanctionClient.getCompanyRisk(domain.getRegistrationNumber());

    }
}
```

### Arquivos desta etapa que **não mudaram** desde o Vídeo 04/05 (confirmados no `.zip`)

`ComplianceApplication.java`, `settings.gradle`, todos os arquivos de `domain/` (`Company.java`, `CompanyId.java`, `CompanyRepository.java`, `CompliancePolicy.java`, `ComplianceScreening.java`, `RiskAssessment.java`, `RiskAssessmentStatus.java`, `RiskLevel.java`) e todos os arquivos de `infrastructure/persistence/` (`CompanyEntity.java`, `CompanyEventHandler.java`, `CompanyEntityRepository.java`, `InMemoryCompanyRepository.java`) permanecem idênticos ao checkpoint dos tutoriais anteriores.

---

## Próximos passos: o que vem a partir do Vídeo 07

Com o fallback do `SanctionClient` implementado e o circuit breaker do Resilience4j habilitado — protegendo o fluxo de cadastro de empresas contra falhas da API de sanções, seja por erro (500) ou por indisponibilidade — o roteiro restante do curso (conferido no README) é:

- **Vídeo 07 — Consumindo Dados Complexos:** deve, finalmente, usar de fato o valor de `sanctions` dentro de `AnalyzeCompanyRiskUseCase` — que, até esta etapa, é apenas atribuído a uma variável local e descartado (seção 6.10) —, chamando `toDomain()` (pronto desde o Vídeo 05) para obter uma lista de `ComplianceScreening.SanctionIdentity`, e conectando esse resultado à lógica de `CompliancePolicy.evaluate(...)` (criada no Vídeo 02, mas ainda não usada em nenhum fluxo real), populando finalmente o `RiskAssessment` de uma `Company`.
- **Vídeo 08 — Estratégias de Tolerância a Falhas:** deve aprofundar o que foi apenas habilitado nesta etapa — os detalhes de configuração fina do circuit breaker do Resilience4j (limiares de falha, tempo de espera no estado aberto, chamadas de teste no estado semiaberto) e, possivelmente, outros recursos da própria biblioteca mencionados na seção 6.8 deste documento (retry, time limiter) — tema já anunciado desde o Vídeo 01.

> **Sugestão de uso deste documento**
> Depois de assistir a cada novo vídeo, crie um novo arquivo (por exemplo, `005-Tutorial_Compliance_OpenFeign_Video07.md`) seguindo o mesmo formato: bloco de código → explicação linha a linha → checkpoint fiel ao `.zip` mais recente → atualização do glossário e dos próximos passos. Isso mantém o material sempre alinhado ao seu ritmo de estudo.

---

*Este documento cobre o Vídeo 06 do curso, dando continuidade a `003-Tutorial_Compliance_OpenFeign_Video05.md`. O próximo tutorial da série deve continuar a partir daqui, documentando o Vídeo 07 ("Consumindo Dados Complexos") com o mesmo nível de detalhe.*
