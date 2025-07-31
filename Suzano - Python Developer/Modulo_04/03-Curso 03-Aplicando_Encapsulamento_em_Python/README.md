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

# Parte 2 - Materiais de apoio e Questionário

## Materiais de apoio

- O material de apoio desta aula não foi disponibilizado.

## Certificado: Aprendendo o Conceito de Herança com Python
Link do certificado: https://hermes.dio.me/certificates/IYESG1OU.pdf