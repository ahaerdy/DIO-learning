# Tutorial de Estudos — Simplificando a Segurança em APIs REST com Spring Security

**De formulário para REST: autenticação via JSON e sessão por cookie — Vídeo 03**

- Curso: NTT Data — Jornada Tech (DIO) · Módulo 4 — Bootcamp Java + Spring + AI
- Curso: "Simplificando a Segurança em APIs REST com Spring Security"
- Instrutor: Thiago Poiani (Principal Engineer at Skip)
- Projeto: `proposal-managemnet` (pacote base `dio.proposalmanagement`)
- Documento de referência pessoal — nível iniciante em Java
- Continuação de: `001-Tutorial_ProposalManagement_Spring_Security_Videos01a02.md`

---

## Sobre este documento

Este é o **segundo tutorial** da série, cobrindo o **Vídeo 03 — "Evoluindo a Autenticação"**. Ele foi escrito a partir de três fontes conferidas diretamente: a transcrição do vídeo (`transcricao.md`), o README atualizado (cuja seção de anotações do Vídeo 03 ainda está em branco — por isso a narrativa abaixo se apoia na transcrição) e o código-fonte real do projeto na etapa correspondente (`proposal-managemnet_ate_o_video03.zip`), comparado bloco a bloco com o estado anterior (`proposal-managemnet_ate_o_video02.zip`, embutido dentro do novo `.zip`).

Segue exatamente o mesmo formato do tutorial anterior: bloco de código → explicação linha a linha, em nível iniciante → destaques de "porquê" → uma seção de "Pontos de atenção" comparando a narrativa da aula com o que está realmente no seu `.zip` → glossário (só termos novos) → checkpoint fiel do código → próximos passos atualizados.

---

## Parte 3 — Evoluindo a Autenticação: de formulário para REST (Vídeo 03)

### 3.1. O que muda neste vídeo

Até o Vídeo 02, a aplicação usava `formLogin` (a tela de login HTML pronta do Spring Security) e exigia autenticação para qualquer requisição. Isso funciona bem para aplicações que rodam dentro de um navegador, mas não é adequado para uma **API REST** consumida por um app mobile, um front-end em JavaScript ou outro serviço — nenhum desses clientes quer (ou consegue, de forma prática) preencher um formulário HTML.

O objetivo do Vídeo 03 é **substituir o formulário por uma rota de login que recebe e devolve JSON**, mantendo por baixo dos panos o mesmo mecanismo de filtro que o Spring Security já usa para o `formLogin`: o `UsernamePasswordAuthenticationFilter`. A ideia central da aula é: em vez de reinventar a autenticação do zero, **estender** essa classe e ajustar apenas os pontos que precisam mudar (de onde vêm os dados, e o que acontece depois de autenticar).

### 3.2. CSRF: o que é, e por que foi desabilitado

Antes de mexer em código, a aula explica um conceito que motiva uma das primeiras mudanças na configuração: **CSRF**.

> **CSRF (Cross-Site Request Forgery)** — em português, "falsificação de solicitação entre sites". É um ataque em que um site malicioso aproveita uma sessão já autenticada do usuário em **outro** site (o seu) para enviar, sem que o usuário perceba, uma requisição para esse outro site usando a sessão dele. Como o navegador anexa os cookies de sessão automaticamente em qualquer requisição para aquele domínio, o servidor, sem uma proteção extra, não teria como distinguir uma requisição legítima de uma forjada por outro site.

Para se defender disso, o Spring Security, por padrão, exige que **todo formulário** carregue um campo escondido (`hidden`) chamado *CSRF token* — um valor que muda a cada vez que o formulário é gerado. O servidor guarda esse valor associado à sessão e só aceita a requisição se o token recebido bater com o esperado. Se o token não vier (ou vier errado), a requisição é rejeitada.

Essa proteção é fundamental para aplicações que dependem de **navegador + cookie de sessão** (o cenário clássico de CSRF). Mas ela faz menos sentido, ou exige uma estratégia diferente, em outros tipos de cliente:

- Em **aplicativos mobile**, normalmente não há esse risco de "sessão compartilhada entre sites", então a proteção costuma ser desnecessária.
- Em **Single Page Applications (SPAs)** em JavaScript, o token geralmente é obtido no carregamento da página e reenviado manualmente em cada requisição — uma estratégia diferente da do formulário HTML tradicional.

Para simplificar o desenvolvimento e os testes desta etapa do curso, a proteção CSRF é desabilitada explicitamente na configuração (isso aparece no bloco de código da seção 3.10, dentro do bean `securityFilterChain`). Fica registrado, porém, que essa é uma decisão que **depende do tipo de cliente** que vai consumir a API — não é uma prática que deva ser copiada sem análise para qualquer projeto em produção.

### 3.3. Entendendo o `UsernamePasswordAuthenticationFilter` por baixo do `formLogin`

A aula relembra que o `formLogin`, usado até aqui, na verdade é implementado internamente através de um filtro chamado `UsernamePasswordAuthenticationFilter`. Um **filtro** (`Filter`), no mundo Java Web, é um componente que intercepta uma requisição HTTP **antes** dela chegar ao seu destino final (o Controller), podendo inspecioná-la, modificá-la, bloqueá-la ou até responder diretamente sem deixá-la seguir adiante. O Spring Security é construído como uma **cadeia de filtros** (a `Filter Chain` já mencionada no tutorial anterior), e cada filtro cuida de uma responsabilidade específica.

O comportamento do `UsernamePasswordAuthenticationFilter`, conforme a documentação consultada na aula, é:

1. Ele intercepta requisições `POST` para uma URL específica (por padrão, `/login`).
2. Recupera usuário e senha dos parâmetros da requisição (no caso do formulário, campos de formulário HTML).
3. Cria um **token** de autenticação — um objeto simples que carrega apenas usuário e senha, ainda não verificados.
4. Delega a verificação a um `AuthenticationManager`, que por sua vez consulta o `UserDetailsService` (o `AuthenticationProvider` mencionado no Vídeo 01) para confirmar se as credenciais são válidas.
5. Se a autenticação for bem-sucedida, cria a sessão, guarda o resultado no contexto de segurança e segue adiante (podendo, inclusive, habilitar um cookie de "remember me").
6. Se falhar, devolve um erro (por exemplo, `403`).

A decisão de projeto da aula é: em vez de usar esse filtro exatamente como ele é (recebendo dados de um formulário), criar uma **subclasse** dele — um novo filtro que reaproveita toda essa engrenagem, mas troca a etapa "de onde vêm usuário e senha" para ler um corpo `JSON`, em vez de campos de formulário.

### 3.4. Criando o pacote `security` e o esqueleto do filtro REST

A primeira mudança estrutural é a criação de um novo pacote, `security`, dentro de `auth.infrastructure`, para agrupar todas as classes relacionadas à configuração de segurança (até então, `SecurityConfig` vivia direto em `auth.infrastructure`). Dentro desse novo pacote, é criada a classe `RestUsernamePasswordAuthenticationFilter`, estendendo `UsernamePasswordAuthenticationFilter`:

```java
package dio.proposalmanagement.auth.infrastructure.security;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class RestUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
}
```

- **`package dio.proposalmanagement.auth.infrastructure.security;`** — o novo pacote lógico criado especificamente para reunir classes de configuração e componentes de segurança, separando essa responsabilidade da camada `http` (onde vive o `Controller`).
- **`public class RestUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter`** — a palavra-chave `extends` indica **herança**: esta nova classe é uma **especialização** de `UsernamePasswordAuthenticationFilter`, herdando todo o comportamento da classe original e podendo sobrescrever (*override*) apenas os métodos que precisam de um comportamento diferente. É o mecanismo de orientação a objetos que permite reaproveitar a engrenagem descrita na seção 3.3 sem reescrevê-la do zero.
- **`@Component`** — anotação do Spring (já vista de relance no glossário anterior, mas usada pela primeira vez aqui) que marca uma classe como um **componente genérico** gerenciado pelo container do Spring. Diferente de `@Configuration` (usada para classes que produzem `@Bean`s) ou `@RestController` (usada para controllers web), `@Component` é a anotação "guarda-chuva" para qualquer classe que deva ser descoberta pelo *component scan* e transformada em *bean* — usada aqui porque o filtro precisa ser instanciado e gerenciado pelo Spring para poder receber suas próprias dependências por injeção (o `ObjectMapper` da próxima seção).

### 3.5. O DTO `LoginRequest`: modelando o JSON recebido

Antes de implementar a lógica de leitura, é preciso decidir **qual formato de JSON** o filtro vai aceitar. A aula cria um **DTO** (*Data Transfer Object* — um objeto cujo único propósito é carregar dados entre camadas ou entre sistemas, sem lógica de negócio) para representar isso, usando um **`record`** do Java:

```java
public record LoginRequest(String username, String password) {}
```

- **`record`** — um tipo especial de classe, introduzido no Java moderno, pensado especificamente para modelar dados **imutáveis**. Ao escrever `record LoginRequest(String username, String password) {}`, o compilador Java gera automaticamente, nos bastidores: um construtor que recebe `username` e `password`; métodos de acesso `username()` e `password()` (repare que **não** têm o prefixo `get`, diferente do padrão de uma classe Java tradicional); e implementações prontas de `equals()`, `hashCode()` e `toString()`. A aula escolhe `record` em vez de uma `class` tradicional exatamente por esse motivo: menos código escrito à mão para representar algo que é, por natureza, só um pacotinho de dados que não deveria mudar depois de criado.
- **`{}`** — o corpo do record fica vazio porque não há nenhuma lógica adicional a acrescentar além do que o compilador já gera automaticamente.

No código final, este `record` aparece como uma classe **aninhada** (uma classe declarada dentro de outra) dentro do próprio `RestUsernamePasswordAuthenticationFilter` — isso é conferido no checkpoint mais adiante.

### 3.6. Injetando o `ObjectMapper` e customizando a URL do filtro

Para transformar o JSON recebido (texto) em um objeto Java (`LoginRequest`), a aula introduz o `ObjectMapper`, e aproveita o construtor da nova classe para customizar outros dois comportamentos herdados de `UsernamePasswordAuthenticationFilter`:

```java
public RestUsernamePasswordAuthenticationFilter(AuthenticationConfiguration authenticationConfiguration, ObjectMapper objectMapper) throws Exception {
    super(authenticationConfiguration.getAuthenticationManager());
    this.objectMapper = objectMapper;
    setFilterProcessesUrl("/api/auth/login");
    setAuthenticationSuccessHandler((request, response, authentication) ->
            response.setStatus(HttpServletResponse.SC_OK));
}
```

- **`ObjectMapper`** — classe da biblioteca **Jackson** (incluída automaticamente no classpath pelo `spring-boot-starter-web`, que já traz suporte a JSON), responsável por converter objetos Java em JSON (*serialização*) e JSON em objetos Java (*desserialização*). É a peça que faltava para o filtro entender o corpo da requisição.
- **`public RestUsernamePasswordAuthenticationFilter(AuthenticationConfiguration authenticationConfiguration, ObjectMapper objectMapper) throws Exception`** — o construtor da classe recebe dois parâmetros. Como a classe está anotada com `@Component`, o Spring identifica automaticamente esse construtor e faz **injeção de dependência via construtor**: ele mesmo busca, no container de *beans*, um `ObjectMapper` (já disponibilizado automaticamente pelo Spring Boot Web) e um `AuthenticationConfiguration` (disponibilizado automaticamente pelo Spring Security) e os passa para cá, sem que seja preciso escrever `new RestUsernamePasswordAuthenticationFilter(...)` manualmente em nenhum lugar do código.
- **`AuthenticationConfiguration`** — uma classe utilitária do Spring Security cujo papel é dar acesso ao **`AuthenticationManager` padrão** que o Spring já monta automaticamente a partir do `UserDetailsService` e do `PasswordEncoder` configurados. Ela é explicada com mais detalhe na seção 3.9, no momento exato em que a aula percebe que precisa dela.
- **`super(authenticationConfiguration.getAuthenticationManager());`** — a palavra-chave `super(...)` chama o **construtor da classe pai** (`UsernamePasswordAuthenticationFilter`), passando para ele o `AuthenticationManager` obtido a partir do `AuthenticationConfiguration`. Isso resolve, desde o início do construtor, a pergunta "qual `AuthenticationManager` este filtro deve usar" — evitando o erro que a aula encontra e corrige ao vivo (detalhado na seção 3.9).
- **`this.objectMapper = objectMapper;`** — guarda a referência recebida por injeção de dependência em um campo (*field*) da própria classe, para que outros métodos (como o `attemptAuthentication`, visto na próxima seção) possam usá-la depois.
- **`setFilterProcessesUrl("/api/auth/login");`** — método herdado de `UsernamePasswordAuthenticationFilter` que define **em qual URL** este filtro deve interceptar requisições `POST`. Por padrão, essa URL seria `/login` (a mesma usada pelo `formLogin`); aqui ela é trocada para `/api/auth/login`, deixando claro, pela própria URL, que se trata de uma rota de API, não de uma página HTML.
- **`setAuthenticationSuccessHandler((request, response, authentication) -> response.setStatus(HttpServletResponse.SC_OK));`** — também um método herdado, que define **o que fazer quando a autenticação dá certo**. O valor passado é uma expressão lambda que implementa a interface funcional `AuthenticationSuccessHandler`: ela recebe a requisição, a resposta e o objeto `Authentication` já autenticado, e aqui apenas define o código de status HTTP da resposta como `200 OK` (`HttpServletResponse.SC_OK` é uma constante que vale `200`). Essa customização é necessária porque o comportamento padrão herdado da classe pai é fazer um **redirecionamento de navegador** — adequado para um formulário HTML, mas não para uma API REST, como a aula descobre na prática (seção 3.13).

### 3.7. `attemptAuthentication`: lendo o corpo JSON da requisição

O método mais importante para sobrescrever é o `attemptAuthentication` — é nele que o filtro pai, originalmente, lê os parâmetros do formulário. A nova versão lê, em vez disso, o corpo da requisição como JSON:

```java
@Override
public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

    try {
        var loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);
        var token = UsernamePasswordAuthenticationToken.unauthenticated(
                loginRequest.username(),
                loginRequest.password());
        return getAuthenticationManager().authenticate(token);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
```

- **`@Override`** — anotação (não obrigatória para o código funcionar, mas fortemente recomendada) que avisa tanto o compilador quanto quem lê o código que este método está **substituindo** um método já existente na classe pai. Se o nome ou a assinatura não baterem exatamente com um método da classe pai, o compilador aponta erro imediatamente — protegendo contra erros de digitação no nome do método.
- **`public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException`** — a assinatura precisa ser **idêntica** à do método original, na classe pai, para que a sobrescrita seja válida. `HttpServletRequest` e `HttpServletResponse` são interfaces do pacote `jakarta.servlet` (o padrão Java para aplicações web dentro de um servidor como o Tomcat, embutido no Spring Boot) que representam, respectivamente, a requisição HTTP recebida e a resposta HTTP a ser construída.
- **`objectMapper.readValue(request.getInputStream(), LoginRequest.class)`** — `request.getInputStream()` devolve um fluxo de bytes (`InputStream`) com todo o conteúdo bruto do corpo da requisição (o JSON enviado pelo cliente). `objectMapper.readValue(...)` lê esse fluxo e, usando reflexão (mecanismo do Java que permite inspecionar e manipular classes em tempo de execução), tenta **desserializar** o JSON diretamente para um objeto `LoginRequest` — casando as chaves do JSON (`"username"`, `"password"`) com os componentes de mesmo nome do record.
- **`var loginRequest = ...`** — `var` é a forma do Java de declarar uma variável local sem escrever explicitamente o tipo, deixando o compilador **inferir** o tipo a partir do valor atribuído (aqui, `LoginRequest`). O tipo continua sendo verificado em tempo de compilação — `var` é apenas um atalho de escrita, não torna a variável "dinâmica" como em linguagens não tipadas.
- **`UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(), loginRequest.password())`** — cria o **token de autenticação** mencionado na seção 3.3: um objeto simples que carrega usuário e senha, ainda sem confirmação. O nome do método estático, `unauthenticated`, deixa explícito que este token, neste momento, **ainda não** foi validado — ele é apenas a "pergunta" que será enviada ao `AuthenticationManager` para verificação. Repare que `loginRequest.username()` e `loginRequest.password()` são os métodos de acesso gerados automaticamente pelo `record` (seção 3.5).
- **`getAuthenticationManager().authenticate(token)`** — `getAuthenticationManager()` é um método **herdado** da classe pai, que devolve o `AuthenticationManager` recebido lá no construtor (seção 3.6, através do `super(...)`). Chamar `.authenticate(token)` nele dispara, de fato, a verificação: internamente, ele localiza o usuário através do `UserDetailsService`, aplica o `PasswordEncoder` configurado sobre a senha recebida e compara com o hash armazenado. Se tudo bater, o método devolve um novo objeto `Authentication`, desta vez **autenticado**; se falhar (usuário inexistente, senha errada, conta bloqueada, etc.), ele lança uma exceção — por exemplo, `BadCredentialsException` — que o restante da cadeia de filtros do Spring Security sabe capturar e transformar em uma resposta `403 Forbidden`.
- **`try { ... } catch (IOException e) { throw new RuntimeException(e); }`** — `objectMapper.readValue(...)` é declarado como podendo lançar `IOException`, uma **checked exception** (conceito já visto no tutorial anterior) relacionada a falhas de leitura de dados. Como a assinatura do método `attemptAuthentication` (herdada da classe pai) não permite declarar `throws IOException`, a solução aqui é capturá-la e relançá-la envolvida em uma `RuntimeException` — uma **unchecked exception**, que não precisa ser declarada na assinatura do método. Isso efetivamente "traduz" uma falha de leitura de JSON em um erro genérico de execução, que vai interromper o processamento da requisição.
- **`return getAuthenticationManager().authenticate(token);`** — o valor devolvido por este método é, portanto, o resultado da tentativa de autenticação: um `Authentication` válido, se tudo correu bem, ou uma exceção lançada por dentro do próprio `authenticate(...)`, propagada para cima.

### 3.8. Completando a classe: campo `objectMapper` e o record `LoginRequest`

Juntando os pedaços das seções anteriores, a classe completa fica assim (a versão final, conferida diretamente no `.zip`, é apresentada por completo no checkpoint mais adiante):

```java
@Component
public class RestUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;

    // construtor da seção 3.6

    // attemptAuthentication da seção 3.7

    public record LoginRequest(String username, String password) {}
}
```

- **`private final ObjectMapper objectMapper;`** — a declaração do campo (*field*) que guarda a referência ao `ObjectMapper` recebido por injeção de dependência. `private` restringe o acesso a essa variável apenas ao código de dentro da própria classe (encapsulamento); `final` indica que, uma vez atribuído no construtor, esse valor **nunca mais muda** durante a vida do objeto.
- **`public record LoginRequest(String username, String password) {}`** — o record da seção 3.5, declarado como uma classe **aninhada** dentro de `RestUsernamePasswordAuthenticationFilter`. Isso é uma escolha de organização: como esse DTO só faz sentido no contexto deste filtro específico (ele modela exatamente o que este filtro espera receber), ele é mantido "dentro" da classe que o usa, em vez de virar um arquivo `.java` separado.

### 3.9. O erro esperado: "qual `AuthenticationManager` eu devo usar?"

Ao tentar subir a aplicação pela primeira vez com esse novo filtro (antes de ter o construtor completo da seção 3.6), a aula encontra um erro de inicialização: o Spring não sabe **qual** `AuthenticationManager` esse filtro deve usar para autenticar. Isso acontece porque `UsernamePasswordAuthenticationFilter` (a classe pai) exige, no seu próprio construtor, que um `AuthenticationManager` seja informado — e, até esse ponto, nenhum foi passado.

A solução, explicada na aula, é usar o `AuthenticationConfiguration`:

> **`AuthenticationConfiguration`** — uma classe de configuração do próprio Spring Security, disponível automaticamente como *bean* assim que `@EnableWebSecurity` está ativo. Seu método `getAuthenticationManager()` devolve o `AuthenticationManager` **padrão** que o Spring já monta sozinho, combinando o `UserDetailsService` e o `PasswordEncoder` que já existem na aplicação (configurados desde o Vídeo 02). Por baixo dos panos, esse `AuthenticationManager` padrão é implementado por uma classe chamada `DaoAuthenticationProvider` — o "provedor" mencionado no Vídeo 01, responsável por, de fato, buscar o usuário no `UserDetailsService` e comparar a senha usando o `PasswordEncoder`.
>
> A aula destaca quando isso deixaria de ser suficiente: se o armazenamento de senhas fosse muito customizado, ou se o usuário não viesse de um `UserDetailsService` convencional, seria necessário implementar um `AuthenticationManager` próprio. Neste projeto, porém, o padrão já resolve — não há necessidade de reinventar essa peça.

Com isso, o construtor apresentado na seção 3.6 (`super(authenticationConfiguration.getAuthenticationManager())`) já nasce completo, resolvendo de antemão o erro que a aula demonstra ao vivo.

### 3.10. Atualizando o `SecurityConfig`: registrando o novo filtro

Com o filtro pronto, falta plugá-lo na cadeia de segurança. A classe `SecurityConfig` (que também passa a viver no novo pacote `security`, conforme visto no checkpoint) ganha, agora sim, um bean `SecurityFilterChain` **explícito** — o bean que, no tutorial anterior (seção 2.6, "Pontos de atenção"), havia sido omitido do projeto final por não ser necessário até então:

```java
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
```

- **`SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationConfiguration authenticationConfiguration, ObjectMapper objectMapper)`** — o método de bean agora recebe três parâmetros injetados automaticamente pelo Spring: o já conhecido `HttpSecurity`, mais o `AuthenticationConfiguration` e o `ObjectMapper`, necessários para **instanciar manualmente** o novo filtro logo em seguida.
- **`RestUsernamePasswordAuthenticationFilter customAuthFilter = new RestUsernamePasswordAuthenticationFilter(authenticationConfiguration, objectMapper);`** — repare que, embora a classe do filtro já esteja anotada com `@Component` (o que permitiria ao Spring criá-la e injetá-la automaticamente em qualquer lugar, como um parâmetro de método), aqui ela é instanciada manualmente com `new`, passando os mesmos dois parâmetros que o próprio `Bean` já recebeu por injeção. Isso garante que a instância usada aqui seja construída com os *beans* corretos do contexto, dentro do próprio processo de montagem da cadeia de segurança.
- **`.csrf(AbstractHttpConfigurer::disable)`** — desabilita a proteção CSRF discutida na seção 3.2. `AbstractHttpConfigurer::disable` é uma **referência de método** (*method reference*), uma forma ainda mais compacta de escrever uma expressão lambda quando ela apenas chama um método já existente — aqui, equivalente a escrever `csrfConfigurer -> csrfConfigurer.disable()`.
- **`.securityContext(context -> context.requireExplicitSave(false))`** — configura o `SecurityContext` (o objeto interno do Spring Security que guarda a identidade do usuário autenticado durante o ciclo de vida da requisição/sessão). Por padrão, ao usar um filtro customizado como este, seria necessário **salvar explicitamente** esse contexto de segurança (associá-lo à sessão HTTP) depois de uma autenticação bem-sucedida. Passar `false` para `requireExplicitSave` pede ao Spring Security para fazer esse salvamento **automaticamente**, sem que o código precise chamar nenhum método adicional para isso — resolvendo, na prática, o problema descrito na seção 3.15 (a sessão não sendo mantida entre requisições).
- **`.authorizeHttpRequests(auth -> auth.requestMatchers("/api/auth/login").permitAll().anyRequest().authenticated())`** — a regra de autorização ganha uma exceção: `.requestMatchers("/api/auth/login")` identifica especificamente a URL do novo filtro de login, e `.permitAll()` libera o acesso a ela **sem exigir autenticação prévia** (afinal, é justamente ali que o usuário ainda não autenticado envia usuário e senha pela primeira vez). Todo o restante continua exigindo autenticação, através de `.anyRequest().authenticated()`, exatamente como antes.
- **`.addFilterAt(customAuthFilter, UsernamePasswordAuthenticationFilter.class)`** — método de `HttpSecurity` que insere um filtro **na mesma posição** que outro filtro ocuparia dentro da cadeia. Aqui, ele diz: "coloque `customAuthFilter` exatamente onde o `UsernamePasswordAuthenticationFilter` (o filtro original do `formLogin`) estaria" — ou seja, **substitui** o filtro padrão pelo filtro REST customizado, em vez de adicionar um filtro extra ao lado dele.
- **`AbstractHttpConfigurer`** e **`UsernamePasswordAuthenticationFilter`** (como classe, não como instância) — os dois `import`s novos usados apenas como **referências de tipo/método** (`AbstractHttpConfigurer::disable` e `UsernamePasswordAuthenticationFilter.class`), sem que nenhum objeto dessas classes seja criado diretamente neste trecho.

Como o `formLogin` deixou de ser chamado — a aula relata que essa linha foi removida (ou comentada) da configuração — a aplicação para de oferecer a tela de login HTML por padrão. Toda autenticação passa a acontecer exclusivamente através da rota REST recém-criada.

### 3.11. Primeiro teste: erro 403 com senha errada

Usando a ferramenta *HTTP Client*, embutida no IntelliJ (uma forma de escrever e disparar requisições HTTP diretamente do editor, sem precisar de ferramentas externas como Postman), a aula envia:

```
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
  "username": "influencer",
  "password": "123"
}
```

Como a senha real cadastrada é `password`, e não `123`, a resposta é `403 Forbidden` — resultado esperado de uma `BadCredentialsException` lançada internamente pelo `AuthenticationManager` ao comparar a senha informada com o hash armazenado. A aula comenta que seria possível capturar essa exceção no futuro e devolver uma mensagem de erro mais amigável (por exemplo, através de um `@ExceptionHandler` em um Controller), mas isso fica como uma melhoria futura, fora do escopo deste vídeo.

### 3.12. O problema do redirecionamento

Corrigindo a senha para `password`, a requisição ainda devolve um `403` — mas desta vez acompanhado de um **redirecionamento** HTTP. Isso acontece porque o comportamento **padrão**, herdado de `UsernamePasswordAuthenticationFilter`, é: em caso de autenticação bem-sucedida, redirecionar o navegador de volta para `/` — um comportamento pensado para o `formLogin` (onde faz sentido levar o usuário de volta à página inicial após o login), mas sem sentido para uma API REST, onde o cliente só precisa saber "deu certo ou não deu".

A correção é exatamente a customização já mostrada na seção 3.6: `setAuthenticationSuccessHandler(...)`, sobrescrevendo esse comportamento padrão para simplesmente devolver `200 OK`. Repetindo a requisição após essa mudança, o resultado passa a ser `200 OK` — a autenticação funcionou, e a resposta agora é adequada a um cliente de API.

### 3.13. Sessão e cookies: autenticar não é o mesmo que continuar autenticado

Mesmo com o `200 OK` no login, uma tentativa seguinte de acessar `GET /` (endpoint que exige autenticação) ainda devolve `403`. A explicação da aula: autenticar uma vez, isoladamente, não é suficiente — é preciso que a aplicação **lembre** dessa autenticação nas requisições seguintes, e o mecanismo clássico para isso, em aplicações *stateful* (com estado), é a **sessão HTTP**, identificada por um **cookie** (aqui, o cookie padrão do Java, `JSESSIONID`).

A correção para esse problema é exatamente a configuração `.securityContext(context -> context.requireExplicitSave(false))`, já apresentada na seção 3.10 — sem ela, o `SecurityContext` resultante da autenticação nunca chega a ser de fato salvo na sessão, porque o `AuthenticationSuccessHandler` customizado (seção 3.6) não faz esse salvamento explicitamente (ele só define o status `200`). Com `requireExplicitSave(false)`, o próprio Spring Security garante esse salvamento automaticamente.

Repetindo a requisição de login após essa configuração, a resposta passa a incluir um cabeçalho `Set-Cookie` — a instrução do servidor para o cliente: "guarde este valor e reenvie-o em toda requisição futura". A partir daí, os testes confirmam o fluxo completo:

- Uma requisição para `/` **sem** o cookie continua devolvendo `403`.
- A mesma requisição, **com** o cookie recebido no login, devolve `"Hello World influencer"`.
- Autenticando como `brand`, um **outro** cookie é recebido (`JSESSIONID` diferente) — e cada cookie, usado separadamente, mantém a identidade correta (`influencer` continua sendo `influencer`; `brand` continua sendo `brand`), confirmando que cada sessão é isolada por usuário.

### 3.14. Limitação discutida: sessão em memória e o problema de escalabilidade

A aula fecha o vídeo com uma reflexão importante sobre os limites da abordagem atual, que **não é resolvida com código nesta etapa**, mas orienta o restante do curso:

- Os usuários continuam **em memória** (`InMemoryUserDetailsManager`) — assunto que a aula promete endereçar no próximo vídeo, migrando para um banco de dados.
- A **sessão** também vive em memória, dentro do próprio processo Java da aplicação. Isso é demonstrado ao vivo: reiniciando a aplicação e tentando reusar um cookie de sessão obtido **antes** do restart, a requisição volta a devolver `403` — porque aquela sessão específica deixou de existir quando o processo foi reiniciado.
- Esse comportamento revela uma limitação de escalabilidade: uma aplicação que guarda sessão em memória é chamada de **stateful** (com estado) — ela só "lembra" da autenticação enquanto o mesmo processo/servidor que autenticou o usuário continuar de pé, e continuar recebendo as requisições seguintes desse mesmo usuário. Isso dificulta rodar a aplicação em múltiplas instâncias (escalar horizontalmente), porque uma sessão criada em uma instância não existiria nas outras.
- O contraponto é uma aplicação **stateless** (sem estado): em vez de depender de uma sessão guardada no servidor, cada requisição carrega tudo que é necessário para se autoidentificar (o exemplo citado pela aula é o **JWT** — *JSON Web Token*, um token autocontido que carrega a identidade do usuário de forma verificável, sem exigir que o servidor "lembre" de nada entre requisições).

Esse é um gancho explícito para os próximos vídeos, que devem aprofundar tanto a persistência de usuários em banco de dados quanto, eventualmente, estratégias mais escaláveis de autenticação.

---

## Pontos de atenção: divergências entre a aula e o seu projeto

Comparando o código real do seu `.zip` com a narrativa da transcrição, dois pontos merecem registro:

1. **`SecurityContextConfigurer` importado, mas não usado diretamente.** O arquivo final `SecurityConfig.java` importa `org.springframework.security.config.annotation.web.configurers.SecurityContextConfigurer`, mas nenhuma linha do código faz referência direta a essa classe pelo nome — ela só aparece "por trás" da lambda `context -> context.requireExplicitSave(false)`, cujo parâmetro (`context`) é, na verdade, uma instância de `SecurityContextConfigurer`, mesmo sem o tipo ser escrito explicitamente (o compilador infere isso sozinho, a partir do método `.securityContext(...)`). Esse é um `import` que o IntelliJ provavelmente já sinaliza como não utilizado (*unused import*), da mesma forma que aconteceu com os `import`s de `Customizer` e `SecurityFilterChain` apontados no tutorial anterior. Não gera nenhum erro de compilação — é apenas um resquício que pode ser removido em uma limpeza futura do código.

2. **`SecurityConfig` mudou de pacote.** No Vídeo 02, a classe vivia em `dio.proposalmanagement.auth.infrastructure`. Neste vídeo, ela foi movida para o novo pacote `dio.proposalmanagement.auth.infrastructure.security`, junto com a nova classe `RestUsernamePasswordAuthenticationFilter`. Isso está de acordo com o que a transcrição descreve ("vou criar um pacote aqui security e jogar todas essas configurações lá dentro") — não é uma divergência, mas vale registrar para quem for procurar o arquivo esperando encontrá-lo no local antigo.

---

## Glossário de conceitos novos (Vídeo 03)

Apenas os termos que **ainda não** apareciam no glossário do tutorial anterior. Para os termos já vistos (`@Bean`, `@Configuration`, `HttpSecurity`, `PasswordEncoder`, etc.), consulte `001-Tutorial_ProposalManagement_Spring_Security_Videos01a02.md`.

### Estrutura da linguagem Java

| Termo | Significado |
|---|---|
| `extends` | Palavra-chave que estabelece **herança**: a classe à esquerda passa a ser uma especialização da classe à direita, herdando seus métodos e podendo sobrescrever (*override*) o comportamento de alguns deles. |
| `@Override` | Anotação que sinaliza que um método está substituindo um método de mesma assinatura já existente na classe pai (ou em uma interface implementada); o compilador valida essa correspondência e aponta erro se ela não existir. |
| `record` | Tipo de classe imutável introduzido no Java moderno para modelar dados simples (DTOs). Gera automaticamente construtor, métodos de acesso (sem prefixo `get`), `equals()`, `hashCode()` e `toString()`. |
| classe aninhada (*nested class*) | Uma classe declarada dentro de outra classe (como `LoginRequest` dentro de `RestUsernamePasswordAuthenticationFilter`), usada quando ela só faz sentido no contexto da classe que a contém. |
| `var` | Palavra-chave que permite declarar uma variável local sem escrever seu tipo explicitamente, deixando o compilador inferi-lo a partir do valor atribuído. O tipo continua sendo verificado em tempo de compilação. |
| referência de método (*method reference*, `Classe::metodo`) | Forma compacta de escrever uma expressão lambda que apenas chama um método já existente, como em `AbstractHttpConfigurer::disable`. |
| *unchecked exception* (`RuntimeException`) | Uma exceção que **não** precisa ser declarada com `throws` na assinatura do método nem obrigatoriamente tratada com `try/catch` — usada aqui para "traduzir" uma `IOException` (checked) em algo que pode ser relançado livremente. |
| `IOException` | Exceção checked do Java que sinaliza falhas ao ler ou escrever dados (arquivos, streams de rede, etc.) — lançada, neste caso, por uma possível falha ao ler o corpo da requisição. |
| Reflexão (*reflection*) | Mecanismo do Java que permite inspecionar e manipular classes, métodos e campos em tempo de execução — é o que permite ao `ObjectMapper` descobrir sozinho os campos de um `record` como `LoginRequest` e preenchê-los a partir de um JSON. |

### Anotações e conceitos do Spring / Spring Boot

| Termo | Significado |
|---|---|
| `@Component` | Anotação "guarda-chuva" do Spring que marca uma classe como um componente genérico a ser descoberto pelo *component scan* e gerenciado como *bean* — usada quando a classe não é nem um `@Configuration`, nem um `@RestController`, mas ainda precisa ser instanciada e ter dependências injetadas pelo Spring. |
| Injeção de dependência via construtor | Técnica em que um *bean* declara, no seu próprio construtor, quais outras dependências ele precisa — o Spring identifica esse construtor automaticamente e injeta os valores necessários, sem exigir configuração manual adicional. |
| `ObjectMapper` (Jackson) | Classe da biblioteca Jackson (incluída junto com `spring-boot-starter-web`) responsável por converter objetos Java em JSON e JSON em objetos Java. |

### Anotações e conceitos do Spring Security

| Termo | Significado |
|---|---|
| CSRF (*Cross-Site Request Forgery*) | Ataque em que um site malicioso aproveita a sessão autenticada de um usuário em outro site para enviar requisições forjadas em nome dele; mitigado, por padrão, através de um token exigido em cada formulário. |
| `.csrf(AbstractHttpConfigurer::disable)` | Desabilita a proteção CSRF do Spring Security — adequado para clientes que não dependem de sessão + cookie de navegador da forma clássica (ex.: APIs REST puras), mas deve ser avaliado caso a caso. |
| `Filter` (filtro) | Componente que intercepta uma requisição HTTP antes dela chegar ao seu destino final, podendo inspecioná-la, modificá-la ou bloqueá-la. O Spring Security é implementado como uma cadeia (*chain*) desses filtros. |
| `UsernamePasswordAuthenticationFilter` | O filtro padrão do Spring Security responsável por interceptar requisições `POST` de login, extrair usuário e senha, montar um token de autenticação e delegá-lo ao `AuthenticationManager`. Usado tanto pelo `formLogin` quanto, de forma estendida, pelo filtro REST criado neste vídeo. |
| `attemptAuthentication(...)` | Método de `UsernamePasswordAuthenticationFilter` (e de suas subclasses) onde a lógica de **extrair** usuário e senha da requisição é definida — sobrescrito neste vídeo para ler JSON em vez de campos de formulário. |
| `UsernamePasswordAuthenticationToken.unauthenticated(user, pass)` | Método estático que cria um token de autenticação "cru", contendo apenas usuário e senha ainda não verificados — a "pergunta" enviada ao `AuthenticationManager` para validação. |
| `AuthenticationManager` | Interface central do Spring Security responsável por, de fato, **verificar** um token de autenticação (usuário e senha) e devolver um resultado autenticado, ou lançar uma exceção em caso de falha. |
| `AuthenticationConfiguration` | Classe de configuração do Spring Security que dá acesso ao `AuthenticationManager` **padrão**, já montado automaticamente a partir do `UserDetailsService` e do `PasswordEncoder` existentes na aplicação, através do método `getAuthenticationManager()`. |
| `DaoAuthenticationProvider` | Implementação padrão de `AuthenticationManager`/`AuthenticationProvider` usada pelo Spring Security quando a autenticação é baseada em um `UserDetailsService` convencional combinado com um `PasswordEncoder`. |
| `setFilterProcessesUrl(...)` | Método de `UsernamePasswordAuthenticationFilter` que define em qual URL o filtro deve interceptar requisições `POST` (por padrão, `/login`; neste projeto, alterado para `/api/auth/login`). |
| `setAuthenticationSuccessHandler(...)` | Método que define o comportamento a ser executado quando a autenticação é bem-sucedida — sobrescrito neste vídeo para devolver `200 OK` em vez do redirecionamento padrão (pensado para formulários HTML). |
| `.addFilterAt(filtro, Classe.class)` | Método de `HttpSecurity` que insere um filtro customizado na **mesma posição** de outro filtro já existente na cadeia, efetivamente substituindo-o. |
| `SecurityContext` | Objeto interno do Spring Security que guarda a identidade do usuário autenticado durante o ciclo de vida de uma requisição/sessão. |
| `requireExplicitSave(false)` | Configuração que instrui o Spring Security a salvar automaticamente o `SecurityContext` resultante de uma autenticação bem-sucedida, sem exigir que o código faça esse salvamento manualmente. |
| `.requestMatchers(url).permitAll()` | Regra de autorização que libera o acesso a uma URL específica sem exigir autenticação — usada aqui para a própria rota de login (`/api/auth/login`). |
| Sessão HTTP / cookie `JSESSIONID` | Mecanismo pelo qual um servidor "lembra" de um cliente entre requisições: após autenticar, o servidor devolve um cookie (`Set-Cookie`) que o cliente deve reenviar em requisições futuras para ser reconhecido. |
| Aplicação *stateful* × *stateless* | *Stateful*: a aplicação guarda estado (como sessões) na memória do próprio servidor, exigindo que requisições futuras do mesmo usuário cheguem a essa mesma instância. *Stateless*: cada requisição carrega tudo que é necessário para se autoidentificar (ex.: um JWT), sem depender de estado guardado no servidor — mais fácil de escalar horizontalmente. |
| JWT (*JSON Web Token*) | Mencionado como exemplo de mecanismo de autenticação *stateless*: um token autocontido e verificável que carrega a identidade do usuário, sem exigir sessão guardada no servidor. Será aprofundado em vídeos futuros. |

### Ferramentas

| Termo | Significado |
|---|---|
| HTTP Client (IntelliJ) | Ferramenta embutida no IntelliJ IDEA que permite escrever e disparar requisições HTTP diretamente de um arquivo no editor, sem depender de ferramentas externas como Postman ou Insomnia. |
| DTO (*Data Transfer Object*) | Um objeto cujo único propósito é carregar dados entre camadas (ou entre sistemas), sem lógica de negócio embutida — papel cumprido, neste vídeo, pelo `record LoginRequest`. |

---

## Estado atual do projeto (checkpoint do Vídeo 03)

Este é o retrato fiel do código-fonte na etapa atual, conferido diretamente nos arquivos do `.zip` `proposal-managemnet_ate_o_video03.zip` — e não apenas na narrativa do README (cuja seção de anotações do Vídeo 03 está em branco). `build.gradle`, `settings.gradle`, `ProposalManagementApplication.java` e `Controller.java` **não mudaram** em relação ao checkpoint do Vídeo 02.

### Estrutura de pastas

```
proposal-managemnet/
├── build.gradle
├── settings.gradle
└── src/
    ├── main/
    │   ├── java/dio/proposalmanagement/
    │   │   ├── ProposalManagementApplication.java
    │   │   └── auth/
    │   │       ├── application/            (ainda vazio nesta etapa)
    │   │       ├── domain/                  (ainda vazio nesta etapa)
    │   │       └── infrastructure/
    │   │           ├── http/
    │   │           │   └── Controller.java              (inalterado desde o Vídeo 02)
    │   │           └── security/                          ← novo pacote
    │   │               ├── SecurityConfig.java            ← movido para cá e evoluído
    │   │               └── RestUsernamePasswordAuthenticationFilter.java   ← novo arquivo
    │   └── resources/                       (ainda vazio nesta etapa — sem application.properties)
    └── test/                                 (ainda vazio nesta etapa)
```

### `auth/infrastructure/security/RestUsernamePasswordAuthenticationFilter.java` (novo arquivo)

```java
package dio.proposalmanagement.auth.infrastructure.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RestUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;

    public RestUsernamePasswordAuthenticationFilter(AuthenticationConfiguration authenticationConfiguration, ObjectMapper objectMapper) throws Exception {
        super(authenticationConfiguration.getAuthenticationManager());
        this.objectMapper = objectMapper;
        setFilterProcessesUrl("/api/auth/login");
        setAuthenticationSuccessHandler((request, response, authentication) ->
                response.setStatus(HttpServletResponse.SC_OK));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            var loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);
            var token = UsernamePasswordAuthenticationToken.unauthenticated(
                    loginRequest.username(),
                    loginRequest.password());
            return getAuthenticationManager().authenticate(token);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public record LoginRequest(String username, String password) {}
}
```

### `auth/infrastructure/security/SecurityConfig.java` (movido de pacote e evoluído)

```java
package dio.proposalmanagement.auth.infrastructure.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.SecurityContextConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

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
    UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails influencer = User.withUsername("influencer")
                .password(passwordEncoder.encode("password"))
                .roles("INFLUENCER")
                .build();

        UserDetails brand = User.withUsername("brand")
                .password(passwordEncoder.encode("password"))
                .roles("BRAND")
                .build();

        return new InMemoryUserDetailsManager(influencer, brand);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

> Note o `import` de `SecurityContextConfigurer` (linha 10): ele não é referenciado explicitamente em nenhum ponto do arquivo pelo nome, mas corresponde ao tipo inferido do parâmetro `context` na lambda `.securityContext(context -> ...)` — ver "Pontos de atenção" acima.

### `auth/infrastructure/http/Controller.java` (inalterado)

```java
package dio.proposalmanagement.auth.infrastructure.http;

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
    public String hello(@AuthenticationPrincipal UserDetails user) {
        return "Hello World " + user.getUsername();
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

### `build.gradle` (inalterado)

Confirmado no `.zip`: nenhuma dependência nova foi adicionada — o `ObjectMapper` (Jackson) e as classes `jakarta.servlet.*` já vêm inclusas transitivamente através de `spring-boot-starter-web`, que já estava presente desde o Vídeo 02.

```groovy
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.2.5'
    id 'io.spring.dependency-management' version '1.1.4'
}

group = 'dio'
version = '1.0-SNAPSHOT'

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
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
}

test {
    useJUnitPlatform()
}
```

---

## Próximos passos: o que vem a partir do Vídeo 04

Segundo o roteiro do curso (conferido no seu README), a sequência dos próximos vídeos é:

- **Vídeo 04 — Evoluindo a Autenticação (continuação):** o próprio README repete o título "Evoluindo a Autenticação" para os Vídeos 03 e 04, sugerindo que este vídeo dá sequência direta ao que foi construído aqui — possivelmente refinando a autenticação REST (tratamento de erros mais amigável para credenciais inválidas, por exemplo) antes de partir para persistência em banco de dados.
- **Vídeo 05 — Segurança com Banco de Dados:** deve substituir o `InMemoryUserDetailsManager` por uma implementação de `UserDetailsService` que consulta um banco de dados real, resolvendo a primeira limitação apontada na seção 3.14 deste tutorial.
- **Vídeo 06 — Segurança Baseada em Papéis:** deve aprofundar o uso de RBAC, possivelmente introduzindo múltiplas roles por usuário, hierarquia de roles, ou autorização mais fina (*fine-grained*), retomando o "espectro de autorização" apresentado no Vídeo 01.
- **Vídeo 07 — Implementando o Use Case de Listagem:** deve introduzir a primeira funcionalidade de negócio real do estudo de caso "Influencer & Brand Connect" — provavelmente a listagem de propostas.
- **Vídeo 08 — Criando Entidades de Persistência:** deve modelar as entidades JPA relacionadas a propostas (`Proposal`), possivelmente reaproveitando UUIDs e Records, como sugerido no Vídeo 01.
- **Vídeo 09 — Implementando o ProposalController:** deve criar o controller real da aplicação (substituindo o `Controller` genérico criado no Vídeo 02), com endpoints de negócio para criação e consulta de propostas.
- **Vídeo 10 — Segurança Escalável:** deve fechar o curso discutindo como fazer essa configuração de segurança crescer de forma sustentável — um bom momento para retomar a discussão *stateful × stateless* e o JWT, mencionados de passagem na seção 3.14 deste tutorial.

> **Sugestão de uso deste documento**
> Depois de assistir ao próximo vídeo, crie um novo arquivo numerado (`003-Tutorial_..._Video04.md`) seguindo este mesmo formato, e sempre confira o código real do `.zip` recebido naquela etapa — não apenas a narrativa do README ou da transcrição — antes de escrever qualquer explicação.
