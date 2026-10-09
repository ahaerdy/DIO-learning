# Tutorial de Estudos — Simplificando a Segurança em APIs REST com Spring Security

**Segurança Baseada em Papéis: iniciando o módulo `proposal` com DDD e Clean Architecture — Vídeo 05**

- Curso: NTT Data — Jornada Tech (DIO) · Módulo 4 — Bootcamp Java + Spring + AI
- Curso: "Simplificando a Segurança em APIs REST com Spring Security"
- Instrutor: Thiago Poiani (Principal Engineer at Skip)
- Projeto: `proposal-managemnet` (pacote base `dio.proposalmanagement`)
- Documento de referência pessoal — nível iniciante em Java
- Continuação de: `003-Tutorial_ProposalManagement_Spring_Security_Video04.md`

---

## Sobre este documento

Este é o **quarto tutorial** da série, cobrindo o **Vídeo 05 — "Segurança Baseada em Papéis"**. Ele foi escrito a partir de três fontes conferidas diretamente: a transcrição do vídeo (`transcricao.md`), a seção de anotações do Vídeo 05 no README atualizado, e o código-fonte real do projeto na etapa correspondente (`proposal-managemnet_ate_o_video05.zip`), conferido arquivo a arquivo — inclusive a pasta `build/`, que revelou uma pista extra sobre o estado real do código (ver seção "Pontos de atenção").

Segue exatamente o mesmo formato dos tutoriais anteriores: bloco de código → explicação linha a linha, em nível iniciante → destaques de "porquê" → uma seção de "Pontos de atenção" comparando a narrativa da aula/README com o que está realmente no seu `.zip` → glossário (só termos novos) → checkpoint fiel do código → próximos passos atualizados.

> Apesar do título do vídeo ("Segurança Baseada em Papéis"), o conteúdo efetivamente gravado — tanto na transcrição quanto no README — **não mexe em nenhum mecanismo de segurança novo**. Nenhuma linha do módulo `auth` é tocada nesta etapa. O vídeo é, na prática, o início da modelagem de domínio (DDD) do estudo de caso de propostas, usando Clean Architecture. Isso é comentado com mais detalhe na seção "Pontos de atenção".

---

## Parte 5 — Segurança Baseada em Papéis (Vídeo 05)

### 5.1. O que muda neste vídeo

Até o Vídeo 04, todo o código do projeto girava em torno de autenticação e autorização (form login, filtro REST customizado, `UserDetailsService` vindo do banco). Este vídeo muda de assunto: começa a modelar a **regra de negócio** do estudo de caso "Influencer & Brand Connect" — influencers criam propostas, marcas visualizam propostas, e cada influencer só pode ver as suas próprias propostas.

Para isso, três frentes são trabalhadas em sequência:

1. Criar um novo módulo `proposal`, separado do módulo `auth`, organizado em três camadas (`domain`, `application`, `infrastructure`) — o mesmo padrão de pastas já usado em `auth`.
2. Modelar as classes de domínio `Proposal`, `Owner`, e os identificadores fortemente tipados `ProposalId` e `OwnerId`, discutindo por que o domínio de propostas é mantido **desacoplado** do módulo de autenticação (`Owner` não é o mesmo tipo que `User`, mesmo representando, por baixo dos panos, a mesma pessoa).
3. Criar a interface `ProposalRepository` (padrão Repository) e o primeiro caso de uso da aplicação, `CreateProposalUseCase`, seguindo o padrão **Use Case** da Clean Architecture, com DTOs de entrada (`CreateProposalInput`) e saída (`ProposalOutput`).

Nada disso ainda é persistido de verdade em banco (a implementação de `ProposalRepository` fica para um vídeo futuro) nem exposto via HTTP (não há controller de propostas ainda) — é só a fundação do domínio e do primeiro caso de uso.

### 5.2. Por que separar `proposal` de `auth`: um comentário sobre arquitetura

Antes de criar qualquer classe, a aula para para discutir uma decisão de design: por que criar um módulo `proposal` inteiramente separado do módulo `auth`, em vez de, por exemplo, associar diretamente uma proposta à entidade `User` já existente?

A resposta é uma prática comum em arquiteturas de sistemas maiores: pensar desde já que o módulo `proposal` **poderia**, no futuro, virar um microsserviço isolado, com seu próprio banco de dados, sem nenhuma dependência direta do banco de autenticação. Para isso ser possível, a classe de domínio `Proposal` não pode depender diretamente da entidade `User` do módulo `auth` — ela precisa de sua própria representação do "dono da proposta", ainda que essa representação aponte, na prática, para o mesmo identificador de usuário. É esse o papel do `Owner`, criado mais adiante neste vídeo.

Isso é um exemplo prático de **baixo acoplamento** entre módulos: dois módulos podem, eventualmente, compartilhar o mesmo dado (o ID do usuário), sem que um dependa da estrutura interna do outro.

### 5.3. Criando o módulo `proposal`: pacotes `application`, `domain` e `infrastructure`

Na IDE, é criado um novo pacote `dio.proposalmanagement.proposal`, com a mesma organização em três subpacotes já usada em `auth` desde o Vídeo 01:

```
dio.proposalmanagement.proposal/
├── application/
├── domain/
└── infrastructure/
```

