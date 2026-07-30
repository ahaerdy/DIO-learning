# Tutorial de Estudos — Consumindo APIs Externas com o Spring Cloud OpenFeign

**Da persistência em memória ao primeiro Feign Client real — Vídeos 03 e 04**

- Curso: NTT Data — Jornada Tech (DIO) · Módulo 04 — Java/Spring com IA
- Curso 4 do módulo: "Consumindo APIs Externas com a Spring Cloud OpenFeign"
- Instrutor: Thiago Poiani (Principal Engineer at Skip)
- Projeto: `compliance`
- Documento de referência pessoal — nível iniciante em Java
- **Continuação direta de** `001-Tutorial_Compliance_OpenFeign_Videos01a02.md`

---

## Sobre este documento

Este é o segundo tutorial da série. Ele parte exatamente de onde o anterior parou (fim do Vídeo 02: classes de domínio prontas, mas nenhuma persistência real, nenhum endpoint HTTP e nenhuma chamada externa ainda funcionando) e documenta os **Vídeos 03 e 04**, com base:

- no README atualizado (anotações de aula até o Vídeo 04);
- no código-fonte real do projeto `compliance`, no estado correspondente ao `compliance_ate_o_video04.zip`.

O mesmo compromisso do documento anterior vale aqui: cada bloco de código é apresentado na ordem em que foi escrito na aula, seguido de explicação em nível iniciante — o que cada instrução faz, por que foi escrita daquela forma, e qual conceito de Java, Spring ou de arquitetura ela representa. Sempre que aparece uma anotação, classe da biblioteca padrão ou padrão de projeto ainda não visto no primeiro tutorial, ele é explicado do zero.

> **Importante sobre os checkpoints deste documento**
> O `.zip` enviado corresponde ao estado **final do Vídeo 04**. Isso significa que, para arquivos que o Vídeo 04 modificou (como `CompanyEventHandler`), não existe um "retrato" isolado de como eles ficaram exatamente ao final do Vídeo 03 — o zip já contém as mudanças do vídeo seguinte por cima. Nesses casos específicos, o checkpoint do Vídeo 03 é montado com base no README/transcrição daquele ponto da aula, e fica marcado explicitamente como tal; o checkpoint do Vídeo 04, esse sim, é 100% conferido contra o código real do `.zip`.

---

## Parte 3 — Vídeo 03: Modelando Empresas com Spring Data

O Vídeo 03 conecta, pela primeira vez, as classes de domínio criadas no Vídeo 02 (`Company`, `CompanyRepository`) a uma implementação real de persistência. Até aqui, `CompanyRepository` era apenas um contrato (uma interface) sem nenhuma classe que o implementasse de fato. Este vídeo resolve isso usando o **Spring Data KeyValue**, que já havia sido adicionado como dependência no Vídeo 02, mas ainda não tinha sido usado.

### 3.1. Organizando os pacotes da camada de persistência

Antes de criar qualquer classe, a aula organiza a estrutura de pastas dentro de `infrastructure`:

```
dio.compliance.infrastructure.persistence
├── entity          (classes que representam os dados efetivamente persistidos)
└── repository      (interfaces responsáveis por acessar/persistir essas entidades)
```

- **`entity`** — vai abrigar `CompanyEntity`, uma classe separada de `Company` (a classe de domínio). Isso é proposital: a classe de domínio (`Company`) representa o conceito de negócio "empresa", livre de qualquer detalhe técnico de persistência; a entidade (`CompanyEntity`) representa como esses dados são efetivamente guardados no mecanismo de armazenamento escolhido (nesse caso, um mapa em memória).
- **`repository`** — vai abrigar `CompanyEntityRepository`, a interface que efetivamente executa operações de persistência sobre `CompanyEntity`.

> **Por que não usar `Company` diretamente como entidade?**
> Separar `Company` (domínio) de `CompanyEntity` (persistência) evita que uma decisão técnica — por exemplo, trocar o armazenamento em memória por um banco relacional, ou anotar campos com anotações específicas de um ORM — vaze para dentro da classe de domínio. Se amanhã a persistência mudar de tecnologia, só `CompanyEntity` (e a lógica que converte entre as duas) precisa mudar; `Company` permanece intocada. Esse é o mesmo princípio de separação em camadas discutido na seção 2.1 do tutorial anterior, agora aplicado na prática.

### 3.2. Definindo a `CompanyEntity`

```java
package dio.compliance.infrastructure.persistence.entity;

import dio.compliance.domain.Company;
import dio.compliance.domain.CompanyId;
import dio.compliance.domain.RiskAssessment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;

import java.util.Optional;
import java.util.UUID;

@KeySpace("companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyEntity {
    @Id
    private UUID id;
    private String name, registrationNumber;
    private RiskAssessment riskAssessment;
}
```

Explicando cada parte:

- **`@KeySpace("companies")`** — anotação específica do **Spring Data Key/Value** (o módulo usado para persistência em memória). Ela define o nome do "espaço de chaves" onde os objetos dessa classe serão guardados — pense nele como o nome de uma tabela, mas em um armazenamento que é, na prática, um `Map` gigante em memória, e não um banco de dados relacional de verdade. Sem essa anotação, o Spring Data KeyValue não saberia sob qual "rótulo" agrupar os objetos `CompanyEntity` salvos.
- **`@Data`** (Lombok) — uma anotação "combo" do Lombok: ela gera, de uma vez só, os *getters* e *setters* de todos os campos, além de `toString()`, `equals()` e `hashCode()`. É mais abrangente do que o `@Getter` usado em `Company` no Vídeo 02 (que só gera getters) — aqui, como `CompanyEntity` é uma classe técnica de persistência (e não uma entidade de domínio que preza por imutabilidade), faz sentido ter também os *setters*, já que frameworks de persistência costumam precisar deles para popular objetos.
- **`@NoArgsConstructor`** (Lombok) — gera um construtor **sem argumentos** (`CompanyEntity()`). Isso é importante porque muitos frameworks (incluindo o Spring Data) precisam conseguir criar uma instância "vazia" da classe primeiro, para depois preencher os campos um a um (via *setters* ou reflexão) — um construtor que já exige todos os valores de uma vez não serve para esse propósito.
- **`@AllArgsConstructor`** (Lombok) — já visto no Vídeo 02, em `Company`: gera um construtor que recebe todos os campos como parâmetros, na ordem em que foram declarados.
- **`@Id`** (`org.springframework.data.annotation.Id`) — anotação do Spring Data que marca qual campo da classe deve ser tratado como **identificador único** do registro. É genérica o suficiente para funcionar com diferentes módulos do Spring Data (KeyValue, JPA, MongoDB etc.) — cada um usa essa marcação da forma que faz sentido para sua tecnologia de armazenamento.
- **`private String name, registrationNumber;`** — uma sintaxe do Java pouco usada, mas válida: é possível declarar **duas ou mais variáveis do mesmo tipo em uma única linha**, separando os nomes por vírgula. É equivalente a escrever `private String name; private String registrationNumber;` em duas linhas — só uma forma mais compacta de escrever quando os tipos são idênticos.
- **`private RiskAssessment riskAssessment;`** — aqui a entidade reaproveita diretamente a classe `RiskAssessment` do domínio (Vídeo 02), em vez de criar uma versão própria para persistência. A aula é explícita sobre essa ser uma escolha possível justamente por se tratar de armazenamento em memória; com um banco relacional de verdade (via JPA), esse relacionamento provavelmente seria modelado como uma tabela separada, ou como um objeto embutido usando a anotação `@Embedded`.

### 3.3. Convertendo domínio → entidade: o método `from`

```java
public static CompanyEntity from(Company company) {
    return new CompanyEntity(
            company.getId().id(),
            company.getName(),
            company.getRegistrationNumber(),
            company.getRiskAssessment().orElse(null));
}
```

- **`public static CompanyEntity from(Company company)`** — um método **estático** (não precisa de uma instância de `CompanyEntity` para ser chamado; chama-se diretamente como `CompanyEntity.from(...)`) que recebe um objeto de domínio (`Company`) e devolve o objeto de persistência (`CompanyEntity`) correspondente. Esse é um padrão bastante comum em Java chamado **método de fábrica estático** (*static factory method*): em vez de usar `new CompanyEntity(...)` diretamente em vários lugares do código, centraliza-se a lógica de conversão em um único método com nome descritivo.
- **`company.getId().id()`** — dois passos: `company.getId()` retorna o `CompanyId` (o identificador fortemente tipado visto no Vídeo 02); `.id()` é o *accessor* automático gerado pelo `record CompanyId(UUID id)` para acessar o `UUID` "cru" guardado dentro dele. Isso é necessário porque `CompanyEntity.id` é um `UUID` puro, não um `CompanyId` — a entidade de persistência, aqui, opta por não usar o identificador fortemente tipado.
- **`company.getRiskAssessment().orElse(null)`** — como visto no Vídeo 02, `Company.riskAssessment` é um `Optional<RiskAssessment>`, porque uma empresa recém-criada pode ainda não ter sido avaliada. O método `.orElse(null)` do `Optional` funciona assim: se houver um valor presente dentro do `Optional`, ele é retornado; se não houver (`Optional` vazio), o valor informado como argumento — aqui, `null` — é retornado no lugar. Isso é necessário porque `CompanyEntity.riskAssessment` é um `RiskAssessment` puro (sem `Optional` ao redor), então é preciso "desembrulhar" o valor antes de atribuí-lo, aceitando que ele pode ser `null` nesse ponto.

### 3.4. Convertendo entidade → domínio: o método `toDomain`

```java
public Company toDomain() {
    return new Company(
            new CompanyId(this.getId()),
            this.getName(),
            this.registrationNumber,
            Optional.ofNullable(getRiskAssessment())
    );
}
```

- **`public Company toDomain()`** — o caminho inverso do método `from`: converte uma `CompanyEntity` (persistência) de volta em um `Company` (domínio). Diferente de `from`, este método **não é estático** — ele é chamado a partir de um objeto `CompanyEntity` já existente (`entity.toDomain()`), já que faz sentido pensar nele como "transforme a mim mesma em um objeto de domínio".
- **`new CompanyId(this.getId())`** — o caminho inverso do que foi feito em `from`: pega o `UUID` puro guardado na entidade e o envolve de volta em um `CompanyId`, reconstruindo o identificador fortemente tipado esperado pela classe de domínio.
- **`Optional.ofNullable(getRiskAssessment())`** — `Optional.ofNullable(valor)` é um método estático da classe `Optional` que cria um `Optional` a partir de um valor que **pode ser `null`**: se o valor passado não for `null`, o `Optional` resultante contém esse valor; se for `null`, o `Optional` resultante vem vazio (equivalente a `Optional.empty()`). É o inverso conceitual do `.orElse(null)` usado em `from`: lá, "desembrulhava-se" um `Optional` aceitando `null`; aqui, "embrulha-se" um valor que pode ser `null` de volta em um `Optional`, já que a classe de domínio exige esse tipo no campo `riskAssessment`.

> **Por que ter os dois métodos, `from` e `toDomain`, na própria `CompanyEntity`?**
> Colocar a lógica de conversão dentro da própria classe de persistência (em vez de, por exemplo, em uma classe separada de "mapper" ou dentro do repositório) é uma escolha de organização: `CompanyEntity` passa a saber, sozinha, como se transformar a partir de um `Company` e como se transformar de volta em um. Isso mantém a lógica de mapeamento em um único lugar, fácil de localizar sempre que os campos de uma das duas classes mudarem.

### 3.5. Criando o `CompanyEntityRepository` e expondo-o via Spring Data REST

```java
package dio.compliance.infrastructure.persistence.repository;

import dio.compliance.infrastructure.persistence.entity.CompanyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(path = "companies")
public interface CompanyEntityRepository extends CrudRepository<CompanyEntity, UUID> {
}
```

- **`interface CompanyEntityRepository extends CrudRepository<CompanyEntity, UUID>`** — a interface não declara nenhum método próprio; ela apenas **estende** (herda de) `CrudRepository<CompanyEntity, UUID>`. `CrudRepository<T, ID>` é uma interface do Spring Data que já traz, prontas, as operações básicas de CRUD (*Create, Read, Update, Delete*): `save(entity)`, `findAll()`, `findById(id)`, `existsById(id)`, `count()`, `deleteById(id)`, entre outras. Os dois parâmetros genéricos dizem ao Spring **qual entidade** (`CompanyEntity`) e **qual o tipo do identificador dela** (`UUID`) essa interface gerencia. O Spring Data, em tempo de execução, gera automaticamente uma classe que implementa toda essa interface — não é necessário escrever nenhuma implementação manual, nem mesmo do `save`.
- **`@RepositoryRestResource(path = "companies")`** — anotação do módulo **Spring Data REST** (adicionado como dependência `spring-boot-starter-data-rest` no Vídeo 02, mas usado pela primeira vez agora). Ela expõe automaticamente esse repositório como uma **API REST completa**, sem que seja necessário escrever nenhum `@RestController` manualmente. O parâmetro `path = "companies"` define a URL base desse recurso (`/companies`). É essa única anotação que já disponibiliza endpoints de criação, listagem e consulta por ID.

> **CrudRepository é um exemplo prático do padrão Repository**
> No Vídeo 02, o glossário já explicava o padrão Repository de forma abstrata: "abstrai o armazenamento de dados atrás de uma interface". `CrudRepository` é a materialização concreta desse padrão dentro do Spring Data — uma interface genérica e reutilizável que qualquer projeto pode estender para ganhar, de graça, as operações mais comuns de persistência.

### 3.6. Testando a API gerada automaticamente (HATEOAS)

Com a aplicação rodando, uma requisição `GET` para a raiz (`http://localhost:8080`) já retorna algo assim:

```json
{
  "_links": {
    "companyEntities": { "href": "http://localhost:8080/companies{?page,size,sort}" },
    "profile": { "href": "http://localhost:8080/profile" }
  }
}
```

Esse formato de resposta — dados acompanhados de um bloco `_links`, com referências a outros recursos relacionados — é o padrão **HATEOAS** (*Hypermedia as the Engine of Application State*). A ideia central do HATEOAS é que uma API REST não deveria exigir que o cliente já saiba de antemão todas as URLs disponíveis: a própria resposta traz links que permitem "descobrir" quais recursos existem e como acessá-los, de forma navegável — parecido com clicar em links em uma página web.

Seguindo o link `companyEntities`, uma requisição `GET /companies` (com o banco em memória ainda vazio) retorna:

```json
{
  "_embedded": { "companyEntities": [] },
  "_links": { "self": { "href": "http://localhost:8080/companies" }, "profile": { "href": "..." } }
}
```

E uma requisição `POST /companies` com o corpo abaixo:

```http
POST http://localhost:8080/companies
Accept: application/json

{
  "name": "Logistics",
  "registrationNumber": "REG-1234"
}
```

retorna status **201 Created**, junto com os links `self` e `companyEntity` apontando para a URL do recurso recém-criado, e os dados `name`/`registrationNumber` persistidos. Tudo isso — criação, listagem e consulta por ID — funciona sem que **nenhuma linha de código de controller** tenha sido escrita manualmente.

> **De onde vem o status 201?**
> Relembrando a "Tabela Periódica da Semântica Web" do Vídeo 01: o verbo `POST`, quando bem-sucedido, deve retornar a faixa `2xx`, especificamente `201` ("Recurso Criado com Sucesso"). O Spring Data REST já segue essa convenção HTTP automaticamente, sem que o desenvolvedor precise configurar nada a respeito.

### 3.7. Implementando o `InMemoryCompanyRepository`

Com a API já expondo `CompanyEntity` diretamente, falta ainda ligar isso ao contrato de domínio `CompanyRepository`, criado no Vídeo 02. É para isso que existe a classe `InMemoryCompanyRepository`:

