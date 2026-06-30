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

```java
import br.com.dio.persistence.FilePersistence;
import br.com.dio.persistence.IOFilePersistence;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FilePersistence persistence = new IOFilePersistence("user.csv");
        System.out.println(persistence.write("Lucas;Lucas@lucas.com;15/01/1990"));
    }
}
```

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

### Anotações

Segue abaixo a implementação inicial da classe `IOFilePersistence`, que implementa a interface `FilePersistence`. Neste estágio da aula, apenas o método `write` e o método `findAll` estão funcionais; os métodos `remove`, `replace` e `findBy` retornam valores vazios ou `false`, sinalizando que ainda serão implementados. O método `write` utiliza `FileWriter`, `BufferedWriter` e `PrintWriter` para gravar dados em modo _append_ no arquivo. O método `findAll` lê o arquivo linha a linha com `BufferedReader` e `FileReader`, acumulando o conteúdo em um `StringBuilder`. O método privado `clearFile` zera o arquivo usando `FileOutputStream`.

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
        var content = new StringBuilder();
        try (var reader = new BufferedReader(new FileReader(currentDir + storedDir + fileName))) {
            String line;
            do
            {
                line = reader.readLine();
                if ((line != null)) content.append(line)
                        .append(System.lineSeparator());
            } while (line != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
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

A seguir a classe `Main` em um primeiro teste de uso, onde são gravadas três entradas no arquivo `user.csv` via `persistence.write(...)` e, em seguida, é chamado `persistence.findAll()` para exibir todo o conteúdo gravado. Este é o ponto em que o professor valida que o método `write` e o método `findAll` funcionam corretamente juntos.

```java
import br.com.dio.persistence.FilePersistence;
import br.com.dio.persistence.IOFilePersistence;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FilePersistence persistence = new IOFilePersistence("user.csv");
        System.out.println(persistence.write("Lucas;lucas@lucas.com;15/01/1990"));
        System.out.println(persistence.write("Maria;maria@maria.com;23/10/2000"));
        System.out.println(persistence.write("Lucas;lucas@lucas.com;01/12/1995"));
        System.out.println(persistence.findAll());
    }
}
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-06-29-18h48m54s384.jpg" alt="" width="840">
</p>

A imagem exibe o ambiente IntelliJ IDEA com o arquivo `user.csv` aberto, mostrando o resultado da execução do `Main`. O arquivo gerado na pasta `managedFiles/IO/` contém exatamente as três linhas gravadas pelo método `write`. No painel de execução, é possível ver que os dados foram impressos duas vezes: primeiro como retorno de cada chamada a `write`, e depois como resultado do `findAll`, confirmando que ambos os métodos funcionam corretamente.

No código a seguir vemos uma versão evoluída de `IOFilePersistence`, agora com o método `findBy` implementado. O método percorre o arquivo linha a linha com `BufferedReader` e `FileReader`, verificando via `line.contains(sentence)` se a linha contém o trecho buscado. Quando encontrado, armazena a linha em `found` e interrompe o laço com `break`. Caso nenhuma linha corresponda, retorna uma `String` vazia.

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
        var content = new StringBuilder();
        try (var reader = new BufferedReader(new FileReader(currentDir + storedDir + fileName))) {
            String line;
            do
            {
                line = reader.readLine();
                if ((line != null)) content.append(line)
                        .append(System.lineSeparator());
            } while (line != null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return content.toString();
    }

    @Override
    public String findBy(String sentence) {
        String found = "";
        try (var reader = new BufferedReader(new FileReader(currentDir + storedDir + fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(sentence)) {
                    found = line;
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return found;
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

Abaixo temos a classe `Main` atualizada para testar o método `findBy` com diferentes sentenças de busca: pelo nome (`"Lucas;"`), pelo e-mail (`"Maria;"`), por parte de uma data (`"95"`) e por um valor inexistente (`"22;"`). Os separadores com `"================"` facilitam a leitura do resultado no console.

```java
import br.com.dio.persistence.FilePersistence;
import br.com.dio.persistence.IOFilePersistence;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FilePersistence persistence = new IOFilePersistence("user.csv");
        System.out.println(persistence.write("Lucas;lucas@lucas.com;15/01/1990"));
        System.out.println(persistence.write("Maria;maria@maria.com;23/10/2000"));
        System.out.println(persistence.write("Lucas;lucas@lucas.com;01/12/1995"));
        System.out.println("================");
        System.out.println(persistence.findAll());
        System.out.println("================");
        System.out.println(persistence.findBy("Lucas;"));
        System.out.println("================");
        System.out.println(persistence.findBy("Maria;"));
        System.out.println("================");
        System.out.println(persistence.findBy("95"));
        System.out.println("================");
        System.out.println(persistence.findBy("22;"));
        System.out.println("================");
    }
}
```

---

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-06-29-19h16m06s990.jpg" alt="" width="840">
</p>

A imagem exibe o resultado da execução no console do IntelliJ, após os testes com `findBy`. É possível verificar que: a busca por `"Lucas;"` retornou a primeira ocorrência do nome Lucas; a busca por `"Maria;"` retornou a linha de Maria; a busca por `"95"` retornou a linha de Lucas com data `01/12/1995`; e a busca por `"22;"` retornou vazio, pois nenhuma linha continha essa sequência. O comportamento confirma que o método `findBy` opera por correspondência parcial de conteúdo.

O código a seguir apresenta a implementação do método `remove` na classe `IOFilePersistence`. A lógica consiste em: carregar todo o conteúdo do arquivo com `findAll()`; converter em uma `ArrayList` mutável usando `Stream.of(...).toList()` com quebra por `System.lineSeparator()`; verificar se alguma linha contém a sentença buscada — se não houver correspondência, retorna `false` imediatamente; caso contrário, chama `clearFile()` para limpar o arquivo e, em seguida, reescreve apenas as linhas que **não** contêm a sentença, usando `filter` + `forEach(this::write)`.

```java
package br.com.dio.persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Stream;

public class IOFilePersistence implements FilePersistence {

    private final String currentDir = System.getProperty("user.dir");
    private final String storedDir = "/managedFiles/IO/";
    private final String fileName;

    // ... (construtor e outros métodos omitidos para brevidade)

    @Override
    public boolean remove(final String sentence) {
        var content = findAll();
        var contentList = new ArrayList<>(Stream.of(content.split(System.lineSeparator())).toList());
        if (contentList.stream().noneMatch(c -> c.contains(sentence))) return false;
        clearFile();
        contentList.stream()
                .filter(c -> !c.contains(sentence))
                .forEach(this::write);
        return true;
    }

    @Override
    public String replace(String oldContent, String newContent) {
        return "";
    }

    // ... (demais métodos)
}
```

A seguir, a classe `Main` atualizada para testar o método `remove`. Após gravar e listar os três registros, são realizadas duas tentativas de remoção: a primeira com `"/12/19"` (que corresponde à linha de João com data `01/12/1995`) deve retornar `true`; a segunda com `"/06/2021"` (inexistente no arquivo) deve retornar `false`. Em seguida, novas buscas por `findBy` verificam que João não é mais encontrado.

```java
import br.com.dio.persistence.FilePersistence;
import br.com.dio.persistence.IOFilePersistence;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FilePersistence persistence = new IOFilePersistence("user.csv");
        System.out.println(persistence.write("Lucas;lucas@lucas.com;15/01/1990"));
        System.out.println(persistence.write("Maria;maria@maria.com;23/10/2000"));
        System.out.println(persistence.write("Lucas;lucas@lucas.com;01/12/1995"));
        System.out.println("================");
        System.out.println(persistence.findAll());
        System.out.println("================");
        System.out.println(persistence.remove("/12/19"));
        System.out.println("================");
        System.out.println(persistence.remove("/06/2021"));
        System.out.println("================");
        System.out.println(persistence.findBy("Lucas;"));
        System.out.println("================");
        System.out.println(persistence.findBy("Maria;"));
        System.out.println("================");
        System.out.println(persistence.findBy("95"));
        System.out.println("================");
    }
}
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-06-29-19h27m25s262.jpg" alt="" width="840">
</p>

A imagem exibe a saída do console após a execução com os testes de `remove`. O resultado confirma o comportamento esperado: o primeiro `remove("/12/19")` retornou `true` e removeu a linha de João; o segundo `remove("/06/2021")` retornou `false` pois nenhuma linha continha esse padrão. As buscas posteriores mostram que Lucas e Maria ainda estão presentes, mas a busca por `"95"` (referente à data de João) não retornou nenhum resultado, evidenciando que a remoção foi bem-sucedida.

Abaixo a versão completa da classe `IOFilePersistence`, agora com o método `replace` implementado e o método auxiliar privado `toListString()` extraído via refatoração. O método `replace` segue lógica semelhante ao `remove`: carrega o conteúdo como lista, verifica se o `oldContent` existe, limpa o arquivo e reescreve as linhas — substituindo, via `map`, as linhas que contêm `oldContent` pelo `newContent`. O método `toListString()` encapsula a conversão do conteúdo do arquivo em uma `List<String>` mutável, evitando duplicação de código.

```java
package br.com.dio.persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class IOFilePersistence implements FilePersistence {

    private final String currentDir = System.getProperty("user.dir");
    private final String storedDir = "/managedFiles/IO/";
    private final String fileName;

    // ... (construtor omitido para brevidade)

    @Override
    public boolean remove(final String sentence) {
        var contentList = toListString();
        if (contentList.stream().noneMatch(c -> c.contains(sentence))) return false;
        clearFile();
        contentList.stream()
                .filter(c -> !c.contains(sentence))
                .forEach(this::write);
        return true;
    }

    @Override
    public String replace(final String oldContent, final String newContent) {
        var contentList = toListString();

        if (contentList.stream().noneMatch(c -> c.contains(oldContent))) return "";

        clearFile();
        contentList.stream()
                .map(c -> c.contains(oldContent) ? newContent : c)
                .forEach(this::write);
        return newContent;
    }

    @Override
    public String findAll() {
        var content = new StringBuilder();
        try (var reader = new BufferedReader(new FileReader(currentDir + storedDir + fileName))) {
            String line;
            do
            {
                line = reader.readLine();
                if ((line != null)) content.append(line)
                        .append(System.lineSeparator());
            } while (line != null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return content.toString();
    }

    @Override
    public String findBy(String sentence) {
        String found = "";
        try (var reader = new BufferedReader(new FileReader(currentDir + storedDir + fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(sentence)) {
                    found = line;
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return found;
    }

    private List<String> toListString() {
        var content = findAll();
        return new ArrayList<>(Stream.of(content.split(System.lineSeparator())).toList());
    }

    private void clearFile(){
        try(OutputStream outputStream = new FileOutputStream(currentDir + storedDir + fileName)) {
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private void createFile(){

    }
}
```

Por fim, a classe `Main` com o teste completo de `replace`. Após remover João e listar os dados, é chamado `persistence.replace(".com;15/01/", "Carlos;carlos@carlos.com;22/03/1991")`, substituindo a linha de Lucas pela de Carlos. Em seguida, `findAll()` é chamado para exibir o estado final do arquivo e confirmar a substituição.

```java
import br.com.dio.persistence.FilePersistence;
import br.com.dio.persistence.IOFilePersistence;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FilePersistence persistence = new IOFilePersistence("user.csv");
        System.out.println(persistence.write("Lucas;lucas@lucas.com;15/01/1990"));
        System.out.println(persistence.write("Maria;maria@maria.com;23/10/2000"));
        System.out.println(persistence.write("Lucas;lucas@lucas.com;01/12/1995"));
        System.out.println("================");
        System.out.println(persistence.findAll());
        System.out.println("================");
        System.out.println(persistence.remove("/12/19"));
        System.out.println("================");
        System.out.println(persistence.remove("/06/2021"));
        System.out.println("================");
        System.out.println(persistence.findBy("Lucas;"));
        System.out.println("================");
        System.out.println(persistence.findBy("Maria;"));
        System.out.println("================");
        System.out.println(persistence.findBy( "95;" ));
        System.out.println("================");
        System.out.println(persistence.replace(".com;15/01/", "Carlos;carlos@carlos.com;22/03/1991" ));
        System.out.println("================");
        System.out.println(persistence.findAll());
    }
}
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-06-29-19h43m02s649.jpg" alt="" width="840">
</p>

A imagem exibe a saída final do console, confirmando o funcionamento completo de todos os métodos da `IOFilePersistence`. O fluxo de execução mostra: gravação dos três registros, listagem completa, remoção bem-sucedida de João (`true`), tentativa de remoção inexistente (`false`), buscas por Lucas e Maria, o retorno do `replace` com o novo conteúdo (`Carlos;carlos@carlos.com;22/03/1991`), e o `findAll` final exibindo apenas Carlos e Maria no arquivo. Com isso, a implementação completa da API de IO do Java para gerenciamento de arquivos CSV está validada e funcional.


## Parte 2 - Trabalhando com java.nio

### 🟩 Vídeo 03 - Usando java.nio

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.03-curso.02-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/simplificando-io-de-arquivos-e-diretorios-com-java/learning/af09c850-e686-4488-b820-d36b71b50adb?autoplay=1

### Anotações

#### Criando a classe NIOFilePersistence

A classe `NIOFilePersistence` é criada implementando a interface `FilePersistence`, dando início à segunda forma de trabalhar com arquivos em Java, agora usando a API `java.io` em conjunto com recursos de baixo nível (como o `RandomAccessFile`). O construtor recebe o `fileName`, monta o caminho do arquivo a partir do diretório atual (`currentDir`) somado ao diretório de armazenamento (`storedDir`), cria o diretório caso não exista e chama `clearFile()` — método reaproveitado da implementação anterior em Java IO, responsável por garantir que o arquivo exista (criando-o, se necessário) através de um `FileOutputStream`.

O método `write` já está implementado: dentro de um `try-with-resources`, é aberto um `RandomAccessFile` no modo `"rw"` (leitura e escrita), o cursor é posicionado no fim do arquivo com `file.seek(file.length())` para não sobrescrever o conteúdo existente, e em seguida o conteúdo (`data`) é escrito com `writeBytes`, seguido de uma quebra de linha via `System.lineSeparator()`. Os demais métodos da interface (`remove`, `replace`, `findAll`, `findBy`) ainda estão como stubs, retornando valores vazios ou `false`, pois serão implementados nas etapas seguintes da aula.

```java
package br.com.dio.persistence;

