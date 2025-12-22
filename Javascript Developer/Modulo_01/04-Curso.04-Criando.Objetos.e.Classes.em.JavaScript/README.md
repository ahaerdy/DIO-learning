## Instrutor:

- Renan Johannsen de Paula (Software Engineer Lead)
- Contato Linkedin: / [renanjpaula](https://www.linkedin.com/in/renanjpaula/)

# Parte 1 - xxxxxxxxxxxxxxx

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


Link do vídeo: https://web.dio.me/track/formacao-javascript-developer/course/criando-objetos-e-classes-em-javascript/learning/856c81da-ab0d-4e8d-9409-1235b5b0213a?autoplay=1



# Parte 2 - Entendendo Classes

## 🟩 Vídeo 01 - Como Criar Classes e Instâncias


Link do vídeo:  


## 🟩 Vídeo 02 - Criando Instâncias Com Constructor


Link do vídeo: 


## 🟩 Vídeo 03 - Funções Recebendo Objetos


Link do vídeo: 

# Parte 3 - Praticando com Objetos e Classes

## 🟩 Vídeo 01 - Exercício Guiado - Criando a Classe Carro


Link do vídeo:  


## 🟩 Vídeo 02 - Exercício Guiado - Criando a Instância


Link do vídeo: 


## 🟩 Vídeo 03 - Exercício Guiado - Criando o Método calcularGastoDePercurso


Link do vídeo: 


## 🟩 Vídeo 04 - Resumo do Exercício Guiado


Link do vídeo: 


## 🟩 Vídeo 05 - Exercício Guiado - Criando a Classe Pessoa


Link do vídeo: 


## 🟩 Vídeo 06 - Exercício Guiado - Criando o Método calcularImc


Link do vídeo: 


## 🟩 Vídeo 07 - Exercício Guiado - Criando a Instância


Link do vídeo: 


## 🟩 Vídeo 08 - Exercício Guiado - Criando o Método classificarImc


Link do vídeo: 



##  Materiais de Apoio


# Certificado: 

- Link na plataforma: 
- Certificado em pdf: 
