## Instrutor

- Thiago Leite e Carvalho (Senior Java Developer and Software Engineer | Java Tech Lead | University Professor | Writer | Speaker | Instructor)
- Contato Linkedin: / [thiago-leite-e-carvalho-1b337b127](https://www.linkedin.com/in/thiago-leite-e-carvalho-1b337b127/)
- Códigos de exemplo da aula neste link: https://github.com/tlcdio

## Parte 1 - Criação

### 🟩 Vídeo 01 - Introdução ao Curso

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.03-video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/entendendo-metodos-java/learning/1d32857c-8137-4b87-8a1c-474300f71648?autoplay=1

Esta fonte consiste na transcrição de uma aula introdutória de um curso focado em métodos na linguagem Java, ministrada por Thiago Leite Carvalho, um desenvolvedor Back-end com 17 anos de experiência e mestre em Engenharia de Software que atua no SERPRO. O instrutor estabelece que o objetivo principal do curso é capacitar o aluno a compreender, criar e utilizar métodos, estruturando o conteúdo em três aulas principais sobre criação, sobrecarga e retornos, ao mesmo tempo em que define a lógica de programação, o Java 11 e a IDE IntelliJ como pré-requisitos essenciais. Além de apresentar sua trajetória acadêmica e profissional, Thiago compartilha seus canais de contato, como LinkedIn e GitHub para acesso a projetos e materiais, e orienta sobre o uso do fórum do curso e da comunidade no Discord para a resolução de dúvidas durante o aprendizado.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-21-15h00m43s491.jpg" alt="" width="840">
</p>

O instrutor Thiago Leite e Carvalho, engenheiro de software com 17 anos de experiência, inicia o curso focado no estudo de **Métodos**. Como especialista em Java Backend, ele introduz sua trajetória profissional, destacando sua atuação em camadas de persistência, modelagem de dados e criação de APIs, além de sua experiência na docência universitária e produção de conteúdo técnico.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-21-15h00m53s781.jpg" alt="" width="840">
</p>

Além da atuação técnica como mestre em engenharia de software e desenvolvedor no SERPRO (empresa de tecnologia do Governo Federal), o instrutor compartilha seus interesses pessoais. Ele destaca que sua motivação profissional vem da ausência de rotina e dos novos desafios de negócio, enquanto pessoalmente dedica seu tempo a hobbies como gastronomia (massas e pizzas), cervejas artesanais e futebol.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-21-15h00m58s271.jpg" alt="" width="840">
</p>

Para manter o contato e acompanhar as publicações técnicas e materiais de apoio, são disponibilizados os perfis profissionais:

* **LinkedIn:** Canal para artigos, livros e conteúdos sobre o cotidiano do desenvolvedor.
* **GitHub Pessoal:** Repositório de projetos particulares e exemplos de artigos.
* **GitHub DIO:** Perfil exclusivo para os projetos, exercícios e soluções desenvolvidos especificamente para este curso.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-21-15h01m00s488.jpg" alt="" width="840">
</p>

O objetivo central do curso é capacitar o aluno a compreender a natureza de um método, além de dominar sua criação e utilização prática. O método é apresentado como a unidade fundamental onde a execução do software se concentra, sendo o mecanismo que faz as operações funcionarem dentro de uma aplicação.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-21-15h01m03s076.jpg" alt="" width="840">
</p>

O percurso de aprendizado está estruturado em três aulas principais:

1. **Criação:** Fundamentos de como construir um método.
2. **Sobrecarga:** Entendimento deste conceito e como ele se aplica aos métodos.
3. **Retornos:** Estudo sobre como os métodos processam operações e retornam (ou não) valores e resultados.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-21-15h01m07s557.jpg" alt="" width="840">
</p>

Para um melhor aproveitamento do conteúdo, são necessários os seguintes requisitos:

* **Lógica de Programação:** Conhecimento essencial prévio para evitar dificuldades no acompanhamento.
* **Java:** O curso utiliza especificamente a versão 11 da linguagem.
* **IntelliJ:** IDE (ambiente de desenvolvimento) que será utilizada para a execução dos exercícios práticos.      


### 🟩 Vídeo 02 - Conceituação de métodos

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.03-video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/entendendo-metodos-java/learning/c3449aae-b555-4dea-ade4-f0e4837c150e?autoplay=1

Esta aula introduz o conceito de métodos como sub-rotinas essenciais que definem o comportamento de um software, operando obrigatoriamente dentro de uma classe. O conteúdo foca em três objetivos fundamentais: a compreensão do que constitui um método, o aprendizado das regras para sua definição e utilização, e a aplicação de boas práticas para garantir que o código seja legível e facilite manutenções corretivas ou evolutivas. Por fim, ressalta-se que um método não funciona de forma isolada, exigindo uma requisição ou chamada para executar as operações que fazem a aplicação funcionar na prática.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-21-15h29m36s308.jpg" alt="" width="840">
</p>

Esta etapa inicial da aula foca no processo de criação de métodos e na compreensão de como essas estruturas devem ser desenvolvidas. O objetivo é estabelecer uma base sólida sobre como organizar o código para que ele funcione corretamente dentro de uma aplicação.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-21-15h29m40s102.jpg" alt="" width="840">
</p>

O aprendizado é estruturado em torno de três metas principais:

* **Entender a definição**: Compreender fundamentalmente o que constitui um método.
* **Definição e utilização**: Aprender as regras específicas para criar um método e como realizar a chamada (requisição) para que ele seja executado.
* **Aplicação de boas práticas**: Utilizar padrões que garantam a qualidade do código, facilitando futuras manutenções corretivas ou evolutivas e permitindo que outros desenvolvedores compreendam a lógica aplicada.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-21-15h29m42s592.jpg" alt="" width="840">
</p>

Um método é definido como uma sub-rotina ou porção de código disponibilizada obrigatoriamente por uma classe. Ele possui características fundamentais para o funcionamento do software:

* **Vínculo com Classes**: Não existe método sem uma classe; ele deve estar sempre contido dentro de uma para existir.
* **Execução sob Demanda**: O código interno do método permanece inativo até que seja feita uma requisição específica para sua execução.
* **Definição de Comportamento**: Os métodos são os responsáveis diretos por realizar as ações e operações que fazem o software funcionar de fato, definindo como a aplicação deve se comportar diante de cada tarefa.


### 🟩 Vídeo 03 - Definição

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.03-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/entendendo-metodos-java/learning/648b17de-d6e5-47e3-84c1-097e701ab296?autoplay=1

### 🟩 Vídeo 04 - Conceitos Inerentes aos métodos e boas práticas na sua criação

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.03-video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 05 - Apresentação do exercício

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.03-video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 06 - Exercício prático

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.03-video_06.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

## Parte 2 - Sobrecarga

### 🟩 Vídeo 07 - Como criar sobrecargas, usá-las e exercício prático

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.03-video_07.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 08 - Resolução do exercício prático

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.03-video_08.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

## Parte 3 - Retornos

### 🟩 Vídeo 09 - Funcionamento, considerações, uso de retornos e apresentação do exercício

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.03-video_09.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 10 - Resolução do exercício

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.03-video_10.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 11 - Encerramento do curso

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.03-video_11.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

##  Materiais de Apoio

# Certificado: 

- Link na plataforma: 
- Certificado em pdf: 
