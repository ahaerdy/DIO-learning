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

![[formacao_javascript_developer-modulo_01-Curso_02-Video_05.webm|vid-60]]

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

![[formacao_javascript_developer-modulo_01-Curso_02-Video_06.webm|vid-60]]

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

## 🟩 Vídeo 07 - Conceito de Boolean e Condicionais

![[formacao_javascript_developer-modulo_01-Curso_02-Video_07.webm|vid-60]]

Link do vídeo:  https://web.dio.me/track/formacao-javascript-developer/course/primeiros-passos-com-javascript/learning/22b0eb2b-f250-4101-9bd5-a0becb5e2bf8?autoplay=1

**Aula técnica** focada nos fundamentos da **programação e lógica de algoritmos**. O instrutor inicia revisando conceitos anteriores, como a **declaração de variáveis** utilizando "let" e "const", além do uso de **operadores aritméticos**. O foco principal da lição é a introdução às **estruturas condicionais**, explicando como o sistema toma decisões baseadas em critérios específicos. Para ilustrar esse processo, o texto detalha o funcionamento dos **dados do tipo booleano**, que representam valores de verdadeiro ou falso. Por fim, são apresentados os **operadores de comparação**, como maior ou menor, essenciais para validar situações dentro de um código.


## 🟩 Vídeo 08 - Exemplo com Resto de Divisão

![[formacao_javascript_developer-modulo_01-Curso_02-Video_08.webm|vid-60]]

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


## 🟩 Vídeo 09 - Tipos de Operadores de Igualdade

![[formacao_javascript_developer-modulo_01-Curso_02-Video_09.webm|vid-60]]

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

##### === — igualdade estrita (strict equality)

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

#### Conclusão

O código original funciona **por coincidência** (‼️)  graças à coerção automática do operador ==.
O uso de ===  torna a intenção do algoritmo explícita, evita surpresas e é a prática recomendada em JavaScript moderno.


## 🟩 Vídeo 10 - Estrutura Condicional Com If e Else

![[formacao_javascript_developer-modulo_01-Curso_02-Video_10.webm|vid-60]]

Link do vídeo: https://web.dio.me/track/formacao-javascript-developer/course/primeiros-passos-com-javascript/learning/ea8cea3f-1833-46ac-bfb1-bd622bacd3cc?autoplay=1

O vídeo explica detalhadamente como o **operador "if"** valida uma condição lógica para decidir se um bloco de código deve ser executado ou ignorado. Os participantes demonstram o uso da **exclamação para negar** valores booleanos, transformando afirmações verdadeiras em falsas. Além disso, a conversa evolui para a introdução do **comando "else"**, que serve como uma alternativa automática quando a condição inicial não é atendida. Para ilustrar esses conceitos técnicos, são utilizados exemplos práticos simples, como a verificação de **números pares, ímpares** ou divisíveis por cinco.

### Anotações

Código:
```javascript
const numero = 0;
const numeroPar = (numero % 2) === 0;

if (numeroPar) {
    console.log('Par');
} else {
    console.log('Impar');
}
```

#### O que o código faz

1. `const numero = 0;`  
   Define a constante `numero` com o valor **0**.

2. `const numeroPar = (numero % 2) === 0;`  
   Verifica se o número é par:  
   - `numero % 2` calcula o resto da divisão por 2.  
   - Se o resto for `0`, o número é par.  
   - A comparação usa === para garantir que o tipo e o valor sejam iguais.

3. `if (numeroPar) { ... } else { ... }`  
   - Se `numeroPar` for `true`, imprime `'Par'`.  
   - Caso contrário, imprime `'Impar'`.

#### Saída esperada

Como `0 % 2 === 0`, o valor de `numeroPar` será `true`.  
Portanto, o console exibirá:

```
Par
```

#### Observação

- O uso de === é recomendado para evitar coerção de tipos inesperada.
- O código está em português, tornando-o acessível para falantes da língua.

```javascript
// Resultado final:
Par
```


## 🟩 Vídeo 11 - Estrutura Condicional com Else If

![[formacao_javascript_developer-modulo_01-Curso_02-Video_11.webm|vid-60]]

Link do vídeo: https://web.dio.me/track/formacao-javascript-developer/course/primeiros-passos-com-javascript/learning/9560b66d-953b-44f7-ba80-feba07976c47?autoplay=1


O material apresenta uma explicação didática sobre a implementação de **estruturas condicionais** em programação, utilizando exemplos práticos para demonstrar o controle de fluxo. O instrutor detalha como utilizar o **if**, o **else if** e o **else** para tratar diferentes cenários, como a validação de números inválidos que fogem de um intervalo específico. É enfatizada a importância de encadear as condições corretamente para evitar que o código execute comandos indesejados simultaneamente. Além disso, o texto destaca que essa **lógica de decisão** é universal, sendo aplicada de forma semelhante em diversas linguagens de programação. Por fim, discute-se a possibilidade de incluir múltiplas verificações, alertando que, embora funcional, o excesso de condições pode prejudicar a **legibilidade do código**.

### Anotações

Código:
```javascript
const numero = 5;
const numeroDivisivelPor5 = (numero % 5) === 0;

if (numero === 0) {
    console.log('O número é inválido');
} else if (numeroDivisivelPor5) {
    console.log('Sim');
} else {
    console.log('Não');
}
```

Saida:
```shell
Sim
```

#### O que o código faz

1. **Define o número**
   ```javascript
   const numero = 5;
   ```
   A constante `numero` recebe o valor **5**.

2. **Verifica se é divisível por 5**
   ```javascript
   const numeroDivisivelPor5 = (numero % 5) === 0;
   ```
   - Calcula o resto da divisão de `numero` por 5.
   - Se o resto for `0`, significa que o número é divisível por 5.
   - O resultado da comparação é armazenado em `numeroDivisivelPor5` como um valor booleano (`true` ou `false`).

3. **Estrutura condicional**
   ```javascript
   if (numero === 0) {
       console.log('O número é inválido');
   } else if (numeroDivisivelPor5) {
       console.log('Sim');
   } else {
       console.log('Não');
   }
   ```
   - Se `numero` for igual a `0`, imprime `'O número é inválido'`.
   - Se for divisível por 5, imprime `'Sim'`.
   - Caso contrário, imprime `'Não'`.

####  Saída esperada

Como `numero` é `5`, e `5 % 5 === 0`, a variável `numeroDivisivelPor5` será `true`.  
Portanto, o console exibirá:

```
Sim
```


## 🟩 Vídeo 12 - Revisão e Dicas de Boas Práticas

![[formacao_javascript_developer-modulo_01-Curso_02-Video_12.webm|vid-60]]

Link do vídeo: https://web.dio.me/track/formacao-javascript-developer/course/primeiros-passos-com-javascript/learning/169bfa8f-e391-4b21-aeb7-45112a9f5f71?autoplay=1

O instrutor explica como esses valores binários, representando **verdadeiro ou falso**, podem armazenar resultados de operações lógicas, como verificar se um número é divisível por outro. Um ponto central da discussão é a importância da **legibilidade do código**, sugerindo que nomear variáveis de forma clara torna a lógica mais compreensível do que inserir condições complexas diretamente nos comandos. O diálogo também aborda **convenções de nomenclatura**, recomendando o uso de prefixos como "is" ou "é" para indicar que a variável representa um estado ou pergunta. Por fim, o conteúdo enfatiza que escrever um **código limpo** facilita a manutenção e o entendimento mental do fluxo de execução pelo programador.


## 🟩 Vídeo 13 - Exercício - Incremento do Cálculo de Valor de Uma Viagem

![[formacao_javascript_developer-modulo_01-Curso_02-Video_13.webm|vid-60]]

Link do vídeo: [[formacao_javascript_developer-modulo_01-Curso_02-Video_13.webm]]vídeo: https://web.dio.me/track/formacao-javascript-developer/course/primeiros-passos-com-javascript/learning/4bc658d5-9f54-4e27-817b-bfecf3fb218f?autoplay=1

O vídeo apresenta um **desafio de programação** que expande um exercício anterior sobre o cálculo de **custos de viagem**. O objetivo principal é implementar uma **estrutura condicional** para lidar com veículos do tipo **flex**, que podem ser abastecidos com diferentes tipos de energia. O sistema agora deve processar **cinco variáveis** distintas, incluindo os preços específicos do **etanol e da gasolina**. Dependendo do combustível escolhido pelo usuário, o programa deve aplicar o valor correspondente para realizar o cálculo final. Ao concluir a lógica, o resultado do **gasto total** da jornada deve ser exibido no console para o usuário.

### Anotações

Desafio:
```javascript
Faça um programa para calcular o valor de uma viagem.

Você terá 3 variáveis. Sendo elas:

1. Preço do etanol;
2. Preço da gasolina;
3. O tipo de combustível que está no seu carro;
4. Gasto médio de combustível do carro por KM;
5. Distância em KM da viagem;

Imprima no console o valor que será gasto para realizar esta viagem.
```


## 🟩 Vídeo 14 - Resolução Guiada do Exercício

![[formacao_javascript_developer-modulo_01-Curso_02-Video_14.webm|vid-60]]

Link do vídeo:  https://web.dio.me/track/formacao-javascript-developer/course/primeiros-passos-com-javascript/learning/a3bcf4ad-8356-41ea-83a5-5063fd6ae41a?autoplay=1

O vídeo apresenta um **tutorial prático de programação** focado no desenvolvimento de um sistema para **calcular gastos de viagem**. Dois interlocutores discutem a implementação de uma **lógica condicional** para lidar com diferentes tipos de combustíveis, como **etanol e gasolina**. Eles detalham o uso de variáveis para armazenar preços, distância e consumo médio, explicando como as estruturas de **controle de fluxo**, como o *if* e o *else*, definem o cálculo correto. Durante o diálogo, são abordados conceitos técnicos fundamentais como **escopo de variáveis**, manipulação de strings e a importância da formatação do código. O objetivo final é aprimorar um programa básico para que ele identifique automaticamente qual valor aplicar com base na escolha do usuário.

### Anotações

Código:
```javascript
const precoEtanol = 5.79;
const precoGasolina = 6.66;
const kmPorLitros = 10;
const distanciaEmKm = 100;
const tipoCombustivel = 'Etanol';

const litrosConsumidos = distanciaEmKm / kmPorLitros;

if (tipoCombustivel === 'Etanol') {
    const valorGasto = litrosConsumidos * precoEtanol;
    console.log(valorGasto.toFixed(2));
} else {
    const valorGasto = litrosConsumidos * precoGasolina;
    console.log(valorGasto.toFixed(2));
}
```

Saída:
```shell
57.90
```


#### Explicação passo a passo do programa – Cálculo do valor de uma viagem

Este programa em JavaScript tem como objetivo calcular o valor total gasto em combustível em uma viagem, considerando a distância percorrida, o consumo médio do veículo, o tipo de combustível utilizado e o preço correspondente.

#### Declaração dos preços dos combustíveis

```javascript
const precoEtanol = 5.79;
const precoGasolina = 6.66;
```

São declaradas duas constantes que armazenam o preço por litro de cada combustível.
Utiliza-se `const` porque esses valores não sofrem alteração durante a execução do programa.

#### Definição do consumo médio do veículo

```javascript
const kmPorLitros = 10;
```

Essa variável indica que o veículo percorre 10 quilômetros para cada litro de combustível consumido.

#### Definição da distância da viagem

```javascript
const distanciaEmKm = 100;
```

Representa a distância total da viagem, em quilômetros.

#### Definição do tipo de combustível

```javascript
const tipoCombustivel = 'Etanol';
```

Indica qual combustível está sendo utilizado no veículo.
O valor é uma string e pode ser `'Etanol'` ou `'Gasolina'`.
Essa variável será usada como critério na estrutura condicional.

#### Cálculo da quantidade de litros consumidos

```javascript
const litrosConsumidos = distanciaEmKm / kmPorLitros;
```

Aqui é calculada a quantidade total de litros necessários para realizar a viagem.

Exemplo prático:
Se o carro faz 10 km por litro e a viagem tem 100 km, serão consumidos 10 litros.

Esse cálculo é feito antes do `if` porque ele independe do tipo de combustível.

#### Estrutura condicional para escolha do combustível

```javascript
if (tipoCombustivel === 'Etanol') {
```

O programa verifica se o tipo de combustível informado é etanol.
O operador === é utilizado para realizar uma comparação estrita, avaliando valor e tipo.

Se a condição for verdadeira, o código dentro do bloco `if` será executado.

#### Cálculo do valor gasto com etanol

```javascript
const valorGasto = litrosConsumidos * precoEtanol;
console.log(valorGasto.toFixed(2));
```

Caso o combustível seja etanol, o programa multiplica a quantidade de litros consumidos pelo preço do etanol.
O método `toFixed(2)` é usado para formatar o valor com duas casas decimais antes de exibi-lo no console.

#### Cálculo do valor gasto com gasolina

```javascript
} else {
    const valorGasto = litrosConsumidos * precoGasolina;
    console.log(valorGasto.toFixed(2));
}
```

Se o combustível não for etanol, o programa assume que é gasolina.
O cálculo segue a mesma lógica, alterando apenas o preço utilizado na multiplicação.

#### Observação importante sobre escopo de variáveis

A variável `valorGasto` é declarada dentro dos blocos `if` e `else`.
Por estar em escopo de bloco, ela só existe dentro das chaves onde foi definida.

Se o `console.log` estivesse fora desses blocos, ocorreria o erro `valorGasto is not defined`.
Por isso, a exibição do resultado precisa estar dentro de cada condição.

#### Conclusão

O programa demonstra conceitos fundamentais de JavaScript, como declaração de constantes, operações matemáticas, estruturas condicionais, uso de strings para controle de fluxo, escopo de variáveis e formatação numérica.

A lógica central das condicionais é executar diferentes trechos de código com base em uma condição, neste caso, o tipo de combustível utilizado na viagem.



# Parte 3 - Praticando com Variáveis, Operadores e Estruturas Condicionais

## 🟩 Vídeo 1 - Exercício 1 - Média de Notas

![[formacao_javascript_developer-modulo_01-Curso_02-Video_15.webm|vid-60]]

Link do vídeo:  https://web.dio.me/track/formacao-javascript-developer/course/primeiros-passos-com-javascript/learning/557eff5c-1262-452d-88a7-351ff91828d1?autoplay=1

O objetivo central é orientar os alunos no desenvolvimento de um **algoritmo acadêmico** para calcular a média aritmética simples de três notas semestrais. O exercício exige que o sistema classifique o desempenho do estudante em categorias de **reprovação, recuperação ou aprovação**, baseando-se em faixas numéricas específicas. Os professores incentivam uma abordagem lógica, sugerindo que os programadores dividam o problema em etapas de **entrada, processamento e saída de dados**. Por fim, a dinâmica busca ensinar como transformar requisitos teóricos em uma **solução funcional de software**.

### Anotações

Desafio:
```shell
1) Faça um algoritmo que dado as 3 notas tiradas por um aluno em um semestre da faculdade calcule e imprima a sua média e a sua classificação conforme a tabela abaixo.

Média = (nota 1 + nota 2 + nota 3) / 3;

Classificação:
- Média menor que 5, reprovação;
- Média entre 5 e 7, recuperação;
- Média acima de 7, passou de semestre;
```

## 🟩 Vídeo 16 - Resolução Guiada do Exercício 1

![[formacao_javascript_developer-modulo_01-Curso_02-Video_16.webm|vid-60]]

Link do vídeo: https://web.dio.me/track/formacao-javascript-developer/course/primeiros-passos-com-javascript/learning/2b484f80-9d91-496d-a51a-592c0b2784a8?autoplay=1

O vídeo trata de um algoritmo simples em **JavaScript** para calcular a média escolar. A conversa detalha o processo de **declaração de variáveis**, a importância da **precedência dos operadores matemáticos** e a lógica de **estruturas condicionais** para determinar o status do aluno. Através da prática, os interlocutores exploram como aplicar regras de negócio para classificar resultados entre reprovação, recuperação e aprovação. O diálogo enfatiza que a **sintaxe da programação** pode ser esquecida sem uso constante, reforçando a necessidade de exercícios contínuos para a fixação do aprendizado. Por fim, a interação demonstra a execução do código via **Node.js**, validando os resultados obtidos no terminal.

### Anotações

Desafio:
```shell
1) Faça um algoritmo que dado as 3 notas tiradas por um aluno em um semestre da faculdade calcule e imprima a sua média e a sua classificação conforme a tabela abaixo.

Média = (nota 1 + nota 2 + nota 3) / 3;

Classificação:
- Média menor que 5, reprovação;
- Média entre 5 e 7, recuperação;
- Média acima de 7, passou de semestre;
```

Código:
```javascript
const nota1 = 7;
const nota2 = 7;
const nota3 = 8;
const media = (nota1 + nota2 + nota3) / 3;
console.log(media.toFixed(2));

if (media < 5) {
    console.log('Reprovação');
} else if (media >= 5 && media <= 7) {
    console.log('Recuperação');
} else {
    console.log('Passou de semestre');
}
```

Saída:
```shell
7.33
Passou de semestre
```


## 🟩 Vídeo 17 - Exercício 2 - Cálculo do IMC

![[formacao_javascript_developer-modulo_01-Curso_02-Video_17.webm|vid-60]]

Link do vídeo: https://web.dio.me/track/formacao-javascript-developer/course/primeiros-passos-com-javascript/learning/2b484f80-9d91-496d-a51a-592c0b2784a8?autoplay=1

Os instrutores explicam que o exercício utiliza a **fórmula matemática do peso dividido pela altura ao quadrado** para determinar o índice de massa corporal de um adulto. Além do cálculo, o desafio proposto exige que os alunos classifiquem o resultado final em categorias como **peso normal, sobrepeso ou obesidade**, seguindo as diretrizes da Organização Mundial da Saúde. A fonte destaca a importância de comparar diferentes **lógicas de programação** para chegar ao mesmo objetivo prático. O diálogo encoraja os estudantes a praticarem a resolução de problemas por meio de **pair programming** antes de conferirem o resultado final.

### Anotações

Desafio:
```shell
2) O IMC - Índice de Massa Corporal é um critério da Organização Mundial de Saúde para dar uma indicação sobre a condição de peso de uma pessoa adulta.

Formula do IMC:
IMC = peso / (altura * altura)

Elabore um algoritmo que dado o peso e a altura de um adulto mostre sua condição de acordo com a tabela abaixo.

IMC em adultos Condição:
- Abaixo de 18.5 Abaixo do peso;
- Entre 18.5 e 25 Peso normal;
- Entre 25 e 30 Acima do peso;
- Entre 30 e 40 Obeso;
- Acima de 40 Obesidade Grave;
```


## 🟩 Vídeo 18 - Resolução Guiada do Exercício 2

![[formacao_javascript_developer-modulo_01-Curso_02-Video_18.webm|vid-60]]

Link do vídeo: https://web.dio.me/track/formacao-javascript-developer/course/primeiros-passos-com-javascript/learning/6469f29b-e3c5-4f34-8012-150229babe53?autoplay=1

O vídeo apresenta um algoritmo para o cálculo do **Índice de Massa Corporal (IMC)** utilizando a linguagem JavaScript. Durante a conversa, o instrutor orienta o aluno sobre como receber **dados de entrada**, realizar operações matemáticas e utilizar a biblioteca **Math.pow** para potências. A explicação aborda convenções de nomenclatura, como o uso de **letras maiúsculas para constantes**, e introduz conceitos de **estruturas condicionais** para classificar os resultados. O exercício prático demonstra como o código reage a diferentes valores de **peso e altura**, simulando cenários reais de saúde. Ao final, a interação reforça que a **prática constante** e a repetição são fundamentais para a fluidez no desenvolvimento de algoritmos.

### Anotações

Código:
```javascript
const peso = 70;
const altura = 1.70;
const imc = peso / Math.pow(altura, 2);
console.log(imc.toFixed(2));

if (imc < 18.5) {
    console.log('Abaixo do peso');
} else if (imc >= 18.5 && imc < 25) {
    console.log('Peso normal');
} else if (imc >= 25 && imc < 30) {
    console.log('Acima do peso');
} else if (imc >= 30 && imc < 40) {
    console.log('Obeso');
} else {
    console.log('Obsedidade Grave');
}
```

Saída:
```javascript
24.22
Peso normal
```


## 🟩 Vídeo 19 - Exercício 3 - Valor Pago de um Produto

![[formacao_javascript_developer-modulo_01-Curso_02-Video_19.webm|vid-60]]

Link do vídeo: https://web.dio.me/track/formacao-javascript-developer/course/primeiros-passos-com-javascript/learning/b5e101ce-9f6b-48af-af4d-51f43177ff9f?autoplay=1

Este conteúdo apresenta um **exercício de lógica de programação** focado no desenvolvimento de um **algoritmo de vendas**. O objetivo principal é calcular o **valor final de um produto** baseando-se no preço de etiqueta e em diferentes **métodos de pagamento**. Pagamentos à vista oferecem **descontos variados**, sendo o maior benefício concedido para transações via **Pix ou dinheiro**. Por outro lado, o parcelamento em até duas vezes mantém o **preço original**, enquanto prazos maiores aplicam um **acréscimo de juros**. A fonte utiliza uma linguagem didática para explicar como as condicionais influenciam o resultado financeiro da compra.

### Anotações

Desafio:
```shell
3) Elabore um algoritmo que calcule o que deve ser pago por um produto, considerando o preço normal de etiqueta e a escolha da condição de pagamento.
Utilize os códigos da tabela a seguir para ler qual a condição de pagamento escolhida e efetuar o cálculo adequado.

Código Condição de pagamento:
- À vista Débito, recebe 10% de desconto;
- À vista no Dinheiro ou PIX, recebe 15% de desconto;
- Em duas vezes, preço normal de etiqueta sem juros;
- Acima de duas vezes, preço normal de etiqueta mais juros de 10%;
```


## 🟩 Vídeo 20 - Resolução Guiada do Exercício 3


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