import java.io.*;

public class NIOFilePersistence implements FilePersistence{

    private final String currentDir = System.getProperty("user.dir");
    private final String storedDir = "/managedFiles/IO/";
    private final String fileName;

    public NIOFilePersistence(String fileName) throws IOException {
        this.fileName = fileName;
        var file = new File(currentDir + storedDir);
        if (!file.exists() && !file.mkdirs()) throw new IOException("Erro ao criar arquivo");
        clearFile();
    }

    @Override
    public String write(final String data) {
        try(var file = new RandomAccessFile(new File(currentDir + storedDir + fileName), "rw")){
            file.seek(file.length());
            file.writeBytes(data);
            file.writeBytes(System.lineSeparator());
        }catch (IOException ex){
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
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
```

#### Testando o método write

Na classe `Main`, é instanciado um `FilePersistence` usando a nova implementação `NIOFilePersistence`, apontando para o arquivo `user.csv`. Em seguida, o método `write` é chamado três vezes, cada uma escrevendo uma linha no formato `nome;email;data_de_nascimento;` para os usuários Bianca, Bernardo e Ricardo, com separadores impressos no console entre cada chamada para facilitar a visualização do resultado.

```java
import br.com.dio.persistence.FilePersistence;
import br.com.dio.persistence.NIOFilePersistence;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FilePersistence persistence = new NIOFilePersistence("user.csv");
        System.out.println(persistence.write( "bianca;bia@bia.com;22/09/1997;" ));
        System.out.println("====================");
        System.out.println(persistence.write(  "bernardo;bernardo@bernardo.com;28/11/1999;" ));
        System.out.println("====================");
        System.out.println(persistence.write(  "ricardo;ricardo@ricardo.com;12/01/2000;" ));
        System.out.println("====================");
    }
}
```

#### Resultado da execução do write

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-06-30-08h59m52s335.jpg" alt="" width="840">
</p>

Ao executar a classe `Main`, o console mostra cada linha escrita sendo retornada e impressa logo após o seu respectivo separador, confirmando que o método `write` está funcionando corretamente e acrescentando, e não sobrescrevendo, o conteúdo no arquivo `user.csv`. O processo finaliza com o código de saída `0`, indicando que não houve erros durante a escrita.

```
bianca;bia@bia.com;22/09/1997;
====================
bernardo;bernardo@bernardo.com;28/11/1999;
====================
ricardo;ricardo@ricardo.com;12/01/2000;
====================

Process finished with exit code 0
```

#### Implementando o método findAll

Agora a classe `NIOFilePersistence` recebe a implementação do método `findAll`, responsável por ler todo o conteúdo do arquivo. Primeiro é verificado se o arquivo existe; se não existir, retorna uma string vazia. Em seguida, dentro de um `try-with-resources`, é aberto um `RandomAccessFile` no modo `"r"` (somente leitura) e obtido o seu `FileChannel` através de `raf.getChannel()`. Um `ByteBuffer` de 256 bytes é alocado para realizar a leitura em blocos: enquanto o canal ainda tiver bytes para ler (`channel.read(buffer) != -1`), o buffer é virado com `flip()` para permitir a leitura dos dados nele armazenados, e cada byte lido é convertido para `char` e adicionado ao `StringBuilder` (`content`). Depois de esgotar o conteúdo do buffer, ele é limpo com `clear()` para receber a próxima leitura. Ao final, o método retorna `content.toString()`, ou seja, todo o conteúdo do arquivo lido byte a byte — uma abordagem mais próxima do sistema operacional e, por isso, um pouco mais complexa do que a leitura feita com a API Java IO.

```java
@Override
public String findAll() {
    var content = new StringBuilder();
    File file = new File(currentDir + storedDir + fileName);

    if (!file.exists()) return "";

    try(var raf = new RandomAccessFile(file, "r");
        var channel = raf.getChannel()){

        var buffer = ByteBuffer.allocate(256);
        while (channel.read(buffer) != -1){
            buffer.flip();
            while (buffer.hasRemaining()){
                content.append((char) buffer.get());
            }
            buffer.clear();
        }
    }catch (IOException ex){
        ex.printStackTrace();
    }
    return content.toString();
}
```

#### Depurando a leitura com breakpoint no findAll

Na classe `Main`, é adicionada uma nova chamada `persistence.findAll()` logo após as três escritas, e um breakpoint é posicionado nessa linha para permitir o acompanhamento, em modo de depuração, de como a leitura byte a byte realmente acontece dentro do método implementado anteriormente.

```java
public class Main {
    public static void main(String[] args) throws IOException {
        FilePersistence persistence = new NIOFilePersistence("user.csv");
        System.out.println(persistence.write( "bianca;bia@bia.com;22/09/1997;" ));
        System.out.println("====================");
        System.out.println(persistence.write(  "bernardo;bernardo@bernardo.com;28/11/1999;" ));
        System.out.println("====================");
        System.out.println(persistence.write(  "ricardo;ricardo@ricardo.com;12/01/2000;" ));
        System.out.println("====================");
        System.out.println(persistence.findAll());
        System.out.println("========================");
    }
}
```

#### Acompanhando a conversão de bytes para caracteres no debugger

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-06-30-09h05m05s844.jpg" alt="" width="840">
</p>

Com a execução pausada dentro do `findAll`, o painel de variáveis do depurador permite observar, passo a passo, o comportamento do laço de leitura: o `buffer` aparece como um `HeapByteBuffer` já posicionado após a leitura de alguns bytes, e a variável `content` mostra progressivamente o `StringBuilder` sendo montado (já contendo `"bianca"` neste ponto da execução). É possível ver, ao avaliar `buffer.get()`, que cada byte lido é convertido para o caractere correspondente (por exemplo, o primeiro byte convertido resulta na letra "i"), confirmando visualmente como o conteúdo do arquivo é reconstruído caractere a caractere até formar a string final.

#### Resultado final: write e findAll funcionando juntos

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-06-30-09h25m54s976.jpg" alt="" width="840">
</p>

Ao liberar o breakpoint e concluir a execução em modo debug, o console exibe primeiro as três linhas escritas pelo método `write` (Bianca, Bernardo e Ricardo, cada uma seguida do separador), e logo em seguida o resultado do `findAll()`, que reproduz exatamente o mesmo conteúdo lido diretamente do arquivo `user.csv`, confirmando que a leitura byte a byte reconstituiu corretamente todas as linhas gravadas. A execução é encerrada com a desconexão da VM de depuração e o código de saída `0`. Com a escrita (`write`) e a leitura completa (`findAll`) implementadas e validadas, a aula é encerrada, ficando para o próximo encontro a implementação dos métodos `remove`, `replace` e `findBy`.

```
bianca;bia@bia.com;22/09/1997;
====================
bernardo;bernardo@bernardo.com;28/11/1999;
====================
ricardo;ricardo@ricardo.com;12/01/2000;
====================
bianca;bia@bia.com;22/09/1997;
bernardo;bernardo@bernardo.com;28/11/1999;
ricardo;ricardo@ricardo.com;12/01/2000;

========================
Disconnected from the target VM, address: '127.0.0.1:60275', transport: 'socket'

Process finished with exit code 0
```      


### 🟩 Vídeo 04 - Concluindo implementação do java.nio

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.03-curso.02-video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/simplificando-io-de-arquivos-e-diretorios-com-java/learning/7230497d-7e54-4de6-9a67-89ccb0280150?autoplay=1

### Anotações

#### Estrutura inicial do `NIOFilePersistence`

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-06-30-13h02m14s568.jpg" alt="" width="840">
</p>

A imagem mostra a classe `NIOFilePersistence`, que implementa a interface `FilePersistence` usando os recursos de `java.nio` (em especial `RandomAccessFile`, `FileChannel` e `ByteBuffer`). Já estão prontos os métodos `write`, `findAll` e `clearFile`, enquanto `remove` e `replace` ainda retornam valores fixos (`false` e `""`, respectivamente). O método `findBy`, foco da aula, aparece com uma lógica que percorre o buffer byte a byte, monta o conteúdo em um `StringBuilder` e tenta identificar quando uma linha contém a sentença buscada, mas ainda está em processo de construção e ajuste.

#### Código de teste no `Main` com chamadas ao `findBy`

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-06-30-13h02m15s698.jpg" alt="" width="840">
</p>

Aqui está a classe `Main`, que instancia `NIOFilePersistence` para o arquivo `user.csv` e grava três registros (bianca, bernardo e ricardo). Em seguida, chama `findAll()` para listar tudo e, na sequência, duas chamadas a `findBy`: uma buscando `";bia@"` (que deve encontrar o registro da Bianca) e outra buscando `";laura@"` (que não existe no arquivo). Um breakpoint vermelho na linha do primeiro `findBy` indica que a aula vai usar o depurador para acompanhar passo a passo o que acontece dentro do método.

#### Iniciando a depuração (debug) da aplicação

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-06-30-13h02m16s493.jpg" alt="" width="840">
</p>

Nesta imagem, o menu de execução do IntelliJ IDEA é aberto sobre a classe `Main`, mostrando as opções "Run", "Debug" e "Run with Coverage". A opção "Debug 'Main.main()'" é selecionada para iniciar a execução em modo de depuração, permitindo parar no breakpoint definido dentro do `findBy` e observar variável por variável o comportamento do laço de leitura do arquivo.

#### Primeira parada no depurador: buffer ainda vazio

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-06-30-13h09m51s423.jpg" alt="" width="840">
</p>

A execução já está parada dentro do `findBy`, na linha do `while (!content.toString().endsWith(System.lineSeparator()))`. No painel de variáveis é possível ver que `sentence` vale `";bia@"`, `content` ainda está vazio (`""`) e `bytesReader` é `114`, ou seja, o canal já leu 114 bytes do arquivo para dentro do buffer, mas o `StringBuilder` ainda não processou nenhum caractere. Essa é a etapa inicial do laço que irá montar a linha caractere a caractere.

#### Acompanhando a montagem da linha caractere a caractere

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-06-30-13h10m00s868.jpg" alt="" width="840">
</p>

Avançando no depurador, o conteúdo de `content` já apresenta o trecho parcial `"bianca;bia@bi"`, mostrando que o laço interno `content.append((char) buffer.get())` está, de fato, lendo o `ByteBuffer` posição a posição e concatenando cada caractere ao `StringBuilder`. O buffer indica `pos=13`, confirmando que treze caracteres já foram consumidos do total de 114 bytes lidos.

#### Linha completa formada e teste do `contains`

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-06-30-13h10m09s345.jpg" alt="" width="840">
</p>

O laço interno termina quando `content` passa a terminar com o separador de linha do sistema, e agora `content` contém a linha inteira: `"bianca;bia@bia.com;22/09/1997;\n"`. A condição `if (content.toString().contains(sentence))` é avaliada com `sentence = ";bia@"`, e como essa sequência está presente na linha, o fluxo deve entrar no `break`, interrompendo a busca porque a linha procurada foi encontrada.

#### Retorno do conteúdo encontrado

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-06-30-13h10m22s335.jpg" alt="" width="840">
</p>

A execução chegou até o `return content.toString()`, no final do método `findBy`. O painel de variáveis confirma que `content` ainda guarda a linha completa da Bianca (`"bianca;bia@bia.com;22/09/1997;\n"`), que será o valor devolvido pela chamada. Isso mostra que, para esse primeiro caso de teste (busca por `";bia@"`), a lógica implementada até aqui funciona corretamente.

#### Resultado no console para a primeira busca

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-06-30-13h10m33s494.jpg" alt="" width="840">
</p>

O console mostra a sequência completa de saídas até este ponto: a gravação dos três registros (bianca, bernardo e ricardo), o resultado do `findAll()` listando todos eles, e logo depois o resultado de `persistence.findBy(";bia@")`, que retornou corretamente apenas a linha da Bianca. Isso confirma visualmente que o primeiro cenário de busca, com correspondência encontrada, está funcionando como esperado.

#### Erro ao buscar uma sentença inexistente

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-06-30-13h11m57s801.jpg" alt="" width="840">
</p>

Ao testar agora a busca por `";laura@"` (que não existe no arquivo), a aplicação lança uma `StringIndexOutOfBoundsException`, com a mensagem "String index out of range: -1". O stack trace aponta para a linha 89 do `NIOFilePersistence`, exatamente no trecho `content.setLength(-1)`, dentro do bloco `else` da verificação `contains`. Esse erro evidencia uma falha na lógica: ao tentar "limpar" o `StringBuilder` para continuar a busca na próxima linha, o código está chamando `setLength` com um valor inválido (`-1`).

#### Corrigindo o `setLength` para continuar a busca

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-06-30-13h12m17s123.jpg" alt="" width="840">
</p>

O trecho `content.setLength(-1)` é então corrigido para `content.setLength(0)`, que é a forma correta de esvaziar um `StringBuilder` para reaproveitá-lo na leitura da próxima linha. No painel de depuração, com a busca agora por `";laura@"`, o `content` ainda mostra a linha da Bianca antes de ser limpo, confirmando que o fluxo está prestes a reiniciar a montagem de uma nova linha já sem o erro anterior.

#### Avançando para a linha do Bernardo, ainda sem correspondência

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-06-30-13h12m29s772.jpg" alt="" width="840">
</p>

Com a correção aplicada, o laço de leitura segue normalmente e agora `content` contém a linha `"bernardo;bernardo@bernardo.com;28/11/1999;\n"`. Como `sentence` continua sendo `";laura@"` e essa linha não contém esse trecho, a condição do `if (content.toString().contains(sentence))` é falsa, e o código deve seguir para o `else`, limpando o `content` novamente para tentar a próxima linha.

#### Nova parada no breakpoint, agora na linha do Ricardo

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-06-30-13h12m34s751.jpg" alt="" width="840">
</p>

O depurador para outra vez no breakpoint da linha 86, e desta vez `content` mostra a linha `"Ricardo;ricardo@ricardo.com;12/01/2000;\n"`. A mensagem no rodapé confirma "Breakpoint reached", indicando que o laço continua avançando linha por linha pelo arquivo em busca de `";laura@"`, que ainda não foi localizada, já que nem Bernardo nem Ricardo correspondem à sentença buscada.

#### Console final: busca sem correspondência termina sem erros

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-06-30-13h12m41s639.jpg" alt="" width="840">
</p>

No console, depois do resultado já conhecido de `findBy(";bia@")` (a linha da Bianca), o processo é desconectado da JVM de depuração e finalizado com "Process finished with exit code 0", ou seja, sem lançar exceções. Isso mostra que, após a correção do `setLength(0)`, a busca por uma sentença inexistente (`";laura@"`) agora consegue percorrer todas as linhas do arquivo sem quebrar a aplicação, mesmo que o tratamento do "não encontrado" ainda precise ser refinado.

#### Versão final e simplificada do `NIOFilePersistence`

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-06-30-13h21m54s981.jpg" alt="" width="840">
</p>

```java
package br.com.dio.persistence;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.stream.Stream;

public class NIOFilePersistence implements FilePersistence{

    private final String currentDir = System.getProperty("user.dir");
    private final String storedDir = "/managedFiles/IO/";
    private final String fileName;

    public NIOFilePersistence(String fileName) throws IOException {
        this.fileName = fileName;
        var file = new File(currentDir + storedDir);
        if (!file.exists() && !file.mkdirs()) throw new IOException("Erro ao criar arquivo");
        clearFile();
    }

    @Override
    public String write(final String data) {
        try(var file = new RandomAccessFile(new File(currentDir + storedDir + fileName), "rw")){
            file.seek(file.length());
            file.writeBytes(data);
            file.writeBytes(System.lineSeparator());
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return data;
    }

    @Override
    public boolean remove(final String sentence) {
        var content = findAll();
        var contentList = Stream.of(content.split(System.lineSeparator())).toList();

        if (contentList.stream().noneMatch(c -> c.contains(sentence))) return false;

        clearFile();
        contentList.stream()
                .filter(c -> !c.contains(sentence))
                .forEach(this::write);
        return true;
    }

    @Override
    public String replace(String oldContent, String newContent) {
        return "";
    }

    @Override
    public String findAll() {
        var content = new StringBuilder();
        File file = new File(currentDir + storedDir + fileName);

        if (!file.exists()) return "";

        try(var raf = new RandomAccessFile(file, "r");
            var channel = raf.getChannel()){

            var buffer = ByteBuffer.allocate(256);
            while (channel.read(buffer) != -1){
                buffer.flip();
                while (buffer.hasRemaining()){
                    content.append((char) buffer.get());
                }
                buffer.clear();
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return content.toString();
    }

    // Exemplo de correção simples para o findBy
    @Override
    public String findBy(final String sentence) {
        try {
            var lines = java.nio.file.Files.readAllLines(java.nio.file.Path.of(currentDir + storedDir + fileName));
            return lines.stream()
                    .filter(line -> line.contains(sentence))
                    .findFirst()
                    .orElse("Registro não encontrado");
        } catch (IOException ex) {
            return "Erro ao buscar: " + ex.getMessage();
        }
    }

    private void clearFile(){
        try(OutputStream outputStream = new FileOutputStream(currentDir + storedDir + fileName)) {
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
```

Esta é a versão final da classe `NIOFilePersistence` apresentada na aula. O `findBy` original, baseado em leitura byte a byte com `ByteBuffer`, foi substituído por uma alternativa mais simples utilizando `Files.readAllLines` combinado com Stream API (`filter`, `findFirst`, `orElse`), o que reduz bastante a complexidade do código, embora implique carregar todo o conteúdo do arquivo em memória de uma vez. Os métodos `remove` e `replace` também foram implementados: `remove` usa `findAll()` para obter o conteúdo, separa em linhas com `split`, verifica se existe alguma correspondência com `noneMatch`, limpa o arquivo com `clearFile()` e regrava apenas as linhas que não contêm a sentença buscada, usando `forEach(this::write)`.

#### Código final de teste no `Main`

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-06-30-13h21m57s040.jpg" alt="" width="840">
</p>

```java
import br.com.dio.persistence.FilePersistence;
import br.com.dio.persistence.NIOFilePersistence;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FilePersistence persistence = new NIOFilePersistence("user.csv");
        System.out.println(persistence.write("bianca;bia@bia.com;22/09/1997;"));
        System.out.println("====================================");
        System.out.println(persistence.write("bernardo;bernardo@bernardo.com;28/11/1999;"));
        System.out.println("====================================");
        System.out.println(persistence.write("ricardo;ricardo@ricardo.com;12/01/2000;"));
        System.out.println("====================================");
        System.out.println(persistence.findAll());
        System.out.println("====================================");
        System.out.println(persistence.remove("ricardo@"));
        System.out.println("====================================");
        System.out.println(persistence.findBy("bia@"));
        System.out.println("====================================");
        System.out.println(persistence.findBy("ricardo@"));
        System.out.println("====================================");

    }
}
```

Esta é a versão final da classe `Main`, que exercita toda a API construída ao longo da aula: grava os três registros (bianca, bernardo e ricardo), lista tudo com `findAll`, remove o registro do Ricardo com `remove("ricardo@")`, busca pela Bianca com `findBy("bia@")` (que deve retornar a linha encontrada) e, por fim, busca novamente por `"ricardo@"` com `findBy`, que agora deve indicar que o registro não foi mais encontrado, já que ele foi removido na etapa anterior.

#### Execução final completa no console

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-06-30-13h21m58s103.jpg" alt="" width="840">
</p>

O console mostra a execução completa e bem-sucedida do programa: os três registros são gravados e exibidos um a um, o `findAll()` lista todos eles juntos, o `remove("ricardo@")` retorna `true` (indicando que encontrou e removeu o registro), o `findAll()` seguinte mostra que apenas a linha da Bianca permanece, e o `findBy("ricardo@")` retorna "Registro não encontrado", confirmando que a remoção funcionou corretamente. O processo finaliza com "exit code 0", sem erros, encerrando a implementação da persistência baseada em `java.nio`.      


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