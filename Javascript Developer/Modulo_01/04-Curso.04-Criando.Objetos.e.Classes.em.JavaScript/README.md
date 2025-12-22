## Instrutor:

- Renan Johannsen de Paula (Software Engineer Lead)
- Contato Linkedin: / [renanjpaula](https://www.linkedin.com/in/renanjpaula/)

# Parte 1 - Entendendo Objetos

## 🟩 Vídeo 01 - Estrutura Básica de Um Objeto

![[formacao_javascript_developer-modulo_01-Curso_04-Video_01.webm|vid-60]]

Link do vídeo:  https://web.dio.me/track/formacao-javascript-developer/course/criando-objetos-e-classes-em-javascript/learning/0048b76e-1d27-406a-9d29-fc808c2d9bae?autoplay=1

O instrutor explica que objetos funcionam como uma **coleção dinâmica de dados**, organizados através de um sistema de **chave e valor**. Durante a demonstração prática, é mostrado como declarar um **objeto literal** para agrupar informações relacionadas, como nome e idade de uma pessoa. O conteúdo destaca que elementos comuns da linguagem, como o comando de console, já operam como objetos contendo diversos **métodos internos**. Por fim, a aula ensina como **acessar propriedades específicas** ou exibir o conjunto completo de dados estruturados de uma variável.

### Anotações

Código:
```javascript
const vitor = {
    nome: 'Vitor J Guerra',
    idade: 25
};

console.log(vitor.nome);
console.log(vitor.idade);
console.log(vitor);
```

Saída:
```shell
Vitor J Guerra
25
{ nome: 'Vitor J Guerra', idade: 25 }
```

#### Entendendo Objetos em JavaScript

O vídeo apresenta um conceito fundamental do JavaScript: os **objetos**. Vamos analisar o código fornecido e compreender como ele se relaciona com os conceitos explicados na aula.

##### O que é um Objeto

Um objeto em JavaScript é uma **coleção dinâmica de dados** organizada em pares de **chave e valor**. Podemos imaginar um objeto como uma pasta de arquivos etiquetada, onde cada etiqueta representa uma chave (como "nome" ou "idade") e o documento dentro dela é o valor correspondente.

Na prática, objetos são estruturas que agrupam informações relacionadas sob um único nome de referência, tornando o código mais organizado e legível.

##### Anatomia do Código

No exemplo apresentado, temos a criação de um objeto literal chamado `vitor`:

```javascript
const vitor = {
  nome: 'Vitor J Guerra',
  idade: 25
};
```

##### Estrutura do Objeto Literal

A sintaxe do objeto literal segue um padrão específico:

**Declaração**: começa com `const vitor =` para criar uma variável constante que armazenará o objeto.

**Chaves**: o objeto é delimitado por chaves `{ }`, que marcam o início e o fim da coleção de dados.

**Pares chave-valor**: dentro das chaves, temos os pares de dados organizados como `chave: valor`. No exemplo, `nome` e `idade` são as chaves, enquanto `'Vitor J Guerra'` e `25` são os valores correspondentes.

**Separação**: cada par chave-valor é separado por vírgula, permitindo que múltiplas propriedades sejam definidas no mesmo objeto.

##### Acessando Propriedades do Objeto

O código demonstra três formas de acessar os dados do objeto usando a **notação de ponto**:

**Acessando o nome**: `console.log(vitor.nome)` imprime no console a string `'Vitor J Guerra'`.

**Acessando a idade**: `console.log(vitor.idade)` imprime no console o número `25`.

**Acessando o objeto completo**: `console.log(vitor)` imprime o objeto inteiro, mostrando toda a coleção de chave-valor: `{ nome: 'Vitor J Guerra', idade: 25 }`.

##### Por que Usar Objetos

Conforme explicado no vídeo, objetos são usados constantemente em sistemas JavaScript, muitas vezes sem que o programador iniciante perceba. O próprio `console` que utilizamos para imprimir valores é um objeto que possui vários **métodos** (funções associadas ao objeto), como o `log`.

Objetos permitem agrupar dados relacionados de forma lógica e acessível, facilitando a organização e manutenção do código. Em vez de criar variáveis separadas para nome e idade, podemos agrupá-las em um único objeto que representa uma entidade completa.

##### Objetos como Coleção Dinâmica

Um aspecto importante mencionado no vídeo é que objetos funcionam como uma **coleção dinâmica**. Isso significa que podemos adicionar, modificar ou remover propriedades do objeto conforme necessário durante a execução do programa, tornando-os extremamente flexíveis para representar estruturas de dados complexas.

## 🟩 Vídeo 02 - Editando Informações de Um Objeto

![[formacao_javascript_developer-modulo_01-Curso_04-Video_02.mkv|vid-60]]

Link do vídeo: https://web.dio.me/track/formacao-javascript-developer/course/criando-objetos-e-classes-em-javascript/learning/dfc8bd6d-551c-4581-be63-28b0003c981b?autoplay=1

O vídeo apresenta o conceito fundamental de **objetos no JavaScript**, definindo-os como **coleções dinâmicas de chave e valor**. Por meio de uma demonstração prática, explica-se que é possível **acrescentar novas propriedades** a um objeto já existente de maneira simples e imediata. Além da inserção, o conteúdo destaca a flexibilidade dessa estrutura ao demonstrar como **remover atributos** utilizando comandos específicos. O autor ressalta que essa característica mutável permite **editar a estrutura do dado** conforme a necessidade do programador durante a execução. Por fim, faz-se uma antecipação sobre como esse modelo se relaciona com outras **estruturas de dados complexas**, como o Map.

### Anotações

Código:
```javascript
const vitor = {
    nome: 'Vitor J Guerra',
    idade: 25
};

vitor.altura = 1.69;

/* delete vitor.nome; */*

console.log(vitor);
```

Saída:
```shell
{ nome: 'Vitor J Guerra', idade: 25, altura: 1.69 }
```

#### Objetos Dinâmicos em JavaScript

O vídeo demonstra uma característica fundamental dos objetos em JavaScript: sua **natureza dinâmica**. Vamos analisar o código fornecido e compreender como ele ilustra a capacidade de modificar objetos após sua criação.

##### Criação do Objeto Inicial

O código começa com a definição de um objeto literal básico:

```javascript
const vitor = {
  nome: 'Vitor J Guerra',
  idade: 25
};
```

Neste momento, o objeto `vitor` possui apenas duas **propriedades**: `nome` (com valor `'Vitor J Guerra'`) e `idade` (com valor `25`). Cada propriedade é um par chave-valor. Essa é a estrutura inicial, mas como veremos a seguir, não é definitiva.

##### Adicionando Propriedades Dinamicamente

Uma das características mais poderosas dos objetos em JavaScript é a capacidade de **adicionar novas propriedades após a criação**. No código, isso é demonstrado com:

```javascript
vitor.altura = 1.69;
```

Aqui, estamos criando uma nova propriedade chamada `altura` e atribuindo o valor `1.69` a ela. **Não foi necessário declarar essa propriedade na definição inicial do objeto**. Simplesmente acessamos o objeto com a notação de ponto e atribuímos um novo par chave-valor, e o JavaScript automaticamente cria essa propriedade.

Isso significa que você pode:

**Criar objetos vazios** e adicionar propriedades posteriormente:
```javascript
const pessoa = {};
pessoa.nome = 'João';
pessoa.idade = 30;
```

**Criar objetos com algumas propriedades** e adicionar outras quando necessário:
```javascript
const pessoa = { nome: 'João' };
pessoa.idade = 30;
pessoa.cidade = 'São Paulo';
```

Após a operação demonstrada no código, o objeto `vitor` passa a ter três propriedades: `nome`, `idade` e `altura`.

##### Removendo Propriedades Dinamicamente

O vídeo também explica que podemos **remover propriedades existentes** de um objeto. No código, há uma linha comentada que demonstra isso:

```javascript
/* delete vitor.nome; */*
```

O operador `delete` é usado para remover completamente uma propriedade de um objeto. Se essa linha fosse executada (sem o comentário), a propriedade `nome` (o par chave-valor completo) seria removida do objeto `vitor`, e ele passaria a conter apenas as propriedades `idade` e `altura`.

É importante notar que `delete` não apenas limpa o valor da propriedade, mas remove a propriedade inteira do objeto. A chave e o valor deixam de existir.

##### Visualizando as Mudanças

Por fim, o código imprime o objeto no console:

```javascript
console.log(vitor);
```

Dependendo de quais operações foram executadas, o resultado será diferente:

**Sem o delete**: o objeto será exibido com as três propriedades `{ nome: 'Vitor J Guerra', idade: 25, altura: 1.69 }`.

**Com o delete**: o objeto seria exibido sem a propriedade nome `{ idade: 25, altura: 1.69 }`.

##### O Conceito de Coleção Dinâmica

Conforme enfatizado no vídeo, um objeto em JavaScript é uma **coleção dinâmica de chave e valor**. Isso significa que:

**Flexibilidade total**: podemos modificar a estrutura do objeto durante a execução do programa, adicionando ou removendo propriedades conforme necessário, sem precisar declará-las antecipadamente.

**Chave-Valor**: cada propriedade é sempre um par de chave (o nome da propriedade) e valor (o dado armazenado nela).

**Criação sob demanda**: as propriedades são criadas automaticamente no momento em que você as atribui, mesmo que não existissem na definição original do objeto.

**Diferente de linguagens tipadas**: ao contrário de linguagens com tipagem estática, não precisamos declarar antecipadamente todas as propriedades que um objeto terá. Você pode começar com um objeto vazio `{}` e ir adicionando propriedades conforme necessário.

##### Conexão com Estruturas de Dados Avançadas

O vídeo menciona que esse conceito de chave-valor é fundamental e serve como base para estruturas de dados mais avançadas, como o **Map**. Entender como objetos funcionam dinamicamente prepara o terreno para compreender essas estruturas mais complexas no futuro.

##### Analogia da Mochila Mágica

Para facilitar a compreensão, podemos imaginar um objeto JavaScript como uma **mochila mágica**: você pode colocar novos itens etiquetados nela a qualquer momento (adicionar propriedades) ou retirar o que não precisa mais (remover propriedades), e ela se ajusta instantaneamente para acomodar o que estiver lá dentro. Cada item na mochila tem uma etiqueta (a chave) e o conteúdo (o valor), formando uma propriedade completa. Não há necessidade de esvaziar a mochila inteira para reorganizá-la.

##### Comparação com Python

Se você conhece Python, é importante entender que os **objetos em JavaScript** servem para dois propósitos que em Python são separados:

**Para dados simples em chave-valor**: os objetos JavaScript funcionam como **dicionários (dict)** em Python.

**Para programação orientada a objetos**: os objetos JavaScript também funcionam como **instâncias de classes** em Python.

No exemplo do vídeo, estamos usando objetos JavaScript da primeira forma - como uma estrutura de dados simples para armazenar informações. Em Python, usaríamos um **dicionário** para essa finalidade, não uma classe.

**Criando a estrutura inicial:**

Em JavaScript (objeto):
```javascript
const vitor = {
  nome: 'Vitor J Guerra',
  idade: 25
};
```

Em Python (dicionário):
```python
vitor = {
  'nome': 'Vitor J Guerra',
  'idade': 25
}
```

**Adicionando propriedades/chaves dinamicamente:**

Em JavaScript:
```javascript
vitor.altura = 1.69;
// ou
vitor['altura'] = 1.69;
```

Em Python (dicionário):
```python
vitor['altura'] = 1.69
```

**Removendo propriedades/chaves:**

Em JavaScript:
```javascript
delete vitor.nome;
```

Em Python (dicionário):
```python
del vitor['nome']
```

**Acessando valores:**

Em JavaScript:
```javascript
console.log(vitor.nome);
// ou
console.log(vitor['nome']);
```

Em Python (dicionário):
```python
print(vitor['nome'])
```

**E se quiséssemos usar classes em Python?**

Python também permite adicionar atributos dinamicamente a objetos de classes:

```python
class Pessoa:
    def __init__(self, nome, idade):
        self.nome = nome
        self.idade = idade

vitor = Pessoa('Vitor J Guerra', 25)
vitor.altura = 1.69  # Adiciona atributo dinamicamente
del vitor.nome  # Remove atributo
```

Porém, para o caso simples mostrado no vídeo (apenas agrupar dados), **dicionários são mais apropriados em Python**, enquanto em JavaScript usamos objetos para ambas as situações.

**Principais diferenças entre JavaScript e Python:**

**Separação de conceitos**: Python separa claramente dicionários (dados) de classes (objetos com comportamento). JavaScript usa objetos para ambos os propósitos.

**Notação de ponto**: JavaScript permite acessar propriedades de objetos usando a notação de ponto (`vitor.nome`). Em Python, isso só funciona com atributos de objetos de classes, não com dicionários.

**Sintaxe das chaves**: Em Python, as chaves de dicionários são declaradas explicitamente como strings (`'nome'`, `'idade'`). Em JavaScript, ao usar objetos literais, as chaves são automaticamente tratadas como strings, mesmo sem aspas.

**Flexibilidade**: JavaScript foi projetado para usar objetos como estrutura universal, tornando-os extremamente flexíveis. Python prefere estruturas específicas para propósitos específicos (dict para dados, classes para objetos).

Apesar dessas diferenças, o conceito fundamental permanece: tanto objetos JavaScript quanto dicionários Python são **coleções dinâmicas de chave-valor** que permitem adicionar, modificar e remover pares de dados durante a execução do programa.

## 🟩 Vídeo 03 - Criando Métodos Para o Objeto

![[formacao_javascript_developer-modulo_01-Curso_04-Video_03.webm|vid-60]]

Link do vídeo: https://web.dio.me/track/formacao-javascript-developer/course/criando-objetos-e-classes-em-javascript/learning/32e75b24-c549-4338-af73-98a9bbc54dcb?autoplay=1

O vídeo apresenta o conceito de **métodos em JavaScript**, explicando como **funções** podem ser integradas a **objetos** para realizar ações específicas. O instrutor demonstra que essas funções internas permitem o uso da palavra reservada **"this"**, que serve para acessar e manipular as **propriedades** do próprio objeto no contexto atual. Através de exemplos práticos, ilustra-se como um objeto funciona como uma **estrutura dinâmica** de chave e valor, capaz de armazenar tanto dados quanto comportamentos. A lição destaca a flexibilidade da linguagem ao mostrar que é possível **sobrescrever** essas funções ou aplicá-las a diferentes instâncias para gerar resultados distintos. Em suma, o material foca na interação entre **lógica e dados** dentro da programação orientada a objetos.

### Anotações

Código:
```javascript
const pessoa = {
  nome: 'Vitor J Guerra',
  idade: 25,
  descrever: function () {
    console.log(`Meu nome é ${this.nome} e minha idade é ${this.idade}`);
  }
};

/*
pessoa.descrever = function() {
    console.log(`Meu nome é ${this.nome}`);
} */

pessoa.nome = 'Renan';
pessoa.idade = 30;

pessoa.descrever();
```

Saída:
```shell
Meu nome é Renan e minha idade é 30
```

#### Métodos e o Contexto This em Objetos JavaScript

O vídeo avança para um conceito essencial dos objetos em JavaScript: a capacidade de armazenar **funções como propriedades**, criando o que chamamos de **métodos**. Vamos analisar o código fornecido e compreender como funções se comportam dentro de objetos.

##### O que são Métodos

Quando uma função é definida como propriedade de um objeto, ela recebe um nome especial: **método**. Um método é simplesmente uma função que pertence a um objeto e pode acessar e manipular as outras propriedades desse objeto.

No código, vemos a criação de um objeto `pessoa` com um método chamado `descrever`:

```javascript
const pessoa = {
  nome: 'Vitor J Guerra',
  idade: 25,
  descrever: function () {
    console.log(`Meu nome é ${this.nome} e minha idade é ${this.idade}`);
  }
};
```

O objeto `pessoa` possui três propriedades: duas armazenam dados simples (`nome` e `idade`) e uma armazena uma função (`descrever`).

##### A Palavra Reservada This

A parte mais importante deste conceito é entender o **`this`**. Esta é uma palavra reservada do JavaScript que se refere ao **contexto** onde a função está sendo executada.

Quando um método é executado dentro de um objeto, o `this` assume o valor do próprio objeto. Isso significa que `this.nome` acessa a propriedade `nome` do objeto `pessoa`, e `this.idade` acessa a propriedade `idade`.

No código do método `descrever`:

```javascript
console.log(`Meu nome é ${this.nome} e minha idade é ${this.idade}`);
```

O `this` está se referindo ao objeto `pessoa`. Portanto:

**`this.nome`** é equivalente a `pessoa.nome`, retornando `'Vitor J Guerra'`

**`this.idade`** é equivalente a `pessoa.idade`, retornando `25`

##### Invocando o Método

Para executar um método, usamos a notação de ponto seguida de parênteses:

```javascript
pessoa.descrever();
```

Quando essa linha é executada, o console imprime: `"Meu nome é Vitor J Guerra e minha idade é 25"`.

##### Modificando Propriedades e Reutilizando Métodos

Uma das grandes vantagens dos métodos é que eles se adaptam automaticamente às propriedades do objeto. O código demonstra isso alterando as propriedades:

```javascript
pessoa.nome = 'Renan';
pessoa.idade = 30;

pessoa.descrever();
```

Agora, quando o método `descrever()` é invocado novamente, ele imprime: `"Meu nome é Renan e minha idade é 30"`.

O mesmo método produziu resultados diferentes porque o `this` sempre se refere ao estado atual do objeto. Não foi necessário reescrever o método - ele automaticamente acessa os valores atualizados das propriedades.

##### Sobrescrevendo Métodos Dinamicamente

O vídeo também mostra que, assim como qualquer propriedade, métodos podem ser sobrescritos. O código comentado demonstra isso:

```javascript
pessoa.descrever = function() {
  console.log(`Meu nome é ${this.nome}`);
};
```

Aqui, estamos substituindo completamente a função original do método `descrever` por uma nova função que imprime apenas o nome. Como objetos são estruturas dinâmicas, essa substituição acontece sem problemas.

Após essa modificação, quando `pessoa.descrever()` for chamado, ele executará a nova versão do método, imprimindo apenas: `"Meu nome é Renan"`.

##### Funções como Valores

Um conceito fundamental mencionado no vídeo é que **funções são objetos** em JavaScript. Isso significa que podemos atribuir funções como valores de propriedades, exatamente como fazemos com strings ou números.

Quando escrevemos:

```javascript
descrever: function () { ... }
```

Estamos dizendo: "a propriedade `descrever` recebe como valor uma função". Essa função pode ser executada, substituída ou removida, como qualquer outra propriedade.

##### O Contexto é a Chave

O vídeo enfatiza que o `this` assume o **contexto** onde a função está sendo executada. Como o método `descrever` está dentro do objeto `pessoa`, o `this` se refere a `pessoa`.

Isso é poderoso porque permite que o método acesse todas as propriedades do objeto sem precisar conhecer o nome da variável que armazena o objeto. Se tivéssemos outro objeto com a mesma estrutura, poderíamos até mesmo copiar o método para ele, e o `this` se ajustaria automaticamente.

##### Objetos como Estruturas Completas

Com a adição de métodos, objetos se tornam estruturas completas que agrupam não apenas dados, mas também **comportamentos**. Um objeto pode conter:

**Dados**: propriedades com valores literais como strings, números, booleanos

**Comportamentos**: métodos que podem ler, modificar ou processar esses dados

**Flexibilidade**: tudo pode ser modificado dinamicamente durante a execução

Isso faz dos objetos uma das estruturas mais versáteis e fundamentais do JavaScript, permitindo criar representações ricas e interativas de entidades do mundo real ou conceitos abstratos dentro do código.

## 🟩 Vídeo 04 - Exercício - Acessando Dinamicamente Valores de um Objeto

![[formacao_javascript_developer-modulo_01-Curso_04-Video_04.webm|vid-60]]

Link do vídeo: https://web.dio.me/track/formacao-javascript-developer/course/criando-objetos-e-classes-em-javascript/learning/856c81da-ab0d-4e8d-9409-1235b5b0213a?autoplay=1

O vídeo apresenta os conceitos fundamentais dos **objetos literais** em JavaScript, destacando sua natureza flexível e dinâmica. O instrutor explica que esses objetos funcionam como coleções de **chave e valor**, permitindo a inclusão ou remoção constante de dados. Um ponto central da aula é a demonstração da **notação de colchetes**, que possibilita o acesso a atributos por meio de **strings** em vez de referências diretas. Essa técnica é valorizada por permitir que o código manipule propriedades cujos nomes podem ser recebidos como **parâmetros variáveis**. Ao final, reforça-se que essa abordagem amplia as possibilidades de programação ao tratar informações de forma menos rígida. O conteúdo foca em ensinar como o **dinamismo** na manipulação de dados torna o desenvolvimento mais poderoso e versátil.

### Anotações

Código:
```javascript
const pessoa = {
  nome: 'Vitor J Guerra',
  idade: 25,

  descrever: function () {
    console.log(`Meu nome é ${this.nome} e minha idade é ${this.idade}`);
  }
};

pessoa['nome'] = 'teste';
pessoa.nome = 'teste';
```

#### Acesso Dinâmico a Propriedades em Objetos JavaScript

O vídeo apresenta uma funcionalidade poderosa dos objetos em JavaScript: a capacidade de acessar propriedades de forma **dinâmica** usando a notação de colchetes. Vamos analisar o código fornecido e compreender como essa abordagem difere do acesso direto.

##### O Objeto Inicial

O código começa com o objeto `pessoa` já conhecido:

```javascript
const pessoa = {
  nome: 'Vitor J Guerra',
  idade: 25,
  descrever: function () {
    console.log(`Meu nome é ${this.nome} e minha idade é ${this.idade}`);
  }
};
```

Até aqui, temos um objeto com duas propriedades de dados e um método.

##### Acesso Direto vs Acesso Dinâmico

Existem duas formas de acessar propriedades de objetos em JavaScript, e o vídeo demonstra ambas:

**Acesso Direto (Notação de Ponto)**

É a forma que vimos até agora:

```javascript
pessoa.nome = 'teste';
```

Nesta sintaxe, você precisa conhecer e escrever explicitamente o nome da propriedade no código. É uma forma **estática** de acesso - você está codificando diretamente qual propriedade deseja acessar.

**Acesso Dinâmico (Notação de Colchetes)**

O código demonstra uma forma alternativa:

```javascript
pessoa['nome'] = 'teste';
```

Aqui, usamos colchetes e passamos o nome da propriedade como uma **string**. As duas linhas do código produzem exatamente o mesmo resultado - ambas alteram a propriedade `nome` para o valor `'teste'`.

##### A Diferença Fundamental

A grande diferença entre essas abordagens está na **flexibilidade**. Com a notação de colchetes, o nome da propriedade pode vir de uma variável, de um parâmetro de função, ou de qualquer expressão que resulte em uma string.

O vídeo menciona um exemplo onde uma variável armazena o nome da propriedade:

```javascript
const atributo = 'idade';
console.log(pessoa[atributo]); // Imprime: 25
```

Neste caso, não estamos acessando diretamente `pessoa.idade`. Em vez disso, estamos:

**Definindo uma variável** `atributo` com o valor `'idade'` (uma string)

**Usando essa variável** dentro dos colchetes para acessar dinamicamente a propriedade correspondente

**O JavaScript lê o valor** da variável `atributo`, encontra a string `'idade'`, e então busca a propriedade com esse nome no objeto

Se mudássemos o valor da variável `atributo` para `'nome'`, o mesmo código acessaria a propriedade `nome` em vez de `idade`.

##### Por Que Isso é Útil

Conforme explicado no vídeo, o acesso dinâmico abre **possibilidades infinitas**. Imagine situações onde:

**Você não sabe antecipadamente** qual propriedade precisará acessar - isso pode depender de entrada do usuário, dados vindos de uma API, ou lógica complexa do programa.

**Você precisa iterar** por várias propriedades de forma programática, sem escrever código repetitivo para cada uma.

**Você está construindo funcionalidades genéricas** que funcionam com diferentes objetos e propriedades.

Por exemplo, você poderia criar uma função que recebe o nome de uma propriedade como parâmetro:

```javascript
function obterValor(objeto, nomePropriedade) {
  return objeto[nomePropriedade];
}

console.log(obterValor(pessoa, 'nome'));  // 'Vitor J Guerra'
console.log(obterValor(pessoa, 'idade')); // 25
```

Isso seria impossível com a notação de ponto, pois você não pode usar variáveis diretamente com ela.

##### Modificando Propriedades Dinamicamente

Da mesma forma que podemos ler propriedades dinamicamente, também podemos modificá-las:

```javascript
const atributo = 'nome';
pessoa[atributo] = 'teste';
```

Isso é equivalente a `pessoa.nome = 'teste'`, mas permite que o nome da propriedade seja determinado em tempo de execução.

##### Comparação das Sintaxes

Vamos comparar as duas abordagens lado a lado:

**Notação de Ponto (Acesso Direto):**
```javascript
pessoa.nome = 'teste';
```
- Simples e direta
- Requer conhecer o nome da propriedade em tempo de codificação
- Mais legível para casos simples

**Notação de Colchetes (Acesso Dinâmico):**
```javascript
pessoa['nome'] = 'teste';
// ou
const atributo = 'nome';
pessoa[atributo] = 'teste';
```
- Permite usar variáveis e expressões
- O nome da propriedade pode vir de qualquer fonte
- Essencial para código genérico e reutilizável

##### Analogia da Estante de Livros

Para facilitar o entendimento, pense em uma **estante de livros**:

**Acesso direto** (`pessoa.nome`) é como ir direto à prateleira que você já sabe que tem o rótulo "História". Você conhece a localização exata e vai diretamente até ela.

**Acesso dinâmico** (`pessoa[atributo]`) é como ter um papel nas mãos onde alguém pode escrever o nome de qualquer categoria. Você lê o que está escrito no papel e só então vai até a prateleira correspondente, não importa qual seja ela. O papel pode ter "História", "Ficção", "Biografia" - você não sabe até ler.

##### O Básico dos Objetos

O vídeo conclui reforçando o conceito fundamental: um objeto em JavaScript é declarado com chaves `{ }` e contém uma **coleção de chave e valor**. Essa estrutura pode ser acessada de duas formas:

**Diretamente**, quando você conhece o nome da propriedade no momento da escrita do código

**Dinamicamente**, quando o nome da propriedade precisa ser determinado durante a execução do programa

Ambas as formas são válidas e úteis - a escolha depende do contexto e das necessidades do seu código. O acesso dinâmico adiciona uma camada extra de flexibilidade que é essencial para código mais avançado e reutilizável.


# Parte 2 - Entendendo Classes

## 🟩 Vídeo 05 - Como Criar Classes e Instâncias

![[formacao_javascript_developer-modulo_01-Curso_04-Video_05.webm|vid-60]]

Link do vídeo:  https://web.dio.me/track/formacao-javascript-developer/course/criando-objetos-e-classes-em-javascript/learning/2d585f3d-072b-470f-9eb5-54b66e4ab3bf?autoplay=1

O vídeo apresenta o conceito fundamental de **orientação a objetos** na programação, focando na transição de objetos literais para o uso de **classes**. O autor explica que uma **classe** funciona como um **modelo ou definição** que estabelece as características e comportamentos que um objeto deve possuir. Em contrapartida, as **instâncias** são as ocorrências reais e específicas criadas a partir desse molde, como exemplificado pelos personagens Vítor e Renan. Essa abordagem é defendida como uma solução eficiente para **evitar a repetição de código**, garantindo que múltiplos objetos sigam o mesmo padrão estrutural. Ao final, o diálogo ressalta que essa organização permite maior **dinamismo e padronização** no desenvolvimento de software.

### Anotações

Código:
```javascript
class Pessoa {
  nome;
  idade;

  descrever() {
    console.log(`Meu nome é ${this.nome} e minha idade é ${this.idade}`);
  }
}

const vitor = new Pessoa();
vitor.nome = 'Vitor J Guerra';
vitor.idade = 25;

const renan = new Pessoa();
renan.nome = 'Renan J Paula';
renan.idade = 30;

vitor.descrever();
renan.descrever();
```

Saída:
```shell
Meu nome é Vitor J Guerra e minha idade é 25
Meu nome é Renan J Paula e minha idade é 3
```

#### Explicação do código de classes em JavaScript

##### Contexto geral
O código apresentado exemplifica o uso de **classes** em JavaScript como uma forma de criar um modelo reutilizável para representar pessoas. No vídeo, é explicado que essa abordagem surge para evitar a repetição de código que acontece quando se usam apenas objetos literais, tornando a solução mais organizada, reutilizável e alinhada com a programação orientada a objetos.

##### Definição da classe Pessoa
A palavra-chave `class` é utilizada para declarar a classe `Pessoa`. Essa classe funciona como um **modelo**, ou seja, uma definição de como uma pessoa deve ser representada no código.

Dentro da classe são definidos:
- `nome`: uma propriedade que armazenará o nome da pessoa.
- `idade`: uma propriedade que armazenará a idade da pessoa.
- `descrever()`: um método responsável por exibir no console uma descrição da pessoa.

Na sintaxe de classes, o método é declarado diretamente pelo nome, sem a necessidade da palavra-chave `function`. O uso de `this` dentro do método faz referência à instância específica que está chamando o método, permitindo acessar corretamente `nome` e `idade`.

##### Criação de instâncias da classe
Após a definição da classe, o código cria novas pessoas utilizando o operador `new`. Esse processo é chamado de **instanciação**.

Quando se escreve:
`const vitor = new Pessoa();`

Está sendo criada uma instância da classe `Pessoa`. Nesse momento, o objeto existe, mas ainda não possui valores atribuídos às suas propriedades. Em seguida, o código define:
- `vitor.nome`
- `vitor.idade`

O mesmo processo é repetido para a criação da instância `renan`. Ambas seguem exatamente o mesmo modelo definido pela classe, mudando apenas os valores atribuídos.

##### Classe versus instância
Conforme explicado no vídeo, a **classe** representa a definição do que algo deve ser, enquanto a **instância** representa uma ocorrência concreta dessa definição. A classe `Pessoa` descreve como toda pessoa deve se comportar e quais dados ela possui. Já `vitor` e `renan` são pessoas específicas criadas a partir desse modelo.

Essa separação evita duplicação de código e garante consistência, pois todas as pessoas seguem a mesma estrutura.

##### Uso dos métodos das instâncias
Ao final do código, os métodos são chamados:
`vitor.descrever();`
`renan.descrever();`

Cada chamada executa o método `descrever()` no contexto da instância correspondente. Isso faz com que o texto exibido no console utilize corretamente os valores de `nome` e `idade` de cada pessoa, demonstrando como o mesmo método pode ser reutilizado por diferentes instâncias.

##### Conceito de orientação a objetos
O exemplo introduz os fundamentos da **programação orientada a objetos**, destacando a ideia de modelar o código a partir de entidades do mundo real. O vídeo reforça que, embora existam muitos conceitos envolvidos nesse paradigma, o ponto principal neste momento é compreender a diferença entre classe e instância e como isso ajuda a criar código mais organizado, reutilizável e escalável.


## 🟩 Vídeo 06 - Criando Instâncias Com Constructor

![[formacao_javascript_developer-modulo_01-Curso_04-Video_06.webm|vid-60]]

Link do vídeo: https://web.dio.me/track/formacao-javascript-developer/course/criando-objetos-e-classes-em-javascript/learning/61572044-e4b7-4f3e-994b-3dfecc9ab1de?autoplay=1

O vídeo apresenta uma introdução didática aos conceitos de **Programação Orientada a Objetos**, focando especificamente no papel do **método construtor**. O instrutor explica como essa função é executada automaticamente no momento da **instanciação**, permitindo que atributos obrigatórios sejam definidos logo na criação do objeto. Além de padronizar a entrada de dados, o texto demonstra como o construtor pode ser usado para realizar **cálculos automáticos**, como deduzir o ano de nascimento a partir da idade informada. Essa abordagem estabelece uma espécie de **contrato de definição**, garantindo que cada nova ocorrência do objeto possua todas as propriedades necessárias. O conteúdo destaca, de forma prática, como transformar definições abstratas em **instâncias concretas e funcionais** dentro do código.

### Anotações

Código:
```javascript
class Pessoa {
    nome;
    idade;
    anoDeNascimento;

    constructor(nome, idade) {
        this.nome = nome;
        this.idade = idade;
        this.anoDeNascimento = 2022 - idade;
    }

    descrever() {
        console.log(`Meu nome é ${this.nome} e minha idade é ${this.idade}`);
    }
}

const vitor = new Pessoa('Vitor', 25);
const renan = new Pessoa('Renan', 30);

console.log(vitor);
```

Saída:
```shell
Pessoa { nome: 'Vitor', idade: 25, anoDeNascimento: 1997 }
```

#### Explicação do código com classe, construtor e atributos derivados em JavaScript

##### Contexto geral
O código apresentado aprofunda o uso de **classes em JavaScript**, introduzindo o conceito de **constructor**. No vídeo, a explicação evolui da simples definição de um modelo para a ideia de obrigar certos dados no momento da criação do objeto, reforçando a noção de contrato entre a classe e suas instâncias.

##### Definição da classe Pessoa
A classe `Pessoa` define o modelo que representa uma pessoa no sistema. Ela estabelece quais informações e comportamentos todas as pessoas devem possuir.

São declarados três atributos:
- `nome`, que representa o nome da pessoa  
- `idade`, que representa a idade  
- `anoDeNascimento`, que será calculado automaticamente  

Esses atributos definem a estrutura básica que toda instância da classe seguirá.

##### Papel do constructor
O método `constructor(nome, idade)` é executado **automaticamente sempre que uma nova instância da classe é criada** usando a palavra-chave `new`.

No vídeo, o construtor é apresentado como um mecanismo que garante que certos dados sejam obrigatórios. Ao exigir `nome` e `idade` como parâmetros, o código impede a criação de uma pessoa incompleta.

Dentro do construtor:
- `this.nome` recebe o valor passado como parâmetro  
- `this.idade` recebe a idade informada  
- `this.anoDeNascimento` é calculado dinamicamente com base na idade  

Esse cálculo demonstra que o construtor pode não apenas copiar valores, mas também **preparar novos atributos derivados**, criando informações adicionais a partir dos dados recebidos.

##### Uso de this e escopo da instância
A palavra-chave `this` representa a instância específica que está sendo criada naquele momento. No vídeo, isso é explicado como a forma de diferenciar cada pessoa criada a partir da mesma classe.

Assim, cada instância de `Pessoa` terá seus próprios valores de nome, idade e ano de nascimento, mesmo que todas sigam o mesmo modelo.

##### Método descrever
O método `descrever()` é responsável por exibir no console uma frase com o nome e a idade da pessoa.

Esse método não precisa da palavra-chave `function` por estar dentro da definição da classe. Ao ser chamado por uma instância, ele utiliza `this.nome` e `this.idade` para acessar os dados daquela pessoa específica.

##### Instanciação de objetos
As linhas:
`const vitor = new Pessoa('Vitor', 25);`  
`const renan = new Pessoa('Renan', 30);`

criam duas instâncias distintas da classe `Pessoa`. Cada chamada ao `new` executa o construtor, garantindo que ambas as pessoas sejam criadas já com nome, idade e ano de nascimento corretamente definidos.

Isso ilustra o conceito apresentado no vídeo de que **classe é a definição** e **instância é a ocorrência concreta dessa definição**.

##### Inspeção do objeto no console
O comando `console.log(vitor)` imprime a instância criada no console, permitindo visualizar todos os atributos do objeto, incluindo o `anoDeNascimento`, que não foi passado diretamente, mas foi calculado no construtor.

Esse ponto reforça a ideia de que o construtor pode enriquecer o objeto no momento da criação, preparando dados que não existiriam se o objeto fosse criado de forma literal.

##### Orientação a objetos e contrato
O vídeo destaca que esse padrão representa um primeiro contato com a **programação orientada a objetos**. A classe funciona como um contrato que define como o objeto deve ser criado e quais dados são obrigatórios, enquanto as instâncias representam ocorrências reais que seguem esse contrato.

Esse modelo torna o código mais organizado, previsível e fácil de manter, além de evitar duplicação e inconsistências na criação de objetos.

## 🟩 Vídeo 07 - Funções Recebendo Objetos

![[formacao_javascript_developer-modulo_01-Curso_04-Video_07.webm|vid-60]]

Link do vídeo: https://web.dio.me/track/formacao-javascript-developer/course/criando-objetos-e-classes-em-javascript/learning/ef839bd6-21ee-4667-b036-efbd284ed952?autoplay=1

O instrutor explica como o construtor atua como um **contrato obrigatório** que define as propriedades necessárias, como nome e idade, no momento em que um novo **objeto é instanciado**. Além de apenas armazenar dados, o texto demonstra que o construtor pode ser utilizado para **processar informações automaticamente**, como calcular o ano de nascimento a partir da idade fornecida. Através de exemplos práticos, a conversa ilustra como a **instanciação** cria ocorrências únicas de um objeto seguindo regras pré-definidas. Por fim, o diálogo reforça que essa estrutura permite a criação de **códigos mais organizados** e coerentes dentro do desenvolvimento de software.

### Anotações

Código:
```javascript
class Pessoa {
  nome;
  idade;
  anoDeNascimento;

  constructor(nome, idade) {
    this.nome = nome;
    this.idade = idade;
    this.anoDeNascimento = 2022 - idade;
  }

  descrever() {
    console.log(`Meu nome é ${this.nome} e minha idade é ${this.idade}`);
  }
}

function compararPessoas(p1, p2) {
    if (p1.idade > p2.idade) {
        console.log(`${p1.nome} é mais velho(a) que ${p2.nome}`);
    } else if (p2.idade > p1.idade) {
        console.log(`${p2.nome} é mais velho(a) que ${p1.nome}`);
    } else {
        console.log(`${p1.nome} e ${p2.nome} tem a mesma idade`);
    }
}

const vitor = new Pessoa('Vitor', 25);
const renan = new Pessoa('Renan', 30);

compararPessoas(vitor, renan);
```

Saída:
```shell
Renan é mais velho(a) que Vitor
```

##### Definição da Classe `Pessoa`

A classe `Pessoa` representa um modelo para criar objetos que descrevem pessoas com três atributos principais: `nome`, `idade` e `anoDeNascimento`.

O método `constructor` é automaticamente chamado sempre que uma nova instância da classe é criada com o operador `new`. No vídeo, isso é explicado como o momento em que o objeto é instanciado e o construtor é acionado. O construtor exige dois parâmetros obrigatórios: `nome` e `idade`, reforçando a ideia de que esses dados são essenciais para definir uma pessoa.

Além disso, o atributo `anoDeNascimento` é calculado dentro do construtor como uma dedução: subtrai-se a idade do ano de referência (2022), como mostrado no vídeo. Isso demonstra como o construtor pode preparar atributos derivados com base nos dados recebidos.

##### Método `descrever`

O método `descrever` é uma função interna da classe que imprime no console uma frase com o nome e a idade da pessoa. Ele utiliza template literals para interpolar os valores diretamente na string. Esse tipo de método é útil para encapsular comportamentos que pertencem ao objeto, como também foi comentado no vídeo.

##### Função `compararPessoas`

A função `compararPessoas` recebe dois objetos do tipo `Pessoa` e compara suas idades. Ela imprime no console quem é mais velho ou se ambos têm a mesma idade. Essa lógica condicional exemplifica como objetos podem ser comparados entre si com base em seus atributos, reforçando o conceito de instâncias distintas criadas a partir de uma mesma estrutura (classe).

##### Instanciação e Execução

Duas instâncias da classe `Pessoa` são criadas: `vitor` e `renan`, com idades diferentes. Isso mostra como a classe serve como um "contrato" para criar múltiplos objetos com a mesma estrutura, mas com dados únicos — como mencionado no vídeo com a analogia da planta da casa.

Por fim, a função `compararPessoas` é chamada com os dois objetos, e o resultado da comparação é exibido no console.

# Parte 3 - Praticando com Objetos e Classes

## 🟩 Vídeo 08 - Exercício Guiado - Criando a Classe Carro

![[formacao_javascript_developer-modulo_01-Curso_04-Video_08.webm|vid-60]]

Link do vídeo:  https://web.dio.me/track/formacao-javascript-developer/course/criando-objetos-e-classes-em-javascript/learning/b37a8a14-0612-4654-99c2-33b2bd655dba?autoplay=1

O vídeo apresenta o desafio de código e o início da criação da classe.

### Anotações

Desafio:
```shell
/*
1 - Crie uma classe para representar carros.
Os carros possuem uma marca, uma cor e um gasto médio de combustível por kilômetro rodado.
Crie um método que dado a quantidade de quilômetros e o preço do combustível nos dê o valor
gasto em reais para realizar este percurso.
*/
```


Código (criando a classe carro):
```javascript
class Carro {
  marca;
  cor;
  gastoMedioPorKm;

  constructor(marca, cor, gastoMedioPorKm) {
    this.marca = marca;
    this.cor = cor;
    this.gastoMedioPorKm = gastoMedioPorKm;
  }
}
```


## 🟩 Vídeo 09 - Exercício Guiado - Criando a Instância


Link do vídeo: https://web.dio.me/track/formacao-javascript-developer/course/criando-objetos-e-classes-em-javascript/learning/c4d39f42-dfe0-4272-9069-c23bbfad66b0?autoplay=1




## 🟩 Vídeo 10 - Exercício Guiado - Criando o Método calcularGastoDePercurso


Link do vídeo: 


## 🟩 Vídeo 11 - Resumo do Exercício Guiado


Link do vídeo: 


## 🟩 Vídeo 12 - Exercício Guiado - Criando a Classe Pessoa


Link do vídeo: 


## 🟩 Vídeo 13 - Exercício Guiado - Criando o Método calcularImc


Link do vídeo: 


## 🟩 Vídeo 14 - Exercício Guiado - Criando a Instância


Link do vídeo: 


## 🟩 Vídeo 15 - Exercício Guiado - Criando o Método classificarImc


Link do vídeo: 



##  Materiais de Apoio


# Certificado: 

- Link na plataforma: 
- Certificado em pdf: 
