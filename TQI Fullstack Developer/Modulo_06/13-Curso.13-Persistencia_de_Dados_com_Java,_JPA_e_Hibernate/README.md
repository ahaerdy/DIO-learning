## Instrutor

- Joao Paulo Oliveira Santos (Senior Fullstack Engineer | Payment Systems (5B txns) + DeFi ($250M TVL) | Python•Java•Node.js•Go•React•React Native | Blockchain & Stablecoins | Remote)
- Contato Linkedin: / [desenvolvedorjoaopaul](https://www.linkedin.com/in/desenvolvedorjoaopaul/)

# Parte 1 - Entendendo a estrutura do projeto

## 🟩 Vídeo 01 - Introdução e objetivos do curso

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.13-video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/jpa-com-hibernate/learning/6cb58f70-30ac-4de8-aaa8-f440efa3bc97?autoplay=1

Este guia resume a trajetória profissional do palestrante, sua filosofia de aprendizado contínuo e os objetivos técnicos da sessão sobre tecnologias de persistência em Java.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-10-14h41m42s112.jpg" alt="" width="840">
</p>

Esta apresentação introduz o tema de **persistência de dados utilizando JPA (Java Persistence API) com Hibernate**. O conteúdo é ministrado por **João Paulo Oliveira Santos**, especialista de TI no Banco Inter, com o objetivo de demonstrar como essas tecnologias facilitam a comunicação entre aplicações Java e bancos de dados relacionais.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-10-14h47m48s064.jpg" alt="" width="840">
</p>

Neste momento, o palestrante compartilha sua trajetória profissional de aproximadamente seis anos na área de TI, destacando seu início com tecnologias legadas (JSP com Java) e passagens por instituições como o Sicoob. É reforçada a importância da **curiosidade técnica** e do aprendizado contínuo ao se deparar com novos termos e tecnologias. Para acompanhar o material prático e manter contato, são disponibilizados os seguintes links:

* **Instagram:** joaoolisantos
* **LinkedIn:** [desenvolvedorjoaopaulo](https://www.linkedin.com/in/desenvolvedorjoaopaulo)
* **GitHub:** [jpbaterabsb](https://github.com/jpbaterabsb) (onde os códigos e slides da live estão disponíveis)

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-10-14h47m54s083.jpg" alt="" width="840">
</p>

O roteiro principal da aula está estruturado para cobrir desde conceitos fundamentais até a aplicação prática. Os **objetivos centrais** incluem:

* **JDBC:** Compreender a base da conectividade Java com bancos de dados.
* **ORM (Object-Relational Mapping):** Entender o conceito de mapeamento objeto-relacional, que é o pilar para frameworks de persistência.
* **JPA:** Definir o papel da especificação Java Persistence API.
* **Prática:** Aplicar os conceitos de JPA e Hibernate no desenvolvimento de uma solução de persistência.

## 🟩 Vídeo 02 - O que é JDBC

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.13-video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/jpa-com-hibernate/learning/6ee17344-4f78-42e2-b2ff-d9fd0415ed4b?autoplay=1

O JDBC é a tecnologia fundamental que permite a aplicações Java interagirem com bancos de dados relacionais (SQL). Ele atua como uma camada de abstração ou "contrato", garantindo que o código Java seja padronizado, independentemente de o banco de dados ser MySQL, PostgreSQL, Oracle ou qualquer outro que suporte SQL.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-10-15h05m29s584.jpg" alt="" width="840">
</p>

O JDBC (Java Database Connectivity) foi criado como uma estratégia para permitir que a linguagem Java se conectasse a bancos de dados SQL. Antes de sua existência, não havia uma padronização, o que obrigaria cada desenvolvedor a criar métodos próprios de conexão para cada tipo de banco (MySQL, PostgreSQL, Oracle, etc.). O JDBC estabelece um contrato de padronização para que todos os bancos SQL conversem com o Java de uma única forma.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-10-15h05m36s292.jpg" alt="" width="840">
</p>

O JDBC consiste em um conjunto de classes e interfaces (APIs) escritas em Java que permitem o envio de instruções SQL para qualquer banco de dados relacional. Ele funciona como uma biblioteca que facilita o desenvolvimento ao fornecer as ferramentas necessárias para a comunicação entre a aplicação e o banco.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-10-15h05m42s107.jpg" alt="" width="840">
</p>

Embora o JDBC forneça as interfaces necessárias dentro da JDK, ele funciona apenas como um contrato. Por serem interfaces, elas não possuem uma implementação própria direta para cada banco de dados. Surge então a necessidade de uma "ponte" ou implementação que conecte a aplicação Java ao banco de dados real, permitindo que as chamadas de métodos funcionem corretamente sem retornar erros de nulidade.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-10-15h06m07s569.jpg" alt="" width="840">
</p>

Para gerenciar a conexão, é criada uma classe utilitária (como a classe `DB`), que utiliza a interface `Connection` para obter instâncias de conexão com o banco de dados.

```java
package one.digitalinnovation.jdbc.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_cadastro_cliente", "root", "root");
        }
        return connection;
    }
}
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-10-15h06m13s336.jpg" alt="" width="840">
</p>

Ao verificar a estrutura interna da JDK (como a versão 1.8), confirma-se que classes fundamentais do pacote `java.sql`, como a `Connection`, são na verdade interfaces. Outros componentes como `ResultSet`, `PreparedStatement` e `Statement` também seguem este modelo de interface, enquanto o `DriverManager` é uma classe concreta.

```java
public interface Connection extends Wrapper, AutoCloseable {
    Statement createStatement() throws SQLException;
    PreparedStatement prepareStatement(String sql) throws SQLException;
    // ...
}
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-10-15h06m22s653.jpg" alt="" width="840">
</p>

Como o JDBC define apenas os contratos, os fabricantes de sistemas de gerenciamento de banco de dados (SGBDs) devem fornecer os drivers que implementam essas interfaces. Na IDE, é possível visualizar que a implementação da interface `Connection` é fornecida por classes específicas do driver, como a `ConnectionImpl` vinda do pacote do MySQL.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-10-15h06m32s330.jpg" alt="" width="840">
</p>

A classe `ConnectionImpl` é a implementação real do driver (neste caso, do MySQL) para a interface `Connection` do Java. É dentro dessas classes do driver que ocorrem os detalhes técnicos "por debaixo dos panos", como a comunicação via protocolos, segurança SSL e criptografia de dados entre a aplicação e o banco.

```java
package com.mysql.cj.jdbc;

public class ConnectionImpl implements JdbcConnection, SessionEventListener, Serializable {
    // Implementação dos métodos do driver
}
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-10-15h06m44s135.jpg" alt="" width="840">
</p>

Em projetos Maven, o driver necessário para a implementação das interfaces JDBC é adicionado como uma dependência no arquivo `pom.xml`. Ao importar o driver (ex: `mysql-connector-java`), a aplicação passa a ter as implementações concretas que o Java SQL utilizará automaticamente para realizar as operações no banco.

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.16</version>
</dependency>
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-10-15h06m53s258.jpg" alt="" width="840">
</p>

Para facilitar o ambiente de desenvolvimento, pode-se utilizar um arquivo `docker-compose.yml` para subir uma instância do banco de dados MySQL sem a necessidade de instalação manual. O arquivo define configurações como imagem, portas, senhas e o nome do banco de dados que será utilizado pela aplicação.

```yaml
services:
  db:
    image: mysql
    command: --default-authentication-plugin=mysql_native_password
    restart: always
    ports:
      - 3306:3306
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: db_cadastro_cliente
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-10-15h06m59s093.jpg" alt="" width="840">
</p>

O arquivo `README.md` do projeto detalha os pré-requisitos e instruções para execução, incluindo links para instalação do Docker e comandos para subir o banco de dados via terminal com `docker-compose up`. É essencial que o banco de dados esteja rodando e devidamente configurado com usuário e senha para que os testes do JDBC funcionem corretamente.      


## 🟩 Vídeo 03 - Estrutura do código - Parte 1

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.13-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/jpa-com-hibernate/learning/ad5fc6b4-496d-4d3f-829b-fe6f8d81d15d?autoplay=1

Este guia explora a implementação prática de conexões com bancos de dados relacionais usando Java. O foco está na criação de uma arquitetura robusta, gerenciamento de conexões e manipulação de dados através do padrão DAO.

### Anotações

      


## 🟩 Vídeo 04 - O que são ORM, JPA e Hibernate

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.13-video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/jpa-com-hibernate/learning/3174d9aa-2160-4f76-9b88-2825d38278c1?autoplay=1

## 🟩 Vídeo 05 - Estrutura do código - Parte 2

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.13-video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

## Parte 2 - Desenvolvimento da comunicação com banco de dados

## 🟩 Vídeo 06 - Estrutura básica do banco de dados

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.13-video_06.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

## 🟩 Vídeo 07 - Desenvolvendo o código de persistência de dados

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.13-video_07.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

## 🟩 Vídeo 08 - Como funciona uma transação

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.13-video_08.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

## 🟩 Vídeo 09 - Por que utilizar o Hibernate?

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.13-video_09.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

## 🟩 Vídeo 10 - Dúvidas e comentários finais

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.13-video_10.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:


##  Materiais de Apoio

# Certificado: 

- Link na plataforma: 
- Certificado em pdf: