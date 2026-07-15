## Instrutor

- Thiago Poiani (Principal Engineer at Skip)
- Contato Linkedin: / [thpoiani](https://www.linkedin.com/in/thpoiani/)

### 🟩 Vídeo 01 - Introdução ao conectando sua API

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.02-video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/conectando-sua-api-com-banco-de-dados-atraves-do-spring-data/learning/fec2f224-9679-45fb-9833-e571d8e7e3b3?autoplay=1

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-15-07h43m38s605.jpg" alt="" width="840">
</p>

Slide de abertura da aula. Ele apresenta o título do curso, **"Conectando sua API com Banco de Dados Através do Spring Data"**, dentro da trilha Jornada Tech, e traz a agenda dividida em cinco blocos:

1. Introdução ao conectando sua API
2. Modelando SQL e NoSQL
3. Criando a API REST para Customers
4. Flexibilidade com NoSQL
5. Aplicações práticas e benefícios do CrewAI

Esse é o slide de abertura, indicando o panorama geral do que será abordado ao longo da jornada.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-15-07h43m56s197.jpg" alt="" width="840">
</p>

Slide "Agenda do Curso", detalhando a estrutura específica deste vídeo introdutório:

- **Fundamentos e Conceitos**
- **Estudo de caso**, desdobrado em três etapas de desenvolvimento
- **Roadmap do Aluno**

Essa agenda confirma o formato do curso: primeiro os fundamentos teóricos sobre persistência, em seguida a apresentação do estudo de caso, depois uma sequência de vídeos de desenvolvimento prático e, por fim, um vídeo de roadmap com desafios e sugestões de aprofundamento.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-15-07h44m15s983.jpg" alt="" width="840">
</p>

Slide "O Desafio da Persistência Moderna", contrastando o **Mundo Relacional** (representado por um arquivo de gavetas, remetendo a tabelas rígidas) e o **Mundo Não-Relacional** (representado por documentos, chaves-valor e grafos interligados).

Três pilares são destacados:

- **Integridade** — transações financeiras impecáveis
- **Flexibilidade** — estruturas dinâmicas que mudam a cada requisição
- **Velocidade** — acesso em milissegundos sob tráfego massivo

Essa imagem não contém código; é um slide conceitual que ilustra por que a arquitetura moderna precisa de um ecossistema poliglota, combinando bancos relacionais (com garantias como o ACID) e bancos não relacionais, cada um atendendo a uma necessidade diferente de negócio.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-15-07h46m16s776.jpg" alt="" width="840">
</p>

Slide "Fase 1: A Era Sombria do JDBC", subtitulado **"O Controle Manual"**. Ele descreve os três grandes fardos do desenvolvimento com JDBC puro:

- Abertura e fechamento manual de conexões
- Mapeamento exaustivo de colunas para objetos
- Tratamento de exceções repetitivo (o famoso "Try/Catch hell")

O destaque final é o custo do **boilerplate**: desenvolvedores gastando tempo escrevendo infraestrutura de acesso a dados em vez de regras de negócio. Não há código nesta imagem, apenas uma representação conceitual dessa fase inicial e mais custosa da persistência em Java.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-15-07h47m27s364.jpg" alt="" width="840">
</p>

Slide "Fase 2: O Salto do ORM (JPA e Hibernate)", subtitulado **"A Especificação que Mudou o Jogo"**. Ele mostra a transição de um Objeto Java para uma Tabela de Banco por meio de mapeamento automático, e explica a mecânica por trás disso:

- **JPA**: a especificação oficial do ecossistema Java para persistência
- **Hibernate**: a engine (ORM) líder de mercado que roda por baixo dos panos

O ganho destacado é o fim do mapeamento manual: o framework passa a assumir a tradução entre o paradigma orientado a objetos e o modelo relacional. Não há trecho de código nesta imagem, apenas o diagrama conceitual dessa evolução.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-15-07h48m39s742.jpg" alt="" width="840">
</p>

Slide "Fase 3: A Revolução Spring Data", subtitulado **"Abstração Baseada em Interfaces"**. Ele ilustra o salto seguinte na evolução da persistência:

- **Sem Implementação Manual**: basta declarar a interface (o exemplo exibido é `interface UserRepository`) e o Spring cria a lógica em tempo de execução.
- **Linguagem Universal**: nomes de métodos se transformam automaticamente em consultas, como no exemplo `findByEmail`.
- **Unificação**: o mesmo padrão de acesso é usado independentemente do banco de dados por trás.

A imagem traz apenas o nome de uma interface e de um método como exemplo ilustrativo dentro do diagrama, não um bloco de código completo — por isso não é reproduzida como snippet de código.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-15-07h50m08s743.jpg" alt="" width="840">
</p>

Slide "Padrões de Design: Onde Fica a Lógica?", que apresenta as duas filosofias arquiteturais possíveis para persistência:

- **ActiveRecord** (Caminho A): a própria entidade se salva.
- **Repository** (Caminho B): um mediador especializado salva a entidade.

O slide reforça que toda ferramenta de abstração precisa escolher uma dessas filosofias de design. Não há código nesta imagem, apenas os dois ícones representando cada padrão arquitetural — o Repository sendo o caminho separado da entidade, atuando como camada de comunicação com o banco.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-15-07h51m25s093.jpg" alt="" width="840">
</p>

Slide "A Síntese: A Família Spring Data", subtitulado **"Uma única abstração para governar todos os mundos"**. Ele mostra a interface Repository do Spring Data no centro, conectando-se a diferentes bancos:

- **PostgreSQL** — traduz o contrato para gerenciar transações e integridade (JPA)
- **MongoDB** — traduz o contrato para flexibilidade estrutural de esquemas
- **Redis** — traduz o contrato para velocidade e expiração de dados

A mensagem central é que o desenvolvedor foca apenas no domínio da aplicação, enquanto o próprio ecossistema Spring Data adapta a linguagem para a ferramenta exata de persistência escolhida.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-15-07h51m53s692.jpg" alt="" width="840">
</p>

Slide "O Salto Arquitetural", que resume os benefícios estratégicos de toda a evolução apresentada até aqui, sustentados por uma "Application Layer":

- **Isolamento do Domínio**: regras de negócio blindadas contra mudanças de infraestrutura ou atualizações de banco de dados.
- **Redução de Complexidade**: eliminação quase total de código boilerplate de infraestrutura e mapeamento manual.
- **Flexibilidade Poliglota**: capacidade de orquestrar SQL, NoSQL e Cache sob a mesma filosofia de design, usando o Padrão Repository.

A conclusão do slide resume o raciocínio de toda a fase conceitual do vídeo: a persistência em Java evoluiu de uma barreira técnica para um facilitador estratégico. Não há bloco de código nesta imagem, apenas o diagrama conceitual dos três pilares.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-15-07h51m58s052.jpg" alt="" width="840">
</p>

Slide "Persistência Poliglota: O Caso do Ticketing System", que introduz o estudo de caso prático do curso: uma arquitetura poliglota usando PostgreSQL (seguro), MongoDB (flexível) e Redis (veloz) para desafios específicos de um sistema de ticketing. O slide organiza a justificativa técnica em quatro frentes:

- **Estratégias de Armazenamento** (Spring Data JPA / PostgreSQL): garante transações financeiras atômicas (ACID) e integridade referencial absoluta nos pedidos.
- **Flexibilidade** (Spring Data MongoDB): gerencia catálogos dinâmicos, já que eventos diferentes (shows vs. conferências) possuem atributos distintos.
- **Performance** (Spring Data Redis): implementa o bloqueio temporário de assentos (Ticket Locking) com expiração automática (TTL).
- **Gestão de Concorrência**: o uso do Redis evita sobrecarga no banco principal durante picos de acesso na escolha de assentos.

Esse slide é o ponto de virada da aula: encerrada a parte conceitual, ele apresenta a justificativa técnica para o estudo de caso que será construído ao longo do curso — um sistema de catálogo de eventos, gerenciamento de usuários e escolha de assentos, combinando bancos relacionais e não relacionais conforme a necessidade de cada domínio.


### 🟩 Vídeo 02 - Modelando SQL e NoSQL

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.02-video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/conectando-sua-api-com-banco-de-dados-atraves-do-spring-data/learning/13762d00-abe3-4831-95f8-7eb6441eccef?autoplay=1

### Anotações

      


### 🟩 Vídeo 03 - Criando a API REST para Customers

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.02-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/conectando-sua-api-com-banco-de-dados-atraves-do-spring-data/learning/cccb92aa-2e9e-429d-8d8a-ba56939b4599?autoplay=1



### 🟩 Vídeo 04 - Flexibilidade com NoSQL

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.02-video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 05 - Multi-Database com Docker

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.02-video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 06 - Criando Endpoints Customizados

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.02-video_06.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 07 - Implementando Redis com Spring Data

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.02-video_07.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 08 - Comunicação entre Microsserviços

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.02-video_08.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 09 - Implementando Persistência com Postgres

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.02-video_09.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 10 - Evitando Overbooking

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.02-video_10.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 11 - Consistência e Governança

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.02-video_11.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 12 - Questionário - Conectando sua API com Banco de Dados Através do Spring Data

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.02-video_12.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:


##  Materiais de Apoio

# Certificado: 

- Link na plataforma: 
- Certificado em pdf: