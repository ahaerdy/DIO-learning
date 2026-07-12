# Tutorial de Estudos — Construindo um Task Manager com Java e Spring Boot

**Criando sua primeira API REST — Vídeo 06**

- Curso: NTT Data — Jornada Tech (DIO) · Trilha Java + Spring
- Instrutor: Thiago Poiani
- Documento de referência pessoal — nível iniciante em Java
- Continuação de: *"Tutorial de Estudos — Construindo um Task Manager com Java e Spring Boot — Da camada de aplicação à listagem de tarefas — Vídeos 04 e 05"*, que por sua vez continua *"...Do zero à modelagem de domínio — Vídeos 01 a 03"*

---

## Sobre este documento

Este é o terceiro capítulo do tutorial de estudos deste curso, e dá sequência direta ao documento anterior (Vídeos 04 e 05), que deixou o projeto com toda a camada `application` pronta: cinco Use Cases (`CreateTaskUseCase`, `GetTasksUseCase`, `GetTaskByIdUseCase`, `DeleteTaskUseCase`, `UpdateTaskUseCase`) capazes de orquestrar o domínio — mas ainda **inacessíveis de fora da aplicação**. Até o final do Vídeo 05, não existia nenhuma forma de chamar esse código a não ser por um teste ou pelo depurador; não havia nenhum servidor HTTP rodando, nem qualquer endpoint exposto.

É exatamente essa lacuna que o Vídeo 06 fecha: pela primeira vez no curso, a aplicação passa a subir um **servidor web de verdade** (Tomcat, embutido pelo Spring Boot) e a responder a requisições HTTP reais, através de um `TaskController`. É também a primeira vez que os conceitos de REST apresentados lá no Vídeo 01 — verbos HTTP, JSON como formato de troca de dados — deixam de ser teoria e viram código funcionando de ponta a ponta: uma requisição `POST /tasks` chega ao controller, é convertida em um DTO, atravessa o `CreateTaskUseCase` já existente, é persistida no repositório e volta ao cliente como uma resposta JSON.

Assim como os documentos anteriores, este foi escrito a partir das anotações de aula (README atualizado até o Vídeo 06) e conferido diretamente contra o código-fonte real do projeto nesta etapa — novamente com destaque, ao longo do texto, para pequenos pontos em que a narrativa da aula e o código real divergem em algum detalhe.

> **Como este documento está organizado**
> Mesmo formato dos documentos anteriores: o código é apresentado em pequenos blocos, na ordem em que foi escrito na aula, seguido de uma explicação linha a linha; ao final do vídeo, um checkpoint mostra o estado real dos arquivos criados e alterados nesta etapa, conferido diretamente no `.zip` entregue. Ao final deste documento, um glossário complementar reúne apenas os termos novos introduzidos no Vídeo 06, e a seção de "Próximos passos" é atualizada mais uma vez.

> **Nota de concatenação:** este arquivo é a continuação direta de `002-Tutorial_TaskManager_Java_Spring_Videos04a05.md`. Para juntar os três documentos em um único arquivo: cole o conteúdo abaixo **logo após** a seção "Próximos passos: o que vem a partir do Vídeo 06" do documento anterior (ou substitua aquela seção pela versão atualizada de "Próximos passos" que está no final deste arquivo). O glossário e o checkpoint deste arquivo são complementares aos dos documentos anteriores — não os substituem, apenas os estendem.

---

## Parte 6 — Infraestrutura e interface (Vídeo 06)

### 6.1. Da camada de aplicação à camada HTTP: o pacote `http`

Com a camada `application` completa (Parte 5), falta agora dar ao mundo exterior um jeito de acionar esses Use Cases. Essa responsabilidade — receber requisições HTTP, entender o que o cliente está pedindo, e traduzir isso em chamadas para a camada de aplicação — não é uma regra de negócio do domínio de tarefas; é um detalhe de **como** a aplicação é acessada. Por isso, seguindo os mesmos princípios de Domain-Driven Design já vistos desde a Parte 2 (separação em `domain`, `application` e `infrastructure`), esse código é isolado dentro de um novo subpacote:

```text
dio.taskmanager.infrastructure.http
```

Repare que ele nasce como um "irmão" de `infrastructure.repository` (já existente desde a Parte 3): os dois representam formas diferentes de a aplicação conversar com o mundo externo — um com um banco de dados (ou, por enquanto, um `HashMap` em memória), o outro com um cliente HTTP — e por isso os dois vivem lado a lado dentro de `infrastructure`, nunca dentro de `domain`.

### 6.2. Criando a classe TaskController

Dentro do novo pacote `http`, nasce a classe `TaskController`, propositalmente vazia no início:

```text
TaskController
```

> **O que é um Controller?**
> Controller é o nome dado, em praticamente todo framework web (não só no Spring), à classe responsável por funcionar como **ponto de entrada** das requisições HTTP relacionadas a um recurso específico. É ele quem "atende a porta": recebe a requisição, delega o trabalho pesado para outras camadas (aqui, os Use Cases), e devolve uma resposta. Um Controller não deveria conter regra de negócio — sua única responsabilidade é orquestrar a conversa entre o protocolo HTTP e o restante da aplicação. Neste projeto, o `TaskController` cuida especificamente do recurso `task`.

### 6.3. Adicionando a dependência spring-boot-starter-web

Antes de a classe fazer qualquer coisa, é preciso dar à aplicação a capacidade de falar HTTP — e isso não vem de graça. É adicionada uma nova dependência ao `build.gradle`:

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter'

    implementation 'org.springframework.boot:spring-boot-starter-web'

    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```

> **O que é um "starter" no Spring Boot?**
> Um *starter* é um pacote de dependências "pré-selecionado" pelo próprio time do Spring Boot, reunindo tudo que costuma ser necessário para um determinado tipo de funcionalidade, sem que o desenvolvedor precise descobrir e declarar manualmente cada biblioteca individual envolvida. `spring-boot-starter` (já presente desde a Parte 1) traz o essencial do próprio Spring; `spring-boot-starter-web`, adicionado agora, traz tudo que é necessário para expor uma API REST — incluindo, entre outras coisas, um **servidor web embutido** (por padrão, o Apache Tomcat) e a biblioteca Jackson, responsável por converter objetos Java em JSON e vice-versa (já antecipada, ainda sem uso prático, lá na Parte 1 deste tutorial).

- **`implementation 'org.springframework.boot:spring-boot-starter-web'`** — a sintaxe é a mesma já vista na Parte 1 para `spring-boot-starter`: `implementation` diz ao Gradle que essa dependência é necessária para compilar e rodar o código principal do projeto (diferente de `testImplementation`, usada só para dependências exclusivas dos testes).

Com essa única linha adicionada, a classe principal `TaskmanagerApplication` (inalterada desde a Parte 1) já se comporta de forma visivelmente diferente ao ser executada:

```bash
:: Spring Boot ::                (v3.2.0)

INFO --- dio.taskmanager.TaskmanagerApplication : Starting TaskmanagerApplication
INFO --- o.s.b.w.embedded.tomcat.TomcatWebServer : Tomcat initialized with port 8080
INFO --- dio.taskmanager.TaskmanagerApplication : Started TaskmanagerApplication in 1.198 seconds
```

Até a Parte 3 (Vídeos 01 a 03), rodar `TaskmanagerApplication` fazia o Spring subir seu `ApplicationContext` (o "catálogo de beans" apresentado na Parte 4) e, como não havia nada de web configurado, a aplicação encerrava sozinha logo em seguida. Agora, com `spring-boot-starter-web` no classpath, o Spring Boot detecta automaticamente essa dependência e, além de montar o `ApplicationContext`, também inicializa um **servidor Tomcat embutido** na porta `8080` — e, principalmente, **mantém a aplicação em execução**, esperando por requisições, em vez de encerrar.

> **Um detalhe encontrado no README, não presente no código real:** o bloco de dependências mostrado nas anotações da aula inclui também a linha `testRuntimeOnly 'org.junit.platform:junit-platform-launcher'`. Conferindo o `build.gradle` real do projeto entregue neste `.zip`, essa linha **não está presente** — nem nesta etapa, nem em nenhuma etapa anterior deste tutorial. É provável que essa linha faça parte de um trecho de código gerado por padrão pelo Spring Initializr em outro contexto (por exemplo, ao gerar um novo projeto pela IDE), reaproveitado sem querer na captura de tela do README, sem ter sido de fato adicionada a este projeto. Como os testes já rodam normalmente sem ela (confirmado nos checkpoints anteriores), essa divergência não tem, por ora, nenhum efeito prático.

### 6.4. Anotando o TaskController com @RestController

```java
package dio.taskmanager.infrastructure.http;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
}
```

- **`@RestController`** — assim como `@Service` e `@Repository` (já vistos na Parte 4), esta anotação registra a classe como um bean gerenciado pelo Spring — mas com um significado adicional e mais específico: ela diz ao Spring que essa classe deve ser conectada ao servidor Tomcat como um **receptor de requisições HTTP**, e que tudo o que seus métodos devolverem deve ser convertido diretamente para o corpo da resposta (por exemplo, como JSON), em vez de ser interpretado como o nome de uma página HTML a ser renderizada (esse último seria o comportamento de uma anotação irmã, `@Controller`, usada historicamente em aplicações Spring MVC tradicionais com páginas web renderizadas no servidor — `@RestController` é, na prática, um atalho para `@Controller` combinado com outra anotação, `@ResponseBody`, que instrui esse mesmo comportamento "sempre devolva os dados diretamente").

### 6.5. Injetando o CreateTaskUseCase via construtor

```java
@RestController
public class TaskController {
    private final CreateTaskUseCase createTaskUseCase;

    public TaskController(CreateTaskUseCase createTaskUseCase) {
        this.createTaskUseCase = createTaskUseCase;
    }

}
```

Nada de novo em termos de sintaxe aqui: é exatamente o mesmo padrão de injeção de dependência via construtor já usado por todos os Use Cases desde a Parte 4 — um campo `private final`, atribuído no construtor. A diferença é apenas *quem* está recebendo a dependência: agora é a camada HTTP (`TaskController`) que depende da camada de aplicação (`CreateTaskUseCase`), e não mais um Use Case dependendo do repositório. É a mesma ideia de "declarar do que se precisa, sem se preocupar em criar" se repetindo em mais uma camada da arquitetura.

### 6.6. Primeiro endpoint: @PostMapping

```java
@PostMapping
void create() {
    System.out.println("Creating task");
}
```

- **`@PostMapping`** — mapeia este método para requisições HTTP do verbo `POST` (já apresentado, em teoria, lá na Parte 1: "criar um novo recurso"). Sem nenhum caminho adicional especificado dentro dos parênteses da anotação, o método fica associado à raiz da aplicação — ou seja, qualquer `POST` enviado para `localhost:8080` cai aqui.
- **`void create()`** — assim como observado com `execute()` na Parte 4, o **nome do método em si não importa** para o Spring; o que importa é a anotação que o precede. `create` é apenas um nome descritivo escolhido para facilitar a leitura humana do código. O método não tem nenhum modificador de acesso explícito (nem `public`, nem `private`) — o mesmo padrão de visibilidade "package-private" já visto na Parte 4: o Spring consegue chamar esse método através de reflexão (uma técnica que permite inspecionar e invocar métodos de uma classe em tempo de execução, sem precisar deles serem publicamente acessíveis no sentido tradicional de compilação), então a ausência de `public` não impede seu funcionamento.
- **`System.out.println("Creating task");`** — um comando temporário, usado apenas para confirmar visualmente, no console, que uma requisição de fato chegou até este método — o equivalente, em uma API, ao "Hello, World" de qualquer curso introdutório.

### 6.7. Testando o endpoint: HTTP Client do IntelliJ e curl

Duas ferramentas são usadas para validar o endpoint recém-criado, e vale entender o papel de cada uma:

> **O que é um "cliente HTTP"?**
> Um cliente HTTP é qualquer programa capaz de montar e enviar requisições HTTP para um servidor — o navegador é um cliente HTTP (usado o tempo todo para acessar páginas), mas para testar uma API (que não é feita para ser "navegada" visualmente) costuma-se usar ferramentas mais específicas.

- **HTTP Client do IntelliJ** — uma ferramenta embutida na IDE, acessível pelo menu de contexto com a opção **Create Request in HTTP Client**, que permite montar e enviar requisições diretamente de dentro do editor, sem sair do IntelliJ:
  ```http
  POST http://localhost:8080
  ```
- **curl** — um programa de linha de comando, presente na maioria dos sistemas operacionais, usado para fazer requisições HTTP diretamente pelo terminal:
  ```bash
  curl -i -X POST http://localhost:8080
  ```
  - **`-X POST`** — especifica explicitamente o verbo HTTP da requisição (por padrão, sem essa opção, o curl usaria `GET`).
  - **`-i`** — instrui o curl a incluir os cabeçalhos (*headers*) da resposta HTTP na saída exibida no terminal, além do corpo da resposta.

Nas duas ferramentas, a requisição recebe como resposta `HTTP/1.1 200`, com corpo vazio (`Content-Length: 0`) — esperado, já que o método `create` ainda não devolve nenhum conteúdo, apenas executa sua lógica interna. No console da aplicação, a mensagem `Creating task` aparece, confirmando que a requisição de fato foi roteada corretamente até o método.

### 6.8. Instanciando manualmente um CreateTaskInput (passo temporário)

Para validar que os dados de uma tarefa realmente conseguem atravessar toda a aplicação — do controller até o repositório —, o método `create` passa, temporariamente, a montar um `CreateTaskInput` "na mão" e chamar o Use Case já existente:

```java
@PostMapping
void create() {
    var input = new CreateTaskInput("Renovar Passaporte", Optional.empty());
    createTaskUseCase.execute(input);
}
```

Este é um passo deliberadamente provisório — os valores `"Renovar Passaporte"` e `Optional.empty()` estão fixos no código só para efeito de teste, e serão substituídos pelos dados reais da requisição HTTP já na Seção 6.11. Repare que `CreateTaskInput` e `Optional.empty()` são conceitos já conhecidos desde a Parte 4 — nada de novo aqui, além de reaproveitar, pela primeira vez, um Use Case a partir de um contexto real de produção (não mais um teste).

Com um *breakpoint* colocado dentro do método `save` de `InMemoryTaskRepository`, e a aplicação rodando em modo de depuração, o mesmo teste de requisição confirma, na prática, que a execução realmente para naquele ponto, com a `Task` recém-criada disponível para inspeção — validando de ponta a ponta a integração entre `TaskController`, `CreateTaskUseCase` e `InMemoryTaskRepository`.

### 6.9. Mapeando o controller para o recurso /tasks

```java
@RestController
@RequestMapping("/tasks")
public class TaskController {
    ...
}
```

- **`@RequestMapping("/tasks")`** — colocada no nível da **classe** (e não de um método específico), esta anotação define um caminho-base para todos os endpoints daquele controller. Antes desta mudança, o `@PostMapping` (sem argumentos) respondia à raiz da aplicação (`/`); combinado agora com `@RequestMapping("/tasks")` na classe, o mesmo `@PostMapping` passa a responder a `POST /tasks` — o caminho da classe funciona como um prefixo, somado ao caminho (se houver algum) declarado em cada método.

Essa mudança faz sentido arquiteturalmente: como a aplicação provavelmente terá outros recursos no futuro (o próprio README já lista, em seu roteiro, cinco tópicos ainda por vir), cada um deveria responder em seu próprio caminho, evitando que tudo dispute a mesma raiz `/`.

### 6.10. CreateTaskRequest: um DTO específico da camada HTTP

Com o endpoint respondendo corretamente em `/tasks`, chega a hora de parar de fixar os dados no código e passar a recebê-los de fato do cliente. Em vez de reaproveitar diretamente o `CreateTaskInput` (já existente desde a Parte 4) como o tipo do parâmetro do método `create`, é criado um novo pacote e uma nova classe, dedicados exclusivamente à camada HTTP:

```text
dio.taskmanager.infrastructure.http.request
```

```java
package dio.taskmanager.infrastructure.http.request;

import java.util.Optional;

public record CreateTaskRequest(String title, Optional<String> description) {
}
```

Note que a *forma* de `CreateTaskRequest` é, neste momento, idêntica à de `CreateTaskInput` — os mesmos dois campos, os mesmos tipos. Ainda assim, a decisão de criar uma classe nova (em vez de reaproveitar o `CreateTaskInput`) segue o mesmo raciocínio de desacoplamento entre camadas já visto na Parte 4 para os DTOs de entrada/saída dos Use Cases: `CreateTaskRequest` representa especificamente **o formato que a API HTTP expõe ao mundo externo** (o "contrato" que o cliente da API enxerga), enquanto `CreateTaskInput` representa **o formato que a camada de aplicação espera receber**. Hoje os dois são iguais; nada garante que continuarão sendo assim para sempre — se um dia a API precisar aceitar, por exemplo, um campo adicional só para fins de auditoria HTTP (como um cabeçalho customizado), ele poderia ser adicionado a `CreateTaskRequest` sem que `CreateTaskInput` (e, por consequência, o `CreateTaskUseCase`) precisasse saber de sua existência.

### 6.11. Recebendo o CreateTaskRequest como @RequestBody

```java
@PostMapping
void create(@RequestBody CreateTaskRequest request) {
    var input = new CreateTaskInput(request.title(), request.description());
    createTaskUseCase.execute(input);
}
```

- **`@RequestBody CreateTaskRequest request`** — a anotação `@RequestBody`, colocada sobre o parâmetro do método, instrui o Spring a pegar o **corpo** da requisição HTTP (o conteúdo enviado depois dos cabeçalhos, tipicamente em formato JSON) e convertê-lo automaticamente em uma instância de `CreateTaskRequest`. É a biblioteca Jackson (trazida pelo `spring-boot-starter-web`, como visto na Seção 6.3) quem faz esse trabalho de conversão nos bastidores — o mesmo processo de "objeto Java vira JSON e vice-versa" já antecipado, em teoria, lá na Parte 1 deste tutorial.
- **`request.title()` e `request.description()`** — assim como em `CreateTaskInput` (Parte 4), esses são os métodos de acesso gerados automaticamente pelo `record` `CreateTaskRequest`, usados aqui para "abrir o envelope" recebido da requisição e repassar seus valores para um novo `CreateTaskInput`.

### 6.12. O erro 415 e o cabeçalho Content-Type

Ao testar esse novo endpoint com um corpo JSON, a primeira tentativa falha com um erro:

```http
POST http://localhost:8080/tasks
Content-Type: application/json

{"title": "ABC"}
```

> **O que é o cabeçalho Content-Type, e o que significa o erro 415?**
> Cabeçalhos (*headers*) HTTP são metadados que acompanham uma requisição (ou resposta), descrevendo características dela sem fazer parte do "conteúdo principal". `Content-Type` é um desses cabeçalhos, e informa ao servidor **em qual formato o corpo da requisição está codificado** — aqui, `application/json` avisa: "o texto que vem a seguir deve ser interpretado como JSON". Sem esse cabeçalho declarado corretamente, o Spring não sabe que estratégia de conversão usar para transformar o corpo recebido em um `CreateTaskRequest`, e rejeita a requisição com o código de status **`415 Unsupported Media Type`** — um erro do cliente (por isso a faixa `4xx`), que significa, em linhas gerais: "entendi o que você quer fazer, mas não sei processar o formato em que você me enviou os dados".

Uma vez incluído corretamente o cabeçalho `Content-Type: application/json`, a mesma requisição passa a ser aceita, retornando `HTTP/1.1 200` com corpo vazio — confirmando que o `CreateTaskRequest` foi corretamente reconstruído a partir do JSON enviado.

### 6.13. Centralizando a conversão: o método toInput()

```java
public record CreateTaskRequest(String title, Optional<String> description) {
    public CreateTaskInput toInput() {
        return new CreateTaskInput(title, description);
    }
}
```

Em vez de deixar a conversão de `CreateTaskRequest` para `CreateTaskInput` espalhada dentro do método `create` do controller, essa lógica é movida para dentro do próprio `CreateTaskRequest`, em um método `toInput()`. É exatamente o mesmo raciocínio já visto na Parte 4 com o método estático `TaskOutput.from(Task)` — centralizar a conversão entre dois tipos em um único lugar, de preferência dentro de uma das próprias classes envolvidas, em vez de duplicá-la (ou espalhá-la) por quem quer que precise fazer essa conversão. A diferença de estilo aqui é que `toInput()` é um método de **instância** (não `static`), já que ele converte "esta instância específica" de `CreateTaskRequest` em um `CreateTaskInput` correspondente — nomeado no sentido inverso de `from` (que "vem de fora para dentro"), `toInput` "vai de dentro para fora".

Com isso, o controller fica mais enxuto:

```java
@PostMapping
void create(@RequestBody CreateTaskRequest request) {
    var input = request.toInput();
    createTaskUseCase.execute(input);
}
```

> **Detalhe encontrado no código-fonte real:** conferindo o arquivo `CreateTaskRequest.java` entregue no `.zip`, o método `toInput()` está acompanhado de um comentário `// no usages`, deixado ali provavelmente pelo próprio IntelliJ em algum momento anterior à gravação (a IDE costuma marcar métodos que ela não encontra sendo chamados em nenhum outro lugar do código, como uma forma de destacar possível código morto). Esse comentário está **desatualizado** em relação ao estado final do projeto: `toInput()` é, sim, chamado — dentro de `TaskController.create()`, como mostrado acima. É um resquício inofensivo de uma etapa intermediária da edição do código, mas vale saber que ele não reflete mais a realidade caso apareça ao abrir o arquivo.

### 6.14. Capturando o output e criando o pacote response

```java
void create(@RequestBody CreateTaskRequest request) {
    var input = request.toInput();
    var output = createTaskUseCase.execute(input);
}
```

O retorno de `createTaskUseCase.execute(input)` — um `TaskOutput`, já conhecido desde a Parte 4 — passa a ser guardado na variável `output`. Para devolver essas informações ao cliente da API, é criado, dentro do pacote `http`, mais um novo subpacote — desta vez para os DTOs de **saída** da camada HTTP:

```text
dio.taskmanager.infrastructure.http.response
```

### 6.15. TaskResponse: o DTO de saída da API, e @JsonInclude

```java
package dio.taskmanager.infrastructure.http.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import dio.taskmanager.application.output.TaskOutput;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
public record TaskResponse(String id, String title, String description, String status) {
    public static TaskResponse from(TaskOutput output) {
        return new TaskResponse(output.id(),
                output.title(),
                output.description().orElse(null),
                output.status());
    }

}
```

- **Campos do record** — assim como `TaskOutput` (Parte 4) já havia optado por expor `id` e `status` como `String` simples (em vez dos tipos ricos `TaskId`/`TaskStatus`), `TaskResponse` dá um passo semelhante com `description`: embora ela seja `Optional<String>` tanto em `Task` (domínio) quanto em `TaskOutput` (aplicação), aqui ela é declarada como um `String` comum, que pode ser `null`. A justificativa: `Optional` é um tipo pensado para uso **dentro do código Java**, como forma de forçar quem programa a lidar explicitamente com a ausência de um valor; ele não tem um equivalente natural em JSON (não existe um "Optional JSON") — então, na borda da API, o mais simples é "desembrulhar" o `Optional` e representar sua ausência da forma como HTTP/JSON já sabem lidar: com a ausência do campo, ou com `null`.
- **`output.description().orElse(null)`** — o método `orElse` de `Optional` (uma alternativa mais simples ao `orElseThrow`, já visto na Parte 5) devolve o valor de dentro do `Optional`, se houver um; caso contrário, devolve o valor informado como argumento — aqui, `null`.
- **`public static TaskResponse from(TaskOutput output)`** — mesmo padrão de Static Factory Method já visto em `TaskOutput.from(Task)` (Parte 4): um método estático dedicado a converter um objeto de um tipo (`TaskOutput`, da camada de aplicação) em outro (`TaskResponse`, da camada HTTP).
- **`@JsonInclude(JsonInclude.Include.NON_ABSENT)`** — uma anotação da biblioteca Jackson, que controla quais campos de um objeto são incluídos quando ele é convertido para JSON. `NON_ABSENT` instrui o Jackson a **omitir do JSON gerado** qualquer campo cujo valor seja considerado "ausente" — o que inclui valores `null` (para campos comuns, como é o caso de `description` aqui, já um `String` puro) e também `Optional` vazios (caso a anotação fosse usada em um campo declarado como `Optional`, algo que não é o caso nesta classe específica, mas que dá nome à opção `NON_ABSENT`, mais abrangente que a alternativa mais simples `NON_NULL`). Na prática, para esta classe: se uma tarefa não tiver descrição, o JSON de resposta simplesmente não incluirá a chave `"description"`, em vez de incluí-la como `"description": null`.

### 6.16. Retornando o TaskResponse: o endpoint completo

```java
@PostMapping
TaskResponse create(@RequestBody CreateTaskRequest request) {
    var input = request.toInput();
    var output = createTaskUseCase.execute(input);
    return TaskResponse.from(output);
}
```

O tipo de retorno do método muda de `void` para `TaskResponse`, e a última linha converte o `output` do Use Case no DTO de resposta, devolvendo-o. Como a classe está anotada com `@RestController` (Seção 6.4), o Spring sabe que esse valor de retorno não deve ser tratado como o nome de uma página a renderizar — ele é automaticamente serializado para JSON (pelo Jackson, mais uma vez) e colocado no corpo da resposta HTTP.

Testando o endpoint completo:

```http
POST http://localhost:8080/tasks
Content-Type: application/json

{"title": "ABC", "description": "qwerty"}
```

A resposta traz de volta os dados da tarefa efetivamente persistida:

```json
{
  "id": "0483ebff-8063-4fb1-8b99-660098ff194b",
  "title": "ABC",
  "description": "qwerty",
  "status": "PENDING"
}
```

Esse resultado confirma o fluxo de ponta a ponta funcionando: requisição HTTP → `CreateTaskRequest` (desserializado pelo Jackson) → `toInput()` → `CreateTaskInput` → `CreateTaskUseCase.execute()` → `Task` criada e persistida no `InMemoryTaskRepository` → `TaskOutput` → `TaskResponse.from()` → JSON de resposta (serializado pelo Jackson) devolvido ao cliente.

### 6.17. A ferramenta de Endpoints do IntelliJ

Por fim, a aula apresenta uma ferramenta da IDE chamada **Endpoints**, acessível pelo menu de contexto sobre o mapeamento (`@RequestMapping`/`@PostMapping`) através da opção **Show all endpoints of module**. Ela lista todos os endpoints já mapeados na aplicação — neste momento, apenas `/tasks [POST]` — permitindo testá-los diretamente pelo HTTP Client, visualizar a especificação **OpenAPI** gerada automaticamente, ou exportá-la para ferramentas como Swagger ou Redoc (um tema que, segundo o próprio roteiro do curso, deve ser explorado com mais profundidade no Vídeo 09 — Documentando a API).

---

### ✅ Checkpoint — Estado do projeto ao final do Vídeo 06

Conferido diretamente contra o código-fonte real entregue em `taskmanager_ate_o_video_06.zip`.

**Estrutura de pastas (alterações em relação ao checkpoint do Vídeo 05, em negrito)**

```
taskmanager/
├── build.gradle                                        (alterado — nova dependência web)
└── src/
    ├── main/java/dio/taskmanager/
    │   ├── TaskmanagerApplication.java                  (sem alterações de código)
    │   ├── application/                                 (sem alterações nesta etapa)
    │   ├── domain/                                       (sem alterações nesta etapa)
    │   ├── infrastructure/
    │   │   ├── http/
    │   │   │   ├── TaskController.java                    ← novo
    │   │   │   ├── request/
    │   │   │   │   └── CreateTaskRequest.java              ← novo
    │   │   │   └── response/
    │   │   │       └── TaskResponse.java                   ← novo
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

    implementation 'org.springframework.boot:spring-boot-starter-web'

    // Testes com Spring Boot + JUnit 5
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```

**TaskController.java (versão final desta etapa)**

```java
package dio.taskmanager.infrastructure.http;

import dio.taskmanager.application.CreateTaskUseCase;
import dio.taskmanager.application.input.CreateTaskInput;
import dio.taskmanager.infrastructure.http.request.CreateTaskRequest;
import dio.taskmanager.infrastructure.http.response.TaskResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final CreateTaskUseCase createTaskUseCase;

    public TaskController(CreateTaskUseCase createTaskUseCase) {
        this.createTaskUseCase = createTaskUseCase;
    }

    @PostMapping
    TaskResponse create(@RequestBody CreateTaskRequest request) {
        var input = request.toInput();
        var output = createTaskUseCase.execute(input);
        return TaskResponse.from(output);
    }
}
```

> **Detalhe encontrado no código real: dois imports não utilizados.** Tanto `import dio.taskmanager.application.input.CreateTaskInput;` quanto `import java.util.Optional;` permanecem no topo do arquivo, mas nenhum dos dois é mais efetivamente usado no corpo da classe — eram necessários na versão intermediária mostrada na Seção 6.8 (`new CreateTaskInput("Renovar Passaporte", Optional.empty())`), e deixaram de ser quando o método passou a usar `request.toInput()` (Seção 6.13), que já devolve o `CreateTaskInput` pronto, sem precisar do tipo ou de `Optional` declarados explicitamente ali dentro do controller. São resíduos inofensivos (não impedem a compilação nem a execução), típicos do ritmo de edição ao vivo de uma aula — mas um `import` não utilizado costuma ser sinalizado pela IDE, e seria natural vê-lo removido em uma limpeza futura do código.

**CreateTaskRequest.java (versão final desta etapa)**

```java
package dio.taskmanager.infrastructure.http.request;
import dio.taskmanager.application.input.CreateTaskInput;

import java.util.Optional;

public record CreateTaskRequest(String title, Optional<String> description) {
    public CreateTaskInput toInput() {  // no usages
        return new CreateTaskInput(title, description);
    }
}
```

> Como já observado na Seção 6.13, o comentário `// no usages` está desatualizado: `toInput()` é chamado normalmente por `TaskController.create()`.

**TaskResponse.java (versão final desta etapa)** — exatamente como mostrado na Seção 6.15, sem diferenças em relação ao código-fonte real.

**Arquivos sem nenhuma alteração nesta etapa:** todos os cinco Use Cases (`CreateTaskUseCase`, `GetTasksUseCase`, `GetTaskByIdUseCase`, `DeleteTaskUseCase`, `UpdateTaskUseCase`), toda a camada `domain` (`Task`, `TaskId`, `TaskStatus`, `TaskRepository`, `TaskNotFoundException`), `InMemoryTaskRepository`, `TaskmanagerApplication`, `Main.java` (o *playground* já visto desde a Parte 2) e todos os arquivos de teste existentes.

> **Nota importante sobre o alcance desta etapa:** apenas o `CreateTaskUseCase` foi conectado a um endpoint HTTP até aqui. Os outros quatro Use Cases (listagem, busca por id, exclusão e atualização), completos desde a Parte 5, continuam existindo apenas internamente — sem nenhum `@GetMapping`, `@DeleteMapping` ou `@PatchMapping`/`@PutMapping` que os exponha. Também não há, nesta etapa, nenhum teste automatizado para `TaskController`, `CreateTaskRequest` ou `TaskResponse` — apenas testes manuais, feitos via HTTP Client e curl, conforme narrado na aula.

---

## Glossário de conceitos Java e Spring — complemento do Vídeo 06

Continuação dos glossários dos documentos anteriores — apenas os termos novos, introduzidos neste vídeo.

| Termo | Significado |
|---|---|
| Controller | Classe responsável por funcionar como ponto de entrada das requisições HTTP relacionadas a um recurso específico, delegando o trabalho de negócio para outras camadas (aqui, os Use Cases) e devolvendo uma resposta. |
| `@RestController` | Anotação que registra uma classe como bean gerenciado pelo Spring e a conecta ao servidor embutido como receptora de requisições HTTP, serializando automaticamente o retorno de seus métodos (tipicamente para JSON) como corpo da resposta. Equivale, na prática, a `@Controller` combinada com `@ResponseBody`. |
| `@RequestMapping(caminho)` | Anotação que define um caminho-base de URL para uma classe (ou um método) — os caminhos declarados nos métodos de dentro dela são somados a esse prefixo. |
| `@PostMapping` / `@GetMapping` / `@PutMapping` / `@PatchMapping` / `@DeleteMapping` | Anotações que mapeiam um método a um verbo HTTP específico (POST, GET, PUT, PATCH, DELETE), combinado opcionalmente com um caminho de URL. |
| `@RequestBody` | Anotação usada sobre um parâmetro de método, que instrui o Spring a desserializar o corpo da requisição HTTP (tipicamente JSON) diretamente para o tipo declarado desse parâmetro. |
| Starter (Spring Boot) | Um pacote de dependências pré-selecionado pelo time do Spring Boot, reunindo tudo que costuma ser necessário para uma determinada funcionalidade (ex.: `spring-boot-starter-web` para construir uma API REST), sem exigir que cada biblioteca individual seja descoberta e declarada manualmente. |
| Servidor embutido (embedded server) | Um servidor web (por padrão, o Apache Tomcat) que passa a rodar dentro do próprio processo da aplicação Java, assim que uma dependência como `spring-boot-starter-web` está presente — sem exigir a instalação de um servidor separado no sistema operacional. |
| Content-Type (cabeçalho HTTP) | Um cabeçalho (metadado) enviado junto de uma requisição ou resposta HTTP, que informa o formato em que o corpo está codificado (ex.: `application/json`). |
| `415 Unsupported Media Type` | Código de status HTTP da faixa 4xx (erro do cliente), devolvido quando o servidor não consegue interpretar o formato do corpo de uma requisição — tipicamente por ausência ou incorreção do cabeçalho `Content-Type`. |
| Jackson | Biblioteca usada internamente pelo Spring Boot (trazida por `spring-boot-starter-web`) para converter objetos Java em JSON (serialização) e JSON em objetos Java (desserialização), de forma automática. |
| `@JsonInclude` | Anotação da biblioteca Jackson que controla quais campos de um objeto são incluídos ao convertê-lo para JSON — por exemplo, `NON_ABSENT` omite campos `null` (e `Optional` vazios) do JSON gerado. |
| curl | Programa de linha de comando usado para montar e enviar requisições HTTP diretamente pelo terminal, útil para testar APIs sem depender de uma interface gráfica. |
| HTTP Client (IntelliJ) | Ferramenta embutida na IDE IntelliJ que permite montar, salvar e enviar requisições HTTP diretamente de dentro do editor de código. |

---

## Próximos passos: o que vem a partir do Vídeo 07

*(Esta seção substitui a versão anterior, "Próximos passos: o que vem a partir do Vídeo 06", removendo o vídeo já coberto por este documento.)*

Com o primeiro endpoint HTTP funcionando de ponta a ponta — criação de tarefas via `POST /tasks` — a infraestrutura básica de uma API REST está validada: servidor embutido, controller, DTOs de entrada/saída específicos da camada HTTP, e a conversão automática entre JSON e objetos Java. Segundo o roteiro do curso (títulos já visíveis no README, ainda sem conteúdo detalhado na época em que este documento foi escrito), os próximos vídeos devem seguir esta sequência:

- **Vídeo 07 — Consulta de tarefas:** deve conectar `GetTasksUseCase` e `GetTaskByIdUseCase`, já prontos desde a Parte 5, a endpoints `GET` reais no `TaskController` (provavelmente `GET /tasks` e `GET /tasks/{id}`, este último usando a anotação `@PathVariable` para capturar o id a partir da própria URL — um conceito ainda não utilizado até este ponto do curso). É esperado, também, que a exceção `TaskNotFoundException` (Parte 5) passe a ser tratada nesta etapa, convertendo-a em uma resposta HTTP `404 Not Found` — hoje, se lançada, ela provavelmente resultaria em um genérico `500 Internal Server Error`, já que nenhum tratamento específico de exceções foi configurado até aqui.
- **Vídeo 08 — Validando dados:** deve introduzir validações automáticas de entrada (por exemplo, com anotações do Bean Validation, como `@NotBlank` ou `@Valid`), tratando respostas de erro (`400 Bad Request`) para dados inválidos — como criar uma tarefa sem título, algo que hoje a API aceita sem qualquer verificação.
- **Vídeo 09 — Documentando a API:** deve apresentar uma ferramenta de documentação automática de APIs (comumente o Swagger/OpenAPI no ecossistema Spring), gerando uma interface visual para explorar os endpoints criados — dando continuidade direta à especificação OpenAPI já mencionada de forma indireta na ferramenta de Endpoints do IntelliJ (Seção 6.17).
- **Vídeo 10 — Evoluindo a API:** deve fechar o curso com melhorias adicionais, possivelmente conectando os endpoints de atualização (`PATCH`/`PUT`) e remoção (`DELETE`), que continuam existindo apenas como Use Cases (`UpdateTaskUseCase`, `DeleteTaskUseCase`) sem nenhum mapeamento HTTP até este ponto.

> **Sugestão de uso deste documento (mantida das versões anteriores):** depois de assistir a cada novo vídeo, adicione uma nova seção a este tutorial seguindo o mesmo formato: bloco de código → explicação linha a linha → checkpoint conferido contra o código real → atualização do glossário e dos próximos passos. Isso mantém o material sempre alinhado ao seu ritmo de estudo.
