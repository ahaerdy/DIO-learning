## Instrutor:

- Renan Johannsen de Paula (Software Engineer Lead)
- Contato Linkedin: / [renanjpaula](https://www.linkedin.com/in/renanjpaula/)

# Parte 1 - Introdução ao curso de HTML

## 🟩 Vídeo 01 - Estrutura básica

![[bootcamp_tqi_fullstack-modulo_02-Video_01.webm|vid-60]]

Link do vídeo:  https://web.dio.me/track/tqi-fullstack-developer/course/introducao-criacao-de-websites-com-html5-e-css3/learning/462f831d-5fdf-485e-bf07-1d391eb94ac8?autoplay=1

Esta fonte consiste na **Aula introdutória** ministrada por um engenheiro de software focada no aprendizado de **HTML5 e CSS3**. O instrutor apresenta o contexto histórico do HTML, destacando sua criação por **Tim Berners-Lee** e a evolução da linguagem até sua estrutura contemporânea. A explicação detalha a anatomia de um **elemento HTML**, abordando desde o uso de tags e atributos até a organização fundamental de um documento. O conteúdo ensina a configurar o **esqueleto básico de uma página**, incluindo as seções de metadados e o corpo do site. Por fim, o autor demonstra a aplicação prática desses conceitos iniciais através da **criação de um site pessoal** simples em um editor de código.

### Anotações

#### Introdução ao HTML5 e CSS3

A aula se inicia com a apresentação do curso de **Introdução ao HTML5 e CSS3**, focado inicialmente no HTML5. O instrutor, Lucas Vilaboim, Front-End Engineer no LuizaLabs, estabelece o escopo do módulo.

  <p align="center">
    <img src="000-Midia_e_Anexos/vlcsnap-2025-12-24-09h25m04s800.jpg" alt="" width="840">
   </p>

#### Objetivos e Requisitos do Curso

Os objetivos centrais da aula são:
1. Conhecer a história e a **estrutura básica** do HTML.
2. Entender o conceito de **semântica** e sua importância.
3. Conhecer os principais elementos do HTML.

  <p align="center">
    <img src="000-Midia_e_Anexos/vlcsnap-2025-12-24-09h25m26s819.jpg" alt="" width="840">
   </p>

Para acompanhar o curso, os **Requisitos Básicos** são um editor de texto (o instrutor utiliza o **VS Code**) e um navegador de internet (o instrutor utiliza o **Chrome**).

  <p align="center">
    <img src="000-Midia_e_Anexos/vlcsnap-2025-12-24-09h25m34s375.jpg" alt="" width="840">
   </p>

#### História e Evolução do HTML

O HTML foi criado em 1991 por **Tim Berners-Lee** no CERN, com o objetivo de compartilhar documentos. A linguagem evoluiu ao longo dos anos, com a versão mais recente, o **HTML5**, sendo lançada em 2014.

  <p align="center">
    <img src="000-Midia_e_Anexos/vlcsnap-2025-12-24-09h25m42s320.jpg" alt="" width="840">
   </p>

#### O Elemento HTML: A Base da Estrutura

O **elemento HTML** é a unidade fundamental de um documento. Ele é composto por uma **tag de abertura**, que pode conter **atributos** (modificando funcionalidade ou aparência), o **conteúdo** do elemento e, por fim, a **tag de fechamento**.

O exemplo abaixo ilustra a estrutura de um elemento `<h1>` (cabeçalho de nível 1), que possui um atributo `class`.

  <p align="center">
    <img src="000-Midia_e_Anexos/vlcsnap-2025-12-24-09h26m12s770.jpg" alt="" width="840">
   </p>

```html
<h1 class="titulo">Título</h1>
```

#### Estrutura Básica de um Documento HTML

A estrutura básica de um documento HTML é concisa e essencial para o navegador interpretar o conteúdo corretamente.

  <p align="center">
    <img src="000-Midia_e_Anexos/vlcsnap-2025-12-24-09h26m56s270.jpg" alt="" width="840">
   </p>

- **`<!DOCTYPE html>`**: Não é um elemento HTML, mas uma declaração que informa ao navegador a versão do HTML que está sendo utilizada (HTML5).
- **`<html>`**: O elemento raiz que engloba todo o conteúdo do documento.
- **`<head>`**: Contém **meta informações** sobre o documento, necessárias para o navegador e buscadores (como o `charset` e o `<title>`).
- **`<body>`**: Contém o **conteúdo visível** da página para o usuário.

#### Demonstração Prática: Criando a Estrutura

O instrutor demonstra a criação da estrutura básica em um arquivo `index.html` no VS Code, incluindo a definição do `charset` para **UTF-8** e a tag `<title>` para o título da aba do navegador. O objetivo é criar um site pessoal.

  <p align="center">
    <img src="000-Midia_e_Anexos/vlcsnap-2025-12-24-09h29m38s577.jpg" alt="" width="840">
   </p>

```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lucas Vilaboim</title>
</head>
<body>
    Lucas Vilaboim
</body>
</html>
```

O código acima representa a estrutura mínima, onde o nome do instrutor é inserido diretamente no `<body>` para visualização inicial.

#### Resultado no Navegador

Ao salvar o arquivo e abri-lo no navegador, o conteúdo do `<body>` (`Lucas Vilaboim`) é exibido na tela, e o conteúdo da tag `<title>` (`Lucas Vilaboim`) é exibido na aba do navegador.

  <p align="center">
    <img src="000-Midia_e_Anexos/vlcsnap-2025-12-24-09h29m51s306.jpg" alt="" width="840">
   </p>

A aula conclui reforçando que essa é a estrutura básica a partir da qual o site será enriquecido nas próximas aulas, com foco no conceito de **semântica**.


## 🟩 02 - Material de apoio


Link: https://web.dio.me/track/tqi-fullstack-developer/course/introducao-criacao-de-websites-com-html5-e-css3/learning/f8acdea7-2ee8-49a8-8b96-7200e309fc3a?autoplay=1


### Anotações

#### HTML5

##### Definição e estrutura básica

Em 1991 Tim Berners-Lee criou essa linguagem de marcação para melhorar a comunicação entre ele e seus colegas de trabalho no CERN. Desde então já surgiram 5 versões e o HTML se tornou a base da web.

Com o HTML definimos o significado e a estrutura do conteúdo da web. Além de texto, nossas páginas precisam de imagens, vídeos e vários outros formatos — para isso temos os elementos HTML.

Um elemento HTML é formado pela tag de abertura e seus atributos, o conteúdo e uma tag de fechamento. Mais à frente veremos que existem elementos que não têm tag de fechamento.

Com esses elementos podemos agrupar tipos de conteúdo, alterar tamanho e forma de fontes e adicionar diferentes mídias à nossa página na web.

A primeira linha do documento deve ser o `<!DOCTYPE html>`. Apesar de parecer um elemento HTML, ela apenas diz ao navegador que ele está lidando com um arquivo do tipo HTML5.

##### Estrutura básica

```html
<html>
  <!-- raiz do documento, define o idioma com atributo lang -->
  <head>
    <!-- metadados, charset UTF-8, scripts, estilos e título -->
  </head>
  <body>
    <!-- conteúdo visível: textos, imagens, vídeos -->
  </body>
</html>
```

---

#### Prática: Estrutura inicial

Vamos construir um site pessoal com a estrutura básica:

1. Criar o arquivo `index.html`.
2. Adicionar `<!DOCTYPE html>`, `<html>`, `<head>` e `<body>`.
3. Dentro do `<head>`, adicionar:
   - `<meta charset="UTF-8">`
   - `<title>` com seu nome.
4. Dentro do `<body>`, escrever seu nome para visualizar no navegador.

---

#### Semântica

Durante muitos anos o elemento padrão era a `<div>`, gerando a "sopa de divs". Em 2014, com o HTML5, vieram melhorias de performance, acessibilidade e semântica.

A semântica permite descrever melhor o conteúdo. Agora um bloco de texto pode ser um `<article>`, por exemplo.

##### Elementos semânticos

- `<section>`: seção genérica de conteúdo.
- `<header>`: cabeçalho da página ou seção.
- `<article>`: conteúdo independente e relevante (ex.: post de blog).
- `<aside>`: conteúdo relacionado ao principal (ex.: biografia, publicidade).
- `<footer>`: rodapé do conteúdo ou da página.
- `<h1>` a `<h6>`: títulos hierárquicos (use apenas um `<h1>` por página).

---

#### Prática: Estrutura semântica

1. Criar `<header>` com `<h1>` para o nome.
2. Criar `<section>` com `<header>` e `<h2>` para lista de postagens.
3. Criar `<article>` com `<header>` e `<h3>` para cada postagem.
4. Criar `<footer>` com informações de contato.

---

#### Textos e Links

- `<p>`: representa parágrafos, pode conter diversos tipos de conteúdo.
- `<a>`: representa hiperlinks.

##### Atributos importantes

- `href`: destino do link (página, e-mail, telefone).
- `target="_blank"`: abre o link em nova aba.

---

#### Prática: Texto e hiperlinks

1. Adicionar `<p>` com texto fictício (ex.: do site lipsum.com).
2. Adicionar `<a>` com `href` para perfil no LinkedIn e `target="_blank"`.
3. Adicionar link para e-mail:

```html
<a href="mailto:lucas@vilaboim.com" target="_blank">lucas@vilaboim.com</a>
```

---

#### Imagens

- `<img>`: representa imagens (sem tag de fechamento).
- `src`: caminho da imagem (obrigatório).
- `alt`: descrição da imagem (recomendado para acessibilidade).

---

#### Prática: Inserir imagens

1. Colocar imagens na pasta do projeto.
2. No `<header>` principal, antes do `<h1>`:

```html
<img src="/lucas-vilaboim.jpg" alt="Ilustração do rosto de Lucas Vilaboim" width="100" height="100">
```

3. No `<header>` do `<article>`, após o `<h3>`:

```html
<img src="/html-code.jpg" alt="Editor de texto mostrando códigos HTML" width="960" height="322">
```

---

#### Listas

- `<ul>`: lista não ordenada (pontos, círculos).
- `<ol>`: lista ordenada (números, letras).
- `<li>`: item da lista, pode conter vários tipos de conteúdo.

---

#### Prática: Lista de contatos

1. Criar `<ul>` dentro do `<footer>`.
2. Adicionar `<li>` com `<a>` para redes sociais:

```html
<ul>
  <li><a href="https://linkedin.com/in/seuperfil" target="_blank">LinkedIn</a></li>
</ul>
```


# Parte 2 - Entendendo o que é semântica

## 🟩 Vídeo 02 - Semântica - Parte 1


Link do vídeo:  https://web.dio.me/track/tqi-fullstack-developer/course/introducao-criacao-de-websites-com-html5-e-css3/learning/3922c73b-3452-467d-80f3-b11eb9cb911e?autoplay=1




## 🟩 Vídeo 02 - Semântica - Parte 2


Link do vídeo: 


## 🟩 Vídeo 03 - Entendendo o que é semântica


Link do vídeo: 


# Parte 3 - Como usar textos e links em HTML

## 🟩 Vídeo 01 - Tags para textos


Link do vídeo:  


## 🟩 Vídeo 02 - Tags para links


Link do vídeo: 


## 🟩 Vídeo 03 - Exercício prático


Link do vídeo: 


## 🟩 Vídeo 04 - Como usar textos e links em HTML


Link do vídeo: 


# Parte 4 - Como inserir imagens em seu site

## 🟩 Vídeo 01 - Tag img


Link do vídeo:  


## 🟩 Vídeo 02 - Exercício prático


Link do vídeo:  


## 🟩 Vídeo 03 - Como inserir imagens em seu site


Link do vídeo:  


# Parte 5 - Como organizar listas com HTML

## 🟩 Vídeo 01 - Tags li, ul e ol


Link do vídeo:  

## 🟩 Vídeo 02 - Exercício prático


Link do vídeo:  

## 🟩 Vídeo 03 - Como organizar listas com HTML


Link do vídeo:  

## 🟩 Vídeo 04 - Como organizar listas com HTML


Link do vídeo:  


# Parte 6 - Introdução e conceitos básicos do CSS3

## 🟩 Vídeo 01 - Introdução ao CSS3


Link do vídeo:  

## 🟩 Vídeo 02 - Conceitos básicos


Link do vídeo:  

## 🟩 Vídeo 03 - Material de apoio


Link do vídeo:  

## 🟩 Vídeo 04 - Introdução e conceitos básicos do CSS3


Link do vídeo:  


# Parte 7 - Estilizando elementos, textos e listas

## 🟩 Vídeo 01 - Estilizando elementos


Link do vídeo:  

## 🟩 Vídeo 02 - Estilizando textos


Link do vídeo:  

## 🟩 Vídeo 03 - Estilizando listas


Link do vídeo:  

## 🟩 Vídeo 04 - Estilizando elementos, textos e listas


Link do vídeo:  


# Parte 8 - Dimensão e alinhamento

## 🟩 Vídeo 01 - Propriedades de dimensões e alinhamento


Link do vídeo:  

## 🟩 Vídeo 02 - Dimensão e alinhamento


Link do vídeo:  

## 🟩 Vídeo 03 - Dimensão e alinhamento


Link do vídeo:  


# Certificado: 

- Link na plataforma: 
- Certificado em pdf: 
