## Instrutor

- José Luiz Abreu Cardoso Junior (Engenheiro de software sênior)
- Contato Linkedin: / [juniorjrjl](https://www.linkedin.com/in/juniorjrjl/)

## Parte 1 - Introdução às Annotations em Runtime

### 🟩 Vídeo 01 - Introdução a Annotations em runtime

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.03-curso.04-video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/annotations-em-java-marcando-o-seu-codigo-de-maneira-inteligente/learning/24ed8e31-d0c0-44d5-b28a-2629ec6c80a4?autoplay=1

### Anotações

O código abaixo mostra o arquivo `build.gradle` do projeto Serializer já ajustado para a aula. O bloco `plugins` habilita o plugin `java`, e são definidos o `group` (`br.com.dio`) e a `version` (`1.0-SNAPSHOT`) do projeto. Em `repositories`, o Maven Central é configurado como fonte de dependências. Por fim, o bloco `dependencies` adiciona a biblioteca Guava, que será utilizada mais adiante para auxiliar na formatação dos nomes de campos.

```groovy
plugins {
    id("java")
}

group = "br.com.dio"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.guava:guava:33.0.0-jre")
}
```

Aqui é apresentado o enum `FieldFormatEnum`, criado dentro do pacote `br.com.dio.annotation`. Cada constante do enum (`CAMEL_CASE`, `PASCAL_CASE`, `SNAKE_CASE`, `KEBAB_CASE`) recebe uma expressão lambda associada, do tipo `Function<String, String>`, responsável por converter um nome de campo para o padrão de escrita correspondente. Essas conversões utilizam o utilitário `CaseFormat` da biblioteca Guava, importado estaticamente no início do arquivo. O método `format(String field)` aplica a função definida na constante escolhida, retornando o campo já formatado.

```java
package br.com.dio.annotation;
import com.google.common.base.CaseFormat;
import java.util.function.Function;

import static com.google.common.base.CaseFormat.*;

public enum FieldFormatEnum {

    CAMEL_CASE(field -> field),
    PASCAL_CASE(field -> LOWER_CAMEL.to(UPPER_CAMEL, field)),
    SNAKE_CASE(field -> LOWER_CAMEL.to(LOWER_UNDERSCORE, field)),
    KEBAB_CASE(field -> LOWER_CAMEL.to(LOWER_HYPHEN, field));

    private final Function<String, String> format;

    FieldFormatEnum(final Function<String, String> format) {
        this.format = format;
    }

    public String format(String field) {
        return format.apply(field);
    }
}
```

O código seguinte traz a segunda anotação criada na aula, `SerializedMethod`, também no pacote `br.com.dio.annotation`. Ela utiliza `@Retention(RUNTIME)`, para que fique disponível em tempo de execução, e `@Target(METHOD)`, restringindo seu uso apenas a métodos. A anotação define uma propriedade `value` do tipo `String`, com valor padrão vazio (`""`), permitindo que o desenvolvedor informe um nome customizado para a propriedade que será gerada no JSON a partir do retorno de um método.

```java
package br.com.dio.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(METHOD)
public @interface SerializedMethod {
    String value() default "";
}
```

Abaixo é exibida a anotação principal do projeto, `SerializerType`. Assim como a anotação anterior, ela usa `@Retention(RUNTIME)`, mas com `@Target(TYPE)`, o que indica que só pode ser aplicada em classes, interfaces, enums ou records. Ela define duas propriedades opcionais: `fieldFormat`, do tipo `FieldFormatEnum`, com valor padrão `CAMEL_CASE`, indicando o padrão de formatação dos campos ao gerar o JSON; e `prettify`, um `boolean` com valor padrão `true`, que define se o JSON gerado será formatado (identado) ou não. Por terem valores padrão, essas propriedades não obrigam quem for usar a anotação a defini-las explicitamente.

```java
package br.com.dio.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static br.com.dio.annotation.FieldFormatEnum.CAMEL_CASE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(TYPE)
public @interface SerializerType {
    FieldFormatEnum fieldFormat() default CAMEL_CASE;

    boolean prettify() default true;
}
```

O código seguinte mostra a classe modelo `Person`, criada no pacote `br.com.dio.model`. A classe é anotada com `@SerializerType(fieldFormat = KEBAB_CASE, prettify = false)`, definindo explicitamente que o JSON gerado a partir dela usará o formato kebab-case e não será formatado (prettify desativado). A classe possui os atributos `id`, `name` e `age`, com construtores (vazio e completo) e os respectivos getters e setters. Ela também define o método `firstName()`, anotado com `@SerializedMethod("firstPersonName")`, que retorna apenas o primeiro nome extraído do campo `name` (usando `split(" ")[0]`) — esse método será serializado no JSON com o nome customizado `firstPersonName`, em vez do nome padrão do método.

```java
package br.com.dio.model;

import br.com.dio.annotation.SerializedMethod;
import br.com.dio.annotation.SerializerType;

import static br.com.dio.annotation.FieldFormatEnum.KEBAB_CASE;

@SerializerType(fieldFormat = KEBAB_CASE, prettify = false)
public class Person {

    private long id;

    private String name;

    private int age;

    public Person() {
    }

    public Person(final long id, final String name, final int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @SerializedMethod("firstPersonName")
    public String firstName() {
        return name.split(" ")[0];
    }
}
```

A seguir o segundo modelo do projeto, `User`, implementado como um `record` no pacote `br.com.dio.model`. Diferentemente da classe `Person`, aqui a anotação `@SerializerType` é usada sem nenhum parâmetro customizado, ou seja, assumirá os valores padrão definidos na anotação (formato camel-case e JSON formatado). O record possui os componentes `id` (long), `fullName` (String), `age` (int) e `salary` (double), que serão utilizados como exemplo de uma estrutura mais simples e totalmente baseada nas configurações padrão.

```java
package br.com.dio.model;

import br.com.dio.annotation.SerializerType;

@SerializerType
public record User(
        long id,
        String fullName,
        int age,
        double salary
) { }
```

Por fim, temos a classe `Main`, criada no pacote raiz `br.com.dio`, contendo apenas o método `main` vazio. Essa classe serve como ponto de entrada da aplicação, onde futuramente será feito o processamento das anotações criadas para gerar o JSON a partir dos modelos `Person` e `User`.

```java
package br.com.dio;

public class Main {
    public static void main(String[] args) {

    }
}
```

#### Fluxo

```mermaid
graph TD
    %% Bloco de Entrada e Inspeção
    subgraph Entrada [Início do Fluxo]
        A["<strong>Objeto Java anotado</strong>
        (Classe com <strong>@SerializerType</strong> 
        ou método com <strong>@SerializedMethod</strong>)"]
        B{"<strong>Motor de Reflexão</strong>
        (Inspeção dinâmica da 
        estrutura do objeto)"}
    end

    %% Bloco de Decisão (Leitura das Annotations)
    subgraph Processamento_de_Metadados [Análise de Metadados]
        C["<strong>Lê @SerializerType</strong>
        (Extrai regras de formato
        e identação da classe)"]
        D["Lê <strong>@SerializedMethod</strong>
        (Identifica métodos que 
        devem ser serializados)"]
    end

    %% Bloco de Ação (Aplicação das Regras)
    subgraph Aplicacao_de_Regras [Execução da Lógica]
        E["<strong>Aplica Formatação</strong>
        (Usa Guava para converter 
        campos conforme a regra)"]
        F["<strong>Customiza Serialização</strong>
        (Aplica nomes definidos no 
        value da anotação)"]
    end

    %% Bloco de Saída
    subgraph Saida [Resultado Final]
        G["<strong>Gera JSON Final</strong>
        (Montagem do arquivo estruturado 
        conforme as anotações)"]
    end

    %% Definição de Conexões
    A --> B
    B --> C
    B --> D
    C --> E
    D --> F
    E --> G
    F --> G

    %% Estilização para garantir fonte preta e legibilidade
    style A fill:#f9f,stroke:#333,color:#000
    style B fill:#fff4dd,stroke:#d4a017,color:#000
    style C fill:#d1e7dd,stroke:#0f5132,color:#000
    style D fill:#d1e7dd,stroke:#0f5132,color:#000
    style E fill:#cfe2f3,stroke:#084298,color:#000
    style F fill:#cfe2f3,stroke:#084298,color:#000
    style G fill:#bbf,stroke:#333,color:#000
```



### 🟩 Vídeo 02 - Explorando Annotations em runtime

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.03-curso.04-video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/annotations-em-java-marcando-o-seu-codigo-de-maneira-inteligente/learning/6866303d-13cb-4ebb-ae8d-742b920c96b2?autoplay=1




### 🟩 Vídeo 03 - Questionário: Introdução às Annotations em Runtime

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.03-curso.04-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/annotations-em-java-marcando-o-seu-codigo-de-maneira-inteligente/learning/5414e2f1-1151-48d1-a95f-0ff04dd0dee6?autoplay=1

## Parte 2 - Explorando Annotation Processors

### 🟩 Vídeo 04 - Introdução a Annotation Processor

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.03-curso.04-video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/annotations-em-java-marcando-o-seu-codigo-de-maneira-inteligente/learning/66dde68f-f31a-4937-9a97-cc7d005cb892?autoplay=1

### 🟩 Vídeo 05 - Explorando Annotation Processor

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.03-curso.04-video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: 

##  Materiais de Apoio

# Certificado: 

- Link na plataforma: 
- Certificado em pdf: