- Instrutor: Guilherme Arthur de Carvalho (Analista de Sistemas)
- Contato Libkedin: https://www.linkedin.com/in/decarvalhogui/

# Parte 1 -  Introdução à Programação Orientada a Objetos (POO) com Python

## 🟩 Vídeo 01 - Herança em POO  

### O que é herança?

- Em programação herança é a capacidade de uma classe filha derivar ou herdar as características e comportamentos da classe pai (base).

### Benefícios da herança

- Representa bem os relacionamentos do mundo real.
- *Fornece reutilização de código*, não precisamos escrever o mesmo código repetidamente. Além disso, permite adicionar mais recursos a uma classe sem modificá-la.
- *É de natureza transitiva*, o que significa que, se a classe B herdar da classe A, todas as subclasses de B herdarão automaticamente da classe A.

### Sintaxe da herança

```python
class A:
    pass

class B(A):
    pass
```

## 🟩 Vídeo 02 - Conceituando Herança Simples e Herança Múltipla

### Herança simples

- Quando uma classe filha herda apenas uma classe pai, ela é chamada de herança simples.

### Exemplo

```python
class A:
    pass

class B(A):
    pass
```

### Herança múltipla

- Quando uma classe filha herda de várias classes pai, ela é chamada de herança múltipla.

## Exemplo

```python
class A:
    pass

class B:
    pass

class C(A, B):
    pass
```

## 🟩 Vídeo 03 - Hands-on: Herança Simples

### Herança Simples

#### Diagrama 

<p align="center">
    <img src="images/image.png" alt="" width="480">
</p>

```python
class Veiculo:
    def __init__(self, cor, placa, numero_rodas):
        self.cor = cor
        self.placa = placa
        self.numero_rodas = numero_rodas

    def ligar_motor(self):
        print("Ligando o motor")

    def __str__(self):
        return f"{self.__class__.__name__}: {', '.join([f'{chave}={valor}' for chave, valor in self.__dict__.items()])}"


class Motocicleta(Veiculo):
    pass


class Carro(Veiculo):
    pass


class Caminhao(Veiculo):
    def __init__(self, cor, placa, numero_rodas, carregado):
        super().__init__(cor, placa, numero_rodas)
        self.carregado = carregado

    def esta_carregado(self):
        print(f"{'Sim' if self.carregado else 'Não'} estou carregado")


moto = Motocicleta("preta", "abc-1234", 2)
carro = Carro("branco", "xde-0098", 4)
caminhao = Caminhao("roxo", "gfd-8712", 8, True)

print(moto)
print(carro)
print(caminhao)
```


## 🟩 Vídeo 04 - Hands-on: Herança Múltipla

# Parte 2 - Materiais de apoio e Questionário

## Materiais de apoio

Disponibilizados os seguinte arquivos em formato MS Powerpoint:

- Programacao_Orientada_a_Objetos_Com_Python.pptx

## Certificado: Introdução à Programação Orientada a Objetos (POO) com Python

Link do certificado: 