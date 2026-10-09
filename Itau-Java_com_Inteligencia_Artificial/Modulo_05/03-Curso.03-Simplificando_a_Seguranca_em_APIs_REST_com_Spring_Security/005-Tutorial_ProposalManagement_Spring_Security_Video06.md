# Tutorial de Estudos — Simplificando a Segurança em APIs REST com Spring Security

**Implementando o Use Case de Listagem: os padrões Strategy e Factory — Vídeo 06**

- Curso: NTT Data — Jornada Tech (DIO) · Módulo 4 — Bootcamp Java + Spring + AI
- Curso: "Simplificando a Segurança em APIs REST com Spring Security"
- Instrutor: Thiago Poiani (Principal Engineer at Skip)
- Projeto: `proposal-managemnet` (pacote base `dio.proposalmanagement`)
- Documento de referência pessoal — nível iniciante em Java
- Continuação de: `004-Tutorial_ProposalManagement_Spring_Security_Video05.md`

---

## Sobre este documento

Este é o **quinto tutorial** da série, cobrindo o **Vídeo 06 — "Implementando o Use Case de Listagem"**. Ele foi escrito a partir de três fontes conferidas diretamente: a transcrição do vídeo (`transcricao.md`), a seção de anotações do Vídeo 06 no README atualizado, e o código-fonte real do projeto na etapa correspondente (`proposal-managemnet_ate_o_video06.zip`), conferido arquivo a arquivo — inclusive a pasta `build/`, usada como evidência na seção "Pontos de atenção".

Segue exatamente o mesmo formato dos tutoriais anteriores: bloco de código → explicação linha a linha, em nível iniciante → destaques de "porquê" → uma seção de "Pontos de atenção" comparando a narrativa da aula/README com o que está realmente no seu `.zip` → glossário (só termos novos) → checkpoint fiel do código → próximos passos atualizados.

> Diferente do Vídeo 05, cujo título ("Segurança Baseada em Papéis") não combinava com o conteúdo, o título deste vídeo é fiel: aqui, sim, é implementado o segundo caso de uso do módulo `proposal` — a listagem — e a regra de negócio que diferencia o que uma `BRAND` e um `INFLUENCER` podem ver. A solução escolhida, no entanto, não é um `if/else` direto sobre a `Role`: é a combinação de dois padrões de projeto, **Strategy** e **Factory**, junto com um novo conceito de domínio criado especialmente para desacoplar essa regra da autenticação — o `AccessScope`.

---

## Parte 6 — Implementando o Use Case de Listagem (Vídeo 06)

### 6.1. O que muda neste vídeo

No Vídeo 05, o módulo `proposal` ganhou suas classes de domínio (`Proposal`, `Owner`, `ProposalId`, `OwnerId`), a interface `ProposalRepository` e o primeiro caso de uso, `CreateProposalUseCase`. Este vídeo cria o **segundo** caso de uso: a listagem de propostas.

A regra de negócio anunciada é: quando um **influencer** consulta a listagem, ele deve receber **apenas as propostas dele**; quando uma **brand** consulta, ela deve receber **todas** as propostas.

A forma mais direta de resolver isso seria um `if/else` dentro do próprio caso de uso: "se for brand, busque tudo; se for influencer, busque só do dono". A aula propõe, em vez disso, avançar um pouco mais e trazer um padrão de projeto chamado **Strategy**, evitando que o caso de uso cresça, no futuro, cheio de condicionais para cada nova regra de acesso.

Para isso, seis novas classes são criadas, nesta ordem:

1. Um novo enum, `AccessScope`, para desacoplar a regra de acesso da `Role` de autenticação.
2. A interface `Strategy`, que define o contrato comum a toda regra de listagem.
3. `OwnStrategy`, a implementação que busca apenas as propostas do dono (escopo `OWN`).
4. `AllStrategy`, a implementação que busca todas as propostas (escopo `ALL`).
5. `Factory`, responsável por escolher, em tempo de execução, qual `Strategy` usar.
6. `ListProposalsUseCase`, o caso de uso propriamente dito, que orquestra a `Factory` e transforma o resultado em `ProposalOutput`.

Assim como no Vídeo 05, nada aqui ainda é exposto via HTTP — não existe controller de propostas até este ponto — e a implementação real de `ProposalRepository` continua pendente para um vídeo futuro.

### 6.2. Por que não usar `Role` diretamente: o enum `AccessScope`

Antes de escrever qualquer `Strategy`, a aula para para discutir um problema de acoplamento. Se o caso de uso de listagem trabalhasse diretamente com a `Role` do usuário autenticado (`BRAND`, `INFLUENCER` — o enum `UserRole`, criado lá no Vídeo 03/04), ele estaria amarrado a um conceito que pertence ao módulo `auth`: autenticação e autorização.

A proposta é criar, dentro do próprio módulo `proposal`, um conceito equivalente, mas com nome e significado ligados à regra de negócio de listagem, não a "quem still pode logar". Esse conceito é o enum `AccessScope`, com dois valores:

```java
package dio.proposalmanagement.proposal.application.list;

public enum AccessScope {
    OWN,
    ALL
}
```

**Explicando linha a linha:**

- `package dio.proposalmanagement.proposal.application.list;` — declara que esta classe pertence a um novo subpacote, `list`, dentro de `proposal.application`. Esse pacote é criado especificamente para reunir as classes deste vídeo (`AccessScope`, `Strategy`, `OwnStrategy`, `AllStrategy`, `Factory`), mantendo o pacote `application` organizado por assunto — o mesmo tipo de decisão já vista com os subpacotes `input` e `output` no Vídeo 05.
- `public enum AccessScope { ... }` — um **enum** (abreviação de *enumeration*, "enumeração") é um tipo especial em Java usado para representar um conjunto **fixo e conhecido** de valores possíveis. Diferente de uma `String` ou de um `int`, que aceitariam qualquer valor, um enum restringe as opções àquelas explicitamente listadas — aqui, apenas `OWN` ou `ALL`. Isso já era usado no projeto desde o Vídeo 03/04, com `UserRole` (`BRAND`, `INFLUENCER`).
- `OWN, ALL` — os dois valores possíveis do enum, separados por vírgula. `OWN` representa o escopo "apenas os meus registros"; `ALL` representa "todos os registros".

A ideia central: em vez de o caso de uso perguntar "é uma brand?", ele passa a perguntar apenas "qual é o meu `AccessScope`?" — `OWN` ou `ALL`. Quem decide **qual** `AccessScope` corresponde a cada `Role` fica, por enquanto, fora dessas classes (a aula comenta que esse mapeamento existiria em algum ponto de fronteira entre os dois módulos — no controller, por exemplo, que ainda não existe). Esse desacoplamento é proposital: se o módulo `proposal` um dia virar um serviço totalmente isolado, ele nem precisa saber que uma `Role` existe — basta receber o escopo já calculado.

### 6.3. A interface `Strategy`: o padrão de projeto Strategy

Com o `AccessScope` pronto, a aula introduz o padrão de projeto usado para representar cada regra de acesso como uma classe própria.

```java
package dio.proposalmanagement.proposal.application.list;

import dio.proposalmanagement.proposal.domain.OwnerId;
import dio.proposalmanagement.proposal.domain.Proposal;

import java.util.List;

public interface Strategy {
    List<Proposal> getProposals(OwnerId ownerId);
    AccessScope getScope();
}
```

**Explicando linha a linha:**

- `public interface Strategy { ... }` — uma **interface** em Java é um contrato: ela declara **quais métodos** uma classe deve implementar, sem dizer **como**. Qualquer classe que declarar `implements Strategy` é obrigada, pelo compilador, a fornecer um corpo para todos os métodos declarados aqui.
- `List<Proposal> getProposals(OwnerId ownerId);` — o método principal do contrato: recebe o `OwnerId` do usuário autenticado e devolve a lista de `Proposal` correspondente àquela regra de acesso. O parâmetro `ownerId` é necessário apenas quando a regra precisa filtrar por dono (o caso `OWN`); para a regra que devolve tudo (`ALL`), o parâmetro simplesmente não é usado dentro do método — a aula comenta que isso é normal em uma `Strategy`: nem toda implementação precisa usar todos os parâmetros do contrato.
- `AccessScope getScope();` — o segundo método do contrato: cada implementação deve informar **qual** escopo ela representa. É esse método que a `Factory` (criada mais adiante) vai usar para descobrir qual implementação corresponde a qual escopo.

**O que é o padrão Strategy, do zero:** Strategy é um padrão de projeto comportamental em que um **algoritmo variável** (aqui, "como buscar as propostas") é extraído para fora da classe que o usa e encapsulado em uma família de classes que implementam a mesma interface. Em vez de uma classe cheia de `if/else` decidindo qual comportamento seguir, cada comportamento vira sua própria classe (sua própria "estratégia"), e quem usa a interface simplesmente chama o método do contrato, sem saber (nem precisar saber) qual implementação concreta está por trás. O ganho aparece quando surge uma nova regra: em vez de editar uma classe cheia de condicionais — arriscando quebrar as regras já existentes — basta **criar mais uma classe** que implementa a interface. Isso está alinhado a um princípio maior da orientação a objetos chamado **Open/Closed Principle** (o "O" do SOLID): o código deve estar aberto para extensão (novas `Strategy`s), mas fechado para modificação (as `Strategy`s existentes não precisam mudar).

### 6.4. `OwnStrategy`: a primeira implementação

```java
package dio.proposalmanagement.proposal.application.list;

import dio.proposalmanagement.proposal.domain.OwnerId;
import dio.proposalmanagement.proposal.domain.Proposal;
import dio.proposalmanagement.proposal.domain.ProposalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnStrategy implements Strategy {
    private final ProposalRepository proposalRepository;

    public OwnStrategy(ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }

    @Override
    public List<Proposal> getProposals(OwnerId ownerId) {
        return proposalRepository.findAllByOwnerId(ownerId);
    }

    @Override
    public AccessScope getScope() {
        return AccessScope.OWN;
    }
}
```

**Explicando linha a linha:**

- `public class OwnStrategy implements Strategy` — declara a classe `OwnStrategy` como uma implementação concreta da interface `Strategy`. A palavra-chave `implements` é o que estabelece esse vínculo: a partir daqui, o compilador exige que `OwnStrategy` tenha um corpo para `getProposals` e `getScope`.
- `private final ProposalRepository proposalRepository;` — um campo (atributo) privado e `final` que guarda a referência para o repositório de propostas. `private` significa que só código dentro da própria classe pode acessar esse campo diretamente; `final` significa que, uma vez atribuído (no construtor), esse valor não pode mais ser trocado — reforçando que a dependência é fixa durante toda a vida do objeto.
- `public OwnStrategy(ProposalRepository proposalRepository) { this.proposalRepository = proposalRepository; }` — o construtor da classe, que recebe o repositório como parâmetro e o guarda no campo. Esse é o padrão **injeção de dependência via construtor**, já usado desde os primeiros vídeos: em vez de a classe criar sua própria instância de `ProposalRepository` (o que a amarraria a uma implementação específica), ela recebe pronta a implementação que o Spring decidir fornecer.
- `@Service` — anotação do Spring que marca esta classe como um **componente gerenciado pelo container de Injeção de Dependência**. Sem essa anotação (ou uma equivalente, como `@Component`), o Spring nunca criaria uma instância de `OwnStrategy` automaticamente, e ela não estaria disponível para ser injetada em nenhum outro lugar — como, por exemplo, na `Factory`, que precisa de **todas** as implementações de `Strategy`.
- `@Override public List<Proposal> getProposals(OwnerId ownerId) { return proposalRepository.findAllByOwnerId(ownerId); }` — a anotação `@Override` avisa ao compilador (e a quem lê o código) que este método está sobrescrevendo/implementando um método declarado em um supertipo (aqui, na interface `Strategy`). Se o nome ou a assinatura não baterem exatamente com o que está na interface, o compilador acusa erro — o que ajuda a pegar erros de digitação cedo. O corpo do método delega diretamente para `proposalRepository.findAllByOwnerId(ownerId)`, o método do `ProposalRepository` (criado no Vídeo 05) que busca apenas as propostas de um dono específico.
- `@Override public AccessScope getScope() { return AccessScope.OWN; }` — implementa o segundo método do contrato, informando que esta classe representa o escopo `OWN`.

### 6.5. `AllStrategy`: a segunda implementação

```java
package dio.proposalmanagement.proposal.application.list;

import dio.proposalmanagement.proposal.domain.OwnerId;
import dio.proposalmanagement.proposal.domain.Proposal;
import dio.proposalmanagement.proposal.domain.ProposalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllStrategy implements Strategy {
    private final ProposalRepository proposalRepository;

    public AllStrategy(ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }

    @Override
    public List<Proposal> getProposals(OwnerId ownerId) {
        return proposalRepository.findAll();
    }

    @Override
    public AccessScope getScope() {
        return AccessScope.ALL;
    }
}
```

Estruturalmente, `AllStrategy` é quase idêntica a `OwnStrategy` — mesma injeção de `ProposalRepository` via construtor, mesma anotação `@Service`. As duas diferenças estão nos dois métodos do contrato:

- `getProposals` chama `proposalRepository.findAll()` (sem filtro nenhum), ignorando completamente o parâmetro `ownerId` recebido — exatamente o cenário mencionado na Seção 6.3, em que uma `Strategy` recebe um parâmetro do contrato sem precisar usá-lo.
- `getScope` retorna `AccessScope.ALL`, identificando esta implementação como a responsável pelo escopo "tudo".

Com essas duas classes prontas, as duas regras de negócio ("só o meu" e "tudo") estão completamente isoladas uma da outra, cada uma em sua própria classe, sem nenhum `if/else` amarrado a papel de usuário.

### 6.6. `Factory`: o padrão de projeto Factory + `Map` + `Collectors.toMap`

Com as duas `Strategy`s prontas, falta um mecanismo que, dado um `AccessScope`, devolva a implementação correta. Essa é a responsabilidade da classe `Factory`.

```java
package dio.proposalmanagement.proposal.application.list;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class Factory {
    private final Map<AccessScope, Strategy> strategies;

    public Factory(List<Strategy> strategies) {
        this.strategies = strategies
                .stream()
                .collect(
                        Collectors.toMap(
                                Strategy::getScope,
                                Function.identity()));
    }

    public Strategy getStrategy(AccessScope scope) {
        return strategies.get(scope);
    }
}
```

**Explicando linha a linha:**

- `@Component` — outra anotação de estereótipo do Spring, com o mesmo efeito prático de `@Service` (marca a classe como gerenciada pelo container de Injeção de Dependência). A aula opta por `@Component` em vez de `@Service` aqui porque `Factory` não representa uma regra de negócio em si (como um "serviço" faria) — ela representa um mecanismo de apoio, de construção de objetos. Na prática, para o Spring, o efeito de habilitar a injeção é o mesmo; a escolha entre as duas anotações é, sobretudo, uma questão de comunicar a intenção da classe a quem lê o código.
- `private final Map<AccessScope, Strategy> strategies;` — um campo que guarda um **mapa** (uma estrutura de dados chave-valor) associando cada `AccessScope` à `Strategy` correspondente. `Map<K, V>` é uma interface da biblioteca padrão do Java para coleções do tipo "para cada chave, existe (no máximo) um valor associado" — aqui, para cada escopo (`OWN` ou `ALL`), existe uma única `Strategy`.
- `public Factory(List<Strategy> strategies) { ... }` — o construtor recebe uma **lista** de `Strategy`. Este é o ponto mais interessante da injeção de dependência do Spring usado neste vídeo: quando um construtor declara um parâmetro do tipo `List<Strategy>`, o Spring automaticamente coleta **todas** as classes gerenciadas (anotadas com `@Service`, `@Component`, etc.) que implementam aquela interface, e as entrega já prontas dentro de uma lista — sem que seja preciso escrever nenhuma configuração adicional. Como `OwnStrategy` e `AllStrategy` são ambas anotadas com `@Service` e ambas implementam `Strategy`, essa lista chega ao construtor da `Factory` já contendo as duas instâncias.
- `strategies.stream()` — converte a `List<Strategy>` recebida em uma **Stream**, a API funcional do Java (introduzida no Java 8) usada para processar coleções de forma declarativa (descrevendo *o que* deve acontecer com cada elemento, em vez de escrever um laço `for` manual descrevendo *como* percorrer a coleção).
- `.collect(Collectors.toMap(Strategy::getScope, Function.identity()))` — a operação terminal da stream, que converte os elementos processados de volta em uma estrutura de dados concreta — aqui, um `Map`. `Collectors.toMap` é um método de fábrica da classe utilitária `Collectors` que constrói exatamente esse tipo de mapa, recebendo dois argumentos:
  - `Strategy::getScope` — uma **referência de método** (*method reference*, sintaxe `Classe::metodo`) que diz: "para cada elemento da stream, chame o método `getScope()` dele, e use o resultado como **chave** do mapa". É uma forma mais concisa de escrever a expressão lambda equivalente, `strategy -> strategy.getScope()`.
  - `Function.identity()` — um método estático da interface funcional `Function<T, R>` que devolve uma função que simplesmente **retorna o próprio valor recebido**, sem transformá-lo (equivalente à lambda `strategy -> strategy`). Aqui, ele é usado para dizer: "o **valor** associado a cada chave é a própria instância da `Strategy`, sem nenhuma conversão".
  - Combinando os dois: a stream percorre a lista `[OwnStrategy, AllStrategy]`, e para cada uma chama `getScope()` para descobrir a chave (`OWN` para a primeira, `ALL` para a segunda) e guarda a própria instância como valor — resultando no mapa `{OWN: <instância de OwnStrategy>, ALL: <instância de AllStrategy>}`.
- `public Strategy getStrategy(AccessScope scope) { return strategies.get(scope); }` — o único método público da classe, usado pelo caso de uso (na próxima seção). Ele apenas consulta o mapa já pronto (`Map.get(chave)`, um método padrão de qualquer `Map`) e devolve a `Strategy` associada ao escopo pedido.

**O que é o padrão Factory, do zero:** Factory (ou *Factory Method*) é um padrão de projeto criacional cuja responsabilidade é **concentrar a lógica de decidir qual instância concreta usar** em um único lugar, em vez de espalhar essa decisão pelo código que consome o objeto. Aqui, a `Factory` é quem sabe, dado um `AccessScope`, qual `Strategy` deve ser usada — o `ListProposalsUseCase` (próxima seção) não precisa saber nada sobre `OwnStrategy` ou `AllStrategy`; ele só pergunta "me dá a strategy para este escopo" e recebe a resposta certa.

### 6.7. `ListProposalsUseCase`: juntando tudo no segundo caso de uso

Com `Strategy` e `Factory` prontas, o caso de uso de listagem é finalizado.

```java
package dio.proposalmanagement.proposal.application;

import dio.proposalmanagement.proposal.application.list.AccessScope;
import dio.proposalmanagement.proposal.application.list.Factory;
import dio.proposalmanagement.proposal.application.output.ProposalOutput;
import dio.proposalmanagement.proposal.domain.OwnerId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListProposalsUseCase {
    private final Factory factory;

    public ListProposalsUseCase(Factory factory) {
        this.factory = factory;
    }

    public List<ProposalOutput> execute(AccessScope scope, OwnerId ownerId) {
        var proposals = factory.getStrategy(scope).getProposals(ownerId);

        return proposals.stream().map(ProposalOutput::from).toList();
    }
}
```

**Explicando linha a linha:**

- `@Service` / `public class ListProposalsUseCase` — segue exatamente o mesmo padrão **Use Case** já visto em `CreateProposalUseCase` (Vídeo 05): uma classe por operação de negócio, anotada com `@Service`, com um único método público de entrada.
- `private final Factory factory;` / construtor — a `Factory` é injetada via construtor, do mesmo jeito que o `ProposalRepository` foi injetado em `CreateProposalUseCase`. Repare que o caso de uso **não** depende diretamente de `OwnStrategy`, `AllStrategy` nem de `ProposalRepository` — ele só conhece a `Factory`, que por sua vez é quem conhece as estratégias concretas. Essa é uma cadeia de dependências em camadas: `ListProposalsUseCase` → `Factory` → `Strategy` → `ProposalRepository`.
- `public List<ProposalOutput> execute(AccessScope scope, OwnerId ownerId) { ... }` — o método de entrada do caso de uso, seguindo a convenção `execute` já usada em `CreateProposalUseCase`. Ele recebe dois parâmetros: o `AccessScope` (calculado em algum ponto de fronteira ainda não implementado — provavelmente o futuro controller) e o `OwnerId` do usuário autenticado.
- `var proposals = factory.getStrategy(scope).getProposals(ownerId);` — a linha central do método, com duas chamadas encadeadas:
  1. `factory.getStrategy(scope)` — pede à `Factory` a implementação de `Strategy` correspondente ao escopo recebido (`OwnStrategy` ou `AllStrategy`, embora o caso de uso não saiba, nem precise saber, qual das duas).
  2. `.getProposals(ownerId)` — chama, sobre a `Strategy` devolvida, o método do contrato que efetivamente busca as propostas — que executa a regra "só o meu" ou "tudo", dependendo de qual implementação foi devolvida no passo anterior.
  
  `var` é usado aqui pelo mesmo motivo já explicado em vídeos anteriores: o Java consegue inferir, a partir do lado direito da atribuição, que o tipo da variável `proposals` é `List<Proposal>`, dispensando a repetição explícita do tipo.
- `return proposals.stream().map(ProposalOutput::from).toList();` — converte a lista de `Proposal` (objetos de domínio) em uma lista de `ProposalOutput` (o DTO de saída criado no Vídeo 05), usando o mesmo padrão de *stream* + *method reference* + método de fábrica estático já visto em `CreateProposalUseCase`: `stream()` converte a lista em uma Stream; `.map(ProposalOutput::from)` aplica, a cada `Proposal` da stream, o método estático `ProposalOutput.from(proposal)`, transformando-a no DTO correspondente; `.toList()` (método adicionado ao Java a partir da versão 16, mais conciso que o antigo `.collect(Collectors.toList())`) reúne o resultado de volta em uma `List`.

Com isso, o segundo caso de uso está completo. A vantagem central da abordagem com Strategy + Factory, reforçada ao final do vídeo: se surgir uma terceira regra de acesso no futuro (por exemplo, um administrador que vê tudo, mas de forma diferente da `brand`), basta criar mais uma classe que implementa `Strategy` e anotá-la com `@Service` — o Spring já a injeta automaticamente na lista da `Factory`, e nem `Factory` nem `ListProposalsUseCase` precisam ser alterados.

---

## Pontos de atenção: divergências e os mesmos erros de compilação, ainda presentes

Comparando o código do `.zip` deste vídeo com a narrativa da transcrição e do README, valem os seguintes destaques:

1. **Os dois bugs de compilação do Vídeo 05 seguem presentes, sem alteração, no `.zip` deste vídeo.** Como já apontado no tutorial anterior, `CreateProposalUseCase.execute` continua declarado como `void` mas termina com `return ProposalOutput.from(saved);`, e `OwnerId.java` continua sem o `import java.util.UUID;`. Nenhum dos dois é tocado na transcrição nem no README do Vídeo 06 — o vídeo foca inteiramente na listagem, sem revisitar o caso de uso de criação. Isso significa que, mesmo depois deste vídeo, **o módulo `proposal` continua sem compilar**, tal como está no `.zip`.
2. **Evidência independente, de novo, na pasta `build/` do próprio `.zip`.** Assim como no Vídeo 05, `build/classes/java/main` traz apenas as classes do módulo `auth` (as mesmas desde o Vídeo 04) — nenhuma classe de `proposal`, nem as antigas (`Proposal`, `CreateProposalUseCase`, etc.) nem as seis novas deste vídeo (`AccessScope`, `Strategy`, `OwnStrategy`, `AllStrategy`, `Factory`, `ListProposalsUseCase`), aparece compilada. Isso é consistente com o ponto 1: os dois erros de compilação, ainda não corrigidos, continuam impedindo que `./gradlew build` seja executado com sucesso desde a criação do módulo `proposal`.
3. **Pequena imprecisão na nomenclatura falada: "ON" em vez de "OWN".** Em vários momentos da transcrição (por exemplo, "eu vou definir esse escopo como sendo **ON**... ou **ALL**", "vou criar minha primeira implementação, que é o **ON strategy**"), a fala usa o nome "ON" para o escopo que, no código real, se chama `OWN` (`AccessScope.OWN`, classe `OwnStrategy`). É apenas uma imprecisão de pronúncia/transcrição — o código-fonte, tanto no README quanto no `.zip`, usa consistentemente `OWN`, nunca `ON`. Este tutorial segue o código real.
4. **A transcrição chama o método de busca completa de "find all" antes de se corrigir para "find all by owner id".** Em um trecho da fala (`00:04:10`), ao descrever a implementação de `OwnStrategy`, a instrutora começa dizendo "vamos fazer o repository find all" e se corrige na sequência: "find all não, desculpa, esse é ON [OWN], então eu preciso fazer o repository find all by owner id". O código final, tanto no README quanto no `.zip`, está correto (`findAllByOwnerId` em `OwnStrategy`, `findAll` em `AllStrategy`) — é apenas um deslize momentâneo de fala, já autocorrigido pela própria instrutora.
5. **Nenhuma mudança no módulo `auth` nem exposição via HTTP.** Assim como no Vídeo 05, este vídeo trabalha inteiramente dentro de `proposal.application` — nenhum arquivo de `auth` é tocado, e não existe ainda nenhum controller que exponha `ListProposalsUseCase` (ou `CreateProposalUseCase`) via HTTP. O README já sinaliza que a criação do controller de propostas fica para o Vídeo 08.

> **Recomendação prática:** as mesmas duas correções apontadas no tutorial do Vídeo 05 continuam pendentes — ajustar a assinatura de `CreateProposalUseCase.execute` para `public ProposalOutput execute(...)` e adicionar `import java.util.UUID;` em `OwnerId.java`. Sem elas, o projeto, tal como está no `.zip` deste vídeo, ainda não compila.

---

## Glossário de conceitos novos (Vídeo 06)

Apenas os termos que **ainda não** apareciam nos glossários anteriores. Para os termos já vistos (`record`, `Optional<T>`, `var`, `@Service`, `UUID`, `@Component`, Clean Architecture, Use Case, Repository, ID fortemente tipado, etc.), consulte `001-...md` a `004-...md`.

### Arquitetura e padrões de projeto

| Termo | Significado |
|---|---|
| Strategy (padrão de projeto) | Padrão comportamental em que um comportamento variável (aqui, "como buscar as propostas de acordo com o escopo de acesso") é extraído para uma interface comum (`Strategy`), com uma classe própria para cada variação do comportamento (`OwnStrategy`, `AllStrategy`). Evita `if/else` acumulados dentro de uma única classe e permite adicionar novas regras criando apenas novas classes, sem alterar as existentes. |
| Factory (padrão de projeto) | Padrão criacional cuja responsabilidade é concentrar, em uma única classe, a lógica de **qual** instância concreta de uma interface deve ser usada em cada situação — aqui, a classe `Factory`, que devolve a `Strategy` correspondente a um `AccessScope`, usando um `Map` como estrutura de busca. |
| Open/Closed Principle (OCP) | A letra "O" do princípio SOLID: uma classe (ou módulo) deve estar aberta para extensão, mas fechada para modificação. É o princípio que justifica a escolha do padrão Strategy neste vídeo: uma nova regra de acesso é adicionada com uma nova classe (extensão), sem precisar editar `ListProposalsUseCase`, `Factory`, `OwnStrategy` ou `AllStrategy` já existentes (sem modificação). |
| Injeção de uma coleção de implementações (`List<Interface>`) | Recurso do Spring em que, se um construtor declara um parâmetro do tipo `List<InterfaceX>`, o container de Injeção de Dependência automaticamente localiza **todos** os beans gerenciados que implementam `InterfaceX` e os entrega já reunidos em uma lista — usado aqui para que `Factory` receba, sem nenhuma configuração manual, tanto `OwnStrategy` quanto `AllStrategy`. |

### Estrutura da linguagem Java / API padrão

| Termo | Significado |
|---|---|
| `Map<K, V>` | Interface da biblioteca padrão do Java que representa uma coleção de pares chave-valor, em que cada chave está associada a, no máximo, um valor. Usada aqui como `Map<AccessScope, Strategy>`, associando cada escopo à sua implementação. |
| `Stream` (revisão + `Collectors.toMap`) | A API funcional do Java (introduzida no Java 8) para processar coleções de forma declarativa. Neste vídeo, é usada tanto para transformar uma `List<Proposal>` em `List<ProposalOutput>` (já visto no Vídeo 05, com `.map(...).toList()`) quanto, de forma nova, para transformar uma `List<Strategy>` em um `Map<AccessScope, Strategy>`, através do coletor `Collectors.toMap(chave, valor)`. |
| `Collectors.toMap(keyMapper, valueMapper)` | Método de fábrica da classe utilitária `Collectors` que constrói um `Map` a partir de uma `Stream`, recebendo duas funções: uma que extrai a **chave** de cada elemento, e outra que extrai (ou apenas devolve) o **valor** correspondente. |
| Referência de método (*method reference*, `Classe::metodo`) | Sintaxe abreviada para uma expressão lambda que apenas chama um método já existente, sem nenhuma lógica adicional. `Strategy::getScope` é equivalente à lambda `strategy -> strategy.getScope()` — já usado antes com `ProposalOutput::from`, e reforçado aqui com um exemplo novo. |
| `Function<T, R>` e `Function.identity()` | `Function<T, R>` é uma interface funcional da biblioteca padrão que representa "uma função que recebe um valor do tipo `T` e devolve um valor do tipo `R`". `Function.identity()` é um método estático dessa interface que devolve uma função "vazia", que apenas retorna o próprio valor recebido sem transformá-lo — útil quando um método (como `Collectors.toMap`) exige uma função de transformação, mas a transformação desejada é "nenhuma". |
| `List.toList()` (revisão) | Método terminal de `Stream`, adicionado no Java 16, que coleta os elementos processados de volta em uma `List` imutável — alternativa mais concisa ao antigo `.collect(Collectors.toList())`. Já mencionado no Vídeo 05, reforçado aqui em um segundo uso, dentro de `ListProposalsUseCase`. |

---

## Estado atual do projeto (checkpoint do Vídeo 06)

Este é o retrato fiel do código-fonte na etapa atual, conferido diretamente nos arquivos do `.zip` `proposal-managemnet_ate_o_video06.zip` (incluindo a pasta `build/`, usada como evidência na seção "Pontos de atenção") — e não apenas na narrativa do README. O código é reproduzido **exatamente como está no `.zip`**, incluindo os dois problemas de compilação herdados do Vídeo 05 e ainda não corrigidos.

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
        │       │   ├── ListProposalsUseCase.java              ← novo
        │       │   ├── input/
        │       │   │   └── CreateProposalInput.java          (inalterado)
        │       │   ├── list/                                  ← pacote novo deste vídeo
        │       │   │   ├── AccessScope.java                   ← novo
        │       │   │   ├── AllStrategy.java                   ← novo
        │       │   │   ├── Factory.java                       ← novo
        │       │   │   ├── OwnStrategy.java                   ← novo
        │       │   │   └── Strategy.java                      ← novo
        │       │   └── output/
        │       │       └── ProposalOutput.java                (inalterado)
        │       ├── domain/
        │       │   ├── Owner.java                             (inalterado)
        │       │   ├── OwnerId.java                           (inalterado — ainda com bug de compilação)
        │       │   ├── Proposal.java                          (inalterado)
        │       │   ├── ProposalId.java                        (inalterado)
        │       │   └── ProposalRepository.java                (inalterado)
        │       └── infrastructure/                            (ainda vazio)
        └── resources/
            └── application.properties                        (inalterado)
```

### `proposal/application/list/AccessScope.java` (novo arquivo)

```java
package dio.proposalmanagement.proposal.application.list;

public enum AccessScope {
    OWN,
    ALL
}
```

### `proposal/application/list/Strategy.java` (novo arquivo)

```java
package dio.proposalmanagement.proposal.application.list;

import dio.proposalmanagement.proposal.domain.OwnerId;
import dio.proposalmanagement.proposal.domain.Proposal;

import java.util.List;

public interface Strategy {
    List<Proposal> getProposals(OwnerId ownerId);
    AccessScope getScope();
}
```

### `proposal/application/list/OwnStrategy.java` (novo arquivo)

```java
package dio.proposalmanagement.proposal.application.list;

import dio.proposalmanagement.proposal.domain.OwnerId;
import dio.proposalmanagement.proposal.domain.Proposal;
import dio.proposalmanagement.proposal.domain.ProposalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnStrategy implements Strategy {
    private final ProposalRepository proposalRepository;

    public OwnStrategy(ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }

    @Override
    public List<Proposal> getProposals(OwnerId ownerId) {
        return proposalRepository.findAllByOwnerId(ownerId);
    }

    @Override
    public AccessScope getScope() {
        return AccessScope.OWN;
    }
}
```

### `proposal/application/list/AllStrategy.java` (novo arquivo)

```java
package dio.proposalmanagement.proposal.application.list;

