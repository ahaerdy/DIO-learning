## Instrutor

- Stephany Nusch (Software Engineer at Microsoft)
- Contato Linkedin: / [stephanynusch](https://www.linkedin.com/in/stephanynusch/)

# Parte 1 - Atribuindo valores

## 🟩 Vídeo 01 - Apresentação do curso

<video width="60%" controls>
<source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_03-Curso.05-Video_01.webm" type="video/webm">
  Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo:  https://web.dio.me/track/tqi-fullstack-developer/course/variaveis-e-tipos-com-javascript/learning/f0fb48d7-aaab-4f2d-a8bf-08b426f345e5?autoplay=1

Este conteúdo apresenta o **início de um treinamento de JavaScript** focado em **variáveis e tipos de dados**, ministrado pela engenheira de software Stephanie Nush. A instrutora detalha sua **trajetória profissional e acadêmica** antes de explicar que o curso está estruturado em **três etapas distintas**, abrangendo desde conceitos teóricos até uma **atividade prática final**. Os alunos aprenderão a **manipular estruturas de dados** e a utilizar métodos essenciais para o desenvolvimento na linguagem. Além disso, a fonte destaca a importância do **uso do GitHub** para versionamento de código e incentiva a **interação na comunidade digital** para troca de conhecimentos. O objetivo central é capacitar o estudante a compreender como **atribuir valores e organizar informações** na memória do computador.

## 🟩 Vídeo 02 - Variáveis

<video width="60%" controls>
<source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_03-Curso.05-Video_02.webm" type="video/webm">
  Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/variaveis-e-tipos-com-javascript/learning/d775ea49-52c4-4f00-9c25-2e9c4f9429a0?autoplay=1

Esta fonte consiste em uma **aula introdutória de JavaScript** focada na **atribuição de valores** e no uso de **variáveis e constantes**. O instrutor detalha as diferenças fundamentais entre **VAR**, **LET** e **CONST**, destacando como o **escopo de bloco** e o conceito de **hoisting** impactam a segurança e a organização do código. Além das regras técnicas, o material apresenta **boas práticas de nomenclatura**, como as convenções **camelCase** e **snake_case**, essenciais para o mercado de trabalho. Por meio de exemplos práticos no **VS Code**, a lição demonstra por que o uso de **LET** é preferível em relação ao **VAR** no desenvolvimento moderno. O objetivo central é familiarizar o estudante com a manipulação de **endereços de memória** e a sintaxe correta da linguagem.

### Anotações

Esta aula introduz os conceitos fundamentais de atribuição de valores em JavaScript, abordando o uso de variáveis e constantes. O objetivo principal é capacitar o entendimento sobre como armazenar dados em endereços de memória utilizando as palavras reservadas `var`, `let` e `const`.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2025-12-29-09h11m41s261.jpg" alt="" width="840">
</p>

Nesta etapa inicial, definimos os objetivos da aula: compreender a definição de variáveis e constantes, além de explorar as diferenças práticas entre o uso de `var`, `const` e `let` no ecossistema JavaScript.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2025-12-29-09h11m50s214.jpg" alt="" width="840">
</p>

Existem diversas convenções de nomenclatura (cases) utilizadas na programação para a atribuição de valores. No JavaScript, a boa prática mais comum para nomear variáveis é o **camelCase**, onde a primeira letra da primeira palavra é minúscula e as subsequentes iniciam com maiúscula. Outras convenções incluem:

| Case Type | Example |
| --- | --- |
| Original Variable | `some awesome var` |
| **Camel Case** | `someAwesomeVar` |
| Snake Case | `some_awesome_var` |
| Kebab Case | `some-awesome-var` |
| Pascal Case | `SomeAwesomeVar` |
| Upper Case Snake Case | `SOME_AWESOME_VAR` |

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2025-12-29-09h14m02s032.jpg" alt="" width="840">
</p>

Iniciamos agora o estudo aprofundado sobre as diferenças entre `var` e `let`, focando em como essas palavras reservadas gerenciam o escopo e o ciclo de vida das variáveis no código.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2025-12-29-09h18m41s984.jpg" alt="" width="840">
</p>

A principal diferença entre `var` e `let` reside no escopo. Enquanto `var` possui escopo global ou de função, o `let` respeita o escopo de bloco (como dentro de uma condicional `if`). No exemplo abaixo, vemos que a variável `a` (declarada com `var`) é alterada globalmente dentro do bloco, enquanto a variável `b` (declarada com `let`) mantém seu valor original fora do bloco onde foi redefinida.

```javascript
var a = 1;
var b = 2;

if (a === 1) {
  var a = 11; // escopo global
  let b = 22; // escopo de bloco (dentro do if)

  console.log(a); // 11
  console.log(b); // 22
}

console.log(a); // 11
console.log(b); // 2

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2025-12-29-09h20m08s819.jpg" alt="" width="840">
</p>

O conceito de **Hoisting** (elevação) permite que variáveis declaradas com `var` sejam utilizadas antes de sua linha de declaração formal no código. O JavaScript "eleva" a declaração para o topo, permitindo que o código abaixo funcione sem erros, embora a atribuição ocorra na ordem escrita.

```javascript
numberOne = 1;
console.log(numberOne + 2);

var numberOne;

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2025-12-29-09h20m36s985.jpg" alt="" width="840">
</p>

Diferente do `var`, o `let` não permite o uso da variável antes de sua inicialização. Tentar acessar uma variável declarada com `let` antes da linha onde ela é definida resultará em um erro de referência (`ReferenceError`), pois o hoisting não funciona da mesma forma para `let`.

```javascript
numberOne = 1;
console.log(numberOne + 2);

let numberOne;

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2025-12-29-09h21m21s273.jpg" alt="" width="840">
</p>

Para corrigir o erro anterior e utilizar o `let` corretamente, devemos primeiro declarar a variável e só então realizar a atribuição e o uso dos dados. Isso garante que o endereço de memória esteja pronto antes do acesso.

```javascript
let numberOne;

numberOne = 1;

console.log(numberOne + 2);

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2025-12-29-09h23m22s194.jpg" alt="" width="840">
</p>

Neste exemplo prático, testamos a redeclaração e o escopo. Temos variáveis globais `firstName` e `lastName`. Dentro do bloco `if`, tentamos alterar ambas. Como `firstName` usa `var`, a alteração impacta o escopo global. Já `lastName` usa `let`, criando uma nova instância apenas para o bloco interno.

```javascript
var firstName = "João";
var lastName = "Souza";

if (firstName === "João") {
  var firstName = "Pedro";
  let lastName = "Silva";
}

console.log(firstName, lastName);

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2025-12-29-09h24m17s078.jpg" alt="" width="840">
</p>

Ao executar o código anterior, o resultado no terminal será `Pedro Souza`. Isso prova que a variável `firstName` foi de fato alterada para "Pedro" globalmente devido ao uso de `var`. Por outro lado, o `lastName` impresso é o global ("Souza"), pois o "Silva" declarado com `let` existiu apenas dentro do bloco `if`.

```bash
node index.js
Pedro Souza

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2025-12-29-09h25m36s313.jpg" alt="" width="840">
</p>

Para demonstrar a reatribuição dentro de um escopo de bloco, podemos declarar uma variável com `let` e mudar seu valor sem redeclará-la. No console, vemos o valor atualizado que foi atribuído por último dentro da lógica do bloco.

Código:
```javascript
if (firstName === "João") {
  let lastName = "Rodrigues";
  var firstName = "Pedro";
  lastName = "Silva"; // reatribuição
  
  console.log(lastname);
}

console.log(firstName, lastName);
```

Saída:
```bash
$ node index.js

Silva
Pedro Souza
```


## 🟩 Vídeo 03 - Constantes

<video width="60%" controls>
<source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_03-Curso.05-Video_03.webm" type="video/webm">
  Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo:  https://web.dio.me/track/tqi-fullstack-developer/course/variaveis-e-tipos-com-javascript/learning/4d4a2f42-858c-4033-9d1d-40360ef021bb?autoplay=1

O vídeo aborda as características fundamentais das **constantes** na linguagem de programação JavaScript, destacando que elas possuem **escopo de bloco** e não permitem o fenômeno de **hoisting**. A explicação enfatiza que, diferentemente de outros tipos de variáveis, uma constante exige **inicialização imediata** e seu valor permanece **imutável**, sendo impossível realizar a reatribuição ou redeclaração durante a execução do código. O material também detalha a convenção de nomenclatura **Snake Upper Case**, onde utiliza-se letras maiúsculas separadas por sublinhados para identificar esses elementos. Por meio de exemplos práticos, demonstra-se que tentar acessar uma constante antes de sua definição resulta em erro, pois o sistema não eleva a declaração automaticamente. Assim, o conteúdo serve como um guia técnico sobre a **estabilidade e integridade de dados** no desenvolvimento de software.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2025-12-29-10h18m58s749.jpg" alt="" width="840">
</p>

Nesta etapa da aula, iniciamos o estudo sobre as **constantes**. Elas representam um tipo fundamental de declaração de variáveis no JavaScript, inseridas dentro do contexto de **Variáveis e Tipos**, e possuem comportamentos específicos que as diferenciam das declarações feitas com `var` ou `let`.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2025-12-29-10h19m06s730.jpg" alt="" width="840">
</p>

As constantes no JavaScript possuem três características principais que definem seu funcionamento:

* **SNAKE_UPPER_CASE**: Por convenção, as constantes devem ser declaradas com todas as letras maiúsculas e as palavras separadas por sublinhados (underlines).
* **Escopo de bloco**: Assim como o `let`, o acesso à constante é restrito ao bloco onde ela foi criada.
* **Não faz hoisting**: A declaração não é "elevada" ao topo do código; ela precisa ser declarada antes de ser utilizada.

```javascript
const DAYS_IN_A_WEEK = 7;
const MY_NAME = "Kieran";

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2025-12-29-10h20m00s422.jpg" alt="" width="840">
</p>

Uma característica fundamental das constantes é a sua imutabilidade em relação à redeclaração. Uma vez que um identificador foi definido como uma constante, ele não pode ser declarado novamente dentro do mesmo escopo. Tentar fazer isso resultará em um erro de sintaxe, interrompendo a execução do código.

```javascript
const FIRST_NAME = "Stephany";

const FIRST_NAME = "Julia";
// SyntaxError: Identifier 'FIRST_NAME' has already been declared

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2025-12-29-10h20m08s067.jpg" alt="" width="840">
</p>

Diferente das variáveis globais ou declaradas com `var`, as constantes não permitem o uso antes da sua definição formal. Tentar atribuir um valor a um nome que será posteriormente definido como `const` ou tentar acessá-lo sem a devida inicialização gera falhas, pois o JavaScript não realiza o hoisting (subida da declaração) para este tipo de dado.

```javascript
FIRST_NAME = "Stephany";
console.log(FIRST_NAME);

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2025-12-29-10h20m25s111.jpg" alt="" width="840">
</p>

Diferente de variáveis que podem ser apenas declaradas para receber um valor posteriormente, a **constante exige uma inicialização obrigatória**. Você deve obrigatoriamente atribuir um valor no momento exato da declaração. Como o valor de uma constante não pode ser reatribuído ao longo do ciclo de vida do código, o JavaScript exige que ela já nasça com seu valor definido.

```javascript
const FIRST_NAME;
// SyntaxError: Missing initializer in const declaration

```

#### Comparativo de Variáveis e Constantes

| Característica | `var` | `let` | `const` (Constantes) |
| --- | --- | --- | --- |
| **Escopo** | *Não detalhado no trecho* | *Não detalhado no trecho* | Escopo de bloco |
| **Hoisting** | Sim (age como global) | Não faz hoisting | Não faz hoisting |
| **Reatribuição** | *Não detalhado no trecho* | *Não detalhado no trecho* | Não permitida |
| **Redeclaração** | *Não detalhado no trecho* | *Não detalhado no trecho* | Não permitida |
| **Inicialização** | Opcional | Opcional | Obrigatória na declaração |
| **Convenção de Nome** | *Não detalhado no trecho* | *Não detalhado no trecho* | SNAKE_UPPER_CASE |

---

### Pontos de Atenção para Constantes:

* **Imutabilidade**: O valor definido no momento da inicialização permanecerá o mesmo durante todo o ciclo de vida do código.
* **Erro de Referência**: Tentar usar uma constante antes de declará-la resultará em erro, pois o JavaScript não "sobe" a declaração para você.
* **Sintaxe**: A falta de um valor inicial em uma declaração `const` gera um `SyntaxError` imediato.

# Parte 2 - Tipos

## 🟩 Vídeo 01 - Estrutura de dados

<video width="60%" controls>
<source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo_03-Curso.05-Video_04.webm" type="video/webm">
  Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/variaveis-e-tipos-com-javascript/learning/4145f810-f3f5-4519-9593-36eddebe75f4?autoplay=1

### Anotações





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
