# Parte 1 - Estruturas condicionais e de repetição

## Vídeo 01 - Indentação e blocos

### Aprender como o interpretador Python utiliza a indentação do código para delimitar os blocos de comandos

- Identar código é uma forma de manter o código fonte mais legível e manutenível. Mas em Python ela exerce um segundo papel, através da indentação o interpretador consegue determinar onde um bloco de comando inicia e onde ele termina.

### Bloco de comando

- As linguagens de programação costumam utilizar caracteres ou palavras reservadas para terminar o início e fim do bloco. Em Java e C por exemplo, utilizamos chaves:


#### Bloco em Java

```
void sacar(double valor) {  // início do bloco do método

    if (this.saldo >= valor) { // início do bloco do if
    
        this.saldo -= valor;

    } // fim do bloco do if

}  // fim do bloco do método
```

#### Bloco em Java sem formatar

```
void sacar(double valor) {  // início do bloco do método
if (this.saldo >= valor) {  // início do bloco do if
this.saldo -= valor;
}  // fim do bloco do if
}  // fim do bloco do método
```

### Utilizando espaços

- Existe uma convenção em Python, que define as boas práticas para escrita de código na linguagem. Nesse documento é indicado utilizar 4 espaços em branco por nível de indentação, ou seja, a cada novo bloco adicionamos 4 novos espaços em branco.

#### Bloco em Python

```
def sacar(self, valor: float) -> None:  # início do bloco do método

    if self.saldo >= valor:  # início do bloco do if
    
        self.saldo -= valor
    
    # fim do bloco do if

# fim do bloco do método
```

#### ⚠️ Isso não funciona em Python!

```
def sacar(self, valor: float) -> None:  # início do bloco do método
if self.saldo >= valor:  # início do bloco do if    
self.saldo -= valor
# fim do bloco do if
# fim do bloco do método
```

### Qual versão é mais fácil de ler?

```
void sacar(double valor) {
if (this.saldo >= valor) {
this.saldo -= valor;}}


def sacar(self, valor: float) -> None:
    if self.saldo >= valor:
        self.saldo -= valor
```

### 🧪 Exemplo utilizado na aula

```
def sacar(valor):  # início do bloco do método

    saldo=500

    if saldo >= valor:  # início do bloco do if
        print ("valor sacado, retire seu dinheiro na boca do caixa.")
    
    # fim do bloco do if
    print("Obrigado por ser nosso cliente, tenha um bom dia!")

# fim do bloco do método

sacar(100)
```

### 📤 Saída:

```
Valor sacado, retire seu dinheiro na boca do caixa.
Obrigado por ser nosso cliente, tenha um bom dia!
```

## Vídeo 02 - Estruturas condicionais

### O que são as estruturas condicionais e como utilizá-las.

- A estrutura condicional permite o desvio de fluxo de controle, quando determinadas expressões lógicas são atendidas.

### If

- Para criar uma estrutura condicional simples, composta por um único desvio, podemos utilizar a palavra reservada if. O comando irá testar a expressão lógica, e em caso de retorno verdadeiro as ações presentes no bloco de código do if serão executadas.

### Exemplo

```
saldo = 2000.0
saque = float(input("Informe o valor do saque: "))

if saldo >= saque:
    print("Realizando saque!")

if saldo <= saque:
	print("Saldo insuficiente!")
```

### If/else

- Para criar uma estrutura condicional com dois desvios, podemos utilizar as palavras reservadas if e else. Como sabemos se a expressão lógica testada no if for verdadeira, então o bloco de código do if será executado. Caso contrário o bloco de código do else será executado.

### Exemplo

```
saldo = 2000.0
saque = float(input("Informe o valor do saque: "))

if saldo >= saque:
    print("Realizando saque!")
else:
	print("Saldo insuficiente!")
```

### If/elif/else

- Em alguns cenários queremos mais de dois desvios, para isso podemos utilizar a palavra reservada elif. O elif é composto por uma nova expressão lógica, que será testada e caso retorne verdadeiro o bloco de código do elif será executado. Não existe um número máximo de elifs que podemos utilizar, porém evite criar grandes estruturas condicionais, pois elas aumentam a complexidade do código. 


### Exemplo

```
opcao = int(input("Informe uma opção: [1] Sacar \n[2] Extrato: "))

if opcao == 1:
	valor = float(input("Informe a quantia para o saque: "))
	# ...
elif opcao == 2:
	print("Exibindo o extrato...")
else:
	sys.exit("Opção inválida")
```

### If aninhado

- Podemos criar estruturas condicionais aninhadas, para isso basta adicionar estruturas if/elif/else dentro do bloco de código de estruturas if/elif/else.

### Exemplo

```
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

### If ternário

- O if ternário permite escrever uma condição em uma única linha. Ele é composto por três partes, a primeira parte é o retorno caso a expressão retorne verdadeiro, a segunda parte é a expressão lógica e a terceira parte é o retorno caso a expressão não seja atendida.

### Exemplo

```
status = "Sucesso" if saldo >= saque else "Falha"

print(f"{status} ao realizar o saque!")
```

### Exemplo FINAL (utilizado em aula)

```
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


## Vídeo 03 - Estruturas de Repetição

# Parte 2 - Materiais de Apoio e Questionário



# Certificado: Estruturas condicionais e de repetição

Link do certificado: 
