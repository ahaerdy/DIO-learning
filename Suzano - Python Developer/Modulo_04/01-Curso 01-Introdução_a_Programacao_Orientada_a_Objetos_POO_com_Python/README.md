- Instrutor: Guilherme Arthur de Carvalho (Analista de Sistemas)
- Contato Libkedin: https://www.linkedin.com/in/decarvalhogui/

# Parte 1 -  Introdução à Programação Orientada a Objetos (POO) com Python

## 🟩 Vídeo 01 - O que é Orientação a Objetos (OO)?

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

### Nosso primeiro programa POO

- João tem uma bicicletaria e gostaria de registrar as vendas de suas bicicletas. Crie um programa onde João informe: cor, modelo, ano e valor da bicicleta vendida. Uma bicicleta pode: buzinar, parar e correr. Adicione esses comportamentos! 


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

# Parte 2 - Materiais de apoio e Questionário

## Materiais de apoio

Disponibilizados os seguinte arquivos em formato MS Powerpoint:

- 

# Certificado: Trabalhando com Listas em Python

Link do certificado: 