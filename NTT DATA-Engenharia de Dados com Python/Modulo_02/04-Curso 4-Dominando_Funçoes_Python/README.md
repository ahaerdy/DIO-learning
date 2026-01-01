# Parte 1 - Dominando Funções Python

- Instrutor: Guilherme Arthur de Carvalho (Analista de Sistemas)
- Contato Linkedin: https://www.linkedin.com/in/decarvalhogui/

## 🟩 Vídeo 01 - Funções Python – Parte 01

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp-ntt-data_modulo_02-Curso_04-Video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/dominando-funcoes-python/learning/065ecbd9-7623-486d-b10f-28efc150d00f?autoplay=1

O vídeo consiste em uma aula técnica detalhada sobre a **declaração e o uso de funções na linguagem Python**. O conteúdo explora a definição de funções como **blocos de código identificáveis** que promovem a legibilidade e a estruturação do software através do uso da palavra reservada **def**. São explicados conceitos fundamentais como a **passagem de argumentos obrigatórios**, o uso de **valores padrão** e a capacidade única do Python de retornar **múltiplos valores em tuplas**. O texto também aborda a distinção crucial entre **argumentos posicionais e nomeados**, demonstrando como estes últimos previnem erros de inversão de dados. Por fim, o material detalha o funcionamento dos parâmetros especiais **args e kwargs**, que permitem lidar com listas e dicionários de argumentos de forma dinâmica.

### Objetivo Geral

- Entender como funcionam as funções em Python.

### O que são funções?

- Função é um bloco de código identificado por um nome e pode receber uma lista de parâmetros, esses parâmetros podem ou não ter valores padrões. Usar funções torna o código mais legível e possibilita o reaproveitamento de código. Programar baseado em funções, é o mesmo que dizer que estamos programando de maneira estruturada.

### Exemplo

```
def exibir_mensagem():
    print("Olá mundo!")

def exibir_mensagem_2(nome):
    print(f"Seja bem vindo {nome}!")

def exibir_mensagem_3(nome="Anônimo"):
    print(f"Seja bem vindo {nome}!")

exibir_mensagem()
exibir_mensagem_2(nome="Guilherme")
exibir_mensagem_3()
exibir_mensagem_3(nome="Chappie")
```

### Retornando valores

- Para retornar um valor, utilizamos a palavra reservada return. Toda função Python retorna None por padrão. Diferente de outras linguagens de programação, em Python uma função pode retornar mais de um valor.

### Exemplo

```
def calcular_total(numeros):
    return sum(numeros)

def retorna_antecessor_e_sucessor(numero):
    antecessor = numero - 1
    sucessor = numero + 1

    return antecessor, sucessor

calcular_total([10, 20, 34])  # 64
retorna_antecessor_e_sucessor(10)  # (9, 11)
```

### Argumentos nomeados

- Funções também podem ser chamadas usando argumentos nomeados da forma chave=valor.

### Exemplo

```
def salvar_carro(marca, modelo, ano, placa):
    # salva carro no banco de dados...
    print(f"Carro inserido com sucesso! {marca}/{modelo}/{ano}/{placa}")

salvar_carro("Fiat", "Palio", 1999, "ABC-1234")
salvar_carro(marca="Fiat", modelo="Palio", ano=1999, placa="ABC-1234")
salvar_carro(**{"marca": "Fiat", "modelo": "Palio", "ano": 1999, "placa": "ABC-1234"})

# Carro inserido com sucesso! Fiat/Palio/1999/ABC-1234
```

### Args e kwargs

- Podemos combinar parâmetros obrigatórios com args e kwargs. Quando esses são definidos (*args e **kwargs), o método recebe os valores como tupla e dicionário respectivamente.

### Exemplo

