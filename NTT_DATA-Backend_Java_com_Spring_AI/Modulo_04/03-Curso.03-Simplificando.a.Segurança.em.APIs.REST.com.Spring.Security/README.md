## Instrutor

- Thiago Poiani (Principal Engineer at Skip)
- Contato Linkedin: / [thpoiani](https://www.linkedin.com/in/thpoiani/)

## Parte 1 - Introdução ao simplificando a segurança

### 🟩 Vídeo 01 - Introdução ao simplificando a segurança

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.02-video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/simplificando-a-seguranca-em-apis-rest-com-spring-security/learning/0f9aeabb-1614-435a-8808-c250a854e956?autoplay=1

### Anotações
     
#### Simplificando a Segurança em APIs REST com Spring Security

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-08h37m31s111.jpg" alt="" width="840">
</p>

Slide de abertura do curso "Jornada Tech", apresentando o tema central — segurança em APIs REST com Spring Security — e a agenda completa do módulo em nove etapas, desde a introdução aos fundamentos até a construção de um caso prático com segurança escalável.

#### Agenda do Curso

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-08h37m36s335.jpg" alt="" width="840">
</p>

O instrutor apresenta a estrutura da aula: primeiro os fundamentos e conceitos de segurança (autenticação e autorização), em seguida um estudo de caso prático que será desenvolvido ao longo do curso, e por fim um roadmap com sugestões de próximos passos para o aluno.

#### A Grande Divisão: Comprovação vs. Permissão

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-08h37m38s881.jpg" alt="" width="840">
</p>

Este slide estabelece a diferença fundamental entre os dois pilares da segurança. Autenticação (AuthN) responde à pergunta "você é quem diz ser?" — é o ato de verificação da identidade. Autorização (AuthO) responde a "você pode acessar este recurso?" — é o ato de conceder ou negar permissão para uma ação ou acesso específico, já assumindo que a identidade foi confirmada.

#### Elevando a confiança na Autenticação (AuthN)

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-08h37m44s217.jpg" alt="" width="840">
</p>

O diagrama de três círculos mostra os fatores que compõem a autenticação moderna: algo que você sabe (senhas, PINs), algo que você possui (token físico, SMS, app) e algo que você é (biometria facial ou digital). Sistemas que combinam múltiplos fatores — o conceito de autenticação multifator (MFA) — atingem o ponto de confiança máxima, representado pela interseção dos três círculos.

#### Matriz de Protocolos e Métodos de Autenticação

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-08h37m48s457.jpg" alt="" width="840">
</p>

A tabela resume os principais métodos de autenticação usados hoje. O Basic Authentication é simples e legado, enviando usuário e senha a cada requisição, por isso pouco seguro e pouco escalável. O Bearer/JWT (JSON Web Token) carrega as informações do usuário já autenticado dentro de um token criptografado, tornando a aplicação stateless — sem necessidade de controlar sessões — e é o padrão mais usado em comunicação entre microsserviços. O OAuth 2.0 é voltado a acesso delegado, como o login via redes sociais em aplicações de terceiros. O OpenID Connect adiciona uma camada de identidade sobre o OAuth, viabilizando Single Sign-On (SSO). Por fim, o mTLS (Mutual TLS) usa certificados mútuos entre dois serviços, sendo ideal para comunicação service-to-service.

#### Delegação de complexidade via Identity Providers

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-08h37m57s995.jpg" alt="" width="840">
</p>

A mentalidade "Production-Ready" recomenda não implementar o gerenciamento de credenciais dentro da própria aplicação. Em vez disso, esse gerenciamento é centralizado em um Identity Provider especializado — como Keycloak, Auth0 ou Okta — que entrega um token confiável (Trusted Token) para a API. O Keycloak é a opção open source recomendada; Auth0 e Okta são alternativas pagas. Delegar essa responsabilidade evita o esforço de manter uma camada inteira de segurança (senhas, reset, integrações externas) sempre atualizada dentro do próprio sistema.

#### Defense in Depth: A mentalidade Production-Ready

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-08h38m03s297.jpg" alt="" width="840">
</p>

O conceito de Defense in Depth (defesa em profundidade) parte da ideia de que a segurança real não deve depender de um único ponto de falha, mas sim de barreiras concêntricas: se uma camada falhar, a próxima mitiga o ataque. Na camada de Rede, isso envolve firewall, WAF e mTLS. Na camada de Aplicação, o uso de Spring Security, validação de JWT e validações de entrada. Na camada de Banco de Dados, criptografia dos dados e mecanismos como Row-Level Security, que restringem o acesso a registros conforme o usuário.

#### O espectro da Autorização (AuthO)

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-08h38m08s424.jpg" alt="" width="840">
</p>

A ilustração da lente representa o espectro de granularidade da autorização. No extremo coarse-grained (grão grosso), as permissões são amplas — por exemplo, acesso liberado a toda a aplicação com base em uma role simples, como admin ou user. No extremo fine-grained (grão fino), as permissões são muito mais específicas, podendo restringir o acesso a uma ação sobre um único registro, como editar apenas um campo de um item específico.

#### Modelos de Decisão: Estrutura vs. Contexto

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-08h38m12s300.jpg" alt="" width="840">
</p>

Dois modelos de controle de acesso são comparados. O RBAC (Role-Based Access Control) é focado em cargos e papéis, é rígido e de implementação rápida — o exemplo clássico é a distinção entre roles como admin e user, organizadas hierarquicamente. Já o ABAC (Attribute-Based Access Control) é mais flexível e complexo, avaliando atributos dinâmicos do usuário, do recurso e do contexto (como horário ou localização) para decidir, em tempo real, se o acesso deve ser permitido ou negado.

#### A orquestração da decisão: O modelo NIST

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-08h38m15s268.jpg" alt="" width="840">
</p>

O diagrama apresenta o modelo de autorização definido pelo NIST (National Institute of Standards and Technology), organizado em quatro componentes principais. O PEP (Policy Enforcement Point) é a "catraca" — o ponto central que recebe a requisição e aplica a autorização; no Spring Security, esse papel é exercido pela filter chain, e em arquiteturas maiores pode ser um API Gateway ou o próprio Keycloak. O PAP (Policy Administration Point) define as regras de negócio, como roles, contexto e a forma como as políticas se aplicam. O PIP (Policy Information Point) recupera os dados de contexto do usuário e da requisição. Por fim, o PDP (Policy Decision Point) — o "cérebro" — reúne todas essas informações, avalia as políticas relacionadas ao endpoint e decide se o usuário pode prosseguir ou é bloqueado. No Spring Security esses componentes já vêm integrados; ao usar o Keycloak, os recursos completos do modelo só entram em ação se o Keycloak Authorization Services for habilitado — caso contrário, o Keycloak cobre principalmente a autenticação.

#### A anatomia completa de uma requisição segura

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-08h38m26s855.jpg" alt="" width="840">
</p>

Este diagrama resume o fluxo completo de uma requisição segura: a identidade comprova quem o usuário é (AuthN) por meio de uma credencial como o JWT; o Gateway, atuando como PEP, verifica as regras junto ao PDP; e, uma vez validado, o acesso é concedido de forma cirúrgica através das camadas de defesa — rede, aplicação e banco de dados — até alcançar o recurso solicitado. É a junção prática de tudo o que foi discutido sobre autenticação, autorização e defesa em profundidade.

#### Influencer & Brand Connect: O Modelo de Segurança

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-08h38m30s698.jpg" alt="" width="840">
</p>

Apresentação do estudo de caso que será desenvolvido no curso: uma plataforma de gerenciamento de propostas entre influenciadores digitais e marcas. O influenciador pode criar propostas de mídia, mas não tem acesso às propostas de outros influenciadores — só pode ver as suas próprias. A marca, por sua vez, pode visualizar as propostas públicas disponíveis para contratação, mas não pode criar propostas como um influenciador faria. Esse controle de acesso é implementado com RBAC (papéis de Influenciador e Marca), e a tabela de permissões deixa claro quem pode criar, ver propostas públicas ou ver a própria proposta. O uso de UUIDs e Java Records é indicado como forma de garantir integridade e rastreabilidade dos contratos gerados.


### 🟩 Vídeo 02 - Primeiros Passos com Spring Security

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.02-video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/simplificando-a-seguranca-em-apis-rest-com-spring-security/learning/acb58be5-1969-47f8-b340-2b1eca9e74f4?autoplay=1

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-09h18m00s769.jpg" alt="" width="840">
</p>

Slide de abertura da aula, parte da trilha **Jornada Tech**. O tema apresentado é *"Simplificando a Segurança em APIs REST com Spring Security"*, com o sumário do módulo em destaque no item 02, **"Primeiros Passos com Spring Security"** — que é justamente o assunto tratado nesta aula.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-09h21m12s480.jpg" alt="" width="840">
</p>

Tela de criação de um novo projeto no IntelliJ IDEA usando o gerador **Spring Boot** (start.spring.io). O projeto recebe o nome `proposal-management`, grupo `dio`, pacote `dio.proposalmanagement`, build **Gradle - Groovy** e a versão de linguagem definida é **Java 25** (via Eclipse Temurin, gerenciado pelo SDKMAN).

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-09h33m16s810.jpg" alt="" width="840">
</p>

Arquivo `build.gradle` do projeto recém-criado, já configurado com o toolchain para Java 25. Nesse momento são adicionadas as duas primeiras dependências do projeto:

```groovy
implementation 'org.springframework.boot:spring-boot-starter-security'
implementation 'org.springframework.boot:spring-boot-starter-web'
```

O *Spring Boot Starter Security* traz o Spring Security para o projeto, e o *Starter Web* permite observar como a segurança se comporta em uma aplicação web.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-10h09m22s685.jpg" alt="" width="840">
</p>

Criação do primeiro pacote do projeto, chamado `auth` (dentro de `dio.proposalmanagement`), reunindo os conceitos de autenticação e autorização em um único módulo.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-10h10m42s449.jpg" alt="" width="840">
</p>

Dentro do pacote `auth`, é criado o subpacote `application`, seguindo a organização em camadas do Domain Driven Design. A camada de aplicação é responsável por orquestrar as regras de negócio, chamando services e repositories.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-10h12m02s268.jpg" alt="" width="840">
</p>

Em seguida, é criado o subpacote `domain`, que representa a camada mais interna do módulo — onde ficam as regras de negócio, sem dependência de frameworks externos.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-10h12m30s548.jpg" alt="" width="840">
</p>

Por fim, é criado o subpacote `infrastructure`, responsável por tudo que é externo ao domínio: controllers, configurações de autenticação, acesso a banco de dados, entre outros. Com isso, o módulo `auth` fica estruturado nas três camadas: `application`, `domain` e `infrastructure`.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-10h14m56s261.jpg" alt="" width="840">
</p>

Criação da primeira classe de configuração do módulo, chamada **SecurityConfig**, que ficará responsável por centralizar as configurações do Spring Security.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-10h25m06s120.jpg" alt="" width="840">
</p>

A classe `SecurityConfig` recém-criada é anotada com `@Configuration`, anotação do Spring que sinaliza que essa classe deve ser processada e inicializada durante o `component scan`, o mesmo mecanismo usado por `@SpringBootApplication` para descobrir classes de configuração.

```java
package dio.proposalmanagement.auth.infrastructure;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {
}
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-10h26m30s538.jpg" alt="" width="840">
</p>

É adicionada a anotação `@EnableWebSecurity`, que indica explicitamente à aplicação Spring que o Spring Security deve ser utilizado.

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
}
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-10h30m20s495.jpg" alt="" width="840">
</p>

Início da criação do bean `SecurityFilterChain`, o ponto central de configuração de segurança do Spring Security — é ele quem define por quais filtros as requisições HTTP vão passar.

```java
@Bean
SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

}
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-10h38m42s749.jpg" alt="" width="840">
</p>

