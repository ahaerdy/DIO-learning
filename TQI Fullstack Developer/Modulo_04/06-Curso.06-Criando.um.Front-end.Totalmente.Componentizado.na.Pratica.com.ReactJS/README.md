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

### 🟩 Vídeo 05 - Transformando nosso lego em interface

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/tqi_fullstack-modulo.04-curso.06-video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 06 - Criando componentes e explorando o React Hooks

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/tqi_fullstack-modulo.04-curso.06-video_06.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

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
