# Desafio 1 - Criando uma Conta Bancária

## Descrição

- Implemente uma classe chamada ContaBancaria para representar uma conta bancária simples. Essa classe deve permitir que você realize as operações básicas de uma conta: depósito, saque e consulta de saldo. O saldo negativo.

## Requisitos

- A classe ContaBancaria deve ter:

    - Atributos:
        - titular (nome do dono da conta).
        - saldo (saldo inicial, que começa com 0 por padrão).

    - Métodos:
        - depositar(valor): adiciona o valor informado ao saldo.
        - sacar(valor): subtrai o valor informado do saldo, se houver saldo suficiente. Caso contrário, exiba a mensagem "Saque não permitido".
        - saldo_atual(): retorna o saldo atual da conta.

## Entrada

1.  Nome do titular (string).
2.  Sequência de valores representando operações de depósito e saque:

    - Valores positivos representam depósitos.
    - Valores negativos representam saques.

## Saída

- Exiba as operações realizadas e o saldo final no formato:  
    - "Operações: +500, -200; Saldo: 300" 

## Exemplos

- A tabela abaixo apresenta exemplos com alguns dados de entrada e suas respectivas saídas esperadas. Certifique-se de testar seu programa com esses exemplos e com outros casos possíveis..

<p align="center">
    <img src="images/image.png" alt="" width="480">
</p>