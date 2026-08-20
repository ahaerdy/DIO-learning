## Instrutor

- José Luiz Abreu Cardoso Junior (Engenheiro de software sênior)
- Contato Linkedin: / [juniorjrjl](https://www.linkedin.com/in/juniorjrjl/)

## Parte 1 - Estruturas de Controle em Java

### 🟩 Vídeo 01 - Estrutura Condicional If else e else if

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/itau_java_com_inteligencia_artificial-modulo.02-curso.03-video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/itau-java-com-inteligencia-artificial/course/estruturas-de-controle-em-java/learning/b209a1e1-980b-4307-98be-8bfa7aede465?autoplay=1

### Anotações

#### Estrutura básica do `if`/`else`

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-20-07h26m33s797.jpg" alt="" width="840">
</p>

```java
public class Main {

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        System.out.println("Informe seu nome:");
        var name = scanner.next();
        System.out.println("Informe sua idade:");
        var age = scanner.nextInt();

        if (age >= 18) {
            System.out.printf("%s, você tem %s anos e pode dirigir \n", name, age);
        } else {
            System.out.printf("%s, você não pode dirigit \n", name);
        }

        System.out.println("Fim da execução");
    }
}
```

O código lê o nome e a idade digitados pelo usuário com `Scanner` e usa esses valores dentro de uma estrutura `if`. A expressão `age >= 18` é avaliada como um valor booleano: se for `true`, o bloco do `if` é executado com `System.out.printf`, formatando a mensagem com os placeholders `%s` para nome e idade. No teste exibido no console, o usuário "Juca" informa 18 anos e a mensagem "Juca, você tem 18 anos e pode dirigir" é impressa, confirmando que a condição foi satisfeita.

#### Testando o caminho do `else`

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-20-07h26m58s497.jpg" alt="" width="840">
</p>

O mesmo código é executado novamente, agora com a entrada "Maria" e idade 14. Como 14 não satisfaz a condição `age >= 18`, o fluxo pula o bloco do `if` e executa o bloco do `else`, imprimindo "Maria, você não pode dirigit". Esse teste demonstra que apenas um dos dois caminhos (`if` ou `else`) é executado por vez, nunca os dois.

#### Adicionando a verificação de emancipação (`else if`)

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-20-07h27m54s412.jpg" alt="" width="840">
</p>

```java
var scanner = new Scanner(System.in);
System.out.println("Informe seu nome:");
var name = scanner.next();
System.out.println("Informe sua idade:");
var age = scanner.nextInt();
System.out.println("Você é emancipado? (s/n)");
var isEmancipated = scanner.next().equalsIgnoreCase("s");

if (age >= 18) {
    System.out.printf("%s, você tem %s anos e pode dirigir \n", name, age);
} else if (age >= 16 && isEmancipated) {
    System.out.printf("%s, apesar de você ter %s anos, você é emancipado e pode dirigir \n", name, age);
} else {
    System.out.printf("%s, você não pode dirigir \n", name);
}
```

Uma nova pergunta é adicionada ao programa: "Você é emancipado? (s/n)". A resposta é capturada com `scanner.next()` e comparada usando `equalsIgnoreCase("s")`, que retorna `true` independentemente de o usuário digitar "s" maiúsculo ou minúsculo, armazenando o resultado na variável `isEmancipated`. Uma nova condição `else if (age >= 16 && isEmancipated)` é encadeada após o `if` original, permitindo verificar um segundo cenário caso o primeiro seja falso. Na imagem, um breakpoint foi posicionado na linha do `if` e a execução em modo debug está parada aguardando a resposta sobre a emancipação, após o usuário informar nome "Juca" e idade 18.

#### Inspecionando as variáveis no debugger

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-20-07h27m58s046.jpg" alt="" width="840">
</p>

Com a execução pausada no breakpoint, o painel do debugger exibe o estado atual das variáveis: `name = "Juca"`, `age = 18` e `isEmancipated = false`. O próprio editor mostra inline o resultado da expressão avaliada naquele ponto, `age >= 18 = true`, evidenciando que a condição do primeiro `if` já é verdadeira nesse momento da execução, antes mesmo de o fluxo continuar.

#### Executando até o fim (F8)

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-20-07h28m14s689.jpg" alt="" width="840">
</p>

Ao avançar a execução até o final, o console mostra a sequência completa: idade 18, resposta "n" para a pergunta sobre emancipação, e a mensagem final "Juca, você tem 18 anos e pode dirigir". Isso confirma que, como a idade já satisfaz `age >= 18`, o programa entra no primeiro bloco `if` independentemente da resposta dada à pergunta sobre emancipação — o `else if` sequer chega a ser avaliado.

#### Novo cenário: Maria, 16 anos, emancipada

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-20-07h28m30s978.jpg" alt="" width="840">
</p>

Uma nova execução em modo debug é iniciada com um cenário diferente: nome "Maria", idade 16. O programa aguarda a resposta da pergunta "Você é emancipado? (s/n)", que está sendo respondida com "s", configurando um caso em que a idade não atinge 18, mas pode satisfazer a condição alternativa do `else if`.

#### Avaliando a condição composta (`&&`)

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-20-07h28m33s298.jpg" alt="" width="840">
</p>

O painel de variáveis confirma `name = "Maria"`, `age = 16` e `isEmancipated = true`. O editor mostra que a primeira condição `age >= 18` foi avaliada como `false`, então o fluxo passa para o `else if (age >= 16 && isEmancipated)`, onde ambas as subexpressões são `true`. Como o operador `&&` (E lógico) exige que as duas condições sejam verdadeiras simultaneamente para que o resultado seja `true`, esse bloco é o que será executado.

#### Resultado do cenário da Maria

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-20-07h28m41s510.jpg" alt="" width="840">
</p>

O console exibe o resultado da execução: "Maria, apesar de você ter 16 anos, você é emancipado e pode dirigir". Essa é a mensagem definida no bloco `else if`, confirmando que o segundo caminho da estrutura condicional foi corretamente acionado quando a primeira condição falha, mas a segunda é satisfeita.

#### Cenário do Leo: 14 anos, não emancipado

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-20-07h29m14s089.jpg" alt="" width="840">
</p>

Uma terceira execução em modo debug é iniciada, agora com nome "Leo" e idade 14. O programa aguarda a resposta à pergunta sobre emancipação, que está sendo respondida com "n", configurando um cenário em que nenhuma das condições anteriores (idade ≥ 18, ou idade ≥ 16 e emancipado) deve ser satisfeita.

#### Nenhuma condição satisfeita → cai no `else`

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-20-07h29m16s704.jpg" alt="" width="840">
</p>

O painel de variáveis mostra `name = "Leo"`, `age = 14` e `isEmancipated = false`. O editor evidencia que tanto `age >= 18` quanto `age >= 16` são avaliadas como `false`. Como nenhuma das condições do `if` e do `else if` foi atendida, a execução segue para o bloco `else`, que é sempre o caminho final quando todas as verificações anteriores falham.

#### Removendo o `else if`: simplificando a estrutura

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-20-07h29m22s681.jpg" alt="" width="840">
</p>

O console confirma o resultado do cenário anterior: "Leo, você não pode dirigir". Em seguida, o código começa a ser editado diretamente no editor para simplificar a estrutura condicional, removendo o bloco `else if` e deixando apenas `if` e `else` com uma mensagem comum para os casos em que a pessoa pode dirigir — independentemente de ter atingido a maioridade ou de ser emancipada. Por estar em meio à edição, o editor mostra temporariamente um token inesperado, típico de um código ainda incompleto durante a refatoração.

#### Unindo as condições em uma variável booleana (`canDrive`)

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-20-07h30m23s376.jpg" alt="" width="840">
</p>

```java
var canDrive = (age >= 18) || (age >= 16 && isEmancipated);
if (canDrive) {
    System.out.printf("%s, você pode dirigir \n", name);
} else {
    System.out.printf("%s, você não pode dirigir \n", name);
}
```

Após a simplificação, as duas regras de elegibilidade — ser maior de idade ou ter 16 anos ou mais e ser emancipado — são combinadas em uma única expressão booleana, atribuída à variável `canDrive`. O operador `||` (OU lógico) é usado para unir as duas possibilidades: basta que uma delas seja verdadeira para que `canDrive` seja `true`. A estrutura `if`/`else` passa então a verificar apenas essa variável, tornando o código mais direto e a idade deixa de ser necessária na mensagem impressa.

#### Execução final com a variável `canDrive`

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-20-07h30m33s752.jpg" alt="" width="840">
</p>

O programa é executado novamente com nome "Juca", idade 18 e resposta "s" para a pergunta sobre emancipação. O console exibe "Juca, você pode dirigir", confirmando que a versão refatorada com a variável `canDrive` produz o mesmo resultado da versão anterior com `if`/`else if`/`else`, porém com uma estrutura condicional mais enxuta.


### 🟩 Vídeo 02 - Estrutura Condicional switch case

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/itau_java_com_inteligencia_artificial-modulo.02-curso.03-video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/itau-java-com-inteligencia-artificial/course/estruturas-de-controle-em-java/learning/1de01157-7e03-4b2b-968f-7effb2c0333b?autoplay=1

### 🟩 Vídeo 03 - Estrutura de Repetição for

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/itau_java_com_inteligencia_artificial-modulo.02-curso.03-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/itau-java-com-inteligencia-artificial/course/estruturas-de-controle-em-java/learning/94aa45c1-ee65-40a8-9284-2008a5f36b3c?autoplay=1

### 🟩 Vídeo 04 - Estruturas de repetição while e do while

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/itau_java_com_inteligencia_artificial-modulo.02-curso.03-video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/itau-java-com-inteligencia-artificial/course/estruturas-de-controle-em-java/learning/9b547d62-9926-4e00-bce1-74e820892a16?autoplay=1

### 🟩 Vídeo 05 - Exercícios

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/itau_java_com_inteligencia_artificial-modulo.02-curso.03-video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/itau-java-com-inteligencia-artificial/course/estruturas-de-controle-em-java/learning/ea315c43-9a84-4e2c-9e64-2c7ad2950abf?autoplay=1

### 🟩 Vídeo 06 - Bônus: Configurando Git e GitHub

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/itau_java_com_inteligencia_artificial-modulo.02-curso.03-video_06.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/itau-java-com-inteligencia-artificial/course/estruturas-de-controle-em-java/learning/55c4ebb0-e605-4bce-99f2-1d0972aaf32b?autoplay=1


# Certificado: Estruturas de Controle em Java

- Link na plataforma: 
- Certificado em pdf: