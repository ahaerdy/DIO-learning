# Tutorial: Criando um Serializador de JSON com Anotações Customizadas em Java

## 1. Visão Geral

### 1.1. O que este projeto faz

Este projeto implementa, do zero, um pequeno **serializador de objetos para JSON**. Ou seja: dado um objeto Java qualquer (por exemplo, um `Person` ou um `User`), o código é capaz de transformá-lo automaticamente em uma string no formato JSON, como:

```json
{
"full_name":"Maria Silva",
"id":2,
"salary":3222.23,
"age":30
}
```

O que torna esse projeto interessante — e é o coração da aula — é que essa conversão **não é feita à mão** para cada classe. Em vez de escrever um método `toJson()` específico para `Person` e outro específico para `User`, o projeto usa dois recursos avançados do Java:

1. **Annotations (anotações)** — marcações especiais que colocamos no código para descrever *como* uma classe ou método deve se comportar.
2. **Reflection (reflexão)** — uma API do Java que permite que o próprio programa "olhe para si mesmo" em tempo de execução: descobrir quais campos uma classe tem, quais métodos ela possui, quais anotações estão presentes, e até ler ou invocar essas coisas dinamicamente, sem saber de antemão qual é a classe.

A combinação dessas duas ferramentas permite escrever **um único método genérico** (`serializer`) que funciona para *qualquer* classe, desde que ela esteja devidamente anotada.

### 1.2. Por que isso é útil?

Esse é exatamente o mesmo princípio usado por bibliotecas famosas como Jackson ou Gson (usadas no dia a dia para converter objetos Java em JSON e vice-versa). Elas usam anotações como `@JsonProperty` e reflection por baixo dos panos. Este projeto é uma versão simplificada, feita para fins didáticos, do que essas bibliotecas fazem "de verdade".

### 1.3. Conceitos que você vai aprender

Antes de entrar nos arquivos, vale explicar rapidamente cada conceito novo que aparece no projeto. Se você nunca viu isso, leia com calma — o resto do tutorial vai fazer muito mais sentido depois.

#### O que é uma Annotation (anotação)?

Uma anotação é uma marcação que começa com `@`, colocada acima de uma classe, método, campo, etc. Ela **não executa nenhuma lógica sozinha** — ela apenas anexa metadados (informações extras) àquele elemento do código. Exemplos que você já deve ter visto: `@Override`, `@Deprecated`.

O interessante é que também podemos **criar nossas próprias anotações**, como faremos aqui com `@SerializerType` e `@SerializedMethod`. Só criar a anotação não faz nada por si só — é preciso que **outro código** (no nosso caso, a classe `SerializerProcessor`) leia essa anotação via reflection e decida o que fazer com essa informação.

Toda anotação customizada é declarada com a palavra-chave `@interface` e normalmente tem duas outras anotações "meta" sobre ela:

- `@Retention(RUNTIME)`: diz que a anotação deve continuar disponível mesmo depois de compilada, para que possamos lê-la em tempo de execução via reflection. Sem isso, a anotação só existiria durante a compilação e não poderia ser consultada pelo nosso código.
- `@Target(...)`: diz **onde** essa anotação pode ser usada — em uma classe (`TYPE`), em um método (`METHOD`), em um campo, etc.

#### O que é Reflection (reflexão)?

Reflection é a API do Java (pacote `java.lang.reflect`) que permite inspecionar e manipular classes, métodos e campos **em tempo de execução**, mesmo sem conhecer a classe em tempo de compilação. Com ela é possível:

- Descobrir a classe de um objeto: `object.getClass()`
- Listar os campos de uma classe: `clazz.getDeclaredFields()`
- Listar os métodos de uma classe: `clazz.getMethods()`
- Ler o valor de um campo mesmo que ele seja `private`: `field.get(object)`
- Descobrir quais anotações estão presentes em uma classe, método ou campo: `getAnnotations()`
- Chamar (invocar) um método dinamicamente: `method.invoke(object)`

Reflection é poderosa, mas tem um custo: o código fica mais difícil de ler e tem uma perda de performance, já que o Java não consegue otimizar tanto código que só é decidido em tempo de execução. Por isso ela costuma ser usada em bibliotecas e frameworks (como este projeto), e não no dia a dia comum de escrever regras de negócio.

#### Enum com comportamento (Function)

Você provavelmente já viu enums simples, como:

```java
enum Cor { VERMELHO, VERDE, AZUL }
```

Neste projeto, o enum `FieldFormatEnum` vai um passo além: cada constante do enum carrega **um comportamento** (uma função) associado a ela, usando a interface funcional `Function<String, String>`. Isso permite que cada valor do enum "saiba" como transformar uma string — funcionalidade que será explicada em detalhes na seção do arquivo `FieldFormatEnum`.

#### Streams e expressões lambda

O projeto usa bastante a API de **Streams** (`java.util.stream.Stream`), introduzida no Java 8, que permite processar coleções de dados de forma declarativa (dizendo *o que* queremos, e não *como* fazer passo a passo), encadeando operações como `filter`, `map`, `flatMap` e `collect`. Junto dela aparecem **expressões lambda** (como `field -> field`), que são uma forma compacta de escrever pequenas funções sem precisar criar uma classe separada. Vamos explicar cada uso conforme ele aparece no código.

### 1.4. Estrutura de arquivos do projeto

```
br.com.dio
├── annotation
│   ├── FieldFormatEnum.java     → enum com os formatos de nomenclatura (camelCase, snake_case, etc.)
│   ├── SerializedMethod.java    → anotação usada em métodos
│   └── SerializerType.java      → anotação usada em classes
├── model
│   ├── Person.java              → classe de exemplo anotada com @SerializerType
│   └── User.java                → record de exemplo anotado com @SerializerType
├── processor
│   └── SerializerProcessor.java → classe que faz o trabalho pesado: lê as anotações e gera o JSON
└── Main.java                    → classe com o método main, usada para testar tudo
```

Vamos entender cada um, na ordem que faz mais sentido para o aprendizado: primeiro as anotações, depois os modelos que as usam, depois o processador que interpreta tudo, e por fim o teste.

---

## 2. As Anotações

### 2.1. `FieldFormatEnum.java`

```java
package br.com.dio.annotation;
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

    public Function<String, String> getFormat() { return format; }
}
```

**Para que serve:** definir quais estilos de nomenclatura de campo o serializador é capaz de gerar, e como converter um nome de campo Java (que normalmente está em `camelCase`, como `fullName`) para cada um desses estilos.

**Linha a linha:**

- `import static com.google.common.base.CaseFormat.*;` — importa estaticamente a classe utilitária `CaseFormat`, da biblioteca **Guava** (do Google), que já sabe converter strings entre convenções de nomenclatura (`LOWER_CAMEL`, `UPPER_CAMEL`, `LOWER_UNDERSCORE`, `LOWER_HYPHEN`, etc.). O `import static` permite usar `LOWER_CAMEL` diretamente no código, sem precisar escrever `CaseFormat.LOWER_CAMEL`.
- `public enum FieldFormatEnum {` — declara um enum público chamado `FieldFormatEnum`. Um enum é um tipo especial que representa um conjunto fixo de constantes.
- `CAMEL_CASE(field -> field),` — esta é a primeira constante do enum, chamada `CAMEL_CASE`. Repare que ela recebe um **argumento entre parênteses**: `field -> field`. Isso é uma expressão lambda que representa uma função que recebe uma string (`field`) e devolve ela mesma sem alterações — afinal, camelCase já é o formato padrão dos nomes de campo em Java, então não precisa de conversão.
- `PASCAL_CASE(field -> LOWER_CAMEL.to(UPPER_CAMEL, field)),` — a constante `PASCAL_CASE` recebe uma função que converte a string do formato `LOWER_CAMEL` para `UPPER_CAMEL` (PascalCase, com a primeira letra maiúscula), usando o utilitário do Guava.
- `SNAKE_CASE(...)` e `KEBAB_CASE(...)` seguem a mesma lógica, convertendo para `LOWER_UNDERSCORE` (`snake_case`, com underscore separando as palavras) e `LOWER_HYPHEN` (`kebab-case`, com hífen), respectivamente.
- `private final Function<String, String> format;` — cada constante do enum vai guardar, em um campo interno chamado `format`, a função que foi passada no seu construtor. `Function<String, String>` é uma interface funcional do Java que representa "uma função que recebe uma String e devolve uma String".
- `FieldFormatEnum(final Function<String, String> format) { this.format = format; }` — este é o **construtor do enum**. Todo enum pode ter um construtor, que é chamado automaticamente uma vez para cada constante declarada acima. Aqui, ele apenas guarda a função recebida no campo `format`.
- `public Function<String, String> getFormat() { return format; }` — um getter simples, que permite a qualquer código externo (no nosso caso, o `SerializerProcessor`) obter a função de formatação associada à constante escolhida e aplicá-la a uma string.

**Resumindo em uma frase:** este enum é uma "caixa de ferramentas" com quatro formatos de nomenclatura prontos, cada um sabendo, sozinho, como transformar `fullName` no formato correspondente (`FullName`, `full_name` ou `full-name`).

### 2.2. `SerializedMethod.java`

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

**Para que serve:** marcar métodos cujo **resultado** deve entrar no JSON gerado, além dos campos comuns da classe. É útil para incluir valores calculados (que não são um campo direto), como um `firstName()` que extrai o primeiro nome de um campo completo.

**Linha a linha:**

- `@Retention(RUNTIME)` — como explicado na seção de conceitos, garante que essa anotação continue visível depois da compilação, para que o `SerializerProcessor` consiga lê-la em tempo de execução via reflection.
- `@Target(METHOD)` — restringe o uso desta anotação apenas a métodos. Se você tentar colocar `@SerializedMethod` em uma classe ou em um campo, o compilador vai acusar erro.
- `public @interface SerializedMethod {` — a palavra-chave `@interface` (e não apenas `interface`) é o que diz ao Java "isto é a definição de uma anotação", e não uma interface comum.
- `String value() default "";` — toda anotação pode ter "atributos", que se parecem com métodos sem corpo. Aqui, `value` é um atributo do tipo `String`, com valor padrão de string vazia (`""`). O nome `value` é especial: quando um atributo se chama `value`, ele pode ser preenchido sem precisar escrever `value = "..."`, bastando escrever `@SerializedMethod("algumNome")` diretamente. Esse valor será usado como o **nome customizado** da propriedade no JSON; se ficar vazio, o processador usará o nome padrão do método.

### 2.3. `SerializerType.java`

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

**Para que serve:** marcar uma **classe** (ou record) inteira como "serializável", informando ao `SerializerProcessor` como ela deve se comportar: qual formato de nome de campo usar e se o JSON final deve ficar formatado (com indentação) ou compactado.

**Linha a linha:**

- `@Target(TYPE)` — diferente de `SerializedMethod`, aqui o alvo é `TYPE`, que significa "classes, interfaces, enums ou records" — ou seja, esta anotação vai no topo da declaração da classe, e não em métodos.
- `FieldFormatEnum fieldFormat() default CAMEL_CASE;` — este atributo determina qual constante de `FieldFormatEnum` (visto na seção 2.1) será usada para formatar os nomes dos campos daquela classe. Se nada for informado, o padrão é `CAMEL_CASE`.
- `boolean prettify() default true;` — este atributo controla se o JSON de saída será "bonito" (com quebras de linha e indentação) ou compactado em uma única linha. O padrão é `true` (formatado).

Repare que, juntas, `SerializerType` e `SerializedMethod` funcionam como uma pequena "linguagem de configuração": uma classe usa `@SerializerType` para dizer *como* ela quer ser serializada de forma geral, e pode opcionalmente usar `@SerializedMethod` em métodos específicos para incluir dados extras no resultado.

---

## 3. Os Modelos (classes de exemplo)

Agora que as anotações existem, veremos como elas são **usadas** em duas classes de exemplo diferentes, para testar formatos distintos.

### 3.1. `Person.java`

```java
package br.com.dio.model;

import br.com.dio.annotation.SerializedMethod;
import br.com.dio.annotation.SerializerType;

import static br.com.dio.annotation.FieldFormatEnum.PASCAL_CASE;

@SerializerType(fieldFormat = PASCAL_CASE, prettify = false)
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

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    @SerializedMethod("firstPersonName")
    public String firstName() {
        return name.split(" ")[0];
    }
}
```

**Para que serve:** é a primeira classe de teste do serializador, uma classe Java tradicional (com construtor, getters e setters).

**Linha a linha (partes relevantes ao tema da aula):**

- `@SerializerType(fieldFormat = PASCAL_CASE, prettify = false)` — esta é a aplicação prática da anotação vista na seção 2.3. Aqui estamos dizendo: "quando `Person` for serializada, formate os nomes dos campos em PascalCase (ex.: `Name`, não `name`) e **não** deixe o JSON formatado — gere tudo em uma linha só".
- `private long id; private String name; private int age;` — são os três campos comuns da classe. Serão descobertos automaticamente pelo `SerializerProcessor` via reflection (`getDeclaredFields()`), sem que precisemos escrever manualmente "coloque o id no JSON", "coloque o name no JSON", etc.
- Construtores, getters e setters — código padrão de qualquer classe Java, sem relação direta com anotações. Servem apenas para criar e manipular objetos `Person` normalmente.
- `@SerializedMethod("firstPersonName") public String firstName() { return name.split(" ")[0]; }` — aqui está o uso prático da segunda anotação (seção 2.2). O método `firstName()` **não é um campo** da classe — é um método calculado, que separa o campo `name` por espaços (`split(" ")`) e retorna apenas a primeira posição do array (`[0]`), ou seja, o primeiro nome. Como ele está anotado com `@SerializedMethod("firstPersonName")`, o resultado dessa chamada será incluído no JSON final sob a chave `firstPersonName` — mesmo esse valor não existindo como campo dentro da classe.

### 3.2. `User.java`

```java
package br.com.dio.model;

import br.com.dio.annotation.SerializerType;
import static br.com.dio.annotation.FieldFormatEnum.SNAKE_CASE;

@SerializerType(fieldFormat = SNAKE_CASE)

public record User(
        long id,
        String fullName,
        int age,
        double salary
) { }
```

**Para que serve:** é a segunda classe de teste, usada para validar o serializador com um formato de nomenclatura diferente (`snake_case`) e com um tipo de declaração diferente: **record**.

**Conceito novo — o que é um `record`?** Um record é uma forma resumida de declarar uma classe **imutável**, que só serve para carregar dados. Ao escrever `public record User(long id, String fullName, int age, double salary) { }`, o Java gera automaticamente, nos bastidores, os campos privados `id`, `fullName`, `age` e `salary`, um construtor que recebe os quatro valores, métodos de acesso (`id()`, `fullName()`, etc.) e implementações de `equals`, `hashCode` e `toString`. Ou seja, é uma forma muito mais curta de escrever uma classe que só teria campos, construtor e getters, como o `Person`.

**Linha a linha:**

- `@SerializerType(fieldFormat = SNAKE_CASE)` — aqui usamos o valor padrão de `prettify` (que é `true`, formatado), mas trocamos o formato de nomenclatura para `SNAKE_CASE`. Isso significa que, ao serializar um `User`, o campo `fullName` deve virar `full_name` no JSON.
- `long id, String fullName, int age, double salary` — os quatro componentes do record, que se tornam os "campos" que o `SerializerProcessor` vai encontrar via reflection, exatamente como fez com os campos de `Person`.

Repare que `User` não usa `@SerializedMethod` — ele serve para provar que o serializador funciona mesmo sem métodos anotados, usando apenas os campos comuns da classe.

---

## 4. O Processador — `SerializerProcessor.java`

Esta é a classe mais importante do projeto: é ela que **lê** as anotações de `Person` e `User` (usando reflection) e realmente monta a string JSON. Vamos analisar o método `serializer` em blocos, na ordem em que o código executa.

```java
package br.com.dio.processor;

import br.com.dio.annotation.SerializedMethod;
import br.com.dio.annotation.SerializerType;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class SerializerProcessor {

    public String serializer(final Object object) throws IllegalAccessException, InvocationTargetException {
```

- A assinatura do método recebe um parâmetro do tipo `Object`, a superclasse de todos os tipos em Java. Isso é o que permite que o método `serializer` funcione tanto para `Person` quanto para `User` — ou para qualquer outra classe anotada que venha a existir no futuro — sem precisar saber, em tempo de compilação, qual classe específica está sendo passada.
- `throws IllegalAccessException, InvocationTargetException` — são exceções verificadas exigidas pela API de reflection: `IllegalAccessException` pode ocorrer ao tentar ler um campo/método sem permissão, e `InvocationTargetException` pode ocorrer ao invocar um método via reflection que, internamente, lance alguma exceção.

### 4.1. Validação e obtenção da classe

```java
        Objects.requireNonNull(object, "Enter with non null object");

        var clazz = object.getClass();
```

- `Objects.requireNonNull(object, "Enter with non null object");` — é uma verificação defensiva padrão: se `object` for `null`, o programa lança imediatamente uma `NullPointerException` com a mensagem informada, em vez de falhar mais adiante de forma confusa.
- `var clazz = object.getClass();` — obtém, em tempo de execução, a classe real do objeto recebido (por exemplo, `Person.class` ou `User.class`). `getClass()` é o ponto de partida de praticamente toda operação de reflection: a partir dele conseguimos os campos, métodos e anotações da classe. A palavra-chave `var` apenas deixa o Java inferir o tipo automaticamente (aqui, `Class<?>`), evitando repetir o nome do tipo.

### 4.2. Buscando a anotação `@SerializerType` da classe

```java
        var typeAnnotation = Stream.of(clazz.getAnnotations())
                .flatMap(a -> (a instanceof SerializerType s) ? Stream.of(s) : Stream.empty())
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        "For serialize object annotate it with @SerializerType"));
```

Este é um dos trechos mais avançados do arquivo, então vamos por partes:

- `clazz.getAnnotations()` — retorna um array com **todas** as anotações presentes na classe (poderia haver mais de uma, além de `@SerializerType`).
- `Stream.of(clazz.getAnnotations())` — transforma esse array em uma `Stream`, permitindo processá-lo de forma declarativa.
- `.flatMap(a -> (a instanceof SerializerType s) ? Stream.of(s) : Stream.empty())` — para cada anotação `a` encontrada, verificamos se ela é uma instância de `SerializerType` usando `instanceof SerializerType s` (uma forma moderna do Java que já declara a variável `s` do tipo correto, caso o teste seja verdadeiro). Se for, devolvemos uma stream contendo apenas essa anotação já convertida para `SerializerType` (`Stream.of(s)`); se não for, devolvemos uma stream vazia (`Stream.empty()`). O `flatMap` "achata" o resultado, juntando essas mini-streams em uma única stream final. O efeito prático é: dentre todas as anotações da classe, ficamos apenas com a que é do tipo `SerializerType`, já no tipo certo (sem precisar de um cast manual como `(SerializerType) a`).
- `.findFirst()` — pega a primeira (e única, no nosso caso) anotação do tipo `SerializerType` encontrada, envolvida em um `Optional<SerializerType>`.
- `.orElseThrow(() -> new NoSuchElementException("For serialize object annotate it with @SerializerType"));` — se nenhuma anotação `SerializerType` for encontrada (ou seja, se a classe não tiver sido anotada), o código lança uma exceção explicando o problema, em vez de continuar e falhar de forma confusa mais adiante.

**Resultado:** a variável `typeAnnotation` guarda, com o tipo correto `SerializerType`, a configuração definida na anotação da classe (por exemplo, `fieldFormat = PASCAL_CASE, prettify = false`, no caso de `Person`).

### 4.3. Extraindo as configurações da anotação

```java
        var fieldNameFormatter = typeAnnotation.fieldFormat().getFormat();
        var prettify = typeAnnotation.prettify();
```

- `typeAnnotation.fieldFormat()` — chama o atributo `fieldFormat` da anotação (visto na seção 2.3), retornando a constante do enum `FieldFormatEnum` escolhida (por exemplo, `PASCAL_CASE`).
- `.getFormat()` — em seguida, chama o método do enum (visto na seção 2.1) que devolve a `Function<String, String>` correspondente, guardada na variável `fieldNameFormatter`. É essa função que, mais adiante, será aplicada a cada nome de campo para convertê-lo para o formato escolhido.
- `typeAnnotation.prettify()` — obtém diretamente o valor booleano configurado na anotação, guardado na variável `prettify`, que será usado no final do método para decidir se o JSON sai formatado ou compactado.

### 4.4. Lendo os campos da classe via reflection

```java
        Map<String, Object> elements = new HashMap<>();
        for (var field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            elements.put(field.getName(), field.get(object));
        }
```

- `Map<String, Object> elements = new HashMap<>();` — cria um mapa (uma estrutura de chave-valor) onde cada entrada será "nome do campo" → "valor do campo". Esse mapa é o que, ao final, vira o JSON.
- `clazz.getDeclaredFields()` — retorna um array com todos os campos declarados na classe (incluindo os `private`), representados por objetos do tipo `Field`, também parte da API de reflection.
- `for (var field : clazz.getDeclaredFields())` — percorre cada um desses campos, um de cada vez.
- `field.setAccessible(true);` — por padrão, a reflection respeita os modificadores de acesso (como `private`). Esta linha "destrava" o acesso àquele campo específico, permitindo lê-lo mesmo sendo privado.
- `elements.put(field.getName(), field.get(object));` — `field.getName()` retorna o nome do campo como string (ex.: `"name"`), e `field.get(object)` lê o valor daquele campo **no objeto específico** que foi passado como parâmetro (ex.: o `name` do `Person` que estamos serializando agora). Os dois são adicionados ao mapa `elements`.

Ao final deste laço, o mapa `elements` contém todos os campos do objeto e seus respectivos valores — por exemplo, para o `Person("João da Silva", 1, 26)`: `{"id": 1, "name": "João da Silva", "age": 26}` (ainda como um `Map` Java, não como JSON de fato).

### 4.5. Lendo os métodos anotados com `@SerializedMethod`

```java
        var annotatedMethods = Stream.of(object.getClass().getMethods())
                .filter(m -> Stream.of(m.getAnnotations())
                        .anyMatch(a -> a.annotationType().equals(SerializedMethod.class)))
                .toList();

        for (var method : annotatedMethods) {
            method.setAccessible(true);
            String customName = method.getAnnotation(SerializedMethod.class).value();
            elements.put(customName.isBlank() ? method.getName() : customName, method.invoke(object));
        }
```

- `object.getClass().getMethods()` — diferente de `getDeclaredFields()`, aqui usamos `getMethods()`, que retorna todos os métodos **públicos** da classe (incluindo os herdados). Cada um é representado por um objeto `Method`.
- `.filter(m -> Stream.of(m.getAnnotations()).anyMatch(a -> a.annotationType().equals(SerializedMethod.class)))` — filtramos essa lista de métodos, mantendo apenas aqueles que possuem, entre suas anotações, uma do tipo `SerializedMethod`. Para isso, para cada método `m`, olhamos suas anotações (`m.getAnnotations()`) e verificamos se **existe alguma** (`anyMatch`) cujo tipo (`annotationType()`) seja igual a `SerializedMethod.class`.
- `.toList();` — converte o resultado filtrado em uma lista (`List<Method>`), guardada em `annotatedMethods`. No exemplo de `Person`, essa lista conteria apenas o método `firstName()`.
- `for (var method : annotatedMethods) {` — percorre cada método anotado encontrado.
- `method.setAccessible(true);` — assim como fizemos com os campos, liberamos o acesso ao método (por segurança/consistência, embora aqui ele já fosse público).
- `String customName = method.getAnnotation(SerializedMethod.class).value();` — busca a anotação `SerializedMethod` presente naquele método específico e lê seu atributo `value` (o nome customizado, se houver, ex.: `"firstPersonName"`).
- `elements.put(customName.isBlank() ? method.getName() : customName, method.invoke(object));` — aqui há uma decisão: se `customName` estiver em branco (`isBlank()`, ou seja, vazio ou só espaços), usamos o nome padrão do método (`method.getName()`, ex.: `"firstName"`); caso contrário, usamos o nome customizado (`"firstPersonName"`). Como **valor**, chamamos `method.invoke(object)`, que executa aquele método sobre o objeto recebido e retorna o resultado — no nosso exemplo, o método `firstName()` é de fato executado agora, e seu retorno (`"João"`) é o que entra no mapa.

Depois deste bloco, o mapa `elements` também vai conter, além dos campos comuns, o resultado dos métodos anotados — por exemplo: `{"id": 1, "name": "João da Silva", "age": 26, "firstPersonName": "João"}`.

### 4.6. Montando os campos do JSON

```java
        var jsonFields = elements.entrySet().stream()
                .map(e -> String.format(
                        "\"%s\":%s",
                        fieldNameFormatter.apply(e.getKey()),
                        formatValue(e.getValue())
                ))
                .collect(joining(String.format(",%s", System.lineSeparator())));
```

- `elements.entrySet()` — transforma o mapa em um conjunto de "entradas" (pares chave-valor), permitindo iterar por chave e valor ao mesmo tempo.
- `.stream()` — cria uma stream a partir desse conjunto de entradas, para poder aplicar `map` e `collect` a seguir.
- `.map(e -> ...)` — para cada entrada `e` (um par chave/valor), montamos a string que representa aquele campo no JSON, no formato `"chave":valor`.
  - `String.format("\"%s\":%s", ..., ...)` — monta a string, colocando o nome da chave entre aspas duplas (obrigatório em JSON) e, na sequência, o valor já formatado.
  - `fieldNameFormatter.apply(e.getKey())` — aplica a função de formatação de nome (obtida na seção 4.3, a partir da anotação) sobre o nome original do campo (`e.getKey()`). É aqui que `name` vira `Name` (PascalCase) ou `full_name` vira o próprio `full_name` (já estava em camelCase como `fullName`, e é convertido para snake_case).
  - `formatValue(e.getValue())` — chama um método auxiliar (definido logo abaixo, seção 4.9) que formata corretamente o **valor** do campo, adicionando aspas se for uma string, ou apenas convertendo para texto se for número/booleano.
- `.collect(joining(String.format(",%s", System.lineSeparator())));` — depois de mapear todas as entradas para o formato `"chave":valor`, juntamos tudo em uma única string, separando cada campo por vírgula seguida de uma quebra de linha (`System.lineSeparator()`, que se adapta ao sistema operacional — usar isso em vez de escrever `"\n"` na mão é uma boa prática, já que o separador de linha pode variar entre Windows, Linux e macOS).

Ao final, `jsonFields` contém algo como:

```
"Id":1,
"Name":"João da Silva",
"Age":26,
"FirstPersonName":"João"
```

(ainda sem as chaves `{ }` externas do JSON, que virão no próximo passo).

### 4.7. Montando o JSON final

```java
        var json = String.format(
                "{%s%s%s}",
                System.lineSeparator(),
                jsonFields,
                System.lineSeparator()
        );
```

- Aqui simplesmente envolvemos o conteúdo já pronto (`jsonFields`) entre chaves `{` e `}`, adicionando uma quebra de linha logo após a chave de abertura e outra antes da chave de fechamento, para deixar o resultado legível quando formatado.

### 4.8. Aplicando (ou não) a formatação bonita — `prettify`

```java
        return prettify ?
                json :
                json.replaceAll(System.lineSeparator(), "")
                        .replaceAll("    ", "");
    }
```

- Esta é uma expressão condicional (operador ternário: `condição ? valorSeVerdadeiro : valorSeFalso`).
- Se `prettify` for `true` (o padrão, usado por `User`), o método simplesmente retorna o `json` como está, com quebras de linha.
- Se `prettify` for `false` (como configurado em `Person`), o código remove todas as quebras de linha (`replaceAll(System.lineSeparator(), "")`) e também remove blocos de 4 espaços em branco (`replaceAll("    ", "")`), produzindo um JSON compactado em uma única linha.

> **Observação importante para quem está aprendendo:** essa abordagem de "remover 4 espaços" é uma solução simples, mas tem uma limitação: se algum valor de texto contiver, por coincidência, 4 espaços seguidos, eles também seriam removidos indevidamente. Uma versão mais robusta do código provavelmente controlaria a indentação de forma estrutural (por exemplo, montando o JSON já sem indentação quando `prettify` for falso), em vez de gerar o texto formatado e depois "consertá-lo" com substituições de texto.

### 4.9. Método auxiliar `formatValue`

```java
    private String formatValue(final Object value) {
        return value instanceof String s ?
                String.format("\"%s\"", s) :
                value.toString();
    }
}
```

- Este é um método privado (só pode ser chamado de dentro da própria classe `SerializerProcessor`), usado no passo 4.6 para decidir como escrever cada valor no JSON.
- `value instanceof String s` — verifica se o valor recebido é uma `String`. Se for, a variável `s` já fica disponível, tipada como `String`, dentro do próprio teste condicional.
- Se for uma string, o método devolve o valor entre aspas duplas (`String.format("\"%s\"", s)`), pois no JSON textos sempre ficam entre aspas.
- Se não for uma string (ou seja, for um número como `int`/`long`/`double`, ou um booleano), o método apenas chama `value.toString()`, já que números e booleanos **não** devem ter aspas em JSON.

---

## 5. Testando tudo — `Main.java`

```java
package br.com.dio;

import br.com.dio.model.Person;
import br.com.dio.model.User;
import br.com.dio.processor.SerializerProcessor;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        var processor = new SerializerProcessor();
        System.out.println(processor.serializer(new Person(1, "João da Silva", 26)));
        System.out.println(processor.serializer(new User(2, "Maria Silva", 30, 3222.23)));
    }
}
```

- `var processor = new SerializerProcessor();` — cria uma instância do processador visto na seção 4.
- `new Person(1, "João da Silva", 26)` — cria um objeto `Person`, que está anotado com `@SerializerType(fieldFormat = PASCAL_CASE, prettify = false)`.
- `processor.serializer(...)` — chama o método genérico de serialização (seção 4) passando o `Person` recém-criado. Internamente, todo o processo descrito nas seções 4.1 a 4.9 é executado, resultando em uma string JSON compactada e em PascalCase.
- `System.out.println(...)` — imprime o JSON gerado no console.
- O mesmo processo se repete para `new User(2, "Maria Silva", 30, 3222.23)`, que está anotado com `@SerializerType(fieldFormat = SNAKE_CASE)` (com `prettify` no padrão, verdadeiro), gerando dessa vez um JSON formatado (com quebras de linha) e com os nomes em snake_case.
- `throws InvocationTargetException, IllegalAccessException` — como o método `serializer` pode lançar essas exceções (por conta das chamadas de reflection), e o `main` não faz nenhum tratamento especial para elas, ele apenas as declara em sua assinatura, deixando que, se algo der errado, o programa termine mostrando o erro no console — o que é aceitável aqui, já que o foco é aprender o funcionamento das anotações, e não o tratamento de erros.

### 5.1. O resultado observado

```
{"Name":"João da Silva","Id":1,"FirstPersonName":"João","Age":26}
{
"full_name":"Maria Silva",
"id":2,
"salary":3222.23,
"age":30
}
```

Comparando os dois resultados, dá para confirmar exatamente o que cada anotação configurou:

- **`Person`**: uma única linha (efeito de `prettify = false`), com as chaves em PascalCase (`Name`, `Id`, `FirstPersonName`, `Age`) — e repare que `FirstPersonName` não é um campo da classe, e sim o valor retornado pelo método anotado com `@SerializedMethod`.
- **`User`**: várias linhas com indentação (efeito do `prettify` padrão, verdadeiro), com as chaves em snake_case (`full_name`, `id`, `salary`, `age`).

Isso comprova, na prática, que o **mesmo método `serializer`**, sem nenhuma alteração de código para cada classe, conseguiu gerar dois JSONs com formatações completamente diferentes — apenas porque cada classe foi anotada de um jeito diferente. Essa é a essência do poder de anotações combinadas com reflection: comportamento configurável por metadados, e não por código duplicado.

---

## 6. Recapitulando o fluxo completo

Para fechar o entendimento, aqui está o caminho percorrido por uma chamada `processor.serializer(new Person(...))`, do início ao fim:

1. O objeto `Person` é validado (não pode ser nulo).
2. A classe do objeto é obtida via `getClass()`.
3. A anotação `@SerializerType` da classe é localizada entre todas as anotações presentes, usando `instanceof` e `flatMap`.
4. O formato de nome de campo (`fieldFormat`) e a opção de formatação (`prettify`) são extraídos dessa anotação.
5. Todos os campos declarados da classe (`id`, `name`, `age`) são lidos via reflection e colocados em um mapa.
6. Todos os métodos anotados com `@SerializedMethod` (no caso, `firstName()`) são localizados, executados via `invoke`, e seus resultados também são adicionados ao mapa, usando o nome customizado quando definido.
7. Cada entrada do mapa é convertida em um trecho de texto `"chave":valor`, aplicando o formato de nomenclatura escolhido nas chaves e formatando corretamente os valores (com ou sem aspas).
8. Esses trechos são unidos e envolvidos por chaves `{ }`, formando o JSON completo.
9. Se `prettify` for falso, o JSON é compactado, removendo quebras de linha e indentação.
10. A string JSON final é retornada e impressa no console.

Com este fluxo em mente, você já é capaz de olhar para qualquer uma das anotações ou para o `SerializerProcessor` e entender exatamente qual papel cada trecho de código cumpre no processo geral — e, mais importante, entender a ideia central por trás de anotações customizadas combinadas com reflection: **descrever comportamento por metadados, e deixar que um único trecho de código genérico interprete e execute esse comportamento em tempo de execução.**
