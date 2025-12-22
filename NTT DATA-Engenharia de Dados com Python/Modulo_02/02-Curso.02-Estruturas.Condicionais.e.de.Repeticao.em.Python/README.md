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

![[bootcamp-ntt-data_modulo_02-Curso_02-Video_02.webm|vid-60]]

Link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/estruturas-condicionais-e-de-repeticao-em-python/learning/fae4234b-5e28-432b-9fd2-495bc75e28ed?autoplay=1

O vídeo consiste em uma aula educativa focada em **estruturas condicionais na linguagem Python**, abordando desde conceitos básicos até aplicações avançadas. O material explica como utilizar os comandos **if, elif e else** para desviar o fluxo de um programa com base em condições lógicas específicas. Através de exemplos práticos, como sistemas de habilitação de motoristas e operações bancárias, o conteúdo demonstra a implementação de **verificações simples e compostas**. Adicionalmente, o texto detalha o uso de **estruturas aninhadas** para lógica complexa e do **if ternário** para codificação concisa em uma única linha. O objetivo final é capacitar o estudante a controlar tomadas de decisão dentro de seus algoritmos de forma eficiente.

### Anotações

#### O que são as estruturas condicionais e como utilizá-las.

- A estrutura condicional permite o desvio de fluxo de controle, quando determinadas expressões lógicas são atendidas.

#### If

- Para criar uma estrutura condicional simples, composta por um único desvio, podemos utilizar a palavra reservada if. O comando irá testar a expressão lógica, e em caso de retorno verdadeiro as ações presentes no bloco de código do if serão executadas.

#### Exemplo

```python
saldo = 2000.0
saque = float(input("Informe o valor do saque: "))

if saldo >= saque:
    print("Realizando saque!")

if saldo <= saque:
	print("Saldo insuficiente!")
```

#### If/else

- Para criar uma estrutura condicional com dois desvios, podemos utilizar as palavras reservadas if e else. Como sabemos se a expressão lógica testada no if for verdadeira, então o bloco de código do if será executado. Caso contrário o bloco de código do else será executado.

#### Exemplo

```python
saldo = 2000.0
saque = float(input("Informe o valor do saque: "))

if saldo >= saque:
    print("Realizando saque!")
else:
	print("Saldo insuficiente!")
```

#### If/elif/else

- Em alguns cenários queremos mais de dois desvios, para isso podemos utilizar a palavra reservada elif. O elif é composto por uma nova expressão lógica, que será testada e caso retorne verdadeiro o bloco de código do elif será executado. Não existe um número máximo de elifs que podemos utilizar, porém evite criar grandes estruturas condicionais, pois elas aumentam a complexidade do código. 


#### Exemplo

```python
opcao = int(input("Informe uma opção: [1] Sacar \n[2] Extrato: "))

if opcao == 1:
	valor = float(input("Informe a quantia para o saque: "))
	# ...
elif opcao == 2:
	print("Exibindo o extrato...")
else:
	sys.exit("Opção inválida")
```

#### If aninhado

- Podemos criar estruturas condicionais aninhadas, para isso basta adicionar estruturas if/elif/else dentro do bloco de código de estruturas if/elif/else.

#### Exemplo

```python
if conta_normal:
	if saldo >= saque:
		print("Saque realizado com sucesso!")
	elif saque <= (saldo + cheque_especial):
		print("Saque realizado com uso do cheque especial!")
elif conta_universitaria:
	if saldo >= saque:
		print("Saque realizado com sucesso!")
	else:
		print("Saldo insuficiente!")
```

#### OUTRO Exemplo (utilizado em aula)

```python
conta_normal = False
conta_universitaria = False
conta_especial = True

saldo = 2000
saque = 1500
cheque_especial = 450

if conta_normal:

    if saldo >= saque:
        print("Saque realizado com sucesso!")
    elif saque <= (saldo + cheque_especial):
        print("Saque realizado com uso do cheque especial!")
    else:
        print("Não foi possível realizar o saque, saldo insuficiente!")

elif conta_universitaria:

    if saldo >= saque:
        print("Saque realizado com sucesso!")
    else:
        print("Saldo insuficiente!")

elif conta_especial:
    print("Conta especial selecionada!")

else:
    print("Sistema não reconheceu seu tipo de conta, entre em contato com o seu gerente.")

```

#### If ternário

- O if ternário permite escrever uma condição em uma única linha. Ele é composto por três partes, a primeira parte é o retorno caso a expressão retorne verdadeiro, a segunda parte é a expressão lógica e a terceira parte é o retorno caso a expressão não seja atendida.

#### Exemplo

```python
status = "Sucesso" if saldo >= saque else "Falha"

print(f"{status} ao realizar o saque!")
```

## 🟩 Vídeo 03 - Estruturas de Repetição

![[bootcamp-ntt-data_modulo_02-Curso_02-Video_03.webm|vid-60]]

Link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/estruturas-condicionais-e-de-repeticao-em-python/learning/08d2c695-d00c-4e10-b698-057323c08d9f?autoplay=1

O vídeo apresenta uma aula técnica sobre **estruturas de repetição** na linguagem **Python**, com foco prático no uso dos comandos **for** e **while**. O conteúdo explica que o **for** é ideal para percorrer objetos iteráveis quando se conhece o número de repetições, enquanto o **while** é indicado para laços baseados em condições lógicas variáveis. O autor detalha recursos auxiliares importantes, como a função **range** para gerar sequências numéricas e as instruções **break** e **continue** para controlar o fluxo dos loops. Exemplos práticos, como a filtragem de vogais em textos e a criação de menus interativos, ilustram como aplicar esses conceitos no **VS Code**. Por fim, o material reforça a importância da experimentação do código para a fixação do aprendizado em programação.

### Anotações


- São estruturas utilizadas para repetir um trecho de código um determinado número de vezes. Esse número pode ser conhecido previamente ou determinado através de uma expressão lógica.

#### Exemplo sem repetição

```python
# Receba um número do teclado e exiba os 2 números seguintes

a = int(input("Informe um número inteiro: "))
print(a)

a += 1
print(a)

a += 1
print(a)
```

#### Exemplo com repetição

```python
# Receba um número do teclado e exiba os 2 números seguintes

a = int(input("Informe um número inteiro: "))
print(a)

repita 2 vezes:
    a += 1
    print(a)
```

#### Comando for

- O comando for é usado para percorrer um objeto iterável. Faz sentido usar for quando sabemos o número exato de vezes que nosso bloco de código deve ser executado, ou quando queremos percorrer um objeto iterável.

#### for

```python
texto = input("Informe um texto: ")
VOGAIS = "AEIOU"

for letra in texto:
	if letra.upper() in VOGAIS:
		print(letra, end="")

print()  # adiciona uma quebra de linha
```

#### for/else

```python
texto = input("Informe um texto: ")
VOGAIS = "AEIOU"

for letra in texto:
	if letra.upper() in VOGAIS:
		print(letra, end="")
else:
    print()  # adiciona uma quebra de linha
```

#### Função range

- Range é uma função built-in do Python, ela é usada para produzir uma sequência de números inteiros a partir de um ínicio (inclusivo) para um fim (exclusivo). Se usarmos range(i, j) será produzido: i, i+1, i+2, i+3, ..., j-1.

- Ela recebe 3 argumentos: stop (obrigatório), start (opcional) e step opcional.

#### range

```python
# range(stop) -> range object
# range(start, stop[, step]) -> range object

list(range(4))
>>> [0, 1, 2, 3]
```

#### Utilizando range com for

```python
for numero in range(0, 11):
    print(numero, end=" ")

>>> 0 1 2 3 4 5 6 7 8 9 10


# exibindo a tabuada do 5
for numero in range(0, 51, 5):
    print(numero, end=" ")

>>> 0 5 10 15 20 25 30 35 40 45 50
```

#### Comando while

- O comando while é usado para repetir um bloco de código várias vezes. Faz sentido usar while quando não sabemos o número exato de vezes que nosso bloco de código deve ser executado.

#### while

```python
opcao = -1

while opcao != 0:
    opcao = int(input("[1] Sacar \n[2] Extrato \n[0] Sair \n: "))
    
    if opcao == 1:
        print("Sacando...")
    elif opcao == 2:
        print("Exibindo o extrato...")
```

#### while/else

```python
opcao = -1

while opcao != 0:
    opcao = int(input("[1] Sacar \n[2] Extrato \n[0] Sair \n: "))
    
    if opcao == 1:
        print("Sacando...")
    elif opcao == 2:
        print("Exibindo o extrato...")
else:
	print("Obrigado por usar nosso sistema bancário, até logo!")
```

##  Materiais de Apoio

Disponibilizados os seguinte arquivos em formato MS Powerpoint:

- [[15-Dio.Indentacao.e.blocos.pptx]]
- [[16-Dio.Estruturas.condicionais.pptx]]
- [[17-Dio.Estruturas.de.repeticao.pptx]]

# Certificado: 

- Link na plataforma:  https://hermes.dio.me/certificates/XVD62HUQ.pdf
- Certificado em pdf: [[Certificado-Estruturas.Condicionais.e.de.Repeticao.em.Python.pdf]]