```java
package dio.compliance.infrastructure.persistence.repository;

import dio.compliance.domain.Company;
import dio.compliance.domain.CompanyRepository;
import dio.compliance.infrastructure.persistence.entity.CompanyEntity;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryCompanyRepository implements CompanyRepository {
    private final CompanyEntityRepository repository;

    public InMemoryCompanyRepository(CompanyEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Company save(Company company) {
        var entity = CompanyEntity.from(company);
        var savedEntity = repository.save(entity);
        return savedEntity.toDomain();
    }
}
```

- **`public class InMemoryCompanyRepository implements CompanyRepository`** — esta é a primeira classe do projeto que efetivamente **implementa** a interface `CompanyRepository` do domínio (Vídeo 02). Até este vídeo, `CompanyRepository` era só um contrato sem nenhuma implementação real por trás.
- **`private final CompanyEntityRepository repository;`** e o **construtor** que recebe `CompanyEntityRepository repository` como parâmetro — este é exatamente o mecanismo de **injeção de dependência** já visto na teoria do Vídeo 02: em vez de `InMemoryCompanyRepository` criar sua própria instância de `CompanyEntityRepository` com `new`, ela recebe uma instância pronta de fora, via construtor. O Spring, ao encontrar uma interface anotada como `@RepositoryRestResource` que estende `CrudRepository`, já sabe gerar uma implementação e disponibilizá-la para ser injetada aqui.
- **`@Override`** — anotação padrão do Java (não específica do Spring) que marca explicitamente que um método está **sobrescrevendo** um método declarado em uma interface (ou superclasse). Ela não muda o comportamento do código, mas ajuda o compilador a avisar caso a assinatura do método não bata exatamente com a da interface (por exemplo, por um erro de digitação no nome ou nos tipos dos parâmetros).
- **Dentro do `save`:**
  - `var entity = CompanyEntity.from(company);` — usa o método de fábrica estático da seção 3.3 para converter o `Company` de domínio recebido em uma `CompanyEntity` de persistência.
  - `var savedEntity = repository.save(entity);` — aqui, `repository` é o `CompanyEntityRepository` injetado (o `CrudRepository` gerado automaticamente pelo Spring Data). Chamar `.save(entity)` nele efetivamente persiste os dados no mapa em memória, e devolve a entidade salva (possivelmente com o `id` já preenchido, se ainda não existisse).
  - `return savedEntity.toDomain();` — converte a entidade recém-salva de volta em um objeto de domínio (`Company`), usando o método da seção 3.4, e devolve esse objeto de domínio como resultado do `save` — cumprindo exatamente a assinatura definida na interface `CompanyRepository` (`Company save(Company company)`).
- **`@Repository`** — uma das anotações do Spring (junto com `@Component` e `@Service`, que aparecerão a seguir) que habilitam a **injeção de dependência**. Todas elas fazem, na prática, a mesma coisa técnica: dizem ao Spring "esta classe deve virar um *bean* gerenciado pelo container, disponível para ser injetado em outros lugares". A diferença entre `@Repository`, `@Service` e `@Component` é, sobretudo, semântica — cada uma comunica a intenção da classe (acesso a dados, regra de negócio, componente genérico, respectivamente), embora `@Repository` também ative um tratamento especial de exceções de persistência por trás dos panos.

> **Por que isso é "inversão de controle"?**
> A partir de agora, quando qualquer outra classe do projeto declarar uma dependência do tipo `CompanyRepository` (a interface do domínio) no seu construtor, o Spring já sabe automaticamente que deve injetar uma instância de `InMemoryCompanyRepository` ali — mesmo que a classe que está recebendo a dependência nunca mencione `InMemoryCompanyRepository` por nome. Quem decide qual implementação usar não é mais a classe que consome (`Company` continua dependendo apenas do contrato `CompanyRepository`); é o *container* do Spring, de fora. Essa troca de "quem decide" é exatamente o que o termo **inversão de controle** descreve.

### 3.8. Criando o `CompanyEventHandler` e reagindo ao evento `afterCreate`

O último passo do Vídeo 03 aproveita um recurso do Spring Data REST: **eventos de repositório**. Sempre que uma operação de CRUD acontece (criação, atualização, exclusão), o Spring Data REST dispara eventos correspondentes, que podem ser "escutados" por classes específicas.

```java
package dio.compliance.infrastructure.persistence.event;

import dio.compliance.infrastructure.persistence.entity.CompanyEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class CompanyEventHandler {
    private static final Logger LOG = LoggerFactory.getLogger(CompanyEventHandler.class);

    @HandleAfterCreate
    public void handleAfterCreateEvent(CompanyEntity entity) {
        LOG.info("handleAfterCreateEvent {}", entity);
    }
}
```

- **`@Component`** — a forma mais genérica das três anotações de injeção de dependência mencionadas na seção 3.7. Usada aqui porque `CompanyEventHandler` não é nem um repositório nem um serviço de regra de negócio no sentido estrito — é um "escutador de eventos", um papel mais próximo de infraestrutura técnica.
- **`@RepositoryEventHandler`** — anotação de classe do Spring Data REST que marca `CompanyEventHandler` como uma classe capaz de reagir a eventos de repositórios. Sozinha, ela não faz nada — precisa ser combinada com anotações de método, como a próxima.
- **`@HandleAfterCreate`** — anotação de método que diz ao Spring: "execute este método automaticamente, sempre que uma nova entidade for criada com sucesso através de um repositório exposto via Spring Data REST". O parâmetro do método (`CompanyEntity entity`) é preenchido automaticamente pelo Spring com a entidade recém-criada.
- **Por que o parâmetro é `CompanyEntity`, e não `Company`?** — porque esses eventos pertencem à camada de persistência do Spring Data: eles disparam em cima do que foi de fato salvo no repositório (a entidade), não em cima de um conceito abstrato de domínio. É por isso que a conversão de volta para `Company`, usando `entity.toDomain()`, será necessária assim que a lógica de negócio precisar entrar em cena (o que acontece já no Vídeo 04).
- **`private static final Logger LOG = LoggerFactory.getLogger(CompanyEventHandler.class);`** — um padrão extremamente comum em projetos Java/Spring para registrar mensagens de log. `Logger` é uma interface da biblioteca **SLF4J** (*Simple Logging Facade for Java*), um "padrão comum" de logging que várias implementações de bibliotecas de log seguem por trás. `LoggerFactory.getLogger(CompanyEventHandler.class)` cria um logger identificado com o nome da própria classe — assim, toda mensagem registrada por ele já vem etiquetada na origem, facilitando encontrar de onde ela veio quando o console tiver muitas linhas.
  - `static` — assim como em `determineRiskLevel` no Vídeo 02, indica que o campo `LOG` pertence à classe como um todo, não a cada instância individual — não faz sentido ter um logger diferente por objeto criado.
  - `final` — indica que, uma vez atribuído, o valor de `LOG` nunca mais muda. Combinado com `static`, é o padrão usual para constantes de classe em Java.
- **`LOG.info("handleAfterCreateEvent {}", entity);`** — registra uma mensagem de log no nível `INFO` (um dos níveis de severidade do log, ao lado de `DEBUG`, `WARN`, `ERROR` etc.). O `{}` dentro da string é um **placeholder**: o SLF4J substitui automaticamente cada `{}` pelos argumentos passados em seguida (aqui, pelo `entity`), evitando concatenação manual de strings.

Ao reiniciar a aplicação e enviar novamente o `POST /companies`, o console mostra a linha de log gerada por `handleAfterCreateEvent`, confirmando que o evento `afterCreate` foi disparado corretamente logo após a persistência. Esse gatilho — disparado automaticamente após a criação de uma empresa, sem precisar de nenhum endpoint dedicado a isso — é exatamente o ponto de partida do Vídeo 04.

### Checkpoint do Vídeo 03

> **Nota sobre este checkpoint:** como explicado na introdução deste documento, o `.zip` enviado reflete o estado **após o Vídeo 04**. Os arquivos `CompanyEntity`, `CompanyEntityRepository` e `InMemoryCompanyRepository` **não foram alterados** pelo Vídeo 04, então o conteúdo abaixo já é 100% conferido diretamente no código real. Já `CompanyEventHandler` **foi modificado** no Vídeo 04 (ganhou a chamada ao use case) — a versão mostrada abaixo é a versão de fechamento do Vídeo 03, reconstruída a partir do README/aula; a versão real e final desse arquivo, conferida no `.zip`, aparece no checkpoint do Vídeo 04, logo a seguir.

**Estrutura de pastas nova nesta etapa** (confirmada no `.zip`):

```
dio.compliance.infrastructure
└── persistence/
    ├── entity/
    │   └── CompanyEntity.java
    ├── repository/
    │   ├── CompanyEntityRepository.java
    │   └── InMemoryCompanyRepository.java
    └── event/
        └── CompanyEventHandler.java   (versão do Vídeo 03, sem o use case ainda)
```

`infrastructure/persistence/entity/CompanyEntity.java` — conferido no `.zip`:

```java
package dio.compliance.infrastructure.persistence.entity;

import dio.compliance.domain.Company;
import dio.compliance.domain.CompanyId;
import dio.compliance.domain.RiskAssessment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;

import java.util.Optional;
import java.util.UUID;

@KeySpace("companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyEntity {
    @Id
    private UUID id;
    private String name, registrationNumber;
    private RiskAssessment riskAssessment;

    public static CompanyEntity from(Company company) {
        return new CompanyEntity(
                company.getId().id(),
                company.getName(),
                company.getRegistrationNumber(),
                company.getRiskAssessment().orElse(null));
    }

    public Company toDomain() {
        return new Company(
                new CompanyId(this.getId()),
                this.getName(),
                this.registrationNumber,
                Optional.ofNullable(getRiskAssessment())
        );
    }
}
```

`infrastructure/persistence/repository/CompanyEntityRepository.java` — conferido no `.zip`:

```java
package dio.compliance.infrastructure.persistence.repository;

import dio.compliance.infrastructure.persistence.entity.CompanyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(path = "companies")
public interface CompanyEntityRepository extends CrudRepository<CompanyEntity, UUID> {
}
```

`infrastructure/persistence/repository/InMemoryCompanyRepository.java` — conferido no `.zip`:

```java
package dio.compliance.infrastructure.persistence.repository;

import dio.compliance.domain.Company;
import dio.compliance.domain.CompanyRepository;
import dio.compliance.infrastructure.persistence.entity.CompanyEntity;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryCompanyRepository implements CompanyRepository {
    private final CompanyEntityRepository repository;

    public InMemoryCompanyRepository(CompanyEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Company save(Company company) {
        var entity = CompanyEntity.from(company);
        var savedEntity = repository.save(entity);
        return savedEntity.toDomain();
    }
}
```

`infrastructure/persistence/event/CompanyEventHandler.java` — **versão reconstruída a partir do README** (fechamento do Vídeo 03, antes das mudanças do Vídeo 04):

```java
package dio.compliance.infrastructure.persistence.event;

import dio.compliance.infrastructure.persistence.entity.CompanyEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class CompanyEventHandler {
    private static final Logger LOG = LoggerFactory.getLogger(CompanyEventHandler.class);

    @HandleAfterCreate
    public void handleAfterCreateEvent(CompanyEntity entity) {
        LOG.info("handleAfterCreateEvent {}", entity);
    }
}
```

---

## Parte 4 — Vídeo 04: Estruturando Use Cases

O Vídeo 04 tem dois objetivos: (1) organizar a camada `application` com o padrão **use case**, ligando o evento `afterCreate` do Vídeo 03 a uma primeira regra de negócio real, e (2) introduzir, finalmente, o **Spring Cloud OpenFeign** — o assunto que dá nome ao curso — criando o primeiro cliente REST declarativo do projeto.

### 4.1. Criando a classe `AnalyzeCompanyRiskUseCase`

Dentro do pacote `application` (na aula; no projeto real, ainda grafado `appicantion` — ver seção de "Pontos de atenção" abaixo), é criada a primeira classe de caso de uso do projeto:

```java
package dio.complianceApplication;

import org.springframework.stereotype.Service;

@Service
public class AnalyzeCompanyRiskUseCase {

    public void execute() {

    }
}
```

- **Padrão *use case*** — um conceito de arquitetura (associado à Clean Architecture) em que cada regra de negócio ganha sua própria classe, com uma única responsabilidade bem definida. Isso se opõe ao padrão mais tradicional de uma única classe de "serviço" genérica, com vários métodos (`save`, `find`, `delete`, `validate` etc.) misturados. A vantagem é que cada use case fica pequeno, fácil de entender isoladamente, e com um nome que já descreve exatamente o que ele faz — aqui, `AnalyzeCompanyRiskUseCase` ("analisar o risco de uma empresa").
- **`@Service`** — já mencionada na seção 3.7: uma das anotações de injeção de dependência do Spring, usada por convenção em classes que representam regras de negócio (em oposição a `@Repository`, usada em classes de acesso a dados, e `@Component`, mais genérica).
- **Convenção do método `execute`** — por convenção adotada nesta aula (não é uma exigência da linguagem nem do Spring), todo use case expõe um único método público chamado `execute`, que concentra toda a regra de negócio daquele caso específico. Isso torna previsível como qualquer use case do projeto deve ser chamado, não importa o que ele faça internamente.

### 4.2. Um passo intermediário: instanciando manualmente com `new`

Antes de usar injeção de dependência de fato, a aula mostra — como exercício didático — a forma "errada" (ou pelo menos não recomendada) de conectar o use case ao `CompanyEventHandler`:

```java
@Component
@RepositoryEventHandler
public class CompanyEventHandler {
    private static final Logger LOG = LoggerFactory.getLogger(CompanyEventHandler.class);

    private final AnalyzeCompanyRiskUseCase analyzeCompanyRiskUseCase;

    public CompanyEventHandler() {
        this.analyzeCompanyRiskUseCase = new AnalyzeCompanyRiskUseCase();
    }

    @HandleAfterCreate
    public void handleAfterCreateEvent(CompanyEntity entity) {
        LOG.info("handleAfterCreateEvent {}", entity);
    }
}
```

- **`this.analyzeCompanyRiskUseCase = new AnalyzeCompanyRiskUseCase();`** — dentro do construtor **sem parâmetros**, uma instância de `AnalyzeCompanyRiskUseCase` é criada manualmente com a palavra-chave `new`. Isso funciona, mas tem um problema: `CompanyEventHandler` passa a ser responsável por saber *como* construir um `AnalyzeCompanyRiskUseCase`. Se, no futuro, essa classe passar a exigir outras dependências no construtor (como de fato vai acontecer já na seção 4.13), **todo lugar que faz `new AnalyzeCompanyRiskUseCase()` manualmente vai quebrar** e precisar ser atualizado.

> **Por que a aula mostra o jeito "errado" antes do certo?**
> É uma escolha pedagógica: ver explicitamente o problema (acoplamento rígido, causado pelo `new` manual) torna mais claro *por que* a injeção de dependência, no próximo passo, é preferível — em vez de simplesmente apresentar a "resposta certa" sem mostrar o que ela evita.

### 4.3. Ajustando para injeção de dependência de verdade

```java
private final AnalyzeCompanyRiskUseCase analyzeCompanyRiskUseCase;

public CompanyEventHandler(AnalyzeCompanyRiskUseCase analyzeCompanyRiskUseCase) {
    this.analyzeCompanyRiskUseCase = analyzeCompanyRiskUseCase;
}
```

O construtor passa a **receber** o use case já pronto, como parâmetro — em vez de criá-lo internamente com `new`. Como `AnalyzeCompanyRiskUseCase` já está anotada com `@Service`, o Spring já sabe como instanciá-la (e, futuramente, como resolver qualquer dependência que ela venha a precisar) e a injeta automaticamente aqui, sem que `CompanyEventHandler` precise saber nada sobre como esse objeto é montado por dentro. Esse é o mesmo mecanismo de injeção via construtor já usado em `InMemoryCompanyRepository` (seção 3.7).

### 4.4. Chamando o use case a partir do evento de criação

```java
@HandleAfterCreate
public void handleAfterCreateEvent(CompanyEntity entity) {
    LOG.info("handleAfterCreateEvent {}", entity);
    this.analyzeCompanyRiskUseCase.execute(entity.toDomain());
}
```

Dentro do método que já reagia ao evento `afterCreate` (Vídeo 03), é adicionada a chamada ao use case, passando **`entity.toDomain()`** — o método de conversão criado na seção 3.4 — em vez da própria `entity`. A regra reforçada aqui: dentro das camadas `application` e `domain`, sempre se trabalha com classes de domínio (`Company`), nunca com classes de persistência (`CompanyEntity`). A conversão acontece exatamente na fronteira entre as duas camadas — dentro do `CompanyEventHandler`, que é código de infraestrutura.

### 4.5. Preparando os pontos de verificação KYC e AML no `execute`

```java
package dio.compliance.application;

import dio.compliance.domain.Company;
import org.springframework.stereotype.Service;

@Service
public class AnalyzeCompanyRiskUseCase {

    public void execute(Company domain) {

        // KYC -> San
        // AML

    }
}
```

O método `execute` passa a receber `Company domain` como parâmetro — o dado de negócio que essa regra vai processar. Os dois comentários (`// KYC -> San`, `// AML`) são apenas marcadores deixados pela aula, indicando as duas verificações que a regra de negócio vai realizar: uma consulta de **KYC** (que inclui checagem de sanções, "San" sendo a abreviação usada no comentário) e uma checagem de **AML**. Comentários assim são comuns durante o desenvolvimento incremental: servem como um "TODO" informal, marcando onde a próxima etapa vai encaixar código.

### 4.6. Simulando a API externa com o Mockoon

Antes de consumir uma API externa de verdade — que, no contexto do curso, ainda nem existe, já que se trata de uma simulação —, é necessário ter algo para consumir. Para isso, a aula usa o **Mockoon**, uma ferramenta desktop que permite criar e rodar APIs REST simuladas ("mockadas") localmente, sem exigir implantação remota ou conta de usuário. A aula cita, como alternativas ao Mockoon, o **WireMock** e o próprio **Postman** (que também tem recursos de mock).

> **Por que simular a API em vez de usar uma real, como o OpenSanctions citado no Vídeo 01?**
> Simular a API externa dá controle total sobre os cenários testados — inclusive cenários difíceis de reproduzir com uma API real, como respostas lentas ou erros específicos (assunto dos Vídeos 06 e 08). Isso também evita depender de credenciais, limites de uso ou disponibilidade de um serviço de terceiros só para estudar o consumo de APIs.

Depois de confirmar que a "Demo API" padrão do Mockoon (rodando em `localhost:3000`) está funcionando com uma requisição de teste, um **novo ambiente** é criado e salvo com o nome **"KYC"**. Dentro dele, é configurada a primeira rota mocada:

```json
{
  "matches": []
}
```

Essa é a resposta configurada para a rota `GET /sanctions/companies/:registrationNumber`, com status `200`, representando o cenário "empresa sem riscos" (nenhuma sanção encontrada). O ambiente KYC é configurado para rodar na porta `3001` (diferente da Demo API, em `3000`), e o servidor local é iniciado. Uma requisição de teste confirma que o mock responde corretamente nessa porta.

> **Sintaxe da rota `:registrationNumber`**
> O `:` antes de `registrationNumber` na definição da rota do Mockoon é a forma como essa ferramenta declara um **parâmetro de caminho** (*path parameter*) — um trecho variável da URL. É o mesmo conceito que, no Spring, é declarado com `{registrationNumber}` entre chaves (como será visto na seção 4.11) — só a sintaxe de declaração muda entre as duas ferramentas.

### 4.7. Adicionando o Spring Cloud como dependência

O Spring Cloud OpenFeign não faz parte do conjunto padrão de *starters* do Spring Boot — ele pertence a um ecossistema maior, o **Spring Cloud**, distribuído em um repositório próprio, com seu próprio ciclo de versões. Por isso, adicioná-lo exige alguns passos extras no `build.gradle`.

Primeiro, uma variável de versão:

```groovy
ext {
    set('springCloudVersion', "2025.1.1")
}
```

- **`ext { ... }`** — um bloco do Gradle (a ferramenta de build usada no projeto) que permite declarar **propriedades extras** customizadas, disponíveis para uso no restante do arquivo. Aqui, `springCloudVersion` é definida uma única vez e reaproveitada mais adiante — uma boa prática para evitar repetir o mesmo número de versão em múltiplos lugares.
- A aula descobre esse número de versão consultando o **Spring Initializr** (o mesmo gerador de projetos usado para criar o `compliance` no Vídeo 02), ao gerar um projeto de exemplo com a dependência OpenFeign selecionada — o `build.gradle` gerado por ele já revela a versão correta e o formato esperado dos blocos necessários.

Em seguida, a dependência propriamente dita é adicionada ao bloco `dependencies`:

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'

    implementation 'org.springframework.data:spring-data-keyvalue'
    implementation 'org.springframework.boot:spring-boot-starter-data-rest'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-actuator'

    implementation 'org.springframework.cloud:spring-cloud-starter-openfeign'
}
```

E, por fim, um novo bloco, **fora** de `dependencies`:

```groovy
dependencyManagement {
    imports {
        mavenBom "org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"
    }
}
```

- **`dependencyManagement { ... }`** — um bloco disponibilizado pelo plugin `io.spring.dependency-management` (já presente no projeto desde o Vídeo 02, junto com o plugin do Spring Boot). Ele permite importar um **BOM** (*Bill of Materials* — "lista de materiais", em tradução livre) de um conjunto de bibliotecas relacionadas.
- **BOM (`mavenBom`)** — um artefato especial que não contém código, apenas uma lista de versões compatíveis entre si para um conjunto de bibliotecas. Ao importar o BOM do `spring-cloud-dependencies`, o Gradle passa a saber, automaticamente, qual versão específica de `spring-cloud-starter-openfeign` (e de qualquer outra biblioteca do Spring Cloud usada no projeto) é compatível com a versão `2025.1.1` declarada em `springCloudVersion` — sem que seja necessário escrever o número da versão diretamente ao lado de cada dependência do Spring Cloud individualmente.
- **`${springCloudVersion}`** — sintaxe de **interpolação de string** do Groovy (a linguagem em que o `build.gradle` deste projeto está escrito): o valor da variável `springCloudVersion`, definida no bloco `ext`, é inserido diretamente dentro da string do BOM.

### 4.8. Habilitando os Feign Clients na aplicação

```java
@SpringBootApplication
@EnableMapRepositories
@EnableFeignClients
public class ComplianceApplication {

    public static void main(String[] args) { SpringApplication.run(ComplianceApplication.class, args); }

}
```

- **`@EnableFeignClients`** — a primeira anotação específica do OpenFeign usada no projeto. Ela avisa ao Spring que deve **escanear** o projeto em busca de interfaces marcadas como Feign clients (a próxima seção mostra como marcar uma) e, para cada uma encontrada, **gerar automaticamente** uma implementação concreta que sabe fazer as chamadas HTTP correspondentes. Sem essa anotação na classe principal, nenhuma interface `@FeignClient` seria detectada, mesmo que estivesse corretamente escrita.
- Repare que essa anotação segue exatamente o mesmo padrão de `@EnableMapRepositories` (Vídeo 02): ambas são anotações "liga-desliga" que habilitam um mecanismo de escaneamento automático de um módulo específico do Spring — uma para repositórios em memória, outra para clientes Feign.

### 4.9. Organizando o pacote do REST client

Dentro de `infrastructure`, é criado o pacote `rest`, e dentro dele o subpacote `client`, formando `dio.compliance.infrastructure.rest.client`. O termo **REST client** é o nome usual, na indústria, para o componente responsável por fazer requisições HTTP para uma API externa a partir da própria aplicação — é essa nomenclatura que a estrutura de pastas do projeto passa a refletir explicitamente.

### 4.10. Criando o Feign Client `SanctionClient`

```java
package dio.compliance.infrastructure.rest.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "sanction-client", url = "http://localhost:3001")
public interface SanctionClient {
}
```

- **`interface SanctionClient`** — repare que, assim como `CompanyEntityRepository` (seção 3.5), este é apenas um **contrato**: uma interface, sem nenhum corpo de método implementado. O Spring Cloud OpenFeign vai gerar a implementação dela em tempo de execução, exatamente como o Spring Data gera a implementação de um `CrudRepository`.
- **`@FeignClient(name = "sanction-client", url = "http://localhost:3001")`** — a anotação central do OpenFeign, que transforma essa interface comum em um Feign client de verdade:
  - **`name`** — um identificador lógico para esse client (`"sanction-client"`). É usado internamente pelo Spring Cloud, entre outras coisas, para fins de log e (em cenários mais avançados, fora do escopo deste vídeo) para descoberta de serviços.
  - **`url`** — o **endereço base** do serviço que esse client vai consumir. Aqui, aponta diretamente para o mock KYC criado no Mockoon, rodando na porta `3001`. Em cenários com descoberta de serviços automática (*service discovery*), esse parâmetro poderia ser omitido; como o projeto ainda não usa nada disso, a URL é informada de forma explícita e fixa.

> **Só essa anotação já basta?**
> Sim, para o comportamento básico. O OpenFeign já traz, só com `@FeignClient`, toda a configuração necessária para abrir conexões HTTP, montar a requisição e tratar a resposta. Comportamentos mais específicos (timeouts customizados, interceptadores de requisição, decodificadores de erro próprios — temas que aparecem nos Vídeos 05, 06 e 08) podem ser adicionados depois, através de uma classe de configuração própria, sem precisar reescrever o client do zero.

### 4.11. Declarando o método `getCompanyRisk`

```java
@FeignClient(name = "sanction-client", url = "http://localhost:3001")
public interface SanctionClient {

    @GetMapping("/sanctions/companies/{registrationNumber}")
    void getCompanyRisk(@PathVariable String registrationNumber);
}
```

- **`@GetMapping("/sanctions/companies/{registrationNumber}")`** — anotação do Spring Web (já usada de forma equivalente em qualquer `@RestController` tradicional) que associa este método a uma requisição HTTP **GET** para a rota informada. O trecho `{registrationNumber}`, entre chaves, é um **espaço reservado** dentro da URL — o mesmo conceito da rota `:registrationNumber` configurada no Mockoon (seção 4.6), só que na sintaxe usada pelo Spring.
- **`@PathVariable String registrationNumber`** — liga o parâmetro do método Java ao espaço reservado da URL: quando `getCompanyRisk("REG-1234")` for chamado, o OpenFeign substitui `{registrationNumber}` por `"REG-1234"` na URL final da requisição, montando `http://localhost:3001/sanctions/companies/REG-1234`.
- **Nenhuma implementação de corpo é escrita** — essa é a essência declarativa do OpenFeign, apresentada na teoria do Vídeo 01: basta essa **assinatura de método**, com as anotações certas, para que o Spring monte toda a lógica de abrir a conexão, montar a URL, disparar a requisição `GET` e (quando o tipo de retorno não for `void`) converter a resposta JSON de volta para um objeto Java — tudo isso sem uma única linha de código imperativo escrita manualmente.
- **Por que o tipo de retorno é `void`?** — neste primeiro momento, o objetivo do vídeo é apenas confirmar que a chamada HTTP está de fato saindo e chegando ao mock; o corpo da resposta ainda não é processado. Transformar essa resposta em um objeto Java de verdade (por exemplo, um `ComplianceScreening`) é o assunto anunciado para os próximos vídeos, quando dados mais complexos entrarem em cena.

### 4.12. Injetando o `SanctionClient` no use case

```java
package dio.compliance.application;

import dio.compliance.domain.Company;
import dio.compliance.infrastructure.rest.client.SanctionClient;
import org.springframework.stereotype.Service;

@Service
public class AnalyzeCompanyRiskUseCase {
    private final SanctionClient sanctionClient;

    public AnalyzeCompanyRiskUseCase(SanctionClient sanctionClient) {
        this.sanctionClient = sanctionClient;
    }

    public void execute(Company domain) {

        // KYC -> San
        // AML

    }
}
```

Mais uma vez, o mesmo padrão de injeção via construtor: como `SanctionClient` está anotada com `@FeignClient` (e a aplicação tem `@EnableFeignClients` habilitado), o Spring já sabe gerar e injetar uma implementação dela automaticamente, sem que `AnalyzeCompanyRiskUseCase` precise conhecer nenhum detalhe de como essa implementação é construída por dentro.

> **Uma ressalva sobre arquitetura, admitida na própria aula**
> Aqui, uma classe de **infraestrutura** (`SanctionClient`, que pertence ao pacote `infrastructure.rest.client`) está sendo injetada diretamente dentro de um **use case** (camada `application`). Em uma arquitetura limpa "hexagonal" mais rígida, o ideal seria o use case depender apenas de uma **interface de domínio** (algo como uma `SanctionRepository` ou `RiskScreeningGateway`, seguindo o mesmo princípio de inversão de dependência já visto em `CompanyRepository`), com `SanctionClient` implementando essa interface por trás. A própria aula reconhece essa simplificação como aceitável neste momento — o objetivo é validar rapidamente que a integração via OpenFeign funciona de ponta a ponta antes de refinar a arquitetura.

### 4.13. Chamando o `SanctionClient` dentro do `execute`

```java
public void execute(Company domain) {
    sanctionClient.getCompanyRisk(domain.getRegistrationNumber());

    // KYC -> San
    // AML

}
```

A chamada `sanctionClient.getCompanyRisk(domain.getRegistrationNumber())` é, finalmente, a **primeira chamada HTTP real** disparada pelo projeto Compliance para um serviço externo (mesmo que mocado). É esse único método chamado — sem nenhuma menção a sockets, URLs completas ou parsing de JSON dentro do próprio use case — que ilustra na prática tudo o que foi discutido de forma teórica no Vídeo 01 sobre a diferença entre integração "na unha" e integração declarativa.

### 4.14. Confirmando o fluxo de ponta a ponta

Com a aplicação Compliance no ar e o Mockoon (ambiente KYC, porta `3001`) também rodando com os logs visíveis, uma nova requisição é enviada:

```http
POST http://localhost:8080/companies
Accept: application/json

{
  "name": "Logistics",
  "registrationNumber": "REG-1234"
}
```

O caminho completo que essa única requisição percorre, encadeando tudo o que foi construído nos Vídeos 03 e 04, é:

1. O `POST /companies` cria uma `CompanyEntity` via `CompanyEntityRepository` (Spring Data REST) — seção 3.5.
2. A criação bem-sucedida dispara o evento `afterCreate`, capturado por `CompanyEventHandler.handleAfterCreateEvent` — seção 3.8.
3. O handler converte a entidade em domínio (`entity.toDomain()`) e chama `AnalyzeCompanyRiskUseCase.execute(...)` — seção 4.4.
4. O use case chama `sanctionClient.getCompanyRisk(...)`, disparando uma requisição `GET` real para o Mockoon — seção 4.13.

Nos logs do Mockoon, aparece o registro da requisição `GET /sanctions/companies/REG-1234`, capturada pela rota configurada e respondida com status `200` — confirmando que a integração via Spring Cloud OpenFeign está funcionando de ponta a ponta: da criação da empresa até a chamada real ao serviço externo mocado.

### Checkpoint do Vídeo 04

Estado final do código nesta etapa, **conferido diretamente nos arquivos do `.zip`** enviado.

#### Estrutura de pastas

```
compliance/
├── build.gradle
├── settings.gradle
└── src/
    ├── main/
    │   ├── java/dio/compliance/
    │   │   ├── ComplianceApplication.java
    │   │   ├── appicantion/                          (nome ainda com o erro de digitação do Vídeo 02)
    │   │   │   └── AnalyzeCompanyRiskUseCase.java
    │   │   ├── domain/
    │   │   │   ├── Company.java
    │   │   │   ├── CompanyId.java
    │   │   │   ├── CompanyRepository.java
    │   │   │   ├── CompliancePolicy.java
    │   │   │   ├── ComplianceScreening.java
    │   │   │   ├── RiskAssessment.java
    │   │   │   ├── RiskAssessmentStatus.java
    │   │   │   └── RiskLevel.java
    │   │   └── infrastructure/
    │   │       ├── persistence/
    │   │       │   ├── entity/CompanyEntity.java
    │   │       │   ├── repository/CompanyEntityRepository.java
    │   │       │   ├── repository/InMemoryCompanyRepository.java
    │   │       │   └── event/CompanyEventHandler.java
    │   │       └── rest/
    │   │           └── client/SanctionClient.java
    │   └── resources/
    │       └── application.properties
    └── test/
        └── java/dio/compliance/
            └── ComplianceApplicationTests.java
```

#### `build.gradle`

```groovy
plugins {
    id 'java'
    id 'org.springframework.boot' version '4.0.0'
    id 'io.spring.dependency-management' version '1.1.7'

    id("io.freefair.lombok") version "9.2.0"

}

group = 'dio'
version = '0.0.1-SNAPSHOT'
description = 'compliance'

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

ext {
    set('springCloudVersion', "2025.1.1")
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'

    implementation 'org.springframework.data:spring-data-keyvalue'
    implementation 'org.springframework.boot:spring-boot-starter-data-rest'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-actuator'

    implementation 'org.springframework.cloud:spring-cloud-starter-openfeign'

}

dependencyManagement {
    imports {
        mavenBom "org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"
    }
}

tasks.named('test') {
    useJUnitPlatform()
}
```

#### `ComplianceApplication.java`

```java
package dio.compliance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.map.repository.config.EnableMapRepositories;

@SpringBootApplication
@EnableMapRepositories
@EnableFeignClients
public class ComplianceApplication {

    public static void main(String[] args) { SpringApplication.run(ComplianceApplication.class, args); }

}
```

#### `appicantion/AnalyzeCompanyRiskUseCase.java`

```java
package dio.compliance.appicantion;

import dio.compliance.domain.Company;
import dio.compliance.infrastructure.rest.client.SanctionClient;
import org.springframework.stereotype.Service;

@Service
public class AnalyzeCompanyRiskUseCase {

    private final SanctionClient sanctionClient;

    public AnalyzeCompanyRiskUseCase(SanctionClient sanctionClient) {
        this.sanctionClient = sanctionClient;
    }

    public void execute(Company domain) {
        sanctionClient.getCompanyRisk(domain.getRegistrationNumber());

    }
}
```

> Note que, no código real, os comentários `// KYC -> San` e `// AML` mostrados no README **não estão presentes** — foram removidos antes da versão final gravada no `.zip`. O pacote também está, de fato, com o nome `appicantion` (com erro de digitação), e não `application` — o `import` dentro de `CompanyEventHandler` confirma isso (`import dio.compliance.appicantion.AnalyzeCompanyRiskUseCase;`).

#### `infrastructure/persistence/event/CompanyEventHandler.java`

```java
package dio.compliance.infrastructure.persistence.event;

import dio.compliance.appicantion.AnalyzeCompanyRiskUseCase;
import dio.compliance.infrastructure.persistence.entity.CompanyEntity;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;

@Component
@RepositoryEventHandler
public class CompanyEventHandler {
    private static final Logger LOG = LoggerFactory.getLogger(CompanyEventHandler.class);

    private final AnalyzeCompanyRiskUseCase analyzeCompanyRiskUseCase;

    public CompanyEventHandler(AnalyzeCompanyRiskUseCase analyzeCompanyRiskUseCase) {
        this.analyzeCompanyRiskUseCase = analyzeCompanyRiskUseCase;

    }

    @HandleAfterCreate
    public void handleAfterCreateEvent(CompanyEntity entity) {
        LOG.info("handleAfterCreateEvent {}", entity);
        this.analyzeCompanyRiskUseCase.execute(entity.toDomain());
    }

}
```

#### `infrastructure/rest/client/SanctionClient.java`

```java
package dio.compliance.infrastructure.rest.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "sanction-client", url = "http://localhost:3001" )
public interface SanctionClient {

    @GetMapping("/sanctions/companies/{registrationNumber}")
    void getCompanyRisk(@PathVariable String registrationNumber);

}
```

> No código real, a `url` é `http://localhost:3001` — diferente do endereço `http://192.168.64.1:3001` mostrado nas capturas de tela do README (ver "Pontos de atenção" abaixo).

#### Arquivos desta etapa que **não mudaram** desde o Vídeo 02 (confirmados no `.zip`)

`domain/Company.java`, `domain/CompanyId.java`, `domain/CompanyRepository.java`, `domain/CompliancePolicy.java`, `domain/ComplianceScreening.java`, `domain/RiskAssessment.java`, `domain/RiskAssessmentStatus.java`, `domain/RiskLevel.java`, `settings.gradle` e `application.properties` permanecem idênticos ao checkpoint do tutorial anterior.

---

## Pontos de atenção: divergências entre a aula e o seu projeto

Comparando o README e as capturas de tela com o `.zip` real, valem os seguintes registros — nenhum deles impede a aplicação de compilar ou de subir:

1. **Versão do Spring Boot mudou novamente** — o tutorial anterior havia registrado `4.1.0` no `build.gradle` real, contra `4.0.5` mencionado no README na época. Neste novo `.zip`, a versão real caiu para **`4.0.0`**, enquanto o README desta etapa segue citando `4.0.5`. É provável que isso reflita ajustes manuais de versão feitos entre uma gravação e outra da aula (ou entre a criação do seu projeto e o material de referência). Como nos vídeos anteriores, a diferença é de uma versão *minor*, sem impacto esperado no conteúdo ensinado até aqui.
2. **Toolchain Java ainda em 21, não 25** — divergência já registrada no tutorial anterior e ainda não resolvida: o `build.gradle` real continua com `JavaLanguageVersion.of(21)`, enquanto o README segue mencionando JDK 25 na criação do projeto. Todo o código escrito até aqui (incluindo `@FeignClient`, `CrudRepository` genérico, `record`s aninhados) é totalmente compatível com Java 21, então isso não bloqueia nada — mas continua valendo a recomendação de decidir conscientemente qual versão seguir.
3. **Pacote `appicantion` ainda não corrigido — e agora com código dentro** — o tutorial anterior já havia sinalizado esse erro de digitação (`appicantion` em vez de `application`) quando o pacote ainda estava vazio, recomendando corrigi-lo antes do Vídeo 04. Isso não aconteceu: o `.zip` confirma que `AnalyzeCompanyRiskUseCase.java` foi criado dentro de `dio.compliance.appicantion`, e o `CompanyEventHandler` já importa essa classe usando esse mesmo pacote com erro de digitação. Corrigir esse nome agora já exige mais cuidado do que antes (é preciso atualizar o `package` da classe **e** o `import` em `CompanyEventHandler`), mas ainda é uma correção simples — vale considerá-la antes que mais classes sejam adicionadas a esse pacote nos próximos vídeos.
4. **URL do `SanctionClient`: `localhost` no código, IP de rede local nas capturas de tela** — o README mostra a anotação `@FeignClient` configurada com `url = "http://192.168.64.1:3001"` (um endereço IP de rede local, provavelmente da máquina onde a aula foi gravada), mas o `.zip` real usa `url = "http://localhost:3001"`. Funcionalmente, ambos endereçam a mesma máquina quando o Mockoon roda localmente — `localhost` é, inclusive, mais portável entre diferentes redes e computadores, então essa divergência tende a ser uma vantagem, não um problema, para quem for reproduzir o projeto em outra máquina.
5. **Comentários `// KYC -> San` / `// AML` não estão no código final** — esses comentários aparecem nas capturas de tela do README como marcadores intermediários (seção 4.5), mas não estão presentes na versão final do `AnalyzeCompanyRiskUseCase.execute` gravada no `.zip`. Isso é esperado: são apenas anotações temporárias de desenvolvimento, removidas naturalmente conforme o código evolui — não uma divergência que exija ação.

---

## Glossário de conceitos Java e Spring — apenas os termos novos desta etapa

> Este glossário complementa (não substitui) o glossário do tutorial anterior. Aqui aparecem só os termos que ainda não haviam sido explicados nos Vídeos 01 e 02.

### Estrutura da linguagem Java

| Termo | Significado |
|---|---|
| Declaração múltipla na mesma linha (`String a, b;`) | Sintaxe do Java que permite declarar duas ou mais variáveis do mesmo tipo em uma única linha, separadas por vírgula. |
| `@Override` | Anotação padrão do Java (não específica do Spring) que marca explicitamente que um método sobrescreve um método de uma interface ou superclasse; ajuda o compilador a detectar erros de assinatura. |
| Método de fábrica estático (*static factory method*) | Padrão de projeto em que um método `static` centraliza a lógica de criação de um objeto (ex.: `CompanyEntity.from(...)`), em vez de espalhar chamadas a `new` pelo código. |

### Anotações e bibliotecas

| Termo | Significado |
|---|---|
| `@KeySpace` (Spring Data KeyValue) | Define o nome do "espaço de chaves" (equivalente a uma tabela) onde os objetos de uma classe são armazenados na persistência em memória. |
| `@Data` (Lombok) | Anotação "combo" que gera getters, setters, `toString`, `equals` e `hashCode` de uma vez, indicada para classes técnicas que precisam de mutabilidade (ao contrário de `@Getter`, mais restrito). |
| `@NoArgsConstructor` (Lombok) | Gera um construtor sem parâmetros, necessário para frameworks que precisam instanciar um objeto "vazio" antes de preenchê-lo. |
| `@Id` (Spring Data, genérico) | Marca qual campo de uma classe deve ser tratado como identificador único do registro, de forma independente do módulo de persistência usado. |
| `CrudRepository<T, ID>` | Interface do Spring Data que já traz prontas as operações básicas de CRUD (`save`, `findAll`, `findById`, `count`, `delete` etc.), implementada automaticamente pelo framework. |
| `@RepositoryRestResource` | Anotação do Spring Data REST que expõe um repositório automaticamente como uma API REST completa, sem necessidade de um `@RestController` manual. |
| HATEOAS | *Hypermedia as the Engine of Application State*: padrão em que respostas de API incluem links (`_links`) para recursos relacionados, permitindo navegação/descoberta sem depender de URLs previamente conhecidas. |
| `@Component` | Anotação genérica do Spring para marcar uma classe como bean gerenciado, usada quando ela não se encaixa claramente como `@Service` (regra de negócio) ou `@Repository` (acesso a dados). |
| `@Repository` | Anotação do Spring para classes de acesso a dados; além de habilitar injeção de dependência, ativa tratamento especial de exceções de persistência. |
| `@RepositoryEventHandler` | Anotação de classe do Spring Data REST que marca uma classe como capaz de reagir a eventos disparados por repositórios. |
| `@HandleAfterCreate` | Anotação de método do Spring Data REST que executa o método automaticamente sempre que uma nova entidade é criada com sucesso via repositório exposto. |
| SLF4J / `Logger` / `LoggerFactory` | *Simple Logging Facade for Java*: padrão comum de logging em Java; `Logger` é a interface usada para registrar mensagens, e `LoggerFactory.getLogger(Classe.class)` cria um logger identificado com o nome da classe. |
| Mockoon | Ferramenta desktop para criar e rodar APIs REST simuladas ("mocks") localmente, usada no curso para simular as APIs externas de KYC e AML. |
| Spring Cloud | Ecossistema de bibliotecas do Spring voltado a sistemas distribuídos, distribuído separadamente do Spring Boot, com seu próprio ciclo de versões. |
| BOM (*Bill of Materials*) | Artefato que não contém código, apenas uma lista de versões compatíveis entre si para um conjunto de bibliotecas relacionadas; importado via `mavenBom` no Gradle. |
| `dependencyManagement` (Gradle) | Bloco que permite importar um BOM, garantindo versões compatíveis entre dependências relacionadas sem precisar declará-las manualmente uma a uma. |
| `ext { }` (Gradle) | Bloco que permite declarar propriedades extras customizadas no `build.gradle`, reaproveitáveis no restante do arquivo. |
| `@EnableFeignClients` | Anotação que habilita o escaneamento automático de interfaces Feign client no projeto, gerando implementações concretas para elas. |
| `@FeignClient` | Anotação principal do OpenFeign, que transforma uma interface comum em um cliente REST declarativo, definindo nome lógico e URL base do serviço consumido. |
| `@GetMapping` | Anotação do Spring Web que associa um método a uma requisição HTTP `GET` em uma rota específica; usada tanto em controllers quanto em Feign clients. |
| `@PathVariable` | Anotação que liga um parâmetro de método a um trecho variável (*path parameter*) declarado na URL entre chaves, como `{registrationNumber}`. |

### Arquitetura e padrões de projeto

| Termo | Significado |
|---|---|
| Use case (caso de uso) | Padrão de arquitetura em que cada regra de negócio ganha uma classe própria, com responsabilidade única, tipicamente expondo um único método público (`execute`). |
| Entidade de persistência vs. classe de domínio | Separação entre a classe que representa um conceito de negócio (domínio) e a classe que representa como esse conceito é efetivamente armazenado (persistência) — permite que decisões técnicas de armazenamento não vazem para as regras de negócio. |
| REST client | Nome usual para o componente de uma aplicação responsável por fazer requisições HTTP para uma API externa. |

---

## Próximos passos: o que vem a partir do Vídeo 05

Com KYC/sanções já integrado via OpenFeign de ponta a ponta, o roteiro restante do curso (conferido no README) é:

- **Vídeo 05 — Monitorando Requisições e Respostas:** deve explorar o Actuator mais a fundo e/ou introduzir logging estruturado das chamadas feitas pelo `SanctionClient`, complementando o `LOG.info` simples já usado em `CompanyEventHandler`.
- **Vídeo 06 — Configurando Cenários de Exceção:** deve tratar de tratamento de erros e exceções na comunicação com a API externa — provavelmente configurando, no Mockoon, novos cenários de resposta (erros 4xx/5xx) além do "empresa sem riscos" já criado no ambiente KYC.
- **Vídeo 07 — Consumindo Dados Complexos:** deve popular de fato um `ComplianceScreening` a partir da resposta HTTP real do mock (até aqui, `getCompanyRisk` retorna `void` e ignora o corpo da resposta), conectando finalmente a chamada ao `SanctionClient` com a lógica de `CompliancePolicy.evaluate(...)`, criada no Vídeo 02 mas ainda não usada em nenhum fluxo real.
- **Vídeo 08 — Estratégias de Tolerância a Falhas:** deve fechar o curso tratando de resiliência (timeouts, retries, fallbacks) diante de falhas da API externa — tema já anunciado desde o Vídeo 01.

> **Uma correção em relação à previsão do tutorial anterior**
> O documento dos Vídeos 01–02 estimava que o Spring Cloud OpenFeign só entraria em cena no Vídeo 07. Na prática, ele foi introduzido já no **Vídeo 04** — mais cedo do que o esperado, mas de forma coerente com a estrutura do curso: primeiro os use cases foram organizados (Vídeo 04), e só depois de validada a chamada mais simples possível (retorno `void`, sem processar a resposta) é que os vídeos seguintes devem aprofundar no consumo de dados complexos e no tratamento de falhas.

> **Sugestão de uso deste documento**
> Depois de assistir a cada novo vídeo, crie um novo arquivo (por exemplo, `003-Tutorial_Compliance_OpenFeign_Video05.md`) seguindo o mesmo formato: bloco de código → explicação linha a linha → checkpoint fiel ao `.zip` mais recente → atualização do glossário e dos próximos passos. Isso mantém o material sempre alinhado ao seu ritmo de estudo.

---

*Este documento cobre os Vídeos 03 e 04 do curso, dando continuidade a `001-Tutorial_Compliance_OpenFeign_Videos01a02.md`. O próximo tutorial da série deve continuar a partir daqui, documentando o Vídeo 05 ("Monitorando Requisições e Respostas") com o mesmo nível de detalhe.*