- **`domain`** — as classes de negócio "puras": `Proposal`, `Owner`, seus identificadores, e as interfaces que o domínio precisa (como `ProposalRepository`). Nada aqui depende de Spring, de JPA ou de HTTP.
- **`application`** — os casos de uso (*use cases*), que orquestram o domínio para realizar uma ação específica (por exemplo, criar uma proposta), além dos DTOs de entrada e saída desses casos de uso.
- **`infrastructure`** — tudo o que é "detalhe técnico": implementação concreta do repositório (usando JPA, quando isso for feito em um vídeo futuro), controllers HTTP, configuração de segurança, etc. Nesta etapa, essa pasta ainda fica vazia dentro do módulo `proposal`.

Essa separação em camadas — domínio no centro, sem depender de nada externo; aplicação orquestrando o domínio; infraestrutura implementando os detalhes técnicos "de fora para dentro" — é a essência da **Clean Architecture**, abordada em mais detalhe na seção 5.9.

### 5.4. Criando a classe de domínio `Proposal`

Dentro de `domain`, é criada a primeira classe do módulo:

```java
package dio.proposalmanagement.proposal.domain;

public class Proposal {
    private ProposalId id;
}
```

- `public class Proposal` — diferente dos DTOs vistos até aqui (que eram todos `record`), `Proposal` é declarada como uma **classe comum**. A justificativa dada na aula: um `record` é adequado para um *data value* (um valor sem identidade própria além dos seus dados — dois `record`s com os mesmos valores são "iguais"). Já `Proposal` é uma **entidade de domínio**: tem um ciclo de vida (é criada, pode ser modificada, tem um identificador que a torna única mesmo que dois campos mudem). Esse é um conceito central de DDD: a diferença entre uma **Entity** (tem identidade, `Proposal`) e um **Value Object** (não tem identidade própria, como `Owner` e os IDs, vistos a seguir).
- `private ProposalId id` — o primeiro campo da classe é o identificador, e ele não é um `UUID` cru: é um tipo próprio, `ProposalId`, criado na próxima seção.

### 5.5. Identificador fortemente tipado: o record `ProposalId`

```java
package dio.proposalmanagement.proposal.domain;

import java.util.UUID;

public record ProposalId(UUID id) {
}
```

- `public record ProposalId(UUID id)` — um `record` que **encapsula** um único valor, um `UUID`. Isso é o padrão de **identificador fortemente tipado** (*strongly typed ID*), um Value Object cujo único propósito é "ser" um ID e nada mais.
- **Por que não usar `UUID` diretamente?** Esse é o argumento central da aula: se um método recebesse, por exemplo, `criarAlgo(UUID proposalId, UUID ownerId)`, nada no compilador impediria alguém de trocar a ordem dos dois argumentos por engano — os dois são apenas `UUID`, tipos idênticos aos olhos do Java. Ao criar um tipo dedicado para cada ID (`ProposalId`, `OwnerId`), o compilador passa a rejeitar essa troca: `criarAlgo(OwnerId ownerId, ProposalId proposalId)` só aceita os argumentos na combinação de tipos correta. É uma forma de mover um erro que só apareceria em tempo de execução (ou pior, silenciosamente) para um erro detectado em **tempo de compilação**.
- Como `ProposalId` é declarado como `record`, o Java gera automaticamente, entre outras coisas, um **construtor canônico** — `new ProposalId(algumUUID)` — que é o único construtor existente neste momento (isso muda na seção 5.8).

### 5.6. Completando os campos de `Proposal`

```java
package dio.proposalmanagement.proposal.domain;

import java.util.Optional;

public class Proposal {
    private ProposalId id;
    private String title;
    private Optional<String> description;
    private Owner owner;
}
```

- `private String title` — o título da proposta, obrigatório.
- `private Optional<String> description` — a descrição é **opcional**, e essa opcionalidade é expressa no próprio tipo do campo com `Optional<String>` (conceito já visto no Vídeo 04), em vez de simplesmente permitir que `description` seja `null`.
- `private Owner owner` — o dono da proposta, do tipo `Owner`, criado a seguir.

### 5.7. Os records `Owner` e `OwnerId`

```java
package dio.proposalmanagement.proposal.domain;

public record Owner(OwnerId id, String name) {
}
```

```java
package dio.proposalmanagement.proposal.domain;

import java.util.UUID;

public record OwnerId(UUID id) {
}
```

- `Owner` é modelado como `record` (mais um Value Object: não tem ciclo de vida próprio dentro do módulo `proposal`, apenas representa, de forma simplificada, quem é o dono de uma proposta) contendo um `OwnerId` e um `name`.
- `OwnerId` segue exatamente o mesmo padrão de `ProposalId`: um `record` que encapsula um `UUID`, evitando misturar `OwnerId` e `ProposalId` por engano em uma assinatura de método.
- O ponto conceitual mais importante aqui, reforçado na transcrição: **`Owner` não é o `User` do módulo `auth`.** São dois tipos Java completamente diferentes, em dois módulos diferentes. Na prática, o `id` de um `Owner` vai ser preenchido com o mesmo `UUID` do `id` de um `User` autenticado — mas essa ligação é feita **fora** do domínio de propostas (em uma camada futura, provavelmente no controller ou em um outro ponto de integração entre os dois módulos), mantendo `Proposal` e `Owner` sem nenhuma dependência de código do pacote `auth`.

