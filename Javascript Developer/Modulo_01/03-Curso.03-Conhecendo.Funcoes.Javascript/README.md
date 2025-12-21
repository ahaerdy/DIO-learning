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


## 🟩 Vídeo 03 - Exemplo Prático com Funções


Link do vídeo: https://web.dio.me/track/formacao-javascript-developer/course/conhecendo-funcoes-javascript/learning/37228bab-a45f-413b-b998-d2d7c4941805?autoplay=1



## 🟩 Vídeo 04 - Exercício - Funções Invocadas Imediatamente


Link do vídeo: https://web.dio.me/track/formacao-javascript-developer/course/conhecendo-funcoes-javascript/learning/7b488e2c-943b-42cb-a863-9ad9323c1731?autoplay=1


# Parte 2 - Praticando com Funções

## 🟩 Vídeo 01 - Exercício Guiado 1 - Função escrevaMeuNome


Link do vídeo:  


## 🟩 Vídeo 02 - Exercício Guiado 2 - Função verificarIdade


Link do vídeo: 


## 🟩 Vídeo 03 - Exercício Guiado 3 - Invocando Função Dentro de Outra Função


Link do vídeo: 


## 🟩 Vídeo 04 - Exercício Guiado 4 - Funções Para Calcular Preço


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


# Certificado: 

- Link na plataforma: 
- Certificado em pdf: 
