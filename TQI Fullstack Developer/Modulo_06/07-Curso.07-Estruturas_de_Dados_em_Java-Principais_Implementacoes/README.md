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
package one.digitalinnovation;

import java.util.Objects;

public class Carro {

    private String marca;

    public Carro (String marca) {
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca (String marca) {
        this.marca = marca;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Carro)) return false;
        Carro carro = (Carro) o;
        return Objects.equals(getMarca(), carro.getMarca());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMarca());
    }
}

```

#### Teste Prático com Listas e Objetos

No método `main`, validamos o comportamento da classe. Ao adicionar instâncias de `Carro` em uma `ArrayList`, o método `contains` utiliza a implementação de `equals` para verificar a existência de um item. Além disso, observamos que dois objetos diferentes com a mesma marca ("Ford") geram o mesmo `hashCode`, garantindo que a estrutura de dados os reconheça como logicamente equivalentes para fins de busca e organização.

```java
package one.digitalinnovation;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){

        List<Carro> listaCarros = new ArrayList<>();

        listaCarros.add(new Carro("Ford"));
        listaCarros.add(new Carro("Chevrolet"));
        listaCarros.add(new Carro("Volkswagen"));

        System.out.println(listaCarros.contains(new Carro("Ford")));
        System.out.println(new Carro("Ford").hashCode());
        System.out.println(new Carro("Ford").hashCode());
    }
}

```

### 🟩 Vídeo 02 - Stack (Pilha)

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.06-curso.07-video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

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
