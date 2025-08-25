- Instrutor: Renato Romão de Souza (Microsoft MVP, MCT, Especialista em Copilot & IA).
- Contato Linkedin: https://www.linkedin.com/in/renatoromao

## 🟩 Vídeo 01 - Conteúdo Programático

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image.png" alt="" width="840">
</p>
</details>

## 🟩 Vídeo 02 - O que são Entidades

### Entidades

- Uma parte significativa das conversas do copiloto no Copilot Studio é o reconhecimento de linguagem natural, que é a capacidade da IA de compreender a intenção do usuário.

- Uma **entidade** pode ser considerada uma **unidade de informação** que representa um **certo tipo de assunto**.

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-2.png" alt="" width="840">
</p>
</details>

### Preenchimento de slot

- O preenchimento de slot é um conceito de reconhecimento de linguagem natural que significa salvar uma entidade extraída para um objeto.

- No entanto, no Copilot Studio, o preenchimento do slot significa colocar o valor da entidade extraída em uma variável.

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-3.png" alt="" width="840">
</p>
</details>

### Entidades

- “Quero solicitar 50 camisetas do Brasil para os meus funcionários.”

- Entidades:
  - Quantidade – 50  
  - País – Brasil

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-4.png" alt="" width="840">
</p>
</details>

### Tipos de entidades

- Entidades predefinidas:
  - Idade
  - Verdadeiro e Falso
  - etc...

- Entidades personalizadas:
  - Closed list
  - Regular expression (Regex)

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-5.png" alt="" width="840">
</p>
</details>

### Exemplos de Entidades Pré-definidas

- Age  
  - Descrição: Idade de uma pessoa, lugar ou coisa...  
  - Método: Prebuilt

- Boolean  
  - Descrição: Respostas positivas ou negativas, e...  
  - Método: Prebuilt

- City  
  - Descrição: Nomes de cidades, extraídos como string  
  - Método: Prebuilt

- Color  
  - Descrição: Cores primárias e tonalidades na c...  
  - Método: Prebuilt

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-6.png" alt="" width="840">
</p>
</details>

### Uso de Expressões Regulares

#### Expressões regulares do .NET – Microsoft Learn
- Link: https://learn.microsoft.com/pt-br/dotnet/standard/base-types/regular-expressions
    - A página Expressões regulares do .NET – Microsoft Learn oferece uma visão abrangente sobre o uso de expressões regulares na plataforma .NET. Aqui estão os principais conteúdos abordados:

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-7.png" alt="" width="840">
</p>
</details>

### Onde as Entidades ficam?

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-8.png" alt="" width="840">
</p>
</details>

### Resumo

- As entidades podem facilitar as entradas do usuário sem a necessidade de usar a parte de Resposta Generativa ou AI Builder dentro do Copilot Studio para reconhecer informações já mapeadas.

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-9.png" alt="" width="840">
</p>
</details>

## 🟩 Vídeo 03 - Como usar uma entidade existente

### Dentro do Copilot Studio ir em "Settings" (Configurações)

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-10.png" alt="" width="840">
</p>
</details>

### Depois, ir em "Entidades"

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-11.png" alt="" width="840">
</p>
</details>

- Aqui você vai encontrar uma série de Entidades já definidas.

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-12.png" alt="" width="840">
</p>
</details>

- Selecionando "Cidades" a título de exemplo.

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-13.png" alt="" width="840">
</p>
</details>

### Retornando ao nosso Tópico "Dicas de Viagem"

- Verifique que ele ja utiliza algumas dessas entidades, como Cidade (City), por exemplo, onde perguntamos sobre o destino.

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-14.png" alt="" width="840">
</p>
</details>

## 🟩 Vídeo 04 - Diferença entre entidade e tipos de perguntas

- Quando tivermos modelos de informações que são padronizadas eu sempre vou usar as Entidades.
- Caso contrário, ao invés de usar uma pergunta simples, nós temos a opção de usar "Adaptive Cards", que podem facilitar a forma de obter as informações.
- Em alguns casos os tipos de pergunta podem facilitar e melhorar a experiência do nosso usuário.
- As Entidades servem parar capturar e ignorar parte das entradas do nosso usuário.

<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-15.png" alt="" width="840">
</p>
</details>

## 🟩 Vídeo 05 - O que são variáveis

### Variáveis

- As variáveis servem para salvar respostas do usuário e reutilizar seu conteúdo posteriormente na conversa.

- Também pode usar variáveis para criar expressões lógicas que direcionam dinamicamente o usuário por diferentes caminhos de conversa.


<details>
<summary> Slide da aula🔻</summary>
<p align="center">
    <img src="images/image-16.png" alt="" width="840">
</p>
</details>
