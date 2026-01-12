## Instrutor

- Bruno Carneiro (Planning & Business Performance Director na Magazine Luiza)
- Contato Linkedin: / [brunohvcarneiro](https://www.linkedin.com/in/brunohvcarneiro/)

## Parte 1 - Aprofundando sobre o Ciclo de Vida do React

### 🟩 Vídeo 01 - Ciclo de Vida e suas fases

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/praticas-avancadas-em-projetos-com-reactjs/learning/8f7be828-9ebd-473c-a215-bf6f04ffea3d?autoplay=1

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-12-11h21m09s630.jpg" alt="" width="840">
</p>

Nesta aula de React JS Avançado, ministrada por Bruno Carneiro, o foco inicial recai sobre o entendimento profundo do ciclo de vida dos componentes, o uso de Hooks e a Context API. O domínio desses conceitos é fundamental para a transição do modelo de classes para o modelo funcional e para o uso eficiente dos novos recursos da biblioteca.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-12-11h21m14s562.jpg" alt="" width="840">
</p>

Para acompanhar este conteúdo avançado, são necessários os seguintes pré-requisitos:

* Conhecimentos obtidos nos cursos de Introdução e Intermediário de React JS.
* Domínio básico de JavaScript (mínimo ECMAScript 6).
* Ambiente de desenvolvimento configurado com Node.js (versão 12 recomendada) e NPM.
* Versão do React utilizada: 16.12.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-12-11h21m18s515.jpg" alt="" width="840">
</p>

O ciclo de vida do React é dividido em quatro fases principais:

1. **Inicialização:** O componente recebe suas propriedades (`props`) e define seu estado inicial (`state`).
2. **Montagem:** O componente é inserido no DOM.
3. **Atualização:** Ocorre quando propriedades ou estados são alterados, provocando uma nova renderização.
4. **Desmontagem:** Quando o componente é removido da árvore do DOM.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-12-11h21m22s068.jpg" alt="" width="840">
</p>

É importante destacar que, a partir da versão 17 do React, alguns métodos do ciclo de vida serão depreciados e removidos. São eles:

* `componentWillMount`
* `componentWillReceiveProps`
* `componentWillUpdate`

O uso desses métodos não é recomendado em novos projetos, devendo ser substituídos por alternativas mais modernas como `componentDidMount` ou hooks equivalentes.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-12-11h21m23s764.jpg" alt="" width="840">
</p>

Os métodos do ciclo de vida que permanecem essenciais para o desenvolvimento com classes incluem:

* **`componentDidMount`**: Executado após o componente estar pronto no DOM.
* **`componentDidUpdate`**: Executado após atualizações na árvore.
* **`componentWillUnmount`**: Utilizado para limpeza antes da destruição do componente.
* **`componentDidCatch`**: Para tratamento de erros e exceções.
* **`shouldComponentUpdate`**: Permite controlar se o componente deve ou não renderizar novamente por razões de performance.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-12-11h21m26s903.jpg" alt="" width="840">
</p>

Para demonstrar o ciclo de vida na prática, utilizamos uma estrutura de classe. No exemplo abaixo, o método `componentDidMount` é utilizado para disparar ações logo após a montagem do componente, enquanto o `render` define a estrutura visual:

```javascript
import React, { Component } from 'react';

class Twitter extends Component {
  componentDidMount() {
    console.log('componentDidMount');
  }

  render() {
    return (
      <div>
        Teste
      </div>
    );
  }
}

export default Twitter;

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-12-11h21m37s125.jpg" alt="" width="840">
</p>

O método `shouldComponentUpdate` é uma ferramenta poderosa para otimização. Ele recebe as próximas propriedades (`nextProps`) e o próximo estado (`nextState`), retornando um booleano que define se a renderização deve prosseguir:

```javascript
shouldComponentUpdate(nextProps, nextState) {
  return this.state.tweet !== nextState.tweet;
}

```

Além disso, o `componentWillUnmount` é fundamental para evitar vazamentos de memória (memory leaks), como limpar timers criados com `setInterval`:

```javascript
componentWillUnmount() {
  console.log('componentWillUnmount: componente removido');
}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-12-11h22m07s199.jpg" alt="" width="840">
</p>

Para iniciar o ambiente de desenvolvimento e visualizar as mensagens de log do ciclo de vida no console do navegador, utiliza-se o comando:

```bash
npm run start

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-12-11h22m21s385.jpg" alt="" width="840">
</p>

Caso seja necessário criar um novo projeto do zero com toda a configuração de Webpack e Babel automatizada, o comando recomendado é o `create-react-app` via NPX:

```bash
npx create-react-app advanced-reactjs-dio

```

### 🟩 Vídeo 02 - O que são Hooks e como ele pode otimizar o código

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/praticas-avancadas-em-projetos-com-reactjs/learning/a8522b88-3b01-4044-9138-ed0b49193b1e?autoplay=1

O vídeo consiste em uma **aula técnica** focada na transição do **React para o modelo de programação funcional** através do uso de **Hooks**. O instrutor explica como substituir a estrutura tradicional de **classes** por **funções**, destacando que essa abordagem torna o código mais legível, simples e elimina a necessidade do escopo **"this"**. Durante a explicação, são demonstradas as implementações práticas de hooks essenciais como **useState** para gerenciamento de estado e **useEffect** para controlar o **ciclo de vida** do componente. O conteúdo detalha como replicar comportamentos de montagem, atualização e desmontagem de componentes de forma modular e eficiente. Além disso, o autor aborda a otimização de performance com o uso de **memo** para evitar renderizações desnecessárias. Por fim, são reforçadas as **boas práticas**, recomendando que hooks sejam utilizados exclusivamente dentro de componentes React para garantir a ordem de execução correta.

### 🟩 Vídeo 03 - Context API

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/praticas-avancadas-em-projetos-com-reactjs/learning/ee284557-ca75-4902-9797-d9f483288cb1?autoplay=1

O vídeo apresenta um guia técnico sobre a implementação da **Context API** do React utilizando **Hooks**, destacando como essa ferramenta facilita o compartilhamento de estados globais. O autor demonstra a criação de um **Provider** na camada superior da aplicação para distribuir dados, como temas e tokens de autenticação, sem a necessidade de passar propriedades manualmente por componentes intermediários. Através de exemplos práticos com **useContext**, o conteúdo explica como os componentes filhos podem consumir valores e reagir a atualizações de estado de forma eficiente. Além de abordar o ciclo de vida e a renderização, o material compara essa abordagem com bibliotecas externas como **Redux** ou **MobX**. Por fim, o autor reforça que o uso de contextos globais deve ser feito com cautela para manter a **manutenibilidade do código** e evitar renderizações desnecessárias.

## Parte 2 - Técnicas com components e DOM

### 🟩 Vídeo 05 - Por que trabalhar com Fragments

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/praticas-avancadas-em-projetos-com-reactjs/learning/0f5903a8-0af9-4fc6-b665-f94c5d99520c?autoplay=1

O vídeo é uma aula de **React avançado** que foca no uso estratégico de **Fragments** para otimizar a estrutura do código e do **DOM**. O instrutor explica que o React exige que componentes retornem um **único elemento pai**, o que frequentemente leva à criação de **divs desnecessárias** que poluem o HTML final. Para resolver isso, os **Fragments** permitem agrupar múltiplos filhos sem adicionar nós extras, mantendo a árvore de renderização mais limpa e eficiente. O conteúdo demonstra diferentes formas de sintaxe, incluindo o **syntax sugar** e a necessidade de usar a nomenclatura completa ao lidar com **propriedades de chave (keys)** em listas. Por fim, o autor reforça que essa técnica é essencial para evitar problemas de estilização e para garantir um **desempenho melhor** em aplicações complexas.

### 🟩 Vídeo 06 - Error Boundaries

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/praticas-avancadas-em-projetos-com-reactjs/learning/8007118e-7c1b-49c9-8e9a-b504d547697c?autoplay=1

O vídeo aborda o funcionamento dos **Error Boundaries**, uma funcionalidade introduzida no **React 16** para evitar que falhas isoladas de JavaScript interrompam todo o funcionamento da interface. Essa ferramenta atua como um **componente wrapper**, capturando exceções em partes específicas da árvore de elementos e permitindo a exibição de uma **mensagem amigável** ou imagem de fallback ao usuário. O autor destaca que, ao contrário do tradicional bloco **try/catch**, essa API é projetada para a natureza declarativa do React, sendo capaz de registrar logs detalhados de erros para serviços externos. Além disso, é explicado que o React prefere **desmontar a árvore de componentes** corrompida para garantir a integridade dos dados e evitar comportamentos imprevisíveis na aplicação. Por fim, o material esclarece que certas situações, como **eventos assíncronos** ou falhas no próprio componente de erro, ainda exigem métodos convencionais de tratamento.

### 🟩 Vídeo 07 - Render Props

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/praticas-avancadas-em-projetos-com-reactjs/learning/77ea105d-a6a0-470b-947a-7ace45fc6461?autoplay=1

O vídeo consiste em uma **aula técnica** sobre o conceito de **Render Props** no desenvolvimento com **React**. O autor define essa estratégia como uma forma de **compartilhar lógica** entre componentes ao passar uma **função como propriedade**, permitindo que um componente receba dados e métodos sem implementá-los internamente. Para ilustrar a teoria, o instrutor demonstra a criação de um **componente wrapper de contador**, que gerencia estados de incremento e decremento para outros elementos filhos. O exemplo prático foca no **reaproveitamento de código**, comparando a abordagem ao funcionamento de bibliotecas famosas como **Formik** e **React Router**. Por fim, a fonte reforça que essa técnica é essencial para manter a aplicação modular e evitar a repetição desnecessária de funcionalidades complexas.

### 🟩 Vídeo 08 - Typechecking com PropTypes

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/praticas-avancadas-em-projetos-com-reactjs/learning/fc938d47-258a-40c3-9879-938d3ce7ed0d?autoplay=1

O conteúdo explica como utilizar a biblioteca **Prop Types** para realizar a checagem de tipos em aplicações **React**, servindo como uma alternativa nativa ao **TypeScript** ou **Flow**. O autor demonstra que, embora essa validação ocorra apenas durante o **tempo de execução**, ela é fundamental para identificar a ausência de propriedades obrigatórias ou o envio de dados incorretos. Através de exemplos práticos, como a criação de um componente de pet shop, o texto detalha como definir **tipos específicos** e configurar **valores padrão** via **default props**. Além de cobrir validações comuns para números, strings e funções, o material destaca a importância de garantir a **segurança do código** para evitar efeitos colaterais em produção. Por fim, o recurso incentiva a adoção dessas práticas para melhorar a **manutenção do projeto** e a experiência de desenvolvimento.

### 🟩 Vídeo 09 - Refs e DOM

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/praticas-avancadas-em-projetos-com-reactjs/learning/e94c09d4-4601-400e-b0b6-a4bd7311b0d0?autoplay=1

O conteúdo aborda o funcionamento do hook **useRef** no React, destacando sua capacidade de acessar diretamente elementos da **árvore do DOM**. O autor explica que essa ferramenta é essencial para gerenciar **focos de input**, manipular **bibliotecas de terceiros** e criar animações sem disparar novas renderizações. Diferente do estado tradicional, as alterações em uma referência não atualizam a interface, o que torna o recurso ideal para **armazenar valores anteriores** e realizar comparações lógicas eficientes. O tutorial demonstra na prática como capturar métodos nativos do JavaScript através da propriedade **current** do objeto de referência. Por fim, o material incentiva o uso dessa técnica para otimizar a performance ao evitar **re-renderizações desnecessárias** em componentes complexos.

## Parte 3 - Organizando o seu projeto

### 🟩 Vídeo 10 - O que é um Dumb Components

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/praticas-avancadas-em-projetos-com-reactjs/learning/ece08045-15d2-46f3-9836-31008fb94f93?autoplay=1

Nesta aula de **React JS avançado**, o instrutor Bruno Cardeira detalha as características fundamentais dos **Dumb Components**, também conhecidos como componentes de apresentação. Essas estruturas focam exclusivamente na **renderização da interface**, sendo totalmente **desacopladas** da lógica de negócios ou do gerenciamento de dados complexos da aplicação. Eles operam como **funções puras**, recebendo informações e funções de retorno estritamente por meio de **props** para garantir a reutilização do código. O autor ressalta que tais componentes raramente possuem estado interno, exceto quando necessário para controlar elementos visuais específicos, como um **menu suspenso ou calendário**. Exemplos práticos citados incluem botões e barras laterais, que servem apenas para exibir conteúdo e disparar ações externas sem processar mutações de dados.

### 🟩 Vídeo 11 - Como trabalhar com Dumb Components

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/praticas-avancadas-em-projetos-com-reactjs/learning/5dbe1d00-bf11-4d69-9f04-1712280ea0d8?autoplay=1

Este conteúdo técnico descreve a implementação de **Dumb Components** no desenvolvimento com React, destacando que sua principal característica é a **ausência de estado interno** e lógica de negócio. O autor utiliza exemplos práticos, como botões e calculadoras, para demonstrar que esses componentes devem apenas **receber propriedades e callbacks** de um componente pai para funcionar. Através dessa abordagem, os elementos tornam-se **totalmente desacoplados**, facilitando a reutilização em diferentes partes do sistema. A explicação reforça que a **inteligência da aplicação** deve residir nos containers, enquanto os componentes de apresentação focam exclusivamente na interface. Além disso, é enfatizada a importância do uso de **Prop Types** para garantir a integridade dos dados recebidos. O tutorial encerra incentivando a criação de componentes puros que apenas **renderizam informações externas** sem manipular dados de forma direta.

### 🟩 Vídeo 12 - E Smart Components?

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/praticas-avancadas-em-projetos-com-reactjs/learning/c1e02c65-1526-42c4-bfa3-04e650127447?autoplay=1

O vídeo aborda o conceito de **Smart Components** no desenvolvimento de software, diferenciando-os dos chamados **Dumb Components**, que são focados apenas na interface visual. O autor explica que os componentes inteligentes gerenciam o **comportamento da aplicação**, lidando com estados, regras de negócio e a comunicação com serviços externos ou sistemas de armazenamento de dados. Por meio de um exemplo prático de uma **galeria de fotos**, demonstra-se como esses elementos podem conter outros componentes para executar ações específicas, como a remoção de itens de uma lista. Embora sejam menos reutilizáveis devido ao seu **forte acoplamento**, eles são essenciais para organizar fluxos complexos e manter a lógica separada da apresentação. O conteúdo enfatiza que essa distinção facilita a **manutenção do código** e preserva a integridade do design do sistema. Por fim, recomenda-se o uso de técnicas como a **imutabilidade** e a otimização de renderização para melhorar o desempenho e a clareza da aplicação.

### 🟩 Vídeo 13 - Finalizando o projeto

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/praticas-avancadas-em-projetos-com-reactjs/learning/684f5f85-07f2-4f93-9eaf-91b86c61ce56?autoplay=1

O vídeo apresenta uma aula sobre **arquitetura e organização de pastas** em projetos desenvolvidos com a biblioteca **React**. O autor enfatiza que, por não ser um framework opinativo, o React exige que a equipe defina sua própria estrutura para garantir a **escalabilidade e manutenção** do código. A metodologia sugerida foca na separação de responsabilidades, distribuindo elementos como **componentes reutilizáveis, páginas (containers), serviços de API e rotas** em diretórios específicos. Além disso, destaca-se a importância de manter **testes e estilos** próximos aos seus respectivos arquivos para facilitar a gestão do projeto. O conteúdo é concluído com orientações sobre como lidar com **funções utilitárias puras** e a integração de estados globais de forma organizada.


# Certificado: Práticas avançadas em projetos com ReactJS

- Link na plataforma: https://hermes.dio.me/certificates/68QSK6HS.pdf
- Certificado em pdf: [Certificado-Praticas.avancadas.em.projetos.com.ReactJS.pdf](000-Midia_e_Anexos/Certificado-Praticas.avancadas.em.projetos.com.ReactJS.pdf)
