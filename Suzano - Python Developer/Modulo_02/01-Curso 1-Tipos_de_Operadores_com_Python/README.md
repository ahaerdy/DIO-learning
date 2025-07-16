# Parte 1 - Tipos de Operadores

## Vídeo 01 - Operadores aritméticos

### O que são operadores aritméticos e como utilizá-los.

- Os operadores aritméticos executam operações matemáticas, como adição, subtração com operandos.

#### Adição, subtração e multiplicação

```
# Adição
print(1 + 1)
>>> 2

# Subtração
print(10 - 2)
>>> 8

# Multiplicação
print(4 * 3)
>>> 12

# Divisão
print(12 / 3)
>>> 4.0

# Divisão inteira
print(12 // 2)
>>> 6

# Módulo
print(10 % 3)
>>> 1

# Exponenciação
print(2 ** 3)
>>> 8
```
#### Precedência de operadores

Na matemática existe uma regra que indica quais operações devem ser executadas primeiro. Isso é útil pois ao analisar uma expressão, a depender da ordem das operações o valor pode ser diferente:

x = 10 - 5 * 2 
x é igual a 10 ou 0?

A definição indica a seguinte ordem como a correta:

- Parêntesis
- Expoêntes
- Multiplicações e divisões (da esquerda para a direita)
- Somas e subtrações (da esquerda para a direita)

```
print(10 - 5 * 2)
>>> 0

print((10 - 5) * 2)
>>> 10

print(10 ** 2 * 2)
>>> 200

print(10 ** (2 * 2))
>>> 10000

print(10 / 2 * 4)
>>> 20.0
```

#### Links Úteis

https://github.com/guicarvalho/trilha-python-dio

## Vídeo 02 - Operadores de comparação

### O que são operadores de comparação e como utilizá-los.

- São operadores utilizados para comparar dois valores.

### Igualdade

```
saldo = 450
saque = 200

print(saldo == saque)
>>> False
```

### Diferença

```
saldo = 450
saque = 200
print(saldo != saque)
>>> True
```

### Maior que / maior ou igual 

```
saldo = 450
saque = 200
print(saldo > saque)
>>> True

print(saldo >= saque)
>>> True
```

### Menor que / menor ou igual 

```
saldo = 450
saque = 200
print(saldo < saque)
>>> False

print(saldo <= saque)
>>> False
```

## Vídeo 03 - Operadores de atribuição

### O que são operadores de atribuição e como utilizá-los.

- São operadores utilizados para definir o valor inicial ou sobrescrever o valor de uma variável.

### Atribuição simples

```
saldo = 500

print(saldo)
>>> 500
```

### Atribuição com adição

```
saldo = 500
saldo += 200

print(saldo)
>>> 700
```

### Atribuição com subtração

```
saldo = 500
saldo -= 100

print(saldo)
>>> 400
```

### Atribuição com multiplicação

```
saldo = 500
saldo *= 2

print(saldo)
>>> 1000
```

### Atribuição com divisão

```
saldo = 500
saldo /= 5

print(saldo)
>>> 100.0

saldo = 500
saldo //= 5

print(saldo)
>>> 100
```

### Atribuição com módulo

```
saldo = 500
saldo %= 480

print(saldo)
>>> 20
```

### Atribuição com exponenciação

```
saldo = 80
saldo **= 2

print(saldo)
>>> 6400
```

## Vídeo 04 - Operadores Lógicos

### O que são operadores lógicos e como utilizá-los.

- São operadores utilizados em conjunto com os operadores de comparação, para montar uma expressão lógica. Quando um operador de comparação é utilizado, o resultado retornado é um booleano, dessa forma podemos combinar operadores de comparação com os operadores lógicos, exemplo: ``` op_comparacao + op_logico + op_comparacao… N … ```

### 

## Vídeo 05 - Operadores de identidade

## Vídeo 06 - Operadores de associação

# Parte 2 - Materiais de apoio e Questionário

## Materiais de apoio

