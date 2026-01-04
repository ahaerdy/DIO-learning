- Instrutor:  Nathally Souza (Software Engineer | Startup Founder | Javascript, Typescript, Cloud, Frontend, Backend)
- Contato Linkedin: / [nathsouza](https://www.linkedin.com/in/nathsouza/)

# Parte 1: O que é o TypeScript

## 🟩 Vídeo 01 - Introdução

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/formacao_typescript_fullstack-modulo_01-Curso_01-Video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo:https://web.dio.me/track/formacao-typescript-fullstack-developer/course/o-que-e-o-typescript/learning/88a53e9c-b178-42ff-8278-85ea34126c30?autoplay=1

A instrutora **Natalie Souza**, desenvolvedora no Itaú, se apresenta e introduz um módulo de um curso de **introdução ao Typescript**, que faz parte de uma trilha Full Stack. Natalie compartilha sua transição de uma **carreira de dez anos no jornalismo** para a tecnologia, motivada pelo desejo de **transformar e impactar a sociedade**. Ela também aborda sua identidade como **mulher trans/travesti** e a importância de sua posição para **comunidades sub-representadas** na área de tecnologia. Finalmente, ela detalha que o **objetivo do curso** é ensinar a sintaxe básica do Typescript e **conceitos de orientação a objetos**, mencionando que o pré-requisito é ter uma base em lógica de programação.

## 🟩 Vídeo 02 - Conhecendo o Typescript

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/formacao_typescript_fullstack-modulo_01-Curso_01-Video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/formacao-typescript-fullstack-developer/course/o-que-e-o-typescript/learning/a8aab96a-40fd-4601-bfbb-a387614fda39?autoplay=1

O material fornece uma introdução detalhada ao **TypeScript**, explicando que ele é uma linguagem de programação e um superset do **JavaScript** que adiciona uma camada de tipagem. O instrutor demonstra a necessidade do TypeScript ao mostrar as limitações do JavaScript puro no controle de tipos, ilustrando como o JavaScript pode concatenar strings em vez de somar números. Em seguida, o vídeo orienta os alunos através da **configuração completa do ambiente**, incluindo a instalação do Node.js, a instalação global e local do TypeScript, e a inicialização de um projeto. Finalmente, a instrução mostra como o TypeScript oferece um **feedback rápido de erros** de tipagem diretamente no VS Code, um recurso que **aumenta a produtividade** ao evitar a necessidade de transpilar o código manualmente após cada mudança, especialmente com a ajuda de ferramentas como o TS Node dev.

### Anotações

A instrutora inicia a demonstração com o seguinte código:

```javascript
const soma = (a, b) => {
  console.log(a + b)
}

soma(2, 2)
```

Resultado:

```
4
```

Em seguida, a função é chamada novamente, desta vez utilizando strings como parâmetros:

```python
const soma = (a, b) => {
  console.log(a + b)
}

soma(2, 2)
soma('Nath', 'Souza')
```

Resultado:

```
4
NathSouza
```

Explicação técnica: no JavaScript, o operador + possui comportamento SOBRECARGA IMPLÍCITA:

- Quando aplicado a valores numéricos, realiza a adição aritmética.
- Quando aplicado a valores do tipo string, realiza a concatenação.

Assim, ao passar os argumentos 'Nath' e 'Souza', o operador + concatena os dois valores, produzindo a saída NathSouza.

#### O que significa "comportamento de sobrecarga implícita" 
- Em linguagens de programação, sobrecarga ocorre quando um mesmo operador ou função pode assumir comportamentos diferentes dependendo do tipo dos operandos ou argumentos. 
- No caso específico do JavaScript, não existe uma sobrecarga explícita definida pelo programador (como em C++ ou Java). O que acontece é um comportamento implícito do operador +:
  
	- Operandos numéricos→ o operador realiza adição aritmética.
		- Exemplo: 2 + 2 → 4
	- Operandos do tipo string→ o operador realiza concatenação de strings.
		- Exemplo: 'Nath' + 'Souza' → 'NathSouza'
	- Operandos mistos (string + número) → o número é convertido implicitamente em string, e ocorre concatenação.
		- Exemplo: 'Idade: ' + 30 → 'Idade: 30'

- Esse comportamento é chamado de sobrecarga implícita do operador +, pois o programador não define manualmente como o operador deve agir; o motor do JavaScript decide automaticamente com base nos tipos envolvidos, aplicando coerção de tipos (type coercion).

O Javascript Original não tem o controle de tipos. 

# Parte 2: Fundamentos e sintaxe

## 🟩 Vídeo 03 - Variáveis e tipos

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/formacao_typescript_fullstack-modulo_01-Curso_01-Video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/formacao-typescript-fullstack-developer/course/o-que-e-o-typescript/learning/8f3cfcc8-85b8-444b-ae9f-cff4a5b3322c?autoplay=1

O vídeo consiste em uma aula técnica que introduz os **fundamentos e a sintaxe básica do TypeScript**, destacando a importância da **tipagem estática** para a segurança do código. A instrutora detalha as três formas de declarar variáveis — **var, let e const** — recomendando evitar o uso de escopo global por questões de proteção. O conteúdo explica os **tipos primitivos**, como strings, números e booleanos, demonstrando como definir explicitamente a natureza de cada dado. Além disso, o texto aborda o uso de **union types** e faz uma crítica ao uso excessivo do tipo **any**, que descaracteriza as vantagens da linguagem. Por fim, a aula reforça que o TypeScript proporciona um **feedback rápido** durante o desenvolvimento, preparando o terreno para o estudo de objetos e interfaces.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-04-15h03m16s374.jpg" alt="" width="840">
</p>

Esta etapa marca o início do estudo sobre os **fundamentos e a sintaxe básica** do TypeScript. O foco inicial é compreender como a introdução de tipos modifica a escrita do JavaScript tradicional, proporcionando uma transição suave para quem já conhece a linguagem base e um passo a passo estruturado para iniciantes. Nesta fase, o objetivo é entender o impacto da tipagem no ciclo de desenvolvimento.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-04-15h03m22s963.jpg" alt="" width="840">
</p>

Nesta parte da aula, os tópicos abordados focam na estrutura essencial da linguagem, incluindo:

* **Variáveis e tipos**: Como declarar e manipular dados.
* **Tipos primitivos**: O uso de `string`, `number` e `boolean`.
* **Objetos**: A estrutura de dados complexos.
* **Interface x Type**: As principais diferenças e aplicações de cada um para definir contratos de dados.

O aprendizado é direcionado para a prática direta no VS Code, priorizando a segurança e o controle do código através da tipagem.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-04-15h04m21s539.jpg" alt="" width="840">
</p>

A declaração de variáveis no TypeScript utiliza `let` e `const`, abandonando o uso do `var` devido à sua insegurança por escopo global. Ao definir variáveis, o TypeScript permite a **tipagem explícita** através da sintaxe de dois pontos (`:`), o que garante que a variável aceite apenas o tipo de dado definido, como demonstrado abaixo:

```typescript
let b: string = 'Nath'
let n: number = 2
let x: boolean = true

let m: string | number = 2
m = 'Nath'

```

O exemplo acima também ilustra o uso do **Union Type** (`|`), que permite a uma variável (`m`) aceitar mais de um tipo específico (neste caso, `string` ou `number`). Tentar atribuir um valor `boolean` a essa variável resultaria em um erro imediato do editor.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-04-15h04m31s304.jpg" alt="" width="840">
</p>

Embora o TypeScript ofereça flexibilidade, o uso do tipo `any` deve ser evitado sempre que possível. Ao declarar uma variável como `any`, ela passa a aceitar qualquer tipo de dado, o que anula os benefícios de segurança e feedback rápido da linguagem.

```typescript
let m: any = 2
m = 'Nath'
m = false

```

O uso indiscriminado do `any` compromete a integridade da aplicação. Ele deve ser reservado para contextos específicos onde a tipagem dinâmica é estritamente necessária, mantendo-se a preferência por tipos bem definidos para garantir a robustez do código.


## 🟩 Vídeo 04 - Objetos e interfaces

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/formacao_typescript_fullstack-modulo_01-Curso_01-Video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/formacao-typescript-fullstack-developer/course/o-que-e-o-typescript/learning/0f530f9b-6172-4177-a649-401b7347cb67?autoplay=1

O vídeo consiste em uma aula técnica sobre a **tipagem de objetos e arrays** no **TypeScript**. O conteúdo explica detalhadamente como utilizar **interfaces** para estruturar objetos, definindo propriedades obrigatórias e o uso do ponto de interrogação para marcar atributos como **opcionais**. Além disso, o material demonstra como garantir a segurança de dados em **listas**, apresentando duas sintaxes distintas para declarar **arrays de objetos ou tipos primitivos**. A explicação foca na importância de definir tipos claros para evitar erros de atribuição durante o desenvolvimento de software. Por fim, o texto reforça como o **VS Code** auxilia o programador ao identificar incompatibilidades de dados em tempo real.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-04-15h51m42s318.jpg" alt="" width="840">
</p>

Nesta etapa, exploramos como tipar objetos no TypeScript utilizando **interfaces**. Diferente dos tipos primitivos, uma interface permite definir a estrutura de um objeto, especificando quais propriedades ele deve ter e de quais tipos elas são.

No exemplo apresentado, criamos a interface `Pessoa` com as seguintes características:

* **nome**: Definido como `string`.
* **idade**: Definido como `number`.
* **profissao?**: O uso do operador de interrogação (`?`) indica que esta propriedade é **opcional**. Isso significa que o TypeScript não exigirá esse valor ao instanciar um objeto desse tipo, permitindo que ele seja `string` ou `undefined`.

Com a interface definida, podemos criar objetos que seguem essa estrutura:

1. **pessoa**: Um objeto que contém apenas as propriedades obrigatórias (`nome` e `idade`).
2. **outraPessoa**: Um objeto que utiliza todas as propriedades, incluindo a opcional `profissao`.

Além da tipagem de objetos individuais, o TypeScript permite definir tipos para **arrays**. Existem duas sintaxes comuns para isso:

* **Sintaxe de colchetes**: Como em `number[]`, indicando um array que contém apenas números.
* **Operador Diamante (Generics)**: Como em `Array<Pessoa>` ou `Array<string>`, que utiliza uma sintaxe mais explícita para definir o tipo de dado contido na lista.

```typescript
interface Pessoa {
  nome: string,
  idade: number,
  profissao?: string
}

const pessoa: Pessoa = {
  nome: 'Nath',
  idade: 32
}

const outraPessoa: Pessoa = {
  nome: 'Paulo',
  idade: 25,
  profissao: 'Desenvolvedor'
}

const arrayPessoa: Array<Pessoa> = [
  pessoa,
  outraPessoa
]

const arrayNum: number[] = [
  1, 2, 3
]

const arrayString: Array<string> = [
  '1', '2', '3'
]

```     

## 🟩 Vídeo 05 - Decisões e repetições

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/formacao_typescript_fullstack-modulo_01-Curso_01-Video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/formacao-typescript-fullstack-developer/course/o-que-e-o-typescript/learning/5d564867-2317-4433-8109-826220d7fd1a?autoplay=1

O material apresenta uma aula técnica sobre **estruturas de controle** no **TypeScript**, focando inicialmente na sintaxe e no funcionamento de condicionais como **if**, **else if** e **else**. O instrutor demonstra como realizar **comparações de valores e tipos** de forma segura e como encadear múltiplas decisões para gerenciar o fluxo de dados. Além da abordagem nativa, o conteúdo introduz o uso de **objetos literais** como uma alternativa mais limpa e organizada para substituir blocos condicionais extensos. Através de exemplos práticos com **funções** e mapeamento de chaves, o vídeo ensina a otimizar o código para facilitar a manutenção em aplicações complexas. O objetivo final é capacitar o aluno a escolher a melhor estratégia de decisão antes de avançar para o estudo de estruturas de repetição.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-04-16h56m16s233.jpg" alt="" width="840">
</p>

Esta aula marca o início do estudo sobre **estruturas de decisão e repetição** no TypeScript. Após concluir o módulo de variáveis e tipos — onde foram abordados tipos primitivos e a tipagem de objetos — o foco agora é compreender como controlar o fluxo da aplicação. O conteúdo programático inclui o funcionamento das estruturas de decisão clássicas, o uso de **objetos literais** como alternativa estratégica para essas decisões e a compreensão das estruturas de repetição.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-04-16h56m21s694.jpg" alt="" width="840">
</p>

As **estruturas de decisão** permitem que o código execute diferentes blocos de instrução baseados em condições lógicas. No TypeScript, isso é implementado de forma similar ao JavaScript, utilizando principalmente o bloco `if`. Além das estruturas condicionais básicas, será explorado como os objetos literais podem complementar e otimizar a escrita de decisões no código, tornando-o mais limpo e organizado.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-04-16h57m05s503.jpg" alt="" width="840">
</p>

A estrutura fundamental de decisão utiliza o `if` seguido de uma condição entre parênteses. No exemplo prático, verificamos se uma variável numérica é maior que 15. Caso a condição seja verdadeira, o primeiro bloco de código é executado; caso contrário, o bloco `else` (senão) assume o controle. Para executar o código e visualizar o resultado no terminal, utiliza-se o comando configurado previamente no projeto.

```typescript
const num: number = 16

if (num > 15) {
  console.log('Num maior que 15')
} else {
  console.log('Num menor que 15')
}

```

```bash
fundamentos yarn run dev
yarn run v1.22.10
$ ts-node-dev app.ts
[INFO] 17:18:15 ts-node-dev ver. 2.0.0 (using ts-node ver. 10.8.2, typescript ver. 4.7.4)
Num maior que 15
Done in 2.17s.

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-04-16h58m19s854.jpg" alt="" width="840">
</p>

Para lidar com múltiplas possibilidades, podemos utilizar o **encadeamento de decisão** com o `else if`. No caso de comparar se um número é igual a outro, o TypeScript (e o JavaScript) exige o uso de operadores de comparação. É altamente recomendado utilizar **três sinais de igual (`===`)**, pois eles garantem a comparação tanto do valor quanto do tipo do dado, evitando comportamentos inesperados que podem ocorrer ao usar apenas dois sinais (`==`).

```typescript
const num: number = 15

if (num > 15) {
  console.log('Num maior que 15')
} else if (num === 15) {
  console.log('Num igual a 15')
} else {
  console.log('Num menor que 15')
}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-04-16h58m28s104.jpg" alt="" width="840">
</p>

Quando uma aplicação cresce e apresenta muitas condições encadeadas, o uso excessivo de `if/else` pode tornar o código de difícil manutenção. Uma alternativa eficiente é o uso de **objetos literais**. Ao criar um objeto que mapeia chaves (como tipos de usuário) para seus respectivos valores (mensagens), podemos acessar a informação desejada diretamente através de uma função, reduzindo a complexidade visual do código. No TypeScript, ao acessar um objeto dinamicamente com uma string, utilizamos a expressão `as keyof typeof` para garantir que a chave informada pertence de fato ao objeto.

```typescript
const typeUser = {
  admin: 'Seja bem vindo admin',
  student: 'Voce é um estudante',
  viewer: 'Voce pode visualizar'
}

function validateUser(user: string) {
  console.log(typeUser[user as keyof typeof typeUser])
}

const usuario = 'admin'
validateUser(usuario)

```

```bash
fundamentos yarn run dev
yarn run v1.22.10
$ ts-node-dev app.ts
[INFO] 17:45:38 ts-node-dev ver. 2.0.0 (using ts-node ver. 10.8.2, typescript ver. 4.7.4)
Seja bem vindo admin
Done in 2.16s.

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-04-16h58m37s017.jpg" alt="" width="840">
</p>

A flexibilidade do objeto literal permite validar diferentes entradas chamando a mesma função de validação. Ao passar parâmetros como 'admin', 'student' ou 'viewer', a função recupera instantaneamente o valor associado no objeto `typeUser`. Essa abordagem, combinada com funções, resulta em uma estrutura de decisão limpa, de fácil compreensão e manutenção, sendo uma excelente escolha para substituir sequências extensas de `else if`.

```typescript
validateUser(usuario)
validateUser('student')
validateUser('viewer')

```

```bash
Seja bem vindo admin
Voce é um estudante
Voce pode visualizar
Done in 1.73s.

```      


## 🟩 Vídeo 06 - Estruturas de Repetição

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/formacao_typescript_fullstack-modulo_01-Curso_01-Video_06.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/formacao-typescript-fullstack-developer/course/o-que-e-o-typescript/learning/827e326e-5e34-42b4-9f7c-521dd0012c58?autoplay=1

## 🟩 Vídeo 07 - Array

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/formacao_typescript_fullstack-modulo_01-Curso_01-Video_07.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo:

## 🟩 Vídeo 08 - Manipulando Arrays

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/formacao_typescript_fullstack-modulo_01-Curso_01-Video_08.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo:

# Parte 3: Materiais de Apoio e Questionário



# Certificado: 

- Link na plataforma: 
- Certificado em pdf: 
