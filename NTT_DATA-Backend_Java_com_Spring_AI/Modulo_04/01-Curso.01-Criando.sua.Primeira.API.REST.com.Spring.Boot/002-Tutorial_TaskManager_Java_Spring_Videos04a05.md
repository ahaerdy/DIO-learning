# Tutorial de Estudos — Construindo um Task Manager com Java e Spring Boot

**Da camada de aplicação à listagem de tarefas — Vídeos 04 e 05**

- Curso: NTT Data — Jornada Tech (DIO) · Trilha Java + Spring
- Instrutor: Thiago Poiani
- Documento de referência pessoal — nível iniciante em Java
- Continuação de: *"Tutorial de Estudos — Construindo um Task Manager com Java e Spring Boot — Do zero à modelagem de domínio — Vídeos 01 a 03"*

---

## Sobre este documento

Este é o segundo capítulo do tutorial de estudos deste curso. Ele dá sequência direta ao primeiro documento (Vídeos 01 a 03), que deixou o projeto com o domínio modelado — a entidade `Task`, o enum `TaskStatus`, o record `TaskId`, o contrato `TaskRepository` e sua implementação em memória — mas ainda sem nenhuma camada de aplicação com conteúdo.

É exatamente daí que este documento parte: com o domínio já pronto, os Vídeos 04 e 05 preenchem a camada `application`, introduzindo o padrão de Use Case e, com ele, uma leva de conceitos novos — DTOs de entrada e saída, injeção de dependência (manual e via Spring), testes isolados com Mockito, Stream API e o cuidado de manter as regras de atualização dentro da própria entidade de domínio.

Assim como o primeiro documento, este foi escrito a partir das anotações de aula (README atualizado até o Vídeo 05) e conferido diretamente contra o código-fonte real do projeto na etapa correspondente a cada vídeo — com destaque, ao longo do texto, para os pontos em que a narrativa da aula e o código real divergem em algum detalhe.

> **Como este documento está organizado**
> Segue exatamente o mesmo formato do documento anterior: cada parte corresponde a um vídeo do curso; dentro de cada parte, o código é apresentado em pequenos blocos, na ordem em que foi escrito na aula, seguido de uma explicação linha a linha; ao final de cada vídeo, um checkpoint mostra o estado real dos arquivos naquela etapa. Ao final deste documento, um glossário complementar reúne apenas os termos novos introduzidos nos Vídeos 04 e 05, e a seção de "Próximos passos" é atualizada para refletir o que ainda falta até o Vídeo 10.

> **Nota de concatenação:** este arquivo é a continuação direta de `001-Tutorial_TaskManager_Java_Spring_Videos01a03_3.md`. Para juntar os dois em um único documento: cole o conteúdo abaixo **logo após** a seção "Próximos passos: o que vem a partir do Vídeo 04" do documento antigo (ou simplesmente substitua aquela seção pela versão atualizada de "Próximos passos" que está no final deste arquivo) e **antes** da seção "Diagrama: como as classes se relacionam...". O glossário e o checkpoint deste arquivo são complementares aos do documento antigo — não os substituem, apenas os estendem.

---

## Parte 4 — Orquestrando o domínio (Vídeo 04)

Até o Vídeo 03, todo o código escrito vivia na camada `domain` (a entidade `Task`, o contrato `TaskRepository`) e na camada `infrastructure` (a implementação em memória). Ainda não existia nada na terceira camada, `application`, que havia sido criada como pasta vazia lá na Parte 2. O Vídeo 04 é o momento em que essa camada finalmente ganha conteúdo, através de um padrão de projeto chamado **Use Case**.

### 4.1. O padrão Use Case: uma classe por operação

Antes de escrever qualquer código, vale entender a decisão de design por trás dele. Uma alternativa comum (e mais "ingênua") seria criar uma única classe `TaskService` com vários métodos: `criar(...)`, `listar()`, `buscarPorId(...)`, `deletar(...)`, `atualizar(...)`. O curso opta por outra abordagem: **uma classe para cada operação**, cada uma delas chamada de "Use Case" (caso de uso). `CreateTaskUseCase` só sabe criar tarefas; mais adiante, `GetTasksUseCase` só sabe listar; e assim por diante.

Essa escolha é uma aplicação direta do **Princípio da Responsabilidade Única** (Single Responsibility Principle, ou SRP — o "S" do conhecido acrônimo SOLID): cada classe deve ter um, e apenas um, motivo para mudar. Uma classe `TaskService` gigante, com dez métodos, tende a crescer descontroladamente e a misturar responsabilidades que não têm relação direta entre si. Com um Use Case por classe, cada arquivo fica pequeno, fácil de testar isoladamente e fácil de entender só de ler o nome.

Por convenção, todo Use Case desse projeto vai expor um único método público chamado **`execute`**, que concentra toda a lógica daquela regra de negócio específica.

### 4.2. Criando o esqueleto do CreateTaskUseCase

O primeiro Use Case a ser criado é o de criação de tarefas, começando propositalmente vazio:

```java
package dio.taskmanager.application;

public class CreateTaskUseCase {
    void execute() {

    }
}
```

- **`package dio.taskmanager.application;`** — a classe é posicionada dentro do pacote `application`, a camada responsável por orquestrar o domínio (diferente de conter as regras de negócio em si, que continuam em `domain`).
- **`public class CreateTaskUseCase`** — a classe em si é `public`, podendo ser usada por qualquer outra camada do projeto (por exemplo, futuramente, por um Controller HTTP).
- **`void execute()`** — o método `execute`, aqui ainda sem parâmetros e sem corpo, é criado sem nenhum modificador de acesso explícito (nem `public`, nem `private`). Isso é chamado de visibilidade **"padrão" (package-private)**: o método só pode ser chamado por classes do mesmo pacote. Esse detalhe muda mais adiante, quando o método precisa ser chamado de fora do pacote.

### 4.3. O DTO de entrada: CreateTaskInput

Para criar uma `Task`, é preciso receber um título e uma descrição. Uma alternativa simples seria fazer `execute` receber esses dois valores soltos, como `execute(String title, String description)`. O curso rejeita essa ideia e introduz, em vez disso, o conceito de **DTO**.

> **O que é um DTO (Data Transfer Object)?**
> Um DTO é uma classe cujo único propósito é carregar dados de um ponto a outro do sistema — sem regra de negócio, sem comportamento, só dados. A vantagem de usar um DTO em vez de passar valores soltos (ou a própria entidade de domínio) é o **desacoplamento**: se amanhã a entrada do sistema deixar de vir de um JSON de uma requisição REST e passar a vir de uma mensagem de fila, por exemplo, o Use Case não precisa mudar nada — só muda quem monta o DTO antes de chamar `execute`.

Para hospedar esse DTO, é criado um novo subpacote, `application.input`, e dentro dele a classe `CreateTaskInput`:

```java
package dio.taskmanager.application.input;

import java.util.Optional;

public record CreateTaskInput(String title, Optional<String> description) {
}
```

- **`public record CreateTaskInput(String title, Optional<String> description)`** — assim como `TaskId`, o DTO é escrito como **`record`** (conceito já visto na Parte 2 deste tutorial). A escolha não é acidental: um `record` já nasce imutável — depois de criado um `CreateTaskInput`, não existe nenhum método para alterar seu `title` ou `description`. Isso é exatamente o que se espera de um DTO: ele representa um "retrato" fixo dos dados de entrada, sem risco de ser modificado no meio do caminho entre quem o criou e quem o consome.
- Como todo `record`, o Java gera automaticamente os métodos de acesso `title()` e `description()` (não `getTitle()`/`getDescription()` — records usam o próprio nome do campo como nome do método de leitura, sem o prefixo `get`).

### 4.4. Recebendo o CreateTaskInput em execute()

```java
package dio.taskmanager.application;

import dio.taskmanager.application.input.CreateTaskInput;
import dio.taskmanager.domain.Task;

public class CreateTaskUseCase {
    void execute(CreateTaskInput input) {
        var task = new Task(input.title(), input.description());
    }
}
```

- **`void execute(CreateTaskInput input)`** — o método passa a receber um parâmetro do tipo `CreateTaskInput`. É a primeira vez que a camada `application` conversa com a camada `domain`: o import de `dio.taskmanager.domain.Task` confirma essa dependência (a `application` depende do `domain`, nunca o contrário — outra regra importante de arquitetura em camadas).
- **`var task = new Task(input.title(), input.description());`** — aqui `input.title()` e `input.description()` são os métodos de acesso gerados automaticamente pelo `record` `CreateTaskInput`, chamados para "abrir o envelope" e pegar os valores de dentro dele. Esses valores são então repassados diretamente para o construtor de `Task`, já conhecido da Parte 2.
- **`var`** — já usado antes na Parte 2/3 deste tutorial: o compilador infere o tipo (aqui, `Task`) a partir do que está do lado direito do `=`, sem precisar escrevê-lo explicitamente.

### 4.5. O DTO de saída: TaskOutput

O raciocínio se repete, agora para o outro lado: assim como existe um DTO específico para a entrada, é criado outro para a saída, evitando que a entidade de domínio `Task` "vaze" para fora da camada `application`. Isso protege o domínio (ele pode evoluir livremente sem quebrar quem consome o Use Case) e permite reaproveitar o mesmo `TaskOutput` em outros casos de uso futuros (listagem, busca por id etc. — o que de fato acontece no Vídeo 05).

Primeiro, um esqueleto vazio, dentro do novo subpacote `application.output`:

```java
package dio.taskmanager.application.output;

public record TaskOutput() {
}
```

E, na sequência, a versão completa:

```java
package dio.taskmanager.application.output;

import dio.taskmanager.domain.Task;
import java.util.Optional;

public record TaskOutput(String id, String title, Optional<String> description, String status) {
    public static TaskOutput from(Task task) {
        return new TaskOutput(task.getId().id().toString(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus().name());
    }
}
```

- **Campos do record** — repare que `id` e `status` são `String` aqui, embora em `Task` eles sejam, respectivamente, um `TaskId` (record) e um `TaskStatus` (enum). Essa é uma decisão proposital: o DTO de saída usa apenas **tipos simples** (`String`), que qualquer formato de serialização (como JSON, que aparecerá nos próximos vídeos) sabe representar sem esforço extra. Tipos ricos de domínio como `TaskId` e `TaskStatus` fazem sentido dentro do domínio, mas não precisam (nem devem) ser expostos como estão para fora dele.
- **`public static TaskOutput from(Task task)`** — este é um exemplo do padrão de projeto **Static Factory Method** ("método de fábrica estático"): em vez de um construtor comum, é oferecido um método `static` com um nome descritivo (`from`, ou seja, "a partir de") cuja única responsabilidade é converter um objeto de um tipo (`Task`) em outro (`TaskOutput`). Centralizar essa conversão em um único método evita que essa lógica de mapeamento se espalhe (e se duplique) por vários Use Cases diferentes.
- **`task.getId().id().toString()`** — uma cadeia de três chamadas encadeadas, que vale destrinchar:
  1. `task.getId()` — chama o getter gerado pelo Lombok (`@Getter`, já visto na Parte 3) e devolve o `TaskId` (o record) da tarefa.
  2. `.id()` — chama o acessor automático do próprio record `TaskId`, que devolve o `UUID` guardado dentro dele.
  3. `.toString()` — todo `UUID` sabe se converter para sua representação textual padrão (ex.: `"a1b2c3d4-..."`), através do método `toString()` herdado de `Object` e sobrescrito pela classe `UUID`.
- **`task.getStatus().name()`** — `getStatus()` devolve o valor do enum `TaskStatus` (por exemplo, `TaskStatus.PENDING`). O método `.name()` é um método especial que **todo enum em Java ganha automaticamente**, sem precisar de nenhuma anotação: ele devolve o nome exato da constante, como texto — `"PENDING"`, `"IN_PROGRESS"` ou `"COMPLETED"`. É diferente de chamar `.toString()` no enum: por padrão os dois devolvem o mesmo texto, mas `.toString()` pode ser sobrescrito por quem programou o enum (para exibir algo mais amigável, por exemplo), enquanto `.name()` sempre devolve o nome literal da constante, exatamente como foi declarado no código. Por isso `.name()` é a escolha mais segura quando se quer o valor "cru" do enum.

### 4.6. execute() retornando um TaskOutput

```java
public TaskOutput execute(CreateTaskInput input) {
    var task = new Task(input.title(), input.description());
    // repository
    return TaskOutput.from(task);
}
```

- O tipo de retorno do método muda de `void` para `TaskOutput`.
- **`// repository`** — um comentário deixado propositalmente como lembrete de um passo que ainda falta (persistir a tarefa) — uma técnica comum durante o desenvolvimento incremental, às vezes chamada de comentário "TODO" (mesmo sem usar literalmente a palavra `TODO`).
- **`return TaskOutput.from(task);`** — o método estático de fábrica, visto na seção anterior, é chamado para converter a `Task` recém-criada na resposta do Use Case.

### 4.7. Injeção de dependência manual: o TaskRepository chega via construtor

Para persistir a `Task`, o Use Case precisa de um `TaskRepository`. Em vez de instanciar um `InMemoryTaskRepository` diretamente dentro da classe (o que a acoplaria a uma implementação concreta específica), ele passa a **receber** essa dependência de fora, através do construtor:

```java
public class CreateTaskUseCase {

    private final TaskRepository repository;

    public CreateTaskUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    public TaskOutput execute(CreateTaskInput input) {
        var task = new Task(input.title(), input.description());
        // repository.save
        return TaskOutput.from(task);
    }
}
```

> **O que é Injeção de Dependência (Dependency Injection)?**
> É uma técnica em que uma classe **declara do que precisa** (aqui, "eu preciso de algo que implemente `TaskRepository`"), sem se preocupar em criar esse "algo" com suas próprias mãos, nem em saber qual implementação concreta será usada. Quem decide isso é quem cria o objeto `CreateTaskUseCase` — nesta etapa do curso, ainda "na mão" (por isso o nome "injeção manual"); a partir da próxima seção, esse trabalho passa a ser automatizado pelo Spring.
>
> A vantagem prática: o `CreateTaskUseCase` depende apenas da **interface** `TaskRepository`, nunca da classe `InMemoryTaskRepository`. Se um dia o projeto trocar o armazenamento em memória por um banco de dados de verdade, basta criar uma nova classe que implemente `TaskRepository` — nenhuma linha do Use Case muda.

- **`private final TaskRepository repository;`** — o campo é `final`, ou seja, só pode ser atribuído uma vez (dentro do construtor) e nunca mais reatribuído depois disso — a dependência do Use Case é fixa durante toda sua vida útil.
- **`public CreateTaskUseCase(TaskRepository repository) { this.repository = repository; }`** — o construtor recebe o repositório como parâmetro e o guarda no campo da classe, usando `this` para diferenciar o parâmetro do campo (mesmo padrão já visto no construtor de `Task`, na Parte 2).

### 4.8. Chamando repository.save()

```java
public TaskOutput execute(CreateTaskInput input) {
    var task = new Task(input.title(), input.description());
    var saved = repository.save(task);
    return TaskOutput.from(saved);
}
```

O comentário `// repository.save` dá lugar à chamada real: `repository.save(task)` persiste a `Task` no repositório injetado e devolve a instância salva, que é guardada na variável `saved`. É essa instância que, segundo a explicação da aula, deveria ser convertida para `TaskOutput`.

> **Divergência encontrada entre a narrativa da aula e o código-fonte final**
> Ao conferir o arquivo `CreateTaskUseCase.java` real, dentro do `.zip` entregue até o Vídeo 05, o método `execute` está assim:
> ```java
> public TaskOutput execute(CreateTaskInput input) {
>     var task = new Task(input.title(), input.description());
>     var saved = repository.save(task);
>     return TaskOutput.from(task); // usa "task", não "saved"
> }
> ```
> A variável `saved` é criada e usada apenas para disparar a chamada `repository.save(task)`, mas o `return` converte a variável `task` (o objeto original, antes de ser salvo) — não `saved`, como a explicação em áudio da aula sugere.
>
> Na prática, **o resultado final é idêntico nesta etapa do curso**: como `InMemoryTaskRepository.save()` devolve exatamente o mesmo objeto que recebeu (`return task;`, visto na Parte 3), `task` e `saved` apontam para a mesma instância na memória. Só que essa equivalência é uma característica específica **desta implementação em memória** — não é garantida em geral. Se um dia o repositório passar a ser, por exemplo, um banco de dados relacional (JPA/Hibernate), o método `save` costuma devolver uma **nova** instância (com campos preenchidos pelo banco, como um id gerado automaticamente ou uma data de criação). Nesse cenário, retornar `task` em vez de `saved` produziria um `TaskOutput` desatualizado. Boa prática: sempre converter para o DTO de saída a partir do valor **devolvido** por `save`, não do valor **enviado** para ele — vale ficar de olho se esse ponto for corrigido em vídeos futuros.

### 4.9. Testando o Use Case com injeção manual

Com o Use Case implementado, o próximo passo é validar seu comportamento com um teste automatizado, gerado pelo próprio IntelliJ (menu **Generate...**):

```java
package dio.taskmanager.application;

import dio.taskmanager.application.input.CreateTaskInput;
import dio.taskmanager.application.output.TaskOutput;
import dio.taskmanager.infrastructure.repository.InMemoryTaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CreateTaskUseCaseTest {
    CreateTaskUseCase useCase;

    @BeforeEach
    void setup() {
        this.useCase = new CreateTaskUseCase(new InMemoryTaskRepository());
    }

    @Test
    void should_create_task_successfully() {
        // given
        String descricaoEsperada = "Finalizar o módulo de Records";
        var input = new CreateTaskInput("Estudar Java", Optional.of(descricaoEsperada));

        // when
        TaskOutput output = useCase.execute(input);

        // then
        assertNotNull(output);
        assertNotNull(output.id());
        assertEquals("Estudar Java", output.title());
        assertEquals(Optional.of(descricaoEsperada), output.description());
    }
}
```

- **`@BeforeEach void setup()`** — assim como já visto na Parte 3 (para o teste do repositório), este método roda antes de cada teste, montando um `CreateTaskUseCase` novo a cada vez, injetado **manualmente** com um `new InMemoryTaskRepository()` recém-criado. Isso garante isolamento: nenhum teste "vê" tarefas salvas por outro teste.
- **Comentários `// given` / `// when` / `// then`** — não são um recurso da linguagem, mas uma convenção de organização de testes conhecida como **Given-When-Then** (ou "Arrange-Act-Assert"): primeiro prepara-se o cenário (`given`), depois executa-se a ação sob teste (`when`), e por fim verifica-se o resultado (`then`). Deixar esses comentários explícitos ajuda a ler o teste como uma pequena "história".
- O corpo do teste em si segue o mesmo padrão já visto na Parte 3: criar os dados de entrada, chamar o método sob teste, e usar `assertNotNull`/`assertEquals` para conferir o resultado.

### 4.10. De injeção manual para o container do Spring: @Service e @Repository

Até aqui, quem monta o `CreateTaskUseCase` (e decide qual `TaskRepository` injetar nele) é o próprio código — seja o teste (`new CreateTaskUseCase(new InMemoryTaskRepository())`), seja, no futuro, alguma outra classe que precise chamá-lo. A partir daqui, essa responsabilidade passa a ser delegada ao **Spring**.

```java
@Service
public class CreateTaskUseCase {
    private final TaskRepository repository;
    // ...
}
```

> **O que é o container de IoC do Spring, e o que é um "bean"?**
> IoC significa **Inversion of Control** (Inversão de Controle): em vez do próprio código decidir quando e como criar cada objeto, essa responsabilidade é "invertida" e entregue a um componente externo — o **container do Spring**. Esse container mantém, durante toda a execução da aplicação, um "catálogo" de objetos que ele mesmo criou e gerencia, chamados de **beans**. Anotações como `@Service`, `@Repository` e `@Component` são formas de avisar ao Spring: "esta classe deve virar um bean — crie uma instância dela e a deixe disponível para quem precisar".

- **`@Service`** — anotação usada aqui para marcar `CreateTaskUseCase` como um bean gerenciado pelo Spring, sinalizando semanticamente que essa classe contém lógica de aplicação/negócio (em oposição a, por exemplo, `@Repository`, usada para classes de acesso a dados). Funcionalmente as duas fazem a mesma coisa (registram a classe como bean); a diferença está no significado que passam para quem lê o código.

Ao anotar a classe com `@Service` e rodar o projeto, o Spring tenta montar o bean sozinho — e falha, com o erro **"Could not autowire. No beans of 'TaskRepository' type found"**. A explicação é simples: `TaskRepository` é apenas uma **interface**. O Spring sabe que `CreateTaskUseCase` precisa de "alguma coisa que implemente `TaskRepository`", mas, até este ponto, nenhuma classe concreta foi registrada como bean para esse contrato.

A solução é anotar a implementação real, `InMemoryTaskRepository`, com `@Repository`:

```java
package dio.taskmanager.infrastructure.repository;

@Repository
public class InMemoryTaskRepository implements TaskRepository {
    // ...
}
```

- **`@Repository`** — assim como `@Service`, registra a classe como um bean gerenciado pelo Spring; a convenção, no entanto, é reservá-la para implementações da camada de acesso a dados (o que `InMemoryTaskRepository` é, mesmo sendo "só" um `HashMap` em memória por enquanto).

> **Um detalhe que fecha um ciclo aberto no checkpoint do Vídeo 03**
> No checkpoint anterior deste tutorial, foi observado que `InMemoryTaskRepository` estava anotada com `@Setter` (do Lombok), aparentemente "sem uso concreto ainda", com a expectativa de que ganharia importância quando a atualização de tarefas fosse implementada. Conferindo o código real desta etapa, o que de fato aconteceu foi diferente: **o `@Setter` foi removido** e, no lugar dele, entrou o `@Repository` do Spring. Ou seja, aquele `@Setter` acabou não sendo aproveitado como o checkpoint anterior previa — quando a atualização de tarefas for implementada (Vídeo 05, adiante), ela usa um caminho diferente: um método de domínio dentro da própria classe `Task`, e não um setter genérico no repositório.

Com o `TaskRepository` finalmente tendo uma implementação registrada como bean, o construtor manual de `CreateTaskUseCase` pode ser substituído por injeção automática via `@Autowired`:

```java
@Service
public class CreateTaskUseCase {
    @Autowired
    private final TaskRepository repository;

    // public CreateTaskUseCase(TaskRepository repository) {
    //     this.repository = repository;
    // }

    public TaskOutput execute(CreateTaskInput input) {
        var task = new Task(input.title(), input.description());
        var saved = repository.save(task);
        return TaskOutput.from(saved);
    }
}
```

- **`@Autowired`** — colocada diretamente sobre o campo, instrui o Spring a **injetar automaticamente** um bean compatível com o tipo declarado (`TaskRepository`) assim que a classe for criada, sem precisar de um construtor escrito à mão para isso. É essa anotação que resolve, de forma automática, o mesmo problema que a Seção 4.7 resolveu manualmente.
- O construtor antigo é apenas comentado (não apagado) — uma prática comum durante a exploração de alternativas em uma aula ao vivo, para poder comparar as duas formas.

> **Nota de fidelidade ao código-fonte:** esta variação com `@Autowired` em campo é uma etapa intermediária, explorada durante a aula para demonstrar a diferença entre injeção manual e injeção automática. Conferindo o arquivo `CreateTaskUseCase.java` na versão final entregue (a mesma mostrada na Seção 4.8), o projeto **volta a usar injeção via construtor** — sem `@Autowired` explícito no campo. Isso não é um erro: a partir do Spring 4.3, quando uma classe tem **um único construtor**, o `@Autowired` nele é opcional — o Spring o usa automaticamente mesmo sem a anotação. Ou seja, o construtor da Seção 4.7, combinado com `@Service`, já é suficiente para a injeção automática funcionar; a versão com `@Autowired` em campo, mostrada acima, é apenas um passo pedagógico do meio da aula, não o estado final do arquivo.

### 4.11. Testando com o contexto real do Spring: @SpringBootTest e @Autowired

Para comprovar que a injeção automática realmente funciona, o teste é temporariamente reescrito para também usar o Spring:

- A instanciação manual (`new CreateTaskUseCase(new InMemoryTaskRepository())`) é substituída por um campo `@Autowired CreateTaskUseCase useCase`.
- Como agora o teste depende do container do Spring para resolver essa injeção, a classe de teste precisa ser anotada com **`@SpringBootTest`**, que sobe todo o `ApplicationContext` da aplicação (o mesmo "catálogo de beans" mencionado na Seção 4.10) antes de rodar o teste.

Rodando o teste em modo **Debug**, com um *breakpoint* (um ponto de parada manual) colocado dentro do método `save()` de `InMemoryTaskRepository`, a execução realmente para naquele ponto — confirmando, na prática, que a implementação real (`InMemoryTaskRepository`) foi injetada automaticamente pelo Spring e efetivamente chamada pelo Use Case. No painel de variáveis do depurador, é possível inspecionar o `task` recém-criado e o `storage` (ainda vazio, `size = 0`) exatamente no instante em que a execução foi pausada.

### 4.12. Isolando o teste com Mockito: @Mock e @InjectMocks

Depender do contexto real do Spring (e de uma implementação real do repositório) em um teste tem um custo: o teste fica mais lento (o Spring precisa montar todo o contexto da aplicação) e, em cenários mais realistas — como um repositório que conversa com um banco de dados de verdade — pode até exigir uma infraestrutura externa disponível só para rodar o teste. Para isolar o Use Case dessas dependências, a aula introduz o **Mockito**.

> **O que é o Mockito, e o que é um mock?**
> Mockito é uma biblioteca de *mocking* — muito usada junto com JUnit — que permite criar objetos "de mentira" (chamados de **mocks**) para qualquer interface ou classe, sem nenhuma implementação real por trás. Um mock não faz nada sozinho: você mesmo decide, dentro do teste, o que cada método dele deve devolver quando for chamado, e depois consegue verificar quais métodos foram efetivamente chamados e quantas vezes. Isso permite testar uma classe (aqui, `CreateTaskUseCase`) **isolada** de suas dependências reais.
>
> O Mockito já vem incluído de forma transitiva na dependência `spring-boot-starter-test` (vista na Parte 3 deste tutorial), então não foi preciso adicionar nenhuma nova linha em `build.gradle` para trazer essa biblioteca — ela já estava disponível desde o Vídeo 03.

```java
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CreateTaskUseCaseTest {
    @Mock
    TaskRepository repository;

    @Autowired
    CreateTaskUseCase useCase;
    // ...
}
```

- **`@ExtendWith(MockitoExtension.class)`** — uma "extensão" do JUnit 5 que ativa o suporte do Mockito dentro da classe de teste — em particular, é ela que faz o Mockito processar as anotações `@Mock` e `@InjectMocks` automaticamente, sem precisar chamar nenhum método de inicialização manual.
- **`@Mock TaskRepository repository;`** — cria uma "casca" da interface `TaskRepository`, sem nenhuma implementação real atrás dela. Por padrão, todo método chamado nesse mock devolve um valor "vazio" (como `null`, `0` ou uma lista vazia) até que seu comportamento seja explicitamente configurado no teste.

Em seguida, o `@Autowired` no campo `useCase` é substituído por **`@InjectMocks`**:

```java
@Mock
TaskRepository repository;

@InjectMocks
CreateTaskUseCase useCase;
```

- **`@InjectMocks`** — diferente de `@Autowired` (que pede ao *Spring* para resolver a dependência), esta é uma anotação do próprio *Mockito*: ela instancia `CreateTaskUseCase` diretamente (sem subir nenhum contexto do Spring) e injeta nele, automaticamente, qualquer mock declarado na mesma classe de teste — aqui, o `repository` mockado.

O comportamento do mock precisa então ser configurado explicitamente:

```java
when(repository.save(any(Task.class)))
        .thenAnswer(invocation -> invocation.getArgument(0));
```

- **`when(...).thenAnswer(...)`** — uma das formas de configurar um mock no Mockito: "quando o método `X` for chamado com tais argumentos, responda assim". Aqui, sempre que `repository.save(...)` for chamado com **qualquer** `Task` (`any(Task.class)`), a resposta simulada é devolver o próprio argumento recebido — imitando o comportamento real de `InMemoryTaskRepository.save()`, sem de fato executar nenhuma linha dela.
- **`any(Task.class)`** — um *matcher* do Mockito que aceita qualquer instância de `Task`, em vez de exigir um objeto específico. Útil porque, dentro do Use Case, a `Task` é criada internamente (com um `TaskId` novo, gerado aleatoriamente) — o teste não teria como prever exatamente qual objeto seria passado para `save`.
- **`invocation.getArgument(0)`** — dentro do `thenAnswer`, `invocation` representa a chamada real que está sendo simulada; `getArgument(0)` pega o primeiro argumento passado a ela (a `Task` recebida por `save`) e o devolve como resultado — daí o mock "ecoar" de volta a mesma tarefa recebida.

Por fim, a verificação:

```java
verify(repository, times(1)).save(any(Task.class));
```

- **`verify(repository, times(1)).save(...)`** — depois de rodar o Use Case, `verify` confirma que o método `save` do mock foi chamado **exatamente uma vez**. Diferente de `assertEquals`, que compara um *valor*, `verify` compara um *comportamento*: "esse método deveria ter sido chamado, e foi?".

Repetindo a depuração com o mesmo *breakpoint* de antes (dentro de `InMemoryTaskRepository.save()`), dessa vez ele **não** é acionado — prova de que o Use Case está de fato usando o repositório mockado, e não a implementação real em memória.

### 4.13. Um ajuste no build.gradle, exigido pelo Mockito

Comparando o `build.gradle` desta etapa com o da etapa anterior (Vídeo 03), aparece uma mudança que **não é narrada explicitamente no README**, mas está presente no arquivo real do projeto:

```groovy
test {
    useJUnitPlatform()
    jvmArgs(
            "-XX:+EnableDynamicAgentLoading",
            "-Xshare:off" // Desativa o compartilhamento de classes, eliminando a warning
    )
    // Configura o log para ignorar warnings da JVM
    testLogging { events "passed", "failed"
    }
}
```

- **`jvmArgs(...)`** — dentro do bloco `test` do Gradle, esta linha passa argumentos extras para a máquina virtual Java (JVM) usada para *rodar* os testes.
- **`-XX:+EnableDynamicAgentLoading`** — o Mockito, para conseguir criar mocks, usa uma técnica de manipulação de bytecode em tempo de execução chamada *Java agent*. A partir do Java 21, a JVM passou a emitir um aviso ("*a Java agent has been loaded dynamically*") sempre que um agente é carregado dessa forma, sem ter sido declarado antecipadamente na linha de comando. Esta flag informa à JVM, de forma explícita, que esse carregamento dinâmico é esperado e autorizado, eliminando o aviso.
- **`-Xshare:off`** — desativa um recurso de otimização da JVM chamado *class data sharing* (compartilhamento de dados de classes entre execuções), que pode gerar avisos adicionais quando combinado com o carregamento dinâmico de agentes como o do Mockito.
- **`testLogging { events "passed", "failed" }`** — configura o Gradle para exibir no console, durante a execução dos testes, quais testes passaram e quais falharam (por padrão, o Gradle é mais silencioso sobre isso).

Este pequeno ajuste de configuração é o tipo de detalhe que só aparece quando algo realmente "dá erro" durante a gravação (aqui, o aviso da JVM sobre o Mockito) — por isso não teria como prever sua existência apenas lendo o README; só a comparação direta com o arquivo `build.gradle` real revelou essa mudança.

---

### ✅ Checkpoint — Estado do projeto ao final do Vídeo 04

Este é o retrato fiel do código-fonte nesta etapa, conferido diretamente nos arquivos reais do projeto (a partir do `.zip` intermediário do Vídeo 04, contido dentro do material enviado).

**Estrutura de pastas (arquivos novos ou alterados em relação ao checkpoint do Vídeo 03, em negrito)**

```
taskmanager/
├── build.gradle                                    (alterado — jvmArgs/testLogging)
└── src/
    ├── main/java/dio/taskmanager/
    │   ├── TaskmanagerApplication.java              (sem alterações)
    │   ├── application/
    │   │   ├── CreateTaskUseCase.java                ← novo
    │   │   ├── input/
    │   │   │   └── CreateTaskInput.java               ← novo
    │   │   └── output/
    │   │       └── TaskOutput.java                    ← novo
    │   ├── domain/
    │   │   ├── Task.java                             (sem alterações nesta etapa)
    │   │   ├── TaskId.java                            (sem alterações)
    │   │   ├── TaskRepository.java                    (sem alterações)
    │   │   └── TaskStatus.java                        (sem alterações)
    │   └── infrastructure/repository/
    │       └── InMemoryTaskRepository.java            (alterado — @Setter → @Repository)
    └── test/java/dio/taskmanager/
        ├── application/
        │   └── CreateTaskUseCaseTest.java              ← novo
        ├── domain/
        │   └── TaskRepositoryTest.java                (sem alterações)
        └── infrastructure/repository/
            └── InMemoryTaskRepositoryTest.java         (sem alterações)
```

**CreateTaskUseCase.java (versão final desta etapa)**

```java
package dio.taskmanager.application;
import dio.taskmanager.application.input.CreateTaskInput;
import dio.taskmanager.application.output.TaskOutput;
import dio.taskmanager.domain.Task;
import dio.taskmanager.domain.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateTaskUseCase {

    private final TaskRepository repository;

    public CreateTaskUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    public TaskOutput execute(CreateTaskInput input) {
        var task = new Task(input.title(), input.description());
        var saved = repository.save(task);
        return TaskOutput.from(task);
    }
}
```

> Como já explicado na Seção 4.8, repare que o `return` usa `task`, e não `saved` — uma pequena divergência em relação ao que a explicação em áudio do vídeo sugere, mas que não muda o resultado nesta implementação em memória.

**CreateTaskInput.java e TaskOutput.java** — exatamente como mostrados nas Seções 4.3 e 4.5, sem alterações adicionais.

**InMemoryTaskRepository.java** — idêntico ao checkpoint do Vídeo 03, exceto pela troca de `@Setter` (Lombok) por `@Repository` (Spring), conforme a Seção 4.10.

**CreateTaskUseCaseTest.java (versão final desta etapa)**

```java
package dio.taskmanager.application;

import dio.taskmanager.application.input.CreateTaskInput;
import dio.taskmanager.application.output.TaskOutput;
import dio.taskmanager.domain.Task;
import dio.taskmanager.domain.TaskRepository;
import dio.taskmanager.infrastructure.repository.InMemoryTaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Incubating;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Apenas isso é suficiente para unitários
class CreateTaskUseCaseTest {
    @Mock
    TaskRepository repository;

    @InjectMocks
    CreateTaskUseCase useCase;

    @Test
    void should_create_task_successfully() {
        // given
        String descricaoEsperada = "Finalizar o módulo de Records";
        var input = new CreateTaskInput("Estudar Java", Optional.of(descricaoEsperada));

        when(repository.save(any(Task.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // when
        TaskOutput output = useCase.execute(input);

        // then
        assertNotNull(output);
        assertNotNull(output.id());
        assertEquals("Estudar Java", output.title());
        assertEquals(Optional.of(descricaoEsperada), output.description());

        verify(repository, times(1)).save(any(Task.class));
    }
}
```

> **Detalhe do código real:** o comentário `// Apenas isso é suficiente para unitários`, deixado ao lado de `@ExtendWith(MockitoExtension.class)`, confirma que a versão final do teste **não** usa mais `@SpringBootTest` — a etapa da Seção 4.11 (testando com o contexto real do Spring) foi um passo intermediário e exploratório da aula, substituído pela versão isolada com Mockito como estado definitivo desta etapa. Note também um import não utilizado, `org.mockito.Incubating`, deixado no arquivo — resquício comum de auto-complete do IDE, sem efeito no funcionamento do teste.

---

## Parte 5 — Listagem de tarefas (Vídeo 05)

Com o primeiro Use Case (`CreateTaskUseCase`) servindo de modelo/referência, o Vídeo 05 repete a mesma receita para os quatro casos de uso restantes da camada de aplicação: listar todas as tarefas, buscar uma tarefa específica, excluir uma tarefa e atualizar uma tarefa. Cada um deles segue exatamente a mesma forma: `@Service`, repositório injetado via construtor, e um único método `execute`.

### 5.1. GetTasksUseCase: listando todas as tarefas

```java
package dio.taskmanager.application;

import dio.taskmanager.application.output.TaskOutput;
import dio.taskmanager.domain.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetTasksUseCase {

    private final TaskRepository repository;

    public GetTasksUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    public List<TaskOutput> execute() {
        return repository.findAll().stream().map(TaskOutput::from).toList();
    }

}
```

A estrutura da classe (campo `final`, construtor, `@Service`) é idêntica à de `CreateTaskUseCase`, então o destaque desta seção é a única linha nova de lógica:

```java
return repository.findAll().stream().map(TaskOutput::from).toList();
```

Essa linha usa a **Stream API** do Java, um conjunto de ferramentas para processar coleções (como `List`) de forma declarativa — descrevendo *o que* se quer fazer com os dados, em vez de escrever manualmente um `for` que percorre a lista item por item. Ela é lida da esquerda para a direita, como uma linha de montagem:

- **`repository.findAll()`** — devolve uma `List<Task>` (já visto na Parte 3), com todas as tarefas salvas.
- **`.stream()`** — converte essa lista em uma `Stream<Task>`, uma espécie de "esteira" pela qual os elementos vão passar, um a um, pelas próximas operações.
- **`.map(TaskOutput::from)`** — para cada `Task` que passa pela esteira, aplica uma transformação, produzindo um novo elemento. Aqui a transformação é o método estático `TaskOutput.from(Task)`, já visto na Seção 4.5. O resultado é uma nova esteira, agora de `TaskOutput` em vez de `Task`.
- **`TaskOutput::from`** — esta é a sintaxe de **method reference** ("referência a método"), representada pelo símbolo `::`. É uma forma resumida de escrever uma pequena função sem precisar de todo o "verbalismo" de uma lambda: em vez de escrever `.map(task -> TaskOutput.from(task))` (uma **lambda**, ou seja, uma função anônima escrita ali mesmo, que recebe um `task` e devolve o resultado de `TaskOutput.from(task)`), `TaskOutput::from` diz diretamente "para cada elemento, chame este método existente". As duas formas produzem exatamente o mesmo resultado; a referência a método é preferida quando a lambda faria *apenas* chamar um método já pronto, sem lógica extra.
- **`.toList()`** — encerra a esteira, recolhendo todos os elementos transformados de volta em uma `List<TaskOutput>` comum. Sem essa chamada final, o resultado continuaria sendo uma `Stream` (uma estrutura "de passagem", não pensada para ser guardada ou percorrida diretamente como uma lista).

> **Resumo em uma frase:** "pegue todas as tarefas, transforme cada uma em `TaskOutput`, e devolva como lista" — é exatamente isso que a linha faz, só que escrita em uma única expressão encadeada em vez de um laço `for` com uma lista auxiliar sendo preenchida manualmente.

### 5.2. GetTaskByIdUseCase: buscando uma tarefa específica

```java
package dio.taskmanager.application;

import dio.taskmanager.application.output.TaskOutput;
import dio.taskmanager.domain.TaskId;
import dio.taskmanager.domain.TaskNotFoundException;
import dio.taskmanager.domain.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class GetTaskByIdUseCase {
    private final TaskRepository repository;

    public GetTaskByIdUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    public TaskOutput execute(TaskId id) {
        return repository.findById(id)
                .map(TaskOutput::from)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

}
```

- **`execute(TaskId id)`** — repare que o parâmetro é do tipo `TaskId` (a classe de domínio, já vista na Parte 2), e não uma `String` solta. Isso mantém a validação e a semântica de identificador único centralizadas em um único lugar (o próprio `TaskId`), em vez de espalhar conversões de `String` para `TaskId` por vários pontos do código.
- **`repository.findById(id)`** — devolve um `Optional<Task>` (já visto na Parte 3): a tarefa pode existir ou não.
- **`.map(TaskOutput::from)`** — o mesmo `map` das streams pode ser chamado também sobre um `Optional` (não apenas sobre uma `Stream`): se o `Optional` contiver um valor, `map` aplica a transformação (aqui, converter para `TaskOutput`) e devolve um novo `Optional`, agora de `TaskOutput`; se o `Optional` estiver vazio, `map` simplesmente não faz nada e devolve outro `Optional` vazio.
- **`.orElseThrow(() -> new TaskNotFoundException(id))`** — o método `orElseThrow` de `Optional` funciona assim: se houver um valor presente, ele é devolvido normalmente; se o `Optional` estiver vazio, em vez de devolver algo, uma exceção é **lançada**. O que vai dentro dos parênteses, `() -> new TaskNotFoundException(id)`, é uma **lambda** (função anônima) sem nenhum parâmetro (por isso os parênteses vazios `()`) que, quando chamada, cria e devolve uma nova `TaskNotFoundException`. `orElseThrow` só executa essa lambda **se realmente precisar** — ou seja, a exceção só é de fato criada quando a tarefa não é encontrada, e não a cada chamada do método.

### 5.3. TaskNotFoundException: uma exceção de domínio

```java
package dio.taskmanager.domain;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(TaskId taskId) {
        super("Task with id " + taskId + " not found");
    }
}
```

> **O que é uma exceção customizada, e por que estender RuntimeException?**
> Em Java, exceções são objetos que representam a ocorrência de um erro ou de uma condição excepcional durante a execução do programa. É possível criar exceções próprias (customizadas) estendendo (`extends`) uma das classes de exceção já existentes na linguagem. `RuntimeException` representa uma **exceção não verificada** (*unchecked exception*): o compilador **não obriga** quem chama um método que pode lançá-la a tratá-la explicitamente (com `try/catch`) nem a declará-la na assinatura do método (com `throws`). Isso contrasta com as chamadas exceções **verificadas** (*checked exceptions*, como `IOException`), que o compilador exige tratar. A escolha por `RuntimeException` aqui é comum para representar violações de regras de negócio (como "essa tarefa não existe"): o erro é real, mas obrigar cada chamador a lidar com um `try/catch` explícito tornaria o código mais verboso sem trazer benefício proporcional.

- **`public class TaskNotFoundException extends RuntimeException`** — a palavra-chave `extends` (herança, já vista no glossário da Parte 3) diz que `TaskNotFoundException` é um tipo especializado de `RuntimeException`, herdando todo o seu comportamento (por exemplo, poder ser capturada com `catch (RuntimeException e)`).
- **`public TaskNotFoundException(TaskId taskId)`** — o construtor recebe o `TaskId` que não foi encontrado, para poder montar uma mensagem de erro específica sobre ele.
- **`super("Task with id " + taskId + " not found");`** — a palavra-chave `super` chama o construtor da superclasse (`RuntimeException`), passando a mensagem de erro. Toda `RuntimeException` (e toda `Exception`, na verdade) sabe guardar uma mensagem textual, acessível depois através do método `getMessage()`.
- **`"Task with id " + taskId + " not found"`** — a concatenação de texto com `+` funciona mesmo `taskId` não sendo uma `String`, porque, ao concatenar um objeto qualquer com uma `String`, o Java chama automaticamente o método `toString()` desse objeto. Como `TaskId` é um `record` (`record TaskId(UUID id)`, visto na Parte 2), o Java já gerou um `toString()` automático para ele, no formato `TaskId[id=...]` — é esse texto que aparece na mensagem final da exceção.

**Por que colocar essa exceção no pacote `domain`, e não em `application`?** Porque "não encontrei uma tarefa com esse id" é, em si, uma regra de negócio — faz parte do vocabulário do domínio de tarefas, e não é uma preocupação exclusiva de como a aplicação está orquestrada. Em outros cenários (por exemplo, uma exceção que representasse "erro de conversão de um DTO", algo puramente técnico da camada de aplicação), o pacote `application` seria a escolha mais adequada.

### 5.4. DeleteTaskUseCase

```java
package dio.taskmanager.application;

import dio.taskmanager.domain.TaskId;
import dio.taskmanager.domain.TaskNotFoundException;
import dio.taskmanager.domain.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteTaskUseCase {
    private final TaskRepository repository;

    public DeleteTaskUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    public void execute(TaskId taskId) {
        if (repository.findById(taskId).isEmpty()) {
            throw new TaskNotFoundException(taskId);
        }

        repository.delete(taskId);
    }
}
```

- **`public void execute(TaskId taskId)`** — diferente dos Use Cases anteriores, este método não devolve nenhum `TaskOutput`: `void` indica que a operação (excluir) não precisa devolver dado nenhum de volta.
- **`repository.findById(taskId).isEmpty()`** — `isEmpty()` é o método "espelho" de `isPresent()` (já usado na Parte 2/3): devolve `true` quando o `Optional` **não** contém nenhum valor. Aqui, verifica-se explicitamente se a tarefa existe antes de tentar excluí-la.
- **`throw new TaskNotFoundException(taskId);`** — a palavra-chave `throw` interrompe a execução do método imediatamente, lançando a exceção customizada criada na Seção 5.3, caso a tarefa não seja encontrada.
- **`repository.delete(taskId);`** — se a tarefa existir, o método `delete` (já implementado desde a Parte 3, dentro de `InMemoryTaskRepository`) é finalmente chamado.

### 5.5. UpdateTaskInput: um DTO com todos os campos opcionais (estilo PATCH)

O quinto e último Use Case, de atualização, é descrito na aula como um pouco mais complexo, pois precisa suportar **atualização parcial**: o cliente da futura API pode querer alterar só o título, só o status, ou qualquer combinação de campos — sem ser obrigado a reenviar tudo de novo.

> **PATCH vs. PUT — dois jeitos diferentes de "atualizar"**
> Lá na Parte 1 deste tutorial, quatro verbos HTTP foram apresentados: `GET`, `POST`, `PUT` e `DELETE`. Existe ainda um quinto verbo relevante para esta seção, `PATCH`, embora ele ainda não tenha sido mencionado explicitamente no README até este ponto. A diferença semântica entre eles: `PUT` normalmente representa uma **substituição completa** de um recurso (o cliente reenvia todos os campos, mesmo os que não mudaram); `PATCH` representa uma **atualização parcial** (o cliente envia só os campos que quer alterar, e os demais permanecem como estavam). O DTO `UpdateTaskInput`, com todos os campos opcionais, é desenhado especificamente para viabilizar esse segundo estilo — mesmo que o endpoint HTTP que vai usá-lo só apareça em um vídeo futuro.

```java
package dio.taskmanager.application.input;

import dio.taskmanager.domain.TaskStatus;

import java.util.Optional;

public record UpdateTaskInput(Optional<String> title,
                              Optional<String> description,
                              Optional<TaskStatus> status) {
}
```

- Assim como `CreateTaskInput`, é um `record` — imutável, sem lógica própria.
- **Todos os três campos são `Optional`** — incluindo `title`, que em `CreateTaskInput` era obrigatório (`String` puro). A diferença faz sentido: ao **criar** uma tarefa, título é sempre exigido; ao **atualizar**, o cliente pode estar alterando apenas a descrição ou o status, sem tocar no título — daí ele também precisar ser opcional aqui.
- O `id` da tarefa **não** aparece como campo deste DTO — porque, como o próprio nome do use case indica (`execute(TaskId id, UpdateTaskInput input)`, visto a seguir), o identificador é recebido separadamente, e não teria sentido permitir que uma requisição de atualização tentasse também *trocar* o id de uma tarefa já existente.

> **Nota sobre a localização física do arquivo:** conferindo a estrutura de pastas real do projeto, `UpdateTaskInput.java` está fisicamente guardado dentro de `application/` (junto de `UpdateTaskUseCase.java`), e **não** dentro do subpacote `application/input/`, onde vive `CreateTaskInput.java` — apesar de sua declaração `package dio.taskmanager.application.input;` dizer o contrário. Isso funciona sem erro de compilação (o Java, no fluxo de build usado pelo Gradle, não exige que a pasta física do arquivo corresponda exatamente ao pacote declarado dentro dele — o que importa é que o pacote declarado seja usado de forma consistente nos `import`s de quem o utiliza, como acontece em `UpdateTaskUseCase.java`), mas é uma pequena inconsistência de organização em relação à convenção adotada pelo resto do projeto (`CreateTaskInput` está corretamente dentro de `application/input/`). Vale considerar mover o arquivo para a pasta correspondente ao seu pacote, por consistência.

### 5.6. UpdateTaskUseCase e o método update() em Task

A estrutura inicial de `UpdateTaskUseCase` segue o padrão já conhecido:

```java
package dio.taskmanager.application;

import dio.taskmanager.application.output.TaskOutput;
import dio.taskmanager.domain.TaskId;
import dio.taskmanager.domain.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateTaskUseCase {
    private final TaskRepository repository;

    public UpdateTaskUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    public TaskOutput execute(TaskId id, UpdateTaskInput input) {

    }
}
```

O primeiro passo dentro de `execute` é localizar a tarefa, reaproveitando exatamente o mesmo padrão já usado em `GetTaskByIdUseCase` e `DeleteTaskUseCase`:

```java
public TaskOutput execute(TaskId id, UpdateTaskInput input) {
    var task = repository.findById(id).orElseThrow(()
            -> new TaskNotFoundException(id));
}
```

A pergunta seguinte é: onde deve morar a lógica de *como* atualizar os campos? A decisão do curso é colocar essa regra dentro da própria classe `Task`, no domínio — não no Use Case. A justificativa: "quem sabe como uma tarefa deve ser atualizada é a própria tarefa". Esse princípio de design tem nome:

> **Tell, Don't Ask e o "domínio rico"**
> É um princípio de orientação a objetos que recomenda **dizer** a um objeto o que fazer (`task.update(...)`), em vez de **perguntar** o estado dele para decidir por fora o que fazer (por exemplo, um Use Case fazendo `if (input.title().isPresent()) task.setTitle(input.title().get());` diretamente). Quando as regras de negócio ficam concentradas nas próprias entidades de domínio (como `Task`), diz-se que o domínio é **rico**; quando as entidades viram apenas "sacos de getters e setters" e toda a lógica fica espalhada pelos serviços que as usam, diz-se que o domínio é **anêmico** — um estilo geralmente considerado menos alinhado com os princípios de Domain-Driven Design (já apresentado na Parte 2 deste tutorial).

O método `update` é então adicionado à classe `Task`:

```java
public void update(Optional<String> title, Optional<String> description,
                   Optional<TaskStatus> status) {
    title.ifPresent(value -> this.title = value);
    description.ifPresent(value -> this.description = Optional.of(value));
    status.ifPresent(value -> this.status = value);
}
```

- **`title.ifPresent(value -> this.title = value);`** — `ifPresent` é um método de `Optional` que recebe uma lambda e a executa **somente se** houver um valor presente dentro do `Optional`; se ele estiver vazio, a lambda simplesmente não é chamada, e nada acontece. Aqui: "se um novo título foi informado na atualização, atribua-o ao campo `title` desta tarefa; caso contrário, deixe o título como já estava". A lambda `value -> this.title = value` recebe o valor de dentro do `Optional` (chamado de `value`) e o atribui ao campo da classe.
- **`status.ifPresent(value -> this.status = value);`** — exatamente a mesma lógica, agora para o campo `status`.
- **`description.ifPresent(value -> this.description = Optional.of(value));`** — aqui há uma pequena diferença: como o campo `description` da classe `Task` já é, ele mesmo, um `Optional<String>` (desde a Parte 2), não basta atribuir `value` (que é um `String` puro, já "desembrulhado" pelo `ifPresent`) diretamente — é preciso "reembrulhá-lo" com `Optional.of(value)` antes de guardá-lo no campo.

> **Divergência entre a narrativa da aula e o código-fonte final: `Optional.of` vs. `Optional.ofNullable`**
> A explicação da aula, registrada no README, afirma que a atualização da descrição usa `Optional.ofNullable`, "permitindo diferenciar 'não alterar' de 'definir como vazio'". Conferindo o arquivo `Task.java` real, porém, o código usa `Optional.of(value)`, não `Optional.ofNullable(value)` — e é este `Optional.of` que também aparece no próprio bloco de código do README, contradizendo a frase da narrativa escrita ali ao lado.
>
> Vale entender a diferença entre os dois métodos, já que ela é sutil:
> - **`Optional.of(valor)`** exige que `valor` **não seja `null`** — se for, ele lança uma `NullPointerException` imediatamente.
> - **`Optional.ofNullable(valor)`** aceita tanto um valor quanto `null` — se `valor` for `null`, ele devolve um `Optional` vazio, sem lançar exceção.
>
> Na prática, dentro deste método `update`, o uso de `Optional.of` **é seguro e correto**: como essa linha só é executada dentro de `ifPresent` (ou seja, `value` já foi comprovadamente extraído de um `Optional` não vazio), `value` nunca pode ser `null` neste ponto — logo, `Optional.of` e `Optional.ofNullable` teriam, aqui, exatamente o mesmo resultado. A frase do README parece descrever uma intenção (ou talvez uma versão anterior do código, ajustada durante a gravação) que não corresponde exatamente ao que ficou na versão final do arquivo — mas o comportamento do código, na prática, está correto de qualquer forma.

De volta ao `UpdateTaskUseCase`, a versão final do método `execute`:

```java
public TaskOutput execute(TaskId id, UpdateTaskInput input) {
    var task = repository.findById(id).orElseThrow(()
            -> new TaskNotFoundException(id));
    task.update(input.title(), input.description(), input.status());
    var updated = repository.save(task);
    return TaskOutput.from(updated);
}
```

- **`task.update(input.title(), input.description(), input.status());`** — os três valores `Optional` do DTO de entrada são repassados diretamente para o método de domínio recém-criado, que decide, campo a campo, o que deve ou não ser alterado.
- **`var updated = repository.save(task);`** — a tarefa, já modificada em memória pelo `update`, é persistida novamente através do mesmo método `save` usado na criação (Parte 4). Note que, diferente do que foi observado como divergência na Seção 4.8 (`CreateTaskUseCase`), aqui o `return` de fato usa a variável `updated` (o valor devolvido por `save`), e não `task` — este Use Case não repete a mesma inconsistência.
- **`return TaskOutput.from(updated);`** — por fim, o mesmo método de fábrica estático já conhecido converte a tarefa atualizada na resposta do Use Case.

Com isso, os cinco Use Cases da camada de aplicação — criação, listagem, busca por id, exclusão e atualização — estão completos, junto com a exceção de domínio `TaskNotFoundException`.

---

### ✅ Checkpoint — Estado do projeto ao final do Vídeo 05

Conferido diretamente contra o código-fonte real entregue em `taskmanager_ate_o_video_05.zip`.

**Estrutura de pastas (alterações em relação ao checkpoint do Vídeo 04, em negrito)**

```
taskmanager/
└── src/
    ├── main/java/dio/taskmanager/
    │   ├── application/
    │   │   ├── CreateTaskUseCase.java                (sem alterações nesta etapa)
    │   │   ├── DeleteTaskUseCase.java                  ← novo
    │   │   ├── GetTaskByIdUseCase.java                 ← novo
    │   │   ├── GetTasksUseCase.java                    ← novo
    │   │   ├── UpdateTaskInput.java                    ← novo (⚠️ ver nota da Seção 5.5 sobre a pasta)
    │   │   ├── UpdateTaskUseCase.java                  ← novo
    │   │   ├── input/
    │   │   │   └── CreateTaskInput.java                (sem alterações)
    │   │   └── output/
    │   │       └── TaskOutput.java                     (sem alterações)
    │   ├── domain/
    │   │   ├── Task.java                               (alterado — novo método update())
    │   │   ├── TaskId.java                             (sem alterações)
    │   │   ├── TaskNotFoundException.java               ← novo
    │   │   ├── TaskRepository.java                     (sem alterações)
    │   │   └── TaskStatus.java                         (sem alterações)
    │   └── infrastructure/repository/
    │       └── InMemoryTaskRepository.java             (sem alterações nesta etapa)
    └── test/java/dio/taskmanager/
        └── (nenhum teste novo nesta etapa — ver nota abaixo)
```

> **Nota importante sobre testes:** ao contrário do Vídeo 04, que produziu `CreateTaskUseCaseTest.java`, **nenhum teste automatizado novo foi criado** para `GetTasksUseCase`, `GetTaskByIdUseCase`, `DeleteTaskUseCase` ou `UpdateTaskUseCase` nesta etapa — conferido tanto pela ausência desses arquivos em `src/test`, quanto pela ausência de `.class` compilados correspondentes dentro de `build/classes/java/test`. Se isso for corrigido em um vídeo futuro, esta seção deve ser atualizada.

**Task.java (versão final desta etapa)**

```java
package dio.taskmanager.domain;

import lombok.Getter;
import org.springframework.util.Assert;
import java.util.Optional;

@Getter
public class Task {
    private final TaskId id;
    private String title;
    private Optional<String> description;
    private TaskStatus status;

    public Task(String title, Optional<String> description) {
        this.id = new TaskId(); // gera um novo identificador
        this.title = title;
        this.description = description;
        this.status = TaskStatus.PENDING;
    }

    public void update(Optional<String> title, Optional<String> description,
                       Optional<TaskStatus> status) {
        title.ifPresent(value -> this.title = value);
        description.ifPresent(value -> this.description = Optional.of(value));
        status.ifPresent(value -> this.status = value);
    }

    public TaskId getId() {
        return id;
    }
}
```

**TaskNotFoundException.java, GetTasksUseCase.java, GetTaskByIdUseCase.java, DeleteTaskUseCase.java, UpdateTaskInput.java e UpdateTaskUseCase.java** — exatamente como mostrados nas Seções 5.1 a 5.6 deste documento, sem diferenças em relação ao código-fonte real.

**TaskRepository.java** — permanece idêntico desde o checkpoint do Vídeo 03 (já continha `save`, `findAll`, `findById` e `delete` — os novos Use Cases apenas passaram a *usar* métodos que já existiam no contrato, sem precisar alterá-lo).

---

## Glossário de conceitos Java e Spring — complemento dos Vídeos 04 e 05

Continuação do glossário iniciado na Parte 3 deste tutorial — aqui aparecem **apenas os termos novos**, introduzidos nestes dois vídeos.

### Arquitetura e padrões de projeto

| Termo | Significado |
|---|---|
| Use Case (caso de uso) | Padrão de projeto em que cada operação de negócio ganha sua própria classe, com um único método público (`execute`), em vez de um serviço genérico com muitos métodos. Aplicação direta do Princípio da Responsabilidade Única (SRP). |
| DTO (Data Transfer Object) | Classe cujo único propósito é carregar dados entre camadas do sistema, sem regra de negócio própria. Usado para não expor diretamente as entidades de domínio para fora da camada de aplicação. |
| Static Factory Method (método de fábrica estático) | Método `static` com nome descritivo (ex.: `from`), usado como alternativa a um construtor comum, para centralizar a lógica de criação/conversão de um objeto em um único lugar. |
| Injeção de Dependência (Dependency Injection) | Técnica em que uma classe declara do que precisa (via construtor ou campo), sem criar essa dependência com as próprias mãos nem conhecer a implementação concreta que será usada. |
| IoC (Inversion of Control) | Princípio segundo o qual a responsabilidade de criar e conectar objetos é retirada do próprio código e "invertida", sendo delegada a um componente externo — no caso do Spring, o container de IoC. |
| Bean (Spring) | Um objeto criado e gerenciado pelo container do Spring, registrado a partir de anotações como `@Service`, `@Repository` ou `@Component`. |
| Mock | Objeto "de mentira", criado por uma biblioteca de mocking (como o Mockito), que simula o comportamento de uma dependência real, sem executá-la de fato — usado para isolar uma classe durante um teste. |
| PATCH vs. PUT | Dois verbos HTTP usados para atualização: `PUT` normalmente substitui um recurso por completo; `PATCH` permite uma atualização parcial, alterando apenas os campos enviados. |
| Tell, Don't Ask | Princípio de orientação a objetos que recomenda dizer a um objeto o que fazer (ex.: `task.update(...)`), em vez de perguntar seu estado por fora para decidir a ação em outro lugar. |
| Domínio rico / domínio anêmico | "Domínio rico" descreve entidades que concentram suas próprias regras de negócio (como o método `update` em `Task`); "domínio anêmico" descreve entidades reduzidas a getters/setters, com toda a lógica espalhada por serviços externos — geralmente visto como um antipadrão em DDD. |
| Exceção verificada / não verificada (checked / unchecked exception) | Exceções verificadas (que estendem `Exception`, mas não `RuntimeException`) exigem tratamento explícito (`try/catch` ou `throws`), fiscalizado pelo compilador; exceções não verificadas (que estendem `RuntimeException`) não têm essa exigência. |

### Streams e programação funcional

| Termo | Significado |
|---|---|
| Stream API | Conjunto de ferramentas do Java para processar coleções de forma declarativa, encadeando operações como `.map()`, `.filter()` e `.toList()`, em vez de escrever laços `for` manuais. |
| `.stream()` | Converte uma coleção (como uma `List`) em uma `Stream`, permitindo aplicar operações encadeadas sobre seus elementos. |
| `.map(...)` | Operação de Stream (ou de `Optional`) que transforma cada elemento em outro, aplicando uma função, e devolve uma nova Stream (ou Optional) com os elementos já transformados. |
| `.toList()` | Encerra uma Stream, recolhendo seus elementos de volta em uma `List` comum. |
| Lambda (expressão lambda) | Uma função anônima, escrita diretamente onde é usada, no formato `parâmetros -> corpo`. Ex.: `value -> this.title = value`. |
| Method reference (referência a método) | Sintaxe abreviada (`Classe::metodo`) para passar um método já existente como se fosse uma função, quando uma lambda faria apenas chamar esse método sem lógica extra. Ex.: `TaskOutput::from`. |
| `Optional#ifPresent(...)` | Executa a lambda recebida somente se o `Optional` contiver um valor; se estiver vazio, não faz nada. |
| `Optional#orElseThrow(...)` | Devolve o valor do `Optional`, se presente; caso contrário, lança a exceção produzida pela lambda fornecida. |
| `Optional.of(...)` vs. `Optional.ofNullable(...)` | `Optional.of(valor)` exige um valor não nulo (lança `NullPointerException` se for `null`); `Optional.ofNullable(valor)` aceita `null`, devolvendo um `Optional` vazio nesse caso. |

### Anotações e bibliotecas (Spring / testes)

| Termo | Significado |
|---|---|
| `@Service` | Marca uma classe como um bean gerenciado pelo Spring, indicando semanticamente que ela contém lógica de aplicação/negócio. |
| `@Repository` | Marca uma classe como um bean gerenciado pelo Spring, indicando semanticamente que ela é responsável por acesso a dados. |
| `@Autowired` | Instrui o Spring a injetar automaticamente, em um campo ou construtor, um bean compatível com o tipo declarado. Opcional em construtores únicos desde o Spring 4.3. |
| `@SpringBootTest` | Anotação de teste que sobe o `ApplicationContext` completo do Spring (todos os beans reais), permitindo testar a aplicação de forma mais próxima do ambiente real. |
| `@ExtendWith(MockitoExtension.class)` | Ativa o suporte do Mockito dentro de uma classe de teste JUnit 5, processando automaticamente anotações como `@Mock` e `@InjectMocks`. |
| `@Mock` (Mockito) | Cria um objeto simulado (mock) de uma interface ou classe, sem implementação real por trás. |
| `@InjectMocks` (Mockito) | Instancia a classe sob teste e injeta nela, automaticamente, os mocks declarados na mesma classe de teste. |
| `when(...).thenAnswer(...)` / `when(...).thenReturn(...)` (Mockito) | Configura o comportamento simulado de um método de um mock: "quando chamado assim, responda assim". |
| `verify(mock, times(n)).metodo(...)` (Mockito) | Confirma que um método de um mock foi chamado um número específico de vezes durante o teste. |
| `any(Classe.class)` (Mockito) | *Matcher* que aceita qualquer instância da classe informada como argumento, ao configurar ou verificar chamadas em um mock. |

---

## Próximos passos: o que vem a partir do Vídeo 06

*(Esta seção substitui a versão anterior, "Próximos passos: o que vem a partir do Vídeo 04", removendo os vídeos já cobertos por este documento.)*

Com os cinco Use Cases da camada de aplicação prontos (criação, listagem, busca por id, exclusão e atualização), a base de orquestração do domínio está completa. Segundo o roteiro do curso (títulos já visíveis no README, ainda sem conteúdo detalhado na época em que este documento foi escrito), os próximos vídeos devem seguir esta sequência:

- **Vídeo 06 — Infraestrutura e interface:** deve introduzir a camada `infrastructure` voltada à Web — um `@RestController` que expõe os Use Cases já prontos como endpoints HTTP reais, muito provavelmente usando as anotações `@GetMapping`, `@PostMapping`, `@PutMapping`/`@PatchMapping` e `@DeleteMapping`, cada uma mapeada para um dos cinco Use Cases já implementados.
- **Vídeo 07 — Consulta de tarefas:** deve conectar `GetTasksUseCase` e `GetTaskByIdUseCase` a endpoints `GET` reais (provavelmente `GET /tasks` e `GET /tasks/{id}`), incluindo o tratamento HTTP da exceção `TaskNotFoundException` (esperado: convertê-la em uma resposta `404 Not Found`).
- **Vídeo 08 — Validando dados:** deve introduzir validações automáticas de entrada (por exemplo, com anotações do Bean Validation, como `@NotBlank` ou `@Valid`), tratando respostas de erro (`400 Bad Request`) para dados inválidos — como criar uma tarefa sem título.
- **Vídeo 09 — Documentando a API:** deve apresentar uma ferramenta de documentação automática de APIs (comumente o Swagger/OpenAPI no ecossistema Spring), gerando uma interface visual para explorar os endpoints criados.
- **Vídeo 10 — Evoluindo a API:** deve fechar o curso com melhorias adicionais, possivelmente conectando os endpoints de criação (`POST`), atualização (`PATCH`/`PUT`) e remoção (`DELETE`) que, até este ponto, só existem como Use Cases — ainda sem nenhum `@RestController` que os exponha via HTTP.

> **Sugestão de uso deste documento (mantida da versão anterior):** depois de assistir a cada novo vídeo, adicione uma nova seção a este tutorial seguindo o mesmo formato: bloco de código → explicação linha a linha → checkpoint conferido contra o código real → atualização do glossário e dos próximos passos. Isso mantém o material sempre alinhado ao seu ritmo de estudo.
