# Tutorial de Estudos — Construindo um Task Manager com Java e Spring Boot

**Criando sua primeira API REST — Vídeo 09**

- Curso: NTT Data — Jornada Tech (DIO) · Trilha Java + Spring
- Instrutor: Thiago Poiani
- Documento de referência pessoal — nível iniciante em Java
- Continuação de: *"Tutorial de Estudos — Construindo um Task Manager com Java e Spring Boot — Validando dados — Vídeo 08"*, que por sua vez continua os documentos dos Vídeos 07, 04–05 e 01 a 03

---

## Sobre este documento

Este é o sexto capítulo do tutorial de estudos deste curso, e dá sequência direta ao documento anterior (Vídeo 08), que deixou a API com validação de dados de entrada funcionando na criação de tarefas, mas ainda **sem nenhuma documentação formal**: para saber quais campos cada endpoint espera, ou o que cada um devolve, seria preciso ler o código-fonte do `TaskController` diretamente.

É essa lacuna que o Vídeo 09 começa a fechar — mas de uma forma bem diferente da mais comum (o Swagger/OpenAPI, que gera documentação a partir de anotações direto nos controllers). O curso opta pelo **Spring REST Docs**: uma abordagem em que a documentação nasce a partir dos **testes de integração** da aplicação. Na prática, isso significa escrever um teste que faz requisições reais (simuladas) ao `TaskController`, e o próprio Spring REST Docs captura essas requisições e respostas, gerando fragmentos de documentação (*snippets*) que depois são reunidos em um documento final, escrito no formato Asciidoctor. É um vídeo com bastante configuração de build (`build.gradle`) antes de qualquer teste realmente rodar — e vale entender o motivo de cada peça antes de segui-la de cabeça baixa.

Assim como os documentos anteriores, este foi escrito a partir das anotações de aula (README atualizado até o Vídeo 09) e conferido diretamente contra o código-fonte real do projeto nesta etapa (`taskmanager_ate_o_video_09.zip`). Duas coisas valem adiantar: primeiro, **a boa notícia** — o bug de anotações duplicadas no `GlobalExceptionHandler`, identificado no documento do Vídeo 08, **já não está mais presente** neste `.zip`, embora o README do Vídeo 09 não mencione essa correção em nenhum momento (é uma correção "silenciosa", provavelmente feita entre uma gravação e outra). Segundo, um conjunto de divergências pontuais entre a narrativa e o código real, detalhadas ao longo do texto — a mais notável sendo que o `build.gradle` mostrado no README, no passo inicial, aparenta vir de um projeto com versões mais novas de Spring Boot e Java do que as que o projeto real deste curso de fato usa.

---

## Parte 9 — Documentando a API (Vídeo 09)

### 9.1. Escolhendo a abordagem: Spring REST Docs em vez de Swagger

Diferente da alternativa mais popular no ecossistema Spring (o Swagger/OpenAPI, que inspeciona anotações dos controllers em tempo de execução para gerar uma documentação interativa automaticamente), o curso apresenta o **Spring REST Docs**. A diferença central é filosófica: em vez de gerar documentação a partir do código de produção, o Spring REST Docs gera documentação a partir dos **testes** — a ideia sendo que, se a documentação nasce de um teste que precisa passar, ela nunca fica desatualizada em relação ao comportamento real da API (um teste que falha barra o build, e barra também a geração de uma documentação incorreta).

> **O que é Asciidoctor / AsciiDoc?** É um formato de texto para escrever documentação (parecido em espírito com o Markdown, já usado neste projeto para os próprios arquivos deste tutorial), só que com recursos adicionais voltados a documentação técnica mais robusta — como a diretiva `include::`, usada mais adiante (Seção 9.10) para importar conteúdo de outros arquivos dentro de um documento principal. "Asciidoctor" é o nome da ferramenta (um processador/conversor) que transforma arquivos `.adoc` (escritos em AsciiDoc) em formatos finais como HTML ou PDF.

### 9.2. Preparando o `build.gradle`: o plugin do Asciidoctor

O primeiro passo prático é declarar, no bloco `plugins` do `build.gradle`, o plugin responsável por processar os arquivos Asciidoctor durante o build:

```groovy
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.2.0'
    id 'io.spring.dependency-management' version '1.1.4'
    id("io.freefair.lombok") version "9.2.0"

    id 'org.asciidoctor.jvm.convert' version '4.0.5'
}
```

- **`id 'org.asciidoctor.jvm.convert' version '4.0.5'`** — mais um plugin do Gradle (o mesmo mecanismo de "complemento de build" já apresentado desde a Parte 1, quando os plugins `java`, `org.springframework.boot` e `io.freefair.lombok` foram vistos pela primeira vez). Este em particular adiciona ao projeto a capacidade de processar arquivos `.adoc`, convertendo-os para HTML (ou outros formatos) através de uma nova *task* do Gradle chamada `asciidoctor` (retomada na Seção 9.6).

> **Divergência encontrada no código-fonte real desta etapa — atenção especial ao bloco `plugins`/`java`/`group`:** o README mostra este bloco completo:
> ```groovy
> plugins {
>     id 'java'
>     id 'org.springframework.boot' version '4.0.5'
>     id 'io.spring.dependency-management' version '1.1.7'
>     id("io.freefair.lombok") version "9.2.0"
>
>     id 'org.asciidoctor.jvm.convert' version '4.0.5'
> }
>
> group = 'dio'
> version = '0.0.1-SNAPSHOT'
> description = 'taskmanager'
>
> java {
>     toolchain { JavaToolchainSpec it ->
>         languageVersion = JavaLanguageVersion.of(25)
>     }
> }
> ```
> Ou seja, com o **Spring Boot na versão `4.0.5`** (bem à frente da `3.2.0` usada por este projeto desde a Parte 1), o **`io.spring.dependency-management` na versão `1.1.7`**, o `group` simplificado para `'dio'` (em vez de `'dio.taskmanager'`), uma nova propriedade `description`, e um bloco `java { toolchain { ... } }` fixando explicitamente o uso do **JDK 25**. Conferindo o `build.gradle` real entregue neste `.zip`, **nenhuma dessas mudanças está presente**: a versão do Spring Boot continua `3.2.0`, a do `dependency-management` continua `1.1.4`, o `group` continua `'dio.taskmanager'`, e não existe bloco `java { toolchain { ... } }` nem propriedade `description` em lugar nenhum do arquivo — apenas a nova linha do plugin do Asciidoctor foi de fato adicionada.
>
> A explicação mais provável é que aquela captura de tela específica do README tenha vindo de um projeto de referência diferente (talvez um ambiente pessoal do instrutor, já atualizado para versões mais novas de Spring Boot e Java, usado só para ilustrar como ficaria um `build.gradle` mais moderno), e não do projeto real construído ao longo deste curso — o mesmo projeto que, em todas as etapas anteriores, permaneceu no Spring Boot `3.2.0` e sem bloco de toolchain explícito (rodando com o JDK que a própria IDE já tinha configurado, JDK 21, como visto nos logs de execução de teste do Vídeo 07). Como o restante deste documento segue o **código-fonte real** (e não a imagem do README), todos os blocos de código a seguir mantêm as versões e o `group` já conhecidos desde a Parte 1. Vale ficar atento a essa diferença caso, ao consultar o README original, o leitor tente copiar aquele bloco `plugins` específico para o próprio projeto — ele não corresponde ao estado real deste `taskmanager`.

### 9.3. Definindo onde os *snippets* serão gerados

Em seguida, é criada uma variável de projeto chamada `snippetsDir`, guardando o caminho onde os fragmentos de documentação (gerados a partir dos testes, como será visto na Seção 9.8) devem ser depositados:

```groovy
ext {
    snippetsDir = file('build/generated-snippets')
}
```

- **`ext { ... }`** — no Gradle, o bloco `ext` (abreviação de *extra properties*) é usado para declarar variáveis personalizadas dentro do script de build, que podem ser reaproveitadas mais adiante em outras partes do mesmo arquivo (como acontece nas Seções 9.5 e 9.6). É, essencialmente, a forma do Gradle de dizer "aqui vai uma variável que eu, autor do build, estou definindo, e não uma configuração padrão do Gradle ou de algum plugin".
- **`file('build/generated-snippets')`** — a função `file(...)` (própria do Gradle) converte um caminho de texto em um objeto que representa um arquivo/diretório real no sistema de arquivos, relativo à raiz do projeto. Aqui, aponta para uma subpasta dentro do diretório de build (`build/`) já existente desde a Parte 1 — o mesmo diretório onde ficam as classes compiladas, os relatórios de teste, e agora também os *snippets* de documentação.

### 9.4. A configuração `asciidoctorExtensions`

O próximo bloco cria uma nova **configuração** do Gradle, uma espécie de "categoria" de dependências, usada mais adiante para isolar as bibliotecas que servem apenas para estender o Asciidoctor, sem misturá-las com as dependências normais de compilação ou de teste:

```groovy
configurations {
    asciidoctorExtensions
}
```

> **O que é uma *configuration* no Gradle?** Já eram conhecidas, deste projeto, duas configurations: `implementation` (dependências necessárias para compilar e rodar o código de produção) e `testImplementation` (dependências adicionais, disponíveis só durante a compilação e execução dos testes) — ambas usadas desde a Parte 1. Uma *configuration* é, no fundo, um "balde" nomeado onde dependências podem ser despejadas, e cada plugin do Gradle decide o que fazer com o conteúdo de cada balde. Ao declarar `asciidoctorExtensions` dentro do bloco `configurations`, um novo balde, com esse nome, passa a existir — e é o próprio plugin do Asciidoctor (Seção 9.2) que sabe procurar dependências dentro dele para estender seu comportamento (Seção 9.6).

### 9.5. Fixando a versão do Asciidoctor

```groovy
asciidoctorj {
    version = "3.0.0"
}
```

- **`asciidoctorj { version = "3.0.0" }`** — configura uma propriedade específica do plugin do Asciidoctor (Seção 9.2), fixando a versão da biblioteca `AsciidoctorJ` (a implementação em Java do processador Asciidoctor) que será usada para converter os arquivos `.adoc`. Fixar a versão explicitamente, em vez de deixar o plugin escolher uma versão padrão, é uma prática comum em builds Gradle para garantir que o projeto se comporte de forma previsível, independentemente de quando (ou em qual máquina) o build for executado.

### 9.6. As dependências do Spring REST Docs

Duas novas dependências são adicionadas ao bloco `dependencies` já existente:

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter'
    implementation 'org.springframework.boot:spring-boot-starter-validation'

    implementation 'org.springframework.boot:spring-boot-starter-web'

    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'

    asciidoctorExtensions 'org.springframework.restdocs:spring-restdocs-asciidoctor'
    testImplementation 'org.springframework.restdocs:spring-restdocs-mockmvc'
}
```

- **`asciidoctorExtensions 'org.springframework.restdocs:spring-restdocs-asciidoctor'`** — vai para dentro do "balde" criado na Seção 9.4: é a extensão que ensina o Asciidoctor a entender a diretiva especial de *snippets* usada dentro dos arquivos `.adoc` (retomada na Seção 9.10).
- **`testImplementation 'org.springframework.restdocs:spring-restdocs-mockmvc'`** — vai para o "balde" de testes já conhecido desde a Parte 1 (`testImplementation`, ao lado de `spring-boot-starter-test`). É a biblioteca que integra o Spring REST Docs com o `MockMvc` (apresentado na Seção 9.8) — sem ela, não existiriam os métodos estáticos usados dentro do teste, como `document(...)` e `documentationConfiguration(...)`.

> **Divergência encontrada no código-fonte real desta etapa:** conferindo o `build.gradle` real, a linha `testImplementation 'org.springframework.restdocs:spring-restdocs-mockmvc'` aparece **duplicada** — a mesma dependência declarada duas vezes seguidas (com uma linha em branco entre elas), diferente do bloco único mostrado no README. Diferente das outras divergências já registradas neste tutorial, esta é inofensiva: o Gradle não rejeita dependências declaradas mais de uma vez na mesma configuração — ele simplesmente as trata como uma só, sem duplicar nada no classpath final. Não afeta a compilação, os testes, nem a geração da documentação; é, na prática, apenas uma linha redundante que poderia ser removida por organização, mas não por necessidade. Também é reafirmada, mais uma vez, a ausência da linha `testRuntimeOnly 'org.junit.platform:junit-platform-launcher'` no arquivo real (mesmo padrão de divergência já visto e explicado nos documentos dos Vídeos 06 e 08).

### 9.7. Ensinando a task `test` a gerar os *snippets*, e criando a task `asciidoctor`

Duas novas configurações de *task* do Gradle fecham a preparação do `build.gradle`:

```groovy
tasks.named('test') { Task it ->
    useJUnitPlatform()
    outputs.dir snippetsDir
}

tasks.named('asciidoctor') { Task it ->
    configurations "asciidoctorExtensions"
    inputs.dir snippetsDir
    dependsOn test
}
```

- **`tasks.named('test') { ... }`** — é uma forma do Gradle de dizer "pegue a *task* já existente chamada `test` (a que roda os testes, já configurada desde a Parte 1 com `jvmArgs` e `testLogging`) e aplique esta configuração adicional a ela". Diferente de criar uma task nova, isso **complementa** a configuração já existente — o projeto passa a ter dois blocos separados configurando a mesma task `test` (o bloco antigo, mais a `tasks.named('test') {...}` novo), e o Gradle aplica ambos, sem conflito, no momento em que a task é executada.
- **`outputs.dir snippetsDir`** — declara, para o Gradle, que a task `test` produz arquivos dentro do diretório `snippetsDir` (definido na Seção 9.3) como parte de sua saída. Isso é usado pelo Gradle para saber quando é necessário rodar a task de novo (se os testes já rodaram e nada mudou, o Gradle pode pular a execução, reaproveitando a saída anterior — um mecanismo chamado *incremental build*, já visto em ação nos logs `UP-TO-DATE` do Vídeo 09, Seção 9.9).
- **`tasks.named('asciidoctor') { ... }`** — mesma ideia, mas configurando a task `asciidoctor`, criada automaticamente pelo plugin adicionado na Seção 9.2.
- **`configurations "asciidoctorExtensions"`** — instrui a task `asciidoctor` a carregar, como extensões do processador Asciidoctor, tudo o que estiver declarado na configuration `asciidoctorExtensions` (Seções 9.4 e 9.6) — no caso, a extensão de integração com o Spring REST Docs.
- **`inputs.dir snippetsDir`** — o contrário de `outputs.dir`, visto acima: declara que a task `asciidoctor` **lê** arquivos de dentro de `snippetsDir` como parte de sua entrada — fazendo sentido, já que é exatamente o diretório onde a task `test` deposita os *snippets* gerados pelos testes.
- **`dependsOn test`** — declara uma dependência explícita entre tasks do Gradle: sempre que a task `asciidoctor` for executada, o Gradle garante que a task `test` rode antes dela (e, se os testes falharem, a task `asciidoctor` nem chega a rodar). Faz todo sentido dentro da filosofia do Spring REST Docs (Seção 9.1): a documentação depende dos testes terem passado, então gerar a documentação sem rodar os testes primeiro não faria sentido.

### 9.8. Gerando a classe `TaskControllerTest`

Com o `build.gradle` pronto, a aula usa um recurso do IntelliJ para criar o esqueleto de uma nova classe de teste: com o cursor dentro do `TaskController`, o menu de contexto (clique direito → *Generate...* → *Test...*) abre um assistente que permite escolher a biblioteca de testes (JUnit 5) e os métodos do controller para os quais gerar stubs de teste. O resultado inicial é uma classe vazia:

```java
package dio.taskmanager.infrastructure.http;

import static org.junit.jupiter.api.Assertions.*;

class TaskControllerTest {

}
```

A partir daqui, o restante da aula é a construção manual, passo a passo, dessa classe — a IDE apenas criou o arquivo e o pacote corretos; toda a lógica de teste (e de geração de documentação) é escrita à mão.

### 9.9. Anotando a classe: `@ExtendWith` e `@SpringBootTest`

```java
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
class TaskControllerTest {
```

- **`@SpringBootTest`** — anotação do Spring Boot que sobe o contexto completo da aplicação (todos os beans configurados — controllers, use cases, repositório — exatamente como aconteceria rodando a aplicação de verdade) especificamente para o ambiente de teste. É o que torna este teste um **teste de integração**: diferente de um teste unitário (como os já existentes, desde a Parte 3, para `CreateTaskUseCase` e `InMemoryTaskRepository`, que testam uma única classe isoladamente, sem envolver o Spring), aqui o objetivo é verificar que várias camadas da aplicação — controller, use cases, repositório — funcionam corretamente **juntas**, através de requisições HTTP simuladas.
- **`@ExtendWith({...})`** — anotação padrão do JUnit 5 para registrar uma ou mais **extensões**: mecanismos que adicionam comportamento extra ao ciclo de vida de um teste (por exemplo, executar código antes/depois de cada teste, ou injetar parâmetros especiais nos métodos de teste). Diferente de `@BeforeEach`/`@Test` (já vistos desde a Parte 3, e que o próprio desenvolvedor escreve), uma extensão é uma peça de comportamento pronta, fornecida por uma biblioteca externa, que é apenas "conectada" à classe de teste através dessa anotação.
  - **`SpringExtension.class`** — a extensão que conecta o JUnit 5 ao ecossistema do Spring, permitindo que anotações como `@Autowired` (Seção 9.10) funcionem dentro de uma classe de teste. Na prática, `@SpringBootTest` já registra essa extensão automaticamente por baixo dos panos — declará-la aqui de forma explícita é redundante, mas inofensivo, e comum em exemplos de configuração do Spring REST Docs (a própria documentação oficial da biblioteca recomenda essa forma).
  - **`RestDocumentationExtension.class`** — a extensão específica do Spring REST Docs, responsável por preparar o ambiente necessário para capturar as requisições e respostas de teste e transformá-las nos *snippets* de documentação (Seção 9.12).

### 9.10. Injetando o `MockMvc`, o `WebApplicationContext` e o `ObjectMapper`

```java
class TaskControllerTest {

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp(RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

}
```

- **`MockMvc`** — classe do Spring especializada em testes de controllers: permite simular requisições HTTP completas (com verbo, cabeçalhos, corpo) contra os controllers da aplicação, e verificar as respostas, **sem precisar subir um servidor HTTP real** (nenhuma porta é aberta, nenhuma conexão de rede acontece de fato — tudo roda em memória, dentro do próprio processo de teste). É a ferramenta que, na prática, "chama" os endpoints do `TaskController` durante o teste, no lugar de uma ferramenta externa como o HTTP Client do IntelliJ, já usado manualmente em vídeos anteriores.
- **`WebApplicationContext`** — uma especialização do contexto de aplicação do Spring (o "container" que guarda todos os beans, já mencionado informalmente em vídeos anteriores), voltada especificamente para aplicações web — carrega, além dos beans comuns, os componentes ligados ao processamento de requisições HTTP (como os próprios controllers). É a partir dele que o `MockMvc` é construído.
- **`@Autowired WebApplicationContext webApplicationContext`** — reaproveita a anotação `@Autowired` (já conhecida desde o Vídeo 05) para pedir ao Spring que injete, neste campo, o contexto de aplicação inteiro da aplicação de teste.
- **`ObjectMapper`** — classe da biblioteca Jackson (já mencionada, em relação à serialização automática de JSON, desde o Vídeo 06) usada para converter, manualmente, objetos Java em texto JSON (e vice-versa). Enquanto nos controllers reais essa conversão acontece de forma automática e invisível (por trás de `@RequestBody`/`@RestController`), dentro de um teste com `MockMvc` é preciso montar o corpo da requisição como uma `String` de JSON explicitamente — e é o `ObjectMapper` quem faz essa conversão (Seção 9.11).
- **`@BeforeEach void setUp(RestDocumentationContextProvider restDocumentation) { ... }`** — reaproveita `@BeforeEach` (já conhecido desde a Parte 3) para preparar o `mockMvc` antes de cada teste. O parâmetro `RestDocumentationContextProvider` é injetado automaticamente pela extensão `RestDocumentationExtension` (Seção 9.9) — é o mecanismo específico do Spring REST Docs para rastrear informações do contexto de documentação (como o diretório de saída dos *snippets*) ao longo da execução do teste.
- **`MockMvcBuilders.webAppContextSetup(webApplicationContext)`** — método estático que inicia a construção de um `MockMvc`, a partir do contexto de aplicação completo (`webApplicationContext`) — ou seja, o `MockMvc` resultante terá acesso a todos os controllers, use cases e demais beans reais da aplicação, exatamente como ela rodaria em produção.
- **`.apply(documentationConfiguration(restDocumentation))`** — "conecta" a configuração do Spring REST Docs ao `MockMvc` sendo construído, através do método estático `documentationConfiguration(...)`. É esse encadeamento que faz o `MockMvc`, a partir daqui, também gerar os *snippets* de documentação sempre que o método `document(...)` (Seção 9.12) for usado em uma requisição de teste.
- **`.build()`** — finaliza a configuração e produz a instância de `MockMvc`, atribuída ao campo `mockMvc`, pronta para ser usada dentro dos métodos de teste.

> **Nota sobre a evolução deste bloco ao longo da aula:** o README mostra uma primeira versão em que o `WebApplicationContext` é recebido diretamente como **parâmetro** do próprio método `setUp(...)` (em vez de um campo `@Autowired`), e uma anotação `import org.springframework.test.context.junit.jupiter.SpringExtension;` que aparece duplicada nos imports dessa etapa intermediária. Essa primeira versão gerava um erro do JUnit 5 (parâmetros de método de teste precisam ser resolvidos por um `ParameterResolver` registrado, e o `WebApplicationContext` não tem um por padrão), o que levou à correção mostrada acima — anotada, no próprio README, com o comentário `// <-- Removido o context daqui`. O código-fonte real, conferido no `.zip` desta etapa, já reflete diretamente essa versão corrigida (a versão com o campo `@Autowired`), sem nenhum resquício da tentativa anterior.

### 9.11. Montando o corpo da requisição de teste

```java
@Test
void should_save_and_retrieve_task_by_id() throws Exception {
    Map<String, String> taskRequest = new HashMap<>();
    taskRequest.put("title", "Aprender Spring RestDocs");
    taskRequest.put("description", "Ler o guia oficial do Spring");
```

- **`Map<String, String> taskRequest = new HashMap<>();`** — reaproveita o `HashMap` (já apresentado no Vídeo 08, dentro do `GlobalExceptionHandler`) para montar, "na mão", os dados que serão enviados como corpo da requisição de criação — uma alternativa mais simples do que instanciar diretamente um `CreateTaskRequest` (o `record` visto no Vídeo 06), já que o objetivo aqui é simular exatamente o JSON que um cliente real enviaria, e não construir um objeto Java tipado.
- **`taskRequest.put("title", ...)` / `.put("description", ...)`** — preenche o mapa com as chaves que correspondem exatamente aos nomes dos campos esperados pelo `CreateTaskRequest` no lado do servidor (`title`, `description`), já que é assim que o Jackson (Seção 9.10) vai interpretá-los ao desserializar o JSON gerado a partir deste mapa.

### 9.12. A requisição de criação, com geração de documentação

```java
    String responseJson = this.mockMvc.perform(
                    post("/tasks")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(taskRequest))
            )
            .andExpect(status().isCreated())
            .andDo(document("create-task",
                    requestFields(
                            fieldWithPath("title").description("Título da tarefa"),
                            fieldWithPath("description").description("Descrição detalhada")
                    ),
                    responseFields(
                            fieldWithPath("id").description("Identificador único da tarefa"),
                            fieldWithPath("title").description("Título da tarefa"),
                            fieldWithPath("description").description("Descrição detalhada"),
                            fieldWithPath("status").description("Status da tarefa")
                    )
            ))
            .andReturn()
            .getResponse()
            .getContentAsString();
```

- **`this.mockMvc.perform(post("/tasks")...)`** — o método `perform(...)` do `MockMvc` executa uma requisição simulada; `post("/tasks")` (um método estático, importado de `RestDocumentationRequestBuilders`, e não de `MockMvcRequestBuilders` — ponto retomado com mais detalhe na Seção 9.14) monta uma requisição `POST` para o caminho `/tasks`, o mesmo endpoint já conhecido desde o Vídeo 06.
- **`.contentType(MediaType.APPLICATION_JSON)`** — declara, no cabeçalho da requisição simulada, que o corpo enviado é JSON — o equivalente, dentro do teste, ao cabeçalho `Content-Type: application/json` que uma ferramenta como o HTTP Client do IntelliJ já enviava implicitamente nas requisições manuais dos vídeos anteriores.
- **`.content(objectMapper.writeValueAsString(taskRequest))`** — usa o `ObjectMapper` (Seção 9.10) para converter o `Map<String, String>` montado na Seção 9.11 em uma `String` de JSON (`writeValueAsString`), e define essa string como o corpo da requisição.
- **`.andExpect(status().isCreated())`** — verifica se o código de status da resposta é **201 Created**. `status()` e `isCreated()` são métodos de uma API de asserções fluente do `MockMvc`, desenhada para ler quase como uma frase em inglês ("expect status is created").
- **`.andDo(document("create-task", ...))`** — o coração da integração com o Spring REST Docs: `document(...)` (importado estaticamente de `MockMvcRestDocumentation`) recebe um identificador para os *snippets* gerados (aqui, `"create-task"`, que define o nome da subpasta dentro de `build/generated-snippets`, como visto na Seção 9.15) e uma lista de descritores de documentação.
  - **`requestFields(...)`** — descreve os campos esperados no **corpo da requisição**, cada um through um `fieldWithPath("nome").description("...")`. Essas descrições viram, depois, a tabela de campos documentada no arquivo `request-fields.adoc` (Seção 9.15).
  - **`responseFields(...)`** — mesma ideia, mas para os campos do **corpo da resposta**.
  - **`fieldWithPath("...")`** — identifica um campo específico do JSON pelo seu caminho (aqui, sempre um campo de primeiro nível, como `"title"` ou `"id"` — a mesma sintaxe suportaria caminhos aninhados, como `"endereco.cidade"`, embora isso não seja necessário para este DTO).
  > **Por que documentar `requestFields` e `responseFields` separadamente do teste em si?** Porque o Spring REST Docs verifica, em tempo de execução, se **todos** os campos presentes no JSON real (requisição ou resposta) foram, de fato, descritos — se um campo aparecer no JSON sem uma descrição correspondente (ou vice-versa), o teste falha. Isso cria uma garantia poderosa: a documentação gerada nunca fica "desatualizada" em relação ao formato real dos dados, porque, se ficasse, o próprio teste (e, consequentemente, o build inteiro, graças ao `dependsOn test` da Seção 9.7) pararia de passar.
- **`.andReturn().getResponse().getContentAsString()`** — encerra a cadeia de chamadas do `MockMvc`, capturando a resposta HTTP recebida (`getResponse()`) e extraindo seu corpo como uma `String` (`getContentAsString()`) — necessário para o próximo passo (Seção 9.13), que precisa ler o `id` gerado dentro dessa resposta.

### 9.13. Extraindo o ID gerado com `JsonPath`, e testando a busca por ID

```java
    String generatedId = JsonPath.read(responseJson, "$.id");

    this.mockMvc.perform(
                    get("/tasks/{id}", generatedId)
                            .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(generatedId))
            .andExpect(jsonPath("$.title").value("Aprender Spring RestDocs"))
            .andDo(document("get-task-by-id",
                    pathParameters(
                            parameterWithName("id").description("Identificador único da tarefa")
                    ),
                    responseFields(
                            fieldWithPath("id").description("Identificador único da tarefa"),
                            fieldWithPath("title").description("Título da tarefa"),
                            fieldWithPath("description").description("Descrição detalhada"),
                            fieldWithPath("status").description("Status da tarefa")
                    )
            ));
}
```

- **`JsonPath.read(responseJson, "$.id")`** — usa a biblioteca `JsonPath` (trazida de forma transitiva pelo `spring-boot-starter-test`, já declarado desde a Parte 1) para extrair um valor específico de dentro de uma `String` JSON, através de uma expressão de caminho. `"$.id"` significa "a partir da raiz do documento (`$`), pegue o campo `id`" — o mesmo `id` gerado automaticamente pelo servidor ao criar a tarefa na requisição anterior (Seção 9.12), e que o teste não teria como prever de antemão.
- **`get("/tasks/{id}", generatedId)`** — monta uma requisição `GET`, desta vez usando um *placeholder* (`{id}`) no caminho, com o valor real (`generatedId`) passado como argumento adicional — o próprio `MockMvc` se encarrega de substituir `{id}` pelo valor fornecido antes de montar a URL final da requisição simulada.
- **`.andExpect(jsonPath("$.id").value(generatedId))`** — verifica, usando a mesma sintaxe de `JsonPath` (agora através do método estático `jsonPath(...)`, uma integração específica do `MockMvc` com essa biblioteca), que o campo `id` da resposta é exatamente igual ao `id` que havia sido gerado na criação — confirmando que a tarefa encontrada é, de fato, a mesma que foi criada.
- **`.andDo(document("get-task-by-id", pathParameters(...), responseFields(...)))`** — gera os *snippets* deste segundo endpoint, desta vez usando `pathParameters(...)` em vez de `requestFields(...)` (já que uma requisição `GET` por ID não tem corpo, apenas um parâmetro de caminho) — `parameterWithName("id").description(...)` descreve exatamente esse parâmetro, capturado pelo `@PathVariable UUID id` do lado do `TaskController` (Vídeo 07).

### 9.14. Por que `RestDocumentationRequestBuilders`, e não `MockMvcRequestBuilders`

Um detalhe sutil, mas essencial para o teste funcionar corretamente com a geração de `pathParameters`: os métodos estáticos `get(...)` e `post(...)`, usados nas Seções 9.12 e 9.13, não vêm da classe mais comumente usada em testes `MockMvc` "simples" (`MockMvcRequestBuilders`), e sim de uma classe equivalente, específica do Spring REST Docs: `RestDocumentationRequestBuilders`. O próprio README destaca essa troca com um comentário no código:

```java
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;  // <-- Corrigido para RestDocs
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post; // <-- Corrigido para RestDocs
```

O motivo é que `RestDocumentationRequestBuilders.get("/tasks/{id}", generatedId)`, diferente de sua contraparte "genérica", preserva a informação do *template* da URL (o padrão `/tasks/{id}`, com a chave `{id}` ainda visível) junto com a requisição — informação que o `pathParameters(...)` (Seção 9.13) precisa para conseguir gerar corretamente o *snippet* de parâmetros de caminho. Usando a versão "genérica" de `MockMvcRequestBuilders`, a URL já chegaria totalmente substituída (`/tasks/82f2-...`), sem nenhum registro de que aquele segmento era, originalmente, um parâmetro — e a geração da documentação de `pathParameters` falharia.

### 9.15. Rodando o teste, corrigindo o status 200 → 201, e os *snippets* gerados

Na primeira execução do teste, ele falhava: o endpoint de criação, até aquele ponto, retornava **200 OK** (o padrão do Spring para qualquer método que não lance exceção e não tenha `@ResponseStatus`, como já visto no Vídeo 07), mas o teste esperava **201 Created** (`.andExpect(status().isCreated())`, Seção 9.12) — o código de status correto, em REST, para uma operação que efetivamente cria um novo recurso. A correção foi adicionar `@ResponseStatus(HttpStatus.CREATED)` ao método `create()`, do `TaskController`:

```java
@PostMapping
@ResponseStatus(HttpStatus.CREATED)
TaskResponse create(@RequestBody @Valid CreateTaskRequest request) {
    var input = request.toInput();
    var output = createTaskUseCase.execute(input);
    return TaskResponse.from(output);
}
```

- **`@ResponseStatus(HttpStatus.CREATED)`** — mesmo mecanismo já apresentado no Vídeo 07 (usado ali para `HttpStatus.NO_CONTENT`, no `delete()`): `HttpStatus.CREATED` é a constante do `enum` `HttpStatus` para o código **201**, a resposta correta para uma criação bem-sucedida. É interessante notar que esse ajuste só foi necessário nesta etapa: durante todos os testes manuais dos Vídeos 06, 07 e 08, o código de status **200** (o padrão) nunca chamou atenção como um problema — foi só ao formalizar o comportamento esperado em um teste automatizado (`.andExpect(status().isCreated())`) que a inconsistência ficou evidente. É um bom exemplo prático do valor de se ter testes automatizados: eles capturam expectativas de forma explícita, coisas que passariam despercebidas em testes manuais mais informais.

Com essa correção, o teste passa (`PASSED`), o build é concluído com sucesso (`BUILD SUCCESSFUL`), e, como efeito colateral da execução da task `test` (agora configurada para depositar sua saída em `snippetsDir`, Seção 9.7), uma série de arquivos `.adoc` é gerada automaticamente dentro de `build/generated-snippets/create-task/` e `build/generated-snippets/get-task-by-id/` — confirmado, no `.zip` desta etapa, pela presença real desses arquivos:

```
build/generated-snippets/create-task/
├── curl-request.adoc        (exemplo de comando cURL equivalente à requisição)
├── http-request.adoc        (a requisição HTTP crua, com cabeçalhos e corpo)
├── http-response.adoc       (a resposta HTTP crua, com cabeçalhos e corpo)
├── httpie-request.adoc      (exemplo de comando HTTPie equivalente à requisição)
├── request-body.adoc        (apenas o corpo JSON da requisição, isolado)
├── request-fields.adoc      (tabela com os campos da requisição e suas descrições)
├── response-body.adoc       (apenas o corpo JSON da resposta, isolado)
└── response-fields.adoc     (tabela com os campos da resposta e suas descrições)

build/generated-snippets/get-task-by-id/
├── curl-request.adoc
├── http-request.adoc
├── http-response.adoc
├── httpie-request.adoc
├── path-parameters.adoc     (tabela com os parâmetros de caminho e suas descrições)
├── request-body.adoc
├── response-body.adoc
└── response-fields.adoc
```

Cada um desses arquivos é gerado automaticamente pelo Spring REST Docs a partir da própria execução do teste — nenhum deles é escrito manualmente; eles são o "produto" do `document(...)` chamado na Seção 9.12/9.13, prontos para serem combinados dentro de um documento maior, como visto a seguir.

### 9.16. Criando a estrutura de documentação: `src/docs/asciidoc/index.adoc`

Com os *snippets* prontos, a atenção volta para a task `asciidoctor` (Seção 9.7), que espera encontrar um documento `.adoc` de entrada dentro do diretório `src` do projeto — ainda inexistente neste ponto. Pelo menu de contexto do IntelliJ, é criada a estrutura de pastas `src/docs/asciidoc/`, e dentro dela um novo arquivo, `index.adoc`, que serve como ponto de entrada da documentação — o documento "principal", que pode incluir outros arquivos `.adoc` (inclusive os próprios *snippets* gerados na Seção 9.15) através da diretiva `include::`.

```asciidoc
== Criar Tarefa

Cria uma nova tarefa.

=== Requisição

include::{snippets}/create-task/curl-request.adoc[]
```

- **`== Criar Tarefa`** — em AsciiDoc, o número de sinais de igual no início de uma linha define o nível do título (de forma parecida com o número de `#` no Markdown, já usado neste próprio tutorial). Um `==` é um título de segundo nível (o título do documento inteiro, se houvesse, seria `=`).
- **`include::{snippets}/create-task/curl-request.adoc[]`** — a diretiva `include::`, mencionada na Seção 9.1, importa o conteúdo de outro arquivo `.adoc` diretamente para dentro deste ponto do documento. `{snippets}` é uma variável de substituição que o Spring REST Docs/Asciidoctor resolve automaticamente para o caminho de `snippetsDir` (Seção 9.3) — ou seja, esta linha efetivamente "cola" o conteúdo de `build/generated-snippets/create-task/curl-request.adoc` (o exemplo de comando cURL gerado automaticamente na Seção 9.15) neste ponto exato do `index.adoc`.

O painel de pré-visualização do IntelliJ já mostra, a essa altura, o resultado renderizado: um título "Criar Tarefa" seguido do exemplo de requisição em cURL.

### 9.17. A documentação narrada como versão final (com ressalva sobre o `.zip` real)

O README segue narrando a expansão progressiva do `index.adoc` até uma versão bem mais completa: um documento chamado "API de Tarefas", com uma introdução, uma tabela descrevendo o modelo de dados `Task` (campos `id`, `title`, `description`, `status`), seções de criação e consulta de tarefas (reunindo os demais *snippets* gerados, como `request-fields.adoc` e `response-fields.adoc`), uma tabela de "Códigos de Status" cobrindo 200, 201, 204, 400 e 404, e um bloco final de observações gerais sobre a API (formato JSON, datas em ISO-8601, valores aceitos pelo campo `status`).

> **Divergência encontrada no código-fonte real desta etapa:** conferindo o arquivo `src/docs/asciidoc/index.adoc` realmente entregue neste `.zip`, seu conteúdo **não corresponde** a essa versão final elaborada — ele contém apenas a versão inicial e mais simples, mostrada na Seção 9.16 (a seção "Criar Tarefa", com o `include::` do `curl-request.adoc`), sem a introdução, a tabela do modelo de dados, as seções de consulta, a tabela de códigos de status ou o bloco de observações descritos no restante da narrativa da aula. O arquivo real tem, ao todo, apenas sete linhas, e curiosamente contém também um caractere solto de emoji (💡) em uma linha isolada, logo após o título "Requisição" — quase certamente um resíduo acidental de digitação (talvez um atalho de emoji do sistema operacional ou da IDE acionado sem querer durante a gravação), sem nenhuma função dentro do documento.
>
> Isso sugere que a expansão do `index.adoc` até a versão "API de Tarefas" completa, descrita nos últimos parágrafos da aula, foi feita ao vivo durante a gravação, mas não chegou a ser salva (ou não foi incluída) no `.zip` de apoio distribuído para esta etapa — de forma parecida com o que já se observou no `build.gradle` (Seção 9.2): a narrativa do vídeo vai além do que o código efetivamente entregue reflete. Como este documento segue o código-fonte real no checkpoint a seguir, o conteúdo mostrado ali é a versão simples de sete linhas, e não a versão elaborada apenas narrada. Fica como referência, para quem quiser reconstruir a versão completa por conta própria, a lista de elementos que ela deveria conter, descrita no parágrafo acima.

---

### ✅ Checkpoint — Estado do projeto ao final do Vídeo 09

Conferido diretamente contra o código-fonte real entregue em `taskmanager_ate_o_video_09.zip`.

**Estrutura de pastas (alterações em relação ao checkpoint do Vídeo 08, em negrito)**

```
taskmanager/
├── build.gradle                                        (alterado — plugin, dependências e tasks do Asciidoctor/RestDocs)
└── src/
    ├── main/java/dio/taskmanager/
    │   ├── TaskmanagerApplication.java                  (sem alterações)
    │   ├── application/                                 (sem alterações nesta etapa)
    │   ├── domain/                                       (sem alterações nesta etapa)
    │   ├── infrastructure/
    │   │   ├── http/
    │   │   │   ├── TaskController.java                    (alterado — @ResponseStatus(CREATED) em create())
    │   │   │   ├── GlobalExceptionHandler.java             (alterado — bug de anotação duplicada do Vídeo 08 corrigido ✅)
    │   │   │   ├── request/                                 (sem alterações)
    │   │   │   └── response/                                (sem alterações)
    │   │   └── repository/                                 (sem alterações)
    │   └── playground/                                     (sem alterações)
    ├── docs/asciidoc/
    │   └── index.adoc                                      ← novo (⚠️ ver nota abaixo — versão simples, não a "final" narrada)
    └── test/java/dio/taskmanager/
        ├── application/                                     (sem alterações)
        ├── domain/                                           (sem alterações)
        ├── infrastructure/http/
        │   └── TaskControllerTest.java                       ← novo
        └── infrastructure/repository/                        (sem alterações)
```

**build.gradle (versão final desta etapa, arquivo completo)**

```groovy
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.2.0'
    id 'io.spring.dependency-management' version '1.1.4'
    id("io.freefair.lombok") version "9.2.0"

    id 'org.asciidoctor.jvm.convert' version '4.0.5'
}

group = 'dio.taskmanager'
version = '0.0.1-SNAPSHOT'

repositories {
    mavenCentral()
}

ext {
    snippetsDir = file('build/generated-snippets')
}

configurations {
    asciidoctorExtensions
}

asciidoctorj {
    version = "3.0.0"
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter'
    implementation 'org.springframework.boot:spring-boot-starter-validation'

    implementation 'org.springframework.boot:spring-boot-starter-web'

    // Testes com Spring Boot + JUnit 5
    testImplementation 'org.springframework.boot:spring-boot-starter-test'

    asciidoctorExtensions 'org.springframework.restdocs:spring-restdocs-asciidoctor'
    testImplementation 'org.springframework.restdocs:spring-restdocs-mockmvc'

    testImplementation 'org.springframework.restdocs:spring-restdocs-mockmvc'

}

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

tasks.named('test') { Task it ->
    useJUnitPlatform()
    outputs.dir snippetsDir
}

tasks.named('asciidoctor') {
    configurations "asciidoctorExtensions"
    inputs.dir snippetsDir
    dependsOn test
}
```

> ⚠️ Como já detalhado na Seção 9.2, este arquivo **não** reflete o bloco `plugins`/`group`/`java { toolchain }` mostrado no início do README para esta etapa (que aponta Spring Boot `4.0.5`, `dependency-management` `1.1.7`, `group = 'dio'` e JDK 25) — o `group` permanece `dio.taskmanager`, as versões de Spring Boot e `dependency-management` permanecem as mesmas desde a Parte 1, e não existe bloco de `toolchain`. Note também a linha `testImplementation 'org.springframework.restdocs:spring-restdocs-mockmvc'` duplicada (Seção 9.6) — inofensiva, mas redundante — e a ausência, mais uma vez, de `testRuntimeOnly 'org.junit.platform:junit-platform-launcher'` (padrão já observado desde o Vídeo 06).

**TaskController.java (trecho alterado desta etapa)**

```java
@PostMapping
@ResponseStatus(HttpStatus.CREATED) // <-- Adicionado para retornar status 201 Created
TaskResponse create(@RequestBody @Valid CreateTaskRequest request) {
    var input = request.toInput();
    var output = createTaskUseCase.execute(input);
    return TaskResponse.from(output);
}
```

O restante da classe (`list()`, `read()`, `delete()`, `update()`, e o construtor com os cinco Use Cases) permanece idêntico ao checkpoint do Vídeo 08 — já corrigido desde aquela etapa, e sem nenhuma alteração adicional nesta.

**GlobalExceptionHandler.java (versão final desta etapa — bug do Vídeo 08 corrigido)**

```java
package dio.taskmanager.infrastructure.http;

import dio.taskmanager.domain.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
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
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
```

> ✅ **Boa notícia, comparando com o checkpoint do Vídeo 08:** as anotações `@ExceptionHandler(MethodArgumentNotValidException.class)` e `@ResponseStatus(HttpStatus.BAD_REQUEST)`, que estavam duplicadas (Seção 8.5 do documento anterior, um erro que impedia a compilação do projeto), aparecem aqui **corrigidas**, cada uma presente apenas uma vez. O README desta etapa não menciona essa correção em nenhum momento — não faz parte do conteúdo narrado do Vídeo 09 —, mas o `.zip` real confirma que o ajuste foi aplicado em algum momento entre as duas gravações.

**TaskControllerTest.java (arquivo novo, conteúdo completo)** — exatamente como mostrado ao longo das Seções 9.9 a 9.14, sem diferenças em relação ao código-fonte real (já na sua versão final, corrigida — ver nota da Seção 9.10 sobre a versão intermediária narrada, mas não presente no `.zip`).

```java
package dio.taskmanager.infrastructure.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;  // <-- Corrigido para RestDocs
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post; // <-- Corrigido para RestDocs
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
class TaskControllerTest {

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext; // <-- Injetado aqui para evitar erro no parâmetro do JUnit 5

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp(RestDocumentationContextProvider restDocumentation) { // <-- Removido o context daqui
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    void should_save_and_retrieve_task_by_id() throws Exception {
        Map<String, String> taskRequest = new HashMap<>();
        taskRequest.put("title", "Aprender Spring RestDocs");
        taskRequest.put("description", "Ler o guia oficial do Spring");

        String responseJson = this.mockMvc.perform(
                        post("/tasks")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(taskRequest))
                )
                .andExpect(status().isCreated())
                .andDo(document("create-task",
                        requestFields(
                                fieldWithPath("title").description("Título da tarefa"),
                                fieldWithPath("description").description("Descrição detalhada")
                        ),
                        responseFields(
                                fieldWithPath("id").description("Identificador único da tarefa"),
                                fieldWithPath("title").description("Título da tarefa"),
                                fieldWithPath("description").description("Descrição detalhada"),
                                fieldWithPath("status").description("Status da tarefa")
                        )
                ))
                .andReturn()
                .getResponse()
                .getContentAsString();

        String generatedId = JsonPath.read(responseJson, "$.id");

        this.mockMvc.perform(
                        get("/tasks/{id}", generatedId)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(generatedId))
                .andExpect(jsonPath("$.title").value("Aprender Spring RestDocs"))
                .andDo(document("get-task-by-id",
                        pathParameters(
                                parameterWithName("id").description("Identificador único da tarefa")
                        ),
                        responseFields(
                                fieldWithPath("id").description("Identificador único da tarefa"),
                                fieldWithPath("title").description("Título da tarefa"),
                                fieldWithPath("description").description("Descrição detalhada"),
                                fieldWithPath("status").description("Status da tarefa")
                        )
                ));
    }
}
```

**src/docs/asciidoc/index.adoc (conteúdo real e completo — versão simples, ver nota da Seção 9.17)**

```asciidoc
== Criar Tarefa

Cria uma nova tarefa.

=== Requisição
💡
include::{snippets}/create-task/curl-request.adoc[]
```

> ⚠️ Como detalhado na Seção 9.17, este é o conteúdo **real e integral** do arquivo (sete linhas, incluindo um caractere de emoji solto e aparentemente acidental) — não a versão "API de Tarefas" completa, com sumário, tabela de modelo de dados, seções adicionais, tabela de códigos de status e observações finais, narrada no restante do README para esta etapa.

**Arquivos sem nenhuma alteração nesta etapa:** todos os cinco Use Cases, toda a camada `domain`, `InMemoryTaskRepository`, `TaskmanagerApplication`, `CreateTaskRequest.java`, `UpdateTaskRequest.java`, `TaskResponse.java`, `Main.java` (o *playground*), e todos os testes existentes desde vídeos anteriores (`CreateTaskUseCaseTest`, `TaskRepositoryTest`, `InMemoryTaskRepositoryTest`).

> **Nota importante sobre o alcance desta etapa:** o único teste automatizado de integração criado até aqui (`should_save_and_retrieve_task_by_id`) cobre apenas dois dos cinco endpoints do `TaskController` — criação e busca por ID —, e apenas o "caminho feliz" (sem testar, por exemplo, os cenários de validação do Vídeo 08, ou o `404` de uma busca por um ID inexistente, já testado manualmente desde o Vídeo 07). Os endpoints de listagem, atualização e remoção continuam sem nenhum teste automatizado, e, consequentemente, sem nenhum *snippet* de documentação gerado para eles. Como visto na Seção 9.17, a documentação Asciidoctor final também está, nesta etapa, incompleta em relação ao que foi narrado na aula — o `index.adoc` real cobre apenas o início do endpoint de criação.

---

## Glossário de conceitos Java e Spring — complemento do Vídeo 09

Continuação dos glossários dos documentos anteriores — apenas os termos novos, introduzidos neste vídeo.

| Termo | Significado |
|---|---|
| Spring REST Docs | Biblioteca do ecossistema Spring para gerar documentação de APIs a partir de testes de integração — em vez de inspecionar anotações em tempo de execução (como o Swagger/OpenAPI), ela captura requisições e respostas reais simuladas durante os testes, gerando fragmentos de documentação (*snippets*). |
| AsciiDoc / Asciidoctor | AsciiDoc é um formato de texto para documentação técnica (parecido com o Markdown, mas com recursos adicionais, como a diretiva `include::`); Asciidoctor é a ferramenta que converte arquivos `.adoc` para formatos finais como HTML. |
| *Snippet* (de documentação) | Um fragmento de documentação gerado automaticamente (por exemplo, pelo Spring REST Docs) a partir da execução de um teste — como a requisição HTTP crua, a resposta, ou uma tabela de campos — pronto para ser incluído em um documento maior. |
| `ext { ... }` (Gradle) | Bloco usado no `build.gradle` para declarar variáveis personalizadas (*extra properties*) do próprio script de build, reaproveitáveis em outras partes do mesmo arquivo. |
| *Configuration* (Gradle) | Um "balde" nomeado de dependências dentro de um projeto Gradle (como `implementation` ou `testImplementation`, já conhecidos, ou um novo, criado sob medida, como `asciidoctorExtensions`), usado por plugins para saber quais bibliotecas aplicar em cada contexto. |
| `tasks.named('nome') { ... }` | Sintaxe do Gradle para aplicar configuração adicional a uma *task* já existente (identificada pelo nome), complementando qualquer configuração anterior já aplicada a ela, em vez de substituí-la. |
| `dependsOn` (Gradle) | Declaração, em uma *task* do Gradle, de que ela depende da execução prévia de outra *task* — o Gradle garante que a *task* dependida rode (e seja bem-sucedida) antes da que a declara. |
| Teste de integração | Um teste que verifica o comportamento de várias camadas da aplicação funcionando **em conjunto** (por exemplo, controller + use cases + repositório, através de requisições HTTP simuladas), em contraste com um teste unitário, que isola uma única classe. |
| `@SpringBootTest` | Anotação que sobe o contexto completo de beans da aplicação Spring Boot especificamente para um teste, permitindo testar a integração real entre várias camadas. |
| `@ExtendWith({...})` | Anotação do JUnit 5 usada para registrar uma ou mais extensões — mecanismos prontos, fornecidos por bibliotecas externas, que adicionam comportamento ao ciclo de vida de um teste. |
| `MockMvc` | Classe do Spring que permite simular requisições HTTP completas contra os controllers de uma aplicação, sem a necessidade de um servidor HTTP real rodando. |
| `WebApplicationContext` | Especialização do contexto de aplicação do Spring voltada a aplicações web, usada como base para a construção de um `MockMvc` a partir de todos os beans reais da aplicação. |
| `ObjectMapper` | Classe da biblioteca Jackson usada para converter manualmente objetos Java em JSON (e vice-versa) — útil em testes, onde essa conversão não acontece automaticamente como acontece nos controllers reais via `@RequestBody`/`@RestController`. |
| `JsonPath` | Biblioteca para extrair valores específicos de dentro de um texto JSON através de uma expressão de caminho (por exemplo, `"$.id"` para o campo `id` na raiz do documento). |
| `RestDocumentationRequestBuilders` vs. `MockMvcRequestBuilders` | Duas classes com métodos equivalentes (`get`, `post`, etc.) para montar requisições em testes `MockMvc`; a primeira, específica do Spring REST Docs, preserva o *template* original da URL (com `{placeholders}` intactos), necessário para gerar corretamente a documentação de `pathParameters`. |
| `document(...)` | Método estático do Spring REST Docs usado dentro de `.andDo(...)` para capturar uma requisição/resposta de teste e gerar seus *snippets* de documentação, associados a um identificador (usado como nome da subpasta de saída). |
| `requestFields(...)` / `responseFields(...)` / `pathParameters(...)` | Descritores do Spring REST Docs usados dentro de `document(...)` para documentar, respectivamente, os campos do corpo da requisição, os campos do corpo da resposta, e os parâmetros de caminho de uma URL. |
| `fieldWithPath(...)` / `parameterWithName(...)` | Métodos usados para identificar um campo (por caminho, dentro de `requestFields`/`responseFields`) ou um parâmetro de caminho (por nome, dentro de `pathParameters`), encadeados com `.description(...)` para fornecer sua descrição textual na documentação gerada. |

---
