- Instrutor: Guilherme Arthur de Carvalho (Analista de Sistemas)
- Contato Libkedin: https://www.linkedin.com/in/decarvalhogui/

# Parte 1 -  Ampliando o conhecimento em POOConhecendo Polimorfismo em Python

## 🟩 Vídeo 01 - Variáveis de classe e Variáveis de instância

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


- ✅ Se **preciso do contexto da classe** --> uso `@classmethod` (método de classe)
- ✅ Se **não preciso nem de classe nem de instância** --> uso `@staticmethod` (método estático)

## 🟩 Vídeo 03 - O que são Interfaces

## 🟩 Vídeo 04 - Classes Abstratas


# Parte 2 - Materiais de apoio e Questionário

## Materiais de apoio

- O material de apoio desta aula não foi disponibilizado.

## Certificado: Conhecendo Polimorfismo em Python
Link do certificado: https://hermes.dio.me/certificates/OBIHTKZH.pdf