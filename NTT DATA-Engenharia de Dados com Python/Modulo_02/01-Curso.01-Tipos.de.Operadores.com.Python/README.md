# Instrutor

- Guilherme Arthur de Carvalho (Analista de Sistemas)
- Contato Linkedin: https://www.linkedin.com/in/decarvalhogui/
  
# Parte 1 - Tipos de Operadores

## Vídeo 01 - Operadores aritméticos

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp-ntt-data_modulo_02-Curso_01-Video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/tipos-de-operadores-com-python/learning/6df69727-d6c2-4956-b81b-2f3c21427c11?autoplay=1

### Anotações

#### O que são operadores aritméticos e como utilizá-los.

- Os operadores aritméticos executam operações matemáticas, como adição, subtração com operandos.

#### Adição, subtração e multiplicação

```python
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

```python
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

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp-ntt-data_modulo_02-Curso_01-Video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/tipos-de-operadores-com-python/learning/cfa81a73-1936-484f-8143-f145469f995a?autoplay=1

### Anotações

#### O que são operadores de comparação e como utilizá-los.

- São operadores utilizados para comparar dois valores.

#### Igualdade

```python
saldo = 450
saque = 200

print(saldo == saque)
>>> False
```

#### Diferença

```python
saldo = 450
saque = 200
print(saldo != saque)
>>> True
```

#### Maior que / maior ou igual 

```python
saldo = 450
saque = 200
print(saldo > saque)
>>> True

print(saldo >= saque)
>>> True
```

#### Menor que / menor ou igual 

```python
saldo = 450
saque = 200
print(saldo < saque)
>>> False

print(saldo <= saque)
>>> False
```

## Vídeo 03 - Operadores de atribuição

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp-ntt-data_modulo_02-Curso_01-Video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/tipos-de-operadores-com-python/learning/fdc7a723-ffaf-4366-870f-90b8197b1ccd?autoplay=1

### Anotações

#### O que são operadores de atribuição e como utilizá-los.

- São operadores utilizados para definir o valor inicial ou sobrescrever o valor de uma variável.

#### Atribuição simples

```python
saldo = 500

print(saldo)
>>> 500
```

#### Atribuição com adição

```python
saldo = 500
saldo += 200

print(saldo)
>>> 700
```

#### Atribuição com subtração

```python
saldo = 500
saldo -= 100

print(saldo)
>>> 400
```

#### Atribuição com multiplicação

```python
saldo = 500
saldo *= 2

print(saldo)
>>> 1000
```

#### Atribuição com divisão

```python
saldo = 500
saldo /= 5

print(saldo)
>>> 100.0

saldo = 500
saldo //= 5

print(saldo)
>>> 100
```

#### Atribuição com módulo

```python
saldo = 500
saldo %= 480

print(saldo)
>>> 20
```

#### Atribuição com exponenciação

```python
saldo = 80
saldo **= 2

print(saldo)
>>> 6400
```

## Vídeo 04 - Operadores Lógicos

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp-ntt-data_modulo_02-Curso_01-Video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/tipos-de-operadores-com-python/learning/41df67fd-70a9-4bb1-b87f-34b129996013?autoplay=1

### Anotações

#### O que são operadores lógicos e como utilizá-los.

- São operadores utilizados em conjunto com os operadores de comparação, para montar uma expressão lógica. Quando um operador de comparação é utilizado, o resultado retornado é um booleano, dessa forma podemos combinar operadores de comparação com os operadores lógicos, exemplo: ``` op_comparacao + op_logico + op_comparacao… N … ```

#### Exemplo

```python
saldo = 1000
saque = 200
limite = 100

saldo >= saque
>>> True

saque <= limite
>>> False
```

#### Operador E

```python
saldo = 1000
saque = 200
limite = 100

saldo >= saque and saque <= limite
>>> False
```

#### Operador OU 

```python
saldo = 1000
saque = 200
limite = 100

saldo >= saque or saque <= limite
>>> True
```

#### Operador Negação

```python
contatos_emergencia = []

not 1000 > 1500
>>> True

not contatos_emergencia
>>> True

not "saque 1500;"
>>> False

not ""
>>> True
```

#### Parênteses

```python
saldo = 1000
saque = 250
limite = 200
conta_especial = True

saldo >= saque and saque <= limite or conta_especial and saldo >= saque
>>> True

(saldo >= saque and saque <= limite) or (conta_especial and saldo >= saque)
>>> True
```

## Vídeo 05 - Operadores de identidade

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp-ntt-data_modulo_02-Curso_01-Video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/tipos-de-operadores-com-python/learning/e4027b9f-edd1-43e9-8211-01c0090d81ff?autoplay=1

O vídeo apresenta uma aula técnica focada no funcionamento dos **operadores de identidade** dentro da linguagem de programação **Python**. O instrutor detalha como os comandos **"is"** e **"is not"** são fundamentais para verificar se duas variáveis distintas apontam para o **mesmo local na memória**, diferenciando essa lógica de uma simples comparação de valores. Por meio de exemplos práticos no **VS Code**, o conteúdo demonstra a aplicação desses operadores com strings e números inteiros para validar a ocupação de espaço no sistema. Além da explicação teórica, o material incentiva a prática local e oferece suporte em comunidades digitais para sanar eventuais dúvidas dos estudantes. A lição conclui reforçando que essa ferramenta é essencial para entender a **gestão de objetos** e a eficiência do código durante o desenvolvimento de software.

### Anotações

#### O que são operadores de identidade e como utilizá-los.

- São operadores utilizados para comparar se os dois objetos testados ocupam a mesma posição na memória.

#### Exemplo

```python
curso = "Curso de Python"
nome_curso = curso
saldo, limite = 200, 200

curso is nome_curso
>>> True

curso is not nome_curso
>>> False

saldo is limite
>>> True
```

#### ⚠️ Explicando: 

```python
saldo is limite
>>> True
```

- True, porque 200 é um número pequeno (inteiro), e o Python geralmente reusa objetos pequenos imutáveis para economizar memória. Então saldo e limite provavelmente estão apontando para o mesmo objeto 200.

#### 🔎 Diferença entre is e ==

- == → compara valores (conteúdo).
- is → compara identidade de objeto (mesma referência em memória).

```python
a = [1, 2]
b = [1, 2]

a == b      # True  (mesmo conteúdo)
a is b      # False (objetos diferentes em memória)
```

## Vídeo 06 - Operadores de associação

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp-ntt-data_modulo_02-Curso_01-Video_06.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/tipos-de-operadores-com-python/learning/cc6031d4-26a1-4a0f-9fc3-72b9250c95af?autoplay=1

O vídeo consiste em uma aula técnica sobre o uso de **operadores de associação** na linguagem de programação **Python**. O conteúdo explica como utilizar os comandos **"in"** e **"not in"** para verificar se um elemento específico existe dentro de sequências, como **strings** ou **listas**. O instrutor demonstra exemplos práticos no **VS Code**, ressaltando que essas comparações são **case-sensitive**, ou seja, diferenciam letras maiúsculas de minúsculas. Além da sintaxe, o material aborda aplicações reais, como a busca de itens em retornos de **bancos de dados**. Por fim, o autor incentiva a prática por meio de exercícios no **GitHub** e a interação com a comunidade para sanar dúvidas.

### Anotações

#### O que são operadores de associação e como utilizá-los.

- São operadores utilizados para verificar se um objeto está presente em uma sequência .

#### Exemplo

```python
curso = "Curso de Python"
frutas = ["laranja", "uva", "limão"]
saques = [1500, 100]

"Python" in curso
>>> True

"maçã" not in frutas
>>> True

200 in saques
>>> False
```

# Parte 2 - Materiais de apoio e Questionário

## Materiais de apoio

Disponibilizados os seguinte arquivos em formato MS Powerpoint:
- 09-Dio_Operadores_aritmeticos.pptx
- 10-Dio_Operadores_de_comparacao.pptx
- 11-Dio_Operadores_de_atribuicao.pptx
- 12-Dio_Operadores_logicos.pptx
- 13-Dio_Operadores_de_identidade.pptx

# Certificado: Tipos de Operadores com Python

- Link do certificado:  https://hermes.dio.me/certificates/EUCVFLQ4.pdf
- Certificado em pdf: [Tipos de Operadores com Python](000-Midia_e_Anexos/Certificado-Tipos.de.Operadores.com.Python.pdf)
