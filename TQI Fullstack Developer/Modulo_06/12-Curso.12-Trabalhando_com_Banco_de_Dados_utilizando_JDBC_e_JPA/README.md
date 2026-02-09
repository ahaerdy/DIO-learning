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

Esta aula foca na Parte 2 do aprendizado de Java com Banco de Dados, detalhando o que é o JDBC (Java Database Connectivity) e como estabelecer uma conexão real com o MySQL. O objetivo principal é entender a abstração que o Java oferece para lidar com diferentes bancos de dados de forma padronizada.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-09-09h36m23s562.jpg" alt="" width="840">
</p>

Esta imagem introduz a segunda parte do curso, focada em **JDBC e drivers de conexão** no contexto de Java e Banco de Dados. O JDBC (Java Database Connectivity) é apresentado como a ferramenta fundamental para permitir que aplicações escritas em Java se comuniquem com diferentes sistemas de gerenciamento de banco de dados.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-09-09h36m26s359.jpg" alt="" width="840">
</p>

O JDBC é uma API composta por classes e interfaces nos pacotes `java.sql` e `javax.sql` que padronizam a conexão com bancos de dados. Sem essa API, o desenvolvedor precisaria conhecer o protocolo proprietário de cada banco. Com o JDBC, utiliza-se uma interface única, delegando ao **driver** a implementação das especificidades de cada banco, o que permite ao desenvolvedor focar apenas na seleção do driver correto e na criação das queries SQL.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-09-09h36m35s640.jpg" alt="" width="840">
</p>

As principais ferramentas para estabelecer essa conexão são a classe **DriverManager** e a interface **Connection**. A `DriverManager` gerencia os drivers disponíveis e utiliza uma URL específica (contendo o driver, a localização e o nome do banco) para criar uma instância de `Connection`. Esta interface, por sua vez, representa a conexão ativa e permite a criação de "Statements" para a execução de comandos SQL.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-09-09h36m37s641.jpg" alt="" width="840">
</p>

O processo de conexão segue três passos fundamentais:

1. **Download do driver**: Obter o driver específico do banco (neste caso, MySQL) via Gradle, Maven ou manualmente.
2. **Criação da URL**: Definir a string de conexão com os parâmetros do driver, endereço e nome do banco de dados.
3. **Estabelecimento da conexão**: Utilizar o método `getConnection` da `DriverManager`, fornecendo a URL, o usuário e a senha.

A implementação prática em Java utiliza o bloco *try-with-resources* para garantir que a conexão seja fechada automaticamente. O código abaixo demonstra a definição dos parâmetros, a construção da URL via `StringBuilder` (incluindo parâmetros de timezone para evitar erros no Linux) e a tentativa de conexão.

```java
package part2; // Define que este arquivo pertence ao pacote 'part2', organizando a estrutura do projeto.

// Importa a interface Connection, que gerencia a sessão de comunicação com o banco de dados.
import java.sql.Connection;
// Importa a classe DriverManager, responsável por selecionar o driver correto e estabelecer a conexão.
import java.sql.DriverManager;
// Importa a classe SQLException, que serve para tratar erros que possam ocorrer no banco (ex: senha errada).
import java.sql.SQLException;

public class ConnectionJDBC {

    // Método principal (main) onde a execução do programa Java começa.
    public static void main(String[] args) {

        // 1 - DEFINIÇÃO DOS PARAMETROS DE CONEXÃO

        // Define qual tecnologia de banco de dados estamos usando (neste caso, MySQL).
        String driver = "mysql";

        // Define o endereço do servidor. 'localhost' indica que o banco está na sua própria máquina (Kubuntu).
        String dataBaseAddress = "localhost";

        // Define o nome exato do banco de dados que foi criado anteriormente via terminal.
        String dataBaseName = "digital_innovation_one";

        // Define o usuário administrador do banco de dados.
        String user = "root";

        // Define a senha de acesso. Aqui usamos "123456" conforme você configurou no seu sistema.
        String password = "123456";

        // 2 - CONSTRUÇÃO DA URL DE CONEXÃO

        // Instancia um StringBuilder para montar a String de conexão de forma eficiente e legível.
        StringBuilder sb = new StringBuilder("jdbc:")
                .append(driver).append("://")            // Adiciona o protocolo e driver: "jdbc:mysql://"
                .append(dataBaseAddress).append("/")     // Adiciona o endereço: "localhost/"
                .append(dataBaseName)                    // Adiciona o nome do banco: "digital_innovation_one"
                // Adiciona parâmetros vitais para o Linux/Kubuntu para evitar erros de fuso horário.
                .append("?useTimezone=true&serverTimezone=UTC");

        // Converte o objeto construtor (StringBuilder) para uma String final que o JDBC entende.
        String connectionUrl = sb.toString();

        // 3 - TENTATIVA DE CONEXÃO (TRY-WITH-RESOURCES)

        /* O comando 'try (Connection conn = ...)' é especial.
           Ele tenta abrir a conexão. Se conseguir, executa o bloco.
           Ao final (dando certo ou erro), ele FECHA a conexão automaticamente, liberando memória.
        */
        try (Connection conn = DriverManager.getConnection(connectionUrl, user, password)) {

            // Se a linha acima funcionar, o código entra aqui e imprime a mensagem de sucesso.
            System.out.println("SUCESSO ao se conectar ao banco MySQL no Kubuntu!");

        } catch (SQLException e) {
            // Se algo der errado (senha inválida, banco desligado, etc), o código pula para cá.

            // Imprime uma mensagem simples avisando que falhou.
            System.out.println("FALHA ao se conectar ao banco MySQL!");

            // Imprime o motivo técnico do erro (ex: "Access denied for user 'root'").
            System.out.println("Motivo: " + e.getMessage());

            // Imprime o rastro completo do erro no console (linhas vermelhas) para ajudar a corrigir.
            e.printStackTrace();
        }
    }
}
```

O uso do bloco `try-catch` (especificamente o **try-with-resources**) no código de conexão JDBC não é apenas uma boa prática, mas uma medida de segurança e estabilidade para a aplicação.

Aqui está a análise detalhada da sua necessidade e finalidade:

#### 1. A Natureza da Operação (Por que o Try?)

Conectar-se a um banco de dados é uma operação considerada "instável" por natureza. Diferente de somar dois números, uma conexão depende de fatores externos ao código Java:

* O banco de dados pode estar desligado.
* A rede pode oscilar.
* As credenciais (usuário/senha) podem estar incorretas.

Por isso, o Java **obriga** o uso de tratamento de exceções. Se você tentar usar o `DriverManager.getConnection` sem um bloco `try-catch`, o código nem sequer compilará. O bloco `try` sinaliza ao programa: *"Tente executar este conjunto de instruções que podem falhar a qualquer momento"*.

#### 2. O Recurso "With-Resources" (A finalidade do parêntese)

Note que no código o `Connection` é declarado dentro de parênteses: `try (Connection conn = ...)`. Esta é uma funcionalidade do Java 7 chamada **Try-with-resources**.

Sua finalidade é a **gestão de memória e recursos**. Uma conexão com o banco de dados consome memória tanto na sua aplicação quanto no servidor do banco. Se você abrir milhares de conexões e não as fechar, o banco de dados travará por falta de recursos (o famoso "leak").

* **Antigamente:** Era necessário um bloco `finally` e um comando `conn.close()` manual.
* **Atualmente:** Ao usar essa estrutura, o Java garante que, assim que o bloco terminar (seja com sucesso ou com erro), a conexão será **fechada automaticamente**.

#### 3. O Bloco Catch (A Narrativa do Erro)

O bloco `catch (SQLException e)` é o seu "plano de contingência". Sem ele, se houvesse um erro de senha, a aplicação simplesmente "quebraria" (pararia de funcionar) e mostraria uma mensagem técnica indecifrável para o usuário final.

Sua finalidade técnica é:

* **Interceptar a Exceção:** Capturar o objeto `SQLException`, que contém os detalhes do que deu errado.
* **Tratamento Amigável:** No código, ele imprime uma mensagem clara: *"FALHA ao se conectar ao banco MySQL!"*.
* **Diagnóstico:** O uso de `e.getMessage()` e `e.printStackTrace()` permite que o desenvolvedor saiba exatamente se o problema foi uma senha errada, um banco inexistente ou um erro de rede, sem interromper abruptamente o fluxo de outros processos que o sistema possa estar executando.

#### Resumo Técnico

| Componente | Função Técnica | Finalidade Prática |
| --- | --- | --- |
| **Try block** | Delimita a zona de risco. | Isolar o código que depende do banco de dados externo. |
| **Resources ( )** | Implementa `AutoCloseable`. | Garante o fechamento da conexão e evita vazamento de memória. |
| **Catch block** | Captura `SQLException`. | Evita que o programa encerre e fornece logs para correção do erro. |


<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-09-10h37m03s491.jpg" alt="" width="840">
</p>

Para encontrar a dependência correta do driver, uma prática comum é realizar uma busca em repositórios como o **MVN Repository**. A imagem mostra uma pesquisa por "mysql jdbc driver", que lista diversas versões do `mysql-connector-java` disponíveis para integração em projetos que utilizam gerenciadores de dependências.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-09-10h37m07s770.jpg" alt="" width="840">
</p>

Ao acessar o repositório Maven, é possível visualizar as diferentes versões do artefato (como a 8.0.17 ou a 5.1.48) e escolher a mais adequada para o projeto. O site fornece informações sobre a data de lançamento e a popularidade de cada versão.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-09-10h37m29s981.jpg" alt="" width="840">
</p>

Após selecionar a versão desejada, o repositório disponibiliza os trechos de configuração prontos para serem copiados. Na imagem, observa-se a aba do **Gradle**, onde a linha de dependência para a versão 8.0.17 do `mysql-connector-java` é fornecida para ser colada no arquivo de configuração do projeto.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-09-10h37m36s448.jpg" alt="" width="840">
</p>

No IntelliJ IDEA, a dependência é adicionada dentro do bloco `dependencies` no arquivo `build.gradle`. Após a sincronização, o driver é baixado automaticamente, permitindo que a execução da classe `ConnectionJDBC` resulte em sucesso, conforme indicado no console da IDE com o código de saída 0.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-09-10h38m40s695.jpg" alt="" width="840">
</p>

Para consolidar o aprendizado, os exercícios finais propõem:

1. Criar um novo usuário no banco de dados e conectar-se com ele via JDBC.


2. Explorar os métodos das classes `DriverManager` e `Connection` através da IDE ou documentação oficial.


3. Configurar um banco de dados diferente (PostgreSQL ou H2) e realizar a conexão utilizando o driver apropriado.      



### 🟩 Vídeo 04 - Consultas com JDBC

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.12-video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/trabalhando-com-banco-de-dados-utilizando-jdbc-e-jpa/learning/f0c8a11c-cb6c-474b-bb44-fac4b5d575ef?autoplay=1

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