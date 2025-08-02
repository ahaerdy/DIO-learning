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

## 🟩 Vídeo 03 - O que são Interfaces

## 🟩 Vídeo 04 - Classes Abstratas


# Parte 2 - Materiais de apoio e Questionário

## Materiais de apoio

- O material de apoio desta aula não foi disponibilizado.

## Certificado: Conhecendo Polimorfismo em Python
Link do certificado: https://hermes.dio.me/certificates/OBIHTKZH.pdf