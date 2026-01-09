## Instrutor

- Felipe Aguiar (DIO - Tech Educator)
- Contato Linkedin: / [felipeaguiar-exe](https://www.linkedin.com/in/felipeaguiar-exe/)

# Parte 1 - Trabalhando com States & Effects no ReactJs

## 🟩 Vídeo 01 - Visão geral do curso

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-curso.03-Video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/trabalhando-com-states-effects-no-reactjs/learning/97ef28ff-37d4-4618-b970-a0380e11de4a?autoplay=1 

Nesta introdução de curso, o instrutor Felipe Aguiar apresenta um guia focado no **comportamento lógico de componentes React**, priorizando a funcionalidade em vez da estética visual. O conteúdo central gira em torno dos **React Hooks**, especificamente no gerenciamento de **estados e efeitos** para criar interfaces inteligentes e reativas. Para acompanhar as aulas, o aluno deve possuir o ambiente de desenvolvimento configurado com **Node.js e VS Code**, além de dominar conceitos de **JavaScript moderno**, como desestruturação e funções. O autor enfatiza que compreender o que é um **componente funcional** é indispensável para progredir no aprendizado das ferramentas apresentadas. Ao longo das lições, os estudantes aprenderão a estruturar projetos e a aplicar o **lado funcional da biblioteca** para desenvolver aplicações robustas. O objetivo final é capacitar o desenvolvedor a manipular a **reatividade do framework** de forma profissional e eficiente.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-09-08h44m38s582.jpg" alt="" width="840">
</p>

Felipe Aguiar apresenta a introdução ao curso focado em **React Hooks**, destacando que o aprendizado será centrado em **componentes funcionais** e na lógica comportamental da aplicação. O objetivo principal é capacitar o desenvolvedor a criar componentes inteligentes através do gerenciamento de estados e efeitos, deixando a estilização visual (CSS) em segundo plano para priorizar a funcionalidade.

Para acompanhar o conteúdo, são estabelecidos os seguintes pré-requisitos técnicos e conceituais:

* **Ambiente:** Instalação do Node.js, npm e um editor de código (como VS Code).
* **Base de JavaScript:** Domínio de funções, desestruturação, sistemas de import/export e funções com retorno.
* **Conceito de Componente:** Compreensão de que um componente é uma função ou classe JavaScript capaz de retornar HTML.

O cronograma do curso abrange desde a organização inicial do projeto e arquivos até o uso avançado do **useState** para reatividade e do **useEffect** para o controle de efeitos colaterais na aplicação.

## 🟩 Vídeo 02 - O que são hooks

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-curso.03-Video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/trabalhando-com-states-effects-no-reactjs/learning/42e8f074-bb7f-4066-b940-d96ba1273d19?autoplay=1 

O vídeo apresenta o conceito fundamental de **Hooks no React**, definindo-os como **funções que se conectam aos componentes** para gerenciar estados e efeitos. O autor utiliza a metáfora de um **anzol de pesca** para ilustrar como essas ferramentas permanecem "enganchadas", garantindo que ações específicas sejam executadas sempre que o componente for atualizado. Além de citar exemplos práticos como o **useState e o useEffect**, o texto ressalta a importância de consultar a **documentação oficial** para compreender essa funcionalidade introduzida na versão 16.8 da biblioteca. A explicação destaca que o objetivo inicial é ensinar a **reatividade de forma correta**, comparando o uso de estados com métodos menos eficazes. Assim, os ganchos são estabelecidos como peças essenciais para que o desenvolvedor consiga **vincular comportamentos lógicos** ao ciclo de vida das interfaces.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-09-08h58m23s807.jpg" alt="" width="840">
</p>

Os **Hooks** (termo que significa "gancho" em inglês) são funções fundamentais que permitem "enganchar" funcionalidades aos componentes do React. A analogia visual do anzol ilustra perfeitamente esse conceito: assim como um peixe preso ao anzol reage quando a vara é puxada, o componente reage e executa ações sempre que sofre um clique ou uma atualização, justamente por estar conectado a um Hook. Essa estrutura permite o uso de estados e outros recursos de forma simplificada.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-09-08h59m38s281.jpg" alt="" width="840">
</p>

Introduzidos na versão 16.8 do React, os Hooks permitem gerenciar estados e outros recursos sem a necessidade de escrever classes. O exemplo de código abaixo demonstra a utilização do `useState`, o Hook de estado, para criar um contador simples onde a interface reage ao clique do usuário:

```javascript
import React, { useState } from 'react';

function Example() {
  // Declare uma nova variável de state, a qual chamaremos de "count"
  const [count, setCount] = useState(0);

  return (
    <div>
      <p>You clicked {count} times</p>
      <button onClick={() => setCount(count + 1)}>
        Click me
      </button>
    </div>
  );
}

```

Antes de avançarmos para as implementações complexas, é essencial compreender que o estado (`state`) é o que garante a reatividade correta dos componentes, permitindo que eles se atualizem visualmente sempre que houver uma alteração nos dados.      


## 🟩 Vídeo 03 - Criando seu projeto

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-curso.03-Video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/trabalhando-com-states-effects-no-reactjs/learning/74812597-ead3-4e7c-a360-edc01ab95b30?autoplay=1

O autor explica o conceito de **Hooks no React**, definindo-os como funções que se conectam aos componentes para simplificar o desenvolvimento e a atualização de interfaces. Para demonstrar a aplicação prática, o texto orienta a criação de um novo projeto utilizando a ferramenta **Create React App** via linha de comando. Durante o processo de instalação das dependências, são detalhadas as funcionalidades de arquivos essenciais, como o **package.json** e as folhas de estilo. O guia também destaca a importância de possuir uma base sólida em **JavaScript** para facilitar o aprendizado da biblioteca. Por fim, o conteúdo antecipa que a estrutura gerada será organizada seguindo as **boas práticas** do mercado para otimizar o fluxo de trabalho.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-09-09h27m29s585.jpg" alt="" width="840">
</p>

Para iniciar o desenvolvimento com Hooks, o primeiro passo é a criação do ambiente de trabalho. Utiliza-se o **Create React App**, uma ferramenta de *scaffolding* que agiliza o processo ao baixar automaticamente todas as dependências e templates fundamentais para um projeto inicial.

A criação é feita via terminal através do comando `npx`, garantindo que você utilize a versão mais recente do utilitário sem a necessidade de instalação global. É importante que o nome do projeto seja escrito inteiramente em letras minúsculas e, caso precise separar palavras, deve-se utilizar o hífen.

```bash
npx create-react-app aula-hooks

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-09-09h28m03s982.jpg" alt="" width="840">
</p>

Após a conclusão do processo de instalação, o React gera uma estrutura de pastas padronizada. Os principais arquivos e diretórios incluem:

* **public/**: Contém os arquivos estáticos acessíveis publicamente.
* **src/**: Onde reside o código-fonte da aplicação, incluindo o `App.js` (componente principal) e o `index.js` (ponto de entrada).
* **App.css / index.css**: Arquivos destinados à estilização dos componentes.
* **App.test.js / setupTests.js**: Estrutura configurada para a criação de testes automatizados utilizando Jest.
* **package.json**: Arquivo central de configuração que lista as dependências do projeto e define scripts de execução, como o comando `start`.
* **reportWebVitals.js**: Arquivo responsável por métricas de performance e saúde da aplicação web.

Essa organização segue os padrões convencionais do desenvolvimento *frontend*, onde cada arquivo JavaScript atua como um componente capaz de retornar blocos de HTML e CSS.


## 🟩 Vídeo 04 - 👨🏻‍💻 Organizando o seu projeto

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-curso.03-Video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/trabalhando-com-states-effects-no-reactjs/learning/87678a53-4135-42ab-b728-7249a14e70d6?autoplay=1

O vídeo apresenta um guia prático sobre como **organizar e otimizar a estrutura inicial de um projeto React**. O autor demonstra como **executar a aplicação via terminal** e explica o funcionamento do **hot reload**, que atualiza o navegador automaticamente após mudanças no código. O conteúdo foca na **limpeza de arquivos desnecessários** gerados pelo comando padrão e na criação de uma arquitetura baseada em **pastas de componentes**. É detalhada a importância do arquivo **index.js** como o ponto de entrada que injeta os elementos na **div root** do HTML. Por fim, o material define o que é um **componente funcional** e prepara o terreno para o ensino de **Hooks**.

### Anotações

Ao analisar a estrutura de pastas gerada pelo **Create React App**, identificamos o arquivo `public/index.html`. Este arquivo contém uma `<div id="root">`, que funciona como o "coração" da aplicação. Todos os componentes JavaScript e elementos HTML que construirmos serão injetados dinamicamente dentro desta div específica. Geralmente, não manipulamos este arquivo HTML diretamente, pois o React cuida dessa renderização.

Para manter o projeto limpo e focado no desenvolvimento com Hooks, removemos arquivos desnecessários gerados pelo template padrão. Foram deletados os arquivos de testes (`App.test.js` e `setupTests.js`) e o `reportWebVitals.js`. Após a exclusão, é necessário limpar as referências a esses arquivos no `index.js` para evitar erros de compilação, como demonstrado no terminal do editor.

Uma boa prática de organização consiste em criar uma pasta chamada `Components` dentro do diretório `src`. O objetivo é isolar cada componente em sua própria pasta. Neste exemplo, movemos os arquivos relacionados ao componente principal (`App.js`, `App.css` e o logo) para a subpasta 👉 `src/Components/App/`. Ao mover os arquivos, o editor pode auxiliar na atualização automática dos caminhos de importação.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-09-10h01m01s162.jpg" alt="" width="840">
</p>

Após a reestruturação das pastas, o arquivo `index.js` (o ponto de entrada principal) passa a importar o componente `App` do novo local. O `index.js` permanece na raiz da pasta `src`, servindo como a ponte entre o componente principal e o arquivo HTML físico.

```javascript
import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './Components/App/App';

ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById('root')
);

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-09-10h01m59s899.jpg" alt="" width="840">
</p>

Para finalizar a limpeza, simplificamos o componente `App.js`. Removemos o conteúdo padrão e o logo, deixando apenas um elemento `<h1>`. Um componente React, em sua forma mais pura, é uma função JavaScript que retorna elementos HTML (JSX). Esta estrutura limpa servirá de base para a criação dos nossos primeiros componentes e para o estudo das vantagens dos Hooks.

```javascript
import './App.css';

function App() {
  return (
    <div className="App">
      <h1>Hello World</h1>
    </div>
  );
}

export default App;

```

## 🟩 Vídeo 05 - Como criar um componente sem utilizar estados

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-curso.03-Video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/trabalhando-com-states-effects-no-reactjs/learning/a65bdeb8-eaf0-4006-96f9-86dc93dda6dc?autoplay=1

O instrutor explica como estruturar funções JavaScript que retornam **código HTML** e a importância de exportá-las para integração no projeto principal. Um ponto central da explicação é o uso de **fragments** para envolver múltiplos elementos sem adicionar tags desnecessárias ao navegador. A fonte detalha as dificuldades de atualizar manualmente a **interface do usuário** via manipulação direta do DOM quando variáveis internas sofrem alterações. Por fim, o conteúdo prepara o estudante para entender a necessidade dos **Hooks**, apresentando-os como uma solução inteligente para automatizar a renderização de dados.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-09-12h20m37s262.jpg" alt="" width="840">
</p>

Para compreender a necessidade dos Hooks, iniciamos a criação de um componente de forma tradicional. A estrutura do projeto no VS Code mostra a organização da pasta `aula-hooks`, onde criamos uma subpasta chamada `Counter` dentro de `components`. O arquivo principal do componente, `Counter.js`, é iniciado com letra maiúscula, seguindo as boas práticas do React. No terminal, o projeto está rodando localmente através do endereço `http://localhost:3000`.

```javascript
import React from 'react';

function Counter() {
  return (
    <>
      <h1>1</h1>
      <button>Aumentar</button>
    </>
  );
}

export default Counter;

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-09-12h20m59s284.jpg" alt="" width="840">
</p>

Nesta etapa, definimos a lógica inicial do contador. Criamos uma variável local chamada `quantity` com o valor inicial de `10` e a exibimos no HTML utilizando interpolação `{quantity}`. Também implementamos a função `upQuantity`, que incrementa o valor da variável e imprime o resultado no console para verificação. No navegador, a interface exibe o título "Hello World" (vindo do componente App) seguido pelo valor do contador e o botão.

```javascript
import React from 'react';

function Counter(){

  let quantity = 10;

  function upQuantity(){
    quantity = quantity + 1;
    console.log(quantity);
  }

  return(
    <>
      <h1>{quantity}</h1>
      <button onClick={upQuantity}>Aumentar</button>
    </>
  )
}

export default Counter;
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-09-12h21m11s303.jpg" alt="" width="840">
</p>

Ao testar o botão, percebemos que, embora a variável `quantity` aumente na memória (como mostrado no `console.log`), a interface do usuário não é atualizada automaticamente pelo React. Para resolver isso de forma "arcaica" (sem Hooks), adicionamos um `id="counter-box"` ao elemento `h1` e utilizamos manipulação direta do DOM com `document.getElementById` para forçar a atualização do conteúdo na tela sempre que a função `upQuantity` é disparada.

```javascript
import React from 'react';

function Counter(){
  let quantity = 10;

  function upQuantity(){
    quantity = quantity + 1;
    document.getElementById("counter-box").innerHTML = quantity;
    console.log(quantity);
  }

  return(
    <>
      <h1 id="counter-box">{quantity}</h1>
      <button onClick={upQuantity}>Aumentar</button>
    </>
  )
}

export default Counter;
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-09-12h21m16s944.jpg" alt="" width="840">
</p>

Aqui vemos o resultado da manipulação manual do DOM em funcionamento. O console do navegador mostra que o valor da variável subiu para `12` após cliques no botão, e graças à instrução `innerHTML`, o valor exibido na tela agora reflete esse estado. Este exemplo demonstra o trabalho explícito necessário para atualizar a interface quando não utilizamos as ferramentas nativas de estado do React, servindo de base para introduzirmos o conceito de Hooks.

🟡 ./Components/Counter/Counter.js

```javascript
import React from 'react';

function Counter(){
  let quantity = 10;

  function upQuantity(){
    quantity = quantity + 1;
    document.getElementById("counter-box").innerHTML = quantity;
    console.log(quantity);
  }

  return(
    <>
      <h1 id="counter-box">{quantity}</h1>
      <button onClick={upQuantity}>Aumentar</button>
    </>
  )
}

export default Counter;
```

🔴 App.js

```javascript
import './App.css';
import Counter from '../Counter/Counter';

function App() {
  return (
    <>
      <h1>Hello World</h1>
      <Counter />
    </>
  )
}

export default App;
```

O próximo passo será transformar essa lógica manual em um código mais profissional e inteligente utilizando Hooks. 

## 🟩 Vídeo 06 - Como criar um componente utilizando estados

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-curso.03-Video_06.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/trabalhando-com-states-effects-no-reactjs/learning/3f00bb5d-0ae0-4cc6-868e-f826d024a9b4?autoplay=1

Esta transcrição aborda o uso de **Hooks no React**, focando especificamente na implementação do **useState** para criar componentes com **estado e memória**. O instrutor explica a transição de um contador comum para um **Smart Counter**, demonstrando como abandonar a manipulação manual do DOM em favor de uma abordagem **reativa**. Ao utilizar essa ferramenta, o desenvolvedor obtém uma **variável de estado** e uma função vinculada que atualiza automaticamente a interface sempre que o valor muda. O conteúdo destaca que componentes **stateful** são mais eficientes, pois permitem que o sistema gerencie a renderização de forma **padronizada e inteligente**. Por fim, a aula reforça que os **Hooks** funcionam como ganchos que conectam funções a variáveis, facilitando o desenvolvimento de interfaces dinâmicas.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-09-12h56m22s957.jpg" alt="" width="840">
</p>

Nesta etapa, o ambiente de desenvolvimento no Visual Studio Code mostra a transição de um componente estático para um componente mais sofisticado. O objetivo é substituir o componente `Counter` manual por um novo chamado `SmartCounter`, que utilizará o poder dos **Hooks** do React para gerenciar seu próprio estado de forma padronizada.

O código inicial no arquivo `App.js` demonstra a importação do `SmartCounter` e sua utilização dentro do retorno da função `App`. Note que, ao utilizar editores modernos, a importação pode ocorrer de forma automática ao digitar o nome do componente como uma tag HTML.

```javascript
import './App.css';
import Counter from '../Counter/Counter';
import SmartCounter from '../Smartcounter/Smartcounter';

function App() {
  return (
    <>
      <h1>Hello World</h1>
      <SmartCounter/>
    </>
  );
}

export default App;

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-09-12h59m57s967.jpg" alt="" width="840">
</p>

Para que o componente tenha "memória", utilizamos o hook **useState**. Ele transforma um componente *stateless* (sem estado) em um componente *stateful* (com estado). O `useState` retorna um vetor contendo dois elementos: uma variável que armazena o valor atual e uma função específica para atualizar esse valor.

No exemplo do `SmartCounter`, a variável `quantity` guarda o estado e a função `upQuantity` é responsável por alterá-lo. Ao clicar no botão "Aumentar", a função é disparada, atualizando o valor na memória e forçando o React a refletir essa mudança automaticamente em todos os lugares onde a variável `quantity` estiver sendo exibida na tela.

🟡 ./Components/SmartCounter/SmartCounter.js

```javascript
import {useState} from 'react';

function SmartCounter(){
  const [quantity, upQuantity] = useState(1);

  return (
    <>
      <h1>{quantity}</h1>
      <button onClick={() => upQuantity(quantity + 1)}>Aumentar</button>
    </>
  )
}

export default SmartCounter;
```

🔴 App.js

```javascript
import './App.css';
import SmartCounter from '../SmartCounter/SmartCounter';

function App() {
  return (
    <>
      <h1>Hello World</h1>
      <SmartCounter />
    </>
  )
}

export default App;
```

## 🟩 Vídeo 07 - Criando um componente prático com estado

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-curso.03-Video_07.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/trabalhando-com-states-effects-no-reactjs/learning/e9f852c4-f0dc-4b99-8756-96846f37694a?autoplay=1

O vídeo consiste em uma **aula técnica** voltada para o ensino de **React hooks**, utilizando a criação de um contador inspirado no aplicativo iFood como exemplo prático. O instrutor detalha o processo de construção de um **componente funcional**, demonstrando como gerenciar estados através do hook `useState` para manipular valores numéricos. Além da lógica aritmética de somar e subtrair, o conteúdo foca em **boas práticas de programação**, como a nomenclatura de funções e a responsabilidade única. O tutorial também ensina a tornar o **CSS dinâmico**, alternando estilos visualmente para desabilitar botões quando o contador chega a zero. Por fim, reforça-se que o estado de um componente pode ser aplicado não apenas a dados visíveis, mas a qualquer propriedade lógica ou estética da interface.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-09-20h01m06s290.jpg" alt="" width="840">
</p>

A imagem acima apresenta o resultado visual inicial da aplicação React no navegador. É exibida a mensagem "Hello World" e a estrutura básica do componente de contador inspirado no iFood, composto por um botão de subtração, o número zero centralizado e um botão de adição. Este componente demonstra a aplicação prática de estados para criar interfaces dinâmicas que reagem às interações do usuário.

O arquivo CSS (abaixo) define a estilização do componente `iFoodCounter`, utilizando **Flexbox** para o alinhamento central e distribuição do espaço. O código estabelece classes específicas para alternar o estado visual dos botões entre ativo (vermelho) e desativado (cinza), alterando propriedades como cor e cursor conforme a lógica de negócio do contador.

🟡 ./Components/Ifoodcounter/Ifoodcounter.ss

```css
.countex-wrapper {
  display: flex;
  flex-direction: row;
  border: 3px solid rgb(192, 192, 192);
  border-radius: 4px;
  align-items: center;
  justify-content: space-between;
  height: 30px;
  width: 6%;
}

.counter-button-plus-active {
    border: none;
    background-color: transparent;
    height: 100%;
    font-size: larger;
    color: red;
    cursor: pointer;
}

.counter-button-minus-active {
    border: none;
    background-color: transparent;
    height: 100%;
    font-size: larger;
    color: red;
    cursor: pointer;
}

.counter-button-minus-desactive {
    border: none;
    background-color: transparent;
    height: 100%;
    font-size: larger;
    color: rgb(105, 105, 105);
    cursor: pointer;
}
```

O trecho abaixo apresenta a implementação lógica do componente funcional utilizando o hook `useState`. São definidos dois estados: `value`, para o valor numérico do contador, e `buttonStyle`, para controlar dinamicamente a classe CSS do botão de diminuir. As funções `up` e `down` encapsulam a lógica de atualização, garantindo que o valor não seja negativo e que a interface mude visualmente quando o contador chega a zero.

🟡 ./Components/Ifoodcounter/Ifoodcounter.js

```javascript
import { useState } from "react"
import '../Ifoodcounter/Ifoodcounter.css'

export default function Ifoodcounter() {

  const [value, setValue] = useState(1)
  const [buttonStyle, setButtonStyle] = useState("counter-button-minus-active")


  function down (){
    if (value <= 1) {
      setButtonStyle("counter-button-minus-inactive")
    }
    
    if (value > 0) { 
      setValue(value - 1)  
    }
  }

  function up (){
    setValue(value + 1)
    setButtonStyle("counter-button-minus-active")
  } 

  return (
    <div className="countex-wrapper">
      <button 
        className={buttonStyle}
        onClick={down}         
      >-</button >
      <p>{ value }</p>
      <button 
        className="counter-button-plus-active"
        onClick={up}
      >+</button>
    </div> 
  )
}
```

O código abaixo mostra a integração do componente `Ifoodcounter` dentro do componente principal `App`. Para que o contador seja renderizado na tela, ele é importado e declarado no JSX do arquivo `App.js`, permitindo que a aplicação exiba o título "Hello World" seguido pelo componente funcional desenvolvido.

🔴 ./Components/App/App.js

```javascript
import './App.css';
import SmartCounter from '../SmartCounter/SmartCounter';
import Ifoodcounter from '../Ifoodcounter/Ifoodcounter';

function App() {
  return (
    <>
      <h1>Hello World</h1>
      <Ifoodcounter />
    </>
  )
}

export default App;
```

## 🟩 Vídeo 08 - Trabalhando com efeitos colaterais (useEffect)


Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/trabalhando-com-states-effects-no-reactjs/learning/3676c554-2ade-4d2f-8879-cf5ac125d8dd?autoplay=1


## 🟩 Vídeo 09 - Conclusão do curso


Link do vídeo: 


## 🟩 Vídeo 10 - Trabalhando com States & Effects no ReactJs


Link do vídeo: 


# Certificado: Trabalhando com States & Effects no ReactJs

- Link na plataforma: 
- Certificado em pdf: 
