- Instrutor: Guilherme Arthur de Carvalho (Analista de Sistemas)
- Contato Linkedin: https://www.linkedin.com/in/decarvalhogui/

# Parte 1 -  Ampliando o conhecimento em POO

## 🟩 Vídeo 01 - Variáveis de classe e Variáveis de instância

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.04-curso.05-video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/ampliando-o-conhecimento-em-poo/learning/9e2a04d1-4591-43bf-a6d2-56ad7144e69d?autoplay=1

### Objetivo Geral

- Entender as diferenças entre variáveis de classe e variáveis de instância.

### Atributos do objeto

- Todos os objetos nascem com o mesmo número de atributos de classe e de instância. Atributos de instância são diferentes para cada objeto (cada objeto tem uma cópia), já os atributos de classe são compartilhados entre os objetos.

### Exemplo

```python
class Estudante:
    escola = "DIO"

    def __init__(self, nome, numero):
        self.nome = nome
        self.numero = numero

    def __str__(self):
        return f"{self.nome} ({self.numero}) - {self.escola}"

gui = Estudante("Guilherme", 56451)
gi = Estudante("Giovanna", 17323)
```

## 🟩 Vídeo 02 - Métodos de classe e Métodos estático
 
<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.04-curso.05-video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/ampliando-o-conhecimento-em-poo/learning/eb1cfc19-96ab-4f5f-8f5a-197b4bce7f01?autoplay=1

### Objetivo Geral

- Entender as diferenças entre métodos de classe e métodos estáticos.

### Métodos de classe

- Métodos de classe estão ligados à classe e não ao objeto. Eles têm acesso ao estado da classe, pois recebem um parâmetro que aponta para a classe e não para a instância do objeto.

### Métodos estáticos

- Um método estático não recebe um primeiro argumento explícito. Ele também é um método vinculado à classe e não ao objeto da classe. Este método não pode acessar ou modificar o estado da classe. Ele está presente em uma classe porque faz sentido que o método esteja presente na classe.

### Métodos de classe x métodos estáticos

- Um método de classe recebe um primeiro parâmetro que aponta para a classe, enquanto um método estático não.
- Um método de classe pode acessar ou modificar o estado da classe enquanto um método estático não pode acessá-lo ou modificá-lo.

### Quando utilizar método de classe ou estático

- Geralmente usamos o método de classe para criar métodos de fábrica.
- Geralmente usamos métodos estáticos para criar funções utilitárias.

### Exemplo

```python
class Pessoa:
    def __init__(self, nome=None, idade=None):
        self.nome = nome
        self.idade = idade

    @classmethod
    def criar_de_data_nascimento(cls, ano, mes, dia, nome):
        idade = 2022 - ano
        return cls(nome, idade)

# p = Pessoa("Guilherme", 28)
# print(p.nome, p.idade)

p = Pessoa.criar_de_data_nascimento(1994, 3, 21, "Guilherme")
print(p.nome, p.idade)
```

### 🟥🟥🟥 Passo a Passo da execução do código

#### 1️⃣ Definição da Classe `Pessoa`

```python
class Pessoa:
```

- Aqui está sendo definida a classe Pessoa, que modela um objeto com nome e idade.

#### 2️⃣ Método `__init__`

```python
def __init__(self, nome=None, idade=None):
    self.nome = nome
    self.idade = idade
```

- Esse é o construtor da classe. Ele é chamado quando uma nova instância da classe Pessoa é criada.
    - Parâmetros: nome e idade (ambos opcionais, valor padrão None)
    - Atribui os valores recebidos aos atributos self.nome e self.idade.

#### 3️⃣ Método de Classe `criar_de_data_nascimento`

```python
@classmethod
def criar_de_data_nascimento(cls, ano, mes, dia, nome):
    idade = 2022 - ano
    return cls(nome, idade)
```

- A anotação `@classmethod` indica que este método pertence à classe, não à instância.
- `cls` é uma referência à classe (como `self` é à instância).
- O método recebe uma data de nascimento (ano, mês, dia) e o nome.
- Calcula a idade como 2022 - ano 

#### 4️⃣ Criação da Instância Usando o Método de Classe

```python
p = Pessoa.criar_de_data_nascimento(1994, 3, 21, "Guilherme")
```

- Aqui, cria-se uma instância da classe Pessoa a partir de uma data de nascimento:

    - 1994 → ano de nascimento
    - 3 → mês
    - 21 → dia
    - "Guilherme" → nome

- Calcula-se a idade como 2022 - 1994 = 28, e retorna-se Pessoa("Guilherme", 28).

#### 5️⃣ Impressão

```python
print(p.nome, p.idade)
```

- Exibe:
  ```
  Guilherme 28
  ```

### Novo Código

```python
class Pessoa:
    def __init__(self, nome=None, idade=None):
        self.nome = nome
        self.idade = idade

    @classmethod
    def criar_de_data_nascimento(cls, ano, mes, dia, nome):
        idade = 2022 - ano
        return cls(nome, idade)

    @staticmethod
    def e_maior_idade(idade):
        return idade > 18

# p = Pessoa("Guilherme", 28)
# print(p.nome, p.idade)

p = Pessoa.criar_de_data_nascimento(1994, 3, 21, "Guilherme")
print(p.nome, p.idade)

Pessoa.e_maior_idade(18)
Pessoa.e_maior_idade(28)
```

- Esse novo trecho de código é uma extensão do anterior, adicionando um método estático para verificar se uma pessoa é maior de idade. 

#### Método Estático e_maior_idade

```python
@staticmethod
def e_maior_idade(idade):
    return idade > 18
```

- Esse método:
    - É estático: não acessa nem a instância (self) nem a classe (cls);
    - Recebe um valor de idade e retorna True se for maior que 18.

- Exemplos:
  ```python
  Pessoa.e_maior_idade(18)  # False
  Pessoa.e_maior_idade(28)  # True
  ```

#### Criação e Impressão
  
```python
p = Pessoa.criar_de_data_nascimento(1994, 3, 21, "Guilherme")
print(p.nome, p.idade)
```

- Cria um objeto Pessoa com nome = "Guilherme" e idade = 2022 - 1994 = 28;
- Imprime: Guilherme 28

#### Uso do Método Estático

```python
Pessoa.e_maior_idade(18)  # False
Pessoa.e_maior_idade(28)  # True
```

- Essas chamadas verificam se os valores informados representam uma pessoa maior de idade.

### Conclusão

| Elemento                   | Tipo                | Finalidade                                  |
| -------------------------- | ------------------- | ------------------------------------------- |
| `__init__`                 | Método de instância | Cria o objeto com nome e idade              |
| `criar_de_data_nascimento` | Método de classe    | Cria o objeto com base no ano de nascimento |
| `e_maior_idade`            | Método estático     | Avalia se uma idade é maior que 18          |


- ✅ Se **preciso do contexto da classe** ⇒ uso `@classmethod` (método de classe)
- ✅ Se **não preciso nem de classe, nem de instância** ⇒ uso `@staticmethod` (método estático)

## 🟩 Vídeo 03 - O que são Interfaces

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.04-curso.05-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/ampliando-o-conhecimento-em-poo/learning/ad015503-d5fe-4cf5-9250-64acf63b4285?autoplay=1

### Objetivo Geral

- Aprender o conceito de contrato e como podemos utilizar classes abstratas em Python para implementá-los.

### Importante!

- Interfaces definem o que uma classe deve fazer e não como.

### Python tem interface?

- O conceito de interface é definir um contrato, onde são declarados os métodos (o que deve ser feito) e suas respectivas assinaturas. Em Python utilizamos classes abstratas para criar contratos. Classes abstratas não podem ser instanciadas.

## 🟩 Vídeo 04 - Classes Abstratas

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.04-curso.05-video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: 

### ABC

- Por padrão, o Python não fornece classes abstratas. O Python vem com um módulo que fornece a base para definir as classes abstratas, e o nome do módulo é ABC. O ABC funciona decorando métodos da classe base como abstratos e, em seguida, registrando classes concretas como implementações da base abstrata. Um método se torna abstrato quando decorado com `@abstractmethod`.

### Exemplo

```python
from abc import ABC, abstractmethod, abstractproperty


class ControleRemoto(ABC):
    @abstractmethod
    def ligar(self):
        pass

    @abstractmethod
    def desligar(self):
        pass

    @property
    @abstractproperty
    def marca(self):
        pass


class ControleTV(ControleRemoto):
    def ligar(self):
        print("Ligando a TV...")
        print("Ligada!")

    def desligar(self):
        print("Desligando a TV...")
        print("Desligada!")

    @property
    def marca(self):
        return "Philco"


class ControleArCondicionado(ControleRemoto):
    def ligar(self):
        print("Ligando o Ar Condicionado...")
        print("Ligado!")

    def desligar(self):
        print("Desligando o Ar Condicionado...")
        print("Desligado!")

    @property
    def marca(self):
        return "LG"


controle = ControleTV()
controle.ligar()
controle.desligar()
print(controle.marca)


controle = ControleArCondicionado()
controle.ligar()
controle.desligar()
print(controle.marca)
```

### 🟥🟥🟥 Passo a Passo da execução do código

#### 1️⃣ Importações

```python
from abc import ABC, abstractmethod, abstractproperty
```

- `ABC`: Classe base para definir uma classe abstrata.
- `abstractmethod`: Decorador que marca um método como abstrato, ou seja, deve ser implementado pelas subclasses.
- ``abstractproperty`: Decorador legado para propriedades abstratas (hoje usamos `@property` + `@abstractmethod`).

#### 2️⃣ Definição da Interface: `ControleRemoto`

```python
class ControleRemoto(ABC):
```

- Essa é a interface (classe abstrata) que define o "contrato" para qualquer controle remoto.
- Herda de ABC, então não pode ser instanciada diretamente.

##### Métodos abstratos:

```python
@abstractmethod
def ligar(self):
    pass

@abstractmethod
def desligar(self):
    pass
```

- São métodos obrigatórios para qualquer classe que herde de `ControleRemoto`.

##### Propriedade abstrata:

```python
@property
@abstractproperty
def marca(self):
    pass
```

- Define que toda subclasse deve ter uma propriedade chamada marca.

#### 3️⃣ Implementação concreta: `ControleTV`

```python
class ControleTV(ControleRemoto):
```

- Herda de ControleRemoto e implementa todos os métodos e propriedades exigidos.

##### Métodos:

```python
def ligar(self):
    print("Ligando a TV...")
    print("Ligada!")

def desligar(self):
    print("Desligando a TV...")
    print("Desligada!")
```

##### Propriedade:

```python
@property
def marca(self):
    return "Philco"
```

#### 4️⃣ Outra implementação: `ControleArCondicionado`

```python
class ControleArCondicionado(ControleRemoto):
```

- Também herda de `ControleRemoto` e implementa os métodos e a propriedade.

##### Métodos:

```python
def ligar(self):
    print("Ligando o Ar Condicionado...")
    print("Ligado!")

def desligar(self):
    print("Desligando o Ar Condicionado...")
    print("Desligado!")
```

##### Propriedade:

```python
@property
def marca(self):
    return "LG"
```

#### 5️⃣ Execução do código

```python
controle = ControleTV()
controle.ligar()
controle.desligar()
print(controle.marca)
```

- Cria um objeto da classe ControleTV.
- Chama os métodos ligar e desligar.
- Imprime a marca: "Philco".

```python
controle = ControleArCondicionado()
controle.ligar()
controle.desligar()
print(controle.marca)
```

- Agora o objeto é da classe ControleArCondicionado.
- Executa os métodos e imprime a marca: "LG".

### Conclusão

- Esse algoritmo demonstra perfeitamente o uso de interfaces com classes abstratas em Python:

    - Define um contrato com métodos e propriedades obrigatórias.
    - Garante que todas as subclasses implementem esse contrato.
    - Permite polimorfismo: diferentes controles com comportamentos específicos, mas mesma interface.


# Parte 2 - Materiais de apoio e Questionário

## Materiais de apoio

- O material de apoio desta aula não foi disponibilizado.

## Certificado: Interfaces e Classes Abstratas com Python
Link do certificado: https://hermes.dio.me/certificates/BHZ9MWI7.pdf