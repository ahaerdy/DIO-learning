- Instrutor: Guilherme Arthur de Carvalho (Analista de Sistemas)
- Contato Libkedin: https://www.linkedin.com/in/decarvalhogui/

# Parte 1 -  Aplicando Encapsulamento em Python

## 🟩 Vídeo 01 - O que é Encapsulamento?

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

- Se eu quisesse tornar o depositar em método privado eu colocaria o underline 1a frente do nome para converncioanr que ele não poderia ser chamado externamente.

## 🟩 Vídeo 03 - Propriedades

### Para que servem?

- Com o `property()` do Python, você pode criar atributos gerenciados em suas classes. Você pode usar atributos gerenciados, também conhecidos como propriedades, quando precisar modificar sua implementação interna sem alterar a API pública da classe.

### Exemplo

```python
class Foo:
    def __init__(self, x=None):
        self._x = x

    @property                   # Uso do decorador (neste caso property)
    def x(self):                # que é uma função que executa antes da função x
        return self._x or 0

    @x.setter                   # Aferindo o processo de inserção do atributo x
    def x(self, value):
        _x = self._x or 0
        _value = value or 0
        self._x = _x + _value

    @x.deleter                  # Aferindo o processo de deleção do atributo x
    def x(self):
        self._x = -1


foo = Foo(10)
print(foo.x)
foo.x = 10
print(foo.x)
del foo.x
print(foo.x)
```

### Análise do código

#### Classe Foo

```python
class Foo:
    def __init__(self, x=None):
        self._x = x
```

- O atributo privado _x é inicializado com o valor passado ou `None`.

#### Getter com `@property`

```python
    @property
    def x(self):
        return self._x or 0
```

- Sempre que foo.x for acessado:
    - Se self._x for falso (como None, 0, '', etc), retornará 0.
    - Caso contrário, retornará self._x.

#### Setter com `@x.setter`

```python
    @x.setter
    def x(self, value):
        _x = self._x or 0
        _value = value or 0
        self._x = _x + _value
```

- Ao fazer foo.x = valor, ele não define diretamente o valor recebido.
- Em vez disso, ele value value a _x e atualiza self._x.
- Se self._x ou value forem None, considera-se 0.

#### Deleter com `@x.deleter`

```python
    @x.deleter
    def x(self):
        self._x = -1
```

- Ao chamar del foo.x, self._x passa a valer -1.

### Teste

```
foo = Foo(10)       # self._x = 10
print(foo.x)        # => 10

foo.x = 10          # self._x = 10 + 10 => 20
print(foo.x)        # => 20

del foo.x           # self._x = -1
print(foo.x)        # => -1
```

### 🟥🟥🟥 Passo a Passo da execução do código

#### 1️⃣ foo = Foo(10)
- Cria um objeto da classe Foo.
- Chama o método __init__ com x = 10.
- Atribui: self._x = 10
- Resultado: foo._x == 10




# Parte 2 - Materiais de apoio e Questionário

## Materiais de apoio

- O material de apoio desta aula não foi disponibilizado.

## Certificado: Aprendendo o Conceito de Herança com Python
Link do certificado: https://hermes.dio.me/certificates/IYESG1OU.pdf