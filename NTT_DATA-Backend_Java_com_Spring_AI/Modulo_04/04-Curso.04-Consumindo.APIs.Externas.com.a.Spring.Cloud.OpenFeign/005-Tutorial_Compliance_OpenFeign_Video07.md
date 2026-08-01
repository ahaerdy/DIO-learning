# Tutorial de Estudos — Consumindo APIs Externas com o Spring Cloud OpenFeign

**Consumindo Dados Complexos: um segundo Feign Client e a regra de negócio completa — Vídeo 07**

- Curso: NTT Data — Jornada Tech (DIO) · Módulo 04 — Java/Spring com IA
- Curso 4 do módulo: "Consumindo APIs Externas com a Spring Cloud OpenFeign"
- Instrutor: Thiago Poiani (Principal Engineer at Skip)
- Projeto: `compliance`
- Documento de referência pessoal — nível iniciante em Java
- **Continuação direta de** `004-Tutorial_Compliance_OpenFeign_Video06.md`

---

## Sobre este documento

Este é o quinto tutorial da série. Ele parte exatamente de onde o anterior parou (fim do Vídeo 06: fallback do `SanctionClient` implementado e Circuit Breaker do Resilience4j habilitado, protegendo o cadastro de empresas contra falhas da API de sanções) e documenta o **Vídeo 07**, com base:

- no README atualizado (anotações de aula até o Vídeo 07);
- na transcrição literal do Vídeo 07 (`transcricao.md`);
- no código-fonte real do projeto `compliance`, no estado correspondente ao `compliance_ate_o_video07.zip`.

O mesmo compromisso dos documentos anteriores vale aqui: cada bloco de código é apresentado na ordem em que foi escrito na aula, seguido de explicação em nível iniciante — o que cada instrução faz, por que foi escrita daquela forma, e qual conceito de Java, Spring ou de arquitetura ela representa. Sempre que aparece uma anotação, propriedade, classe da biblioteca padrão ou padrão de projeto ainda não visto nos tutoriais anteriores, ele é explicado do zero.

> **Sobre o título da etapa**
> O README chama o Vídeo 07 de "Consumindo Dados Complexos", e é exatamente isso que a aula faz: em vez de um DTO relativamente simples (o `SanctionResult` do Vídeo 05, uma lista de correspondências), a API de AML (*Anti-Money Laundering* — combate à lavagem de dinheiro) devolve um objeto mais aninhado, com um sub-objeto `pep` que por sua vez contém uma lista de ocorrências. É também neste vídeo que a lógica de negócio da aplicação é **fechada de ponta a ponta** pela primeira vez: as duas chamadas HTTP (sanções + AML) alimentam `CompliancePolicy.evaluate(...)`, e o resultado é finalmente persistido junto da empresa. Segundo a transcrição, este é também o **último vídeo de desenvolvimento** do curso — o vídeo seguinte (08) é anunciado como uma conversa sobre próximos passos e desafios, não uma continuação direta de código.

---

## Parte 7 — Vídeo 07: Consumindo Dados Complexos

### 7.1. Contexto: uma segunda API mocada, agora com payload dinâmico

A aula apresenta uma nova API simulada no Mockoon, na porta **3002**, com a rota `GET /aml/v1/screening/:registrationNumber`. Diferente da API de sanções (Vídeo 04), cuja resposta era estática, esta usa um **template dinâmico** escrito em Handlebars — a linguagem de templates embutida no Mockoon para gerar corpos de resposta que variam a cada chamada:

```handlebars
{{!-- riskLevel --}}
{{setVar 'riskLevel' (oneOf (array 'LOW' 'MEDIUM' 'HIGH'))}}
{{!-- score --}}
{{#if (eq (getVar 'riskLevel') 'LOW')}}
  {{setVar 'score' (faker 'number.int' min=0 max=30)}}
{{else if (eq (getVar 'riskLevel') 'MEDIUM')}}
  {{setVar 'score' (faker 'number.int' min=31 max=60)}}
{{else}}
  {{setVar 'score' (faker 'number.int' min=61 max=100)}}
{{/if}}
{{!-- isPepUser --}}
{{#if (eq (getVar 'riskLevel') 'HIGH')}}
  {{setVar 'isPepUser' true}}
{{/if}}
```

- **`{{setVar 'nome' valor}}` / `{{getVar 'nome'}}`** — pares de *helpers* do Handlebars usados pelo Mockoon para guardar e reler um valor dentro do mesmo template, como se fossem uma variável local. Isso permite que decisões tomadas em uma parte do template (o `riskLevel` sorteado) sejam reaproveitadas em outra parte (o cálculo do `score`, coerente com o nível sorteado).
- **`{{oneOf (array 'LOW' 'MEDIUM' 'HIGH')}}`** — sorteia aleatoriamente um valor dentre os elementos de um `array` informado. É o mecanismo que faz o Mockoon devolver, a cada requisição, um nível de risco diferente.
- **`{{faker 'number.int' min=X max=Y}}`** — integra o template com a biblioteca Faker (já usada em outros pontos do curso para gerar dados falsos), aqui pedindo um número inteiro aleatório dentro de uma faixa. A faixa muda de acordo com o `riskLevel` sorteado, então um risco `HIGH` sempre vem acompanhado de um `score` também alto (61–100), mantendo os dois campos coerentes entre si.
- **`{{#if (eq (getVar 'riskLevel') 'HIGH')}} ... {{/if}}`** — um condicional do Handlebars: `eq` compara dois valores (equivalente a um `==` do Java) e `#if`/`/if` delimitam um bloco que só é processado se a condição for verdadeira. Aqui, a flag `isPepUser` só é definida como `true` quando o risco sorteado foi `HIGH` — nos outros dois casos, ela simplesmente não é atribuída (e a API tende a devolvê-la como ausente/falsa).
- Este template não é código Java nem faz parte do projeto `compliance` — ele vive só na configuração do Mockoon (a ferramenta de mock já apresentada no Vídeo 04). Ele é reproduzido aqui porque explica **por que** as respostas de exemplo mostradas no restante do vídeo têm valores coerentes entre `riskLevel`, `score` e a presença (ou não) de uma pessoa politicamente exposta.

### 7.2. Testando o endpoint de screening pelo cliente HTTP e lendo uma resposta de alto risco

Antes de escrever qualquer código Java, a aula valida a nova API manualmente, usando o cliente HTTP embutido do IntelliJ (arquivos `.http`, já usado desde o Vídeo 05):

```http
GET http://localhost:3002/aml/v1/screening/:registrationNumber
Authorization: Bearer xyz123
```

- **Porta 3002** — diferente da API de sanções, que responde na porta 3001 (Vídeo 04); a instância do Mockoon com a rota de AML foi configurada para escutar em uma porta própria, permitindo rodar as duas APIs mocadas ao mesmo tempo.
- **`Authorization: Bearer xyz123`** — este é um esquema de autenticação diferente do usado pela API de sanções. A API de sanções (Vídeo 05) espera um header customizado, `X-API-Key: kyc-secret-123`, com o valor secreto direto. Já a API de AML espera o header padrão **`Authorization`**, com o valor prefixado por `Bearer` (em português, "portador") seguido do token propriamente dito (`xyz123`). O esquema *Bearer* é um padrão amplamente usado em APIs HTTP: qualquer requisição que "porte" (carregue) o token correto é aceita como autenticada, sem verificação adicional de quem originalmente obteve aquele token — por isso o nome. Tecnicamente é só uma convenção de formatação do header; o servidor mock simplesmente confere se o valor recebido bate com o esperado.

A resposta obtida para uma empresa de risco alto é examinada em detalhe:

```json
{
  "riskLevel": "HIGH",
  "riskScore": 67,
  "flags": [
    "STRUCTURING",
    "HIGH_RISK_COUNTRY",
    "PEP_ASSOCIATED",
    "ADVERSE_MEDIA",
    "SANCTIONS_MATCH"
  ]
}
```

- Cada item de `flags` é um código curto (em caixa alta, com `_` separando palavras — convenção comum para constantes/enumerações vindas de APIs) que resume um motivo de risco: `STRUCTURING` (fracionamento de valores para evitar limites de notificação), `HIGH_RISK_COUNTRY` (país de risco, tipicamente por sanções internacionais ou conflito armado), `PEP_ASSOCIATED` (associação com pessoa politicamente exposta), `ADVERSE_MEDIA` (menções negativas em mídia) e `SANCTIONS_MATCH` (correspondência em lista de sanções). Nenhum desses códigos é tratado individualmente pelo código Java neste vídeo — a lista inteira é apenas armazenada no domínio, como será visto na seção 7.5.
- O JSON reproduzido acima **não** mostra ainda o campo `pep`; ele aparece apenas na resposta completa examinada quando o DTO é modelado (seção 7.5), o que é coerente com o payload dinâmico da seção 7.1: como o `riskLevel` sorteado nessa chamada foi `HIGH`, a flag `isPepUser` (e as ocorrências associadas) também estariam presentes na resposta completa.

### 7.3. Criando a interface `AntiMoneyLaunderingClient`

Com a API validada manualmente, a aula cria um novo Feign Client seguindo o mesmo padrão do `SanctionClient` (Vídeo 04): uma interface anotada, sem nenhuma implementação escrita à mão.

```java
package dio.compliance.infrastructure.rest.client;

import dio.compliance.infrastructure.rest.dto.AmlResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("aml-client")
public interface AntiMoneyLaunderingClient {
    @GetMapping("/aml/v1/screening/{registrationNumber}")
    AmlResult screening(@PathVariable String registrationNumber);
}
```

- **`@FeignClient("aml-client")`** — repare que, diferente do `SanctionClient` (que escreve `@FeignClient(name = "sanction-client", fallback = ...)`, explicitando o nome do atributo), aqui o nome lógico é passado **sem** o `name =` na frente. Isso é possível porque, quando uma anotação Java declara um atributo chamado `value` (o `@FeignClient` declara), esse é o único atributo que pode ser preenchido de forma **posicional**, sem precisar nomear — e, na definição de `@FeignClient`, o atributo `name` é um *alias* (um nome alternativo) do atributo `value`. Ou seja, `@FeignClient("aml-client")` e `@FeignClient(name = "aml-client")` têm exatamente o mesmo efeito; a forma curta só é possível porque nenhum outro atributo (como `fallback`) está sendo preenchido nesta chamada.
- **`interface` sem implementação** — mesmo conceito já visto no Vídeo 04 com o `SanctionClient`: o Spring Cloud OpenFeign lê essa interface em tempo de execução (graças ao escaneamento habilitado por `@EnableFeignClients`, em `ComplianceApplication`) e gera automaticamente, nos bastidores, uma classe que implementa `screening(...)` fazendo a chamada HTTP real.
- **`@GetMapping("/aml/v1/screening/{registrationNumber}")` e `@PathVariable String registrationNumber`** — mesmo mecanismo do `SanctionClient`: o trecho `{registrationNumber}` na URL é um espaço reservado, e o Spring substitui esse trecho pelo valor do parâmetro anotado com `@PathVariable` antes de disparar a requisição.
- **`AmlResult screening(...)`** — diferente da assinatura original do `SanctionClient` no Vídeo 04 (que começou retornando `void`), este método **já nasce** retornando um tipo (`AmlResult`, o DTO criado a seguir) — reaproveitando a lição do Vídeo 05, quando `getCompanyRisk` teve que ser alterada de `void` para `SanctionResult` para que a resposta da API pudesse ser efetivamente usada.

### 7.4. Modelando o DTO `AmlResult` e seu método `toDomain()`

Para representar o formato de resposta da API de AML, um novo record é criado na mesma pasta `dto` onde já vive o `SanctionResult` (Vídeo 05):

```java
package dio.compliance.infrastructure.rest.dto;

import dio.compliance.domain.ComplianceScreening;
import java.util.List;

public record AmlResult(int riskScore, List<String> flags, Pep pep) {

    public record PepOccurrence(String personName, String position) {}

    public record Pep(boolean isPep, List<PepOccurrence> occurrences) {}

    public ComplianceScreening.AmlProfile toDomain() {
        List<ComplianceScreening.AmlProfile.PoliticalExposure> exposures = pep().occurrences().stream()
                .map(occ -> new ComplianceScreening.AmlProfile.PoliticalExposure(
                        occ.personName(),
                        occ.position()
                ))
                .toList();

        return new ComplianceScreening.AmlProfile(
                riskScore(),
                flags() != null ? flags() : List.of(),
                pep().isPep(),
                exposures
        );
    }
}
```

- **`public record AmlResult(int riskScore, List<String> flags, Pep pep)`** — assim como `SanctionResult` (Vídeo 05), este record espelha diretamente o formato JSON da resposta HTTP: três componentes, na mesma ordem/nome dos campos `riskScore`, `flags` e `pep` do JSON. O Jackson (biblioteca de serialização já usada implicitamente pelo Spring Web desde o início do projeto) consegue converter o JSON recebido para este record automaticamente, casando cada campo do JSON com o componente de mesmo nome.
- **`record PepOccurrence(String personName, String position) {}`** e **`record Pep(boolean isPep, List<PepOccurrence> occurrences) {}`** — dois records aninhados dentro de `AmlResult`, no mesmo espírito de `SanctionMatch` dentro de `SanctionResult` (Vídeo 05): tipos que só existem para representar uma parte específica desta resposta, sem uso em nenhum outro lugar do projeto. A diferença aqui é que a estrutura tem **um nível a mais** de aninhamento — o JSON de exemplo da seção 7.2, quando `isPepUser` está presente, teria o formato `{ "pep": { "isPep": true, "occurrences": [ { "personName": "...", "position": "..." } ] } }`; cada um desses três níveis (`AmlResult` → `Pep` → `PepOccurrence`) ganha seu próprio record.
- **`boolean isPep`** — tipo primitivo (já apresentado no Vídeo 02) usado aqui para representar diretamente se a empresa está associada a uma pessoa politicamente exposta (PEP), sem possibilidade de valor nulo — se o JSON não trouxer o campo, o Jackson usa o valor padrão de `boolean`, que é `false`.
- **`ComplianceScreening.AmlProfile toDomain()`** — mesmo padrão do `toDomain()` de `SanctionResult` (Vídeo 05): um método de instância que converte este DTO técnico para o tipo de domínio equivalente (`ComplianceScreening.AmlProfile`, já modelado desde o Vídeo 02, mas até este vídeo nunca populado com dados reais).
- **`pep().occurrences().stream()`** — encadeamento de chamadas: `pep()` é o *getter* gerado automaticamente pelo record `AmlResult` para o componente `pep` (retorna um `Pep`); `occurrences()` é o getter gerado pelo record `Pep` para sua lista de `PepOccurrence`; `.stream()` (já apresentado desde o Vídeo 02) transforma essa `List` em um `Stream`, permitindo aplicar operações encadeáveis como o `.map(...)` a seguir.
- **`.map(occ -> new ComplianceScreening.AmlProfile.PoliticalExposure(occ.personName(), occ.position()))`** — para cada `PepOccurrence` do DTO, cria um `PoliticalExposure` de domínio equivalente, usando uma expressão lambda (já apresentada no Vídeo 02) que recebe cada ocorrência (`occ`) e devolve o objeto de domínio correspondente. É o mesmo padrão de conversão elemento a elemento já usado em `SanctionResult.toDomain()`.
- **`.toList()`** — encerra o `Stream`, coletando o resultado de volta em uma `List` imutável — mesmo método já usado em `SanctionResult.toDomain()` no Vídeo 05.
- **`flags() != null ? flags() : List.of()`** — o operador ternário (apresentado no Vídeo 05) trata o caso em que a API não envie o campo `flags` (o que resultaria em `null` após a desserialização): em vez de propagar esse `null` para dentro do domínio, o código substitui por uma lista vazia e imutável (`List.of()`, também já visto no Vídeo 05). É a mesma cautela defensiva já aplicada em `SanctionResult.toDomain()` para o campo `matches`.
- **Um ponto que o `toDomain()` desta etapa *não* trata** — diferente do cuidado tomado com `flags()`, o código chama `pep().occurrences()` diretamente, sem checar se `pep()` ou `occurrences()` poderiam vir como `null` (por exemplo, se a API omitisse o campo `pep` inteiro em vez de enviá-lo com `isPep: false`). Se isso acontecesse, a chamada lançaria uma `NullPointerException` antes mesmo de chegar ao `.stream()`. Isso não chega a ser testado neste vídeo — todas as respostas mostradas trazem o objeto `pep` presente, mesmo quando `isPep` é `false` (ver seção 7.7) — mas vale registrar como uma fragilidade do código nesta etapa, na mesma linha do que os tutoriais anteriores já apontaram para outros DTOs.

### 7.5. Conectando o novo cliente ao use case: da injeção "esquecida" à URL "esquecida"

Com a interface e o DTO prontos, a aula volta ao `AnalyzeCompanyRiskUseCase` (criado no Vídeo 04) para injetar o novo client. A transcrição registra o processo em duas etapas visíveis: primeiro o campo é declarado sem que o construtor seja ajustado, depois o construtor é corrigido — o mesmo tipo de "erro de digitação seguido de correção" já visto nos vídeos anteriores.

```java
package dio.compliance.appicantion;

import dio.compliance.domain.Company;
import dio.compliance.infrastructure.rest.client.AntiMoneyLaunderingClient;
import dio.compliance.infrastructure.rest.client.SanctionClient;
import org.springframework.stereotype.Service;

@Service
public class AnalyzeCompanyRiskUseCase {

    private final SanctionClient sanctionClient;
    private final AntiMoneyLaunderingClient antiMoneyLaunderingClient;

    public AnalyzeCompanyRiskUseCase(SanctionClient sanctionClient,
                                      AntiMoneyLaunderingClient antiMoneyLaunderingClient) {
        this.sanctionClient = sanctionClient;
        this.antiMoneyLaunderingClient = antiMoneyLaunderingClient;
    }

    public void execute(Company domain) {
        var sanctions = sanctionClient.getCompanyRisk(domain.getRegistrationNumber());
        var amlProfile = antiMoneyLaunderingClient.screening(domain.getRegistrationNumber());
    }
}
```

- **Segundo parâmetro do construtor** — o Spring já injetava o `SanctionClient` via construtor desde o Vídeo 04 (injeção de dependência, conceito apresentado no Vídeo 02). Adicionar `AntiMoneyLaunderingClient antiMoneyLaunderingClient` como um segundo parâmetro segue o mesmo mecanismo: como só existe **um** construtor nesta classe, o Spring é obrigado a usá-lo para criar o bean de `AnalyzeCompanyRiskUseCase`, e precisa ter, no container, um bean de cada tipo declarado ali — o que já é o caso, já que `@FeignClient` faz o próprio Spring Cloud OpenFeign registrar automaticamente uma implementação de `AntiMoneyLaunderingClient` como bean.
- **`amlProfile` chamado, mas ainda não usado** — nesta versão intermediária do código, exatamente como havia acontecido com `sanctions` até o Vídeo 06, o valor retornado por `antiMoneyLaunderingClient.screening(...)` é atribuído a uma variável local e descartado no fim do método, sem nenhum efeito prático ainda. Isso muda na seção 7.7, quando o método `execute` ganha sua versão final.
- Ao tentar subir a aplicação nesse ponto, a transcrição relata um erro: a aula percebe que **esqueceu de configurar a URL do novo client** no `application.properties`, o que motiva o próximo passo.

### 7.6. Configurando o `aml-client` no `application.properties`

```properties
spring.application.name=compliance

spring.cloud.openfeign.client.config.sanction-client.url=http://localhost:3001
spring.cloud.openfeign.client.config.sanction-client.logger-level=full
spring.cloud.openfeign.client.config.sanction-client.default-request-headers.x-api-key=kyc-secret-123
spring.cloud.openfeign.circuitbreaker.enabled=true

spring.cloud.openfeign.client.config.aml-client.url=http://localhost:3002
spring.cloud.openfeign.client.config.aml-client.logger-level=full
spring.cloud.openfeign.client.config.aml-client.default-request-headers.authorization=Bearer xyz123

logging.level.dio.compliance.infrastructure.rest=DEBUG
```

- **Três novas linhas, mesmo prefixo já conhecido** — `spring.cloud.openfeign.client.config.<nome>.*` (apresentado no Vídeo 05) é reaproveitado aqui trocando apenas o `<nome>` para `aml-client`, o mesmo valor passado em `@FeignClient("aml-client")` na seção 7.3 — é essa correspondência de nomes que permite ao Spring Cloud OpenFeign saber que estas três propriedades pertencem a este client específico, e não ao `sanction-client`.
- **`default-request-headers.authorization=Bearer xyz123`** — mesmo mecanismo de header automático já usado para `x-api-key` (Vídeo 05), mas aqui o nome do header é o padrão HTTP `Authorization`, com o valor completo `Bearer xyz123` (esquema + token, explicado na seção 7.2) escrito de uma vez só — o Feign não trata o prefixo `Bearer` de forma especial; ele é só texto dentro do valor do header, exatamente como qualquer outro.
- **O `IllegalStateException` mencionado pelo README** — antes de acrescentar a linha `.url=http://localhost:3002`, subir a aplicação com o `AntiMoneyLaunderingClient` já injetado (mas sem URL configurada) produz um erro em tempo de execução informando que não havia um Feign Client definido para *load balancing*. Isso acontece porque, por padrão, o Spring Cloud OpenFeign assume que o nome passado a `@FeignClient` (`"aml-client"`) é o nome de um **serviço registrado** em um mecanismo de descoberta de serviços (um recurso do Spring Cloud LoadBalancer, não usado neste projeto) — e tenta resolver esse nome por esse caminho. Só quando uma propriedade `...client.config.aml-client.url` é declarada explicitamente é que o Feign passa a tratar `aml-client` apenas como um **rótulo lógico** de configuração, chamando diretamente a URL fixa informada, sem tentar nenhuma resolução via descoberta de serviços. É o mesmo motivo, na prática, pelo qual `sanction-client` já precisava dessa propriedade desde o Vídeo 05.
- Depois desse ajuste, o console mostra o Spring Boot (versão 4.0.5) subindo normalmente, com o Tomcat na porta configurada e a aplicação `compliance` pronta para receber requisições.

### 7.7. Montando a regra de negócio completa em `AnalyzeCompanyRiskUseCase`

Com os dois clients funcionando de forma isolada, a aula fecha o método `execute`, conectando as duas respostas HTTP à lógica de domínio já existente desde o Vídeo 02 (`CompliancePolicy.evaluate`), mas nunca antes usada em um fluxo real:

```java
public void execute(Company company) {
    var sanctions = sanctionClient.getCompanyRisk(company.getRegistrationNumber()).toDomain();
    var amlProfile = antiMoneyLaunderingClient.screening(company.getRegistrationNumber()).toDomain();

    var screening = new ComplianceScreening(sanctions, amlProfile);
    var riskAssessment = CompliancePolicy.evaluate(screening);

    company.applyRiskAssessment(riskAssessment);
    companyRepository.save(company);
}
```

- **`.toDomain()` encadeado direto na chamada do client** — diferente da versão anterior (seção 7.5), que guardava o DTO bruto (`SanctionResult`/`AmlResult`) em uma variável, aqui o `.toDomain()` (métodos escritos nas seções 7.4 e no Vídeo 05) é chamado **imediatamente** sobre o retorno de cada client, então `sanctions` já é uma `List<ComplianceScreening.SanctionIdentity>` e `amlProfile` já é um `ComplianceScreening.AmlProfile` — os tipos de domínio, não mais os DTOs de infraestrutura.
- **`new ComplianceScreening(sanctions, amlProfile)`** — instancia o record `ComplianceScreening` (modelado no Vídeo 02) passando as duas listas/objetos de domínio recém-obtidos. É o primeiro momento do projeto em que um `ComplianceScreening` é montado com **dados reais**, vindos de uma chamada HTTP de verdade, em vez de ser apenas uma estrutura vazia planejada de antemão.
- **`CompliancePolicy.evaluate(screening)`** — chama o método estático (conceito apresentado no Vídeo 02) escrito ainda no início do projeto, mas nunca antes exercitado com dados reais. Vale relembrar a regra, já existente desde o Vídeo 02 e inalterada nesta etapa: a política rejeita a empresa (`RISK_ASSESSMENT.status = REJECTED`) se houver alguma sanção com `confidence` acima de `0.8`; caso contrário, marca para revisão manual (`MANUAL_REVIEW`) se houver pessoa politicamente exposta (`isPepPresent`) ou se o `riskScore` da AML for maior que `70`; e aprova (`APPROVED`) nos demais casos. O `RiskAssessment` resultante calcula ainda um `RiskLevel` (`LOW`/`MEDIUM`/`HIGH`/`CRITICAL`) a partir do score e do status, em uma regra também já existente desde o Vídeo 02 (record `RiskAssessment`, construtor auxiliar `determineRiskLevel`).
- **`company.applyRiskAssessment(riskAssessment)`** — chamada a um método que, segundo a transcrição, foi criado nesta etapa dentro da classe `Company`, funcionando como um "setter" que recebe o `RiskAssessment` calculado e o associa à empresa em memória, antes da persistência.
- **`companyRepository.save(company)`** — chamada ao mesmo `CompanyRepository` (interface de domínio criada no Vídeo 02, implementada por `InMemoryCompanyRepository` desde o Vídeo 03) para persistir a empresa já com o resultado da análise de risco aplicado.

> ⚠️ **Divergência importante entre o que a aula narra e o código-fonte real desta etapa**
> Tanto a transcrição quanto o README descrevem, nesta parte da aula, a criação do método `applyRiskAssessment` dentro de `Company` e a injeção de um `CompanyRepository` (via construtor) dentro de `AnalyzeCompanyRiskUseCase`. Conferindo diretamente o `.zip` desta etapa, porém:
> - a classe `domain/Company.java` **não** contém nenhum método `applyRiskAssessment` — ela continua exatamente como estava desde o Vídeo 02, apenas com o campo `riskAssessment` e o getter gerado por `@Getter` (Lombok);
> - a classe `appicantion/AnalyzeCompanyRiskUseCase.java` **não** declara nenhum campo `companyRepository`, nem recebe um `CompanyRepository` no construtor — o construtor continua recebendo apenas `sanctionClient` e `antiMoneyLaunderingClient` (seção 7.5).
>
> Ou seja, o método `execute` reproduzido acima — que é, de fato, o conteúdo real do arquivo `.zip` — **não compila** da forma como está: `applyRiskAssessment` não existe em `Company`, e `companyRepository` não é uma variável conhecida dentro do método. Isso é diferente dos "erros de digitação seguidos de correção" registrados nos tutoriais anteriores (como o `Fallback` sem `public` no Vídeo 06): naqueles casos, o `.zip` da etapa já trazia a versão corrigida; aqui, o `.zip` fornecido está no estado **anterior** à correção que a própria aula descreve ter feito. É possível que isso reflita apenas o momento exato em que o arquivo do projeto foi exportado durante a gravação (antes de salvar as últimas alterações em `Company.java`), e não um problema real do curso — mas, para quem está reproduzindo o projeto a partir deste material, os dois ajustes narrados a seguir são **necessários** para que o código funcione:
> 1. Adicionar em `Company.java` um método como `public void applyRiskAssessment(RiskAssessment riskAssessment) { this.riskAssessment = Optional.of(riskAssessment); }` (compatível com o campo `Optional<RiskAssessment> riskAssessment` já existente desde o Vídeo 02).
> 2. Adicionar em `AnalyzeCompanyRiskUseCase.java` um campo `private final CompanyRepository companyRepository;`, recebê-lo como terceiro parâmetro do construtor, e atribuí-lo a `this.companyRepository` — seguindo exatamente o mesmo padrão de injeção via construtor já usado para os dois Feign Clients.
>
> Este ponto está detalhado com mais profundidade na seção "Pontos de atenção" adiante, e o checkpoint de código ao final deste documento reproduz fielmente o conteúdo **real** do `.zip` (com a inconsistência), não uma versão já corrigida.

### 7.8. Validando o fluxo ponta a ponta

Com a aplicação no ar, a aula repete o ciclo de testes já familiar desde o Vídeo 03: `GET /companies` retorna uma coleção vazia; em seguida, um `POST /companies` é enviado para cadastrar uma nova empresa.

```http
POST http://localhost:8080/companies
Accept: application/json

{
  "name": "Logistics",
  "registrationNumber": "REG-1234"
}
```

A resposta imediata do `POST` (status `201`) traz o campo `riskAssessment` ainda como `null` — o mesmo comportamento já observado desde o Vídeo 04: o endpoint gerado pelo Spring Data REST responde assim que a empresa é salva pela primeira vez (sem análise de risco), e é só **depois** disso que o evento `@HandleAfterCreate` (Vídeo 03) dispara `AnalyzeCompanyRiskUseCase.execute(...)`, que agora, com a lógica completa da seção 7.7, faz as duas chamadas HTTP e regrava a empresa com o resultado.

Os logs em nível `DEBUG` (habilitado desde o Vídeo 06) confirmam as duas chamadas em sequência, para a mesma empresa `REG-1234`:

```json
"matches": [
  {
    "entity": "REG-1234",
    "list": "OFAC SDN List",
    "reason": "Financing of Prohibited Entities",
    "confidenceScore": 0.98
  }
]
```

```json
{
  "riskLevel": "MEDIUM",
  "riskScore": 56,
  "flags": ["SANCTIONS_MATCH", "ADVERSE_MEDIA", "HIGH_RISK_COUNTRY"],
  "pep": {
    "isPep": false,
    "occurrences": []
  }
}
```

- No log de sanções, a `confidenceScore` de `0.98` está **acima** do limiar de `0.8` usado por `CompliancePolicy.evaluate` (seção 7.7) — isso é o suficiente, sozinho, para que o `status` seja `REJECTED`, independentemente do resultado da AML.
- No log de AML, mesmo com `riskLevel: MEDIUM` e nenhuma pessoa politicamente exposta (`isPep: false`, `occurrences: []`), o objeto `pep` **está presente** na resposta (apenas com valores "vazios"), e não ausente — reforçando a observação da seção 7.4 de que o `toDomain()` desta etapa nunca chega a testar o caminho em que `pep` viesse `null`.
- Consultando a empresa pelo `id` depois que o processamento assíncrono termina, o `riskAssessment` aparece persistido com `level: CRITICAL` e `status: REJECTED`:

```http
GET http://localhost:8080/companies/f5e85a96-9f9a-471b-9312-447d8dc15995
```

```json
{
  "name": "Logistics",
  "registrationNumber": "REG-1234",
  "riskAssessment": {
    "score": 2,
    "level": "CRITICAL",
    "status": "REJECTED"
  }
}
```

- **`level: CRITICAL` mesmo com `score: 2`** — à primeira vista pode parecer estranho um score tão baixo resultar no nível mais alto de risco, mas é exatamente o comportamento esperado da regra escrita no Vídeo 02 (`RiskAssessment.determineRiskLevel`, revisitada na seção 7.7): quando o `status` já é `REJECTED` (por causa da sanção de alta confiança), o nível é **sempre** `CRITICAL`, e o valor de `score` (que, nesta implementação, vem apenas do `riskScore` da AML — não da sanção) deixa de ser o fator decisivo. Isso confirma, com um exemplo end-to-end real, uma regra que até este vídeo só existia "no papel", desde o Vídeo 02.
- É esse resultado — uma empresa rejeitada com base na combinação das duas APIs externas consumidas via Feign — que fecha o objetivo central do curso, apresentado desde o Vídeo 01: consumir APIs externas de compliance (KYC/sanções e AML) para alimentar uma decisão de negócio real.

---

## Pontos de atenção: divergências entre a aula e o seu projeto

Comparando o README, a transcrição e as capturas de tela com o `.zip` real desta etapa, valem os seguintes registros:

1. **`Company.applyRiskAssessment` e a injeção de `CompanyRepository` em `AnalyzeCompanyRiskUseCase`, narrados mas ausentes no código real (o ponto mais importante desta etapa)** — como detalhado na seção 7.7, o método `execute` presente no `.zip` chama `company.applyRiskAssessment(riskAssessment)` e `companyRepository.save(company)`, mas nem o método `applyRiskAssessment` existe em `Company.java`, nem o campo `companyRepository` é declarado ou injetado em `AnalyzeCompanyRiskUseCase.java`. **O código, exatamente como está no `.zip`, não compila.** As imagens do README e as telas de teste bem-sucedido (empresa persistida com `riskAssessment` preenchido) só fazem sentido presumindo que essas duas alterações foram, de fato, feitas durante a gravação — só não chegaram a este arquivo exportado. Quem for reproduzir o projeto a partir daqui precisa aplicar as duas correções descritas na seção 7.7 manualmente.
2. **`localhost` no código, IP de rede local nas capturas de tela — divergência que persiste** — como já registrado nos três tutoriais anteriores, o `application.properties` real usa `url=http://localhost:3001` e `url=http://localhost:3002`, enquanto as capturas de tela do README mostram `192.168.64.1` nesses mesmos pontos (inclusive no teste manual do endpoint de AML, seção 7.2). Continua sendo, provavelmente, apenas o endereço de rede local da máquina onde a aula foi gravada — `localhost` é a opção mais portável para reproduzir o projeto em uma única máquina.
3. **`@FeignClient("aml-client")` sem `fallback`, diferente do `SanctionClient`** — não é um erro, mas vale registrar: apenas o `SanctionClient` recebeu um mecanismo de fallback (Vídeo 06). O `AntiMoneyLaunderingClient` criado nesta etapa não tem nenhuma proteção equivalente — uma falha na API de AML ainda derruba o fluxo de cadastro de empresa com uma exceção não tratada, exatamente como acontecia com o `SanctionClient` antes do Vídeo 06. Isso é coerente com o fato de o Vídeo 08 (o próximo da trilha) se chamar "Estratégias de Tolerância a Falhas" — é provável que esse tema seja retomado ali, mas, até o fim deste vídeo, essa proteção não existe para o novo client.
4. **`AmlResult.toDomain()` sem tratamento defensivo para `pep` nulo** — apontado na seção 7.4: diferente do cuidado tomado com `flags()` (`!= null ? ... : List.of()`), o método acessa `pep().occurrences()` sem checar se `pep()` poderia ser `null`. Nenhum cenário testado no vídeo chega a expor esse problema, mas ele existe no código real.
5. **Import estático não utilizado em `SanctionResult.java` — ainda presente** — já registrado nos tutoriais dos Vídeos 05 e 06; o arquivo real continua com `import static java.util.regex.Pattern.matches;`, sem nenhuma referência a ele no restante do código. Nenhuma mudança nesta etapa.
6. **Pacote `appicantion` continua com o erro de digitação** — já sinalizado nos quatro tutoriais anteriores; nenhuma classe nova foi adicionada a esse pacote nesta etapa (apenas `AnalyzeCompanyRiskUseCase`, já existente, foi alterada), e a correção continua pendente.
7. **Toolchain Java sem mudanças** — confirmado no `.zip`: o projeto continua declarando `JavaLanguageVersion.of(21)`, divergência com o JDK 25 mencionado na criação do projeto (Vídeo 02) que segue sem impacto prático.

---

## Glossário de conceitos Java e Spring — apenas os termos novos desta etapa

> Este glossário complementa (não substitui) os glossários dos tutoriais anteriores. Aqui aparecem só os termos que ainda não haviam sido explicados nos Vídeos 01 a 06.

### Estrutura da linguagem Java

| Termo | Significado |
|---|---|
| Atributo `value` de uma anotação | Quando uma anotação declara um atributo chamado especificamente `value`, esse é o único atributo que pode ser preenchido de forma posicional, sem escrever `nomeDoAtributo =` antes dele — por exemplo, `@FeignClient("aml-client")` em vez de `@FeignClient(value = "aml-client")`. Só funciona quando nenhum outro atributo da anotação está sendo preenchido na mesma chamada. |
| Alias de atributo de anotação | Quando a definição de uma anotação marca um atributo como alias de outro (caso de `name` em relação a `value`, dentro de `@FeignClient`), preencher um dos dois tem exatamente o mesmo efeito que preencher o outro — são, na prática, o mesmo dado, acessível por dois nomes diferentes. |
| Encadeamento de *getters* de records aninhados (`pep().occurrences()`) | Chamar, em sequência, os métodos de acesso gerados automaticamente por records aninhados uns dentro dos outros, navegando pela estrutura de dados sem precisar de variáveis intermediárias para cada nível. |

### Anotações, propriedades e bibliotecas

| Termo | Significado |
|---|---|
| Esquema de autenticação *Bearer* (`Authorization: Bearer <token>`) | Convenção de autenticação HTTP em que o token de acesso é enviado no header padrão `Authorization`, prefixado pela palavra `Bearer`. Diferente de um header customizado (como o `X-API-Key` visto no Vídeo 05), este segue um formato padronizado, reconhecido por praticamente qualquer ferramenta ou biblioteca HTTP. |
| Handlebars (templates do Mockoon) | Linguagem de templates usada pelo Mockoon para gerar corpos de resposta dinâmicos, com *helpers* como `{{setVar}}`/`{{getVar}}` (variáveis locais ao template), `{{oneOf}}` (sorteio entre valores de um array), `{{faker}}` (integração com a biblioteca Faker) e `{{#if}}...{{/if}}` (condicionais) — usada nesta etapa para fazer o `riskLevel`, o `score` e a presença de PEP de uma resposta mocada variarem de forma coerente entre si a cada chamada. |
| Spring Cloud LoadBalancer (mecanismo de descoberta de serviços) | Componente do Spring Cloud que o OpenFeign tenta usar, por padrão, para resolver o nome lógico passado a `@FeignClient` como o nome de um serviço registrado em um mecanismo de descoberta. Quando esse nome não corresponde a nenhum serviço registrado e nenhuma URL fixa foi configurada via propriedade `...client.config.<nome>.url`, o resultado é um `IllegalStateException` em tempo de execução — motivo pelo qual toda `@FeignClient` deste projeto precisa da propriedade `.url` explícita no `application.properties`. |

---

## Estado atual do projeto (checkpoint do Vídeo 07)

> Conferido diretamente contra o código-fonte real em `compliance_ate_o_video07.zip` — não apenas contra a narrativa do README ou da transcrição. Como registrado nos "Pontos de atenção", o método `execute` reproduzido abaixo é **fielmente igual ao arquivo real**, incluindo a inconsistência (`applyRiskAssessment` e `companyRepository` referenciados, mas não declarados em nenhum lugar do projeto).

### Estrutura de pastas (pacotes alterados nesta etapa)

```
dio.compliance
├── appicantion
│   └── AnalyzeCompanyRiskUseCase.java       (ALTERADO nesta etapa: 2º client + regra de negócio completa)
├── domain
│   └── ... (sem mudanças desde o Vídeo 02 — inclui a divergência do item 1 em "Pontos de atenção")
└── infrastructure
    ├── persistence
    │   └── ... (sem mudanças desde o Vídeo 04)
    └── rest
        ├── client
        │   ├── SanctionClient.java           (sem mudanças desde o Vídeo 06)
        │   └── AntiMoneyLaunderingClient.java (NOVO nesta etapa)
        └── dto
            ├── SanctionResult.java            (sem mudanças desde o Vídeo 05)
            └── AmlResult.java                 (NOVO nesta etapa)
```

### `application.properties` (alterado nesta etapa)

```properties
spring.application.name=compliance

spring.cloud.openfeign.client.config.sanction-client.url=http://localhost:3001
spring.cloud.openfeign.client.config.sanction-client.logger-level=full
spring.cloud.openfeign.client.config.sanction-client.default-request-headers.x-api-key=kyc-secret-123

spring.cloud.openfeign.circuitbreaker.enabled=true

spring.cloud.openfeign.client.config.aml-client.url=http://localhost:3002
spring.cloud.openfeign.client.config.aml-client.logger-level=full
spring.cloud.openfeign.client.config.aml-client.default-request-headers.authorization=Bearer xyz123

logging.level.dio.compliance.infrastructure.rest=DEBUG
```

### `infrastructure/rest/client/AntiMoneyLaunderingClient.java` (novo nesta etapa)

```java
package dio.compliance.infrastructure.rest.client;

import dio.compliance.infrastructure.rest.dto.AmlResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("aml-client")
public interface AntiMoneyLaunderingClient {
    @GetMapping("/aml/v1/screening/{registrationNumber}")
    AmlResult screening(@PathVariable String registrationNumber);
}
```

### `infrastructure/rest/dto/AmlResult.java` (novo nesta etapa)

```java
package dio.compliance.infrastructure.rest.dto;

import dio.compliance.domain.ComplianceScreening;
import java.util.List;

public record AmlResult(int riskScore, List<String> flags, Pep pep) {

    public record PepOccurrence(String personName, String position) {}

    public record Pep(boolean isPep, List<PepOccurrence> occurrences) {}

    public ComplianceScreening.AmlProfile toDomain() {
        List<ComplianceScreening.AmlProfile.PoliticalExposure> exposures = pep().occurrences().stream()
                .map(occ -> new ComplianceScreening.AmlProfile.PoliticalExposure(
                        occ.personName(),
                        occ.position()
                ))
                .toList();

        return new ComplianceScreening.AmlProfile(
                riskScore(),
                flags() != null ? flags() : List.of(),
                pep().isPep(),
                exposures
        );
    }
}
```

### `appicantion/AnalyzeCompanyRiskUseCase.java` (alterado nesta etapa — reproduzido tal como está no `.zip`, com a divergência do item 1 de "Pontos de atenção")

```java
package dio.compliance.appicantion;

import dio.compliance.domain.Company;
import dio.compliance.domain.CompliancePolicy;
import dio.compliance.domain.ComplianceScreening;
import dio.compliance.infrastructure.rest.client.AntiMoneyLaunderingClient;
import dio.compliance.infrastructure.rest.client.SanctionClient;
import org.springframework.stereotype.Service;

@Service
public class AnalyzeCompanyRiskUseCase {

    private final SanctionClient sanctionClient;
    private final AntiMoneyLaunderingClient antiMoneyLaunderingClient;

    public AnalyzeCompanyRiskUseCase(SanctionClient sanctionClient,
                                     AntiMoneyLaunderingClient antiMoneyLaunderingClient) {
        this.sanctionClient = sanctionClient;
        this.antiMoneyLaunderingClient = antiMoneyLaunderingClient;
    }

    public void execute(Company company) {
        var sanctions = sanctionClient.getCompanyRisk(company.getRegistrationNumber());
        var amlProfile = antiMoneyLaunderingClient.screening(company.getRegistrationNumber());

        var screening = new ComplianceScreening(sanctions, amlProfile);
        var riskAssessment = CompliancePolicy.evaluate(screening);

        company.applyRiskAssessment(riskAssessment);
        companyRepository.save(company);

    }
}
```

> Note que, no arquivo real, `sanctions` e `amlProfile` são atribuídos **sem** a chamada a `.toDomain()` mostrada na seção 7.7 (`new ComplianceScreening(sanctions, amlProfile)` exige, pelos tipos declarados no record `ComplianceScreening`, uma `List<SanctionIdentity>` e um `AmlProfile` — não os DTOs `SanctionResult`/`AmlResult` brutos). Isso é mais uma peça da mesma divergência já registrada: o arquivo exportado no `.zip` está em um ponto anterior ao fechamento completo do método narrado pela aula. A versão funcional, com `.toDomain()` encadeado, é a reproduzida na seção 7.7 deste tutorial.

### `domain/Company.java` (confirmado **sem mudanças** desde o Vídeo 02 — ver item 1 de "Pontos de atenção")

```java
package dio.compliance.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@Getter
@AllArgsConstructor
public class Company {
    private CompanyId id;
    private String name;
    private String registrationNumber;
    private Optional<RiskAssessment> riskAssessment;
}
```

### Arquivos desta etapa que **não mudaram** desde os checkpoints anteriores (confirmados no `.zip`)

`ComplianceApplication.java`, `build.gradle`, `settings.gradle`, `SanctionClient.java` (Vídeo 06), `SanctionResult.java` (Vídeo 05) e todos os demais arquivos de `domain/` (`CompanyId.java`, `CompanyRepository.java`, `CompliancePolicy.java`, `ComplianceScreening.java`, `RiskAssessment.java`, `RiskAssessmentStatus.java`, `RiskLevel.java`) e de `infrastructure/persistence/` (`CompanyEntity.java`, `CompanyEventHandler.java`, `CompanyEntityRepository.java`, `InMemoryCompanyRepository.java`) permanecem idênticos ao checkpoint dos tutoriais anteriores.

---

## Próximos passos: o que vem a partir do Vídeo 08

> **Uma correção em relação ao pedido original deste documento**
> O sumário da trilha, repetido em várias das capturas de tela do README ("o sumário lateral mostra os **oito** tópicos da trilha" — Vídeos 03, 05, 06 e 07), indica que o curso tem **oito** vídeos no total, e não dez. Não há, até onde os materiais enviados mostram, um "Vídeo 09" ou "Vídeo 10" planejado — o Vídeo 08 aparenta ser o encerramento da trilha. Por isso, a previsão abaixo cobre apenas o que falta: o próprio Vídeo 08.

Com a regra de negócio completa (uma vez aplicadas as duas correções descritas na seção 7.7) — as duas APIs externas (sanções e AML) alimentando `CompliancePolicy.evaluate(...)` e o resultado sendo persistido junto da empresa —, o roteiro restante do curso (conferido no README) é:

- **Vídeo 08 — Estratégias de Tolerância a Falhas:** segundo a própria transcrição deste vídeo ("o próximo vídeo eu vou conversar sobre algumas ideias para futuro e alguns desafios para você"), este parece ser mais uma conversa de encerramento do que uma continuação direta de código — possivelmente incluindo o aprofundamento, já anunciado desde o Vídeo 06, dos detalhes finos do Circuit Breaker do Resilience4j (limiares de falha, tempo em estado aberto, chamadas de teste em estado semiaberto), além de, plausivelmente, a criação de um mecanismo de fallback para o `AntiMoneyLaunderingClient` (que, nesta etapa, ainda não tem nenhum — item 3 de "Pontos de atenção") e/ou dos outros recursos do Resilience4j mencionados desde o Vídeo 01 (retry, time limiter), ainda não usados no projeto.

> **Sugestão de uso deste documento**
> Depois de assistir ao Vídeo 08, crie um novo arquivo (por exemplo, `006-Tutorial_Compliance_OpenFeign_Video08.md`) seguindo o mesmo formato: bloco de código → explicação linha a linha → checkpoint fiel ao `.zip` mais recente → atualização do glossário e dos próximos passos (ou, caso o Vídeo 08 confirme ser um encerramento sem código novo, um fechamento da série resumindo o projeto `compliance` de ponta a ponta).

---

*Este documento cobre o Vídeo 07 do curso, dando continuidade a `004-Tutorial_Compliance_OpenFeign_Video06.md`. O próximo (e, aparentemente, último) tutorial da série deve continuar a partir daqui, documentando o Vídeo 08 ("Estratégias de Tolerância a Falhas") com o mesmo nível de detalhe.*
