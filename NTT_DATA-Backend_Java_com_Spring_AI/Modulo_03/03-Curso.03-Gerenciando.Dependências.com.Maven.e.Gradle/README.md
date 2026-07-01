## Instrutor

- José Luiz Abreu Cardoso Junior (Engenheiro de software sênior)
- Contato Linkedin: / [juniorjrjl](https://www.linkedin.com/in/juniorjrjl/)

## Parte 1 - Instalação e Configuração do Maven

### 🟩 Vídeo 01 - Instalando Maven

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.03-curso.03-video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/gerenciando-dependencias-com-maven-e-gradle/learning/d9d9f3b4-84fa-4114-8436-28067fffae34?autoplay=1

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/2026-07-01-08-51-23.png" alt="" width="840">
</p>

A aula trata da instalação e uso do Maven no Ubuntu/Linux. O instrutor explica que o Maven é uma ferramenta para gerenciar dependências e automatizar builds em projetos Java, mostrando como instalar via apt (forma convencional e simples) e também mencionando a alternativa manual de baixar diretamente do site oficial do Apache Maven, configurar variáveis de ambiente e usar versões mais atualizadas. O foco é demonstrar como preparar o ambiente para que o Maven esteja disponível no sistema e pronto para ser utilizado em projetos Java.

#### Preparando Ambiente Java + Maven no Kubuntu

#### 1. Verificar instalação do Java
Execute os comandos:
```bash
java -version
javac -version
```
Saída esperada (exemplo):
```
openjdk version "21.0.11"
javac 21.0.11
```

#### 2. Instalar o Maven
Atualize os pacotes e instale:
```bash
sudo apt update
sudo apt install maven
```

#### 3. Confirmar instalação do Maven
Verifique a versão:
```bash
mvn -version
```
Saída esperada (exemplo):
```
Apache Maven 3.9.12
Java version: 21.0.11
```

#### 4. Estrutura do Projeto
O projeto deve conter um arquivo `pom.xml` na raiz, que define:
- Dependências
- Plugins
- Configurações de build

#### 5. Compilar o Projeto
Dentro da pasta do projeto:
```bash
mvn compile
```

#### 6. Executar Testes
```bash
mvn test
```

#### 7. Gerar Arquivo JAR
```bash
mvn package
```
O `.jar` será criado em `target/`.

#### 8. Executar o Programa
```bash
java -jar target/meu-projeto-1.0-SNAPSHOT.jar
```

#### 9. Executar Diretamente sem JAR
```bash
mvn exec:java -Dexec.mainClass="com.seuprojeto.Main"
```

#### 10. Observações
- O IntelliJ IDEA reconhece automaticamente o `pom.xml` e baixa dependências.  
- O terminal usa o mesmo `pom.xml`, garantindo consistência entre IDE e linha de comando.  
- Maven facilita o gerenciamento de **dependências** e a **automatização de builds**.

### 🟩 Vídeo 02 - Instalando Maven com sdkman e criando projetos

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.03-curso.03-video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/gerenciando-dependencias-com-maven-e-gradle/learning/425b429c-c4bf-4bef-937d-4a523c4013ee?autoplay=1

### Anotações

#### Verificando a instalação do Maven via terminal

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-01-09h07m30s738.jpg" alt="" width="840">
</p>

A imagem mostra o terminal com a checagem da instalação do Maven usando o comando `mvn -v`, ao lado da página oficial do Apache Maven ("Maven in 5 Minutes") aberta no navegador. A saída do comando confirma a versão do Maven instalada, o diretório `Maven home`, a versão do Java utilizada pelo runtime e informações do sistema operacional.

```bash
mvn -v
```
```
Apache Maven 3.9.6 (bc0240f3c744dd6b6ec2920b3cd08dcc295161ae)
Maven home: /Users/junior/.sdkman/candidates/maven/current
Java version: 17.0.10, vendor: Amazon.com Inc., runtime: /Users/junior/.sdkman/candidates/java/17.0.10-amzn
Default locale: en_BR, platform encoding: UTF-8
OS name: "mac os x", version: "14.4", arch: "aarch64", family: "mac"
```

#### Criando o projeto Maven no IntelliJ

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-01-09h10m10s536.jpg" alt="" width="840">
</p>

Nesta etapa, a criação do projeto é feita diretamente pela interface do IntelliJ, usando a tela de **New Project**. É selecionado o *Build system* **Maven**, a **JDK 21**, e preenchidos os campos de **GroupId** (`br.com.dio`) e **ArtifactId** (`maven-project`), definindo assim as coordenadas básicas que identificam o projeto dentro do ecossistema Maven.

#### Declarando as dependências do MapStruct e do Lombok no pom.xml

O `pom.xml` já reflete a organização das versões das bibliotecas em `<properties>`, evitando repetição de números de versão ao longo do arquivo, e referenciando-as nas dependências através da sintaxe `${...}`. São declaradas as dependências do **MapStruct** (`mapstruct` e `mapstruct-processor`) e do **Lombok** (`lombok` e `lombok-mapstruct-binding`), sendo esta última responsável por integrar corretamente o Lombok ao processo de geração de código do MapStruct.

```xml
<properties>
    <maven.compiler.source>21</maven.compiler.source>
    <maven.compiler.target>21</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <org.mapstruct.version>1.5.5.Final</org.mapstruct.version>
    <org.projectlombok.version>1.18.30</org.projectlombok.version>
    <lombok-mapstruct-binding.version>0.2.0</lombok-mapstruct-binding.version>
</properties>

<dependencies>
    <dependency>
        <groupId>org.mapstruct</groupId>
        <artifactId>mapstruct</artifactId>
        <version>${org.mapstruct.version}</version>
    </dependency>
    <dependency>
        <groupId>org.mapstruct</groupId>
        <artifactId>mapstruct-processor</artifactId>
        <version>${org.mapstruct.version}</version>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok-mapstruct-binding</artifactId>
        <version>${lombok-mapstruct-binding.version}</version>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>${org.projectlombok.version}</version>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok-mapstruct-binding</artifactId>
        <version>${lombok-mapstruct-binding.version}</version>
    </dependency>
</dependencies>
```
#### Dependências baixadas e disponíveis no projeto

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-01-10h04m18s791.jpg" alt="" width="560">
</p>

Após o *refresh* do Maven, o painel de **External Libraries** do IntelliJ passa a exibir as bibliotecas efetivamente baixadas: `mapstruct`, `mapstruct-processor`, `lombok` e `lombok-mapstruct-binding`, além da JDK do projeto. Isso confirma que o Maven resolveu corretamente as dependências declaradas no `pom.xml` e as disponibilizou como bibliotecas Java prontas para uso no código.

#### Página oficial do Project Lombok

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-01-10h07m08s321.jpg" alt="" width="840">
</p>


A imagem mostra a página inicial do **Project Lombok** (`projectlombok.org`), biblioteca Java que se integra ao editor e às ferramentas de build para reduzir a verbosidade do código, gerando automaticamente métodos como getters, setters, construtores, `equals`, `hashCode` e `toString` a partir de anotações.

#### Página oficial do MapStruct

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-01-10h07m10s589.jpg" alt="" width="840">
</p>

Aqui é exibida a página inicial do **MapStruct** (`mapstruct.org`), um gerador de código que simplifica o mapeamento entre diferentes tipos de objetos Java (por exemplo, entidades e DTOs). Por atuar como um *annotation processor* em tempo de compilação, o MapStruct produz mapeamentos type-safe e de alta performance, sem depender de reflection em tempo de execução.

#### Documentação de instalação do MapStruct via Maven

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-01-10h07m20s379.jpg" alt="" width="840">
</p>

A documentação oficial do MapStruct apresenta o trecho de configuração necessário no `pom.xml` para o uso da biblioteca via Maven: a declaração da versão em `<properties>`, a dependência principal `mapstruct` e a configuração do plugin de compilação do Maven, incluindo o `mapstruct-processor` como *annotation processor path*.

#### Configuração final do plugin de compilação no pom.xml

O `pom.xml` completo mostra a seção `<build><plugins>`, onde o **maven-compiler-plugin** é configurado apontando para a versão do Java definida em `${java.version}` e declarando, em `<annotationProcessorPaths>`, os processadores de anotação necessários: `mapstruct-processor` e `lombok`. Essa configuração é essencial para que o Lombok e o MapStruct, que dependem fortemente de anotações, funcionem corretamente durante a compilação do projeto.

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.12.1</version>
            <configuration>
                <source>${java.version}</source>
                <target>${java.version}</target>
                <annotationProcessorPaths>
                    <path>
                        <groupId>org.mapstruct</groupId>
                        <artifactId>mapstruct-processor</artifactId>
                        <version>${org.mapstruct.version}</version>
                    </path>
                    <path>
                        <groupId>org.projectlombok</groupId>
                        <artifactId>lombok</artifactId>
                        <version>${org.projectlombok.version}</version>
                    </path>
                    <path>
                        <groupId>org.mapstruct</groupId>
                        <artifactId>mapstruct-processor</artifactId>
                        <version>${org.mapstruct.version}</version>
                    </path>
                </annotationProcessorPaths>
            </configuration>
        </plugin>
    </plugins>
</build>
```

#### Instalando o plugin do Lombok no IntelliJ

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-01-10h55m08s988.jpg" alt="" width="840">
</p>

Na tela de **Settings > Plugins** do IntelliJ, o plugin **Lombok** aparece instalado e habilitado. Esse plugin é necessário para que a IDE reconheça corretamente o código gerado automaticamente pelo Lombok (como getters e setters), permitindo autocompletar e navegação normalmente, mesmo que esses métodos não existam explicitamente no código-fonte.

#### Instalando o plugin do MapStruct no IntelliJ

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-01-10h56m39s346.jpg" alt="" width="840">
</p>

Ainda na tela de **Settings > Plugins**, é exibido o plugin **MapStruct Support**, também instalado e habilitado. Ele fornece suporte adicional da IDE para projetos que utilizam o MapStruct, como autocompletar de propriedades de origem e destino nas anotações `@Mapping` e `@ValueMapping`.

#### Criando a classe UserDTO com a anotação @Data

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-01-12h10m25s822.jpg" alt="" width="840">
</p>

A classe `UserDTO` é criada com os atributos `id`, `name` e `birthdate`, e recebe a anotação `@Data` do Lombok. Essa anotação é responsável por gerar automaticamente, em tempo de compilação, os métodos de acesso e outros métodos utilitários da classe.

```java
package br.com.dio.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UserDTO {

    private int id;
    private String name;
    private LocalDate birthdate;

}
```

#### Testando o autocomplete dos métodos gerados pelo Lombok

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-01-12h10m45s254.jpg" alt="" width="840">
</p>

Ao instanciar um `UserDTO` na classe `Main` e digitar `dto.set`, o autocomplete do IntelliJ já exibe os métodos `setId`, `setName` e `setBirthdate`, mesmo sem esses métodos terem sido escritos manualmente no código. Isso evidencia, na prática, o efeito da anotação `@Data`: os setters (assim como os getters) são gerados automaticamente pelo Lombok durante a compilação.

```java
var dto = new UserDTO();
dto.set...
```

#### Compilando o projeto e gerando a pasta target

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-01-12h11m13s701.jpg" alt="" width="840">
</p>

Após chamar `dto.setId(2);` na classe `Main`, o projeto é compilado através da tarefa **compile** do Maven, exibida no painel lateral. O resultado no console mostra `BUILD SUCCESS`, confirmando que a compilação foi concluída sem erros e que a pasta `target`, com os arquivos `.class` gerados, foi criada.

```java
public class Main {
    public static void main(String[] args) {
        var dto = new UserDTO();
        dto.setId(2);
    }
}
```

#### Inspecionando o bytecode decompilado de UserDTO (construtor e getters)

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-01-12h13m31s746.jpg" alt="" width="840">
</p>

Abrindo o arquivo `UserDTO.class` gerado dentro de `target/classes`, o IntelliJ decompila o bytecode e mostra o código Java equivalente ao que o Lombok gerou. É possível ver o construtor sem argumentos (`public UserDTO() {}`) e os métodos `getId()` e `getName()`, criados automaticamente a partir da anotação `@Data`, sem que o desenvolvedor tenha escrito essas linhas manualmente.

#### Setters gerados e início do método equals()

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-01-12h13m36s149.jpg" alt="" width="840">
</p>

Rolando o arquivo decompilado, aparecem também os métodos `getBirthdate()`, `setId()`, `setName()` e `setBirthdate()`, além do início da implementação do método `equals(Object o)`, que compara os atributos da classe para determinar a igualdade entre duas instâncias de `UserDTO`. Todos esses métodos foram gerados automaticamente pelo Lombok.

#### Continuação do método equals() gerado pelo Lombok

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-01-12h13m42s181.jpg" alt="" width="840">
</p>

O restante do método `equals()` decompilado mostra a lógica interna gerada pelo Lombok: primeiro é verificado se o objeto comparado é do mesmo tipo, depois se os valores de `id` são iguais, e em seguida os valores de `name` são comparados de forma segura contra `null`. Esse tipo de implementação, embora simples, é repetitivo de escrever manualmente — exatamente o tipo de verbosidade que o Lombok elimina.

#### canEqual(), hashCode() e toString() gerados pelo Lombok

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-01-12h13m45s751.jpg" alt="" width="840">
</p>

Por fim, o arquivo decompilado revela os métodos `canEqual()` (usado internamente pelo `equals()` para suportar herança com segurança), `hashCode()` (que combina os valores de `id`, `name` e `birthdate` em um único código hash) e `toString()` (que monta uma representação textual do objeto no formato `UserDTO(id=..., name=..., birthdate=...)`). Juntos, esses métodos — construtor, getters, setters, `equals`, `hashCode` e `toString` — resumem tudo o que a anotação `@Data` gera automaticamente para a classe.

#### Preenchendo os dados do UserDTO

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-01-12h15m15s014.jpg" alt="" width="840">
</p>

Na classe `Main`, o objeto `dto` tem seus atributos preenchidos utilizando os setters gerados pelo Lombok: o `id` recebe o valor `2`, o `name` recebe `"Maria"`, e o `birthdate` recebe uma data calculada a partir de `LocalDate.now().minusYears(30)`, ou seja, a data atual menos 30 anos.

```java
import br.com.dio.dto.UserDTO;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        var dto = new UserDTO();
        dto.setId(2);
        dto.setName("Maria");
        dto.setBirthdate(LocalDate.now().minusYears(30));
    }

}
```

#### Executando mvn compile pelo terminal

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-01-12h29m50s932.jpg" alt="" width="840">
</p>

O comando `mvn compile` é executado diretamente pelo terminal integrado, a partir do diretório raiz do projeto `maven-project`. A saída mostra as fases de `resources` e `compile` do ciclo de vida do Maven sendo executadas, finalizando com `BUILD SUCCESS`, o que confirma que o projeto compila corretamente com a configuração atual do `pom.xml`.

```bash
mvn compile
```
```
[INFO] --- resources:3.3.1:resources (default-resources) @ maven-project ---
[INFO] Copying 0 resource from src/main/resources to target/classes
[INFO] --- compiler:3.12.1:compile (default-compile) @ maven-project ---
[INFO] Nothing to compile - all classes are up to date.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

#### Recompilando o projeto após alterações no código-fonte

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-01-12h29m52s381.jpg" alt="" width="840">
</p>

Ao rodar novamente a tarefa **compile** do Maven, agora pelo painel de execução do IntelliJ, o log indica que o módulo foi recompilado por conta de mudanças no código-fonte, compilando os **3 arquivos-fonte** existentes até este ponto (`Main`, `UserDTO` e a nova classe de modelo). O resultado é novamente `BUILD SUCCESS`.

```
[INFO] --- compiler:3.12.1:compile (default-compile) @ maven-project ---
[INFO] Recompiling the module because of changed source code.
[INFO] Compiling 3 source files with javac [debug target 21] to target/classes
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

#### Criando UserModel com anotações individuais do Lombok

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-01-12h31m58s799.jpg" alt="" width="840">
</p>

Para demonstrar que a anotação `@Data` é, na prática, um resumo de várias anotações menores do Lombok, a classe `UserModel` é criada utilizando essas anotações de forma explícita e individual: `@Getter`, `@Setter`, `@EqualsAndHashCode`, `@NoArgsConstructor` e `@ToString`. O resultado da compilação é equivalente ao obtido anteriormente com `@Data` na classe `UserDTO`.

```java
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class UserModel {

    private int code;
    private String userName;
    private LocalDate birthday;

}
```


### 🟩 Vídeo 03 - Concluindo o projeto

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.03-curso.03-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/gerenciando-dependencias-com-maven-e-gradle/learning/e7fcb087-73a3-40e5-8e4d-5a9516441bd6?autoplay=1

## Parte 2 - Instalação e Configuração do Gradle

### 🟩 Vídeo 04 - Instalando Gradle

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.03-curso.03-video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/gerenciando-dependencias-com-maven-e-gradle/learning/b7d688f1-cd7a-4678-a786-6978c61eb002?autoplay=1

### 🟩 Vídeo 05 - Criando um projeto Gradle

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.03-curso.03-video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

## Parte 3 - Comparação e Migração entre Ferramentas

### 🟩 Vídeo 06 - Mas afinal porque usar o Maven ou Gradle

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.03-curso.03-video_06.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 07 - Migrando Projeto Maven para Gradle

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.03-curso.03-video_07.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 08 - Migrando projeto Gradle para Maven

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.03-curso.03-video_08.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

##  Materiais de Apoio

# Certificado: 

- Link na plataforma: 
- Certificado em pdf: