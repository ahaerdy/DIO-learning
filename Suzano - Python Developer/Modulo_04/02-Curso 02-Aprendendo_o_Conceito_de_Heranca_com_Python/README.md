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
    <img src="images/image.png" alt="" width="560">
</p>

### Código (exemplo)

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

### Análise

#### 1. Definição da classe base Veiculo
Construtor (__init__): recebe cor, placa e numero_rodas e atribui aos atributos do objeto.

- Método ligar_motor(): simplesmente imprime "Ligando o motor".
- Método especial __str__():
    - Retorna uma string representando o objeto.
    - Usa self.__class__.__name__ para pegar o nome da classe (ex: Carro, Motocicleta).
    - Usa um list comprehension para montar uma lista de chave=valor para todos os atributos do objeto (self.__dict__.items()).
    - Junta tudo com vírgulas e retorna no formato:
        - Classe: atributo1=valor1, atributo2=valor2, ...

#### 2. Classes filhas Motocicleta e Carro

    - Herdam diretamente de Veiculo.
    - Não acrescentam ou alteram nada (pass), então usam tudo da classe base.

#### 3. Classe filha Caminhao

    - Sobrescreve o construtor para adicionar um atributo extra carregado.
    - Usa super().__init__(...) para chamar o construtor da classe base e inicializar os atributos herdados.
    - Define método esta_carregado() que imprime se o caminhão está carregado ou não, baseado no atributo carregado.

#### . Criação e impressão dos objetos

    - Três objetos são criados:
        
        - moto: Motocicleta preta, placa "abc-1234", 2 rodas.
        - carro: Carro branco, placa "xde-0098", 4 rodas.
        - caminhao: Caminhão roxo, placa "gfd-8712", 8 rodas, carregado (True).
        - print(moto), print(carro), print(caminhao) chamam implicitamente o método __str__() definido em Veiculo.

#### 5. Exemplo da saída esperada

    
    Motocicleta: cor=preta, placa=abc-1234, numero_rodas=2
    Carro: cor=branco, placa=xde-0098, numero_rodas=4
    Caminhao: cor=roxo, placa=gfd-8712, numero_rodas=8, carregado=True
    
#### 6. Observações finais

    - O método __str__() facilita a visualização do estado do objeto.
    - A herança reutiliza código da classe base.
    - O uso de super().__init__() no Caminhao é a forma de estender o construtor.
    - O método esta_carregado() é um exemplo de funcionalidade específica da subclasse.


## 🟩 Vídeo 04 - Hands-on: Herança Múltipla

# Parte 2 - Materiais de apoio e Questionário

## Materiais de apoio

Disponibilizados os seguinte arquivos em formato MS Powerpoint:

- Programacao_Orientada_a_Objetos_Com_Python.pptx

## Certificado: Introdução à Programação Orientada a Objetos (POO) com Python

Link do certificado: 