O corpo do bean `SecurityFilterChain` é completado, definindo que qualquer requisição precisa estar autenticada e habilitando a autenticação via formulário (`form login`) com as configurações padrão:

```java
@Bean
SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth
            .anyRequest().authenticated())
        .formLogin(Customizer.withDefaults());

    return http.build();
}
```

Esse formulário padrão é a forma mais simples que o Spring Security oferece para coletar usuário e senha antes de conceder acesso.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-10h40m49s693.jpg" alt="" width="840">
</p>

Para validar usuário e senha, é criado um bean `UserDetailsService`, responsável por fornecer os dados de autenticação para o `AuthenticationProvider`. O primeiro usuário é criado com `User.withDefaultPasswordEncoder()`, deixando o Spring escolher automaticamente o algoritmo de criptografia (nesse caso, BCrypt) para a senha:

```java
@Bean
UserDetailsService userDetailsService() {
    UserDetails user = User.withDefaultPasswordEncoder()
}
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-10h41m51s816.jpg" alt="" width="840">
</p>

O próprio IntelliJ sinaliza que o método `withDefaultPasswordEncoder()` está **deprecated (obsoleto)**, alertando que essa abordagem não é segura para aplicações em produção — ela é indicada apenas para fins de exemplo, já que a senha em texto puro acaba compilada junto ao código-fonte antes de ser criptografada em memória.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-10h44m14s263.jpg" alt="" width="840">
</p>

O primeiro usuário em memória é finalizado: username `influencer`, senha `password` e role `INFLUENCER`. Esse usuário é então registrado em um `InMemoryUserDetailsManager`, retornado pelo bean `UserDetailsService`:

```java
@Bean
UserDetailsService userDetailsService() {
    UserDetails user = User.withDefaultPasswordEncoder()
            .username("influencer")
            .password("password")
            .roles("INFLUENCER")
            .build();

    return new InMemoryUserDetailsManager(user);
}
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-12h37m47s672.jpg" alt="" width="840">
</p>

