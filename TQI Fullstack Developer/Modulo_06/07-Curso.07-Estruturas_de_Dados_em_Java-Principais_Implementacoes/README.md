## Instrutor

- João Dutra (Analista de Sistemas no Bradesco)
- Contato Linkedin: / [joao-ricardo-dutra](https://www.linkedin.com/in/joao-ricardo-dutra/)
- repositório do curso: https://github.com/jrdutra/estruturaDeDadosJavaDio

## Parte 1 - Principais Implementações das estruturas de dados do Java

### 🟩 Vídeo 01 - Conceitos de equals e hashCode

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.07-video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/estrutura-de-dados-em-java-principais-implementacoes/learning/40ce1b96-358f-48c8-a420-271b1741dcfd?autoplay=1

#### Estrutura de Dados em Java: Equals e HashCode

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-29-14h38m08s656.jpg" alt="" width="840">
</p>

Nesta aula, iniciamos a Etapa 1 do módulo de principais implementações das estruturas de dados em Java. O foco inicial é compreender os conceitos de **equals** e **hashCode**, elementos fundamentais para a organização e comparação de objetos dentro das coleções da linguagem.

#### Principais Implementações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-29-14h38m11s581.jpg" alt="" width="840">
</p>

Os métodos `equals()` e `hashCode()` são utilizados para otimizar a busca e a organização de objetos em estruturas de dados. Enquanto o **hashCode** gera um número inteiro (hash) que facilita o posicionamento e a recuperação rápida do objeto, o **equals** define a lógica de comparação para determinar se dois objetos são efetivamente iguais, baseando-se em regras de negócio específicas (como comparar carros apenas pela marca). É uma boa prática que ambos sejam implementados juntos, pois seus conceitos estão interligados no funcionamento interno das coleções Java.

#### Implementação Inicial da Classe Carro

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-29-14h38m15s185.jpg" alt="" width="840">
</p>

Para ilustrar esses conceitos, iniciamos a criação de uma classe chamada `Carro`. Esta classe possui um atributo `marca` e as assinaturas básicas para a sobrescrita dos métodos `equals()` e `hashCode()`.

```java
public class Carro {
    private String marca;

    @Override
    public boolean equals(Object o) {
        //Implementação do equals()
    }

    @Override
    public int hashCode() {
        //Implementação do hashCode()
    }
}

```

#### Detalhamento da Lógica de Comparação

A implementação robusta do método `equals` envolve várias etapas: primeiro, verifica-se se a referência de memória é a mesma; depois, se o objeto passado é nulo ou pertence a uma classe diferente. Por fim, é realizado um *casting* para comparar o atributo `marca`. O `hashCode` é gerado a partir do mesmo atributo utilizando a classe `Objects`.

```java
// Declaração do pacote para organizar a classe dentro da estrutura do projeto[cite: 46, 102].
package one.digitalinnovation;

// Importação da classe utilitária Objects, necessária para implementar equals e hashCode de forma segura[cite: 48].
import java.util.Objects;

// Definição da classe Carro, que servirá de modelo para os nossos objetos[cite: 27, 51].
public class Carro {

    // Atributo privado que define a característica "marca" do carro[cite: 28, 54].
    private String marca;

    // Construtor da classe que permite instanciar um Carro já definindo sua marca[cite: 57].
    public Carro (String marca) {
        // Atribui o valor recebido por parâmetro ao atributo de instância da classe[cite: 59].
        this.marca = marca;
    }

    // Método Getter para acessar o valor do atributo privado 'marca'[cite: 64].
    public String getMarca() {
        return marca;
    }

    // Método Setter para modificar o valor do atributo 'marca'[cite: 71].
    public void setMarca (String marca) {
        this.marca = marca;
    }

    // Indica que estamos sobrescrevendo o método equals da classe superior (Object)[cite: 29, 83].
    @Override
    public boolean equals(Object o) {
        // Testa se a referência de memória de ambos os objetos é a mesma; se sim, são iguais[cite: 85].
        if (this == o) return true;
        
        // Verifica se o objeto comparado é uma instância de Carro; se não for, não podem ser iguais[cite: 86].
        if (!(o instanceof Carro)) return false;
        
        // Faz o casting do objeto genérico 'o' para o tipo específico 'Carro'[cite: 87].
        Carro carro = (Carro) o;
        
        // Retorna a comparação final baseada no conteúdo do atributo marca[cite: 88].
        // O professor explica que, nesta regra de negócio, carros são iguais se as marcas forem iguais [00:01:00, 00:03:00].
        return Objects.equals(getMarca(), carro.getMarca());
    }

    // Indica a sobrescrita do método hashCode para gerar o código identificador do objeto[cite: 37, 94].
    @Override
    public int hashCode() {
        // Gera um número inteiro (hash) a partir do atributo marca[cite: 98].
        // Esse número facilita a organização e busca rápida em estruturas de dados [00:04:00].
        return Objects.hash(getMarca());
    }
}
```

#### Teste Prático com Listas e Objetos

No método `main`, validamos o comportamento da classe. Ao adicionar instâncias de `Carro` em uma `ArrayList`, o método `contains` utiliza a implementação de `equals` para verificar a existência de um item. Além disso, observamos que dois objetos diferentes com a mesma marca ("Ford") geram o mesmo `hashCode`, garantindo que a estrutura de dados os reconheça como logicamente equivalentes para fins de busca e organização.

```java
// Define o pacote onde a classe de execução está localizada.
package one.digitalinnovation;

// Importa a implementação de lista dinâmica ArrayList da biblioteca padrão do Java.
import java.util.ArrayList;
// Importa a interface List, que define o contrato para coleções ordenadas.
import java.util.List;

// Declaração da classe principal que contém o ponto de entrada do programa.
public class Main {
    // Método de entrada (main) onde a execução do código começa.
    public static void main(String[] args){

        // Instancia uma nova lista de objetos do tipo Carro usando ArrayList.
        List<Carro> listaCarros = new ArrayList<>();

        // Adiciona um novo objeto Carro com a marca "Ford" à lista.
        listaCarros.add(new Carro("Ford"));
        // Adiciona um novo objeto Carro com a marca "Chevrolet" à lista.
        listaCarros.add(new Carro("Chevrolet"));
        // Adiciona um novo objeto Carro com a marca "Volkswagen" à lista.
        listaCarros.add(new Carro("Volkswagen"));

        // O método 'contains' usa internamente o equals() para verificar se um carro "Ford" existe na lista.
        // Como implementamos equals() baseado na marca, isso retornará 'true', mesmo sendo uma nova instância.
        System.out.println(listaCarros.contains(new Carro("Ford")));

        // Imprime o código hash (hashCode) de uma nova instância de Carro com a marca "Ford".
        System.out.println(new Carro("Ford").hashCode());
        
        // Imprime o hashCode de outra instância "Ford". O valor será idêntico ao anterior.
        // O professor explica que instâncias diferentes com atributos iguais geram o mesmo hash.
        System.out.println(new Carro("Ford").hashCode());
    }
}
```

### 🟩 Vídeo 02 - Stack (Pilha)

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.07-video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/estrutura-de-dados-em-java-principais-implementacoes/learning/c9e01c6c-8d88-4b0a-88f2-3a3093ca9e5f?autoplay=1

Este guia resume a aula prática sobre a classe Stack da biblioteca java.util. O foco é entender como gerenciar uma pilha de objetos, utilizando o exemplo de uma lista de carros, e as implicações de performance ao lidar com grandes volumes de dados.

### Anotações

#### Introdução à Implementação de Stack em Java

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-29-14h59m59s309.jpg" alt="" width="840">
</p>

Esta etapa aborda as principais implementações das estruturas de dados na linguagem Java, focando especificamente na classe **Stack** (Pilha). O objetivo é demonstrar como utilizar as ferramentas nativas da linguagem para manipular coleções de dados que seguem o princípio LIFO (*Last In, First Out*), onde o último elemento a entrar é o primeiro a sair.

#### Principais Métodos e Documentação

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-29-15h00m02s773.jpg" alt="" width="840">
</p>

Para trabalhar com pilhas em Java, a classe `Stack` oferece métodos fundamentais que permitem gerenciar os elementos de forma eficiente. Um QR Code é disponibilizado para acesso direto à documentação oficial da Oracle. Os métodos destacados incluem:

* **push()**: Adiciona um item ao topo da pilha.
* **pop()**: Remove e retorna o objeto no topo da pilha.
* **peek()**: Retorna o objeto no topo da pilha sem removê-lo.
* **empty()**: Verifica se a pilha está vazia.
* **search()**: Busca por um objeto na pilha e retorna sua posição baseada em 1.

```java
1 Stack<Carro> stackCarros = new Stack  <>();
2
3 stackCarros.push(new Carro("Ford"));
4 stackCarros.push(new Carro("Chevrolet"));
5 stackCarros.push(new Carro("Fiat"));
6 stackCarros.search(new Carro("Chevrolet"))
7
8 stackCarros.pop()
9 stackCarros.peek()
10 stackCarros.empty()

```

#### A Classe de Modelo: Carro

Para exemplificar o uso da pilha, utiliza-se a classe `Carro`. Além dos atributos, *getters* e *setters* básicos, é essencial a implementação dos métodos `equals()` e `hashCode()` para permitir a comparação correta entre objetos, e o método `toString()` para facilitar a visualização dos dados no console durante a execução dos testes.

```java
import java.util.Objects; // Importação necessária para utilizar métodos utilitários de comparação e hash[cite: 26].

public class Carro { // Definição da classe de modelo "Carro" utilizada nos exemplos de Stack[cite: 29].
    private String marca; // Atributo privado que define a propriedade única do objeto[cite: 32].

    public Carro (String marca) { // Construtor da classe para inicializar o objeto com uma marca[cite: 36].
        this.marca = marca; // Atribuição do valor recebido ao atributo da instância[cite: 36].
    }

    public String getMarca() { // Método Getter para acessar o valor da marca[cite: 41].
        return marca; // Retorna o conteúdo da variável marca[cite: 43].
    }

    public void setMarca (String marca) { // Método Setter para modificar o valor da marca[cite: 48].
        this.marca = marca; // Atualiza o atributo com o novo valor fornecido[cite: 50].
    }

    @Override // Sobrescrita de método para garantir o comportamento correto em coleções[cite: 55].
    public boolean equals(Object o) { // Implementação do método equals para comparar logicamente dois carros[cite: 60].
        if (this == o) return true; // Se forem a mesma referência de memória, são iguais[cite: 58].
        if (!(o instanceof Carro)) return false; // Valida se o objeto comparado é do tipo Carro[cite: 61].
        Carro carro = (Carro) o; // Faz o cast do objeto para o tipo Carro[cite: 63].
        return Objects.equals(getMarca(), carro.getMarca()); // Compara se as marcas dos carros são idênticas[cite: 65].
    }

    @Override // Sobrescrita necessária para o funcionamento de tabelas hash e buscas[cite: 70].
    public int hashCode() { // Implementação do método hashCode[cite: 72].
        return Objects.hash(getMarca()); // Gera um código numérico baseado no conteúdo da marca[cite: 74].
    }

    @Override // Sobrescrita do método toString, conforme solicitado pelo professor.
    public String toString() { // Método para permitir a visualização textual do objeto no console.
        return "Carro{" + // Início da formatação da String de saída[cite: 83].
                "marca='" + marca + '\'' + // Exibição amigável do conteúdo do atributo marca[cite: 86].
                '}'; // Fechamento da representação do objeto[cite: 86].
    }
}
```

#### Execução e Manipulação da Pilha

A classe `Main` demonstra a pilha em ação. Ao inserir os carros Ford, Chevrolet e Fiat (nesta ordem), o Fiat torna-se o topo. O método `pop()` remove o Fiat, restando Ford e Chevrolet. O método `peek()` permite visualizar o Chevrolet (novo topo) sem removê-lo, e o `empty()` confirma que a estrutura ainda contém elementos, retornando `false`.

```java
import java.util.Stack; // Importa a classe Stack do pacote utilitário do Java.

[cite_start]public class Main { // Define a classe principal para execução do programa[cite: 100].
    [cite_start]public static void main(String args[]){ // Ponto de entrada (método main) para rodar o código[cite: 101].
        
        // Instancia uma nova pilha (Stack) que armazenará objetos do tipo Carro.
        Stack<Carro> stackCarros = new Stack<>(); 

        // Adiciona o carro "Ford" na base da pilha.
        stackCarros.push(new Carro("Ford")); 
        
        // Adiciona "Chevrolet" sobre o carro anterior.
        stackCarros.push(new Carro("Chevrolet")); 
        
        // Adiciona "Fiat" no topo da pilha; ele é o último a entrar.
        stackCarros.push(new Carro ("Fiat")); 

        // Imprime a pilha completa: [Ford, Chevrolet, Fiat].
        System.out.println(stackCarros); 
        
        // O método pop() remove e retorna o elemento que está no topo (Fiat).
        System.out.println(stackCarros.pop()); 
        
        // Imprime a pilha após o pop; agora resta apenas [Ford, Chevrolet].
        System.out.println(stackCarros); 

        // O método peek() apenas exibe o elemento do topo (Chevrolet) sem removê-lo.
        System.out.println(stackCarros.peek()); 
        
        // Imprime a pilha novamente para mostrar que o Chevrolet continua nela.
        System.out.println(stackCarros); 
        
        // O método empty() testa se a pilha está vazia; retorna false neste caso.
        System.out.println(stackCarros.empty()); 
    }
}
```      


### 🟩 Vídeo 03 - Queue (Fila, LikedList)

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.07-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 04 - List (ArrayList)

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.07-video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 05 - Set (HashSet)

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.07-video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 06 - Map (HashMap)

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.07-video_06.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 07 - Conclusão

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.07-video_07.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

##  Materiais de Apoio

# Certificado: 

- Link na plataforma: 
- Certificado em pdf: 
