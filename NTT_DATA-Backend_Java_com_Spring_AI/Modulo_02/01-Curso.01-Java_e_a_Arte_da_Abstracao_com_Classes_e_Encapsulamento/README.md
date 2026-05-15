# Parte 1 — Java e a Arte da Abstração com Classes e Encapsulamento

## 🎬 Vídeo 01 — Criando a Primeira Classe

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.01-curso.05-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/java-e-a-arte-da-abstracao-com-classes-e-encapsulamento/learning/8367cdbe-ddde-4555-987a-5821f7e05e7f?autoplay=1)

O vídeo revisa os fundamentos da Programação Orientada a Objetos (POO) em Java, com foco na **abstração de conceitos do mundo real para o software**, garantindo segurança, organização e reuso de código.

### Anotações

#### Exemplo 1 — O que acontece a cada `new`?

O professor apresenta o código abaixo para ilustrar que cada `new` aloca um novo objeto na memória Heap:

```java
public class Main {
    public static void main(String[] args) {
        var scanner  = new Scanner(System.in);
        var scanner1 = new Scanner(System.in);
        var scanner2 = new Scanner(System.in);
    }
}
```

##### ⚠️ Problemas desse código

Embora a alocação de três objetos distintos na Heap esteja tecnicamente correta, o compartilhamento do mesmo recurso (`System.in`) gera três problemas sérios:

| Problema | Descrição |
|---|---|
| **Conflito de I/O** | Os três objetos disputam o controle do mesmo fluxo de entrada (`System.in`). |
| **Vazamento de memória** | `Scanner` é um recurso "pesado"; instâncias desnecessárias desperdiçam memória. |
| **Risco de exceção** | Fechar qualquer um dos scanners encerra `System.in` para todos os outros, causando erros em tempo de execução. |

##### ✅ A forma correta

A solução é separar o **mecanismo de leitura** (um único `Scanner`) dos **dados lidos** (variáveis independentes). O scanner deve ser único; os dados capturados é que podem ocupar espaços distintos na memória.

```java
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // 1. Um único mecanismo de leitura na memória Heap
        var leitor = new Scanner(System.in);

        // 2. Variáveis distintas armazenam cada dado capturado
        System.out.println("Digite um nome:");
        String nome = leitor.nextLine();

        System.out.println("Digite um ID:");
        String id = leitor.nextLine();

        System.out.println("Digite uma cidade:");
        String cidade = leitor.nextLine();

        // 3. Exibição dos dados armazenados
        System.out.println("\n--- Conteúdo Armazenado ---");
        System.out.println("Nome:   " + nome);
        System.out.println("ID:     " + id);
        System.out.println("Cidade: " + cidade);

        // 4. Boa prática: fechar o recurso ao final
        leitor.close();
    }
}
```

> 💡 **Conclusão:** o objeto `Scanner` é o *instrumento* de leitura; as variáveis `String` são os *recipientes* dos dados. Confundir os dois papéis é um erro de design comum em iniciantes.

---

#### Exemplo 2 — Criando e Usando uma Classe Própria

O professor cria a classe `Person` para demonstrar como modelar entidades do mundo real em Java.

##### Classe `Person` (versão do professor)

```java
public class Person {
    public String name;
    public int age;
}
```

##### Classe `Main` (versão do professor)

```java
public class Main {

    public static void main(String[] args) {
        var male = new Person();
        male.name = "João";
        male.age  = 12;

        var female = new Person();
        female.name = "Maria";
        female.age  = 10;

        System.out.println("Male name: "   + male.name   + " age: " + female.age); // ← bug!
        System.out.println("Female name: " + female.name + " age: " + female.age);
    }
}
```

##### Saída do programa

```
Male name: João age: 10
Female name: Maria age: 10
```

> 🔴 **Bug na saída:** a primeira linha imprime a idade de `female` no lugar de `male`. Esse erro passou despercebido justamente porque os atributos são acessados diretamente, sem nenhuma camada de controle — evidenciando um problema estrutural de design.

---

##### 🔍 Análise Técnica — Por Que Atributos `public` São um Problema?

O código acima viola um dos pilares fundamentais da POO: o **Encapsulamento**. Expor atributos com `public` apresenta quatro riscos principais:

**1. Falta de validação**
Qualquer parte do código pode inserir valores inválidos sem que a classe possa reagir:
```java
male.age  = -50;   // age negativo: inválido, mas aceito
male.name = null;  // null: pode causar NullPointerException
```

**2. Quebra do encapsulamento**
O mundo externo passa a conhecer *como* os dados são armazenados internamente, criando um acoplamento forte entre as classes. O ideal é que o exterior saiba apenas *o que* a classe faz, não *como* ela faz.

**3. Dificuldade de manutenção**
Se o atributo `age` precisar ser renomeado para `birthDate` no futuro, será necessário alterar manualmente todas as referências diretas espalhadas pelo projeto.

**4. Erros de lógica silenciosos**
Como demonstrado no exemplo, misturar referências (`male.name` com `female.age`) não gera erro de compilação — o bug só aparece na saída, tornando-o difícil de rastrear.

---

##### ✅ A Solução Correta — Encapsulamento com Getters e Setters

A correção é declarar os atributos como `private` e fornecer métodos públicos controlados de acesso (getters) e modificação (setters), com validações embutidas.

###### Classe `Person` refatorada

```java
public class Person {

    // Atributos privados: inacessíveis diretamente de fora da classe
    private String name;
    private int age;

    // --- Getter e Setter para 'name' ---
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name; // 'this.name' → atributo da classe
                              // 'name'      → parâmetro do método
        }
    }

    // --- Getter e Setter para 'age' ---
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 0) {       // Validação: idade não pode ser negativa
            this.age = age;
        }
    }
}
```

###### Classe `Main` refatorada

```java
public class Main {

    public static void main(String[] args) {

        // Objeto masculino
        var male = new Person();
        male.setName("João");
        male.setAge(12);

        // Objeto feminino
        var female = new Person();
        female.setName("Maria");
        female.setAge(10);

        // Impressão via getters — clara, sem risco de trocar referências
        System.out.println("Dados do masculino:");
        System.out.println("Nome: " + male.getName() + " | Idade: " + male.getAge());

        System.out.println("\nDados do feminino:");
        System.out.println("Nome: " + female.getName() + " | Idade: " + female.getAge());
    }
}
```

##### Saída correta

```
Dados do masculino:
Nome: João | Idade: 12

Dados do feminino:
Nome: Maria | Idade: 10
```

#### O que foi visto nesta aula

Ao final da aula, o aluno saiu com um entendimento prático de como:

- Modelar entidades do mundo real como classes Java;
- Compreender que cada new cria um objeto independente na memória;
- Aplicar encapsulamento com private e métodos de acesso;
- Usar this para resolver conflitos de escopo;
- Distinguir membros estáticos (da classe) de membros de instância;
- Usar construtores para controlar como um objeto nasce, tornando obrigatório o fornecimento de dados essenciais desde a criação.

### 🟩 Vídeo 02 - Trabalhando com Records

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.01-curso.05-video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/java-e-a-arte-da-abstracao-com-classes-e-encapsulamento/learning/64263a12-13af-4f4c-b2ec-cb4c27b04d99?autoplay=1

Este vídeo aprofunda o conceito de **Records** no Java, uma funcionalidade introduzida para simplificar a criação de classes que servem puramente como transportadoras de dados imutáveis.  

### Anotações

#### Tentativa de declarar campo de instância em um `record`

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-05-14-14h00m14s384.jpg" alt="" width="840">
</p>

O editor exibe o arquivo `Person.java` com a declaração de um `record`. Logo abaixo do cabeçalho `public record P...`, foi tentada a declaração de um campo de instância `private Str...`, o que gerou imediatamente o aviso **"Instance field is not allowed in record"**. O IntelliJ oferece a sugestão de transformar o campo em `static` como alternativa viável. No painel lateral, a documentação da classe `String` é exibida como contexto da IDE, sem relação direta com o erro.

> **Ponto-chave:** Em um `record`, **não é permitido declarar campos de instância no corpo da declaração**. A única forma aceita de definir atributos é na lista de parâmetros do cabeçalho do `record` — que também funciona como o seu construtor canônico.

```java
// Incorreto — gera erro de compilação em um record
public record Person {
    private String name; // ❌ Instance field is not allowed in record
}
```

#### Instanciando um `record` sem argumentos

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-05-14-14h00m37s090.jpg" alt="" width="840">
</p>

O arquivo `Main.java` mostra a criação de uma instância de `Person` sem qualquer argumento: `var person = new Person();`. Um ícone de aviso (⚠) aparece na linha, indicando que o `record` ainda não possui atributos definidos no cabeçalho — portanto o construtor gerado automaticamente também não exige argumentos neste momento. 

```java
public class Main {

    public static void main(String[] args) {
        var person = new Person(); // ⚠ record sem atributos declarados
    }

}
```

> Neste estágio, `Person` é um `record` vazio. A instanciação funciona, mas o objeto ainda não carrega nenhum dado.

#### Acesso a atributos privados e o getter gerado automaticamente

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-05-14-14h00m52s435.jpg" alt="" width="840">
</p>

O `record Person` já possui os atributos `String name` e `int age` declarados no cabeçalho. Em `Main.java`, a instância é criada com `new Person(name: "João", age: 12)` e impressa com `System.out.println(person)`. Na linha 6, a tentativa de acessar `person.name` (sem parênteses, como se fosse campo público) gerou o aviso **"'name' has private access in 'Person'"**, visível tanto no popup de sugestão quanto na barra de status inferior.

O console, referente a uma execução anterior, já exibiu a saída correta via `toString`:

```
Person[name=João]
```

> **Regra do `record`:** todos os atributos declarados no cabeçalho são automaticamente `private final`. O acesso de leitura se dá por meio de um **método getter gerado automaticamente**, cujo nome é idêntico ao do atributo — `name()` em vez do convencional `getName()`.

```java
// Incorreto
System.out.println(person.name);   // ❌ private access

// Correto
System.out.println(person.name()); // ✅ getter gerado pelo record
```

#### Usando o getter e criando uma segunda instância

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-05-14-14h01m29s594.jpg" alt="" width="840">
</p>

`Main.java` demonstra o uso correto do getter `person.name()` e a criação de um segundo objeto `newPerson` reaproveitando o nome do primeiro mas com idade diferente. O console confirma o comportamento esperado:

```
Person[name=João, age=12]
João
```

```java
public class Main {

    public static void main(String[] args) {
        var person = new Person(name: "João", age: 12);
        System.out.println(person);
        System.out.println(person.name()); // getter gerado automaticamente

        var newPerson = new Person(person.name(), age: 13); // nova instância independente
    }

}
```

> Como `record`s são **imutáveis**, não existe setter. Para "alterar" um valor, é necessário criar uma nova instância — exatamente como ilustrado com `newPerson`. Os dois objetos são independentes entre si.

#### Construtor compacto (*compact constructor*)

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-05-14-14h01m35s189.jpg" alt="" width="840">
</p>

`Person.java` exibe o `record` com um **construtor compacto** declarado explicitamente (`public Person{...}`). Dentro dele, quatro chamadas a `System.out.println` imprimem separadores e os valores de `name` e `age`. O console ao fundo mostra a saída produzida quando esse construtor foi executado durante a instanciação:

```
========
João
12
========end
Person[name=João, age=12]
João
```

```java
public record Person(String name, int age) {

    // Construtor compacto — executado após a atribuição automática dos campos
    public Person {
        System.out.println("========");
        System.out.println(name);
        System.out.println(age);
        System.out.println("========");
    }

}
```

> O construtor compacto **não recebe parâmetros explícitos** e **não atribui os campos** — essa atribuição já é feita automaticamente pelo `record` antes de entrar no bloco. Seu uso mais comum é a **validação de dados**: rejeitar valores nulos, verificar regras de negócio, etc.

#### Visualizando o fluxo completo de execução

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-05-14-14h01m43s185.jpg" alt="" width="840">
</p>

`Main.java` revisado, sem a linha `newPerson`, mostra o fluxo limpo de criação e leitura. O console exibe a ordem completa de execução, confirmando que o construtor compacto é chamado **durante a instanciação**, antes que qualquer `println` do `main` seja executado:

```
========
João
12
========end
Person[name=João, age=12]
João
```

```java
public class Main {

    public static void main(String[] args) {
        var person = new Person(name: "João", age: 12); // construtor compacto dispara aqui
        System.out.println(person);         // Person[name=João, age=12]
        System.out.println(person.name()); // João
    }

}
```

> A sequência do console deixa claro o ciclo de vida: primeiro o `record` atribui os campos, depois executa o construtor compacto, e só então o controle retorna ao `main`.

#### Construtor secundário e método personalizado no `record`

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-05-14-14h01m57s783.jpg" alt="" width="840">
</p>

`Person.java` apresenta três elementos adicionais do `record`:

1. **Construtor compacto vazio** (linhas 3–5) — declarado sem conteúdo, apenas para evidenciar sua existência.
2. **Construtor secundário** (linhas 7–9) — aceita apenas `String name` e delega ao construtor canônico via `this(name, age: 1)`, definindo `age` com valor padrão `1`.
3. **Método personalizado `getInfo()`** (linhas 11–13) — retorna uma `String` formatada com `name` e `age`.

```java
public record Person(String name, int age) {

    // Construtor compacto (vazio — sem lógica adicional)
    public Person {

    }

    // Construtor secundário — obrigatório delegar ao construtor canônico
    public Person(String name) {
        this(name, 1); // age recebe valor padrão
    }

    // Método personalizado
    public String getInfo() {
        return "Name: " + name + " age: " + age;
    }

}
```

> **Regra importante:** qualquer construtor secundário em um `record` **deve obrigatoriamente chamar o construtor canônico** via `this(...)`. Isso garante que todos os campos — que são `final` — recebam um valor antes de qualquer uso.      

#### Conclusão

O `record` não é um substituto da classe convencional — tem um propósito específico: **representar dados imutáveis de forma concisa**. Enquanto uma classe exige construtor, getters, `equals`, `hashCode` e `toString` escritos manualmente, o `record` gera tudo isso automaticamente a partir dos atributos declarados no cabeçalho.

A diferença central está na imutabilidade. Uma vez instanciado, os valores de um `record` não podem ser alterados — os campos são implicitamente `private final` e não existe setter. Essa não é uma limitação acidental, mas uma decisão de design: o objeto deve carregar sempre um estado consistente e previsível.

Um exemplo direto: imagine um sistema que consulta um CEP e recebe o endereço correspondente. Rua, bairro, cidade e estado são dados que vieram de fora e representam uma realidade já existente — não faz sentido alterá-los depois.

```java
public record Endereco(String rua, String bairro, String cidade, String estado) {}
```

Só isso. Nenhum boilerplate, nenhum setter indevido, e a intenção fica clara para qualquer um que leia o código: `Endereco` é um dado, não uma entidade com estado mutável. Esse é o cenário ideal para o `record` — respostas de APIs, DTOs, parâmetros agrupados — qualquer situação onde o objetivo é transportar valores, não transformá-los.

Introduzido experimentalmente no Java 14 e consolidado no **Java 17 (LTS)**, o `record` acompanha uma tendência já presente no Kotlin (`data class`) e no C# (`record type`): tratar dados como dados, com a linguagem garantindo isso em vez de depender da disciplina do desenvolvedor.


## Parte 2 - Exercícios: Classes e Encapsulamento

### 🟩 Vídeo 03 - Exercícios

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.01-curso.05-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/java-e-a-arte-da-abstracao-com-classes-e-encapsulamento/learning/cb67cd06-4a75-4378-b6b6-c9526185a882?autoplay=1

O vídeo apresenta uma série de exercícios de programação orientada a objetos (POO) para prática, com foco na aplicação de conceitos aprendidos. O palestrante detalha três exercícios e, em seguida, demonstra a resolução do terceiro, incluindo a criação de classes, métodos e um menu interativo.

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/2026-05-15-12-06-07.png" alt="" width="480">
</p>

Os exercícios encontram-se discriminados no link: https://github.com/digitalinnovationone/exercicios-java-basico/blob/main/exercicios/3%20-%20Java%20e%20a%20Arte%20da%20Abstra%C3%A7%C3%A3o%20com%20Classes%20e%20Encapsulamento.md

O exercício escolhido foi o terceiro, cujas especificações encontram-se abaixo disciminadas.

```javascript
3. Escreva um código onde temos o controle de banho de um petshop, a maquina de banhos dos pets deve ter as seguintes operações:
   - Dar banho no pet;
   - Abastecer com água;
   - Abastecer com shampoo;
   - verificar nivel de água;
   - verificar nivel de shampoo;
   - verificar se tem pet no banho;
   - colocar pet na maquina;
   - retirar pet da máquina;
   - limpar maquina.

Siga as seguintes regras para implementação

   - A maquina de banho deve permitir somente 1 pet por vez;
   - Cada banho realizado irá consumir 10 litros de água e 2 litros de shampoo;
   - A máquina tem capacidade máxima de 30 litros de água e 10 litros de shampoo;
   - Se o pet for retirado da maquina sem estar limpo será necessário limpar a máquina para permitir a entrada de outro pet;
   - A limpeza da máquina ira consumir 3 litros de água e 1 litro de shampoo;
   - O abastecimento de água e shampoo deve permitir 2 litros por vez que for acionado;
```


##  Materiais de Apoio

Os materiais complementares e de apoio que oferecemos têm como objetivo fornecer informações para facilitar e enriquecer a sua jornada de aprendizado no curso "Java e a Arte da Abstração com Classes e Encapsulamento". Aqui você encontrará links úteis, como slides, repositórios e páginas oficiais, além de dicas sobre como se destacar na DIO e no mercado de trabalho 😉

### Recursos Adicionais

Para ajudá-lo a aprofundar o conhecimento, disponibilizamos a seguir o material complementar contendo os conteúdos e links apresentados no curso:

* **Repositório:** https://github.com/digitalinnovationone/exercicios-java-basico

### Dicas e Links Úteis

Para se desenvolver ainda mais e se destacar na DIO e no mercado de trabalho, sugerimos os seguintes recursos:

* **Artigos e Fórum da DIO:** Compartilhe seus conhecimentos e dúvidas através dos artigos (visíveis globalmente na plataforma da DIO) e nos fóruns específicos para cada experiência educacional, como nossos Bootcamps.
* **Rooms:** Participe do *Rooms*, uma ferramenta de bate-papo em tempo real onde você pode interagir com outros participantes dos nossos Bootcamps, compartilhando dúvidas, dicas e snippets de código.
* **Exploração na Web:** Utilize motores de busca para aprofundar seu conhecimento sobre temas específicos. Páginas como o StackOverflow são recursos valiosos para encontrar soluções e expandir seu entendimento.

# Certificado: Java e a Arte da Abstração com Classes e Encapsulamento

- Link na plataforma: 
- Certificado em pdf: