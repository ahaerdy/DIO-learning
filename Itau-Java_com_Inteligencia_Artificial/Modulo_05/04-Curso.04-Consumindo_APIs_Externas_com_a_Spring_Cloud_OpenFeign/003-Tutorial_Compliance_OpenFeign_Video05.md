# Tutorial de Estudos — Consumindo APIs Externas com o Spring Cloud OpenFeign

**Monitorando Requisições e Respostas: configuração externa, DTOs e autenticação no Feign — Vídeo 05**

- Curso: NTT Data — Jornada Tech (DIO) · Módulo 04 — Java/Spring com IA
- Curso 4 do módulo: "Consumindo APIs Externas com a Spring Cloud OpenFeign"
- Instrutor: Thiago Poiani (Principal Engineer at Skip)
- Projeto: `compliance`
- Documento de referência pessoal — nível iniciante em Java
- **Continuação direta de** `002-Tutorial_Compliance_OpenFeign_Videos03a04.md`

---

## Sobre este documento

Este é o terceiro tutorial da série. Ele parte exatamente de onde o anterior parou (fim do Vídeo 04: primeiro Feign Client funcionando de ponta a ponta, com `SanctionClient.getCompanyRisk(...)` chamado a partir de `AnalyzeCompanyRiskUseCase`, mas ainda sem tratar a resposta, sem configuração externa e sem autenticação) e documenta o **Vídeo 05**, com base:

- no README atualizado (anotações de aula até o Vídeo 05);
- na transcrição do Vídeo 05 (`transcricao.md`);
- no código-fonte real do projeto `compliance`, no estado correspondente ao `compliance_ate_o_video05.zip`.

O mesmo compromisso dos documentos anteriores vale aqui: cada bloco de código é apresentado na ordem em que foi escrito na aula, seguido de explicação em nível iniciante — o que cada instrução faz, por que foi escrita daquela forma, e qual conceito de Java, Spring ou de arquitetura ela representa. Sempre que aparece uma anotação, propriedade, classe da biblioteca padrão ou padrão de projeto ainda não visto nos tutoriais anteriores, ele é explicado do zero.

> **Sobre o título da etapa**
> O README chama o Vídeo 05 de "Monitorando Requisições e Respostas", mas, na prática, o conteúdo do vídeo é mais amplo do que só monitoramento: ele cobre a externalização de configuração do Feign Client, a criação do primeiro DTO de resposta, a simulação de autenticação no Mockoon e a configuração de um header padrão de autenticação no Feign. O "monitoramento" propriamente dito aparece de forma mais indireta, através do `logger-level=full` (que deixa os logs das requisições Feign visíveis) e da leitura dos logs do Mockoon para confirmar cada tentativa de chamada.

---

## Parte 5 — Vídeo 05: Monitorando Requisições e Respostas

### 5.1. Externalizando a configuração do `sanction-client` para `application.properties`

Até o fim do Vídeo 04, o `application.properties` continha apenas uma linha (`spring.application.name=compliance`), e a URL do `SanctionClient` estava fixa (*hardcoded*) diretamente no atributo `url` da anotação `@FeignClient`. A primeira mudança do Vídeo 05 é mover parte dessa configuração para fora do código Java:

```properties
spring.application.name=compliance

spring.cloud.openfeign.client.config.sanction-client.url=http://localhost:3001
spring.cloud.openfeign.client.config.sanction-client.logger-level=full
```

- **`spring.cloud.openfeign.client.config.<nome>.*`** — este é o prefixo de propriedades que o Spring Cloud OpenFeign reconhece para configurar um Feign Client específico **pelo nome** que ele recebeu na anotação `@FeignClient(name = "sanction-client", ...)`. É assim que o Spring liga a propriedade do arquivo `.properties` ao client Java correto: pelo texto `sanction-client`, que precisa ser idêntico ao `name` usado na interface.
- **`.url=http://localhost:3001`** — define, fora do código Java, o endereço base que esse client vai chamar. A vantagem central, como a própria aula explica, é que uma propriedade pode ser **sobrescrita** por variáveis de ambiente, por argumentos de linha de comando ou por outros mecanismos padrão do Spring Boot — sem precisar alterar uma linha de código, recompilar e reimplantar a aplicação. Se o endereço do serviço de sanções mudar (por exemplo, ao ir de ambiente de testes para produção), basta trocar o valor da variável de ambiente correspondente.
- **`.logger-level=full`** — controla o quanto o Feign registra nos logs sobre cada requisição feita por esse client. O OpenFeign define quatro níveis, do mais silencioso ao mais detalhado:
  - `NONE` — nenhum log (padrão);
  - `BASIC` — só o método HTTP, a URL, o código de status da resposta e o tempo de execução;
  - `HEADERS` — tudo o que `BASIC` já mostra, mais os cabeçalhos de requisição e resposta;
  - `FULL` — tudo o que `HEADERS` já mostra, mais o corpo (*body*) da requisição, da resposta e metadados adicionais.

  Usar `full` durante o desenvolvimento é o que permite, mais adiante nesta mesma etapa (seção 5.7), enxergar no console da aplicação o motivo exato de uma falha de autenticação — o próprio stack trace do `feign.FeignException$Unauthorized`.

> **O que é um *error decoder* (mencionado na aula, mas ainda não implementado)?**
> Durante a explicação das propriedades disponíveis para um Feign Client, a aula cita o conceito de **error decoder**: uma classe que o desenvolvedor pode implementar para "traduzir" respostas de erro HTTP (como 404 *Not Found* ou 500 *Internal Server Error*) em exceções customizadas da camada de domínio da aplicação, em vez de deixar vazar exceções genéricas do Feign. Até o Vídeo 05, nenhum error decoder próprio foi criado — o projeto ainda usa o comportamento padrão do OpenFeign, que lança subclasses de `feign.FeignException` (isso fica visível na prática na seção 5.7). Esse é um tema que a própria aula indica como assunto dos próximos vídeos (Vídeo 06, sobre cenários de exceção).

> **Ponto de atenção: a URL continua duplicada**
> A interface `SanctionClient` (criada no Vídeo 04) **não foi alterada** neste passo — ela continua com a URL fixa na própria anotação:
> ```java
> @FeignClient(name = "sanction-client", url = "http://localhost:3001" )
> ```
> Ou seja, a partir de agora a mesma informação (a URL base do serviço) existe em dois lugares ao mesmo tempo: no atributo `url` da anotação e na propriedade `spring.cloud.openfeign.client.config.sanction-client.url`. Isso não impede a aplicação de funcionar — mas é uma duplicação que, idealmente, deveria ser resolvida removendo o atributo `url` da anotação (deixando só `name`) e confiando inteiramente na propriedade externa, já que essa era a motivação original da mudança (evitar informação fixa no código). Fica registrado aqui como um ajuste pendente, sem risco de quebrar nada.

### 5.2. Criando o pacote `dto` e o record `SanctionResult` (vazio)

Antes de criar qualquer tipo, é criado um novo pacote dentro de `infrastructure.rest`, paralelo ao pacote `client` já existente:

```
dio.compliance.infrastructure.rest
├── client     (interfaces @FeignClient, ex.: SanctionClient)
└── dto        (tipos que representam o formato de dados trocado com a API externa)
```

- **`dto` (*Data Transfer Object*)** — convenção de nomenclatura para um pacote (ou classe) cuja única responsabilidade é carregar dados entre duas camadas ou dois sistemas, sem conter lógica de negócio. Aqui, o DTO vai representar exatamente o formato de resposta JSON que a API mocada de sanções devolve — um formato que não é, necessariamente, igual ao formato usado internamente pelo domínio da aplicação (`ComplianceScreening`, criado no Vídeo 02).

Dentro desse pacote, é criado o record `SanctionResult`, inicialmente vazio, acompanhado de um comentário de bloco que documenta o formato de resposta planejado para a API mocada:

```java
package dio.compliance.infrastructure.rest.dto;

public record SanctionResult() {
}

/*
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
*/
```

- **`public record SanctionResult() { }`** — um record (já visto desde o Vídeo 02, em `CompanyId` e `ComplianceScreening`) pode, tecnicamente, ser criado sem nenhum componente — é só um passo intermediário aqui, já que os campos reais são adicionados na próxima seção.
- **Comentário de bloco (`/* ... */`) como "rascunho de contrato"** — é uma prática comum, quando não se tem acesso ao código-fonte da API externa, esboçar primeiro o JSON esperado (baseado na documentação, em exemplos ou, como neste curso, no que foi configurado no próprio mock) e só depois modelar as classes Java que vão representar esse formato. Isso reduz o risco de esquecer um campo ou de dar um nome errado a ele.
- **`{{urlParam 'registrationNumber'}}`** — essa sintaxe com chaves duplas não é Java: é a linguagem de *templates* do próprio Mockoon (baseada em Handlebars), usada dentro do corpo de uma resposta mocada para inserir, dinamicamente, o valor de um parâmetro de caminho (*path parameter*) recebido na requisição. Na prática, isso faz o mock "ecoar" de volta o número de registro da empresa que foi consultado, simulando uma resposta mais realista do que um valor fixo.

### 5.3. Completando `SanctionResult` com a lista de matches

```java
package dio.compliance.infrastructure.rest.dto;

import java.util.List;

public record SanctionResult(List<SanctionMatch> matches) {
    public record SanctionMatch(
            String entity,
            String list,
            String reason,
            Double confidenceScore
    ) {}
}
```

- **`public record SanctionResult(List<SanctionMatch> matches) { ... }`** — o record ganha seu primeiro (e único) componente: `matches`, uma lista de `SanctionMatch`. Isso espelha diretamente a estrutura do JSON esboçado na seção anterior, que tem uma chave `"matches"` contendo um array de objetos.
- **Record aninhado (`SanctionMatch` dentro de `SanctionResult`)** — declarar um record *dentro* de outro (em vez de em um arquivo `.java` separado) é uma forma de comunicar, pela própria estrutura do código, que `SanctionMatch` **só faz sentido no contexto de** `SanctionResult` — ele não é reutilizado em nenhum outro lugar do domínio. É o mesmo recurso de linguagem já usado em `ComplianceScreening` (Vídeo 02), que aninha os records `SanctionIdentity`, `AmlProfile` e `PoliticalExposure`.
- **Os quatro campos de `SanctionMatch`** — `entity`, `list` e `reason` são declarados como `String`; `confidenceScore` é declarado como `Double` (com **D** maiúsculo), e não como `double` (minúsculo). Essa escolha é intencional: `double` é um tipo primitivo do Java e **nunca pode ser nulo** — ele sempre tem algum valor numérico, mesmo que seja `0.0` por padrão. Já `Double` é a **classe wrapper** (envoltório) de `double`, um objeto de verdade, que **pode ser `null`**. Como o JSON de uma API externa pode, em tese, simplesmente omitir o campo `confidenceScore` em algum cenário, usar `Double` permite representar essa ausência de forma explícita (`null`), em vez de forçar um valor numérico que talvez não reflita a realidade. Essa decisão só faz sentido plenamente na próxima seção, quando o valor `null` é tratado.

### 5.4. Convertendo `SanctionResult` em `SanctionIdentity` com `toDomain()`

```java
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
```

Esse método segue exatamente o mesmo padrão de conversão já visto em `CompanyEntity.toDomain()` (Vídeo 03): uma classe da camada de infraestrutura sabe se converter para o tipo de domínio equivalente, mantendo a fronteira entre as duas camadas bem definida — quem chama esse método não precisa conhecer nada sobre o formato JSON da API externa, só recebe uma lista pronta de `ComplianceScreening.SanctionIdentity` (o record de domínio criado no Vídeo 02).

- **`if (matches() == null) { return List.of(); }`** — como `matches` pode, em tese, não vir preenchido na resposta da API (um `record` não impede que um componente do tipo `List` seja `null`), esse `if` evita que o restante do método tente chamar `.stream()` em cima de um valor nulo, o que lançaria um `NullPointerException`. Em vez disso, devolve-se uma lista vazia (`List.of()`, um método de fábrica estático do Java que cria uma lista imutável sem elementos) — um resultado seguro de se trabalhar mais adiante, sem exigir que quem chama esse método também precise checar `null`.
- **`matches().stream()`** — como já visto em `CompliancePolicy.evaluate(...)` (Vídeo 02), transforma a `List<SanctionMatch>` em uma `Stream<SanctionMatch>`, habilitando operações encadeáveis como `.map(...)`.
- **`.map(match -> new ComplianceScreening.SanctionIdentity(...))`** — para **cada** `SanctionMatch` da lista original, essa operação de Stream cria um novo objeto `ComplianceScreening.SanctionIdentity` correspondente, montando uma nova sequência (transformada) de elementos. `match -> ...` é uma expressão lambda (já vista no Vídeo 02): `match` é o parâmetro (cada elemento da lista, um de cada vez) e o que vem depois da seta é o que deve ser calculado/retornado para esse elemento.
- **`match.confidenceScore() != null ? match.confidenceScore() : 0.0`** — primeiro uso, nesta série de tutoriais, do **operador ternário** do Java. Sua sintaxe é `condição ? valorSeVerdadeiro : valorSeFalso`. Aqui: *se* `match.confidenceScore()` não for `null`, o próprio valor é usado; *senão*, usa-se `0.0` como valor padrão. É uma forma compacta de escrever, em uma única linha, o que equivaleria a:
  ```java
  double confidence;
  if (match.confidenceScore() != null) {
      confidence = match.confidenceScore();
  } else {
      confidence = 0.0;
  }
  ```
  Esse tratamento existe justamente por causa da escolha de `Double` (e não `double`) feita na seção 5.3: como o valor recebido da API pode ser `null`, é preciso decidir explicitamente um valor padrão antes de encaixá-lo no campo `confidence` de `SanctionIdentity`, que é um `double` primitivo (e por isso não aceitaria `null` diretamente).
- **`.toList()`** — operação final da Stream (já vista no Vídeo 02) que coleta todos os elementos transformados de volta em uma `List<ComplianceScreening.SanctionIdentity>`, o tipo de retorno declarado no método.

> **Um detalhe curioso do código real: um import sem uso**
> O `.zip` revela que o arquivo `SanctionResult.java` importa `java.util.regex.Pattern.matches` de forma estática (`import static java.util.regex.Pattern.matches;`), mas esse import **não é usado em nenhum lugar do arquivo** — provavelmente um autocompletar do IntelliJ que sugeriu, por engano, um método estático chamado `matches` (da classe `Pattern`, usada para expressões regulares) quando o nome coincidiu com o componente `matches` do próprio record. Isso não afeta a compilação nem o comportamento da aplicação (imports não usados geram, no máximo, um aviso do compilador), mas é um bom lembrete de revisar sugestões automáticas da IDE antes de aceitá-las.

> **Onde esse `toDomain()` é usado até agora?**
> Em lugar nenhum ainda. O método `getCompanyRisk` do `SanctionClient` continua declarado como `void` (não retorna nada), e `AnalyzeCompanyRiskUseCase.execute` continua apenas chamando `sanctionClient.getCompanyRisk(...)` e descartando qualquer resposta. Ou seja: `SanctionResult` e seu `toDomain()` já estão prontos, mas ainda não estão **conectados** ao restante do fluxo — isso é consistente com a previsão feita no tutorial anterior de que o consumo efetivo da resposta HTTP fica para o Vídeo 07 ("Consumindo Dados Complexos").

### 5.5. Simulando autenticação no Mockoon: rota curinga e regra de header

Com o DTO pronto, a aula volta ao Mockoon (ambiente "KYC", `localhost:3001`) para simular um cenário mais realista: exigir autenticação antes de responder qualquer rota de sanções.

- **Nova rota curinga (`/*`, todos os métodos HTTP)** — no Mockoon, o caractere `*` é um coringa que faz uma rota casar com **qualquer** caminho de URL; combinada com a opção "todos os métodos", essa rota intercepta literalmente qualquer requisição recebida pelo ambiente KYC, não importa a URL nem o verbo HTTP usado.
- **Posicionamento da rota e "Fallback response mode"** — essa nova rota é posicionada **antes** da rota de sanções já existente (`GET /sanctions/companies/:registrationNumber`), e configurada com o modo de resposta *Fallback*. Na prática, isso faz essa rota funcionar como um **guarda** (*guard clause*) na frente de todas as demais: toda requisição passa primeiro por ela; se a regra configurada nessa rota **não** for satisfeita, o Mockoon responde imediatamente com o que estiver configurado ali (neste caso, `401 Unauthorized`), sem sequer chegar até a rota real de sanções; se a regra **for** satisfeita, o Mockoon deixa a requisição seguir adiante para a próxima rota que também combine com ela.
- **Regra na aba "Rules": header `X-API-Key` obrigatório** — dentro dessa rota curinga, é configurada uma regra que verifica se o header `X-API-Key` da requisição recebida é exatamente igual ao valor `kyc-secret-123`. Esse é um padrão simples e comum de autenticação entre serviços conhecido como **API key**: um valor secreto fixo, enviado em um header a cada requisição, que a API que recebe compara com o valor esperado antes de processar qualquer coisa.

> **Por que simular autenticação, e não só o retorno de sucesso?**
> Uma API externa real quase nunca aceita chamadas sem nenhuma forma de autenticação. Simular esse comportamento no mock — incluindo o cenário de falha (401 sem o header correto) — prepara o terreno para o próximo passo da aula: fazer o `SanctionClient` também enviar essa credencial em toda chamada, o que é exatamente o tipo de configuração que só faz sentido existir porque a API "do outro lado" exige.

### 5.6. Testando a regra de autenticação diretamente no arquivo `.http`

Antes de alterar qualquer código Java, a regra recém-criada no Mockoon é validada isoladamente, usando o **Cliente HTTP do IntelliJ** — um recurso embutido da IDE que permite escrever requisições HTTP em arquivos de texto simples (extensão `.http`) e executá-las com um clique, sem precisar de uma ferramenta externa como o Postman.

```http
GET http://192.168.64.1:3001/sanctions/companies/123
X-API-KEY: kyc-secret-123
```

```json
{
  "matches": []
}
```

- Uma requisição `GET` é escrita apontando diretamente para a rota de sanções do mock, **com** o header `X-API-KEY` e o valor esperado (`kyc-secret-123`). A resposta é `200 OK`, com um corpo `{"matches": []}` — confirmando que, com o header correto, a requisição atravessa a rota curinga (a regra é satisfeita) e chega até a rota real de sanções, que responde ao cenário "empresa sem riscos" configurado desde o Vídeo 04.
- Uma segunda tentativa, **sem** o header, é usada como contraste: a resposta passa a ser `401 Unauthorized` — a rota curinga intercepta a chamada antes de ela alcançar a rota de sanções, exatamente como configurado na seção anterior.

Esse teste isolado (direto no mock, sem passar pela aplicação `compliance`) é uma boa prática: ele confirma que o **mock está se comportando como esperado** antes de qualquer ajuste ser feito no lado do Feign Client, isolando onde exatamente um eventual problema estaria.

