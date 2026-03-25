- Instrutor: Guilherme Arthur de Carvalho (Analista de Sistemas)
- Contato Linkedin: https://www.linkedin.com/in/decarvalhogui/

# Parte 1 -  Aplicando Encapsulamento em Python

## 🟩 Vídeo 01 - O que é Encapsulamento?

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.04-curso.03-video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: 

## Proteção de acesso

- O encapsulamento é um dos conceitos fundamentais em programação orientada a objetos. Ele descreve a ideia de agrupar dados e os métodos que manipulam esses dados em uma unidade. Isso impõe restrições ao acesso direto a variáveis e métodos e pode evitar a modificação acidental de dados. Para evitar alterações acidentais, a variável de um objeto só pode ser alterada pelo método desse objeto.

## 🟩 Vídeo 02 - Recursos públicos e privados

### Modificadores de acesso

- Em linguagens como Java e C++, existem palavras reservadas para definir o nível de acesso aos atributos e métodos da classe. Em Python não temos palavras reservadas, porém usamos convenções no nome do recurso, para definir se a variável é pública ou privada.

### Definição

- Público: Pode ser acessado de fora da classe.
- Privado: Só pode ser acessado pela classe.

### Público/Privado

- Todos os recursos são públicos, a menos que o nome inicie com underline. Ou seja, o interpretador Python não irá garantir a proteção do recurso, mas por ser uma convenção amplamente adotada na comunidade, quando encontramos uma variável e/ou método com nome iniciado por underline, sabemos que não deveríamos manipular o seu valor diretamente, ou invocar o método fora do escopo da classe.

### Exemplo (códifo Python para representar o diagrama de classe anterior)

```python
class Conta:
    def __init__(self, saldo=0):    # método __init__ construtor da classe Conta
        self._saldo = saldo     # o atributo saldo começa com underline, para indicar que é privado
                                # e ele recebe o valor de saldo

    # os métodos depositar e sacar são públicos

    def depositar(self, valor):     
        pass

    def sacar(self, valor):
        pass
```

- Se eu quisesse tornar o depositar em método privado eu colocaria o underline 1a frente do nome para converncioanrhttps://hermes.dio.me/certificates/ZSXJMW71.pdf que ele não poderia ser chamado externamente.

## 🟩 Vídeo 03 - Propriedades

### Para que servem?

- Com o `property()` do Python, você pode criar atributos gerenciados em suas classes. Você pode usar atributos gerenciados, também conhecidos como propriedades, quando precisar modificar sua implementação interna sem alterar a API pública da classe.

### Exemplo

```python
class Foo:
    def __init__(self, x=None):
        self._x = x

    @property                   
    def x(self):                
        return self._x or 0

    @x.setter                   
    def x(self, value):
        _x = self._x or 0
        _value = value or 0
        self._x = _x + _value

    @x.deleter                 
    def x(self):
        self._x = -1


foo = Foo(10)
print(foo.x)
foo.x = 10
print(foo.x)
del foo.x
print(foo.x)
```

### 🟥🟥🟥 Passo a Passo da execução do código

#### 1️⃣ foo = Foo(10)
- Cria um objeto da classe Foo.
- Chama o método `__init__` com x = 10.
- Atribui: `self._x` = 10
- Resultado: `foo._x` == 10

#### 2️⃣ print(foo.x)
- Chama o getter @property x:
 
  ```python
  def x(self):
    return self._x or 0
  ```

- `self._x` é 10, que é "Verdadeiro" → retorna 10.
- Resultado impresso

  ```
  10
  ```

#### 3️⃣ foo.x = 10
- Chama o setter @x.setter:

  ```python
  def x(self, value):
    _x = self._x or 0
    _value = value or 0
    self._x = _x + _value
  ```

- `self._x` = 10 → `_x` = 10
- `value` = 10 → `_value` = 10
- Calcula: `self._x` = 10 + 10 = 20
- Resultado: `foo._x` == 20

#### 4️⃣ print(foo.x)
- Chama novamente o getter:

```python
return self._x or 0
```

- Agora `self._x == 20`, que é "Verdadeiro" → retorna 20.
- Resultado impresso:
  ```
  20
  ```

#### 5️⃣ del foo.x
- Chama o deleter `@x.deleter`:

```python
def x(self):
    self._x = -1
```

- Atribui `self._x` = -1
- Resultado: `foo._x` == -1

#### 6️⃣ print(foo.x)
- Chama o getter mais uma vez:

```python
return self._x or 0
```

- `self._x` == -1 → que é "Verdadeiro", então retorna -1
- Resultado impresso:
  ```
  -1
  ```

### 🟨🟨🟨 `@property` (aprofundando)

- O `@property` não "protege" no sentido de segurança absoluta, mas ele encapsula e controla o acesso ao dado interno, o que ajuda a manter a integridade do objeto.

#### 🔓 Sem `@property`: acesso direto ao atributo interno

```python
class Conta:
    def __init__(self, saldo):
        self.saldo = saldo  # público

c = Conta(100)
c.saldo = -500  # ops! saldo negativo? nada impede.
```

#### 🔐 Com `@property`: controle no acesso

```python
class Conta:
    def __init__(self, saldo):
        self._saldo = saldo  # "protegido" por convenção

    @property
    def saldo(self):
        return self._saldo

    @saldo.setter
    def saldo(self, valor):
        if valor < 0:
            raise ValueError("Saldo não pode ser negativo")
        self._saldo = valor

c = Conta(100)
c.saldo = -500  # agora isso lança um erro
```

#### 📌 Como o `@property` ajuda na proteção?

- Encapsula o acesso ao atributo real (geralmente com prefixo `_`).
- Permite interpor regras ou validações antes de ler, escrever ou apagar.
- Oculta a implementação interna sem alterar como o atributo é acessado.
- Impede alterações diretas sem passar pela lógica de verificação.

# Parte 2 - Materiais de apoio e Questionário

## Materiais de apoio

- O material de apoio desta aula não foi disponibilizado.

## Certificado: Aplicando Encapsulamento em Python
Link do certificado: https://hermes.dio.me/certificates/ZSXJMW71.pdf