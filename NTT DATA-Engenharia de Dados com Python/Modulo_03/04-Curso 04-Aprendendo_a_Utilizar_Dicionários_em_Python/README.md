# Parte 1 - Aprendendo a Utilizar Dicionários em Python

## 🟩 Vídeo 01 - Dicionários: Criação e acesso aos dados

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp-ntt-data_modulo_03-Curso_04-Video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/aprendendo-a-utilizar-dicionarios-em-python/learning/d60c0324-9369-4e88-9354-abc1dfc876a7?autoplay=1

### Objetivo Geral

- Entender o funcionamento da estrutura de dados dicionário.

### Criando dicionários

- Um dicionário é um conjunto não-ordenado de pares chave:valor, onde as chaves são únicas em uma dada instância do dicionário. Dicionários são delimitados por chaves: {}, e contém uma lista de pares chave:valor separada por vírgulas.

### Exemplo

```python
pessoa = {"nome": "Guilherme", "idade": 28}

pessoa = dict(nome="Guilherme", idade=28)

pessoa["telefone"] = "3333-1234"  # {"nome": "Guilherme", "idade": 28, "telefone": "3333-1234"}
```

### Acesso aos dados

- Os dados são acessados e modificados através da chave.

### Exemplo

```python
dados = {"nome": "Guilherme", "idade": 28, "telefone": "3333-1234"}

dados["nome"]  # "Guilherme"
dados["idade"]  # 28
dados["telefone"]  # "3333-1234"

dados["nome"] = "Maria"
dados["idade"] = 18
dados["telefone"] = "9988-1781"

dados  # {"nome": "Maria", "idade": 18, "telefone": "9988-1781"}
```

### Dicionários aninhados

```python
contatos = {
    "guilherme@gmail.com": {"nome": "Guilherme", "telefone": "3333-2221"},
    "giovanna@gmail.com": {"nome": "Giovanna", "telefone": "3443-2121"},
    "chappie@gmail.com": {"nome": "Chappie", "telefone": "3344-9871"},
    "melaine@gmail.com": {"nome": "Melaine", "telefone": "3333-7766"},
}

contatos["giovanna@gmail.com"]["telefone"]  # "3443-2121"
```

### Iterar dicionários

```python
for chave in contatos:
    print(chave, contatos[chave])

for chave, valor in contatos.items():
	print(chave, valor)

# guilherme@gmail.com {'nome': 'Guilherme', 'telefone': '3333-2221'}
# giovanna@gmail.com {'nome': 'Giovanna', 'telefone': '3443-2121'}
# chappie@gmail.com {'nome': 'Chappie', 'telefone': '3344-9871'}
# melaine@gmail.com {'nome': 'Melaine', 'telefone': '3333-7766'}
```

## 🟩 Vídeo 02 - Métodos da classe dict

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp-ntt-data_modulo_03-Curso_04-Video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/aprendendo-a-utilizar-dicionarios-em-python/learning/02bcfa47-c93d-4d79-a2dd-027dd950e2d5?autoplay=1


### {}.clear

```python
contatos = {
    "guilherme@gmail.com": {"nome": "Guilherme", "telefone": "3333-2221"},
    "giovanna@gmail.com": {"nome": "Giovanna", "telefone": "3443-2121"},
    "chappie@gmail.com": {"nome": "Chappie", "telefone": "3344-9871"},
    "melaine@gmail.com": {"nome": "Melaine", "telefone": "3333-7766"},
}

contatos.clear()
contatos  # {}
```

### {}.copy

```python
contatos = {
    "guilherme@gmail.com": {"nome": "Guilherme", "telefone": "3333-2221"}
}

copia = contatos.copy()
copia["guilherme@gmail.com"] = {"nome": "Gui"}

contatos["guilherme@gmail.com"]  # {"nome": "Guilherme", "telefone": "3333-2221"}
copia["guilherme@gmail.com"]  # {"nome": "Gui"}
```

### {}.fromkeys

```python
dict.fromkeys(["nome", "telefone"])  # {"nome": None, "telefone": None}

dict.fromkeys(["nome", "telefone"], "vazio")  # {"nome": "vazio", "telefone": "vazio"}
```

### {}.get

```python
contatos = {
    "guilherme@gmail.com": {"nome": "Guilherme", "telefone": "3333-2221"}
}

contatos["chave"]  # KeyError

contatos.get("chave")  # None
contatos.get("chave", {})  # {}
contatos.get("guilherme@gmail.com", {})  # {"guilherme@gmail.com": {"nome": "Guilherme", "telefone": "3333-2221"}
```

### {}.items

```python
contatos = {
    "guilherme@gmail.com": {"nome": "Guilherme","telefone": "3333-2221"}
}

contatos.items()  # dict_items([('guilherme@gmail.com', {'nome': 'Guilherme', 'telefone': '3333-2221'})])
```

### {}.keys

```python
contatos = {
    "guilherme@gmail.com": {"nome": "Guilherme","telefone": "3333-2221"}
}

contatos.keys()  # dict_keys(['guilherme@gmail.com'])
```

### {}.pop

```python
contatos = {
    "guilherme@gmail.com": {"nome": "Guilherme","telefone": "3333-2221"}
}

contatos.pop("guilherme@gmail.com")  # {'nome': 'Guilherme', 'telefone': '3333-2221'}
contatos.pop("guilherme@gmail.com", {})  # {}
```

### {}.popitem

```python
contatos = {
    "guilherme@gmail.com": {"nome": "Guilherme","telefone": "3333-2221"}
}

contatos.popitem()  # ('guilherme@gmail.com', {'nome': 'Guilherme', 'telefone': '3333-2221'})
contatos.popitem()  # KeyError
```

### {}.setdefault

```python
contato = {'nome': 'Guilherme', 'telefone': '3333-2221'}

contato.setdefault("nome", "Giovanna")  # "Guilherme"
contato  # {'nome': 'Guilherme', 'telefone': '3333-2221'}

contato.setdefault("idade", 28)  # 28
contato  # {'nome': 'Guilherme', 'telefone': '3333-2221', 'idade': 28}
```

### {}.update

```python
contatos = {
    "guilherme@gmail.com": {"nome": "Guilherme","telefone": "3333-2221"}
}

contatos.update({"guilherme@gmail.com": {"nome": "Gui"}})
contatos  # {'guilherme@gmail.com': {'nome': 'Gui'}}

contatos.update({"giovanna@gmail.com": {"nome": "Giovanna", "telefone": "3322-8181"}})
contatos  # {'guilherme@gmail.com': {'nome': 'Gui'}, 'giovanna@gmail.com': {'nome': 'Giovanna', 'telefone': '3322-8181'}}
```

### {}.values

```python
contatos = {
    "guilherme@gmail.com": {"nome": "Guilherme", "telefone": "3333-2221"},
    "giovanna@gmail.com": {"nome": "Giovanna", "telefone": "3443-2121"},
    "chappie@gmail.com": {"nome": "Chappie", "telefone": "3344-9871"},
    "melaine@gmail.com": {"nome": "Melaine", "telefone": "3333-7766"},
}

contatos.values()  # dict_values([{'nome': 'Guilherme', 'telefone': '3333-2221'}, {'nome': 'Giovanna', 'telefone': '3443-2121'}, {'nome': 'Chappie', 'telefone': '3344-9871'}, {'nome': 'Melaine', 'telefone': '3333-7766'}])
```

### in

#### ➡️ O que o in faz?

- A palavra-chave in é usada para verificar se um valor está contido em uma sequência ou coleção, como:
    - strings
    - listas
    - tuplas
    - dicionários
    - conjuntos (set)

- Ela retorna um valor booleano: True ou False.

```python
contatos = {
    "guilherme@gmail.com": {"nome": "Guilherme", "telefone": "3333-2221"},
    "giovanna@gmail.com": {"nome": "Giovanna", "telefone": "3443-2121"},
    "chappie@gmail.com": {"nome": "Chappie", "telefone": "3344-9871"},
    "melaine@gmail.com": {"nome": "Melaine", "telefone": "3333-7766"},
}

"guilherme@gmail.com" in contatos  # True
"megui@gmail.com" in contatos  # False
"idade" in contatos["guilherme@gmail.com"]  # False
"telefone" in contatos["giovanna@gmail.com"]  # True
```

### del

#### ➡️ del é uma instrução (ou comando) do Python

- Ela faz parte da sintaxe da linguagem, assim como if, for, while, return etc.
    - 💡 Ou seja: você não pode usá-la como del(), porque ela não é uma função — é uma palavra-chave reservada.

```python
contatos = {
    "guilherme@gmail.com": {"nome": "Guilherme", "telefone": "3333-2221"},
    "giovanna@gmail.com": {"nome": "Giovanna", "telefone": "3443-2121"},
    "chappie@gmail.com": {"nome": "Chappie", "telefone": "3344-9871"},
    "melaine@gmail.com": {"nome": "Melaine", "telefone": "3333-7766"},
}

del contatos["guilherme@gmail.com"]["telefone"]
del contatos["chappie@gmail.com"] 

contatos # {'guilherme@gmail.com': {'nome': 'Guilherme'}, 'giovanna@gmail.com': {'nome': 'Giovanna', 'telefone': '3443-2121'}, 'melaine@gmail.com': {'nome': 'Melaine', 'telefone': '3333-7766'}}
```

# Parte 2 - Materiais de apoio e Questionário

## Materiais de apoio

Disponibilizados os seguinte arquivos em formato MS Powerpoint:
- 04-Dio_Dicionarios.pptx

## Certificado: Aprendendo a Utilizar Dicionários em Python

- Link do certificado: https://hermes.dio.me/certificates/UZPKD2UH.pdf
- Certificado em PDF: [Certificado-Aprendendo.a.Utilizar.Dicionarios.em.Python.pdf](000-Midia_e_Anexos/Certificado-Aprendendo.a.Utilizar.Dicionarios.em.Python.pdf)