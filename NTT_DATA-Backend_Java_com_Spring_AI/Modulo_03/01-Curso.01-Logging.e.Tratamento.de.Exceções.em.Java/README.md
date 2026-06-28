## Instrutor

- José Luiz Abreu Cardoso Junior (Engenheiro de software sênior)
- Contato Linkedin: / [juniorjrjl](https://www.linkedin.com/in/juniorjrjl/)

## Parte 1 - Introdução

### 🟩 Vídeo 01 - Entendendo Exceptions

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.03-curso.01-video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/debugging-e-o-tratamento-de-excecoes-em-java/learning/eb6df1a0-d71a-47d5-8e6e-ffb46aa82297?autoplay=1

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-06-26-09h42m03s675.jpg" alt="" width="840">
</p>

A imagem mostra o IntelliJ IDEA com a classe `Main`, contendo um método `test()` que executa uma divisão por zero (`10/0`) dentro de um `System.out.println`. Ao rodar o programa, o console exibe um erro em tempo de execução:

```
Exception in thread "main" java.lang.ArithmeticException: / by zero
    at Main.test(Main.java:7)
    at Main.main(Main.java:3)
```

```java
public class Main {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        System.out.println(10/0);
    }
}
```

Esse é o exemplo prático de uma exceção que ocorre **sem nenhum aviso prévio do compilador**: o código compila normalmente, mas falha apenas quando é executado, ao tentar dividir um número por zero. É a primeira demonstração de uma `ArithmeticException`, usada para introduzir o conceito de exceções não verificadas (que serão detalhadas mais adiante).

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-06-28-10h28m04s964.jpg" alt="" width="840">
</p>

Aqui o professor abre o código-fonte da própria classe `ArithmeticException` (dentro do pacote `java.lang`), mostrando sua documentação interna:

```java
/**
 * Thrown when an exceptional arithmetic condition has occurred. For
 * example, an integer "divide by zero" throws an instance of this class.
 * ArithmeticException objects may be constructed by the virtual
 * machine as if suppression were disabled and/or the stack trace was not
 * writable.
 *
 * Since: 1.0
 */
public class ArithmeticException extends RuntimeException {
    @java.io.Serial
    private static final long serialVersionUID = 2256477558314496007L;

    /**
     * Constructs an ArithmeticException with no detail message.
     */
    public ArithmeticException() { super(); }
```

No console, o mesmo erro de divisão por zero permanece visível, confirmando a origem da exceção mostrada anteriormente:

```
Exception in thread "main" java.lang.ArithmeticException: / by zero
    at Main.test(Main.java:13)
    at Main.main(Main.java:8)
```

Esse trecho evidencia visualmente o que a aula está explicando: a classe `ArithmeticException` **estende `RuntimeException`** (`extends RuntimeException`). É exatamente esse detalhe — a herança a partir de `RuntimeException` — que define a exceção como **não verificada**, ou seja, uma exceção que o Java não exige que seja tratada ou declarada com `throws`, podendo aparecer e interromper a execução do programa sem qualquer aviso em tempo de compilação.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-06-28-10h41m25s284.jpg" alt="" width="840">
</p>

Nesta imagem, o professor monta um esquema visual dentro do próprio método `test()`, ilustrando a hierarquia das exceções em Java por meio de comentários e instanciações de objetos:

```java
private static void test() {
    new Throwable(); // pai de exceptions e errors

    new Exception();              new Error(); //errors

    new RuntimeException();//unchecked   new FileNotFoundException(); chekced
}
```

Esse esquema resume visualmente toda a hierarquia explicada ao longo da aula: **`Throwable`** é a classe-mãe de tudo — tanto de **exceções** (`Exception`) quanto de **erros** (`Error`). A partir de `Exception`, derivam-se dois ramos: as exceções **não verificadas** (*unchecked*), representadas por `RuntimeException` — que não exigem tratamento obrigatório do compilador — e as exceções **verificadas** (*checked*), representadas por `FileNotFoundException` — que obrigatoriamente precisam ser tratadas ou declaradas com `throws`. Essa imagem funciona como uma síntese visual de toda a árvore de exceções apresentada na aula.      

## Parte 2 - Debugging e o Tratamento de Exceções em Java

### 🟩 Vídeo 02 - Debugging e Exceções em Java

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.03-curso.01-video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/debugging-e-o-tratamento-de-excecoes-em-java/learning/7b54028a-0f26-41b5-876f-0425d3ecefe7?autoplay=1



### 🟩 Vídeo 03 - Gerenciando Exceções em Java

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.03-curso.01-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/debugging-e-o-tratamento-de-excecoes-em-java/learning/e99f4cc8-9aa6-4abe-bdec-3152797f31d7?autoplay=1

##  Materiais de Apoio

# Certificado: Logging e Tratamento de Exceções em Java

- Link na plataforma: 
- Certificado em pdf: