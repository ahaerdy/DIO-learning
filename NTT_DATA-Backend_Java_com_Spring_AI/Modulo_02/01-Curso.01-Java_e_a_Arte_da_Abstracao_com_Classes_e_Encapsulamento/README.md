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

> 🐛 **Bug na saída:** a primeira linha imprime a idade de `female` no lugar de `male`. Esse erro passou despercebido justamente porque os atributos são acessados diretamente, sem nenhuma camada de controle — evidenciando um problema estrutural de design.

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

      


## Parte 2 - Exercícios: Classes e Encapsulamento

### 🟩 Vídeo 03 - Exercícios

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.01-curso.05-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: 

##  Materiais de Apoio

# Certificado: 

- Link na plataforma: 
- Certificado em pdf: