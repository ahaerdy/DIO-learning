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

**As Regras do Jogo: Restrições RESTful**. O protocolo HTTP, por si só, permite enviar qualquer dado para qualquer lugar, sem semântica definida — o que gera caos arquitetônico. Para organizar essa comunicação, utiliza-se o padrão REST (Representational State Transfer): não é uma ferramenta ou biblioteca instalável, mas sim um **padrão semântico arquitetural**, que impõe restrições sobre como usar verbos e códigos de status para tornar o tráfego previsível. Um exemplo dessa semântica: uma requisição `POST` significa que está sendo criado um recurso no serviço.

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


## Parte 3 - Modelando o domínio de tarefas
### 🟩 Vídeo 03 - Modelando o domínio de tarefas

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.01-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/criando-sua-primeira-api-rest-com-spring-boot/learning/f45d8163-14cd-4153-af05-fac9e1cd1166?autoplay=1

## Parte 4 - Orquestrando o domínio
### 🟩 Vídeo 04 - Orquestrando o domínio

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.01-video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

## Parte 5 - Listagem de tarefas
### 🟩 Vídeo 05 - Listagem de tarefas

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.01-video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

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

# Certificado: 

- Link na plataforma: 
- Certificado em pdf: