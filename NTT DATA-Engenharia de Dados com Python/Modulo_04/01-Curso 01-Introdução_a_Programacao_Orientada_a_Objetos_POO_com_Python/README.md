- Instrutor: Guilherme Arthur de Carvalho (Analista de Sistemas)
- Contato Linkedin: https://www.linkedin.com/in/decarvalhogui/

# Parte 1 -  Introdução à Programação Orientada a Objetos (POO) com Python

## 🟩 Vídeo 01 - O que é Orientação a Objetos (OO)?

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.04-curso.01-video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/introducao-poo/learning/9aaf0076-a2e6-4d6e-b106-889460797859?autoplay=1

### Paradigmas de programação

- Um paradigma de programação é um estilo e programação. Não é uma linguagem (Python, Java, C, etc), e sim a forma como você soluciona os problemas através do código.

### Exemplo

- Problema: Beber água
    - Solução 1: Usar um copo para beber água.
    - Solução 2: Usar uma garrafa para beber água.

### Alguns paradigmas

- Imperativo ou procedural
- Funcional
- Orientado a eventos

### Programação orientada a objetos

- O paradigma de programação orientada a objetos estrutura o código abstraindo problemas em objetos do mundo real, facilitando o entendimento do código e tornando-o mais modular e extensível. Os dois conceitos chaves para aprender POO são: classes e objetos. 

## 🟩 Vídeo 02 - Os conceitos de Classes e Objetos  

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.04-curso.01-video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/introducao-poo/learning/951aac9e-696c-4d3f-a688-1de9df6018b3?autoplay=1

### Objetivo Geral

- Aprender a utilizar classes e objetos com Python.

### Classes e objetos?

- Uma classe define as características e comportamentos de um objeto, porém não conseguimos usá-las diretamente. Já os objetos podemos usá-los e eles possuem as características e comportamentos que foram definidos nas classes.

### Classe

```python
class Cachorro:
    def __init__(self, nome, cor, acordado=True):
        self.nome = nome
        self.cor = cor
        self.acordado = acordado

    def latir(self):
        print("Auau")

    def dormir(self):
        self.acordado = False
        print("Zzzzz...")
```

### Objeto

```python
cao_1 = Cachorro("chappie", "amarelo", False)
cao_2 = Cachorro("Aladim", "branco e preto")

cao_1.latir()

print(cao_2.acordado)
cao_2.dormir()
print(cao_2.acordado)
```


## 🟩 Vídeo 03 - Criando seu primeiro Programa com POO

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.04-curso.01-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/introducao-poo/learning/fc0c80e3-c7b7-4f48-b8b5-595dea75b26a?autoplay=1

### Nosso primeiro programa POO

- João tem uma bicicletaria e gostaria de registrar as vendas de suas bicicletas. Crie um programa onde João informe: cor, modelo, ano e valor da bicicleta vendida. Uma bicicleta pode: buzinar, parar e correr. Adicione esses comportamentos! 

#### Código:

```python
class Bicicleta:
    def __init__(self, cor, modelo, ano, valor):
        self.cor = cor
        self.modelo = modelo
        self.ano = ano
        self.valor = valor

    def buzinar(self):
        print("Plim plim...")

    def parar(self):
        print("Parando bicicleta...")
        print("Bicicleta parada!")

    def correr(self):
        print("Vrummmmm...")

    def __str__(self):
        return f"{self.__class__.__name__}: {', '.join([f'{chave}={valor}' for chave, valor in self.__dict__.items()])}"


b1 = Bicicleta("vermelha", "caloi", 2022, 600)
b1.buzinar()
b1.correr()
b1.parar()
print(b1.cor, b1.modelo, b1.ano, b1.valor)

b2 = Bicicleta("verde", "monark", 2000, 189)
print(b2)
b2.correr()
```

## 🟩 Vídeo 04 - Construtores e Destrutores

### Objetivo Geral

- Entender o conceito de construtor e destrutor.

### Método construtor

- O método construtor sempre é executado quando uma nova instância da classe é criada. Nesse método inicializamos o estado do nosso objeto. Para declarar o método construtor da classe, criamos um método com o nome `__init__`.

### `__init__`

```python
class Cachorro:
    def __init__(self, nome, cor, acordado=True):
        self.nome = nome
        self.cor = cor
        self.acordado = acordado
```

### Método destrutor

- O método destrutor sempre é executado quando uma instância (objeto) é destruída. Destrutores em Python não são tão necessários quanto em C++ porque o Pyton tem um coletor de lixo que lida com o gerenciamento de memória automaticamente. Para declarar o método destrutor da classe, criamos um método com o nome `__del__`.

### `__del__`

```python
class Cachorro:
    def __del__(self):
        print("Destruindo a instância")

c = Cachorro()
del c
```

### Exemplo utilizado na aula

```python
class Cachorro:
    def __init__(self, nome, cor, acordado=True):
        print("Inicializando a classe...")
        self.nome = nome
        self.cor = cor
        self.acordado = acordado

    def __del__(self):
        print("Removendo a instância da classe.")

    def falar(self):
        print("auau")


def criar_cachorro():
    c = Cachorro("Zeus", "Branco e preto", False)
    print(c.nome)


c = Cachorro("Chappie", "amarelo")
c.falar()

print("Ola mundo")

del c

print("Ola mundo")
print("Ola mundo")
print("Ola mundo")

# criar_cachorro()
```

# Parte 2 - Materiais de apoio e Questionário

## Materiais de apoio

Disponibilizados os seguinte arquivos em formato MS Powerpoint:

- Programacao_Orientada_a_Objetos_Com_Python.pptx

## Certificado: Introdução à Programação Orientada a Objetos (POO) com Python

Link do certificado: https://hermes.dio.me/certificates/PMP6QAQI.pdf