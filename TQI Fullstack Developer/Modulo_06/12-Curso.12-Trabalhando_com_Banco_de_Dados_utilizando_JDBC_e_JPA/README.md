## Instrutor

- Daniel Karam Venceslau (Software Engineer | Elixir)
- Contato Linkedin: / [daniel-kv](https://www.linkedin.com/in/daniel-kv/)

## Parte 1 - Introdução ao JDBC

### 🟩 Vídeo 01 - Introdução ao Banco de Dados

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.12-video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/trabalhando-com-banco-de-dados-utilizando-jdbc-e-jpa/learning/36caf662-304d-444b-978d-958d79bb5a9a?autoplay=1

Este vídeo resume a introdução ao curso de integração entre Java e sistemas de gerenciamento de banco de dados (SGBD), cobrindo desde os objetivos principais até os requisitos técnicos necessários para o acompanhamento das aulas.

### Anotações

#### Introdução ao Curso

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-09-08h11m00s929.jpg" alt="" width="840">
</p>

Esta aula inicia o curso de Java e o Banco de Dados , focando especificamente no JDBC Básico. O conteúdo é apresentado por Daniel Karam , que atua como Senior Software Developer.

#### Objetivos da Aula

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-09-08h11m04s014.jpg" alt="" width="840">
</p>

Os tópicos principais definidos para a aula são:

* **Configurar Banco de Dados**: Preparação do ambiente de armazenamento.
* **JDBC e drivers de conexão**: Entendimento da interface de conectividade do Java.
* **Consultas com JDBC**: Realização de operações de busca de dados.

#### Requisitos Básicos

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-09-08h11m05s977.jpg" alt="" width="840">
</p>

Para o acompanhamento do curso, são necessários os seguintes componentes técnicos:

* **MySQL (SGBD)**: Incluindo noções fundamentais de SQL para manipulação de dados.
* **Java Development Kit (JDK)**: Versão 1.8 ou superior.
* **Intellij 2019.2.3 IDE**: Ambiente de desenvolvimento integrado utilizado.
* **Gradle 5.3.1**: Ferramenta de automação utilizada especificamente para baixar o Driver JDBC.

#### Materiais

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-09-08h11m10s094.jpg" alt="" width="840">
</p>

Os materiais didáticos e códigos utilizados durante a aula estão hospedados no GitHub. O endereço direto para o repositório é:
`https://github.com/danielkv7/digital-innovation-one/tree/master/jdbc-basico`.

#### Estrutura do Repositório

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-09-08h11m12s830.jpg" alt="" width="840">
</p>

A interface do GitHub exibe a organização dos arquivos na pasta `Aula_JDBC_basico`. Entre os arquivos disponíveis estão:

* **Projeto e slides**: Localizados na pasta `jdbc-basico`.
* **Template de apresentação**: Arquivos no formato `.odp` contendo o conteúdo visual da aula.


### 🟩 Vídeo 02 - Configurar Banco de Dados

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.12-video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/trabalhando-com-banco-de-dados-utilizando-jdbc-e-jpa/learning/a069a2bd-5368-46df-8af1-7a65fd602296?autoplay=1

Este guia prático detalha o processo de instalação, configuração e manipulação inicial de um banco de dados MySQL, preparando o ambiente para integração com sistemas Java.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-09-08h34m54s442.jpg" alt="" width="840">
</p>

Esta etapa inicial foca na configuração do ambiente de banco de dados. O objetivo é preparar o ecossistema para que uma aplicação Java consiga se comunicar e persistir informações de forma eficiente.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-09-08h34m57s545.jpg" alt="" width="840">
</p>

Um Banco de Dados (BD) é um software especializado em armazenar dados de forma estruturada. Essa organização aumenta a eficiência computacional, reduzindo o consumo de memória e o tempo de processamento durante operações de busca e atualização. Para este aprendizado, utilizaremos o MySQL, um sistema de gerenciamento de banco de dados (SGBD) relacional amplamente adotado no mercado.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-09-08h35m00s076.jpg" alt="" width="840">
</p>

O processo de configuração está dividido em cinco passos fundamentais:

1. **Instalação do MySQL**: Preparação do SGBD na máquina local.
2. **Configuração de Credenciais**: Definição de usuário e senha para acesso seguro.
3. **Instalação do MySQL Workbench**: Adição de uma interface gráfica (GUI) para facilitar a visualização dos dados.
4. **Criação do Banco de Dados**: Instanciação do ambiente onde as tabelas residirão.
5. **Criação de Tabela**: Definição da estrutura de dados que será manipulada pela aplicação Java.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-09-08h35m03s856.jpg" alt="" width="840">
</p>

Para criar a estrutura necessária para a aula, utilizamos scripts SQL que definem o banco de dados e a tabela de alunos, especificando tipos de dados como inteiros e caracteres, além de restrições como chaves primárias e campos obrigatórios.

```sql
CREATE database digital_innovation_one;

USE digital_innovation_one;

CREATE TABLE aluno (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(80) NOT NULL,
    idade INTEGER NOT NULL,
    estado CHARACTER(2) NOT NULL
);
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-09-08h35m08s297.jpg" alt="" width="840">
</p>

A instalação no ambiente Ubuntu 18.04 é realizada via terminal, começando pela atualização dos repositórios e seguindo para a instalação do servidor MySQL. Após a instalação, é necessário configurar a senha do usuário `root` para permitir acessos futuros via aplicação ou interface gráfica.

```bash
# Atualizar repositórios do sistema
sudo apt update

# Instalar o servidor MySQL
sudo apt install mysql-server

# Verificar a versão instalada para confirmar o sucesso
mysql --version

# Acessar o MySQL e configurar a senha (dentro do prompt MySQL)
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password';
FLUSH PRIVILEGES;
```

O tutorial segue os passos disponíveis no arquivo "DatabaseInstructions" (https://github.com/ahaerdy/fork-DIO-java-jdbc-basico-daniel-kv/blob/master/src/main/java/part1/DatabaseInstructions) para instanciar os bancos de dados, tabelas e alguns registros para teste.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-09-08h35m27s545.jpg" alt="" width="840">
</p>

O MySQL Workbench oferece uma visão clara da estrutura criada e dos dados inseridos. Através da interface, é possível executar comandos SQL e visualizar o resultado em formato de grade, o que é especialmente útil para validar se as inserções de dados (como os registros de Pedro, Maria, João e Ana) foram realizadas corretamente antes de iniciar a integração com o código Java.

```sql
-- Selecionar todos os registros da tabela para validação
SELECT * FROM aluno;
```      


### 🟩 Vídeo 03 - JDBC e drivers de conexão

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.12-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/trabalhando-com-banco-de-dados-utilizando-jdbc-e-jpa/learning/efa48762-3c2f-44a4-befc-3ac7fb124e99?autoplay=1

### 🟩 Vídeo 04 - Consultas com JDBC

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.12-video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 05 - Java JDBC Básico

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.12-video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

## Parte 2 - Trabalhando com JPA

### 🟩 Vídeo 06 - Introdução da aula

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.12-video_06.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 07 - Entendendo o JPA e começando o mapeamento do banco

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.12-video_07.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 08 - Implementações do JPA

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.12-video_08.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 09 - Linguagens de consulta orientada a objetos

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.12-video_09.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:



##  Materiais de Apoio

# Certificado: 

- Link na plataforma: 
- Certificado em pdf: