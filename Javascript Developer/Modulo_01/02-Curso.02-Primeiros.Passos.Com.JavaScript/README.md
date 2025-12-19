## Instrutor

- Renan Johannsen de Paula (Software Engineer Lead)
- Contato Linkedin: / [renanjpaula](https://www.linkedin.com/in/renanjpaula/)

# Parte 1 - Variáveis e Operadores

## 🟩 Vídeo 01 - Apresentação Inicial

![[formacao_javascript_developer-modulo_01-Curso_02-Video_01.webm|vid-60]]

Link do vídeo:  https://web.dio.me/track/formacao-javascript-developer/course/primeiros-passos-com-javascript/learning/f699d9a3-0b44-461f-9839-602945ea6579?autoplay=1

O vídeo apresenta uma **aula introdutória de JavaScript** conduzida por um instrutor e seu primo, Vitor, que também participa como aluno e representante das dúvidas da audiência. O objetivo é ensinar os **conceitos básicos da linguagem**, como **declaração de variáveis e operadores aritméticos**, com a promessa de avançar para tópicos mais complexos. Os participantes estão usando o **Visual Studio Code** e o **Node versão 14**, mencionando que essa versão é utilizada por ser mais estável com alguns projetos. O formato de aprendizado será prático, com a criação de **arquivos de aula, desafios e resoluções** para garantir a fixação do conteúdo.


## 🟩 Vídeo 02 - Console e Como Declarar Variáveis

![[formacao_javascript_developer-modulo_01-Curso_02-Video_02.webm|vid-60]]

Link do vídeo: https://web.dio.me/track/formacao-javascript-developer/course/primeiros-passos-com-javascript/learning/88232320-2521-4813-8e05-84f7c6ec0196?autoplay=1

O vídeo apresentado discute **conceitos introdutórios de programação em JavaScript**, focando em como **executar um arquivo** usando o comando `node` e a importância de **imprimir informações no terminal** com `console.log`. O instrutor explica que esse comando é fundamental para visualizar resultados e interagir com o terminal. Em seguida, a explicação aborda **a declaração e o uso de variáveis** em JavaScript, definindo-as como espaços de memória para armazenar valores. O foco principal é a distinção entre as duas formas mais comuns de declarar variáveis: o `let`, que permite **reatribuição de valores**, e o `const`, que define uma **variável cujo valor não pode ser alterado** após a declaração inicial.

### Anotações:

Código exemplo:

```javascript
let variavel = 10;
const pi = 3.14159;

console.log("variável =",variavel);

variavel = 20;
console.log("variável =",variavel);

console.log("pi =",pi);
```

Saída:

```shell
variável = 10
variável = 20
pi = 3.14159
```

O exemplo mostra claramente a diferença entre variáveis declaradas com let e constantes declaradas com const: a variável variavel começa com o valor 10, pode ser alterada para 20 e o console imprime cada mudança, enquanto a constante pi é definida como 3.14159 e permanece imutável, sendo apenas exibida sem possibilidade de reatribuição; além disso, o uso de console.log permite visualizar no terminal cada etapa da execução, confirmando como os valores são armazenados e manipulados em memória.

Código exemplo:

```javascript
const pi = 3.14159;

console.log("pi =",pi);

pi = 3.19;
```

Saida: 

```shell
TypeError: Assignment to constant variable.
    at Object.<anonymous> (/home/arthur/Downloads/Cursos.DIO/Javascript/aula.js:5:4)
    at Module._compile (node:internal/modules/cjs/loader:1760:14)
    at Object..js (node:internal/modules/cjs/loader:1892:10)
    at Module.load (node:internal/modules/cjs/loader:1480:32)
    at Module._load (node:internal/modules/cjs/loader:1299:12)
    at TracingChannel.traceSync (node:diagnostics_channel:328:14)
    at wrapModuleLoad (node:internal/modules/cjs/loader:245:24)
    at Module.executeUserEntryPoint [as runMain] (node:internal/modules/run_main:154:5)
    at node:internal/main/run_main_module:33:47

Node.js v25.2.1
```

Esse exemplo evidencia a natureza imutável das constantes em JavaScript: ao declarar const pi = 3.14159;, o valor de pi é fixado e não pode ser alterado; o primeiro console.log imprime corretamente pi = 3.14159, mas quando o código tenta reatribuir pi = 3.19;, o interpretador lança um TypeError informando que não é permitido atribuir novamente a uma constante, interrompendo a execução do programa. Isso reforça a diferença entre let, que permite mudanças de valor, e const, que garante que o dado permaneça inalterado após a declaração.


## 🟩 Vídeo 03 - Diferenças entre Variáveis e Tipos de Operadores

![[formacao_javascript_developer-modulo_01-Curso_02-Video_03.webm|vid-60]]

Link do vídeo: https://web.dio.me/track/formacao-javascript-developer/course/primeiros-passos-com-javascript/learning/efa6a6f6-3fa3-409a-8e64-23af101e6d3a?autoplay=1

O vídeo é uma aula de programação que **explica o uso de variáveis e constantes** no código. O instrutor demonstra como **declarar, imprimir e reatribuir** valores a variáveis, mostrando que elas podem ter seus valores alterados. Em contraste, é **apresentado o conceito de `const`**, destacando que tentar reatribuir um valor a uma constante resulta em um erro de programação. Além disso, a aula **introduz operadores de atribuição e operadores matemáticos básicos**, como adição, subtração, multiplicação e divisão. O objetivo é ensinar como **realizar operações aritméticas** e armazenar os resultados em variáveis para exibição.

### Anotações

Código exemplo:

```javascript
let variavel = 10+10;
console.log("variavel =",variavel);

variavel = 10-10;
console.log("variavel =",variavel);

variavel = 10*10;
console.log("variavel =",variavel);

variavel = 10/10;
console.log("variavel =",variavel);
```

Saída:

```shell
variavel = 20
variavel = 0
variavel = 100
variavel = 1
```


## 🟩 Vídeo 04 - Exercício - Cálculo de Valor de Uma Viagem

![[formacao_javascript_developer-modulo_01-Curso_02-Video_04.webm|vid-60]]

Link do vídeo: https://web.dio.me/track/formacao-javascript-developer/course/primeiros-passos-com-javascript/learning/f8a28b03-7979-4273-8fd5-24b763fbee11?autoplay=1

O vídeo apresenta uma discussão sobre a **introdução de desafios práticos** após o aprendizado inicial de programação. O primeiro desafio sugerido é a **criação de um código para resolver uma fórmula aritmética** simples. Em seguida, o foco se volta para um segundo e mais detalhado exercício que exige a elaboração de um programa capaz de **calcular o custo total de uma viagem**. Para isso, os participantes devem considerar três variáveis essenciais: o **preço médio do combustível**, o **consumo médio do veículo por quilômetro** e a **distância total a ser percorrida**. O objetivo final é que o programa **imprima no console o valor exato, em reais, a ser gasto** na viagem.

### Anotações

```markdown
Faça um programa para calcular o valor de uma viagem.

Você lerá 3 variáveis. Sendo elas:
1 - [Preço do combustível];
2 - Gasto médio do combustível do carro por KM;
3 - Distância em KM da viagem;

Imprima no console o valor que será gasto de combustível para realizar esta viagem.
```


## 🟩 Vídeo 05 - Resolução Guiada do Exercício - Parte 1


Link do vídeo: https://web.dio.me/track/formacao-javascript-developer/course/primeiros-passos-com-javascript/learning/f8a28b03-7979-4273-8fd5-24b763fbee11?autoplay=1

O foco principal da lição é a **declaração de constantes e variáveis** para armazenar o preço do combustível, a média de consumo do carro e a distância da viagem, utilizando a convenção **camel case** e o ponto como separador decimal. Ao longo da discussão, eles **calculam os litros consumidos** e o **valor total gasto** na viagem, enfatizando a importância do raciocínio lógico e da correta atribuição de nomes para as variáveis para que o código seja legível.

### Anotações

Código:

```javascript
const precoCombustivel = 5.79;
const kmPorLitros = 10;
const distanciaEmKm = 100;

const litrosConsumidos = distanciaEmKm / kmPorLitros;
const valorGasto = litrosConsumidos * precoCombustivel;
console.log(valorGasto);
```

Saída:

```shell
57.9
```


## 🟩 Vídeo 06 - Resolução Guiada do Exercício - Parte 2



Link do vídeo:  https://web.dio.me/track/formacao-javascript-developer/course/primeiros-passos-com-javascript/learning/db11445e-9fb3-4255-a4a7-20fce7a1a0cc?autoplay=1

Os participantes discutem e demonstram um conceito de programação focado em **arredondamento de números de ponto flutuante**. A conversa começa com um cálculo hipotético do custo de combustível para uma longa viagem, que resulta em um número não inteiro, **R$ 762** e alguma fração. Para resolver o problema de números quebrados, eles introduzem o método **`.toFixed()`**, que é usado para **converter a variável em texto** e limitar a quantidade de casas decimais a dois, o que é apropriado para centavos. O diálogo é conduzido em um formato de aula interativa, onde o objetivo é um aprendizado **dinâmico e colaborativo**, resolvendo as dúvidas de um dos participantes em tempo real.

### Anotações

Código:

```javascript
const precoCombustivel = 5.79;
const kmPorLitros = 12;
const distanciaEmKm = 1580;

const litrosConsumidos = distanciaEmKm / kmPorLitros;
const valorGasto = litrosConsumidos * precoCombustivel;
console.log(valorGasto.toFixed(2));
```

Saída:

```shell
762.35
```



# Parte 2 - Estruturas Condicionais

## 🟩 Vídeo 01 - Conceito de Boolean e Condicionais


Link do vídeo:  https://web.dio.me/track/formacao-javascript-developer/course/primeiros-passos-com-javascript/learning/22b0eb2b-f250-4101-9bd5-a0becb5e2bf8?autoplay=1

**Aula técnica** focada nos fundamentos da **programação e lógica de algoritmos**. O instrutor inicia revisando conceitos anteriores, como a **declaração de variáveis** utilizando "let" e "const", além do uso de **operadores aritméticos**. O foco principal da lição é a introdução às **estruturas condicionais**, explicando como o sistema toma decisões baseadas em critérios específicos. Para ilustrar esse processo, o texto detalha o funcionamento dos **dados do tipo booleano**, que representam valores de verdadeiro ou falso. Por fim, são apresentados os **operadores de comparação**, como maior ou menor, essenciais para validar situações dentro de um código.


## 🟩 Vídeo 02 - Exemplo com Resto de Divisão


Link do vídeo: https://web.dio.me/track/formacao-javascript-developer/course/primeiros-passos-com-javascript/learning/ee712118-2f95-4d8b-93bf-1f1d3d053fb8?autoplay=1

O vídeo explica a **lógica de programação** utilizada para identificar **números pares e ímpares**. Os interlocutores demonstram como o **operador de resto da divisão**, representado pelo símbolo de porcentagem, é essencial para verificar se um valor é múltiplo de outro. Através de exemplos práticos em código, o texto ilustra que, se o resultado dessa operação for zero, o número é classificado como par. Além disso, a conversa aborda a importância da **precedência matemática** ao utilizar parênteses em expressões complexas. Por fim, explica-se que essas validações resultam em **tipos booleanos**, que assumem apenas os valores verdadeiro ou falso. Esta lição serve como uma introdução fundamental ao uso de **condicionais** e operadores aritméticos no desenvolvimento de software.

### Anotações

Código:

```javascript
const numero = 11;

const numeroPar = (numero % 2) === 0;

console.log(numeroPar);
```

Saída:

```shell
false
```


## 🟩 Vídeo 03 - Tipos de Operadores de Igualdade


Link do vídeo: https://web.dio.me/track/formacao-javascript-developer/course/primeiros-passos-com-javascript/learning/840d6b09-3655-42ec-a923-90550e8a1802?autoplay=1

O vídeo explica as distinções fundamentais entre os operadores de comparação na linguagem **JavaScript**, focando especialmente na diferença entre o uso de **dois e três sinais de igual**. O autor esclarece que, enquanto um único sinal serve para **atribuição de valores**, o uso de dois sinais realiza uma **conversão implícita de tipos**, permitindo que um número seja considerado igual a um texto. No entanto, a recomendação principal é a utilização de **três sinais de igual**, pois esse operador exige que tanto o **valor quanto o tipo** dos dados sejam idênticos. Essa abordagem mais rigorosa evita erros lógicos ao impedir comparações inconsistentes entre categorias diferentes de informação. Em suma, o conteúdo defende a **tipagem precisa** para garantir a integridade e a clareza do código desenvolvido.

### Anotações

Código:

```javascript
const numero = 2;

const numeroPar = (numero % 2) == '0';

console.log(numeroPar);
```


#### Interpretação do código JavaScript e diferença entre == e ===

##### Código analisado

```javascript
const numero = 2;

const numeroPar = (numero % 2) == '0';

console.log(numeroPar);
````

##### Passo a passo

Como o operador == é usado, o JavaScript realiza **coerção de tipo automática**.

###### Coerção de tipo (type coercion)

O operador == tenta converter os operandos para um tipo comum antes da comparação:

```javascript
'0' → 0
```

Logo, a comparação se torna:

```javascript
0 == 0 // true
```

Portanto:

```javascript
numeroPar === true
```

```javascript
console.log(numeroPar);
```

Saída no console:

```text
true
```

O resultado está correto (2 é par), porém **por um motivo implícito e perigoso**.

#### Diferença entre == e ===

##### == — igualdade frouxa (loose equality)

* Compara apenas o **valor**
* **Converte tipos automaticamente**
* Pode gerar resultados inesperados

Exemplos:

```javascript
0 == '0'          // true
false == 0        // true
null == undefined // true
```

##### `===` — igualdade estrita (strict equality)

* Compara **valor e tipo**
* **Não faz coerção de tipo**
* Comportamento previsível e seguro

Exemplos:

```javascript
0 === '0'          // false
false === 0        // false
null === undefined // false
```

#### Problema específico no algoritmo

Neste trecho:

```javascript
(numero % 2) == '0'
```

Temos:

* Lado esquerdo: `number`
* Lado direito: `string`

- O algoritmo **depende da coerção automática do JavaScript** para funcionar corretamente.
- ⚠️ Isso pode gerar bugs sutis se o código for alterado, reutilizado ou expandido.

#### Forma correta e recomendada ✅

```javascript
const numeroPar = (numero % 2) === 0;
```

Vantagens:

* Ambos os operandos são do tipo `number`
* Nenhuma conversão implícita
* Código mais claro, seguro e profissional

## Conclusão

O código original funciona **por coincidência**, graças à coerção automática do operador `==`.
O uso de `===` torna a intenção do algoritmo explícita, evita surpresas e é a prática recomendada em JavaScript moderno.

```
```



## 🟩 Vídeo 04 - Estrutura Condicional Com If e Else


Link do vídeo: 


## 🟩 Vídeo 05 - Estrutura Condicional com Else If


Link do vídeo: 


## 🟩 Vídeo 06 - Revisão e Dicas de Boas Práticas


Link do vídeo: 


## 🟩 Vídeo 07 - Exercício - Incremento do Cálculo de Valor de Uma Viagem


Link do vídeo: 

## 🟩 Vídeo 08 - Resolução Guiada do Exercício


Link do vídeo: 


# Parte 3 - Praticando com Variáveis, Operadores e Estruturas Condicionais

## 🟩 Vídeo 01 - Exercício 1 - Média de Notas


Link do vídeo:  


## 🟩 Vídeo 02 - Resolução Guiada do Exercício 1


Link do vídeo: 


## 🟩 Vídeo 03 - Exercício 2 - Cálculo do IMC


Link do vídeo: 


## 🟩 Vídeo 04 - Resolução Guiada do Exercício 2


Link do vídeo: 


## 🟩 Vídeo 05 - Exercício 3 - Valor Pago de um Produto


Link do vídeo: 


## 🟩 Vídeo 06 - Resolução Guiada do Exercício 3


Link do vídeo: 


##  Materiais de Apoio

### Materiais Complementares

Nossos materiais complementares e de apoio têm como objetivo apresentar informações para facilitar e enriquecer a sua jornada de aprendizado. Para isso, links úteis (como slides, repositórios e páginas oficiais) serão disponibilizados, além de dicas sobre como se destacar na DIO e no mercado de trabalho.

#### Dúvidas sobre como funciona o Node?

Assista esse curso com o expert Felipe Aguiar: [Clique aqui](https://web.dio.me/lives/como-criar-e-publicar-seu-primeiro-servidor-com-nodejs-getting-started-7)

#### Dicas / Links Úteis

Disponibilizamos alguns links úteis para que você possa se desenvolver ainda mais através de referências oficiais das tecnologias, páginas de documentação e/ou fóruns de discussão relevantes. Nesse contexto, seguem algumas sugestões:

##### Artigos / Fórum

- Compartilhe conteúdos técnicos através de **Artigos** (visíveis globalmente na plataforma da DIO).
- Compartilhe conquistas e dúvidas nos **Fóruns**, específicos para cada experiência educacional (como Bootcamps).

##### Rooms

- Se estiver inscrito(a) em uma experiência educacional na DIO (como um Bootcamp), você terá acesso ao **Rooms**.
- O Rooms é uma ferramenta de bate-papo em tempo real onde todos os inscritos podem interagir, compartilhando dúvidas e dicas (inclusive com imagens e trechos de código).

##### Pesquise na Web

- Pode parecer óbvio, mas é importante frisar a importância das **ferramentas de busca** no dia a dia de um profissional de TI.
- Se não encontrar o que procura dentro da DIO, pesquise na Internet (dê um Google).
- Muitas vezes você será levado a páginas incríveis como o **StackOverflow**, que podem salvar o seu dia.


# Certificado: Apresentação da Formação JavaScript Developer

- Link na plataforma: https://hermes.dio.me/certificates/YJFBQTUO.pdf
- Certificado em pdf: [[Certificado-Apresentacao.da.Formacao.JavaScript.Developer.pdf]]
