# Tutorial de Estudos — Conectando sua API com Banco de Dados Através do Spring Data

**Continuação: multi-banco de dados manual e o componente Catalog — Vídeo 04**

- Curso: NTT Data — Jornada Tech (DIO) · Módulo 4 — "Criando Soluções Inteligentes com Spring Boot e Java Moderno"
- Curso 2 do módulo: "Conectando sua API com Banco de Dados Através do Spring Data"
- Instrutor: Thiago Poiani (Principal Engineer at Skip)
- Projeto: `marketplace`
- Documento de referência pessoal — nível iniciante em Java

---

## Sobre este documento

Este arquivo é a continuação direta de `002-Tutorial_Marketplace_Java_Spring_Data_Video03.md` e cobre exclusivamente o **Vídeo 04 — "Flexibilidade com NoSQL"** (título da aula no README; na prática, apesar do nome, o vídeo não chega a introduzir nenhum banco NoSQL — ele constrói toda a base *relacional* multi-banco que os vídeos seguintes provavelmente vão usar como alicerce antes de trazer o MongoDB, adiando a parte "NoSQL" propriamente dita para uma etapa futura). Ele foi escrito a partir das anotações atualizadas do README e **conferido diretamente contra o código-fonte real** do `.zip` enviado (`marketplace_ate_o_video_04.zip`), e não apenas contra a narrativa do README — como no documento anterior, isso importa porque existem pequenas divergências entre o texto do README e o `.java` de verdade, detalhadas na seção "Pontos de atenção".

Para concatenar este arquivo ao anterior, basta colar o conteúdo a partir da seção "Parte 4" logo abaixo do final do documento 002 (ajustando a numeração de seções, se desejar um documento único).

> **Como esta parte está organizada**
> Os blocos de código são apresentados na mesma ordem em que foram escritos na aula (seção 4.1 em diante), cada um seguido de uma explicação linha a linha em nível iniciante, com atenção especial a cada anotação nova. Ao final, há: uma seção de divergências entre aula e código real, o glossário atualizado (só termos novos), o checkpoint fiel do projeto no estado do Vídeo 04, e os próximos passos revisados.

---

## Parte 4 — Multi-Database Manual e o Componente Catalog (Vídeo 04)

Até o Vídeo 03, o projeto tinha um único componente (`registration`) e um único banco de dados (MySQL, subindo via Docker Compose). O Vídeo 04 parte da ideia de que o sistema de ticketing descrito no Vídeo 01 precisa de **mais de um banco**: um para clientes (`registration`) e outro para o catálogo de eventos (`catalog`). Como a auto-configuração automática do Spring Boot só sabe lidar com **um único** banco de dados por padrão, esse vídeo é, na prática, uma aula sobre **como assumir manualmente** tudo o que o Spring Boot fazia sozinho até aqui — datasource, pool de conexões, `EntityManagerFactory` e `TransactionManager` — uma vez que essa automação deixa de ser suficiente.

### 4.1. Criando o pacote raiz do novo componente `catalog`

O primeiro passo é criar, na IDE, um novo pacote chamado `dio.marketplace.catalog`, irmão do pacote `dio.marketplace.registration` já existente. Isso não é uma linha de código — é uma decisão de organização: cada "componente" de negócio da aplicação (registration, catalog, e futuramente outros) vive em seu próprio pacote raiz, com a mesma sub-organização interna em camadas (`domain`, `application`, `infrastructure`) usada desde o Vídeo 02. Essa separação por pacote é o que vai permitir, mais adiante, que cada componente aponte para um banco de dados diferente sem que um interfira no outro.

### 4.2. Adicionando o segundo banco de dados no Docker Compose

O arquivo `compose.yml` ganha um segundo serviço de banco de dados:

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

volumes:
  registration_data:
  catalog_data:
```

**O que está acontecendo:** `services` é a lista de containers que o Docker Compose vai orquestrar. Já existia `registration-database`, e agora entra `catalog-database` seguindo exatamente o mesmo formato — mesma imagem (`mysql`, aqui numa versão diferente, `9.6`, só para mostrar que cada serviço pode ter sua própria versão), mesmas variáveis de ambiente (nome do banco, usuário, senha), mas com uma **porta diferente do host** (`3308:3306` em vez de `3307:3306`). Isso é necessário porque os dois containers MySQL escutam internamente na porta padrão `3306`, mas não podem os dois "ocupar" a mesma porta `3306` na sua máquina (o host) ao mesmo tempo — por isso cada um é mapeado para uma porta externa distinta. Cada serviço também recebe seu próprio `volume` (`registration_data` e `catalog_data`), que é o mecanismo do Docker para persistir os dados do banco em disco mesmo que o container seja destruído e recriado — sem isso, os dados sumiriam a cada `docker compose down`. Por fim, o bloco `healthcheck` define um comando (`mysqladmin ping`) que o Docker executa periodicamente para saber se o banco já está pronto para aceitar conexões, e não apenas se o container "está de pé" — um container pode estar rodando por alguns segundos antes do MySQL realmente terminar de inicializar internamente.

### 4.3. Por que a auto-configuração automática do Spring Boot deixa de funcionar

Com dois bancos de dados no ar, o Spring Boot **não sabe mais qual dos dois** usar quando ele tenta, sozinho, criar automaticamente o `DataSource`, o `EntityManagerFactory` e o `TransactionManager` — a auto-configuração padrão do Spring Boot para JPA assume, por padrão, que existe **apenas um** banco de dados na aplicação. É por isso que o próximo passo do vídeo é abrir o `application.properties` (que, até aqui, só trazia as configurações padrão de nome da aplicação, exposição de detalhes de saúde do Actuator e o modo `start-only` do Docker Compose) para configurar manualmente as propriedades dos dois bancos, um de cada vez.

### 4.4. Configurando manualmente as propriedades dos dois bancos

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
```

**O que essas linhas fazem:** o Spring Boot tem um mecanismo genérico chamado `@ConfigurationProperties`, que permite pegar **qualquer prefixo** de propriedades (`registration.datasource`, `catalog.datasource`, etc. — nomes inventados pelo desenvolvedor, não reservados pelo framework) e "despejar" seus valores automaticamente dentro de um objeto Java, casando cada sufixo (`url`, `username`, `password`, `driver-class-name`) com um `set`/campo de mesmo nome. É exatamente esse mecanismo que a classe de configuração (próxima seção) vai usar para ler esses dois blocos. Repare que cada bloco tem seu próprio prefixo (`registration.*` e `catalog.*`) — isso é o que vai permitir que a mesma aplicação "converse" com os dois bancos ao mesmo tempo, cada componente lendo apenas o bloco que lhe pertence. A linha `registration.jpa.properties.hibernate.hbm2ddl.auto=update` merece atenção: o prefixo `registration.jpa` vai virar um objeto `JpaProperties` (classe pronta do Spring Boot), e tudo que vem depois de `.properties.` cai dentro de um mapa genérico de propriedades do Hibernate daquele objeto — nesse caso, a chave `hibernate.hbm2ddl.auto`, que controla se o Hibernate deve **criar/atualizar/validar** o schema do banco automaticamsente a partir das entidades Java. Para `registration` o valor é `update` (preserva os dados existentes e só aplica diferenças), e para `catalog` é `create` (apaga e recria o schema a cada subida da aplicação) — uma escolha didática do professor para deixar claro que cada banco pode ter sua própria estratégia, já que o catálogo, nesta fase de estudo, ainda não tem dados que precisem ser preservados entre reinícios.

> **Nota:** as linhas `spring.jpa.hibernate.ddl-auto=update` e `spring.jpa.show-sql=true`, no topo do arquivo, são resquício da configuração de banco único anterior ao Vídeo 04. Como a aplicação não usa mais um `DataSource` único auto-configurado pelo Spring Boot a partir de `spring.datasource.*`, essas duas propriedades ficam efetivamente "órfãs" — sem efeito prático a partir desta etapa, já que cada componente agora define seu próprio `hibernate.hbm2ddl.auto` via `registration.jpa.properties.*` / `catalog.jpa.properties.*`. Isso é detalhado com mais calma na seção "Pontos de atenção".

### 4.5. Criando a classe `RegistrationConfiguration`

Dentro do pacote `registration.infrastructure`, é criada a classe `RegistrationConfiguration`. Ela assume manualmente, para o banco `registration`, tudo o que o Spring Boot fazia sozinho até o Vídeo 03: criar a conexão com o banco, o pool de conexões, o `EntityManagerFactory` e o `TransactionManager`.

```java
package dio.marketplace.registration.infrastructure;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.LinkedHashMap;

@Configuration(proxyBeanMethods = false)
@EnableJpaRepositories(
        basePackages = "dio.marketplace.registration",
        entityManagerFactoryRef = "registrationEntityManagerFactory",
        transactionManagerRef = "registrationTransactionManager")
public class RegistrationConfiguration {

    @Primary
    @Bean
    @ConfigurationProperties("registration.datasource")
    public DataSourceProperties registrationDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    @ConfigurationProperties("registration.datasource.configuration")
    public HikariDataSource registrationDataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Primary
    @Bean
    @ConfigurationProperties("registration.jpa")
    public JpaProperties registrationJpaProperties() {
        return new JpaProperties();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean registrationEntityManagerFactory(DataSource dataSource,
                                                                                   JpaProperties jpaProperties) {

        var builder = new EntityManagerFactoryBuilder(
                new HibernateJpaVendorAdapter(),
                new LinkedHashMap<>(jpaProperties.getProperties()),
                null
        );

        return builder
                .dataSource(dataSource)
                .packages("dio.marketplace.registration")
                .persistenceUnit("registration")
                .properties(jpaProperties.getProperties())
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager registrationTransactionManager(LocalContainerEntityManagerFactoryBean emf) {
        return new JpaTransactionManager(emf.getObject());
    }

}
```

Vamos por partes, já que essa classe concentra vários conceitos novos de uma vez:

- **`@Configuration(proxyBeanMethods = false)`**: `@Configuration` já era conhecida — é a anotação que marca uma classe como fonte de definições de *beans* (objetos gerenciados pelo Spring) através de métodos anotados com `@Bean`. O atributo `proxyBeanMethods = false` é novo: por padrão, o Spring cria um "proxy" (um objeto intermediário) em volta da classe de configuração, para garantir que, se um método `@Bean` chamar outro método `@Bean` da mesma classe diretamente em Java, o Spring intercepte essa chamada e devolva a instância **já gerenciada** (evitando criar o bean duas vezes). Quando os métodos `@Bean` de uma classe **não chamam uns aos outros diretamente** (como é o caso aqui — cada bean recebe suas dependências como *parâmetros do método*, e não por chamada direta), esse proxy é desnecessário e só adiciona overhead; `proxyBeanMethods = false` desliga essa proteção, deixando a classe mais simples e um pouco mais rápida de inicializar.

- **`@EnableJpaRepositories(basePackages, entityManagerFactoryRef, transactionManagerRef)`**: essa anotação já era usada implicitamente pela auto-configuração do Spring Boot (por trás dos panos) até aqui — agora ela aparece de forma explícita porque a aplicação tem **mais de um** conjunto de repositórios JPA (um para `registration`, outro para `catalog`), e o Spring precisa saber qual `EntityManagerFactory` e qual `TransactionManager` usar para cada grupo. `basePackages = "dio.marketplace.registration"` diz: "procure interfaces de repositório JPA (como `CustomerEntityRepository`) só dentro deste pacote". `entityManagerFactoryRef` e `transactionManagerRef` apontam, **pelo nome do bean** (uma `String`, não uma referência de tipo), para os beans `registrationEntityManagerFactory` e `registrationTransactionManager` definidos mais abaixo na própria classe — fechando o círculo entre "quais repositórios" e "qual banco de dados por trás deles".

- **`@Primary`**: anotação do Spring que resolve um problema de ambiguidade. Como a partir de agora existem **dois** beans candidatos de cada tipo (`DataSourceProperties`, `HikariDataSource`, `JpaProperties`) — um vindo de `RegistrationConfiguration`, outro de `CatalogConfiguration` — o Spring não saberia, sozinho, qual injetar sempre que outro trecho de código pedir "me dê um `DataSource`" sem especificar qual dos dois. `@Primary` marca um bean como a escolha **padrão** nesses casos de ambiguidade — aqui, os beans de `registration` são os "primários" (a escolha padrão de todo o sistema, exceto onde o contrário for pedido explicitamente).

- **`@ConfigurationProperties("registration.datasource")`**: como explicado na seção 4.4, essa anotação faz o Spring ler todas as propriedades sob o prefixo indicado (aqui, `registration.datasource.*` do `application.properties`) e preenchê-las automaticamente nos campos do objeto retornado pelo método — nesse caso, um `DataSourceProperties`, classe pronta do Spring Boot que já sabe representar `url`, `username`, `password` e `driver-class-name` de uma conexão de banco.

- **`DataSourceProperties` → `HikariDataSource`**: o método `registrationDataSource` recebe o `DataSourceProperties` já preenchido (o Spring injeta automaticamente o bean `registrationDataSourceProperties()` como parâmetro) e chama `properties.initializeDataSourceBuilder().type(HikariDataSource.class).build()`. Isso constrói, a partir daquelas informações genéricas (url/usuário/senha), uma instância concreta de `HikariDataSource` — a implementação de **pool de conexões** usada pelo Spring Boot por padrão. Um *pool de conexões* é uma "caixa" de conexões já abertas com o banco, reaproveitadas entre requisições, em vez de abrir e fechar uma conexão nova a cada operação (o que seria caro em performance) — é exatamente o tipo de boilerplate que o slide "Fase 1: A Era Sombria do JDBC" (Vídeo 01) descrevia como fardo manual, e que aqui volta a aparecer de forma explícita (embora ainda muito mais simples do que JDBC puro) porque a aplicação tem mais de um banco.

- **`JpaProperties`**: outra classe pronta do Spring Boot, que representa as configurações específicas de JPA/Hibernate (como o `hibernate.hbm2ddl.auto` visto em 4.4). Aqui ela é lida a partir do prefixo `registration.jpa`.

- **`LocalContainerEntityManagerFactoryBean` e `EntityManagerFactoryBuilder`**: o `EntityManagerFactory` é o objeto central do JPA — é ele quem sabe criar `EntityManager`s, que por sua vez são os objetos que efetivamente conversam com o banco para salvar/buscar entidades. `EntityManagerFactoryBuilder` é uma classe auxiliar do Spring Boot que facilita a montagem desse `EntityManagerFactory` a partir de um `JpaVendorAdapter` (aqui, `HibernateJpaVendorAdapter`, que diz "estou usando Hibernate como implementação de JPA"), de um mapa de propriedades do Hibernate, e de um `DataSource`. O método encadeia `.dataSource(...)`, `.packages(...)` (em quais pacotes o Hibernate deve procurar por classes anotadas com `@Entity`), `.persistenceUnit(...)` (um nome interno para essa "unidade de persistência" — necessário porque agora existem duas, uma para cada banco) e `.properties(...)` (as propriedades do Hibernate), finalizando com `.build()`.

- **`PlatformTransactionManager` / `JpaTransactionManager`**: o `TransactionManager` é o objeto responsável por controlar transações — garantir que um conjunto de operações no banco aconteça de forma **atômica** (tudo ou nada), conceito citado desde o slide "Integridade" do Vídeo 01. `JpaTransactionManager` é a implementação concreta usada quando se trabalha com JPA, construída aqui a partir do `EntityManagerFactory` (`emf.getObject()`, já que `LocalContainerEntityManagerFactoryBean` é uma *fábrica de fábrica* — o `.getObject()` extrai a instância real de `EntityManagerFactory` de dentro dela).

### 4.6. Criando a classe `CatalogConfiguration`

Seguindo o mesmo padrão, é criada a classe `CatalogConfiguration`, dentro do pacote raiz `catalog`, repetindo a estrutura da `RegistrationConfiguration` — mas com uma diferença central:

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

**A diferença central: `@Qualifier` em vez de `@Primary`.** Como os beans de `registration` já foram marcados como `@Primary` (a escolha padrão), os beans equivalentes de `catalog` **não podem** também ser `@Primary` — só pode existir um bean primário por tipo. Em vez disso, eles recebem `@Qualifier("catalog")`, um "rótulo" nomeado. Isso tem uma consequência direta: em qualquer ponto do código que precise **especificamente** do datasource, do `EntityManagerFactory` ou do `JpaProperties` do catálogo (e não o de registration, que seria escolhido por padrão por ser `@Primary`), é preciso repetir esse mesmo `@Qualifier("catalog")` no parâmetro correspondente — é exatamente o que aparece nos parâmetros dos métodos `catalogDataSource`, `catalogEntityManagerFactory` e `catalogTransactionManager`. Em resumo: `@Primary` diz "sou a opção padrão quando ninguém pede um rótulo específico"; `@Qualifier("nome")` diz "só me injete em quem pedir exatamente por este rótulo". O restante da estrutura — leitura de `catalog.datasource`, criação do `HikariDataSource`, do `LocalContainerEntityManagerFactoryBean` (com `persistenceUnit("catalog")`) e do `PlatformTransactionManager` — segue a mesma lógica já explicada na seção 4.5.

### 4.7. Erro ao subir a aplicação e diagnóstico pelo Actuator

Na primeira tentativa de rodar a aplicação com as duas configurações manuais, ocorre o erro `HibernateException: Unable to determine Dialect without JDBC metadata`, encadeado a partir de uma `ServiceException` do Hibernate. Um "dialect" (dialeto), no Hibernate, é a tradução das operações genéricas de SQL para as particularidades exatas do banco usado (MySQL, PostgreSQL, etc.) — e o Hibernate só consegue **descobrir automaticamente** qual dialeto usar se conseguir, de fato, abrir uma conexão de teste com o banco e ler seus metadados. O erro, portanto, indica que um dos dois containers MySQL configurados no Compose não estava realmente no ar no momento em que a aplicação tentou se conectar (provavelmente porque só um dos serviços havia sido subido manualmente até então). Depois de garantir os dois containers ativos com `docker compose up` e reiniciar a aplicação, a verificação é feita acessando `localhost:8080/actuator/health` no navegador — o mesmo endpoint do Actuator, biblioteca de monitoramento já adicionada nos vídeos anteriores. A resposta passa a trazer o componente `db` com **dois** sub-componentes, `catalogDataSource` e `registrationDataSource`, ambos do tipo MySQL e com status `"UP"` — confirmando visualmente que a aplicação está, agora, conectada aos dois bancos simultaneamente.

### 4.8. Estrutura de pacotes do componente `catalog`

Com a conexão dos dois bancos validada, o restante da estrutura de pastas do componente `catalog` é criado, replicando a organização já usada em `registration`: `domain`, `application` e `infrastructure`, e dentro de `infrastructure`, os sub-pacotes `persistence.entity` (onde vai a entidade JPA) e `persistence.repository` (onde vai o repositório do Spring Data). Nesta etapa do curso, `domain` e `application` ficam **vazios** — só a camada `infrastructure` recebe código de fato, já que o vídeo ainda não chegou à parte de modelar regras de negócio para o catálogo (isso é coerente com o que já foi visto: em `registration`, o pacote `application` também segue vazio até este ponto do curso).

### 4.9. Criando a entidade `Event`

Dentro de `catalog.infrastructure.persistence.entity`, é criada a classe `Event`, que representa um evento (show, palestra, apresentação etc.) no banco de dados do catálogo:

```java
package dio.marketplace.catalog.infrastructure.persistence.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

import java.util.UUID;

import dio.marketplace.catalog.infrastructure.event.EventListener;

@Entity
@EntityListeners(EventListener.class)
@Data
@RequiredArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Instant date;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Instant createdOn;
}
```

A maior parte desta entidade já é familiar (ela segue o mesmo padrão de `Customer`/`Address` dos vídeos anteriores), mas há uma anotação nova:

- **`@Entity`**: já conhecida — marca a classe como uma entidade JPA, mapeada para uma tabela do banco (aqui, uma tabela `event` seria criada automaticamente, seguindo a convenção padrão de nomes do Hibernate).
- **`@EntityListeners(EventListener.class)`** *(novo)*: essa anotação registra uma classe **auxiliar** (`EventListener`, vista na próxima seção) para "escutar" o ciclo de vida desta entidade específica — ou seja, ser avisada automaticamente sempre que um `Event` for persistido, atualizado ou removido do banco. É uma alternativa a colocar os métodos de callback (como o `@PrePersist` visto no `Customer` do Vídeo 03) **dentro da própria entidade**: em vez disso, aqui esses métodos ficam isolados em uma classe separada, referenciada por este atributo.
- **`@Data` / `@RequiredArgsConstructor`** (Lombok, já conhecidas): geram automaticamente getters, setters, `equals`, `hashCode`, `toString` (`@Data`) e um construtor com os campos marcados `final` (`@RequiredArgsConstructor` — aqui, como nenhum campo é `final`, o construtor gerado acaba sendo vazio, equivalente ao construtor padrão).
- **`@Id` + `@GeneratedValue(strategy = GenerationType.UUID)`**: diferente da entidade `Customer` original (que gerava o `UUID` manualmente em um método `@PrePersist`), aqui o próprio JPA/Hibernate assume a geração do identificador, através da estratégia `GenerationType.UUID` — uma das opções prontas de `@GeneratedValue`, que instrui o Hibernate a gerar um `UUID` aleatório sozinho, sem que o desenvolvedor precise escrever esse código à mão.
- **`@NotBlank`** (Jakarta Validation, já conhecida): garante, na camada de validação, que o campo `title` não seja nulo nem composto só por espaços em branco.
- **`Instant`** (novo neste contexto, embora seja uma classe padrão do Java): representa um ponto exato no tempo (data + hora), sem fuso horário embutido — sempre em UTC internamente. É o tipo usado tanto para `date` (a data do evento) quanto para `createdOn` (o momento em que o registro foi criado), e é também o motivo de a criação de eventos via API usar um **timestamp Unix** (um número inteiro representando segundos ou milissegundos desde 1º de janeiro de 1970) em vez de uma data escrita por extenso — é o formato mais direto de serializar um `Instant` em JSON.
- **`@CreationTimestamp`** (Hibernate, já conhecida): preenche `createdOn` automaticamente com o instante da inserção, e `updatable = false` impede que esse campo seja alterado em atualizações futuras do registro.

### 4.10. Criando o `EventListener`

Ainda dentro de `catalog.infrastructure`, mas em um novo sub-pacote `event`, é criada a classe `EventListener`, referenciada pela anotação `@EntityListeners` da entidade `Event`:

```java
package dio.marketplace.catalog.infrastructure.event;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dio.marketplace.catalog.infrastructure.persistence.entity.Event;

public class EventListener {

    private static final Logger logger = LoggerFactory.getLogger(EventListener.class);

    @PostPersist
    public void onEventCreated(Event event) {
        logger.info("Event created via @PostPersist {}", event);
    }

    @PostUpdate
    public void onEventUpdated(Event event) {
        logger.info("Event updated via @PostUpdate {}", event);
    }

    @PostRemove
    public void onEventDeleted(Event event) {
        logger.info("Event deleted via @PostRemove {}", event);
    }
}
```

Esse listener merece uma comparação direta com o `CustomerEventHandler` já existente (do Vídeo 03), porque os dois fazem algo parecido — reagir a mudanças em uma entidade — mas usando **dois mecanismos completamente diferentes**:

| | `CustomerEventHandler` (Vídeo 03) | `EventListener` (Vídeo 04) |
|---|---|---|
| Camada responsável | **Spring Data REST** (a camada web/REST) | **JPA/Hibernate** puro (a camada de persistência) |
| Anotação na classe | `@RepositoryEventHandler` + `@Component` | nenhuma — classe Java comum |
| Como é registrado | Escaneado automaticamente pelo Spring como *bean* | Referenciado explicitamente na entidade via `@EntityListeners(EventListener.class)` |
| Anotações nos métodos | `@HandleAfterCreate`, `@HandleAfterSave`, `@HandleAfterDelete` | `@PostPersist`, `@PostUpdate`, `@PostRemove` |
| Quando dispara | Só quando a operação passa pela **API REST** gerada pelo Spring Data REST | Sempre que o **Hibernate** persistir/atualizar/remover a entidade — mesmo se isso acontecer fora da API REST (ex.: um teste automatizado chamando o repositório diretamente em Java) |
| Pode usar `@Autowired`/injeção de dependência? | Sim — é um `@Component`, gerenciado pelo Spring | Não diretamente — o Hibernate instancia a classe sozinho, fora do container do Spring |

Em outras palavras: `@RepositoryEventHandler` escuta a **porta de entrada web**, enquanto `@EntityListeners` escuta o **próprio ciclo de vida do objeto no banco**, não importa por onde ele chegou até ali. O professor usa `EventListener` aqui como uma forma de apresentar essa segunda abordagem, mais próxima do JPA puro (que já existia antes mesmo do Spring existir), como contraste ao que foi visto no vídeo anterior.

### 4.11. Criando o `EventEntityRepository`

Por fim, no pacote `persistence.repository`, é criada a interface `EventEntityRepository`:

```java
package dio.marketplace.catalog.infrastructure.persistence.repository;

import dio.marketplace.catalog.infrastructure.persistence.entity.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface EventEntityRepository extends CrudRepository<Event, UUID> {
}
```

Essa interface é deliberadamente mais simples do que a `CustomerEntityRepository` do Vídeo 03: ela estende apenas `CrudRepository<Event, UUID>` (as operações básicas de criar, ler, atualizar e remover), **sem** também estender `PagingAndSortingRepository`. Isso é uma escolha intencional para mostrar o contraste: sem `PagingAndSortingRepository`, o endpoint de eventos gerado pelo Spring Data REST **não oferece suporte a paginação nem ordenação** — algo perceptível mais adiante, ao explorar a API pelo HAL Explorer. `@RepositoryRestResource`, já conhecida do Vídeo 03, continua fazendo o mesmo trabalho: basta essa anotação sobre a interface para o Spring Data REST expor automaticamente um conjunto completo de endpoints HTTP para `Event`, sem nenhum controller escrito à mão.

### 4.12. Explorando e testando a API de eventos pelo HAL Explorer

Com a aplicação reiniciada, o HAL Explorer (já adicionado no Vídeo 03) passa a listar, na raiz da API, três links: `customers`, `events` e `profile` — confirmando que o novo endpoint já está disponível automaticamente. Diferente de `customers`, o link de `events` **não** apresenta o *URI Template* `{?page,size,sort*}` visto no Vídeo 03, exatamente por causa da ausência de `PagingAndSortingRepository` explicada na seção anterior.

O fluxo de teste segue o ciclo completo de CRUD:

1. **`POST /events`** — uma nova requisição é montada no HAL Explorer. O campo `date` é preenchido com um valor numérico (um *timestamp Unix* — quantidade de segundos ou milissegundos desde 1970 — gerado com a ajuda de um conversor de epoch em uma aba auxiliar do navegador), já que o campo `date` da entidade é do tipo `Instant`, e essa é a forma padrão de representar um `Instant` em JSON. O campo `title` recebe o valor `"Evento 1"`. A requisição é enviada e a API responde com `201 Created`, trazendo os links `self` e `event` para o recurso recém-criado, além dos campos `createdOn`, `date` e `title` — confirmando a persistência.
2. **`PATCH` (no link `self` do evento criado)** — uma atualização parcial é enviada, com o corpo contendo **apenas** `title: "Evento Novo"`. A resposta retorna `200 OK`, com `title` já atualizado e `date`/`createdOn` inalterados — o mesmo comportamento de atualização parcial já visto com `Customer` no Vídeo 03, aqui obtido "de graça" outra vez, só por o repositório estender `CrudRepository`.
3. **`DELETE` (no mesmo recurso)** — a resposta retorna `200 OK`, confirmando a remoção.
4. **`GET /events`** — ao acessar novamente a coleção, `_embedded.events` aparece vazia, fechando o ciclo e confirmando que o evento foi de fato removido do banco `catalog`.

Esse teste validado ponta a ponta mostra que, com pouquíssimo código escrito manualmente (uma entidade + uma interface de uma linha), a aplicação já ganhou uma segunda API REST completa, conectada a um segundo banco de dados totalmente independente do primeiro.

---

## Pontos de atenção: divergências entre a aula (README) e o código real (Vídeo 04)

Como no documento anterior, esta seção lista o que foi conferido **diretamente no `.java`** do `.zip` e diverge (ou complementa) o que está escrito no README. Nenhuma dessas divergências impede a aplicação de compilar ou rodar — são detalhes que só aparecem olhando o código de verdade.

1. **As três correções pendentes do Vídeo 03 já foram aplicadas.** O documento anterior (`002-Tutorial...`) listava três problemas que impediam a compilação ao final do Vídeo 03: o arquivo `CustomerExcept.java` com nome incorreto, o import errado de `@Value` na projeção, e a falta do `@` antes de `RepositoryEventHandler`. No código do Vídeo 04, os três já aparecem corrigidos: o arquivo está renomeado para `CustomerExcerpt.java`, a projeção importa `org.springframework.beans.factory.annotation.Value` (e não `lombok.Value`), e `CustomerEventHandler` está corretamente anotado com `@RepositoryEventHandler`. Essa correção não é narrada no README do Vídeo 04 — só é visível comparando os dois `.zip`.
2. **`CustomerEventHandler` mudou de pacote.** No `.zip` do Vídeo 03, o arquivo ficava em `infrastructure/persistence/event/CustomerEventHandler.java`. No `.zip` do Vídeo 04, o mesmo arquivo (com o mesmo conteúdo) passou a ficar em `infrastructure/event/CustomerEventHandler.java` — um nível acima, fora de `persistence`. Faz sentido como organização (um *event handler* de repositório REST não é, estritamente, parte da camada de persistência), e é coerente com o pacote `catalog.infrastructure.event` criado do zero já nesse formato para o `EventListener` — mas, de novo, é uma mudança silenciosa, não mencionada no texto do README.
3. **Typo no `package(...)` do código exibido no README para `RegistrationConfiguration`.** O bloco de código do README usa `.packages("dio.registration")` dentro de `registrationEntityManagerFactory`; o código real usa `.packages("dio.marketplace.registration")` — o pacote correto e completo, coerente com o resto do projeto. Um `.packages("dio.registration")` de fato faria o Hibernate não encontrar nenhuma entidade `@Entity` de registration, gerando um `EntityManagerFactory` "vazio".
4. **Divergência na assinatura do `EntityManagerFactoryBuilder` entre README e código real.** O bloco de código do README mostra `x -> new LinkedHashMap<>(jpaProperties.getProperties())` (uma expressão lambda) como segundo argumento do construtor de `EntityManagerFactoryBuilder`. O código real passa `new LinkedHashMap<>(jpaProperties.getProperties())` **diretamente**, sem lambda — e é essa a forma correta: o construtor de `EntityManagerFactoryBuilder` espera um `Map<String, ?>` como segundo parâmetro, não uma função. A versão com lambda do README não compilaria; a diferença é, muito provavelmente, um artefato de como o vídeo foi transcrito para o README, e não algo que o instrutor de fato digitou.
5. **Bloco de código do README para `Event.java` está incompleto.** O trecho reproduzido no README pula direto de `@RequiredArgsConstructor` para o corpo dos campos, sem a linha `public class Event {` nem o import de `EventListener` — novamente, um corte no material de apoio, não um problema real: o arquivo `.java` de verdade está completo e compila normalmente.
6. **Assimetria entre `RegistrationConfiguration` e `CatalogConfiguration`.** No método `registrationEntityManagerFactory`, a cadeia de chamadas termina com `.properties(jpaProperties.getProperties()).build()`; no método equivalente de `CatalogConfiguration` (`catalogEntityManagerFactory`), essa chamada a `.properties(...)` não existe — a cadeia termina direto em `.persistenceUnit("catalog").build()`. Na prática isso não muda o comportamento observável (as propriedades do Hibernate, incluindo `hibernate.hbm2ddl.auto`, já chegam ao builder pelo mapa passado no construtor de `EntityManagerFactoryBuilder`), mas é uma inconsistência de estilo entre duas classes que deveriam ser espelhos uma da outra — vale unificar se o projeto continuar crescendo.
7. **Os prefixos `registration.datasource.configuration` e `catalog.datasource.configuration` não têm nenhuma chave correspondente no `application.properties`.** Ambos os métodos `registrationDataSource`/`catalogDataSource` estão anotados com `@ConfigurationProperties("...datasource.configuration")`, mas nenhuma propriedade sob esse prefixo foi definida — o que significa que, hoje, esse `@ConfigurationProperties` simplesmente não injeta nada além do que já vem do `DataSourceProperties` recebido por parâmetro. Não é um erro (o Hikari cai nos valores padrão de pool), mas é um ponto de confusão comum para iniciantes: esse prefixo existe **preparado** para configurações específicas do Hikari (ex.: `registration.datasource.configuration.maximum-pool-size=10`), caso um dia sejam necessárias.
8. **`spring.jpa.hibernate.ddl-auto=update` e `spring.jpa.show-sql=true`, no topo do `application.properties`, ficaram órfãos.** Como já mencionado na seção 4.4, essas duas propriedades pertenciam à configuração de banco único anterior a este vídeo. Como não existe mais um `DataSource` único auto-configurado pelo Spring Boot a partir de `spring.datasource.*`, a auto-configuração de JPA que liga essas propriedades também deixa de atuar — cada componente agora controla seu próprio `hibernate.hbm2ddl.auto` via `registration.jpa.properties.*` / `catalog.jpa.properties.*`. Não chega a ser um bug (não quebra nada), mas são linhas que poderiam ser removidas para não confundir uma leitura futura do arquivo.

---

## Glossário de conceitos Java e Spring — termos novos do Vídeo 04

Esta seção só lista termos que **ainda não apareciam** nos glossários anteriores. Para os termos básicos de Java e os termos de Spring Data REST/HATEOAS/JPA já vistos, consulte os documentos 001-002 e 002-003.

### Multi-datasource manual (Spring Boot + JPA)

| Termo | Significado |
|---|---|
| `DataSource` | Interface padrão do Java (`javax.sql.DataSource`) que representa uma fonte de conexões com um banco de dados. |
| `HikariDataSource` / HikariCP | Implementação de *pool de conexões* (conjunto de conexões abertas e reaproveitadas) usada por padrão pelo Spring Boot; considerada uma das mais rápidas do ecossistema Java. |
| `DataSourceProperties` | Classe pronta do Spring Boot que representa as propriedades básicas de conexão (`url`, `username`, `password`, `driver-class-name`), tipicamente preenchida via `@ConfigurationProperties`. |
| `@ConfigurationProperties("prefixo")` | Anotação que faz o Spring ler todas as propriedades sob um prefixo do `application.properties` e preenchê-las automaticamente nos campos de um objeto Java. |
| `initializeDataSourceBuilder()` | Método de `DataSourceProperties` que inicia a construção de um `DataSource` concreto a partir das propriedades já lidas. |
| `JpaProperties` | Classe pronta do Spring Boot que representa as propriedades de configuração do JPA/Hibernate (ex.: `hibernate.hbm2ddl.auto`), reunidas em um mapa genérico. |
| `EntityManagerFactoryBuilder` | Classe auxiliar do Spring Boot que monta um `EntityManagerFactory` a partir de um `JpaVendorAdapter`, um mapa de propriedades e um `DataSource`. |
| `LocalContainerEntityManagerFactoryBean` | *Factory bean* do Spring que produz (via `.getObject()`) a instância real de `EntityManagerFactory` usada pelo JPA. |
| `HibernateJpaVendorAdapter` | Implementação de `JpaVendorAdapter` que informa ao Spring que o provedor concreto de JPA em uso é o Hibernate. |
| `PlatformTransactionManager` / `JpaTransactionManager` | Interface e implementação do Spring responsáveis por controlar transações (garantir operações atômicas) sobre um `EntityManagerFactory`. |
| `@EnableJpaRepositories(basePackages, entityManagerFactoryRef, transactionManagerRef)` | Anotação que ativa o Spring Data JPA para um conjunto específico de pacotes, apontando explicitamente qual `EntityManagerFactory` e qual `TransactionManager` usar — necessária quando existe mais de uma fonte de dados na aplicação. |
| `@Primary` | Anotação que marca um bean como a escolha padrão do Spring sempre que houver mais de um candidato do mesmo tipo e nenhum qualificador for pedido explicitamente. |
| `@Qualifier("nome")` | Anotação que dá um rótulo nomeado a um bean (ou ao parâmetro que deseja recebê-lo), usada para desambiguar entre múltiplos candidatos do mesmo tipo quando nenhum deles pode ser o `@Primary`. |
| `@Configuration(proxyBeanMethods = false)` | Variante de `@Configuration` que desliga a criação de um proxy em volta da classe, apropriada quando os métodos `@Bean` não se chamam diretamente uns aos outros — reduz overhead de inicialização. |
| `persistenceUnit(nome)` | Identificador interno atribuído a um `EntityManagerFactory`, necessário para diferenciar múltiplas "unidades de persistência" (múltiplos bancos) dentro da mesma aplicação JPA. |

### Docker Compose e Actuator (multi-serviço)

| Termo | Significado |
|---|---|
| `healthcheck` (Docker Compose) | Bloco de configuração de um serviço Docker que define um comando periódico (aqui, `mysqladmin ping`) para verificar se o container está de fato pronto para uso, além de apenas "rodando". |
| Volume nomeado (Docker) | Mecanismo do Docker Compose para persistir dados em disco de forma independente do ciclo de vida do container, evitando perda de dados ao recriá-lo. |
| `/actuator/health` (multi-datasource) | Endpoint do Spring Boot Actuator que, com múltiplos bancos configurados, passa a listar um sub-componente de saúde para cada `DataSource` presente na aplicação. |

### JPA: ciclo de vida via `@EntityListeners`

| Termo | Significado |
|---|---|
| `@EntityListeners(Classe.class)` | Anotação JPA que associa a uma entidade uma classe externa responsável por reagir aos eventos do seu ciclo de vida — alternativa a colocar os métodos de callback dentro da própria entidade. |
| `@PostPersist` | Anotação JPA que marca um método para ser executado imediatamente **depois** que uma entidade é inserida no banco pela primeira vez. |
| `@PostUpdate` | Anotação JPA que marca um método para ser executado imediatamente **depois** que uma entidade existente é atualizada no banco. |
| `@PostRemove` | Anotação JPA que marca um método para ser executado imediatamente **depois** que uma entidade é removida do banco. |
| `Instant` (`java.time`) | Classe padrão do Java que representa um ponto exato no tempo (data e hora), sempre em UTC, comumente serializado em JSON como um *timestamp* numérico. |
| Timestamp Unix / *epoch* | Forma de representar um instante no tempo como um número inteiro de segundos (ou milissegundos) decorridos desde 1º de janeiro de 1970 — formato padrão usado para serializar valores `Instant` em JSON. |

---

## Estado atual do projeto (checkpoint do Vídeo 04)

Este é o retrato fiel do código-fonte na etapa atual, conferido diretamente nos arquivos do `marketplace_ate_o_video_04.zip` — não na narrativa do README. As correções pendentes do Vídeo 03 já estão aplicadas (ver item 1 de "Pontos de atenção"); os arquivos abaixo refletem exatamente o que está salvo no projeto.

### Estrutura de pastas

```
marketplace/
├── build.gradle
├── compose.yml
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
    │   │   │       ├── RegistrationConfiguration.java         (NOVO — Vídeo 04)
    │   │   │       ├── event/
    │   │   │       │   └── CustomerEventHandler.java          (movido de infrastructure/persistence/event)
    │   │   │       └── persistence/
    │   │   │           ├── entity/
    │   │   │           │   ├── Customer.java
    │   │   │           │   ├── Address.java
    │   │   │           │   └── projection/
    │   │   │           │       └── CustomerExcerpt.java       (nome já corrigido)
    │   │   │           └── repository/
    │   │   │               ├── CustomerEntityRepository.java
    │   │   │               └── JpaCustomerRepository.java
    │   │   └── catalog/                                       (NOVO componente — Vídeo 04)
    │   │       ├── CatalogConfiguration.java
    │   │       ├── domain/                                    (ainda vazio)
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
    │       └── application.properties
    └── test/                                                  (ainda vazio nesta etapa)
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

volumes:
  registration_data:
  catalog_data:
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
```

### `registration/infrastructure/RegistrationConfiguration.java`

```java
package dio.marketplace.registration.infrastructure;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.LinkedHashMap;

@Configuration(proxyBeanMethods = false)
@EnableJpaRepositories(
        basePackages = "dio.marketplace.registration",
        entityManagerFactoryRef = "registrationEntityManagerFactory",
        transactionManagerRef = "registrationTransactionManager")
public class RegistrationConfiguration {

    @Primary
    @Bean
    @ConfigurationProperties("registration.datasource")
    public DataSourceProperties registrationDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    @ConfigurationProperties("registration.datasource.configuration")
    public HikariDataSource registrationDataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Primary
    @Bean
    @ConfigurationProperties("registration.jpa")
    public JpaProperties registrationJpaProperties() {
        return new JpaProperties();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean registrationEntityManagerFactory(DataSource dataSource,
                                                                                   JpaProperties jpaProperties) {

        var builder = new EntityManagerFactoryBuilder(
                new HibernateJpaVendorAdapter(),
                new LinkedHashMap<>(jpaProperties.getProperties()),
                null
        );

        return builder
                .dataSource(dataSource)
                .packages("dio.marketplace.registration")
                .persistenceUnit("registration")
                .properties(jpaProperties.getProperties())
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager registrationTransactionManager(LocalContainerEntityManagerFactoryBean emf) {
        return new JpaTransactionManager(emf.getObject());
    }

}
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

### `catalog/infrastructure/persistence/entity/Event.java`

```java
package dio.marketplace.catalog.infrastructure.persistence.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

import java.util.UUID;

import dio.marketplace.catalog.infrastructure.event.EventListener;

@Entity
@EntityListeners(EventListener.class)
@Data
@RequiredArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Instant date;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Instant createdOn;
}
```

### `catalog/infrastructure/event/EventListener.java`

```java
package dio.marketplace.catalog.infrastructure.event;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dio.marketplace.catalog.infrastructure.persistence.entity.Event;

public class EventListener {

    private static final Logger logger = LoggerFactory.getLogger(EventListener.class);

    @PostPersist
    public void onEventCreated(Event event) {
        logger.info("Event created via @PostPersist {}", event);
    }

    @PostUpdate
    public void onEventUpdated(Event event) {
        logger.info("Event updated via @PostUpdate {}", event);
    }

    @PostRemove
    public void onEventDeleted(Event event) {
        logger.info("Event deleted via @PostRemove {}", event);
    }
}
```

### `catalog/infrastructure/persistence/repository/EventEntityRepository.java`

```java
package dio.marketplace.catalog.infrastructure.persistence.repository;

import dio.marketplace.catalog.infrastructure.persistence.entity.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface EventEntityRepository extends CrudRepository<Event, UUID> {
}
```

### `registration/infrastructure/event/CustomerEventHandler.java` (novo caminho — ver nota 2 de "Pontos de atenção")

```java
package dio.marketplace.registration.infrastructure.event;

import dio.marketplace.registration.infrastructure.persistence.entity.Customer;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;

@Component
@RepositoryEventHandler
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

### `registration/infrastructure/persistence/entity/projection/CustomerExcerpt.java` (já corrigido)

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

`domain/Customer.java`, `domain/CustomerId.java`, `domain/CustomerRepository.java`, `infrastructure/persistence/entity/Customer.java`, `infrastructure/persistence/entity/Address.java`, `infrastructure/persistence/repository/CustomerEntityRepository.java`, `infrastructure/persistence/repository/JpaCustomerRepository.java`, `MarketplaceApplication.java` e o bloco `dependencies` do `build.gradle` permanecem exatamente como no checkpoint do Vídeo 03 (documento anterior) — nenhuma alteração de conteúdo foi feita neles nesta etapa, além da mudança de localização já registrada para `CustomerEventHandler.java`.

---

## Próximos passos: o que vem a partir do Vídeo 05

Segundo o roteiro do curso (conferido no README), a sequência dos próximos vídeos deste Curso 2 é:

- **Vídeo 05 — Multi-Database com Docker:** com a configuração manual de dois bancos MySQL já dominada neste vídeo, a expectativa é que o Vídeo 05 formalize e talvez automatize essa orquestração multi-container no `compose.yml` — possivelmente introduzindo o **MongoDB** mencionado desde o Vídeo 01 (o "NoSQL" que o título do Vídeo 04 prometia mas ainda não entregou), já que o estudo de caso original prevê PostgreSQL + MongoDB + Redis, e o projeto até aqui usa apenas MySQL como variação didática para os dois bancos relacionais.
- **Vídeo 06 — Criando Endpoints Customizados:** deve ir além do que o Spring Data REST expõe automaticamente, criando endpoints sob medida — possivelmente com um `@RestController` escrito manualmente, complementando (e não substituindo) o que já foi exposto automaticamente até aqui.
- **Vídeo 07 — Implementando Redis com Spring Data:** deve introduzir o **Redis** e o mecanismo de *ticket locking* com TTL (expiração automática), mencionado no estudo de caso do Vídeo 01.
- **Vídeo 08 — Comunicação entre Microsserviços:** deve tratar de como diferentes módulos (ou serviços) do sistema trocam informações entre si.
- **Vídeo 09 — Implementando Persistência com Postgres:** deve retomar o PostgreSQL como o banco relacional "oficial" do estudo de caso original — possivelmente substituindo (ou complementando) o MySQL usado até aqui como variação didática.
- **Vídeo 10 — Evitando Overbooking:** deve aplicar as ferramentas de concorrência (provavelmente o Redis já implementado) para resolver o problema de dois clientes tentarem reservar o mesmo assento ao mesmo tempo.
- **Vídeo 11 — Consistência e Governança:** deve fechar a parte de persistência com boas práticas de consistência de dados entre os múltiplos bancos usados.
- **Vídeo 12 — Questionário:** avaliação final do Curso 2.

> **Antes de seguir para o Vídeo 05:** o projeto compila e roda normalmente ao final do Vídeo 04 — não há correções pendentes como havia entre os Vídeos 03 e 04. Vale, no entanto, ficar de olho nos dois pontos "órfãos" registrados em "Pontos de atenção" (itens 7 e 8) caso o Vídeo 05 volte a mexer no `application.properties`, já que eles podem gerar dúvida sobre qual configuração está de fato em uso a cada momento.
