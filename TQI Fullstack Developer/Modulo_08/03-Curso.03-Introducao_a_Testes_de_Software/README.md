## Instrutor

- Carolina Santana Louzada
- Perfil no Github: https://github.com/CarolinaSL

## Parte 1 - Introdução

### 🟩 Vídeo 01 - Introdução

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.08-curso.03-video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/introducao-a-testes-de-software/learning/0c6e3021-7351-4a64-8f69-3e8cebc221fa?autoplay=1

Curso introdutório sobre a disciplina de testes na engenharia de software.

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-13-07h46m48s597.jpg" alt="" width="840">
</p>

Este slide de abertura apresenta o título do curso **“Introdução a Testes de Software”** e o nome do instrutor, **Willyan Guimarães Caetano**. É o ponto de partida da nossa jornada: a partir daqui começaremos a explorar os fundamentos, a importância e as aplicações dos testes no desenvolvimento de software.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-13-07h46m51s355.jpg" alt="" width="840">
</p>

Agora conhecemos um pouco mais sobre o instrutor. Willyan atua na área desde 2012, com foco em desenvolvimento backend – especialmente em Java (no qual é certificado) e, mais recentemente, estudando Go. Ele compartilha seu interesse por code review e design de código, práticas fundamentais para a construção de software de qualidade. O slide também traz seu LinkedIn para contato profissional e menciona seus hobbies: fotografia e viagens, mostrando um lado pessoal que equilibra a vida profissional.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-13-07h46m54s592.jpg" alt="" width="840">
</p>

Este slide resume os **objetivos do curso**. Ao final, espera-se que você seja capaz de:
- Entender o conceito de testes de software e sua terminologia principal;
- Compreender como os testes se integram ao processo de desenvolvimento;
- Conhecer os diferentes tipos de teste (unitários, integração, sistema, etc.) e suas finalidades;
- Discutir noções básicas sobre qualidade de software.
Esses pontos nortearão todo o conteúdo que será abordado nas próximas aulas.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-13-07h46m56s150.jpg" alt="" width="840">
</p>

O **percurso do curso** está organizado em quatro aulas:
- **Aula 1 – Definição e conceitos básicos**: introdução à terminologia e fundamentos dos testes.
- **Aula 2 – Níveis e Técnicas de Teste**: exploração dos níveis (unitário, integração, sistema) e técnicas (caixa preta, caixa branca).
- **Aula 3 – Testes não funcionais**: abordagem de requisitos como desempenho, segurança e usabilidade.
- **Aula 4 – A pirâmide de Testes**: apresentação da estratégia de automação e do equilíbrio entre os diferentes tipos de teste.

Essa estrutura progressiva garante uma aprendizagem sólida e aplicável.      

## Parte 2 - Definição e conceitos básicos

### 🟩 Vídeo 02 - Definição de Testes

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.08-curso.03-video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/introducao-a-testes-de-software/learning/da0d3708-1f93-4f86-8661-2fcb26833655?autoplay=1

Esta aula inaugural estabelece a base para o entendimento do teste de software como uma disciplina crítica da Engenharia de Software. O foco vai além da simples detecção de erros, explorando o impacto psicológico e financeiro das falhas no mundo real, além de definir formalmente o que significa "testar" e quais são as limitações intrínsecas dessa atividade.

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-13-08h40m10s691.jpg" alt="" width="840">
</p>

Este slide apresenta a estrutura geral da aula, listando os três tópicos principais que serão abordados: a definição de teste de software, a evolução dessa disciplina no contexto da engenharia de software e os conceitos básicos que fundamentam a área.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-13-08h40m12s030.jpg" alt="" width="840">
</p>

A imagem marca o início da primeira etapa da aula, dedicada à definição de teste de software. O título "Introdução a Testes de Software" contextualiza o espectador para os conceitos introdutórios que serão explorados.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-13-08h40m17s254.jpg" alt="" width="840">
</p>

Conteúdo não identificado com segurança a partir do material disponível.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-13-08h40m24s380.jpg" alt="" width="840">
</p>

O slide levanta a questão central sobre as formas de testar um software. A pergunta "Como podemos testar?" introduz a reflexão sobre metodologias, níveis e tipos de teste que serão detalhados ao longo da disciplina.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-13-08h40m26s891.jpg" alt="" width="840">
</p>

A imagem reúne manchetes reais que ilustram falhas em sistemas de software e seus impactos: transtornos para usuários, prejuízos financeiros e bugs de grande repercussão. Esses exemplos concretos evidenciam a relevância dos testes para evitar ou minimizar problemas em produção.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-13-08h40m29s731.jpg" alt="" width="840">
</p>

O slide apresenta a definição de teste de software segundo Dias Neto, destacando o teste como um processo de execução do produto para verificar se ele atende às especificações e funciona corretamente no ambiente para o qual foi projetado. Essa definição enfatiza o caráter de validação e verificação da atividade.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-13-08h40m33s309.jpg" alt="" width="840">
</p>

A célebre frase de Edsger W. Dijkstra resume uma verdade fundamental sobre testes: eles podem revelar a existência de defeitos, mas jamais provar a ausência total deles. O slide reforça o objetivo dos testes como uma atividade de busca por falhas, não de certificação de correção absoluta.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-13-08h40m41s053.jpg" alt="" width="840">
</p>

A seção "Para saber mais" disponibiliza links para artigos e notícias que aprofundam os exemplos de bugs e prejuízos mencionados anteriormente, além de textos introdutórios sobre teste de software. Esses recursos complementares permitem que o aluno explore os casos reais e amplie seu conhecimento sobre a importância dos testes.      


### 🟩 Vídeo 03 - Evolução de Testes na Engenharia de Software

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.08-curso.03-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/introducao-a-testes-de-software/learning/662cb408-c15e-4f5e-b05b-3756579b37be?autoplay=1



### 🟩 Vídeo 04 - Conceitos básicos

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.08-curso.03-video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

## Parte 3 - Níveis e técnicas de teste

### 🟩 Vídeo 05 - Níveis de Teste

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.08-curso.03-video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 06 - Técnicas de Teste

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.08-curso.03-video_06.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

## Parte 4 - Testes não funcionais

### 🟩 Vídeo 07 - O que são Testes não funcionais

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.08-curso.03-video_07.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

## Parte 5 - A pirâmide de Testes

### 🟩 Vídeo 08 - A pirâmide de Testes

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.08-curso.03-video_08.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:



##  Materiais de Apoio

# Certificado: 



- Link na plataforma: 
- Certificado em pdf: