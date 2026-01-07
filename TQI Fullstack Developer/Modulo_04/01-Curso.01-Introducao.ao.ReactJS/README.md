## Instrutor

- Bruno Carneiro (Planning & Business Performance Director na Magazine Luiza)
- Contato Linkedin: / [brunohvcarneiro](https://www.linkedin.com/in/brunohvcarneiro/)

# Parte 1 - Introdução ao ReactJS

## 🟩 Vídeo 01 - Conheça a tecnologia ReactJS

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-Video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/introducao-ao-reactjs/learning/01818f0a-18b2-4023-a555-57ebefba56af?autoplay=1 

O vídeo apresenta uma introdução abrangente ao **React JS**, conduzida pelo especialista Bruno Carneiro, que define a ferramenta como uma **biblioteca JavaScript** focada na criação de interfaces de usuário. O autor detalha a **trajetória histórica** da tecnologia, desde sua criação no Facebook para resolver problemas de escalabilidade até sua expansão para plataformas **mobile e desktop**. São destacados requisitos técnicos essenciais, como o domínio de **JavaScript e NPM**, além de explicar a natureza **declarativa e modular** da biblioteca através da componentização. A fonte enfatiza a **flexibilidade do React**, diferenciando-o de frameworks rígidos e ilustrando sua relevância no mercado por meio do uso em grandes empresas como **Netflix e Instagram**. Por fim, o conteúdo reforça as vantagens de um ecossistema vasto que permite o desenvolvimento de aplicações **modernas e multiplataforma**.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-13h42m19s745.jpg" alt="" width="840">
</p>

Esta é a abertura do curso de **Introdução ao ReactJS**, ministrado por Bruno Carneiro, Líder Técnico no Luiza Labs. O curso foca nos fundamentos da biblioteca ReactJS para o desenvolvimento de interfaces modernas.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-13h42m52s294.jpg" alt="" width="840">
</p>

Os primeiros objetivos da aula incluem uma introdução à **História e Conceitos** fundamentais do React, seguida pelas etapas de **Configuração** do ambiente de desenvolvimento. Também será abordado o **JSX**, que é a extensão de sintaxe para JavaScript utilizada no React.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-13h43m09s908.jpg" alt="" width="840">
</p>

Dando continuidade ao cronograma, o curso explorará o processo de **Renderização** de elementos na tela e a criação de **Componentes e Props**, que permitem a construção de interfaces modulares. Outro ponto central será o entendimento do **Estado e Ciclo de Vida** dos componentes, essenciais para gerenciar dados dinâmicos.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-13h43m15s078.jpg" alt="" width="840">
</p>

O último tópico listado nos objetivos da aula é o **Ecossistema** do React. Compreender o ecossistema é fundamental para entender como o React interage com outras ferramentas e bibliotecas para criar aplicações completas e escaláveis.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-13h43m24s834.jpg" alt="" width="840">
</p>

Para acompanhar o curso, são necessários alguns pré-requisitos técnicos: conhecimentos básicos em **JavaScript, CSS e HTML**. Além disso, é necessário ter um **Editor de Texto** instalado e o **NPM** (Node Package Manager) para gerenciar as dependências do projeto.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-13h43m30s819.jpg" alt="" width="840">
</p>

Conceitualmente, o React é definido como "uma biblioteca JavaScript para criar interfaces de usuário". No momento da gravação, a versão de referência utilizada era a **16.8.6**.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-13h43m34s657.jpg" alt="" width="840">
</p>

O React foi criado em **2011 por Jordan Walke**, um engenheiro do **Facebook**. Sua origem é baseada no **XHP**, que era um framework utilizado para a criação de HTML dentro do ecossistema PHP da empresa. Inicialmente, o React foi implementado para gerenciar o **mural de notícias** (news feed) do Facebook.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-13h43m43s068.jpg" alt="" width="840">
</p>

A linha do tempo do React mostra marcos importantes: em **2012**, passou a ser utilizado no **Instagram**; em **2013**, foi anunciado como projeto **OpenSource** durante a JSConf US; e em **2015**, expandiu-se com o lançamento do **React Native** e o suporte para **UWP** (Universal Windows Platform).

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-13h43m50s544.jpg" alt="" width="840">
</p>

É fundamental distinguir que o React é uma **biblioteca** e não um framework. Enquanto frameworks costumam ditar a estrutura completa da aplicação, o React foca especificamente em sua principal função: a **criação da interface para o usuário**.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-13h43m59s823.jpg" alt="" width="840">
</p>

De acordo com o relatório do **Stack Overflow de 2019**, o React.js já se posicionava como a segunda tecnologia de interface mais utilizada (31.3%), aproximando-se do jQuery (48.7%) entre os desenvolvedores profissionais. Esse crescimento reflete a popularidade do JavaScript e a eficiência do React em diversos contextos, como dispositivos móveis e front-end.      


## 🟩 Vídeo 02 - Aprenda a configurar o ReactJS

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-Video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/introducao-ao-reactjs/learning/7b2d1a93-9235-412e-9749-44fb27e0ef93?autoplay=1

O vídeo apresenta um guia prático sobre a **configuração inicial e os fundamentos do React**, destacando o uso do **React Scripts** para automatizar a criação de servidores e ferramentas de build. O autor detalha a estrutura de um projeto, explicando a função do arquivo **package.json** e do gerenciador de pacotes **NPM** no ecossistema JavaScript. Uma parte significativa da explicação é dedicada ao **JSX**, descrevendo-o como uma extensão que permite integrar **lógica JavaScript diretamente em estruturas HTML** de forma declarativa. O conteúdo também aborda o conceito de **Single Page Application (SPA)** e demonstra como renderizar componentes dentro de um elemento raiz no navegador. Por fim, são fornecidos exemplos de **interpolação de funções** e a aplicação de estilos através do atributo **className**, ressaltando a facilidade do desenvolvimento com o recurso de **hot reload**.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-14h39m05s841.jpg" alt="" width="840">
</p>

Para iniciar o desenvolvimento com React, o requisito fundamental é ter o **NPM (Node Package Manager)** instalado no sistema. A ferramenta padrão recomendada pelo Facebook para criar novos projetos é o **Create React App**, uma biblioteca que automatiza a configuração inicial do ambiente frontend, entregando uma estrutura base pronta para uso.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-14h39m15s023.jpg" alt="" width="840">
</p>

O **Create React App** utiliza internamente o **React Scripts**, que gerencia ferramentas essenciais como o **Webpack** (para empacotamento) e o **Jest** (para testes). Embora existam alternativas como configurar manualmente via Parcel ou Rollup, ou até utilizar CDNs, o uso do React Scripts simplifica o processo ao montar automaticamente um servidor de desenvolvimento.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-14h41m08s555.jpg" alt="" width="840">
</p>

O processo de criação de um projeto começa com a inicialização do Node.js no diretório escolhido. Ao executar o comando de inicialização, o sistema cria o arquivo de configuração essencial para o ecossistema JavaScript.

```bash
npm init

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-14h41m23s317.jpg" alt="" width="840">
</p>

Após a inicialização, é necessário instalar as dependências principais. O comando abaixo instala a biblioteca do React em uma versão específica e o pacote de scripts que automatiza as tarefas de build e execução.

```bash
npm install react@16.8.6 react-dom@16.8.6 react-scripts

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-14h41m29s306.jpg" alt="" width="840">
</p>

O arquivo **package.json** funciona como o cérebro do projeto, centralizando todas as informações, versões de dependências e os scripts que serão utilizados para rodar a aplicação. Inicialmente, as abas de scripts e dependências refletem o que foi instalado via terminal.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-14h41m42s385.jpg" alt="" width="840">
</p>

Para que o React Scripts funcione corretamente, é necessário criar uma pasta chamada **public**. Este diretório é identificado pela ferramenta como o ponto de entrada estático da aplicação.

```bash
mkdir public

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-14h42m25s790.jpg" alt="" width="840">
</p>

Dentro da pasta public, cria-se um arquivo HTML básico. O elemento mais importante deste arquivo é uma **div** com o atributo `id="root"`. É neste local específico que o React "pendurará" toda a interface da aplicação durante a renderização.

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>React App</title>
</head>
<body>
    <div id="root"></div>
</body>
</html>

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-14h43m03s826.jpg" alt="" width="840">
</p>

Além da pasta pública, deve-se criar uma pasta **src** (source), que conterá o código-fonte lógico da aplicação, incluindo os arquivos JavaScript e CSS.

```bash
mkdir src

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-14h43m18s662.jpg" alt="" width="840">
</p>

Um arquivo CSS inicial é geralmente criado para resetar estilos globais, como remover as margens e paddings padrão do navegador, garantindo um layout consistente.

```css
* {
    margin: 0;
    padding: 0;
}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-14h43m26s451.jpg" alt="" width="840">
</p>

No arquivo principal dentro de `src`, realizamos a importação do **React** e do **ReactDOM**. O código utiliza a função de renderização para localizar o elemento `root` no HTML e injetar o conteúdo desejado.

```javascript
import React from 'react';
import ReactDOM from 'react-dom';

const App = () => (
  <div>
    <h1>Olá React</h1>
  </div>
);

ReactDOM.render(<App />, document.getElementById('root'));

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-14h43m30s430.jpg" alt="" width="840">
</p>

O React opera como uma **Single Page Application (SPA)**. Isso significa que, independentemente da complexidade ou das rotas da aplicação, tudo é renderizado dentro de uma única página HTML, o que melhora a performance e evita recarregamentos desnecessários do navegador.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-14h43m46s820.jpg" alt="" width="840">
</p>

O **JSX** é a extensão de sintaxe utilizada para descrever a interface. Ele permite escrever estruturas similares ao HTML diretamente no JavaScript. No entanto, como os navegadores não entendem JSX nativamente, o **Babel** atua como transpilador, convertendo esse código para chamadas `React.createElement`.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-14h44m00s637.jpg" alt="" width="840">
</p>

Para executar a aplicação e abrir o servidor de desenvolvimento (geralmente no `localhost:3000`), utiliza-se o gerenciador de pacotes.

```bash
npm start

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-14h44m20s005.jpg" alt="" width="840">
</p>

Uma das funcionalidades mais poderosas do JSX é a interpolação. Para executar qualquer código JavaScript (como variáveis ou operações matemáticas) dentro da marcação HTML, utilizam-se as **chaves `{}**`.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-14h44m23s056.jpg" alt="" width="840">
</p>

Exemplo prático de interpolação no JSX, onde uma operação lógica ou variável é inserida diretamente entre elementos HTML:

```javascript
const element = <h1>Resultado: {1 + 1}</h1>;

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-14h44m24s843.jpg" alt="" width="840">
</p>

O ambiente de desenvolvimento configurado pelo React Scripts inclui o **hot reload**. Isso permite que qualquer alteração salva no código seja refletida instantaneamente no navegador, agilizando o ciclo de feedback durante a criação da interface.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-14h44m28s835.jpg" alt="" width="840">
</p>

O React segue um modelo **declarativo** e foca em **programação funcional**. Em vez de manipular o DOM manualmente, você declara como a interface deve ser baseada no estado atual, e o React se encarrega de atualizar os elementos necessários.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-14h44m55s919.jpg" alt="" width="840">
</p>

Embora o React suporte a criação de componentes via classes, a tendência e recomendação moderna é o uso de **funções**, que são suficientes para a maioria das necessidades de desenvolvimento e tornam o código mais conciso.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-14h45m14s168.jpg" alt="" width="840">
</p>

No JSX, devido ao fato de estarmos escrevendo JavaScript, existem algumas diferenças de nomenclatura em relação ao HTML tradicional. Por exemplo, o atributo para definir classes CSS deve ser escrito como `className`, uma vez que `class` é uma palavra reservada da linguagem JavaScript.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-14h45m19s035.jpg" alt="" width="840">
</p>

O CSS é importado diretamente para o arquivo do componente, integrando o estilo ao contexto daquela parte da interface.

```javascript
import './index.css';

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-14h45m52s945.jpg" alt="" width="840">
</p>

A essência do React reside na **componentização**. Diferente de outras ferramentas que tentam separar rigorosamente as tecnologias (HTML, CSS, JS), o React aproxima esses conceitos para criar blocos independentes e reutilizáveis chamados componentes.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-14h46m14s814.jpg" alt="" width="840">
</p>

Essa estrutura fortalece a construção da aplicação, permitindo que cada parte da interface seja tratada como uma unidade lógica que contém sua própria estrutura e comportamento, facilitando a escalabilidade do projeto.      

### ▶️ INICIALIZAÇÃO DE PROJETO REACT (PROCEDIMENTO ATUALIZADO COM VITE)

#### Nota: 

- O curso original utiliza um roteiro de instalação mais antigo (baseado em create-react-app).
- Para acompanhar o conteúdo utilizando as práticas atuais do ecossistema React, este projeto foi inicializado com Vite + React, abordagem moderna (jan/2026) recomendada pela comunidade.

#### PRÉ-REQUISITOS
- Node.js (versão LTS, recomendado >= 18)
- npm (instalado junto com o Node.js)
- Terminal e editor de código (ex: VS Code)

#### PASSO A PASSO

1. Criar o projeto React com Vite
No terminal, navegue até o diretório desejado e execute:

```bash
npm create vite@latest my-react-app -- --template react
```

(Substitua "my-react-app" pelo nome do projeto desejado)

2. Acessar o diretório do projeto

```bash
cd my-react-app
```

3. Instalar as dependências

```bash
npm install
```


4. Iniciar o servidor de desenvolvimento

```bash
npm run dev
```

Após a execução, a aplicação estará disponível em:
http://localhost:5173

#### ESTRUTURA INICIAL DO PROJETO

```bash
my-react-app/
├── index.html
├── package.json
├── vite.config.js
├── public
│   └── vite.svg
├── src
│   ├── 🟢 App.css
│   ├── 🔴 App.jsx
│   ├── index.css
│   ├── main.jsx
│   └── assets/
└── README.md

```

#### 🔴 App.jsx:

```javascript
import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function sum(a, b) {
  return a + b
}

const App = () => {
  return (
    <div className="teste">
      Bruno Carneiro · Introdução ao ReactJS
      <h1>Soma: {sum(10, 20)}</h1>
    </div>
    
  )
}

export default App
```

#### 🟢 App.css 

```css
...

.teste {
  color: green;
}

...
```


#### OBSERVAÇÃO SOBRE COMPATIBILIDADE COM O CURSO

- A lógica do React ensinada no curso permanece a mesma (componentes, JSX, props, state).
- A principal diferença está apenas na ferramenta de build e na estrutura inicial.
- O uso do Vite oferece inicialização mais rápida, build mais leve e melhor experiência de desenvolvimento.

#### RESUMO DOS COMANDOS

```bash
npm create vite@latest my-react-app -- --template react
cd my-react-app
npm install
npm run dev
```

## 🟩 Vídeo 03 - Renderizando elementos

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-Video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/introducao-ao-reactjs/learning/e6388147-f925-4241-964d-eba43b4c531c?autoplay=1

O vídeo explica os fundamentos do **React**, focando na eficiência da renderização por meio do **Virtual DOM**, que atualiza apenas as partes alteradas da interface para otimizar a performance. O autor diferencia **elementos** de **componentes**, descrevendo os componentes como peças modulares e reutilizáveis que facilitam a organização de projetos robustos. A explicação detalha como as **props** são utilizadas para transmitir dados e funções entre esses componentes, permitindo uma comunicação fluida na aplicação. Além disso, aborda a importância do **JSX** e do uso de funções para criar componentes puros, evitando regras de negócio complexas em elementos visuais. Por fim, demonstra a composição de interfaces através da propriedade **children**, que permite aninhar estruturas de forma hierárquica e escalável.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-18h26m17s470.jpg" alt="" width="840">
</p>

### Introdução à Renderização no ReactJS

A renderização é o processo fundamental onde o React transforma os elementos definidos em código em componentes visíveis na interface do usuário. No React, essa renderização é baseada em **nós raiz**, tratando cada retorno de elemento como um nó dentro de uma estrutura organizada. Os elementos representam a menor unidade de construção em uma aplicação React, servindo como a base para a criação de interfaces web complexas.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-18h26m22s350.jpg" alt="" width="840">
</p>

### Gerenciamento via React DOM

O **React DOM** atua como a ponte entre o React e o navegador. Diferente de bibliotecas mais antigas que manipulavam o DOM (Document Object Model) do browser diretamente, o React utiliza uma camada intermediária. Os conceitos-chave para entender este fluxo incluem:

* **Renderização de Elementos:** O processo de atualizar a UI.
* **React DOM:** A biblioteca que renderiza os componentes no contexto do navegador.
* **Nós Raiz:** O ponto de entrada no HTML onde toda a aplicação React será "montada".

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-18h28m18s009.jpg" alt="" width="840">
</p>

### Performance e o Virtual DOM

Uma das maiores vantagens do React é o uso do **Virtual DOM**. Ao invés de atualizar toda a árvore HTML sempre que ocorre uma mudança, o React compara a versão anterior com a nova e identifica **apenas o pedaço que foi alterado**.

Essa abordagem de "pensar em como a interface deve estar a cada momento" em vez de alterá-la manualmente ao longo do tempo garante um ganho significativo de performance e reduz drasticamente a ocorrência de erros de sincronização entre o estado da aplicação e o que é exibido na tela.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-18h44m20s810.jpg" alt="" width="840">
</p>

### Implementação Básica de Renderização

Para renderizar múltiplos elementos, é necessário encapsulá-los dentro de um único nó pai (como uma `div`), pois o React exige um único ponto de retorno por componente. No código abaixo, vemos a definição de elementos simples e a função `App` que os agrupa.

```javascript
import React, { Fragment } from 'react'
import ReactDOM from 'react-dom'
import "./styles.css"

const element = 'Digital Innovation'
const element2 = <h1>Olá Turma D</h1>

function App() {
  return (
    <div>
      {element}
      {element2}
    </div>
  )
}

const rootElement = document.getElementById("root")
ReactDOM.render(<App />, rootElement)

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-18h44m33s762.jpg" alt="" width="840">
</p>

### Introdução a Components e Props

Os componentes são a essência da modularidade no React. Eles permitem dividir a interface em partes independentes e reutilizáveis, facilitando a manutenção e a escalabilidade do projeto. Já as **Props** (propriedades) são o mecanismo utilizado para passar informações e parâmetros de um componente pai para um componente filho.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-18h44m38s516.jpg" alt="" width="840">
</p>

### Analogia dos Blocos de Construção

A componentização pode ser comparada a **peças de Lego**. Criar componentes básicos únicos (como botões e menus) permite que diferentes desenvolvedores utilizem o mesmo padrão visual e funcional em toda a aplicação, evitando duplicação de esforço e inconsistências no design. O que parece ser um excesso de arquivos inicialmente é, na verdade, uma organização que favorece o reaproveitamento de código.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-18h47m19s572.jpg" alt="" width="840">
</p>

### Estrutura de Componentes

Ao trabalhar com componentes e props, existem tópicos fundamentais para dominar a arquitetura React:

* **Função e Classe:** As duas formas de definir componentes (atualmente priorizando funções).
* **Props:** Parâmetros de entrada.
* **Composição de Componentes:** A habilidade de combinar componentes menores para formar interfaces complexas.
* **Extração de Componentes:** O processo de transformar partes de um componente grande em componentes menores e mais puros.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-18h57m40s324.jpg" alt="" width="840">
</p>

### Composição e Uso de Children

Abaixo, um exemplo de aplicação real onde componentes são aninhados. Note o uso da propriedade `children`, que permite que um componente receba e renderize outros componentes inseridos dentro de suas tags de abertura e fechamento.

```javascript
import React from "react"
import ReactDOM from "react-dom"
import Button from './Button'
import "./styles.css"

function soma(a, b) {
  alert(a + b)
}

function App() {
  return (
    <div className="App">
      Hello World
      <Button onClick={() => soma(10, 20)} name="Bruno Carneiro" />
      <ComponentA>
        <ComponentB>
          <Button onClick={() => soma(40, 20)} name="Aline Carneiro" />
        </ComponentB>
      </ComponentA>
    </div>
  )
}

const rootElement = document.getElementById("root")
ReactDOM.render(<App />, rootElement)

```      

## 🟩 Vídeo 04 - Conheça os ecossistemas do ReactJS

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-Video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/introducao-ao-reactjs/learning/838c3b03-88fc-4dca-8904-21f43bd57291?autoplay=1 

O vídeo apresenta uma introdução técnica ao **React**, enfatizando que a compreensão do **ciclo de vida dos componentes** é fundamental para dominar a ferramenta. O autor detalha as fases de **inicialização, montagem, atualização e desmontagem**, alertando que falhas no entendimento desses processos podem causar gargalos de performance. Através de exemplos práticos com **classes e estados locais**, a explicação demonstra como o **Virtual DOM** reage a mudanças de propriedades e dados. Além disso, reforça-se a importância de ter uma base sólida em **JavaScript** antes de avançar para conceitos complexos. Por fim, o conteúdo explora o vasto **ecossistema de bibliotecas** complementares que tornam o React uma solução flexível e escalável para o desenvolvimento de aplicações modernas.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-19h25m14s646.jpg" alt="" width="840">
</p>

Esta aula introduz os conceitos fundamentais de **Estado e Ciclo de Vida** no ReactJS. Compreender esses mecanismos é essencial para dominar a ferramenta, pois eles definem como os componentes reagem a mudanças e como se comportam desde a sua criação até a sua remoção da interface. A base para esse aprendizado avançado reside no domínio sólido do JavaScript básico.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-19h25m16s352.jpg" alt="" width="840">
</p>

O ciclo de vida de um componente React é o conjunto de fases que ele percorre durante sua existência na aplicação. Entender esse fluxo permite ao desenvolvedor executar ações em momentos específicos, garantindo que a lógica de negócio e a interface do usuário estejam sempre sincronizadas de forma eficiente.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-19h25m20s614.jpg" alt="" width="840">
</p>

O funcionamento do React é estruturado em quatro estados ou fases principais:

* **Inicialização:** Onde as propriedades e estados iniciais são definidos.
* **Montagem:** O momento em que o componente é inserido no DOM.
* **Atualização:** Quando ocorrem mudanças em props ou no estado, gerando novas renderizações.
* **Desmontagem:** A fase final, quando o componente é removido da página.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-19h25m25s457.jpg" alt="" width="840">
</p>

O ciclo de vida dita uma ordem de execução técnica rigorosa através de métodos específicos:

* **Montagem:** Inicia com `componentWillMount` (depreciado em versões recentes por questões de segurança), seguido pelo `render` para montar a página, e finaliza com o `componentDidMount`.
* **Atualização:** Toda vez que uma propriedade (**prop**) ou um **state** é alterado, o React inicia um ciclo de atualização que envolve métodos como `shouldComponentUpdate` e `componentDidUpdate`, renderizando o bloco correspondente no DOM virtual.
* **Desmontagem:** Utiliza o método `componentWillUnmount` para realizar limpezas antes do componente deixar de existir.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-19h25m32s658.jpg" alt="" width="840">
</p>

Um conceito fundamental é o isolamento dos componentes. No React, o **estado é local ao componente**, o que significa que componentes pais ou filhos não precisam ter conhecimento sobre a existência de estados internos uns dos outros. Caso seja necessário compartilhar informações entre eles, a comunicação deve ser estabelecida preferencialmente através do uso de **props**.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-19h25m55s661.jpg" alt="" width="840">
</p>

Como o React é uma biblioteca focada na interface, a construção de aplicações robustas e escaláveis depende da integração com seu vasto **ecossistema**. Algumas das ferramentas essenciais que complementam o desenvolvimento incluem:

* **React Router:** Para o gerenciamento de rotas e navegação.
* **Redux:** Para o controle de estados complexos e globais.
* **Material UI / Ant Design:** Bibliotecas de componentes de interface prontos e estilizados.
* **Storybook:** Para o desenvolvimento visual e documentação de componentes de forma isolada.
* **Gatsby:** Focado na criação de sites estáticos otimizados.
* **Jest:** Utilizado para a realização de testes na aplicação.
* **React i18n Next:** Para suporte a internacionalização de projetos.


# Parte 2 - Integrando webpack ao desenvolvimento com ReactJS

## 🟩 Vídeo 05 - O que é webpack?

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-Video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/introducao-ao-reactjs/learning/6c8cc254-0dbf-4ad8-a087-3e6fa5a59af8?autoplay=1

Nesta aula ministrada por Bruno Carneiro, o foco principal é a **integração do Webpack** no desenvolvimento de aplicações com **React JS**. O instrutor define o Webpack como um **empacotador de módulos** que organiza diversos arquivos e dependências em um único feixe otimizado para navegadores. Durante a apresentação, são detalhados os **pré-requisitos técnicos**, como a necessidade do Node.js e do NPM, além de mencionar o uso do VS Code. O conteúdo destaca que, embora ferramentas automáticas como o React Scripts facilitem o início, a **configuração manual** oferece maior controle e poder de otimização para projetos de grande escala. Além de scripts, o Webpack é capaz de processar **CSS, imagens e fontes** através de plugins, permitindo uma arquitetura de software mais flexível e robusta.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-19h41m35s521.jpg" alt="" width="840">
</p>

Nesta aula introdutória ministrada por Bruno Carneiro, Líder Técnico na Luiza Labs, o foco central é a integração do **Webpack** ao desenvolvimento com **React JS**. O objetivo é compreender como essa ferramenta atua no ecossistema moderno de desenvolvimento front-end, permitindo a criação de aplicações robustas e otimizadas.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-19h41m39s332.jpg" alt="" width="840">
</p>

Os objetivos principais desta unidade de aprendizado estão divididos em quatro pilares fundamentais:

1. **O que é o Webpack**: Definição e papel do empacotador.
2. **Configuração**: Como estruturar o ambiente de forma manual.
3. **Criando um Dev Server**: Estabelecimento de um ambiente de desenvolvimento ágil.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-19h41m55s475.jpg" alt="" width="840">
</p>

Além da configuração estrutural do Webpack, a aula abordará a implementação do **ESLint**, ferramenta essencial para garantir a qualidade do código e a padronização entre diferentes desenvolvedores no projeto.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-19h41m58s008.jpg" alt="" width="840">
</p>

Para acompanhar o desenvolvimento prático, são necessários alguns requisitos básicos instalados na máquina:

* **Node.js**: Recomenda-se a versão 10.16 ou superior.
* **NPM**: Gerenciador de pacotes que acompanha a instalação do Node.
* **Editor de Texto**: Preferencialmente o VS Code, embora qualquer editor de preferência possa ser utilizado.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-19h42m02s869.jpg" alt="" width="840">
</p>

O **Webpack** é definido como um *module bundler* (empacotador de módulos) para aplicações JavaScript. Sua função principal é processar os diversos módulos da aplicação e gerar um **bundle** (arquivo final) em **ES5**, garantindo que o navegador consiga interpretar o código corretamente, independentemente da complexidade da árvore de dependências (grafo) do projeto.

Para mais detalhes, a documentação oficial pode ser consultada em: `https://webpack.js.org/`

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-19h42m09s763.jpg" alt="" width="840">
</p>

Embora seja um empacotador focado em JavaScript, o Webpack possui um ecossistema vasto que oferece suporte para diversos tipos de recursos através de *loaders* e extensões:

* **Fontes** e **Imagens**
* **CSS** e **HTML**
* **JavaScript (JS)**
* **Plugins** variados para otimização e automação.

Diferente de ferramentas como o Parcel (mais simples) ou Rollup (focado em bibliotecas), o Webpack é a escolha ideal para grandes aplicações devido ao seu alto poder de customização.      


## 🟩 Vídeo 06 - Aprenda a configurar o webpack

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-Video_06.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/introducao-ao-reactjs/learning/f698389b-6687-4bed-b654-b9b7d1f5d887?autoplay=1

O vídeo consiste em uma transcrição técnica detalhada sobre a **configuração inicial do Webpack 4** voltada para o desenvolvimento com **React**. O autor explica conceitos fundamentais como **entry points**, **output**, **loaders** e **plugins**, demonstrando na prática como transformar código moderno em pacotes otimizados para navegadores. A fonte aborda a instalação de dependências essenciais, incluindo o **Babel** para transpilação de sintaxe e o **Webpack Dev Server** para agilizar o fluxo de trabalho com **hot reloading**. Além de orientar sobre a criação de arquivos de configuração, o material diferencia os modos de **produção e desenvolvimento**, focando em performance e depuração. O objetivo central é capacitar o desenvolvedor a estruturar um ambiente completo de **Single Page Application** do zero. Por fim, o conteúdo ressalta a importância de gerar arquivos finais leves para melhorar a experiência do usuário final.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-20h40m18s104.jpg" alt="" width="840">
</p>

Esta imagem marca o início da aula sobre a configuração do Webpack para o ambiente de desenvolvimento com ReactJS. O objetivo central é compreender como o Webpack gerencia as dependências e módulos de uma aplicação moderna, permitindo que funcionalidades avançadas do JavaScript sejam interpretadas corretamente pelos navegadores.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-20h40m24s379.jpg" alt="" width="840">
</p>

Neste estágio, são apresentados os dois primeiros pilares fundamentais do funcionamento do Webpack:

* **Entry (Entrada):** O Webpack utiliza uma estrutura de grafo para mapear o projeto. O *entry point* é o arquivo inicial onde a ferramenta começa a buscar todos os módulos e dependências necessários para construir a aplicação.
* **Output (Saída):** Define o destino final dos arquivos processados. É através do *output* que determinamos em qual diretório e com qual nome os bundles (pacotes de arquivos emitidos) serão gerados.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-20h40m34s287.jpg" alt="" width="840">
</p>

Dando continuidade aos conceitos principais, exploramos os Loaders e Plugins:

* **Loaders:** Essenciais para expandir a capacidade do Webpack. Por padrão, ele entende apenas JavaScript, mas os *loaders* permitem que ele gerencie e processe outros tipos de arquivos, como CSS, imagens, HTML e fontes.
* **Plugins:** Enquanto os loaders agem na transformação de arquivos, os plugins atuam em uma gama mais ampla de tarefas, como a otimização de pacotes, minificação do código final e a injeção automática de scripts nos arquivos HTML.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-20h40m36s872.jpg" alt="" width="840">
</p>

O conceito de **Mode** (Modo) é introduzido para facilitar a configuração baseada no ambiente de execução. Com a abordagem de "zero configuration" da versão 4, é possível alternar entre:

* **Production:** Focado no usuário final, trazendo otimizações internas e minificação para reduzir o tamanho dos arquivos.
* **Development:** Focado na experiência do desenvolvedor (debug), executando plugins como o *NoEmitOnErrorsPlugin* para auxiliar na identificação de falhas durante a codificação.
* **None:** Nenhuma configuração pré-definida é aplicada.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-20h40m42s615.jpg" alt="" width="840">
</p>

Para colocar os conceitos em prática, é necessária a criação do arquivo `webpack.config.js` e a instalação das dependências via terminal. O processo envolve preparar o ambiente para suportar React e a transpilação do código moderno através do Babel.

Abaixo, os comandos de instalação e a configuração de script para o processo de build:

```bash
# Instalação do Webpack e sua interface de linha de comando como dependência de desenvolvimento
npm i -D webpack webpack-cli

# Instalação do core do Babel, loader para Webpack e presets para JavaScript moderno e React
npm i @babel/core babel-loader @babel/preset-env @babel/preset-react --save-dev

```

No arquivo `package.json`, o script de build deve ser configurado da seguinte forma para gerar a versão otimizada:

```json
"build": "webpack --mode production"

```

---


## 🟩 Vídeo 07 - Instalação e configuração do ESLint

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-Video_07.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/introducao-ao-reactjs/learning/74a29d1d-06eb-47b9-8a2d-ef8ad5c7c031?autoplay=1

O autor explica a importância de utilizar o **ESLint** para garantir a **padronização** e a **qualidade do código** em projetos colaborativos. A ferramenta ajuda a identificar falhas de sintaxe e a aplicar **boas práticas** automaticamente, evitando que divergências de escrita prejudiquem o desenvolvimento. Além disso, o texto destaca o papel fundamental do **Source Map** no processo de **depuração**, pois ele organiza os arquivos para facilitar a localização de erros. Sem essa configuração, o código gerado pelo Webpack torna-se confuso e difícil de analisar durante a fase de testes. Por fim, o instrutor incentiva o uso de **configurações consagradas** e disponibiliza materiais de apoio em seu **GitHub**.

### Anotações

#### Introdução ao ReactJS

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-21h02m01s354.jpg" alt="" width="840">
</p>

O **ESLint** é uma ferramenta fundamental para garantir a **qualidade do código** e manter a padronização em projetos desenvolvidos por múltiplas pessoas. Como cada desenvolvedor possui um estilo de escrita próprio, o ESLint estabelece regras que evitam divergências e ajudam a identificar erros comuns ainda em tempo de desenvolvimento.

A utilização de padrões de mercado, como o guia de estilo do **Airbnb**, permite que o projeto siga convenções amplamente aceitas, verificando automaticamente a presença de itens como ponto e vírgula, o uso correto de tabs ou a estrutura de arrow functions. No contexto desta aula, a ferramenta é integrada ao ecossistema React para assegurar que o código entregue ao repositório esteja limpo e funcional.

#### Configuração do ESLint

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-21h02m05s145.jpg" alt="" width="840">
</p>

Para integrar o ESLint ao projeto, é necessário instalar as dependências de desenvolvimento que permitem a análise do código JavaScript e o suporte específico para a sintaxe do React e do Babel. A instalação é feita através do gerenciador de pacotes npm:

```bash
npm install --save-dev eslint babel-eslint eslint-plugin-react eslint-watch

```

Além da instalação, a configuração é personalizada através de um arquivo chamado **.eslintrc**, que contém as definições das regras de linting que serão aplicadas em todos os arquivos dentro da pasta do projeto.

# Parte 3 - Conceitos aplicados aos tipos de dados e condições da biblioteca

## 🟩 Vídeo 08 - Renderização Condicional

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-Video_08.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/introducao-ao-reactjs/learning/5a5e8e60-fb11-4b66-958b-ed2da9e18432?autoplay=1

O instrutor detalha técnicas de **renderização condicional**, ensinando como exibir ou ocultar elementos na interface utilizando **operadores lógicos** e **ternários** no JSX. Além de abordar a lógica de programação, o conteúdo oferece orientações sobre **boas práticas**, como a modularização do código em funções e a nomeação correta de arquivos para melhorar a legibilidade. Bruno também demonstra configurações práticas no **Webpack** e no ambiente de desenvolvimento para otimizar o fluxo de trabalho. O material serve como um guia técnico para desenvolvedores que buscam compreender o caráter **declarativo** da biblioteca e como aplicá-lo na criação de componentes dinâmicos.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-21h50m46s432.jpg" alt="" width="840">
</p>

Nesta abertura da terceira aula do curso de **Introdução ao React**, o instrutor **Bruno Carneiro**, Líder Técnico no Luiza Labs, apresenta os tópicos centrais que serão abordados. O foco desta etapa é o aprofundamento em conceitos aplicados aos tipos de dados e condições dentro da biblioteca, preparando a base para o desenvolvimento de interfaces mais complexas e dinâmicas.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-21h50m49s869.jpg" alt="" width="840">
</p>

Os objetivos principais desta aula estão estruturados em três pilares fundamentais para a manipulação de componentes e dados no ecossistema React:

1. **Renderização Condicional**: Capacidade de exibir diferentes elementos baseando-se no estado atual.
2. **Listas e Chaves**: Como iterar sobre dados para gerar múltiplos componentes de forma eficiente.
3. **Manipulação de Eventos**: A forma como o React lida com as interações do usuário.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-21h50m53s983.jpg" alt="" width="840">
</p>

Para acompanhar o conteúdo prático, são necessários os seguintes requisitos técnicos e teóricos:

* **Editor de Texto**: Ferramenta para escrita do código.
* **NPM**: Gerenciador de pacotes do Node.js devidamente instalado.
* **Conhecimento de JS (ES5)**: Domínio básico da sintaxe JavaScript para compreensão da lógica da biblioteca.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-21h50m57s213.jpg" alt="" width="840">
</p>

A **Renderização Condicional** fundamenta-se na natureza declarativa do React. Ela permite a criação de componentes distintos que encapsulam comportamentos específicos. Na prática, isso significa que a aplicação decide quais elementos devem ser renderizados na interface do usuário dependendo exclusivamente do **estado da aplicação** em um determinado momento.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-06-21h51m01s970.jpg" alt="" width="840">
</p>

A implementação da lógica condicional no JSX pode ser realizada através de quatro abordagens principais:

* **Variáveis de Elementos**: Armazenar elementos React em variáveis para uso posterior no retorno.
* **If inline com o Operador Lógico &&**: Utilizado para renderizar um elemento apenas quando uma condição é verdadeira.
* **If-Else inline com Operador Condicional**: Uso do ternário para alternar entre dois elementos diferentes.
* **Evitando que um Componente seja Renderizado**: Técnica de retornar `null` para impedir que o componente apareça na árvore do DOM.

### 🔴 App.jsx

```javascript
import React from "react";

const buttonA = <button>Histórico dos Clientes</button>;
const buttonB = <button>Cadastrar Cliente</button>;

const hasCustomer = true;

const App = () => {

  const renderShowHistory = (
    <div>
      Clique no botão abaixo para visualizar o histórico dos clientes
      <br />
      {buttonA}
    </div>
  );

  const renderAddCustomer = (
    <div>
      Clique abaixo para cadastrar o cliente
      <br />
      {buttonB}
    </div>
  );

  const showCustomer = () => {
    if (!hasCustomer) return null;

    return (
      <div>
        <h1>Nome do Cliente: Marcia Castagna</h1>
      </div>
    );
  };

  console.log("hasCustomer", hasCustomer);

  return (
    <div>
      <p>Digital Innovation One</p>
      <p>Bem-vindo a nossa aula =D.</p>
      {hasCustomer ? renderShowHistory : renderAddCustomer}
      <div>
        {showCustomer()}
      </div>
    </div>
  );
};

export default App;

```

### Detalhando o funcionamento das estruturas lógicas aplicadas:

#### 1. Variáveis de Elementos

No início do código, elementos JSX são armazenados diretamente em constantes. Isso permite que botões ou fragmentos de interface sejam reutilizados de forma limpa dentro da lógica de renderização posterior.

* `buttonA`: Armazena o elemento de botão para o histórico.
* `buttonB`: Armazena o elemento de botão para cadastro.

#### 2. Funções de Renderização Auxiliares

Seguindo a boa prática de manter o código legível e modular, foram criadas funções específicas para gerar blocos de interface.

* `renderShowHistory`: Uma função que retorna um agrupamento de elementos (texto + `buttonA`) focado na visualização de dados existentes.
* `renderAddCustomer`: Uma função focada no fluxo de criação, retornando orientações para o cadastro junto ao `buttonB`.

#### 3. Operador Condicional (Ternário) Inline

Dentro do retorno principal do componente `App`, o código utiliza a variável `hasCustomer` para decidir qual bloco exibir.

* **Sintaxe**: `{hasCustomer ? renderShowHistory : renderAddCustomer}`.
* Se `hasCustomer` for **verdadeiro**, a interface mostra o histórico.
* Se for **falso**, a interface alterna automaticamente para a opção de cadastro.

#### 4. Prevenção de Renderização com `null`

A função `showCustomer` exemplifica como impedir que um componente seja montado na tela.

* **Lógica de Bloqueio**: O código verifica se o cliente não existe (`!hasCustomer`) e, caso positivo, retorna **`null`**.
* No React, retornar `null` faz com que o componente não apareça na árvore do DOM, o que é fundamental para gerenciar permissões ou dados ausentes sem poluir a interface.


## 🟩 Vídeo 09 - Listas e Chaves

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-Video_09.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/introducao-ao-reactjs/learning/e123a46b-526b-46bb-9234-ba5c6a669ed9?autoplay=1

O material consiste em uma aula técnica sobre a **renderização de listas e chaves no React JS**, focando em boas práticas de organização de código. O autor demonstra como utilizar a função **map** para transformar arrays de dados em elementos JSX, enfatizando a importância de **separar a lógica em blocos menores** para garantir modularidade. Um ponto central da explicação é a necessidade de atribuir **chaves únicas aos elementos**, preferencialmente utilizando IDs provenientes dos dados em vez de índices simples. O instrutor esclarece que essas propriedades de identificação precisam ser exclusivas apenas entre **elementos irmãos**, permitindo que o React gerencie atualizações de interface de forma eficiente. Além disso, o texto recomenda **evitar funções anônimas embutidas** para prevenir renderizações desnecessárias e melhorar a performance da aplicação. Por fim, o conteúdo incentiva o uso de **arquivos separados para componentes complexos**, promovendo uma arquitetura de software mais legível e escalável.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-07-08h07m09s319.jpg" alt="" width="840">
</p>

Nesta etapa da aula, introduzimos o conceito de **Listas e Chaves** no ReactJS. O objetivo principal é compreender como o framework lida com a renderização de múltiplos elementos a partir de coleções de dados.

Uma recomendação importante para a organização do código é o uso de **Arrow Functions** para o retorno direto de elementos. Essa prática é preferível pois ajuda a **evitar renderizações desnecessárias** no projeto, uma vez que impede a criação de funções adicionais toda vez que o componente sofre uma alteração.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-07-08h07m13s935.jpg" alt="" width="840">
</p>

Para trabalhar com listas, exploramos os seguintes tópicos fundamentais:

* **Renderizando Múltiplos Componentes:** Utilização de métodos de iteração para gerar vários elementos JSX.

* **Componente de Lista Básico:** A estrutura inicial para exibir coleções.

* **Chaves (Keys):** A importância de identificar cada item da lista para o React.

* **Extraindo Componentes com Chaves:** Como manter a referência da chave ao refatorar partes da lista em componentes menores.

* **Unicidade de Chaves:** A regra de que as **chaves devem ser únicas apenas entre elementos irmãos**, não sendo necessário que sejam globais em toda a aplicação.

Na implementação prática, utilizamos o método `.map()` do JavaScript para percorrer o array `listCustomer` e renderizar os componentes na tela. É uma boa prática **separar a renderização em pequenos blocos**, criando funções específicas como `renderCustomers` e `renderSkills` para manter o código modular e legível.

O React exige o uso da propriedade `key` em elementos de lista para controlar quais itens foram alterados, adicionados ou removidos, garantindo a performance da aplicação. Embora o `index` do array possa ser usado, o ideal é utilizar um **ID único** (como `customer.id`) para evitar problemas de renderização e warnings no console.

```javascript
import React from "react";

const listCustomer = [
  { id: 1, name: 'Bruno Carneiro', skills: ['React', 'Node', 'CSS', 'Webpack'] },
  { id: 2, name: 'Aline Carneiro', skills: ['HTML', 'React Native', 'Go', 'JS'] },
  { id: 3, name: 'Fulano de Tal', skills: ['Assembly'] },
  { id: 4, name: 'José Ciclano', skills: ['Reason'] }
]

const App = () => {
  const renderCustomers = (customer, index) => {
    return (
      <div key={`customer-${customer.id}`}>
        <li>{customer.name}</li>
        {customer.skills.map(renderSkills)}
      </div>
    )
  }

  const renderSkills = (skill, index) => {
    return (
      <div style={{ paddingLeft: '30px' }} key={`skill-${index}`}>
        <li>{skill}</li>
      </div>
    )
  }

  return (
    <div>
      <p>Digital Innovation One</p>
      <p>Bem vindo a nossa aula =D.</p>
      <div>
        <ul>
          {listCustomer.map(renderCustomers)}
        </ul>
      </div>
    </div>
  );
};

export default App;

```    

### As partes essenciais para o funcionamento de listas e chaves no React são detalhadas a seguir:

#### 1. Estrutura de Dados (Mocks)

O uso de um array de objetos (`listCustomer`) é a base para a renderização dinâmica.

* **Identificação Única**: Cada objeto na lista possui um atributo `id`.

* **Modularização**: O instrutor sugere que, em projetos reais, esses dados (Mocks) sejam separados em arquivos próprios para manter o código modular e organizado.

#### 2. Funções de Renderização Auxiliares

Em vez de escrever todo o JSX dentro do bloco principal, o código utiliza funções específicas para renderizar partes da interface.

* **Separação em Blocos**: As funções `renderCustomers` e `renderSkills` isolam a lógica de exibição de cada item.

* **Vantagem**: Essa prática facilita a leitura e manutenção, especialmente quando a lógica de renderização se torna complexa, como em relatórios ou chamadas de serviço.

#### 3. O Método `.map()`

O `map` é a ferramenta padrão do JavaScript (ES6+) para iterar sobre listas no React.

* **Retorno de Elementos**: Ele percorre o array e retorna um novo elemento JSX para cada item encontrado.
* **Assinatura da Função**: É possível passar apenas a assinatura da função (ex: `listCustomer.map(renderCustomers)`) para que ela seja executada para cada item.

#### 4. Gestão de Chaves (`key`)

Este é o ponto mais crítico abordado na aula para garantir a performance e evitar erros de sincronização da interface.

* **Importância da Key**: O atributo `key` permite que o React identifique quais itens foram alterados, adicionados ou removidos.
* **Unicidade entre Irmãos**: As chaves não precisam ser únicas em todo o projeto, mas devem ser obrigatoriamente únicas entre elementos que compartilham o mesmo pai (elementos irmãos).
* **Uso do ID vs Index**:
* **ID (Recomendado)**: Utilizar o ID vindo do serviço (ex: `key={customer-${customer.id}}`) é a melhor prática para garantir unicidade e evitar problemas de performance.
* **Index (Alternativa)**: O índice do array (`index`) só deve ser usado se não houver um ID único disponível, como no caso da lista de `skills`.


---


## 🟩 Vídeo 10 - Manipulando Eventos

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-Video_10.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/introducao-ao-reactjs/learning/e8ade921-6274-4122-912a-45400b9eec99?autoplay=1

O vídeo aborda a **manipulação de eventos no React**, destacando que sua lógica é muito similar à do DOM tradicional, embora apresente diferenças sintáticas cruciais. O autor enfatiza que, no React, os eventos utilizam a nomenclatura **camelCase** e recebem **funções como manipuladores** em vez de strings. Através de exemplos práticos com botões e campos de entrada, é demonstrado como capturar informações através do objeto de evento e como utilizar o **onChange** para monitorar alterações. Além disso, a explicação detalha como **passar argumentos extras** para essas funções, o que facilita a identificação de itens específicos em uma lista, como ao deletar um cliente. Por fim, reforça-se a preferência pelo uso de **arrow functions** para manter o escopo léxico de forma simplificada.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-07-08h57m23s672.jpg" alt="" width="840">
</p>

### Introdução à Manipulação de Eventos no React

A manipulação de eventos em elementos React compartilha muitas semelhanças com a manipulação de eventos no DOM tradicional, porém, apresenta diferenças sintáticas cruciais para o desenvolvimento com a biblioteca. As duas principais regras de sintaxe são:

* **Nomenclatura camelCase:** Diferente do HTML puro, onde os eventos são escritos em letras minúsculas (como `onclick`), no React utiliza-se o padrão camelCase (como `onClick`).
* **Passagem de Funções:** No JSX, você deve passar uma função real como o manipulador de eventos, em vez de uma string contendo o código JavaScript.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-07-08h57m25s130.jpg" alt="" width="840">
</p>

### Diferenças Sintáticas e Práticas Recomendadas

Ao trabalhar com eventos no React, é importante observar como a biblioteca integra o JavaScript moderno para facilitar o controle do escopo e a performance:

* **Evitando Re-renderizações Desnecessárias:** Funções de manipulação podem ser definidas fora do componente principal para evitar que sejam recriadas a cada ciclo de renderização, a menos que dependam logicamente do escopo interno do componente.
* **Arrow Functions e Escopo:** O uso de *arrow functions* simplifica a manipulação de eventos, pois elas possuem escopo encapsulado. Isso elimina a necessidade frequente de utilizar o método `.bind()` para vincular o contexto de `this`, comum em componentes de classe.
* **Argumentos e Propagação:** É possível capturar o objeto de evento padrão (geralmente representado por `e` ou `event`) para acessar propriedades como `e.target.value` em inputs ou utilizar métodos como `e.preventDefault()` para controlar o comportamento padrão do navegador.

### Implementação Prática: Passagem de Argumentos

Abaixo, o exemplo demonstra como renderizar uma lista de clientes e associar um evento de clique a um botão de "Deletar". Note o uso de uma função anônima no `onClick` para permitir a passagem do `id` do cliente como argumento para a função `handleClick`.

```javascript
import React from "react";

const listCustomer = [
  {
    id: 1,
    name: "Bruno Carneiro",
    skills: ["React", "Node", "CSS", "Webpack"]
  },
  {
    id: 2,
    name: "Aline Carneiro",
    skills: ["HTML", "React Native", "Go", "JS"]
  },
  {
    id: 3,
    name: "Fulano de Tal",
    skills: ["Assembly"]
  },
  {
    id: 4,
    name: "José Ciclano",
    skills: ["Reason"]
  }
];

const App = () => {
  const handleClick = (e, id) => {
    console.log("deletar cliente");
    console.log(e.target);
    alert(`ID do cliente: ${id}`);
  };

  const renderCustomers = (customer, index) => {
    return (
      <div key={`customer-${customer.id}`}>
        <li>
          {customer.name}
          <button onClick={(e) => handleClick(e, customer.id)}>
            Deletar Cliente x
          </button>
        </li>
        {customer.skills.map(renderSkills)}
      </div>
    );
  };

  const renderSkills = (skill, index) => {
    return (
      <div style={{ paddingLeft: "30px" }} key={`skill-${index}`}>
        <li>{skill}</li>
      </div>
    );
  };

  return (
    <div>
      <p>Digital Innovation One</p>
      <p>Bem vindo a nossa aula =D.</p>
      <div>
        <ul>{listCustomer.map(renderCustomers)}</ul>
      </div>
    </div>
  );
};

export default App;

```

Nesta implementação, ao clicar no botão, a função `handleClick` recebe tanto o objeto de evento do navegador quanto o identificador único do cliente, permitindo ações específicas para aquele item da lista.

Abaixo estão os detalhes técnicos explicados durante a aula:

* **Nomenclatura CamelCase**: No código, observa-se o uso de `onClick` (linha 71) em vez do `onclick` do HTML tradicional, respeitando a convenção do React para eventos.

* **Passagem de Funções no JSX**: Ao contrário do JavaScript puro, onde se passa uma string, o código passa uma função como manipulador. No exemplo da linha 71, é utilizada uma *arrow function* `(e) => handleClick(e, customer.id)` para disparar a ação.

* **Objeto de Evento (`e`)**: A função `handleClick` recebe o argumento `e`, que representa o evento sintético do React. Através dele, é possível acessar propriedades como `e.target` (linha 65), que identifica o elemento que disparou o evento.

* **Passagem de Argumentos**: O código ilustra como passar dados extras (neste caso, o `id` do cliente) para o manipulador. Isso é feito chamando a função `handleClick(e, customer.id)` dentro do evento de clique, permitindo identificar exatamente qual cliente deve ser "deletado".

* **Renderização de Listas e Chaves**: Para cada cliente mapeado em `listCustomer`, o código utiliza uma `key` única baseada no ID (`key={customer.id}`) para garantir que o React gerencie corretamente as atualizações na interface.

* **Uso de Arrow Functions para Escopo**: A preferência por *arrow functions* no código (`const App = () => { ... }`) facilita o encapsulamento do escopo, evitando a necessidade de realizar o `bind` manual de funções, técnica comum em versões mais antigas do React com classes.


## 🟩 Vídeo 11 - Conheça dicas para pensar do jeito ReactJS

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-Video_11.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/introducao-ao-reactjs/learning/ff1ad283-52f1-4d04-8be6-d8de1b1c54d0?autoplay=1

O video apresenta diretrizes fundamentais para o desenvolvimento utilizando a filosofia de **pensar do jeito React**, priorizando a criação de interfaces modulares e organizadas. O autor recomenda iniciar o projeto com um **MOC de dados** e uma versão estática, progredindo para a divisão da aplicação em uma **hierarquia de componentes** menores e reutilizáveis. Um ponto central é a **abstração de componentes**, como botões genéricos sem lógica interna fixa, garantindo que cada parte do código possua uma **responsabilidade única**. A organização de pastas também é destacada, sugerindo o uso de **containers para as views** principais e diretórios específicos para elementos que podem ser compartilhados. Por fim, enfatiza-se a importância de manter o **estado da aplicação** no local correto, utilizando hooks para gerenciar o ciclo de vida de forma eficiente. Todo o conteúdo busca incentivar práticas que facilitem a **manutenção e a testabilidade** do software através da simplicidade.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-07-09h38m56s377.jpg" alt="" width="840">
</p>

Nesta introdução ao tópico "Pensando do Jeito React", o foco inicial é a organização do processo de desenvolvimento. A primeira recomendação fundamental é começar o projeto utilizando um **Mock**, que serve como uma base de dados simulada para facilitar a construção inicial da aplicação. Além disso, é essencial separar a interface do usuário em uma **hierarquia de componentes**, decompondo a aplicação em pequenos pedaços reutilizáveis e fáceis de gerenciar.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-07-09h38m57s842.jpg" alt="" width="840">
</p>

Dando continuidade às boas práticas de arquitetura em React, o desenvolvimento deve seguir etapas lógicas para garantir a escalabilidade:

* **Crie uma versão estática em React**: Desenvolva os componentes sem lógica complexa inicialmente, focando na renderização da UI.
* **Identifique a representação mínima do State da UI**: Determine o conjunto mínimo de dados mutáveis necessários para que a interface funcione.
* **Identifique onde o State deve ficar**: Defina qual componente deve ser o proprietário do estado com base na hierarquia.
* **Adicione o fluxo de dados inverso**: Implemente a comunicação de componentes filhos para componentes pais (via callbacks) para atualizar o estado.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-07-09h39m57s842.jpg" alt="" width="840">
</p>

Este trecho demonstra a aplicação prática da modularização. O componente principal importa um arquivo de **mock** e um componente de **Button** abstraído. A lógica de renderização é separada em funções auxiliares como `renderCustomers` e `renderSkills`, mantendo o retorno principal do componente limpo e focado na estrutura da página.

```javascript
import React from "react";
import mock from "../mock";
import Button from "../components/Button";

const App = () => {
  const handleClick = (id) => {
    console.log("deletar cliente");
    alert(`ID do cliente: ${id}`);
  }

  const renderCustomers = (customer, index) => {
    return (
      <div key={`customer-${customer.id}`}>
        <li>
          {customer.name}
          <Button onClick={() => handleClick(customer.id)}>
            Deletar o Cliente
          </Button>
        </li>
        {customer.skills.map(renderSkills)}
      </div>
    );
  };

  const renderSkills = (skill, index) => {
    return (
      <div style={{ paddingLeft: "30px" }} key={`skill-${index}`}>
        <li>{skill}</li>
      </div>
    );
  };

  return (
    <div>
      <p>Digital Innovation One</p>
      <p>Bem vindo a nossa aula =D.</p>
      <div>
        <ul>{mock.map(renderCustomers)}</ul>
      </div>
    </div>
  );
};

export default App;

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-07-09h40m57s842.jpg" alt="" width="840">
</p>

Para melhorar a clareza do código e separar as responsabilidades, os dados simulados são movidos para um arquivo dedicado de **mock**. Isso simula uma camada de serviço ou uma resposta de API, permitindo que o desenvolvedor trabalhe na interface e nos testes sem depender de um backend real no momento inicial.

```javascript
const mock = [
  {
    id: 1,
    name: 'Bruno Carneiro',
    skills: ['React', 'Node', 'CSS', 'Webpack']
  },
  {
    id: 2,
    name: 'Aline Carneiro',
    skills: ['HTML', 'React Native', 'Go', 'JS']
  },
  {
    id: 3,
    name: 'Fulano de Tal',
    skills: ['Assembly']
  },
  {
    id: 4,
    name: 'José Ciclano',
    skills: ['Reason']
  }
]

export default mock

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-07-09h41m57s842.jpg" alt="" width="840">
</p>

A abstração de componentes é exemplificada na criação de um botão genérico. Este componente é **stateless** (não possui estado interno) e utiliza a propriedade `children` para renderizar o conteúdo interno, além de receber uma função `onClick` via props. Essa abordagem evita que a lógica de negócio fique "amarrada" ao componente visual, permitindo que ele seja reutilizado em qualquer parte do projeto.

```javascript
import React from 'react'

const Button = ({ children, onClick }) => (
  <button onClick={onClick}>{children}</button>
)

export default Button

```

Conclua sua estrutura organizando arquivos em pastas como `containers` (para as views/rotas) e `components` (para elementos reutilizáveis), seguindo o princípio de **responsabilidade única** para facilitar a evolução e os testes do projeto.      


## 🟩 Vídeo 12 - Conceitos aplicados aos tipos de dados e condições da biblioteca

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-Video_12.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: 


# Certificado: Introdução ao ReactJS

- Link na plataforma: 
- Certificado em pdf: 
