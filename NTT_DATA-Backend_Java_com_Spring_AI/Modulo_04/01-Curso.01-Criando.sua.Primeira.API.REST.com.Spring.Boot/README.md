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

## Parte 3 - Modelando o domínio de tarefas
### 🟩 Vídeo 03 - Modelando o domínio de tarefas

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.01-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

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