### 5.8. Construtores: criando uma `Proposal` nova e um `ProposalId` automático

```java
package dio.proposalmanagement.proposal.domain;

import java.util.Optional;

public class Proposal {
    private ProposalId id;
    private String title;
    private Optional<String> description;
    private Owner owner;

    public Proposal(String title, Optional<String> description, Owner owner) {
        this.id = new ProposalId();
        this.title = title;
        this.description = description;
        this.owner = owner;
    }
}
```

- É adicionado o construtor usado **no momento da criação** de uma proposta nova: ele recebe apenas `title`, `description` e `owner` — não recebe um `id`, porque uma proposta que ainda não existe também não tem um identificador definido de antemão.
- Dentro do construtor, `this.id = new ProposalId()` chama um construtor **sem argumentos** de `ProposalId`. Só que, até este ponto, `ProposalId` só tinha o construtor canônico do record, que **exige** um `UUID`. Por isso, o próximo passo é ajustar `ProposalId`:

```java
package dio.proposalmanagement.proposal.domain;

import java.util.UUID;

public record ProposalId(UUID id) {
    public ProposalId() {
        this(UUID.randomUUID());
    }
}
```

- `public ProposalId()` — um construtor adicional, sem argumentos, declarado **dentro** do `record`. Isso é permitido em Java: além do construtor canônico gerado automaticamente a partir dos componentes do record (`(UUID id)`), é possível declarar construtores extras, desde que, no final, eles deleguem para o construtor canônico.
- `this(UUID.randomUUID())` — a palavra-chave `this(...)`, usada como a primeira instrução de um construtor, **delega** a construção para outro construtor da mesma classe — neste caso, o construtor canônico `ProposalId(UUID id)`, passando um `UUID` gerado aleatoriamente por `UUID.randomUUID()` (o mesmo método estático já visto no Vídeo 04, na entidade `User`). O resultado prático: `new ProposalId()` agora funciona, e gera um identificador novo e aleatório automaticamente.

### 5.9. Lombok em `Proposal`: `@Getter` e `@AllArgsConstructor`

```java
package dio.proposalmanagement.proposal.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@Getter
@AllArgsConstructor
public class Proposal {
    private ProposalId id;
    private String title;
    private Optional<String> description;
    private Owner owner;

    public Proposal(String title, Optional<String> description, Owner owner) {
        this.id = new ProposalId();
        this.title = title;
        this.description = description;
        this.owner = owner;
    }
}
```

- `@Getter` — anotação do Lombok (biblioteca já apresentada no Vídeo 04) que gera automaticamente, em tempo de compilação, um método `getX()` público para **cada** campo privado da classe: `getId()`, `getTitle()`, `getDescription()`, `getOwner()`. Sem essa anotação, cada um desses métodos precisaria ser escrito manualmente à mão.
- `@AllArgsConstructor` — outra anotação do Lombok, que gera um construtor recebendo **todos** os campos da classe, na ordem em que foram declarados: `Proposal(ProposalId id, String title, Optional<String> description, Owner owner)`. Isso não substitui o construtor "de criação" escrito manualmente logo abaixo (que recebe só três argumentos) — o Lombok apenas **adiciona** esse construtor completo à classe, como uma sobrecarga (*overload*). Ele é útil, por exemplo, para reconstruir um `Proposal` que já existe (com um `id` já definido), vindo do banco de dados — um cenário que ainda vai aparecer em um vídeo futuro, quando a persistência real for implementada.

### 5.10. A interface `ProposalRepository`: o padrão Repository

```java
package dio.proposalmanagement.proposal.domain;

import java.util.List;

public interface ProposalRepository {
    List<Proposal> findAll();
    List<Proposal> findAllByOwnerId(OwnerId ownerId);
    Proposal save(Proposal proposal);
}
```

- `public interface ProposalRepository` — note que essa interface fica dentro do pacote `domain`, e não de `infrastructure`. Essa é a essência do **padrão Repository** aplicado com Clean Architecture: o domínio **declara** do que precisa (aqui, três operações: listar tudo, listar por dono, e salvar), mas não sabe **como** essas operações são implementadas — se é com JPA, com uma lista em memória, ou com qualquer outra tecnologia. Isso é o chamado **princípio de inversão de dependência** (a letra "D" do SOLID): o domínio não depende da infraestrutura; é a infraestrutura, no futuro, que vai depender do contrato definido pelo domínio (implementando essa interface).
- `List<Proposal> findAll()` — assinatura de método que retorna todas as propostas cadastradas (comportamento pensado para o caso de uma marca, que pode ver todas as propostas).
- `List<Proposal> findAllByOwnerId(OwnerId ownerId)` — retorna apenas as propostas de um dono específico (comportamento pensado para o caso de um influencer, que só pode ver as próprias propostas — a regra de negócio citada na abertura do vídeo).
- `Proposal save(Proposal proposal)` — persiste uma proposta (nova ou já existente) e devolve a versão salva.
- Nenhum desses métodos tem corpo — é só o contrato. A implementação concreta fica para uma etapa futura, na camada de infraestrutura (por isso a aplicação ainda não compila corretamente por enquanto: ver seção "Pontos de atenção").

### 5.11. O primeiro caso de uso: `CreateProposalUseCase` e o padrão Use Case

```java
package dio.proposalmanagement.proposal.application;

public class CreateProposalUseCase {
}
```

Antes de qualquer código, a aula justifica por que não criar, simplesmente, um `ProposalService` com métodos `save`, `find`, `delete`, `update` (uma abordagem comum e, à primeira vista, mais simples). A crítica feita: um "service" genérico com vários métodos não representativos de uma responsabilidade específica tende a virar uma classe difícil de entender — ela "faz de tudo um pouco" sem uma razão clara para existir como unidade.

Em vez disso, a aula usa o padrão **Use Case** (bastante associado à Clean Architecture, de Robert C. Martin): cada operação de negócio relevante vira sua **própria classe**, com um único método público, chamado por convenção `execute`. Isso aplica diretamente o **Single Responsibility Principle** (SRP — a letra "S" do SOLID): uma classe deve ter um, e apenas um, motivo para mudar. `CreateProposalUseCase` só faz uma coisa: criar uma proposta.

### 5.12. Injetando o repositório no use case

```java
package dio.proposalmanagement.proposal.application;

import dio.proposalmanagement.proposal.domain.ProposalRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateProposalUseCase {
    private final ProposalRepository proposalRepository;

    public CreateProposalUseCase(ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }
}
```

- `@Service` — anotação do Spring (já vista no Vídeo 04) que marca a classe como um *bean* gerenciado pelo container, tornando-a elegível para injeção de dependência em outras classes (como um futuro controller).
- `private final ProposalRepository proposalRepository` e o construtor que o recebe — o mesmo padrão de **injeção de dependência via construtor** já usado em `JpaUserDetailsService` no Vídeo 04: o use case declara que precisa de um `ProposalRepository`, e é o Spring quem, em tempo de execução, decide **qual implementação concreta** entregar (quando essa implementação existir).

### 5.13. O DTO de entrada: pacote `input` e o record `CreateProposalInput`

Em vez de o método `execute` receber, um por um, todos os argumentos necessários para montar uma `Proposal` (`title`, `description`, e assim por diante — o que tende a crescer e ficar difícil de manter à medida que a entidade ganha mais campos), a aula cria um pacote `input`, dentro de `application`, com um DTO dedicado:

```java
package dio.proposalmanagement.proposal.application.input;

public record CreateProposalInput() {
}
```

que, em seguida, ganha os campos necessários:

```java
package dio.proposalmanagement.proposal.application.input;

import dio.proposalmanagement.proposal.domain.Owner;
import dio.proposalmanagement.proposal.domain.Proposal;

import java.util.Optional;

public record CreateProposalInput(String title, Optional<String> description) {
    public Proposal toDomain(Owner owner) {
        return new Proposal(title, description, owner);
    }
}
```

- `public record CreateProposalInput(String title, Optional<String> description)` — o DTO de entrada carrega apenas `title` e `description`. Note que ele **não** carrega o `Owner`: essa é uma decisão deliberada, explicada na transcrição — se o `CreateProposalInput` (que representa os dados que, futuramente, virão de uma requisição HTTP) já incluísse um `Owner` ou, pior, o `UserDetails`/`User` do módulo `auth`, o use case ficaria diretamente acoplado ao módulo de autenticação. Ao manter o `Owner` de fora do DTO, o use case continua podendo ser reaproveitado em qualquer contexto que consiga fornecer um `Owner` — não necessariamente vindo de uma requisição HTTP autenticada.
- `public Proposal toDomain(Owner owner)` — um método de **conversão** (também chamado de *mapper*), que recebe o `Owner` como parâmetro à parte e monta um `Proposal` de domínio a partir dos dados do DTO mais esse parâmetro. Esse é o ponto em que os "dois mundos" (dados vindos de fora, representados pelo DTO, e o `Owner` já resolvido em outra camada) se encontram para formar um objeto de domínio válido.
- `return new Proposal(title, description, owner)` — chama exatamente o construtor de criação definido na seção 5.8, que gera um `ProposalId` novo automaticamente.

### 5.14. Montando o `Proposal` dentro do use case

```java
package dio.proposalmanagement.proposal.application;

import dio.proposalmanagement.proposal.application.input.CreateProposalInput;
import dio.proposalmanagement.proposal.domain.Owner;
import dio.proposalmanagement.proposal.domain.ProposalRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateProposalUseCase {
    private final ProposalRepository proposalRepository;

    public CreateProposalUseCase(ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }

    public void execute(CreateProposalInput input, Owner owner) {
        var proposal = input.toDomain(owner);
        proposalRepository.save(proposal);
    }
}
```

- `public void execute(CreateProposalInput input, Owner owner)` — o único método público do use case passa a receber dois parâmetros: o DTO de entrada, e o `Owner`, recebido separadamente pelo mesmo motivo explicado na seção anterior.
- `var proposal = input.toDomain(owner)` — chama o método de conversão criado no DTO, obtendo um `Proposal` de domínio pronto para ser salvo. `var` (visto no Vídeo 03) permite que o compilador infira o tipo (`Proposal`) a partir do valor retornado.
- `proposalRepository.save(proposal)` — delega a persistência para a interface `ProposalRepository`, sem que o use case precise saber como esse salvamento é feito de fato.

### 5.15. O DTO de saída: o record `ProposalOutput`

Da mesma forma que foi criado um DTO de entrada, a aula cria também um **DTO de saída**, no pacote `application.output`:

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

- `public record ProposalOutput(String id, String title, Optional<String> description, String ownerId, String ownerName)` — diferente do domínio (que usa tipos ricos como `ProposalId` e `Owner`), o DTO de saída usa apenas tipos simples (`String`), prontos para, futuramente, serem serializados em JSON por um controller. A aula comenta que essa estrutura pode evoluir de forma independente do domínio — por exemplo, adicionando campos como um "media kit" no futuro, sem precisar alterar `Proposal`.
- `public static ProposalOutput from(Proposal proposal)` — um **método de fábrica estático** (*static factory method*): em vez de um construtor público comum, a classe expõe um método estático (chamado por convenção `from`, `of`, ou `create`) responsável por construir a instância a partir de outro objeto — aqui, converter um `Proposal` de domínio em um `ProposalOutput`. Isso permite dar um nome descritivo à conversão (`ProposalOutput.from(proposal)` é mais legível do que um construtor genérico) e concentrar, em um único lugar, a lógica de "como transformar um `Proposal` em um `ProposalOutput`".
- `proposal.getId().id().toString()` — aqui aparecem, encadeados, dois níveis de acesso: `proposal.getId()` é o getter gerado pelo Lombok (`@Getter`, seção 5.9), que devolve um `ProposalId`; `.id()` é o **método de acesso automático do record** `ProposalId` (gerado a partir do nome do componente declarado em `public record ProposalId(UUID id)` — sem prefixo `get`, diferente de um getter tradicional, como já visto com `record` no Vídeo 03); e `.toString()` converte o `UUID` resultante em `String`, para caber no campo `id` (do tipo `String`) do `ProposalOutput`.
- `proposal.getOwner().id().toString()` e `proposal.getOwner().name()` seguem exatamente o mesmo raciocínio: `getOwner()` é um getter do Lombok (devolve um `Owner`), e `.id()`/`.name()` são os acessores automáticos do record `Owner`.

