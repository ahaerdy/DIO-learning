## Instrutor

- Matheus De Amorim Benites (AI-Driven Full Stack Engineer | Co-Founder @Tamy )
- Contato Linkedin: / [omatheusbenites](https://www.linkedin.com/in/omatheusbenites/)


### 🟩 Vídeo 01 - Como usar os desafios de projeto para criar seu portfolio

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/tqi_fullstack-modulo.04-curso.06-video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/project/criando-um-front-end-totalmente-componentizado-na-pratica-com-reactjs/learning/00fbaa4a-f81c-4a9d-9005-8fbab6db02e2

O conteúdo aborda a importância e o funcionamento dos projetos práticos na plataforma DIO, destacando-os como ferramentas essenciais para o desenvolvimento de portfólios, exercício da criatividade e aceleração do aprendizado. Através de orientações de especialistas do mercado, os alunos são incentivados a ir além da simples reprodução de códigos, personalizando suas entregas para demonstrar competências reais e se destacar no mercado de desenvolvimento de software.


### 🟩 Vídeo 02 - Introdução

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/tqi_fullstack-modulo.04-curso.06-video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/criando-um-front-end-totalmente-componentizado-na-pratica-com-reactjs/learning/4700bb96-97ad-492a-9876-df9902aff174

Neste conteúdo, o engenheiro de software Mateus Benites apresenta uma abordagem prática para o desenvolvimento de aplicações React totalmente componentizadas. O foco central é o processo de tomada de decisão para a divisão de componentes, utilizando a analogia de "peças de Lego" para decompor e reconstruir interfaces complexas, visando uma estrutura de código mais organizada e eficiente.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-13-07h41m22s812.jpg" alt="" width="840">
</p>

Esta aula foca na criação prática de uma aplicação React totalmente componentizada. O objetivo principal é demonstrar o processo de tomada de decisão para a divisão de componentes e o fluxo de trabalho no desenvolvimento front-end. Para dar suporte ao aprendizado, o conteúdo é acompanhado por uma playlist no YouTube (bit.ly/papodedev), onde todo o desenvolvimento do projeto utilizado como exemplo é demonstrado passo a passo.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-13-07h41m29s437.jpg" alt="" width="840">
</p>

Apresentação do instrutor Matheus Benites, Software Engineer Front-end na Rock Content. Com um perfil voltado à resolução de problemas e movido por desafios técnicos, o instrutor compartilha sua trajetória pessoal e profissional, destacando sua motivação em participar de projetos de impacto global.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-13-07h41m36s458.jpg" alt="" width="840">
</p>

Canais de comunicação e redes sociais do instrutor para acompanhamento de conteúdos complementares:

* **Blog:** papode.dev
* **YouTube:** bit.ly/papodedev
* **LinkedIn:** [linkedin.com/in/benites-amorim/](https://www.google.com/search?q=https://linkedin.com/in/benites-amorim/)

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-13-07h41m42s161.jpg" alt="" width="840">
</p>

A aula está estruturada em três objetivos fundamentais para o domínio da componentização:

1. **Entendendo responsabilidades:** Identificar o papel de cada parte da aplicação.
2. **Transformando a interface em pequenas peças de Lego:** Decompor uma interface complexa em componentes menores e independentes.
3. **Remontando o nosso Lego em interface:** Unificar os componentes pequenos para reconstruir a interface completa e funcional.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-13-07h41m43s594.jpg" alt="" width="840">
</p>

Para o acompanhamento eficaz desta aula, são necessários os seguintes requisitos básicos:

* Conhecimentos fundamentais de **JavaScript**.
* Acesso a um **computador com internet**.
* **Força de vontade** para enfrentar as etapas do processo de desenvolvimento.      


### 🟩 Vídeo 03 - Entendendo Responsabilidade

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/tqi_fullstack-modulo.04-curso.06-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/criando-um-front-end-totalmente-componentizado-na-pratica-com-reactjs/learning/726b151f-5bd3-4370-a116-3c2138b31613

O vídeo aborda a importância da componentização no desenvolvimento front-end, com foco especial em React. O palestrante defende que, embora não existam regras rígidas, o consenso na comunidade é que componentes devem ter responsabilidades únicas e mínimas. Essa prática facilita a manutenção, a escalabilidade e a legibilidade do código, permitindo que a aplicação cresça de forma organizada, assemelhando-se à construção com "peças de Lego".

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-13-08h14m48s993.jpg" alt="" width="840">
</p>

No desenvolvimento com ReactJS, o conceito de responsabilidade é fundamental para a criação de sistemas escaláveis. Embora não exista uma regra rígida para a quebra de componentes, o consenso na comunidade de desenvolvimento é que quanto menor a responsabilidade de um componente, melhor será a manutenção, a legibilidade e a capacidade de expansão do código. O objetivo principal é alcançar o ideal de componentes com responsabilidades únicas.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-13-08h15m14s175.jpg" alt="" width="840">
</p>

Para ilustrar o conceito de responsabilidade única, o slide apresenta o componente `Profile`. Neste cenário, o componente possui uma única missão: renderizar os dados do perfil do usuário. Essa abordagem permite que o componente seja facilmente reutilizável em diferentes partes da aplicação, bastando fornecer os dados necessários para a renderização.

```javascript
const Profile = () => {
  const { githubState } = useGithub();

  return (
    <S.Wrapper>
      <S.WrapperImage src={githubState.user.avatar} alt="Avatar of user" />
      <S.WrapperInfoUser>
        <div>
          <h1>{githubState.user.name}</h1>
          <S.WrapperUserGeneric>
            <h3>Username:</h3>
            <a 
              href={githubState.user.html_url} 
              target="_blank" 
              rel="noreferrer"
            >
              {githubState.user.login}
            </a>
          </S.WrapperUserGeneric>
        </div>
      </S.WrapperInfoUser>
    </S.Wrapper>
  );
};

export default Profile;

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-13-08h16m16s168.jpg" alt="" width="840">
</p>

Neste exemplo, observa-se uma prática considerada inadequada para a componentização. O componente `Profile`, além de exibir os dados do usuário, também assume a responsabilidade de renderizar uma lista de repositórios. Ao acumular múltiplas funções, o componente torna-se mais complexo e difícil de manter, especialmente à medida que a equipe e o projeto crescem.

```javascript
const Profile = () => {
  const { githubState } = useGithub();

  return (
    <S.Wrapper>
      <S.WrapperImage src={githubState.user.avatar} alt="Avatar of user" />
      <S.WrapperInfoUser>
        <div>
          <h1>{githubState.user.name}</h1>
          <S.WrapperUserGeneric>
            <h3>Username: </h3>
            <a
              href={githubState.user.html_url}
              target="_blank"
              rel="noreferrer"
            >
              {githubState.user.login}
            </a>
          </S.WrapperUserGeneric>
        </div>
      </S.WrapperInfoUser>
      <Repositories>
        Lista de Repositórios aqui
      </Repositories>
    </S.Wrapper>
  );
};

export default Profile;

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-13-08h17m22s836.jpg" alt="" width="840">
</p>

*Conteúdo não identificado com segurança a partir do material disponível.*

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-13-08h18m19s423.jpg" alt="" width="840">
</p>

A decisão de componentizar uma aplicação front-end traz diversas vantagens estratégicas para o projeto:

* **Responsabilidade e Manutenção:** Permite dividir e desacoplar responsabilidades, facilitando correções e atualizações.
* **Reusabilidade:** Cria componentes que podem ser utilizados em diferentes contextos.
* **Extensibilidade:** Possibilita que componentes herdarem propriedades ou sejam estendidos.
* **Leitura de Código:** Resulta em um código mais limpo e legível, essencial para o trabalho em equipe e para a manutenção a longo prazo.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-13-08h21m25s698.jpg" alt="" width="840">
</p>

A imagem demonstra a arquitetura de divisão de responsabilidades da aplicação GitHub App. O sistema é organizado em blocos lógicos:

* **Profile:** Responsável por exibir dados do usuário, como o avatar, nome/username e contadores de status (seguidores, seguindo).
* **Repositories:** Focado na exibição de listas, dividindo-se entre a lista de repositórios gerais e a lista de repositórios favoritados (starred).

Essa estrutura permite que cada "peça de Lego" do front-end lide com seu próprio contexto sem gerar conflitos.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-13-08h22m48s336.jpg" alt="" width="840">
</p>

Na prática, a estrutura do React reflete a organização conceitual discutida. O componente principal `App` (ou Layout) atua como o container externo que envolve os componentes especializados `Profile` e `Repositories`.

```javascript
const App = () => {
  return (
    <Layout>
      <Profile />
      <Repositories />
    </Layout>
  );
};

export default App;

```      

### 🟩 Vídeo 04 - Transformando a interface em Lego

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/tqi_fullstack-modulo.04-curso.06-video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/criando-um-front-end-totalmente-componentizado-na-pratica-com-reactjs/learning/7fb0feb4-c982-4e67-a722-c3496ff7351a

O conteúdo aborda a importância estratégica de dividir interfaces em React em componentes menores e modulares, utilizando a analogia de peças de Lego. O foco central é a criação de códigos reutilizáveis e eficientes, evitando a duplicação e facilitando a manutenção, especialmente em projetos de grande escala e em ambientes de trabalho colaborativo.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-13-10h43m57s026.jpg" alt="" width="840">
</p>

A abordagem de desenvolvimento no React baseia-se na decomposição da interface em pequenos pedaços, comparáveis a peças de um brinquedo de montar (Lego). O objetivo é transformar a interface em componentes menores, onde cada um possui sua própria responsabilidade bem definida, permitindo que sejam remontados posteriormente para formar a aplicação completa.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-13-10h44m02s543.jpg" alt="" width="840">
</p>

O ponto central da componentização é a criação de códigos **reutilizáveis**. Ao evitar a duplicação de código, é possível desenvolver uma lógica que atenda a diversos cenários de maneira eficiente, garantindo que o esforço de desenvolvimento seja otimizado e o sistema se torne mais sustentável.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-13-10h44m31s454.jpg" alt="" width="840">
</p>

Em uma estrutura sem componentização, um componente principal como o `GitHub App` acumularia toda a lógica de renderização, incluindo o `Profile` e o `Repos`, em um único bloco. Em projetos de grande escala, com milhares de linhas e múltiplos desenvolvedores, essa falta de organização resultaria em arquivos gigantescos e frequentes conflitos de merge no Git, dificultando a manutenção e a escalabilidade do software.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-13-10h44m56s945.jpg" alt="" width="840">
</p>

Para organizar o código de forma efetiva, a estrutura recomendada isola as responsabilidades. O componente principal `GitHub App` contém um `Layout`, que por sua vez organiza os componentes core: `Profile` e `Repos`. Esta arquitetura garante que, se for necessário reutilizar o perfil em outra parte do sistema, o desenvolvedor tenha a certeza de que aquele componente possui uma única responsabilidade: renderizar os dados do usuário.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-13-10h45m04s728.jpg" alt="" width="840">
</p>

Nesta etapa, observamos a implementação do componente de layout, que serve como um container estrutural para os demais elementos da aplicação.

```javascript
import React from 'react';

const Layout = ({ children }) => {
  return (
    <header>
      {children}
    </header>
  );
};

export default Layout;

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-13-10h45m12s912.jpg" alt="" width="840">
</p>

A organização prossegue com a definição dos componentes específicos para o perfil e para os repositórios, mantendo a lógica de cada um isolada em seu próprio escopo.

```javascript
import React from "react";

const Profile = () => {
  return <div>Profile</div>;
};

export default Profile;

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-13-10h45m17s926.jpg" alt="" width="840">
</p>

Abaixo, a estrutura básica para a listagem de repositórios, completando a separação de interesses dentro da interface do GitHub App.

```javascript
import React from "react";

const Repositories = () => {
  return <div>Repositories</div>;
};

export default Repositories;

```      


### 🟩 Vídeo 05 - Transformando nosso lego em interface

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/tqi_fullstack-modulo.04-curso.06-video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/criando-um-front-end-totalmente-componentizado-na-pratica-com-reactjs/learning/2dcd3be2-fff0-4fe7-b38f-9cb8e20b9c19

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-13-13h50m43s835.jpg" alt="" width="840">
</p>

Esta imagem marca o início da terceira parte do curso, intitulada "Parte 3: Transformando nosso lego em interface". O foco desta etapa é a utilização do ReactJs para converter os componentes e a lógica desenvolvidos anteriormente em uma interface de usuário funcional e integrada.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-13-13h50m46s173.jpg" alt="" width="840">
</p>

Neste momento, a aula foca na remontagem da interface. O objetivo é começar a unir as peças ("legos") do projeto para construir a experiência visual final da aplicação, descrevendo este processo como a "parte divertida" do desenvolvimento.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-13-13h51m13s542.jpg" alt="" width="840">
</p>

A imagem apresenta o resultado visual esperado para a aplicação. Ela exibe o perfil de um usuário do GitHub com informações como nome, username, empresa, localização, blog e estatísticas (seguidores, seguindo, gists e repositórios), além de abas para alternar entre a visualização de "Repos" (repositórios) e "Starred" (favoritos).

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-13-13h51m41s787.jpg" alt="" width="840">
</p>

A imagem mostra a estrutura de pastas do projeto `GITHUB-API` no Visual Studio Code e o terminal onde a aplicação está sendo iniciada.

```bash
$ yarn start

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-13-13h52m00s044.jpg" alt="" width="840">
</p>

Aqui vemos o estado inicial da aplicação no navegador (`localhost`). A interface apresenta um campo de busca no topo e a mensagem "Nenhum usuário pesquisado" no corpo da página, indicando que a aplicação está pronta para realizar consultas à API do GitHub.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-13-13h52m07s838.jpg" alt="" width="840">
</p>

A imagem demonstra a aplicação em funcionamento após uma pesquisa. Os dados do usuário "benits" foram carregados, incluindo seu avatar, informações de perfil e a lista de repositórios exibida em cards abaixo das abas de navegação.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-13-13h52m12s037.jpg" alt="" width="840">
</p>

Esta imagem exibe o perfil real do usuário no GitHub para fins de comparação com os dados que serão consumidos pela aplicação. Estão em destaque informações como a bio, localização e links que serão mapeados para o estado da aplicação.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-13-13h53m24s154.jpg" alt="" width="840">
</p>

A imagem mostra o comando utilizado para criar o projeto React inicial através do terminal.

```bash
$ npx create-react-app github-app

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-13-13h54m13s655.jpg" alt="" width="840">
</p>

A imagem exibe o arquivo `package.json` com as dependências necessárias para o projeto, incluindo `axios` para as requisições HTTP, `styled-components` para estilização e `react-tabs` para a interface de abas.

```json
{
  "dependencies": {
    "@testing-library/jest-dom": "^5.11.4",
    "@testing-library/react": "^11.1.0",
    "@testing-library/user-event": "^12.1.10",
    "axios": "^0.21.1",
    "react": "^17.0.1",
    "react-dom": "^17.0.1",
    "react-scripts": "4.0.3",
    "react-tabs": "^3.2.1",
    "styled-components": "^5.2.1",
    "web-vitals": "^1.0.1"
  }
}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-13-13h54m50s452.jpg" alt="" width="840">
</p>

A imagem mostra a implementação do componente `Providers` no arquivo `providers.js`. Este componente é responsável por centralizar os contextos da aplicação, como o `GithubProvider`, e aplicar estilos globais como o `ResetCSS`.

```javascript
import React from "react";
import App from "./App";
import { ResetCSS } from "./global/resetCSS";
import GithubProvider from "./providers/github-provider";

const Providers = () => {
  return (
    <main>
      <GithubProvider>
        <ResetCSS />
        <App />
      </GithubProvider>
    </main>
  );
};

export default Providers;

```

O código foca na propriedade `user` dentro do estado do `GithubProvider`, que centraliza dados como `id`, `avatar`, `login`, `name`, `html_url` e `blog`, obtidos diretamente da API do GitHub para serem consumidos pelo componente de perfil.

### 🟩 Vídeo 06 - Criando componentes e explorando o React Hooks

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/tqi_fullstack-modulo.04-curso.06-video_06.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/criando-um-front-end-totalmente-componentizado-na-pratica-com-reactjs/learning/14bddee1-5c6e-4f61-b8b6-43962d5d165f

### 🟩 Vídeo 07 - Refinando ainda mais nossos componentes

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/tqi_fullstack-modulo.04-curso.06-video_07.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 08 - Dúvidas Parte 1

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/tqi_fullstack-modulo.04-curso.06-video_08.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 09 - Dúvidas Parte 2

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/tqi_fullstack-modulo.04-curso.06-video_09.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 10 - Conselhos para quem está começando com ReactJS

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/tqi_fullstack-modulo.04-curso.06-video_10.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 11 - Entendendo o Desafio

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/tqi_fullstack-modulo.04-curso.06-video_11.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:


##  Materiais de Apoio

# Certificado: 

- Link na plataforma: 
- Certificado em pdf: 