```
def exibir_poema(data_extenso, *args, **kwargs):
    texto = "\n".join(args)
    meta_dados = "\n".join([f"{chave.title()}: {valor}" for chave, valor in kwargs.items()])
    mensagem = f"{data_extenso}\n\n{texto}\n\n{meta_dados}"
    print(mensagem)


exibir_poema(
    "Sexta-feira, 26 de agosto de 2022",
    "Zen of Python",
    "Beautiful is better than ugly.",
    "Explicit is better than implicit.",
    "Simple is better than complex.",
    "Complex is better than complicated.",
    "Flat is better than nested.",
    "Sparse is better than dense.",
    "Readability counts.",
    "Special cases aren't special enough to break the rules.",
    "Although practicality beats purity.",
    "Errors should never pass silently.",
    "Unless explicitly silenced.",
    "In the face of ambiguity, refuse the temptation to guess.",
    "There should be one-- and preferably only one --obvious way to do it.",
    "Although that way may not be obvious at first unless you're Dutch.",
    "Now is better than never.",
    "Although never is often better than *right* now.",
    "If the implementation is hard to explain, it's a bad idea.",
    "If the implementation is easy to explain, it may be a good idea.",
    "Namespaces are one honking great idea -- let's do more of those!",
    autor="Tim Peters",
    ano=1999,
)

exibir_poema("Zen of Python", "Beautiful is better than ugly.", autor="Tim Peters", ano=1999)
```

#### Análise

- O código em Python exibido acima define uma função chamada exibir_poema que recebe três tipos de parâmetros:
    - data_extenso: uma string representando a data por extenso 
    - *args: argumentos posicionais, que serão tratados como partes do poema.
    - **kwargs: argumentos nomeados (chave-valor), usados como metadados (como autor, ano etc.).


- Definindo a função exibir_poema:
```
def exibir_poema(data_extenso, *args, **kwargs):
```

- Unindo todos os argumentos posicionais (args) em um único texto, separando cada item com uma quebra de linha:
```
    texto = "\n".join(args)
```

- Criando uma string formatada com os metadados, convertendo as chaves para Title Case (ex: "autor" vira "Autor") e unindo tudo com quebras de linha:
```
    meta_dados = "\n".join([f"{chave.title()}: {valor}" for chave, valor in kwargs.items()])
```

- Montando a mensagem final com:
    - data no topo
    - o poema (texto)
    - os metadados no final, separados por linhas em branco
```
    mensagem = f"{data_extenso}\n\n{texto}\n\n{meta_dados}"
```

- Exibindo a mensagem completa no terminal:
```
    print(mensagem)
```
#### Chamada da função:

```
exibir_poema("Zen of Python", "Beautiful is better than ugly.", autor="Tim Peters", ano=1999)
```

- data_extenso: "Zen of Python"
- *args: "Beautiful is better than ugly." (parte do poema)
- **kwargs: autor="Tim Peters", ano=1999

#### Saída esperada:

```
Sexta-feira, 26 de agosto de 2022

Zen of Python
Beautiful is better than ugly.
Explicit is better than implicit.
Simple is better than complex.
Complex is better than complicated.
Flat is better than nested.
Sparse is better than dense.
Readability counts.
Special cases aren't special enough to break the rules.
Although practicality beats purity.
Errors should never pass silently.
Unless explicitly silenced.
In the face of ambiguity, refuse the temptation to guess.
There should be one-- and preferably only one --obvious way to do it.
Although that way may not be obvious at first unless you're Dutch.
Now is better than never.
Although never is often better than *right* now.
If the implementation is hard to explain, it's a bad idea.
If the implementation is easy to explain, it may be a good idea.
Namespaces are one honking great idea -- let's do more of those!

Autor: Tim Peters
Ano: 1999
```

#### Conclusão:

- Este código é um formato simples e elegante para exibir poemas ou frases com metadados, utilizando recursos como *args, **kwargs, f-strings e join() para composição de texto.

## 🟩 Vídeo 02 - Funções Python – Parte 02

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp-ntt-data_modulo_02-Curso_04-Video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/dominando-funcoes-python/learning/31c31eb0-d504-4876-9b7e-6ffa70072c7f?autoplay=1

### Parâmetros especiais

- Por padrão, argumentos podem ser passados para uma função Python tanto por posição quanto explicitamente pelo nome. Para uma melhor legibilidade e desempenho, faz sentido restringir a maneira pelo qual argumentos possam ser passados, assim um desenvolvedor precisa apenas olhar para a definição da função para determinar se os itens são passados por posição, por posição e nome, ou por nome.

<p align="center">
  <img src="image.png">
</p>

### Positional only

```
def criar_carro(modelo, ano, placa, /, marca, motor, combustivel):
    print(modelo, ano, placa, marca, motor, combustivel)


criar_carro("Palio", 1999, "ABC-1234", marca="Fiat", motor="1.0", combustivel="Gasolina")  # válido
criar_carro("Palio", 1999, "ABC-1234", "Fiat", "1.0", "Gasolina")  # 💡 também é válido


criar_carro(modelo="Palio", ano=1999, placa="ABC-1234", marca="Fiat", motor="1.0", combustivel="Gasolina")  # inválido
```

### Keyword only

```
def criar_carro(*, modelo, ano, placa, marca, motor, combustivel):
    print(modelo, ano, placa, marca, motor, combustivel)


criar_carro(modelo="Palio", ano=1999, placa="ABC-1234", marca="Fiat", motor="1.0", combustivel="Gasolina")  # válido

criar_carro("Palio", 1999, "ABC-1234", marca="Fiat", motor="1.0", combustivel="Gasolina")  # inválido
```

### Keyword and positional only

```
def criar_carro(modelo, ano, placa, /, *, marca, motor, combustivel):
    print(modelo, ano, placa, marca, motor, combustivel)


criar_carro("Palio", 1999, "ABC-1234", marca="Fiat", motor="1.0", combustivel="Gasolina")  # válido

criar_carro(modelo="Palio", ano=1999, placa="ABC-1234", marca="Fiat", motor="1.0", combustivel="Gasolina")  # inválido
```

### Objetos de primeira classe

- Em Python tudo é objeto, dessa forma funções também são objetos o que as tornam objetos de primeira classe. Com isso podemos atribuir funções a variáveis, passá-las como parâmetro para funções, usá-las como valores em estruturas de dados (listas, tuplas, dicionários, etc) e usar como valor de retorno para uma função (closures).

### Exemplo

```
def somar(a, b):
    return a + b

def exibir_resultado(a, b, funcao):
    resultado = funcao(a, b)
    print(f"O resultado da operação {a} + {b} = {resultado}")

exibir_resultado(10, 10, somar)  # O resultado da operação 10 + 10 = 20
```

### Escopo local e escopo global

- Python trabalha com escopo local e global, dentro do bloco da função o escopo é local. Portanto alterações ali feitas em objetos imutáveis serão perdidas quando o método terminar de ser executado. Para usar objetos globais utilizamos a palavra-chave global, que informa ao interpretador que a variável que está sendo manipulada no escopo local é global. Essa NÃO é uma boa prática e deve ser evitada.

### Exemplo

```python
salario = 2000

def salario_bonus(bonus):
    global salario
    salario += bonus
    return salario

salario_bonus(500)  # 2500
```

### Links Úteis

- https://github.com/digitalinnovationone/trilha-python-dio


# Parte 2 - Materiais de apoio e Questionário

## Materiais de apoio

Disponibilizado o seguinte arquivo em formato MS Powerpoint:

- [05-Dio_Funcoes.pptx](000-Midia_e_Anexos/05-Dio.Funcoes.pptx)

# Certificado: Dominando Funções Python

- Link do certificado: https://hermes.dio.me/certificates/M4SVGNUZ.pdf
- Certificado em formato PDF: [000-Midia_e_Anexos/Certificado-Dominando.Funcoes.Python.pdf](Certificado-Dominando.Funcoes.Python.pdf)