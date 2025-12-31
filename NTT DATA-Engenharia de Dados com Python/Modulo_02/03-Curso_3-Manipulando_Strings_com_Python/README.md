# Parte 1 - Dominando Strings e Fatiamento

- Instrutor: Guilherme Arthur de Carvalho (Analista de Sistemas)
- Contato Linkedin: https://www.linkedin.com/in/decarvalhogui/

## 🟩 Vídeo 01 - Conhecendo métodos úteis da classe string

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp-ntt-data_modulo_02-Curso_03-Video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/manipulando-strings-com-python/learning/b67433a9-2fc7-41cc-8db0-c0ddd3964198?autoplay=1

O vídeo consiste em uma aula técnica sobre a manipulação de **strings em Python**, focada em métodos práticos para facilitar o desenvolvimento diário. O conteúdo explica funções essenciais para transformar textos, como **upper**, **lower** e **title**, além de técnicas para **remover espaços em branco** indesejados. O instrutor demonstra como **centralizar strings** e utilizar o método **join** para unir caracteres de forma eficiente, comparando a simplicidade do Python com linguagens mais complexas. Através de exemplos no **VS Code**, o material enfatiza a legibilidade do código e a versatilidade da classe string para lidar com objetos iteráveis. O objetivo final é capacitar o aluno a realizar **interpolação de variáveis**, fatiamento e trabalhar com múltiplas linhas de texto.

### Introdução

- A classe String do Python é famosa por ser rica em métodos e possuir uma interface muito fácil de trabalhar.
- Em algumas linguagens manipular sequências de caracteres não é um trabalho trivial, porém, em Python esse trabalho é muito simples.

### Maiúscula, minúscula e título

```
curso = "pYtHon"

print(curso.upper())
>>> PYTHON

print(curso.lower())
>>> python

print(curso.title())
>>> Python
```

### Eliminando espaços em branco

```
curso = "   Python "

print(curso.strip())
>>> "Python"

print(curso.lstrip())
>>> "Python "

print(curso.rstrip())
>>> "   Python"
```

### Junções e centralização

```
curso = "Python"

print(curso.center(10, "#"))
>>> "##Python##"

print(".".join(curso))
>>> "P.y.t.h.o.n"
```

## 🟩 Vídeo 02 - Interpolação de variáveis

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp-ntt-data_modulo_02-Curso_03-Video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video> 

Link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/manipulando-strings-com-python/learning/9a3a3061-5a00-4c43-8d5f-d115bd098144?autoplay=1

### Interpolação de variáveis

- Em Python temos 3 formas de interpolar variáveis em strings, a primeira é usando o sinal %, a segunda é utilizando o método format e a última é utilizando f strings.
- A primeira forma não é atualmente recomendada e seu uso em Python 3 é raro, por esse motivo iremos focar nas 2 últimas.

### Old Style

```
nome = "Guilherme"
idade = 28
profissao = "Progamador"
linguagem = "Python"


print("Olá, me chamo %s. Eu tenho %d anos de idade, trabalho como %s e estou matriculado no curso de %s." % (nome, idade, profissao, linguagem))

>>> Olá, me chamo Guilherme. Eu tenho 28 anos de idade, trabalho como Progamador e utilizo e estou matriculado no curso de Python.
```

### Método format

```
nome = "Guilherme"
idade = 28
profissao = "Programador"
linguagem = "Python"

print("Olá, me chamo {}. Eu tenho {} anos de idade, trabalho como {} e estou matriculado no curso de {}.".format(nome, idade, profissao, linguagem))

print("Olá, me chamo {3}. Eu tenho {2} anos de idade, trabalho como {1} e estou matriculado no curso de {0}.".format(linguagem, profissao, idade, nome))

print("Olá, me chamo {nome}. Eu tenho {idade} anos de idade, trabalho como {profissao} e estou matriculado no curso de {linguagem}.".format(nome=nome, idade=idade, profissao=profissao, linguagem=linguagem))

# format(**pessoa) ---> dicionário com o valor pessoa

print("Olá, me chamo {nome}. Eu tenho {idade} anos de idade, trabalho como {profissao} e estou matriculado no curso de {linguagem}.".format(**pessoa)) 

>>> Olá, me chamo Guilherme. Eu tenho 28 anos de idade, trabalho como Progamador e estou matriculado no curso de Python.
```

### f-string

```
nome = "Guilherme"
idade = 28
profissao = "Programador"
linguagem = "Python"

print(f"Olá, me chamo {nome}. Eu tenho {idade} anos de idade, trabalho como {profissao} e estou matriculado no curso de {linguagem}.")

>>> Olá, me chamo Guilherme. Eu tenho 28 anos de idade, trabalho como Progamador e utilizo e estou matriculado no curso de Python.
```

### Formatar strings com f-string

```
PI = 3.14159

print(f"Valor de PI: {PI:.2f}")
>>> "Valor de PI: 3.14"

print(f"Valor de PI: {PI:10.2f}")
>>> "Valor de PI:       3.14"
```

💡 Interpretação (2a instrução):
- Na f-string f"Valor de PI: {PI:10.2f}" --> {PI:10.2f} indica que:
    - O número deve ser formatado com 10 caracteres de largura total, sendo 2 casas decimais.
    - O valor será alinhado à direita por padrão, preenchendo com espaços à esquerda se necessário.
    - O número 3.14 ocupa 4 caracteres (3, ., 1, 4), então os outros 6 espaços são preenchidos com espaços em branco à esquerda.

## 🟩 Vídeo 03 - Fatiamento de string

### Introdução

- Fatiamento de strings é uma técnica utilizada para retornar substrings (partes da string original), informando inicio (start), fim (stop) e passo (step): [start: stop[, step]].

### Fatiamento

```
nome = "Guilherme Arthur de Carvalho"

nome[0]
>>> "G"

nome[:9]
>>> "Guilherme"

nome[10:]
>>> "Arthur de Carvalho"

nome[10:16]
>>> "Arthur"

nome[10:16:2]
>>> "Atu"

nome[:]
>>> "Guilherme Arthur de Carvalho"

nome[::-1]
>>> "ohlavraC ed ruhtrA emrehliuG"
```

## 🟩 Vídeo 04 - String múltiplas linhas

### Introdução

- Strings de múltiplas linhas são definidas informando 3 aspas simples ou duplas durante a atribuição. Elas podem ocupar várias linhas do código, e todos os espaços em branco são incluídos na string final. 

### Strings triplas

```
nome = "Guilherme"

mensagem = f"""
Olá meu nome é {nome},
Eu estou aprendendo Python
"""
>>> 

Olá meu nome é Guilherme,
Eu estou aprendendo Python
```

### Links Úteis

- https://github.com/guicarvalho/trilha-python-dio
- https://docs.python.org/pt-br/3/library/string.html
- https://docs.python.org/pt-br/3/library/stdtypes.html#textseq

# Parte 2 - Materiais de apoio e Questionário

## Materiais de apoio

Disponibilizado o seguinte arquivo em formato MS Powerpoint:

- 18-Dio_String_e_fatiamento.pptx

# Certificado: Manipulando Strings com Python

Link do certificado: https://hermes.dio.me/certificates/5JWHA9TE.pdf

