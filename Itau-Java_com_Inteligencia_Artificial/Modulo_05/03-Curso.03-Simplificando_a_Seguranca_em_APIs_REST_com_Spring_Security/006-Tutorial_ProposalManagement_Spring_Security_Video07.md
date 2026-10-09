# Tutorial de Estudos — Simplificando a Segurança em APIs REST com Spring Security

**Criando Entidades de Persistência: implementando o `ProposalRepository` de verdade — Vídeo 07**

- Curso: NTT Data — Jornada Tech (DIO) · Módulo 4 — Bootcamp Java + Spring + AI
- Curso: "Simplificando a Segurança em APIs REST com Spring Security"
- Instrutor: Thiago Poiani (Principal Engineer at Skip)
- Projeto: `proposal-managemnet` (pacote base `dio.proposalmanagement`)
- Documento de referência pessoal — nível iniciante em Java
- Continuação de: `005-Tutorial_ProposalManagement_Spring_Security_Video06.md`

---

## Sobre este documento

Este é o **sexto tutorial** da série, cobrindo o **Vídeo 07 — "Criando Entidades de Persistência"**. Ele foi escrito a partir de três fontes conferidas diretamente: a transcrição do vídeo (`transcricao.md`), a seção de anotações do Vídeo 07 no README atualizado, e o código-fonte real do projeto na etapa correspondente (`proposal-managemnet_ate_o_video07.zip`), conferido arquivo a arquivo — inclusive a pasta `build/`, usada novamente como evidência na seção "Pontos de atenção".

Segue exatamente o mesmo formato dos tutoriais anteriores: bloco de código → explicação linha a linha, em nível iniciante → destaques de "porquê" → uma seção de "Pontos de atenção" comparando a narrativa da aula/README com o que está realmente no seu `.zip` → glossário (só termos novos) → checkpoint fiel do código → próximos passos atualizados.

> Este é o vídeo que finalmente conecta o módulo `proposal` ao banco de dados. Até aqui, `ProposalRepository` era apenas uma interface no domínio, sem nenhuma classe concreta que a implementasse — e os dois casos de uso (`CreateProposalUseCase`, `ListProposalsUseCase`) não tinham como funcionar de verdade. Neste vídeo são criadas as classes que fecham esse ciclo: uma entidade JPA (`ProposalEntity`), dois métodos de mapeamento entre domínio e entidade, e a implementação concreta do repositório (`JpaProposalRepository`).

---

## Parte 7 — Criando Entidades de Persistência (Vídeo 07)

### 7.1. O que muda neste vídeo

No Vídeo 06, o módulo `proposal` ganhou seus dois casos de uso (`CreateProposalUseCase` e `ListProposalsUseCase`), mas a interface `ProposalRepository` — definida lá no Vídeo 05 — continuava sem nenhuma implementação real. Isso significa que, até aqui, não existia nenhuma classe que o Spring pudesse injetar quando um caso de uso pedisse um `ProposalRepository` no construtor.

Este vídeo fecha essa lacuna, criando três arquivos novos, nesta ordem:

1. `ProposalEntity` — a classe que representa a tabela `proposal` no banco de dados, incluindo dois métodos de mapeamento (`from` e `toDomain`) entre ela e a classe de domínio `Proposal`.
2. `ProposalEntityRepository` — a interface que estende `CrudRepository`, no mesmo padrão já usado para `UserRepository` no Vídeo 04.
3. `JpaProposalRepository` — a classe que implementa de fato a interface de domínio `ProposalRepository`, convertendo entre `Proposal` (domínio) e `ProposalEntity` (persistência) em cada método.

Antes de escrever qualquer código, porém, a aula para para discutir um ponto de arquitetura importante: **como vincular uma proposta ao usuário dono dela**, dado que os módulos `proposal` e `auth` foram propositalmente mantidos desacoplados desde o Vídeo 05.

### 7.2. Organizando os pacotes: `infrastructure.persistence.entity` e `infrastructure.persistence.repository`

Assim como já havia sido feito para `auth` no Vídeo 04, a primeira ação é criar a estrutura de pastas dentro da camada de infraestrutura do módulo `proposal`:

```
proposal/infrastructure/
├── persistence/
│   ├── entity/
│   └── repository/
```

- **`persistence`** — pacote que concentra tudo o que envolve comunicação com o banco de dados, mantendo essa responsabilidade isolada das camadas `application` e `domain`.
- **`entity`** — vai abrigar as classes que representam diretamente as tabelas do banco (neste caso, `ProposalEntity`).
- **`repository`** — vai abrigar as classes/interfaces responsáveis por efetivamente acessar esses dados (`ProposalEntityRepository` e `JpaProposalRepository`).

Essa organização repete, ponto por ponto, o padrão já visto em `auth.infrastructure.persistence.entity.User` e `auth.infrastructure.persistence.repository.UserRepository`. A diferença é que agora essas classes moram dentro do módulo `proposal`, isoladas do módulo `auth`.

### 7.3. Por que `ProposalEntity`, e não simplesmente uma segunda classe `Proposal`?

O nome escolhido para a nova classe é `ProposalEntity`, e não apenas `Proposal`, porque já existe uma classe de domínio chamada `Proposal` (criada no Vídeo 05). Ter dois nomes diferentes deixa explícito, só de olhar a assinatura de um método, qual das duas representações está sendo usada:

- `Proposal` (pacote `domain`) — a classe que representa a **regra de negócio**: como uma proposta se comporta, quais dados ela carrega do ponto de vista da aplicação. É essa classe que os casos de uso (`CreateProposalUseCase`, `ListProposalsUseCase`) conhecem e manipulam.
- `ProposalEntity` (pacote `infrastructure.persistence.entity`) — a classe que representa **a tabela do banco**: como os dados ficam armazenados fisicamente, com que tipos de coluna, que restrições (`nullable`, `unique`), etc. É essa classe que o Hibernate/JPA conhece e manipula.

Manter as duas separadas — em vez de anotar a própria `Proposal` com `@Entity` — é uma escolha deliberada de arquitetura: a classe de domínio fica livre de qualquer anotação ou dependência do JPA, podendo evoluir por razões de negócio sem qualquer relação com o banco, e vice-versa. O preço dessa separação é ter que escrever manualmente a conversão entre as duas — o que a aula resolve com os métodos `from` e `toDomain`, vistos na seção 7.6.

### 7.4. Criando `ProposalEntity`: os campos e as anotações já conhecidas

```java
package dio.proposalmanagement.proposal.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProposalEntity {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private UUID ownerId;
}
```

**Explicando linha a linha:**

- `package dio.proposalmanagement.proposal.infrastructure.persistence.entity;` — a classe pertence ao novo pacote criado na seção anterior.
- `import jakarta.persistence.Column;`, `Entity`, `Id` — as três anotações da especificação JPA (Jakarta Persistence API) já usadas em `User`, no Vídeo 04.
- `@Entity` — marca a classe como mapeada para uma tabela do banco. Cada instância de `ProposalEntity` corresponde a uma linha da tabela `proposal`.
- `@Data` (Lombok) — gera automaticamente `getters`, `setters`, `equals`, `hashCode` e `toString` para todos os campos, evitando escrever esse código repetitivo à mão.
- `@NoArgsConstructor` (Lombok) — gera um construtor **sem parâmetros**. O Hibernate/JPA **exige** esse construtor vazio: internamente, ele instancia a entidade primeiro (com o construtor vazio) e só depois preenche os campos via reflexão, um por um.
- `@AllArgsConstructor` (Lombok) — gera um construtor que recebe **todos** os campos como parâmetros, na ordem em que foram declarados na classe. É esse construtor gerado automaticamente que os métodos `from`/`toDomain` (seção 7.6) vão usar para montar um `ProposalEntity` novo a partir de um `Proposal`.
- `@Id` — marca `id` como a chave primária da entidade.
- `private UUID id;` — **sem** a anotação `@GeneratedValue`, diferente do que foi feito em `User` no Vídeo 04. Esse é um ponto deliberado, comentado explicitamente na aula: como a classe de domínio `ProposalId` já gera o `UUID` sozinha (usando `UUID.randomUUID()` dentro do seu próprio construtor, desde o Vídeo 05), não faz sentido pedir também ao Hibernate para gerar um valor — o ID já chega pronto, vindo do domínio, e a entidade apenas guarda esse valor para salvar no banco.
- `@Column(nullable = false)` em `title` e (mais adiante) `ownerId` — exige que essas colunas nunca fiquem vazias no banco.
- `private String description;` — **sem** nenhuma anotação `@Column`. Diferente de `title`, a descrição pode ser nula (opcional), então não há necessidade de restringir nada — o comportamento padrão do JPA já permite valores nulos.

### 7.5. O ponto de discussão central: como vincular `Proposal` a `User`?

Antes de adicionar o campo que representa o "dono" da proposta, a aula interrompe a codificação para discutir uma decisão de arquitetura com bastante profundidade. Vale reproduzir o raciocínio completo, porque ele é o coração conceitual deste vídeo.

**A alternativa mais direta: mapeamento relacional (`@ManyToOne`/`@OneToMany`)**

Como hoje o projeto usa um único banco de dados, seria tecnicamente simples fazer `ProposalEntity` apontar diretamente para a entidade `User`, criando um relacionamento JPA de "muitas propostas para um usuário" (`@ManyToOne`) — o equivalente, no banco relacional, a uma **foreign key** (chave estrangeira): uma coluna em `proposal` que referencia, literalmente, uma linha existente na tabela `user`.

**Vantagens dessa abordagem:**
- É mais simples de implementar — o próprio JPA cuida do relacionamento.
- O banco garante a **integridade referencial**: fisicamente não é possível salvar uma proposta apontando para um `id` de usuário que não existe.

**Desvantagens dessa abordagem:**
- Cria um **acoplamento** forte entre as duas tabelas (e, por extensão, entre os dois módulos, `proposal` e `auth`).
- Fica "mais pesado": a entidade `Proposal` passa a **depender** da entidade `User` existir no mesmo banco, com a mesma estrutura, sempre disponível na mesma consulta.

**A alternativa escolhida: guardar apenas o identificador (`ownerId`)**

A aula opta, em vez disso, por um caminho mais simples na estrutura, mas com implicações mais profundas: `ProposalEntity` não terá nenhuma referência de objeto para `User` — apenas um campo `UUID ownerId`, sem nenhum relacionamento JPA de fato. Do ponto de vista do banco, `ownerId` é só mais uma coluna `UUID`, sem foreign key nenhuma.

O motivo apresentado é pensar além do cenário atual (um único banco, um único serviço) e imaginar uma aplicação de mundo real, dividida em **microsserviços** — pequenos serviços independentes, cada um responsável por uma parte do sistema, muitas vezes com seu **próprio banco de dados isolado**. Se `proposal` fosse, no futuro, um serviço totalmente separado de `auth`, ele **não teria acesso** à tabela de usuários — e talvez nem precisasse dela por completo. O que ele realmente precisa é apenas **saber qual é o identificador** do usuário dono da proposta.

**Prós e contras, resumidos pela aula:**

| | Mapeamento direto (`@ManyToOne`, foreign key) | Apenas o ID (`ownerId: UUID`) |
|---|---|---|
| Complexidade de implementação | Mais simples — o JPA cuida do relacionamento | Um pouco mais de trabalho manual (sem ajuda do JPA) |
| Acoplamento entre módulos | Alto — `Proposal` depende fisicamente de `User` existir | Baixo — `proposal` não sabe nada sobre a estrutura de `User` |
| Isolamento de contexto | Baixo | Alto — mais alinhado a Domain-Driven Design e a serviços distribuídos |
| Integridade | Garantida pelo banco (foreign key) | **Integridade lógica**: a responsabilidade de garantir que o ID existe passa a ser do próprio serviço, não mais do banco |
| Velocidade de escrita | Um pouco mais lenta (o banco precisa validar a foreign key) | Um pouco mais rápida (não há validação de chave estrangeira) |
| Consistência dos dados | Imediata (o banco nunca deixa salvar um ID inválido) | **Consistência eventual** (ver definição abaixo) |

**Consistência eventual** (*eventual consistency*) é o nome dado a esse tradeoff: em sistemas distribuídos, onde cada serviço guarda apenas uma parte dos dados (aqui, `proposal` guarda só o `ownerId`, sem a tabela de usuários), pode existir uma janela de tempo em que os dados de um serviço "confiam" em uma informação que veio de outro serviço sem poder confirmá-la imediatamente contra o próprio banco. É um cenário comum e aceito em arquiteturas de microsserviços, mas exige consciência: se o `ownerId` salvo estiver errado ou o usuário correspondente for removido em outro serviço, `proposal` não teria como saber isso sozinho, olhando apenas para o próprio banco.

A decisão final da aula é seguir com apenas o `UUID ownerId`, justamente para não criar vínculo (relacionamento) entre a camada de propostas e a camada de autenticação — mesmo reconhecendo que, para aplicações mais simples (sem intenção real de virar microsserviços), o mapeamento direto também seria uma opção válida. A ressalva feita é que, mesmo optando pelo mapeamento direto em cenários simples, o relacionamento **não deveria ser bidirecional** — ou seja, `User` não precisaria saber a lista de todas as suas propostas; apenas `Proposal` precisaria saber quem é o seu dono.

### 7.6. Adicionando `ownerId` e `ownerName`: mais um tradeoff, agora sobre duplicação de dados

```java
    @Column(nullable = false)
    private UUID ownerId;

    @Column(nullable = false)
    private String ownerName;
```

Além do identificador, a entidade também passa a guardar o **nome** do usuário dono da proposta (`ownerName`), como um campo simples, direto (não nulo). A justificativa é prática: assim, a aplicação consegue exibir "quem criou esta proposta" sem precisar consultar outro serviço ou outra tabela a cada listagem.

Isso, porém, introduz um novo ponto de discussão: **e se o usuário mudar de nome** no módulo `auth` (ou em outro serviço, num cenário de microsserviços)? A cópia de `ownerName` guardada dentro de `proposal` ficaria desatualizada, já que os dois módulos não têm nenhum vínculo direto. A aula menciona, como possível solução futura (não implementada neste vídeo), a ideia de o módulo `proposal` **escutar um evento** disparado sempre que um usuário for atualizado em `auth`, e usar esse evento para manter `ownerName` sincronizado. Essa técnica — comunicação entre módulos/serviços através de eventos, em vez de consultas diretas — é comum em arquiteturas orientadas a eventos, mas fica, por ora, apenas registrada como um problema em aberto.

A classe completa, com todos os cinco campos, fica:

```java
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProposalEntity {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private UUID ownerId;

    @Column(nullable = false)
    private String ownerName;
}
```

### 7.7. O padrão de projeto Mapper: convertendo `Proposal` → `ProposalEntity`

Com os dois "lados" da conversão prontos (a classe de domínio `Proposal`, já existente, e a nova `ProposalEntity`), o próximo passo é escrever o código que traduz um para o outro. Esse tipo de método — cuja única responsabilidade é converter um objeto de um formato para outro, sem nenhuma outra lógica de negócio — é chamado de **Mapper** (padrão de projeto). Aqui, os dois mapeamentos são implementados como métodos dentro da própria `ProposalEntity`, em vez de em uma classe `Mapper` separada — uma variação comum e igualmente válida do mesmo padrão.

```java
public static ProposalEntity from(Proposal proposal) {
    return new ProposalEntity(
            proposal.getId().id(),
            proposal.getTitle(),
            proposal.getDescription().orElse(null),
            proposal.getOwner().id().id(),
            proposal.getOwner().name()
    );
}
```

**Explicando linha a linha:**

- `public static ProposalEntity from(Proposal proposal)` — um método **estático** (não precisa de uma instância de `ProposalEntity` já existente para ser chamado; é invocado diretamente como `ProposalEntity.from(proposal)`), que recebe um objeto de domínio `Proposal` e devolve um `ProposalEntity` pronto para ser salvo no banco.
- `return new ProposalEntity(...)` — usa o construtor com todos os parâmetros, gerado automaticamente pelo `@AllArgsConstructor` (seção 7.4), passando um valor para cada campo, **na mesma ordem** em que os campos foram declarados na classe (`id`, `title`, `description`, `ownerId`, `ownerName`).
- `proposal.getId().id()` — `Proposal` expõe um `getId()` (gerado pelo `@Getter` do Lombok, visto no Vídeo 05), que devolve um `ProposalId`. Como `ProposalId` é um `record` com um único componente (`id`, do tipo `UUID`), `proposal.getId().id()` "desembrulha" o `UUID` de dentro do `ProposalId`.
- `proposal.getTitle()` — copia o título diretamente, sem nenhuma conversão (ambos os lados usam `String`).
- `proposal.getDescription().orElse(null)` — como visto no Vídeo 05, `getDescription()` devolve um `Optional<String>`. O método `orElse(null)` é a forma de "abrir" o `Optional`: se houver um valor presente, ele é devolvido; se o `Optional` estiver vazio, `orElse` devolve o valor informado como argumento (aqui, `null`). Isso é necessário porque o campo `description` da entidade é uma `String` "crua", sem `Optional` — o JPA não sabe lidar com `Optional<String>` como tipo de coluna.
- `proposal.getOwner().id().id()` — `getOwner()` devolve um `Owner` (`record` com dois componentes: `id`, do tipo `OwnerId`, e `name`, `String`). `proposal.getOwner().id()` acessa o `OwnerId`; o segundo `.id()` desembrulha o `UUID` de dentro dele — o mesmo padrão de "ID fortemente tipado" já usado para `ProposalId`.
- `proposal.getOwner().name()` — acessa diretamente o nome do dono, guardado no `record Owner`.

### 7.8. Completando o Mapper: `toDomain`, o caminho inverso

```java
public Proposal toDomain() {
    return new Proposal(
            new ProposalId(this.id),
            this.title,
            Optional.ofNullable(this.description),
            new Owner(new OwnerId(this.ownerId), this.ownerName)
    );
}
```

**Explicando linha a linha:**

- `public Proposal toDomain()` — diferente de `from`, este método **não é estático**: ele é chamado sobre uma instância já existente de `ProposalEntity` (por exemplo, uma entidade que acabou de ser lida do banco), como em `entidadeSalva.toDomain()`. Ele devolve um objeto `Proposal` (domínio) reconstruído a partir dos dados da entidade.
- `new ProposalId(this.id)` — reconstrói o `ProposalId` "embrulhando" de volta o `UUID` bruto (`this.id`) que veio da coluna do banco. `this` se refere à própria instância de `ProposalEntity` sobre a qual `toDomain()` foi chamado.
- `this.title` — copiado diretamente.
- `Optional.ofNullable(this.description)` — o caminho inverso de `orElse(null)`, visto na seção anterior. `Optional.ofNullable(valor)` é um método estático da própria classe `Optional` que devolve `Optional.empty()` se o valor for `null`, ou um `Optional` contendo o valor, caso contrário. Isso "reembrulha" a `String` (que pode ou não ser nula, vinda do banco) de volta no formato `Optional<String>` que a classe de domínio `Proposal` espera.
- `new Owner(new OwnerId(this.ownerId), this.ownerName)` — reconstrói o `record Owner`, embrulhando novamente o `UUID` bruto do banco (`this.ownerId`) dentro de um `OwnerId`, e reaproveitando o `ownerName` diretamente.

Com os dois métodos prontos, `ProposalEntity` passa a ter uma via de mão dupla: `from(proposal)` converte domínio → entidade (usado ao **salvar**), e `entidade.toDomain()` converte entidade → domínio (usado ao **ler** do banco). Essa é exatamente a mesma ideia central do padrão **Mapper**: isolar, em um único lugar, toda a lógica de "tradução" entre duas representações diferentes do mesmo conceito, para que o resto da aplicação nunca precise fazer essa conversão manualmente, espalhada pelo código.

### 7.9. `ProposalEntityRepository`: o repositório JPA da entidade

```java
package dio.proposalmanagement.proposal.infrastructure.persistence.repository;

import dio.proposalmanagement.proposal.infrastructure.persistence.entity.ProposalEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ProposalEntityRepository extends CrudRepository<ProposalEntity, UUID> {
    List<ProposalEntity> findAllByOwnerId(UUID ownerId);
}
```

**Explicando linha a linha:**

- `public interface ProposalEntityRepository extends CrudRepository<ProposalEntity, UUID>` — o mesmo padrão já usado em `UserRepository` (Vídeo 04): uma interface que estende `CrudRepository`, informando qual entidade ela gerencia (`ProposalEntity`) e qual o tipo da chave primária (`UUID`). O Spring Data JPA gera, em tempo de execução, uma implementação completa dessa interface — com `save`, `findById`, `findAll`, `deleteById`, etc. — sem que uma única linha de acesso ao banco precise ser escrita manualmente.
- `List<ProposalEntity> findAllByOwnerId(UUID ownerId);` — um **query method** (método de consulta derivado, já visto no Vídeo 04 com `findByUsername`): o Spring Data JPA interpreta o nome do método (`findAllBy` + `OwnerId`) e deduz sozinho a consulta SQL equivalente (`SELECT * FROM proposal WHERE owner_id = ?`), desde que `OwnerId` corresponda a um atributo existente em `ProposalEntity` (que, de fato, existe: o campo `ownerId` criado na seção 7.6). Repare que aqui o parâmetro é um `UUID` puro — a mesma ideia de manter a entidade JPA "simples", sem os tipos fortemente tipados do domínio (`OwnerId`, `ProposalId`), que só existem na camada de domínio.

Este repositório **não implementa** a interface `ProposalRepository` do domínio — ele lida exclusivamente com `ProposalEntity`, a representação de persistência. Quem faz a ponte entre os dois mundos é a próxima classe.

### 7.10. `JpaProposalRepository`: finalmente, a implementação de `ProposalRepository`

```java
package dio.proposalmanagement.proposal.infrastructure.persistence.repository;

import dio.proposalmanagement.proposal.domain.OwnerId;
import dio.proposalmanagement.proposal.domain.Proposal;
import dio.proposalmanagement.proposal.domain.ProposalRepository;
import dio.proposalmanagement.proposal.infrastructure.persistence.entity.ProposalEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.StreamSupport;

@Repository
public class JpaProposalRepository implements ProposalRepository {

    private final ProposalEntityRepository proposalEntityRepository;

    public JpaProposalRepository(ProposalEntityRepository proposalEntityRepository) {
        this.proposalEntityRepository = proposalEntityRepository;
    }
    // ...
}
```

**Explicando linha a linha:**

- `@Repository` — anotação do Spring, especializada de `@Component` (já vista no Vídeo 06, usada em `Factory`), que marca a classe como um componente da camada de persistência, tornando-a elegível para injeção de dependência. É essa anotação que resolve, de vez, o erro de injeção que existia desde o Vídeo 05/06: sem ela, o Spring não sabia como preencher o parâmetro `ProposalRepository` no construtor de `CreateProposalUseCase` e `OwnStrategy`/`AllStrategy`, porque não existia nenhum `@Component`/`@Service`/`@Repository` implementando essa interface.
- `public class JpaProposalRepository implements ProposalRepository` — diferente de `ProposalEntityRepository` (uma **interface**, cuja implementação é gerada automaticamente pelo Spring Data), `JpaProposalRepository` é uma **classe concreta**, escrita manualmente, que implementa o contrato `ProposalRepository` (a interface de domínio, definida no Vídeo 05, com os métodos `findAll`, `findAllByOwnerId` e `save`).
- `private final ProposalEntityRepository proposalEntityRepository;` — o repositório JPA "de baixo nível" é recebido como dependência, e não construído manualmente pela classe.
- `public JpaProposalRepository(ProposalEntityRepository proposalEntityRepository) { this.proposalEntityRepository = proposalEntityRepository; }` — injeção de dependência via construtor, o mesmo padrão usado em todas as outras classes do projeto desde o Vídeo 04.

### 7.11. Implementando `findAll()`: de `Iterable` para `List`

```java
@Override
public List<Proposal> findAll() {
    var iterable = proposalEntityRepository.findAll();

    return StreamSupport
            .stream(iterable.spliterator(), false)
            .map(ProposalEntity::toDomain)
            .toList();
}
```

**Explicando linha a linha:**

- `var iterable = proposalEntityRepository.findAll();` — o `findAll()` **herdado de `CrudRepository`** (chamado aqui através de `proposalEntityRepository`, e não de forma recursiva sobre o próprio método que está sendo escrito) tem uma particularidade: ele **não devolve uma `List`**, e sim um `Iterable<ProposalEntity>`.
- **`Iterable<T>`** — é a interface mais básica da biblioteca padrão do Java para representar "algo que pode ser percorrido em um `for-each`". `List`, `Set` e várias outras coleções **implementam** `Iterable`, mas o contrário não é verdade: nem todo `Iterable` é uma `List` — um `Iterable` puro só garante a capacidade de percorrer os elementos um a um, sem oferecer métodos como `.get(indice)`, `.stream()` ou `.size()`.
- Como o restante do código do projeto trabalha com `Stream` (para usar `.map(...)` e `.toList()`, como já feito no Vídeo 06), e `Iterable` sozinho não tem um método `.stream()` embutido, é preciso convertê-lo manualmente.
- `StreamSupport.stream(iterable.spliterator(), false)` — `StreamSupport` é uma classe utilitária da biblioteca padrão do Java (pacote `java.util.stream`), cuja única finalidade é oferecer pontes entre estruturas de dados mais antigas (como `Iterable`) e a API de `Stream`. O método `stream(...)` recebe dois parâmetros: um `Spliterator` (obtido chamando `iterable.spliterator()` — um método que **todo** `Iterable` possui, capaz de dividir a coleção em partes para permitir percorrê-la de forma controlada) e um `boolean` indicando se o processamento deve ser paralelo (`false`, aqui — processamento sequencial, item por item, na ordem original).
- `.map(ProposalEntity::toDomain)` — para cada `ProposalEntity` da stream, chama o método `toDomain()` (visto na seção 7.8), transformando-o em um `Proposal` de domínio. `ProposalEntity::toDomain` é uma **referência de método** (já vista no Vídeo 06 com `Strategy::getScope`) — equivalente à lambda `entity -> entity.toDomain()`.
- `.toList()` — coleta o resultado da stream (agora de `Proposal`, não mais `ProposalEntity`) em uma `List` imutável, encerrando a conversão.

Resumindo o fluxo: `Iterable<ProposalEntity>` → (via `StreamSupport` + `spliterator()`) → `Stream<ProposalEntity>` → (via `.map(toDomain)`) → `Stream<Proposal>` → (via `.toList()`) → `List<Proposal>`, que é exatamente o tipo de retorno exigido pela interface `ProposalRepository`.

### 7.12. Implementando `findAllByOwnerId(...)`: um caminho mais direto

```java
@Override
public List<Proposal> findAllByOwnerId(OwnerId ownerId) {
    return proposalEntityRepository.findAllByOwnerId(ownerId.id())
            .stream()
            .map(ProposalEntity::toDomain)
            .toList();
}
```

**Explicando linha a linha:**

- `proposalEntityRepository.findAllByOwnerId(ownerId.id())` — chama o *query method* criado na seção 7.9. Repare que o parâmetro recebido por este método (`ownerId`, do tipo `OwnerId`, o ID fortemente tipado do domínio) precisa ser "desembrulhado" com `.id()` antes de ser passado adiante, já que `ProposalEntityRepository.findAllByOwnerId` espera um `UUID` puro.
- Diferente da seção anterior, este `findAllByOwnerId` **já devolve diretamente uma `List<ProposalEntity>`** (porque foi declarado assim na interface `ProposalEntityRepository`, e não herdado de `CrudRepository` como um `Iterable` genérico) — por isso, não é necessário usar `StreamSupport`/`spliterator()`: basta chamar `.stream()` diretamente sobre a lista.
- `.map(ProposalEntity::toDomain)` e `.toList()` — exatamente a mesma lógica de conversão da seção anterior.

### 7.13. Implementando `save(...)`: o caminho de ida e volta completo

```java
@Override
public Proposal save(Proposal proposal) {
    var entity = ProposalEntity.from(proposal);
    var saved = proposalEntityRepository.save(entity);

    return saved.toDomain();
}
```

**Explicando linha a linha:**

- `var entity = ProposalEntity.from(proposal);` — converte o `Proposal` de domínio recebido como parâmetro em um `ProposalEntity`, usando o Mapper escrito na seção 7.7.
- `var saved = proposalEntityRepository.save(entity);` — `save(...)`, herdado de `CrudRepository`, efetivamente grava a entidade no banco (executando um `INSERT` ou `UPDATE` SQL, por trás dos panos) e devolve a entidade **salva** — que pode conter valores gerados ou ajustados pelo próprio banco (embora, neste caso específico, o `id` já venha pronto do domínio, como visto na seção 7.4).
- `return saved.toDomain();` — converte a entidade salva de volta para `Proposal` (domínio), usando o Mapper da seção 7.8, e devolve esse objeto de domínio como resultado do método — exatamente o tipo de retorno (`Proposal`) exigido pela interface `ProposalRepository`.

Com os três métodos implementados, `JpaProposalRepository` está completo, e a interface `ProposalRepository` finalmente tem uma implementação concreta e anotada com `@Repository` — o que permite ao Spring injetá-la em `CreateProposalUseCase`, `OwnStrategy` e `AllStrategy`, todos escritos em vídeos anteriores aguardando por essa peça.

---

## Pontos de atenção

Como nos tutoriais anteriores, esta seção compara o que a aula/README narram com o que foi **efetivamente conferido** no código-fonte do `.zip` desta etapa.

### Os dois bugs de compilação herdados dos Vídeos 05 e 06 continuam presentes

O tutorial do Vídeo 06 já havia registrado dois problemas de compilação no módulo `proposal`, e a expectativa registrada nos "próximos passos" daquele documento era que este vídeo, ao finalmente implementar um repositório JPA real, tornasse esses problemas "ainda mais evidentes". Conferindo o `.zip` desta etapa, os dois bugs **seguem exatamente como estavam**, sem nenhuma correção:

1. **`OwnerId.java` continua sem o `import java.util.UUID;`:**

```java
package dio.proposalmanagement.proposal.domain;

public record OwnerId(UUID id) {
}
```

Isso é particularmente relevante neste vídeo porque `ProposalEntity.from(...)` e `ProposalEntity.toDomain()` (seções 7.7 e 7.8) **dependem diretamente** de `OwnerId` para funcionar (`proposal.getOwner().id().id()` e `new OwnerId(this.ownerId)`). Ou seja, o Mapper criado neste vídeo já nasce sobre uma classe que não compila.

2. **`CreateProposalUseCase.execute(...)` continua declarado como `void`, mas com um `return` de valor dentro do corpo:**

```java
public void execute(CreateProposalInput input, Owner owner) {
    var proposal = input.toDomain(owner);
    var saved = proposalRepository.save(proposal);

    return ProposalOutput.from(saved);
}
```

Nenhuma linha deste arquivo foi tocada no Vídeo 07 — ele permanece idêntico ao que já estava documentado nos dois tutoriais anteriores.

**Evidência no `.zip`:** assim como no Vídeo 06, a pasta `build/classes/java/main` deste `.zip` contém apenas as classes do pacote `auth` (`User`, `UserRepository`, `SecurityConfig`, `Controller`, `JpaUserDetailsService`, `RestUsernamePasswordAuthenticationFilter`, `UserRole`, além da classe principal `ProposalManagementApplication`) — **nenhuma classe do pacote `proposal`** aparece compilada, nem as novas (`ProposalEntity`, `ProposalEntityRepository`, `JpaProposalRepository`), nem as já existentes (`Proposal`, `ListProposalsUseCase`, etc.). Isso é consistente com um projeto cujo módulo `proposal` ainda não compila, por causa dos dois bugs acima. O `.jar` gerado em `build/libs/` também está desatualizado (contém apenas três classes, de uma etapa bem anterior do curso), reforçando que o `build/` não foi executado com sucesso desde então.

Nem a transcrição do Vídeo 07 nem o README mencionam esses erros — o vídeo segue normalmente, escrevendo código que compilaria perfeitamente **se** os dois bugs anteriores fossem corrigidos. É bem possível que, na prática, o instrutor os corrija rapidamente em algum ponto não capturado na transcrição (por exemplo, ao rodar a aplicação pela primeira vez, algo mencionado como próximo passo no fim do vídeo), mas nenhuma evidência disso aparece nos materiais desta etapa.

### `ProposalEntity` não usa `@GeneratedValue` — diferente de `User`, e de propósito

Vale reforçar este ponto porque, à primeira vista, pode parecer uma inconsistência com o padrão usado em `User` (Vídeo 04), que usa `@GeneratedValue(strategy = GenerationType.UUID)`. Não é um esquecimento: a transcrição é explícita sobre essa escolha (seção 7.4) — como a classe de domínio `ProposalId` já gera o `UUID` no momento em que um `Proposal` novo é instanciado (desde o Vídeo 05), delegar também ao Hibernate a geração do ID criaria uma duplicidade de responsabilidade, e potencialmente um `UUID` diferente do que o domínio já havia atribuído.

### `application.properties` e `build.gradle` permanecem inalterados

Nenhuma nova propriedade de configuração ou dependência foi adicionada neste vídeo — o `application.properties` (com `spring.jpa.show-sql=true` e `spring.jpa.hibernate.ddl-auto=create`, vistos no Vídeo 04) e o `build.gradle` (já com `spring-boot-starter-data-jpa` e `mysql-connector-j` desde o Vídeo 04) seguem exatamente como estavam. Isso faz sentido: a dependência de JPA já havia sido adicionada para viabilizar a entidade `User`, e a mesma dependência é reaproveitada aqui para `ProposalEntity`, sem necessidade de nada novo.

---

## Glossário de conceitos novos (Vídeo 07)

Apenas os termos que **ainda não** apareciam nos glossários anteriores. Para os termos já vistos (`@Entity`, `@Id`, `@Column`, `@GeneratedValue`, `CrudRepository`, `@Repository`, *query method*, `record`, `Optional<T>`, `var`, `UUID`, referência de método, DDD, Entity × Value Object, generics, etc.), consulte `001-...md` a `005-...md`.

### Arquitetura e padrões de projeto

| Termo | Significado |
|---|---|
| Mapper (padrão de projeto) | Padrão cuja única responsabilidade é converter um objeto de uma representação para outra (por exemplo, de uma classe de domínio para uma entidade de persistência, e vice-versa), sem misturar essa lógica de conversão com regras de negócio ou acesso a dados. Aqui, implementado como dois métodos dentro da própria `ProposalEntity`: `from(Proposal)` (domínio → entidade) e `toDomain()` (entidade → domínio). |
| Foreign key (chave estrangeira) | Em bancos de dados relacionais, uma coluna (ou conjunto de colunas) de uma tabela que referencia a chave primária de outra tabela, e cujo valor o próprio banco garante corresponder a uma linha existente. É a alternativa descartada neste vídeo para vincular `Proposal` a `User`, em favor de guardar apenas um `UUID` sem vínculo formal com nenhuma outra tabela. |
| Integridade referencial × integridade lógica | Integridade referencial é a garantia, fornecida pelo próprio banco através de uma foreign key, de que um identificador salvo sempre corresponde a um registro existente. Integridade lógica é quando essa garantia não existe no banco, e passa a ser responsabilidade do código da aplicação assegurar (ou aceitar o risco de) que um identificador salvo (como o `ownerId` deste vídeo) realmente corresponde a algo válido em outro serviço/tabela. |
| Consistência eventual (*eventual consistency*) | Em sistemas distribuídos (como arquiteturas de microsserviços), a situação em que diferentes partes do sistema podem, temporariamente, não estar totalmente sincronizadas entre si — por exemplo, o nome de um usuário mudar em um serviço sem que a cópia guardada em outro serviço (aqui, `ownerName` dentro de `ProposalEntity`) seja atualizada de imediato. É um tradeoff aceito em troca de menor acoplamento entre os serviços. |
| Microsserviços (menção introdutória) | Estilo de arquitetura em que uma aplicação é dividida em vários serviços pequenos e independentes, cada um com sua própria responsabilidade e, frequentemente, seu próprio banco de dados isolado. Mencionado neste vídeo como o cenário hipotético que justifica não criar um relacionamento direto entre `Proposal` e `User`. |

### Estrutura da linguagem Java / API padrão

| Termo | Significado |
|---|---|
| `Iterable<T>` | Interface mais básica da biblioteca padrão do Java para representar qualquer estrutura que possa ser percorrida em um laço `for-each`. `List`, `Set` e outras coleções implementam `Iterable`, mas um `Iterable` "puro" não oferece métodos como `.get(indice)` ou `.stream()`. É o tipo devolvido por `CrudRepository.findAll()`. |
| `Spliterator<T>` e `iterable.spliterator()` | `Spliterator` (contração de *splittable iterator*, "iterador divisível") é uma interface do Java 8+ capaz de percorrer e, opcionalmente, dividir uma coleção em partes menores — é a peça usada internamente para viabilizar processamento em `Stream`. Todo `Iterable` possui o método `spliterator()`, usado para obter essa estrutura. |
| `StreamSupport.stream(spliterator, paralelo)` | Método estático da classe utilitária `StreamSupport` (pacote `java.util.stream`) que constrói uma `Stream` a partir de um `Spliterator`, servindo de ponte entre um `Iterable` "cru" (que não tem `.stream()` embutido) e a API funcional de `Stream`. O segundo parâmetro (`boolean`) indica se o processamento deve ser paralelo (`true`) ou sequencial (`false`). |
| `Optional.ofNullable(valor)` | Método estático de fábrica da classe `Optional` que devolve `Optional.empty()` se o valor recebido for `null`, ou um `Optional` preenchido com esse valor, caso contrário — o caminho inverso de `Optional#orElse(valorPadrão)`, já visto nos vídeos anteriores. |

---

## Estado atual do projeto (checkpoint do Vídeo 07)

Este é o retrato fiel do código-fonte na etapa atual, conferido diretamente nos arquivos do `.zip` `proposal-managemnet_ate_o_video07.zip` (incluindo a pasta `build/`, usada como evidência na seção "Pontos de atenção") — e não apenas na narrativa do README. O código é reproduzido **exatamente como está no `.zip`**, incluindo os dois problemas de compilação herdados dos Vídeos 05 e 06 e ainda não corrigidos.

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
        │   └── proposal/
        │       ├── application/
        │       │   ├── CreateProposalUseCase.java            (inalterado desde o Vídeo 05 — ainda com bug de compilação)
        │       │   ├── ListProposalsUseCase.java              (inalterado desde o Vídeo 06)
        │       │   ├── input/
        │       │   │   └── CreateProposalInput.java          (inalterado)
        │       │   ├── list/                                  (inalterado desde o Vídeo 06)
        │       │   │   ├── AccessScope.java
        │       │   │   ├── AllStrategy.java
        │       │   │   ├── Factory.java
        │       │   │   ├── OwnStrategy.java
        │       │   │   └── Strategy.java
        │       │   └── output/
        │       │       └── ProposalOutput.java                (inalterado)
        │       ├── domain/
        │       │   ├── Owner.java                             (inalterado)
        │       │   ├── OwnerId.java                           (inalterado — ainda com bug de compilação)
        │       │   ├── Proposal.java                          (inalterado)
        │       │   ├── ProposalId.java                        (inalterado)
        │       │   └── ProposalRepository.java                (inalterado)
        │       └── infrastructure/
        │           └── persistence/                           ← pacote novo deste vídeo
        │               ├── entity/
        │               │   └── ProposalEntity.java             ← novo
        │               └── repository/
        │                   ├── JpaProposalRepository.java      ← novo
        │                   └── ProposalEntityRepository.java   ← novo
        └── resources/
            └── application.properties                        (inalterado)
```

### `proposal/infrastructure/persistence/entity/ProposalEntity.java` (novo arquivo)

```java
package dio.proposalmanagement.proposal.infrastructure.persistence.entity;

