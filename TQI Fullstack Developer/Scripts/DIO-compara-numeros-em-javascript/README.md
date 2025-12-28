# Comparação de Números em JavaScript  

Este repositório apresenta o **projeto final do curso “Sintaxe e Operadores JavaScript”**, pertencente ao **Módulo III – JavaScript** do **Bootcamp TQI Fullstack Developer**, realizado na plataforma **Digital Innovation One (DIO)**.

O projeto tem como objetivo aplicar, de forma prática, conceitos fundamentais da linguagem JavaScript, como **operadores**, **funções**, **comparações lógicas**, **template strings** e **organização de código**.

---

## Objetivo do projeto

Desenvolver uma função capaz de:

- Comparar dois números
- Verificar se eles são **iguais ou diferentes**
- Calcular a **soma dos valores**
- Avaliar essa soma em relação aos valores **10** e **20**
- Retornar uma frase clara e bem estruturada com os resultados das comparações

---

## Conceitos trabalhados

- Funções em JavaScript
- Operadores aritméticos (`+`)
- Operadores de comparação (`===`, `>`)
- Estruturas condicionais (`if`)
- Template strings
- Organização e separação de responsabilidades no código
- Retorno de valores a partir de funções

---

## Tecnologias utilizadas

- **JavaScript (ES6+)**

O projeto foi desenvolvido utilizando apenas JavaScript puro (*Vanilla JS*), com foco exclusivo na lógica e na sintaxe da linguagem.

---

## Estrutura do projeto

```text
.
└── comparaNumeros.js   # Implementação da lógica de comparação
```

🔍 Funcionamento da lógica
- comparaNumeros(num1, num2): dunção principal, responsável por orquestrar a comparação e retornar a frase final.
- criaPrimeiraFrase(num1, num2): verifica se os dois números são iguais ou não.
- criaSegundaFrase(num1, num2): calcula a soma dos números e compara o resultado com os valores 10 e 20.

Essa separação melhora a legibilidade, manutenção e clareza do código.

## 📚 Contexto educacional

Este repositório foi criado como material de estudo autoral, com o objetivo de consolidar o aprendizado e servir como referência futura sobre os fundamentos da linguagem JavaScript.

## Possíveis evoluções

- Validar tipos de entrada
- Adaptar a função para entrada dinâmica (prompt ou formulário)
- Criar testes automatizados
- Integrar com uma interface HTML simples