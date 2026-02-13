## Instrutor

- Venilton Falvo Jr (Doutor em Engenharia de Software, Education Tech Lead na DIO)
- Contato Linkedin: / [falvojr](https://www.linkedin.com/in/falvojr/)

## Parte 1 - Programação Orientada a Objetos

### 🟩 Vídeo 01 - Apresentação e conceitos sobre Domínio/Problema

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.14-video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/project/criando-um-banco-digital-com-java-e-orientacao-objetos/learning/133f3c7e-4474-4582-8e78-987f038ab283?back=/track/tqi-fullstack-developer&tab=undefined&moduleId=undefined

Este guia resume a Expert Talk sobre Programação Orientada a Objetos (POO), focando em como desmistificar conceitos complexos através de uma abordagem prática e baseada em domínios do mundo real. O objetivo principal é capacitar desenvolvedores a criarem sistemas modulares, sustentáveis e de fácil compreensão.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-13-15h11m11s885.jpg" alt="" width="840">
</p>

Nesta introdução, o instrutor Venilton FalvoJr, Tech Lead na DIO, apresenta a proposta da Expert Talk: explorar os **pilares da Orientação a Objetos (OO) utilizando a linguagem Java**. O foco inicial é desmistificar o tema para iniciantes, mostrando que, através de uma abordagem prática e um domínio de problema simples, é possível compreender conceitos fundamentais que garantem a organização, modularidade e manutenibilidade de projetos de software a longo prazo.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-13-15h11m16s212.jpg" alt="" width="840">
</p>

Para exercitar a abstração orientada a objetos, é definido o **Domínio do Problema** baseado em um cenário bancário. A escolha desse domínio deve-se à familiaridade geral com o tema, o que facilita a tradução de regras de negócio para o código. O desafio consiste em interpretar o seguinte cenário:

* **Entidades**: O banco oferece dois tipos de contas: **Corrente** e **Poupança**.
* **Funcionalidades**: As contas devem permitir operações de **depósito**, **saque** e **transferência** (restrita a contas da própria instituição).

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-13-15h11m18s543.jpg" alt="" width="840">
</p>

Para acompanhar o desenvolvimento da solução e progredir de forma fluida, são estabelecidos os seguintes requisitos:

* **Conhecimento Técnico**: Noções básicas de **Java**, **Orientação a Objetos** e uso de ferramentas de versionamento como **Git e GitHub**.
* **Postura**: Engajamento e vontade de aprender para transformar o cenário hipotético em uma solução de software real.      


### 🟩 Vídeo 02 - Pilares da orientação a objetos - Abstração

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.14-video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/criando-um-banco-digital-com-java-e-orientacao-objetos/learning/722fe69c-dd71-4441-ac2a-e01e888f342d

Este resumo explora o conceito de Abstração, um dos pilares fundamentais da POO, demonstrando como transformar problemas do mundo real em estruturas de código eficientes e concisas.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-13-16h32m56s482.jpg" alt="" width="840">
</p>

O instrutor inicia a aula introduzindo os pilares da Programação Orientada a Objetos (POO), destacando que o percurso incluirá uma introdução teórica seguida pela exploração prática desses conceitos. O foco inicial da trilha é preparar o ambiente para a resolução de um desafio prático de desenvolvimento.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-13-16h32m58s407.jpg" alt="" width="840">
</p>

A **Abstração** é apresentada como um dos pilares fundamentais da POO. Ela consiste na capacidade de focar exclusivamente nos aspectos essenciais de um domínio de negócio, descartando características acidentais ou irrelevantes para o sistema. O objetivo é reduzir a complexidade, garantindo que o software seja conciso e implemente apenas o necessário para sua efetividade.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-13-16h33m01s686.jpg" alt="" width="840">
</p>

Para aplicar a abstração na prática, o instrutor inicia a criação de um novo projeto Java no Eclipse IDE. O projeto é nomeado como `desafio-dio-banco`, servindo de base para modelar o domínio bancário proposto no exercício.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-13-16h33m07s439.jpg" alt="" width="840">
</p>

Neste contexto de modelagem, os objetos do sistema são definidos como abstrações de entidades reais do domínio. A tarefa do desenvolvedor é interpretar o problema e identificar quais entidades (como bancos ou contas) possuem relevância para serem transformadas em classes dentro do código.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-13-16h33m14s381.jpg" alt="" width="840">
</p>

Início da implementação das classes no Eclipse. A primeira entidade criada é a classe `Banco`, que representa um dos elementos centrais do domínio de aplicação.

```java
public class Banco {

}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-13-16h33m16s350.jpg" alt="" width="840">
</p>

O processo de criação de novas classes continua, focando em especializar os tipos de contas identificados na abstração do domínio bancário. O assistente de criação do Eclipse é utilizado para gerar a estrutura básica de cada entidade.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-13-16h33m17s694.jpg" alt="" width="840">
</p>

As classes `ContaCorrente` e `ContaPoupanca` são adicionadas ao projeto. A organização inicial foca em separar as responsabilidades de cada tipo de conta que o banco oferecerá aos seus clientes.

```java
public class ContaCorrente {

}

```

```java
public class ContaPoupanca {

}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-13-16h33m20s797.jpg" alt="" width="840">
</p>

A abstração segue para a identificação da entidade `Cliente`. O instrutor reforça que "entidade" é o termo utilizado para classes que representam conceitos vitais dentro do domínio de aplicação, como o proprietário das contas.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-13-16h33m31s969.jpg" alt="" width="840">
</p>

Criação da classe `Cliente` no ambiente de desenvolvimento. Com isso, o projeto já possui o conjunto mínimo de classes (Banco, Cliente, ContaCorrente e ContaPoupanca) para começar a definir atributos e comportamentos.

```java
public class Cliente {

}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-13-16h33m44s718.jpg" alt="" width="840">
</p>

Nesta etapa, a classe `ContaPoupanca` é populada com atributos essenciais (agência, número e saldo) e métodos que representam as operações básicas exigidas pelo domínio: sacar, depositar e transferir. O código ainda não se preocupa com encapsulamento ou repetição, focando apenas na representação funcional da abstração.

```java
public class ContaPoupanca {

	public int agencia;
	public int numero;
	public double saldo;

	public void sacar() {
		
	}

	public void depositar() {
		
	}

	public void transferir() {
		
	}
}

```      


### 🟩 Vídeo 03 - Pilares da orientação a objetos - Encapsulamento

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.14-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/criando-um-banco-digital-com-java-e-orientacao-objetos/learning/b6aaafb2-9b38-440f-a14a-a99779980623

### 🟩 Vídeo 04 - Pilares da orientação a objetos - Herança

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.14-video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 05 - Pilares da orientação a objetos - Polimorfismo

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.14-video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 06 - Desenvolvendo saque, depósito, transferência e extrato

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.14-video_06.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 07 - Proposta de desafio de projeto

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.14-video_07.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 08 - Subindo o código no Github e comentários finais

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.14-video_08.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 09 - Entendendo o Desafio

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.14-video_09.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:


##  Materiais de Apoio

# Certificado: 

- Link na plataforma: 
- Certificado em pdf: