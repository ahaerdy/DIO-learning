# Tutorial de Estudos — Construindo um Task Manager com Java e Spring Boot

**Criando sua primeira API REST — Vídeo 08**

- Curso: NTT Data — Jornada Tech (DIO) · Trilha Java + Spring
- Instrutor: Thiago Poiani
- Documento de referência pessoal — nível iniciante em Java
- Continuação de: *"Tutorial de Estudos — Construindo um Task Manager com Java e Spring Boot — Consulta de tarefas — Vídeo 07"*, que por sua vez continua os documentos dos Vídeos 04–05 e 06, e dos Vídeos 01 a 03

---

## Sobre este documento

Este é o quinto capítulo do tutorial de estudos deste curso, e dá sequência direta ao documento anterior (Vídeo 07), que deixou o `TaskController` com os cinco endpoints do CRUD de tarefas mapeados — criação, listagem, busca por ID, atualização e remoção —, mas **sem nenhuma validação de dados de entrada**: até ali, a API aceitava, por exemplo, uma tarefa criada com título vazio, ou com um texto de centenas de caracteres, sem reclamar de nada.

É exatamente essa lacuna que o Vídeo 08 fecha: pela primeira vez no curso, aparece a biblioteca **Bean Validation** (na sua implementação usada pelo Spring Boot), permitindo declarar regras de validação diretamente nos campos de um DTO através de anotações — e fazendo com que o próprio Spring rejeite automaticamente requisições que violem essas regras, antes mesmo de qualquer código de negócio ser executado. O vídeo também retoma e amplia o `GlobalExceptionHandler`, criado no Vídeo 07, ensinando-o a devolver mensagens de erro específicas para cada campo inválido, em vez de um `400 Bad Request` genérico e sem contexto.

Assim como os documentos anteriores, este foi escrito a partir das anotações de aula (README atualizado até o Vídeo 08) e conferido diretamente contra o código-fonte real do projeto nesta etapa (`taskmanager_ate_o_video_08.zip`) — novamente com destaque para pontos em que a narrativa da aula e o código real divergem. **Há uma boa notícia e uma má notícia nesta etapa:** a boa notícia é que o bug do construtor do `TaskController`, identificado e detalhado no documento anterior (Vídeo 07, Seção 7.6), **foi corrigido** neste `.zip` — os cinco Use Cases agora são injetados corretamente. A má notícia é que surgiu um novo problema, desta vez no `GlobalExceptionHandler`: como será detalhado na Seção 8.5 e reforçado no checkpoint, o método de tratamento de validação tem **anotações duplicadas**, um erro que impede a compilação do arquivo tal como entregue.

---

## Parte 8 — Validando dados (Vídeo 08)

### 8.1. Adicionando a dependência `spring-boot-starter-validation`

Antes de qualquer anotação de validação poder ser usada, é preciso trazer a biblioteca responsável por interpretá-las. Uma nova linha é adicionada ao `build.gradle`, ao lado das dependências já existentes desde os vídeos anteriores:

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter'
    implementation 'org.springframework.boot:spring-boot-starter-validation'

    implementation 'org.springframework.boot:spring-boot-starter-web'

    // Testes com Spring Boot + JUnit 5
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```

- **`implementation 'org.springframework.boot:spring-boot-starter-validation'`** — segue exatamente o mesmo padrão de *starter* já apresentado no Vídeo 06 (relembrando: um *starter* é um pacote de dependências pré-selecionado pelo Spring Boot para uma funcionalidade específica, evitando que cada biblioteca precise ser descoberta e declarada manualmente uma a uma). Este starter em particular traz a implementação de referência da especificação **Bean Validation** (também conhecida por seu nome de especificação, Jakarta Validation), usada por baixo dos panos pelo Spring para interpretar anotações como `@NotBlank` e `@Size`.

> **Nota sobre o `build.gradle` real desta etapa:** o bloco de dependências mostrado no README inclui também a linha `testRuntimeOnly 'org.junit.platform:junit-platform-launcher'`. Conferindo o `build.gradle` real entregue neste `.zip`, essa linha **novamente não está presente** — o mesmo padrão de divergência já identificado e explicado no documento do Vídeo 06 (Seção 6.3 daquele tutorial): tudo indica que essa linha faz parte de um trecho gerado por padrão em algum outro contexto (por exemplo, ao criar um novo projeto pelo Spring Initializr), reaproveitado sem querer nas capturas de tela do README, sem nunca ter sido de fato adicionado a este projeto específico. Como nos vídeos anteriores, essa divergência não tem, por ora, nenhum efeito prático sobre a compilação ou execução dos testes.

### 8.2. Validando o `CreateTaskRequest`: `@NotBlank` e `@Size`

Com a dependência disponível, o primeiro DTO a receber anotações de validação é o `CreateTaskRequest` (criado no Vídeo 06). As regras são aplicadas diretamente sobre os componentes do `record`, no ponto exato de sua declaração:

```java
public record CreateTaskRequest(
        @NotBlank
        @Size(min = 3, max = 100)
        String title,
        Optional<@Size(max = 500) String> description) {
    public CreateTaskInput toInput() {
        return new CreateTaskInput(title, description);
    }
}
```

> **O que é Bean Validation?**
> É uma especificação do ecossistema Java (parte do Jakarta EE, sucessor do antigo Java EE) que define um conjunto padronizado de anotações para expressar regras de validação de dados — sem que o desenvolvedor precise escrever manualmente `if (title == null || title.isBlank()) { throw ... }` para cada regra. Cada anotação (como `@NotBlank` ou `@Size`) é interpretada, em tempo de execução, por um mecanismo que percorre o objeto sendo validado, verifica cada regra declarada e reúne as violações encontradas. No Spring Boot, esse mecanismo entra em ação automaticamente quando um parâmetro anotado com `@Valid` (Seção 8.3) recebe um objeto anotado dessa forma.

- **`@NotBlank`** — aplicada sobre `title`, exige que o valor recebido não seja `null`, nem uma string vazia (`""`), nem composta apenas por espaços em branco. É uma anotação mais rigorosa que sua "prima" `@NotEmpty` (que só rejeita `null` e string vazia, mas aceitaria `"   "`) e que `@NotNull` (que só rejeita `null`, mas aceitaria `""`).
- **`@Size(min = 3, max = 100)`** — também sobre `title`, exige que o tamanho do texto (número de caracteres) esteja entre 3 e 100, inclusive. Repare que `@Size` sozinha não impede um valor `null` — por isso ela aparece combinada com `@NotBlank` no mesmo campo: a primeira garante que exista algum texto; a segunda garante que esse texto tenha um tamanho razoável.
- **`Optional<@Size(max = 500) String> description`** — o ponto sintaticamente mais peculiar deste bloco. Como `description` continua sendo um campo opcional (padrão já estabelecido desde o Vídeo 02, quando a descrição foi modelada como `Optional<String>`), a anotação `@Size` não pode ser colocada na posição usual (antes do tipo `Optional`), porque a regra de validação não deve se aplicar ao `Optional` em si, e sim ao `String` que eventualmente existe *dentro* dele. Por isso, a anotação aparece **dentro** dos sinais de menor/maior do genérico: `Optional<@Size(max = 500) String>`. Essa é uma forma de **anotação de tipo** (*type annotation*), um recurso do Java (desde a versão 8) que permite anotar não apenas uma variável ou campo inteiro, mas um uso específico de um tipo, em qualquer lugar em que ele apareça — inclusive dentro de um parâmetro genérico. Na prática, isso instrui o mecanismo de validação a "olhar dentro do `Optional`, e validar o texto que estiver lá, se houver um".
  > Note que `description` não recebe `min`, apenas `max = 500` — ou seja, continua sendo permitido enviar uma descrição vazia (ou omiti-la completamente, como já era o comportamento desde o Vídeo 02), mas não uma descrição excessivamente longa.

### 8.3. Ativando a validação no controller: `@Valid`

Declarar as anotações no DTO, por si só, não faz nada — é preciso avisar o Spring de que ele deve, de fato, disparar a validação ao receber aquele objeto. Isso é feito acrescentando `@Valid` ao parâmetro do método `create()`, no `TaskController`:

```java
@PostMapping
TaskResponse create(@RequestBody @Valid CreateTaskRequest request) {
    var input = request.toInput();
    var output = createTaskUseCase.execute(input);
    return TaskResponse.from(output);
}
```

- **`@Valid`** — colocada logo antes do parâmetro (a ordem entre `@RequestBody` e `@Valid` não importa; ambas anotam o mesmo parâmetro), instrui o Spring a acionar o mecanismo de Bean Validation sobre o `CreateTaskRequest` recém-desserializado, **antes** do corpo do método `create()` começar a rodar. Se alguma regra declarada no DTO (Seção 8.2) for violada, o método `create()` nunca chega a ser executado: o Spring intercepta a requisição e lança uma exceção própria de validação (`MethodArgumentNotValidException`, retomada na Seção 8.5), interrompendo o fluxo ali mesmo.
  > **Sem `@Valid`, as anotações do DTO seriam ignoradas.** É um erro relativamente comum (mesmo entre desenvolvedores com experiência) declarar `@NotBlank`/`@Size` em um DTO e esquecer de anotar o parâmetro correspondente com `@Valid` — nesse caso, o código compila e roda normalmente, mas nenhuma validação de fato acontece, porque não existe nada instruindo o Spring a verificá-las.

Com a aplicação reiniciada, uma requisição de teste enviando apenas `{"title": "AB"}` (um título de dois caracteres, abaixo do mínimo de três exigido por `@Size(min = 3, ...)`) já é suficiente para a API responder com **400 Bad Request** — embora, neste primeiro momento, sem nenhum detalhe sobre qual campo falhou ou por quê:

```json
{
  "timestamp": "2026-03-27T16:...",
  "status": 400,
  "error": "Bad Request",
  "path": "/tasks"
}
```

### 8.4. Por que o 400 padrão não é suficiente

Esse comportamento — rejeitar a requisição com 400, mas sem detalhar o motivo — é o tratamento **padrão** que o próprio Spring Boot já aplica, de forma automática, sempre que uma validação falha e nenhum tratamento customizado foi configurado (nesse sentido, é parecido com o 500 genérico já visto no Vídeo 07 para uma `TaskNotFoundException` não tratada: o framework tem um comportamento de segurança embutido, mas ele é propositalmente genérico). Do ponto de vista de quem consome a API, um `400` sem detalhes obriga o cliente a "adivinhar" o que deu errado — nada muito diferente de receber apenas um "erro" sem explicação. O próximo passo, então, é ensinar o `GlobalExceptionHandler` (criado no Vídeo 07) a tratar esse cenário de forma mais informativa.

### 8.5. Detalhando os erros de validação no `GlobalExceptionHandler`

Um novo método é adicionado ao `GlobalExceptionHandler`, desta vez tratando `MethodArgumentNotValidException` — a exceção específica lançada internamente pelo Spring sempre que uma validação acionada por `@Valid` falha:

```java
@ExceptionHandler(MethodArgumentNotValidException.class)
@ResponseStatus(HttpStatus.BAD_REQUEST)
public Map<String, String> handleValidationExceptions(
        MethodArgumentNotValidException ex) {

    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult().getAllErrors().forEach(error -> {
        String fieldName = ((FieldError) error).getField();
        String errorMessage = error.getDefaultMessage();
        errors.put(fieldName, errorMessage);
    });

    return errors;
}
```

- **`@ExceptionHandler(MethodArgumentNotValidException.class)`** — segue exatamente o mesmo padrão já apresentado no Vídeo 07 para `TaskNotFoundException` (Seção 7.5 do documento anterior): declara que este método trata especificamente esse tipo de exceção, sempre que ela for lançada por qualquer controller da aplicação.
- **`MethodArgumentNotValidException`** — é a exceção própria do Spring, lançada quando um argumento anotado com `@Valid` (Seção 8.3) falha em uma ou mais das regras declaradas no seu tipo. O nome é bastante literal: "o argumento do método não é válido".
- **`@ResponseStatus(HttpStatus.BAD_REQUEST)`** — mesmo raciocínio do Vídeo 07: sem essa anotação, o método "tratar" a exceção não seria suficiente para garantir o código de status correto na resposta. `BAD_REQUEST` é a constante do `enum HttpStatus` para o código **400**.
- **`Map<String, String> errors = new HashMap<>();`** — diferente do handler de `TaskNotFoundException` (que devolve uma simples `String`), aqui o retorno é um `Map<String, String>` — uma coleção de pares chave/valor. A ideia é que, se várias regras forem violadas ao mesmo tempo (por exemplo, um título curto demais **e** uma descrição longa demais), a resposta consiga informar todas elas de uma vez, uma por campo.
  > **O que é um `HashMap`?** É a implementação mais comum da interface `Map` em Java (já usada, aliás, desde o Vídeo 03, dentro do `InMemoryTaskRepository`, para guardar as tarefas por ID) — uma estrutura de dados que associa **chaves** a **valores**, sem permitir chaves repetidas. Aqui, a chave é o nome do campo que falhou na validação (por exemplo, `"title"`), e o valor é a mensagem de erro correspondente (por exemplo, `"size must be between 3 and 100"`).
- **`ex.getBindingResult()`** — todo `MethodArgumentNotValidException` carrega consigo um `BindingResult`, um objeto que reúne o resultado completo do processo de validação (quais campos foram checados, quais falharam, com que mensagem). É basicamente o "relatório" gerado pelo mecanismo de Bean Validation ao processar o objeto.
- **`.getAllErrors()`** — a partir do `BindingResult`, devolve uma `List` com todas as violações de regra encontradas, cada uma representada por um `ObjectError` (a superclasse mais genérica de erro de validação do Spring).
- **`.forEach(error -> { ... })`** — percorre essa lista, executando o bloco de código para cada erro encontrado — o mesmo `forEach` já visto anteriormente para outras coleções neste projeto (embora, até aqui, o padrão dominante do código tenha sido `stream().map(...).toList()`; `forEach` é usado aqui porque o objetivo não é *transformar* a coleção em outra, e sim *executar uma ação* — preencher o `Map` — para cada elemento).
- **`((FieldError) error).getField()`** — cada `ObjectError` genérico devolvido por `getAllErrors()` é, na prática (quando a violação está associada a um campo específico, como é o caso de `@NotBlank`/`@Size`), uma instância de uma subclasse mais específica, `FieldError`, que adiciona informações como *qual campo* falhou. Como o tipo declarado da variável `error` (dentro do `forEach`) é o supertipo `ObjectError`, é preciso fazer um **cast explícito** — `(FieldError) error` — para acessar o método `getField()`, específico de `FieldError` e ausente em `ObjectError`.
  > **O que é um *cast*?** É uma instrução explícita para o compilador Java tratar uma referência de um tipo como se fosse de outro tipo (geralmente mais específico) — aqui, "trate este `ObjectError` como um `FieldError`". Um cast malfeito (tentando converter para um tipo incompatível com o objeto real) lança uma `ClassCastException` em tempo de execução; neste caso específico, o cast é seguro, porque toda violação vinda de um `@NotBlank`/`@Size` aplicado a um campo de `record` é, de fato, reportada pelo Spring como um `FieldError`.
- **`error.getDefaultMessage()`** — devolve a mensagem de validação padrão associada à regra violada — por exemplo, para `@Size(min = 3, max = 100)`, a mensagem padrão gerada é `"size must be between 3 and 100"`. Essas mensagens padrão vêm embutidas na própria biblioteca de validação, mas podem ser customizadas (por exemplo, `@NotBlank(message = "...")`) — customização ainda não usada neste projeto.
- **`errors.put(fieldName, errorMessage)`** — associa, dentro do `Map`, o nome do campo à sua mensagem de erro correspondente.

Repetindo o mesmo teste de antes (`{"title": "AB"}`), a resposta passa a trazer exatamente essa informação:

```json
{
  "title": "size must be between 3 and 100"
}
```

Com isso, o cliente da API — seja uma aplicação mobile, web, ou outro serviço consumindo essa API — recebe uma informação objetiva e acionável sobre o erro, em vez de apenas um código de status sem nenhum contexto adicional.

> **Divergência encontrada no código-fonte real desta etapa:** o método `handleValidationExceptions`, conferido diretamente no `.zip` entregue nesta etapa, foi escrito com as anotações **duplicadas**:
> ```java
> @ExceptionHandler(MethodArgumentNotValidException.class)
> @ResponseStatus(HttpStatus.BAD_REQUEST)
> @ExceptionHandler(MethodArgumentNotValidException.class)
> @ResponseStatus(HttpStatus.BAD_REQUEST)
> public Map<String, String> handleValidationExceptions(
>         MethodArgumentNotValidException ex) {
> ```
> Ou seja, tanto `@ExceptionHandler(MethodArgumentNotValidException.class)` quanto `@ResponseStatus(HttpStatus.BAD_REQUEST)` aparecem **duas vezes seguidas**, seguidas então da assinatura do método. Diferente das divergências "cosméticas" já vistas em vídeos anteriores (como imports não utilizados, no Vídeo 06) e diferente também do bug do Vídeo 07 (que compilava, mas falhava só em tempo de execução), **esta é uma divergência que impede a compilação do projeto**: nem `@ExceptionHandler` nem `@ResponseStatus` são anotações declaradas como `@Repeatable` no Spring Framework — ou seja, o Java não permite, por padrão, aplicar a mesma anotação duas vezes sobre o mesmo elemento (aqui, o mesmo método). Tentar compilar a classe exatamente como está gera um erro do tipo `duplicate annotation`.
>
> Tudo indica, mais uma vez, que isso é um resíduo do ritmo de edição ao vivo da aula: bastante provável que, ao mover o cursor para acrescentar as anotações prontas de um bloco copiado ou reaproveitado, as duas linhas originais não tenham sido removidas antes de colar novamente as mesmas duas linhas logo abaixo. A correção é simples — remover o par duplicado, deixando cada anotação apenas uma vez, exatamente como mostrado no início desta seção (Seção 8.5) e como o próprio README também mostra, sem duplicação, em suas capturas de tela. Esse ponto é retomado no checkpoint a seguir, com o conteúdo completo e exato do arquivo real.

---

### ✅ Checkpoint — Estado do projeto ao final do Vídeo 08

Conferido diretamente contra o código-fonte real entregue em `taskmanager_ate_o_video_08.zip`.

**Estrutura de pastas (alterações em relação ao checkpoint do Vídeo 07, em negrito)**

```
taskmanager/
├── build.gradle                                        (alterado — nova dependência de validação)
└── src/
    ├── main/java/dio/taskmanager/
    │   ├── TaskmanagerApplication.java                  (sem alterações)
    │   ├── application/                                 (sem alterações nesta etapa)
    │   ├── domain/                                       (sem alterações nesta etapa)
    │   ├── infrastructure/
    │   │   ├── http/
    │   │   │   ├── TaskController.java                    (alterado — construtor corrigido + @Valid)
    │   │   │   ├── GlobalExceptionHandler.java             (alterado — novo handler ⚠️ ver nota abaixo)
    │   │   │   ├── request/
    │   │   │   │   ├── CreateTaskRequest.java               (alterado — anotações de validação)
    │   │   │   │   └── UpdateTaskRequest.java               (sem alterações)
    │   │   │   └── response/
    │   │   │       └── TaskResponse.java                   (sem alterações)
    │   │   └── repository/
    │   │       └── InMemoryTaskRepository.java            (sem alterações)
    │   └── playground/
    │       └── Main.java                                  (sem alterações)
    └── test/java/dio/taskmanager/                          (sem alterações nesta etapa)
```

**build.gradle (versão final desta etapa, seção `dependencies`)**

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter'
    implementation 'org.springframework.boot:spring-boot-starter-validation'

    implementation 'org.springframework.boot:spring-boot-starter-web'

    // Testes com Spring Boot + JUnit 5
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```

**CreateTaskRequest.java (versão final desta etapa)** — exatamente como mostrado na Seção 8.2, sem diferenças em relação ao código-fonte real.

```java
package dio.taskmanager.infrastructure.http.request;
import dio.taskmanager.application.input.CreateTaskInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Optional;

public record CreateTaskRequest(
        @NotBlank
        @Size(min = 3, max = 100)
        String title,
        Optional<@Size(max = 500) String> description) {

    public CreateTaskInput toInput() {
        return new CreateTaskInput(title, description);
    }
}
```

**TaskController.java (versão final desta etapa)**

```java
package dio.taskmanager.infrastructure.http;

import dio.taskmanager.application.*;
import dio.taskmanager.domain.TaskId;
import dio.taskmanager.infrastructure.http.request.CreateTaskRequest;
import dio.taskmanager.infrastructure.http.request.UpdateTaskRequest;
import dio.taskmanager.infrastructure.http.response.TaskResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final CreateTaskUseCase createTaskUseCase;
    private final GetTasksUseCase getTasksUseCase;
    private final GetTaskByIdUseCase getTaskByIdUseCase;
    private final DeleteTaskUseCase deleteTaskUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;


    public TaskController(CreateTaskUseCase createTaskUseCase,
                          GetTasksUseCase getTasksUseCase,
                          GetTaskByIdUseCase getTaskByIdUseCase,
                          DeleteTaskUseCase deleteTaskUseCase,
                          UpdateTaskUseCase updateTaskUseCase) {
        this.createTaskUseCase = createTaskUseCase;
        this.getTasksUseCase = getTasksUseCase;
        this.getTaskByIdUseCase = getTaskByIdUseCase;
        this.deleteTaskUseCase = deleteTaskUseCase;
        this.updateTaskUseCase = updateTaskUseCase;
    }


    @PostMapping
    TaskResponse create(@RequestBody @Valid CreateTaskRequest request) {
        var input = request.toInput();
        var output = createTaskUseCase.execute(input);
        return TaskResponse.from(output);
    }

    @GetMapping
    List<TaskResponse> list() {
        return getTasksUseCase.execute().stream().map(TaskResponse::from).toList();
    }

    @GetMapping("/{id}")
    TaskResponse read(@PathVariable UUID id) {
        var output = getTaskByIdUseCase.execute(new TaskId(id));
        return TaskResponse.from(output);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable UUID id) {
        deleteTaskUseCase.execute(new TaskId(id));
    }

    @PatchMapping("/{id}")
    TaskResponse update(@PathVariable UUID id, @RequestBody UpdateTaskRequest request) {
        var input = request.toInput();
        var output = updateTaskUseCase.execute(new TaskId(id), input);
        return TaskResponse.from(output);
    }

}
```

> ✅ **Boa notícia, comparando com o checkpoint do Vídeo 07:** o construtor do `TaskController` agora recebe corretamente os cinco Use Cases como parâmetros (`createTaskUseCase`, `getTasksUseCase`, `getTaskByIdUseCase`, `deleteTaskUseCase`, `updateTaskUseCase`), exatamente como o bug descrito na Seção 7.6 do documento anterior recomendava corrigir. Os endpoints `read()`, `delete()` e `update()`, que antes lançariam `NullPointerException` em tempo de execução, agora funcionam normalmente — este ponto específico do checkpoint anterior está resolvido nesta etapa.

**GlobalExceptionHandler.java (versão final desta etapa — conteúdo exato do arquivo real, incluindo a duplicação de anotações)**

```java
package dio.taskmanager.infrastructure.http;

import dio.taskmanager.domain.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleTaskNotFoundException(TaskNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((ObjectError error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
```

> ⚠️ **Repetindo, de forma resumida, o ponto já detalhado na Seção 8.5:** as linhas `@ExceptionHandler(MethodArgumentNotValidException.class)` e `@ResponseStatus(HttpStatus.BAD_REQUEST)` aparecem duplicadas, uma logo depois da outra, sobre o método `handleValidationExceptions`. Como nenhuma das duas anotações é `@Repeatable`, este arquivo **não compila** como está — é o primeiro erro deste tutorial que impede a build do projeto por completo (diferente do bug do construtor no Vídeo 07, que compilava e falhava apenas em tempo de execução). A correção é remover o segundo par de linhas duplicadas:
> ```java
> @ExceptionHandler(MethodArgumentNotValidException.class)
> @ResponseStatus(HttpStatus.BAD_REQUEST)
> public Map<String, String> handleValidationExceptions(
>         MethodArgumentNotValidException ex) {
>     // ... corpo inalterado
> }
> ```
> Esse ajuste é necessário para conseguir compilar e rodar o projeto localmente a partir deste `.zip`; o restante do arquivo (a lógica de `handleValidationExceptions`, e o handler de `TaskNotFoundException`, herdado do Vídeo 07) está correto e não precisa de nenhuma outra alteração.
>
> Vale notar também duas diferenças pequenas e sem efeito prático em relação ao bloco de código mostrado no README (Seção 8.5): o parâmetro do `forEach` aparece com o tipo explícito, `(ObjectError error) -> {...}`, em vez de apenas `error -> {...}` (o Java já seria capaz de inferir esse tipo sozinho; declará-lo explicitamente não muda o comportamento) e há uma linha em branco a mais antes da chave de fechamento da classe. Nenhuma das duas tem qualquer impacto sobre a compilação ou o comportamento do código.

**Arquivos sem nenhuma alteração nesta etapa:** todos os cinco Use Cases (`CreateTaskUseCase`, `GetTasksUseCase`, `GetTaskByIdUseCase`, `DeleteTaskUseCase`, `UpdateTaskUseCase`), toda a camada `domain` (`Task`, `TaskId`, `TaskStatus`, `TaskRepository`, `TaskNotFoundException`), `InMemoryTaskRepository`, `TaskmanagerApplication`, `UpdateTaskRequest.java`, `TaskResponse.java`, `Main.java` (o *playground*) e todos os arquivos de teste existentes.

> **Nota importante sobre o alcance desta etapa:** a validação com Bean Validation, nesta etapa, foi aplicada **apenas** ao `CreateTaskRequest` (campos `title` e `description`) — o `UpdateTaskRequest` (criado no Vídeo 07) continua sem nenhuma anotação de validação, e o parâmetro `request` do método `update()`, no `TaskController`, continua sem `@Valid`. Ou seja, uma requisição `PATCH /tasks/{id}` com um título vazio, por exemplo, ainda seria aceita normalmente pela API nesta etapa. Também não há, até aqui, nenhuma validação sobre o campo `status` do `UpdateTaskRequest` — um valor de texto que não corresponda a nenhuma constante de `TaskStatus` continuaria resultando em uma `IllegalArgumentException` não tratada (como já observado no documento do Vídeo 07, Seção 7.7), e não em um `400` amigável como os agora tratados pelo `handleValidationExceptions`. Por fim, como nos vídeos anteriores, não há nenhum teste automatizado cobrindo essas novas validações — apenas os testes manuais narrados na aula.

---

## Glossário de conceitos Java e Spring — complemento do Vídeo 08

Continuação dos glossários dos documentos anteriores — apenas os termos novos, introduzidos neste vídeo.

| Termo | Significado |
|---|---|
| Bean Validation | Especificação do ecossistema Java (parte do Jakarta EE) que define um conjunto padronizado de anotações para expressar regras de validação de dados em objetos Java, interpretadas automaticamente por um mecanismo de validação em tempo de execução. |
| `@NotBlank` | Anotação de Bean Validation que exige que um campo `String` não seja `null`, nem uma string vazia, nem composta apenas por espaços em branco. |
| `@Size(min, max)` | Anotação de Bean Validation que exige que o tamanho de um campo (número de caracteres em uma `String`, ou de elementos em uma coleção) esteja dentro do intervalo definido pelos parâmetros `min` e/ou `max`. |
| Anotação de tipo (*type annotation*) | Recurso do Java (desde a versão 8) que permite anotar um uso específico de um tipo em qualquer posição onde ele apareça — inclusive dentro de um parâmetro genérico, como em `Optional<@Size(max = 500) String>` — em vez de apenas o campo ou variável inteiros. |
| `@Valid` | Anotação usada sobre um parâmetro de método (tipicamente combinada com `@RequestBody`) que instrui o Spring a acionar o mecanismo de Bean Validation sobre aquele objeto antes de o corpo do método ser executado, interrompendo o fluxo com uma exceção caso alguma regra seja violada. |
| `MethodArgumentNotValidException` | Exceção lançada automaticamente pelo Spring quando um argumento anotado com `@Valid` falha em uma ou mais regras de validação declaradas em seu tipo. |
| `BindingResult` | Objeto que acompanha uma `MethodArgumentNotValidException`, reunindo o resultado completo do processo de validação — quais campos foram checados e quais, entre eles, falharam, com suas respectivas mensagens. |
| `ObjectError` / `FieldError` | Classes do Spring que representam uma violação de validação; `ObjectError` é o tipo mais genérico (usado quando `getAllErrors()` devolve a lista de violações), e `FieldError` é uma subclasse mais específica, usada quando a violação está associada a um campo em particular, adicionando o método `getField()`. |
| `HashMap` | Implementação mais comum da interface `Map` em Java — uma estrutura de dados que associa chaves a valores, sem permitir chaves repetidas (já usada, desde o Vídeo 03, dentro do `InMemoryTaskRepository`). |
| Cast (conversão explícita de tipo) | Instrução explícita, escrita entre parênteses antes de uma expressão (ex.: `(FieldError) error`), que instrui o compilador a tratar uma referência de um tipo como se fosse de outro tipo, geralmente mais específico — lança `ClassCastException` em tempo de execução se o objeto real não for, de fato, compatível com o tipo do cast. |
| `@Repeatable` | Meta-anotação do Java que, quando presente na definição de uma anotação, permite que ela seja aplicada mais de uma vez sobre o mesmo elemento. Anotações do Spring como `@ExceptionHandler` e `@ResponseStatus` **não** são `@Repeatable` — aplicá-las duas vezes sobre o mesmo método é um erro de compilação. |

---

## Próximos passos: o que vem a partir do Vídeo 09

*(Esta seção substitui a versão anterior, "Próximos passos: o que vem a partir do Vídeo 08", removendo o vídeo já coberto por este documento.)*

Com a validação de dados de entrada funcionando para a criação de tarefas — e retornando mensagens de erro específicas por campo, através do `GlobalExceptionHandler` — a API ganha uma camada importante de robustez. Segundo o roteiro do curso (títulos já visíveis no README, ainda sem conteúdo detalhado na época em que este documento foi escrito), os próximos vídeos devem seguir esta sequência:

- **Vídeo 09 — Documentando a API:** deve apresentar uma ferramenta de documentação automática de APIs (comumente o Swagger/OpenAPI no ecossistema Spring), gerando uma interface visual para explorar os cinco endpoints já existentes — possivelmente já refletindo, nessa documentação, as regras de validação (`@NotBlank`, `@Size`) introduzidas neste vídeo, já que ferramentas como o springdoc-openapi costumam inspecionar automaticamente essas anotações.
- **Vídeo 10 — Evoluindo a API:** deve fechar o curso com melhorias adicionais sobre a base construída até aqui — possivelmente revisitando pontos já identificados neste tutorial, como a ausência de validação em `UpdateTaskRequest` (Seção 8.2 e nota final do checkpoint deste documento), a duplicação de anotações no `GlobalExceptionHandler` (Seção 8.5), ou a localização física de `UpdateTaskInput.java` (já observada desde o documento dos Vídeos 04/05).

---