import dio.proposalmanagement.proposal.domain.Owner;
import dio.proposalmanagement.proposal.domain.OwnerId;
import dio.proposalmanagement.proposal.domain.Proposal;
import dio.proposalmanagement.proposal.domain.ProposalId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProposalEntity {
    @Id
    private UUID id;
    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private UUID ownerId;

    @Column(nullable = false)
    private String ownerName;

    public static ProposalEntity from(Proposal proposal) {
        return new ProposalEntity(
                proposal.getId().id(),
                proposal.getTitle(),
                proposal.getDescription().orElse(null),
                proposal.getOwner().id().id(),
                proposal.getOwner().name()
        );
    }

    public Proposal toDomain() {
        return new Proposal(
                new ProposalId(this.id),
                this.title,
                Optional.ofNullable(this.description),
                new Owner(new OwnerId(this.ownerId), this.ownerName)
        );
    }

}
```

### `proposal/infrastructure/persistence/repository/ProposalEntityRepository.java` (novo arquivo)

```java
package dio.proposalmanagement.proposal.infrastructure.persistence.repository;
import dio.proposalmanagement.proposal.infrastructure.persistence.entity.ProposalEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ProposalEntityRepository extends CrudRepository<ProposalEntity, UUID> {
    List<ProposalEntity> findAllByOwnerId(UUID ownerId);
}
```

### `proposal/infrastructure/persistence/repository/JpaProposalRepository.java` (novo arquivo)

```java
package dio.proposalmanagement.proposal.infrastructure.persistence.repository;
import dio.proposalmanagement.proposal.domain.OwnerId;
import dio.proposalmanagement.proposal.domain.Proposal;
import dio.proposalmanagement.proposal.domain.ProposalRepository;
import dio.proposalmanagement.proposal.infrastructure.persistence.entity.ProposalEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.StreamSupport;

@Repository
public class JpaProposalRepository implements ProposalRepository {

    private final ProposalEntityRepository proposalEntityRepository;

    public JpaProposalRepository(ProposalEntityRepository proposalEntityRepository) {
        this.proposalEntityRepository = proposalEntityRepository;
    }

    @Override
    public List<Proposal> findAll() {
        var iterable = proposalEntityRepository.findAll();
        return StreamSupport
                .stream(iterable.spliterator(), false)
                .map(ProposalEntity::toDomain)
                .toList();
    }

    @Override
    public List<Proposal> findAllByOwnerId(OwnerId ownerId) {
        return proposalEntityRepository.findAllByOwnerId(ownerId.id())
                .stream()
                .map(ProposalEntity::toDomain)
                .toList();
    }

    @Override
    public Proposal save(Proposal proposal) {
        var entity = ProposalEntity.from(proposal);
        var saved = proposalEntityRepository.save(entity);

        return saved.toDomain();
    }
}
```

### `proposal/application/CreateProposalUseCase.java` (inalterado desde o Vídeo 05 — **ainda com bug de compilação**)

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

### `proposal/domain/OwnerId.java` (inalterado desde o Vídeo 05 — **sem o `import java.util.UUID;`, ainda não compila**)

```java
package dio.proposalmanagement.proposal.domain;

public record OwnerId(UUID id) {
}
```

### Demais arquivos do módulo `proposal` (inalterados desde o Vídeo 06)

`Proposal.java`, `ProposalId.java`, `Owner.java`, `ProposalRepository.java`, `CreateProposalInput.java`, `ProposalOutput.java`, `ListProposalsUseCase.java`, `AccessScope.java`, `Strategy.java`, `OwnStrategy.java`, `AllStrategy.java` e `Factory.java` não foram tocados neste vídeo — os checkpoints completos desses arquivos permanecem exatamente como documentado em `004-...md` (Vídeo 05) e `005-...md` (Vídeo 06), confirmado agora também neste `.zip`.

### Módulo `auth` (inalterado desde o Vídeo 04)

Nenhum arquivo do pacote `dio.proposalmanagement.auth` foi tocado neste vídeo — o checkpoint completo desses arquivos (`UserRole.java`, `User.java`, `UserRepository.java`, `JpaUserDetailsService.java`, `SecurityConfig.java`, `RestUsernamePasswordAuthenticationFilter.java`, `Controller.java`) permanece exatamente como documentado em `003-...md`, confirmado agora também neste `.zip`.

### `build.gradle` e `application.properties` (inalterados desde o Vídeo 04)

Nenhuma dependência ou propriedade nova foi adicionada neste vídeo — a entidade, os mapeamentos e os dois repositórios usam apenas o que já estava disponível desde o Vídeo 04 (`spring-boot-starter-data-jpa`, Lombok, `mysql-connector-j`).

---

## Próximos passos: o que vem a partir do Vídeo 08

O README confirma que o curso tem, ao todo, **9 vídeos**. Com o Vídeo 07 concluído, restam:

- **Vídeo 08 — Implementando o `ProposalController`:** deve expor os use cases (`CreateProposalUseCase`, `ListProposalsUseCase`) via HTTP, criando o primeiro controller REST dedicado a propostas — e é o candidato natural para, finalmente, resolver a conversão entre o `User` autenticado (`@AuthenticationPrincipal`) e o `Owner`/`OwnerId` do domínio de propostas, além de decidir, com base na `Role` do usuário logado, qual `AccessScope` passar para `ListProposalsUseCase.execute(...)` — o mapeamento que ficou propositalmente de fora do Vídeo 06. É também o momento mais provável para que os dois bugs de compilação, arrastados desde os Vídeos 05 e 06 e ainda presentes neste `.zip`, sejam finalmente corrigidos — dificilmente um controller REST completo poderia ser demonstrado funcionando de ponta a ponta (e testado via requisição HTTP) sem que o módulo `proposal` compile.
- **Vídeo 09 — Segurança Escalável:** deve fechar o curso discutindo como fazer a configuração de segurança crescer de forma sustentável — um bom momento para retomar a discussão *stateful × stateless* e o JWT, mencionados de passagem no tutorial do Vídeo 03, e possivelmente para aprofundar o tema "segurança baseada em papéis" que deu nome ao Vídeo 05, mas que, até aqui, ainda não foi tratado de forma explícita dentro do módulo `auth`.

> **Sugestão de uso deste documento**
> Depois de assistir ao próximo vídeo, crie um novo arquivo numerado (`007-Tutorial_..._Video08.md`) seguindo este mesmo formato, e sempre confira o código real do `.zip` recebido naquela etapa — incluindo a pasta `build/`, que, até aqui, se mostrou uma fonte confiável de evidência sobre se o código realmente compila — antes de escrever qualquer explicação.