import dio.proposalmanagement.proposal.domain.OwnerId;
import dio.proposalmanagement.proposal.domain.Proposal;
import dio.proposalmanagement.proposal.domain.ProposalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllStrategy implements Strategy {
    private final ProposalRepository proposalRepository;

    public AllStrategy(ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }

    @Override
    public List<Proposal> getProposals(OwnerId ownerId) {
        return proposalRepository.findAll();
    }

    @Override
    public AccessScope getScope() {
        return AccessScope.ALL;
    }
}
```

### `proposal/application/list/Factory.java` (novo arquivo)

```java
package dio.proposalmanagement.proposal.application.list;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class Factory {
    private final Map<AccessScope, Strategy> strategies;

    public Factory(List<Strategy> strategies) {
        this.strategies = strategies
                .stream()
                .collect(
                        Collectors.toMap(
                                Strategy::getScope,
                                Function.identity()
                        )
                );
    }

    public Strategy getStrategy(AccessScope scope) {
        return strategies.get(scope);
    }
}
```

### `proposal/application/ListProposalsUseCase.java` (novo arquivo)

```java
package dio.proposalmanagement.proposal.application;

import dio.proposalmanagement.proposal.application.list.AccessScope;
import dio.proposalmanagement.proposal.application.list.Factory;
import dio.proposalmanagement.proposal.application.output.ProposalOutput;
import dio.proposalmanagement.proposal.domain.OwnerId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListProposalsUseCase {
    private final Factory factory;

    public ListProposalsUseCase(Factory factory) {
        this.factory = factory;
    }

    public List<ProposalOutput> execute(AccessScope scope, OwnerId ownerId) {
        var proposals = factory.getStrategy(scope).getProposals(ownerId);

        return proposals.stream().map(ProposalOutput::from).toList();
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

### Demais arquivos do módulo `proposal` (inalterados desde o Vídeo 05)

`Proposal.java`, `ProposalId.java`, `Owner.java`, `ProposalRepository.java`, `CreateProposalInput.java` e `ProposalOutput.java` não foram tocados neste vídeo — o checkpoint completo desses arquivos permanece exatamente como documentado em `004-Tutorial_ProposalManagement_Spring_Security_Video05.md`, confirmado agora também neste `.zip`.

### Módulo `auth` (inalterado desde o Vídeo 04)

Nenhum arquivo do pacote `dio.proposalmanagement.auth` foi tocado neste vídeo — o checkpoint completo desses arquivos (`UserRole.java`, `User.java`, `UserRepository.java`, `JpaUserDetailsService.java`, `SecurityConfig.java`, `RestUsernamePasswordAuthenticationFilter.java`, `Controller.java`) permanece exatamente como documentado em `003-Tutorial_ProposalManagement_Spring_Security_Video04.md`, confirmado agora também neste `.zip`.

### `build.gradle` (inalterado desde o Vídeo 04)

Nenhuma dependência nova foi adicionada neste vídeo — as seis novas classes usam apenas Java puro e Spring Framework (anotações `@Service`/`@Component`), sem nenhuma biblioteca adicional além do que já estava presente desde o Vídeo 04.

---

## Próximos passos: o que vem a partir do Vídeo 07

O README confirma que o curso tem, ao todo, **9 vídeos**, e já traz os títulos oficiais de todos os que faltam:

- **Vídeo 07 — Criando Entidades de Persistência:** deve finalmente implementar `ProposalRepository` de verdade, criando uma entidade JPA para `Proposal` (reaproveitando os padrões já vistos em `User`: `@Entity`, `@Id`, `@GeneratedValue`) e uma classe concreta na camada `infrastructure` que implementa a interface de domínio. É provável que os dois bugs de compilação herdados dos Vídeos 05 e 06 (assinatura de `CreateProposalUseCase.execute` e `import` de `OwnerId`) precisem ser finalmente corrigidos aqui, já que uma entidade JPA de verdade tende a expor esses problemas de forma ainda mais evidente (por exemplo, ao tentar persistir um `OwnerId` que nem compila).
- **Vídeo 08 — Implementando o `ProposalController`:** deve expor os use cases (`CreateProposalUseCase`, `ListProposalsUseCase`) via HTTP, criando o primeiro controller REST dedicado a propostas — e é o candidato natural para, finalmente, resolver a conversão entre o `User` autenticado (`@AuthenticationPrincipal`) e o `Owner`/`OwnerId` do domínio de propostas, além de decidir, com base na `Role` do usuário logado, qual `AccessScope` passar para `ListProposalsUseCase.execute(...)` — o mapeamento que ficou propositalmente de fora deste vídeo.
- **Vídeo 09 — Segurança Escalável:** deve fechar o curso discutindo como fazer a configuração de segurança crescer de forma sustentável — um bom momento para retomar a discussão *stateful × stateless* e o JWT, mencionados de passagem no tutorial do Vídeo 03, e possivelmente para aprofundar o tema "segurança baseada em papéis" que deu nome ao Vídeo 05, mas que, até aqui, ainda não foi tratado de forma explícita dentro do módulo `auth`.

> **Sugestão de uso deste documento**
> Depois de assistir ao próximo vídeo, crie um novo arquivo numerado (`006-Tutorial_..._Video07.md`) seguindo este mesmo formato, e sempre confira o código real do `.zip` recebido naquela etapa — incluindo a pasta `build/`, que, até aqui, se mostrou uma fonte confiável de evidência sobre se o código realmente compila — antes de escrever qualquer explicação.
