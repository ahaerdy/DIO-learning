# Desafio 1 - Princípios Básicos

e

- Daenerys é a khaleesi dos Dothraki. Juntamente com Drogon, eles vão atrás do Tyrion, para tentar dominar Westeros. Ela possui, além do seu dragãozinho, um rastreador que mede o nível de energia de qualquer ser vivo. Todos os seres com o nível menor ou igual a 8000, ela considera como se fosse um inseto. Quando passa deste valor, que foi o caso do Drogon, ela se espanta e grita “Mais de 8000”. Baseado nisso, utilize a mesma tecnologia e analise o nível de energia dos seres vivos.

## Entrada

- A primeira linha contém um número inteiro C relativo ao número de casos de teste. Em seguida, haverá C linhas, com um número inteiro N (100 <= N <= 100000) relativo ao nível de energia de um ser vivo.

## Saída

Para cada valor lido, imprima o texto correspondente.

<p align="center">
    <img src="000-Midia_e_Anexos/image.png" alt="" width="480">
</p>

## Solução

```javascript
// TODO: complete os espaços em branco com sua solução para o problema
// a função gets é implementada dentro do sistema para ler as entradas(inputs) dos dados e a função print para imprimir a saída (output) de dados e já pula uma linha ("\n")
// Abaixo segue um exemplo de código que você pode ou não utilizar

let input = gets();

let qtdEntradas = parseInt(input);
let nivelEnergia;

//Resolva o desafios nos espaços em branco
for(let i = 0; i < qtdEntradas; i++){
    input = gets();
    nivelEnergia = parseInt(input);
    
    if (nivelEnergia > 8000) {
        print ("Mais de 8000!")
    } else {
        print ("Inseto!")
    }
```

# Desafio 2 - Soma de números naturais

## Descrição

- Um número natural é um inteiro não-negativo (0, 1, 2, 3, 4, 5,...). A sua tarefa neste problema é calcular a soma dos números naturais que estão presentes em um determinado intervalo [A, B] inclusive.

- Por exemplo, a soma dos números naturais no intervalo [2, 5] é 14 = (2+3+4+5).

## Entrada

- Cada caso de teste contém dois inteiros A e B (1 ≤ A ≤ B ≤ 109), representando o limite inferior e o superior respectivamente.

## Saída

Para cada caso de teste, a saída consiste de uma linha contendo a soma dos números naturais do intervalo.

Exemplo 1

<p align="center">
    <img src="000-Midia_e_Anexos/image_02.png" alt="" width="480">
</p>

Exemplo 2

<p align="center">
    <img src="000-Midia_e_Anexos/image_03.png" alt="" width="480">
</p>

## Solução

```javascript
// a função gets é implementada dentro do sistema para ler as entradas(inputs) dos dados e a função print para imprimir a saída (output) de dados e já pula uma linha ("\n")
// Abaixo segue um exemplo de código que você pode ou não utilizar

const A = parseInt(gets());
const B = parseInt(gets());
const total = (B * (B + 1)) / 2 - ((A - 1) * A) / 2 ;
print(total);

//Escreva o seu código no espaço em branco
```

## Explicação Soma de Gauss

A **soma de Gauss** é uma fórmula matemática que permite calcular rapidamente a soma dos números naturais consecutivos.

### Fórmula geral
A soma dos primeiros `n` números naturais é:

S = n × (n + 1) / 2

### Intervalo [A, B]
Para somar números dentro de um intervalo específico, usamos:

S = (B × (B + 1)) / 2 − ((A − 1) × A) / 2

### Exemplo
Intervalo [2, 5]:

S = (5 × 6) / 2 − (1 × 2) / 2  
S = 15 − 1 = 14

Resultado: **14**



