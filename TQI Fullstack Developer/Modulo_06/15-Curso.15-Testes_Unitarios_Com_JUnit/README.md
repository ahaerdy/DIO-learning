## Instrutor

- Willyan Guimarães Caetano (Senior Software Engineer | Tech Lead | Java | Backend)
- Contato Linkedin: / [willyancaetanodev](https://www.linkedin.com/in/willyancaetanodev/)

## Parte 1 - Apresentação do curso

### 🟩 Vídeo 01 - Apresentação do curso

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.15-video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/testes-unitarios-com-junit/learning/66d6b610-ab3e-4475-b71b-675462c1ab49?autoplay=1

Este documento resume a introdução ao curso de Testes Unitários com JUnit, apresentado por William, um desenvolvedor Java experiente. O curso visa não apenas ensinar a ferramenta, mas também a fundamentação técnica e as motivações por trás da escrita de testes de qualidade.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-17-14h01m24s992.jpg" alt="" width="840">
</p>

Nesta introdução, o instrutor Willyan Guimarães Caetano apresenta o curso focado em **Testes Unitários com JUnit**. O objetivo central desta jornada é capacitar o desenvolvedor a utilizar esta ferramenta essencial do ecossistema Java para garantir a qualidade e o funcionamento isolado de componentes de software.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-17-14h01m26s629.jpg" alt="" width="840">
</p>

O instrutor compartilha seu perfil profissional e pessoal para estabelecer o contexto de sua experiência:

* **Experiência Profissional**: Atua há 10 anos com a tecnologia Java, possuindo especialidade e paixão pelo desenvolvimento **Backend**, além de interesse em arquitetura e design de código.
* **Vida Pessoal**: Seus passatempos incluem jogos, fotografia e viagens.
* **Contato**: Disponibiliza seu perfil no LinkedIn para conexões e troca de conhecimentos técnicos.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-17-14h01m31s391.jpg" alt="" width="840">
</p>

Os objetivos principais do curso estão estruturados em três pilares fundamentais:

1. **Motivação**: Compreender a fundamentação técnica e as circunstâncias que levam à necessidade de se escrever testes unitários.
2. **Configuração**: Conhecer o JUnit e aprender como configurar a ferramenta adequadamente em projetos Java.
3. **Aprofundamento**: Explorar os recursos avançados que a biblioteca provê para a construção de testes efetivos.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-17-14h01m32s818.jpg" alt="" width="840">
</p>

O percurso do aprendizado está dividido em etapas incrementais:

* **Aula 1**: Focada na teoria e nos motivos para a adoção de testes unitários.
* **Aula 2**: Parte prática inicial com a criação de um "Hello World" utilizando JUnit.
* **Aula 3**: Detalhamento técnico dos recursos, incluindo as principais anotações e funcionalidades da ferramenta para aprimorar os testes.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-17-14h01m34s590.jpg" alt="" width="840">
</p>

A fase final do curso aborda a integração e a evolução da prática de testes:

* **Aula 4**: Explora como as principais IDEs do mercado oferecem suporte e recursos para facilitar a execução e gerenciamento dos testes.
* **Aula 5**: Apresentação de boas práticas e premissas para a escrita de testes de alta qualidade.
* **Aula 6**: Conclusão com insights para o desenvolvimento contínuo da habilidade de testagem.      


## Parte 2 - Por que escrever testes unitários?

### 🟩 Vídeo 02 - Por que escrever testes unitários?

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.15-video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/testes-unitarios-com-junit/learning/f1526efd-2e98-4c3d-931e-3e9e889867e8?autoplay=1

Este guia resume a primeira aula do curso de testes unitários, abordando desde a definição fundamental até a importância estratégica de implementar testes no ciclo de vida de desenvolvimento de software.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-17-14h21m16s727.jpg" alt="" width="840">
</p>

Esta é a aula inaugural do curso voltado para o aprendizado de testes unitários utilizando o framework JUnit. O foco inicial desta sessão é explorar as motivações e a importância de se escrever esses testes dentro do fluxo de desenvolvimento.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-17-14h21m20s931.jpg" alt="" width="840">
</p>

Os objetivos centrais desta etapa consistem em fornecer uma compreensão clara sobre a definição de testes unitários e demonstrar a relevância fundamental dessa prática para a qualidade e estabilidade do software.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-17-14h21m23s649.jpg" alt="" width="840">
</p>

Testes unitários, também conhecidos como testes de unidade, focam em validar a menor parte funcional de uma aplicação. Isso implica testar isoladamente componentes como funções, métodos ou classes. Tradicionalmente, esses testes são elaborados simultaneamente ao desenvolvimento do código-fonte.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-17-14h21m28s739.jpg" alt="" width="840">
</p>

Abaixo, apresenta-se um exemplo prático de uma regra de negócio simples e seu respectivo teste unitário utilizando JUnit:

```java
class Pessoa {
    //construtor, atributos e outros métodos
    public boolean ehMaiorDeIdade() {
        return idade > 18;
    }
}

class PessoaTeste {
    @Test
    void validaVerificacaoDeMaioridade() {
        Pessoa joaozinho = new Pessoa ("João", LocalDate.of (2004, 1, 1));
        Assertions.assertTrue(joaozinho.ehMaiorDeIdade());
    }
}

```

Neste exemplo, a classe de teste verifica se a lógica do método `ehMaiorDeIdade` retorna o valor esperado ao instanciar um objeto com uma data de nascimento específica.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-17-14h21m34s821.jpg" alt="" width="840">
</p>

A importância dos testes unitários se manifesta em diversas situações críticas do cotidiano de um desenvolvedor:

* **Compreensão do código:** Facilitam o entendimento da lógica interna por novos membros da equipe.
* **Correção de bugs:** Permitem ajustar falhas com a segurança de que o comportamento esperado será mantido.
* **Refatoração segura:** Oferecem suporte para melhorar a estrutura do código sem introduzir novos defeitos.
* **Entrega de funcionalidades:** Garantem que novas features cheguem ao usuário final com maior confiabilidade.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-17-14h21m42s817.jpg" alt="" width="840">
</p>

Além dos benefícios diretos no código, os testes unitários fundamentam conceitos avançados de engenharia de software:

* **Pirâmide de Testes:** Representam a base sólida e volumosa de uma estratégia de testes eficiente.
* **Métricas de Qualidade:** Servem como indicadores de confiabilidade e cobertura do sistema.
* **Automação e Agilidade:** São essenciais para processos de execução automatizada e para a manutenção de uma cultura ágil de desenvolvimento.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-17-14h21m47s809.jpg" alt="" width="840">
</p>

Para aprofundamento nos temas de cultura de qualidade e práticas de DevSecOps, são sugeridas as seguintes referências externas:

* Artigos sobre a importância dos testes unitários no iMasters.
* Guias práticos sobre definição e implementação de testes no Medium.
* Estudos sobre a promoção de qualidade no desenvolvimento de software ágil.      

#### Referências

- https://imasters.com.br/devsecops/testes-unitarios-qual-a-importancia  
- https://dayvsonlima.medium.com/entenda-de-uma-vez-por-todas-o-que-s%C3%A3o-testes-unit%C3%A1rios-para-que-servem-e-como-faz%C3%AA-los-2a6f645bab3  
- https://medium.com/cesar-update/aprendendo-a-promover-uma-cultura-de-qualidade-no-desenvolvimento-de-software-%C3%A1gil-f5a3444dd


## Parte 3 - Hello world, JUnit!

### 🟩 Vídeo 03 - Hello World no JUNIT

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.15-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/testes-unitarios-com-junit/learning/311d5f09-9441-4802-8bca-c48e767a74b1?autoplay=1



## Parte 4 - Aprofundando nos recursos

### 🟩 Vídeo 04 - O básico para testar

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.15-video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 05 - Mais algumas asserções

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.15-video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 06 - After e Before

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.15-video_06.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 07 - Assumptions e Testes condicionais

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.15-video_07.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 08 - Testando exceptions

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.15-video_08.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 09 - Ordenando testes

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.15-video_09.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

## Parte 5 - Recursos de testes nas IDEs

### 🟩 Vídeo 10 - Visual Studio Code

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.15-video_10.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 11 - Eclipse

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.15-video_11.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 12 - IntelliJ IDEA

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.15-video_12.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

## Parte 6 - Boas práticas

### 🟩 Vídeo 13 - Boas práticas

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.15-video_13.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

## Parte 7 - Conclusão do curso

### 🟩 Vídeo 14 - Conclusão do curso

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.15-video_14.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

##  Materiais de Apoio

# Certificado: 

- Link na plataforma: 
- Certificado em pdf: