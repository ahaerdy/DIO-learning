## Instrutor

- Gleyson Sampaio (Analista Desenvolvedor Java)
- Contato Linkedin: / [glysns](https://www.linkedin.com/in/glysns/)

## Parte 1 - Apresentação e visão geral do curso

### 🟩 Vídeo 01 - Apresentação e visão geral do curso

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.07-curso.04-video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/adicionando-seguranca-a-uma-api-rest-com-spring-security/learning/99032de1-b0da-4986-9907-6028acc4202d?autoplay=1

Este curso é uma imersão no ecossistema de segurança do Spring Framework, com foco específico em Spring Boot Security. O objetivo principal é capacitar desenvolvedores a proteger aplicações web modernas, com ênfase em APIs REST, garantindo que o acesso a dados e funcionalidades seja restrito e monitorado.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-02-15h42m24s082.jpg" alt="" width="840">
</p>

Esta série de vídeos introduz o ecossistema do **Spring Boot Security**, uma iniciativa da Digital Innovation One focada em prover camadas de proteção robustas para aplicações modernas. O objetivo central é demonstrar como adicionar segurança de forma eficiente a **APIs REST**, utilizando as facilidades e padrões oferecidos pelo ecossistema Spring Framework para garantir a integridade e o controle de acesso dos dados.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-02-15h42m28s922.jpg" alt="" width="840">
</p>

A instrução é conduzida por **Gleyson Sampaio**, profissional com mais de 10 anos de experiência no desenvolvimento de software. Sua trajetória abrange setores críticos como o tributário, bancário, seguradoras e instituições de ensino. Além da experiência prática em grandes instituições (como indicado pelos logotipos da BM&FBovespa e Oracle), o instrutor possui a certificação **Oracle Certified Professional Java SE 6 Programmer**, consolidando o embasamento técnico que será aplicado durante o curso.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-02-15h42m30s902.jpg" alt="" width="840">
</p>

O roteiro de aprendizado está estruturado para cobrir desde fundamentos até implementações avançadas de segurança. Os tópicos principais incluem:

* **Introdução sobre Segurança**: Conceitos base para entender a proteção de dados.
* **Habilitando segurança com Spring**: Como ativar o framework no projeto.
* **Configure Adapter**: Uso de adapters para ganhar flexibilidade além das configurações padrão.
* **Autenticação com banco de dados**: Gerenciamento de registros de usuários e perfis de acesso diretamente via banco de dados.
* **JWT (JSON Web Token)**: Implementação de tokens para autenticação stateless em APIs.


## Parte 2 - Springboot Security com JWT

### 🟩 Vídeo 02 - Habilitando segurança com Spring

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.07-curso.04-video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/adicionando-seguranca-a-uma-api-rest-com-spring-security/learning/e7af8139-2278-4bcd-9872-56a2d0288c58?autoplay=1

Este guia explora os conceitos básicos do framework Spring Security, detalhando como ele protege aplicações Java, a diferença entre autenticação e autorização, e como realizar a configuração inicial em um projeto Spring Boot.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-02-16h12m58s396.jpg" alt="" width="840">
</p>

O Spring Security é apresentado fundamentalmente como um conjunto de filtros de servlet. Sua função principal é facilitar a implementação de camadas de autenticação e autorização em aplicações web desenvolvidas sobre o ecossistema Spring. Ao habilitar as dependências apropriadas, o framework configura automaticamente filtros padrões que interceptam as requisições para garantir a segurança da aplicação.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-02-16h13m02s897.jpg" alt="" width="840">
</p>

Para compreender o funcionamento do framework, é necessário distinguir três conceitos fundamentais:

* **Autenticação:** É o processo de verificação da identidade, respondendo à pergunta "Quem é você?". Geralmente utiliza credenciais como usuário e senha.
* **Autorização:** Ocorre após a autenticação e determina se o usuário possui permissões para executar uma ação ou acessar dados específicos ("O usuário pode fazer/ler isso?").
* **Princípio (Principal):** Refere-se ao usuário que está atualmente autenticado no sistema.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-02-16h13m13s536.jpg" alt="" width="840">
</p>

Em projetos que utilizam Spring Boot Web, a ativação dos recursos de segurança é simplificada, exigindo apenas a inclusão da dependência starter correspondente no arquivo de configuração do Maven (`pom.xml`).

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-02-16h13m20s639.jpg" alt="" width="840">
</p>

Ao iniciar um projeto do zero utilizando o Spring Initializr, definimos os metadados do projeto. Neste exemplo, o projeto é configurado como um projeto Maven, utilizando a linguagem Java, com o Group `dio` e o Artifact `dio-spring-security`.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-02-16h13m25s142.jpg" alt="" width="840">
</p>

Para criar uma aplicação web segura, selecionamos as dependências **Spring Web** (para construção de aplicações RESTful e uso do Tomcat) e **Spring Security** (para autenticação e controle de acesso). Nesta configuração, utiliza-se o Java 8 e o empacotamento do tipo Jar.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-02-16h13m34s713.jpg" alt="" width="840">
</p>

Após a importação do projeto na IDE, verificamos no arquivo `pom.xml` se as dependências foram carregadas corretamente. O trecho abaixo confirma a presença dos starters necessários para o funcionamento da web e da segurança:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-02-16h13m38s069.jpg" alt="" width="840">
</p>

A classe principal da aplicação, `DioSpringSecurityApplication.java`, é apresentada com a anotação `@SpringBootApplication`. Este é o ponto de entrada que inicializa todo o ecossistema do Spring Boot, incluindo os filtros de segurança recém-adicionados.

```java
package dio.spring.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DioSpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(DioSpringSecurityApplication.class, args);
    }

}
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-02-16h13m41s909.jpg" alt="" width="840">
</p>

Ao executar a aplicação, o console do Spring Boot exibe logs importantes. Notavelmente, o Spring Security gera automaticamente uma senha de segurança temporária para o usuário padrão, que deve ser utilizada para o primeiro acesso. O log também indica que o servidor Tomcat foi iniciado na porta 8080.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-02-16h13m44s629.jpg" alt="" width="840">
</p>

Ao tentar acessar `localhost:8080`, o Spring Security redireciona automaticamente o usuário para uma página de login padrão (`/login`). Este comportamento confirma que a segurança foi habilitada com sucesso e que qualquer recurso da aplicação agora exige autenticação prévia.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-02-16h13m53s581.jpg" alt="" width="840">
</p>

O detalhe do log de inicialização destaca a senha gerada dinamicamente pelo framework. Sem uma configuração personalizada, o Spring Boot utiliza o componente `UserDetailsServiceAutoConfiguration` para prover esta credencial inicial.

```text
Using generated security password: 49b81238-c855-47dc-9d53-1f5711efd41a
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-02-16h13m55s150.jpg" alt="" width="840">
</p>

Após realizar o login com sucesso utilizando as credenciais padrão, o navegador exibe uma "Whitelabel Error Page" com o status 404 (Not Found). Isso ocorre porque, embora a autenticação tenha funcionado, a aplicação ainda não possui rotas ou controladores definidos para a URL acessada.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-02-16h14m06s992.jpg" alt="" width="840">
</p>

Em resumo, o comportamento padrão do Spring Security consiste em criar um usuário fixo chamado `user` e gerar uma senha aleatória no console a cada vez que a aplicação é reiniciada. Esta abordagem é útil para testes iniciais, mas demonstra a necessidade de evoluir para configurações de usuários em memória ou bancos de dados em etapas posteriores.


### 🟩 Vídeo 03 - Autenticação simples

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.07-curso.04-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 04 - Configure Adapter

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.07-curso.04-video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 05 - Autenticação com Banco de dados

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.07-curso.04-video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 06 - JWT - JSON Web Token - Parte 1

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.07-curso.04-video_06.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 07 - JWT - JSON Web Token - Parte 2

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.07-curso.04-video_07.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 08 - JWT - JSON Web Token - Parte 3

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.07-curso.04-video_08.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 09 - JWT - JSON Web Token - Parte 4

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.07-curso.04-video_09.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

## Parte 3 - Conclusão e para saber mais

### 🟩 Vídeo 10 - Conclusão e para saber mais

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.07-curso.04-video_10.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

##  Materiais de Apoio

# Certificado: 

- Link na plataforma: 
- Certificado em pdf: