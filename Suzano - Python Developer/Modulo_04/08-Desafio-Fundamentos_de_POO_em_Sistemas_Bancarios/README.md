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