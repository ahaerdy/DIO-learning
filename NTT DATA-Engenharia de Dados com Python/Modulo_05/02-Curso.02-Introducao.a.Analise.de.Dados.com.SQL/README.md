## Instrutor:

- Juliana Mascarenhas (Tech Education Specialist / Sócia (Content Creator) @SimplificandoRedes / Me Modelagem Computacional / Cientista de dados)
- Contato Linkedin: / [juliana-mascarenhas-ds](https://www.linkedin.com/in/juliana-mascarenhas-ds/)

## Parte 1 - Introdução a Análise de Dados com SQL

### 🟩 Vídeo 01 - Considerações sobre SQL – Ferramenta de todo BI Analyst

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.05-curso.02-video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Este conteúdo encerra um módulo sobre MySQL, destacando a importância da cláusula `WHERE` para a precisão em consultas, atualizações e exclusões de dados. O instrutor reforça a utilização de IDs únicos como boa prática para evitar ambiguidades e revisa conceitos de junção de tabelas (`JOINs`) e união de resultados (`UNION`), enfatizando que a prática constante e a correção de erros são fundamentais para o desenvolvimento profissional na área de banco de dados.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-17-13h51m56s503.jpg" alt="" width="840">
</p>

Este slide introduz o curso de **SQL Analytics**, direcionado especificamente para o perfil de **Power BI Analyst**. A proposta inicial é explorar como o SQL serve de base para a análise de dados, aproveitando a organização e as restrições de integridade dos bancos de dados relacionais para garantir consultas precisas no contexto de Business Intelligence.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-17-13h52m00s332.jpg" alt="" width="840">
</p>

O SQL, com origem na década de 1970, é apresentado como a linguagem padrão para a manipulação de dados e execução de operações. É uma ferramenta transversal e essencial para diversos profissionais do ecossistema de dados, incluindo **DBAs**, especialistas em **BI**, desenvolvedores (**Devs**) e cientistas de dados (**DS**).

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-17-13h52m02s371.jpg" alt="" width="840">
</p>

As instruções SQL são classificadas em quatro categorias principais baseadas em suas funções:

* **DDL (Data Definition Language):** Comandos de definição, como a criação de estruturas.
* **DML (Data Manipulation Language):** Comandos para manipular os registros (inserção, edição).
* **DCL (Data Control Language):** Comandos de controle de acesso e segurança.
* **DQL (Data Query Language):** Comandos de consulta, fundamentais para a análise de dados e a geração de perguntas ao banco.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-17-13h52m04s638.jpg" alt="" width="840">
</p>

O diagrama reforça a ideia do SQL como uma interface de "Ordem de Serviço" que traduz necessidades humanas para o sistema de gerenciamento de banco de dados. Ele destaca a evolução histórica e a importância de entender a estrutura de armazenamento para que as perguntas feitas aos dados façam sentido lógico.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-17-13h52m06s470.jpg" alt="" width="840">
</p>

Abaixo, a exemplificação prática de comandos **DDL** para a criação de uma estrutura tabular. O código demonstra a criação de um banco de dados e de uma tabela chamada `person` com seus respectivos tipos e restrições:

```sql
-- Visualizar bancos existentes
show databases;

-- Criar o banco de dados de exemplo
create database if not exists first_example;

-- Selecionar o banco de dados
use first_example;

-- Criar tabela com tipos de dados e chave primária
CREATE TABLE person(
    person_id smallint unsigned,
    fname varchar(20),
    lname varchar(20),
    gender enum('M', 'F'),
    birth_date DATE,
    street varchar(30),
    city varchar(20),
    state varchar(20),
    country varchar(28),
    postal_code varchar(28),
    constraint pk_person primary key (person_id)
);

-- Exibir a estrutura da tabela
desc person;

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-17-13h52m17s191.jpg" alt="" width="840">
</p>

Neste exemplo, são apresentadas operações de **DQL** avançado para análise, utilizando funções de agregação e subconsultas para responder perguntas complexas de negócio:

```sql
-- Agregação de salários por departamento usando JOIN
SELECT SUM(Salary), MAX(Salary), MIN(Salary), AVG(Salary)
 FROM (employee JOIN departament ON Dno = Dnumber)
 WHERE Dname = 'Research';

-- Subconsulta para localizar funcionários com 2 ou mais dependentes
SELECT Lname, Fname
FROM employee
WHERE (SELECT count(*)
       FROM dependent
       WHERE Ssn = Essn) >= 2;

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-17-13h52m21s283.jpg" alt="" width="840">
</p>

O conceito de **SQL Analytics** é definido como o uso estratégico da linguagem para transformar e analisar dados brutos em informações úteis para o Business Intelligence, focando na extração de padrões e métricas relevantes para a tomada de decisão.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-17-13h52m28s899.jpg" alt="" width="840">
</p>

O plano de estudo para a formação foca em um workflow orientado a problemas reais. O processo envolve a definição de um cenário de negócio, a identificação do problema e a aplicação de diferentes tipos de análise: **Descritiva** (o que aconteceu), **Diagnóstica** (por que aconteceu) e, eventualmente, evoluindo para visões **Preditivas** ou **Prescritivas**.


link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/introducao-a-analise-de-dados-com-sql/learning/a9ab1473-a2f1-4e7c-a832-6f758ce1ee21?autoplay=1

### 🟩 Vídeo 02 - Workbench e DBeaver : Explorando Ferramentas de Acesso à Banco de dados

link do vídeo:

### 🟩 Vídeo 03 - Analisando a Sample do DBeaver persistida no SQLite

link do vídeo:

### 🟩 Vídeo 04 - O que fazer em um primeiro contato com base de dados?

link do vídeo:

### 🟩 Vídeo 05 - Análise Descritiva: Caracterizando os Registros dos Artistas

link do vídeo:

### 🟩 Vídeo 06 - Análise Descritiva: Caracterizando os Registros de Customers

link do vídeo:

### 🟩 Vídeo 07 - Análise Descritiva: Caracterizando os Registros de Invoice

link do vídeo:

### 🟩 Vídeo 08 - Utilizando Junções para Caracterizar os Dados

link do vídeo:

### 🟩 Vídeo 09 - Analisando Frequência dos Dados com Funções de Agregação

link do vídeo:

### 🟩 Vídeo 10 - Discretização com CASE Statement

link do vídeo:

### 🟩 Vídeo 11 - Aplicando Padronização na Recuperação dos Dados com CASE Statement

link do vídeo:


##  Materiais de Apoio

# Certificado: 

- Link na plataforma: 
- Certificado em pdf: 
