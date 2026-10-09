# Tutorial de Estudos — Simplificando a Segurança em APIs REST com Spring Security

**Implementando o `ProposalController`: expondo os use cases de propostas via HTTP — Vídeo 08**

- Curso: NTT Data — Jornada Tech (DIO) · Módulo 4 — Bootcamp Java + Spring + AI
- Curso: "Simplificando a Segurança em APIs REST com Spring Security"
- Instrutor: Thiago Poiani (Principal Engineer at Skip)
- Projeto: `proposal-managemnet` (pacote base `dio.proposalmanagement`)
- Documento de referência pessoal — nível iniciante em Java
- Continuação de: `006-Tutorial_ProposalManagement_Spring_Security_Video07.md`

---

## Sobre este documento

Este é o **sétimo tutorial** da série, cobrindo o **Vídeo 08 — "Implementando o `ProposalController`"**. Ele foi escrito a partir de três fontes conferidas diretamente: a transcrição do vídeo (`transcricao.md`), a seção de anotações do Vídeo 08 no README atualizado, e o código-fonte real do projeto na etapa correspondente (`proposal-managemnet_ate_o_video08.zip`), conferido arquivo a arquivo — inclusive a pasta `build/`, usada novamente como evidência na seção "Pontos de atenção".

Segue exatamente o mesmo formato dos tutoriais anteriores: bloco de código → explicação linha a linha, em nível iniciante → destaques de "porquê" → uma seção de "Pontos de atenção" comparando a narrativa da aula/README com o que está realmente no seu `.zip` → glossário (só termos novos) → checkpoint fiel do código → próximos passos atualizados.

> Este é o vídeo em que o módulo `proposal` finalmente "sobe à superfície": até aqui, `CreateProposalUseCase` e `ListProposalsUseCase` existiam, mas nada na aplicação os chamava — não havia nenhum jeito de um cliente HTTP alcançá-los. Neste vídeo é criado o `ProposalController`, com dois endpoints (`POST /proposals` e `GET /proposals`), dois DTOs novos (`CreateProposalRequest` e `ProposalResponse`), e — talvez o mais importante para quem acompanha os bugs arrastados desde o Vídeo 05 — é também o vídeo em que a aplicação **compila e roda de ponta a ponta pela primeira vez**, permitindo os primeiros testes reais via requisição HTTP.

---

## Parte 8 — Implementando o `ProposalController` (Vídeo 08)

### 8.1. O que muda neste vídeo

Com o Vídeo 07, o módulo `proposal` ganhou uma implementação real de `ProposalRepository` (`JpaProposalRepository`), fechando o ciclo entre domínio, aplicação e banco de dados. Mas, até ali, nada na aplicação **chamava** `CreateProposalUseCase` ou `ListProposalsUseCase` — eles existiam como classes `@Service` prontas para serem injetadas, mas sem nenhum controller que as invocasse a partir de uma requisição HTTP.

Este vídeo fecha essa lacuna, criando três arquivos novos, nesta ordem:

1. `ProposalController` — a classe `@RestController` que expõe `/proposals` via HTTP, com um endpoint de criação (`POST`) e um de listagem (`GET`).
2. `CreateProposalRequest` — o DTO de entrada (pacote `infrastructure.http.request`) que representa o JSON recebido no corpo da requisição de criação.
3. `ProposalResponse` — o DTO de saída (pacote `infrastructure.http.response`) que representa o JSON devolvido ao cliente, tanto na criação quanto na listagem.

O padrão de organização repete o que já foi visto em `auth.infrastructure.security.RestUsernamePasswordAuthenticationFilter` (Vídeo 03), que também usava um DTO de entrada (`LoginRequest`) convertido a partir do corpo da requisição — só que agora dentro do módulo `proposal`, e com um DTO de saída também, algo que o módulo `auth` ainda não tem.

### 8.2. Criando o pacote `http` e o esqueleto do `ProposalController`

O primeiro passo é criar o pacote `dio.proposalmanagement.proposal.infrastructure.http` e, dentro dele, a classe `ProposalController`, inicialmente vazia:

```java
package dio.proposalmanagement.proposal.infrastructure.http;

public class ProposalController {
}
```

- Repare que esse pacote fica dentro de `infrastructure`, no mesmo nível de `persistence` (criado no Vídeo 07). Isso reforça a organização em camadas já usada no projeto: `domain` (regra de negócio pura), `application` (casos de uso) e `infrastructure` (tudo que conversa com o mundo externo — banco de dados **e**, agora, também HTTP).
- A classe começa completamente vazia, sem nenhuma anotação — é só o ponto de partida antes de transformá-la, de fato, em um controller REST nos próximos passos.

### 8.3. Anotando a classe: `@RestController` e `@RequestMapping("/proposals")`

```java
@RestController
@RequestMapping("/proposals")
public class ProposalController {
```

- `@RestController` — anotação já conhecida desde os Vídeos 01/02 (usada em `auth.infrastructure.http.Controller`): marca a classe como um controller REST, onde o valor de retorno de cada método é serializado diretamente no corpo da resposta HTTP (por padrão, em JSON, via Jackson), em vez de ser interpretado como o nome de uma página a renderizar.
- `@RequestMapping("/proposals")` — define o caminho base ao qual todos os métodos desta classe respondem. Qualquer endpoint declarado aqui dentro (com `@PostMapping`, `@GetMapping`, etc.) vai ter `/proposals` como prefixo da URL.

### 8.4. Injetando os dois use cases via construtor

```java
private final CreateProposalUseCase createProposalUseCase;
private final ListProposalsUseCase listProposalsUseCase;

public ProposalController(CreateProposalUseCase createProposalUseCase,
                          ListProposalsUseCase listProposalsUseCase) {
    this.createProposalUseCase = createProposalUseCase;
    this.listProposalsUseCase = listProposalsUseCase;
}
```

- Os dois campos são `private final` — o mesmo padrão de **injeção de dependência via construtor** já usado em todas as outras classes gerenciadas pelo Spring neste projeto (`JpaProposalRepository`, `AllStrategy`, `OwnStrategy`, `Factory`, etc.). Como `ProposalController` só tem um construtor, o Spring não precisa de nenhuma anotação adicional (como `@Autowired`) para saber que deve injetar essas duas dependências automaticamente ao criar o `bean` do controller.
- `CreateProposalUseCase` e `ListProposalsUseCase` já estão anotadas com `@Service` (desde os Vídeos 05 e 06), então o Spring já sabe como construí-las e disponibilizá-las para injeção.
- Do ponto de vista de arquitetura, este é o momento em que a camada `infrastructure.http` passa a depender da camada `application` — nunca o contrário. O controller não conhece `ProposalRepository`, nem `Strategy`, nem `ProposalEntity`; ele só conhece os dois use cases, que são o único ponto de entrada para a lógica de negócio.

### 8.5. O primeiro endpoint: criação de propostas

```java
@PostMapping
@PreAuthorize("hasRole('INFLUENCER')")
public ResponseEntity<?> createProposal(@RequestBody CreateProposalRequest request) {

}
```

- `@PostMapping` — especialização de `@RequestMapping` para requisições HTTP `POST`, sem precisar declarar `method = RequestMethod.POST` por extenso. Como a classe já tem `@RequestMapping("/proposals")`, este método responde a `POST /proposals`.
- `@PreAuthorize("hasRole('INFLUENCER')")` — a mesma anotação de autorização declarativa vista desde o Vídeo 01/02: avaliada **antes** de o método rodar; se o usuário autenticado não tiver a role `ROLE_INFLUENCER` entre suas *authorities*, o método nunca chega a ser executado, e o Spring Security devolve `403 Forbidden` automaticamente. Faz sentido que só quem tem o papel de influenciador possa **criar** uma proposta — é a `Owner` da proposta quem a está oferecendo.
- `@RequestBody` — anotação de parâmetro que instrui o Spring a pegar o corpo (*body*) da requisição HTTP (o JSON enviado pelo cliente) e convertê-lo automaticamente em um objeto Java do tipo indicado (`CreateProposalRequest`), usando o Jackson por trás dos panos (a mesma biblioteca já mencionada no Vídeo 03, na explicação do `ObjectMapper`).
- `ResponseEntity<?>` — nesta primeira versão, ainda esboçada, o tipo de retorno é `ResponseEntity<?>` — uma classe do Spring que representa uma resposta HTTP completa (status, cabeçalhos e corpo). O `?` (*wildcard*, "curinga") indica que, por enquanto, ainda não se sabe qual será o tipo exato do corpo da resposta — só um placeholder até o método ser completado. Mais adiante (seção 8.11), esse tipo de retorno é trocado por `ProposalResponse` diretamente, sem precisar do `ResponseEntity`.
- O IntelliJ havia sugerido usar o próprio `CreateProposalUseCase` como tipo do parâmetro do método — sugestão descartada, porque o parâmetro do controller precisa representar o **JSON recebido na requisição**, não o objeto de entrada do use case (que é uma camada diferente, com um propósito diferente). Essa distinção é o motivo de existir o DTO `CreateProposalRequest`, criado a seguir.

### 8.6. `CreateProposalRequest`: o DTO de entrada da requisição

```java
package dio.proposalmanagement.proposal.infrastructure.http.request;

import java.util.Optional;

public record CreateProposalRequest(String title, Optional<String> description) {
}
```

- Um novo pacote, `infrastructure.http.request`, é criado especificamente para abrigar DTOs de entrada de requisições HTTP — paralelo ao `infrastructure.http.response`, criado mais adiante para os DTOs de saída.
- `record` — o mesmo recurso do Java moderno já usado para `LoginRequest` (Vídeo 03), `CreateProposalInput` e `ProposalOutput` (Vídeo 05): uma classe imutável, com construtor, acessores (`title()`, `description()`) e `equals()`/`hashCode()`/`toString()` gerados automaticamente, ideal para modelar um DTO sem lógica de negócio.
- `title` é uma `String` obrigatória; `description` é `Optional<String>` — o mesmo padrão já usado em `CreateProposalInput` e na própria classe de domínio `Proposal`, para representar explicitamente que a descrição da proposta pode estar ausente.
- A aula comenta que este é o ponto natural para, futuramente, adicionar anotações de validação de payload (como as da biblioteca Bean Validation, `@NotBlank`, `@Size`, etc.) — algo que ainda não foi feito neste vídeo, mas que fica registrado como próximo passo possível.

### 8.7. Convertendo o DTO de entrada no input do use case: `toInput()`

```java
package dio.proposalmanagement.proposal.infrastructure.http.request;

import dio.proposalmanagement.proposal.application.input.CreateProposalInput;

import java.util.Optional;

public record CreateProposalRequest(String title, Optional<String> description) {
    public CreateProposalInput toInput() {
        return new CreateProposalInput(title, description);
    }
}
```

- `CreateProposalUseCase.execute(...)` espera receber um `CreateProposalInput` (criado no Vídeo 05) — não faz sentido o use case da camada `application` conhecer diretamente o DTO `CreateProposalRequest`, que é um detalhe da camada `infrastructure.http`. Por isso, é o próprio `CreateProposalRequest` quem sabe se converter em `CreateProposalInput`, através do método `toInput()`.
- `public CreateProposalInput toInput()` — um método de instância (não `static`, diferente do `from(...)` visto em `ProposalOutput` e, mais adiante, em `ProposalResponse`) porque, aqui, já existe uma instância de `CreateProposalRequest` (o `request` recebido pelo controller) que sabe transformar a si mesma em outro objeto. É um pequeno mapeador embutido no próprio record, evitando que essa lógica de conversão vaze para dentro do controller.
- `return new CreateProposalInput(title, description);` — `title` e `description` aqui são os próprios acessores automáticos do record (equivalente a `this.title()` e `this.description()`), reaproveitados diretamente como argumentos do construtor de `CreateProposalInput`.

### 8.8. O acoplamento consciente: `@AuthenticationPrincipal User user`

```java
public ResponseEntity<?> createProposal(@RequestBody CreateProposalRequest request,
                                         @AuthenticationPrincipal User user) {
    var owner = new Owner(new OwnerId(user.getId()), user.getUsername());
    var output = this.createProposalUseCase.execute(request.toInput(), owner);
}
```

- `CreateProposalUseCase.execute(...)` também espera um `Owner` (a classe de domínio criada no Vídeo 05, dentro do módulo `proposal`) — e a única forma de descobrir **quem** é o dono da proposta é olhar para o usuário autenticado, que é uma entidade do módulo `auth` (`User`).
- `@AuthenticationPrincipal User user` — a mesma anotação já usada desde os Vídeos 01/02, injeta diretamente, como parâmetro do método, o objeto do usuário autenticado na sessão atual (o `UserDetails` devolvido por `JpaUserDetailsService`, que na prática é uma instância de `User`).
- A aula é explícita sobre o problema de arquitetura que essa linha revela: o controller do módulo `proposal` está importando `User`, uma entidade do módulo `auth` — um **acoplamento** que contradiz a separação cuidadosamente mantida até aqui (por exemplo, `Owner` foi criado no Vídeo 05 justamente para que a classe de domínio `Proposal` não precisasse conhecer `User`). A diferença é que, aqui, é o **controller** (camada de infraestrutura) que depende de `User`, e não o domínio — o que é considerado um acoplamento aceitável, já que é exatamente o papel da camada de infraestrutura lidar com os detalhes concretos de como a autenticação foi implementada neste projeto específico.
- A aula também discute alternativas mais adequadas para um cenário de **sistema distribuído**, em que o módulo `proposal` fosse um microsserviço isolado: em vez de receber o objeto `User` inteiro, o controller poderia receber apenas um identificador do usuário através de um cabeçalho HTTP (por exemplo, um cabeçalho customizado `X-User-Id`), ou receber um **JWT** (*JSON Web Token* — um token assinado, usado para carregar informações do usuário autenticado entre serviços, sem exigir que o segundo serviço tenha acesso direto à sessão do primeiro) já emitido por um serviço de autenticação separado, e extrair dali o id e o nome do usuário. Nenhuma dessas alternativas foi implementada neste vídeo — a aula optou por manter o acoplamento direto a `User`, reconhecendo-o como uma limitação da forma como a autenticação foi construída até aqui, sem introduzir a complexidade extra de um cenário distribuído que este projeto não tem.
- `new Owner(new OwnerId(user.getId()), user.getUsername())` — aqui `user.getId()` (um `UUID`, gerado pelo Lombok `@Data` em `User`) é embrulhado em um `OwnerId` (o identificador fortemente tipado do domínio de propostas, criado no Vídeo 05), e `user.getUsername()` fornece o nome exibido do dono. É esta linha, e só ela, que efetivamente conecta os dois módulos — todo o resto do controller, e absolutamente tudo em `application` e `domain`, permanece livre dessa dependência.
- `this.createProposalUseCase.execute(request.toInput(), owner)` — o controller finalmente delega para a camada de aplicação, passando o `CreateProposalInput` (convertido a partir do request) e o `Owner` (montado a partir do usuário autenticado). O resultado é um `ProposalOutput` (o DTO de saída do use case, criado no Vídeo 05).

### 8.9. `ProposalResponse`: o DTO de saída

```java
package dio.proposalmanagement.proposal.infrastructure.http.response;

public record ProposalResponse() {
}
```

Um novo pacote, `infrastructure.http.response`, é criado especificamente para os DTOs que representam o JSON devolvido ao cliente — o par exato de `infrastructure.http.request`, criado na seção 8.6. O esqueleto começa como um `record` sem nenhum componente, só para ser detalhado a seguir.

### 8.10. Detalhando `ProposalResponse`: campos, `record` aninhado e `@JsonInclude`

```java
package dio.proposalmanagement.proposal.infrastructure.http.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import dio.proposalmanagement.proposal.application.output.ProposalOutput;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProposalResponse(String id, String title, String description, OwnerResponse owner) {
    public record OwnerResponse(String id, String name) {
    }

    public static ProposalResponse from(ProposalOutput output) {
        return new ProposalResponse(
                output.id(),
                output.title(),
                output.description().orElse(null),
                new OwnerResponse(output.ownerId(), output.ownerName())
        );
    }
}
```

- `String id, String title, String description, OwnerResponse owner` — diferente do DTO de entrada (`Optional<String> description`), aqui a descrição é apenas uma `String`, que pode ser `null`. A razão fica clara na próxima anotação.
- `@JsonInclude(JsonInclude.Include.NON_NULL)` — anotação da biblioteca Jackson (a mesma mencionada no Vídeo 03, por trás do `ObjectMapper`, e já disponível no projeto via `spring-boot-starter-web`, sem necessidade de nenhuma dependência nova) aplicada à classe inteira. Ela instrui o Jackson a **omitir**, na hora de gerar o JSON de saída, qualquer campo cujo valor seja `null` — em vez de escrever `"description": null` no JSON, o campo simplesmente não aparece quando a proposta não tiver descrição.
- `public record OwnerResponse(String id, String name) { }` — um **record aninhado** (*nested record*, o mesmo conceito de classe aninhada já visto no Vídeo 03 com `LoginRequest` dentro de `RestUsernamePasswordAuthenticationFilter`, só que agora aplicado a um `record`): como `OwnerResponse` só faz sentido dentro do contexto de `ProposalResponse` (representando o dono da proposta especificamente na resposta HTTP), ele é declarado dentro da própria classe, em vez de ganhar um arquivo `.java` separado.
- `public static ProposalResponse from(ProposalOutput output)` — mais um **método de fábrica estático** (o mesmo padrão já visto em `ProposalOutput.from(Proposal)`, Vídeo 05), responsável por converter o `ProposalOutput` (DTO de saída do use case, camada `application`) no `ProposalResponse` (DTO de saída HTTP, camada `infrastructure`). Repare que também aqui existem dois DTOs de saída diferentes, por camadas diferentes, seguindo exatamente a mesma lógica de separação já usada entre `CreateProposalRequest` e `CreateProposalInput` (seção 8.7).
- `output.description().orElse(null)` — como `ProposalOutput.description()` é um `Optional<String>` (Vídeo 05), e o campo `description` de `ProposalResponse` é uma `String` simples que aceita `null`, `.orElse(null)` faz exatamente essa conversão: devolve o valor de dentro do `Optional`, se houver, ou `null`, caso contrário. É esse `null`, combinado com `@JsonInclude(NON_NULL)`, que garante que o campo simplesmente desapareça do JSON quando a proposta não tiver descrição.
- `new OwnerResponse(output.ownerId(), output.ownerName())` — monta o sub-objeto `owner` da resposta a partir dos campos já simples (`String`) `ownerId` e `ownerName`, já presentes em `ProposalOutput` desde o Vídeo 05.

