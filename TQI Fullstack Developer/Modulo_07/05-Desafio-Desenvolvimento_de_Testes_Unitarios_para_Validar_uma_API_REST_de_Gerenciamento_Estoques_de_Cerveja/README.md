## Instrutor

- Rodrigo D'Agostini Peleias (Tech Lead | Staff Software Engineer | Senior Software Engineer | Backend | Java | Spring Boot | 3x AWS Certified)
- Contato Linkedin: / [rodrigopeleias](https://www.linkedin.com/in/rodrigopeleias/)
- Link do projeto no github: https://github.com/rpeleias-v1/beer_api_digital_innovation_one
- fork: https://github.com/ahaerdy/fork-DIO-beer_api_digital_innovation_one

## Parte 1 - Testes Unitários e Qualidade de Software

### 🟩 Vídeo 01 - Como usar os desafios de projetos para criar seu portfólio

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.07-curso.05-video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvimento-de-testes-unitarios-para-validar-uma-api-rest-de-gerenciamento-estoques-de-cerveja/learning/d00f891f-65bb-4149-85f0-57d771116214?back=/track/tqi-fullstack-developer

O vídeo detalha a importância, a execução e a entrega de projetos práticos dentro da plataforma DIO, destacando como essas atividades aceleram a carreira de um desenvolvedor através da criação de um portfólio sólido e da participação em um ecossistema gamificado.
 

### 🟩 Vídeo 02 - Objetivos do curso e apresentação do repositório no GitHub

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.07-curso.05-video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvimento-de-testes-unitarios-para-validar-uma-api-rest-de-gerenciamento-estoques-de-cerveja/learning/a28d07c7-0d18-4986-bd96-d448d7ec05ba

Este guia resume a aula sobre a pirâmide de testes de software, com foco especial em testes unitários, frameworks essenciais do ecossistema Java e a configuração de um ambiente de desenvolvimento moderno para a criação de uma API de Cervejas (Beer API).

### Anotações

#### Objetivos da Aula

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-04-14h45m33s372.jpg" alt="" width="840">
</p>

Nesta etapa inicial, são estabelecidos os fundamentos da aula, focando na estruturação e na qualidade do desenvolvimento de software através de testes. O conteúdo programático está dividido em quatro pilares principais:

* **Pirâmide de Testes:** Apresentação e detalhamento dos níveis de teste, abrangendo testes unitários, integrados e ponta a ponta.
* **Testes Unitários:** Discussão sobre a relevância desses testes durante o ciclo de desenvolvimento, destacando como eles garantem a qualidade do código e auxiliam na documentação do projeto.
* **Frameworks de Referência:** Introdução às ferramentas que serão utilizadas no ecossistema Java, especificamente JUnit, Mockito e Hamcrest.
* **Prática:** Sessão de codificação colaborativa para aplicar os conceitos aprendidos e compartilhar o conhecimento.

#### Stack Tecnológica

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-04-14h45m37s342.jpg" alt="" width="840">
</p>

Para a execução do projeto e das atividades de live coding, será utilizada uma stack tecnológica moderna e padronizada para o desenvolvimento Java. As ferramentas e versões definidas são:

* **Linguagem:** Java 14.
* **Gerenciador de Dependências:** Maven 3.6.3.
* **Framework Base:** Spring Boot, utilizando a última versão estável lançada.
* **Versionamento:** GIT e GitHub para o controle de versão do código.
* **Testes:** Frameworks JUnit, Mockito e Hamcrest.

#### Repositório e Ferramentas

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-04-16h12m45s348.jpg" alt="" width="840">
</p>

O projeto prático consiste em uma **Beer API**, desenvolvida para o gerenciamento de estoques de cerveja e hospedada no GitHub. O repositório centraliza não apenas o código-fonte, mas também guias de execução e referências importantes para o desenvolvedor.

A estrutura do projeto inclui:

* **Desenvolvimento de Testes:** Foco em testes unitários para validar a API REST e a prática de TDD (Test Driven Development).
* **Arquivos do Projeto:** Presença de arquivos essenciais como o `pom.xml` para gestão do Maven, pastas de código `src` e configurações de ignore do Git.
* **Suporte a Testes:** Inclusão de uma coleção do Postman para facilitar a validação das rotas da API.
* **Histórico:** O repositório conta com diversos commits detalhando desde a criação do projeto até o desenvolvimento de serviços específicos para a criação de cervejas.

Link do repositório: https://github.com/rpeleias-v1/beer_api_digital_innovation_one

### 🟩 Vídeo 03 - Apresentação do Projeto no IntelliJ

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.07-curso.05-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvimento-de-testes-unitarios-para-validar-uma-api-rest-de-gerenciamento-estoques-de-cerveja/learning/f1ea7b4a-ddb4-406c-8951-b391763b8a01

O projeto BeerStock é uma aplicação Java desenvolvida com o framework Spring Boot, focada no gerenciamento de estoque de cervejas. A aplicação segue os padrões modernos de desenvolvimento web, utilizando uma arquitetura em camadas e diversas bibliotecas para aumentar a produtividade e garantir a qualidade do código.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-07h22m55s790.jpg" alt="" width="840">
</p>

O ponto de partida do projeto **Beer Stock** é o arquivo `pom.xml`, onde são definidas as dependências necessárias para o funcionamento da aplicação Spring Boot. Entre as principais tecnologias integradas estão o **Spring Data JPA** para persistência, o **Bean Validation** para validação de regras de entrada, o banco de dados em memória **H2** e ferramentas de produtividade como o **Lombok** e o **MapStruct**. Além disso, o projeto inclui o **Swagger** para a geração automática da documentação da API.

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>

    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-07h24m30s515.jpg" alt="" width="840">
</p>

A entidade principal do sistema é a classe `Beer`. Ela utiliza anotações do **Lombok**, como `@Data`, para gerar automaticamente métodos essenciais como getters, setters, `equals` e `hashCode`, reduzindo o código repetitivo. A anotação `@Entity` marca a classe para o mapeamento objeto-relacional (ORM) via JPA, definindo o campo `id` como uma chave primária de autoincremento.

```java
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-07h26m00s738.jpg" alt="" width="840">
</p>

Para categorizar os produtos, foi criado o enum `BeerType`. Ele define os tipos de cerveja suportados pelo sistema (como Lager, IPA e Stout) e armazena uma descrição textual para cada um. O uso das anotações `@Getter` e `@AllArgsConstructor` do Lombok garante o acesso simplificado a essas descrições.

```java
package one.digitalinnovation.beerstock.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BeerType {

    LAGER("Lager"),
    MALZBIER("Malzbier"),
    WITBIER("Witbier"),
    WEISS("Weiss"),
    ALE("Ale"),
    IPA("IPA"),
    STOUT("Stout");

    private final String description;
}
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-07h26m19s056.jpg" alt="" width="840">
</p>

Os atributos da entidade `Beer` incluem regras de negócio específicas refletidas no banco de dados através da anotação `@Column`. O nome da cerveja deve ser único no sistema, impedindo cadastros duplicados. Outros campos como marca, quantidade atual e limite máximo de estoque são definidos como obrigatórios (`nullable = false`). O tipo da cerveja é persistido como uma string no banco de dados para facilitar a leitura.

```java
    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private int max;

    @Column(nullable = false)
    private int quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BeerType type;
}
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-07h27m36s817.jpg" alt="" width="840">
</p>

A estrutura final da entidade `Beer` consolida o mapeamento necessário para gerenciar o estoque. Através da integração entre o JPA e o Lombok, a classe permanece limpa, focando na definição dos dados e restrições enquanto as funcionalidades de infraestrutura são tratadas pelas anotações.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-07h28m15s216.jpg" alt="" width="840">
</p>

A camada de acesso a dados é representada pela interface `BeerRepository`, que estende `JpaRepository`. Essa abordagem permite herdar operações padrão de CRUD (Criar, Ler, Atualizar e Deletar) sem a necessidade de implementar manualmente os métodos. Além disso, a definição do método `findByName` demonstra o poder do Spring Data JPA, que gera automaticamente a consulta SQL necessária baseada apenas na nomenclatura do método, retornando um `Optional` para um tratamento seguro de valores nulos.

```java
package one.digitalinnovation.beerstock.repository;

import one.digitalinnovation.beerstock.entity.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BeerRepository extends JpaRepository<Beer, Long> {

    Optional<Beer> findByName(String name);
}
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-07h30m29s653.jpg" alt="" width="840">
</p>

Ao analisar a interface `JpaRepository` do próprio framework Spring, observa-se que ela define um contrato robusto para manipulação de listas e entidades. Ela estende interfaces como `PagingAndSortingRepository` e `QueryByExampleExecutor`, fornecendo métodos como `findAll()`, `saveAll()` e `flush()`, que abstraem a complexidade das operações em lote e do gerenciamento de transações.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-07h30m43s450.jpg" alt="" width="840">
</p>

Subindo na hierarquia do Spring Data, a `PagingAndSortingRepository` introduz funcionalidades essenciais para APIs modernas: a paginação e a ordenação. Ela herda de `CrudRepository` e adiciona sobrecargas ao método `findAll` que aceitam objetos `Sort` ou `Pageable`, permitindo que a aplicação processe grandes volumes de dados de forma eficiente.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-07h31m15s818.jpg" alt="" width="840">
</p>

A interface `JpaRepository` serve como a base para que o desenvolvedor crie repositórios personalizados com pouco esforço. O Spring interpreta essas interfaces em tempo de execução para fornecer a implementação concreta que lidará com o banco de dados.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-07h31m52s101.jpg" alt="" width="840">
</p>

Internamente, o Spring utiliza a classe `SimpleJpaRepository` para fornecer a implementação padrão das interfaces de repositório. Ela utiliza o `EntityManager` do JPA para realizar as operações, gerenciando automaticamente as conexões e o contexto transacional (marcado com `@Transactional`), o que garante a integridade dos dados sem que o desenvolvedor precise escrever código de infraestrutura.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-07h33m52s517.jpg" alt="" width="840">
</p>

A camada `BeerService` centraliza as regras de negócio da aplicação. Anotada com `@Service`, ela é gerenciada pelo Spring para injeção de dependências. O uso de `@AllArgsConstructor(onConstructor = @__(@Autowired))` facilita a injeção do repositório e do mapper por meio do construtor. Métodos como `createBeer` e `findByName` realizam a orquestração entre a validação de existência, a conversão de DTOs (Data Transfer Objects) para entidades e a persistência final.

```java
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper = BeerMapper.INSTANCE;

    public BeerDTO createBeer(BeerDTO beerDTO) throws BeerAlreadyRegisteredException {
        verifyIfAlreadyRegistered(beerDTO.getName());
        Beer beer = beerMapper.toModel(beerDTO);
        Beer savedBeer = beerRepository.save(beer);
        return beerMapper.toDTO(savedBeer);
    }

    public BeerDTO findByName(String name) throws BeerNotFoundException {
        Beer foundBeer = beerRepository.findByName(name)
                .orElseThrow(() -> new BeerNotFoundException(name));
        return beerMapper.toDTO(foundBeer);
    }
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-07h34m52s639.jpg" alt="" width="840">
</p>

Complementando a lógica de serviço, métodos auxiliares são implementados para garantir a robustez das operações. O método `verifyIfAlreadyRegistered` impede a criação de nomes duplicados, enquanto `verifyIfExists` é utilizado antes de deleções para assegurar que o ID informado corresponde a um registro real, lançando exceções personalizadas quando as condições não são atendidas.

```java
    public List<BeerDTO> listAll() {
        return beerRepository.findAll()
                .stream()
                .map(beerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) throws BeerNotFoundException {
        verifyIfExists(id);
        beerRepository.deleteById(id);
    }

    private void verifyIfAlreadyRegistered(String name) throws BeerAlreadyRegisteredException {
        Optional<Beer> optSavedBeer = beerRepository.findByName(name);
        if (optSavedBeer.isPresent()) {
            throw new BeerAlreadyRegisteredException(name);
        }
    }

    private Beer verifyIfExists(Long id) throws BeerNotFoundException {
        return beerRepository.findById(id)
                .orElseThrow(() -> new BeerNotFoundException(id));
    }
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-07h35m01s705.jpg" alt="" width="840">
</p>

A última camada é o `BeerController`, que expõe os endpoints da API REST. Utilizando `@RestController` e `@RequestMapping("/api/v1/beers")`, ele define a rota base e o formato de troca de dados (JSON). Os verbos HTTP são mapeados para os métodos do serviço: `@PostMapping` para criação, `@GetMapping` para buscas e listagens, e `@DeleteMapping` para remoção. A validação das entradas é feita através da anotação `@Valid` no corpo das requisições.

```java
@RestController
@RequestMapping("/api/v1/beers")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BeerController implements BeerControllerDocs {

    private final BeerService beerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BeerDTO createBeer(@RequestBody @Valid BeerDTO beerDTO) throws BeerAlreadyRegisteredException {
        return beerService.createBeer(beerDTO);
    }

    @GetMapping("/{name}")
    public BeerDTO findByName(@PathVariable String name) throws BeerNotFoundException {
        return beerService.findByName(name);
    }

    @GetMapping
    public List<BeerDTO> listBeers() {
        return beerService.listAll();
    }
}
```      

### 🟩 Vídeo 04 - Introdução ao estilo arquitetural REST

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.07-curso.05-video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvimento-de-testes-unitarios-para-validar-uma-api-rest-de-gerenciamento-estoques-de-cerveja/learning/8bc8324c-0e6d-4c44-bbc0-ee0444f6b42d

O vídeo descreve a transição dos protocolos de integração, o funcionamento dos verbos HTTP e os níveis de maturidade que definem uma API verdadeiramente RESTful.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-08h20m11s168.jpg" alt="" width="840">
</p>

O padrão arquitetural **REST** (Representational State Transfer) surgiu como uma alternativa mais simples e menos complexa ao protocolo SOAP. Enquanto o SOAP dependia de documentos XML extensos que exigiam traduções complexas de código , o REST utiliza a infraestrutura nativa do protocolo **HTTP** para realizar operações de integração.

Neste ecossistema, o fluxo de comunicação baseia-se em:

* **Clients**: Dispositivos ou usuários que enviam **HTTP Requests** e aguardam por uma resposta.
* **Rest API**: Recebe as requisições, processa a lógica necessária (como a criação de usuários) e interage com o **Database**.
* **Verbos HTTP**: Definem a ação a ser executada, como **GET** para leitura, **POST** para criação, **PUT** para atualização total, **PATCH** para modificação parcial e **DELETE** para exclusão.
* **Endpoints**: Rotas específicas como `/allUsers`, `/newUser` ou `/updateUser` que identificam o recurso alvo.
* **Response**: Após o processamento, a API retorna os dados ao cliente, tipicamente formatados em **JSON** ou **XML**.


<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-08h20m22s266.jpg" alt="" width="840">
</p>

A padronização dos **Verbos HTTP** é fundamental para garantir a semântica de uma API RESTful. Cada recurso (URI) deve ser manipulado por métodos específicos que determinam o comportamento esperado do sistema e o formato da resposta:
| Uri             | Method  | Descrição                                | Request            | Response          | Status Code |
|-----------------|---------|------------------------------------------|--------------------|-------------------|-------------|
| `/people`       | **GET** | Recupera todas as pessoas do sistema     | n/a                | Person collection | 200/404     |
| `/people/{id}`  | **GET** | Obtém uma pessoa específica pelo ID      | n/a                | Person            | 200/404     |
| `/people`       | **POST**| Cria uma nova entidade no sistema        | Person (sem ID)    | Person            | 201/404     |
| `/people/{id}`  | **PUT** | Modifica ou substitui um recurso existente | Person             | n/a               | 200/404     |
| `/people/{id}`  | **DELETE** | Remove um recurso do sistema          | n/a                | n/a               | 200/404     |

O uso correto dos **Status Codes** (como o **201 Created** para POST ou **200 OK** para sucessos) permite que o cliente compreenda o resultado da operação sem ambiguidades.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-08h20m24s192.jpg" alt="" width="840">
</p>

Para medir o nível de aderência aos princípios REST, utiliza-se o **Modelo de Maturidade de Richardson**. Este modelo organiza a evolução de um serviço em quatro níveis distintos, conhecidos como "A Glória do REST":

* **Nível 0 (The Swamp of POX)**: O uso do HTTP apenas como um meio de transporte para mensagens, sem explorar seus recursos semânticos.
* **Nível 1 (Resources)**: A API passa a utilizar URIs diferentes para identificar recursos individuais em vez de um único endpoint.
* **Nível 2 (HTTP Verbs)**: Implementação correta dos verbos HTTP e códigos de status para representar as operações.
* **Nível 3 (Hypermedia Controls)**: Introdução de **HATEOAS**, onde a resposta da API fornece links que guiam o cliente sobre as próximas interações possíveis.


### 🟩 Vídeo 05 - Pirâmide de testes

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.07-curso.05-video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvimento-de-testes-unitarios-para-validar-uma-api-rest-de-gerenciamento-estoques-de-cerveja/learning/cb7cccbc-238e-48bd-844a-6c955bd56979

O vídeo discute a importância da **pirâmide de testes** no desenvolvimento de software, destacando como essa estrutura organiza validações desde o nível **unitário** até os testes de **ponta a ponta**. O autor detalha que a base da pirâmide foca em processos rápidos e baratos, enquanto o topo lida com a **usabilidade e regressão**, simulando a experiência real do usuário. A utilização de ferramentas automatizadas e frameworks específicos é apresentada como essencial para garantir a **qualidade do código** e a prevenção de erros em sistemas críticos. Além de validar funcionalidades, os testes servem como uma forma eficiente de **documentação** e facilitam a integração de novos desenvolvedores à equipe. Por fim, a fonte enfatiza que uma suite de testes robusta é o pilar fundamental para implementar fluxos modernos de **integração e entrega contínua (CI/CD)**.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-09h10m25s332.jpg" alt="" width="840">
</p>

A **Pirâmide de Testes**, conceito proposto por Mike Cohn, estabelece uma estrutura estratégica para organizar os testes em diferentes níveis de granularidade, equilibrando custo e velocidade de execução.

* **Testes Unitários (Base)**: Representam o maior volume e focam na validação de unidades isoladas do código, como classes e services. São rápidos e utilizam **mocks** para isolar dependências externas.
* **Testes de Integração (Meio)**: Validam a comunicação entre componentes, como queries em bancos de dados ou chamadas a Web Services. Exigem recursos à parte, o que aumenta o custo de tempo e trabalho.
* **UI Tests / End-to-End (Topo)**: Testam o sistema do ponto de vista do usuário final, percorrendo fluxos completos (como o checkout de um e-commerce). São os mais lentos e custosos de automatizar.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-09h10m31s623.jpg" alt="" width="840">
</p>

A aplicação prática dessa pirâmide no desenvolvimento mobile e Java utiliza ferramentas específicas distribuídas por nível de isolamento e integração:

* **Fast and Cheap (Base)**: Prioriza a velocidade com ferramentas como **JUnit**, **Mockito** e **MockK** para testes isolados. O **Hamcrest** também é citado como auxílio na validação.
* **Camada Intermediária**: Envolve o suporte do **Spring Boot** para testes de integração com repositórios e bancos de dados, além do **AndroidJUnit4** para o contexto Android.
* **Slow and Expensive (Topo)**: Utiliza ferramentas de automação de interface como **Appium**, **Espresso**, **UI Automator** ou **Selenium** para simular interações reais na tela.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-09h10m38s302.jpg" alt="" width="840">
</p>

A adoção desta estratégia de testes oferece benefícios fundamentais para o ciclo de vida do software:

* **Evolução Segura**: Garante que manutenções no código não quebrem funcionalidades existentes (prevenção de bugs).
* **Documentação e Onboarding**: Testes bem projetados e com boas nomenclaturas servem como documentação técnica, auxiliando novos desenvolvedores a entenderem o funcionamento do sistema.
* **Base para CI/CD**: A automação é o alicerce para a **Integração Contínua (CI)** e o **Deploy Contínuo (CD)**. Ferramentas como **Travis CI** e **Circle CI** disparam baterias de testes a cada commit para garantir que apenas código de qualidade chegue à produção.


### 🟩 Vídeo 06 - Frameworks de testes unitários

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.07-curso.05-video_06.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvimento-de-testes-unitarios-para-validar-uma-api-rest-de-gerenciamento-estoques-de-cerveja/learning/868d63ee-6953-4151-a272-017dc4ca047a

O vídeo explica os conceitos fundamentais de testes unitários (Nível 1), abordando desde a filosofia de desenvolvimento até as ferramentas práticas como JUnit 5, Mockito e Hamcrest, integradas ao ecossistema Spring Boot.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-09h00m00s385.jpg" alt="" width="840">
</p>

Os testes unitários representam o primeiro nível da pirâmide de testes, sendo fundamentais para o desenvolvimento de software com qualidade. Suas principais características incluem:

* **Eficiência e Custo:** Possuem o maior volume de execução, apresentando o menor custo e tempo de implementação.
* **Responsabilidade:** São desenvolvidos pelo próprio programador como parte integrante da funcionalidade.
* **Velocidade:** São execuções rápidas focadas diretamente nas linhas de código.
* **Abrangência:** Permitem a cobertura de múltiplos cenários de saída para a lógica implementada.
* **Isolamento:** A integração com outros componentes do sistema é realizada de forma controlada através do uso de **mocks**.

O objetivo central é que o teste não seja uma tarefa separada, mas sim parte do cronograma e do "mindset" de desenvolvimento, garantindo que cada método cubra todos os cenários possíveis de execução.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-09h00m06s269.jpg" alt="" width="840">
</p>

O **JUnit 5** é um dos principais frameworks utilizados para a criação de testes no ecossistema Java. A estrutura básica consiste em criar classes específicas dentro do pacote de testes, utilizando anotações como `@Test` para identificar os métodos que devem ser executados pelo framework. Para validar se o resultado de uma operação está correto, utiliza-se o método `assertEquals`.

```java
import static org.junit.jupiter.api.Assertions.assertEquals;
import example.util.Calculator;
import org.junit.jupiter.api.Test;

class MyFirstJUnitJupiterTests {
    private final Calculator calculator = new Calculator();

    @Test
    void addition() {
        assertEquals(2, calculator.add(1, 1));
    }
}
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-09h00m08s725.jpg" alt="" width="840">
</p>

A página oficial do JUnit 5 detalha a nova geração do framework, que foi redesenhada para suportar funcionalidades do Java 8 (como expressões Lambda) e permitir diferentes estilos de teste na JVM. No portal, o desenvolvedor encontra recursos essenciais como:

* **Guia do Usuário:** Instruções detalhadas de configuração.
* **Javadoc:** Documentação técnica da API.
* **Repositório GitHub:** Acesso ao código-fonte e acompanhamento de problemas (issues).
* **Versões Atuais:** Informações sobre os artefatos disponíveis no Maven Central, incluindo os módulos Jupiter, Vintage e Platform.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-09h00m13s535.jpg" alt="" width="840">
</p>

O **Mockito** é a ferramenta padrão para a criação de objetos dublês (**mocks**). Ele permite simular o comportamento de dependências externas (como um repositório ou serviço) para que o teste unitário foque exclusivamente na lógica da classe atual.

As duas principais funcionalidades demonstradas são:

1. **Verificação de Interações:** Validar se um método específico foi chamado dentro do objeto mocado através do comando `verify`.
2. **Stubbing (Simulação):** Instruir o objeto dublê a retornar um valor específico quando um método for acionado, utilizando a estrutura `when(...).thenReturn(...)`.

```java
// Exemplo de verificação
verify(mockedList).clear();

// Exemplo de stubbing
when(mockedList.get(0)).thenReturn("first");
System.out.println(mockedList.get(0)); // Imprimirá "first"
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-09h00m18s230.jpg" alt="" width="840">
</p>

O site oficial do projeto Mockito apresenta a ferramenta como um framework de "mocking saboroso" para Java. A documentação centralizada no portal auxilia na configuração inicial via Maven ou Gradle e oferece notas de lançamento sobre a compatibilidade das versões (como a necessidade do Java 8 a partir da versão 3.x).

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-09h00m22s801.jpg" alt="" width="840">
</p>

O **Hamcrest** é uma biblioteca de matchers que complementa os testes, tornando-os mais fluentes e legíveis, aproximando a escrita do código da linguagem natural. Ao utilizar o método `assertThat` em conjunto com matchers como `equalTo`, a intenção do teste torna-se muito mais clara do que nas asserções tradicionais.

```java
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BiscuitTest {
    @Test
    public void testEquals() {
        Biscuit theBiscuit = new Biscuit("Ginger");
        Biscuit myBiscuit = new Biscuit("Ginger");
        assertThat(theBiscuit, equalTo(myBiscuit));
    }
}
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-09h00m28s329.jpg" alt="" width="840">
</p>

Para facilitar o gerenciamento de dependências, o ecossistema Spring Boot oferece o **spring-boot-starter-test**. Ao adicionar apenas esta dependência no arquivo de configuração do projeto (pom.xml), o desenvolvedor passa a ter acesso imediato a todas as ferramentas essenciais mencionadas anteriormente.

As bibliotecas incluídas automaticamente são:

* **JUnit:** Motor principal de execução.
* **Mockito:** Para criação de mocks.
* **Hamcrest:** Para asserções fluentes.
* **Spring Test & Spring Core:** Para suporte ao contexto do framework.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

### 🟩 Vídeo 07 - Revisando as dependências do arquivo pom.xml

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.07-curso.05-video_07.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvimento-de-testes-unitarios-para-validar-uma-api-rest-de-gerenciamento-estoques-de-cerveja/learning/ce86cba9-edcc-42e7-8707-59c147683b28

O vídeo aborda a configuração de ambientes de teste utilizando JUnit 5 e Mockito, a implementação de regras de negócio na camada de serviço e a otimização do código através do mapeamento automático com MapStruct.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-11h05m08s914.jpg" alt="" width="840">
</p>

O projeto utiliza o `spring-boot-starter-test` como dependência base para a implementação de testes. Conforme configurado no `pom.xml`, o escopo é definido como `test` e há uma exclusão explícita do `junit-vintage-engine` para garantir que apenas o JUnit 5 (Jupiter) seja utilizado, removendo o suporte à versão 4.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
    <exclusions>
        <exclusion>
            <groupId>org.junit.vintage</groupId>
            <artifactId>junit-vintage-engine</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-11h05m24s922.jpg" alt="" width="840">
</p>

Ao detalhar as dependências internas do starter de teste do Spring Boot, observa-se a inclusão automática de bibliotecas fundamentais como Hamcrest, AssertJ e o próprio JUnit Jupiter (versão 5). Essas ferramentas fornecem os matchers e o framework necessários para a execução de testes unitários e de integração de forma padronizada.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-11h06m51s367.jpg" alt="" width="840">
</p>

A entidade `Beer` representa a estrutura de dados no banco de dados. Ela utiliza anotações do Lombok (`@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`) para reduzir a verbosidade e anotações JPA para o mapeamento objeto-relacional. O atributo `name` é marcado como único no banco de dados para evitar duplicidade.

```java
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private int max;

    @Column(nullable = false)
    private int quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BeerType type;
}
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-11h07m07s932.jpg" alt="" width="840">
</p>

A classe `BeerService` contém a lógica de negócio do sistema. O método `createBeer` ilustra o fluxo de criação: primeiro valida se a cerveja já existe, depois converte o DTO em entidade usando o `beerMapper`, salva através do repositório e, por fim, retorna o objeto persistido convertido novamente em DTO.

```java
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper = BeerMapper.INSTANCE;

    public BeerDTO createBeer(BeerDTO beerDTO) throws BeerAlreadyRegisteredException {
        verifyIfIsAlreadyRegistered(beerDTO.getName());
        Beer beer = beerMapper.toModel(beerDTO);
        Beer savedBeer = beerRepository.save(beer);
        return beerMapper.toDTO(savedBeer);
    }
}
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-11h07m25s494.jpg" alt="" width="840">
</p>

O `BeerDTO` (Data Transfer Object) é utilizado para validar os inputs de entrada antes que eles cheguem à entidade. Ele utiliza anotações do `javax.validation` como `@NotNull`, `@Size` e `@Max` para garantir que as regras de negócio básicas (como campos obrigatórios e limites de quantidade) sejam respeitadas logo na requisição.

```java
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerDTO {

    private Long id;

    @NotNull
    @Size(min = 1, max = 200)
    private String name;

    @NotNull
    @Size(min = 1, max = 200)
    private String brand;

    @NotNull
    @Max(500)
    private Integer max;

    @NotNull
    @Max(100)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @NotNull
    private BeerType type;
}
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-11h07m52s581.jpg" alt="" width="840">
</p>

Além da criação, a `BeerService` implementa funcionalidades de listagem e deleção. O método `listAll` utiliza Streams do Java 8 para mapear todas as entidades encontradas no banco para DTOs, mantendo a separação entre a camada de persistência e a de apresentação.

```java
public List<BeerDTO> listAll() {
    return beerRepository.findAll().stream()
            .map(beerMapper::toDTO)
            .collect(Collectors.toList());
}

public void deleteById(Long id) throws BeerNotFoundException {
    verifyIfExists(id);
    beerRepository.deleteById(id);
}
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-11h07m57s202.jpg" alt="" width="840">
</p>

O `BeerRepository` estende `JpaRepository`, fornecendo operações padrão de CRUD. Foi adicionado o método customizado `findByName`, que retorna um `Optional<Beer>`, utilizado pela service para verificar a existência de registros duplicados pelo nome da cerveja.

```java
public interface BeerRepository extends JpaRepository<Beer, Long> {

    Optional<Beer> findByName(String name);
}
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-11h08m11s763.jpg" alt="" width="840">
</p>

Dentro da `BeerService`, métodos privados auxiliam na validação de integridade. O `verifyIfIsAlreadyRegistered` consulta o repositório pelo nome e lança uma exceção caso o registro já exista. Já o `verifyIfExists` garante que operações de deleção ou busca por ID só ocorram se o registro for encontrado, disparando um erro caso contrário.

```java
private void verifyIfIsAlreadyRegistered(String name) throws BeerAlreadyRegisteredException {
    Optional<Beer> optSavedBeer = beerRepository.findByName(name);
    if (optSavedBeer.isPresent()) {
        throw new BeerAlreadyRegisteredException(name);
    }
}

private Beer verifyIfExists(Long id) throws BeerNotFoundException {
    return beerRepository.findById(id)
            .orElseThrow(() -> new BeerNotFoundException(id));
}
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-11h08m15s374.jpg" alt="" width="840">
</p>

A exceção customizada `BeerAlreadyRegisteredException` é anotada com `@ResponseStatus(HttpStatus.BAD_REQUEST)`. Isso instrui o Spring Boot a retornar automaticamente o código de status HTTP 400 quando essa exceção for lançada, padronizando a resposta de erro para o cliente da API.

```java
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BeerAlreadyRegisteredException extends Exception {

    public BeerAlreadyRegisteredException(String beerName) {
        super(String.format("Beer with name %s already registered in the system.", beerName));
    }
}
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-11h08m24s028.jpg" alt="" width="840">
</p>

O `BeerController` expõe os endpoints REST da aplicação. Ele delega as chamadas para a `BeerService` e utiliza anotações como `@PostMapping`, `@GetMapping` e `@DeleteMapping` para mapear os verbos HTTP. A anotação `@Valid` no método de criação garante que as restrições definidas no DTO sejam verificadas.

```java
@RestController
@RequestMapping("/api/v1/beers")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BeerController {

    private final BeerService beerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BeerDTO createBeer(@RequestBody @Valid BeerDTO beerDTO) throws BeerAlreadyRegisteredException {
        return beerService.createBeer(beerDTO);
    }
    
    // ... outros métodos mapeados
}
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-11h11m45s674.jpg" alt="" width="840">
</p>

Para validar manualmente a API antes da automação dos testes, utiliza-se o Postman. Na imagem, observa-se a configuração de uma requisição `POST` com um corpo JSON contendo os dados da cerveja "Colorado", simulando o preenchimento de campos obrigatórios como marca, quantidade máxima e tipo.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-11h13m08s607.jpg" alt="" width="840">
</p>

A execução da requisição no Postman resulta em sucesso, retornando o status `201 Created`. O corpo da resposta exibe a cerveja criada com um `id` gerado pelo sistema (ID 1), confirmando que o fluxo completo, desde a controller até a persistência no banco de dados, está operante.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-11h13m12s636.jpg" alt="" width="840">
</p>

Ao tentar cadastrar novamente a mesma cerveja ("Colorado"), a API retorna `400 Bad Request`. A mensagem de erro detalha que a cerveja já está registrada, validando na prática o funcionamento da exceção `BeerAlreadyRegisteredException` e da lógica de verificação implementada na service.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-11h13m31s014.jpg" alt="" width="840">
</p>

A estrutura do projeto para testes é organizada dentro da pasta `src/test/java`. Segue-se a mesma estrutura de pacotes da aplicação principal para facilitar a localização e a injeção de dependências durante a execução dos testes unitários com JUnit.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-11h13m39s851.jpg" alt="" width="840">
</p>

A classe `BeerServiceTest` é inicializada com a anotação `@ExtendWith(MockitoExtension.class)`. Essa configuração informa ao JUnit que os testes utilizarão a extensão do Mockito para gerenciar a criação de objetos simulados (mocks), permitindo isolar a classe `BeerService` de suas dependências reais.

```java
@ExtendWith(MockitoExtension.class)
public class BeerServiceTest {

    @Mock
    private BeerRepository beerRepository;

    private BeerMapper beerMapper = BeerMapper.INSTANCE;

    @InjectMocks
    private BeerService beerService;
}
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-11h14m44s488.jpg" alt="" width="840">
</p>

No contexto do teste, `@Mock` cria uma versão dublê do `BeerRepository`, enquanto `@InjectMocks` garante que esse mock seja injetado automaticamente na instância de `BeerService` que está sendo testada. Isso permite simular o comportamento do banco de dados sem a necessidade de uma conexão real.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-11h17m34s830.jpg" alt="" width="840">
</p>

O `BeerMapper` é uma interface que utiliza a biblioteca MapStruct para automatizar a conversão entre DTOs e entidades. A anotação `@Mapper` sinaliza que o framework deve gerar a implementação dessa interface durante o processo de compilação.

```java
@Mapper
public interface BeerMapper {

    BeerMapper INSTANCE = Mappers.getMapper(BeerMapper.class);

    Beer toModel(BeerDTO beerDTO);

    BeerDTO toDTO(Beer beer);
}
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-11h17m54s057.jpg" alt="" width="840">
</p>

O uso do mapper resolve o problema de verbosidade no código. Em vez de realizar múltiplos `setID`, `setName`, etc., manualmente para cada conversão, os métodos `toModel` e `toDTO` realizam o mapeamento de todos os atributos correspondentes de forma automática e performática.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-05-11h18m00s039.jpg" alt="" width="840">
</p>

Para que o MapStruct funcione corretamente com o Lombok, é necessário configurar o `maven-compiler-plugin` no `pom.xml`. Adicionam-se os caminhos dos processadores de anotação (`annotationProcessorPaths`) tanto para o Lombok quanto para o `mapstruct-processor`, permitindo que ambos coexistam e gerem código durante a build.

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <configuration>
        <annotationProcessorPaths>
            <path>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>${lombok.version}</version>
            </path>
            <path>
                <groupId>org.mapstruct</groupId>
                <artifactId>mapstruct-processor</artifactId>
                <version>1.3.1.Final</version>
            </path>
        </annotationProcessorPaths>
    </configuration>
</plugin>
```      

### 🟩 Vídeo 08 - Testando os métodos das classes BeerService e BeerController - parte 1

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.07-curso.05-video_08.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvimento-de-testes-unitarios-para-validar-uma-api-rest-de-gerenciamento-estoques-de-cerveja/learning/a8bc625a-5f37-40c2-a310-90221d3bacb7

O vĩdeo descreve o processo de criação de um teste unitário para a camada de serviço (BeerService) em uma aplicação Java, utilizando ferramentas modernas como JUnit, Mockito e Lombok. O foco é garantir que, ao informar os dados de uma nova cerveja, ela seja criada e salva corretamente no sistema.

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/2026-03-06-07-14-31.png" alt="" width="840">
</p>

Nesta etapa, é demonstrada a construção do primeiro teste unitário para o serviço de cervejas, focando no cenário onde uma nova cerveja é cadastrada com sucesso. O método de teste utiliza o padrão de nomenclatura `whenBeerInformedThenItShouldBeCreated`, seguindo a convenção de comportamento (quando algo acontece, então este deve ser o resultado).

Para facilitar a criação dos objetos de teste, é utilizado o padrão **Builder** através da classe `BeerDTOBuilder`, que permite instanciar um `BeerDTO` com dados pré-definidos (como a marca "Brahma" e o tipo "Lager") de forma fluente. A lógica de teste segue a estrutura:

1. **Given (Dado):** Preparação dos dados de entrada (`BeerDTO`) e a conversão para a entidade de modelo (`Beer`) utilizando um Mapper.
2. **When (Quando):** Configuração do comportamento dos Mocks. O `beerRepository.findByName` é instruído a retornar um `Optional` vazio (indicando que a cerveja não existe) e o `beerRepository.save` é configurado para retornar o objeto esperado.
3. **Then (Então):** Execução da chamada ao método `createBeer` da service e a validação dos resultados através de `assertEquals`, garantindo que o ID e o nome da cerveja retornada correspondam aos dados enviados.

```java
@Test
void whenBeerInformedThenItShouldBeCreated() throws BeerAlreadyRegisteredException {
    // given
    BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
    Beer expectedSavedBeer = beerMapper.toModel(beerDTO);

    // when
    when(beerRepository.findByName(beerDTO.getName())).thenReturn(Optional.empty());
    when(beerRepository.save(expectedSavedBeer)).thenReturn(expectedSavedBeer);

    // then
    BeerDTO createdBeerDTO = beerService.createBeer(beerDTO);

    assertEquals(beerDTO.getId(), createdBeerDTO.getId());
    assertEquals(beerDTO.getName(), createdBeerDTO.getName());
}
```

### 🟩 Vídeo 09 - Testando os métodos das classes BeerService e BeerController - parte 2

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.07-curso.05-video_09.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvimento-de-testes-unitarios-para-validar-uma-api-rest-de-gerenciamento-estoques-de-cerveja/learning/9dca85b1-ba31-4dd9-bb88-f3d074e5d50d

O vídeo demonstra como aprimorar a qualidade e a legibilidade de testes unitários em Java. O instrutor guia o espectador através de um processo de depuração (debug), simulação de dependências com Mockito e a transição de asserções tradicionais do JUnit para uma sintaxe mais fluente e expressiva utilizando a biblioteca Hamcrest.

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-06-09h20m50s859.jpg" alt="" width="840">
</p>

A imagem mostra o método de teste responsável por verificar a criação de uma cerveja quando os dados são informados ao serviço. O teste prepara um `BeerDTO`, converte-o para a entidade esperada, simula o comportamento do repositório com `when(...).thenReturn(...)` e, por fim, chama `beerService.createBeer(...)` para validar o fluxo de criação. 

Trecho de código:

```java
void whenBeerInformedThenItShouldBeCreated() throws BeerAlreadyRegisteredException {
    BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
    Beer expectedSavedBeer = beerMapper.toModel(beerDTO);

    when(beerRepository.findByName(beerDTO.getName())).thenReturn(Optional.empty());
    when(beerRepository.save(expectedSavedBeer)).thenReturn(expectedSavedBeer);

    BeerDTO createdBeerDTO = beerService.createBeer(beerDTO);

    assertEquals(beerDTO.getId(), createdBeerDTO.getId());
    assertEquals(beerDTO.getName(), createdBeerDTO.getName());
}
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-06-09h20m57s888.jpg" alt="" width="840">
</p>

A imagem mostra a execução do teste em modo *debug* dentro do IntelliJ: o professor destaca o uso do debugger para inspecionar o DTO criado, a conversão pelo MapStruct e a simulação das chamadas ao repositório (mocks). Observe que o repositório foi mocado com Mockito, portanto a chamada `findByName` retorna `Optional.empty()` conforme o cenário "não existe ainda". A intenção é validar o fluxo completo de criação sem acessar a camada de persistência real.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-06-09h21m22s362.jpg" alt="" width="840">
</p>

Aqui vemos o painel do *Debugger* com variáveis instanciadas durante a execução do teste: `beerDTO` preenchido, `beerMapper` (implementação do MapStruct) e o mock `beerRepository`. A captura evidencia que o DTO contém valores reais (ex.: `name=Brahma`, `brand=Ambev`, `quantity=10`) e que a conversão para `Beer` foi realizada antes da chamada ao `save`. Esse passo confirma que o mapeamento e os valores esperados estão corretos antes da persistência simulada.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-06-09h21m28s300.jpg" alt="" width="840">
</p>

A captura mostra trechos do código da `BeerService` durante o debug, especificamente a execução do método `createBeer`. É possível ver as variáveis locais `beerDTO`, `beer` (entidade resultante do mapeamento) e `savedBeer` (resultado do `save` simulado). O foco didático é demonstrar como o serviço chama `verifyIfIsAlreadyRegistered(...)`, converte o DTO para entidade, salva e retorna o DTO mapeado do objeto persistido.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-06-09h21m56s592.jpg" alt="" width="840">
</p>

Nesta imagem aparece o método `verifyIfIsAlreadyRegistered` sendo inspecionado: o professor mostra que a verificação consulta `beerRepository.findByName(name)` e, caso exista, lança a exceção `BeerAlreadyRegisteredException`. A captura enfatiza a importância de checar duplicidade antes de persistir, garantindo que o teste cubra o caminho "não existe" (mock retorna `Optional.empty()`), permitindo que o fluxo de criação prossiga.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-06-09h22m40s690.jpg" alt="" width="840">
</p>

A imagem mostra o trecho do serviço onde ocorre a conversão e o `save` efetivo (simulado). Visualmente, nota-se a sequência: `verifyIfIsAlreadyRegistered(beerDTO.getName());` → `Beer beer = beerMapper.toModel(beerDTO);` → `Beer savedBeer = beerRepository.save(beer);` → `return beerMapper.toDTO(savedBeer);`. Esse padrão deixa claro o papel do mapper e do repositório no fluxo de criação.
 
<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-06-09h22m47s016.jpg" alt="" width="840">
</p>

A captura exibe o console de execução dos testes e o resultado: o teste `whenBeerInformedThenItShouldBeCreated` foi executado com sucesso. O professor usa isso para mostrar que, com os mocks corretamente configurados e asserções apropriadas, o teste valida o comportamento esperado do serviço sem dependências externas.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-06-09h26m27s764.jpg" alt="" width="840">
</p>

Aqui o foco é a refatoração dasserções para usar Hamcrest, tornando os asserts mais fluentes e legíveis. O instrutor substitui `assertEquals(...)` por `MatcherAssert.assertThat(...)` com `Matchers.is(equalTo(...))` e também demonstra matchers adicionais como `greaterThan(...)` para validar propriedades numéricas (por exemplo, quantidade maior que um valor). Essa mudança melhora a expressividade dos testes e facilita leitura e manutenção.

Trecho ilustrativo (forma fluente com Hamcrest):

```java
assertThat(createdBeerDTO.getId(), is(equalTo(expectedBeerDTO.getId())));
assertThat(createdBeerDTO.getName(), is(equalTo(expectedBeerDTO.getName())));
assertThat(createdBeerDTO.getQuantity(), is(greaterThan(2)));
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-06-09h27m25s586.jpg" alt="" width="840">
</p>

A última imagem mostra a execução final dos testes após a alteração para Hamcrest: o teste continua passando e o console confirma "Tests passed: 1". O professor usa esse momento para reforçar boas práticas de testes unitários: organizar o teste em `given/when/then`, usar mocks para isolar dependências, empregar mappers para conversão entre DTO e entidade, e matchers para asserções mais claras.


### 🟩 Vídeo 10 - Testando os métodos das classes BeerService e BeerController - parte 3

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.07-curso.05-video_10.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvimento-de-testes-unitarios-para-validar-uma-api-rest-de-gerenciamento-estoques-de-cerveja/learning/3ed42823-3f3f-4b14-9844-b0990256823f


Este vídeo foca na implementação de testes de unidade para a camada de serviço (BeerService) de uma aplicação Java. O objetivo principal é automatizar a validação de cenários de erro, especificamente quando se tenta cadastrar uma cerveja que já existe no sistema, garantindo que a exceção correta seja lançada.

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/2026-03-06-11-05-44.png" alt="" width="840">
</p>

Nesta etapa, o foco é a automação de testes unitários para cenários de exceção na camada de serviço (`BeerService`). O objetivo é simular o comportamento do sistema quando um usuário tenta cadastrar uma cerveja que já existe no banco de dados, garantindo que a exceção personalizada `BeerAlreadyRegisteredException` seja lançada corretamente.

O teste utiliza o padrão **Given/When/Then** e faz uso de **Mocks** para isolar a dependência do repositório. Ao configurar o `beerRepository.findByName` para retornar um `Optional` contendo uma cerveja (em vez de vazio), força-se o fluxo de erro na lógica de negócio. Para validar o lançamento da exceção, utiliza-se o método `assertThrows` do JUnit 5, que verifica se a chamada ao método `createBeer` resulta na exceção esperada.

```java
@Test
void whenAlreadyRegisteredBeerInformedThenAnExceptionShouldBeThrown() {
    // given
    BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
    Beer duplicatedBeer = beerMapper.toModel(expectedBeerDTO);

    // when
    when(beerRepository.findByName(expectedBeerDTO.getName())).thenReturn(Optional.of(duplicatedBeer));

    // then
    assertThrows(BeerAlreadyRegisteredException.class, () -> beerService.createBeer(expectedBeerDTO));
}
```
 

### 🟩 Vídeo 11 - Testando os métodos das classes BeerService e BeerController - parte 4

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.07-curso.05-video_11.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvimento-de-testes-unitarios-para-validar-uma-api-rest-de-gerenciamento-estoques-de-cerveja/learning/7d465124-8a02-4f3e-9bdd-e7f7c931b638

Este vídeo apresenta uma sessão prática de codificação focada no refinamento de uma API Java utilizando o framework Spring Boot. O desenvolvedor demonstra o ciclo completo de tratamento de exceções, depuração (debugging) e a implementação de testes unitários para a camada de controle (Controller) utilizando Mockito e JUnit.

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-06-13h38m51s178.jpg" alt="" width="840">
</p>

A imagem exibe o método `createBeer` dentro da classe `BeerService`. Este método é central para a lógica de negócio de criação de uma nova cerveja. A primeira ação do método é chamar `verifyIfIsAlreadyRegistered`, garantindo que não haja duplicidade de cervejas com o mesmo nome no sistema. Se a cerveja não existir, ela é salva no repositório e, em seguida, o DTO correspondente é retornado.

```java
public BeerDTO createBeer(BeerDTO beerDTO) throws BeerAlreadyRegisteredException {
    verifyIfIsAlreadyRegistered(beerDTO.getName());
    Beer beer = beerMapper.toModel(beerDTO);
    Beer savedBeer = beerRepository.save(beer);
    return beerMapper.toDTO(savedBeer);
}
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-06-13h38m56s426.jpg" alt="" width="840">
</p>

Esta imagem detalha o método `verifyIfIsAlreadyRegistered`, que é responsável por verificar a existência de uma cerveja pelo nome antes de permitir sua criação. Ele utiliza o `beerRepository.findByName` para consultar o banco de dados. Caso a cerveja já esteja registrada, uma exceção `BeerAlreadyRegisteredException` é lançada, impedindo a duplicação. A imagem também mostra o método `findByName` que busca uma cerveja pelo nome e lança `BeerNotFoundException` se não encontrar.

```java
public BeerDTO findByName(String name) throws BeerNotFoundException {
    Beer foundBeer = beerRepository.findByName(name)
            .orElseThrow(() -> new BeerNotFoundException(name));
    return beerMapper.toDTO(foundBeer);
}

private void verifyIfIsAlreadyRegistered(String name) throws BeerAlreadyRegisteredException {
    Optional<Beer> optSavedBeer = beerRepository.findByName(name);
    if (optSavedBeer.isPresent()) {
        throw new BeerAlreadyRegisteredException(name);
    }
}
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-06-13h39m23s726.jpg" alt="" width="840">
</p>

A imagem apresenta a definição da classe `BeerAlreadyRegisteredException`, uma exceção personalizada utilizada para indicar que uma cerveja com o mesmo nome já se encontra registrada no sistema. Esta classe estende `Exception` e é anotada com `@ResponseStatus(HttpStatus.BAD_REQUEST)`, o que garante que, ao ser lançada, a API retorne um status HTTP 400 (Bad Request), conforme o padrão REST para problemas no lado do cliente.

```java
package one.digitalinnovation.beerstock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BeerAlreadyRegisteredException extends Exception {

    public BeerAlreadyRegisteredException(String beerName) {
        super(String.format("Beer with name %s already registered in the system.", beerName));
    }
}
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-06-13h39m42s183.jpg" alt="" width="840">
</p>

Esta imagem ilustra o teste `whenAlreadyRegisteredInformedThenAnExceptionShouldBeThrown` na classe `BeerServiceTest`. O teste verifica se, ao tentar criar uma cerveja que já existe, a exceção `BeerAlreadyRegisteredException` é corretamente lançada. A seção `when` simula a chamada ao `beerService.createBeer` com um DTO de cerveja já existente, e a seção `then` valida que a exceção esperada é disparada.

```java
@Test
void whenAlreadyRegisteredInformedThenAnExceptionShouldBeThrown() {
    // given
    BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build();

    // when
    when(beerRepository.findByName(expectedBeerDTO.getName()))
            .thenReturn(Optional.of(duplicatedBeer));

    // then
    assertThrows(BeerAlreadyRegisteredException.class, () -> beerService.createBeer(expectedBeerDTO));
}
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-06-13h40m12s661.jpg" alt="" width="840">
</p>

Esta imagem mostra o processo de depuração (`debug`) do método `createBeer` na `BeerService`. É possível observar o fluxo de execução, onde o `verifyIfIsAlreadyRegistered` é chamado e, ao encontrar uma cerveja com o mesmo nome (`Brahma`), a exceção `BeerAlreadyRegisteredException` é preparada para ser lançada. Os detalhes do `beerDTO` e do `beerRepository` são visíveis no painel de variáveis, confirmando a detecção da duplicidade.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-06-13h40m18s929.jpg" alt="" width="840">
</p>

Esta imagem continua a depuração, focando no momento exato em que a exceção `BeerAlreadyRegisteredException` é lançada. O debugger aponta para a linha `throw new BeerAlreadyRegisteredException(name);` dentro do método `verifyIfIsAlreadyRegistered`, confirmando que a lógica de validação de duplicidade está funcionando conforme o esperado. A variável `beerName` exibe o valor "Brahma", indicando a cerveja que causou a exceção.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-06-13h40m39s178.jpg" alt="" width="840">
</p>

Esta imagem exibe uma requisição HTTP POST realizada via Postman para a API de cervejas (`/api/v1/beers`). O corpo da requisição JSON tenta criar uma cerveja chamada "Colorado". A resposta da API, com status `400 Bad Request`, indica que a operação falhou porque "Beer with name Colorado already registered in the system.", confirmando o tratamento da exceção de cerveja já registrada no nível da API.

```json
// Request Body
{
    "name": "Colorado",
    "brand": "Colorado",
    "max": 50,
    "quantity": 10,
    "type": "LAGER"
}

// Response
{
    "timestamp": "2020-09-01T21:05:06.000+00:00",
    "status": 400,
    "error": "Bad Request",
    "message": "Beer with name Colorado already registered in the system.",
    "path": "/api/v1/beers"
}
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-06-13h48m05s506.jpg" alt="" width="840">
</p>

A imagem apresenta a estrutura inicial da classe `BeerControllerTest`, responsável por testar a camada de controle da aplicação. São definidas constantes importantes para os testes, como a URL base da API (`API_URL_PATH`), IDs de cervejas válidas e inválidas, e subcaminhos para operações de incremento e decremento. A classe estende `MockitoExtension.class`, indicando o uso do framework Mockito para simulação de dependências.

```java
package one.digitalinnovation.beerstock.controller;

import one.digitalinnovation.beerstock.builder.BeerDTOBuilder;
import one.digitalinnovation.beerstock.dto.BeerDTO;
import one.digitalinnovation.beerstock.exception.BeerAlreadyRegisteredException;
import one.digitalinnovation.beerstock.exception.BeerNotFoundException;
import one.digitalinnovation.beerstock.service.BeerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collections;

import static one.digitalinnovation.beerstock.utils.JsonConversionUtils.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class BeerControllerTest {

    private static final String BEER_API_URL_PATH = "/api/v1/beers";
    private static final long VALID_BEER_ID = 1L;
    private static final long INVALID_BEER_ID = 2L;
    private static final String BEER_API_SUBPATH_INCREMENT_URL = "/increment";
    private static final String BEER_API_SUBPATH_DECREMENT_URL = "/decrement";

    @Mock
    private BeerService beerService;

    @InjectMocks
    private BeerController beerController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(beerController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView().build())
                .build();
    }
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-06-13h49m22s470.jpg" alt="" width="840">
</p>

Esta imagem detalha o teste `whenPOSTIsCalledThenABeerIsCreated` na classe `BeerControllerTest`. Este teste verifica o cenário de sucesso na criação de uma cerveja via requisição POST. Ele configura um `BeerDTO` válido, simula o comportamento do `beerService.createBeer` para retornar o DTO criado, e então utiliza o `mockMvc.perform` para executar uma requisição POST para `/api/v1/beers`. As asserções (`andExpect`) verificam se o status HTTP é 201 (Created) e se os campos do JSON de resposta correspondem aos dados da cerveja criada.

```java
@Test
void whenPOSTIsCalledThenABeerIsCreated() throws Exception {
    // given
    BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build();

    // when
    when(beerService.createBeer(expectedBeerDTO)).thenReturn(expectedBeerDTO);

    // then
    mockMvc.perform(post(BEER_API_URL_PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(expectedBeerDTO)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name", is(expectedBeerDTO.getName())))
            .andExpect(jsonPath("$.brand", is(expectedBeerDTO.getBrand())))
            .andExpect(jsonPath("$.type", is(expectedBeerDTO.getType().toString())));
}
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-06-13h49m57s149.jpg" alt="" width="840">
</p>

Esta imagem exibe um cenário de falha em um teste de controlador, especificamente um erro `415 UNSUPPORTED_MEDIA_TYPE`. Este erro ocorre quando o tipo de mídia (`Content-Type`) enviado na requisição HTTP não é suportado pelo servidor ou não corresponde ao esperado. No contexto do teste, isso pode indicar um problema na configuração do `contentType(MediaType.APPLICATION_JSON)` ou na conversão do `BeerDTO` para JSON usando `asJsonString`, resultando em uma requisição malformada ou com um cabeçalho `Content-Type` incorreto.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-06-13h51m38s720.jpg" alt="" width="840">
</p>

Esta imagem mostra o método `asJsonString` da classe `JsonConversionUtils`, que é uma utilidade para converter objetos Java em strings JSON. Este método é crucial para os testes de integração do controlador, pois permite simular o corpo de requisições HTTP POST e PUT com objetos DTOs serializados corretamente. A implementação utiliza a biblioteca `Jackson` (`ObjectMapper`) para realizar a serialização, garantindo que os dados sejam formatados de acordo com o padrão JSON esperado pela API.

```java
package one.digitalinnovation.beerstock.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonConversionUtils {

    public static String asJsonString(Object beerDTO) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.registerModules(new JavaTimeModule());

            return objectMapper.writeValueAsString(beerDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
```

### 🟩 Vídeo 12 - Testando os métodos das classes BeerService e BeerController - parte 5

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.07-curso.05-video_12.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvimento-de-testes-unitarios-para-validar-uma-api-rest-de-gerenciamento-estoques-de-cerveja/learning/846c64c2-1959-49eb-8462-c63c71aa31cf

Implementação de testes unitários para uma API de gerenciamento de cervejas, focando na criação de registros (POST) e na validação de regras de negócio através de DTOs (Data Transfer Objects).

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/2026-03-06-15-52-38.png" alt="" width="840">
</p>

Nesta etapa do desenvolvimento, o foco volta-se para a validação de integridade dos dados de entrada na API. O objetivo é garantir que as restrições definidas no DTO (como campos `@NotNull`) sejam respeitadas pelo framework antes mesmo da lógica de negócio ser processada.

O teste implementado simula um cenário de erro onde um campo obrigatório, neste caso a marca (**brand**), é enviado como nulo. Diferente do teste de criação bem-sucedida, aqui não há necessidade de mocar o serviço (`beerService.createBeer`), pois a **Bean Validation** deve interceptar a requisição e impedir que ela avance para a camada de serviço. O resultado esperado para essa operação é o status HTTP **400 Bad Request**.

```java
@Test
void whenPOSTIsCalledWithoutRequiredFieldThenAnErrorIsReturned() throws Exception {
    // given
    BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
    beerDTO.setBrand(null);

    // then
    mockMvc.perform(post(BEER_API_URL_PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(beerDTO)))
            .andExpect(status().isBadRequest());
}
```

### 🟩 Vídeo 13 - Testando os métodos das classes BeerService e BeerController - parte 6

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.07-curso.05-video_13.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvimento-de-testes-unitarios-para-validar-uma-api-rest-de-gerenciamento-estoques-de-cerveja/learning/5d7adb19-026a-4fd4-94b5-d27003c0e690

Este vídeo foca na implementação e nos testes unitários da funcionalidade de busca de cervejas pelo nome em uma aplicação Java com Spring Boot. O instrutor demonstra desde a validação no Postman até a codificação da lógica de serviço, tratamento de exceções e automação de testes usando Mockito e Hamcrest

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/2026-03-06-16-13-54.png" alt="" width="840">
</p>

Nesta etapa, o foco recai sobre a implementação de testes unitários na classe `BeerServiceTest` para validar a busca de cervejas por nome. O ambiente de desenvolvimento (IntelliJ IDEA) exibe a estrutura do projeto e o código responsável por garantir que as regras de negócio de consulta estejam funcionando corretamente.

O teste utiliza o padrão **Given/When/Then** para organizar a lógica:

* **Given (Dado):** Prepara-se um `BeerDTO` esperado através de um builder e converte-se para o modelo de entidade correspondente.
* **When (Quando):** Configura-se o comportamento do mock (`beerRepository`) para retornar um `Optional` contendo a cerveja quando o nome for consultado.
* Em seguida, o método `findByName` do serviço é efetivamente chamado.
* **Then (Então):** Utiliza-se o `assertThat` do Hamcrest para verificar se o objeto retornado pelo serviço é igual ao DTO esperado, validando a integridade da busca e da conversão de dados.

```java
@Test
void whenValidBeerNameIsGivenThenReturnABeer() throws BeerNotFoundException {
    // given
    BeerDTO expectedFoundBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
    Beer expectedFoundBeer = beerMapper.toModel(expectedFoundBeerDTO);

    // when
    when(beerRepository.findByName(expectedFoundBeer.getName())).thenReturn(Optional.of(expectedFoundBeer));

    // then
    BeerDTO foundBeerDTO = beerService.findByName(expectedFoundBeerDTO.getName());

    assertThat(foundBeerDTO, is(equalTo(expectedFoundBeerDTO)));
}
```

### 🟩 Vídeo 14 - Testando os métodos das classes BeerService e BeerController - parte 7

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.07-curso.05-video_14.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvimento-de-testes-unitarios-para-validar-uma-api-rest-de-gerenciamento-estoques-de-cerveja/learning/0b1a6821-7b63-4554-99f3-9a426080f5a3

O vídeo foca na implementação de testes automatizados para validar o comportamento de uma API de cervejas. O desenvolvedor demonstra como testar cenários de erro (exceções) na camada de serviço e como validar os endpoints da camada de controle (Controller) utilizando Mockito e MockMvc.

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-06-16h48m47s344.jpg" alt="" width="840">
</p>

A imagem exibe o arquivo `BeerService.java` aberto no IntelliJ IDEA, com destaque para o método `findByName`. Esse método tenta localizar uma cerveja no repositório pelo nome e, caso não encontre, lança a exceção `BeerNotFoundException` usando o encadeamento `.orElseThrow()`. No painel inferior, o resultado da execução do teste `whenNotRegisteredBeerNameIsGivenThenThrowAnException` é exibido com sucesso — **1 teste aprovado em 3s 958ms** — confirmando que a camada de serviço lança corretamente a exceção quando nenhuma cerveja é encontrada para o nome informado.

```java
public BeerDTO findByName(String name) throws BeerNotFoundException {
    Beer foundBeer = beerRepository.findByName(name)
            .orElseThrow(() -> new BeerNotFoundException(name));
    return beerMapper.toDTO(foundBeer);
}
```


<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-06-16h49m21s270.jpg" alt="" width="840">
</p>
A imagem mostra o Postman executando uma requisição `GET` para o endpoint `http://localhost:8080/api/v1/beers/Colorado appia`. A resposta retorna o status **200 OK** com o corpo JSON abaixo, demonstrando que a busca de cerveja por nome está funcionando corretamente na aplicação em execução:

```json
{
  "id": 1,
  "name": "Colorado appia",
  "brand": "Colorado",
  "max": 20,
  "quantity": 10,
  "type": "LAGER"
}
```

Esse teste manual no Postman serve de referência para o comportamento esperado que será validado automaticamente pelo teste unitário da camada de controller.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-06-16h51m15s571.jpg" alt="" width="840">
</p>

A imagem exibe o arquivo `BeerControllerTest.java` no IntelliJ IDEA com o método de teste `whenGETIsCalledWithValidNameThenOkStatusIsReturned`. O teste segue o padrão **given / when / then**:

- **given**: um `BeerDTO` é construído via `BeerDTOBuilder`.
- **when**: o mock do `beerService.findByName()` é configurado para retornar esse DTO quando chamado com o nome da cerveja.
- **then**: o `mockMvc` executa um `GET` no caminho `BEER_API_URL_PATH + "/" + beerDTO.getName()` e verifica que o status retornado é `200 OK`, além de validar os campos `$.name`, `$.brand` e `$.type` no corpo da resposta JSON.

```java
@Test
void whenGETIsCalledWithValidNameThenOkStatusIsReturned() throws Exception {
    // given
    BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();

    //when
    when(beerService.findByName(beerDTO.getName())).thenReturn(beerDTO);

    // then
    mockMvc.perform(MockMvcRequestBuilders.get(BEER_API_URL_PATH + "/" + beerDTO.getName())
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is(beerDTO.getName())))
            .andExpect(jsonPath("$.brand", is(beerDTO.getBrand())))
            .andExpect(jsonPath("$.type", is(beerDTO.getType().toString())));
}
```

Esse teste valida unitariamente a camada de controller, isolando-a da camada de serviço por meio do Mockito, de forma análoga ao que foi verificado manualmente no Postman.      


### 🟩 Vídeo 15 - Testando os métodos das classes BeerService e BeerController - parte 8

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.07-curso.05-video_15.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvimento-de-testes-unitarios-para-validar-uma-api-rest-de-gerenciamento-estoques-de-cerveja/learning/51a2c2e3-d82a-4d01-a85c-21c0c5a6d68a

Este vídeo tutorial foca na implementação de testes unitários para uma aplicação Spring Boot (API de Cervejas), utilizando ferramentas como JUnit, Mockito e Hamcrest. O instrutor demonstra como simular cenários de erro (404 Not Found), testar listagens de recursos e manter o fluxo de trabalho integrado com o Git.

### Anotações

<p align="center">
   <img src="000-Midia_e_Anexos/vlcsnap-2026-03-07-10h03m07s027.jpg" alt="" width="840">
</p>

Nesta etapa, o foco é validar o comportamento da API quando uma cerveja não é encontrada pelo nome. O teste unitário na camada de controller é configurado para simular essa exceção. Utilizando o framework de mock, instruímos o serviço a lançar uma `BeerNotFoundException` quando o método `findByName` for chamado.

Em seguida, a requisição HTTP é performada e espera-se explicitamente que o status de resposta seja `404 Not Found`. Isso garante que a camada de controle esteja tratando corretamente as exceções de negócio e retornando o código de status adequado ao cliente.

```java
@Test
void whenGETIsCalledWithoutRegisteredNameThenNotFoundStatusIsReturned() throws Exception {
    // given
    BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
    
    // when
    when(beerService.findByName(beerDTO.getName())).thenThrow(BeerNotFoundException.class);
    
    // then
    mockMvc.perform(MockMvcRequestBuilders.get(
            BEER_API_URL_PATH + "/" + beerDTO.getName())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
}
```

<p align="center">
   <img src="000-Midia_e_Anexos/vlcsnap-2026-03-07-10h04m00s758.jpg" alt="" width="840">
</p>

Após a implementação do teste para o cenário de não encontrado, a execução é realizada para confirmar o sucesso da validação. O ambiente de desenvolvimento indica que o teste passou corretamente, validando a lógica de retorno do status 404.

Com a funcionalidade testada e validada, o próximo passo é versionar o código. O commit é realizado com uma mensagem descritiva que resume as alterações: implementação de testes unitários para as operações de serviço e controllers relacionadas aos métodos POST e GET por nome. Isso assegura que o progresso seja salvo no repositório remoto.

```bash
git add .
git commit -m "developed unit tests for service and controllers for post and get by name operation"
git push origin master
```

<p align="center">
   <img src="000-Midia_e_Anexos/vlcsnap-2026-03-07-10h14m30s357.jpg" alt="" width="840">
</p>

Agora a atenção se volta para a camada de serviço, especificamente para a operação de listagem de todas as cervejas. O teste unitário é criado para garantir que o método `listAll` retorne corretamente uma lista de objetos DTO.

O repositório é mockado para retornar uma lista contendo um elemento esperado quando o método `findAll` é chamado. A validação utiliza asserções fluentes para confirmar que a lista retornada não está vazia e que o primeiro elemento corresponde exatamente ao DTO esperado, garantindo a integridade da conversão e dos dados retornados pelo serviço.

```java
@Test
void whenListBeerIsCalledThenReturnAListOfBeers() {
    // given
    BeerDTO expectedFoundBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
    Beer expectedFoundBeer = beerMapper.toModel(expectedFoundBeerDTO);
    
    // when
    when(beerRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundBeer));
    
    // then
    List<BeerDTO> foundListBeersDTO = beerService.listAll();
    
    assertThat(foundListBeersDTO, is(not(empty())));
    assertThat(foundListBeersDTO.get(0), is(equalTo(expectedFoundBeerDTO)));
}
```      

### 🟩 Vídeo 16 - Testando os métodos das classes BeerService e BeerController - parte 9

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.07-curso.05-video_16.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvimento-de-testes-unitarios-para-validar-uma-api-rest-de-gerenciamento-estoques-de-cerveja/learning/7baadb5f-5dca-4a0f-9e4b-bf420b5391e3

Este vídeo foca na implementação e validação de testes automatizados para uma API de gerenciamento de cervejas, utilizando Java, Spring Boot, Mockito e JUnit. O desenvolvedor demonstra como testar cenários de sucesso (com dados) e cenários de lista vazia, tanto na camada de serviço/repositório quanto na camada de controle (Controller).

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-07-10h50m11s666.jpg" alt="" width="840">
</p>

Nesta etapa, o teste unitário para o controller está sendo estruturado para validar o endpoint de listagem de cervejas. O método de teste `whenGETListWithBeersIsCalledThenOkStatusIsReturned` configura um cenário onde um objeto `BeerDTO` é criado e o serviço é mockado para retornar esse objeto. A requisição GET é executada contra a URL da API e valida-se não apenas o status HTTP 200 (OK), mas também o conteúdo do JSON retornado.

Utiliza-se o `jsonPath` para assegurar que os atributos do primeiro elemento da lista (nome, marca e tipo) correspondam aos dados do objeto esperado. Note que, neste momento inicial, a configuração do mock ainda reflete uma chamada específica por nome (`findByName`), que será ajustada para refletir corretamente uma listagem geral.

```java
@Test
void whenGETListWithBeersIsCalledThenOkStatusIsReturned() throws Exception {
    // given
    BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
    // when
    when(beerService.findByName(beerDTO.getName())).thenReturn(beerDTO);
    // then
    mockMvc.perform(MockMvcRequestBuilders.get(BEER_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$[0].name", is(beerDTO.getName())))
          .andExpect(jsonPath("$[0].brand", is(beerDTO.getBrand())))
          .andExpect(jsonPath("$[0].type", is(beerDTO.getType().toString())));
}
```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-03-07-10h50m38s536.jpg" alt="" width="840">
</p>

Aqui observa-se a correção aplicada ao teste de listagem. Para que o teste reflita corretamente o comportamento de listar todas as cervejas, o método mockado do serviço foi alterado de `findByName` para `listAll`. Além disso, o retorno do mock agora utiliza `Collections.singletonList(beerDTO)`, garantindo que o serviço retorne uma lista contendo o objeto criado, em vez do objeto isolado.

Essa alteração alinha a configuração do teste (bloco *given*) com a operação de listagem geral que está sendo validada na requisição GET. Os logs no console inferior indicam a execução dos testes e as validações internas do framework, confirmando que o ajuste no código permite que o teste prossiga para validação correta do status e do conteúdo da resposta.

```java
@Test
void whenGETListWithBeersIsCalledThenOkStatusIsReturned() throws Exception {
    // given
    BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
    // when
    when(beerService.listAll()).thenReturn(Collections.singletonList(beerDTO));
    // then
    mockMvc.perform(MockMvcRequestBuilders.get(BEER_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$[0].name", is(beerDTO.getName())))
          .andExpect(jsonPath("$[0].brand", is(beerDTO.getBrand())))
          .andExpect(jsonPath("$[0].type", is(beerDTO.getType().toString())));
}
```     

### 🟩 Vídeo 17 - Testando os métodos das classes BeerService e BeerController - parte 10

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.07-curso.05-video_17.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvimento-de-testes-unitarios-para-validar-uma-api-rest-de-gerenciamento-estoques-de-cerveja/learning/d1eb84f8-a09b-4472-a179-51beec8a885e

Este documento detalha o processo de implementação do método de exclusão de registros em uma aplicação Java, seguindo padrões REST, e a criação de testes unitários robustos utilizando Mockito para validar comportamentos em métodos que não possuem retorno (void).

### Anotações

#### 

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-07-13h29m12s626.jpg" alt="" width="840">
</p>

Nesta seção da aula, é apresentado o código da classe BeerService, focando nos métodos responsáveis pela busca, listagem e exclusão de cervejas. O método `findById` recupera uma cerveja pelo ID, convertendo-a para DTO e lançando uma exceção se não encontrada. O `listAll` retorna uma lista de todas as cervejas como DTOs. O `deleteById` verifica a existência da cerveja antes de deletá-la, alinhando com o padrão REST para retornar status 204 No Content em exclusões bem-sucedidas. Os métodos auxiliares `verifyIfIsAlreadyRegistered` e `verifyIfExists` garantem integridade, evitando duplicatas e confirmando existência.

```java
return beerMapper.toDTO(foundBeer);
}

public List<BeerDTO> listAll() {
    return beerRepository.findAll().stream()
        .map(beerMapper::toDTO)
        .collect(Collectors.toList());
}

public void deleteById(Long id) throws BeerNotFoundException {
    verifyIfExists(id);
    beerRepository.deleteById(id);
}

private void verifyIfIsAlreadyRegistered(String name) throws BeerAlreadyRegisteredException {
    Optional<Beer> optSavedBeer = beerRepository.findByName(name);
    if (optSavedBeer.isPresent()) {
        throw new BeerAlreadyRegisteredException(name);
    }
}

private Beer verifyIfExists(Long id) throws BeerNotFoundException {
    return beerRepository.findById(id)
        .orElseThrow(() -> new BeerNotFoundException(id));
}
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-07-13h29m47s294.jpg" alt="" width="840">
</p>

Aqui, são demonstrados testes unitários para o BeerService usando Mockito. O teste `whenListBeerIsCalledThenReturnAnEmptyListOfBeers` verifica se a listagem retorna uma lista vazia quando o repositório está vazio. O teste `whenExclusionIsCalledWithValidIdThenABeerShouldBeDeleted` simula a exclusão de uma cerveja válida, mockando a busca e a deleção, e usando `verify` para confirmar que os métodos do repositório foram chamados exatamente uma vez cada.

```java
@Test
void whenListBeerIsCalledThenReturnAnEmptyListOfBeers() {
    when(beerRepository.findAll()).thenReturn(Collections.emptyList());
    List<BeerDTO> foundListBeersDTO = beerService.listAll();
    assertThat(foundListBeersDTO, is(empty()));
}

@Test
void whenExclusionIsCalledWithValidIdThenABeerShouldBeDeleted() throws BeerNotFoundException {
    // given
    BeerDTO expectedDeletedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
    Beer expectedDeletedBeer = beerMapper.toModel(expectedDeletedBeerDTO);

    // when
    when(beerRepository.findById(expectedDeletedBeerDTO.getId())).thenReturn(Optional.of(expectedDeletedBeer));
    doNothing().when(beerRepository).deleteById(expectedDeletedBeerDTO.getId());

    // then
    beerService.deleteById(expectedDeletedBeerDTO.getId());
    verify(beerRepository, times(1)).findById(expectedDeletedBeerDTO.getId());
    verify(beerRepository, times(1)).deleteById(expectedDeletedBeerDTO.getId());
}
```      

### 🟩 Vídeo 18 - Testando os métodos das classes BeerService e BeerController - parte 11

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.07-curso.05-video_18.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvimento-de-testes-unitarios-para-validar-uma-api-rest-de-gerenciamento-estoques-de-cerveja/learning/f14b87a1-0fe8-44ad-8f34-944813896e44

Este vídeo tutorial foca na implementação de testes unitários para a funcionalidade de exclusão (DELETE) de uma API de cervejas, utilizando JUnit, Mockito e MockMVC. O instrutor demonstra como validar tanto o cenário de sucesso (quando o ID existe) quanto o cenário de erro (quando o ID não é encontrado), seguindo princípios de TDD (Test-Driven Development).

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-07-13h49m02s247.jpg" alt="" width="840">
</p>

Esta imagem apresenta trechos de código de testes unitários em Java para um controller de API, focando em verificações de chamadas HTTP. O primeiro teste simula uma requisição GET para listar itens (cervejas), mocando o serviço para retornar uma lista e esperando um status OK. O segundo teste inicia a configuração para uma requisição DELETE, mocando o serviço para não executar ação e esperando status No Content. Isso ilustra a abordagem inicial para testar endpoints de leitura e exclusão em um ambiente controlado com mocks, garantindo que o controller responda corretamente sem depender de um banco de dados real.

```java
@Test
void whenGETListWithoutBeersIsCalledThenOkStatusIsReturned() throws Exception {
    BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
    //when
    when(beerService.listAll()).thenReturn(Collections.singletonList(beerDTO));
    //then
    mockMvc.perform(MockMvcRequestBuilders.get(BEER_API_URL_PATH)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
}

@Test
void whenDELETEIsCalled() throws Exception {
    Debug 'whenDELETEIsCalled()'
    Run 'whenDELETEIsCalled() with Coverage
    Create 'whenDELETEIsCalled()...
    //when
    doNothing().when(beerService).deleteById(beerDTO.getId());
    //then
    mockMvc.perform(MockMvcRequestBuilders.delete(urlTemplate: BEER_API_URL_PATH + "/" + beerDTO.getId())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
}
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-07-13h51m28s730.jpg" alt="" width="840">
</p>

Esta imagem exibe testes unitários em Java para o endpoint DELETE de um controller de API. O primeiro teste configura um cenário de sucesso para exclusão com ID válido, mocando o serviço para não executar ação (doNothing) e verificando se o status HTTP retornado é No Content (204). O segundo teste simula um erro para ID inválido, mocando o serviço para lançar uma exceção BeerNotFoundException e esperando status Not Found (404). Essa estrutura demonstra como validar fluxos positivos e negativos no controller, promovendo uma cobertura robusta de casos de uso em desenvolvimento orientado a testes.

```java
@Test
void whenDELETEIsCalledWithValidIdThenNoContentStatusIsReturned() throws Exception {
    BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
    // when
    doNothing().when(beerService).deleteById(beerDTO.getId());
    // then
    mockMvc.perform(MockMvcRequestBuilders.delete(urlTemplate: BEER_API_URL_PATH + "/" + beerDTO.getId())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
}

@Test
void whenDELETEIsCalledWithInvalidIdThenNotFoundStatusIsReturned() throws Exception {
    BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
    // when
    doThrow(BeerNotFoundException.class).when(beerService).deleteById(beerDTO.getId());
    // then
    mockMvc.perform(MockMvcRequestBuilders.delete(urlTemplate: BEER_API_URL_PATH + "/" + beerDTO.getId())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
}
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-07-13h51m56s428.jpg" alt="" width="840">
</p>

Esta imagem mostra a continuação de testes unitários em Java para o controller, com foco no endpoint DELETE. Inclui a configuração para um teste de exclusão com ID válido, usando doNothing no mock do serviço e verificando status No Content. Em seguida, um teste para ID inválido, lançando BeerNotFoundException com um ID constante específico (INVALID_BEER_ID) e esperando status Not Found. Há também um comentário sobre um teste futuro para PATCH, ilustrando a progressão no desenvolvimento de testes para cobrir cenários de erro e atualizações parciais.

```java
// given 
BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO(); 
// when 
doNothing().when(beerService).deleteById(beerDTO.getId()); 
// then 
mockMvc.perform(MockMvcRequestBuilders.delete(urlTemplate: BEER_API_URL_PATH + "/" + beerDTO.getId()) 
        .contentType(MediaType.APPLICATION_JSON)) 
        .andExpect(status().isNoContent()); 
} 
@Test 
void whenDELETEIsCalledWithInvalidIdThenNotFoundStatusIsReturned() throws Exception { 
    //when 
    doThrow(BeerNotFoundException.class).when(beerService).deleteById(INVALID_BEER_ID); 
    // then 
    mockMvc.perform(MockMvcRequestBuilders.delete(urlTemplate: BEER_API_URL_PATH + "/" + INVALID_BEER_ID) 
            .contentType(MediaType.APPLICATION_JSON)) 
            .andExpect(status().isNotFound()); 
} 
// @Test 
void whenPATCHIsCalledToIncrementDiscountThenOkStatusIsReturned() throws Exception { 
    // QuantityDTO quantityDTO = QuantityDTO.builder() 
    .quantity(10) 
    .build(); 
    // BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO(); 
    // beerDTO.setQuantity(beerDTO.getQuantity() + quantityDTO.netQuantity()); 
```

### 🟩 Vídeo 19 - Testando os métodos das classes BeerService e BeerController - parte 12

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.07-curso.05-video_19.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvimento-de-testes-unitarios-para-validar-uma-api-rest-de-gerenciamento-estoques-de-cerveja/learning/ab33eb1d-8cd2-4bc9-b495-e72a25542b25

Este vídeo tutorial foca na consolidação de testes unitários para uma API de gerenciamento de cervejas e na introdução da prática de TDD (Test Driven Development) para implementar uma nova funcionalidade de incremento de estoque.

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-07-14h18m08s632.jpg" alt="" width="840">
</p>

A imagem exibe um trecho de código fonte em um editor de texto, mostrando o final de um método de teste unitário em Java para exclusão de uma cerveja no serviço BeerService, incluindo chamadas ao serviço e verificações com Mockito.

Este conteúdo visual corresponde ao segmento inicial da aula onde o instrutor discute a execução de testes unitários para métodos como criação, busca por nome, listagem e exclusão, enfatizando a importância de rodar todos os testes incrementais.

Nesta seção da aula, estamos vendo a implementação de testes unitários para o serviço de gerenciamento de cervejas. O teste verifica se o método de exclusão é chamado corretamente, simulando a interação com o repositório e confirmando que as operações de busca e deleção ocorrem exatamente uma vez, garantindo que a lógica de negócio funcione sem erros reais no banco de dados.

```java
beerService.deleteById(expectedDeletedBeerDTO.getId());
verify(beerRepository, times(wantedNumberOfInvocations: 1)).findById(expectedDeletedBeerDTO.getId());
verify(beerRepository, times(wantedNumberOfInvocations: 1)).deleteById(expectedDeletedBeerDTO.getId());
}
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-07-14h18m37s618.jpg" alt="" width="840">
</p>

A imagem apresenta código fonte em Java, iniciando um novo método de teste unitário para incrementar o estoque de uma cerveja, com seções de given para setup de objetos DTO e modelo.

Este visual alinha-se com o momento da aula em que o instrutor introduz a funcionalidade de incremento no estoque, contextualizando o máximo e a quantidade atual da cerveja, e começa a aplicar TDD para desenvolver o método.

Aqui, a aula avança para o desenvolvimento de funcionalidades de estoque usando TDD. O teste é estruturado em given-when-then: prepara os dados esperados para uma cerveja, simula o repositório, e define a quantidade a incrementar, preparando para validar se o estoque é atualizado corretamente sem exceder o limite máximo.

```java
@Test
void whenIncrementIsCalledThenIncrementBeerStock() throws BeerNotFoundException, BeerStockExceededException {
    //given
    BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
    Beer expectedBeer = beerMapper.toModel(expectedBeerDTO);
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-07-14h19m59s421.jpg" alt="" width="840">
</p>

A imagem mostra continuação do código de teste em Java, com a seção when configurando mocks para o repositório e calculando a quantidade esperada após incremento, seguida da chamada ao serviço e asserções no then.

O conteúdo relaciona-se ao ponto da aula onde o instrutor explica o uso de TDD para o incremento, descrevendo como configurar mocks, executar o incremento e validar que a quantidade atualizada é correta e inferior ao máximo.

Nesta etapa, focamos na simulação de comportamentos do repositório com Mockito e nas asserções com AssertJ. Isso garante que o incremento adicione a quantidade especificada ao estoque existente, mantendo a integridade dos limites de estoque definidos para cada cerveja.

```java
    //when
    when(beerRepository.findById(expectedBeerDTO.getId())).thenReturn(Optional.of(expectedBeer));
    when(beerRepository.save(expectedBeer)).thenReturn(expectedBeer);

    int quantityToIncrement = 10;
    int expectedQuantityAfterIncrement = expectedBeerDTO.getQuantity() + quantityToIncrement;

    //then
    BeerDTO incrementedBeerDTO = beerService.increment(expectedBeerDTO.getId(), quantityToIncrement);

    assertThat(expectedQuantityAfterIncrement, equalTo(incrementedBeerDTO.getQuantity()));
    assertThat(expectedQuantityAfterIncrement, lessThan(expectedBeerDTO.getMax()));
}
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-07-14h20m10s665.jpg" alt="" width="840">
</p>

A imagem exibe um método de teste comentado em Java para o caso em que o incremento excede o estoque máximo, com setup inicial de objetos DTO e modelo.

Este visual corresponde à menção na aula sobre o tratamento de exceções quando o incremento ultrapassa o máximo, embora o instrutor note que deixará alguns testes comentados para foco no incremento básico.

A aula destaca a importância de testar cenários de erro no TDD, como quando adicionar mais itens ao estoque violaria o limite máximo definido, preparando para lançar uma exceção apropriada e garantindo robustez no serviço.

```java
// @Test
void whenIncrementIsGreaterThanMaxThenThrowException() {
    BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
    Beer expectedBeer = beerMapper.toModel(expectedBeerDTO);
    // 
}
```      

### 🟩 Vídeo 20 - Testando os métodos das classes BeerService e BeerController - parte 13

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.07-curso.05-video_20.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvimento-de-testes-unitarios-para-validar-uma-api-rest-de-gerenciamento-estoques-de-cerveja/learning/f5ade97b-f9b9-4fc2-8388-3fbdac1b14f6

Este vídeo tutorial foca na implementação da lógica de negócio para incrementar o estoque de cervejas em uma aplicação Java com Spring Boot. O desenvolvedor demonstra o ciclo de codificação, desde a busca no repositório até a validação de regras de negócio complexas, como o limite máximo de estoque, utilizando testes unitários para guiar o desenvolvimento.

### Anotações

#### 

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-07-15h00m39s151.jpg" alt="" width="840">
</p>

Nesta imagem, é apresentado um trecho de código Java de um serviço relacionado a gerenciamento de estoque de cervejas. O método mostrado é `deleteById`, que verifica a existência de uma cerveja pelo ID antes de deletá-la, lançando uma exceção se não encontrada. Em seguida, há métodos privados para verificação de registro duplicado por nome e existência por ID. Por fim, inicia o método `increment`, que busca uma cerveja pelo ID, incrementa sua quantidade e salva as alterações, retornando um DTO atualizado ou lançando exceção se não encontrada.

```java
public void deleteById(Long id) throws BeerNotFoundException {
    verifyIfExists(id);
    beerRepository.deleteById(id);
}

private void verifyIfIsAlreadyRegistered(String name) throws BeerAlreadyRegisteredException {
    Optional<Beer> optSavedBeer = beerRepository.findByName(name);
    if (optSavedBeer.isPresent()) {
        throw new BeerAlreadyRegisteredException(name);
    }
}

private Beer verifyIfExists(Long id) throws BeerNotFoundException {
    return beerRepository.findById(id)
            .orElseThrow(() -> new BeerNotFoundException(id));
}

public BeerDTO increment(Long id, int quantityToIncrement) throws BeerNotFoundException {
    Optional<Beer> optBeer = beerRepository.findById(id);
    if (optBeer.isPresent()) {
        Beer beerToIncrementStock = optBeer.get();
        beerToIncrementStock.setQuantity(beerToIncrementStock.getQuantity() + quantityToIncrement);
        Beer incrementedBeerStock = beerRepository.save(beerToIncrementStock);
        return beerMapper.toDTO(incrementedBeerStock);
    }
    throw new BeerNotFoundException(id);
}
```

Este código ilustra a implementação inicial de incremento no estoque, focando na busca e atualização da entidade, sem validações adicionais de limite máximo ainda.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-07-15h00m56s853.jpg" alt="" width="840">
</p>

Aqui, a imagem exibe um builder para um objeto de teste relacionado a BeerDTO, definindo valores padrão para atributos como nome, marca, máximo e quantidade de estoque, e tipo de cerveja. Inclui um método para converter o builder em um BeerDTO.

```java
@Builder.Default
private String name = "Brahma";
@Builder.Default
private String brand = "Ambev";
@Builder.Default
private int max = 50;
@Builder.Default
private int quantity = 10;
@Builder.Default
private BeerType type = BeerType.LAGER;

public BeerDTO toBeerDTO() {
    return new BeerDTO(id, name, brand, max, quantity, type);
}
```

Este trecho é usado para criar instâncias padronizadas em testes, facilitando a configuração de cenários iniciais para verificações de incremento no estoque.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-07-15h00m59s909.jpg" alt="" width="840">
</p>

A imagem mostra testes unitários para o método de incremento no serviço de cervejas. O primeiro teste verifica se o incremento ocorre corretamente sem exceder o máximo, usando mocks para o repositório. O segundo teste verifica o lançamento de exceção quando o incremento excede o máximo.

```java
void whenIncrementIsCalledThenIncrementBeerStock() throws BeerNotFoundException, BeerStockExceededException {
    //given
    BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
    Beer expectedBeer = beerMapper.toModel(expectedBeerDTO);

    //when
    when(beerRepository.findById(expectedBeerDTO.getId())).thenReturn(Optional.of(expectedBeer));
    when(beerRepository.save(expectedBeer)).thenReturn(expectedBeer);

    int quantityToIncrement = 45;
    int expectedQuantityAfterIncrement = expectedBeerDTO.getQuantity() + quantityToIncrement;

    // then
    BeerDTO incrementedBeerDTO = beerService.increment(expectedBeerDTO.getId(), quantityToIncrement);

    assertThat(expectedQuantityAfterIncrement, equalTo(incrementedBeerDTO.getQuantity()));
    assertThat(expectedQuantityAfterIncrement, lessThan(expectedBeerDTO.getMax()));
}

@Test
void whenIncrementIsGreaterThanMaxThenThrowException() {
    BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
    Beer expectedBeer = beerMapper.toModel(expectedBeerDTO);

    when(beerRepository.findById(expectedBeerDTO.getId())).thenReturn(Optional.of(expectedBeer));

    int quantityToIncrement = 80;
    assertThrows(BeerStockExceededException.class, () -> beerService.increment(expectedBeerDTO.getId(), quantityToIncrement));
}
```

Esses testes garantem que o incremento funcione dentro dos limites e falhe apropriadamente quando excedido, validando a lógica de negócios.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-07-15h01m59s125.jpg" alt="" width="840">
</p>

Esta imagem apresenta métodos privados de verificação em um serviço de cervejas, semelhantes aos vistos anteriormente, e uma versão atualizada do método `increment` que inclui uma verificação para não exceder o máximo antes de salvar.

```java
private void verifyIfIsAlreadyRegistered(String name) throws BeerAlreadyRegisteredException {
    Optional<Beer> optSavedBeer = beerRepository.findByName(name);
    if (optSavedBeer.isPresent()) {
        throw new BeerAlreadyRegisteredException(name);
    }
}

private Beer verifyIfExists(Long id) throws BeerNotFoundException {
    return beerRepository.findById(id)
            .orElseThrow(() -> new BeerNotFoundException(id));
}

public BeerDTO increment(Long id, int quantityToIncrement) throws BeerNotFoundException {
    Beer beerToIncrementStock = verifyIfExists(id);
    if (quantityToIncrement <= beerToIncrementStock.getMax()) {
        beerToIncrementStock.setQuantity(beerToIncrementStock.getQuantity() + quantityToIncrement);
        Beer incrementedBeerStock = beerRepository.save(beerToIncrementStock);
        return beerMapper.toDTO(incrementedBeerStock);
    }
    throw new BeerStockExceededException(id, quantityToIncrement);
}
```

O foco está na refatoração para reutilizar verificações existentes e adicionar a condição de limite máximo, prevenindo estoques inválidos.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-07-15h02m22s511.jpg" alt="" width="840">
</p>

Aqui, é mostrado um teste unitário para o caso em que o incremento excede o máximo, usando mocks e verificando o lançamento de `BeerStockExceededException`. Há também o início de outro teste para ID inválido.

```java
@Test
void whenIncrementIsGreaterThanMaxThenThrowException() {
    BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
    Beer expectedBeer = beerMapper.toModel(expectedBeerDTO);

    when(beerRepository.findById(expectedBeerDTO.getId())).thenReturn(Optional.of(expectedBeer));

    int quantityToIncrement = 10;
    assertThrows(BeerStockExceededException.class, () -> beerService.increment(expectedBeerDTO.getId(), quantityToIncrement));
}

@Test
void whenIncrementIsCalledWithInvalidIdThenThrowException() {
    int quantityToIncrement = 10;
```

Este teste reforça a validação de limites no incremento, garantindo que exceções sejam tratadas corretamente em cenários de falha.


### 🟩 Vídeo 21 - Testando os métodos das classes BeerService e BeerController - parte 14

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.07-curso.05-video_21.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvimento-de-testes-unitarios-para-validar-uma-api-rest-de-gerenciamento-estoques-de-cerveja/learning/bb555b56-cca1-498c-8e3e-990edc5f8df4

Este vídeo demonstra o processo prático de refatoração de uma regra de negócio e a implementação de um endpoint REST para incrementar o estoque de cervejas. O desenvolvedor utiliza a metodologia TDD (Test-Driven Development) para garantir que o sistema se comporte corretamente, especialmente em cenários de erro, como exceder o limite máximo de estoque.

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-07-15h20m54s185.jpg" alt="" width="840">
</p>

Nesta seção da aula, o instrutor demonstra o código do método `increment` em uma classe de serviço para gerenciamento de estoque de cervejas. O método verifica se a adição da quantidade especificada ao estoque atual não excede o limite máximo definido para o item. Caso exceda, uma exceção é lançada para prevenir a atualização inválida. Isso ilustra uma regra de negócio essencial para manter a integridade do estoque.

```java
public BeerDTO increment(Long id, int quantityToIncrement) throws BeerNotFoundException, BeerStockExceededException { 
    Beer beerToIncrementStock = verifyIfExists(id); 
    int quantityAfterIncrement = quantityToIncrement + beerToIncrementStock.getQuantity(); 
    if (quantityAfterIncrement <= beerToIncrementStock.getMax()) { 
        beerToIncrementStock.setQuantity(beerToIncrementStock.getQuantity() + quantityToIncrement); 
        Beer incrementedBeerStock = beerRepository.save(beerToIncrementStock); 
        return beerMapper.toDTO(incrementedBeerStock); 
    } 
    throw new BeerStockExceededException(id, quantityToIncrement); 
} 
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-07-15h21m48s304.jpg" alt="" width="840">
</p>

Nesta parte da aula, são exibidos testes unitários para o método `increment`. O primeiro teste valida o cenário em que a soma da quantidade a incrementar com o estoque atual excede o máximo, lançando uma `BeerStockExceededException`. Os testes subsequentes verificam o comportamento quando um ID inválido é fornecido, resultando em uma `BeerNotFoundException`. Esses testes garantem que o método lide corretamente com condições de erro.

```java
BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO(); 
Beer expectedBeer = beerMapper.toModel(expectedBeerDTO); 
when(beerRepository.findById(expectedBeerDTO.getId())).thenReturn(Optional.of(expectedBeer)); 
int quantityToIncrement = 80; 
assertThrows(BeerStockExceededException.class, () -> beerService.increment(expectedBeerDTO.getId(), quantityToIncrement)); 
} 
@Test void whenIncrementAfterSumIsGreaterThanMaxThenThrowException() { 
    BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO(); 
    Beer expectedBeer = beerMapper.toModel(expectedBeerDTO); 
    when(beerRepository.findById(INVALID_BEER_ID)).thenReturn(Optional.empty()); 
    assertThrows(BeerNotFoundException.class, () -> beerService.increment(INVALID_BEER_ID, quantityToIncrement)); 
} 
@Test void whenIncrementIsCalledWithInvalidIdThenThrowException() { 
    int quantityToIncrement = 10; 
    when(beerRepository.findById(INVALID_BEER_ID)).thenReturn(Optional.empty()); 
    assertThrows(BeerNotFoundException.class, () -> beerService.increment(INVALID_BEER_ID, quantityToIncrement)); 
} 
```

### 🟩 Vídeo 22 - Finalizando o curso e explicando os testes comentados no GitHub

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_tqi_fullstack-modulo.07-curso.05-video_22.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvimento-de-testes-unitarios-para-validar-uma-api-rest-de-gerenciamento-estoques-de-cerveja/learning/b68c33c8-41b0-4bed-b3bd-55fbad8b7518

Fase final da implementação de uma funcionalidade de incremento de estoque em uma API de gerenciamento de cervejas, utilizando TDD (Test-Driven Development). O apresentador foca na validação prática das regras de negócio e na organização do código via Git/GitHub.

### Anotações

#### 

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-07-15h42m00s979.jpg" alt="" width="840">
</p>

A imagem apresenta código fonte em Java, especificamente métodos de teste unitário para validar o comportamento de um endpoint PATCH em uma API de gerenciamento de cervejas. O código simula cenários onde o incremento no estoque excede o limite máximo, lançando uma exceção e retornando um status HTTP de bad request. Há também um teste comentado para o caso de ID inválido resultando em not found.

No contexto da aula sobre desenvolvimento orientado a testes (TDD), esta seção demonstra como criar testes para garantir que as regras de negócio sejam respeitadas, focando na integração com o serviço e verificação de respostas HTTP usando MockMvc. O professor ajusta o código para simular a exceção BeerStockExceededException, enfatizando a importância de testes para manutenção e funcionalidade robusta.

```java
@Test
void whenPATCHIsCalledToIncrementGreaterThanMaxThenBadRequestStatusIsReturned() throws Exception {
    QuantityDTO quantityDTO = QuantityDTO.builder()
        .quantity(30)
        .build();
    BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
    beerDTO.setQuantity(beerDTO.getQuantity() + quantityDTO.getQuantity());
    when(beerService.increment(VALID_BEER_ID, quantityDTO.getQuantity())).thenThrow(BeerStockExceededException.class);
    mockMvc.perform.patch(urlTemplate: BEER_API_URL_PATH + "/" + VALID_BEER_ID + BEER_API_SUBPATH_INCREMENT_URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(quantityDTO))
        .andExpect(status().isBadRequest());
}

@Test // void whenPATCHIsCalledWithInvalidBeerIdToIncrementThenNotFoundStatusIsReturned() throws Exception { 
// QuantityDTO quantityDTO = QuantityDTO.builder() 
// .quantity(10) 
// .build(); 
// BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO(); 
// beerDTO.setQuantity(beerDTO.getQuantity() + quantityDTO.getQuantity()); 
// when(beerService.increment(VALID_BEER_ID, quantityDTO.getQuantity())).thenThrow(BeerStockExceededException.class); 
// mockMvc.perform.patch(urlTemplate: BEER_API_URL_PATH + "/" + VALID_BEER_ID + BEER_API_SUBPATH_INCREMENT_URL) 
// .contentType(MediaType.APPLICATION_JSON) 
// .content(asJsonString(quantityDTO)) 
// .andExpect(status().isNotFound()); 
} 
```


### 🔴 23 - Objetivo do projeto

link do descritivo: https://web.dio.me/lab/desenvolvimento-de-testes-unitarios-para-validar-uma-api-rest-de-gerenciamento-estoques-de-cerveja/learning/df366920-d30b-482c-9263-df3eab2c8691


# Descrição do Desafio

Neste Labs você irá aprender a testar, unitariamente, uma API REST para o gerenciamento de estoques de cerveja. Vamos construir testes unitários para validar o nosso sistema de gerenciamento de estoques de cerveja desenvolvido em Spring Boot, e também apresentar os principais conceitos e vantagens de criar testes unitários com JUnit e Mockito. Além disso, vamos também mostrar como desenvolver funcionalidades da nossa API através da prática do TDD.

Neste desafio, você irá aprender ao mesmo tempo que desenvolve algo prático para o seu portfólio! Sendo assim, as seguintes tarefas serão realizadas:

- Entender sobre testes unitários;
- Entender os principais frameworks para testes unitários;
- Desenvolver testes unitários para validação de funcionalidades básicas: criação, listagem, consulta por nome e exclusão;
- TDD: apresentação e exemplo prático com 2 funcionalidades importantes.

**Dica:** você pode dar um "fork" no repositório para organizar melhor as suas alterações e evoluções, mantendo uma referência direta ao código original.

## Links Úteis

- [Repositório no GitHub](https://github.com/rpeleias-v1/beer_api_digital_innovation_one) (implementação de referência)


# Certificado: 

- Link na plataforma: 
- Certificado em pdf: