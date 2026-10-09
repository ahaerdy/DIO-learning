# Tutorial de Estudos — Conectando sua API com Banco de Dados Através do Spring Data

**Continuação: expondo a API REST de Customers — Vídeo 03**

- Curso: NTT Data — Jornada Tech (DIO) · Módulo 4 — "Criando Soluções Inteligentes com Spring Boot e Java Moderno"
- Curso 2 do módulo: "Conectando sua API com Banco de Dados Através do Spring Data"
- Instrutor: Thiago Poiani (Principal Engineer at Skip)
- Projeto: `marketplace`
- Documento de referência pessoal — nível iniciante em Java

---

## Sobre este documento

Este arquivo é a continuação direta de `001-Tutorial_Marketplace_Java_Spring_Data_Videos01a02.md` e cobre exclusivamente o **Vídeo 03 — "Criando a API REST para Customers"**. Ele foi escrito a partir das anotações atualizadas do README e — o mais importante — **conferido diretamente contra o código-fonte real** do `.zip` enviado (`marketplace_ate_o_video_03.zip`), e não apenas contra a narrativa do README. Isso importa porque, como será detalhado na seção "Pontos de atenção" mais abaixo, o código real desta etapa contém três problemas que impedem a compilação e que **não aparecem** no texto do README — só ficam visíveis olhando o `.java` de verdade.

Para concatenar este arquivo ao anterior, basta colar o conteúdo a partir da seção "Parte 3" logo abaixo do final do documento 01-02 (ajustando a numeração de seções, se desejar um documento único).

> **Como esta parte está organizada**
> Os blocos de código são apresentados na mesma ordem em que foram escritos na aula (seção 3.1 em diante), cada um seguido de uma explicação linha a linha em nível iniciante. Ao final, há: uma seção de divergências entre aula e código real, o glossário atualizado (só termos novos), o checkpoint fiel do projeto no estado do Vídeo 03, e os próximos passos revisados.

---

## Parte 3 — Expondo a API REST de Customers (Vídeo 03)

O Vídeo 03 parte da camada de persistência já pronta (entidade `Customer` + `JpaCustomerRepository`, construídas no Vídeo 02) e a transforma em uma **API REST completa**, usando o **Spring Data REST** — um recurso do Spring que gera controllers automaticamente a partir de repositórios, sem que seja preciso escrever `@RestController`, `@GetMapping`, `@PostMapping` etc. manualmente.

### 3.1. Adicionando a dependência do Spring Data REST

No `build.gradle`, uma nova linha é adicionada ao bloco `dependencies`:

```groovy
implementation 'org.springframework.boot:spring-boot-starter-data-rest'
```

**O que essa linha faz:** ela baixa e disponibiliza, no classpath do projeto, a biblioteca **Spring Data REST**. Diferente do Spring Data JPA (que conecta o código Java ao banco de dados), o Spring Data REST conecta os **repositórios** (como `CustomerEntityRepository`) diretamente à **web**, expondo cada operação de CRUD como um endpoint HTTP. Em outras palavras: até aqui, `save` e `findAll` só podiam ser chamados de dentro do próprio código Java; a partir de agora, eles passam a poder ser chamados por qualquer cliente HTTP (navegador, Postman, um app mobile) através de uma URL.

### 3.2. Anotando o repositório com `@RepositoryRestResource`

```java
@RepositoryRestResource
public interface CustomerEntityRepository extends CrudRepository<Customer, UUID> {
}
```

**O que essa anotação faz:** `@RepositoryRestResource` é a anotação que efetivamente "liga" o Spring Data REST para aquele repositório específico. Sozinha, ela já é suficiente para o Spring Data REST identificar `CustomerEntityRepository`, olhar para as operações que ele herda de `CrudRepository` (salvar, buscar por ID, listar todos, deletar) e criar, automaticamente, em tempo de execução, um conjunto de endpoints HTTP equivalentes — sem que uma linha de controller seja escrita. Repare que essa é a mesma lógica de "declarar o quê, e deixar o Spring resolver o como" que já apareceu no Vídeo 02 com o próprio `CrudRepository`; aqui ela se estende da camada de banco de dados até a camada web.

### 3.3. Estendendo o repositório com `PagingAndSortingRepository`

```java
@RepositoryRestResource
public interface CustomerEntityRepository extends
        PagingAndSortingRepository<Customer, UUID>,
        CrudRepository<Customer, UUID> {
}
```

**O que mudou:** a interface passa a estender **duas** interfaces ao mesmo tempo (Java permite que uma `interface` estenda várias outras interfaces — diferente de uma `class`, que só pode `extends` uma única superclasse). `PagingAndSortingRepository<Customer, UUID>` é outra interface pronta do Spring Data, especializada em duas funcionalidades:

- **Paginação** — em vez de devolver todos os registros de uma vez (o que seria péssimo se a tabela tivesse milhões de linhas), a API passa a devolver os resultados em "páginas" (ex.: 20 registros por vez).
- **Ordenação** — permite pedir os resultados ordenados por um ou mais campos (ex.: por `firstName`, crescente ou decrescente).

Assim como `CrudRepository<T, ID>` visto no Vídeo 02, `PagingAndSortingRepository<T, ID>` também usa **generics**: o primeiro parâmetro (`Customer`) é o tipo da entidade, o segundo (`UUID`) é o tipo do identificador.

> **Por que declarar as duas interfaces, já que uma poderia bastar?**
> Nas versões atuais do Spring Data, `PagingAndSortingRepository` já não estende `CrudRepository` automaticamente (isso mudou em relação a versões antigas do framework) — por isso o professor declara as duas explicitamente, garantindo que a interface final tenha tanto os métodos de paginação/ordenação quanto os métodos padrão de CRUD.

### 3.4. Explorando a API pela primeira vez: HATEOAS

Com a aplicação reiniciada, uma requisição é feita na raiz da aplicação usando a ferramenta **HTTP Client** da IDE — um recurso que permite escrever requisições HTTP em um arquivo de texto (extensão `.http`) e executá-las diretamente de dentro do editor, sem precisar de um programa externo como Postman:

```http
GET http://localhost:8080/
Accept: application/json
```

A resposta:

```json
{
  "_links": {
    "customers": {
      "href": "http://localhost:8080/customers{?page,size,sort*}",
      "templated": true
    },
    "profile": {
      "href": "http://localhost:8080/profile"
    }
  }
}
```

**O que está acontecendo:** essa resposta é um exemplo do padrão **HATEOAS** (*Hypermedia as the Engine of Application State*). A ideia central do HATEOAS é que a própria API deve "ensinar" o cliente sobre quais ações ele pode tomar a seguir, através de **links** embutidos na resposta — em vez de o cliente precisar saber, de antemão e fora da API, todas as URLs que existem. Aqui, ao acessar a raiz (`/`), a API já devolve um link para `customers` (o recurso principal) e para `profile` (a documentação da API). O trecho `{?page,size,sort*}` é um **URI Template** (padrão RFC 6570): indica que a URL de `customers` aceita opcionalmente os parâmetros `page`, `size` e `sort` — exatamente os recursos de paginação e ordenação adicionados na seção anterior.

### 3.5. O link `profile`: documentação viva da API

```http
GET http://localhost:8080/profile
```

```json
{
  "_links": {
    "self": { "href": "http://localhost:8080/profile" },
    "customers": { "href": "http://localhost:8080/profile/customers" }
  }
}
```

Seguindo esse link até `/profile/customers`, a API retorna metadados detalhados sobre cada operação disponível para o recurso `customers` — por exemplo, `create-customers` (classificada como `UNSAFE`, ou seja, uma operação que altera dados) e `get-customers` (classificada como `SAFE`, apenas leitura), incluindo a descrição de cada campo aceito. Esse mecanismo (chamado de **ALPS** — *Application-Level Profile Semantics* — por trás dos panos) funciona como uma documentação sempre atualizada e gerada automaticamente a partir do próprio código, sem que ninguém precise escrevê-la ou mantê-la manualmente à parte.

### 3.6. Adicionando o HAL Explorer

```groovy
implementation 'org.springframework.data:spring-data-rest-hal-explorer'
```

**Dois conceitos novos aqui:**

- **HAL** (*Hypertext Application Language*) é o formato específico de JSON que o Spring Data REST usa para representar hipermídia — é o que explica a presença dos campos `_links` (links de navegação) e, como será visto adiante, `_embedded` (os dados propriamente ditos) em todas as respostas.
- **HAL Explorer** é uma interface web (visual, no navegador) que sabe interpretar esse formato HAL e permite clicar nos links da API, preencher formulários de requisição e visualizar respostas — tudo sem escrever uma única linha de `.http` manualmente. Com essa dependência adicionada, acessar `http://localhost:8080` no navegador (não mais via HTTP Client) já abre essa interface automaticamente.

### 3.7. `GET /customers`: lista vazia, mas com metadados de paginação

```json
{
  "_embedded": { "customers": [] },
  "_links": {
    "self": { "href": "http://localhost:8080/customers?page=0&size=20" },
    "profile": { "href": "http://localhost:8080/profile/customers" }
  },
  "page": {
    "number": 0,
    "size": 20,
    "totalElements": 0,
    "totalPages": 0
  }
}
```

Esse é o formato padrão de uma coleção paginada no Spring Data REST: os registros de fato ficam dentro de `_embedded.customers` (ainda vazio, já que nenhum customer foi criado por essa via); `_links.self` mostra a URL completa usada, já com os valores padrão de `page=0` e `size=20` aplicados; e o bloco `page` traz os metadados de paginação (número da página atual, tamanho da página, total de elementos e total de páginas) — tudo isso vindo "de graça" da combinação `@RepositoryRestResource` + `PagingAndSortingRepository`.

### 3.8. Primeira tentativa de `POST` e o erro de identificador

Pelo HAL Explorer, o formulário de criação (baseado no *JSON Schema* do `profile`) é preenchido e gera o corpo:

```json
{
  "firstName": "Thiago",
  "lastName": "Poiani",
  "email": "thpoiani@gmail.com"
}
```

Ao enviar, a aplicação retorna um erro. O motivo, ao revisar a entidade `Customer` (Vídeo 02), é este trecho:

```java
@Id
private UUID id;
```

O campo `id` está marcado com `@Id` (define que ele é a chave primária), mas **sem nenhuma anotação de geração automática** (`@GeneratedValue`). O log confirma:

```
org.hibernate.jpa.JpaSystemException: Identifier of entity 'dio.marketplace.registration.infrastructure.persistence.entity.Customer' must be manually assigned...
```

**Em outras palavras:** o Hibernate está dizendo "eu não sei gerar esse ID sozinho, e como o JSON enviado não trouxe nenhum `id`, o campo chegou nulo até mim — preciso que alguém o preencha antes de eu tentar salvar".

### 3.9. Resolvendo com `@PrePersist`

```java
@PrePersist
public void prePersist() {
    if (id == null) {
        id = UUID.randomUUID();
    }
}
```

**Conceito novo — `@PrePersist` (JPA lifecycle callback):** essa anotação marca um método para ser executado automaticamente pelo JPA/Hibernate **imediatamente antes** de um novo registro ser inserido no banco pela primeira vez. É um dos vários "ganchos de ciclo de vida" que a especificação JPA oferece (existem outros, como `@PostPersist`, `@PreUpdate`, `@PreRemove`, etc.), permitindo executar lógica customizada em momentos específicos da vida de uma entidade.

Aqui, o método verifica se `id` ainda está `null` e, se estiver, atribui um novo `UUID.randomUUID()` antes que o Hibernate tente gravar a linha. É funcionalmente parecido com o que a anotação `@GeneratedValue(strategy = GenerationType.UUID)` faria automaticamente (e que será usada, alguns passos à frente, na entidade `Address`) — a diferença é que, escrevendo o método manualmente com `@PrePersist`, o desenvolvedor mantém controle total sobre a lógica de geração, podendo evoluí-la no futuro com regras de negócio próprias, caso necessário.

### 3.10. Ajustando `spring.jpa.hibernate.ddl-auto` para `update`

```properties
spring.jpa.hibernate.ddl-auto=update
```

No Vídeo 02, essa propriedade estava configurada como `create`. A diferença entre os dois valores:

- **`create`** — a cada vez que a aplicação sobe, o Hibernate **apaga e recria** todo o schema do banco do zero. Ótimo para começar do zero repetidas vezes durante o aprendizado, péssimo assim que se quer manter dados entre uma execução e outra.
- **`update`** — o Hibernate compara o schema atual do banco com o que as entidades Java descrevem e aplica **apenas as diferenças** (ex.: criar uma tabela nova, adicionar uma coluna), preservando os dados já existentes.

A partir daqui, os customers criados persistem entre reinicializações da aplicação — essencial para os próximos testes, que vão acumular vários registros.

### 3.11. `POST` bem-sucedido: `201 Created`

Repetindo a mesma criação de customer, a resposta agora é:

```json
{
  "_links": {
    "self": { "href": "http://localhost:8080/customers/a8163831-7f52-4321-8e3b-957467812e08" },
    "customer": { "href": "http://localhost:8080/customers/a8163831-7f52-4321-8e3b-957467812e08" }
  },
  "createdOn": "2026-03-29T21:25:26.945674Z",
  "email": "thpoiani@gmail.com",
  "firstName": "Thiago",
  "lastName": "Poiani"
}
```

**Conceito novo — código de status `201 Created`:** dentro do protocolo HTTP, os códigos de status na faixa `2xx` indicam sucesso; especificamente, `201` significa "um novo recurso foi criado com sucesso" (diferente do `200`, que é um sucesso genérico). O Spring Data REST já devolve esse código automaticamente para operações de criação bem-sucedidas, sem que nada precise ser configurado. A resposta também já traz o `createdOn` (preenchido pelo `@CreationTimestamp`, visto no Vídeo 02) e os links `self`/`customer` apontando para a URL exata daquele novo registro — outro exemplo do padrão HATEOAS: a API "devolve o caminho" para acessar aquilo que acabou de ser criado.

A persistência é confirmada consultando a tabela `customer` diretamente no banco pela IDE, onde a linha aparece com os mesmos valores enviados.

### 3.12. Testando o `DELETE` (ainda sem restrição)

Ainda no HAL Explorer, o botão de exclusão do customer recém-criado é acionado. Uma nova consulta a `GET /customers` confirma que a lista volta a ficar vazia (`_embedded.customers: []`) — ou seja, por padrão, o Spring Data REST já expõe **todas** as operações do `CrudRepository`, inclusive `deleteById`, sem que nada precise ser feito para isso.

### 3.13. Restringindo `deleteById` com `@RestResource(exported = false)`

```java
@Override
@RestResource(exported = false)
void deleteById(UUID id);
```

Esse trecho sobrescreve (`@Override`) o método `deleteById`, que a interface já herda de `CrudRepository`, apenas para anotá-lo com `@RestResource(exported = false)`.

**O que essa anotação faz:** `@RestResource` controla, método a método, se uma operação deve ou não ser exposta como endpoint HTTP pelo Spring Data REST. Com `exported = false`, o método `deleteById` continua existindo e funcionando **internamente** (ou seja, outro código Java dentro da aplicação ainda poderia chamá-lo diretamente), mas deixa de estar disponível como uma rota HTTP acessível por quem consome a API. É uma forma simples de "esconder" operações específicas sem precisar remover o método da interface nem escrever um controller manual só para bloquear uma rota.

> **Por que reescrever um método que já vem pronto do `CrudRepository`?**
> Em Java, quando uma interface herda um método de outra interface e você quer adicionar (ou alterar) apenas uma anotação sobre ele — sem mudar seu comportamento —, a forma correta é declará-lo novamente na interface filha, usando `@Override` para deixar explícito que essa é uma redeclaração de um método já existente, e não um método novo.

### 3.14. Criando uma consulta personalizada: *query method*

```java
List<Customer> findByFirstNameStartingWithIgnoreCase(@Param("firstName") String firstName);
```

**Conceito novo — *query method* (consulta derivada do nome do método):** esse é um dos recursos mais conhecidos do Spring Data. Ao declarar um método em uma interface de repositório seguindo uma convenção de nomenclatura específica, o Spring Data **interpreta o próprio nome do método** e gera a consulta correspondente automaticamente — sem que uma única linha de SQL (ou JPQL) seja escrita. Decompondo o nome:

- `findBy` — indica que é uma operação de busca.
- `FirstName` — indica o campo da entidade `Customer` a ser usado no filtro.
- `StartingWith` — indica que o filtro é do tipo "começa com" (equivalente a um `LIKE 'valor%'` em SQL).
- `IgnoreCase` — indica que a comparação deve ignorar diferenças entre maiúsculas e minúsculas.

O parâmetro `firstName` do método é anotado com `@Param("firstName")`, uma anotação do Spring Data que associa explicitamente o nome do parâmetro Java ao nome do parâmetro esperado na consulta gerada — útil especialmente quando o código é compilado sem manter os nomes originais dos parâmetros (o que pode acontecer dependendo da configuração do compilador).

### 3.15. Populando a base e descobrindo o endpoint de busca

Usando o HTTP Client, uma requisição reaproveitável é criada com uma variável dinâmica:

```http
POST http://localhost:8080/customers
Accept: application/json

{
  "firstName":"{{$timestamp}}",
  "email":"{{$timestamp}}@email.com"
}
```

**Conceito novo — `{{$timestamp}}`:** é uma *variável dinâmica* reconhecida pelo HTTP Client da IDE. A cada execução da requisição, ela é substituída pelo timestamp atual (um número que muda a cada segundo), gerando um `firstName` e um `email` diferentes em cada chamada — uma forma prática de criar vários registros de teste sem editar o corpo da requisição manualmente a cada envio. Após várias execuções (seis, neste caso), `GET /customers` já mostra `"totalElements": 6`, e a resposta passa a incluir um novo link, `search`.

Seguindo esse link:

```json
{
  "_links": {
    "findByFirstNameStartingWithIgnoreCase": {
      "href": "http://localhost:8080/customers/search/findByFirstNameStartingWithIgnoreCase{?firstName}",
      "templated": true
    },
    "self": { "href": "http://localhost:8080/customers/search" }
  }
}
```

Isso confirma outro comportamento automático do Spring Data REST: todo *query method* declarado no repositório é automaticamente exposto como um endpoint dentro de `/customers/search`. Preenchendo `firstName=1` (já que os nomes de teste são números de timestamp), a busca retorna todos os customers cujo nome começa com `1`.

### 3.16. Confirmando a restrição do `deleteById`

Selecionando um dos customers retornados pela busca e tentando excluí-lo pelo HAL Explorer, a resposta agora é um erro **`405 Method Not Allowed`**, com o header `allow` listando apenas `HEAD, GET, OPTIONS, PUT, PATCH` — sem `DELETE`. Isso confirma, na prática, que a anotação `@RestResource(exported = false)` (seção 3.13) funcionou: o método continua existindo, mas a rota HTTP correspondente não está mais disponível. Uma nova listagem confirma que os seis customers continuam intactos.

### 3.17. Atualizando um customer com `PATCH`

```json
{
  "firstName":"Thiago",
  "lastName":"Poiani"
}
```

**Conceito novo — `PATCH` vs. `PUT`:** ambos são métodos HTTP usados para atualizar um recurso existente, mas com uma diferença importante: `PUT` normalmente exige o **objeto completo** no corpo da requisição (o servidor entende que qualquer campo omitido deve ser apagado/zerado), enquanto `PATCH` é pensado para atualizações **parciais** — apenas os campos enviados são alterados, e todos os demais permanecem exatamente como estavam. Aqui, apenas `firstName` e `lastName` são enviados, e a resposta confirma que `email` e `createdOn` permanecem intactos. O Spring Data REST já suporta `PATCH` automaticamente para qualquer recurso exposto, sem configuração adicional.

### 3.18. Criando a entidade `Address`

```java
package dio.marketplace.registration.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@RequiredArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String street, postalCode, city, state;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Instant createdOn;

    public String toString() {
        return String.format("%s, %s %s, %s", street, postalCode, city, state);
    }
}
```

Uma nova classe de entidade, no mesmo padrão de `Customer`: `@Entity` marca a classe como persistível; `@Data` (Lombok) gera getters, setters, `equals`, `hashCode` e um `toString()` padrão — que, no entanto, é **sobrescrito manualmente** logo abaixo, porque o formato desejado (`"rua, cep cidade, estado"`) é específico e diferente do que o Lombok geraria automaticamente. `@RequiredArgsConstructor` (Lombok) gera um construtor com os campos `final` da classe — como nenhum campo aqui é `final`, na prática esse construtor fica vazio (sem parâmetros).

**Conceito novo — `@GeneratedValue(strategy = GenerationType.UUID)`:** diferente da abordagem manual usada em `Customer` (seção 3.9, com `@PrePersist`), aqui o próprio JPA/Hibernate assume a responsabilidade de gerar o identificador. `@GeneratedValue` indica que o valor do campo `@Id` deve ser gerado automaticamente; o argumento `strategy = GenerationType.UUID` diz especificamente que a estratégia de geração deve produzir um `UUID` aleatório (um dos vários `GenerationType` disponíveis na JPA — outros incluem `IDENTITY`, que delega a geração ao próprio banco via auto-incremento, e `SEQUENCE`, que usa uma sequência do banco). A vantagem dessa abordagem é ter menos código para manter; a desvantagem é ter menos controle — por isso a aula opta por mostrar as duas formas, uma em cada entidade.

O campo `street, postalCode, city, state` usa uma sintaxe do Java que declara **múltiplas variáveis do mesmo tipo em uma única linha**, separadas por vírgula — equivalente a escrever quatro linhas `private String street; private String postalCode; ...`, apenas mais compacto.

### 3.19. Relacionando `Customer` e `Address`

```java
@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
private Address address;
```

**Três conceitos novos juntos aqui:**

- **`@OneToOne`** — anotação JPA que declara um relacionamento "um para um" entre duas entidades: cada `Customer` está associado a, no máximo, um `Address`, e vice-versa. Por trás dos panos, o Hibernate cria uma coluna extra na tabela `customer` (uma chave estrangeira) apontando para a linha correspondente na tabela `address`.
- **`cascade = CascadeType.ALL`** — define que operações realizadas sobre o `Customer` (salvar, atualizar, remover) devem ser **propagadas automaticamente** para o `Address` associado. Sem isso, seria preciso salvar o `Address` manualmente, em uma chamada separada, antes ou depois de salvar o `Customer`. Com `cascade = CascadeType.ALL`, basta salvar o `Customer` que o `Address` vinculado a ele é salvo junto, na mesma operação.
- **`orphanRemoval = true`** — garante que, se um `Address` deixar de estar associado a um `Customer` (por exemplo, o campo `address` for setado como `null` e o customer salvo novamente), esse endereço "órfão" seja automaticamente **removido do banco**, em vez de ficar esquecido como um registro sem dono.

Ao subir a aplicação, o Hibernate cria a nova tabela `address` (inicialmente vazia) e adiciona a coluna de relacionamento à tabela `customer` — e, como a estratégia é `ddl-auto=update` (seção 3.10), os seis customers já existentes permanecem intactos.

### 3.20. Criando um customer com endereço

```http
POST http://localhost:8080/customers
Accept: application/json

{
  "firstName":"{{$timestamp}}",
  "email":"{{$timestamp}}@email.com",
  "address": {
    "street": "ABCDEF",
    "city": "Cidade",
    "state": "Estado"
  }
}
```

O corpo da requisição passa a incluir um objeto `address` aninhado (sem informar `postalCode`, que fica de fora deliberadamente). A resposta confirma `201 Created`, trazendo o `address` completo — incluindo `createdOn` (gerado pelo `@CreationTimestamp` da própria entidade `Address`) e `postalCode: null` (já que não foi enviado). Isso demonstra, na prática, o `cascade = CascadeType.ALL` da seção anterior: uma única requisição HTTP resultou em **duas** linhas gravadas no banco (uma em `customer`, outra em `address`) — confirmado consultando a tabela `address` diretamente, onde a nova linha aparece com `street: ABCDEF` e `state: Estado`.

### 3.21. Criando uma projeção: `CustomerExcerpt`

```java
package dio.marketplace.registration.infrastructure.persistence.entity.projection;
import dio.marketplace.registration.infrastructure.persistence.entity.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "excerpt", types = Customer.class)
public interface CustomerExcerpt {

    String getFirstName();

    String getLastName();

    @Value("#{target.address?.toString()}")
    String getAddress();
}
```

**Conceito novo — Projeção (`@Projection`):** uma projeção é uma forma de definir uma "vista" alternativa e reduzida de uma entidade, expondo apenas um subconjunto de campos (ou até campos calculados) diferente do que a entidade completa exporia por padrão. É útil, por exemplo, quando um endpoint de listagem não precisa (ou não deve) devolver todos os dados de cada registro — reduzindo o tamanho da resposta e evitando expor informações desnecessárias.

Decompondo a anotação `@Projection(name = "excerpt", types = Customer.class)`: `types = Customer.class` associa essa projeção especificamente à entidade `Customer`; `name = "excerpt"` define o nome pelo qual essa projeção poderá ser referenciada via parâmetro de URL (`?projection=excerpt`, visto na seção 3.23). A interface declara três métodos, no padrão *getter* (`getX()`), que definem exatamente quais campos aparecerão na resposta:

- `getFirstName()` e `getLastName()` espelham diretamente os campos homônimos da entidade `Customer` — o Spring Data REST resolve essa correspondência automaticamente, pelo nome do método.
- `getAddress()` é diferente: em vez de espelhar o campo `address` (que na entidade é um objeto `Address` completo), ele usa a anotação `@Value` com uma expressão entre chaves (`#{...}`) para devolver, no lugar do objeto inteiro, apenas o resultado de chamar `.toString()` no endereço.

**Conceito novo — `@Value` (Spring) e SpEL:** essa anotação `@Value` (do pacote `org.springframework.beans.factory.annotation`, e **não** a anotação de mesmo nome do Lombok — ver observação na seção "Pontos de atenção" logo abaixo) permite associar a um método (ou campo) o resultado de uma expressão escrita em **SpEL** (*Spring Expression Language*), uma pequena linguagem de expressões do próprio Spring. A expressão `"#{target.address?.toString()}"` significa: "pegue o objeto original desta projeção (`target`, ou seja, o `Customer` sendo projetado), acesse seu campo `address` e, **se ele não for nulo** (o operador `?.` é o *safe navigation operator* — evita um erro caso `address` seja `null`), chame `.toString()` nele". É por isso que o método `toString()` foi implementado manualmente em `Address` (seção 3.18) — ele é exatamente o texto que essa expressão vai devolver.

### 3.22. Aplicando a projeção como padrão do repositório

```java
@RepositoryRestResource(excerptProjection = CustomerExcerpt.class)
public interface CustomerEntityRepository extends
        PagingAndSortingRepository<Customer, UUID>,
        CrudRepository<Customer, UUID> {
    // ...
}
```

O atributo `excerptProjection` da anotação `@RepositoryRestResource` informa ao Spring Data REST qual projeção deve ser aplicada **automaticamente** sempre que os recursos daquele repositório forem retornados dentro de uma **coleção** (ou seja, dentro do `_embedded` de uma listagem, como `GET /customers`). Ao consultar a listagem novamente, cada customer que possui endereço passa a exibir `address` já como uma string resumida (ex.: `"ABCDEF, null Cidade, Estado"`), em vez do objeto completo com todos os subcampos.

### 3.23. Aplicando a projeção manualmente via parâmetro

Consultando um customer diretamente pelo seu ID (fora da listagem embutida), o comportamento muda: `address` volta a aparecer como o objeto completo. Isso acontece porque a `excerptProjection` configurada na seção anterior só se aplica automaticamente a **coleções** — não a recursos individuais.

Para aplicar a mesma projeção também nesse caso, usa-se o parâmetro de URL `projection`, com o valor correspondente ao `name` definido na anotação `@Projection` (seção 3.21):

```
http://localhost:8080/customers/d9ae3909-525d-4d65-8f14-5cf89a6d2826?projection=excerpt
```

Com esse parâmetro, a resposta passa a incluir apenas os três campos definidos na interface `CustomerExcerpt` (`firstName`, `lastName` e `address` resumido) — demonstrando como diferentes projeções podem ser criadas e combinadas para atender diferentes consumidores da mesma API (por exemplo, uma resposta enxuta para um app mobile, e uma resposta completa para um painel administrativo).

### 3.24. Criando um *listener* de eventos do repositório

Um novo pacote, `event`, é criado dentro de `infrastructure/persistence`, com a classe `CustomerEventHandler`:

```java
package dio.marketplace.registration.infrastructure.persistence.event;

import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

@RepositoryEventHandler
public class CustomerEventHandler {
}
```

**Conceito novo — `@RepositoryEventHandler`:** essa anotação marca uma classe como uma "ouvinte" (*listener*) dos eventos que o Spring Data REST dispara durante o ciclo de vida das operações de um repositório — por exemplo, sempre que um `Customer` é criado, salvo (atualizado) ou removido através da API REST. Sozinha, a anotação apenas identifica a classe como candidata a ouvir esses eventos; os métodos específicos que reagem a cada evento são adicionados a seguir.

### 3.25. Adicionando um `Logger` e registrando a classe como `@Component`

```java
package dio.marketplace.registration.infrastructure.persistence.event;

import dio.marketplace.registration.infrastructure.persistence.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class CustomerEventHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomerEventHandler.class);
}
```

**Conceito novo — SLF4J, `Logger` e `LoggerFactory`:** o **SLF4J** (*Simple Logging Facade for Java*) é uma fachada (interface padronizada) para registrar mensagens de log em uma aplicação Java, sem amarrar o código a uma implementação específica de log por trás. `LoggerFactory.getLogger(CustomerEventHandler.class)` cria (ou reaproveita) um objeto `Logger` associado àquela classe — é uma convenção quase universal em projetos Java usar exatamente esse padrão, com o `Logger` declarado como `private static final` (compartilhado por todas as instâncias da classe, e nunca reatribuído). Esse `Logger` será usado, na próxima seção, para registrar quando cada evento é disparado.

**Por que `@Component` também é necessário:** `@RepositoryEventHandler` sozinha apenas *descreve* o papel da classe; ela não faz o Spring "descobrir" e instanciar essa classe automaticamente. `@Component` é a anotação genérica do Spring que marca uma classe como um **bean** — um objeto gerenciado pelo container do Spring, criado e mantido automaticamente na inicialização da aplicação (o mesmo mecanismo por trás de anotações mais específicas já vistas, como `@Repository`, no Vídeo 02). Sem `@Component`, o Spring nunca chegaria a criar uma instância de `CustomerEventHandler`, e os métodos de evento (seção seguinte) simplesmente nunca seriam chamados, mesmo estando corretamente anotados.

### 3.26. Implementando os métodos de ciclo de vida

```java
@HandleAfterCreate
public void handleAfterCreate(Customer customer) {
    logger.warn("CustomerEventHandler#handleAfterCreate");
}

@HandleAfterSave
public void handleAfterSave(Customer customer) {
    logger.warn("CustomerEventHandler#handleAfterSave");
}

@HandleAfterDelete
public void handleAfterDelete(Customer customer) {
    logger.warn("CustomerEventHandler#handleAfterDelete");
}
```

**Conceito novo — `@HandleAfterCreate`, `@HandleAfterSave`, `@HandleAfterDelete`:** essas três anotações (do mesmo pacote de `@RepositoryEventHandler`) marcam métodos individuais dentro da classe *listener* para serem chamados automaticamente **depois** que a respectiva operação for concluída com sucesso pelo Spring Data REST — respectivamente: depois de criar um novo registro, depois de salvar/atualizar um registro existente, e depois de remover um registro. Cada método recebe como parâmetro a própria entidade (`Customer`) afetada pela operação, permitindo, por exemplo, inspecionar seus dados.

Aqui, cada método apenas registra uma mensagem de log (`logger.warn(...)`) identificando qual evento foi disparado — um exemplo simples, mas que já demonstra o mecanismo. Na prática, esse tipo de *listener* é útil sempre que a aplicação precisa **reagir** a mudanças de dados sem acoplar essa reação diretamente ao código que fez a alteração — por exemplo, enviar uma notificação, invalidar um cache, ou disparar um evento para outro sistema, sempre que um customer for criado.

Criando um novo customer via HTTP Client, o console de debug da aplicação confirma o funcionamento: a mensagem `CustomerEventHandler#handleAfterCreate` aparece no log logo após a criação ser concluída, validando que o *listener* está ativo e reagindo corretamente aos eventos do repositório.

---

## Pontos de atenção: divergências entre a aula e o seu projeto (Vídeo 03)

Assim como na etapa anterior, comparar linha a linha o `.zip` enviado com a narrativa do README revela algumas divergências. Diferente do Vídeo 02, porém, aqui existem **três problemas reais que impedem a compilação** do projeto no estado atual — por isso vale revisá-los com atenção antes de seguir para o Vídeo 04.

1. **`CustomerEventHandler.java` — falta o `@` na anotação `@RepositoryEventHandler`.** No seu arquivo, a classe está assim:

   ```java
   @Component
   RepositoryEventHandler
   public class CustomerEventHandler {
   ```

   Sem o símbolo `@` antes de `RepositoryEventHandler`, o compilador Java não interpreta essa palavra como uma anotação — ele espera que `RepositoryEventHandler` seja o **nome de um tipo** (uma classe ou interface) sendo declarado ali, o que não faz sentido nesse contexto e gera erro de compilação. É, com quase certeza, uma pequena distração de digitação durante a gravação (provavelmente o `@` foi digitado e o autocomplete da IDE, ou um corte de tela, acabou não capturando o caractere). A correção é simples:

   ```java
   @Component
   @RepositoryEventHandler
   public class CustomerEventHandler {
   ```

2. **`CustomerExcept.java` — o nome do arquivo não bate com o nome da interface pública.** O arquivo salvo em `infrastructure/persistence/entity/projection/CustomerExcept.java` declara, internamente, `public interface CustomerExcerpt`. Em Java, existe uma regra rígida: **todo tipo (classe, interface, enum, record) declarado como `public` em um arquivo precisa estar salvo em um arquivo com exatamente o mesmo nome** (incluindo maiúsculas/minúsculas), seguido da extensão `.java`. Como o arquivo se chama `CustomerExcept.java` mas a interface dentro dele se chama `CustomerExcerpt` (repare no "-erpt" faltando no nome do arquivo), o compilador rejeita o arquivo com um erro do tipo `class CustomerExcerpt is public, should be declared in a file named CustomerExcerpt.java`. A correção é renomear fisicamente o arquivo para `CustomerExcerpt.java`.

3. **`CustomerExcept.java` — import errado para a anotação `@Value`.** O arquivo importa:

   ```java
   import lombok.Value;
   ```

   Só que existem **duas anotações diferentes chamadas `@Value` em bibliotecas diferentes**, e o projeto precisa da errada trocada pela certa:

   - `lombok.Value` — uma anotação do Lombok que se aplica a **classes inteiras** (não a métodos), transformando-as em objetos imutáveis (todos os campos `private final`, com getters gerados automaticamente, sem setters). Ela não aceita nenhum parâmetro do tipo texto/expressão.
   - `org.springframework.beans.factory.annotation.Value` — a anotação do Spring usada na aula, que se aplica a métodos, campos ou parâmetros, e aceita uma expressão SpEL como argumento (`@Value("#{...}")`), exatamente o comportamento descrito na seção 3.21 deste tutorial.

   Como o arquivo importou `lombok.Value` mas tentou usá-la sobre um **método** de uma interface, com uma expressão SpEL como argumento — algo que a anotação do Lombok não suporta —, o compilador rejeita essa combinação. A correção é trocar o import:

   ```java
   import org.springframework.beans.factory.annotation.Value;
   ```

   Essa é justamente a razão pela qual, ao inspecionar os `.class` já compilados dentro do `.zip` (pasta `build/classes`), nem `CustomerExcerpt`, nem `CustomerEventHandler`, nem `Address`, nem sequer `MarketplaceApplication` aparecem compilados nesta etapa: a última compilação bem-sucedida do projeto é anterior a essas três alterações, e o build atual está quebrado até que os três pontos acima sejam corrigidos.

4. **`CustomerEntityRepository.java` — `JpaRepository` no lugar de `CrudRepository`, e um import não utilizado.** Assim como já havia acontecido no Vídeo 02, seu projeto usa `JpaRepository<Customer, UUID>` em vez do `CrudRepository<Customer, UUID>` mostrado na aula — sem problema algum, já que `JpaRepository` estende `CrudRepository` e oferece tudo que ele oferece, e mais. Há também um import não utilizado (`io.micrometer.common.lang.NonNull`), provavelmente deixado por uma sugestão automática da IDE que não chegou a ser aplicada — isso não impede a compilação, mas pode ser removido com segurança.

5. **Divergência de pacote em `CustomerEventHandler` entre README e código.** O README descreve o pacote como `dio.marketplace.registration.infrastructure.event`, mas o arquivo real está em `dio.marketplace.registration.infrastructure.persistence.event` — o que, na verdade, está **correto e consistente** com a pasta física onde o arquivo foi salvo (`infrastructure/persistence/event/`). Aqui quem diverge é apenas o texto do README, não o código; nenhuma ação é necessária.

> **Recomendação:** aplique as correções 1, 2 e 3 antes de tentar compilar/rodar o projeto novamente. Sem elas, `./gradlew build` (ou o botão de *run* da IDE) deve falhar com os erros descritos acima.

---

## Glossário de conceitos Java e Spring — termos novos do Vídeo 03

Esta seção só lista termos que **ainda não apareciam** no glossário do documento 01-02. Para os termos básicos de Java (`package`, `interface`, `record`, generics, etc.), consulte o glossário anterior.

### Spring Data REST e HATEOAS

| Termo | Significado |
|---|---|
| Spring Data REST | Biblioteca que gera automaticamente uma API REST completa a partir de interfaces de repositório do Spring Data, sem exigir controllers escritos manualmente. |
| `spring-boot-starter-data-rest` | *Starter* do Spring Boot que traz o Spring Data REST pronto para uso. |
| `@RepositoryRestResource` | Anotação que expõe um repositório do Spring Data como um recurso REST completo. O atributo `excerptProjection` define qual projeção usar por padrão em listagens. |
| HATEOAS | *Hypermedia as the Engine of Application State*: padrão de API em que a própria resposta inclui links (`_links`) para as próximas ações possíveis, em vez de exigir que o cliente já conheça todas as URLs de antemão. |
| HAL | *Hypertext Application Language*: formato de JSON (com os campos `_links` e `_embedded`) usado pelo Spring Data REST para representar hipermídia. |
| `spring-data-rest-hal-explorer` / HAL Explorer | Dependência que adiciona uma interface web navegável (no próprio `http://localhost:8080`) para explorar visualmente uma API que segue o formato HAL. |
| `_links` | Campo de uma resposta HAL contendo os links de navegação disponíveis a partir daquele recurso. |
| `_embedded` | Campo de uma resposta HAL contendo os dados propriamente ditos de uma coleção (ex.: a lista de customers). |
| `profile` (Spring Data REST) | Endpoint de metadados/documentação gerado automaticamente, descrevendo as operações e campos disponíveis para cada recurso da API. |
| `PagingAndSortingRepository<T, ID>` | Interface do Spring Data que adiciona suporte a paginação (`page`, `size`) e ordenação (`sort`) sobre uma entidade. |

### Ferramentas de teste de API

| Termo | Significado |
|---|---|
| HTTP Client (IDE) | Ferramenta embutida na IDE que permite escrever e executar requisições HTTP a partir de um arquivo `.http`, sem depender de um programa externo. |
| `{{$timestamp}}` | Variável dinâmica reconhecida pelo HTTP Client, substituída pelo timestamp atual a cada execução da requisição — útil para gerar dados de teste variados. |
| `201 Created` | Código de status HTTP que indica que uma requisição resultou na criação bem-sucedida de um novo recurso. |
| `405 Method Not Allowed` | Código de status HTTP que indica que o método usado na requisição (ex.: `DELETE`) não é permitido para aquele recurso; o header `allow` da resposta lista os métodos que de fato são aceitos. |
| `PATCH` | Método HTTP usado para atualizações parciais: apenas os campos enviados no corpo da requisição são alterados; os demais permanecem inalterados. |

### JPA/Hibernate: ciclo de vida e relacionamentos

| Termo | Significado |
|---|---|
| `@PrePersist` | Anotação JPA que marca um método para ser executado automaticamente pelo Hibernate imediatamente antes de uma entidade ser inserida no banco pela primeira vez. |
| `@GeneratedValue` | Anotação JPA que delega ao próprio JPA/Hibernate a responsabilidade de gerar o valor do campo `@Id`, em vez de exigir atribuição manual. |
| `GenerationType.UUID` | Uma das estratégias possíveis para `@GeneratedValue`: gera um `UUID` aleatório automaticamente para o identificador. |
| `ddl-auto=create` vs. `ddl-auto=update` | Duas estratégias de sincronização de schema do Hibernate: `create` apaga e recria o banco a cada subida da aplicação; `update` preserva os dados existentes e aplica apenas as diferenças necessárias. |
| `@OneToOne` | Anotação JPA que declara um relacionamento "um para um" entre duas entidades. |
| `CascadeType.ALL` | Configuração de um relacionamento JPA que propaga automaticamente todas as operações (criar, atualizar, remover) da entidade "dona" para a entidade relacionada. |
| `orphanRemoval` | Configuração de um relacionamento JPA que remove automaticamente do banco uma entidade relacionada que deixou de estar associada à entidade "dona". |

### Projeções, SpEL e eventos

| Termo | Significado |
|---|---|
| Projeção (`@Projection`) | Recurso do Spring Data REST que define uma "vista" alternativa e reduzida (ou combinada) de uma entidade, expondo apenas um subconjunto de campos, ou até campos calculados, em vez do objeto completo. |
| `excerptProjection` | Atributo de `@RepositoryRestResource` que define qual projeção deve ser aplicada automaticamente sempre que os recursos daquele repositório aparecerem dentro de uma coleção (listagem). |
| SpEL (*Spring Expression Language*) | Pequena linguagem de expressões do Spring, escrita entre `#{...}`, usada para calcular valores dinamicamente (ex.: acessar um campo aninhado e chamar um método sobre ele). |
| `@Value` (Spring) | Anotação do Spring que associa a um método, campo ou parâmetro o resultado de uma expressão SpEL. **Atenção:** existe uma anotação homônima no Lombok (`lombok.Value`), com propósito totalmente diferente (torna uma classe imutável) — os dois imports não são intercambiáveis. |
| operador `?.` (*safe navigation*) | Operador usado dentro de expressões SpEL para acessar um campo apenas se o objeto não for `null`, evitando um erro em tempo de execução. |
| `@RepositoryEventHandler` | Anotação que marca uma classe como um *listener* de eventos disparados pelo Spring Data REST durante o ciclo de vida das operações de um repositório. |
| `@Component` (Spring) | Anotação genérica que registra uma classe como um bean gerenciado pelo Spring, para que ele seja instanciado e gerenciado automaticamente pelo container. |
| `@HandleAfterCreate` / `@HandleAfterSave` / `@HandleAfterDelete` | Anotações que marcam métodos, dentro de uma classe `@RepositoryEventHandler`, para serem chamados automaticamente depois que a respectiva operação (criar, salvar, remover) for concluída com sucesso. |
| SLF4J | *Simple Logging Facade for Java*: fachada padronizada para registro de logs em aplicações Java, independente da implementação concreta usada por trás. |
| `Logger` / `LoggerFactory` (SLF4J) | `LoggerFactory.getLogger(MinhaClasse.class)` cria um objeto `Logger` associado a uma classe, usado para registrar mensagens (`logger.warn(...)`, `logger.info(...)`, etc.) durante a execução. |

---

## Estado atual do projeto (checkpoint do Vídeo 03)

Este é o retrato fiel do código-fonte na etapa atual, conferido diretamente nos arquivos do seu `marketplace_ate_o_video_03.zip` — **não** na narrativa do README. Os três problemas descritos na seção "Pontos de atenção" (itens 1, 2 e 3) estão presentes exatamente como aparecem no seu projeto, sem correções aplicadas, para que este checkpoint reflita com exatidão o que você tem salvo. Onde relevante, a correção sugerida aparece logo abaixo do bloco, em uma nota.

### Estrutura de pastas

```
marketplace/
├── build.gradle
├── compose.yml
└── src/
    ├── main/
    │   ├── java/dio/marketplace/registration/
    │   │   ├── MarketplaceApplication.java
    │   │   ├── domain/
    │   │   │   ├── Customer.java
    │   │   │   ├── CustomerId.java
    │   │   │   └── CustomerRepository.java
    │   │   ├── application/                              (ainda vazio nesta etapa)
    │   │   └── infrastructure/persistence/
    │   │       ├── entity/
    │   │       │   ├── Customer.java
    │   │       │   ├── Address.java
    │   │       │   └── projection/
    │   │       │       └── CustomerExcept.java             (nome incorreto — ver nota 2)
    │   │       ├── repository/
    │   │       │   ├── CustomerEntityRepository.java
    │   │       │   └── JpaCustomerRepository.java
    │   │       └── event/
    │   │           └── CustomerEventHandler.java
    │   └── resources/
    │       └── application.properties
    └── test/                                                (ainda vazio nesta etapa)
```

### `infrastructure/persistence/repository/CustomerEntityRepository.java`

```java
package dio.marketplace.registration.infrastructure.persistence.repository;

import dio.marketplace.registration.infrastructure.persistence.entity.projection.CustomerExcerpt;
import io.micrometer.common.lang.NonNull; // import não utilizado — pode ser removido
import org.springframework.data.jpa.repository.JpaRepository;
import dio.marketplace.registration.infrastructure.persistence.entity.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource(excerptProjection = CustomerExcerpt.class)
public interface CustomerEntityRepository extends PagingAndSortingRepository<Customer, UUID>,
        JpaRepository<Customer, UUID> {

    List<Customer> findByFirstNameStartingWithIgnoreCase(@Param("firstName") String firstName);

    @Override
    @RestResource(exported = false)
    void deleteById(UUID id);
}
```

> Este arquivo só compilará depois que `CustomerExcept.java` for renomeado para `CustomerExcerpt.java` (nota 2 da seção anterior), já que ele importa `CustomerExcerpt` de dentro do pacote `projection`.

### `infrastructure/persistence/entity/projection/CustomerExcept.java`

```java
package dio.marketplace.registration.infrastructure.persistence.entity.projection;
import dio.marketplace.registration.infrastructure.persistence.entity.Customer;
import lombok.Value; // import incorreto — ver nota 3
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "excerpt", types = Customer.class)
public interface CustomerExcerpt {

    String getFirstName();

    String getLastName();

    @Value("#{target.address?.toString()}")
    String getAddress();

}
```

> **Correções necessárias antes de compilar:** (1) renomear o arquivo para `CustomerExcerpt.java`; (2) trocar o import por `org.springframework.beans.factory.annotation.Value`.

### `infrastructure/persistence/entity/Customer.java`

```java
package dio.marketplace.registration.infrastructure.persistence.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@RequiredArgsConstructor
public class Customer {
    @Id
    private UUID id;

    @NotBlank
    @Column(nullable = false)
    private String firstName;
    private String lastName;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdOn;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}
```

### `infrastructure/persistence/entity/Address.java`

```java
package dio.marketplace.registration.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@RequiredArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String street, postalCode, city, state;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Instant createdOn;

    public String toString() {
        return String.format("%s, %s %s, %s", street, postalCode, city, state);
    }
}
```

### `infrastructure/persistence/event/CustomerEventHandler.java`

```java
package dio.marketplace.registration.infrastructure.persistence.event;

import dio.marketplace.registration.infrastructure.persistence.entity.Customer;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;

@Component
RepositoryEventHandler // falta o "@" — ver nota 1
public class CustomerEventHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomerEventHandler.class);

    @HandleAfterCreate
    public void handleAfterCreate(Customer customer) {
        logger.warn("CustomerEventHandler#handleAfterCreate");
    }

    @HandleAfterSave
    public void handleAfterSave(Customer customer) {
        logger.warn("CustomerEventHandler#handleAfterSave");
    }

    @HandleAfterDelete
    public void handleAfterDelete(Customer customer) {
        logger.warn("CustomerEventHandler#handleAfterDelete");
    }
}
```

> **Correção necessária antes de compilar:** adicionar `@` antes de `RepositoryEventHandler`.

### `application.properties`

```properties
spring.application.name=marketplace

management.endpoint.health.show-details=always
spring.docker.compose.lifecycle-management=start-only

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### `build.gradle` (bloco `dependencies`)

```groovy
dependencies {
    testImplementation platform('org.junit:junit-bom:5.10.0')
    testImplementation 'org.junit.jupiter:junit-jupiter'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'

    implementation 'org.springframework:spring-core:6.2.8'
    developmentOnly 'org.springframework.boot:spring-boot-docker-compose'

    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    runtimeOnly 'com.mysql:mysql-connector-j'

    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-actuator'

    implementation 'org.springframework.boot:spring-boot-starter-validation'

    implementation 'org.springframework.boot:spring-boot-starter-data-rest'

    implementation 'org.springframework.data:spring-data-rest-hal-explorer'
}
```

`domain/Customer.java`, `domain/CustomerId.java`, `domain/CustomerRepository.java`, `JpaCustomerRepository.java` e `MarketplaceApplication.java` permanecem exatamente como no checkpoint do Vídeo 02 (documento anterior) — nenhuma alteração foi feita neles nesta etapa.

---

## Próximos passos: o que vem a partir do Vídeo 04

Segundo o roteiro do curso (conferido no README), a sequência dos próximos vídeos deste Curso 2 é:

- **Vídeo 04 — Flexibilidade com NoSQL:** deve introduzir o **MongoDB** e o **Spring Data MongoDB**, aplicando a mesma lógica de Repository já usada até aqui, mas para um banco não-relacional — provavelmente reaproveitando parte da modelagem de `Customer`/`Address` para mostrar como um documento NoSQL se compara a duas tabelas relacionadas.
- **Vídeo 05 — Multi-Database com Docker:** deve expandir o `compose.yml` para orquestrar múltiplos bancos ao mesmo tempo (o MySQL já configurado + o MongoDB do Vídeo 04, possivelmente já antecipando o Redis).
- **Vídeo 06 — Criando Endpoints Customizados:** deve ir além do que o Spring Data REST expõe automaticamente, criando endpoints sob medida — possivelmente com um `@RestController` escrito manualmente, complementando (e não substituindo) o que já foi exposto automaticamente neste vídeo.
- **Vídeo 07 — Implementando Redis com Spring Data:** deve introduzir o **Redis** e o mecanismo de *ticket locking* com TTL (expiração automática), mencionado no estudo de caso do Vídeo 01.
- **Vídeo 08 — Comunicação entre Microsserviços:** deve tratar de como diferentes módulos (ou serviços) do sistema trocam informações entre si.
- **Vídeo 09 — Implementando Persistência com Postgres:** deve retomar o PostgreSQL como o banco relacional "oficial" do estudo de caso original (o projeto `marketplace`, até aqui, usa MySQL como variação didática).
- **Vídeo 10 — Evitando Overbooking:** deve aplicar as ferramentas de concorrência (provavelmente o Redis já implementado) para resolver o problema de dois clientes tentarem reservar o mesmo assento ao mesmo tempo.
- **Vídeo 11 — Consistência e Governança:** deve fechar a parte de persistência com boas práticas de consistência de dados entre os múltiplos bancos usados.
- **Vídeo 12 — Questionário:** avaliação final do Curso 2.

> **Antes de seguir para o Vídeo 04:** vale aplicar as três correções listadas em "Pontos de atenção" (o `@` faltando em `CustomerEventHandler`, o nome do arquivo `CustomerExcept.java` → `CustomerExcerpt.java`, e o import trocado de `@Value`), garantindo que o projeto volte a compilar antes de continuar adicionando código novo por cima.

