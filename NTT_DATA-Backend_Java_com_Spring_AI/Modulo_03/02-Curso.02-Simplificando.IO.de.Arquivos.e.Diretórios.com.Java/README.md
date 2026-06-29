## Instrutor

- José Luiz Abreu Cardoso Junior (Engenheiro de software sênior)
- Contato Linkedin: / [juniorjrjl](https://www.linkedin.com/in/juniorjrjl/)

## Parte 1 - Trabalhando com java.io

### 🟩 Vídeo 01 - Começando com java.io

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.03-curso.02-video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/simplificando-io-de-arquivos-e-diretorios-com-java/learning/566a8795-7456-4b30-9142-8d71ec4c1fad?autoplay=1

### Anotações

Abaixo a definição da interface `FilePersistence`, criada no pacote `br.com.dio.persistence`. Ela estabelece o contrato que qualquer implementação de persistência de arquivos deverá seguir, com cinco operações: `write` (escreve um dado no arquivo), `remove` (remove um conteúdo a partir de uma sentença, retornando um booleano), `replace` (substitui um conteúdo antigo por um novo), `findAll` (retorna todo o conteúdo do arquivo) e `findBy` (busca um conteúdo específico a partir de uma sentença). A ideia é que diferentes formas de manipular arquivos no Java possam implementar essa mesma interface, mantendo um padrão único de uso.

```java
package br.com.dio.persistence;

public interface FilePersistence {

    String write(final String data);

    boolean remove(final String sentence);

    String replace(final String oldContent, final String newContent);

    String findAll();

    String findBy(final String sentence);

}
```

O próximo código implementa a classe `IOFilePersistence`, que implementa a interface `FilePersistence` utilizando as classes de I/O tradicionais do Java (`File`, `FileOutputStream`, `IOException`, `OutputStream`). No construtor, a classe recebe o nome do arquivo, monta o caminho a partir do diretório atual do projeto (`System.getProperty("user.dir")`) combinado com o diretório de armazenamento `/managedFiles/IO/`, e verifica se esse diretório já existe. Caso não exista e não seja possível criá-lo, uma exceção é lançada e convertida em `RuntimeException`. Ao final do construtor, o método `clearFile()` é chamado para garantir que o arquivo comece "limpo". Os métodos da interface (`write`, `remove`, `replace`, `findAll`, `findBy`) ainda estão como esqueleto, retornando valores vazios ou `false`, pois serão implementados nas etapas seguintes. O método privado `clearFile()` é responsável por abrir um `FileOutputStream` no caminho do arquivo, o que tem o efeito de truncar (limpar) seu conteúdo, exibindo uma mensagem informando que os recursos estão sendo inicializados. Já o método `createFile()` aparece apenas como um esqueleto vazio, ainda sem uso definido nesse momento.

```java
package br.com.dio.persistence;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class IOFilePersistence implements FilePersistence {

    private final String currentDir = System.getProperty("user.dir");

    private final String storedDir = "/managedFiles/IO/";

    private final String fileName;

    public IOFilePersistence(String fileName) throws IOException {
        this.fileName = fileName;
        var file = new File(currentDir + storedDir);
        if (!file.exists() && !file.mkdirs()) try {
            throw new IOException("Erro ao criar arquivo");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        clearFile();
    }

    @Override
    public String write(String data) {
        return "";
    }

    @Override
    public boolean remove(String sentence) {
        return false;
    }

    @Override
    public String replace(String oldContent, String newContent) {
        return "";
    }

    @Override
    public String findAll() {
        return "";
    }

    @Override
    public String findBy(String sentence) {
        return "";
    }

    private void clearFile(){
        try(OutputStream outputStream = new FileOutputStream(currentDir + storedDir + fileName)) {
            System.out.printf("inicializando recursos (%s) \n", currentDir + storedDir + fileName);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private void createFile(){
    }
}
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-06-29-15h09m57s447.jpg" alt="" width="840">
</p>

Nesta imagem é mostrado o primeiro teste da classe `IOFilePersistence`, feito a partir de uma classe `Main` criada no projeto. Nela, é instanciada a `FilePersistence` passando `"user.csv"` como nome do arquivo. O painel de execução, na parte inferior, confirma que o construtor funcionou corretamente: aparece a mensagem "inicializando recursos" seguida do caminho completo do arquivo dentro de `managedFiles/IO/user.csv`, e o processo termina com exit code 0. Isso comprova que o diretório foi criado (quando necessário) e que o arquivo foi limpo/inicializado com sucesso pela lógica implementada no construtor e no método `clearFile()`.


O código a seguir mostra a evolução da classe `IOFilePersistence`, já com o método `write` implementado. Para isso, o import passa a ser `java.io.*`, simplificando as importações. Dentro do `write`, é utilizado um bloco `try` com *try-with-resources*, declarando três recursos: um `FileWriter` (apontando para o caminho do arquivo, com o parâmetro `true` indicando que a escrita deve ser feita em modo *append*, ou seja, adicionando conteúdo ao final do arquivo sem sobrescrever o que já existe), um `BufferedWriter` (que envolve o `FileWriter` para otimizar a escrita) e um `PrintWriter` (que envolve o `BufferedWriter` e é, de fato, quem escreve a informação no arquivo através do método `println`). Esse encadeamento de classes é uma forma clássica do Java para compor camadas de escrita em arquivo, cada uma agregando uma funcionalidade: o `FileWriter` lida com o arquivo em si, o `BufferedWriter` otimiza a performance com buffer, e o `PrintWriter` oferece métodos convenientes como `println`. Ao final, o método captura uma possível `IOException` com `printStackTrace()` e retorna o próprio `data` recebido como parâmetro, indicando que o conteúdo foi processado.

```java
package br.com.dio.persistence;

import java.io.*;

public class IOFilePersistence implements FilePersistence {

    private final String currentDir = System.getProperty("user.dir");
    private final String storedDir = "/managedFiles/IO/";
    private final String fileName;

    public IOFilePersistence(String fileName) throws IOException {
        this.fileName = fileName;
        var file = new File(currentDir + storedDir);
        if (!file.exists() && !file.mkdirs()) try {
            throw new IOException("Erro ao criar arquivo");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        clearFile();
    }

    @Override
    public String write(String data) {
        try(
            var fileWriter = new FileWriter(currentDir + storedDir + fileName, true);
            var bufferedWrite = new BufferedWriter(fileWriter);
            var printWriter = new PrintWriter(bufferedWrite)
        ) {
            printWriter.println(data);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    @Override
    public boolean remove(String sentence) {
        return false;
    }

    @Override
    public String replace(String oldContent, String newContent) {
        return "";
    }

    @Override
    public String findAll() {
        return "";
    }

    @Override
    public String findBy(String sentence) {
        return "";
    }

    private void clearFile(){
        try(OutputStream outputStream = new FileOutputStream(currentDir + storedDir + fileName)) {
            System.out.printf("inicializando recursos (%s) \n", currentDir + storedDir + fileName);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private void createFile(){

    }
}
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-06-29-15h25m09s391.jpg" alt="" width="840">
</p>

Aqui é exibida a classe `Main` já chamando o método `write` recém-implementado. A instância de `FilePersistence` é criada apontando para o arquivo `"user.csv"` e, em seguida, é feita a chamada `persistence.write(data: "Lucas;lucas@lucas.com;15/01/1990")`, simulando a escrita de um registro em formato CSV (nome, e-mail e data de nascimento separados por ponto e vírgula). O resultado é impresso com `System.out.println`. No painel de execução, abaixo, aparece novamente a mensagem de inicialização dos recursos do arquivo `user.csv`, e o processo finaliza com exit code 0, confirmando que a escrita ocorreu sem erros.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-06-29-15h25m58s090.jpg" alt="" width="840">
</p>

Esta imagem mostra o conteúdo final do arquivo `user.csv`, aberto diretamente no editor. Nele aparece a linha `Lucas;Lucas@lucas.com;15/01/1990`, comprovando visualmente que o método `write` da classe `IOFilePersistence` gravou corretamente o registro no arquivo, no formato CSV esperado (campos separados por ponto e vírgula). Essa é a confirmação prática de que toda a cadeia de escrita — `FileWriter`, `BufferedWriter` e `PrintWriter` dentro do *try-with-resources* — funcionou como planejado.      


### 🟩 Vídeo 02 - Concluindo implementação de java.io

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.03-curso.02-video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/simplificando-io-de-arquivos-e-diretorios-com-java/learning/6395a541-e246-47df-b9d9-61af2dd6b22f?autoplay=1


## Parte 2 - Trabalhando com java.nio

### 🟩 Vídeo 03 - Usando java.nio

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.03-curso.02-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/simplificando-io-de-arquivos-e-diretorios-com-java/learning/af09c850-e686-4488-b820-d36b71b50adb?autoplay=1

### 🟩 Vídeo 04 - Concluindo implementação do java.nio

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.03-curso.02-video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/simplificando-io-de-arquivos-e-diretorios-com-java/learning/7230497d-7e54-4de6-9a67-89ccb0280150?autoplay=1

### 🟩 Vídeo 05 - Usando java.nio2

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.03-curso.02-video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/simplificando-io-de-arquivos-e-diretorios-com-java/learning/19f6a205-2026-4620-8fa0-980b9db73dcb?autoplay=1

##  Materiais de Apoio

# Certificado: Simplificando IO de Arquivos e Diretórios com Java

- Link na plataforma: 
- Certificado em pdf: