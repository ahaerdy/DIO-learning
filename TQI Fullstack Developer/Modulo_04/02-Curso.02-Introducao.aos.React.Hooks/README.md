## Instrutor

- Celso Henrique (Engineering Manager @ Stone)
- Contato Linkedin: / [devfrontend](https://www.linkedin.com/in/devfrontend/)

# Parte 1 - Introdução

## 🟩 Vídeo 01 - Apresentação do projeto

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-Curso.02-Video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/introducao-aos-react-hooks/learning/5cd612fb-c2bc-405f-bd15-c0a568ebcc1e?autoplay=1

Nesta apresentação, o engenheiro de software **Celso Henrique** introduz um workshop técnico voltado ao desenvolvimento **frontend** utilizando **React**. O instrutor demonstra a criação de um **gerador de frases** temático do Naruto, integrando uma **API simplificada** para exercitar conceitos de **programação assíncrona**. Além da interface visual, o projeto prioriza o ensino de **testes automatizados** e a estilização de componentes através de **CSS-in-JS**. Os participantes têm acesso a um repositório estruturado em etapas para facilitar o acompanhamento da lógica de programação e das **ferramentas modernas** de mercado. O objetivo central é capacitar desenvolvedores em práticas essenciais do cotidiano corporativo, unindo **teoria e aplicação prática**.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-08-09h36m37s945.jpg" alt="" width="840">
</p>

O instrutor Celso Henrique apresenta o repositório central do projeto no GitHub, intitulado **naruto-quotes-client**. Este repositório contém a estrutura de um projeto inicializado com `Create React App`, utilizando **JavaScript** e **HTML** como linguagens principais. O objetivo da aula é desenvolver um gerador de frases do Naruto, explorando o consumo de uma API externa para praticar conceitos de **programação assíncrona** e manipulação de estado no React.

No repositório, observam-se arquivos fundamentais como o `package.json` para gerenciamento de dependências e o `README.md` com as instruções iniciais de instalação e clonagem do projeto.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-08-09h36m54s837.jpg" alt="" width="840">
</p>

A imagem demonstra a versão final da aplicação em execução no ambiente local (`localhost:3000`). A interface apresenta uma estética temática com uma imagem do personagem Naruto Uzumaki, uma área de exibição de texto e o botão **Quote No Jutsu**.

O funcionamento básico consiste em:

* **Exibição de Frases:** Uma citação aleatória trazida da API (ex: "My name is Uzumaki Naruto...").
* **Interatividade:** Ao clicar no botão, a aplicação realiza uma nova requisição assíncrona ao servidor para atualizar a frase e o autor exibidos na tela.
* **Tecnologias Aplicadas:** Além do React Hooks para controle de estado, o projeto utiliza **CSS-in-JS** para estilização dos componentes e uma estrutura preparada para a implementação de **testes unitários**.


## 🟩 Vídeo 02 - Criando seu projeto

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-Curso.02-Video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/introducao-aos-react-hooks/learning/0bcd9fde-8c6e-4a50-b728-a1f679440225?autoplay=1

O instrutor explica a organização do **diretório de arquivos**, destacando a inclusão prévia de recursos como imagens, sons e bibliotecas para otimizar o tempo de aula. É dada ênfase ao uso do **Styled Components** para a criação de um estilo global e ao funcionamento do servidor de frases que utiliza Python. O tutorial orienta sobre a estrutura de **pastas e módulos JavaScript**, demonstrando como os componentes são exportados e importados no ecossistema do frontend. Por fim, o autor inicia a construção da **interface do usuário**, planejando a disposição visual entre textos e elementos gráficos na tela principal.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-08-10h06m45s501.jpg" alt="" width="840">
</p>

O instrutor inicia a exploração da estrutura de pastas do projeto através do terminal. Dentro do diretório principal `Naruto-quotes-live`, destaca-se a pasta **src**, que contém o código-fonte da aplicação. Além dos arquivos de configuração padrão como `package.json` e `yarn.lock`, o ambiente já conta com recursos pré-configurados, incluindo diretórios para imagens e sons que serão utilizados para personalizar a experiência do usuário com a temática de Naruto.

```bash
> cd src
ls
components
images
> cd images
bg.jpeg
naruto.png
sounds
pages

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-08-10h08m17s979.jpg" alt="" width="840">
</p>

Para a edição do código, é utilizado o **Emacs**, um editor de texto baseado em terminal. O instrutor demonstra a navegação pelos arquivos do projeto, focando na organização da pasta `src`, que centraliza os componentes, páginas e estilos globais da aplicação.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-08-10h08m22s590.jpg" alt="" width="840">
</p>

Ao abrir o arquivo `index.js`, observa-se a estrutura inicial do React. A principal customização inserida é a importação do **GlobalStyle** a partir da pasta de componentes e o componente **App** da pasta de páginas. O `ReactDOM.render` é configurado para envolver a aplicação com os estilos globais, garantindo que as definições de CSS se apliquem a todos os elementos renderizados.

```javascript
import React from 'react';
import ReactDOM from 'react-dom';
import { GlobalStyle } from './components';
import { App } from './pages';

ReactDOM.render(
  <React.StrictMode>
    <GlobalStyle />
    <App />
  </React.StrictMode>,
  document.getElementById('root')
);

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-08-10h08m54s654.jpg" alt="" width="840">
</p>

A estilização da aplicação é fundamentada na biblioteca **styled-components**, que permite a criação de "visual primitives" utilizando a sintaxe do ES6 e CSS diretamente no JavaScript. O instrutor acessa a documentação oficial para contextualizar como os estilos são aplicados de forma modular e eficiente no projeto.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-08-10h11m05s840.jpg" alt="" width="840">
</p>

No arquivo `GlobalStyle.js`, define-se a identidade visual da aplicação. Através da função `createGlobalStyle`, são estabelecidas as configurações do `body`, incluindo o reset de margens e preenchimento, a cor do texto e a importação da fonte **'New Tegomin'**, escolhida especificamente para remeter à estética de animes.

```javascript
import { createGlobalStyle } from 'styled-components';

export const GlobalStyle = createGlobalStyle`
  body {
    color: #332c36;
    padding: 0;
    margin: 0;
    font-family: 'New Tegomin', serif;
  }
`;

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-08-10h11m49s585.jpg" alt="" width="840">
</p>

O componente principal da aplicação, localizado em `App.js`, inicia com uma estrutura simplificada. Trata-se de uma função funcional que retorna um elemento de cabeçalho básico. Este arquivo servirá como o ponto central para a construção da interface, que futuramente dividirá o espaço entre as frases geradas pelo servidor e a imagem do personagem.

```javascript
export function App() {
  return <h1>Hello</h1>;
}

```      


## 🟩 Vídeo 03 - Criando sua primeira página e seus respectivos testes

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-Curso.02-Video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/introducao-aos-react-hooks/learning/564a2de4-1e02-4322-a065-cd35761ac225?autoplay=1


## 🟩 Vídeo 04 - Conhecendo o React Styled Components e criando um componente

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-Curso.02-Video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: 


## 🟩 Vídeo 05 - Evoluindo e testando nosso componente

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-Curso.02-Video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: 


## 🟩 Vídeo 06 - xxxxxxxxxxxxxxx



Link do vídeo: 


## 🟩 Vídeo 07 - xxxxxxxxxxxxxxx


Link do vídeo: 


## 🟩 Vídeo 08 - xxxxxxxxxxxxxxx


Link do vídeo: 


## 🟩 Vídeo 09 - xxxxxxxxxxxxxxx


Link do vídeo: 


## 🟩 Vídeo 10 - xxxxxxxxxxxxxxx


Link do vídeo: 


# Parte 2 - xxxxxxxxxxxxxxx

## 🟩 Vídeo 01 - xxxxxxxxxxxxxxx


Link do vídeo:  


## 🟩 Vídeo 02 - xxxxxxxxxxxxxxx


Link do vídeo: 


## 🟩 Vídeo 03 - xxxxxxxxxxxxxxx


Link do vídeo: 


## 🟩 Vídeo 04 - xxxxxxxxxxxxxxx


Link do vídeo: 


## 🟩 Vídeo 05 - xxxxxxxxxxxxxxx


Link do vídeo: 


## 🟩 Vídeo 06 - xxxxxxxxxxxxxxx


Link do vídeo: 


## 🟩 Vídeo 07 - xxxxxxxxxxxxxxx


Link do vídeo: 


## 🟩 Vídeo 08 - xxxxxxxxxxxxxxx


Link do vídeo: 


## 🟩 Vídeo 09 - xxxxxxxxxxxxxxx


Link do vídeo: 


## 🟩 Vídeo 10 - xxxxxxxxxxxxxxx


Link do vídeo: 


# Parte 3 - xxxxxxxxxxxxxxx

## 🟩 Vídeo 01 - Exxxxxxxxxxxxxxx


Link do vídeo:  


## 🟩 Vídeo 02 - xxxxxxxxxxxxxxx


Link do vídeo: 


## 🟩 Vídeo 03 - xxxxxxxxxxxxxxx


Link do vídeo: 


## 🟩 Vídeo 04 - xxxxxxxxxxxxxxx


Link do vídeo: 


## 🟩 Vídeo 05 - xxxxxxxxxxxxxxx


Link do vídeo: 


## 🟩 Vídeo 06 - xxxxxxxxxxxxxxx


Link do vídeo: 


## 🟩 Vídeo 07 - xxxxxxxxxxxxxxx


Link do vídeo: 


## 🟩 Vídeo 08 - xxxxxxxxxxxxxxx


Link do vídeo: 


## 🟩 Vídeo 09 - xxxxxxxxxxxxxxx


Link do vídeo: 


## 🟩 Vídeo 10 - xxxxxxxxxxxxxxx


Link do vídeo: 


##  Materiais de Apoio


# Certificado: 

- Link na plataforma: 
- Certificado em pdf: 
