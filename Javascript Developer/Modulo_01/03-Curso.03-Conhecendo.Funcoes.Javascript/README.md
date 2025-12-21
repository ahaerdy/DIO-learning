## Instrutor:

- Renan Johannsen de Paula (Software Engineer Lead)
- Contato Linkedin: / [renanjpaula](https://www.linkedin.com/in/renanjpaula/)

# Parte 1 - O Poder das Funções na Qualidade do Seu Código

## 🟩 Vídeo 01 - Conceito Básico Sobre Funções

![[formacao_javascript_developer-modulo_01-Curso_03-Video_01.webm|vid-60]]

Link do vídeo:  https://web.dio.me/track/formacao-javascript-developer/course/conhecendo-funcoes-javascript/learning/85b3ec82-438b-4a29-974a-c4af7e12d9d0?autoplay=1

O conteúdo explica que funções são **blocos de código reutilizáveis** que podem ser invocados a qualquer momento, evitando a repetição desnecessária de instruções. São detalhados elementos essenciais como a **sintaxe de declaração**, o uso de **parâmetros** para tornar o código dinâmico e a diferença entre procedimentos simples e funções que possuem um **valor de retorno**. Através de exemplos práticos, como o cálculo de juros e potências, demonstra-se como essa estrutura organiza e facilita a resolução de problemas lógicos. O diálogo reforça a importância das funções para a criação de programas mais **eficientes e modulares**.

### Anotações

Código:
```javascript
function incrementarJuros(valor, percentualJuros) {
    const valorDeAcrecimo = (percentualJuros / 100) * valor;
    return valor + valorDeAcrecimo;
}

console.log(incrementarJuros(100, 10));
console.log(incrementarJuros(100, 15));
console.log(incrementarJuros(100, 20));
```

Saída:
```shell
110
115
120
```


## 🟩 Vídeo 02 - Como Organizar as Funções

![[formacao_javascript_developer-modulo_01-Curso_03-Video_02.webm|vid-60]]

Link do vídeo: https://web.dio.me/track/formacao-javascript-developer/course/conhecendo-funcoes-javascript/learning/65fe8c0e-9250-4b41-8f96-291ddd0a0b70?autoplay=1

Este vídeo detalha uma aula de programação focada na **organização de código** através do uso de **funções**. O instrutor explica a importância de centralizar a lógica principal em uma função denominada **main**, em vez de escrever comandos soltos no arquivo. Para ilustrar o conceito, ele propõe a criação de **funções auxiliares** que executam tarefas específicas, como o cálculo de juros ou do IMC. Essa abordagem visa aumentar a **legibilidade** e a estruturação lógica dos projetos à medida que se tornam mais complexos. Ao final, o professor orienta o aluno a **refatorar exercícios anteriores** para praticar essa nova metodologia de desenvolvimento.


## 🟩 Vídeo 03 - Exemplo Prático com Funções

![[formacao_javascript_developer-modulo_01-Curso_03-Video_03.webm|vid-60]]

Link do vídeo: https://web.dio.me/track/formacao-javascript-developer/course/conhecendo-funcoes-javascript/learning/37228bab-a45f-413b-b998-d2d7c4941805?autoplay=1

O vídeo transcrito detalha um processo de **refatoração de código** voltado para o cálculo do **Índice de Massa Corporal (IMC)**. O instrutor demonstra como decompor um script linear em **funções específicas**, isolando a lógica de cálculo da lógica de classificação diagnóstica. Essa técnica visa aumentar a **legibilidade e a manutenibilidade**, permitindo que cada trecho de código possua uma **responsabilidade única**. Ao final, é introduzida a criação de uma **função principal (main)** para organizar o fluxo do programa de maneira profissional. O objetivo central é ensinar como reduzir a **complexidade do software** através da modularização.

### Anotações

Código:
```javascript
function calcularImc(peso, altura) {
    return peso / Math.pow(altura, 2);
}

function classificarImc(imc) {
    if (imc < 18.5) {
        return 'Abaixo do peso';
    } else if (imc >= 18.5 && imc < 25) {
        return 'Peso normal';
    } else if (imc >= 25 && imc < 30) {
        return 'Acima do peso';
    } else if (imc >= 30 && imc < 40) {
        return 'Obeso';
    } else {
        return 'Obsedidade Grave';
    }
}

function main() {
  const peso = 75;
  const altura = 1.75;

  const imc = calcularImc(peso, altura);
  console.log(classificarImc(imc));
}

main();
```

Saída:
```shell
Peso normal
```


## 🟩 Vídeo 04 - Exercício - Funções Invocadas Imediatamente

![[formacao_javascript_developer-modulo_01-Curso_03-Video_04.webm|vid-60]]

Link do vídeo: https://web.dio.me/track/formacao-javascript-developer/course/conhecendo-funcoes-javascript/learning/7b488e2c-943b-42cb-a863-9ad9323c1731?autoplay=1

O vídeo explica que no **JavaScript** as funções são tratadas como **valores ou objetos**, permitindo que sejam atribuídas a variáveis e manipuladas como números ou textos. O autor demonstra como essa flexibilidade possibilita a **reatribuição de lógica** e a passagem de funções como argumentos. Um conceito central abordado é a **função imediatamente invocada**, que utiliza parênteses para declarar e executar um código de forma instantânea. Essa técnica é útil para criar **funções anônimas** que isolam o escopo e executam uma única vez, organizando o fluxo principal do programa. O conteúdo foca em estabelecer uma **estrutura padrão** para o desenvolvimento de códigos, priorizando a execução imediata do bloco principal.

### Anotações

Código:
```javascript
// Main
(function () {
    const peso = 75;
    const altura = 1.75;

    const imc = calcularImc(peso, altura);
    console.log(classificarImc(imc));
})();
```

#### ⚡Função Imediatamente Invocada (`IIFE`)

No JavaScript, funções são cidadãs de primeira classe (ou seja, podem ser atribuídas a variáveis, passadas como argumentos e retornadas de outras funções). No código:

```javascript
(function () {
    const peso = 75;
    const altura = 1.75;

    const imc = calcularImc(peso, altura);
    console.log(classificarImc(imc));
})();
```

Essa estrutura é chamada de `IIFE` (`Immediately Invoked Function Expression`). Ela é composta por:

- Uma função anônima (sem nome)
- Envolvida por parênteses para transformá-la em uma expressão
- Seguido por () para invocá-la imediatamente

Isso garante que o bloco seja executado assim que definido, sem depender de chamadas externas. É uma técnica usada para:

- Isolar escopo (evitar que variáveis como peso e altura vazem para o escopo global)
- Organizar o fluxo principal do programa
- Criar uma estrutura padrão para scripts que precisam rodar imediatamente

#### Estrutura padrão e organização

Ao encapsular o código principal em uma IIFE, o autor estabelece uma estrutura limpa e segura para iniciar o programa. Isso é especialmente útil em projetos maiores, onde o isolamento de escopo evita conflitos e facilita a manutenção.


# Parte 2 - Praticando com Funções

## 🟩 Vídeo 01 - Exercício Guiado 1 - Função escrevaMeuNome

![[formacao_javascript_developer-modulo_01-Curso_03-Video_05.webm|vid-60]]

Link do vídeo: https://web.dio.me/track/formacao-javascript-developer/course/conhecendo-funcoes-javascript/learning/6e321d99-6f80-4808-9689-2a054f428be6?autoplay=1

Durante o exercício, o tutor explica a importância de utilizar **nomes semânticos e verbos** para definir as funções, facilitando a leitura do código. Eles trabalham conceitos fundamentais como a **passagem de parâmetros**, a concatenação de strings e a correção de erros comuns, como esquecer as aspas em textos. Ao final, o aluno consegue **invocar a função** com sucesso, demonstrando como o mesmo bloco de código pode ser reutilizado para exibir diferentes resultados.

### Anotações

Código:
```javascript
function escrevaMeuNome(nome) {
    console.log('Meu nome é ' + nome);
}
escrevaMeuNome('Vitor');
escrevaMeuNome('Renan');
```

Saída:
```shell
Meu nome é Vitor
Meu nome é Renan
```


## 🟩 Vídeo 02 - Exercício Guiado 2 - Função verificarIdade

![[formacao_javascript_developer-modulo_01-Curso_03-Video_06.webm|vid-60]]

Link do vídeo: https://web.dio.me/track/formacao-javascript-developer/course/conhecendo-funcoes-javascript/learning/e444cb4e-81a4-4c21-8fb0-5a612a09ce90?autoplay=1

Durante a conversa, os interlocutores desenvolvem uma **função computacional** capaz de determinar se um indivíduo é **maior ou menor de idade**. O processo detalha a importância de utilizar **parâmetros e estruturas condicionais** para processar informações de forma automatizada. Ao final, a fonte enfatiza o conceito de **encapsulamento**, explicando que funções funcionam como pequenos programas independentes que podem ser **reutilizados a qualquer momento**. Essa abordagem didática demonstra como transformar um problema lógico em um **algoritmo funcional e organizado**.

### Anotações

Código:
```javascript
function verificarIdade(idade) {
  if (idade >= 18) {
    console.log('Maior');
  } else {
    console.log('Menor');
  }
}

verificarIdade(11);
```

Saída:
```shell
Menor
```



## 🟩 Vídeo 03 - Exercício Guiado 3 - Invocando Função Dentro de Outra Função

![[formacao_javascript_developer-modulo_01-Curso_03-Video_07.webm|vid-60]]

Link do vídeo: https://web.dio.me/track/formacao-javascript-developer/course/conhecendo-funcoes-javascript/learning/6374516c-0434-4a60-bade-2e112486b06c?autoplay=1

Os interlocutores exploram a diferença técnica entre **exibir um valor** no console e **retornar um dado** para que ele seja processado por outras partes do código. Durante a explicação, destaca-se que uma função pode ser tratada como uma **variável comum**, podendo ser passada como parâmetro ou invocada dentro de contextos maiores. O tutor demonstra como a ausência de um **retorno explícito** gera resultados indefinidos ao tentar concatenar textos. Por fim, a conversa evolui para a estratégia de **decompor problemas complexos** em pequenas funções reutilizáveis e organizadas.

### Anotações

Código:
```javascript
function verificarIdade(idade) {
  if (idade >= 18) {
    console.log('Maior');
  } else {
    console.log('Menor');
  }
}

verificarIdade(11);
```

Saída:
```shell
Menor
```


## 🟩 Vídeo 04 - Exercício Guiado 4 - Funções Para Calcular Preço


Link do vídeo: https://web.dio.me/track/formacao-javascript-developer/course/conhecendo-funcoes-javascript/learning/9b5bdb20-57a7-4c18-8d36-4077cd7761b1?autoplay=1



##  Materiais de Apoio

Nossos materiais complementares e de apoio têm como objetivo apresentar informações para facilitar e enriquecer a sua jornada de aprendizado. Para isso, links úteis (como slides, repositórios e páginas oficiais) serão disponibilizados, além de dicas sobre como se destacar na DIO e no mercado de trabalho 😉

Repositório Git

O Git é um conceito essencial no mercado de trabalho atualmente, por isso sempre reforçamos sua importância em nossa metodologia educacional. Por isso, todo código-fonte desenvolvido durante este conteúdo foi versionado no seguinte endereço para que você possa consultá-lo a qualquer momento:

https://github.com/digitalinnovationone/javascript-developer-m1*

* No contexto deste conteúdo, a(s) pasta(s) "01 - variaveis e operadores" e "02 - condicionais" possui(em) todo o código-fonte desenvolvido durante as aulas.

# Certificado: Conhecendo Funções JavaScript

- Link na plataforma: https://hermes.dio.me/certificates/MQNEERSU.pdf
- Certificado em pdf: [[Cerificado-Conhecendo.Funcoes.JavaScript.pdf]]