### 5.7. Reproduzindo a falha de autenticação pelo fluxo real da aplicação

Com a regra confirmada isoladamente, a aula volta ao fluxo real da aplicação `compliance` — que, neste ponto, **ainda não envia** o header `X-API-Key` em nenhuma chamada feita pelo `SanctionClient`.

```http
POST http://localhost:8080/companies
Accept: application/json

{
  "name": "Logistics",
  "registrationNumber": "REG-1234"
}
```

```json
{
  "timestamp": "2026-03-31T10:29:25.769Z",
  "status": 500,
  "error": "Internal Server Error",
  "path": "/companies"
}
```

Uma nova empresa é criada via `POST /companies` (o mesmo endpoint gerado automaticamente pelo Spring Data REST desde o Vídeo 03). Isso dispara a cadeia já conhecida: `handleAfterCreateEvent` → `AnalyzeCompanyRiskUseCase.execute` → `sanctionClient.getCompanyRisk(...)`. Só que, dessa vez, a chamada ao Mockoon **falha**, e esse erro se propaga de volta até quem chamou o endpoint `/companies`, que recebe **`500 Internal Server Error`** — o comportamento padrão do Spring Boot quando uma exceção não tratada (`RuntimeException` ou subclasse) sobe até o controller sem ser capturada em nenhum ponto do caminho.

Consultando o console de logs da aplicação (habilitado em detalhe graças ao `logger-level=full` configurado na seção 5.1), a causa exata aparece:

```
feign.FeignException$Unauthorized: [401 Unauthorized] during [GET] to [http://192.168.64.1:3001/sanctions/companies/REG-1234]
    at feign.FeignException.clientErrorStatus(FeignException.java:245)
    at feign.FeignException.errorStatus(FeignException.java:223)
    at feign.codec.ErrorDecoder$Default.decode(ErrorDecoder.java:103)
    at feign.InvocationContext.decodeError(...)
    at feign.ResponseHandler.handleResponse(...)
    at feign.SynchronousMethodHandler.execute(SynchronousMethodHandler.java:109)
```

- **`feign.FeignException`** — a classe-base de exceção que o OpenFeign lança sempre que uma chamada HTTP recebe um código de status de erro (4xx ou 5xx) e nenhum error decoder customizado foi configurado para tratá-la de outra forma (ver o comentário sobre *error decoder*, na seção 5.1). O Feign define **subclasses específicas** para os códigos de status mais comuns — aqui, `FeignException.Unauthorized` (exibida no log como `FeignException$Unauthorized`, usando a notação de classe interna do Java) corresponde especificamente ao status `401`.
- **`feign.codec.ErrorDecoder$Default.decode(...)`** — essa linha do stack trace mostra o **`ErrorDecoder` padrão** do Feign efetivamente em ação: é ele quem, ao ver o status `401` na resposta HTTP, decide lançar um `FeignException.Unauthorized`. Isso conecta diretamente com o conceito apresentado (mas ainda não customizado) na seção 5.1 — o comportamento que um error decoder próprio poderia substituir por uma exceção mais específica do domínio da aplicação.
- Essa exceção não é tratada em nenhum ponto entre `SanctionClient` e o controller REST gerado pelo Spring Data REST — por isso ela "estoura" e vira um `500` genérico na resposta HTTP para quem criou a empresa, mesmo o problema real sendo, tecnicamente, uma falha de autenticação com um serviço de terceiros.

### 5.8. Configurando o header padrão `X-API-Key` no Feign Client

A aula apresenta três formas possíveis de fazer o `SanctionClient` enviar o header de autenticação: (1) uma classe de configuração Java declarando um esquema de autenticação (como Basic Auth); (2) um **interceptor**, uma classe que intercepta e pode modificar toda requisição antes dela ser efetivamente disparada; ou (3) diretamente pelo `application.properties`, adicionando um header padrão à configuração do client. A aula opta pela terceira, por ser a mais simples para este cenário:

```properties
spring.application.name=compliance

spring.cloud.openfeign.client.config.sanction-client.url=http://localhost:3001
spring.cloud.openfeign.client.config.sanction-client.logger-level=full
spring.cloud.openfeign.client.config.sanction-client.default-request-headers.x-api-key=kyc-secret-123
```

- **`spring.cloud.openfeign.client.config.sanction-client.default-request-headers.x-api-key=kyc-secret-123`** — uma nova propriedade, sob o mesmo prefixo de configuração do `sanction-client` já usado na seção 5.1. `default-request-headers` é o "bloco" de configuração do OpenFeign que permite declarar headers que devem ser enviados **automaticamente em toda requisição** feita por aquele client, sem que o código de `SanctionClient` ou de `AnalyzeCompanyRiskUseCase` precise saber que esse header existe. O nome do header (`x-api-key`) vira parte do caminho da propriedade, e o valor (`kyc-secret-123`) é o próprio conteúdo enviado.
- Essa abordagem mantém a credencial (assim como a URL, na seção 5.1) fora do código Java — permitindo, por exemplo, sobrescrever esse valor por ambiente (desenvolvimento, testes, produção) sem recompilar a aplicação. Em um cenário real de produção, o valor não ficaria hardcoded desse jeito no `.properties` versionado no Git; normalmente viria de uma variável de ambiente ou de um cofre de segredos — mas, para os fins didáticos desta etapa do curso, o valor fixo é suficiente para demonstrar o mecanismo.

Depois de salvar essa propriedade, a aplicação é reiniciada para que a nova configuração do Feign seja carregada.

### 5.9. Confirmando o fluxo autenticado de ponta a ponta

Com a aplicação reiniciada, a mesma requisição de criação de empresa é repetida:

```json
{
  "href": "http://localhost:8080/companies/0c1b513e-0834-48de-962e-090b12837467",
  "companyEntity": {
    "href": "http://localhost:8080/companies/0c1b513e-0834-48de-962e-090b12837467"
  },
  "name": "Logistics",
  "registrationNumber": "REG-1234",
  "riskAssessment": null
}
```

Dessa vez, a resposta é de sucesso: o corpo traz o `href` do recurso criado, os dados da empresa (`name`, `registrationNumber`) e o campo `riskAssessment`, ainda `null` — o que é esperado, já que (como registrado na seção 5.4) `AnalyzeCompanyRiskUseCase` ainda não usa o resultado da chamada ao `SanctionClient` para preencher nada na empresa.

Na aba **Logs** do Mockoon, a confirmação final: a requisição `GET /sanctions/companies/REG-1234` aparece registrada com status **200**, e o detalhe da requisição capturada mostra o header `X-API-Key` chegando corretamente — validando, de ponta a ponta, que o Feign Client agora está autenticado ao consumir a API mocada de sanções.

---

## Pontos de atenção: divergências entre a aula e o seu projeto

Comparando o README, a transcrição e as capturas de tela com o `.zip` real desta etapa, valem os seguintes registros — nenhum deles impede a aplicação de compilar ou de subir:

1. **URL duplicada entre a anotação `@FeignClient` e a propriedade externa** — como já registrado na seção 5.1, a interface `SanctionClient` continua com `url = "http://localhost:3001"` fixo na anotação, mesmo depois de essa mesma informação passar a existir também em `spring.cloud.openfeign.client.config.sanction-client.url`. O objetivo original da externalização (evitar valor fixo no código) fica parcialmente comprometido enquanto essa duplicação não for resolvida — o ajuste natural seria remover o atributo `url` da anotação, deixando `@FeignClient(name = "sanction-client")`.
2. **`localhost` no código, IP de rede local nas capturas de tela — divergência que persiste** — assim como já registrado no tutorial anterior para a anotação `@FeignClient`, o README desta etapa mostra as propriedades configuradas com `url=http://192.168.64.1:3001` nas capturas de tela, enquanto o `.zip` real usa `url=http://localhost:3001` tanto na anotação quanto na propriedade. A explicação provável continua a mesma: `192.168.64.1` é um endereço de rede local da máquina onde a aula foi gravada (possivelmente por rodar o Mockoon em uma VM ou ambiente separado), enquanto `localhost` é a opção mais portável para quem reproduz o projeto em uma única máquina.
3. **`getCompanyRisk` continua retornando `void`** — mesmo com `SanctionResult` e seu método `toDomain()` prontos nesta etapa, a assinatura do método no `SanctionClient` não foi alterada para retornar `SanctionResult`. Isso é esperado (ver seção 5.4) e não é um erro: a conexão entre a chamada HTTP e o processamento da resposta é, pela própria estrutura do curso, assunto do Vídeo 07.
4. **Import estático não utilizado em `SanctionResult.java`** — o arquivo real contém `import static java.util.regex.Pattern.matches;`, que não é referenciado em nenhum lugar do código (ver observação na seção 5.4). Não afeta a compilação, mas pode ser removido com segurança em uma limpeza futura.
5. **Pacote `appicantion` continua com o erro de digitação** — já sinalizado nos dois tutoriais anteriores; nesta etapa nenhuma classe nova foi adicionada a esse pacote (`AnalyzeCompanyRiskUseCase.java` permanece idêntico ao Vídeo 04), mas a correção continua pendente.
6. **`build.gradle` e toolchain Java sem mudanças** — confirmado no `.zip`: nenhuma dependência nova foi adicionada nesta etapa (não houve necessidade, já que tudo usado — `record`, `Stream`, propriedades do OpenFeign — já estava disponível desde vídeos anteriores), e o toolchain continua em `JavaLanguageVersion.of(21)`, divergência com o JDK 25 mencionado na criação do projeto que segue sem impacto prático.

---

## Glossário de conceitos Java e Spring — apenas os termos novos desta etapa

> Este glossário complementa (não substitui) os glossários dos tutoriais anteriores. Aqui aparecem só os termos que ainda não haviam sido explicados nos Vídeos 01 a 04.

### Estrutura da linguagem Java

| Termo | Significado |
|---|---|
| Operador ternário (`condição ? a : b`) | Forma compacta de uma expressão condicional: avalia `condição` e retorna `a` se for verdadeira, ou `b` se for falsa. Equivale a um `if/else` de uma linha que **retorna um valor**. |
| `Double` vs `double` | `double` é um tipo primitivo do Java, sempre numérico, nunca `null`. `Double` é a classe *wrapper* (envoltório) correspondente, um objeto que pode assumir o valor `null` — útil para representar a ausência de um dado numérico vindo de uma fonte externa, como uma API. |
| Record aninhado (*nested record*) | Um `record` declarado dentro de outro `record` (ou classe), usado quando o tipo aninhado só faz sentido no contexto do tipo que o contém, sem uso em nenhum outro lugar do código. |
| Import estático (`import static ...`) | Forma de `import` que traz, para uso direto (sem prefixo de classe), um método ou campo `static` de outra classe. Diferente do `import` comum, que traz apenas o nome da classe. |
| `List.of()` | Método de fábrica estático da interface `List` que cria uma lista **imutável** e vazia (ou, com argumentos, com os elementos informados), sem precisar instanciar uma implementação concreta como `ArrayList` diretamente. |

### Anotações, propriedades e bibliotecas

| Termo | Significado |
|---|---|
| `spring.cloud.openfeign.client.config.<nome>.*` | Prefixo de propriedades do Spring Cloud OpenFeign usado para configurar, via `application.properties` (ou `.yml`), um Feign Client específico — identificado pelo `<nome>`, que deve ser igual ao `name` usado na anotação `@FeignClient`. |
| `logger-level` (Feign) | Propriedade que define o nível de detalhe dos logs de um Feign Client: `NONE` (nenhum), `BASIC` (método, URL, status, tempo), `HEADERS` (`BASIC` + cabeçalhos) ou `FULL` (`HEADERS` + corpo da requisição/resposta). |
| `default-request-headers` (Feign) | Bloco de configuração do OpenFeign que permite declarar, via propriedades, headers HTTP enviados automaticamente em **toda** requisição feita por um Feign Client específico — sem alterar o código Java do client. |
| `ErrorDecoder` (OpenFeign) | Interface do Feign responsável por converter uma resposta HTTP de erro (status 4xx/5xx) em uma exceção Java. O comportamento padrão (`ErrorDecoder.Default`) lança subclasses de `FeignException` correspondentes ao status recebido; pode ser substituído por uma implementação customizada para mapear erros para exceções de domínio. |
| `feign.FeignException` | Classe de exceção lançada pelo OpenFeign quando uma chamada HTTP recebe um status de erro. Possui subclasses específicas por status (ex.: `FeignException.Unauthorized` para 401, `FeignException.NotFound` para 404), usadas pelo `ErrorDecoder` padrão. |
| Cliente HTTP do IntelliJ (arquivos `.http`) | Recurso embutido da IDE IntelliJ que permite escrever requisições HTTP em texto simples, em arquivos com extensão `.http`, e executá-las com um clique — sem depender de uma ferramenta externa como Postman ou Insomnia. |
| API key (chave de API) | Padrão simples de autenticação entre serviços em que um valor secreto fixo é enviado em um header (ou parâmetro) a cada requisição, e a API que recebe compara esse valor com o que é esperado antes de processar a chamada. |
| Rota curinga / *fallback* (Mockoon) | Recurso do Mockoon para configurar uma rota que casa com qualquer caminho de URL (`*`) e qualquer método HTTP, posicionada antes das demais rotas para agir como um "guarda": se uma regra configurada nela não for satisfeita, ela responde diretamente (encerrando o fluxo); se for satisfeita, deixa a requisição seguir para as próximas rotas que também combinarem. |

### Arquitetura e padrões de projeto

| Termo | Significado |
|---|---|
| DTO (*Data Transfer Object*) | Classe (ou record) cuja única responsabilidade é carregar dados entre camadas ou sistemas diferentes, sem lógica de negócio — usada aqui para representar o formato de resposta de uma API externa antes de convertê-lo para o modelo de domínio. |
| Rascunho de contrato (*contract sketch*) | Prática de esboçar o formato de dados esperado (ex.: um JSON de exemplo, em comentário) antes de modelar as classes/records que vão representá-lo em código, reduzindo o risco de divergência entre o que a API realmente envia e o que o código espera. |

---

## Estado atual do projeto (checkpoint do Vídeo 05)

> Conferido diretamente contra o código-fonte real em `compliance_ate_o_video05.zip` — não apenas contra a narrativa do README.

### Estrutura de pastas (pacotes alterados nesta etapa)

```
dio.compliance
├── appicantion
│   └── AnalyzeCompanyRiskUseCase.java      (sem mudanças desde o Vídeo 04)
├── domain
│   └── ... (sem mudanças desde o Vídeo 02)
└── infrastructure
    ├── persistence
    │   └── ... (sem mudanças desde o Vídeo 04)
    └── rest
        ├── client
        │   └── SanctionClient.java          (sem mudanças desde o Vídeo 04)
        └── dto                                (NOVO pacote nesta etapa)
            └── SanctionResult.java            (NOVO arquivo nesta etapa)
```

### `application.properties` (alterado nesta etapa)

```properties
spring.application.name=compliance

spring.cloud.openfeign.client.config.sanction-client.url=http://localhost:3001
spring.cloud.openfeign.client.config.sanction-client.logger-level=full
spring.cloud.openfeign.client.config.sanction-client.default-request-headers.x-api-key=kyc-secret-123
```

### `infrastructure/rest/dto/SanctionResult.java` (novo nesta etapa)

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

> O import estático `java.util.regex.Pattern.matches` está presente no arquivo real, mas não é usado (ver seção 5.4 e "Pontos de atenção", item 4).

### `infrastructure/rest/client/SanctionClient.java` (confirmado sem mudanças desde o Vídeo 04)

```java
package dio.compliance.infrastructure.rest.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "sanction-client", url = "http://localhost:3001" )
public interface SanctionClient {

    @GetMapping("/sanctions/companies/{registrationNumber}")
    void getCompanyRisk(@PathVariable String registrationNumber);

}
```

### `appicantion/AnalyzeCompanyRiskUseCase.java` (confirmado sem mudanças desde o Vídeo 04)

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
        sanctionClient.getCompanyRisk(domain.getRegistrationNumber());

    }
}
```

### Arquivos desta etapa que **não mudaram** desde o Vídeo 04 (confirmados no `.zip`)

`ComplianceApplication.java`, `build.gradle`, `settings.gradle`, todos os arquivos de `domain/` (`Company.java`, `CompanyId.java`, `CompanyRepository.java`, `CompliancePolicy.java`, `ComplianceScreening.java`, `RiskAssessment.java`, `RiskAssessmentStatus.java`, `RiskLevel.java`) e todos os arquivos de `infrastructure/persistence/` (`CompanyEntity.java`, `CompanyEventHandler.java`, `CompanyEntityRepository.java`, `InMemoryCompanyRepository.java`) permanecem idênticos ao checkpoint do tutorial anterior.

---

## Próximos passos: o que vem a partir do Vídeo 06

Com a configuração do Feign externalizada, o primeiro DTO de resposta modelado (ainda não conectado ao fluxo) e a autenticação simulada resolvida de ponta a ponta, o roteiro restante do curso (conferido no README) é:

- **Vídeo 06 — Configurando Cenários de Exceção:** deve tratar do tratamento de erros e exceções na comunicação com a API externa — provavelmente introduzindo um `ErrorDecoder` customizado (mencionado, mas não implementado, na seção 5.1 deste documento) e novos cenários de resposta (erros 4xx/5xx) no Mockoon, além dos cenários "empresa sem riscos" (Vídeo 04) e "não autenticado" (este vídeo) já criados no ambiente KYC.
- **Vídeo 07 — Consumindo Dados Complexos:** deve, finalmente, alterar `getCompanyRisk` para retornar `SanctionResult` (em vez de `void`) e usar o método `toDomain()` — já pronto desde este vídeo, mas ainda sem nenhum lugar que o chame — para popular de fato um `ComplianceScreening`, conectando a chamada ao `SanctionClient` com a lógica de `CompliancePolicy.evaluate(...)`, criada no Vídeo 02 mas ainda não usada em nenhum fluxo real.
- **Vídeo 08 — Estratégias de Tolerância a Falhas:** deve fechar o curso tratando de resiliência (timeouts, retries, fallbacks) diante de falhas da API externa — tema já anunciado desde o Vídeo 01.

> **Sugestão de uso deste documento**
> Depois de assistir a cada novo vídeo, crie um novo arquivo (por exemplo, `004-Tutorial_Compliance_OpenFeign_Video06.md`) seguindo o mesmo formato: bloco de código → explicação linha a linha → checkpoint fiel ao `.zip` mais recente → atualização do glossário e dos próximos passos. Isso mantém o material sempre alinhado ao seu ritmo de estudo.

---

*Este documento cobre o Vídeo 05 do curso, dando continuidade a `002-Tutorial_Compliance_OpenFeign_Videos03a04.md`. O próximo tutorial da série deve continuar a partir daqui, documentando o Vídeo 06 ("Configurando Cenários de Exceção") com o mesmo nível de detalhe.*
