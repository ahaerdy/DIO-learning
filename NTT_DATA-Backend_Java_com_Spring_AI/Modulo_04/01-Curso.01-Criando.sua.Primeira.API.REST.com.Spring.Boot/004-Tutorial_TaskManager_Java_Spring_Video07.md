# Tutorial de Estudos — Construindo um Task Manager com Java e Spring Boot

**Criando sua primeira API REST — Vídeo 07**

- Curso: NTT Data — Jornada Tech (DIO) · Trilha Java + Spring
- Instrutor: Thiago Poiani
- Documento de referência pessoal — nível iniciante em Java
- Continuação de: *"Tutorial de Estudos — Construindo um Task Manager com Java e Spring Boot — Infraestrutura e interface — Vídeo 06"*, que por sua vez continua os documentos dos Vídeos 04 e 05, e dos Vídeos 01 a 03

---

## Sobre este documento

Este é o quarto capítulo do tutorial de estudos deste curso, e dá sequência direta ao documento anterior (Vídeo 06), que deixou o projeto com um único endpoint HTTP funcionando de ponta a ponta: `POST /tasks`, criando tarefas através do `CreateTaskUseCase`. Os outros quatro Use Cases da camada `application` — `GetTasksUseCase`, `GetTaskByIdUseCase`, `DeleteTaskUseCase` e `UpdateTaskUseCase`, todos completos desde a Parte 5 (Vídeo 05) — continuavam existindo apenas "por dentro" da aplicação, sem nenhuma rota HTTP que os expusesse ao mundo externo.

É exatamente essa lacuna que o Vídeo 07 fecha: os quatro Use Cases restantes são conectados a novos endpoints do `TaskController`, completando o CRUD (**C**reate, **R**ead, **U**pdate, **D**elete) da API de tarefas. Ao longo do caminho, aparecem três conceitos novos importantes — a anotação `@PathVariable`, para capturar valores diretamente da URL; a anotação `@RestControllerAdvice`, usada para centralizar o tratamento de exceções da aplicação inteira em uma única classe (`GlobalExceptionHandler`); e a anotação `@ResponseStatus`, usada para customizar o código de status HTTP de retorno de um endpoint.

Assim como os documentos anteriores, este foi escrito a partir das anotações de aula (README atualizado até o Vídeo 07) e conferido diretamente contra o código-fonte real do projeto nesta etapa (`taskmanager_ate_o_video_07.zip`) — novamente com destaque, ao longo do texto, para pontos em que a narrativa da aula e o código real divergem. **Desta vez, essa divergência não é cosmética**: como será detalhado na Seção 7.6 e reforçado no checkpoint, o `TaskController` entregue neste `.zip` contém um erro real no construtor, que impede três dos cinco endpoints de funcionar em tempo de execução — mesmo que a aula narre testes manuais bem-sucedidos para todos eles. Vale a pena ler a Seção 7.6 com atenção antes de assumir que o código deste checkpoint está pronto para ser usado como está.

---

## Parte 7 — Consulta de tarefas (Vídeo 07)

### 7.1. Listando todas as tarefas: o endpoint `GET /tasks`

Com o `CreateTaskUseCase` já conectado ao `TaskController` desde o Vídeo 06, o primeiro passo do Vídeo 07 é dar ao `GetTasksUseCase` (pronto desde a Parte 5, mas até aqui só acionado por testes) o mesmo tratamento: um método novo no controller, `list()`, anotado com `@GetMapping` e mapeado para a raiz do recurso (`/tasks`, herdada do `@RequestMapping("/tasks")` já presente na classe desde o Vídeo 06).

```java
@GetMapping
List<TaskResponse> list() {

}
```

- **`@GetMapping`** — o "irmão" de `@PostMapping` (já visto no Vídeo 06) para o verbo HTTP `GET`. Assim como `@PostMapping`, é uma especialização de `@RequestMapping(method = RequestMethod.GET)`: ao anotar um método com `@GetMapping`, diz-se ao Spring "toda requisição `GET` para este caminho deve ser tratada por este método aqui". Sem argumento algum (como neste caso), o caminho mapeado é exatamente o prefixo herdado da classe — ou seja, `GET /tasks`.
- **`List<TaskResponse> list()`** — diferente do `create()` (que devolve um único `TaskResponse`), este método devolve uma `List<TaskResponse>`: faz sentido, já que uma listagem pode ter zero, uma ou várias tarefas. O Jackson (biblioteca de serialização já apresentada no Vídeo 06) sabe converter automaticamente uma `List` de objetos Java em um *array* JSON (`[ {...}, {...} ]`), do mesmo jeito que já convertia um único objeto em um JSON simples.

### 7.2. Implementando o corpo de `list()` com `Stream`

O corpo do método é preenchido reaproveitando exatamente o mesmo padrão de conversão já usado dentro do próprio `GetTasksUseCase` (Parte 5) e do `TaskResponse` (Vídeo 06): transformar uma coleção em outra, elemento a elemento, usando a API de `Stream`.

```java
@GetMapping
List<TaskResponse> list() {
    return getTasksUseCase.execute().stream().map(TaskResponse::from).toList();
}
```

- **`getTasksUseCase.execute()`** — chama o Use Case já existente, que devolve uma `List<TaskOutput>` (o DTO de saída da camada de aplicação, não confundir com `TaskResponse`, o DTO de saída da camada HTTP).
- **`.stream()`** — como já visto no próprio `GetTasksUseCase` (Seção 5.1 do documento anterior), transforma a `List<TaskOutput>` em um `Stream<TaskOutput>`, uma sequência de elementos sobre a qual é possível encadear operações como `map` e `filter` sem alterar a coleção original.
- **`.map(TaskResponse::from)`** — para cada `TaskOutput` do stream, aplica o método estático `TaskResponse.from(...)` (já existente desde o Vídeo 06, usado até aqui apenas para converter o resultado do `create()`), produzindo um `Stream<TaskResponse>`. A sintaxe `TaskResponse::from` é uma **referência a método estático** (*method reference*), equivalente, em comportamento, a escrever `taskOutput -> TaskResponse.from(taskOutput)`, mas de forma mais enxuta.
- **`.toList()`** — encerra o stream, reunindo os elementos processados de volta em uma `List<TaskResponse>` comum — o tipo declarado como retorno do método.

Note que essa é literalmente a mesma sequência de operações (`stream().map(...).toList()`) já vista dentro do próprio `GetTasksUseCase`, só que operando sobre um tipo diferente (`TaskOutput → TaskResponse`, em vez de `Task → TaskOutput`). É um padrão que se repete bastante neste projeto: sempre que uma camada precisa converter uma coleção de um tipo interno para um tipo voltado à camada seguinte, a resposta é `stream().map(conversor).toList()`.

Com a aplicação reiniciada, o painel de *Endpoints* do IntelliJ passa a listar `/tasks [GET]` ao lado de `/tasks [POST]`, e uma requisição de teste (criando uma tarefa com título "123") confirma que a listagem funciona lado a lado com a criação.

### 7.3. Buscando uma tarefa específica: `@PathVariable` e o stub de `read()`

Com a listagem funcionando, o próximo passo é buscar **uma única** tarefa, a partir do seu identificador. Diferente da listagem (que não precisa de nenhuma informação adicional na URL), esse endpoint precisa saber *qual* tarefa buscar — e essa informação vem embutida na própria URL, seguindo o padrão REST: `GET /tasks/{id}`, em vez de, por exemplo, `GET /tasks?id=...`.

```java
@GetMapping("/{id}")
TaskResponse read(@PathVariable UUID id) {

}
```

- **`@GetMapping("/{id}")`** — desta vez, `@GetMapping` recebe um argumento: um trecho de caminho com um segmento entre chaves, `{id}`. Esse segmento é um **placeholder** (um "espaço reservado"): qualquer valor que apareça naquela posição da URL (por exemplo, `/tasks/82f2-...`) é capturado, e fica disponível para ser lido dentro do método.
- **`@PathVariable`** — é a anotação que efetivamente faz essa captura acontecer. Colocada sobre um parâmetro do método, ela instrui o Spring a pegar o valor correspondente extraído da URL (no caso, o segmento capturado por `{id}`) e convertê-lo automaticamente para o tipo declarado do parâmetro.
  > **`@PathVariable` vs. *query parameter*:** existem duas formas comuns de uma URL carregar informação além do caminho fixo. Um *path variable* (como `/tasks/82f2-...`) é parte do próprio caminho, geralmente usado para **identificar um recurso específico** — é o caso aqui, buscando uma tarefa por ID. Um *query parameter* (como `/tasks?status=done`) vem depois de uma interrogação (`?`), geralmente usado para **filtrar ou parametrizar** uma busca mais ampla — por exemplo, listar apenas tarefas com um determinado status. O Spring tem uma anotação equivalente para esse segundo caso, `@RequestParam`, mas ela ainda não aparece neste projeto.
- **`UUID id`** — o tipo do parâmetro é declarado diretamente como `UUID` (a mesma classe usada internamente por `TaskId`, vista desde a Parte 2), e não como `String`. O Spring é capaz de fazer essa conversão automaticamente: o texto capturado na URL é interpretado e transformado em um `java.util.UUID` antes mesmo do corpo do método rodar. Se o texto recebido não for um UUID válido, o Spring já rejeita a requisição antes de chegar ao código do desenvolvedor.

### 7.4. Injetando o `GetTaskByIdUseCase` e implementando `read()`

Para que o método `read()` funcione, o controller passa a depender de mais um caso de uso: o `GetTaskByIdUseCase` (também pronto desde a Parte 5), seguindo o mesmo padrão de injeção de dependência via construtor já estabelecido para `createTaskUseCase` e `getTasksUseCase`.

```java
@GetMapping("/{id}")
TaskResponse read(@PathVariable UUID id) {
    var output = getTaskByIdUseCase.execute(new TaskId(id));
    return TaskResponse.from(output);
}
```

- **`new TaskId(id)`** — o `UUID` puro, capturado pelo `@PathVariable`, é envolvido pelo `record TaskId` (visto desde a Parte 2) antes de ser passado ao Use Case, já que a assinatura de `GetTaskByIdUseCase.execute(...)` espera um `TaskId`, e não um `UUID` bruto. Isso mantém a camada de aplicação (e o domínio) protegida de detalhes específicos da camada HTTP: o controller é quem sabe traduzir "um segmento de URL" em "um identificador de domínio", e não o contrário.
- **`getTaskByIdUseCase.execute(...)`** — devolve um `TaskOutput`, exatamente como os demais Use Cases já vistos.
- **`TaskResponse.from(output)`** — reaproveita, mais uma vez, o mesmo método estático de conversão já usado em `create()` e `list()`.

Com a aplicação reiniciada, o painel de *Endpoints* passa a exibir também `/tasks/{id} [GET]`. Testando com um UUID aleatório (ou seja, um ID que não existe na base em memória), a requisição retorna um erro **500 Internal Server Error** — e, no console, os logs revelam que a causa é uma `TaskNotFoundException` não tratada. Esse é o comportamento padrão do Spring: qualquer exceção lançada durante o processamento de uma requisição e que não seja explicitamente tratada resulta em uma resposta genérica de erro do servidor, independentemente de a causa real ser "culpa do cliente" (pedir algo que não existe) ou "culpa do servidor" (um bug de verdade). Do ponto de vista de uma API REST, um recurso não encontrado deveria retornar **404 Not Found**, não **500** — e é isso que a próxima seção resolve.

### 7.5. Tratando exceções de forma centralizada: `GlobalExceptionHandler`

