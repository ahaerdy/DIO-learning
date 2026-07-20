# Tutorial de Estudos — Conectando sua API com Banco de Dados Através do Spring Data

**Continuação: Multi-Database com Docker e a chegada (conturbada) do MongoDB — Vídeo 05**

- Curso: NTT Data — Jornada Tech (DIO) · Módulo 4 — "Criando Soluções Inteligentes com Spring Boot e Java Moderno"
- Curso 2 do módulo: "Conectando sua API com Banco de Dados Através do Spring Data"
- Instrutor: Thiago Poiani (Principal Engineer at Skip)
- Projeto: `marketplace`
- Documento de referência pessoal — nível iniciante em Java

---

## Sobre este documento

Este arquivo é a continuação direta de `003-Tutorial_Marketplace_Java_Spring_Data_Video04.md` e cobre exclusivamente o **Vídeo 05 — "Multi-Database com Docker"** (título da aula no README). Ele foi escrito a partir das anotações atualizadas do README **e conferido diretamente contra o código-fonte real** do `.zip` enviado (`marketplace_ate_o_video_05.zip`) — não apenas contra a narrativa do README.

Essa checagem foi especialmente importante nesta etapa: ao contrário dos vídeos anteriores, onde as divergências entre README e código eram pequenos detalhes de digitação, **o Vídeo 05 tem uma divergência estrutural grande**. O README narra a criação de `EventMetadata` como um *documento MongoDB* (anotado com `@Document`), mas o arquivo `.java` real do `.zip` é uma **entidade JPA comum** (anotada com `@Entity`, mapeada para tabelas MySQL via `@ElementCollection`). Além disso, dois dos três arquivos novos desta etapa têm problemas que **impedem a compilação** do projeto. Tudo isso está detalhado, com calma, ao longo deste documento e resumido na seção "Pontos de atenção".

Para concatenar este arquivo ao anterior, basta colar o conteúdo a partir da seção "Parte 5" logo abaixo do final do documento 003 (ajustando a numeração de seções, se desejar um documento único).

> **Como esta parte está organizada**
> Os blocos de código são apresentados na mesma ordem em que aparecem narrados no README (seção 5.1 em diante). Para cada um, é mostrado primeiro o que a aula descreve e, em seguida — sempre que há diferença —, o que o arquivo `.java`/`.yml`/`.properties` real do `.zip` efetivamente contém, com explicação em nível iniciante de cada anotação nova. Ao final, há: uma seção de divergências entre aula e código real (mais longa que de costume, por conta dos problemas já citados), o glossário atualizado (só termos novos), o checkpoint fiel do projeto no estado do Vídeo 05, e os próximos passos revisados.

---

## Parte 5 — Multi-Database com Docker (Vídeo 05)

Até o Vídeo 04, o projeto já tinha dois bancos relacionais MySQL configurados manualmente (`registration` e `catalog`), mas o estudo de caso original (descrito desde o Vídeo 01) sempre previu um terceiro banco, de natureza diferente: um banco **NoSQL orientado a documentos**, para guardar metadados de evento de forma flexível — coisas como requisitos técnicos, setores e assentos, que variam muito de evento para evento e não se encaixam bem em um esquema relacional rígido. O Vídeo 05 é o momento em que esse banco — o **MongoDB** — finalmente entra em cena.

### 5.1. Adicionando o serviço do MongoDB no `compose.yml`

O README narra a adição de um novo serviço `catalog-metadata-database` ao `compose.yml`, com um `healthcheck` baseado em `mongosh` (o cliente de linha de comando do MongoDB) rodando o comando `db.runCommand("ping").ok`, e um volume `metadata_data` dedicado a esse banco. O texto também menciona a imagem `mongo:8.2`.

O `compose.yml` real do `.zip`, no entanto, é mais enxuto:

```yaml
services:
  registration-database:
    image: mysql:9.0
    # ...configuração já vista no Vídeo 04...

  catalog-database:
    image: mysql:9.6
    # ...configuração já vista no Vídeo 04...

  catalog-metadata-database:
    image: mongo:7.0
    container_name: marketplace-catalog-metadata-database-1
    ports:
      - "27018:27017"
    volumes:
      - catalog-metadata-data:/data/db

volumes:
  registration_data:
  catalog_data:
  catalog-metadata-data:
```

**O que está acontecendo:** um terceiro serviço, `catalog-metadata-database`, entra na lista, seguindo o mesmo padrão dos outros dois — mas com diferenças importantes em relação a um banco relacional. `image: mongo:7.0` diz ao Docker para baixar e rodar a imagem oficial do MongoDB, versão 7.0 (o README menciona 8.2 — mais detalhes na seção "Pontos de atenção"). `container_name` dá um nome fixo e previsível ao container (`marketplace-catalog-metadata-database-1`), em vez de deixar o Docker Compose gerar um nome automaticamente a partir do nome do projeto e do serviço — útil para identificar o container facilmente em outras ferramentas (como o cliente de banco de dados da IDE, usado mais adiante). A porta `27018:27017` segue a mesma lógica já vista para o MySQL: o MongoDB escuta internamente na porta padrão `27017`, e como essa porta já poderia conflitar com um MongoDB local na máquina do desenvolvedor, ela é mapeada para `27018` no host. Por fim, `volumes: - catalog-metadata-data:/data/db` persiste os arquivos internos do banco no volume nomeado `catalog-metadata-data` — `/data/db` é, por convenção, o diretório onde a imagem oficial do MongoDB grava seus arquivos de dados.

Note que **não há bloco `healthcheck`** neste serviço no código real, diferente do que o README descreve. Isso significa que o Spring Boot, ao subir os containers via seu suporte a Docker Compose, não espera nenhuma verificação de "saúde" específica do Mongo antes de considerar o serviço pronto — ele apenas aguarda o container estar em execução.

### 5.2. Subindo o container do MongoDB

Com o serviço adicionado ao `compose.yml`, basta reiniciar a aplicação (ou rodar `docker compose up`) para que o Spring Boot, através do suporte a Docker Compose já usado desde o Vídeo 02 (dependência `spring-boot-docker-compose`), detecte o novo serviço e suba o container automaticamente. Diferente do MySQL, não é preciso configurar usuário, senha ou nome de banco nas variáveis de ambiente: por padrão, a imagem oficial do MongoDB não exige autenticação e cria bancos "sob demanda" — um banco só passa a existir de verdade no momento em que a primeira coleção (o equivalente Mongo de uma tabela) recebe o primeiro documento.

### 5.3. Adicionando a dependência do Spring Data MongoDB

No `build.gradle`, é acrescentada mais uma dependência ao bloco `dependencies`, ao lado das já existentes:

```gradle
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

    implementation 'org.springframework.boot:spring-boot-starter-data-mongodb'
}
```

**O que essa linha faz:** `spring-boot-starter-data-mongodb` é o *starter* (pacote de dependências pré-configurado) do Spring Boot para trabalhar com MongoDB através do **Spring Data MongoDB** — o "irmão" do Spring Data JPA, mas voltado a bancos de documentos em vez de bancos relacionais. Ele traz, entre outras coisas, o driver oficial do MongoDB para Java, as classes de mapeamento objeto-documento (equivalente ao ORM do JPA, mas chamado de *ODM* — *Object-Document Mapping*) e a auto-configuração que, ao detectar essa dependência no classpath, tenta configurar sozinha uma conexão com um MongoDB — usando, por padrão, `localhost:27017` caso nenhuma outra configuração seja informada.

### 5.4. Habilitando repositórios Mongo e auditoria na `CatalogConfiguration`

O README narra a adição de duas novas anotações à classe `CatalogConfiguration`, ao lado da configuração de JPA para o catálogo já existente desde o Vídeo 04. Conferindo o arquivo real, é exatamente isso que está lá:

```java
package dio.marketplace.catalog;
// ...imports já vistos no Vídeo 04, mais os dois abaixo...
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration(proxyBeanMethods = false)
@EnableJpaRepositories(basePackages = "dio.marketplace.catalog",
        entityManagerFactoryRef = "catalogEntityManagerFactory",
        transactionManagerRef = "catalogTransactionManager")
@EnableMongoRepositories
@EnableMongoAuditing
public class CatalogConfiguration {
    // ...os beans de DataSource, EntityManagerFactory e TransactionManager do catálogo,
    // inalterados desde o Vídeo 04...
}
```

**O que cada anotação nova faz:**

- **`@EnableMongoRepositories`** é o equivalente Mongo do `@EnableJpaRepositories` já conhecido: ativa a criação automática de implementações para interfaces de repositório Mongo (interfaces que estendem `MongoRepository`, por exemplo) encontradas no classpath. Diferente do `@EnableJpaRepositories` usado para JPA neste projeto, aqui ela é usada **sem parâmetros** — ou seja, sem indicar `basePackages` nem qual `MongoTemplate` usar explicitamente. Isso faz sentido porque, diferente dos dois bancos MySQL (que exigem configuração manual por coexistirem), só existe **um único** MongoDB na aplicação até aqui, então a auto-configuração do Spring Boot para Mongo — que cria sozinha um `MongoTemplate` a partir das propriedades padrão (`spring.data.mongodb.*`) — é suficiente, sem precisar do mesmo trabalho manual feito para o JPA multi-banco.
- **`@EnableMongoAuditing`** ativa o suporte a *auditoria* do Spring Data para documentos Mongo — o mecanismo que permite anotar campos com `@CreatedDate` e `@LastModifiedDate` (ou, no caso da versão JPA equivalente, `@CreationTimestamp`/`@UpdateTimestamp` do Hibernate) para que o próprio framework preencha automaticamente a data de criação e de última atualização de um documento, sem que o desenvolvedor precise fazer isso manualmente a cada `save`.

> **Atenção:** como será visto na seção 5.5, a classe `EventMetadata` que essas duas anotações deveriam habilitar **não é**, no código real, um documento Mongo — é uma entidade JPA. Isso significa que, hoje, `@EnableMongoRepositories` e `@EnableMongoAuditing` estão presentes na configuração, mas não têm nenhum documento Mongo real para gerenciar no projeto.

### 5.5. Criando a classe `EventMetadata` — o que a aula narra vs. o que o código tem

Esta é a divergência mais importante do vídeo, e vale a pena parar para entender os dois lados.

**O que o README narra:** a criação, pela IDE, de uma nova classe `EventMetadata`, descrita como um documento MongoDB anotado com `@Document` (a anotação do Spring Data MongoDB equivalente ao `@Entity` do JPA), com um campo `technicalRequirements` do tipo `Map<String, Object>` — descrito como a peça-chave que permite guardar qualquer informação extra sem alterar a estrutura da classe, já que um documento MongoDB não exige um esquema fixo como uma tabela SQL.

**O que o arquivo real contém**, em `registration/infrastructure/persistence/entity/EventMetadata.java`:

```java
package dio.marketplace.registration.infrastructure.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event_metadata")
public class EventMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    @Column(nullable = false, columnDefinition = "VARCHAR(36)")
    private UUID eventId;

    @Column(columnDefinition = "TEXT")
    private String eventDescription;

    @Transient
    private Map<String, Object> technicalRequirements;

    @ElementCollection
    @CollectionTable(name = "event_sectors", joinColumns = @JoinColumn(name = "event_metadata_id"))
    private List<Sector> sectors;

    @ElementCollection
    @CollectionTable(name = "event_seats", joinColumns = @JoinColumn(name = "event_metadata_id"))
    private List<Seat> seats;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdOn;

    @UpdateTimestamp
    private Instant updatedAt;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class Sector {
        @Column(nullable = false)
        private String name;

        @Column(precision = 10, scale = 2)
        private BigDecimal price;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class Seat {
        @Column(nullable = false)
        private String code;

        @Column(nullable = false)
        private String sectorName;
    }
}
```

Ou seja: **é uma entidade JPA comum**, do mesmo tipo já usado para `Customer`, `Address` e `Event` — não um documento MongoDB. Isso muda completamente a explicação de cada anotação:

- **`@Entity` / `@Table(name = "event_metadata")`**: já vistos nos vídeos anteriores — marcam a classe como mapeada para uma tabela relacional chamada `event_metadata`.
- **`@Id` / `@GeneratedValue(strategy = GenerationType.UUID)`**: também já vistos — o identificador primário é gerado automaticamente como um UUID. A diferença sutil aqui é que o campo `id` é declarado como `String`, não como `UUID` — o Hibernate aceita as duas formas quando a estratégia é `GenerationType.UUID`, convertendo o UUID gerado para texto ao gravar num campo `String`.
- **`@Transient`** (conceito novo): anotação JPA que marca um campo para **não ser persistido** no banco de dados — o Hibernate simplesmente ignora esse campo ao gerar o esquema da tabela e ao salvar/ler registros. É o oposto de tudo que se viu até aqui. Aplicada ao campo `technicalRequirements`, ela significa que, apesar do campo existir na classe Java e poder ser preenchido em memória durante a execução, **nada do que for colocado nele chega a ser salvo no banco** — a cada vez que o registro for lido de volta do MySQL, esse campo virá `null`. Isso é uma pista forte de que esse campo foi pensado originalmente para um documento Mongo (onde um `Map` solto se encaixa bem) e "sobrou" na versão adaptada para JPA sem que ninguém resolvesse como mapear um mapa livre para colunas relacionais.
- **`@ElementCollection`** (conceito novo): anotação JPA para mapear uma coleção (`List`, `Set`, etc.) de tipos que **não são entidades próprias** — ou seja, não têm identidade e ciclo de vida independentes, existindo apenas "dentro" da entidade dona. Aqui, cada `EventMetadata` tem sua própria lista de `Sector` e `Seat`; um `Sector` não faz sentido sozinho, fora do contexto de um `EventMetadata`.
- **`@CollectionTable(name = "...", joinColumns = @JoinColumn(name = "..."))`** (conceito novo): usada junto com `@ElementCollection`, define em qual **tabela auxiliar** os elementos da coleção serão gravados (`event_sectors` e `event_seats`, respectivamente) e qual coluna de chave estrangeira (`event_metadata_id`) liga cada linha dessa tabela auxiliar de volta ao `EventMetadata` dono.
- **`@Embeddable`** (conceito novo): anotação JPA que marca uma classe como "encaixável" dentro de outra entidade — seus campos (`name`/`price` em `Sector`, `code`/`sectorName` em `Seat`) não formam, sozinhos, uma tabela própria com identidade; eles só existem embutidos em outra estrutura (aqui, dentro das tabelas auxiliares criadas por `@ElementCollection`/`@CollectionTable`). É o oposto de `@Entity`: uma classe `@Embeddable` nunca tem seu próprio `@Id`.
- **`@Builder`** (conceito novo, do Lombok): gera automaticamente, em tempo de compilação, um "construtor fluente" para a classe — um jeito de montar um objeto encadeando chamadas como `EventMetadata.builder().eventDescription("...").sectors(lista).build()`, em vez de usar um construtor tradicional com todos os parâmetros de uma vez ou vários `set` separados. É especialmente útil em classes com muitos campos opcionais, como é o caso aqui.
- **`@NoArgsConstructor` / `@AllArgsConstructor`** (conceitos novos, do Lombok): duas anotações que geram construtores automaticamente. `@NoArgsConstructor` gera um construtor **sem nenhum argumento** (exigido pelo JPA/Hibernate internamente, que precisa criar instâncias "vazias" das entidades antes de preenchê-las via reflexão); `@AllArgsConstructor` gera um construtor **com um argumento para cada campo** da classe, na ordem em que foram declarados. Usar as duas junto com `@Builder` é um padrão comum: o `@Builder` sozinho, ao gerar seu construtor interno, pode "esconder" o construtor padrão sem argumentos — declará-lo explicitamente com `@NoArgsConstructor` evita esse efeito colateral.

Repare também que a classe usa `import java.util.Map;` e `import java.util.List;` — dois conceitos de Java puro que vale reforçar aqui: **`Map<K, V>`** é uma coleção de pares chave-valor (aqui, `Map<String, Object>` — chaves texto, valores de qualquer tipo), e **`List<T>`** é uma coleção ordenada que permite elementos repetidos — já vista em documentos anteriores, mas reforçada aqui pelo uso em `sectors` e `seats`.

### 5.6. Configurando o `application.properties`

O README narra a adição da propriedade `spring.mongodb.representation.uuid=standard`, explicando que ela é necessária para o Spring mapear corretamente UUIDs entre a forma como são persistidos e como são lidos do MongoDB. O arquivo real tem essa linha — mas também tem uma propriedade adicional, não mencionada no texto do README:

```properties
spring.application.name=marketplace

management.endpoint.health.show-details=always
spring.docker.compose.lifecycle-management=start-only

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Registration (MySQL 3307)
registration.datasource.url=jdbc:mysql://localhost:3307/registration
registration.datasource.username=app
registration.datasource.password=app
registration.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
registration.jpa.properties.hibernate.hbm2ddl.auto=update

# Catalog (MySQL 3308)
catalog.datasource.url=jdbc:mysql://localhost:3308/catalog
catalog.datasource.username=app
catalog.datasource.password=app
catalog.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
catalog.jpa.properties.hibernate.hbm2ddl.auto=create

spring.data.mongodb.uri=mongodb://localhost:27018/catalog-metadata
spring.mongodb.representation.uuid=standard
```

**O que a linha nova faz:** `spring.data.mongodb.uri` é a propriedade padrão que a auto-configuração do Spring Boot para MongoDB usa para saber **onde** se conectar — sem ela, o Spring tentaria `localhost:27017` (a porta padrão do Mongo), que não é onde o container está exposto (lembrando: o `compose.yml` mapeia a porta interna `27017` para a porta externa `27018`). A URI `mongodb://localhost:27018/catalog-metadata` segue o formato padrão de *connection string* do MongoDB: protocolo (`mongodb://`), host e porta (`localhost:27018`) e, por fim, o **nome do banco** a ser usado (`catalog-metadata`) — diferente do MySQL, esse banco não precisa existir previamente; o próprio MongoDB o cria na primeira escrita.

Já `spring.mongodb.representation.uuid=standard` resolve um problema de compatibilidade histórico do driver MongoDB para Java: por padrão, o driver serializa valores `UUID` usando um formato legado, específico do driver Java (`JAVA_LEGACY`), que não é compatível com o formato binário padrão (`STANDARD`) usado por outras linguagens e ferramentas — inclusive pelo próprio `mongosh`. Definir essa propriedade como `standard` garante que os UUIDs sejam gravados no formato universal, evitando incompatibilidades ao inspecionar os dados fora da aplicação Java.

> Como já era esperado desde o Vídeo 04 (ver "Pontos de atenção" daquele documento), as linhas `spring.jpa.hibernate.ddl-auto=update` e `spring.jpa.show-sql=true` continuam órfãs — sem efeito prático, já que não existe mais um `DataSource` único auto-configurado pelo Spring Boot.

### 5.7. Criando o repositório `EventMetadataEntityRepository`

O README narra a criação de uma interface `EventMetadataEntityRepository`, "seguindo a mesma estrutura já usada para os demais repositórios da aplicação". O arquivo real, em `registration/infrastructure/persistence/repository/EventMetadataEntityRepository.java`, é este:

```java
package dio.marketplace.registration.infrastructure.persistence.repository;

import dio.marketplace.registration.infrastructure.event.EventMetadataEventListener;
import dio.marketplace.registration.infrastructure.persistence.entity.EventMetadata;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.logging.Logger;

@RepositoryRestResource(path = "event-metadata", collectionResourceRel = "eventMetadata")
public interface EventMetadataEntityRepository extends JpaRepository<EventMetadata, Long> {
    private static final Logger logger = LoggerFactory.getLogger(EventMetadataEventListener.class);

    @Override
    public void onAfterSave(AfterSaveEvent<EventMetadata> event) {
        logger.info("Event metadata save via onAfterSave {}", event.getDocument());
    }

    @Override
    public void onAfterDelete(AfterDeleteEvent<EventMetadata> event) {
        logger.info("Event metadata delete via onAfterDelete {}", event.getDocument());
    }
}
```

Este arquivo mistura dois mundos que não se encaixam, e o resultado **não compila**. Vale entender cada peça, mesmo as que não funcionam, porque cada uma delas é um conceito legítimo (só que do "lado" errado da aplicação):

- **`@RepositoryRestResource(path = "event-metadata", collectionResourceRel = "eventMetadata")`**: já visto em vídeos anteriores — expõe o repositório como um recurso REST, com o caminho da URL customizado para `/event-metadata` (em vez do nome derivado automaticamente da entidade) e o nome da relação HAL customizado para `eventMetadata` (o campo usado dentro de `_embedded` nas respostas de listagem). Essa parte é válida e funciona.
- **`extends JpaRepository<EventMetadata, Long>`**: aqui já aparece o primeiro problema. `JpaRepository<T, ID>` espera, como segundo parâmetro genérico, o **tipo do campo `@Id`** da entidade — mas o `@Id` de `EventMetadata` é do tipo `String` (visto na seção 5.5), não `Long`. Esse descompasso não impede a compilação em si (o Java não checa, em tempo de compilação, se o tipo genérico "bate" com o `@Id` real da entidade), mas faz o Spring Data JPA falhar **ao iniciar a aplicação**, ao tentar montar o repositório e perceber que o tipo declarado (`Long`) não corresponde ao tipo real do identificador (`String`).
- **`onAfterSave(AfterSaveEvent<EventMetadata> event)` / `onAfterDelete(AfterDeleteEvent<EventMetadata> event)` com `@Override`**: aqui está o problema que efetivamente **impede a compilação**. `AfterSaveEvent` e `AfterDeleteEvent` (importados de `org.springframework.data.mongodb.core.mapping.event`) são classes de eventos do **Spring Data MongoDB** — não existe nenhum método `onAfterSave`/`onAfterDelete` na interface `JpaRepository` (nem em nenhuma de suas superinterfaces) que receba esses tipos como parâmetro. Um `@Override` só é válido quando o método realmente sobrescreve algo herdado; aqui não há nada para sobrescrever, o que gera o erro de compilação *"method does not override or implement a method from a supertype"*. Some-se a isso um segundo problema, independente do primeiro: métodos declarados dentro de uma `interface` só podem ter corpo (chaves `{ }` com código dentro) se forem marcados como `default` ou `static` — o que não é o caso aqui. Ou seja: mesmo que o `@Override` fosse removido, o método continuaria inválido por ter corpo sem ser `default`.

Este trecho é, na prática, um exemplo de código copiado de um contexto Mongo (onde uma interface de *event listener* do Mongo realmente teria métodos assim) e colado dentro de um repositório JPA sem os ajustes necessários — provavelmente um resquício de uma tentativa anterior de usar Mongo de verdade, antes da entidade ter sido convertida para JPA.

### 5.8. O `EventMetadataEventListener` — o arquivo mais problemático da etapa

O README menciona esta classe de passagem, ao explicar o logger usado no repositório acima (`LoggerFactory.getLogger(EventMetadataEventListener.class)`), mas não a narra como um passo separado — pelo texto do README, ela nem chega a ser mostrada. O arquivo, no entanto, existe no `.zip`, em `registration/infrastructure/event/EventMetadataEventListener.java`, e é reproduzido aqui **exatamente como está salvo**, incluindo os problemas:

```java

package dio.marketplace.registration.infrastructure.event;

import dio.marketplace.catalog.infrastructure.persistence.entity.EventMetadata;
import dio.marketplace.registration.infrastructure.event;

import dio.marketplace.catalog.infrastructure.persistence.entity.EventMetadata; // Verifique se o caminho do pacote está correto ou se o módulo precisa ser exportado/importado
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.stereotype.Component;

@Component
public class EventMetadataEventListener extends AbstractMongoEventListener<EventMetadata> {
}
// Verifique se o caminho do pacote está correto ou se o módulo precisa ser exportado/importado
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.stereotype.Component;

```

Este arquivo tem **múltiplos problemas de compilação simultâneos**, cada um interessante de identificar isoladamente:

1. **`import dio.marketplace.registration.infrastructure.event;`** — uma declaração de `import` precisa apontar para uma classe específica (`pacote.Classe`) ou usar `*` para importar tudo de um pacote (`pacote.*`). Importar só o **nome do pacote**, sem classe nem asterisco, não é uma sintaxe válida em Java.
2. **Import duplicado de `EventMetadata`** — a mesma classe é importada duas vezes seguidas (linhas 4 e 7). Java tolera imports duplicados idênticos sem erro (é apenas redundante), mas aqui o problema real é outro: o import aponta para `dio.marketplace.catalog.infrastructure.persistence.entity.EventMetadata` — um pacote **`catalog`** — mas, como visto na seção 5.5, a classe `EventMetadata` que de fato existe no projeto está em `dio.marketplace.registration.infrastructure.persistence.entity` — pacote **`registration`**. Ou seja: este import aponta para uma classe que **não existe** no projeto, o que por si só já impede a compilação ("cannot find symbol").
3. **Declarações de `import` depois do fechamento da classe** — as duas últimas linhas do arquivo (`import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;` e `import org.springframework.stereotype.Component;`) aparecem **depois** da chave `}` que fecha a classe `EventMetadataEventListener`. Em Java, todas as declarações de `import` de um arquivo precisam vir **antes** de qualquer declaração de tipo (classe, interface, enum) — colocá-las depois é um erro de sintaxe.
4. **`extends AbstractMongoEventListener<EventMetadata>`** — mesmo ignorando os problemas acima, esta linha reforça a mesma mistura de mundos vista no repositório: `AbstractMongoEventListener<T>` é uma classe do Spring Data **MongoDB**, pensada para reagir a eventos de documentos Mongo (como `onAfterSave`, sobrescrevendo métodos de verdade herdados desta classe abstrata — diferente da tentativa inválida vista na seção 5.7). Usá-la sobre uma entidade **JPA** como `EventMetadata` não gera erro de compilação por si só (a classe genérica aceita qualquer tipo), mas também **não tem efeito nenhum em tempo de execução**: como `EventMetadata` nunca é salva através do Mongo (é uma entidade JPA, persistida via Hibernate no MySQL), os métodos desta classe — mesmo que fossem sobrescritos corretamente — nunca seriam chamados.

Assim como o `EventMetadataEntityRepository` da seção anterior, este arquivo tem a marca de um trecho de código gerado ou copiado num momento de transição entre "usar Mongo de verdade" e "usar JPA", sem que a limpeza tenha sido concluída antes do fim da gravação do vídeo.

### 5.9. Testando a funcionalidade pelo HAL Explorer e pelo cliente HTTP

Apesar dos problemas de compilação identificados nas seções 5.7 e 5.8, o README segue narrando testes bem-sucedidos da funcionalidade — criação de um `EventMetadata` pelo HAL Explorer, resposta `201 Created`, e depois um fluxo mais completo pelo cliente HTTP da IDE (arquivo `rest-api_5.http`, que não está presente no `.zip` enviado — provavelmente um arquivo de rascunho não versionado): primeiro criando um `Event` (via `POST /events`, já existente desde o Vídeo 04), copiando o `eventId` gerado, e então criando um `EventMetadata` vinculado a esse evento (via `POST /eventMetadatas`), com uma `eventDescription`, um `technicalRequirements` livre (chaves como `sound_system`, `min_age`, `backstage_pass_available`) e as listas `sectors`/`seats` preenchidas.

Como o próprio README relata que esse fluxo funcionou durante a gravação — inclusive mostrando o documento salvo na "collection" `eventMetadata` de um MongoDB — a hipótese mais provável é que os arquivos problemáticos das seções 5.7 e 5.8 tenham sido corrigidos (ou estivessem em outra versão) **no momento da gravação**, e o estado exato dessa correção não tenha sido capturado da mesma forma no `.zip` disponibilizado como material de apoio — ou que o vídeo tenha sido editado a partir de múltiplas tomadas em estados diferentes do código. De qualquer forma, o que importa para quem está estudando por este material é: **o `.zip` real do Vídeo 05, do jeito que foi enviado, não compila**, e os motivos exatos foram detalhados nas seções 5.7 e 5.8. Vale usar isso como exercício — tentar corrigir os dois arquivos por conta própria é uma ótima forma de fixar, na prática, a diferença entre repositórios/listeners JPA e Mongo.

### 5.10. Actuator Health com três bancos de dados

Por fim, o README narra a consulta ao endpoint `/actuator/health`, agora refletindo os três bancos configurados: o componente `db` (que já existia, agrupando `catalogDataSource` e `registrationDataSource`, ambos MySQL) ganha um vizinho, o componente `mongo`, com detalhes como as bases disponíveis no servidor (`admin`, `config`, `local` — bancos internos criados automaticamente por qualquer instância MongoDB) e a versão do protocolo de comunicação (`maxWireVersion`). Isso é possível porque a auto-configuração do Spring Boot Actuator, ao detectar a dependência `spring-boot-starter-data-mongodb` no classpath **e** uma conexão Mongo configurada (via `spring.data.mongodb.uri`), registra automaticamente um *health indicator* para o Mongo — do mesmo jeito que já registra um para cada `DataSource` relacional presente na aplicação. Isso é um bom lembrete de que o Actuator, aqui, está reportando a **conexão de baixo nível** com o servidor MongoDB (que funciona normalmente, já que o container está de pé) — e não tem como saber, por conta própria, que a camada de repositório construída sobre essa conexão tem os problemas descritos nas seções anteriores.

---

## Pontos de atenção: divergências entre a aula (README) e o código real (Vídeo 05)

Esta seção lista o que foi conferido **diretamente no `.java`/`.yml`/`.properties`** do `.zip` e diverge (ou complementa) o que está escrito no README. Diferente dos vídeos anteriores, algumas destas divergências **impedem a compilação** — estão marcadas explicitamente.

1. **[Estrutural] `EventMetadata` é uma entidade JPA, não um documento MongoDB.** O README descreve e mostra o código de uma classe anotada com `@Document`, `@Data` e `@RequiredArgsConstructor`, com campos simples (`Map`, `List` sem anotações extras). O arquivo real é anotado com `@Entity`, `@Table`, `@Builder`, `@NoArgsConstructor`, `@AllArgsConstructor`, tem seus campos de coleção mapeados via `@ElementCollection`/`@CollectionTable`/`@Embeddable`, e o campo que deveria ser o "carro-chefe" da flexibilidade NoSQL (`technicalRequirements`) está marcado como `@Transient` — ou seja, nunca é persistido. Isso significa que, apesar de todo o `compose.yml`, do `build.gradle` e das anotações `@EnableMongoRepositories`/`@EnableMongoAuditing` prepararem o terreno para o MongoDB, **nenhum dado de evento é de fato gravado nele** através desta classe — tudo continua indo para o MySQL do catálogo/registro, e o `Map` livre é descartado silenciosamente a cada `save`.
2. **[Estrutural] `EventMetadata` e seus arquivos relacionados vivem no pacote `registration`, não em `catalog`.** O README fala em criar a classe "ao lado da entidade `Event` já existente" (que fica em `catalog.infrastructure.persistence.entity`), mas o arquivo real está em `registration.infrastructure.persistence.entity`. O mesmo vale para `EventMetadataEntityRepository` (em `registration.infrastructure.persistence.repository`) e `EventMetadataEventListener` (em `registration.infrastructure.event`). Como cada componente tem seu próprio `@EnableJpaRepositories(basePackages = ...)`, isso tem uma consequência prática: caso a aplicação chegasse a compilar, o repositório de `EventMetadata` seria gerenciado pelo `registrationEntityManagerFactory` (o banco MySQL de `registration`, porta 3307) — não pelo `catalogEntityManagerFactory` nem pelo Mongo — mesmo a classe conceitualmente pertencendo ao domínio do catálogo de eventos.
3. **[Impede compilação] `EventMetadataEntityRepository` declara `@Override` sobre métodos que não existem em `JpaRepository`.** Os métodos `onAfterSave(AfterSaveEvent<EventMetadata>)` e `onAfterDelete(AfterDeleteEvent<EventMetadata>)`, com parâmetros de tipos do Spring Data MongoDB, não correspondem a nada herdado de `JpaRepository`. Some-se a isso que métodos de interface com corpo precisam do modificador `default` (ausente aqui). Detalhado na seção 5.7.
4. **[Falha ao iniciar a aplicação] `JpaRepository<EventMetadata, Long>` declara o tipo de ID errado.** O `@Id` de `EventMetadata` é `String`; o repositório declara `Long`. Mesmo que os problemas de compilação do item 3 fossem corrigidos, a aplicação falharia ao subir por causa dessa incompatibilidade.
5. **[Impede compilação] `EventMetadataEventListener` tem sintaxe inválida em múltiplos pontos**: um `import` de pacote sem classe nem `*`; um `import` apontando para uma classe (`catalog...EventMetadata`) que não existe no projeto; e duas declarações de `import` posicionadas depois do fechamento da classe. Detalhado na seção 5.8.
6. **Imagem do MongoDB: `mongo:7.0` no código real, `mongo:8.2` no texto do README.** Não afeta a lógica da aplicação (ambas as versões suportam os recursos usados), mas é uma divergência de dado factual entre a narrativa e o `compose.yml` real.
7. **`healthcheck` do MongoDB, presente no README, ausente no `compose.yml` real.** O README mostra um bloco `healthcheck` completo (comando `mongosh`, `interval`, `timeout`, `retries`) para o serviço `catalog-metadata-database`; o arquivo real não tem esse bloco — o Spring Boot considera o serviço "pronto" assim que o container inicia, sem checar ativamente se o `mongod` já aceita conexões.
8. **Nome do volume do Mongo: `catalog-metadata-data` no código real, `metadata_data` no texto do README.** Divergência apenas de nome, sem efeito funcional.
9. **`application.properties` real tem uma linha a mais que o trecho mostrado no README**: `spring.data.mongodb.uri=mongodb://localhost:27018/catalog-metadata`, essencial para o Spring saber em qual porta/banco se conectar (sem ela, a auto-configuração tentaria a porta padrão `27017`, onde não há nada exposto pelo `compose.yml`). O README só narra explicitamente a linha `spring.mongodb.representation.uuid=standard`.
10. **O arquivo `rest-api_5.http`, citado no README para os testes finais do vídeo, não está presente no `.zip`.** Provavelmente um arquivo de rascunho da IDE, não versionado junto com o restante do projeto — o `.zip` só contém o log automático de requisições da IDE (`.idea/httpRequests/http-requests-log.http`), não o arquivo de testes propriamente dito.

---

## Glossário de conceitos Java e Spring — termos novos do Vídeo 05

Esta seção só lista termos que **ainda não apareciam** nos glossários anteriores. Para os termos básicos de Java, Spring Data REST/HATEOAS/JPA e multi-datasource já vistos, consulte os documentos 001 a 003.

### Coleções e tipos de valor livre (Java)

| Termo | Significado |
|---|---|
| `Map<K, V>` | Interface padrão do Java para uma coleção de pares chave-valor, onde cada chave é única e aponta para exatamente um valor. `Map<String, Object>` guarda chaves de texto associadas a valores de **qualquer** tipo. |
| `Object` (como tipo de valor) | A superclasse de todas as classes em Java. Usar `Object` como tipo de um campo (como em `Map<String, Object>`) sinaliza que o valor pode ser de qualquer tipo — o preço da flexibilidade é perder a checagem de tipos em tempo de compilação. |

### Mapeamento JPA avançado (coleções e classes embutidas)

| Termo | Significado |
|---|---|
| `@Transient` | Anotação JPA que marca um campo para **não** ser persistido no banco — o Hibernate o ignora completamente ao gerar o esquema e ao ler/gravar registros. |
| `@ElementCollection` | Anotação JPA para mapear uma coleção de valores ou objetos simples (sem identidade própria) pertencentes exclusivamente a uma entidade. |
| `@CollectionTable(name, joinColumns)` | Usada com `@ElementCollection`, define o nome da tabela auxiliar onde os elementos da coleção são gravados e a coluna de chave estrangeira que os liga de volta à entidade dona. |
| `@JoinColumn(name)` | Anotação JPA que nomeia a coluna usada como chave estrangeira em um relacionamento entre tabelas. |
| `@Embeddable` | Anotação JPA que marca uma classe como "encaixável" dentro de outra: seus campos não formam uma tabela própria com identidade — só existem embutidos em outra estrutura. |
| `columnDefinition` (atributo de `@Column`) | Permite especificar, manualmente, o tipo de coluna SQL exato a ser usado pelo Hibernate (por exemplo, `"VARCHAR(36)"` ou `"TEXT"`), em vez de deixar o tipo ser inferido automaticamente a partir do tipo do campo Java. |
| `precision` / `scale` (atributos de `@Column`) | Para colunas numéricas decimais: `precision` define o número total de dígitos e `scale` quantos deles ficam depois da vírgula — usado aqui para representar valores monetários (`BigDecimal`) com duas casas decimais. |
| `BigDecimal` (`java.math`) | Classe padrão do Java para representar números decimais com precisão exata, evitando os erros de arredondamento comuns em `float`/`double` — por isso é o tipo recomendado para valores monetários. |

### Lombok: geração de construtores e builders

| Termo | Significado |
|---|---|
| `@Builder` | Anotação do Lombok que gera, em tempo de compilação, um construtor fluente (*builder pattern*) — permite montar um objeto encadeando chamadas de método, uma para cada campo, terminando em `.build()`. |
| `@NoArgsConstructor` | Anotação do Lombok que gera um construtor sem argumentos. Frequentemente necessário em entidades JPA, que exigem um construtor vazio para que o Hibernate possa instanciá-las via reflexão. |
| `@AllArgsConstructor` | Anotação do Lombok que gera um construtor com um parâmetro para cada campo da classe, na ordem em que foram declarados. |

### Spring Data MongoDB

| Termo | Significado |
|---|---|
| `spring-boot-starter-data-mongodb` | Dependência (*starter*) do Spring Boot que traz o driver Java do MongoDB, as classes de mapeamento objeto-documento do Spring Data MongoDB e a auto-configuração de conexão. |
| `@Document` | Anotação do Spring Data MongoDB, equivalente ao `@Entity` do JPA: marca uma classe como mapeada para uma *collection* (o equivalente Mongo de uma tabela) de um banco MongoDB. |
| `@EnableMongoRepositories` | Anotação que ativa a criação automática de implementações para interfaces de repositório MongoDB (como `MongoRepository`) encontradas no classpath — equivalente ao `@EnableJpaRepositories` do mundo relacional. |
| `@EnableMongoAuditing` | Anotação que ativa o suporte a auditoria automática (preenchimento de datas de criação/atualização) para documentos MongoDB, via anotações como `@CreatedDate`/`@LastModifiedDate`. |
| `MongoRepository<T, ID>` | Interface do Spring Data MongoDB, equivalente ao `JpaRepository<T, ID>` do mundo relacional, para repositórios que operam sobre documentos MongoDB. |
| `AbstractMongoEventListener<T>` | Classe abstrata do Spring Data MongoDB que pode ser estendida para reagir a eventos do ciclo de vida de documentos (antes/depois de salvar, deletar, etc.), sobrescrevendo métodos como `onAfterSave`. |
| `AfterSaveEvent<T>` / `AfterDeleteEvent<T>` | Classes de evento do Spring Data MongoDB, disparadas depois que um documento é salvo ou removido, respectivamente — carregam o documento afetado. |
| *Collection* (MongoDB) | O equivalente, no MongoDB, a uma tabela em um banco relacional: um agrupamento de documentos. Diferente de uma tabela, uma collection não exige que todos os seus documentos tenham a mesma estrutura de campos. |
| *Documento* (MongoDB) | O equivalente, no MongoDB, a uma linha/registro em um banco relacional: uma unidade de dado no formato BSON (um formato binário parecido com JSON), com estrutura flexível. |
| `mongosh` | O cliente de linha de comando oficial do MongoDB (*Mongo Shell*), usado para executar comandos administrativos e de consulta diretamente contra o banco — por exemplo, dentro de um `healthcheck` do Docker Compose. |
| Connection string / URI do MongoDB | Formato padrão de endereço de conexão (`mongodb://host:porta/banco`) usado para informar a um driver ou ferramenta onde e a qual banco se conectar. |
| `spring.mongodb.representation.uuid` | Propriedade do Spring Boot que define o formato binário usado para serializar valores `UUID` ao gravá-los no MongoDB — `standard` usa o formato universal (compatível com outras linguagens/ferramentas), evitando o formato legado específico do driver Java. |

---

## Estado atual do projeto (checkpoint do Vídeo 05)

Este é o retrato fiel do código-fonte na etapa atual, conferido diretamente nos arquivos do `marketplace_ate_o_video_05.zip` — não na narrativa do README. **Importante:** diferente dos checkpoints anteriores, o projeto **não compila** neste estado — os motivos exatos estão detalhados nas seções 5.7, 5.8 e nos itens 3 e 5 de "Pontos de atenção".

### Estrutura de pastas

```
marketplace/
├── build.gradle                                               (+ dependência Mongo)
├── compose.yml                                                (+ serviço catalog-metadata-database)
└── src/
    ├── main/
    │   ├── java/dio/marketplace/
    │   │   ├── registration/
    │   │   │   ├── MarketplaceApplication.java
    │   │   │   ├── domain/
    │   │   │   │   ├── Customer.java
    │   │   │   │   ├── CustomerId.java
    │   │   │   │   └── CustomerRepository.java
    │   │   │   ├── application/                              (ainda vazio)
    │   │   │   └── infrastructure/
    │   │   │       ├── RegistrationConfiguration.java
    │   │   │       ├── event/
    │   │   │       │   ├── CustomerEventHandler.java
    │   │   │       │   └── EventMetadataEventListener.java    (NOVO — Vídeo 05, não compila)
    │   │   │       └── persistence/
    │   │   │           ├── entity/
    │   │   │           │   ├── Customer.java
    │   │   │           │   ├── Address.java
    │   │   │           │   ├── EventMetadata.java              (NOVO — Vídeo 05, entidade JPA)
    │   │   │           │   └── projection/
    │   │   │           │       └── CustomerExcerpt.java
    │   │   │           └── repository/
    │   │   │               ├── CustomerEntityRepository.java
    │   │   │               ├── JpaCustomerRepository.java
    │   │   │               └── EventMetadataEntityRepository.java (NOVO — Vídeo 05, não compila)
    │   │   └── catalog/
    │   │       ├── CatalogConfiguration.java                   (+ @EnableMongoRepositories/@EnableMongoAuditing)
    │   │       ├── domain/                                     (ainda vazio)
    │   │       ├── application/                                (ainda vazio)
    │   │       └── infrastructure/
    │   │           ├── event/
    │   │           │   └── EventListener.java
    │   │           └── persistence/
    │   │               ├── entity/
    │   │               │   └── Event.java
    │   │               └── repository/
    │   │                   └── EventEntityRepository.java
    │   └── resources/
    │       └── application.properties                          (+ propriedades do MongoDB)
    └── test/                                                    (ainda vazio nesta etapa)
```

### `compose.yml`

```yaml
services:
  registration-database:
    image: mysql:9.0
    environment:
      MYSQL_DATABASE: registration
      MYSQL_ROOT_PASSWORD: root
      MYSQL_USER: app
      MYSQL_PASSWORD: app
    ports:
      - "3307:3306"
    volumes:
      - registration_data:/var/lib/mysql
    healthcheck:
      test: [ "CMD", "mysqladmin", "ping", "-h", "localhost", "-uapp", "-papp" ]
      interval: 5s
      timeout: 5s
      retries: 5

  catalog-database:
    image: mysql:9.6
    environment:
      MYSQL_DATABASE: catalog
      MYSQL_ROOT_PASSWORD: root
      MYSQL_USER: app
      MYSQL_PASSWORD: app
    ports:
      - "3308:3306"
    volumes:
      - catalog_data:/var/lib/mysql
    healthcheck:
      test: ["CMD", "mysqladmin", "ping", "-h", "localhost", "-uapp", "-papp"]
      interval: 5s
      timeout: 5s
      retries: 3

  catalog-metadata-database:
    image: mongo:7.0
    container_name: marketplace-catalog-metadata-database-1
    ports:
      - "27018:27017"
    volumes:
      - catalog-metadata-data:/data/db

volumes:
  registration_data:
  catalog_data:
  catalog-metadata-data:
```

### `build.gradle`

```gradle
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.3.2' // Ou a versão que você estiver utilizando
    id 'io.spring.dependency-management' version '1.1.6'

    id("io.freefair.lombok") version "9.2.0"

}

group = 'dio'
version = '1.0-SNAPSHOT'

repositories {
    mavenCentral()
}

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

    implementation 'org.springframework.boot:spring-boot-starter-data-mongodb'

}

test {
    useJUnitPlatform()
}
```

### `application.properties`

```properties
spring.application.name=marketplace

management.endpoint.health.show-details=always
spring.docker.compose.lifecycle-management=start-only

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Registration (MySQL 3307)
registration.datasource.url=jdbc:mysql://localhost:3307/registration
registration.datasource.username=app
registration.datasource.password=app
registration.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
registration.jpa.properties.hibernate.hbm2ddl.auto=update

# Catalog (MySQL 3308)
catalog.datasource.url=jdbc:mysql://localhost:3308/catalog
catalog.datasource.username=app
catalog.datasource.password=app
catalog.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
catalog.jpa.properties.hibernate.hbm2ddl.auto=create

spring.data.mongodb.uri=mongodb://localhost:27018/catalog-metadata
spring.mongodb.representation.uuid=standard
```

### `catalog/CatalogConfiguration.java`

```java
package dio.marketplace.catalog;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.LinkedHashMap;

@Configuration(proxyBeanMethods = false)
@EnableJpaRepositories(basePackages = "dio.marketplace.catalog",
        entityManagerFactoryRef = "catalogEntityManagerFactory",
        transactionManagerRef = "catalogTransactionManager")
@EnableMongoRepositories
@EnableMongoAuditing
public class CatalogConfiguration {

    @Qualifier("catalog")
    @Bean
    @ConfigurationProperties("catalog.datasource")
    public DataSourceProperties catalogDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Qualifier("catalog")
    @Bean
    @ConfigurationProperties("catalog.datasource.configuration")
    public HikariDataSource catalogDataSource(@Qualifier("catalog") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Qualifier("catalog")
    @Bean
    @ConfigurationProperties("catalog.jpa")
    public JpaProperties catalogJpaProperties() {
        return new JpaProperties();
    }

    @Qualifier("catalog")
    @Bean
    public LocalContainerEntityManagerFactoryBean catalogEntityManagerFactory(@Qualifier("catalog") DataSource dataSource,
                                                                              @Qualifier("catalog") JpaProperties jpaProperties) {

        var builder = new EntityManagerFactoryBuilder(
                new HibernateJpaVendorAdapter(),
                new LinkedHashMap<>(jpaProperties.getProperties()),
                null
        );

        return builder
                .dataSource(dataSource)
                .packages("dio.marketplace.catalog")
                .persistenceUnit("catalog")
                .build();
    }

    @Qualifier("catalog")
    @Bean
    public PlatformTransactionManager catalogTransactionManager(@Qualifier("catalog") LocalContainerEntityManagerFactoryBean emf) {
        return new JpaTransactionManager(emf.getObject());
    }

}
```

### `registration/infrastructure/persistence/entity/EventMetadata.java` (NOVO — Vídeo 05)

```java
package dio.marketplace.registration.infrastructure.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event_metadata")
public class EventMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    @Column(nullable = false, columnDefinition = "VARCHAR(36)")
    private UUID eventId;

    @Column(columnDefinition = "TEXT")
    private String eventDescription;

    @Transient
    private Map<String, Object> technicalRequirements;

    @ElementCollection
    @CollectionTable(name = "event_sectors", joinColumns = @JoinColumn(name = "event_metadata_id"))
    private List<Sector> sectors;

    @ElementCollection
    @CollectionTable(name = "event_seats", joinColumns = @JoinColumn(name = "event_metadata_id"))
    private List<Seat> seats;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdOn;

    @UpdateTimestamp
    private Instant updatedAt;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class Sector {
        @Column(nullable = false)
        private String name;

        @Column(precision = 10, scale = 2)
        private BigDecimal price;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class Seat {
        @Column(nullable = false)
        private String code;

        @Column(nullable = false)
        private String sectorName;
    }
}
```

### `registration/infrastructure/persistence/repository/EventMetadataEntityRepository.java` (NOVO — Vídeo 05, não compila)

```java
package dio.marketplace.registration.infrastructure.persistence.repository;

import dio.marketplace.registration.infrastructure.event.EventMetadataEventListener;
import dio.marketplace.registration.infrastructure.persistence.entity.EventMetadata;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.logging.Logger;

@RepositoryRestResource(path = "event-metadata", collectionResourceRel = "eventMetadata")
public interface EventMetadataEntityRepository extends JpaRepository<EventMetadata, Long> {
    private static final Logger logger = LoggerFactory.getLogger(EventMetadataEventListener.class);

    @Override
    public void onAfterSave(AfterSaveEvent<EventMetadata> event) {
        logger.info("Event metadata save via onAfterSave {}", event.getDocument());
    }

    @Override
    public void onAfterDelete(AfterDeleteEvent<EventMetadata> event) {
        logger.info("Event metadata delete via onAfterDelete {}", event.getDocument());
    }
}
```

### `registration/infrastructure/event/EventMetadataEventListener.java` (NOVO — Vídeo 05, não compila)

```java

package dio.marketplace.registration.infrastructure.event;

import dio.marketplace.catalog.infrastructure.persistence.entity.EventMetadata;
import dio.marketplace.registration.infrastructure.event;

import dio.marketplace.catalog.infrastructure.persistence.entity.EventMetadata; // Verifique se o caminho do pacote está correto ou se o módulo precisa ser exportado/importado
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.stereotype.Component;

@Component
public class EventMetadataEventListener extends AbstractMongoEventListener<EventMetadata> {
}
// Verifique se o caminho do pacote está correto ou se o módulo precisa ser exportado/importado
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.stereotype.Component;

```

`domain/Customer.java`, `domain/CustomerId.java`, `domain/CustomerRepository.java`, `infrastructure/persistence/entity/Customer.java`, `infrastructure/persistence/entity/Address.java`, `infrastructure/persistence/entity/projection/CustomerExcerpt.java`, `infrastructure/persistence/repository/CustomerEntityRepository.java`, `infrastructure/persistence/repository/JpaCustomerRepository.java`, `infrastructure/event/CustomerEventHandler.java`, `RegistrationConfiguration.java`, `MarketplaceApplication.java`, `catalog/infrastructure/persistence/entity/Event.java`, `catalog/infrastructure/event/EventListener.java` e `catalog/infrastructure/persistence/repository/EventEntityRepository.java` permanecem exatamente como no checkpoint do Vídeo 04 (documento anterior) — nenhuma alteração de conteúdo foi feita neles nesta etapa.

---

## Próximos passos: o que vem a partir do Vídeo 06

Segundo o roteiro do curso (conferido no README, que já traz os títulos e links de todos os vídeos até o 11), a sequência dos próximos vídeos deste Curso 2 é:

- **Vídeo 06 — Criando Endpoints Customizados:** deve ir além do que o Spring Data REST expõe automaticamente, criando endpoints sob medida — possivelmente com um `@RestController` escrito manualmente, complementando (e não substituindo) o que já foi exposto automaticamente até aqui. Este é também o momento mais provável para uma correção dos problemas de compilação identificados nesta etapa (`EventMetadataEntityRepository` e `EventMetadataEventListener`), já que endpoints customizados costumam exigir uma camada de repositório funcional por baixo.
- **Vídeo 07 — Implementando Redis com Spring Data:** deve introduzir o **Redis** e o mecanismo de *ticket locking* com TTL (expiração automática), mencionado no estudo de caso do Vídeo 01.
- **Vídeo 08 — Comunicação entre Microsserviços:** deve tratar de como diferentes módulos (ou serviços) do sistema trocam informações entre si.
- **Vídeo 09 — Implementando Persistência com Postgres:** deve retomar o PostgreSQL como o banco relacional "oficial" do estudo de caso original — possivelmente substituindo (ou complementando) o MySQL usado até aqui como variação didática.
- **Vídeo 10 — Evitando Overbooking:** deve aplicar as ferramentas de concorrência (provavelmente o Redis já implementado) para resolver o problema de dois clientes tentarem reservar o mesmo assento ao mesmo tempo.
- **Vídeo 11 — Consistência e Governança:** deve fechar a parte de persistência com boas práticas de consistência de dados entre os múltiplos bancos usados.
- **Vídeo 12 — Questionário:** avaliação final do Curso 2 (ainda sem link de vídeo associado no README).

> **Antes de seguir para o Vídeo 06:** diferente de todos os checkpoints anteriores, **o projeto não compila** ao final do Vídeo 05, pelos motivos detalhados na seção "Pontos de atenção" (itens 3 e 5) e nas seções 5.7/5.8. Antes de tentar rodar a aplicação localmente a partir deste ponto, vale corrigir manualmente `EventMetadataEntityRepository.java` e `EventMetadataEventListener.java` — por exemplo, transformando `EventMetadata` de fato em um documento Mongo (`@Document`, `MongoRepository<EventMetadata, String>`, `AbstractMongoEventListener<EventMetadata>` com os métodos corretamente sobrescritos como `default` ou movidos para uma classe concreta) — como um exercício prático de fixação antes de seguir para o material do Vídeo 06.
