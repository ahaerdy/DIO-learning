- Instrutor: Guilherme Arthur de Carvalho (Analista de Sistemas)
- Contato Linkedin: https://www.linkedin.com/in/decarvalhogui/

# Parte 1 -  Conhecendo Polimorfismo em Python

## 🟩 Vídeo 01 - O que é Polimorfismo?

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.04-curso.04-video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/polimorfismo/learning/9be015a1-3af8-463b-9cda-a9021e1d1fcb?autoplay=1

### Muitas formas!

- A palavra polimorfismo significa ter muitas formas. Na programação, polimorfismo significa o mesmo nome de função (mas assinaturas diferentes) sendo usado para tipos diferentes.

### Exemplo

```python
len("python")
len([10, 20, 30])
```
## 🟩 Vídeo 02 - Polimorfismo com Herança

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.04-curso.04-video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

Link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/polimorfismo/learning/7a3fff2c-bc24-4c2f-89a4-009c24bec546?autoplay=1

### Mesmo método com comportamento diferente

- Na herança, a classe filha herda os métodos da classe pai. No entanto, é possível modificar um método em uma classe filha herdada da classe pai. Isso é particularmente útil nos casos em que o método herdado da classe pai não se encaixa perfeitamente na classe filha.

### Exemplo

```python
class Passaro:
    def voar(self): pass

class Pardal(Passaro):
    def voar(self):
        print("Pardal voa")

class Avestruz(Passaro):
    def voar(self):
        print("Avestruz não voa")

def plano_de_voo(passaro):
    passaro.voar()

plano_de_voo(Pardal())
plano_de_voo(Avestruz())
```

### 🟥🟥🟥 Passo a Passo da execução do código

#### 1️⃣ Definição da classe base `Passaro`

```python
class Passaro:
    def voar(self): pass
```

- Aqui criamos uma classe base chamada `Passaro`.
- O método voar é declarado, mas não tem implementação (pass é uma instrução que não faz nada).
- Serve como um esqueleto para ser reutilizado ou sobrescrito nas classes filhas.

#### 2️⃣ Classe Pardal herda de Passaro

```python
class Pardal(Passaro):
    def voar(self):
        print("Pardal voa")
```

- A classe `Pardal` é uma subclasse de `Passaro`.
- Ela reescreve (sobrescreve) o método voar, definindo um comportamento específico: imprimir "Pardal voa".
- Isso é um exemplo de polimorfismo por sobrescrita de método.

#### 3️⃣ Classe `Avestruz` herda de `Passaro`

```python
class Avestruz(Passaro):
    def voar(self):
        print("Avestruz não voa")
```

- `Avestruz` também herda de `Passaro`, mas tem comportamento diferente: imprime "Avestruz não voa".
- Mesmo método (voar), comportamento diferente — isso é polimorfismo em ação.

#### 4️⃣ Função `plano_de_voo`

```python
def plano_de_voo(passaro):
    passaro.voar()
```

- Essa função recebe um objeto qualquer chamado `passaro`.
- Ela chama o método `voar`, esperando que o objeto recebido tenha esse método.
- Aqui ocorre o polimorfismo real: a função não se importa se o objeto é um `Pardal` ou um `Avestruz`. Ela apenas chama voar().

#### 5️⃣ Chamadas finais

```python
plano_de_voo(Pardal())
plano_de_voo(Avestruz())
```

- `Pardal()` cria uma instância de `Pardal` e passa para a função.
- Resultado: Pardal voa
- `Avestruz()` cria uma instância de `Avestruz` e passa para a função.
- Resultado: Avestruz não voa

#### ✅ Conclusão

- Esse exemplo mostra polimorfismo com herança e sobrescrita de métodos:
  - Mesmo nome de método (voar) em várias classes;
  - Cada classe tem seu comportamento próprio;
  - Uma função genérica (plano_de_voo) pode trabalhar com qualquer tipo de pássaro, sem saber o tipo exato.

# Parte 2 - Materiais de apoio e Questionário

## Materiais de apoio

- O material de apoio desta aula não foi disponibilizado.

## Certificado: Conhecendo Polimorfismo em Python
Link do certificado: https://hermes.dio.me/certificates/OBIHTKZH.pdf