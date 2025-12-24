## Instrutor

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

![[bootcamp_tqi_fullstack-modulo_02-Video_02.webm|vid-60]]

Link do vídeo:  https://web.dio.me/track/tqi-fullstack-developer/course/introducao-criacao-de-websites-com-html5-e-css3/learning/3922c73b-3452-467d-80f3-b11eb9cb911e?autoplay=1

Esta fonte discute a transição fundamental do uso genérico de **divs** para a implementação de **HTML semântico** a partir da versão 5 da linguagem. O autor explica que elementos modernos como **header**, **section** e **footer** foram criados para fornecer uma estrutura lógica e clara que define o papel de cada parte do conteúdo. O texto também detalha a importância das **tags de título**, que variam de **H1 a H6**, como ferramentas essenciais para estabelecer uma **hierarquia de informações** em uma página web. É ressaltada a diretriz técnica de manter apenas um **H1** por documento para garantir a organização correta. Por fim, o conteúdo propõe uma **atividade prática** para aplicar esses novos conceitos na evolução de um projeto de codificação anterior.


## 🟩 Vídeo 03- Semântica - Parte 2

![[bootcamp_tqi_fullstack-modulo_02-Video_03.webm|vid-60]]

Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/introducao-criacao-de-websites-com-html5-e-css3/learning/cd186102-3c5e-4b94-a631-0071076d40a6?autoplay=1

Esta fonte consiste em uma transcrição de áudio que ensina como **estruturar uma página web** utilizando a semântica do **HTML5**. O conteúdo demonstra a organização hierárquica de um site através da implementação de **cabeçalhos, seções e artigos** específicos. O instrutor explica a importância de utilizar **diferentes níveis de títulos**, do H1 ao H3, para definir a relevância de cada informação. Além disso, o material detalha a inclusão de um **rodapé** destinado a futuras informações de contato. Por fim, o tutorial ressalta como o **navegador interpreta visualmente** essas tags antes de qualquer estilização personalizada.

### Anotações

#### Evolução da Estrutura do Site com Elementos Semânticos

Nesta aula, o foco é evoluir a estrutura de um site HTML adicionando elementos semânticos que organizam o conteúdo de forma hierárquica e significativa. O instrutor Lucas Vilaboim demonstra como construir uma página web utilizando as tags apropriadas do HTML5.

#### Adicionando o Cabeçalho Principal da Página

O primeiro passo é adicionar um elemento `<header>` que representa o cabeçalho principal da página. Dentro deste header, é inserido um `<h1>` com o nome do autor, que funciona como o título principal do site. É importante destacar que deve existir apenas um `<h1>` por página, representando o título mais importante do conteúdo.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2025-12-24-10h12m18s368.jpg" alt="" width="840">
</p>

```html
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Lucas Vilaboim</title>
  </head>
  <body>
    <header>
      <h1>Lucas Vilaboim</h1>
    </header>
    <section>
      <header>
        <h2>Posts</h2>
      </header>
      <article>
        <header>
          <h3>Post #1</h3>
        </header>
      </article>
    </section>
    <footer></footer>
  </body>
</html>
```

A estrutura mostra claramente a hierarquia dos elementos: o `<header>` principal contém o `<h1>`, seguido por uma `<section>` que agrupa o conteúdo de posts, e um `<footer>` ao final.

#### Criando a Seção de Conteúdo

Após o cabeçalho principal, é criada uma seção de conteúdo usando a tag `<section>`, que é um elemento genérico para agrupar conteúdo relacionado. Dentro desta section, adiciona-se outro `<header>` contendo um `<h2>` com o texto "Posts", indicando que esta seção destina-se à listagem de postagens.

Esta abordagem demonstra que é possível ter múltiplos elementos `<header>` em uma página, cada um contextualizando diferentes seções do conteúdo. A hierarquia de títulos (`h1`, `h2`, `h3`) mantém a estrutura semântica adequada.

#### Adicionando Articles e Estrutura de Posts

Dentro da `<section>`, é inserido um elemento `<article>`, que representa um conteúdo independente e autocontido. Cada article possui seu próprio `<header>` contendo um `<h3>` com o título do post (por exemplo, "Post #1").

A utilização de `<article>` é apropriada para posts, artigos, notícias ou qualquer conteúdo que faça sentido de forma independente. A estrutura hierárquica dos headings (`h1` → `h2` → `h3`) mantém a semântica e acessibilidade do documento.

#### Adicionando o Rodapé

Por último, fora da `<section>`, é adicionado um elemento `<footer>` que representará as informações de contato e outros dados complementares da página. O instrutor menciona que este conteúdo será desenvolvido mais adiante.

O `<footer>` é um elemento semântico que geralmente contém informações sobre o autor, links de navegação secundários, informações de copyright ou dados de contato.

#### Recapitulação da Estrutura HTML

O instrutor faz uma recapitulação completa da estrutura criada:

- **Header principal**: contém o `<h1>` único da página com o título do conteúdo
- **Section**: representa a lista de posts com seu próprio header e `<h2>`
- **Article**: dentro da section, contém outro header com `<h3>` para o nome do post
- **Footer**: elemento para representar a lista de contatos

Esta organização segue as boas práticas de HTML semântico, facilitando a compreensão do conteúdo tanto para desenvolvedores quanto para tecnologias assistivas.

#### Visualização no Navegador


<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2025-12-24-10h19m05s736.jpg" alt="" width="840">
</p>

O instrutor mostra como a página é renderizada no navegador, destacando que o próprio navegador aplica estilos diferentes para cada tamanho de título (`h1`, `h2`, `h3`). Estes estilos padrão podem ser personalizados posteriormente com CSS.

A hierarquia visual criada automaticamente pelo navegador ajuda a entender a estrutura do documento, com o `<h1>` em tamanho maior, seguido pelo `<h2>` e `<h3>` em tamanhos progressivamente menores.

#### Próximos Passos

O instrutor conclui mencionando que na próxima aula serão abordados os tópicos de **textos e links**, expandindo ainda mais as possibilidades de estruturação e interatividade do conteúdo HTML.

# Parte 3 - Como usar textos e links em HTML

## 🟩 Vídeo 04 - Tags para textos

![[bootcamp_tqi_fullstack-modulo_02-Video_04.webm|vid-60]]

Link do vídeo:  https://web.dio.me/track/tqi-fullstack-developer/course/introducao-criacao-de-websites-com-html5-e-css3/learning/e48e8a0f-5940-4cdd-9923-979c31c241f7?autoplay=1

O áudio apresenta os fundamentos da **estruturação de documentos na web**, destacando que o compartilhamento de informações textuais permanece como o pilar central da internet. O conteúdo detalha como os **títulos de hierarquia H1 a H6** organizam visualmente as seções de uma página para facilitar a navegação. Para o desenvolvimento de conteúdos mais extensos, a fonte descreve a importância fundamental da **tag de parágrafo**, conhecida como elemento P. Além de organizar blocos de texto, essa funcionalidade é versátil por permitir a inclusão de **mídias diversas**, como imagens, códigos e vídeos. Assim, a explicação reforça que a evolução tecnológica manteve a **essência do HTML** na organização lógica de documentos digitais.


## 🟩 Vídeo 05 - Tags para links

![[bootcamp_tqi_fullstack-modulo_02-Video_05.webm|vid-60]]

Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/introducao-criacao-de-websites-com-html5-e-css3/learning/7b6afe7e-d962-4241-bf47-d76ed6935347?autoplay=1

O vídeo explica o funcionamento da **tag HTML de âncora**, essencial para a conectividade e navegação entre diferentes páginas da rede. O autor destaca o atributo **href**, que define o destino do link, podendo direcionar o usuário para **sites externos**, endereços de **e-mail** ou números de **telefone**. Além disso, é abordado o uso do atributo **target** com o valor **_blank**, permitindo que o conteúdo seja acessado em uma **nova aba** do navegador. A fonte também orienta sobre a aplicação prática desses elementos em **postagens de blog** ou exercícios de codificação. No geral, as instruções detalham como criar **hiperlinks funcionais** e dinâmicos para melhorar a experiência do usuário na web.

### Anotações

#### O Elemento Âncora (A) e sua Importância na Web

O elemento `<a>` (âncora) é um componente extremamente necessário e interessante na web, pois é responsável por interligar diversos conteúdos. Este elemento funciona como uma ponte ou portal entre páginas, sites e diferentes tipos de recursos na internet.

#### Principais Atributos do Elemento A

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2025-12-24-10h37m47s569.jpg" alt="" width="840">
</p>

```html
<a>Link</a>

<a href="linkedin.com/in/vilaboim">LinkedIn</a>
<a href="mailto:lucas@vilaboim.com">E-mail</a>

<a target="_blank">Link</a>
```

O elemento `<a>` possui diversos atributos, mas a aula foca nos dois principais: **href** e **target**.

#### O Atributo href (Hyperlink Reference)

O atributo `href` é o hiperlink que indica para onde a âncora está apontando. Ele pode referenciar:

- **Páginas internas**: páginas dentro do próprio site
- **Sites externos**: endereços de outros sites na web
- **E-mails**: usando o prefixo `mailto:` antes do endereço de email
- **Telefones**: usando o prefixo `tel:` antes do número

Exemplos práticos mostrados:
- `href="linkedin.com/in/vilaboim"` - direciona para um perfil do LinkedIn
- `href="mailto:lucas@vilaboim.com"` - abre o cliente de e-mail padrão com o destinatário já preenchido

#### O Atributo target

O atributo `target` serve para indicar como o link será aberto. O valor apresentado na aula é `_blank`, que instrui o navegador a abrir o link em uma **nova aba**, mantendo a página original aberta.

Esta funcionalidade é especialmente útil quando você deseja que o usuário explore um link externo sem perder a navegação atual no seu site.

#### Aplicação Prática

Para o exercício proposto, será adicionado texto a uma postagem com diversos links incorporados, demonstrando na prática como o elemento `<a>` interliga conteúdos e cria a navegação característica da web.

## 🟩 Vídeo 06 - Exercício prático

![[bootcamp_tqi_fullstack-modulo_02-Video_06.webm|vid-60]]

Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/introducao-criacao-de-websites-com-html5-e-css3/learning/b8233675-5187-40d6-b60c-c6a36ddc18a8?autoplay=1

O vídeo fornece um tutorial técnico sobre a estruturação de elementos básicos em uma página **HTML**. O instrutor demonstra inicialmente como inserir **parágrafos de texto** fictício dentro de um artigo para preencher o conteúdo visual. Em seguida, o foco muda para a criação de **hiperlinks**, detalhando como configurar o atributo para que as páginas abram em **novas abas** do navegador. O material também explora funcionalidades específicas de links, como os prefixos necessários para disparar o envio de **e-mails** e a realização de **chamadas telefônicas**. Por fim, o conteúdo antecipa que as próximas lições abordarão a manipulação de imagens para continuar a evolução do projeto web.

### Anotações

#### Adicionando Texto ao Post com o Elemento P

O primeiro passo para trabalhar com textos na página é adicionar conteúdo aos posts. Dentro do elemento `<article>`, logo abaixo do `<header>`, é inserido um elemento `<p>` (parágrafo). Para este exemplo, o instrutor utiliza um texto fictício do site **lipsum.com**, que é um gerador de texto placeholder comumente usado em desenvolvimento web.

Após colar o texto dentro do elemento `<p>`, ele já aparece renderizado no navegador, demonstrando como o HTML processa e exibe automaticamente o conteúdo textual.

#### Criando um Link para Perfil no LinkedIn

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2025-12-24-10h56m18s017.jpg" alt="" width="840">
</p>

```html
<h1>Lucas Vilaboim</h1>
</header>
<section>
  <header>
    <h2>Posts</h2>
  </header>
  <article>
    <header>
      <h3>Post #1</h3>
    </header>
    <p>
      Lorem ipsum dolor sit amet, <a href="https://www.linkedin.com/in/vilaboim/"
      target="_blank">consectetur adipiscing</a> elit. Quisque tristique risus
      diam, ac interdum augue ornare et. Curabitur in nisl quis diam vestibulum
      viverra. Morbi et augue eget ante hendrerit euismod at eget justo. Duis
      fringilla erat at velit semper mattis nec sed erat. Nulla facilisis ultrices
      eros, non pharetra arcu convallis non. Vestibulum quis purus tortor. Nunc
      dignissim hendrerit orci. Nunc efficitur libero vitae ornare aliquet. Duis
      euismod ligula eget tellus sollicitudin, sit amet auctor ex malesuada.
      Aliquam a rutrum metus, at feugiat mi. Pellentesque tempor varius nibh, non
      porta mauris iaculis aliquet. Nulla facilisi.
    </p>
  </article>
</section>
```

Para adicionar um link, o processo envolve selecionar uma parte do texto, removê-la temporariamente, adicionar o elemento `<a>`, e colar o texto dentro dele. O atributo `href` é então adicionado com o endereço do perfil do LinkedIn. 

O código mostra o link apontando para `https://www.linkedin.com/in/vilaboim/` com o atributo `target="_blank"`, que instrui o navegador a abrir o link em uma nova aba. Esta é uma prática recomendada para links externos, pois mantém o site original aberto enquanto o usuário explora o conteúdo externo.

#### Visualizando o Link no Navegador

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2025-12-24-10h56m23s349.jpg" alt="" width="840">
</p>

No navegador, o link aparece com a aparência padrão de hiperlink (sublinhado e em cor diferente). Inicialmente, quando clicado sem o atributo `target="_blank"`, o link abre na mesma aba. No entanto, após adicionar este atributo, o comportamento muda: o link passa a abrir em uma nova aba do navegador, preservando a página original.

A página mostra a estrutura completa renderizada: o cabeçalho "Lucas Vilaboim" com o `<h1>`, a seção "Posts" com `<h2>`, o título do post "Post #1" com `<h3>`, e o parágrafo contendo o link funcional para "consectetur adipiscing".

#### Adicionando Link para E-mail com mailto

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2025-12-24-10h56m54s743.jpg" alt="" width="840">
</p>

```html
<h1>Lucas Vilaboim</h1>
</header>
<section>
  <header>
    <h2>Posts</h2>
  </header>
  <article>
    <header>
      <h3>Post #1</h3>
    </header>
    <p>
      Lorem ipsum dolor sit amet, <a href="https://www.linkedin.com/in/vilaboim/"
      target="_blank">consectetur adipiscing</a> elit. Quisque tristique risus
      diam, ac <a href="mailto:lucas@email.com">interdum augue</a> ornare et.
      Curabitur in nisl quis diam vestibulum viverra. Morbi et augue eget ante
      hendrerit euismod at eget justo. Duis fringilla erat at velit semper mattis
      nec sed erat. Nulla facilisis ultrices eros, non pharetra arcu convallis non.
      Vestibulum quis purus tortor. Nunc dignissim hendrerit orci. Nunc efficitur
      libero vitae ornare aliquet. Duis euismod ligula eget tellus sollicitudin,
      sit amet auctor ex malesuada. Aliquam a rutrum metus, at feugiat mi.
      Pellentesque tempor varius nibh, non porta mauris iaculis aliquet. Nulla
      facilisi.
    </p>
  </article>
</section>
```

O segundo tipo de link demonstrado é o link para e-mail. O processo é similar ao anterior, mas o atributo `href` recebe um prefixo especial: `mailto:`. Este prefixo instrui o navegador a tratar o link de forma diferente de um endereço web comum.

No código, o link é criado com `href="mailto:lucas@email.com"`, envolvendo o texto "interdum augue". Quando este tipo de link é clicado, em vez de navegar para uma página web, o navegador abre o cliente de e-mail padrão do usuário (ou o webmail, caso não haja cliente configurado) com o destinatário já preenchido.

#### Comportamento dos Links de E-mail no Navegador

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2025-12-24-10h56m58s011.jpg" alt="" width="840">
</p>

Visualmente no navegador, o link de e-mail tem a mesma aparência de um link comum, mas seu comportamento é completamente diferente. Ao clicar nele, o sistema operacional interpreta o protocolo `mailto:` e abre o aplicativo de e-mail configurado.

No caso do instrutor, como não há cliente de e-mail instalado localmente, o navegador abre a interface web do Gmail automaticamente. Este comportamento demonstra a versatilidade dos links HTML para diferentes tipos de comunicação além da simples navegação entre páginas.

#### O Prefixo tel: para Telefones

A aula também menciona um prefixo similar ao `mailto:`, que é o `tel:`. Este funciona de maneira análoga, mas para números de telefone. Quando um usuário clica em um link com `tel:` em um dispositivo móvel, o sistema operacional oferece a opção de realizar uma chamada telefônica para o número especificado.

Este recurso é especialmente útil em sites responsivos que serão acessados por smartphones, facilitando a interação do usuário ao permitir que ele inicie uma chamada diretamente ao tocar no número.

#### Próximos Passos

A aula conclui informando que a página continuará evoluindo nas próximas lições, e que o próximo tópico será sobre **imagens**, expandindo ainda mais as possibilidades de criação de conteúdo rico em HTML.


# Parte 4 - Como inserir imagens em seu site

## 🟩 Vídeo 07 - Tag img

![[bootcamp_tqi_fullstack-modulo_02-Video_07.webm|vid-60]]

Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/introducao-criacao-de-websites-com-html5-e-css3/learning/8e1edc74-dde3-42f0-b307-c28822ad8bcb?autoplay=1

O texto descreve o funcionamento básico da **tag de imagem** no desenvolvimento web, destacando que ela é um elemento que **não requer fechamento**. O autor explica que o atributo **src** é indispensável, pois define a origem do arquivo, seja ele **local ou externo**. Além disso, enfatiza-se a importância fundamental do atributo **alt** para promover a **acessibilidade digital**. Essa descrição alternativa garante que usuários de **leitores de tela** compreendam o conteúdo visual e fornece contexto caso o carregamento falhe. Portanto, o foco central é ensinar as **boas práticas** para a implementação correta de elementos visuais em sites.

### Anotações

#### Introdução ao Elemento de Imagem no HTML

A web é composta por diversos tipos de mídia, e as imagens desempenham um papel fundamental na representação visual de conteúdo. Para inserir imagens em uma página, utilizamos a tag `<img>`. Diferente de muitos outros elementos HTML que possuem tags de abertura e fechamento, a tag de imagem é um elemento que não possui tag de fechamento.

#### Atributos Essenciais e Acessibilidade

O elemento de imagem é simples, mas depende de atributos específicos para funcionar corretamente e garantir uma boa experiência ao usuário.

<p align="center">
 <img src="000-Midia_e_Anexos/vlcsnap-2025-12-24-11h51m34s279.jpg" alt="" width="840">
</p>

```html
<img>

<img src="img/avatar.jpg">

<img alt="Foto de Lucas Vilaboim">

```

O funcionamento desses atributos pode ser comparado a um porta-retratos digital:

* **Atributo `src` (Source):** Este atributo é obrigatório e define o caminho de origem da imagem. Ele indica ao navegador onde o arquivo está guardado, podendo ser um caminho interno (dentro do próprio site) ou externo (um link de outro lugar na web).
* **Atributo `alt` (Alternative Text):** Embora não seja tecnicamente obrigatório para a renderização, seu uso é altamente recomendado para a acessibilidade. Ele serve como uma "etiqueta escrita atrás da foto":
* **Fallback visual:** Exibe uma descrição textual caso a imagem não carregue por problemas de conexão ou erro no caminho.
* **Acessibilidade:** É lido por leitores de tela para informar a usuários com deficiência visual o que a imagem representa.

## 🟩 Vídeo 08 - Exercício prático


Link do vídeo:  https://web.dio.me/track/tqi-fullstack-developer/course/introducao-criacao-de-websites-com-html5-e-css3/learning/770e86ac-5bb8-426b-a003-5ecdb18c14a7?autoplay=1


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
