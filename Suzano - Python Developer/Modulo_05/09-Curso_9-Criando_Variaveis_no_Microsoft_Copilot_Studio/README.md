- Instrutor: Renato Romão de Souza (Microsoft MVP, MCT, Especialista em Copilot & IA).
- Contato Linkedin: https://www.linkedin.com/in/renatoromao

# Parte 01 - Como definir uma variável
 
## 🟩 Vídeo 01 - Como Definir uma Variável

### Conteúdo Programático

- Como definir uma variável?  
- Tipos de variáveis existentes  
- Trabalhando com fórmulas e variáveis

### Dentro do Copilot, ir em Tópicos

- Lembrando que as variáveis sempre vão estar dentro dos tópicos.

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image.png" alt="" width="840">
</p>
</details>

### Selecionando um Tópico existente (p.ex, "Dicas de Viagem")

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-2.png" alt="" width="840">
</p>
</details>

- Uma pergunta, alguma informação que precisamos extrair do nosso usuário, acaba se tornando uma variável.
- O Copilot extrai uma informação do usuário e envia de volta para o chatbot de forma que, se o desenvolvedor quiser usar aquela variável, ele pode. Caso contrário é uma informação descartável.

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-3.png" alt="" width="840">
</p>
</details>

### Se cliacar em "Save respose as" ...

- O Copilot mostra automaticamente o nome, seu tipo, onde estão suas referências e algumas outras informações de uso.
- Eu consigo fazer uma série de configurações dentro de uma variável, que é simplesmente uma pergunta.

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-4.png" alt="" width="840">
</p>
</details>

- A primeira boa prática é dar um nome adequado à variável.
    - Ex: locLinklugarinspiracao ("loc" de local)

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-5.png" alt="" width="840">
</p>
</details>

## 🟩 Vídeo 02 - Entendendo o Uso de Variável de Tópico ou Global
 
- Toda variável tem um tipo que, por padrão, é string.

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-6.png" alt="" width="840">
</p>
</details>

- Eu consigo ver onde a variável está sendo utilizada (todas as referências).
- Também consigo definir se são variáveis de Tópico ou Globais.
    - É o motivo de ter colocado "loc" (de local) seguido do nome da variável.
- Por padrão ele vai sempre se limitar ao escopo de Tópico

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-7.png" alt="" width="840">
</p>
</details>

- Quando definimos como variável global, qualquer outro tópico dentro DESSE Copilot vai me permitir usar as mesmas variáveis (bem como sobreescrever seus varlores).

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-8.png" alt="" width="840">
</p>
</details>

## 🟩 Vídeo 03 - Testando o Uso de Variáveis

### Testando com pergunta

    - Conselhos para viajar?

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-9.png" alt="" width="840">
</p>
</details>

- O Copilot pergunta:
    - Qual o link para a sua inspiração
    -Copia-se o próprio link desta página

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-11.png" alt="" width="840">
</p>
</details>

- A variável por padrão foi definida.

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-12.png" alt="" width="840">
</p>
</details>

- Pode-se visualizar em "variables" também.

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-13.png" alt="" width="840">
</p>
</details>

## 🟩 Vídeo 04 - Gerenciando Variáveis
 
- Dentro de "Variable Management" tenho as opções de definir uma variável, fazer o parse de um valor e limpar todas as variáveis.

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-14.png" alt="" width="840">
</p>
</details>

## 🟩 Vídeo 05 - Trabalhando com Cartões Adaptáveis

### Outra opção é trabalhar com cartões adaptáveis

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-15.png" alt="" width="840">
</p>
</details>

### Propriedades dos Cartões Adaptáveis em formato JSON

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-16.png" alt="" width="840">
</p>
</details>


# Parte 02 - Tipos de variáveis existentes
 
## 🟩 Vídeo 06 - Variáveis de Tópico e Variáveis Globais
 
- No exemplo em questão é usada uma variável Global e local (de Tõpico).
- É possĩvel identificar pela sua formatação.

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-17.png" alt="" width="840">
</p>
</details>

## 🟩 Vídeo 07 - Variáveis de Sistema
 
- Há uma série de variáveis de sistema disponíveis para utilização.

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-18.png" alt="" width="840">
</p>
</details>

- Dentro da documentação da Microsoft as variáveis estão disciminadas.

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-19.png" alt="" width="840">
</p>
</details>

- AS principais são as variáveis relacionadas ao sistema.

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-21.png" alt="" width="840">
</p>
</details>

## 🟩 Vídeo 08 - Variáveis de Ambiente

- Além disso, nós temos também as variáveis de ambiente.

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-22.png" alt="" width="840">
</p>
</details>

- Adicionando uma variável (ex: site do Sharepoint).

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-24.png" alt="" width="840">
</p>
</details>

- Utilizando a variável.

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-25.png" alt="" width="840">
</p>
</details>

# Parte 03 - Trabalhando com fórmulas e variáveis
 
## 🟩 Vídeo 09 - Exemplos de Casos Reais
 
### Exemplos de casos reais

- Comparar duas datas para férias  
- Customizar o nome do usuário  
- Verificar se uma data está dentro do período  
- Calcular preço com imposto  
- Verificar se tem um produto no estoque  
- Calcular a média de preço  

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-26.png" alt="" width="840">
</tp>
</details>
 
## 🟩 Vídeo 10 - Criando Fórmula para Comparar Datas
 
### criando variáveis e testando.

- VarDataInicio e VarDataTermino

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-27.png" alt="" width="840">
</p>
</details>

### Utilizando o MIcrosoft PowerFX

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-28.png" alt="" width="840">
</p>
</details>

- Lembrando que a MicroSoft sempre tem documentação

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-29.png" alt="" width="840">
</p>
</details>

## 🟩 Vídeo 11 - Ajustando a Fórmula para Validação Condicional
 
## 🟩 Vídeo 12 - Criando Fórmula para Calcular Preço
 
## 🟩 Vídeo 13 - Criando Fórmula para Salvar o Histórico da Conversa