Console do IntelliJ mostrando a aplicação Spring Boot iniciada com sucesso na porta 8080, confirmando que a configuração inicial do Spring Security está funcional.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-12h47m59s885.jpg" alt="" width="840">
</p>

Saída completa do terminal ao subir a aplicação: o banner ASCII do Spring Boot, seguido do log de inicialização. Destaca-se o aviso de que `User.withDefaultPasswordEncoder()` não é seguro para produção, e a listagem da cadeia padrão de filtros de segurança (`DefaultSecurityFilterChain`) que passa a proteger qualquer requisição feita à aplicação.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-13h31m50s021.jpg" alt="" width="840">
</p>

Acessando `localhost:8080`, a aplicação redireciona automaticamente para a tela de login padrão do Spring Security (`/login`). São informados usuário `influencer` e senha `password` para autenticação.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-13h32m18s254.jpg" alt="" width="840">
</p>

Após o login bem-sucedido, o Spring Security tenta redirecionar para a URL originalmente solicitada através do parâmetro `/continue`, mas como ainda não existe nenhum endpoint mapeado na aplicação, é exibida a **Whitelabel Error Page** com status `404 Not Found`. Isso confirma que a autenticação funcionou — falta apenas criar um endpoint para receber a requisição.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-13h37m47s130.jpg" alt="" width="840">
</p>

A classe `SecurityConfig` é evoluída: agora existem dois usuários em memória, `influencer` e `brand`, ambos com senha `password`, e um bean `PasswordEncoder` baseado em `BCryptPasswordEncoder` é declarado, preparando o terreno para substituir o encoder padrão (obsoleto) por um explícito:

```java
UserDetails brand = User.withUsername("brand")
        .password(passwordEncoder.encode("password"))
        .roles("BRAND")
        .build();

return new InMemoryUserDetailsManager(influencer, brand);
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-14h04m21s479.jpg" alt="" width="840">
</p>

Dentro de `infrastructure`, é criado o subpacote `http`, destinado a abrigar os controllers da aplicação.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-14h05m18s987.jpg" alt="" width="840">
</p>

Criação da primeira classe de controller do projeto, chamada simplesmente `Controller`, ainda sem um nome mais específico, já que o objetivo inicial é apenas validar o comportamento da autenticação.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-15h01m55s969.jpg" alt="" width="840">
</p>

O `Controller` é implementado com `@RestController` e `@RequestMapping` na raiz (`/`), expondo um endpoint `GET` que devolve a mensagem `"Hello World"`, servindo como primeiro teste de autenticação:

```java
@RestController
@RequestMapping
public class Controller {

    @GetMapping
    public String hello() {
        return "Hello World";
    }
}
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-15h02m49s951.jpg" alt="" width="840">
</p>

Ao acessar `localhost:8080` novamente, a tela de login é exibida uma vez mais, confirmando que qualquer endpoint da aplicação exige autenticação prévia. É informado o usuário `influencer` com a senha `password`.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-15h02m53s562.jpg" alt="" width="840">
</p>

Após autenticar como `influencer`, o navegador finalmente exibe o resultado do endpoint `GET /`: a mensagem **"Hello World"**, validando que o fluxo de autenticação via formulário está funcionando de ponta a ponta.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-15h05m36s172.jpg" alt="" width="840">
</p>

O endpoint do `Controller` é alterado para receber o usuário autenticado através da anotação `@AuthenticationPrincipal`, que retorna um `UserDetails` com as informações de quem fez login. A resposta passa a incluir o nome do usuário autenticado:

```java
@GetMapping
public String hello(@AuthenticationPrincipal UserDetails user) {
    return "Hello World " + user.getUsername();
}
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-15h06m13s895.jpg" alt="" width="840">
</p>

Após reiniciar a aplicação, o acesso a `localhost:8080` é feito novamente, dessa vez autenticando com o usuário `brand` e senha `password`.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-15h06m17s152.jpg" alt="" width="840">
</p>

Com o usuário `brand` autenticado, a resposta do endpoint passa a exibir **"Hello World brand"**, confirmando que o `@AuthenticationPrincipal` está corretamente retornando o nome de quem está logado.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-15h06m46s970.jpg" alt="" width="840">
</p>

É adicionada a anotação `@EnableMethodSecurity` à classe `SecurityConfig`, habilitando o uso de anotações de segurança diretamente em métodos — como em controllers, services ou repositories — permitindo restringir o acesso com base na role do usuário autenticado.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-15h09m48s946.jpg" alt="" width="840">
</p>

Dois novos endpoints são criados no `Controller`: `GET /influencer` e `GET /brand`. Como ainda não possuem nenhuma anotação de segurança específica, nesse momento qualquer usuário autenticado consegue acessar ambos, independentemente da sua role:

```java
@GetMapping("/influencer")
public String influenceEndpoint() {
    return "Hello INFLUENCER";
}

@GetMapping("/brand")
public String brandEndpoint() {
    return "Hello BRAND";
}
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-15h11m46s712.jpg" alt="" width="840">
</p>

Cada endpoint recebe a anotação `@PreAuthorize`, exigindo que o usuário autenticado possua a role correspondente para poder acessá-lo:

```java
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
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-15h13m10s267.jpg" alt="" width="840">
</p>

A aplicação é reiniciada e a autenticação é feita com o usuário `influencer` e senha `password`, para testar as novas regras de autorização por role.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-15h13m14s116.jpg" alt="" width="840">
</p>

Como o último endpoint acessado antes do login foi `/brand`, o Spring Security tenta redirecionar para lá após a autenticação. Só que o usuário logado é `influencer`, que não possui a role `BRAND` exigida pelo `@PreAuthorize` daquele endpoint — resultando em uma **Whitelabel Error Page** com status `403 Forbidden`.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-15h13m37s242.jpg" alt="" width="840">
</p>

Ao acessar diretamente `/influencer` estando autenticado como `influencer`, o acesso é permitido, já que o usuário possui a role exigida (`INFLUENCER`), e a resposta **"Hello INFLUENCER"** é exibida.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-15h13m49s395.jpg" alt="" width="840">
</p>

Em uma janela anônima, o teste é repetido autenticando como `brand`. Após o login, o redirecionamento para `/continue` retorna a mensagem do endpoint raiz, **"Hello World brand"**, confirmando novamente a autenticação do usuário `brand`.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-15h13m57s008.jpg" alt="" width="840">
</p>

Ainda autenticado como `brand`, ao tentar acessar `/influencer` — endpoint restrito à role `INFLUENCER` — a aplicação retorna a **Whitelabel Error Page** com status `403 Forbidden`, pois o usuário `brand` não possui a role necessária.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-22-15h14m01s231.jpg" alt="" width="840">
</p>

Por fim, o usuário `brand` acessa `/brand`, endpoint para o qual ele possui a role exigida (`BRAND`), recebendo como resposta **"Hello BRAND"**. Esse teste fecha a demonstração de autenticação via formulário combinada com autorização baseada em roles a nível de método, usando `@PreAuthorize`.


#### Material de Apoio Até Esta Etapa

- Arquivos do projeto nesta etapa: [./000-Midia_e_Anexos/proposal-managemnet_ate_o_video02.zip](./000-Midia_e_Anexos/etapas_do_codigo/proposal-managemnet_ate_o_video02.zip)
- [001-Tutorial_ProposalManagement_Spring_Security_Videos01a02.md](./001-Tutorial_ProposalManagement_Spring_Security_Videos01a02.md)


### 🟩 Vídeo 03 - Evoluindo a Autenticação

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.02-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/simplificando-a-seguranca-em-apis-rest-com-spring-security/learning/a32d0aa7-df06-4de2-9e2a-6f884af941d8?autoplay=1

### Anotações




### 🟩 Vídeo 04 - Evoluindo a Autenticação

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.02-video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/simplificando-a-seguranca-em-apis-rest-com-spring-security/learning/345dee1d-d60f-4224-ada1-14af162d5058?autoplay=1



### 🟩 Vídeo 05 - Segurança com Banco de Dados

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.02-video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/simplificando-a-seguranca-em-apis-rest-com-spring-security/learning/54d0c000-6178-4510-bf96-6124f45075b1?autoplay=1

### 🟩 Vídeo 06 - Segurança Baseada em Papéis

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.02-video_06.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 07 - Implementando o Use Case de Listagem

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.02-video_07.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 08 - Criando Entidades de Persistência

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.02-video_08.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 09 - Implementando o ProposalController

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.02-video_09.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 10 - Segurança Escalável

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.02-video_10.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### Tutoriais


### Arquivos do Projeto



# Certificado: Simplificando a Segurança em APIs REST com Spring Security

- Link na plataforma: 
- Certificado em pdf: