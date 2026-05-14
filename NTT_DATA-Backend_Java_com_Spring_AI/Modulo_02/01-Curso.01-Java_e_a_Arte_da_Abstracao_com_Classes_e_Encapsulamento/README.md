## Instrutor

- xxxxxxxxxxxxxxxxx (xxxxxxxxxxxxxxxxxxxxxx)
- Contato Linkedin: / [xxxxxxxx](https://www.linkedin.com/in/xxxxxxxxxxxxxx/)

## Parte 1 - Java e a Arte da Abstração com Classes e Encapsulamento

### 🟩 Vídeo 01 - Criando a primeira Classe

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.01-curso.05-video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/java-e-a-arte-da-abstracao-com-classes-e-encapsulamento/learning/8367cdbe-ddde-4555-987a-5821f7e05e7f?autoplay=1

O vídeo esume os fundamentos da POO, utilizando a linguagem Java como base. O foco principal é a abstração de conceitos do mundo real para o software, garantindo segurança, organização e reuso de código.

### Anotações

#### Primeiro Exemplo

O código abaixo é apresentado pelo professor como forma de ilustrar que cada comando `new` cria um novo objeto na memória:

```java
public class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var scanner1 = new Scanner(System.in);
        var scanner2 = new Scanner(System.in);
    }
}

```

##### Observação:

Embora ele de fato aloque três espaços diferentes na memória **Heap** para três objetos `Scanner`, isto cria um conflito de recursos:

* **Conflito de I/O:** todos os três objetos tentam controlar o mesmo recurso do sistema operacional: o `System.in` (teclado).
* **Vazamento de Memória:** o `Scanner` é um recurso "pesado". Criar múltiplos objetos para a mesma função desperdiça memória.
* **Risco de Exceções:** se você fechar `scanner1`, o fluxo de entrada (`System.in`) será encerrado para todos os outros, causando erros no programa.

##### O Código Correto e Eficiente

A forma correta de gerenciar isso é separar o **mecanismo de leitura** (o objeto Scanner) dos **dados lidos** (as variáveis). O Scanner deve ser único, enquanto os dados capturados podem ocupar quantos espaços de memória forem necessários.

```java
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // 1. Criamos um ÚNICO mecanismo de leitura na memória Heap
        var leitor = new Scanner(System.in);

        // 2. Alocamos espaços de memória distintos para os DADOS (Strings)
        // Cada 'nextLine()' captura uma entrada e a coloca em um novo endereço
        System.out.println("Digite o valor para a variável 'scanner' (ex: um nome):");
        String scanner = leitor.nextLine(); 

        System.out.println("Digite o valor para a variável 'scanner2' (ex: um ID):");
        String scanner2 = leitor.nextLine();

        System.out.println("Digite o valor para a variável 'scanner3' (ex: uma cidade):");
        String scanner3 = leitor.nextLine();

        // 3. Demonstração de que os dados estão preservados em locais diferentes
        System.out.println("\n--- Conteúdo Armazenado na Memória ---");
        System.out.println("Variável scanner: " + scanner);
        System.out.println("Variável scanner2: " + scanner1);
        System.out.println("Variável scanner3: " + scanner2);

        // 4. Fechamento do recurso (Boa prática de Engenharia de Software)
        leitor.close();
    }
}

```

#### Segundo exemplo:

##### Classe Main:
```java
public class Main {

    public static void main(String[] args) {
        var male = new Person();
        male.name = "João";
        male.age = 12;

        var female = new Person();
        female.name = "Maria";
        female.age = 10;

        System.out.println("Male name: " + male.name + " age: " + female.age);
        System.out.println("Female name: " + female.name + " age: " + female.age);
    }
}
```

##### Classe Person:
```java
public class Person {

    public String name;

    public int age;
}
```

##### Saída:
```plaintext
Male name: João age: 10
Female name: Maria age: 10

Process finished with exit code 0
```

#### Análise Técnica: O Problema do Acesso Direto a Atributos

O exemplo fornecido ilustra uma prática que viola um dos pilares fundamentais da Programação Orientada a Objetos (POO): o **Encapsulamento**. Embora o código funcione tecnicamente para alocar memória e exibir dados, ele apresenta falhas graves de design e segurança que devem ser evitadas.

##### Por que evitar o acesso direto às variáveis?

Existem quatro motivos principais para evitar o uso de modificadores `public` em atributos de classe, como visto na classe `Person`:

* **Falta de Controle e Validação**: Quando um atributo é `public`, qualquer parte do código pode inserir valores inválidos. Por exemplo, seria possível definir `male.age = -50;` ou `male.name = null;`, e a classe `Person` não teria como impedir ou validar essa entrada.
* **Quebra do Encapsulamento**: O mundo externo não deveria saber *como* os dados são armazenados, apenas *o que* a classe pode fazer. O acesso direto expõe a estrutura interna, criando um acoplamento forte entre as classes.
* **Dificuldade de Manutenção**: Se você decidir mudar o nome do atributo `age` para `dataNascimento` no futuro, terá que alterar manualmente todas as linhas de código em outras classes que acessavam `age` diretamente.
* **Exposição de Erros de Lógica**: O próprio exemplo mostra como é fácil cometer erros de digitação ao acessar variáveis diretamente, como na linha `System.out.println("Male name: " + male.name + " age: " + female.age);`, onde a idade do objeto masculino foi substituída indevidamente pela do feminino.

##### A Solução Correta: Getters e Setters

Para corrigir o exemplo do professor e seguir os padrões de **Engenharia de Software** e **Engenharia de Dados** que você aplica em seus estudos, deve-se utilizar o modificador `private` e métodos de acesso.

##### Classe Person Refatorada:

```java
public class Person {
    // Atributos privados: ninguém acessa diretamente
    private String name;
    private int age;

    // Métodos públicos para controlar o acesso
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 0) { // Validação simples
            this.age = age;
        }
    }
}

```

##### Resumo das Diferenças

| Aspecto | Acesso Direto (Evitar) | Encapsulamento (Recomendado) |
| --- | --- | --- |
| **Segurança** | Nula (qualquer valor é aceito) | Alta (validação via Setters) |
| **Visibilidade** | `public` | `private` |
| **Manutenção** | Difícil e propensa a erros | Centralizada e segura |
| **Flexibilidade** | Rígida | Permite lógica interna oculta |


### 🟩 Vídeo 02 - Trabalhando com Records

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.01-curso.05-video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/java-e-a-arte-da-abstracao-com-classes-e-encapsulamento/learning/64263a12-13af-4f4c-b2ec-cb4c27b04d99?autoplay=1

     


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