Para transformar aquele 500 genérico em um 404 mais preciso, é criada uma nova classe dentro do pacote `http`: `GlobalExceptionHandler`. Diferente das classes vistas até aqui, ela não representa um recurso REST específico — sua responsabilidade é **interceptar exceções lançadas por qualquer controller** da aplicação, convertendo-as em respostas HTTP apropriadas antes que o Spring aplique seu comportamento padrão (o 500 genérico).

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

}
```

> **O que é `@RestControllerAdvice`?**
> É uma anotação de escopo global: uma classe anotada com `@RestControllerAdvice` não atende a nenhuma rota diretamente (não tem `@RequestMapping` nem métodos com `@GetMapping`/`@PostMapping`), mas se registra como um "assistente" de todos os `@RestController`s da aplicação. Sempre que um método de qualquer controller lança uma exceção, o Spring primeiro verifica se existe, em alguma classe anotada com `@RestControllerAdvice`, um método capaz de tratar aquele tipo específico de exceção — e, se existir, delega o tratamento para ele, em vez de simplesmente devolver um 500. O nome é uma combinação de duas ideias: `@ControllerAdvice` (o conceito original do Spring MVC, para tratamento centralizado de exceções em controllers "tradicionais") mais `@RestController` (a versão focada em APIs REST, cujos métodos devolvem dados diretamente no corpo da resposta, como já visto no Vídeo 06). Assim como `@RestController`, essa anotação também registra a classe como um bean gerenciado pelo Spring.

Dentro dessa classe, é implementado um método especificamente para tratar a `TaskNotFoundException`:

```java
@ExceptionHandler(TaskNotFoundException.class)
@ResponseStatus(HttpStatus.NOT_FOUND)
public String handleTaskNotFoundException(TaskNotFoundException ex) {
    return ex.getMessage();
}
```

- **`@ExceptionHandler(TaskNotFoundException.class)`** — declara que este método é o responsável por tratar exceções do tipo `TaskNotFoundException` (a exceção customizada criada no domínio desde a Parte 5) sempre que uma delas for lançada por qualquer controller da aplicação. O parâmetro do método, `TaskNotFoundException ex`, recebe a instância exata da exceção que foi lançada — o mesmo objeto criado, por exemplo, dentro de `GetTaskByIdUseCase.execute(...)`, quando o `orElseThrow(() -> new TaskNotFoundException(id))` é acionado.
- **`@ResponseStatus(HttpStatus.NOT_FOUND)`** — é essa anotação que efetivamente muda o código de status da resposta HTTP. Sem ela, mesmo com a exceção sendo "tratada" por este método, o Spring ainda responderia com o status padrão de sucesso (200); com ela, a resposta passa a ser **404 Not Found** — o código correto, em REST, para "o recurso pedido não existe".
  > **`HttpStatus`** é um `enum` da própria biblioteca do Spring, que reúne todos os códigos de status HTTP padronizados (200, 201, 204, 400, 404, 500, e por aí vai) como constantes nomeadas — evitando que o desenvolvedor precise escrever (e arriscar errar) o número mágico `404` diretamente no código.
- **`return ex.getMessage()`** — o método devolve uma `String`, que vira o corpo da resposta HTTP (graças a `@RestControllerAdvice`, que, assim como `@RestController`, serializa automaticamente o retorno do método como corpo da resposta). `getMessage()` é um método herdado de `RuntimeException` (e, por trás dela, de `Throwable`, a superclasse de todo erro e exceção em Java), que devolve o texto passado ao `super(...)` no construtor da exceção — no caso de `TaskNotFoundException`, algo como `"Task with id ... not found"`.

Repetindo o teste com o mesmo ID inexistente de antes, a resposta agora vem com o código **404**, trazendo a mensagem da exceção como corpo — exatamente o comportamento esperado para um recurso inexistente em uma API REST, e sem que o `GetTaskByIdUseCase` (ou qualquer outra parte do domínio) precise saber nada sobre HTTP: a tradução de "exceção de domínio" para "código de status HTTP" fica isolada dentro do `GlobalExceptionHandler`, na camada de infraestrutura.

### 7.6. Removendo uma tarefa: `@DeleteMapping` e `@ResponseStatus` sem corpo

Com a busca por ID funcionando (e devidamente tratada para o caso de erro), o próximo Use Case a ganhar um endpoint é o `DeleteTaskUseCase`, injetado no `TaskController` seguindo o mesmo padrão de injeção via construtor já usado para os demais casos de uso.

```java
@DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
void delete(@PathVariable UUID id) {
    deleteTaskUseCase.execute(new TaskId(id));
}
```

- **`@DeleteMapping("/{id}")`** — o equivalente, para o verbo `DELETE`, do `@GetMapping("/{id}")` já visto na Seção 7.3: mapeia o método para `DELETE /tasks/{id}`, capturando o identificador da tarefa a ser removida da mesma forma, via `@PathVariable`.
- **`void delete(...)`** — diferente de todos os métodos anteriores, este não devolve nenhum valor (`void`). Faz sentido: uma operação de remoção bem-sucedida não tem, semanticamente, nenhum dado novo para devolver ao cliente — a tarefa simplesmente deixou de existir.
- **`@ResponseStatus(HttpStatus.NO_CONTENT)`** — por padrão, um método que responde com sucesso (sem lançar exceção) e não usa nenhuma anotação de status devolveria o código **200 OK**. Só que, para uma operação que não devolve corpo algum, a convenção REST recomenda o código **204 No Content**: "deu certo, e propositalmente não há nada para te mostrar". É essa anotação que faz o Spring responder com 204 em vez do 200 padrão.
- **`deleteTaskUseCase.execute(new TaskId(id))`** — chama o Use Case (que já lança `TaskNotFoundException`, tratada pelo `GlobalExceptionHandler` da Seção 7.5, caso o ID não exista), sem capturar nenhum retorno, já que `DeleteTaskUseCase.execute(...)` também é `void`.

> **Nota sobre o código-fonte real desta etapa — divergência importante:** a narrativa da aula descreve testes manuais bem-sucedidos para busca por ID, exclusão e (na próxima seção) atualização. Conferindo, porém, o construtor real do `TaskController` entregue no `.zip` desta etapa, ele foi escrito assim:
> ```java
> public TaskController(CreateTaskUseCase createTaskUseCase, GetTasksUseCase getTasksUseCase) {
>     this.createTaskUseCase = createTaskUseCase;
>     this.getTasksUseCase = getTasksUseCase;
>     this.getTaskByIdUseCase = getTaskByIdUseCase;
>     this.deleteTaskUseCase = deleteTaskUseCase;
>     this.updateTaskUseCase = updateTaskUseCase;
> }
> ```
> Repare que o construtor **só declara dois parâmetros** (`createTaskUseCase` e `getTasksUseCase`), mas o corpo tenta atribuir cinco campos. Nas três últimas linhas, como não existe nenhum parâmetro chamado `getTaskByIdUseCase`, `deleteTaskUseCase` ou `updateTaskUseCase` naquele escopo, o Java resolve cada nome do lado direito do `=` como referência ao **próprio campo da classe** — ou seja, `this.getTaskByIdUseCase = getTaskByIdUseCase;` equivale, na prática, a `this.getTaskByIdUseCase = this.getTaskByIdUseCase;`. Isso **compila normalmente** (campos de instância sempre começam com um valor padrão, `null` no caso de referências, mesmo quando são `final`, então não há erro de "variável não inicializada"), mas o efeito é que esses três campos nunca recebem, de fato, os beans reais criados pelo Spring — eles permanecem `null` durante toda a vida da aplicação.
>
> Na prática, isso significa que, com o código exatamente como está neste `.zip`, os endpoints `read()`, `delete()` e `update()` — todos os três construídos nesta mesma etapa — lançariam um `NullPointerException` assim que fossem chamados (por exemplo, `getTaskByIdUseCase.execute(...)`, dentro de `read()`, falharia porque `getTaskByIdUseCase` é `null`). Isso contradiz diretamente a sequência de testes manuais bem-sucedidos narrada no README para esta mesma etapa (Seções 7.3 a 7.9 deste documento). Tudo indica que, durante a gravação da aula, o construtor foi de fato atualizado corretamente (com os cinco parâmetros) — o que explicaria os testes funcionando ao vivo —, e que esta versão específica, capturada no `.zip` entregue, ficou para trás em algum momento da edição, sem os três parâmetros novos. O conserto é simples e seguro (adicionar os três parâmetros que faltam à assinatura do construtor, seguindo exatamente o mesmo padrão já usado para os dois primeiros):
> ```java
> public TaskController(CreateTaskUseCase createTaskUseCase, GetTasksUseCase getTasksUseCase,
>                        GetTaskByIdUseCase getTaskByIdUseCase, DeleteTaskUseCase deleteTaskUseCase,
>                        UpdateTaskUseCase updateTaskUseCase) {
>     this.createTaskUseCase = createTaskUseCase;
>     this.getTasksUseCase = getTasksUseCase;
>     this.getTaskByIdUseCase = getTaskByIdUseCase;
>     this.deleteTaskUseCase = deleteTaskUseCase;
>     this.updateTaskUseCase = updateTaskUseCase;
> }
> ```
> Vale a pena aplicar esse ajuste no seu projeto antes de seguir para o Vídeo 08 — o restante do código (todos os métodos do controller, o `GlobalExceptionHandler`, os DTOs) está correto e não precisa de nenhuma outra alteração. Este ponto é retomado, com mais detalhes de organização de arquivos, no checkpoint ao final desta parte.

### 7.7. Atualizando uma tarefa: o verbo `PATCH` e o DTO `UpdateTaskRequest`

O último Use Case a ganhar um endpoint é o `UpdateTaskUseCase`. A escolha de verbo HTTP aqui é o `PATCH`, e não o `PUT` — ambos representam "atualização" em REST, mas com uma diferença semântica importante (já adiantada, em teoria, no documento do Vídeo 05): `PUT` normalmente implica substituir o recurso inteiro, enquanto `PATCH` implica uma atualização parcial, exatamente o comportamento que o `UpdateTaskUseCase` (e o `UpdateTaskInput`, com todos os campos `Optional`) já foi desenhado para suportar desde a Parte 5.

```java
@PatchMapping("/{id}")
TaskResponse update(@PathVariable UUID id, @RequestBody UpdateTaskRequest request) {

}
```

- **`@PatchMapping("/{id}")`** — mais um "irmão" de `@GetMapping`/`@PostMapping`/`@DeleteMapping`, desta vez para o verbo `PATCH`, combinando a captura de ID via `@PathVariable` (Seção 7.3) com o recebimento de um corpo de requisição via `@RequestBody` (já visto no Vídeo 06, para o `create()`).
- **`UpdateTaskRequest request`** — assim como `CreateTaskRequest` (Vídeo 06) é o DTO específico da camada HTTP para a operação de criação, `UpdateTaskRequest` é o equivalente para a atualização — ainda não criado neste ponto da aula.

Uma nova classe, `UpdateTaskRequest`, é criada como um `record`, seguindo o mesmo padrão dos demais DTOs da camada HTTP:

```java
public record UpdateTaskRequest(
        Optional<String> title,
        Optional<String> description,
        Optional<String> status
) {
    public UpdateTaskInput toInput() {
        return new UpdateTaskInput(title, description, status.map(TaskStatus::valueOf));
    }
}
```

- **`Optional<String> title, description, status`** — assim como o `UpdateTaskInput` da camada de aplicação (Parte 5), todos os três campos são opcionais: o cliente da API pode enviar apenas os campos que deseja alterar, e o `Optional` vazio, nos campos omitidos, sinaliza "não mexa nisso".
- **Por que `status` é `Optional<String>`, e não `Optional<TaskStatus>`?** — assim como já visto para `CreateTaskRequest` no Vídeo 06 (onde os campos vêm sempre como tipos "primitivos" do JSON, como `String`), o corpo de uma requisição HTTP não tem como carregar diretamente um `enum` Java — ele chega como texto puro (por exemplo, `"COMPLETED"`). É papel do DTO da camada HTTP fazer essa tradução para o tipo interno correto.
- **`toInput()`** — reaproveita o mesmo nome e a mesma ideia já vista em `CreateTaskRequest.toInput()` (Vídeo 06): converter o DTO da camada HTTP (`UpdateTaskRequest`) para o DTO da camada de aplicação (`UpdateTaskInput`), que é o que o `UpdateTaskUseCase` realmente espera receber.
- **`status.map(TaskStatus::valueOf)`** — o ponto novo desta conversão: como `status` é um `Optional<String>` (e não um `String` puro, como aconteceria em um DTO de criação obrigatório), a conversão de texto para `enum` não pode ser feita diretamente com `TaskStatus.valueOf(status)` — seria necessário primeiro verificar se o `Optional` está presente. O método `map(...)` do `Optional` resolve isso de forma elegante: se o `Optional<String>` estiver presente, aplica a função `TaskStatus::valueOf` sobre o valor dentro dele, devolvendo um novo `Optional<TaskStatus>` também presente; se estiver vazio, simplesmente devolve um `Optional<TaskStatus>` vazio, sem executar `valueOf` (evitando, por exemplo, uma tentativa de converter `null` em `enum`). É o mesmo raciocínio do `.map()` de `Stream`, já visto nas Seções 7.1 e 7.2, só que aplicado a um `Optional` (que pode ser entendido como "uma coleção de zero ou um elemento") em vez de uma coleção com vários elementos.
  > **`TaskStatus::valueOf`** é outra referência a método — desta vez, a um método especial que todo `enum` em Java ganha automaticamente do compilador: `valueOf(String nome)`, que devolve a constante do enum cujo nome (como texto) seja exatamente igual ao argumento recebido (por exemplo, `TaskStatus.valueOf("COMPLETED")` devolve `TaskStatus.COMPLETED`). Se o texto não corresponder a nenhuma constante existente, esse método lança uma `IllegalArgumentException` — um detalhe que, por ora, ainda não recebe nenhum tratamento específico nesta etapa do projeto (voltará a ser relevante quando o assunto for validação de dados, no Vídeo 08).

### 7.8. Injetando o `UpdateTaskUseCase` e finalizando `update()`

De volta ao `TaskController`, o `UpdateTaskUseCase` é declarado como mais um campo (seguindo o mesmo padrão de injeção via construtor dos demais casos de uso), e o corpo do método `update()` é finalmente implementado:

```java
@PatchMapping("/{id}")
TaskResponse update(@PathVariable UUID id, @RequestBody UpdateTaskRequest request) {
    var input = request.toInput();
    var output = updateTaskUseCase.execute(new TaskId(id), input);
    return TaskResponse.from(output);
}
```

- **`request.toInput()`** — converte o `UpdateTaskRequest` (DTO da camada HTTP) em um `UpdateTaskInput` (DTO da camada de aplicação), exatamente como visto na Seção 7.7.
- **`updateTaskUseCase.execute(new TaskId(id), input)`** — chama o Use Case, passando tanto o identificador da tarefa (extraído da URL, como nos demais endpoints) quanto o DTO com os dados a atualizar.
- **`TaskResponse.from(output)`** — mais uma vez, o mesmo padrão de conversão de saída já repetido em todos os outros endpoints deste controller.

Com isso, todos os cinco mapeamentos de rota da API ficam presentes no código-fonte: criar (`POST`), listar (`GET`), buscar por ID (`GET /{id}`), atualizar (`PATCH /{id}`) e remover (`DELETE /{id}`) — ainda que, como detalhado na Seção 7.6, três desses cinco dependam de uma correção no construtor do controller para de fato funcionarem em tempo de execução no código exatamente como entregue neste `.zip`.

### 7.9. Teste manual completo do CRUD (conforme narrado na aula)

Com a aplicação reiniciada, o painel de *Endpoints* passa a listar as cinco rotas: `/tasks [GET]`, `/tasks [POST]`, `/tasks/{id} [GET]`, `/tasks/{id} [PATCH]` e `/tasks/{id} [DELETE]`. A aula então narra uma sequência de testes manuais cobrindo o ciclo de vida completo de uma tarefa:

1. Um `GET /tasks` inicial, que retorna uma lista vazia.
2. Um `POST /tasks` criando uma tarefa chamada "ABC", sem descrição — a resposta não traz o campo `description`, graças ao `@JsonInclude(JsonInclude.Include.NON_ABSENT)` já configurado em `TaskResponse` desde o Vídeo 06.
3. Um `GET /tasks/{id}` usando o ID da tarefa recém-criada, confirmando que ela é encontrada.
4. Um `PATCH /tasks/{id}`, mantendo o título, adicionando uma descrição e alterando o status para `"COMPLETED"` — a resposta confirma que só os campos enviados foram alterados.
5. Um `DELETE /tasks/{id}`, respondendo **204** sem corpo, seguido de um novo `GET /tasks`, que volta a mostrar uma lista vazia, confirmando a remoção.

> **Lembrete:** conforme detalhado na Seção 7.6, os passos 3, 4 e 5 dessa sequência dependem do construtor do `TaskController` receber corretamente os cinco Use Cases — o que só acontece se o pequeno ajuste sugerido naquela seção for aplicado ao construtor entregue neste `.zip`. Com esse ajuste, a sequência de testes descrita aqui funciona exatamente como narrado.

---

### ✅ Checkpoint — Estado do projeto ao final do Vídeo 07

Conferido diretamente contra o código-fonte real entregue em `taskmanager_ate_o_video_07.zip`.

**Estrutura de pastas (alterações em relação ao checkpoint do Vídeo 06, em negrito)**

```
taskmanager/
├── build.gradle                                        (sem alterações desde o Vídeo 06)
└── src/
    ├── main/java/dio/taskmanager/
    │   ├── TaskmanagerApplication.java                  (sem alterações)
    │   ├── application/                                 (sem alterações nesta etapa — todos os 5 Use Cases já prontos desde a Parte 5)
    │   ├── domain/                                       (sem alterações nesta etapa)
    │   ├── infrastructure/
    │   │   ├── http/
    │   │   │   ├── TaskController.java                    (alterado — 4 novos métodos: list, read, delete, update ⚠️ ver nota abaixo)
    │   │   │   ├── GlobalExceptionHandler.java             ← novo
    │   │   │   ├── request/
    │   │   │   │   ├── CreateTaskRequest.java               (sem alterações)
    │   │   │   │   └── UpdateTaskRequest.java               ← novo
    │   │   │   └── response/
    │   │   │       └── TaskResponse.java                   (sem alterações)
    │   │   └── repository/
    │   │       └── InMemoryTaskRepository.java            (sem alterações)
    │   └── playground/
    │       └── Main.java                                  (sem alterações)
    └── test/java/dio/taskmanager/                          (sem alterações nesta etapa)
```

**TaskController.java (versão final desta etapa — conteúdo exato do arquivo real, incluindo o erro do construtor)**

```java
package dio.taskmanager.infrastructure.http;

import dio.taskmanager.application.*;
import dio.taskmanager.domain.TaskId;
import dio.taskmanager.infrastructure.http.request.CreateTaskRequest;
import dio.taskmanager.infrastructure.http.request.UpdateTaskRequest;
import dio.taskmanager.infrastructure.http.response.TaskResponse;
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


    public TaskController(CreateTaskUseCase createTaskUseCase, GetTasksUseCase getTasksUseCase) {
        this.createTaskUseCase = createTaskUseCase;
        this.getTasksUseCase = getTasksUseCase;
        this.getTaskByIdUseCase = getTaskByIdUseCase;
        this.deleteTaskUseCase = deleteTaskUseCase;
        this.updateTaskUseCase = updateTaskUseCase;

    }

    @PostMapping
    TaskResponse create(@RequestBody CreateTaskRequest request) {
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

> ⚠️ **Repetindo, de forma resumida, o ponto já detalhado na Seção 7.6:** o construtor acima declara apenas dois parâmetros (`createTaskUseCase`, `getTasksUseCase`), mas o corpo tenta inicializar cinco campos. As três últimas atribuições acabam sendo, na prática, `campo = campo` (o valor padrão `null` sendo atribuído a si mesmo) — o código **compila sem erros**, mas `getTaskByIdUseCase`, `deleteTaskUseCase` e `updateTaskUseCase` nunca são de fato preenchidos pelo Spring. Isso quer dizer que, rodando a aplicação exatamente como este `.zip` entrega, os três métodos que dependem desses campos (`read()`, `delete()` e `update()`) lançam `NullPointerException` ao serem chamados, mesmo que o restante do código (mapeamento de rota, `@PathVariable`, `@RequestBody`, conversão para `TaskResponse`) esteja correto. A correção é adicionar os três parâmetros que faltam à assinatura do construtor — ver o bloco de código completo na Seção 7.6.
>
> Vale notar que esse tipo de inconsistência (código correto sendo digitado durante a aula, mas uma versão anterior ou incompleta acabando exportada para o `.zip` de apoio) já não é inédito neste curso — o Vídeo 06 já havia revelado pequenos resíduos de edição, como imports não utilizados (ver checkpoint daquele vídeo). A diferença, desta vez, é que o resíduo tem efeito prático real (uma exceção em tempo de execução), e não é apenas cosmético — por isso o destaque maior dado a ele neste documento.

**GlobalExceptionHandler.java (versão final desta etapa)** — exatamente como mostrado na Seção 7.5, sem diferenças em relação ao código-fonte real.

```java
package dio.taskmanager.infrastructure.http;

import dio.taskmanager.domain.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleTaskNotFoundException(TaskNotFoundException ex) {
        return ex.getMessage();
    }
}
```

**UpdateTaskRequest.java (versão final desta etapa)** — exatamente como mostrado na Seção 7.7, sem diferenças em relação ao código-fonte real.

```java
package dio.taskmanager.infrastructure.http.request;

import dio.taskmanager.application.input.UpdateTaskInput;
import dio.taskmanager.domain.TaskStatus;

import java.util.Optional;

public record UpdateTaskRequest(
        Optional<String> title,
        Optional<String> description,
        Optional<String> status
) {
    public UpdateTaskInput toInput() {
        return new UpdateTaskInput(title, description, status.map(TaskStatus::valueOf));

    }
}
```

**Arquivos sem nenhuma alteração nesta etapa:** todos os cinco Use Cases (`CreateTaskUseCase`, `GetTasksUseCase`, `GetTaskByIdUseCase`, `DeleteTaskUseCase`, `UpdateTaskUseCase`), toda a camada `domain` (`Task`, `TaskId`, `TaskStatus`, `TaskRepository`, `TaskNotFoundException`), `InMemoryTaskRepository`, `TaskmanagerApplication`, `CreateTaskRequest.java`, `TaskResponse.java`, `build.gradle`, `Main.java` (o *playground*) e todos os arquivos de teste existentes — incluindo `UpdateTaskInput.java`, que, como já observado no checkpoint do documento anterior (Vídeos 04/05), continua fisicamente localizado dentro de `application/` em vez de `application/input/`, apesar de declarar o pacote `dio.taskmanager.application.input` (uma inconsistência de organização já existente, sem relação com o Vídeo 07, e sem efeito prático sobre a compilação).

> **Nota importante sobre o alcance desta etapa:** apesar do CRUD estar, na intenção da aula, completo desde este vídeo (criação, listagem, busca por ID, atualização e remoção, todos com o código exatamente narrado nas seções 7.1 a 7.9), o `.zip` real entregue nesta etapa só está de fato funcional para `create()` e `list()` — os outros três endpoints exigem a correção do construtor descrita na Seção 7.6 e reforçada acima. Também não há, nesta etapa, nenhum teste automatizado cobrindo `TaskController`, `GlobalExceptionHandler` ou `UpdateTaskRequest` — como no Vídeo 06, os testes seguem sendo apenas manuais, via HTTP Client do IntelliJ.

---

## Glossário de conceitos Java e Spring — complemento do Vídeo 07

Continuação dos glossários dos documentos anteriores — apenas os termos novos, introduzidos neste vídeo.

| Termo | Significado |
|---|---|
| `@PathVariable` | Anotação usada sobre um parâmetro de método, que instrui o Spring a capturar um valor diretamente de um segmento da URL (declarado entre chaves no `@GetMapping`/`@PatchMapping`/etc., ex.: `"/{id}"`) e convertê-lo automaticamente para o tipo declarado do parâmetro. |
| Path variable | O próprio segmento de URL capturado por `@PathVariable` — usado, por convenção REST, para identificar um recurso específico (ex.: `/tasks/{id}`), diferente de um *query parameter*, usado para filtrar ou parametrizar uma busca. |
| `@RestControllerAdvice` | Anotação que registra uma classe como um "assistente global" de todos os `@RestController`s da aplicação, capaz de interceptar e tratar exceções lançadas por qualquer um deles, evitando o comportamento padrão de erro genérico (500) do Spring. |
| `@ExceptionHandler(TipoDaExceção.class)` | Anotação usada sobre um método (tipicamente dentro de uma classe `@RestControllerAdvice`) para declarar que ele é o responsável por tratar exceções de um tipo específico, recebendo a instância da exceção lançada como parâmetro. |
| `@ResponseStatus(HttpStatus.X)` | Anotação usada sobre um método (ou classe) para customizar o código de status HTTP da resposta, sobrescrevendo o padrão (200 para sucesso). |
| `HttpStatus` | `enum` da biblioteca do Spring que reúne os códigos de status HTTP padronizados (ex.: `NOT_FOUND` para 404, `NO_CONTENT` para 204) como constantes nomeadas. |
| `404 Not Found` | Código de status HTTP da faixa 4xx (erro do cliente), indicando que o recurso solicitado não existe. |
| `204 No Content` | Código de status HTTP da faixa 2xx (sucesso), indicando que a operação deu certo mas, propositalmente, não há nenhum corpo de resposta a devolver — comum em operações de remoção. |
| `@DeleteMapping` / `@PatchMapping` | Especializações de `@RequestMapping`, junto de `@GetMapping`/`@PostMapping` (já vistos no Vídeo 06), para mapear métodos aos verbos HTTP `DELETE` e `PATCH`, respectivamente. |
| `PATCH` (verbo HTTP) | Verbo HTTP usado, por convenção, para representar uma **atualização parcial** de um recurso — o cliente envia apenas os campos que deseja alterar, e os demais permanecem inalterados. Contrasta com `PUT`, usado para representar uma substituição completa do recurso. |
| Method reference (`Classe::metodo`) | Sintaxe do Java (já usada desde a Parte 5 com `TaskOutput::from`, e retomada neste vídeo com `TaskResponse::from` e `TaskStatus::valueOf`) que referencia um método existente para ser usado como implementação de uma interface funcional (como o `Function` esperado por `Stream.map` ou `Optional.map`), equivalente a uma expressão lambda que apenas chama aquele método. |
| `Optional.map(função)` | Método do `Optional` (visto anteriormente apenas com `Stream`) que, se o `Optional` estiver presente, aplica a função recebida sobre o valor contido nele e devolve um novo `Optional` (também presente) com o resultado; se estiver vazio, devolve um `Optional` vazio sem executar a função. |
| `Enum.valueOf(String)` | Método gerado automaticamente pelo compilador para todo `enum` em Java, que devolve a constante cujo nome (como texto) seja exatamente igual ao argumento recebido — lança `IllegalArgumentException` se nenhuma constante corresponder. |

---

## Próximos passos: o que vem a partir do Vídeo 08

*(Esta seção substitui a versão anterior, "Próximos passos: o que vem a partir do Vídeo 07", removendo o vídeo já coberto por este documento.)*

Com o CRUD completo — ainda que, como detalhado nas Seções 7.6 e no checkpoint, dependente de um pequeno ajuste no construtor do `TaskController` para funcionar como narrado — a API de tarefas já expõe todas as operações básicas: criar, listar, buscar por ID, atualizar e remover. Segundo o roteiro do curso (títulos já visíveis no README, ainda sem conteúdo detalhado na época em que este documento foi escrito), os próximos vídeos devem seguir esta sequência:

- **Vídeo 08 — Validando dados:** deve introduzir validações automáticas de entrada (por exemplo, com anotações do Bean Validation, como `@NotBlank` ou `@Valid`), tratando respostas de erro (`400 Bad Request`) para dados inválidos — como criar uma tarefa sem título, ou enviar um valor de `status` que não corresponda a nenhuma constante de `TaskStatus` (hoje, esse segundo caso provavelmente resultaria em outro 500 não tratado, já que apenas `TaskNotFoundException` tem um `@ExceptionHandler` dedicado até este ponto).
- **Vídeo 09 — Documentando a API:** deve apresentar uma ferramenta de documentação automática de APIs (comumente o Swagger/OpenAPI no ecossistema Spring), gerando uma interface visual para explorar os cinco endpoints já existentes.
- **Vídeo 10 — Evoluindo a API:** deve fechar o curso com melhorias adicionais sobre a base construída até aqui — possivelmente revisitando pontos já identificados neste tutorial, como o construtor incompleto do `TaskController` (Seção 7.6) ou a localização física de `UpdateTaskInput.java` (já observada desde o documento dos Vídeos 04/05).

---