### 5.16. Finalizando o `CreateProposalUseCase`: usando o `ProposalOutput`

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

    public void execute(CreateProposalInput input, Owner owner) {
        var proposal = input.toDomain(owner);
        var saved = proposalRepository.save(proposal);

        return ProposalOutput.from(saved);
    }
}
```

- `var saved = proposalRepository.save(proposal)` — o retorno de `save(...)` (definido na interface `ProposalRepository` como `Proposal save(Proposal proposal)`) é guardado em uma nova variável, `saved`.
- `return ProposalOutput.from(saved)` — por fim, o use case passa a **retornar** a versão convertida em DTO de saída, fechando o ciclo: recebe um input (mais um `Owner`, resolvido fora), monta o domínio, persiste, e devolve um output — mantendo essa "porta de entrada e saída" isolada de controllers e de qualquer outra camada de infraestrutura.
- **Atenção:** este trecho, exatamente como aparece no README e no `.zip`, tem um problema de compilação — o método ainda está declarado como `public void execute(...)` (retorno `void`, "sem retorno") duas linhas acima, mas termina com um `return ProposalOutput.from(saved)`, tentando devolver um valor. Isso é abordado em detalhe na próxima seção.

---

## Pontos de atenção: divergências e um erro de compilação real no `.zip`

Comparando o código do `.zip` com a narrativa do README/transcrição, dois pontos merecem destaque — desta vez, diferente dos vídeos anteriores, não são apenas divergências de narrativa: são **erros que impedem a compilação** do módulo `proposal` no estado entregue.

1. **`CreateProposalUseCase.execute` tem um retorno incompatível com sua própria assinatura.** O método é declarado como `public void execute(CreateProposalInput input, Owner owner)` — a palavra `void` diz explicitamente "este método não devolve valor nenhum" — mas o seu corpo termina com `return ProposalOutput.from(saved);`, tentando devolver um `ProposalOutput`. Isso **não compila**: um método `void` só pode usar `return;` (sozinho, sem valor, apenas para encerrar a execução antecipadamente) ou simplesmente não usar `return` algum. A narrativa da aula e do README descreve corretamente a intenção (fazer o use case devolver o `ProposalOutput`), mas o código final, tanto no README quanto no `.zip`, ainda está com a assinatura antiga (`void`), sem o ajuste para `public ProposalOutput execute(...)`. A forma correta, para compilar, seria:
   ```java
   public ProposalOutput execute(CreateProposalInput input, Owner owner) {
       var proposal = input.toDomain(owner);
       var saved = proposalRepository.save(proposal);

       return ProposalOutput.from(saved);
   }
   ```
2. **`OwnerId.java` está sem o `import java.util.UUID;` no `.zip`.** O arquivo real do projeto contém:
   ```java
   package dio.proposalmanagement.proposal.domain;

   public record OwnerId(UUID id) {
   }
   ```
   sem nenhum `import` de `UUID` — apesar de o README, no trecho de código correspondente, mostrar a versão correta, com `import java.util.UUID;`. Sem esse `import`, o compilador não sabe a qual classe `UUID` se refere (poderia ser qualquer coisa), e a compilação falha.
3. **Evidência independente nos artefatos de build do próprio `.zip`.** A pasta `build/classes/java/main` incluída no `.zip` contém os arquivos `.class` **apenas** das classes do módulo `auth` (`User`, `UserRepository`, `Controller`, `SecurityConfig`, etc. — a mesma lista de classes já compiladas desde o Vídeo 04). **Nenhuma** classe do novo módulo `proposal` (`Proposal`, `ProposalId`, `Owner`, `OwnerId`, `ProposalRepository`, `CreateProposalUseCase`, `CreateProposalInput`, `ProposalOutput`) aparece compilada ali. Isso é consistente com os dois erros acima: como eles impedem a compilação do módulo inteiro, a última vez que esse projeto rodou `./gradlew build` com sucesso foi antes de o módulo `proposal` ser criado — o próprio `.zip` "testemunha" que este código, tal como está, ainda não builda.
4. **Divergência de título entre o README e o conteúdo real do vídeo.** O título "Vídeo 05 — Segurança Baseada em Papéis" sugere uma continuação do tema RBAC (*Role-Based Access Control*) — o que seria natural, já que o Vídeo 04 fechou com o bug do prefixo `ROLE_`. Mas nem a transcrição nem o README trazem, de fato, nenhuma mudança em `SecurityConfig`, `UserRole`, ou qualquer outro ponto do módulo `auth`. É bem provável que este título tenha sido reaproveitado do roteiro original do curso, e que o conteúdo de segurança baseada em papéis propriamente dito (múltiplas roles, autorização mais fina, etc.) apareça de fato em um vídeo mais à frente — possivelmente já integrado ao módulo `proposal` (por exemplo, ao decidir, no controller, se quem está autenticado é uma `BRAND` ou um `INFLUENCER`).
5. **A instrutora usa `.getId()` na narração falada, mas o código real usa `.id()` no ponto certo.** Em alguns momentos da transcrição (ex.: "`proposal.getOwner().getId().toString()`"), a fala descreve o acesso ao ID do `Owner` como se fosse um getter tradicional (`getId()`). O código real do `.zip`, no entanto, usa corretamente `proposal.getOwner().id()` — porque `Owner` é um `record`, e records geram acessores **sem** o prefixo `get` (`id()`, não `getId()`), diferente de `Proposal`, que é uma classe comum com Lombok `@Getter` (e por isso usa, sim, `getId()`, `getOwner()`, etc.). Não é um erro no `.zip` — é apenas uma imprecisão da fala, já compatível com o padrão usado desde o Vídeo 03 para `record`s.

> **Recomendação prática:** ao continuar o projeto localmente a partir deste ponto, vale aplicar as duas correções da lista (assinatura de `execute` e `import` de `OwnerId`) antes de seguir para o próximo vídeo — do jeito que está, o projeto não compila.

---

## Glossário de conceitos novos (Vídeo 05)

Apenas os termos que **ainda não** apareciam nos glossários anteriores. Para os termos já vistos (`record`, `Optional<T>`, `var`, `@Service`, `UUID`, `@Component`, etc.), consulte `001-...md`, `002-...md` e `003-...md`.

### Arquitetura e padrões de projeto

| Termo | Significado |
|---|---|
| Clean Architecture | Estilo de arquitetura (proposto por Robert C. Martin) que organiza o código em camadas concêntricas — domínio no centro, sem depender de nada externo; casos de uso orquestrando o domínio; infraestrutura (banco, web, etc.) na camada mais externa, dependendo do domínio, e não o contrário. O objetivo é que a regra de negócio sobreviva a trocas de tecnologia (trocar o banco, o framework web, etc.) sem precisar ser reescrita. |
| Domain-Driven Design (DDD) — Entity × Value Object | Dentro do DDD (já introduzido no Vídeo 01), uma distinção importante: uma **Entity** tem identidade própria e um ciclo de vida (ex.: `Proposal`, identificada pelo seu `ProposalId` mesmo que outros campos mudem); um **Value Object** não tem identidade própria — é definido inteiramente pelos seus valores (ex.: `Owner`, `ProposalId`, `OwnerId`). Essa distinção orienta a escolha entre `class` (para entidades) e `record` (para value objects) usada neste vídeo. |
| Use Case (padrão) | Padrão de organização do código de aplicação, comum em Clean Architecture, em que cada operação de negócio relevante vira sua própria classe, com um único método público (por convenção, `execute`), em vez de um "service" genérico com vários métodos não relacionados. |
| Single Responsibility Principle (SRP) | A letra "S" do princípio SOLID: uma classe deve ter um, e apenas um, motivo para mudar — ou seja, uma única responsabilidade bem definida. É o princípio usado para justificar o padrão Use Case (uma classe por operação) em vez de um `ProposalService` genérico. |
| Repository (padrão de projeto) | Padrão em que o acesso a dados é abstraído por uma interface (definida, aqui, dentro do próprio domínio), separando **o que** a aplicação precisa fazer com os dados (`findAll`, `save`, etc.) de **como** isso é implementado (SQL, JPA, um arquivo, etc.). Diferente da anotação `@Repository` do Spring (vista no Vídeo 04), que é um detalhe técnico de injeção de dependência — aqui o foco é o padrão de projeto em si, aplicado à interface `ProposalRepository`. |
| ID fortemente tipado (*strongly typed ID*) | Técnica de modelagem em que, em vez de usar um tipo genérico (como `UUID` ou `String`) diretamente como identificador, cria-se um tipo dedicado (aqui, `ProposalId`, `OwnerId`) que encapsula esse valor. O compilador passa a impedir a troca acidental entre identificadores de entidades diferentes, mesmo que ambos sejam, por baixo dos panos, um `UUID`. |
| Método de fábrica estático (*static factory method*) | Um método `static` que constrói e devolve uma instância de uma classe, geralmente com um nome descritivo (`from`, `of`, `create`), em vez de expor um construtor genérico — usado aqui em `ProposalOutput.from(proposal)` para converter um objeto de domínio em um DTO de saída. |
| DTO de entrada × DTO de saída (*input* × *output*) | Convenção usada neste vídeo para nomear e organizar DTOs conforme seu papel: um DTO de entrada (`CreateProposalInput`) carrega dados de fora para dentro de um use case; um DTO de saída (`ProposalOutput`) carrega o resultado de um use case para fora dele — mantendo o use case desacoplado tanto de quem o chama quanto de detalhes do domínio. |

### Estrutura da linguagem Java

| Termo | Significado |
|---|---|
| Construtor canônico (de um `record`) | O construtor gerado automaticamente pelo Java para todo `record`, com exatamente os mesmos parâmetros declarados na assinatura do record (ex.: `ProposalId(UUID id)` para `public record ProposalId(UUID id)`). |
| `this(...)` dentro de um construtor | Quando usado como a primeira instrução de um construtor, delega a construção do objeto para **outro construtor da mesma classe** — usado no construtor sem argumentos de `ProposalId` para reaproveitar o construtor canônico, passando um `UUID` aleatório. |
| Acessor automático de `record` | Para cada componente declarado na assinatura de um `record` (ex.: `id` em `record OwnerId(UUID id)`), o Java gera automaticamente um método público de mesmo nome — **sem** o prefixo `get` (`id()`, e não `getId()`) — diferente dos getters gerados pelo Lombok `@Getter` em uma `class` comum. |

---

## Estado atual do projeto (checkpoint do Vídeo 05)

Este é o retrato fiel do código-fonte na etapa atual, conferido diretamente nos arquivos do `.zip` `proposal-managemnet_ate_o_video05.zip` (incluindo a pasta `build/`, usada como evidência na seção "Pontos de atenção") — e não apenas na narrativa do README. O código é reproduzido **exatamente como está no `.zip`**, com os dois problemas de compilação já sinalizados acima.

### Estrutura de pastas

```
proposal-managemnet/
├── build.gradle                                          (inalterado desde o Vídeo 04)
├── settings.gradle                                        (inalterado)
├── compose.yml                                             (inalterado desde o Vídeo 04)
└── src/
    └── main/
        ├── java/dio/proposalmanagement/
        │   ├── ProposalManagementApplication.java          (inalterado)
        │   ├── auth/                                        (inalterado desde o Vídeo 04 — ver checkpoint em 003-...md)
        │   │   ├── application/                             (ainda vazio)
        │   │   ├── domain/
        │   │   │   └── UserRole.java
        │   │   └── infrastructure/
        │   │       ├── http/Controller.java
        │   │       ├── persistence/
        │   │       │   ├── entity/User.java
        │   │       │   └── repository/UserRepository.java
        │   │       └── security/
        │   │           ├── JpaUserDetailsService.java
        │   │           ├── RestUsernamePasswordAuthenticationFilter.java
        │   │           └── SecurityConfig.java
        │   └── proposal/                                    ← módulo novo deste vídeo
        │       ├── application/
        │       │   ├── CreateProposalUseCase.java            ← novo (com bug de compilação, ver acima)
        │       │   ├── input/
        │       │   │   └── CreateProposalInput.java          ← novo
        │       │   └── output/
        │       │       └── ProposalOutput.java                ← novo
        │       ├── domain/
        │       │   ├── Owner.java                             ← novo
        │       │   ├── OwnerId.java                           ← novo (com bug de compilação, ver acima)
        │       │   ├── Proposal.java                          ← novo
        │       │   ├── ProposalId.java                        ← novo
        │       │   └── ProposalRepository.java                ← novo
        │       └── infrastructure/                            ← criado, ainda vazio
        └── resources/
            └── application.properties                        (inalterado)
```

### `proposal/domain/Proposal.java` (novo arquivo)

```java
package dio.proposalmanagement.proposal.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@Getter
@AllArgsConstructor
public class Proposal {
    private ProposalId id;
    private String title;
    private Optional<String> description;
    private Owner owner;

    public Proposal(String title, Optional<String> description, Owner owner) {
        this.id = new ProposalId();
        this.title = title;
        this.description = description;
        this.owner = owner;
    }
}
```

### `proposal/domain/ProposalId.java` (novo arquivo)

```java
package dio.proposalmanagement.proposal.domain;
import java.util.UUID;

public record ProposalId(UUID id) {
    public ProposalId() {
        this(UUID.randomUUID());
    }
}
```

### `proposal/domain/Owner.java` (novo arquivo)

```java
package dio.proposalmanagement.proposal.domain;

public record Owner(OwnerId id, String name) {

}
```

### `proposal/domain/OwnerId.java` (novo arquivo — **sem o `import java.util.UUID;`, não compila**)

```java
package dio.proposalmanagement.proposal.domain;

public record OwnerId(UUID id) {
}
```

### `proposal/domain/ProposalRepository.java` (novo arquivo)

```java
package dio.proposalmanagement.proposal.domain;

import java.util.List;

public interface ProposalRepository {
    List<Proposal> findAll();
    List<Proposal> findAllByOwnerId(OwnerId ownerId);
    Proposal save(Proposal proposal);
}
```

### `proposal/application/input/CreateProposalInput.java` (novo arquivo)

```java
package dio.proposalmanagement.proposal.application.input;

import dio.proposalmanagement.proposal.domain.Owner;
import dio.proposalmanagement.proposal.domain.Proposal;

import java.util.Optional;

public record CreateProposalInput(String title, Optional<String> description) {
    public Proposal toDomain(Owner owner) {
        return new Proposal(title, description, owner);
    }
}
```

### `proposal/application/output/ProposalOutput.java` (novo arquivo)

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

### `proposal/application/CreateProposalUseCase.java` (novo arquivo — **assinatura `void` incompatível com o `return`, não compila**)

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

    public void execute(CreateProposalInput input, Owner owner) {
        var proposal = input.toDomain(owner);
        var saved = proposalRepository.save(proposal);

        return ProposalOutput.from(saved);
    }
}
```

### Módulo `auth` (inalterado desde o Vídeo 04)

Nenhum arquivo do pacote `dio.proposalmanagement.auth` foi tocado neste vídeo — o checkpoint completo desses arquivos (`UserRole.java`, `User.java`, `UserRepository.java`, `JpaUserDetailsService.java`, `SecurityConfig.java`, `RestUsernamePasswordAuthenticationFilter.java`, `Controller.java`) permanece exatamente como documentado em `003-Tutorial_ProposalManagement_Spring_Security_Video04.md`, confirmado agora também neste `.zip`.

### `build.gradle` (inalterado desde o Vídeo 04)

Nenhuma dependência nova foi adicionada neste vídeo — o módulo `proposal`, nesta etapa, usa apenas Java puro e Lombok (já presente desde o Vídeo 04). Não há, ainda, nenhuma anotação JPA (`@Entity`, etc.) nas classes de `proposal`, porque a persistência real deste módulo fica para um vídeo futuro.

---

## Próximos passos: o que vem a partir do Vídeo 06

Diferente da previsão feita no tutorial anterior (que, na ausência do README preenchido para os Vídeos 05–10, estimava um curso de 10 vídeos), o README agora confirma que o curso tem **9 vídeos** ao todo, e já traz os títulos oficiais dos que faltam:

- **Vídeo 06 — Implementando o Use Case de Listagem:** deve criar o `ListProposalUseCase`, mencionado ao final da transcrição do Vídeo 05 — com a regra de negócio já anunciada: quando quem pede a listagem for uma `BRAND`, listar todas as propostas (`findAll`); quando for um `INFLUENCER`, listar apenas as propostas dele (`findAllByOwnerId`). É provável que os dois bugs de compilação identificados neste tutorial (assinatura de `execute` e `import` de `OwnerId`) precisem ser corrigidos antes ou durante esse vídeo para o projeto voltar a compilar.
- **Vídeo 07 — Criando Entidades de Persistência:** deve finalmente implementar `ProposalRepository` de verdade, criando uma entidade JPA para `Proposal` (reaproveitando os padrões já vistos em `User`: `@Entity`, `@Id`, `@GeneratedValue`) e uma classe concreta na camada `infrastructure` que implementa a interface de domínio.
- **Vídeo 08 — Implementando o `ProposalController`:** deve expor os use cases (`CreateProposalUseCase`, `ListProposalUseCase`) via HTTP, criando o primeiro controller REST dedicado a propostas — e é um candidato natural para, finalmente, resolver a conversão entre o `User` autenticado (`@AuthenticationPrincipal`) e o `Owner` do domínio de propostas.
- **Vídeo 09 — Segurança Escalável:** deve fechar o curso discutindo como fazer a configuração de segurança crescer de forma sustentável — um bom momento para retomar a discussão *stateful × stateless* e o JWT, mencionados de passagem no tutorial do Vídeo 03, e possivelmente para, só então, aprofundar o tema "segurança baseada em papéis" que dá nome a este Vídeo 05, mas que ainda não foi de fato abordado (ver "Pontos de atenção").

> **Sugestão de uso deste documento**
> Depois de assistir ao próximo vídeo, crie um novo arquivo numerado (`005-Tutorial_..._Video06.md`) seguindo este mesmo formato, e sempre confira o código real do `.zip` recebido naquela etapa — incluindo, quando disponível, a pasta `build/`, que neste vídeo se mostrou uma fonte extra de evidência sobre se o código realmente compila — antes de escrever qualquer explicação.
