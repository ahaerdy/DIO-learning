## Vídeo 01 - Tipos de Dados

- Instrutor: Guilherme Arthur de Carvalho (Analista de Sistemas)
- Contato Libkedin: https://www.linkedin.com/in/decarvalhogui/

O autor apresenta os principais tipos de dados e porque devemos utilizá-los. Eles servem para definir as caracteristicas e comportamentos de um valor (objeto) para o interpretador. 

Os tipos built-in apresewntados foram:

| Categoria  | Tipos                                |
|------------|---------------------------------------|
| Texto      | `str`                                 |
| Numérico   | `int`, `float`, `complex`             |
| Sequência  | `list`, `tuple`, `range`              |
| Mapa       | `dict`                                |
| Coleção    | `set`, `frozenset`                    |
| Booleano   | `bool`                                |
| Binário    | `bytes`, `bytearray`, `memoryview`    |



link do vídeo: https://web.dio.me/track/suzano-python-developer/course/conhecendo-a-linguagem-de-programacao-python/learning/81ce773a-b004-43b8-bdc1-1f150df40f96?autoplay=1

## Vídeo 02 - Modo Interativo

- Instrutor: Guilherme Arthur de Carvalho (Analista de Sistemas)
- Contato Libkedin: https://www.linkedin.com/in/decarvalhogui/

1. O autor ensina como usar o modo interativo do interpretador Python.
    - Existem duas formas de iniciar o modo interativo, chamando apenas o interpretador (python) ou executando o script com a flag -i (python -i app.py).
2. Explica os comandos dir e help
    - dir: sem argumentos, retorna a lista de nomes no escopo local atual. Com um argumento, retorna uma lista de atributos válidos para o objeto. 
    - help: Invoca o sistema de ajuda integrado. É possível fazer buscas em modo interativo ou informar por parâmetro qual o nome do módulo, função, classe, método ou variável.

- Links úteis: https://github.com/guicarvalho/trilha-python-dio
- Referência: https://wiki.python.org.br/ModoInterativo

## Vídeo 03 - Variáveis e Constantes

- Instrutor: Guilherme Arthur de Carvalho (Analista de Sistemas)
- Contato Libkedin: https://www.linkedin.com/in/decarvalhogui/

1. __Variáveis__: em linguagens de programação podemos definir valores que podem sofrer alterações no decorrer da execução do programa. Esses valores recebem o nome de variáveis, pois eles nascem com um valor e não necessariamente devem permanecer com o mesmo durante a execução do programa.

2. __Alterando__ os valores: não precisamos definir o tipo de dados da variável, o Python faz isso automaticamente para nós. Por isso não podemos simplesmente criar uma variável sem atribuir um valor. Para alterar o valor da variável basta fazer uma atribuição de um novo valor.

3. __Constantes__: assim como as variáveis, constantes são utilizadas para armazenar valores. Uma constante nasce com um valor e permanece com ele até o final da execução do programa, ou seja, o valor é imutável.

4. __Python não tem constantes__
    - Não existe uma palavra reservada para informar ao interpretador que o valor é constante. Em algumas linguagens por exemplo: Java e C utilizamos final e const, respectivamente para declarar uma constante.
    - Em Python usamos a convenção que diz ao programador  que a variável é uma constante. Para fazer isso, você deve criar a variável com o nome todo em letras maíusculas: 
        - BS_PATH = '/home/guilherme/Documents/python_course/'
        - DEBUG = True
        - STATES = ['SP','RJ','MG']
        - AMOUNT = 30.2

5. __Boas práticas__
    - O padrão de nomes deve ser snake case.
    - Escolher nomes sugestivos.
    - Nome de constantes todo em maiúsculo.

Links úteis: https://github.com/guicarvalho/trilha-python-dio

## Vídeo 04 - Conversão de Tipos

- Instrutor: Guilherme Arthur de Carvalho (Analista de Sistemas)
- Contato Libkedin: https://www.linkedin.com/in/decarvalhogui/

Em alguns momentos é necessário será necessário converter o tipo de uma variável para manipular de forma diferente. Por exemplo: variáveis do tipo string, que armazenam números e precisamos fazer alguma operação matemática com esse valor.

```
preco = 10
print(preco)
>>> 10

preco = float(preco)
print(preco)
>>> 10.0

preco = 10 / 2
print(preco)
>>> 5.0

preco = 10.30
print(preco)
>>> 10.3

preco = int(preco)
print(preco)
>>> 10

preco = 10
print(preco)
>>> 10

print(preco / 2)
>>> 5.0

print(preco / 2)
>>> 5

preco = 10.50
idade = 28

print(str(preco))
>>> 10.5

print(str(idade))
>>> 28

texto = f"idade {idade} preco {preco}"
print(texto)
>>> idade 28 preco 10.5

preco = "10.50"
idade = "28"

print(float(preco))
>>> 10.50

print(int(idade))
>>> 28

preco = "python"
print(float(preco))

>>>
Traceback (most recent call last):
  File "main.py", line 3, in <module>
	print(float(preco))
ValueError: could not convert string to float: 'python'
```

Links úteis: https://github.com/guicarvalho/trilha-python-dio

## Vídeo 05 - Funções de entrada e saída

- Instrutor: Guilherme Arthur de Carvalho (Analista de Sistemas)
- Contato Libkedin: https://www.linkedin.com/in/decarvalhogui/

Objetivo geral: Aprender como receber e exibir informações para o usuário.

- __Função input__: a função builtin input é utilizada quando queremos ler dados da entrada padrão (teclado). Ela recebe um argumento do tipo string, que é exibido para o usuário na saída padrão (tela). A função lê a entrada, converte para string e retorna o valor.

```
nome = input("Informe o seu nome: ")
>>> Informe o seu nome: 
```

- __Função print__: a função builtin print é utilizada quando queremos exibir dados na saída padrão (tela). Ela recebe um argumento obrigatório do tipo varargs de objetos e 4 argumentos opcionais (sep, end, file e flush). Todos os objetos são convertidos para string, separados por sep e terminados por end. A string final é exibida para o usuário.

```
nome = "Guilherme"
sobrenome = "Carvalho"

print(nome, sobrenome)
print(nome, sobrenome, end="...\n")
print(nome, sobrenome, sep="#")

>>> Guilherme Carvalho
>>> Guilherme Carvalho...
>>> Guilherme#Carvalho
```

Links úteis:
    - https://github.com/guicarvalho/trilha-python-dio
    - https://docs.python.org/3/library/functions.html#input
    - https://docs.python.org/3/library/functions.html#print

## Certiicado: Conhecendo a Linguagem de Programação Python

Link do certificado: https://hermes.dio.me/certificates/BRMRBL10.pdf