### 8.11. Finalizando o endpoint de criação

```java
@PostMapping
@PreAuthorize("hasRole('INFLUENCER')")
public ProposalResponse createProposal(@RequestBody CreateProposalRequest request,
                                        @AuthenticationPrincipal User user) {
    var owner = new Owner(new OwnerId(user.getId()), user.getUsername());
    var output = this.createProposalUseCase.execute(request.toInput(), owner);

    return ProposalResponse.from(output);
}
```

- O tipo de retorno do método muda de `ResponseEntity<?>` (o esboço da seção 8.5) para `ProposalResponse` diretamente. Como o método é anotado com `@RestController`, o Spring já sabe serializar qualquer objeto de retorno em JSON automaticamente, sem precisar embrulhá-lo manualmente em um `ResponseEntity` — e, quando nenhum código de status especial é necessário (o padrão de sucesso, `200 OK`, já é o que o Spring devolve por padrão para um método que retorna um objeto normalmente), `ResponseEntity` acaba sendo um passo a mais desnecessário.
- `return ProposalResponse.from(output);` — a última linha do método completa o ciclo: o `output` devolvido pelo use case é convertido no DTO de resposta HTTP, que é o que efetivamente será serializado em JSON e devolvido ao cliente.

### 8.12. O segundo endpoint: listagem de propostas

```java
@GetMapping
@PreAuthorize("hasAnyRole('INFLUENCER','BRAND')")
public List<ProposalResponse> findAllProposals(@AuthenticationPrincipal User user) {
    var accessScope = getAccessScope(user.getRole());
    listProposalsUseCase.execute();
}
```

- `@GetMapping` — especialização de `@RequestMapping` para requisições `GET`, respondendo, junto com `@RequestMapping("/proposals")` da classe, a `GET /proposals`.
- `hasAnyRole('INFLUENCER','BRAND')` — variação de `hasRole('X')` (já conhecida), que verifica se o usuário autenticado possui **qualquer uma** das roles informadas (aqui, `ROLE_INFLUENCER` **ou** `ROLE_BRAND`) entre suas *authorities*. Diferente do endpoint de criação (só para influenciadores), a listagem faz sentido para os dois perfis — só que, como será visto a seguir, cada perfil enxerga um conjunto diferente de propostas.
- A ideia, explicitada na aula, é evitar criar dois endpoints separados (um `/proposals/mine` para influenciador, outro `/proposals/all` para marca); em vez disso, um único endpoint decide, internamente, o que mostrar, com base no usuário autenticado — e é exatamente para isso que o padrão Strategy (`AccessScope`, `Strategy`, `OwnStrategy`, `AllStrategy`, `Factory`), construído no Vídeo 06, foi desenhado.
- `List<ProposalResponse>` — o tipo de retorno já indica, de cara, que o endpoint devolve uma coleção de propostas, ao contrário do endpoint de criação, que devolve uma única `ProposalResponse`.
- Neste ponto ainda incompleto, `listProposalsUseCase.execute()` é chamado sem nenhum argumento — só um placeholder, já que `ListProposalsUseCase.execute(...)` (Vídeo 06) na verdade exige dois parâmetros (`AccessScope` e `OwnerId`), ainda não calculados neste trecho.

### 8.13. Mapeando a `UserRole` do usuário para um `AccessScope`: o primeiro `switch` do projeto

```java
private static AccessScope getAccessScope(UserRole role) {
    return switch (role) {
        case ROLE_INFLUENCER -> AccessScope.OWN;
        case ROLE_BRAND -> AccessScope.ALL;
    };
}
```

- `ListProposalsUseCase.execute(...)` espera um `AccessScope` (`OWN` ou `ALL`, o enum criado no Vídeo 06), mas tudo o que o controller tem, vindo do usuário autenticado, é uma `UserRole` (`ROLE_INFLUENCER` ou `ROLE_BRAND`, o enum do módulo `auth`, criado no Vídeo 04). É preciso, então, de uma função que converta uma coisa na outra — e é exatamente esse o papel do método privado `getAccessScope`.
- `private static AccessScope getAccessScope(UserRole role)` — um método auxiliar, `private` (só usado dentro da própria classe) e `static` (não depende de nenhum campo de instância do controller, só do parâmetro `role` recebido). Por ser `private`, ele não aparece na API pública do controller — é um detalhe de implementação interno.
- `switch (role) { ... }` — este é o primeiro uso, em todo o projeto, da forma **moderna** do `switch` do Java (disponível desde o Java 14), chamada de *switch expression* ("expressão switch"). Diferente do `switch` tradicional (com `case X: ... break;`), aqui o próprio `switch` **produz um valor**, que pode ser diretamente devolvido com `return`.
- `case ROLE_INFLUENCER -> AccessScope.OWN;` — a seta (`->`) substitui o par `case X: ... break;` do `switch` tradicional: se `role` for `ROLE_INFLUENCER`, a expressão inteira vale `AccessScope.OWN`, sem nenhum risco do problema clássico de "esquecer o `break`" (nesta forma, não existe `break`, e não há como um `case` "vazar" para o próximo por engano).
- Como `role` é do tipo `UserRole`, um `enum` com exatamente dois valores (`ROLE_INFLUENCER`, `ROLE_BRAND`), o compilador do Java consegue verificar, em tempo de compilação, que **todos** os valores possíveis do enum foram cobertos pelos `case` — por isso não é necessário (nem permitido, sem motivo) um `default`. Se um novo valor fosse adicionado a `UserRole` no futuro sem atualizar este `switch`, o código deixaria de compilar até que o novo caso fosse tratado — uma proteção que o `if/else` tradicional não ofereceria.
- Regra de negócio codificada aqui: um influenciador (`ROLE_INFLUENCER`) só pode ver as **próprias** propostas (`AccessScope.OWN`); uma marca (`ROLE_BRAND`) pode ver **todas** as propostas (`AccessScope.ALL`) — a mesma distinção de acesso desenhada, em abstrato, no Vídeo 06 através de `OwnStrategy` e `AllStrategy`, agora finalmente conectada a uma decisão concreta baseada no usuário autenticado.

### 8.14. Completando `findAllProposals`

```java
@GetMapping
@PreAuthorize("hasAnyRole('INFLUENCER','BRAND')")
public List<ProposalResponse> findAllProposals(@AuthenticationPrincipal User user) {
    var accessScope = getAccessScope(user.getRole());
    var ownerId = new OwnerId(user.getId());

    return listProposalsUseCase.execute(accessScope, ownerId)
            .stream()
            .map(ProposalResponse::from)
            .toList();
}
```

- `var ownerId = new OwnerId(user.getId());` — assim como na criação (seção 8.8), o id do usuário autenticado (`UUID`) é embrulhado em um `OwnerId` do domínio de propostas. Repare que, aqui, não é criado um `Owner` completo (com nome), porque `ListProposalsUseCase.execute(...)` só precisa saber o **identificador** do usuário para decidir quais propostas filtrar quando o escopo for `OWN` — o nome não é necessário para esse fim (compare com `OwnStrategy.getProposals(OwnerId ownerId)`, Vídeo 06, que só recebe um `OwnerId`).
- `listProposalsUseCase.execute(accessScope, ownerId)` — o use case é finalmente chamado com os dois argumentos que ele espera desde o Vídeo 06: o escopo de acesso calculado a partir da role, e o id do usuário. Internamente, isso aciona a `Factory` para escolher entre `OwnStrategy` e `AllStrategy` — toda essa decisão fica encapsulada dentro da camada `application`, e o controller não precisa saber nada sobre como o filtro é aplicado.
- `.stream().map(ProposalResponse::from).toList()` — o resultado de `execute(...)` é uma `List<ProposalOutput>` (DTOs de saída do use case); esse encadeamento converte cada elemento para `ProposalResponse` (DTO de saída HTTP), usando `ProposalResponse::from` como **referência de método** (a mesma sintaxe compacta já usada no Vídeo 06, com `Strategy::getScope` dentro de `Factory`), equivalente a escrever `.map(output -> ProposalResponse.from(output))`. `.toList()` (o método de `Stream` introduzido no Java 16, já usado desde o Vídeo 07 em `JpaProposalRepository`) encerra a stream, produzindo uma `List<ProposalResponse>` imutável — exatamente o tipo de retorno declarado no método.

### 8.15. Subindo a aplicação e testando end-to-end pela primeira vez

Com os dois endpoints completos, a aula sobe a aplicação pela primeira vez desde que o módulo `proposal` ganhou um controller — e, pela primeira vez também, a aplicação inicializa sem erros de compilação (ver seção "Pontos de atenção" a seguir para a comparação com os bugs herdados dos vídeos anteriores). Alguns pontos do teste manual, feito através do arquivo `.http` do IntelliJ (um cliente REST embutido na IDE, que permite descrever e disparar requisições HTTP a partir de um arquivo de texto):

- Ao subir a aplicação, o Hibernate cria a tabela `proposal_entity` (usando `ddl-auto=create`, desde o Vídeo 04), e a tabela `user` já é populada automaticamente com os três usuários de teste do projeto — dois influenciadores e uma marca.
- Uma primeira requisição de criação de proposta, enviada com um cookie de sessão (`JSESSIONID`) já expirado, retorna `403 Forbidden` — o mesmo resultado de não enviar cookie nenhum, confirmando que o Spring Security trata uma sessão inválida/expirada da mesma forma que uma requisição não autenticada.
- Autenticando como o usuário do tipo marca (`logistics`) e tentando criar uma proposta com o `JSESSIONID` desse usuário, o resultado também é `403 Forbidden` — confirmando, na prática, que `@PreAuthorize("hasRole('INFLUENCER')")` bloqueia corretamente uma marca de criar propostas.
- Autenticando como um usuário influenciador (`fitness_vibe`) e repetindo a criação com o `JSESSIONID` correto, a proposta é criada com sucesso, devolvendo um JSON com o `id` da proposta, o `title` enviado, e o campo `owner`. É exatamente neste ponto do teste que a aula percebe um problema no valor devolvido para o `id` de dentro de `owner` — a evidência exata desse problema está detalhada na seção "Pontos de atenção".
- São criadas mais duas propostas de teste (uma segunda para `fitness_vibe`, e uma para o outro influenciador, `tech_guru`), e uma consulta direta ao banco confirma as três linhas na tabela `proposal_entity`, cada uma com o `owner_id` correspondente ao usuário autenticado no momento da criação.
- Na listagem (`GET /proposals`), sem nenhum cookie, o resultado é `403 Forbidden` (mesma regra de autenticação exigida). Autenticando como `tech_guru` (influenciador), a listagem devolve só a proposta desse usuário — confirmando `AccessScope.OWN`. Autenticando como `fitness_vibe`, a listagem devolve as duas propostas desse usuário. Por fim, autenticando como a marca `logistics`, a listagem devolve as três propostas, de todos os usuários — confirmando `AccessScope.ALL`.

O arquivo `.http` usado nesses testes é um artefato específico do IntelliJ (normalmente guardado fora do controle de versão do código-fonte, ou em uma pasta de *scratches*), e **não faz parte** do `.zip` desta etapa — por isso não é reproduzido no checkpoint deste tutorial.

---

## Pontos de atenção

Como nos tutoriais anteriores, esta seção compara o que a aula/README narram com o que foi **efetivamente conferido** no código-fonte do `.zip` desta etapa.

### Os dois bugs de compilação herdados dos Vídeos 05, 06 e 07 foram finalmente corrigidos

Os tutoriais dos três vídeos anteriores registraram, de forma consistente, dois problemas de compilação no módulo `proposal`, sem que nenhum deles fosse corrigido até ali:

1. `OwnerId.java` sem o `import java.util.UUID;`.
2. `CreateProposalUseCase.execute(...)` declarado como `void`, mas com um `return` de valor no corpo.

Conferindo o `.zip` desta etapa, **os dois foram corrigidos**:

```java
// domain/OwnerId.java — agora com o import
package dio.proposalmanagement.proposal.domain;

import java.util.UUID;

public record OwnerId(UUID id) {
}
```

```java
// application/CreateProposalUseCase.java — agora retornando ProposalOutput
public ProposalOutput execute(CreateProposalInput input, Owner owner) {
    var proposal = input.toDomain(owner);
    var saved = proposalRepository.save(proposal);

    return ProposalOutput.from(saved);
}
```

Nem a transcrição nem o README do Vídeo 08 mencionam explicitamente o momento em que essas duas correções foram feitas — é bem provável que tenham sido ajustadas rapidamente pelo instrutor ao tentar compilar o projeto pela primeira vez com o controller pronto (exatamente o passo previsto como próximo passo no fim do tutorial do Vídeo 07), mas nenhuma evidência direta disso aparece nos materiais recebidos para esta etapa — só o resultado final, já corrigido.

**Evidência no `.zip`:** diferente das etapas anteriores, a pasta `build/classes/java/main` deste `.zip` contém, agora, **todas** as classes do pacote `proposal`, incluindo as novas deste vídeo (`ProposalController`, `CreateProposalRequest`, `ProposalResponse`, `ProposalResponse$OwnerResponse`) e todas as já existentes (`Proposal`, `ProposalId`, `Owner`, `OwnerId`, `ProposalRepository`, `CreateProposalUseCase`, `ListProposalsUseCase`, `AccessScope`, `Strategy` e suas implementações, `Factory`, `CreateProposalInput`, `ProposalOutput`, `ProposalEntity`, `ProposalEntityRepository`, `JpaProposalRepository`) — consistente com um projeto que agora compila por completo. Os `.jar` gerados em `build/libs/` continuam desatualizados (com apenas três classes, de uma etapa bem anterior do curso), o que só confirma que aquele artefato específico não foi regenerado nesta etapa — não indica nenhum problema de compilação do código-fonte em si.

### Um novo bug de mapeamento aparece: `ProposalOutput.ownerId()` não devolve um `UUID` puro

Ao testar o endpoint de criação (seção 8.15), a aula percebe que o campo `owner.id` do JSON de resposta veio com um valor estranho, e comenta a suspeita de "algum mapeamento errado". Conferindo o código-fonte, o problema está em `ProposalOutput`, e já existia desde o Vídeo 05 — só que, sem nenhum teste de ponta a ponta até agora, não havia como percebê-lo na prática:

```java
public static ProposalOutput from(Proposal proposal) {
    return new ProposalOutput(proposal.getId().id().toString(),
            proposal.getTitle(),
            proposal.getDescription(),
            proposal.getOwner().id().toString(),
            proposal.getOwner().name());
}
```

- `proposal.getOwner()` devolve um `Owner` (`record Owner(OwnerId id, String name)`).
- `.id()` sobre esse `Owner` devolve um `OwnerId` — **não** um `UUID` diretamente (diferente de `proposal.getId().id()`, na linha logo acima, em que `getId()` devolve `ProposalId`, e `.id()` sobre `ProposalId` já devolve o `UUID`, porque `ProposalId` só tem um componente).
- `.toString()`, chamado sobre esse `OwnerId`, usa o `toString()` **automático do record** (gerado pelo compilador, no formato `NomeDoRecord[campo=valor]`) — e não o `toString()` do `UUID` interno. Ou seja, o valor efetivamente gravado no campo `ownerId` do DTO de saída não é algo como `"3fa85f64-5717-..."`, e sim uma string no formato `"OwnerId[id=3fa85f64-5717-...]"`.

Para comparação, `ProposalEntity.from(Proposal)` (Vídeo 07) faz a conversão equivalente **corretamente**, encadeando dois `.id()`:

```java
proposal.getOwner().id().id() // Owner -> OwnerId -> UUID
```

Ou seja, o mapeamento correto para `ProposalOutput.ownerId()` deveria ser `proposal.getOwner().id().id().toString()`, e não `proposal.getOwner().id().toString()`. Esse é, muito provavelmente, o "mapeamento errado" mencionado de passagem na transcrição durante o teste manual — mas nenhuma correção desse trecho específico aparece no `.zip` desta etapa; o código de `ProposalOutput` permanece exatamente como estava desde o Vídeo 05, com esse comportamento. Vale acompanhar o vídeo seguinte para conferir se esse ajuste é feito ali.

### `application.properties` e `build.gradle` permanecem inalterados

Nenhuma nova propriedade de configuração ou dependência foi adicionada neste vídeo. Em especial, `@JsonInclude` (seção 8.10) não exigiu nenhuma dependência nova: a biblioteca Jackson, que fornece essa anotação, já está disponível transitivamente desde que `spring-boot-starter-web` foi adicionado (Vídeo 01/02), e já vinha sendo usada indiretamente pelo `ObjectMapper` desde o Vídeo 03.

---

## Glossário de conceitos novos (Vídeo 08)

Apenas os termos que **ainda não** apareciam nos glossários anteriores. Para os termos já vistos (`@RestController`, `@RequestMapping`, `@GetMapping`, `@AuthenticationPrincipal`, `@PreAuthorize`, `hasRole('X')`, `record`, `@Service`, `Optional<T>`, `@Component`, `CrudRepository`, DTO, método de fábrica estático, referência de método, classe/record aninhado, etc.), consulte `001-...md` a `006-...md`.

| Termo | Significado |
|---|---|
| `@PostMapping` | Especialização de `@RequestMapping` para requisições HTTP `POST` — usada aqui no endpoint de criação de propostas. |
| `@RequestBody` | Anotação de parâmetro do Spring que instrui a conversão automática do corpo (*body*) de uma requisição HTTP (tipicamente JSON) em um objeto Java, usando o Jackson por trás dos panos. |
| `hasAnyRole('X', 'Y', ...)` | Variação de `hasRole('X')` no SpEL do Spring Security: verifica se o usuário autenticado possui **qualquer uma** das roles informadas entre suas *authorities*, em vez de exigir uma única role específica. |
| `ResponseEntity<T>` | Classe do Spring que representa uma resposta HTTP completa (status, cabeçalhos e corpo), permitindo controle explícito sobre o que é devolvido ao cliente. Usada aqui apenas como esboço inicial do endpoint de criação, depois substituída pelo retorno direto do DTO de resposta. |
| Wildcard (`?`) em generics | Em um tipo genérico como `ResponseEntity<?>`, o `?` indica um tipo desconhecido/não especificado — útil como placeholder temporário quando o tipo exato do conteúdo ainda não foi definido. |
| *Switch expression* (`switch` moderno, Java 14+) | Forma do `switch` que **produz um valor**, usando `case valor -> resultado` em vez do `case valor: ... break;` tradicional. Elimina o risco de "vazamento" entre `case`s por `break` esquecido, e, quando usado sobre um `enum`, permite ao compilador verificar se todos os valores possíveis foram cobertos. |
| Exaustividade de `switch` sobre `enum` | Propriedade de uma *switch expression* que cobre, com `case`, todos os valores possíveis de um `enum`: o compilador consegue garantir, em tempo de compilação, que nenhum valor ficou sem tratamento, dispensando (e não aceitando, sem necessidade) uma cláusula `default`. |
| `@JsonInclude(JsonInclude.Include.NON_NULL)` | Anotação da biblioteca Jackson, aplicada a uma classe (ou `record`), que instrui a omitir do JSON de saída qualquer campo cujo valor seja `null`, em vez de serializá-lo como `"campo": null`. |
| JWT (*JSON Web Token*) | Um formato de token assinado digitalmente, usado para transportar informações (como a identidade de um usuário autenticado) de forma verificável entre serviços, sem que o serviço que recebe o token precise ter acesso direto à sessão original onde a autenticação ocorreu. Mencionado neste vídeo como alternativa mais adequada a `@AuthenticationPrincipal User` em um cenário de microsserviços. |
| Cliente REST do IntelliJ (arquivo `.http`) | Ferramenta embutida na IDE IntelliJ que permite descrever requisições HTTP em um arquivo de texto simples (`.http`) e executá-las diretamente do editor, sem precisar de uma ferramenta externa como Postman ou `curl`. Usada neste vídeo para os primeiros testes manuais dos endpoints de `ProposalController`. |

---

## Estado atual do projeto (checkpoint do Vídeo 08)

Este é o retrato fiel do código-fonte na etapa atual, conferido diretamente nos arquivos do `.zip` `proposal-managemnet_ate_o_video08.zip` (incluindo a pasta `build/`, usada como evidência na seção "Pontos de atenção") — e não apenas na narrativa do README. O código é reproduzido **exatamente como está no `.zip`**, incluindo o bug de mapeamento em `ProposalOutput.ownerId()` ainda presente.

### Estrutura de pastas

```
src/main/java/dio/proposalmanagement/
├── ProposalManagementApplication.java
├── auth/
│   ├── domain/
│   │   └── UserRole.java
│   └── infrastructure/
│       ├── http/
│       │   └── Controller.java
│       ├── persistence/
│       │   ├── entity/
│       │   │   └── User.java
│       │   └── repository/
│       │       └── UserRepository.java
│       └── security/
│           ├── JpaUserDetailsService.java
│           ├── RestUsernamePasswordAuthenticationFilter.java
│           └── SecurityConfig.java
└── proposal/
    ├── application/
    │   ├── CreateProposalUseCase.java
    │   ├── ListProposalsUseCase.java
    │   ├── input/
    │   │   └── CreateProposalInput.java
    │   ├── list/
    │   │   ├── AccessScope.java
    │   │   ├── AllStrategy.java
    │   │   ├── Factory.java
    │   │   ├── OwnStrategy.java
    │   │   └── Strategy.java
    │   └── output/
    │       └── ProposalOutput.java
    ├── domain/
    │   ├── Owner.java
    │   ├── OwnerId.java
    │   ├── Proposal.java
    │   ├── ProposalId.java
    │   └── ProposalRepository.java
    └── infrastructure/
        ├── http/
        │   ├── ProposalController.java           <- novo
        │   ├── request/
        │   │   └── CreateProposalRequest.java     <- novo
        │   └── response/
        │       └── ProposalResponse.java          <- novo
        └── persistence/
            ├── entity/
            │   └── ProposalEntity.java
            └── repository/
                ├── JpaProposalRepository.java
                └── ProposalEntityRepository.java
```

### `proposal/infrastructure/http/ProposalController.java` (novo arquivo)

```java
package dio.proposalmanagement.proposal.infrastructure.http;

import dio.proposalmanagement.auth.domain.UserRole;
import dio.proposalmanagement.auth.infrastructure.persistence.entity.User;
import dio.proposalmanagement.proposal.application.CreateProposalUseCase;
import dio.proposalmanagement.proposal.application.ListProposalsUseCase;
import dio.proposalmanagement.proposal.application.list.AccessScope;
import dio.proposalmanagement.proposal.domain.Owner;
import dio.proposalmanagement.proposal.domain.OwnerId;
import dio.proposalmanagement.proposal.infrastructure.http.request.CreateProposalRequest;
import dio.proposalmanagement.proposal.infrastructure.http.response.ProposalResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proposals")
public class ProposalController {
    private final CreateProposalUseCase createProposalUseCase;
    private final ListProposalsUseCase listProposalsUseCase;

    public ProposalController(CreateProposalUseCase createProposalUseCase,
                              ListProposalsUseCase listProposalsUseCase) {
        this.createProposalUseCase = createProposalUseCase;
        this.listProposalsUseCase = listProposalsUseCase;
    }

    @PostMapping
    @PreAuthorize("hasRole('INFLUENCER')")
    public ProposalResponse createProposal(@RequestBody CreateProposalRequest request,
                                            @AuthenticationPrincipal User user) {
        var owner = new Owner(new OwnerId(user.getId()), user.getUsername());
        var output = this.createProposalUseCase.execute(request.toInput(), owner);

        return ProposalResponse.from(output);

    }

    @GetMapping
    @PreAuthorize("hasAnyRole('INFLUENCER','BRAND')")
    public List<ProposalResponse> findAllProposals(@AuthenticationPrincipal User user) {
        var accessScope = getAccessScope(user.getRole());
        var ownerId = new OwnerId(user.getId());

        return listProposalsUseCase.execute(accessScope, ownerId)
                .stream()
                .map(ProposalResponse::from)
                .toList();
    }

    private static AccessScope getAccessScope(UserRole role) {
        return switch (role) {
            case ROLE_INFLUENCER -> AccessScope.OWN;
            case ROLE_BRAND -> AccessScope.ALL;
        };
    }
}
```

### `proposal/infrastructure/http/request/CreateProposalRequest.java` (novo arquivo)

```java
package dio.proposalmanagement.proposal.infrastructure.http.request;

import dio.proposalmanagement.proposal.application.input.CreateProposalInput;

import java.util.Optional;

public record CreateProposalRequest(String title, Optional<String> description) {
    public CreateProposalInput toInput() {
        return new CreateProposalInput(title, description);
    }
}
```

### `proposal/infrastructure/http/response/ProposalResponse.java` (novo arquivo)

```java
package dio.proposalmanagement.proposal.infrastructure.http.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import dio.proposalmanagement.proposal.application.output.ProposalOutput;
import dio.proposalmanagement.proposal.domain.Proposal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProposalResponse(String id, String title, String description, OwnerResponse owner) {
    public record OwnerResponse(String id, String name) {

    }

    public static ProposalResponse from(ProposalOutput output) {
        return new ProposalResponse(
                output.id(),
                output.title(),
                output.description().orElse(null),
                new OwnerResponse(output.ownerId(), output.ownerName())
        );
    }
}
```

> Nota: o `.zip` traz um `import dio.proposalmanagement.proposal.domain.Proposal;` neste arquivo que não chega a ser usado no corpo da classe (o único tipo de domínio referenciado é indiretamente, via `ProposalOutput`) — um import sem uso, sem impacto na compilação, apenas um resíduo provável de edição durante a aula.

### `proposal/application/CreateProposalUseCase.java` (bug corrigido neste vídeo)

```java
package dio.proposalmanagement.proposal.application;

import dio.proposalmanagement.proposal.application.input.CreateProposalInput;
import dio.proposalmanagement.proposal.application.output.ProposalOutput;
import dio.proposalmanagement.proposal.domain.Owner;
import dio.proposalmanagement.proposal.domain.ProposalRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateProposalUseCase {

    private final ProposalRepository proposalRepository;

    public CreateProposalUseCase(ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }

    public ProposalOutput execute(CreateProposalInput input, Owner owner) {
        var proposal = input.toDomain(owner);
        var saved = proposalRepository.save(proposal);

        return ProposalOutput.from(saved);
    }
}
```

### `proposal/domain/OwnerId.java` (bug corrigido neste vídeo)

```java
package dio.proposalmanagement.proposal.domain;

import java.util.UUID;

public record OwnerId(UUID id) {
}
```

### `proposal/application/output/ProposalOutput.java` (inalterado desde o Vídeo 05 — **ainda com o bug de mapeamento descrito em "Pontos de atenção"**)

```java
package dio.proposalmanagement.proposal.application.output;

import dio.proposalmanagement.proposal.domain.Proposal;

import java.util.Optional;

public record ProposalOutput(String id, String title, Optional<String> description, String ownerId, String ownerName) {
    public static ProposalOutput from(Proposal proposal) {
        return new ProposalOutput(proposal.getId().id().toString(),
                proposal.getTitle(),
                proposal.getDescription(),
                proposal.getOwner().id().toString(),
                proposal.getOwner().name());
    }
}
```

### Demais arquivos do módulo `proposal` (inalterados desde os Vídeos 05/06/07)

`Proposal.java`, `ProposalId.java`, `Owner.java`, `ProposalRepository.java`, `CreateProposalInput.java`, `ListProposalsUseCase.java`, `AccessScope.java`, `Strategy.java`, `OwnStrategy.java`, `AllStrategy.java`, `Factory.java`, `ProposalEntity.java`, `ProposalEntityRepository.java` e `JpaProposalRepository.java` não foram tocados neste vídeo — os checkpoints completos desses arquivos permanecem exatamente como documentado em `004-...md` (Vídeo 05), `005-...md` (Vídeo 06) e `006-...md` (Vídeo 07), confirmado agora também neste `.zip`.

### Módulo `auth` (inalterado desde o Vídeo 04)

Nenhum arquivo do pacote `dio.proposalmanagement.auth` foi tocado neste vídeo — o checkpoint completo desses arquivos (`UserRole.java`, `User.java`, `UserRepository.java`, `JpaUserDetailsService.java`, `SecurityConfig.java`, `RestUsernamePasswordAuthenticationFilter.java`, `Controller.java`) permanece exatamente como documentado em `003-...md`, confirmado agora também neste `.zip`.

### `build.gradle` e `application.properties` (inalterados desde o Vídeo 04)

Nenhuma dependência ou propriedade nova foi adicionada neste vídeo — o controller, os dois DTOs e a anotação `@JsonInclude` usam apenas o que já estava disponível desde o Vídeo 01/02 (`spring-boot-starter-web`, que já traz o Jackson transitivamente) e o Vídeo 04 (`spring-boot-starter-data-jpa`, Lombok, `mysql-connector-j`).

---

## Próximos passos: o que vem a partir do Vídeo 09

O README confirma que o curso tem, ao todo, **9 vídeos**. Com o Vídeo 08 concluído, resta apenas:

- **Vídeo 09 — Segurança Escalável:** deve fechar o curso discutindo como fazer a configuração de segurança crescer de forma sustentável. É um candidato natural para retomar, de forma mais aprofundada, dois temas que só foram tocados de passagem até aqui: a discussão *stateful × stateless* e o JWT (mencionado brevemente já no Vídeo 03, e de novo neste Vídeo 08, seção 8.8, como alternativa a `@AuthenticationPrincipal User` em um cenário de microsserviços); e o tema "segurança baseada em papéis", que deu nome ao Vídeo 05, mas que, até aqui, se resumiu ao uso pontual de `hasRole`/`hasAnyRole` em anotações `@PreAuthorize`, sem nenhum tratamento mais estrutural dentro do módulo `auth`. É também um momento plausível para o instrutor corrigir o bug de mapeamento em `ProposalOutput.ownerId()`, identificado neste vídeo durante o teste manual mas ainda não corrigido no `.zip` desta etapa — vale a pena conferir isso especificamente ao anotar o próximo vídeo.

No momento em que este documento foi escrito, o README ainda não trazia a seção de "Anotações" do Vídeo 09 preenchida (só o título e o link do vídeo) — por isso este tutorial cobre exclusivamente o Vídeo 08, até onde os materiais recebidos permitem confirmar.

> **Sugestão de uso deste documento**
> Depois de assistir ao Vídeo 09, crie um novo arquivo numerado (`008-Tutorial_..._Video09.md`) seguindo este mesmo formato, e sempre confira o código real do `.zip` recebido naquela etapa — incluindo a pasta `build/`, que, até aqui, se mostrou uma fonte confiável de evidência sobre se o código realmente compila — antes de escrever qualquer explicação. Vale também conferir, especificamente, se o bug de mapeamento em `ProposalOutput.ownerId()` (seção "Pontos de atenção" deste documento) foi corrigido nessa etapa.
