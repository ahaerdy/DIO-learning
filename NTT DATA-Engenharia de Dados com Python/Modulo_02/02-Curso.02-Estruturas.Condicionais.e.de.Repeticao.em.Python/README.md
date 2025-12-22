# Instrutor

- Guilherme Arthur de Carvalho (Analista de Sistemas)
- Contato Linkedin: https://www.linkedin.com/in/decarvalhogui/

# Parte 1 - Estruturas condicionais e de repetição

## 🟩 Vídeo 01 - Indentação e blocos

![[bootcamp-ntt-data_modulo_02-Curso_02-Video_01.webm|vid-60]]

Link do vídeo:  https://web.dio.me/track/engenharia-dados-python/course/estruturas-condicionais-e-de-repeticao-em-python/learning/f9b78902-9c92-4a12-b411-9b78a56b15d1?autoplay=1

O vídeo explica como o **Python** utiliza a **identação** obrigatória para estruturar blocos de comandos, diferenciando-se de linguagens como Java ou C que usam chaves. O autor ressalta que o recuo de espaços não serve apenas à estética, mas é uma **regra sintática** essencial para o interpretador identificar o início e o fim de funções ou estruturas condicionais. Através de exemplos práticos de transações bancárias, demonstra-se que o código Python se torna naturalmente mais **legível** e organizado devido a essa exigência. O conteúdo enfatiza que a falta de formatação adequada impede o funcionamento do programa, tornando a linguagem mais intrusiva porém mais **manutenível**. Por fim, incentiva-se a prática da convenção de quatro espaços para garantir a correta hierarquia entre os blocos de código.

### Anotações

#### Aprender como o interpretador Python utiliza a indentação do código para delimitar os blocos de comandos

- Identar código é uma forma de manter o código fonte mais legível e manutenível. Mas em Python ela exerce um segundo papel, através da indentação o interpretador consegue determinar onde um bloco de comando inicia e onde ele termina.

#### Bloco de comando

- As linguagens de programação costumam utilizar caracteres ou palavras reservadas para terminar o início e fim do bloco. Em Java e C por exemplo, utilizamos chaves:


##### Bloco em Java

```java
void sacar(double valor) {  // início do bloco do método

    if (this.saldo >= valor) { // início do bloco do if
    
        this.saldo -= valor;

    } // fim do bloco do if

}  // fim do bloco do método
```

##### Bloco em Java sem formatar

```java
void sacar(double valor) {  // início do bloco do método
if (this.saldo >= valor) {  // início do bloco do if
this.saldo -= valor;
}  // fim do bloco do if
}  // fim do bloco do método
```

#### Utilizando espaços

- Existe uma convenção em Python, que define as boas práticas para escrita de código na linguagem. Nesse documento é indicado utilizar 4 espaços em branco por nível de indentação, ou seja, a cada novo bloco adicionamos 4 novos espaços em branco.

##### Bloco em Python

```python
def sacar(self, valor: float) -> None:  # início do bloco do método

    if self.saldo >= valor:  # início do bloco do if
    
        self.saldo -= valor
    
    # fim do bloco do if

# fim do bloco do método
```

##### ⚠️ Isso não funciona em Python!

```python
def sacar(self, valor: float) -> None:  # início do bloco do método
if self.saldo >= valor:  # início do bloco do if    
self.saldo -= valor
# fim do bloco do if
# fim do bloco do método
```

#### Qual versão é mais fácil de ler?

```python
void sacar(double valor) {
if (this.saldo >= valor) {
this.saldo -= valor;}}


def sacar(self, valor: float) -> None:
    if self.saldo >= valor:
        self.saldo -= valor
```

#### 🧪 Exemplo utilizado na aula

```python
def sacar(valor):  # início do bloco do método

    saldo=500

    if saldo >= valor:  # início do bloco do if
        print ("valor sacado, retire seu dinheiro na boca do caixa.")
    
    # fim do bloco do if
    print("Obrigado por ser nosso cliente, tenha um bom dia!")

# fim do bloco do método

sacar(100)
```

#### 📤 Saída:

```python
Valor sacado, retire seu dinheiro na boca do caixa.
Obrigado por ser nosso cliente, tenha um bom dia!
```

## 🟩 Vídeo 02 - Estruturas condicionais


Link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/estruturas-condicionais-e-de-repeticao-em-python/learning/fae4234b-5e28-432b-9fd2-495bc75e28ed?autoplay=1

## 🟩 Vídeo 03 - Estruturas de Repetição


Link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/estruturas-condicionais-e-de-repeticao-em-python/learning/08d2c695-d00c-4e10-b698-057323c08d9f?autoplay=1


##  Materiais de Apoio



# Certificado: 

- Link na plataforma: 
- Certificado em pdf: 
