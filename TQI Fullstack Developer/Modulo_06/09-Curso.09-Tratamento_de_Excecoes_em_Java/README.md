## Instrutor

- Camila Cavalcante (Software Developer @ Reclame AQUI | Full Stack | Java | Kotlin | Go | TypeScript | PHP)
- Contato Linkedin: / [cami-la](https://www.linkedin.com/in/cami-la/)

## Parte 1 - Apresentação e Visão Geral

### 🟩 Vídeo 01 - Apresentação

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.09-video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/tratamento-de-excecoes-em-java/learning/8ab022fe-7c0e-41bc-95de-daedca653d7c?autoplay=1

Este curso foca em capacitar desenvolvedores a identificar, interpretar e tratar erros em aplicações Java, garantindo que o software seja robusto e resiliente a falhas inesperadas.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-10h34m10s819.jpg" alt="" width="840">
</p>

Esta é a introdução ao curso de **Tratamento de Exceções em Java** , ministrado pela instrutora Camila Cavalcante. O objetivo inicial é apresentar os fundamentos necessários para lidar com erros e imprevistos durante a execução de programas desenvolvidos na linguagem Java, garantindo que o software possa lidar com situações anômalas de maneira controlada.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-10h34m12s619.jpg" alt="" width="840">
</p>

O curso define metas claras para o desenvolvimento do aluno: 

* **Identificação:** Capacidade de reconhecer onde e por que uma exceção pode ocorrer em uma aplicação Java.
* **Interpretação:** Entendimento de eventuais pilhas de exceção (*stack traces*) para localizar falhas.
* **Robustez:** Preparação para capturar e tratar essas exceções, tornando as soluções de software mais seguras e robustas.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-10h34m18s980.jpg" alt="" width="840">
</p>

Para um bom aproveitamento das aulas, são listados os seguintes pré-requisitos: 

1. **Java JDK 8 ou superior:** Requisito de ambiente para compilação e execução.
2. **IDE para desenvolvimento Java:** Ferramenta como IntelliJ, Eclipse ou VS Code.
3. **Conhecimento BÁSICO em OOP:** Noções fundamentais de Orientação a Objetos.
4. **Estar disposto a aprender:** Atitude necessária para absorver o conteúdo proposto.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-10h34m21s377.jpg" alt="" width="840">
</p>

O roteiro de aprendizado será dividido em quatro tópicos principais que compõem o percurso do curso: 

1. **Visão Geral:** Introdução aos conceitos fundamentais.
2. **Unchecked Exception:** Exceções que não são verificadas obrigatoriamente pelo compilador em tempo de compilação.
3. **Checked Exception:** Exceções que exigem tratamento ou declaração obrigatória.
4. **Exception Personalizada:** Desenvolvimento de exceções específicas para o contexto da aplicação.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-10h34m27s366.jpg" alt="" width="840">
</p>

A instrutora Camila Cavalcante apresenta seu perfil profissional e pessoal. Estudante de Ciência da Computação , ela relata ter iniciado sua trajetória na tecnologia através do Excel. Sua principal motivação é a vontade de aprender coisas novas. Em seu tempo livre, prefere atividades não relacionadas a computadores, como assistir séries e ler livros.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-10h34m28s961.jpg" alt="" width="840">
</p>

Para acompanhar o material prático e sanar dúvidas durante o curso, são disponibilizados diversos canais de comunicação e repositórios: 

* **GitHub:** Repositório principal onde o código-fonte da aula e um README detalhado estão localizados.
* **Redes Sociais:** Links para conexão no LinkedIn e acompanhamento de rotinas de estudo no Instagram.
* **Suporte:** Fórum da plataforma DIO, comunidade no Discord e abertura de *issues* no GitHub para dúvidas coletivas.

Links extraídos:

* [https://github.com/cami-la/exceptions-java](https://github.com/cami-la/exceptions-java) 
* [https://www.linkedin.com/in/cami-la/](https://www.linkedin.com/in/cami-la/) 
* [https://www.instagram.com/estudant.i/](https://www.instagram.com/estudant.i/) 
* [https://www.instagram.com/camimi_la](https://www.instagram.com/camimi_la)/      

### 🟩 Vídeo 02 - Visão geral

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.09-video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/tratamento-de-excecoes-em-java/learning/6fad4964-b716-4a93-910c-8fb6175a3a1d?autoplay=1

Este vídeo oferece uma introdução abrangente ao tratamento de exceções em Java, cobrindo a definição, importância, classificação e os mecanismos básicos para lidar com elas. A ênfase é dada à compreensão conceitual antes de mergulhar na implementação prática.

### Anotações

#### Visão Geral: Tratamento de Exceções em Java

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-11h23m35s301.jpg" alt="" width="840">
</p>

Esta aula apresenta uma visão geral sobre o tratamento de exceções na linguagem Java. O objetivo é compreender os conceitos fundamentais e as palavras reservadas que permitem lidar com eventos inesperados durante a execução de um programa, antes de avançar para a implementação prática em código.

#### Definição e Importância das Exceções

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-11h23m41s558.jpg" alt="" width="840">
</p>

Uma exceção é definida como um evento que interrompe o fluxo normal de processamento de uma classe. O tratamento adequado desses eventos é crucial para criar programas robustos e confiáveis, permitindo que a aplicação continue operando mesmo após enfrentar um problema. É fundamental que a estratégia de tratamento de exceções seja incorporada ao sistema desde as etapas iniciais do projeto, pois pode ser difícil implementá-la de forma eficiente após o sistema já estar finalizado.

#### Categorias: Error, Unchecked e Checked

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-11h23m44s111.jpg" alt="" width="840">
</p>

No ecossistema Java, as falhas de execução são categorizadas em três tipos principais:

* **Error:** Utilizado pela JVM para indicar problemas críticos de recursos do programa, onde a execução torna-se impossível de continuar.
* **Unchecked (Runtime):** Exceções que podem ser evitadas se forem analisadas e tratadas preventivamente pelo desenvolvedor.
* **Checked Exception:** Exceções que devem obrigatoriamente ser tratadas pelo desenvolvedor para que o programa funcione corretamente.

#### A Classe java.lang.Error na Documentação Oracle

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-11h23m59s479.jpg" alt="" width="840">
</p>

A documentação oficial define a classe `Error` como uma subclasse de `Throwable` que indica problemas sérios que uma aplicação comum não deve tentar capturar (catch). A maioria desses erros representa condições anormais. Como são condições que tecnicamente nunca deveriam ocorrer em uma execução saudável, os métodos não são obrigados a declarar esses erros em sua cláusula `throws`.

```java
java.lang.Object
   java.lang.Throwable
      java.lang.Error

```

#### A Classe java.lang.Exception na Documentação Oracle

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-11h24m04s166.jpg" alt="" width="840">
</p>

A classe `Exception` e suas subclasses são formas de `Throwable` que indicam condições que uma aplicação pode querer capturar para tratamento. Subclasses de `Exception` que não herdam de `RuntimeException` são classificadas como **Checked Exceptions** (exceções verificadas). Estas precisam ser obrigatoriamente declaradas na cláusula `throws` de um método ou construtor caso possam ser lançadas e propagadas para fora do escopo do método.

```java
java.lang.Object
   java.lang.Throwable
      java.lang.Exception

```

#### Hierarquia de Exceções no Java

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-11h24m15s009.jpg" alt="" width="840">
</p>

A hierarquia de exceções é encabeçada pela classe `Throwable`, que se divide em:

1. **Error:** Eventos irrecuperáveis e não checados pelo compilador, geralmente lançados pela JVM.
2. **Exception:** 
* **Checked Exceptions:** Condições excepcionais checadas pelo compilador, consideradas eventos recuperáveis (ex: `IOException`, `FileNotFoundException`).
* **Runtime Exceptions (Unchecked):** Exceções não checadas pelo compilador, frequentemente relacionadas a erros de lógica (ex: `NullPointerException`, `ArithmeticException`).

#### Palavras-Chave para o Tratamento

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-11h24m21s402.jpg" alt="" width="840">
</p>

Para gerenciar o comportamento do programa diante de exceções, utilizamos as seguintes palavras reservadas:

* **try, catch, finally:** Blocos utilizados em conjunto para definir o tratamento de exceções.
* **throws:** Utilizada na assinatura de um método para declarar que ele pode lançar uma ou várias exceções.
* **throw:** Comando utilizado para lançar explicitamente uma exceção durante a execução.

#### Ementa do Curso e Repositório

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-11h24m25s163.jpg" alt="" width="840">
</p>

O curso de Tratamento de Exceções em Java está estruturado em quatro módulos principais: Visão Geral, Unchecked Exception, Checked Exception e Exception Personalizada. Para acompanhar as aulas, são necessários o Java JDK 8 ou superior, uma IDE e conhecimentos básicos em Programação Orientada a Objetos (OOP). O material de apoio e os exemplos de código estão disponíveis no repositório GitHub `cami-la/exceptions-java`.      


## Parte 2 - Unchecked exception

### 🟩 Vídeo 03 - Unchecked exception

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.09-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/tratamento-de-excecoes-em-java/learning/4eb0f87b-e0e2-4cbe-96c0-ab019e26a7e0?autoplay=1

Este guia resume a aula prática sobre como lidar com erros em tempo de execução, focando em capturar falhas de entrada de dados e operações matemáticas inválidas, garantindo que o programa não "trave" inesperadamente.

### Anotações

Este documento apresenta uma aula prática focada no tratamento de exceções não verificadas (*Unchecked Exceptions*) em Java, demonstrando como lidar com erros de entrada de dados e operações matemáticas inválidas utilizando blocos `try-catch-finally` e lógica de repetição.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-13h14m02s271.jpg" alt="" width="840">
</p>

A aula inicia com a apresentação de um código base para realizar a divisão de dois números inteiros. O programa utiliza a classe `JOptionPane` para capturar as entradas do usuário (`numerador` e `denominador`) como strings, realiza a conversão para inteiros através de `Integer.parseInt` e chama um método dedicado para a operação.

```java
package br.com.dio.exceptions;

import javax.swing.*;

public class UncheckedException {
    public static void main(String[] args) {
        String a = JOptionPane.showInputDialog("Numerador: ");
        String b = JOptionPane.showInputDialog("Denominador: ");

        int resultado = dividir(Integer.parseInt(a), Integer.parseInt(b));
        System.out.println("Resultado: " + resultado);

        System.out.println("O código continua...");
    }

    public static int dividir(int a, int b) {
        return a / b;
    }
}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-13h15m09s921.jpg" alt="" width="840">
</p>

Ao executar o programa com entradas válidas (numerador 4 e denominador 2), o código funciona conforme o esperado, exibindo o resultado "2" no console. Este cenário representa o fluxo normal de execução sem a ocorrência de exceções.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-13h16m36s570.jpg" alt="" width="840">
</p>

Quando o usuário fornece uma entrada inválida, como um nome (ex: "Camila") em vez de um número, o programa interrompe a execução e lança uma `NumberFormatException`. O console exibe o *stack trace*, indicando que a causa do erro foi a tentativa de converter a string "Camila" em um valor inteiro.

```bash
Exception in thread "main" java.lang.NumberFormatException: For input string: "Camila"
	at java.base/java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
	at java.base/java.lang.Integer.parseInt(Integer.java:652)
	at java.base/java.lang.Integer.parseInt(Integer.java:770)
	at br.com.dio.exceptions.UncheckedException.main(UncheckedException.java:11)

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-13h16m42s174.jpg" alt="" width="840">
</p>

A análise do erro aponta diretamente para a linha 11 do código. É neste ponto que o método `Integer.parseInt(a)` tenta processar a string recebida do input, falhando ao encontrar caracteres não numéricos.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-13h16m56s908.jpg" alt="" width="840">
</p>

Para diagnosticar o problema, deve-se ler o *stack trace* de baixo para cima. A base da pilha mostra onde o erro foi originado no contexto do nosso projeto (`UncheckedException.main` na linha 11), subindo pelas classes internas da biblioteca padrão do Java até chegar na exceção específica que foi disparada.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-13h18m36s132.jpg" alt="" width="840">
</p>

Para evitar a interrupção abrupta do programa, é implementado o bloco `try-catch`. O código propenso a erro é colocado dentro do `try`, enquanto o `catch` captura a `NumberFormatException`. Adicionalmente, o bloco `finally` é introduzido, garantindo que seu conteúdo seja executado independentemente da ocorrência de uma exceção.

```java
        try {
            int resultado = dividir(Integer.parseInt(a), Integer.parseInt(b));
            System.out.println("Resultado: " + resultado);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Chegou no finally!");
        }

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-13h19m30s662.jpg" alt="" width="840">
</p>

Em vez de apenas imprimir a pilha de erro no console, o tratamento é aprimorado para exibir uma mensagem didática ao usuário final. Utiliza-se `JOptionPane.showMessageDialog` para informar que a entrada é inválida e solicitar um número inteiro.

```java
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida, informe um número inteiro! ");
        } finally {

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-13h19m53s916.jpg" alt="" width="840">
</p>

Ao rodar o programa com o tratamento implementado, o usuário agora vê uma caixa de diálogo amigável em vez de um erro técnico no console. A mensagem "Entrada inválida, informe um número inteiro!" orienta o usuário sobre como corrigir seu input.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-13h19m58s631.jpg" alt="" width="840">
</p>

Mesmo após o erro de entrada e a exibição do alerta, o programa não trava. O console mostra que o bloco `finally` foi executado ("Chegou no finally!") e que o fluxo principal prosseguiu ("O código continua...").

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-13h20m26s299.jpg" alt="" width="840">
</p>

Um novo problema surge quando as entradas são numéricas, mas matematicamente impossíveis, como dividir por zero. Nesse caso, uma `ArithmeticException` é lançada, revelando outra vulnerabilidade no código que ainda não foi tratada.

```bash
Exception in thread "main" java.lang.ArithmeticException: / by zero
	at br.com.dio.exceptions.UncheckedException.dividir(UncheckedException.java:21)
	at br.com.dio.exceptions.UncheckedException.main(UncheckedException.java:12)

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-13h20m32s926.jpg" alt="" width="840">
</p>

O erro de aritmética ocorre especificamente dentro do método `dividir`. Quando o valor do denominador (`b`) é zero, a operação de divisão falha, disparando a exceção que se propaga de volta para o método principal.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-13h20m45s735.jpg" alt="" width="840">
</p>

Para lidar com essa nova situação, adicionamos um segundo bloco `catch` especificamente para `ArithmeticException`. Isso permite que o programa trate diferentes tipos de erros de formas distintas, fornecendo feedback adequado para cada caso.

```java
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida, informe um número inteiro! ");
        } catch (ArithmeticException e) {
            JOptionPane.showMessageDialog(null, "Impossível dividir um número por 0! ");
        }

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-13h20m55s384.jpg" alt="" width="840">
</p>

Com o novo tratamento, ao tentar dividir por zero, o programa exibe uma mensagem específica: "Impossível dividir um número por 0!". Novamente, o programa permanece estável e continua sua execução após o fechamento do diálogo.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-13h21m15s126.jpg" alt="" width="840">
</p>

Para tornar o programa mais robusto, é adicionado um laço `do-while`. Utilizando uma variável de controle `continueLoop`, o programa continuará solicitando entradas ao usuário até que uma operação seja concluída com sucesso (sem exceções). No momento em que a divisão é bem-sucedida, a flag é alterada para `false`, encerrando o loop.

```java
        boolean continueLoop = true;
        do {
            String a = JOptionPane.showInputDialog("Numerador: ");
            String b = JOptionPane.showInputDialog("Denominador: ");

            try {
                int resultado = dividir(Integer.parseInt(a), Integer.parseInt(b));
                System.out.println("Resultado: " + resultado);
                continueLoop = false;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida, informe um número inteiro! ");
            } catch (ArithmeticException e) {
                JOptionPane.showMessageDialog(null, "Impossível dividir um número por 0! ");
            } finally {
                System.out.println("Chegou no finally!");
            }
        } while (continueLoop);

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-13h21m34s223.jpg" alt="" width="840">
</p>

A execução final demonstra o poder dessa abordagem: o usuário pode errar a entrada (digitando texto) ou tentar uma divisão inválida (por zero) múltiplas vezes. Em todas as tentativas falhas, o programa captura a exceção, mostra o alerta, executa o `finally` e reinicia o loop, só finalizando quando uma divisão válida é finalmente processada.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-13h21m37s563.jpg" alt="" width="840">
</p>

O estudo das *Unchecked Exceptions* mostra que, embora o compilador não obrigue o tratamento dessas falhas (que geralmente decorrem de erros de lógica ou inputs inadequados), tratá-las é essencial para a experiência do usuário e a estabilidade da aplicação. Na sequência, o curso abordará as *Checked Exceptions*.      


## Parte 3 - Checked exception

### 🟩 Vídeo 04 - Checked exception parte 1

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.09-video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: 03-Recursos/00-Inbox_Recursos/02-ReadItLater

## Parte 4 - Exception Personalizada

### 🟩 Vídeo 05 - Exception Personalizada 1

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.09-video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 06 - Exception Personalizada 2

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.09-video_06.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

## Parte 5 - Encerramento do Curso

### 🟩 Vídeo 07 - Final

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.09-video_07.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:


# Certificado: Tratamento de Exceções em Java

- Link na plataforma: 
- Certificado em pdf: