## Instrutor

- Thiago Poiani (Principal Engineer at Skip)
- Contato Linkedin: / [thpoiani](https://www.linkedin.com/in/thpoiani/)

## Parte 1 - Introdução ao API REST

### 🟩 Vídeo 01 - Introdução ao API REST

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.01-video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/criando-sua-primeira-api-rest-com-spring-boot/learning/1ba44230-a11d-4192-ba3d-6c26a0346bd3?autoplay=1

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-08h03m14s255.jpg" alt="" width="840">
</p>

**Agenda do Curso**, organizada em três blocos: Fundamentos e Conceitos, Estudo de Caso (com três etapas de desenvolvimento) e Roadmap do Aluno. Essa estrutura antecipa o percurso da aula: primeiro serão discutidos os fundamentos de desenvolvimento web, HTTP e REST; em seguida, um estudo de caso prático de construção de uma API; e por fim, um conteúdo complementar com sugestões de estudo autônomo.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-08h03m31s373.jpg" alt="" width="840">
</p>

**"Criando sua Primeira API REST: Fundamentos Web"**, com o subtítulo "A arquitetura de comunicação por trás das aplicações modernas". Ele marca o início da explicação sobre os fundamentos de desenvolvimento web e o conceito de API REST, tema que será desenvolvido nos slides seguintes.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-08h03m38s374.jpg" alt="" width="840">
</p>

**Sistemas Distribuídos**: aplicações modernas (sites, apps mobile, wearables) não guardam seus próprios dados — elas funcionam como "vitrines" que consomem recursos e regras de negócio hospedados em um servidor remoto, na nuvem. A pergunta central colocada é como plataformas tão diferentes conseguem se comunicar com um único servidor, o que motiva a introdução do protocolo HTTP a seguir.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-08h04m34s596.jpg" alt="" width="840">
</p>

**Protocolo HTTP** como a "ponte universal" entre clientes e servidor remoto, garantindo que aplicativos e sites diferentes se entendam. A comunicação acontece por meio de uma Requisição (Request), enviada do cliente ao servidor, e de uma Resposta (Response), devolvida ao cliente. A analogia usada é a do navegador: ao acessar um site, o navegador atua como cliente, envia uma requisição HTTP GET ao servidor (via DNS e IP), recebe os dados de volta e os exibe na tela.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-08h04m55s595.jpg" alt="" width="840">
</p>

**Anatomia de uma Requisição** HTTP, destacando três elementos: o **Method** (o verbo, que define a ação desejada — ler, criar, atualizar ou deletar), a **Request URL** (o endereço do recurso no servidor) e os **Headers** (metadados como tokens de autenticação e o formato dos dados). O exemplo de pacote HTTP mostrado é:

```http
POST /api/tasks HTTP/1.1
Host: api.exemplo.com
Authorization: Bearer token123
Content-Type: application/json
```

Nesse exemplo, o método é `POST`, a URL é `/api/tasks`, e os headers incluem o `Host` (indicando o servidor/DNS), o `Authorization` (usado para autenticação e autorização) e o `Content-Type` (que indica o formato do conteúdo enviado no corpo da requisição), além da versão do protocolo, `HTTP/1.1`.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-08h05m01s650.jpg" alt="" width="840">
</p>

**JSON (JavaScript Object Notation)** como o "idioma oficial" para intercâmbio de dados, consolidado como padrão pela sua estrutura minimalista baseada em chaves e valores. O exemplo exibido é:

```json
{
  "id": 101,
  "task": "Estudar HTTP",
  "completed": false
}
```

São destacadas três vantagens do formato: **leveza** (sintaxe enxuta que consome menos banda de rede), **legibilidade** (estrutura previsível, fácil de ler e depurar) e **parseamento nativo** (interpretado de forma instantânea pelas linguagens modernas).

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-08h05m06s248.jpg" alt="" width="840">
</p>

O slide compara a **Evolução do Intercâmbio de Dados**, contrapondo XML/SOAP (o passado) ao JSON (o padrão atual). O SOAP é um paradigma mais antigo, que ainda é utilizado em alguns sistemas legados (integrações bancárias e corporativas), e seu pacote é estruturado em XML, com alta verbosidade devido ao uso de tags de abertura e fechamento. O exemplo de XML mostrado é:

```xml
<Task>
  <id>1</id>
  <name>Ler</name>
</Task>
```

Em contraste, o JSON equivalente é muito mais simplificado:

```json
{"id":1,"name":"Ler"}
```

Essa comparação evidencia por que o JSON se tornou mais leve, deixando o pacote mais enxuto e reduzindo o consumo de internet na transição da informação.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-08h05m10s610.jpg" alt="" width="840">
</p>

**As Regras do Jogo: Restrições RESTful**. O protocolo HTTP, por si só, permite enviar qualquer dado para qualquer lugar, sem semântica definida — o que gera caos arquitetônico. Para organizar essa comunicação, utiliza-se o padrão REST (Representational State Transfer): não é uma ferramenta ou biblioteca instalável, mas sim um **<s>padrão</s> ESTILO semântico arquitetural** 💡, que **impõe restrições sobre como usar verbos e códigos de status** para tornar o tráfego **previsível**. Um exemplo dessa semântica: uma requisição `POST` significa que está sendo criado um recurso no serviço.

> 💡**Nota de Ajuste Conceitual**: Embora o termo "padrão" seja frequentemente usado no mercado de forma coloquial, tecnicamente o REST (Representational State Transfer) não é um padrão formalizado por comitês (como o SOAP é), mas sim um **ESTILO** Arquitetural. Ele foi definido originalmente na tese de doutorado de Roy Fielding (ano 2000) como um conjunto de restrições e boas práticas (como statelessness e interface uniforme). O protocolo HTTP é a ferramenta que escolhemos para implementar esse estilo, utilizando seus métodos nativos (GET, POST, PUT, DELETE) para dar semântica às operações.<br>tags: #rest #restful #arquitetura

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-08h05m15s317.jpg" alt="" width="840">
</p>

**Vocabulário REST: Verbos HTTP**, associando cada verbo a uma ação semântica:

- **GET** — Ler/Recuperar: busca uma representação do recurso no servidor sem alterá-lo (operação segura).
- **POST** — Criar: envia dados (normalmente em JSON) para gerar um recurso inteiramente novo.
- **PUT** — Atualizar: substitui integralmente um recurso existente pelo novo payload fornecido (o PATCH também pode ser usado para atualização).
- **DELETE** — Remover: exclui permanentemente o recurso especificado do servidor remoto.

Assim, através dos verbos HTTP ficou definido como um servidor e um cliente podem se comunicar seguindo o padrão REST.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-08h05m19s093.jpg" alt="" width="840">
</p>

**O Feedback: Status Codes Semânticos**, que informam ao cliente o resultado de uma operação. Na série de **Sucesso (200)**: `200 OK` indica requisição processada com sucesso (padrão para GET e PUT); `201 CREATED` indica que um novo recurso foi gerado (padrão obrigatório para POST); `204 NO CONTENT` indica que a ação foi concluída, mas não há dados para devolver (o que acontece em um DELETE, por exemplo). Já na série de **Erro do Cliente (400)**: `400 BAD REQUEST` indica algum problema de validação, como um JSON malformado ou com campos faltando; `404 NOT FOUND` indica que a URL requisitada não aponta para nenhum recurso existente no servidor. Além dessas, também existem os erros de servidor da série 500 (como 503 e 504), que indicam falhas do servidor ou problemas de rede.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-08h05m23s459.jpg" alt="" width="840">
</p>

**Síntese: A Requisição Perfeita**, resumindo o ciclo de vida completo de uma comunicação web em cinco etapas: (1) **O Gatilho** — um app mobile inicia a chamada; (2) **O Veículo** — um método HTTP (verbo) carrega um payload em JSON; (3) **A Ponte** — o tráfego passa pela rede via protocolo HTTP; (4) **O Servidor** — o processamento é validado pelas restrições do padrão REST; (5) **O Retorno** — o cliente recebe a resposta com o status code correspondente. Essa sequência orquestrada resume, de forma integrada, tudo o que foi visto sobre o gatilho pelo cliente (celular ou navegador), a transição do JSON via requisição HTTP até chegar ao servidor, que interpreta o padrão e devolve a resposta ao cliente.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-08h05m27s653.jpg" alt="" width="840">
</p>

**O Fator Spring Boot: Abstração de Infraestrutura**, mostrando o framework como um "escudo" que elimina o trabalho braçal de infraestrutura para que o desenvolvedor foque apenas no problema do cliente. Por baixo desse escudo ficam tarefas complexas como o gerenciamento de sockets TCP e o parsing manual de JSON para objetos Java — dores que o Spring Boot resolve automaticamente. Assim, ao usar o Spring Boot não é preciso criar manualmente um servidor: através de anotações e dependências, ele já cria um servidor configurado seguindo boas práticas, cuida da conversão de JSON em objetos Java (usando bibliotecas internas como o Jackson) e, através de anotações e filtros, entende qual requisição HTTP chegou e qual método de qual classe deve executá-la.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-08h05m34s227.jpg" alt="" width="840">
</p>

**Resumo da Arquitetura & Próximos Passos**, reforçando os quatro pilares vistos até aqui: Sistemas Distribuídos (a separação física entre clientes e servidores), HTTP & JSON (a ponte universal e o idioma padrão leve para dados), Semântica RESTful (verbos organizando ações e status codes dando feedback) e Spring Boot (a abstração robusta de sockets e parsing pesado). Como próxima fase, o slide anuncia que, com a ponte de comunicação já construída, o próximo passo será definir o que vai trafegar por ela — iniciando o Design de Domínio e a Modelagem de Entidades.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-08h05m37s262.jpg" alt="" width="840">
</p>

**Design de API do Estudo de Caso: Sistema To-Do**. Na parte de Modelagem do Domínio, a entidade `Task` é definida com ID, Título, Descrição e Estado, sendo os estados possíveis Pendente, Em Progresso e Concluído — aplicando o foco no negócio (DDD) para isolar a lógica da tarefa de preocupações externas como Web e banco de dados. Na parte de Arquitetura e Recursos, o endpoint principal `/tasks` mapeia as ações de negócio para a semântica REST: Listar Tarefas → `GET`, Criar Tarefa → `POST` e Remover Tarefa → `DELETE`, com uma estratégia de persistência que separa o backend (orquestração) da listagem e do salvamento de dados. Esse design marca a transição da ideia para o design técnico da aplicação que será construída, uma entidade de tarefa com ID, título, descrição e estado (pendente, em progresso e concluído), sobre a qual serão implementadas as operações de transição de estado, atualização, criação, listagem e remoção de tarefas — inicialmente sem persistência real, utilizando um repositório em memória com foco em REST e Domain Driven Design.
      

## Parte 2 - Gerenciamento de tarefas

### 🟩 Vídeo 02 - Gerenciamento de tarefas

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.01-video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/criando-sua-primeira-api-rest-com-spring-boot/learning/de99d3a8-04bd-47be-964b-ec807a35c99d?autoplay=1

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/2026-07-08-13-15-34.png" alt="" width="840">
</p>

#### Estrutura de pacotes do projeto (Domain, Application e Infrastructure)

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-09h11m44s141.jpg" alt="" width="840">
</p>

O projeto **Task Manager**, criado com Spring Boot, é organizado seguindo os princípios do Domain Driven Design em três pacotes principais dentro de `dio.taskmanager`: `application` (orquestração de chamadas e serviços), `domain` (onde vivem as regras de negócio) e `infrastructure` (integrações externas, como banco de dados e APIs). A classe de entrada `TaskmanagerApplication` permanece com a estrutura padrão gerada pelo Spring Initializr:

```java
package dio.taskmanager;

import ...

@SpringBootApplication
public class TaskmanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskmanagerApplication.class, args);
    }

}
```

Essa separação em camadas é o ponto de partida da arquitetura: o desenvolvimento começa pelo domínio, pois é essa camada que concentra as regras de negócio e reduz o viés técnico na construção da aplicação.

#### Criação da classe Task no pacote domain

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-09h12m32s947.jpg" alt="" width="840">
</p>

Dentro do pacote `domain`, é criada a primeira classe da aplicação: a entidade **Task**, que representa uma tarefa. O IntelliJ exibe o diálogo de criação de nova classe Java, com a opção `Class` selecionada, marcando o início da modelagem do domínio.

#### Definição inicial dos atributos da classe Task

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-09h12m47s196.jpg" alt="" width="840">
</p>

A classe `Task` recebe seus primeiros atributos: identificador, título, descrição e status. O status é tipado como `TaskStatus`, um enum que será criado em seguida, permitindo maior controle sobre os estados possíveis da tarefa.

```java
package dio.taskmanager.domain;

public class Task {
    private String id;
    private String title;
    private String description;
    private TaskStatus status;
}
```

#### Criação do enum TaskStatus

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-09h13m02s099.jpg" alt="" width="840">
</p>

Ainda no pacote `domain`, é aberto o diálogo de criação de nova classe Java para o tipo `TaskStatus`, desta vez com a opção `Enum` selecionada. Modelar o status como um enum facilita o controle dos estados válidos da tarefa e prepara o terreno para uma futura máquina de estados.

#### Estados possíveis de uma tarefa

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-09h13m14s414.jpg" alt="" width="840">
</p>

O enum `TaskStatus` é definido com três valores possíveis para representar o ciclo de vida de uma tarefa:

```java
package dio.taskmanager.domain;

public enum TaskStatus {
    PENDING,
    IN_PROGRESS,
    COMPLETED,
}
```

#### Criação do record TaskId

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-09h14m35s335.jpg" alt="" width="840">
</p>

Um novo tipo é criado no pacote `domain`, desta vez como um **record**: `TaskId`. A escolha de um objeto próprio para o identificador (em vez de usar diretamente `String` ou `Long`) visa dar extensibilidade ao modelo — por exemplo, permitindo transitar apenas a identidade da tarefa entre módulos futuros, ou deixar mais explícito, em uma API REST, o que exatamente está sendo buscado por um endpoint.

#### Declaração do record TaskId com a propriedade id

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-09h16m53s114.jpg" alt="" width="840">
</p>

O `TaskId` é declarado como um record contendo uma única propriedade `id`, do tipo `UUID`:

```java
package dio.taskmanager.domain;

import java.util.UUID;

public record TaskId(UUID id) {
}
```

Por ser um record, essa classe já ganha, de forma implícita, um construtor canônico gerado pelo próprio Java, além de ser imutável por natureza — características que ajudam a garantir que o identificador de uma tarefa nunca mude após sua criação.

#### Construtor compacto com validação do id

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-09h17m56s998.jpg" alt="" width="840">
</p>

É adicionado um **construtor compacto** ao record `TaskId`, responsável por validar que o `id` recebido nunca seja nulo, utilizando a classe utilitária `Assert` do Spring:

```java
package dio.taskmanager.domain;

import org.springframework.util.Assert;

import java.util.UUID;

public record TaskId(UUID id) {
    public TaskId {
        Assert.notNull(id, "id must not be null");
    }
}
```

#### Construtor canônico vazio com geração automática do UUID

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-09h18m15s541.jpg" alt="" width="840">
</p>

Um segundo construtor é adicionado ao `TaskId`, desta vez sem parâmetros, delegando para o construtor compacto um `UUID` gerado aleatoriamente. Assim, sempre que uma tarefa for criada sem um identificador explícito, o próprio `TaskId` se encarrega de gerar um valor único:

```java
package dio.taskmanager.domain;

import org.springframework.util.Assert;

import java.util.UUID;

public record TaskId(UUID id) {
    public TaskId {
        Assert.notNull(id, "id must not be null");
    }

    public TaskId() {
        this(UUID.randomUUID());
    }
}
```

#### Primeira versão do construtor de Task, recebendo o TaskId

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-09h18m55s796.jpg" alt="" width="840">
</p>

De volta à classe `Task`, o atributo `id` passa a ser do tipo `TaskId`, e um construtor básico é criado recebendo todos os atributos, incluindo o identificador, como parâmetros:

```java
package dio.taskmanager.domain;

public class Task {
    private TaskId id;
    private String title;
    private String description;
    private TaskStatus status;

    public Task(TaskId id, String title, String description, TaskStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }
}
```

#### Ajuste do construtor para que o id seja gerado internamente

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-09h19m22s980.jpg" alt="" width="840">
</p>

O construtor de `Task` é ajustado para não receber mais o `id` como parâmetro externo. Em vez disso, o próprio construtor instancia um novo `TaskId`, garantindo que toda tarefa criada já nasça com um identificador válido:

```java
public Task(String title, String description, TaskStatus status) {
    this.id = new TaskId();
    this.title = title;
    this.description = description;
    this.status = status;
}
```

#### Validação do título com Assert.notNull

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-09h19m45s328.jpg" alt="" width="840">
</p>

Uma validação é adicionada ao construtor de `Task` para garantir que o título não seja nulo, seguindo o mesmo padrão já aplicado em `TaskId`:

```java
package dio.taskmanager.domain;

import org.springframework.util.Assert;

public class Task {
    private TaskId id;
    private String title;
    private String description;
    private TaskStatus status;

    public Task(String title, String description, TaskStatus status) {
        Assert.notNull(title, "Title must not be null");

        this.id = new TaskId();
        this.title = title;
        this.description = description;
        this.status = status;
    }
}
```

#### Descrição como campo opcional com Optional&lt;String&gt;

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-09h20m08s787.jpg" alt="" width="840">
</p>

O atributo `description` passa a ser do tipo `Optional<String>`, refletindo o cenário em que a descrição de uma tarefa não é obrigatória e pode não ser enviada em uma requisição:

```java
package dio.taskmanager.domain;

import org.springframework.util.Assert;

import java.util.Optional;

public class Task {
    private TaskId id;
    private String title;
    private Optional<String> description;
    private TaskStatus status;

    public Task(String title, Optional<String> description, TaskStatus status) {
        Assert.notNull(title, "Title must not be null");

        this.id = new TaskId();
        this.title = title;
        this.description = description;
        this.status = status;
    }
}
```

#### Status padrão PENDING na criação da tarefa

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-09h20m46s419.jpg" alt="" width="840">
</p>

Por fim, o parâmetro `status` é removido do construtor de `Task`: como toda tarefa recém-criada começa no mesmo estado, o próprio construtor define o valor padrão `TaskStatus.PENDING`. Com isso, fecha-se a primeira versão da entidade `Task`, já com `TaskId`, título obrigatório, descrição opcional e status inicial definido internamente:

```java
package dio.taskmanager.domain;

import org.springframework.util.Assert;

import java.util.Optional;

public class Task {
    private TaskId id;
    private String title;
    private Optional<String> description;
    private TaskStatus status;

    public Task(String title, Optional<String> description) {
        Assert.notNull(title, "Title must not be null");

        this.id = new TaskId();
        this.title = title;
        this.description = description;
        this.status = TaskStatus.PENDING;
    }
}
```

- Arquivos do projeto nesta etapa: [./000-Midia_e_Anexos/etapas_do_codigo/taskmanager_ate_o_video_02.zip](./000-Midia_e_Anexos/etapas_do_codigo/taskmanager_ate_o_video_02.zip)

## Parte 3 - Modelando o domínio de tarefas

### 🟩 Vídeo 03 - Modelando o domínio de tarefas

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.01-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/criando-sua-primeira-api-rest-com-spring-boot/learning/f45d8163-14cd-4153-af05-fac9e1cd1166?autoplay=1

### Anotações

#### Abertura: modelando o domínio de tarefas

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-13h14m38s955.jpg" alt="" width="840">
</p>

Slide de abertura da aula "Criando sua Primeira API REST com Spring Boot", da trilha Jornada Tech, com o tópico 03 — "Modelando o domínio de tarefas" — em destaque no roteiro, que também inclui introdução ao API REST, gerenciamento de tarefas, orquestração do domínio e listagem de tarefas.


#### Criando a interface TaskRepository

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-13h19m26s292.jpg" alt="" width="840">
</p>

```java
package dio.taskmanager.domain;

public interface TaskRepository {
}
```

A interface TaskRepository é criada, ainda vazia, dentro do pacote de domínio. Ela representa o padrão de projeto Repository: uma abstração da camada de armazenamento (seja banco de dados, arquivo ou memória), que mantém o domínio independente de como os dados serão persistidos.


#### Definindo os métodos do repositório

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-13h21m40s543.jpg" alt="" width="840">
</p>

```java
package dio.taskmanager.domain;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Task save(Task task);
    List<Task> findAll();
    Optional<Task> findById(TaskId id);
    void delete(TaskId id);
}
```

A interface é completada com os métodos `save`, `findAll`, `findById` e `delete`. O `findAll` retorna uma lista de tarefas, enquanto `findById` retorna um `Optional<Task>`, evitando `NullPointerException` caso a tarefa não exista. A busca usa o tipo `TaskId` em vez de `String`, tornando o código mais semântico.


#### Gerando a implementação da interface

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-13h22m31s973.jpg" alt="" width="840">
</p>

Pelo menu de ações do IDE, é selecionada a opção "Implement interface", que gera automaticamente uma classe concreta a partir da interface `TaskRepository`, servindo de ponto de partida para a implementação em memória.


#### Configurando a nova classe InMemoryTaskRepository

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-13h34m37s365.jpg" alt="" width="840">
</p>

Na caixa de diálogo de implementação, a classe recebe o nome `InMemoryTaskRepository` e é destinada ao pacote `dio.taskmanager.infrastructure.repository` — um novo subpacote dentro da camada de infraestrutura, pensado para comportar futuras implementações adicionais de repositório.


#### Selecionando os métodos a implementar

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-13h34m38s061.jpg" alt="" width="840">
</p>

O IDE lista os métodos definidos na interface (`save`, `findAll`, `findById` e `delete`) para que sejam gerados automaticamente na nova classe, com a opção "Insert @Override" habilitada.


#### Esqueleto inicial da implementação

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-13h34m38s724.jpg" alt="" width="840">
</p>

```java
package dio.taskmanager.infrastructure.repository;

import dio.taskmanager.domain.Task;
import dio.taskmanager.domain.TaskId;
import dio.taskmanager.domain.TaskRepository;
import java.util.List;
import java.util.Optional;

public class InMemoryTaskRepository implements TaskRepository {
    @Override
    public Task save(Task task) {
        return null;
    }

    @Override
    public List<Task> findAll() { return List.of(); }

    @Override
    public Optional<Task> findById(TaskId id) { return Optional.empty(); }

    @Override
    public void delete(TaskId id) {

    }
}
```

A classe é gerada com os métodos vazios (stubs), prontos para receber a lógica de armazenamento em memória.


#### Armazenando as tarefas em um Map

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-13h37m14s206.jpg" alt="" width="840">
</p>

```java
private final Map<TaskId, Task> storage = new HashMap<>();

@Override
public Task save(Task task) {
    storage.put();
    return null;
}
```

Para guardar as tarefas, é criado um `Map<TaskId, Task>` chamado `storage`, escolhido em vez de uma lista por facilitar a busca por identificador. Ao chamar `storage.put()`, fica evidente que é necessário informar o identificador da tarefa como chave — o que leva à discussão sobre como obter esse ID a partir de um objeto `Task` com propriedades privadas.


#### Encapsulamento e acesso via getters/setters

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-13h39m28s081.jpg" alt="" width="840">
</p>

```java
public class Task {
    private String id;
    private String title;
    private Optional<String> description;
    private TaskStatus status;

    public Task(String title, Optional<String> description, TaskStatus status) {
        Assert.notNull(title, "Title must not be null");

        this.id = new TaskId();
        this.title = title;
        this.description = description;
        this.status = TaskStatus.PENDING;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
```

A classe `Task` mantém suas propriedades privadas para garantir encapsulamento, permitindo aplicar regras específicas de armazenamento ou recuperação de dados dentro dos próprios getters e setters. Por isso, o acesso ao identificador da tarefa deve ocorrer por meio de um `getId()`, em vez de acessar o campo diretamente.


#### Explorando o plugin do Lombok para Gradle

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-13h54m00s898.jpg" alt="" width="840">
</p>

Com o aumento de classes contendo muitos getters e setters, escrevê-los manualmente se torna trabalhoso. A solução apresentada é o Lombok, cujo plugin oficial para Gradle, `io.freefair.lombok`, é localizado no repositório de plugins do Gradle.


#### Conhecendo o Project Lombok

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-13h54m15s579.jpg" alt="" width="840">
</p>

A página oficial do Project Lombok é aberta, mostrando que se trata de uma biblioteca Java que se integra ao editor e às ferramentas de build para gerar automaticamente getters, setters e outros métodos repetitivos por meio de anotações.


#### Integrando o Lombok ao Gradle

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-13h54m31s651.jpg" alt="" width="840">
</p>

A documentação explica como configurar o Lombok em projetos Gradle, reforçando que, além de getters e setters, a biblioteca também pode gerar construtores — uma ferramenta considerada interessante e que vale a pena ser estudada com mais profundidade.


#### Copiando o plugin para o projeto

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-13h54m39s900.jpg" alt="" width="840">
</p>

De volta à página do plugin, o trecho de configuração (`id("io.freefair.lombok") version "9.2.0"`) é copiado para ser adicionado ao arquivo `build.gradle` do projeto.


#### Adicionando o plugin do Lombok ao build.gradle

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-13h55m51s770.jpg" alt="" width="840">
</p>

```groovy
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.2.0'
    id 'io.spring.dependency-management' version '1.1.4'
    id("io.freefair.lombok") version "9.2.0"
}

group = 'dio.taskmanager'
version = '0.0.1-SNAPSHOT'

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```

O plugin do Lombok é inserido na seção `plugins` do `build.gradle`, junto aos plugins já existentes do Spring Boot, para que a biblioteca seja baixada e configurada automaticamente no projeto.


#### Anotando a classe Task com @Getter

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-13h58m43s470.jpg" alt="" width="840">
</p>

```java
package dio.taskmanager.domain;

import lombok.Getter;
import org.springframework.util.Assert;
import java.util.Optional;

@Getter
public class Task {
    private String id;
    private String title;
    private Optional<String> description;
    private TaskStatus status;

    public Task(String title, Optional<String> description, TaskStatus status) {
        Assert.notNull(title, "Title must not be null");

        this.id = new TaskId();
        this.title = title;
        this.description = description;
        this.status = TaskStatus.PENDING;
    }
}
```

Com a anotação `@Getter` do Lombok aplicada à classe, todos os métodos get são gerados automaticamente em tempo de compilação, eliminando a necessidade de escrevê-los manualmente.


#### Adicionando @Setter e finalizando o método save

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-14h05m43s885.jpg" alt="" width="840">
</p>

```java
import dio.taskmanager.domain.TaskId;
import dio.taskmanager.domain.TaskRepository;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryTaskRepository implements TaskRepository {
    private final Map<TaskId, Task> storage = new HashMap<>();

    @Override
    @Setter
    public Task save(Task task) {
        storage.put(task.getId(), task);
        return task;
    }

    @Override
    public List<Task> findAll() { return List.of(); }

    @Override
    public Optional<Task> findById(TaskId id) { return Optional.empty(); }

    @Override
    public void delete(TaskId id) {

    }
}
```

A anotação `@Setter` também é adicionada, pensando na futura necessidade de atualizar valores da tarefa. Com o `getId()` disponível, o método `save` é finalizado usando `storage.put(task.getId(), task)`, retornando a própria tarefa salva.


#### Implementando o findAll

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-14h11m47s410.jpg" alt="" width="840">
</p>

```java
@Override
public List<Task> findAll() {
    return new ArrayList<>(storage.values());
}
```

Como o método `values()` do `Map` retorna uma `Collection`, e não uma `List`, é necessário envolver o resultado em um `new ArrayList<>()` para atender ao tipo de retorno esperado por `findAll()`.

#### Implementando findById e delete

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-14h21m54s764.jpg" alt="" width="840">
</p>

```java
@Override
public Optional<Task> findById(TaskId id) {
    return Optional.ofNullable(storage.get(id));
}

@Override
public void delete(TaskId id) {
    storage.remove(id);
}
```

O método `get()` do `Map` pode retornar `null` caso a chave não exista, então o resultado é envolvido em `Optional.ofNullable()` para evitar a manipulação direta de valores nulos. O `delete` é implementado com `storage.remove(id)`, removendo a tarefa correspondente do mapa.

#### Gerando a classe de testes

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-14h24m37s872.jpg" alt="" width="840">
</p>

Pelo menu Generate do IDE, a opção "Test..." é selecionada para criar automaticamente uma classe de testes para o `InMemoryTaskRepository`.


#### Configurando dependências e execução de testes no Gradle

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-14h25m08s965.jpg" alt="" width="840">
</p>

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter'

    // Testes com Spring Boot + JUnit 5
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

test {
    useJUnitPlatform()
}
```

O `build.gradle` é ajustado com a dependência de testes do Spring Boot (que inclui o JUnit 5) e com o bloco `test { useJUnitPlatform() }`, necessário para que os testes sejam executados corretamente pela plataforma JUnit.

#### Estrutura da classe de testes do repositório

```java
package dio.taskmanager.infrastructure.repository;
package dio.taskmanager.infrastructure.repository;

import dio.taskmanager.domain.Task;
import dio.taskmanager.domain.TaskId;
import dio.taskmanager.domain.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// Classe de testes unitários para a implementação InMemoryTaskRepository.
// Cada método anotado com @Test valida um comportamento específico do repositório.
class repositoryInMemoryTaskRepositoryTest {

    // Instância do repositório que será testada.
    // É recriada antes de cada teste para garantir isolamento entre os casos de teste.
    private InMemoryTaskRepository repository;

    // Executado automaticamente pelo JUnit antes de CADA método de teste.
    // Garante que cada teste comece com um repositório novo e vazio,
    // evitando que dados de um teste "vazem" para outro.
    @BeforeEach
    void setUp() {
        repository = new InMemoryTaskRepository();
    }

    // Testa se uma tarefa é corretamente salva no repositório.
    @Test
    void shouldSaveTask() {
        // Cria uma nova tarefa com título e descrição.
        Task task = new Task("Estudar Java", Optional.of("Revisar coleções"));
        // Salva a tarefa e captura o retorno (a própria tarefa salva).
        Task saved = repository.save(task);

        // Verifica se um ID foi gerado automaticamente ao salvar.
        assertNotNull(saved.getId());
        // Verifica se o título foi mantido corretamente.
        assertEquals("Estudar Java", saved.getTitle());
        // Verifica se o status inicial padrão é PENDING.
        assertEquals(TaskStatus.PENDING, saved.getStatus());
    }

    // Testa se é possível buscar uma tarefa pelo seu ID após salvá-la.
    @Test
    void shouldFindTaskById() {
        // Cria uma tarefa sem descrição (Optional.empty()).
        Task task = new Task("Estudar Spring", Optional.empty());
        repository.save(task);

        // Busca a tarefa pelo ID gerado no save.
        Optional<Task> found = repository.findById(task.getId());

        // Confirma que a tarefa foi encontrada (Optional não está vazio).
        assertTrue(found.isPresent());
        // Confirma que o título da tarefa encontrada é o esperado.
        assertEquals("Estudar Spring", found.get().getTitle());
    }

    // Testa se o findAll retorna todas as tarefas salvas no repositório.
    @Test
    void shouldReturnAllTasks() {
        // Salva duas tarefas distintas.
        repository.save(new Task("Tarefa 1", Optional.empty()));
        repository.save(new Task("Tarefa 2", Optional.of("Com descrição")));

        // Verifica se o total de tarefas retornadas é igual a 2.
        assertEquals(2, repository.findAll().size());
    }

    // Testa se uma tarefa é corretamente removida do repositório.
    @Test
    void shouldDeleteTask() {
        // Cria e salva uma tarefa que será deletada em seguida.
        Task task = new Task("Tarefa para deletar", Optional.empty());
        repository.save(task);

        // Remove a tarefa do repositório usando seu ID.
        repository.delete(task.getId());

        // Tenta buscar a tarefa removida.
        Optional<Task> found = repository.findById(task.getId());
        // Confirma que a tarefa não está mais presente no repositório.
        assertFalse(found.isPresent());
    }
}
```

A classe de testes gerada instancia um novo `InMemoryTaskRepository` antes de cada teste (`@BeforeEach`) e valida individualmente os métodos `save`, `findById`, `findAll` e `delete`, verificando se os dados salvos, encontrados e removidos correspondem ao esperado.


**`setUp()` (`@BeforeEach`)**
Roda antes de cada teste individualmente. Cria uma nova instância de `InMemoryTaskRepository`, garantindo que cada teste comece com um repositório limpo — sem dados residuais de execuções anteriores. Isso é essencial em testes de armazenamento em memória, já que o `Map` interno persistiria dados entre testes se a instância fosse reaproveitada.

**`shouldSaveTask()`**
Valida o comportamento do método `save`. Cria uma `Task` com título e descrição, salva no repositório e verifica três coisas: (1) que um `id` foi atribuído automaticamente (`assertNotNull`), (2) que o título foi preservado sem alterações, e (3) que toda tarefa nova nasce com status `PENDING` por padrão. Esse teste garante que a regra de negócio "toda tarefa começa pendente" está sendo respeitada.

**`shouldFindTaskById()`**
Valida o método `findById`. Salva uma tarefa e, em seguida, busca-a pelo próprio `id` gerado. Confirma que o `Optional` retornado não está vazio (`isPresent()`) e que a tarefa devolvida é de fato a que foi salva, comparando o título. Isso testa a integridade da associação chave→valor no `Map` interno.

**`shouldReturnAllTasks()`**
Valida o método `findAll`. Salva duas tarefas diferentes e verifica se a lista retornada contém exatamente 2 elementos. O teste é propositalmente simples (checa apenas o tamanho, não o conteúdo), servindo para garantir que múltiplos registros coexistem corretamente no armazenamento.

**`shouldDeleteTask()`**
Valida o método `delete`. Salva uma tarefa, remove-a pelo `id` e depois tenta buscá-la novamente com `findById`. Espera que o `Optional` retornado esteja vazio (`assertFalse(found.isPresent())`), confirmando que a remoção efetivamente apaga o registro do `Map`, sem deixar resíduos.

Em conjunto, esses quatro testes cobrem o CRUD completo da interface `TaskRepository` (Create/Save, Read/FindById, Read/FindAll, Delete), validando tanto o "caminho feliz" de cada operação quanto a consistência dos dados entre elas.

#### Executando os testes com sucesso

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-08-15h14m02s635.jpg" alt="" width="100%">
</p>

Ao rodar a suíte de testes `repositoryInMemoryTaskRepositoryTest`, o Gradle executa as tarefas de compilação e testes e encerra com "BUILD SUCCESSFUL", confirmando os 4 testes aprovados e validando a implementação do repositório em memória.

#### Extraindo um teste de contrato para a interface TaskRepository

O professor observa que os 4 testes criados (`shouldSaveTask`, `shouldFindTaskById`, `shouldReturnAllTasks`, `shouldDeleteTask`) não validam nada específico da implementação em memória — eles validam **regras de negócio da interface `TaskRepository`**. Por isso, ele extrai esses testes para uma classe abstrata que passa a viver ao lado da interface, no domínio, e faz a implementação concreta apenas informar qual repositório deve ser testado. Esse padrão é conhecido como **teste de contrato** (contract test): garante que qualquer implementação futura da interface (banco de dados, arquivo, etc.) seja validada pelas mesmas regras, sem duplicar código de teste.

**Como funciona:**

1. Uma classe abstrata `TaskRepositoryTest` concentra todos os testes de regra de negócio e possui um campo `protected TaskRepository repository`.
2. Um método abstrato `createRepository()` obriga cada subclasse a dizer qual implementação concreta será testada.
3. O `@BeforeEach` da classe abstrata chama `createRepository()` antes de cada teste, garantindo isolamento.
4. A classe `InMemoryTaskRepositoryTest` passa a **estender** `TaskRepositoryTest` e só implementa `createRepository()`, herdando os 4 testes automaticamente.

**Classe abstrata — contém os testes de regra de negócio:**

```java
package dio.taskmanager.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public abstract class TaskRepositoryTest {

    protected TaskRepository repository;

    @BeforeEach
    void setUp() {
        repository = createRepository();
    }

    protected abstract TaskRepository createRepository();

    @Test
    void shouldSaveTask() {
        Task task = new Task("Estudar Java", Optional.of("Revisar coleções"));
        Task saved = repository.save(task);

        assertNotNull(saved.getId());
        assertEquals("Estudar Java", saved.getTitle());
        assertEquals(TaskStatus.PENDING, saved.getStatus());
    }

    @Test
    void shouldFindTaskById() {
        Task task = new Task("Estudar Spring", Optional.empty());
        repository.save(task);

        Optional<Task> found = repository.findById(task.getId());

        assertTrue(found.isPresent());
        assertEquals("Estudar Spring", found.get().getTitle());
    }

    @Test
    void shouldReturnAllTasks() {
        repository.save(new Task("Tarefa 1", Optional.empty()));
        repository.save(new Task("Tarefa 2", Optional.of("Com descrição")));

        assertEquals(2, repository.findAll().size());
    }

    @Test
    void shouldDeleteTask() {
        Task task = new Task("Tarefa para deletar", Optional.empty());
        repository.save(task);

        repository.delete(task.getId());

        Optional<Task> found = repository.findById(task.getId());
        assertFalse(found.isPresent());
    }
}
```

**Classe concreta — apenas informa qual repositório testar:**

```java
package dio.taskmanager.infrastructure.repository;

import dio.taskmanager.domain.TaskRepository;
import dio.taskmanager.domain.TaskRepositoryTest;

class InMemoryTaskRepositoryTest extends TaskRepositoryTest {

    @Override
    protected TaskRepository createRepository() {
        return new InMemoryTaskRepository();
    }
}
```

**Onde cada arquivo deve ficar:**

```
src/test/java/dio/taskmanager/domain/TaskRepositoryTest.java
src/test/java/dio/taskmanager/infrastructure/repository/InMemoryTaskRepositoryTest.java
```

Note que ambos ficam em `src/test/java`, nunca em `src/main/java`. As dependências de teste (JUnit 5, pacote `org.junit.jupiter.api`) são resolvidas pelo Gradle apenas via `testImplementation`, usada pela task `compileTestJava` — que só compila o conteúdo de `src/test`. Se qualquer uma dessas classes for criada dentro de `src/main/java` por engano, o build falha com o erro `package org.junit.jupiter.api does not exist`, porque a task `compileJava` (que compila `src/main`) não tem acesso às dependências de teste.

O arquivo antigo `repositoryInMemoryTaskRepositoryTest`, que continha os 4 testes escritos diretamente na implementação em memória, deve ser removido — seu conteúdo foi totalmente absorvido pela classe abstrata `TaskRepositoryTest`.

**Como rodar:**

Pela IDE: clique com o botão direito em `InMemoryTaskRepositoryTest` → **Run**. Os 4 testes aparecem na execução mesmo não estando escritos diretamente nessa classe, pois são herdados de `TaskRepositoryTest`.

Pelo terminal, na raiz do projeto:

```bash
./gradlew test
```

ou, para rodar apenas essa classe:

```bash
./gradlew test --tests "dio.taskmanager.infrastructure.repository.InMemoryTaskRepositoryTest"
```

**Ganho da abordagem:** se no futuro surgir uma implementação `DatabaseTaskRepository`, basta criar `DatabaseTaskRepositoryTest extends TaskRepositoryTest` e implementar `createRepository()` retornando a nova instância — os mesmos 4 testes de regra de negócio já cobrem essa nova implementação automaticamente, sem duplicação de código.

#### Executando o Teste de Contrato

Rodando os testes a partir da IDE:

<p align="center">
  <img src="000-Midia_e_Anexos/2026-07-08-18-16-37.png" alt="" width="840">
</p>

<p align="center">
  <img src="000-Midia_e_Anexos/2026-07-08-20-19-52.png" alt="" width="100%S">
</p>

**Resultado da execução:** o Gradle reporta `4 tests passed` e finaliza com `BUILD SUCCESSFUL`. É importante notar que esses 4 testes não estão escritos em `InMemoryTaskRepositoryTest` — eles são executados porque a classe herda de `TaskRepositoryTest`. Isso confirma, na prática, que a extração para o teste de contrato funcionou: o JUnit reconhece e executa os métodos herdados da classe abstrata como se pertencessem à subclasse concreta, validando o `InMemoryTaskRepository` contra as mesmas regras de negócio definidas para a interface `TaskRepository`.

### ✍️ Perguntas frequentes sobre o teste de contrato

Esta seção reúne, em forma de perguntas e respostas, os pontos que mais geram dúvida na primeira vez que se aplica esse padrão.

⭐️ **"BUILD SUCCESSFUL" já significa que todos os testes individuais passaram?**

Sim. O Gradle só reporta `BUILD SUCCESSFUL` se **todas** as tasks executadas terminarem sem erro — incluindo a task `:test`, que só passa se **todos** os métodos `@Test` executados tiverem passado em todas as suas assertions. Basta uma assertion falhar para o resultado virar `BUILD FAILED`, apontando exatamente qual teste quebrou. Ainda assim, vale sempre conferir o número explícito (`4 tests passed, 4 tests total`) no painel de resultados — ele garante que nenhum teste foi silenciosamente ignorado na descoberta (por exemplo, se só 3 de 4 tivessem sido descobertos, o build ainda apareceria como `SUCCESSFUL`, só que com uma contagem menor).

⭐️ **Ao rodar `InMemoryTaskRepositoryTest`, que só contém `createRepository()`, o que exatamente é executado?**

Quando uma classe **estende** outra em Java, todo objeto dessa subclasse **também é** um objeto da superclasse, e carrega consigo tudo o que ela define (exceto membros `private`). Isso não é cópia de código — é herança: os métodos `setUp()`, `shouldSaveTask()`, `shouldFindTaskById()`, `shouldReturnAllTasks()` e `shouldDeleteTask()`, embora escritos apenas em `TaskRepositoryTest`, passam a existir no objeto `InMemoryTaskRepositoryTest` assim que ele é instanciado.

O JUnit, ao rodar uma classe de teste, usa reflection para escanear **toda a cadeia de herança** daquele objeto — não só a própria classe — procurando métodos anotados com `@Test` e `@BeforeEach`. Por isso ele encontra e executa os 4 testes definidos na superclasse, mesmo eles não aparecendo no arquivo `InMemoryTaskRepositoryTest.java`. A única responsabilidade dessa subclasse é implementar o método abstrato `createRepository()`, que "liga" os testes genéricos a uma implementação concreta específica.

⭐️ **A refatoração mudou o que os testes realmente fazem em tempo de execução?**

Não. Tanto na versão antiga quanto na nova, o `save()` de um teste chama `storage.put(task.getId(), task)` dentro de `InMemoryTaskRepository`, gravando o objeto de fato em um `HashMap` real, na memória do processo. E um `findById()` de fato lê esse mesmo `HashMap`. Nada disso é simulado ou mockado — é idêntico nas duas versões.

O que mudou foi exclusivamente a **organização do código de teste**:

| | Antes | Depois |
|---|---|---|
| Onde estava o `new InMemoryTaskRepository()` | Dentro de um `setUp()` escrito na própria classe `InMemoryTaskRepositoryTest` | Dentro de `createRepository()`, também na classe `InMemoryTaskRepositoryTest` — só migrou de lugar |
| Onde estavam os 4 métodos `@Test` | Escritos diretamente em `InMemoryTaskRepositoryTest` | Escritos uma única vez em `TaskRepositoryTest` (abstrata) e herdados |
| O que é gravado/lido durante os testes | `HashMap` real, em memória, dentro de `InMemoryTaskRepository` | O mesmo `HashMap` real, sem alteração |

⭐️ **Por que a solução antiga não era considerada ideal, já que ela funcionava e gravava/lia dados de verdade?**

O problema nunca foi o comportamento em tempo de execução — era **onde a lógica de teste ficava presa**. Os 4 testes não verificam nada específico de "estar em memória"; eles verificam regras do **contrato da interface `TaskRepository`** (ex.: "todo `save` deve gerar um ID", "todo `findById` de algo salvo deve retornar presente"). <mark style='background:#00ffff'><font color='#000000'><strong>Escritos diretamente dentro de `InMemoryTaskRepositoryTest`, esses testes ficavam fisicamente amarrados a essa implementação</strong></font></mark>.

Isso trazia três riscos concretos para o futuro do projeto:

1. **Duplicação.** No dia em que surgisse uma segunda implementação (por exemplo, `DatabaseTaskRepository`), a única forma de garantir que ela também respeitasse as mesmas regras seria copiar e colar os 4 métodos de teste para uma nova classe, trocando apenas o `setUp()`.
2. **Divergência entre implementações.** Com testes duplicados, nada garante que as cópias continuem idênticas ao longo do tempo — alguém pode ajustar uma assertion em um lugar e esquecer do outro, fazendo com que implementações diferentes acabem sendo validadas por regras diferentes.
3. **Custo de manutenção multiplicado.** Se uma regra de negócio mudar (por exemplo, o status inicial de uma tarefa deixar de ser `PENDING`), seria preciso editar essa mudança em uma classe de teste para cada implementação existente. Com a classe abstrata, a mudança é feita uma única vez em `TaskRepositoryTest` e todas as implementações já ficam cobertas automaticamente.

⭐️ **Como adicionar uma nova implementação no futuro (ex.: `DatabaseTaskRepository`)?**

Sem alterar `InMemoryTaskRepositoryTest`, cria-se uma nova classe ao lado dela:

```java
package dio.taskmanager.infrastructure.repository;

import dio.taskmanager.domain.TaskRepository;
import dio.taskmanager.domain.TaskRepositoryTest;

class DatabaseTaskRepositoryTest extends TaskRepositoryTest {

    @Override
    protected TaskRepository createRepository() {
        return new DatabaseTaskRepository(/* ex.: um DataSource, EntityManager, etc. */);
    }
}
```

As duas classes concretas passam a coexistir, cada uma rodando os mesmos 4 testes de regra de negócio contra sua própria implementação, sem nenhuma interferência entre elas.

#### Material de Apoio Até Esta Etapa do Projeto

- [001-Tutorial de Estudos — Construindo um Task Manager com Java e Spring Boot - Videos 01 a 03](./001-Tutorial_TaskManager_Java_Spring_Videos01a03_3.md)
  - [Implementação de uma classe `Main` para explorar manualmente](./001-B-extra_main_playground.md)
- Arquivos do projeto: [./000-Midia_e_Anexos/etapas_do_codigo/taskmanager_video_03](./000-Midia_e_Anexos/etapas_do_codigo/taskmanager_ate_o_video_03)

## Parte 4 - Orquestrando o domínio

### 🟩 Vídeo 04 - Orquestrando o domínio

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.01-video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/criando-sua-primeira-api-rest-com-spring-boot/learning/9697c24a-da4d-4165-ae72-2b27f7066252?autoplay=1

### Anotações

#### Introdução: orquestrando o domínio com Use Cases

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-15h35m32s842.jpg" alt="" width="840">
</p>

Slide de abertura da aula "Criando sua Primeira API REST com Spring Boot", destacando o tópico atual do módulo: **Orquestrando o domínio**. A partir daqui a aula sai da camada de domínio (já construída nos vídeos anteriores, com entidade e repositório em memória) e avança para a camada de **application**, responsável por orquestrar as regras de negócio através do padrão de Use Case.


#### Criando a classe CreateTaskUseCase

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-15h37m48s310.jpg" alt="" width="840">
</p>

No IntelliJ, dentro do pacote `application`, é criada uma nova classe Java chamada `CreateTaskUseCase`. Ela representa o primeiro caso de uso da aplicação, seguindo o padrão *Use Case* do Clean Architecture: cada classe concentra uma única regra de negócio, aplicando o princípio de responsabilidade única (SRP), em vez de um único serviço genérico cheio de métodos `save`, `find`, `delete`, etc.


#### O método execute(): ponto de entrada do Use Case

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-15h38m00s679.jpg" alt="" width="840">
</p>

```java
package dio.taskmanager.application;

public class CreateTaskUseCase {
    void execute() {

    }
}
```

Por convenção, todo Use Case expõe um único método público chamado `execute`, que vai concentrar toda a lógica daquela regra de negócio específica — nesse caso, criar uma tarefa.


#### Criando o pacote input para o DTO de entrada

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-15h38m34s508.jpg" alt="" width="840">
</p>

Para instanciar uma `Task` é necessário receber título e descrição. Por isso é criado o pacote `application.input`, que vai abrigar o Data Transfer Object (DTO) responsável por representar os dados de entrada do caso de uso, em vez de acoplar o `execute` diretamente à entidade de domínio.


#### CreateTaskInput como record

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-15h39m18s944.jpg" alt="" width="840">
</p>

A classe de entrada é criada como um **record**, escolha recomendada por trazer imutabilidade por padrão — característica desejável para um DTO que apenas transporta dados entre camadas, sem comportamento próprio.


#### CreateTaskInput completo

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-15h41m09s946.jpg" alt="" width="840">
</p>

```java
package dio.taskmanager.application.input;

import java.util.Optional;

public record CreateTaskInput(String title, Optional<String> description) {
}
```

O `CreateTaskInput` carrega o título (obrigatório) e a descrição (opcional, representada com `Optional<String>`). Usar um DTO em vez de passar a entidade `Task` diretamente reduz o acoplamento entre a camada HTTP/controller e o Use Case: se no futuro a entrada deixar de vir de uma requisição REST e passar a vir de um evento, por exemplo, o Use Case permanece isolado e não precisa mudar.


#### Recebendo o CreateTaskInput no execute()

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-15h42m31s924.jpg" alt="" width="840">
</p>

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

O método `execute` passa a receber o `CreateTaskInput` e usa seus dados (`title` e `description`) para instanciar uma nova `Task` do domínio.


#### Preparando o DTO de saída

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-15h44m21s508.jpg" alt="" width="840">
</p>

Depois de esboçar a criação da `Task` (marcada com o comentário `// repository`, indicando que ainda falta persistir os dados), a aula segue criando o pacote `application.output`, que vai conter o DTO de resposta do caso de uso — o mesmo raciocínio do DTO de entrada, agora aplicado à saída.


#### TaskOutput: o DTO de saída

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-15h45m24s197.jpg" alt="" width="840">
</p>

Assim como existe um DTO de entrada, é criado também um DTO de saída chamado `TaskOutput`, evitando expor a entidade `Task` de domínio para fora da camada de application. Isso mantém o domínio isolado e permite, por exemplo, reaproveitar esse mesmo `TaskOutput` em outros casos de uso, como listagem e busca.


#### Esqueleto inicial do TaskOutput

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-15h45m39s334.jpg" alt="" width="840">
</p>

```java
package dio.taskmanager.application.output;

public record TaskOutput() {
}
```

Neste momento, o record `TaskOutput` acabou de ser criado, ainda sem nenhum campo definido.


#### TaskOutput completo, com mapeamento a partir de Task

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-15h47m08s781.jpg" alt="" width="840">
</p>

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

O `TaskOutput` representa os dados de saída com tipos simples (`String`), inclusive convertendo o `status` (um enum no domínio) para texto. O método estático `from(Task)` centraliza o mapeamento entre a entidade de domínio `Task` e o DTO de resposta, isolando essa conversão em um único lugar.


#### Retomando o Use Case com o DTO de saída

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-15h48m07s709.jpg" alt="" width="840">
</p>

De volta à classe `CreateTaskUseCase`, o método `execute` ainda cria a `Task` e mantém o comentário `// repository` como lembrete do próximo passo: persistir a tarefa antes de devolver a resposta.


#### execute retornando TaskOutput

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-15h48m23s326.jpg" alt="" width="840">
</p>

```java
public TaskOutput execute(CreateTaskInput input) {
    var task = new Task(input.title(), input.description());
    // repository
    return TaskOutput.from(task);
}
```

O método `execute` agora retorna um `TaskOutput`, utilizando o método estático `from` para converter a `Task` recém-criada na resposta do caso de uso.


#### Injetando o TaskRepository via construtor

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-15h53m27s966.jpg" alt="" width="840">
</p>

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

Para persistir a `Task`, o `CreateTaskUseCase` passa a receber um `TaskRepository` pelo construtor — um exemplo de injeção de dependência manual, em que a classe declara o que precisa de fora (o repositório) sem se preocupar com qual implementação concreta será usada.


#### Chamando repository.save()

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-15h56m13s330.jpg" alt="" width="840">
</p>

```java
public TaskOutput execute(CreateTaskInput input) {
    var task = new Task(input.title(), input.description());
    var saved = repository.save(task);
    return TaskOutput.from(saved);
}
```

O comentário `// repository.save` dá lugar à chamada real `repository.save(task)`, que persiste a `Task` e retorna a instância salva. É essa instância (`saved`) que é convertida para `TaskOutput`.


#### Gerando um teste para o Use Case

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-16h02m37s415.jpg" alt="" width="840">
</p>

Com o Use Case implementado, o próximo passo é validar seu comportamento. Pelo menu de contexto do IntelliJ, a opção **Generate...** é usada para criar um teste automatizado para a classe `CreateTaskUseCase`.


#### Configurando o teste com JUnit5

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-16h03m03s485.jpg" alt="" width="840">
</p>

Na janela de criação de teste, é definida a biblioteca **JUnit5**, o nome da classe `CreateTaskUseCaseTest`, e o método `execute` é marcado para gerar automaticamente o esqueleto do teste correspondente.


#### Classe de teste recém-criada

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-16h03m09s775.jpg" alt="" width="840">
</p>

O IntelliJ gera a classe `CreateTaskUseCaseTest`, ainda vazia, pronta para receber o cenário de teste do caso de uso de criação de tarefa.


#### Teste com injeção de dependência manual

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-16h24m13s812.jpg" alt="" width="840">
</p>

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

O teste faz uma injeção de dependência **manual**: a cada execução (`@BeforeEach`), instancia o `CreateTaskUseCase` passando diretamente um `InMemoryTaskRepository`. O cenário `should_create_task_successfully` cria um `CreateTaskInput`, executa o Use Case e valida se o `TaskOutput` retornado tem os dados esperados.


#### Teste executado com sucesso

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-16h24m15s908.jpg" alt="" width="840">
</p>

O painel de resultados confirma que o teste `should_create_task_successfully` passou, validando que o Use Case cria a `Task` corretamente e devolve o `TaskOutput` esperado usando a injeção de dependência manual.


#### Anotando o Use Case com @Service

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-16h25m53s654.jpg" alt="" width="840">
</p>

```java
@Service
public class CreateTaskUseCase {
    private final TaskRepository repository;
    // ...
}
```

Para que o Spring passe a gerenciar essa classe automaticamente, ela é anotada com `@Service` — uma das anotações do Spring para indicar componentes gerenciados pelo contêiner de injeção de dependência, ao lado de `@Component` e `@Repository`.


#### Erro de autowire: interface sem implementação

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-17h38m17s017.jpg" alt="" width="840">
</p>

Ao anotar a classe com `@Service`, o Spring tenta descobrir sozinho como instanciá-la, mas exibe o erro **"Could not autowire. No beans of 'TaskRepository' type found"** — porque `TaskRepository` é apenas uma interface, sem nenhuma implementação concreta registrada ainda no contexto do Spring.


#### O contrato TaskRepository

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-17h39m14s535.jpg" alt="" width="840">
</p>

```java
package dio.taskmanager.domain;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Task save(Task task);
    List<Task> findAll();
    Optional<Task> findById(TaskId id);
    void delete(TaskId id);
}
```

Esse é o contrato `TaskRepository`, definido na camada de domínio, com as operações `save`, `findAll`, `findById` e `delete`. Por ser uma interface, o Spring precisa de uma implementação concreta para saber o que injetar.


#### Navegando até a implementação

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-17h40m39s294.jpg" alt="" width="840">
</p>

Pelo menu de contexto sobre `TaskRepository`, a opção **Go To > Implementation** é usada para navegar até a implementação concreta dessa interface, que precisa ser anotada para o Spring conseguir reconhecê-la.


#### Anotando InMemoryTaskRepository com @Repository

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-17h40m54s420.jpg" alt="" width="840">
</p>

```java
package dio.taskmanager.infrastructure.repository;

@Repository
public class InMemoryTaskRepository implements TaskRepository {
    private final Map<TaskId, Task> storage = new HashMap<>();

    @Override
    public Task save(Task task) {
        storage.put(task.getId(), task);
        return task;
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<Task> findById(TaskId id) { return Optional.ofNullable(storage.get(id)); }
}
```

A implementação `InMemoryTaskRepository` é anotada com `@Repository`, informando ao Spring que, sempre que a interface `TaskRepository` for requisitada, essa classe deve ser injetada.


#### Trocando o construtor manual por @Autowired

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-17h44m25s067.jpg" alt="" width="840">
</p>

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

Com a implementação já reconhecida pelo Spring, o construtor manual é comentado e o campo `repository` passa a ser injetado via `@Autowired`, deixando o Spring responsável por resolver e instanciar a dependência automaticamente.


#### Testando com @SpringBootTest e @Autowired

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-17h47m46s881.jpg" alt="" width="840">
</p>

No teste, a instanciação manual é substituída por `@Autowired` no campo `useCase`, exigindo que a classe de teste seja anotada com `@SpringBootTest` para subir o contexto do Spring. Ao rodar em modo **Debug**, o Spring injeta automaticamente a implementação real (`InMemoryTaskRepository`) disponível no contexto.


#### Breakpoint na implementação real

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-17h49m08s246.jpg" alt="" width="840">
</p>

Um breakpoint é colocado dentro do método `save()` da `InMemoryTaskRepository` para confirmar, durante a depuração, que essa implementação real está sendo injetada pelo Spring e efetivamente chamada pelo Use Case.


#### Breakpoint atingido: injeção confirmada

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-17h57m25s485.jpg" alt="" width="840">
</p>

A execução em modo debug avança e o breakpoint é atingido, confirmando visualmente que a instância injetada pelo Spring é a implementação real de `TaskRepository` e que o método `save` foi de fato chamado.


#### Inspecionando o estado no debugger

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-17h57m36s022.jpg" alt="" width="840">
</p>

No painel de variáveis do depurador é possível inspecionar o estado no momento em que o breakpoint foi atingido: o objeto `task` recém-criado e o `storage` (ainda vazio, `size = 0`) da `InMemoryTaskRepository`, comprovando que o fluxo passou pela implementação real injetada automaticamente pelo Spring.


#### Preparando o teste com Mockito

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-18h01m50s412.jpg" alt="" width="840">
</p>

Nem sempre é viável testar contra uma implementação real — por exemplo, quando ela depende de um banco de dados ou de uma API externa. Para simular esse cenário, a classe de teste passa a usar também a extensão do **Mockito**, biblioteca de mocking que já vem integrada ao Spring Boot, através de `@ExtendWith(MockitoExtension.class)`.


#### Criando o mock do TaskRepository

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-18h05m11s750.jpg" alt="" width="840">
</p>

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

O campo `repository` é anotado com `@Mock`, criando uma "casca" da interface `TaskRepository` sem nenhuma implementação real por trás. Suas chamadas poderão ser manipuladas livremente dentro do teste.


#### Substituindo @Autowired por @InjectMocks

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-18h06m21s217.jpg" alt="" width="840">
</p>

```java
@Mock
TaskRepository repository;

@InjectMocks
CreateTaskUseCase useCase;
```

Em vez de `@Autowired`, o campo `useCase` passa a usar `@InjectMocks`, anotação do Mockito responsável por instanciar o `CreateTaskUseCase` injetando automaticamente os mocks declarados na classe de teste — nesse caso, o `repository` mockado.


#### Configurando o comportamento do mock

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-18h11m16s055.jpg" alt="" width="840">
</p>

```java
when(repository.save(any(Task.class)))
        .thenAnswer(invocation -> invocation.getArgument(0));
```

Com o mock configurado, é definido o comportamento simulado do método `save`: sempre que for chamado com qualquer `Task`, ele responde devolvendo o próprio argumento recebido — simulando, por exemplo, o que uma API real faria ao "aceitar" e devolver os dados enviados, sem depender de uma integração de verdade.


#### Verificando a chamada ao repositório

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-18h11m29s986.jpg" alt="" width="840">
</p>

```java
verify(repository, times(1)).save(any(Task.class));
```

Ao final do teste, `verify` garante que o método `save` do repositório mockado foi chamado exatamente uma vez, reforçando que o comportamento do Use Case está correto mesmo sem uma implementação real por trás.


#### Confirmando que a implementação real não foi acionada

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-18h14m35s819.jpg" alt="" width="840">
</p>

Repetindo a depuração com o breakpoint dentro de `InMemoryTaskRepository.save()`, dessa vez ele **não** é acionado — prova de que o Use Case está usando o repositório mockado, e não mais a implementação real em memória.


#### Teste passando com o repositório mockado

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-11-18h14m53s929.jpg" alt="" width="840">
</p>

O teste `should_create_task_successfully` passa novamente, agora usando o repositório mockado em vez da implementação real, encerrando a demonstração de como usar o Mockito para isolar o Use Case de dependências externas em testes unitários. Com isso, fica consolidado o conceito de Use Case como uma classe de responsabilidade única, testável tanto com injeção real (via `@Autowired`) quanto com mocks (via `@Mock`/`@InjectMocks`).

#### Material de Apoio Até Esta Etapa do Projeto

- Arquivos do projeto (até o vídeo 03): [./000-Midia_e_Anexos/etapas_do_codigo/taskmanager_ate_o_video_03.zip](./000-Midia_e_Anexos/etapas_do_codigo/taskmanager_ate_o_video_04.zip)

## Parte 5 - Listagem de tarefas
### 🟩 Vídeo 05 - Listagem de tarefas

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.01-video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/criando-sua-primeira-api-rest-com-spring-boot/learning/654ce2a1-7dc6-4406-b3aa-c13935b3d17b?autoplay=1

## Parte 6 - Infraestrutura e interface
### 🟩 Vídeo 06 - Infraestrutura e interface

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.01-video_06.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

## Parte 7 - Consulta de tarefas
### 🟩 Vídeo 07 - Consulta de tarefas

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.01-video_07.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

## Parte 8 - Validando dados
### 🟩 Vídeo 08 - Validando dados

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.01-video_08.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

## Parte 9 - Documentando a API
### 🟩 Vídeo 09 - Documentando a API

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.01-video_09.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

## Parte 10 - Evoluindo a API
### 🟩 Vídeo 10 - Evoluindo a API

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.01-video_10.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:


##  Materiais de Apoio

### Tutoriais

- [001-Tutorial de Estudos — Construindo um Task Manager com Java e Spring Boot - Videos 01 a 03](./001-Tutorial_TaskManager_Java_Spring_Videos01a03_3.md)
  - [Implementação de uma classe `Main` para explorar manualmente](./001-B-extra_main_playground.md)

### Arquivos do Projeto

- Arquivos do projeto (até o vídeo 03): [./000-Midia_e_Anexos/etapas_do_codigo/taskmanager_ate_o_video_03.zip](./000-Midia_e_Anexos/etapas_do_codigo/taskmanager_ate_o_video_03.zip)
- Arquivos do projeto (até o vídeo 04): [./000-Midia_e_Anexos/etapas_do_codigo/taskmanager_ate_o_video_04.zip](./000-Midia_e_Anexos/etapas_do_codigo/taskmanager_ate_o_video_04.zip)

# Certificado: 

- Link na plataforma: 
- Certificado em pdf: