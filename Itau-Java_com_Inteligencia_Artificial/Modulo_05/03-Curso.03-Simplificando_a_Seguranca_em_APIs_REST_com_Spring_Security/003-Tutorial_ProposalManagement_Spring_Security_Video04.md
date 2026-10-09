# Tutorial de Estudos — Simplificando a Segurança em APIs REST com Spring Security

**Segurança com Banco de Dados: substituindo o `UserDetailsService` em memória por JPA + MySQL — Vídeo 04**

- Curso: NTT Data — Jornada Tech (DIO) · Módulo 4 — Bootcamp Java + Spring + AI
- Curso: "Simplificando a Segurança em APIs REST com Spring Security"
- Instrutor: Thiago Poiani (Principal Engineer at Skip)
- Projeto: `proposal-managemnet` (pacote base `dio.proposalmanagement`)
- Documento de referência pessoal — nível iniciante em Java
- Continuação de: `002-Tutorial_ProposalManagement_Spring_Security_Video03.md`

---

## Sobre este documento

Este é o **terceiro tutorial** da série, cobrindo o **Vídeo 04 — "Segurança com Banco de Dados"**. Ele foi escrito a partir de três fontes conferidas diretamente: a transcrição do vídeo (`transcricao.md`), a seção de anotações do Vídeo 04 no README atualizado (desta vez preenchida, ao contrário do que aconteceu no Vídeo 03) e o código-fonte real do projeto na etapa correspondente (`proposal-managemnet_ate_o_video04.zip`), conferido arquivo a arquivo.

Segue exatamente o mesmo formato dos tutoriais anteriores: bloco de código → explicação linha a linha, em nível iniciante → destaques de "porquê" → uma seção de "Pontos de atenção" comparando a narrativa da aula/README com o que está realmente no seu `.zip` → glossário (só termos novos) → checkpoint fiel do código → próximos passos atualizados.

> Nesta etapa, o README traz imagens dos slides e do editor da aula, que não fazem parte deste documento — aqui, cada trecho de imagem foi convertido na explicação textual correspondente, sempre validada contra o `.zip`.

---

## Parte 4 — Segurança com Banco de Dados (Vídeo 04)

### 4.1. O que muda neste vídeo

Até o Vídeo 03, os usuários da aplicação viviam **em memória**, dentro de um bean `InMemoryUserDetailsManager` criado na inicialização — uma estrutura de estudo, que perde todos os dados a cada reinício da aplicação. O objetivo deste vídeo é substituir essa fonte de dados por um **banco de dados relacional real** (MySQL), mantendo intacta toda a engrenagem de autenticação já construída (o filtro REST do Vídeo 03, o `AuthenticationManager`, o `PasswordEncoder`).

Para isso, quatro frentes são trabalhadas em sequência:

1. Subir um banco MySQL local via **Docker Compose**, gerenciado automaticamente pelo próprio Spring Boot.
2. Adicionar **Spring Data JPA** (a camada de persistência do Spring) e o **Lombok** (biblioteca que reduz código repetitivo).
3. Modelar a entidade `User` como uma tabela do banco, fazendo-a implementar a mesma interface `UserDetails` já usada desde o Vídeo 01 — reaproveitando, mais uma vez, a infraestrutura pronta do Spring Security.
4. Criar uma nova implementação de `UserDetailsService` (o `JpaUserDetailsService`) que busca os usuários no banco em vez de em uma lista na memória, e um `CommandLineRunner` para popular o banco com usuários de teste.

### 4.2. Subindo um banco de dados com Docker Compose

A primeira peça é um arquivo `compose.yml` na raiz do projeto, descrevendo o banco de dados que a aplicação vai usar:

```yaml
services:
  database:
    image: mysql:9.6
    environment:
      MYSQL_DATABASE: proposals
      MYSQL_ROOT_PASSWORD: root
      MYSQL_USER: app
      MYSQL_PASSWORD: app
    ports:
      - "3307:3306"
    volumes:
      - data:/var/lib/mysql
    healthcheck:
      test: [ "CMD", "mysqladmin", "ping", "-h", "localhost", "-uapp", "-papp" ]
      interval: 5s
      timeout: 5s
      retries: 5

volumes:
  data:
```

- **`services: database:`** — o Docker Compose organiza tudo em **serviços**; aqui só existe um, chamado `database`, que representa um container rodando um banco de dados.
- **`image: mysql:9.6`** — indica qual imagem Docker deve ser baixada e executada: a imagem oficial do MySQL, na versão `9.6`. Uma **imagem Docker** é um "molde" pronto de um sistema já instalado e configurado (neste caso, um servidor MySQL), que pode ser instanciado como um **container** (a "cópia em execução" desse molde) quantas vezes forem necessárias.
- **`environment:`** — variáveis de ambiente passadas para dentro do container no momento em que ele inicia; a imagem oficial do MySQL sabe interpretar essas variáveis específicas para se autoconfigurar:
  - `MYSQL_DATABASE: proposals` — pede para o MySQL já criar, na primeira inicialização, um banco de dados chamado `proposals`.
  - `MYSQL_ROOT_PASSWORD: root` — define a senha do usuário administrador (`root`) do MySQL.
  - `MYSQL_USER: app` / `MYSQL_PASSWORD: app` — cria um segundo usuário, `app`, com senha `app`, com permissão sobre o banco `proposals` — esse é o usuário que a aplicação Java efetivamente vai usar para se conectar, evitando usar o `root` diretamente.
- **`ports: - "3307:3306"`** — mapeia portas entre a máquina local (host) e o container, no formato `"porta_do_host:porta_do_container"`. O MySQL, por padrão, escuta na porta `3306` **dentro** do container; aqui ela é exposta na porta `3307` da sua máquina. Essa escolha (usar `3307` em vez de `3306`) evita conflito caso você já tenha um MySQL rodando localmente na porta padrão.
- **`volumes: - data:/var/lib/mysql`** — um **volume** é um espaço de armazenamento gerenciado pelo Docker que sobrevive mesmo que o container seja destruído e recriado. Aqui, a pasta interna `/var/lib/mysql` (onde o MySQL guarda seus arquivos de dados) é associada a um volume chamado `data`, declarado logo abaixo em `volumes: data:`. Sem isso, todo dado gravado no banco se perderia junto com o container.
- **`healthcheck:`** — define como o Docker deve verificar se o container está **realmente pronto para uso**, e não apenas "iniciado". O comando `mysqladmin ping -h localhost -uapp -papp` tenta se conectar ao MySQL como o usuário `app`; se a resposta for positiva, o container é considerado **healthy** (saudável). Os campos `interval`, `timeout` e `retries` controlam, respectivamente, de quanto em quanto tempo o teste é repetido, quanto tempo esperar por uma resposta, e quantas tentativas malsucedidas são toleradas antes de desistir. Esse `healthcheck` é o que permite à aplicação Spring Boot saber **quando** o banco já está pronto para receber conexões (seção 4.4).

### 4.3. Integrando o Docker Compose ao ciclo de vida da aplicação, e adicionando o Lombok

Três novas dependências são adicionadas ao `build.gradle` nesta etapa: `spring-boot-docker-compose`, `spring-boot-starter-data-jpa` (com o conector do MySQL) e o plugin do Lombok.

```gradle
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.2.5'
    id 'io.spring.dependency-management' version '1.1.4'

    id("io.freefair.lombok") version "9.2.0"
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-security'
    implementation 'org.springframework.boot:spring-boot-starter-web'

    testImplementation platform('org.junit:junit-bom:6.0.0')
    testImplementation 'org.junit.jupiter:junit-jupiter'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'

    developmentOnly 'org.springframework.boot:spring-boot-docker-compose'

    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    runtimeOnly 'com.mysql:mysql-connector-j'
}
```

- **`id("io.freefair.lombok") version "9.2.0"`** — adiciona, na seção `plugins`, o plugin não-oficial (mas amplamente usado) que integra o **Lombok** ao processo de build do Gradle. O Lombok é uma biblioteca que, durante a compilação, **gera código repetitivo automaticamente** a partir de anotações — como *getters*, *setters* e construtores — evitando que o desenvolvedor precise escrevê-los manualmente em cada classe. Ele será usado já na próxima seção, na entidade `User`.
- **`developmentOnly 'org.springframework.boot:spring-boot-docker-compose'`** — adiciona a dependência que dá à aplicação Spring Boot a capacidade de **gerenciar containers Docker Compose automaticamente**: ao subir a aplicação, ela detecta o `compose.yml` na raiz do projeto e sobe os serviços definidos nele (aguardando, inclusive, o `healthcheck` ficar saudável antes de continuar); ao encerrar a aplicação, os containers também são finalizados. A configuração `developmentOnly` (em vez de `implementation`) indica que essa dependência só deve estar presente durante o **desenvolvimento** — ela não é embarcada no artefato final (`.jar`) que seria implantado em produção, onde a infraestrutura de banco já existiria de outra forma.
- **`implementation 'org.springframework.boot:spring-boot-starter-data-jpa'`** — adiciona o **Spring Data JPA**, o módulo do Spring responsável por simplificar drasticamente o acesso a bancos de dados relacionais. Ele reúne, por trás dessa única dependência: o **Hibernate** (a implementação mais usada da especificação **JPA** — *Jakarta Persistence API*, o padrão Java para mapear objetos Java em tabelas de banco de dados, conhecido como **ORM**, *Object-Relational Mapping*); e os **repositórios** do Spring Data, que serão usados na seção 4.6.
- **`runtimeOnly 'com.mysql:mysql-connector-j'`** — adiciona o **driver JDBC** do MySQL: a biblioteca de baixo nível que efetivamente sabe como abrir uma conexão de rede com um servidor MySQL e trocar comandos SQL com ele. `runtimeOnly` indica que essa dependência só é necessária **em tempo de execução** (quando a aplicação realmente se conecta ao banco), e não durante a compilação do código.

A combinação dessas três dependências é o que gera a **autoconfiguração** mencionada na aula: assim que o Spring Boot detecta, via `spring-boot-docker-compose`, que o container do banco subiu (e em qual porta, com qual usuário e senha — informações lidas diretamente do `compose.yml`), ele já configura sozinho a conexão do Spring Data JPA com esse banco, sem que seja necessário escrever nenhuma `url`, `username` ou `password` manualmente em `application.properties`.

### 4.4. Subindo a aplicação: o container ficando *healthy* antes do Spring Boot iniciar

Com as dependências adicionadas, ao rodar a aplicação, os logs mostram uma sequência clara de eventos: o volume e o container são criados, o container entra em estado de espera (`Waiting`) até que o `healthcheck` definido na seção 4.2 passe a responder positivamente (`Healthy`) — e **só então** o Spring Boot efetivamente começa a subir, inicializando o pool de conexões e os repositórios do Spring Data JPA.

```
[proposal-management] DockerCli : Volume proposal-management_data Created
[proposal-management] DockerCli : Container proposal-management-database-1 Created
[proposal-management] DockerCli : Container proposal-management-database-1 Starting
[proposal-management] DockerCli : Container proposal-management-database-1 Waiting
[proposal-management] DockerCli : Container proposal-management-database-1 Healthy
main : Bootstrapping Spring Data JPA repositories
main : Tomcat initialized with port 8080 (http)
```

- **Connection pool (HikariCP)** — mencionado na transcrição como "configuração do Hikari". Um **pool de conexões** é um conjunto de conexões com o banco de dados, já abertas antecipadamente e reutilizadas entre requisições, evitando o custo de abrir e fechar uma conexão de rede a cada consulta. O **HikariCP** é o pool de conexões usado por padrão pelo Spring Boot quando o Spring Data JPA está presente — sua configuração (host, porta, usuário, senha) é montada automaticamente a partir das informações do container Docker Compose, exatamente como descrito na seção anterior.
- A IDE (IntelliJ) também é capaz, nesse momento, de detectar a conexão do banco por **introspecção** — ou seja, ela observa que a aplicação Spring Boot está rodando conectada a um banco vindo de um container Docker, e monta sozinha um painel de navegação sobre esse banco, sem exigir configuração manual de conexão na própria IDE.

### 4.5. Configurações adicionais em `application.properties`

Duas novas linhas são adicionadas ao `application.properties`:

```properties
spring.application.name=proposal-management
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create
```

- **`spring.jpa.show-sql=true`** — instrui o Hibernate a imprimir, no console, cada comando SQL efetivamente executado contra o banco — extremamente útil durante o desenvolvimento, para entender o que o JPA está fazendo "por baixo dos panos" a partir do código Java.
- **`spring.jpa.hibernate.ddl-auto=create`** — instrui o Hibernate a **gerar o esquema do banco automaticamente**, a partir das entidades JPA mapeadas no código (a próxima seção mostra a primeira delas, `User`). O valor `create` especificamente apaga e recria todas as tabelas a cada reinicialização da aplicação — conveniente para estudo e prototipagem, mas **inadequado para produção**, onde uma alteração acidental no mapeamento de uma entidade apagaria dados reais. Em ambientes de produção, o caminho recomendado é o uso de **migrations** (scripts de alteração de esquema versionados e controlados manualmente, com ferramentas como Flyway ou Liquibase), e não a geração automática do schema.
- **DDL** (*Data Definition Language*) — o subconjunto de comandos SQL responsável por definir a **estrutura** do banco de dados (`CREATE TABLE`, `DROP TABLE`, `ALTER TABLE`), em contraste com a **DML** (*Data Manipulation Language*, comandos como `INSERT`, `UPDATE`, `SELECT`, que manipulam os **dados** dentro dessa estrutura).

### 4.6. Organizando a camada de persistência: os pacotes `persistence.entity` e `persistence.repository`

Antes de escrever qualquer classe, é criada uma nova estrutura de pacotes dentro de `auth.infrastructure`, isolando a camada de persistência do restante da aplicação:

```
auth/infrastructure/
├── http/
├── security/
└── persistence/
    ├── entity/
    └── repository/
```

Essa organização segue o mesmo princípio já visto nos vídeos anteriores (separar `http` de `security`): cada tipo de responsabilidade — expor endpoints, configurar segurança, acessar dados — ganha seu próprio pacote dentro de `infrastructure`, a camada que lida com detalhes técnicos e de framework, mantendo `domain` (regras de negócio puras, como o `UserRole`) desacoplado desses detalhes.

### 4.7. A entidade `User`: modelando a tabela e reaproveitando `UserDetails`

Esta é a peça central do vídeo. A classe `User` é criada dentro de `persistence.entity`, e cumpre dois papéis ao mesmo tempo: representar uma **tabela do banco de dados** e servir como o **usuário autenticável** do Spring Security.

```java
package dio.proposalmanagement.auth.infrastructure.persistence.entity;

import dio.proposalmanagement.auth.domain.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
```

- **`@Entity`** — anotação da especificação JPA (não é do Spring; vem de `jakarta.persistence`) que marca uma classe como **mapeada para uma tabela do banco de dados**. Cada instância dessa classe corresponde a uma linha da tabela; cada atributo anotado corresponde a uma coluna. Sem outras anotações, o nome da tabela é derivado do nome da classe (neste caso, `user`, conforme confirmado no banco na seção 4.11).
- **`@Data` (Lombok)** — gera automaticamente, em tempo de compilação, os métodos `getX()`/`setX()` para **todos** os atributos da classe, além de `equals()`, `hashCode()` e `toString()`. É essa anotação que faz `getPassword()`, `getUsername()`, `getId()` e `getRole()` existirem sem que uma única linha desses métodos precise ser escrita manualmente.
- **`@NoArgsConstructor` (Lombok)** — gera um construtor **vazio** (sem argumentos). Ele é exigido pelo Hibernate, que precisa conseguir instanciar a entidade "em branco" antes de preencher seus campos com os dados vindos do banco — um mecanismo que depende de reflexão, e não do construtor "normal" da classe.
- **`@AllArgsConstructor` (Lombok)** — gera um construtor que recebe **todos** os atributos como argumentos, na ordem em que foram declarados. Facilita a criação de um `User` já totalmente preenchido em uma única linha, embora, no `CommandLineRunner` da seção 4.9, o construtor vazio combinado com os *setters* tenha sido a opção escolhida.
- **`implements UserDetails`** — o ponto-chave de toda a estratégia deste vídeo. Ao fazer a própria entidade `User` implementar a interface `UserDetails` (já usada desde o Vídeo 01, quando os usuários viviam em memória), toda a infraestrutura de autenticação do Spring Security — o `AuthenticationManager`, o `DaoAuthenticationProvider`, o filtro REST do Vídeo 03 — continua funcionando **sem nenhuma alteração**, porque, do ponto de vista dela, ainda está lidando com um `UserDetails` qualquer. Só a **origem** desse objeto muda: antes vinha de uma lista em memória, agora vem de uma consulta ao banco.
- **`@Id`** — anotação JPA que marca o atributo `id` como a **chave primária** da tabela — o valor que identifica unicamente cada linha.
- **`@GeneratedValue(strategy = GenerationType.UUID)`** — instrui o JPA a **gerar automaticamente** o valor da chave primária, usando a estratégia `UUID`: em vez de um número sequencial (`1`, `2`, `3`...), cada novo `User` recebe um identificador aleatório de 128 bits, praticamente impossível de colidir com outro gerado em qualquer lugar do mundo. **`UUID`** (*Universally Unique Identifier*) é uma classe do próprio Java (`java.util.UUID`) que representa esse tipo de identificador — vantajoso em sistemas distribuídos, onde múltiplos serviços podem precisar gerar IDs de forma independente, sem coordenação central (diferente de um contador sequencial de banco de dados, que exige uma única fonte de verdade).
- **`@Column(unique = true, nullable = false)`** (no `username`) — anotação JPA que customiza como o atributo vira uma coluna: `unique = true` cria uma restrição de unicidade no banco (dois usuários não podem ter o mesmo `username`), e `nullable = false` impede que a coluna fique vazia (`NULL`).
- **`@Column(nullable = false)`** (no `password`) — mesma ideia, mas exigindo apenas que o campo não fique vazio, sem restrição de unicidade (afinal, nada impede que dois usuários, coincidentemente, tenham a mesma senha).
- **`@Enumerated(EnumType.STRING)`** (no `role`) — instrui o JPA sobre **como persistir um valor de `enum`** (o `UserRole` já criado nos vídeos anteriores) em uma coluna do banco, que só entende tipos primitivos como texto ou número. Com `EnumType.STRING`, o valor é salvo como o **texto** do nome da constante (por exemplo, a coluna guarda literalmente `"ROLE_INFLUENCER"`), o que torna os dados legíveis diretamente no banco e resistente a reordenações futuras do `enum`. A alternativa, `EnumType.ORDINAL` (o padrão, se a anotação fosse omitida), salvaria apenas a **posição numérica** da constante (`0`, `1`...) — mais compacta, porém frágil: se a ordem das constantes no `enum` mudar no código, o significado dos números já salvos no banco muda junto, silenciosamente.
- **`getAuthorities()`** — o único método de `UserDetails` que realmente precisa de uma implementação própria (os demais, como visto abaixo, apenas retornam `true`). Ele devolve as **autoridades** (*authorities*) do usuário — a lista de permissões/papéis usada pelo Spring Security nas checagens de `hasRole(...)` e `@PreAuthorize`. Aqui, a lista é montada com um único elemento: um novo `SimpleGrantedAuthority` (a implementação padrão de `GrantedAuthority` já vista nos vídeos anteriores) construído a partir de `role.name()` — o método, gerado automaticamente por todo `enum` do Java, que devolve o **nome textual** da constante (por exemplo, `"ROLE_INFLUENCER"`).
- **`isAccountNonExpired()`, `isAccountNonLocked()`, `isCredentialsNonExpired()`, `isEnabled()`** — os quatro métodos restantes exigidos pela interface `UserDetails`, todos retornando `true` de forma fixa. Eles existem na interface para suportar cenários mais avançados de gestão de conta (contas temporárias que expiram, contas bloqueadas após tentativas malsucedidas de login, senhas que expiram periodicamente, contas desativadas) — nenhum desses cenários está sendo tratado neste momento do curso, então todos simplesmente "liberam" a conta.

### 4.8. O repositório: `UserRepository` e o método `findByUsername`

Para consultar os usuários salvos no banco, é criada uma interface `UserRepository`, dentro do novo pacote `persistence.repository`:

```java
package dio.proposalmanagement.auth.infrastructure.persistence.repository;

import dio.proposalmanagement.auth.infrastructure.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    Optional<User> findByUsername(String username);
}
```

- **`@Repository`** — anotação do Spring que marca um componente como pertencente à **camada de persistência**. Funciona de forma semelhante a `@Component` (já vista no Vídeo 03): torna a interface elegível para ser descoberta e gerenciada pelo Spring como um *bean*, disponível para injeção de dependência em outras classes. Também sinaliza, por convenção, que esse componente pode lançar exceções específicas de acesso a dados, que o Spring sabe traduzir para um formato comum.
- **`interface UserRepository extends CrudRepository<User, UUID>`** — o ponto mais "mágico" do Spring Data JPA: basta **declarar uma interface** que estende `CrudRepository`, informando entre os `<>` (os **generics**, já mencionados nos tutoriais anteriores) qual entidade ela gerencia (`User`) e qual o tipo da chave primária dessa entidade (`UUID`, coerente com o tipo do atributo `id` visto na seção 4.7). O Spring Data JPA, em tempo de execução, gera **automaticamente** uma implementação completa dessa interface, sem que uma única linha de código de acesso ao banco precise ser escrita.
- **`CrudRepository<T, ID>`** — interface do Spring Data que já vem pronta com as operações básicas de **CRUD** (*Create, Read, Update, Delete* — os quatro tipos fundamentais de operação sobre dados persistidos): `save(...)`, `findById(...)`, `findAll()`, `deleteById(...)`, entre outras, todas implementadas automaticamente a partir dos tipos informados nos generics.
- **`Optional<User> findByUsername(String username);`** — aqui está o verdadeiro ponto de destaque da aula: essa assinatura de método **não existe** em `CrudRepository` — ela foi simplesmente inventada e declarada nesta interface, sem nenhum corpo de implementação. O Spring Data JPA é capaz de **interpretar o próprio nome do método** (`findBy` + `Username`) e deduzir, sozinho, a consulta SQL equivalente (`SELECT * FROM user WHERE username = ?`), desde que `Username` corresponda a um atributo existente na entidade `User`. Essa técnica é chamada de ***query method*** (ou "método de consulta derivado"), e é uma das características mais conhecidas do Spring Data.
- **`Optional<User>`** — o tipo de retorno escolhido para esse método é `Optional`, uma classe do Java (`java.util.Optional`) que representa **um valor que pode ou não estar presente**, evitando o uso de `null` para sinalizar "não encontrado". É um mecanismo mais seguro que retornar `User` diretamente (que poderia ser `null` silenciosamente): o `Optional` **obriga** quem chama o método a lidar explicitamente com a ausência de valor, como visto na próxima seção, através de `orElseThrow(...)`.

### 4.9. O `JpaUserDetailsService`: implementando `UserDetailsService` a partir do banco

Com o repositório pronto, é criada a nova implementação de `UserDetailsService`, dentro do pacote `security` (o mesmo pacote onde já vive o `SecurityConfig` desde o Vídeo 03):

```java
package dio.proposalmanagement.auth.infrastructure.security;

import dio.proposalmanagement.auth.infrastructure.persistence.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
```

- **`@Service`** — mais uma anotação "especializada" de `@Component` (assim como `@Repository`), usada por convenção para marcar classes que concentram **regras de aplicação/serviço** — a camada que fica entre os controllers/filtros (que lidam com requisições) e os repositórios (que lidam com dados brutos). Do ponto de vista técnico, `@Service` se comporta de forma idêntica a `@Component`; a diferença é puramente semântica, ajudando quem lê o código a entender rapidamente o papel de cada classe.
- **`implements UserDetailsService`** — a mesma interface que, nos Vídeos 01–03, era satisfeita pelo `InMemoryUserDetailsManager` (uma classe pronta do Spring Security). Agora, a aplicação fornece sua **própria** implementação, mas o contrato continua sendo exatamente o mesmo — é por isso que nenhum outro ponto do sistema (o `AuthenticationManager`, o `DaoAuthenticationProvider`) precisa saber ou se importar com essa troca.
- **`private final UserRepository userRepository;` + construtor** — o mesmo padrão de **injeção de dependência via construtor** já usado no `RestUsernamePasswordAuthenticationFilter` do Vídeo 03: a classe declara, no construtor, do que ela precisa para funcionar (`UserRepository`), e o Spring — que já conhece esse `UserRepository` por causa da anotação `@Repository` vista na seção 4.8 — injeta automaticamente uma instância dele aqui, sem exigir nenhuma configuração manual adicional. O atributo é `final` porque, uma vez injetado no construtor, essa dependência não deveria ser trocada depois.
- **`loadUserByUsername(String username)`** — o único método exigido pela interface `UserDetailsService`, chamado internamente pelo Spring Security sempre que precisa validar um usuário durante a autenticação (por exemplo, disparado pelo filtro REST do Vídeo 03, através do `AuthenticationManager`).
- **`userRepository.findByUsername(username)`** — chama o *query method* criado na seção 4.8, que devolve um `Optional<User>`.
- **`.orElseThrow(() -> new UsernameNotFoundException(username))`** — método de `Optional` que devolve o valor **se ele estiver presente**, ou executa a função passada como argumento **se estiver vazio**. Aqui, a função lançar uma nova `UsernameNotFoundException` — uma exceção específica do Spring Security que sinaliza, de forma padronizada, que nenhum usuário com aquele `username` foi encontrado. É exatamente o comportamento que a interface `UserDetailsService` espera de qualquer implementação: devolver um `UserDetails` válido, ou lançar essa exceção.
- **Removendo o bean antigo** — como a aplicação agora possui, entre seus componentes gerenciados, uma classe que já implementa `UserDetailsService` (o próprio `JpaUserDetailsService`, graças ao `@Service`), o bean `UserDetailsService` em memória, criado manualmente no `SecurityConfig` desde o Vídeo 01/02 (com o `InMemoryUserDetailsManager`), deixa de ser necessário e é removido. Sempre que algum outro componente da aplicação precisar de um `UserDetailsService` — como o `DaoAuthenticationProvider`, usado internamente pelo `AuthenticationManager` — o Spring, por injeção de dependência, vai automaticamente encontrar e usar o `JpaUserDetailsService`, já que ele é a única implementação dessa interface presente no contexto da aplicação.

### 4.10. Populando o banco: o `CommandLineRunner`

Para não precisar cadastrar usuários manualmente a cada teste, o `SecurityConfig` ganha um novo *bean*, responsável por inserir alguns usuários de exemplo assim que a aplicação sobe:

```java
@Bean
CommandLineRunner initDatabase(UserRepository repository, PasswordEncoder passwordEncoder) {
    return args -> {
        if (repository.count() == 0) {
            User fitnessInfluencer = new User();
            fitnessInfluencer.setUsername("fitness_vibe");
            fitnessInfluencer.setPassword(passwordEncoder.encode("password"));
            fitnessInfluencer.setRole(UserRole.ROLE_INFLUENCER);

            User techInfluencer = new User();
            techInfluencer.setUsername("tech_guru");
            techInfluencer.setPassword(passwordEncoder.encode("password"));
            techInfluencer.setRole(UserRole.ROLE_INFLUENCER);

            User brand = new User();
            brand.setUsername("logistics");
            brand.setPassword(passwordEncoder.encode("password"));
            brand.setRole(UserRole.ROLE_BRAND);

            repository.saveAll(List.<User>of(fitnessInfluencer, techInfluencer, brand));
        }
    };
}
```

- **`CommandLineRunner`** — uma interface **funcional** do Spring Boot (ou seja, com um único método abstrato, o que permite implementá-la com uma expressão lambda) que representa um pedaço de código a ser executado **uma única vez**, logo depois que o contexto da aplicação termina de ser montado, mas antes dela começar a aceitar requisições. É o mecanismo padrão do Spring Boot para rodar tarefas de inicialização, como popular dados de exemplo.
- **`@Bean CommandLineRunner initDatabase(UserRepository repository, PasswordEncoder passwordEncoder)`** — como já visto desde o Vídeo 01 com outros beans, os **parâmetros do método anotado com `@Bean`** são a forma como esse bean **declara suas próprias dependências**: o Spring, ao montar o bean `initDatabase`, automaticamente localiza e injeta o `UserRepository` (visto na seção 4.8) e o `PasswordEncoder` (o mesmo `BCryptPasswordEncoder` já usado desde o início do curso).
- **`return args -> { ... };`** — o corpo do método `@Bean` não executa a lógica diretamente; ele **devolve** uma expressão lambda, que é a implementação do único método da interface `CommandLineRunner` (`run(String... args)`). É essa lambda que o Spring efetivamente executa no momento da inicialização, e não o corpo do método `initDatabase` em si (que só roda uma vez, na montagem do bean, para *construir* essa lambda).
- **`if (repository.count() == 0)`** — `count()` é outro método já pronto, herdado de `CrudRepository`, que devolve quantos registros existem na tabela. Essa checagem evita que os mesmos três usuários sejam recriados (e duplicados, já que `username` é `unique`) toda vez que a aplicação for reiniciada — os dados só são inseridos se o banco estiver vazio.
- **`new User(); ... .setUsername(...); .setPassword(...); .setRole(...);`** — aqui é usado o construtor **vazio** (gerado por `@NoArgsConstructor`) combinado com os *setters* (gerados por `@Data`) para montar cada usuário passo a passo, em vez do construtor com todos os argumentos (`@AllArgsConstructor`) — uma escolha de estilo que deixa mais claro, à primeira leitura, qual valor está sendo atribuído a qual campo.
- **`passwordEncoder.encode("password")`** — a senha nunca é salva em texto puro no banco; ela passa pelo mesmo `BCryptPasswordEncoder` já conhecido dos vídeos anteriores antes de ser gravada, gerando um hash como `$2a$10$...` (confirmado visualmente no banco na seção 4.11).
- **`UserRole.ROLE_INFLUENCER` / `UserRole.ROLE_BRAND`** — os valores do `enum` `UserRole` (seção 4.7), usados para definir o papel de cada usuário criado.
- **`repository.saveAll(List.<User>of(fitnessInfluencer, techInfluencer, brand));`** — `saveAll(...)` é outro método herdado de `CrudRepository`, que recebe uma coleção de entidades e as persiste todas de uma vez. `List.<User>of(...)` é a forma de criar uma lista **imutável** (que não pode ter elementos adicionados ou removidos depois de criada) contendo os três usuários — o `<User>` explícito antes de `of(...)` ajuda o compilador a inferir corretamente o tipo genérico da lista nesse contexto específico.

### 4.11. Testando a autenticação vinda do banco

Com a aplicação reiniciada, três usuários passam a existir no banco: `fitness_vibe`, `tech_guru` (ambos `ROLE_INFLUENCER`) e `logistics` (`ROLE_BRAND`), todos com a senha `password` — já criptografada com BCrypt, visível na tabela como um hash iniciado por `$2a$10$...`.

Os testes via HTTP Client confirmam o comportamento esperado:

- Uma tentativa de login com o usuário `influencer` (válido na implementação **antiga**, em memória) resulta em `403`, porque esse usuário nunca foi inserido no banco — apenas `fitness_vibe`, `tech_guru` e `logistics` existem agora.
- O login com `tech_guru` / `password` funciona (`200 OK`), devolvendo um cookie de sessão `JSESSIONID`; uma requisição subsequente para `http://localhost:8080` usando esse cookie devolve `Hello World tech_guru`.
- O mesmo se repete com sucesso para `logistics` (a *brand*).
- Ao tentar acessar a rota protegida `/brand` autenticado como `logistics`, porém, a resposta é `403` — um problema investigado na seção 4.13.

### 4.12. Aproveitando o `User` próprio no Controller: acessando o `id`

Uma pequena melhoria é feita no `Controller`: como agora existe uma entidade `User` própria (em vez do `UserDetails` genérico do Spring Security), o parâmetro anotado com `@AuthenticationPrincipal` pode ser tipado diretamente como `User`, dando acesso a **todos** os seus atributos — inclusive o `id`, que não existia na interface `UserDetails` padrão:

```java
@GetMapping
public String hello(@AuthenticationPrincipal User user) {
    return "Hello World " + user.getId();
}
```

- **`@AuthenticationPrincipal User user`** — nos vídeos anteriores, esse parâmetro era declarado como `UserDetails user` (a interface genérica). Como o objeto autenticado, em tempo de execução, **é** de fato uma instância de `User` (a própria entidade JPA, que implementa `UserDetails`), o Spring Security consegue injetá-lo corretamente mesmo com o tipo mais específico declarado no parâmetro. Isso só é possível porque a entidade `User` optou por implementar `UserDetails` diretamente (seção 4.7) — se a aplicação usasse uma classe separada só para representar o usuário autenticado, esse tipo de acesso direto ao `id` exigiria um passo a mais (uma conversão ou consulta adicional).
- **`user.getId()`** — o método `getId()`, gerado automaticamente pelo Lombok (`@Data`) na entidade `User`, agora fica disponível diretamente no controller — um ganho prático destacado na aula: antes, só `getUsername()` e `getPassword()` estavam disponíveis via `UserDetails`.

### 4.13. O bug do prefixo `ROLE_`, e por que ele é exigido

Ao testar o endpoint `/brand` com o usuário `logistics` (que tem a *role* `BRAND`), a requisição falha com `403`, mesmo o usuário estando corretamente autenticado. A causa é uma convenção interna do Spring Security: a expressão `hasRole('BRAND')`, usada em `@PreAuthorize("hasRole('BRAND')")` desde o Vídeo 02, **não** procura literalmente por uma *authority* chamada `"BRAND"` — ela automaticamente **adiciona o prefixo `ROLE_`** e procura por `"ROLE_BRAND"` entre as *authorities* do usuário.

Até este ponto, o `enum` `UserRole` guardava apenas `INFLUENCER` e `BRAND` (sem o prefixo), então a *authority* gerada por `getAuthorities()` (seção 4.7) era `"INFLUENCER"` ou `"BRAND"` — nunca teria como bater com o que `hasRole(...)` procura. A correção é simplesmente renomear as constantes do `enum`:

```java
package dio.proposalmanagement.auth.domain;

public enum UserRole {
    ROLE_INFLUENCER,
    ROLE_BRAND
}
```

Com essa mudança, a aplicação é reiniciada (recriando o banco, já que `ddl-auto=create`, e reinserindo os três usuários via `CommandLineRunner`, agora já com as *roles* corrigidas), e uma nova consulta ao banco confirma que a coluna `role` passa a guardar `ROLE_INFLUENCER` e `ROLE_BRAND`. A partir daí, um novo login como `logistics` seguido de uma requisição a `/brand` funciona corretamente (`200 OK`), já que a *authority* `"ROLE_BRAND"` agora existe de fato e corresponde ao que `hasRole('BRAND')` espera.

> Vale registrar que o texto usado dentro de `@PreAuthorize("hasRole('BRAND')")` **continua** sem o prefixo `ROLE_` — o prefixo é responsabilidade exclusiva de quem **gera** a *authority* (aqui, o `enum` `UserRole` e o método `getAuthorities()`); `hasRole(...)` sempre adiciona `ROLE_` sozinho, automaticamente, ao comparar. Escrever `hasRole('ROLE_BRAND')` seria, na verdade, um erro comum: o Spring Security acabaria procurando por `"ROLE_ROLE_BRAND"`, que nunca existiria.

---

## Pontos de atenção: divergências entre a aula/README e o seu projeto

Comparando o código real do seu `.zip` com a narrativa do README (que, ao contrário do Vídeo 03, já vem preenchida para este vídeo) e com a transcrição, alguns pontos merecem registro:

1. **Erros pedagógicos mostrados no README, já corrigidos no `.zip` final.** O README narra, propositalmente, dois erros cometidos "ao vivo" durante a aula, que **não aparecem** no estado final do código (o `.zip` já reflete a versão corrigida):
   - O `UserRepository` foi mostrado, em um primeiro momento, como uma `class` estendendo `CrudRepository` (o que não compila — `CrudRepository` é uma interface, e não pode ser estendida por uma classe concreta dessa forma) e sem o método `findByUsername`. A versão final, conferida no `.zip`, já é a `interface` correta mostrada na seção 4.8 deste tutorial.
   - O `UserRole` foi criado, inicialmente, com as constantes `INFLUENCER`/`BRAND` (sem prefixo), gerando o bug de autorização discutido na seção 4.13 — e só depois corrigido para `ROLE_INFLUENCER`/`ROLE_BRAND`. O `.zip` final já contém apenas a versão corrigida.
2. **Versões de plugin divergentes entre o README e o `build.gradle` real.** O README, em um dos trechos de código exibidos, mostra o bloco `plugins` com `id 'org.springframework.boot' version '4.0.5'` e `id 'io.spring.dependency-management' version '1.1.7'`. O `build.gradle` real do projeto, no entanto, mantém as mesmas versões já usadas desde o Vídeo 02: `3.2.5` e `1.1.4`, respectivamente (apenas o plugin do Lombok, `io.freefair.lombok:9.2.0`, é de fato novo). Esse trecho específico do README parece ser um recorte de outro template/anotação pessoal, e não corresponde ao projeto real — o checkpoint na próxima seção usa o `build.gradle` efetivamente presente no `.zip`.
3. **Trecho do README com dependências genéricas.** Um dos blocos de código do README mostra `implementation 'org.springframework.boot:spring-boot-starter'` (o *starter* genérico) em vez de `spring-boot-starter-security` e `spring-boot-starter-web`, que são as dependências que de fato aparecem no `build.gradle` do projeto (herdadas desde o Vídeo 02). Mais um indício de que esse trecho específico do README foi copiado de um template genérico, e não reflete literalmente o arquivo do projeto.
4. **`Controller.java` no README omite `@EnableMethodSecurity` e usa um nome de método ligeiramente diferente.** O trecho de código do `Controller` mostrado no README não inclui a anotação `@EnableMethodSecurity` (presente desde o Vídeo 02, e mantida no `.zip` real) e nomeia o segundo endpoint como `influencerEndpoint()`. O código real, conferido no `.zip`, mantém `@EnableMethodSecurity` na classe e usa o nome `influenceEndpoint()` (sem o "r" antes de "Endpoint") — uma pequena inconsistência de nomenclatura dentro do próprio projeto, sem nenhum efeito funcional (o nome do método não influencia o comportamento do endpoint, apenas sua legibilidade). O checkpoint a seguir usa o texto exato encontrado no `.zip`.

---

## Glossário de conceitos novos (Vídeo 04)

Apenas os termos que **ainda não** apareciam nos glossários anteriores. Para os termos já vistos (`@Component`, `@Bean`, `PasswordEncoder`, `UserDetailsService`, `hasRole(...)`, `record`, etc.), consulte `001-...md` e `002-...md`.

### Estrutura da linguagem Java

| Termo | Significado |
|---|---|
| `UUID` (`java.util.UUID`) | Classe do Java que representa um identificador de 128 bits, gerado de forma praticamente impossível de colidir com outro, mesmo sem coordenação central — usado aqui como chave primária da entidade `User`, alternativa a um contador numérico sequencial. |
| `Optional<T>` | Classe do Java que representa um valor que pode ou não estar presente, evitando o uso de `null` para indicar "ausência de valor". Obriga quem consome o valor a tratar explicitamente o caso de ausência, por exemplo com `.orElseThrow(...)`. |
| interface funcional | Uma interface Java que declara **um único** método abstrato, o que permite implementá-la de forma compacta com uma expressão lambda — como acontece com `CommandLineRunner` neste vídeo. |
| generics (`<T>`) | Mecanismo do Java que permite parametrizar uma classe ou interface com um tipo, decidido apenas no momento do uso (ex.: `CrudRepository<User, UUID>`), mantendo a segurança de tipos em tempo de compilação sem duplicar código para cada tipo concreto. |

### Anotações e conceitos do Spring / Spring Boot / JPA

| Termo | Significado |
|---|---|
| `@Repository` | Anotação do Spring, especializada de `@Component`, que marca um componente da camada de persistência, tornando-o elegível para injeção de dependência e sinalizando que ele pode lançar exceções de acesso a dados. |
| `@Service` | Anotação do Spring, especializada de `@Component`, usada por convenção para marcar classes de regra de aplicação/serviço — comporta-se, tecnicamente, como `@Component`. |
| `@Entity` | Anotação da especificação JPA que marca uma classe como mapeada para uma tabela do banco de dados; cada instância corresponde a uma linha, cada atributo mapeado a uma coluna. |
| JPA (*Jakarta Persistence API*) | Especificação padrão do Java para mapear objetos Java em tabelas de bancos de dados relacionais (ORM). O Hibernate é a implementação dessa especificação usada pelo Spring Data JPA. |
| ORM (*Object-Relational Mapping*) | Técnica/padrão de projeto que traduz automaticamente entre objetos de um programa orientado a objetos e linhas de tabelas de um banco relacional, evitando escrever SQL manualmente para operações básicas. |
| Hibernate | Implementação mais usada da especificação JPA no ecossistema Java; é o motor por trás do Spring Data JPA responsável por, de fato, gerar e executar o SQL. |
| `@Id` | Anotação JPA que marca um atributo como a chave primária da entidade. |
| `@GeneratedValue(strategy = ...)` | Anotação JPA que instrui o banco/JPA a gerar automaticamente o valor da chave primária, segundo uma estratégia (aqui, `GenerationType.UUID`). |
| `@Column(unique=..., nullable=...)` | Anotação JPA que customiza como um atributo é mapeado para uma coluna — por exemplo, exigindo unicidade ou proibindo valores nulos. |
| `@Enumerated(EnumType.STRING)` | Anotação JPA que define como um `enum` é persistido: como texto (`STRING`, o nome da constante) ou como número (`ORDINAL`, a posição da constante) — `STRING` é mais legível e resistente a reordenações do `enum`. |
| `CrudRepository<T, ID>` | Interface do Spring Data que já disponibiliza operações básicas de CRUD (`save`, `findById`, `findAll`, `count`, `deleteById`, etc.) para uma entidade `T` com chave primária do tipo `ID`, sem exigir implementação manual. |
| *query method* (método de consulta derivado) | Técnica do Spring Data em que a **assinatura** de um método declarado em uma interface de repositório (ex.: `findByUsername(String username)`) é interpretada pelo framework para gerar automaticamente a consulta SQL correspondente, sem exigir implementação escrita à mão. |
| `CommandLineRunner` | Interface funcional do Spring Boot que representa uma tarefa executada uma única vez, logo após o contexto da aplicação ser montado e antes dela aceitar requisições — usada aqui para popular o banco com usuários de exemplo. |
| DDL (*Data Definition Language*) | Subconjunto de comandos SQL responsável por definir a estrutura do banco (`CREATE`, `DROP`, `ALTER`), em oposição à DML (`INSERT`, `UPDATE`, `SELECT`), que manipula os dados dentro dessa estrutura. |
| `spring.jpa.hibernate.ddl-auto` | Propriedade do Spring Boot que controla como o Hibernate gerencia o esquema do banco a partir das entidades — `create` recria o schema do zero a cada início da aplicação (apropriado para estudo, não para produção). |
| *migrations* | Estratégia recomendada para produção, alternativa ao `ddl-auto=create`: scripts de alteração de esquema versionados e aplicados de forma controlada (ferramentas como Flyway ou Liquibase), preservando os dados já existentes. |

### Docker e Docker Compose

| Termo | Significado |
|---|---|
| Imagem Docker | Um "molde" pronto de um ambiente já instalado e configurado (ex.: um servidor MySQL), a partir do qual containers podem ser criados. |
| Container | Uma instância em execução de uma imagem Docker — isolada, mas leve, comparada a uma máquina virtual completa. |
| Docker Compose | Ferramenta que descreve, em um único arquivo (`compose.yml`), um ou mais serviços/containers e como eles devem ser configurados e conectados entre si. |
| `spring-boot-docker-compose` | Biblioteca que integra o Spring Boot ao Docker Compose: ao subir a aplicação, os containers definidos em `compose.yml` sobem junto (e a conexão com eles é autoconfigurada); ao encerrar a aplicação, os containers também são finalizados. |
| Volume (Docker) | Espaço de armazenamento gerenciado pelo Docker que sobrevive à destruição/recriação de um container — usado para não perder os dados do banco entre reinicializações. |
| `healthcheck` | Configuração que define como o Docker deve testar se um container está de fato pronto para uso (e não apenas iniciado), através de um comando executado periodicamente dentro dele. |
| Connection pool / HikariCP | Um conjunto de conexões com o banco de dados, abertas antecipadamente e reutilizadas entre requisições, evitando o custo de abrir/fechar conexões a cada operação. HikariCP é o pool usado por padrão pelo Spring Boot. |

### Outros

| Termo | Significado |
|---|---|
| Lombok | Biblioteca Java que gera, em tempo de compilação, código repetitivo (getters, setters, construtores, `equals`, `hashCode`, `toString`) a partir de anotações como `@Data`, `@NoArgsConstructor` e `@AllArgsConstructor`, reduzindo a verbosidade das classes. |

---

## Estado atual do projeto (checkpoint do Vídeo 04)

Este é o retrato fiel do código-fonte na etapa atual, conferido diretamente nos arquivos do `.zip` `proposal-managemnet_ate_o_video04.zip` — e não apenas na narrativa do README (cujas divergências pontuais estão listadas na seção "Pontos de atenção" acima).

### Estrutura de pastas

```
proposal-managemnet/
├── build.gradle
├── settings.gradle
├── compose.yml                                          ← novo arquivo
└── src/
    ├── main/
    │   ├── java/dio/proposalmanagement/
    │   │   ├── ProposalManagementApplication.java        (inalterado)
    │   │   └── auth/
    │   │       ├── application/                          (ainda vazio nesta etapa)
    │   │       ├── domain/
    │   │       │   └── UserRole.java                     ← alterado (ROLE_ prefix)
    │   │       └── infrastructure/
    │   │           ├── http/
    │   │           │   └── Controller.java                ← alterado (User em vez de UserDetails)
    │   │           ├── security/
    │   │           │   ├── SecurityConfig.java             ← alterado (CommandLineRunner, sem bean em memória)
    │   │           │   ├── RestUsernamePasswordAuthenticationFilter.java  (inalterado desde o Vídeo 03)
    │   │           │   └── JpaUserDetailsService.java       ← novo arquivo
    │   │           └── persistence/                        ← novo pacote
    │   │               ├── entity/
    │   │               │   └── User.java                   ← novo arquivo
    │   │               └── repository/
    │   │                   └── UserRepository.java          ← novo arquivo
    │   └── resources/
    │       └── application.properties                     ← novo arquivo
    └── test/                                                (ainda vazio nesta etapa)
```

### `compose.yml` (novo arquivo)

```yaml
services:
  database:
    image: mysql:9.6
    environment:
      MYSQL_DATABASE: proposals
      MYSQL_ROOT_PASSWORD: root
      MYSQL_USER: app
      MYSQL_PASSWORD: app
    ports:
      - "3307:3306"
    volumes:
      - data:/var/lib/mysql
    healthcheck:
      test: [ "CMD", "mysqladmin", "ping", "-h", "localhost", "-uapp", "-papp" ]
      interval: 5s
      timeout: 5s
      retries: 5

volumes:
  data:
```

### `build.gradle` (alterado)

```groovy
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.2.5' // Ajuste a versão conforme necessário para o seu projeto/curso
    id 'io.spring.dependency-management' version '1.1.4'

    id("io.freefair.lombok") version "9.2.0"

}

group = 'dio'
version = '1.0-SNAPSHOT'

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17) // Ou 21 / 25, dependendo do seu JDK configurado
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-security'
    implementation 'org.springframework.boot:spring-boot-starter-web'

    testImplementation platform('org.junit:junit-bom:6.0.0')
    testImplementation 'org.junit.jupiter:junit-jupiter'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'

    developmentOnly 'org.springframework.boot:spring-boot-docker-compose'

    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    runtimeOnly 'com.mysql:mysql-connector-j'

}

test {
    useJUnitPlatform()
}
```

### `src/main/resources/application.properties` (novo arquivo)

```properties
spring.application.name=proposal-management
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create
```

### `auth/domain/UserRole.java` (alterado)

```java
package dio.proposalmanagement.auth.domain;

public enum UserRole {
    ROLE_INFLUENCER,
    ROLE_BRAND
}
```

### `auth/infrastructure/persistence/entity/User.java` (novo arquivo)

```java
package dio.proposalmanagement.auth.infrastructure.persistence.entity;

import dio.proposalmanagement.auth.domain.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
```

### `auth/infrastructure/persistence/repository/UserRepository.java` (novo arquivo)

```java
package dio.proposalmanagement.auth.infrastructure.persistence.repository;

import dio.proposalmanagement.auth.infrastructure.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    Optional<User> findByUsername(String username);
}
```

### `auth/infrastructure/security/JpaUserDetailsService.java` (novo arquivo)

```java
package dio.proposalmanagement.auth.infrastructure.security;

import dio.proposalmanagement.auth.infrastructure.persistence.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
```

### `auth/infrastructure/security/SecurityConfig.java` (alterado)

```java
package dio.proposalmanagement.auth.infrastructure.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import dio.proposalmanagement.auth.domain.UserRole;
import dio.proposalmanagement.auth.infrastructure.persistence.entity.User; // Import explícito da entidade correta
import dio.proposalmanagement.auth.infrastructure.persistence.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    CommandLineRunner initDatabase(UserRepository repository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (repository.count() == 0) {
                User fitnessInfluencer = new User();
                fitnessInfluencer.setUsername("fitness_vibe");
                fitnessInfluencer.setPassword(passwordEncoder.encode("password"));
                fitnessInfluencer.setRole(UserRole.ROLE_INFLUENCER);

                User techInfluencer = new User();
                techInfluencer.setUsername("tech_guru");
                techInfluencer.setPassword(passwordEncoder.encode("password"));
                techInfluencer.setRole(UserRole.ROLE_INFLUENCER);

                User brand = new User();
                brand.setUsername("logistics");
                brand.setPassword(passwordEncoder.encode("password"));
                brand.setRole(UserRole.ROLE_BRAND);

                repository.saveAll(List.<User>of(fitnessInfluencer, techInfluencer, brand));
            }
        };
    }

    @Bean
    SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            AuthenticationConfiguration authenticationConfiguration,
            ObjectMapper objectMapper) throws Exception {

        RestUsernamePasswordAuthenticationFilter customAuthFilter =
                new RestUsernamePasswordAuthenticationFilter(authenticationConfiguration, objectMapper);

        http
                .csrf(AbstractHttpConfigurer::disable)
                .securityContext(context -> context.requireExplicitSave(false))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/login").permitAll()
                        .anyRequest().authenticated())
                .addFilterAt(customAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

> Note que o bean `UserDetailsService` em memória (com `InMemoryUserDetailsManager`), presente até o Vídeo 03, **não existe mais** neste arquivo — foi integralmente substituído pelo `JpaUserDetailsService`, descoberto automaticamente pelo Spring por estar anotado com `@Service`.

### `auth/infrastructure/security/RestUsernamePasswordAuthenticationFilter.java` (inalterado desde o Vídeo 03)

Confirmado no `.zip`: nenhuma linha mudou neste arquivo desde o checkpoint do Vídeo 03.

### `auth/infrastructure/http/Controller.java` (alterado)

```java
package dio.proposalmanagement.auth.infrastructure.http;

import dio.proposalmanagement.auth.infrastructure.persistence.entity.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@EnableMethodSecurity
public class Controller {

    @GetMapping
    public String hello(@AuthenticationPrincipal User user) {
        return "Hello World " + user.getId();
    }

    @GetMapping("/influencer")
    @PreAuthorize("hasRole('INFLUENCER')")
    public String influenceEndpoint() {
        return "Hello INFLUENCER";
    }

    @GetMapping("/brand")
    @PreAuthorize("hasRole('BRAND')")
    public String brandEndpoint() {
        return "Hello BRAND";
    }
}
```

> O `import` de `UserDetails` (linha 6) fica sem uso direto no corpo da classe após essa mudança — o parâmetro `@AuthenticationPrincipal` agora é tipado como `User` — outro pequeno resíduo (*unused import*) que pode ser limpo futuramente, na mesma linha dos resíduos já registrados nos tutoriais anteriores.

---

## Próximos passos: o que vem a partir do Vídeo 05

Segundo o roteiro do curso (conferido no seu README), a sequência dos próximos vídeos é:

- **Vídeo 05 — Segurança com Banco de Dados (continuação):** o README repete o mesmo título "Segurança com Banco de Dados" para os Vídeos 04 e 05, sugerindo que este vídeo dá sequência direta ao que foi construído aqui — possivelmente refinando o tratamento de erros de autenticação (por exemplo, uma resposta mais clara que `403` genérico para usuário inexistente) ou aprofundando o uso do Spring Data JPA antes de avançar para o RBAC do Vídeo 06. No momento em que este tutorial foi escrito, a seção de anotações do Vídeo 05 no README ainda estava em branco (apenas o link do vídeo estava preenchido).
- **Vídeo 06 — Segurança Baseada em Papéis:** deve aprofundar o uso de RBAC (*Role-Based Access Control*), possivelmente introduzindo múltiplas *roles* por usuário, hierarquia de *roles*, ou autorização mais fina (*fine-grained*), retomando o "espectro de autorização" apresentado no Vídeo 01.
- **Vídeo 07 — Implementando o Use Case de Listagem:** deve introduzir a primeira funcionalidade de negócio real do estudo de caso "Influencer & Brand Connect" — provavelmente a listagem de propostas, já usando a base de persistência (JPA + repositórios) construída neste vídeo.
- **Vídeo 08 — Criando Entidades de Persistência:** deve modelar as entidades JPA relacionadas a propostas (`Proposal`), reaproveitando os mesmos padrões vistos aqui para `User` (`@Entity`, `UUID` como chave primária via `@GeneratedValue`, Lombok).
- **Vídeo 09 — Implementando o ProposalController:** deve criar o controller real da aplicação (substituindo o `Controller` genérico criado no Vídeo 02 e evoluído neste vídeo), com endpoints de negócio para criação e consulta de propostas.
- **Vídeo 10 — Segurança Escalável:** deve fechar o curso discutindo como fazer essa configuração de segurança crescer de forma sustentável — um bom momento para retomar a discussão *stateful × stateless* e o JWT, mencionados de passagem no tutorial do Vídeo 03.

> **Sugestão de uso deste documento**
> Depois de assistir ao próximo vídeo, crie um novo arquivo numerado (`004-Tutorial_..._Video05.md`) seguindo este mesmo formato, e sempre confira o código real do `.zip` recebido naquela etapa — não apenas a narrativa do README ou da transcrição — antes de escrever qualquer explicação.
