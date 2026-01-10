## Instrutor

- Eduardo Gonçalves (Senior Software Engineer @Gupy | JavaScript Specialist | React | Node.js | Design System | AWS | SQL)
- Contato Linkedin: / [geceduardo](https://www.linkedin.com/in/geceduardo/)
- Github: https://github.com/eduardogc
- Repositório (fork): https://github.com/ahaerdy/fork-digital-one-react-intermediario-eduardogc


# Parte 1 - Trabalhando com estilos em elementos e componentes

## 🟩 Vídeo 01 - Introdução e objetivos da aula

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-curso.04-Video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>


Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/desenvolvimento-de-aplicacoes-para-internet-com-reactjs/learning/c6a6c9e3-c419-4443-9191-b3bf851d1ce4?autoplay=1  

Nesta introdução, o desenvolvedor Eduardo apresenta as diretrizes de um curso focado na criação de **aplicações web utilizando React JS**. O conteúdo programático da primeira aula abrange técnicas de **estilização com CSS**, a distinção entre **componentes funcionais e de estado**, além da manipulação de **formulários** na biblioteca. Para acompanhar o aprendizado, os alunos devem possuir conhecimentos prévios em **programação web** e ter ferramentas como o **Node.js** devidamente configuradas. O instrutor também ressalta a importância de saber utilizar o **GitHub** para acessar os materiais práticos disponibilizados. O objetivo final é capacitar os estudantes por meio de uma abordagem técnica estruturada e voltada para o **desenvolvimento frontend**.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-08h26m41s079.jpg" alt="" width="840">
</p>

Esta aula marca o início do curso de **Desenvolvimento de aplicações para internet com ReactJS**, ministrado por Eduardo Gonçalves Costa, desenvolvedor Front End na Tracksale. O foco inicial desta trilha de aprendizado será a exploração de como trabalhar com estilos, focando especificamente na aplicação de CSS em elementos e componentes dentro do ecossistema React.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-08h26m58s621.jpg" alt="" width="840">
</p>

A aula está estruturada em três objetivos principais:

1. **CSS componentes e elementos**: Onde serão abordadas três abordagens distintas para a estilização.
2. **Stateful vs. stateless**: Uma análise sobre a diferença entre esses tipos de componentes e o entendimento dos ciclos de vida no React.
3. **Formulários**: O aprendizado prático de como construir formulários utilizando a biblioteca.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-08h27m00s479.jpg" alt="" width="840">
</p>

Para acompanhar o conteúdo de forma eficiente, são necessários os seguintes requisitos básicos:

* Ter concluído os cursos de **Introdução ao ReactJS** e **Programação para internet com HTML5 e CSS3**.
* Possuir um **Editor de Texto** de sua preferência.
* Ter o **Node.js** e o **React** corretamente instalados no ambiente de desenvolvimento.
* Conhecimentos básicos em Git para clonar repositórios do GitHub e acessar os exemplos práticos.


## 🟩 Vídeo 02 - Aprenda sobre estilização de componentes e elementos CSS

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-curso.04-Video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/desenvolvimento-de-aplicacoes-para-internet-com-reactjs/learning/6f3d2411-f080-4160-9461-649e01ddd3ce?autoplay=1

O vídeo detalha as **três principais estratégias** para aplicar estilos em componentes **React**, começando pela abordagem **inline**. O autor destaca que, embora o estilo direto via atributos seja prático para testes rápidos, ele apresenta **dificuldades de manutenção** em projetos de grande escala. A segunda técnica envolve o uso de **classes CSS**, que oferece uma estrutura mais modular, porém exige atenção à nomenclatura **className** para evitar conflitos e manter a compatibilidade com o ecossistema. Por fim, o texto introduz o **CSS-in-JS**, exemplificado pela biblioteca **Styled Components**, como a solução mais flexível e moderna. Essa última modalidade é elogiada por permitir a **criação de estilos dinâmicos** através de propriedades e por otimizar a performance ao injetar automaticamente prefixos específicos para diferentes navegadores.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-08h47m13s682.jpg" alt="" width="840">
</p>

Esta aula inicia a abordagem sobre a estilização de componentes e elementos no desenvolvimento de aplicações para internet utilizando ReactJS. O foco principal é entender como gerenciar o CSS dentro do ecossistema de componentes do React.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-08h47m20s630.jpg" alt="" width="840">
</p>

Existem três maneiras principais de realizar a estilização em React que serão exploradas:

1. **Inline**: Estilos aplicados diretamente nos elementos via atributos.
2. **Classes**: Uso de classes CSS tradicionais importadas para o componente.
3. 
**CSS in JS**: Bibliotecas que permitem escrever CSS diretamente dentro do arquivo JavaScript.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-08h47m22s780.jpg" alt="" width="840">
</p>

A estilização **Inline** no React é feita passando um objeto JavaScript para o atributo `style` do elemento. Diferente do HTML convencional, as propriedades do CSS que possuem hífen (como `background-image`) devem ser escritas em *camelCase* (como `backgroundImage`).

```javascript
const divStyle = {
  color: 'blue',
  backgroundImage: 'url(' + imgUrl + ')'
};

function HelloWorldComponent() {
  return <div style={divStyle}>Hello World!</div>;
}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-08h47m27s996.jpg" alt="" width="840">
</p>

Também é possível passar estilos inline diretamente como propriedades (*props*) para outros componentes. Isso permite uma personalização rápida de instâncias específicas de um componente.

```javascript
function App() {
  return (
    <HelloWorld style={{ marginTop: '10px' }} />
  )
}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-08h47m31s432.jpg" alt="" width="840">
</p>

A utilização de estilos inline possui vantagens e desvantagens:

* **Prós**: É a maneira mais prática e direta de aplicar estilos, ideal para ajustes rápidos e testes de design.
* **Contras**: Apresenta grande dificuldade de manutenção à medida que a aplicação cresce, pois os estilos ficam espalhados pelo código.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-08h47m33s673.jpg" alt="" width="840">
</p>

A estilização por **Classes** utiliza arquivos CSS externos que são importados para o componente JavaScript. No JSX, como `class` é uma palavra reservada do JavaScript, utiliza-se o atributo `className` para definir as classes CSS nos elementos.

```css
/* HelloWorldComponent.css */
.div-style {
  color: blue;
  background: url('https://bit.ly/2L8xxHz');
}

```

```javascript
import './HelloWorldComponent.css';

function HelloWorldComponent() {
  return <div className="div-style">Hello World!</div>;
}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-08h47m37s568.jpg" alt="" width="840">
</p>

O uso de classes é uma maneira prática e direta, mas também possui pontos negativos relevantes:

* **Contras**: Além da difícil manutenção em larga escala, oferece pouca flexibilidade e pode gerar conflitos de nomes de classes entre diferentes componentes da aplicação.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-08h47m46s320.jpg" alt="" width="840">
</p>

O **CSS in JS** surge como uma alternativa para resolver os problemas das abordagens anteriores. Uma das bibliotecas mais populares para isso é o `styled-components`. Para utilizá-la, é necessário realizar a instalação via npm:

```bash
npm install --save styled-components

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-08h47m49s140.jpg" alt="" width="840">
</p>

Com o `styled-components`, criamos componentes que já possuem estilização atrelada a eles através de *Tagged Templates*. Isso permite, por exemplo, passar propriedades (*props*) para o CSS, tornando o estilo dinâmico e dependente do estado ou das propriedades do componente.

```javascript
import styled from 'styled-components';

const DivStyle = styled.div`
  color: blue;
  background: url('${props => props.imageUrl}');
`;

function HelloWorldComponent() {
  const url = 'https://bit.ly/2L8xxHz';
  return <DivStyle imageUrl={url}>Hello World</DivStyle>;
}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-08h47m55s893.jpg" alt="" width="840">
</p>

As principais vantagens do **CSS in JS** incluem:

* **Manutenção**: Facilidade para remover CSS não utilizado e criação de estilos dinâmicos baseados em props.
* **Performance**: Injeção automática de *vendor prefixes* (como `-webkit-`), o que garante compatibilidade entre navegadores sem esforço manual, além de eliminar a necessidade de pré-processadores externos.      


## 🟩 Vídeo 03 - Stateful vs Stateless

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-curso.04-Video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/desenvolvimento-de-aplicacoes-para-internet-com-reactjs/learning/42654499-4869-47ff-bb4c-8b0dfe5cfd18?autoplay=1

O vídeo explica a diferença fundamental entre componentes **stateful** e **stateless** no ecossistema **React**, focando em como cada um lida com o gerenciamento de dados. O autor descreve que, tradicionalmente, componentes baseados em **classes** gerenciam estados e ciclos de vida complexos, enquanto componentes **funcionais** eram usados apenas para exibição simples. Através de um exemplo de lista de compras, o texto demonstra a evolução para o uso de **Hooks**, que permite que funções também controlem estados de forma mais compacta. Com essa mudança tecnológica, a nomenclatura evoluiu para priorizar os termos **componentes de classe** e **componentes funcionais**. O conteúdo ressalta ainda que os estados são **imutáveis**, exigindo a criação de novas cópias a cada atualização para garantir a performance. Dessa forma, o material serve como um guia didático sobre a modernização das práticas de desenvolvimento de interfaces.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-10h52m38s469.jpg" alt="" width="840">
</p>

Nesta segunda parte da aula, o foco é a diferenciação entre **Stateful** e **Stateless** components no ecossistema React. A distinção fundamental entre ambos reside na forma como lidam com os dados internos e a lógica de estado da aplicação. Com a introdução dos **Hooks**, essa nomenclatura passou por adaptações necessárias para refletir as novas possibilidades de desenvolvimento.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-10h52m44s656.jpg" alt="" width="840">
</p>

As definições básicas para iniciar o estudo deste capítulo são:

* **Stateful:** Indica que o componente utiliza e gerencia estados internamente.
* **Stateless:** Indica que o componente não faz uso de estados, funcionando apenas para renderização.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-10h52m49s855.jpg" alt="" width="840">
</p>

Um componente **Stateful** caracteriza-se por possuir o gerenciamento de estado interno. Tradicionalmente, na arquitetura do React, esses componentes são construídos utilizando **classes** de JavaScript, permitindo o controle de dados dinâmicos que afetam o comportamento e a interface do componente ao longo do tempo.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-10h52m52s142.jpg" alt="" width="840">
</p>

O React oferece diversos métodos de **ciclo de vida** para controlar um componente Stateful desde sua criação até sua destruição. O fluxo divide-se em fases principais:

* **Inicialização (Initialization):** Configuração inicial de `props` (propriedades recebidas) e `states` (estados iniciais).
* **Montagem (Mounting):** Envolve o `componentWillMount` (antes da renderização), a fase de `render` e o `componentDidMount` (executado após a renderização inicial).
* **Atualização (Updation):** Controla as mudanças via props (`componentWillReceiveProps`, `shouldComponentUpdate`) ou via states, permitindo decidir se o componente deve ou não atualizar a interface.
* **Desmontagem (Unmounting):** Fase de destruição onde o `componentWillUnmount` é utilizado para limpezas de memória ou encerramento de processos persistidos.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-10h53m14s026.jpg" alt="" width="840">
</p>

Para ilustrar a aplicação prática de um componente Stateful, utiliza-se o exemplo de uma **lista de supermercado**. A interface apresenta uma lista de itens (como Tomate, Alface e Melancia) e controles interativos, como botões para adicionar novos produtos (ex: Abóbora) ou remover itens existentes. Esta interação exige que o componente armazene e atualize a lista dinamicamente através do estado.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-10h53m19s596.jpg" alt="" width="840">
</p>

A implementação da lista de supermercado utilizando classes envolve a inicialização do estado no construtor. É fundamental notar que os estados no React são **imutáveis**; para atualizar a lista, o estado anterior é destruído e uma nova cópia com os dados atualizados é criada para garantir a performance e a consistência da renderização.

```javascript
import React, { Component } from 'react';

class TodoListStatefull extends Component {
  constructor(props) {
    super(props);
    this.state = {
      items: [
        'Tomate',
        'Alface',
        'Melancia'
      ]
    }
  }
}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-10h53m25s568.jpg" alt="" width="840">
</p>

Para manipular os itens da lista, são definidas funções como `addItem`. No exemplo abaixo, utiliza-se o operador *spread* para garantir a imutabilidade, criando um novo array que contém os itens atuais mais o novo item inserido.

```javascript
addItem = (item) => {
  /* ...this.state.items, item é o mesmo que:
     let itemsUpdated = this.state.items;
     itemsUpdated.push(item);
     this.setState({ items: itemsUpdated }); 
  */
  this.setState({items: [...this.state.items, item]});
}

removeItem = () => {
  /* this.state.items.slice(1) é o mesmo que:
     let itemsUpdated = this.state.items;
     itemsUpdated.pop();
     this.setState({ items: itemsUpdated });
  */
  this.setState({ items: [...this.state.items.slice(1)] })
}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-10h53m30s615.jpg" alt="" width="840">
</p>

O método `render` do componente Stateful extrai os itens do estado e mapeia cada elemento para um item de lista (`<li>`). Os botões de interface disparam as funções de adição e remoção definidas anteriormente através de eventos de clique.

```javascript
render() {
  const { items } = this.state;
  return (
    <div className="bloco-lista">
      <p>Minha lista ClassName</p>
      <ul className="lista-estilizada">
        {items.map(item => <li>{item}</li>)}
      </ul>
      <button onClick={() => this.addItem('Abóbora')}>Adicionar item</button>
      <button onClick={() => this.removeItem()}>Remover item</button>
    </div>
  )
}

export default TodoListStatefull;

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-10h53m34s705.jpg" alt="" width="840">
</p>

Em contraste, o componente **Stateless** não possui gerenciamento de estados internos. Ele é construído utilizando **funções** simples em JavaScript (Functional Components). Este tipo de componente é ideal para renderizações puras, como ícones ou módulos de exibição que dependem apenas das `props` recebidas para mostrar informações, resultando em um código mais conciso.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-10h53m38s683.jpg" alt="" width="840">
</p>

A versão Stateless da lista de tarefas é significativamente mais curta. Ela recebe os `items` via desestruturação das propriedades e apenas renderiza a estrutura HTML. Não há lógica de alteração de estado (como `addItem` ou `removeItem`) dentro deste componente específico, pois ele atua apenas como uma camada de visualização.

```javascript
import React from 'react';

const TodoListStateless = ({items}) => (
  <div className="bloco-lista">
    <p>Minha lista Stateless</p>
    <ul className="lista-estilizada">
      {items.map(item => <li>{item}</li>)}
    </ul>
  </div>
);

export default TodoListStateless;

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-10h53m53s125.jpg" alt="" width="840">
</p>

Com a chegada do **Hooks**, tornou-se possível gerenciar estados dentro de componentes funcionais utilizando o `useState`. Esta abordagem combina a simplicidade das funções com o poder de gerenciamento de dados que antes era exclusivo das classes. O hook define um par contendo o estado atual e uma função para modificá-lo.

```javascript
import React, { useState } from 'react';

const TodoListFunctional = () => {
  const [items, setItems] = useState(['Tomate', 'Alface', 'Melancia']);

  const addItem = (item) => {
    setItems([...items, item]);
  }

  const removeItem = () => {
    setItems([...items.slice(1)]);
  }

  return (
    <div className="bloco-lista">
      <p>Minha Lista</p>
      <ul className="lista-estilizada">
        {items.map(item => <li>{item}</li>)}
      </ul>
      <button onClick={() => addItem('Abóbora')}>Add Item</button>
      <button onClick={() => removeItem()}>Remove Item</button>
    </div>
  );
}

export default TodoListFunctional;

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-10h53m57s781.jpg" alt="" width="840">
</p>

Devido à evolução tecnológica, a nomenclatura foi atualizada. Atualmente, a distinção mais precisa é entre **Class Components** e **Function Components**. Como os componentes funcionais agora podem manipular estados através de Hooks, eles deixaram de ser exclusivamente "stateless". Hoje, chamamos de Stateless apenas o componente funcional que não utiliza gerenciamento de estados.


## 🟩 Vídeo 04 - Introdução em Formulários no ReactJS

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-curso.04-Video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/desenvolvimento-de-aplicacoes-para-internet-com-reactjs/learning/5a44ad8b-f289-4c5f-887e-d4531a186852?autoplay=1

O vídeo consiste em uma aula técnica sobre a manipulação de **formulários em React**, detalhando como gerenciar o estado entre o **DOM** e a biblioteca. O conteúdo explora a diferença entre **componentes controlados**, onde o React é a única fonte de verdade, e **componentes não controlados**, que utilizam referências para acessar valores. São apresentados exemplos práticos envolvendo diversos elementos de entrada, como **inputs, selects e checkboxes**, além de estratégias para simplificar o código. O instrutor também aborda exceções, como campos de **upload de arquivos**, que exigem abordagens específicas por serem somente leitura. Por fim, o texto sugere o uso de bibliotecas externas como **Formic** para lidar com estruturas de dados mais complexas e robustas.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-12h54m55s154.jpg" alt="" width="840">
</p>

### Introdução aos Formulários no React

Os formulários são fundamentais para a interatividade em aplicações web. No ecossistema React, lidamos com uma particularidade: enquanto os elementos do DOM no HTML5 (como `<input>`, `<textarea>` e `<select>`) já mantêm naturalmente um estado interno, o React também busca controlar o estado da aplicação. Para harmonizar essas duas frentes, existem estratégias específicas para garantir que os dados inseridos pelo usuário sejam processados corretamente pela biblioteca.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-12h54m57s455.jpg" alt="" width="840">
</p>

### Componentes Controlados vs. Não Controlados

Existem duas formas principais de trabalhar com formulários no React:

1. **Componentes Controlados:** O React é a "fonte única da verdade" (*single source of truth*). O estado do componente controla o valor do input através do atributo `value` e as atualizações são feitas via eventos como o `onChange`.
2. **Componentes Não Controlados:** O estado é mantido pelo próprio DOM. O React acessa esses valores geralmente por meio de referências (`refs`) quando necessário, como no momento da submissão.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-12h55m01s186.jpg" alt="" width="840">
</p>

### Implementação de um Componente Controlado

Neste exemplo, transformamos um formulário simples em um *stateful component*. O valor do campo de texto é atrelado ao estado `value`, e qualquer alteração dispara a função `handleChange`, que atualiza o estado do React com os dados vindos do DOM (`event.target.value`).

```javascript
class NameForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {value: ''};

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    this.setState({value: event.target.value});
  }

  handleSubmit(event) {
    alert('Um nome foi enviado: ' + this.state.value);
    event.preventDefault();
  }

  render() {
    return (
      <form onSubmit={this.handleSubmit}>
        <label>
          Nome:
          <input type="text" value={this.state.value} onChange={this.handleChange} />
        </label>
        <input type="submit" value="Enviar" />
      </form>
    );
  }
}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-12h55m04s173.jpg" alt="" width="840">
</p>

### Manipulando Múltiplos Elementos

Diferentes elementos de formulário seguem o mesmo princípio de componentes controlados, mas com atributos levemente distintos:

* **Input e Textarea:** Utilizam o atributo `value`.
* **Select:** No React, o `value` é definido na tag `<select>` principal para determinar qual `<option>` está selecionada, simplificando o uso em relação ao HTML puro.
* **Checkbox e Radio Button:** Utilizam o atributo `checked` em vez de `value` para definir seu estado ativado/desativado.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-12h55m07s217.jpg" alt="" width="840">
</p>

### Exemplo: Formulário de Pedidos (Sorveteria)

Para gerenciar formulários mais complexos, como um pedido de sorvete com múltiplas opções, definimos estados iniciais para cada campo (sabor, casquinha, guardanapos).

```javascript
this.state = {
  sabor: 'uva',
  casquinha: true,
  guardanapos: 'sim'
};

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-12h55m11s003.jpg" alt="" width="840">
</p>

### Estrutura do Form com Select e Checkbox

O código abaixo demonstra a renderização do formulário de pedidos. Note que o `select` utiliza o `value` vindo do estado, e o `checkbox` utiliza a propriedade `checked`. Ambos compartilham uma função de manipulação chamada `handleInputChange`.

```javascript
<form onSubmit={this.handleSubmit} style={{display: 'flex', flexDirection: 'column'}}>
  <label>
    Escolha o sabor:
    <select name="sabor" value={this.state.sabor} onChange={this.handleInputChange}>
      <option value="uva">Uva</option>
      <option value="limao">Limão</option>
      <option value="coco">Coco</option>
      <option value="chocolate">Chocolate</option>
      <option value="morango">Morango</option>
    </select>
  </label>
  <label>
    <input
      name="casquinha"
      type="checkbox"
      checked={this.state.casquinha}
      onChange={this.handleInputChange} />
    Colocar na casquinha?
  </label>
</form>

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-12h55m29s477.jpg" alt="" width="840">
</p>

### Radio Buttons e Submissão

No caso dos Radio Buttons, é necessário definir tanto o `value` (valor fixo do campo) quanto o `checked` (comparação lógica com o estado atual) para que o React saiba qual opção exibir como selecionada.

```javascript
<label>
  <input
    type="radio"
    name="guardanapos"
    value="sim"
    checked={this.state.guardanapos === 'sim'}
    onChange={this.handleInputChange} />
  Sim
</label>
<label>
  <input
    type="radio"
    name="guardanapos"
    value="nao"
    checked={this.state.guardanapos === 'nao'}
    onChange={this.handleInputChange} />
  Não
</label>
<button type="submit">Enviar</button>

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-12h55m43s372.jpg" alt="" width="840">
</p>

### Processando o Envio dos Dados

A função `handleSubmit` é responsável por interceptar o evento de submissão do formulário. O `event.preventDefault()` evita que a página recarregue (comportamento padrão do HTML), permitindo que o React exiba os dados processados, como um alerta com as escolhas do usuário.

```javascript
handleSubmit(event) {
  const {sabor, casquinha, guardanapos} = this.state;
  alert(`Seu sabor escolhido foi ${sabor}, guardanapos ${guardanapos} e casquinha ${casquinha}`);
  event.preventDefault();
}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-12h55m51s374.jpg" alt="" width="840">
</p>

### Estratégia Universal: Handle Input Change

Para evitar a criação de uma função de alteração para cada campo, utilizamos uma abordagem genérica. Extraímos o `name`, o `type` e o valor (ou `checked` para checkboxes) do `event.target`. Usamos o atributo `name` do elemento HTML para atualizar dinamicamente a chave correspondente no estado.

```javascript
handleInputChange(event) {
  const target = event.target;
  const value = target.type === 'checkbox' ? target.checked : target.value;
  const name = target.name;

  this.setState({
    [name]: value
  });
}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-12h56m05s210.jpg" alt="" width="840">
</p>

### Componentes Não Controlados e Referências

Em situações onde não queremos (ou não podemos) controlar cada batida de tecla no estado do React, utilizamos componentes não controlados. Neles, os dados do formulário são gerenciados pelo próprio DOM. Para acessar esses valores, utilizamos o atributo `ref`.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-12h56m07s423.jpg" alt="" width="840">
</p>

### A Exceção: Input de Arquivo

Um caso obrigatório de uso de componente não controlado é o `<input type="file">`. No HTML5, este campo é estritamente de leitura (*read-only*). O React não consegue definir seu valor programaticamente por razões de segurança, portanto, devemos sempre usar referências para capturar o arquivo selecionado no momento da submissão.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-12h56m26s056.jpg" alt="" width="840">
</p>

### Implementação de Referência (createRef)

Para trabalhar com o input de arquivo, criamos uma referência no construtor da classe usando `React.createRef()`. No método `render`, atrelamos essa referência ao elemento através do atributo `ref`.

```javascript
class FileInput extends React.Component {
  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.fileInput = React.createRef();
  }

  handleSubmit(event) {
    event.preventDefault();
    alert(
      `Arquivo selecionado - ${this.fileInput.current.files[0].name}`
    );
  }

  render() {
    return (
      <form onSubmit={this.handleSubmit}>
        <label>
          Upload de arquivo:
          <input type="file" ref={this.fileInput} />
        </label>
        <br />
        <button type="submit">Enviar</button>
      </form>
    );
  }
}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-12h56m37s557.jpg" alt="" width="840">
</p>

### Comparativo: Controlados vs. Não Controlados

Embora o React recomende o uso de componentes controlados para a maioria dos casos, a escolha depende da necessidade de validação em tempo real ou da complexidade do formulário. Componentes controlados oferecem mais poder (validação instantânea, desativação condicional de botões), enquanto os não controlados podem ser mais simples para casos básicos ou integrações com bibliotecas externas de terceiros.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-12h56m39s429.jpg" alt="" width="840">
</p>

### Desafios e Verbosidade

Um ponto comum de discussão na comunidade é que lidar com formulários no React "puro" pode se tornar muito verboso. Escrever funções de manipulação, gerenciar cada estado e tratar submissões exige uma quantidade considerável de código repetitivo (*boilerplate*).

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-12h56m41s131.jpg" alt="" width="840">
</p>

### Bibliotecas de Terceiros

Para facilitar a gestão de formulários complexos e robustos, a comunidade desenvolveu bibliotecas especializadas. Duas das mais populares e recomendadas são:

* **Formik:** Atualmente a mais utilizada e recomendada pela própria documentação do React.
* **Redux Form:** Muito comum em projetos que utilizam Redux para gerenciamento de estado global.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-12h56m50s978.jpg" alt="" width="840">
</p>

### Bibliotecas Populares: Formik e Redux Form

A imagem destaca os logotipos e nomes das bibliotecas citadas: **Formik**, que foca em simplicidade e escalabilidade, e **Redux Form**, que integra o estado do formulário diretamente à store do Redux.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-12h56m52s764.jpg" alt="" width="840">
</p>

### Formik: A Recomendação Atual

O Formik destaca-se por resolver problemas de validação, tratamento de erros e submissão sem a necessidade de acoplar o estado do formulário ao Redux, tornando-o mais performático e fácil de implementar em componentes isolados.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-12h56m56s285.jpg" alt="" width="840">
</p>

### Conclusão e Recursos

Para encerrar a aula, é disponibilizado um repositório no GitHub contendo todos os exemplos práticos discutidos, incluindo implementações com componentes controlados, não controlados e um exemplo utilizando a biblioteca Formik. Os alunos são incentivados a explorar o código e praticar as diferentes abordagens.


## 🟩 Vídeo 05 - Trabalhando com estilos em elementos e componentes

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-curso.04-Video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/desenvolvimento-de-aplicacoes-para-internet-com-reactjs/learning/e606a7eb-aadf-4c59-9b47-81b02f0f0100?autoplay=1

Esta terceira aula de **React intermediário** foca no desenvolvimento de competências de **comunicação avançada** entre aplicações e servidores. O conteúdo explora a integração de **APIs REST** utilizando as bibliotecas mais relevantes do ecossistema atual. Os alunos aprenderão conceitos fundamentais de **imutabilidade** e como aplicá-los na gestão de estado global através do **Redux**. Além disso, o instrutor aborda o uso de **middlewares** para otimizar fluxos de dados complexos entre a interface e o backend. Para acompanhar o progresso, é indispensável possuir o ambiente de desenvolvimento configurado e ter concluído os módulos anteriores do curso.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-13h47m14s534.jpg" alt="" width="840">
</p>

Esta aula aborda o desenvolvimento de aplicações para internet com **ReactJS**, focando especificamente em **comunicação avançada entre aplicações**. O conteúdo explora tanto a integração da aplicação com servidores externos quanto a intercomunicação eficiente entre componentes utilizando **Redux**.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-13h47m19s419.jpg" alt="" width="840">
</p>

Os objetivos estabelecidos para esta etapa do aprendizado são:

* **Rest HTTP com React**: Estudo das principais bibliotecas utilizadas para comunicação com APIs.
* **Imutabilidade e Redux**: Compreensão dos conceitos de imutabilidade e sua importância fundamental dentro do ecossistema Redux.
* **Redux + Rest**: Integração de chamadas assíncronas com o estado global, abordando o uso de **Redux middlewares**.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-10-13h47m21s727.jpg" alt="" width="840">
</p>

Para o acompanhamento prático da aula, são necessários os seguintes requisitos:

* Conclusão prévia das aulas 1 e 2 do curso de React intermediário.


* Ambiente de desenvolvimento configurado com um editor de texto, **Node.js** e **React** instalados.


* Instalação da extensão **Redux DevTools** no navegador Google Chrome para depuração do estado da aplicação.      



# Parte 2 - Introdução ao Redux e fluxos da arquitetura com ReactJS

## 🟩 Vídeo 06 - Introdução aos conceitos da aula

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-curso.04-Video_06.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/desenvolvimento-de-aplicacoes-para-internet-com-reactjs/learning/a94f6436-850b-4a4f-9e4b-2ba7d77fcb39?autoplay=1 


## 🟩 Vídeo 07 - O que é Flux?

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-curso.04-Video_07.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: 


## 🟩 Vídeo 08 - Conheça o Redux e como trabalhar com ele

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-curso.04-Video_08.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: 


## 🟩 Vídeo 09 - Aprenda a prática de como desenvolver código com React e Redux

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-curso.04-Video_09.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: 



## 🟩 Vídeo 10 - Introdução ao Redux e fluxos da arquitetura com ReactJS

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-curso.04-Video_10.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: 


# Parte 3 - Comunicação avançada entre aplicações

## 🟩 Vídeo 11 - Introdução da aula

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-curso.04-Video_11.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo:  


## 🟩 Vídeo 12 - Rest HTTP com React

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-curso.04-Video_12.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: 


## 🟩 Vídeo 13 - Imutabilidade e Redux

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-curso.04-Video_13.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: 


## 🟩 Vídeo 14 - Redux + Rest

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-curso.04-Video_14.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: 


## 🟩 Vídeo 15 - Comunicação avançada entre aplicações

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_04-curso.04-Video_15.webm" type="video/webm">
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


##  Materiais de Apoio


# Certificado: 

- Link na plataforma: 
- Certificado em pdf: 
