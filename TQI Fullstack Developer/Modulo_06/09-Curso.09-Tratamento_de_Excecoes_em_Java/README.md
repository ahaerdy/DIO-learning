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

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/tratamento-de-excecoes-em-java/learning/b86d1b01-1427-4238-b2c2-f279346cb8f5?autoplay=1

Este guia explora o conceito de exceções verificadas (Checked Exceptions) através de um exemplo prático de leitura e escrita de arquivos. O foco principal é entender como o compilador Java obriga o desenvolvedor a lidar com riscos previsíveis e as diferentes formas de propagar esses erros.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-15h36m47s909.jpg" alt="" width="840">
</p>

Nesta introdução às **Checked Exceptions** (exceções verificadas), o código inicial é apresentado totalmente comentado. O objetivo desta classe é realizar a leitura de um arquivo de texto e imprimir seu conteúdo no console. Como o Java exige o tratamento obrigatório de certas exceções ao lidar com entrada e saída de arquivos (I/O), o estado inicial do código serve para preparar a explicação sobre como essas exceções se comportam e como o compilador as identifica.

```java
public class CheckedException {
    public static void main(String[] args) {
        String nomeDoArquivo = "romances-blake-crouch.txt";
        //imprimirArquivoNoConsole(nomeDoArquivo);

        System.out.println("Apesar da exception ou não, o programa continua...");
    }
}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-15h37m41s782.jpg" alt="" width="840">
</p>

Ao descomentar o código, a IDE (IntelliJ) imediatamente sinaliza diversos erros de compilação, representados pelas linhas onduladas vermelhas. Isso acontece porque os métodos utilizados para manipulação de arquivos lançam exceções verificadas que o desenvolvedor é obrigado a tratar ou declarar antes mesmo de tentar rodar o programa. O compilador "avisa" que o código não é seguro para execução sem o devido gerenciamento dessas possíveis falhas.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-15h38m57s286.jpg" alt="" width="840">
</p>

Para realizar a leitura, é definido o nome do arquivo "Romances de Blake Crouch.txt" e iniciado um objeto do tipo `File`. A implementação utiliza o padrão *Decorator* com a classe `BufferedReader` para criar um buffer que armazenará o conteúdo do documento, facilitando a leitura linha por linha para a posterior impressão no console.

```java
public static void imprimirArquivoNoConsole(String nomeDoArquivo) {
    File file = new File(nomeDoArquivo);

    BufferedReader br = new BufferedReader(new FileReader(file.getName()));
    String line = br.readLine();
}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-15h39m46s600.jpg" alt="" width="840">
</p>

O processo de impressão utiliza um laço `do-while` para ler cada linha contida no buffer através do método `readLine()`. Enquanto a linha lida não for nula (indicando que ainda há conteúdo), ela é escrita no console. Ao final do processo, o método `flush()` é chamado para garantir o descarregamento total dos dados do buffer e, em seguida, o fluxo de leitura é encerrado com `close()`.

```java
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    do {
        bw.write(line);
        bw.newLine();
        line = br.readLine();
    } while (line != null);
    bw.flush();
    br.close();

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-15h40m15s006.jpg" alt="" width="840">
</p>

No método `main`, além da chamada para a execução do método de impressão, existe uma instrução final que deve ser impressa independentemente do sucesso da operação. Contudo, devido às exceções verificadas não tratadas nos métodos internos, o compilador impede a execução, pois não há garantia de que o fluxo chegará a esse ponto sem interrupções críticas.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-15h40m21s359.jpg" alt="" width="840">
</p>

O motivo técnico da sinalização de erro reside na assinatura da classe `FileReader`. Como mostrado na análise visual, a IDE destaca que o construtor desta classe lança uma exceção específica quando o arquivo indicado não é localizado, exigindo uma ação preventiva do programador.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-15h40m31s124.jpg" alt="" width="840">
</p>

Ao detalhar a exceção lançada pelo `FileReader`, identifica-se a `FileNotFoundException`. Esta é uma exceção verificada clássica: o sistema operacional pode não encontrar o arquivo (por erro de digitação no nome ou diretório inexistente), e o Java obriga que essa possibilidade seja tratada no código.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-15h40m37s399.jpg" alt="" width="840">
</p>

Além do `FileReader`, outros métodos na mesma rotina também apresentam riscos. O método `readLine()`, responsável por ler o conteúdo do buffer, lança uma `IOException`. Como esta exceção é mais genérica (abrange erros de entrada e saída em geral), ela também é considerada uma *Checked Exception* que impede a compilação.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-15h40m42s099.jpg" alt="" width="840">
</p>

A operação de escrita no console através do método `write(String s)` também é um ponto de falha potencial. Assim como a leitura, a escrita lança uma `IOException`, reforçando que toda a cadeia de entrada e saída de dados precisa de uma estratégia de tratamento de erros definida.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-15h41m05s822.jpg" alt="" width="840">
</p>

Se tentarmos forçar a execução do programa sem resolver essas pendências, o compilador falha. O painel de saída demonstra que o Java não permite a execução enquanto existirem exceções verificadas não capturadas ou não declaradas na assinatura dos métodos.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-15h42m24s413.jpg" alt="" width="840">
</p>

Uma das formas de resolver o erro de compilação é "relançar" a exceção para quem chamou o método. A IDE oferece a opção "Add exception to method signature", que insere a cláusula `throws` na assinatura do método, delegando a responsabilidade do tratamento para o nível superior da pilha de execução.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-15h42m32s085.jpg" alt="" width="840">
</p>

Ao aplicar a sugestão da IDE, a assinatura do método passa a ser: `public static void imprimirArquivoNoConsole(String nomeDoArquivo) throws FileNotFoundException`. Isso resolve o erro especificamente para o `FileReader`, informando ao compilador que este método pode lançar essa exceção específica.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-15h42m56s788.jpg" alt="" width="840">
</p>

Entretanto, ao adicionar o `throws` no método `imprimirArquivoNoConsole`, o erro se desloca para o método `main`. Como o `main` é o chamador, ele agora recebe a "bomba" da exceção e também precisa decidir se a trata com um bloco `try-catch` ou se a relança novamente.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-15h44m28s742.jpg" alt="" width="840">
</p>

Como existem várias exceções do tipo `IOException` (para leitura, escrita, nova linha e fechamento de arquivo), é mais eficiente utilizar a classe pai `IOException` na assinatura do método. Isso abrange tanto a `FileNotFoundException` (que é uma subclasse) quanto os demais erros de fluxo de dados em uma única declaração genérica.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-15h44m34s154.jpg" alt="" width="840">
</p>

A assinatura do método agora está consolidada com `throws IOException`. Visualmente, percebe-se que todas as linhas vermelhas de erro dentro do método desapareceram, pois o compilador agora entende que o programador está ciente dos riscos e optou por propagar a exceção.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-15h45m28s468.jpg" alt="" width="840">
</p>

Para que o programa finalmente compile e rode, o método `main` também recebe a declaração `throws IOException`. Como o `main` é o último método na pilha de execução antes da própria JVM (Java Virtual Machine), relançar a exceção aqui significa que, se houver um erro, a execução será interrompida e o rastro da pilha (*stack trace*) será exibido.

```java
public static void main(String[] args) throws IOException {
    String nomeDoArquivo = "romances-blake-crouch.txt";
    imprimirArquivoNoConsole(nomeDoArquivo);

    System.out.println("Apesar da exception ou não, o programa continua...");
}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-15h46m18s472.jpg" alt="" width="840">
</p>

Com o nome do arquivo correto e as exceções devidamente declaradas, o programa executa com sucesso. O console exibe a lista de romances contidos no arquivo, demonstrando que o fluxo de leitura e escrita funcionou conforme o esperado em uma situação ideal.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-15h46m23s871.jpg" alt="" width="840">
</p>

Para testar o comportamento em caso de erro, o nome do arquivo é alterado propositalmente para "romanes-blake-crouch.txt" (um arquivo inexistente). Como apenas declaramos as exceções com `throws` e não as tratamos com `try-catch`, o comportamento esperado é que o programa interrompa sua execução assim que encontrar a falha.

```java
public static void main(String[] args) throws IOException {
    String nomeDoArquivo = "romanes-blake-crouch.txt";
    imprimirArquivoNoConsole(nomeDoArquivo);

    System.out.println("Apesar da exception ou não, o programa continua...");
}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-15h47m00s357.jpg" alt="" width="840">
</p>

O resultado da execução com o nome errado é uma `FileNotFoundException`. O console exibe o rastro do erro, apontando exatamente para a linha 18, onde o `FileReader` tentou abrir o arquivo e falhou. Isso confirma que o `throws` apenas repassa o problema adiante até que alguém o trate ou o programa "estoure".

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-15h47m30s562.jpg" alt="" width="840">
</p>

É importante notar que, como a exceção foi lançada e não tratada, a última linha de código ("Apesar da exception ou não, o programa continua...") nunca chegou a ser executada. O programa encerrou prematuramente devido à exceção verificada. Isso serve de gancho para o aprendizado do tratamento real com blocos `try-catch`.      


### 🟩 Vídeo 05 - Checked exception parte 2

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.09-video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/tratamento-de-excecoes-em-java/learning/40daa1d6-add6-408e-917b-b09c03aa06c9?autoplay=1

Este resumo aborda as melhores práticas para capturar e tratar erros em Java, focando na diferenciação entre exceções genéricas e específicas, além de melhorar a experiência do usuário com interfaces visuais.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-17h52m21s415.jpg" alt="" width="840">
</p>

Nesta etapa, inicia-se o tratamento da exceção diretamente no método chamador em vez de apenas lançá-la (usando `throws`). Através dos recursos da IDE, é implementado um bloco **try-catch-finally** para envolver a chamada do método `imprimirArquivoNoConsole`.

* **Try**: Tenta executar a leitura do arquivo.
* **Catch**: Captura uma `IOException` e exibe uma caixa de diálogo informativa para o usuário através do `JOptionPane`.
* **Finally**: Bloco opcional que executa um trecho de código independentemente de ter ocorrido uma exceção ou não.

```java
String nomeDoArquivo = "romancesblake-crouch.txt";

try {
    imprimirArquivoNoConsole(nomeDoArquivo);
} catch (IOException e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Revise o nome do arquivo que você deseja imprimir!");
} finally {
    System.out.println("Chegou no finally!");
}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-17h53m10s460.jpg" alt="" width="840">
</p>

Aqui, o tratamento é refinado para fornecer mais detalhes sobre o erro. O método `e.printStackTrace()` é comentado para evitar a poluição do console, e utiliza-se o método `e.getCause()` para tentar identificar a origem da exceção dentro da mensagem exibida ao usuário. O sistema demonstra que, mesmo após o erro e o fechamento da mensagem de alerta, o fluxo do programa continua, executando as instruções após o bloco de tratamento.

```java
catch (IOException e) {
    //e.printStackTrace();
    JOptionPane.showMessageDialog(null,
            "Revise o nome do arquivo que você deseja imprimir!" + e.getCause());
} finally {
    System.out.println("Chegou no finally!");
}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-17h53m14s693.jpg" alt="" width="840">
</p>

Nesta visualização, observa-se o comportamento do programa quando o nome do arquivo fornecido está correto. Como nenhuma exceção é lançada, o conteúdo do arquivo "romances-blake-crouch.txt" é devidamente processado e exibido no console da IDE, listando as obras do autor.

```text
Romances Blake Crouch
Abandon (July 7, 2009)
Famous (April 15, 2010)
Snowbound (June 22, 2010)

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-17h55m15s597.jpg" alt="" width="840">
</p>

Para tornar o código mais eficiente e específico, o tratamento é dividido em múltiplas cláusulas `catch`. Isso permite diferenciar erros de arquivo não encontrado de outros erros genéricos de entrada e saída.

* **FileNotFoundException**: Captura especificamente o erro de nome de arquivo incorreto ou inexistente.
* **IOException**: Atua como um "coringa" para capturar outros problemas que podem ocorrer durante a leitura, escrita ou fechamento do arquivo, exibindo uma mensagem de erro inesperado.

```java
try {
    imprimirArquivoNoConsole(nomeDoArquivo);
} catch (FileNotFoundException e) {
    JOptionPane.showMessageDialog(null,
            "Revise o nome do arquivo que você deseja imprimir! " + e.getCause());
} catch (IOException e) {
    JOptionPane.showMessageDialog(null,
            "Ocorreu um erro inesperado! Entre em contato com o suporte! " + e.getCause());
    e.printStackTrace();
} finally {
    System.out.println("Chegou no finally!");
}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-17h55m38s731.jpg" alt="" width="840">
</p>

A organização dos blocos `catch` segue a hierarquia de classes de exceção em Java. As exceções mais específicas (como `FileNotFoundException`) devem sempre ser declaradas antes das exceções mais genéricas (como `IOException`). Caso a ordem fosse invertida, a exceção genérica capturaria todos os erros, tornando o bloco da exceção específica inacessível, o que resultaria em um erro de compilação.

```java
// Estrutura correta: Específica antes da Genérica
catch (FileNotFoundException e) {
    // Trata erro de arquivo não encontrado
} catch (IOException e) {
    // Trata outros erros de IO
}

```      


## Parte 4 - Exception Personalizada

### 🟩 Vídeo 07 - Exception Personalizada 1

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.09-video_07.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/tratamento-de-excecoes-em-java/learning/d6f87416-4278-47c8-b50f-9efee60f2abe?autoplay=1

Este guia aborda a criação e o gerenciamento de exceções personalizadas no Java, focando em boas práticas de programação, como o desacoplamento de código e a melhoria da legibilidade do sistema de erros.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-18h23m13s534.jpg" alt="" width="840">
</p>

Nesta etapa, iniciamos o estudo sobre **exceptions customizadas** (ou personalizadas) no Java. Uma exceção nada mais é do que uma classe comum que estende as capacidades do sistema de tratamento de erros da linguagem. O exemplo prático utiliza um método chamado `imprimirArquivoNoConsole`, que foi refatorado para separar a lógica de impressão da lógica de leitura de arquivos, promovendo o desacoplamento do código.

```java
public static void imprimirArquivoNoConsole (String nomeDoArquivo) {
    try {
        BufferedReader br = lerArquivo(nomeDoArquivo);
        String line = br.readLine();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        do {
            bw.write(line);
            bw.newLine();
            line = br.readLine();
        } while (line != null);
        bw.flush();
        br.close();
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(null,
            "Ocorreu um erro não esperado, por favor, fale com o suporte." + ex.getMessage());
    }
}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-18h24m00s626.jpg" alt="" width="840">
</p>

O método `main` solicita ao usuário o nome do arquivo a ser exibido através de um `JOptionPane`. Após a execução da lógica de impressão, o programa exibe uma mensagem no console confirmando que, independentemente da ocorrência de uma exceção, o fluxo de execução continua.

```java
public static void main(String[] args) {
    String nomeDoArquivo = JOptionPane.showInputDialog("Nome do arquivo a ser exibido: ");
    imprimirArquivoNoConsole(nomeDoArquivo);
    System.out.println("\nCom exception ou não, o programa continua...");
}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-18h24m45s090.jpg" alt="" width="840">
</p>

Ao utilizar a classe `FileReader`, o Java identifica uma **checked exception** do tipo `FileNotFoundException`. O ambiente de desenvolvimento (IDE) sinaliza que essa exceção precisa ser obrigatoriamente tratada ou lançada (utilizando a cláusula `throws`). Neste ponto, preparamos o terreno para substituir o tratamento genérico por uma exceção personalizada.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-18h25m16s045.jpg" alt="" width="840">
</p>

Para criar uma exceção customizada, definimos uma nova classe. No exemplo, a classe é criada dentro do mesmo arquivo para facilitar a visualização, logo abaixo da classe principal. É uma boa prática de programação incluir o sufixo `Exception` no nome da classe. Para que ela funcione como uma exceção legítima no Java, ela deve obrigatoriamente herdar de `Exception`.

```java
class ImpossivelAberturaDeArquivoException extends Exception {
    private String nomeDoArquivo;
    private String diretorio;
}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-18h25m18s300.jpg" alt="" width="840">
</p>

Como a exceção customizada é uma classe Java, podemos definir atributos específicos para enriquecer o erro. Aqui, foram adicionados os campos `nomeDoArquivo` e `diretorio`. Através do atalho `Alt+Insert`, define-se um construtor que utiliza o método `super` para passar uma mensagem personalizada à classe pai (`Exception`), detalhando em qual diretório o arquivo não foi encontrado.

```java
public ImpossivelAberturaDeArquivoException(String nomeDoArquivo, String diretorio) {
    super("O arquivo " + nomeDoArquivo + " não foi encontrado no diretório " + diretorio);
    this.nomeDoArquivo = nomeDoArquivo;
    this.diretorio = diretorio;
}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-18h26m34s791.jpg" alt="" width="840">
</p>

Além do construtor, é possível sobrescrever o método `toString()` da classe. Isso permite definir exatamente como a exceção será representada textualmente quando for impressa ou exibida em logs, exibindo os valores dos atributos customizados (`nomeDoArquivo` e `diretorio`).

```java
@Override
public String toString() {
    return "ImpossivelAberturaDeArquivoException{" +
            "nomeDoArquivo='" + nomeDoArquivo + '\'' +
            ", diretorio='" + diretorio + '\'' +
            '}';
}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-18h26m52s199.jpg" alt="" width="840">
</p>

No método `lerArquivo`, quando a `FileNotFoundException` é capturada, o programa agora lança a nossa exceção customizada utilizando a palavra-chave `throw`. Para isso, instanciamos a `ImpossivelAberturaDeArquivoException` passando o nome e o caminho do arquivo obtidos através da classe `File`.

```java
public static BufferedReader lerArquivo (String nomeDoArquivo) {
    File file = new File(nomeDoArquivo);
    try {
        return new BufferedReader(new FileReader(nomeDoArquivo));
    } catch (FileNotFoundException e) {
        throw new ImpossivelAberturaDeArquivoException(file.getName(), file.getPath());
    }
}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-18h31m01s359.jpg" alt="" width="840">
</p>

Após lançar a nova exceção, a assinatura do método `lerArquivo` precisa ser atualizada com `throws ImpossivelAberturaDeArquivoException`. Isso obriga o método chamador (`imprimirArquivoNoConsole`) a tratar essa nova exceção específica. Na estrutura de `catch`, é crucial posicionar a exceção mais específica antes da mais genérica (`IOException`) para garantir que o erro seja capturado corretamente.

```java
public static BufferedReader lerArquivo (String nomeDoArquivo) throws ImpossivelAberturaDeArquivoException {
    File file = new File(nomeDoArquivo);
    try {
        return new BufferedReader(new FileReader(nomeDoArquivo));
    } catch (FileNotFoundException e) {
        throw new ImpossivelAberturaDeArquivoException(file.getName(), file.getPath());
    }
}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-18h31m12s554.jpg" alt="" width="840">
</p>

Ao executar o programa e inserir um nome de arquivo inexistente (como "camila.txt"), a exceção customizada é disparada. O console exibe a mensagem formatada no construtor da nossa classe, indicando exatamente o que falhou e onde. O rastreamento da pilha (stack trace) permite identificar que a exceção foi lançada no método `lerArquivo` e capturada no bloco `try-catch` do método `imprimirArquivoNoConsole`.

> [!IMPORTANT]
> A mensagem exibida via `getMessage()` será exatamente aquela definida no `super` do construtor da exceção personalizada: "O arquivo [nome] não foi encontrado no diretório [caminho]".


### 🟩 Vídeo 08 - Exception Personalizada 2

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.09-video_08.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/tratamento-de-excecoes-em-java/learning/1c35fa8e-885a-42b5-9fa6-b7140072c0dd?autoplay=1

Este resumo aborda a criação de exceções personalizadas para atender regras de negócio específicas, o tratamento de erros aritméticos e de arrays, e boas práticas de interface e fluxo de código.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-19h01m14s013.jpg" alt="" width="840">
</p>

Nesta etapa, iniciamos a estrutura de um novo exemplo para explorar exceções customizadas. O objetivo é realizar a divisão entre elementos de dois arrays: um contendo numeradores e outro contendo denominadores. O código percorre os arrays utilizando um laço `for` , mas a estrutura inicial é vulnerável a erros como divisões por zero ou índices fora do limite, já que os arrays possuem tamanhos diferentes.

```java
public class ExceptionCustomizada_2 {
    public static void main(String[] args) {
        [cite_start]int[] numerador = {4, 5, 8, 10}; [cite: 16]
        [cite_start]int[] denominador = {2, 4, 0, 2, 8}; [cite: 17]

        [cite_start]for (int i = 0; i < denominador.length; i++) { [cite: 18]
            [cite_start]int resultado = numerador[i] / denominador[i]; [cite: 19]
            [cite_start]System.out.println(resultado); [cite: 19]
        }

        [cite_start]System.out.println("O programa continua..."); [cite: 26]
    }
}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-19h01m33s062.jpg" alt="" width="840">
</p>

Para lidar com regras de negócio específicas, criamos a classe `DivisaoNaoExataException`. Ela estende a classe `Exception`, o que a caracteriza como uma **checked exception** (exceção verificada). A classe armazena o numerador e o denominador que causaram o erro , permitindo um rastreamento detalhado do problema.

```java
[cite_start]public class DivisaoNaoExataException extends Exception { [cite: 57]
    [cite_start]private int numerado; [cite: 58]
    [cite_start]private int denominador; [cite: 59]

    [cite_start]public DivisaoNaoExataException(String message, int numerado, int denominador) { [cite: 63]
        [cite_start]super(message); [cite: 67]
        [cite_start]this.numerado = numerado; [cite: 68]
        [cite_start]this.denominador = denominador; [cite: 69]
    }
}

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-19h01m36s339.jpg" alt="" width="840">
</p>

Retornamos à classe principal para preparar a implementação da lógica de lançamento da nossa exceção customizada. O foco aqui é identificar situações onde a divisão não resultaria em um número inteiro exato antes mesmo da operação ser concluída.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-19h01m55s968.jpg" alt="" width="840">
</p>

Implementamos o bloco `try-catch` dentro do laço de repetição. Antes de realizar a divisão, verificamos se o numerador é ímpar em relação à lógica proposta. Caso a condição seja verdadeira, a exceção `DivisaoNaoExataException` é lançada manualmente com a palavra-chave `throw`. No bloco `catch`, capturamos essa exceção específica para exibir o rastreamento do erro e uma mensagem via `JOptionPane`.

```java
[cite_start]for (int i = 0; i < denominador.length; i++) { [cite: 141]
    [cite_start]try { [cite: 142]
        [cite_start]if (numerador[i] % 2 != 0) [cite: 143]
            [cite_start]throw new DivisaoNaoExataException("Divisão não exata!", numerador[i], denominador[i]); [cite: 144]

        [cite_start]int resultado = numerador[i] / denominador[i]; [cite: 145]
        [cite_start]System.out.println(resultado); [cite: 146]
    [cite_start]} catch (DivisaoNaoExataException e) { [cite: 147]
        [cite_start]e.printStackTrace(); [cite: 168]
        [cite_start]JOptionPane.showMessageDialog(null, e.getMessage()); [cite: 170]
    }
}
[cite_start]System.out.println("O programa continua..."); [cite: 171]

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-19h02m05s735.jpg" alt="" width="840">
</p>

Ao executar o programa, a primeira exceção é disparada quando o laço atinge o numerador `5`. Como ele não atende aos critérios de divisão exata definidos, o programa interrompe o fluxo normal do `try`, entra no `catch` e exibe a caixa de diálogo com a mensagem personalizada: "Divisão não exata!".

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-03-19h02m18s164.jpg" alt="" width="840">
</p>

A execução final demonstra a coexistência de diferentes exceções. Além da nossa `DivisaoNaoExataException` , o console exibe um erro de `java.lang.ArithmeticException: / by zero`. Isso ocorre porque não tratamos especificamente a divisão por zero no bloco `catch` , evidenciando que exceções customizadas funcionam em conjunto com as exceções nativas da linguagem Java.


## Parte 5 - Encerramento do Curso

### 🟩 Vídeo 09 - Final

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.09-video_09.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/tratamento-de-excecoes-em-java/learning/17fae692-a878-478b-93b2-ac6f7bd185a7?autoplay=1


# Certificado: Tratamento de Exceções em Java

- Link na plataforma: 
- Certificado em pdf: