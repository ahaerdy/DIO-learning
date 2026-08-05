## Instrutor

- Thiago Poiani (Principal Engineer at Skip)
- Contato Linkedin: / [thpoiani](https://www.linkedin.com/in/thpoiani/)

### 🟩 Vídeo 01 - Introdução ao desenvolvendo sua API Inteligente

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.05-video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvendo-sua-api-inteligente-com-reconhecimento-de-fala-e-spring-boot-1/learning/6a27cad6-eb4b-453c-9ba7-d0b3d8916a17?back=/track/ntt-data-2026-ai-java-back-end

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-01-14h01m34s413.jpg" alt="" width="840">
</p>

O slide apresenta a **Agenda do Curso**, com cinco tópicos que estruturam a aula: Apresentação, Fundamentos e Conceitos, Estudo de Caso, Desenvolvimento do Projeto e Roadmap do Aluno. Esse roteiro define a ordem em que o conteúdo será abordado: primeiro a ideia geral do projeto de API inteligente com reconhecimento de fala, depois os conceitos de IA envolvidos, em seguida um estudo de caso prático, o desenvolvimento do projeto em si e, por fim, sugestões de evolução para o aluno.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-01-14h01m38s197.jpg" alt="" width="840">
</p>

O diagrama **"A Nova Anatomia da API"** resume visualmente a arquitetura do projeto, dividida em quatro etapas sequenciais:

1. **Áudio -> STT**: entrada de onda sonora, convertida em texto processável.
2. **MCP / Tool Calling (Spring AI)**: integração de modelo, roteamento lógico e conexão com ferramentas remotas ou dados locais.
3. **Java Use Case**: execução da lógica de negócio Spring Boot e das tarefas específicas da aplicação.
4. **TTS -> Áudio**: geração de voz sintetizada como saída para o usuário.

Essa ilustração antecipa o fluxo completo que será detalhado ao longo da aula, mostrando como uma entrada de áudio percorre o sistema até se transformar novamente em áudio na resposta.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-01-14h01m41s864.jpg" alt="" width="840">
</p>

O slide **"A Evolução das Interfaces"** contrasta o padrão atual de APIs com a nova proposta do projeto. Do lado esquerdo, o **Padrão Atual** é caracterizado por receptores de JSON estáticos, regras de validação rígidas e dependência de input perfeitamente estruturado — o modelo REST tradicional, onde uma requisição como um POST para `/companies` segue um contrato fixo. Do lado direito, a **Nova Era** propõe ouvintes ativos de áudio, compreensão de contexto fluida via Spring AI e processamento de linguagem natural nativa, eliminando a rigidez do formato JSON em favor de uma entrada por voz.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-01-14h01m51s123.jpg" alt="" width="840">
</p>

O diagrama **"O Novo Fluxo de Interação"** detalha, de ponta a ponta, como um comando de voz viaja através da aplicação Spring Boot: **Usuário (Microfone) -> Transcrição de Áudio -> Interpretação de Intenção -> Lógica de Domínio (Java) -> Geração de Resposta -> Usuário**. Esse fluxo linear mostra as cinco etapas pelas quais uma mensagem falada passa até retornar como resposta ao usuário, servindo de mapa geral para os componentes que serão explicados individualmente na sequência.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-01-14h01m54s830.jpg" alt="" width="840">
</p>

O slide **"Glossário de IA para Desenvolvedores Java"** abre a seção conceitual da aula com o termo **Linguagem Natural**, apresentando um exemplo de código Java que ilustra a ideia de decodificar uma intenção a partir de texto livre:

```java
public String decodeIntent(String naturalLanguage) {
    return naturalLanguageParser.parse(naturalLanguage).getIntent();
}
```

O trecho representa, de forma conceitual, como uma entrada em linguagem natural pode ser interpretada por um parser para extrair a intenção do usuário — a base sobre a qual os próximos conceitos (Speech-to-Text, Tool Calling e Text-to-Speech) serão construídos.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-01-14h01m58s354.jpg" alt="" width="840">
</p>

O slide **"Speech-to-Text (STT)"** explica a primeira etapa do pipeline: a transformação de ondas sonoras em texto processável, onde "o áudio não estruturado ganha forma para a máquina entender". A ilustração mostra uma onda sonora sendo processada e convertida em um trecho de texto de exemplo, "Gastei 50 reais...", representando o resultado da transcrição de um áudio de entrada.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-01-14h02m01s952.jpg" alt="" width="840">
</p>

O slide **"Tool Calling"** descreve a segunda etapa do pipeline como "a ponte para o domínio": a capacidade da IA de interpretar uma intenção e "chamar" funções Java específicas (Use Cases) para realizar tarefas no mundo real. O diagrama mostra o bloco **IA** direcionando uma seta até o bloco **Use Case (Java)**, representando como o modelo, a partir do texto transcrito, identifica qual método da aplicação deve ser executado e com quais parâmetros.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-01-14h02m11s540.jpg" alt="" width="840">
</p>

O slide **"Text-to-Speech (TTS)"** apresenta a última etapa do pipeline: a humanização da resposta, transformando a saída da lógica de negócio de volta em uma interação humana e fluida. A imagem mostra um objeto JSON de resposta sendo convertido em onda sonora sintetizada:

```json
{
  "status": "ok",
  "response": {
    "message": "Sua conta foi criada com sucesso."
  }
}
```

O diagrama ilustra esse JSON sendo processado por um alto-falante, gerando uma "Acoustic Waveform" (onda acústica) como saída de voz sintetizada para o usuário.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-01-14h02m16s339.jpg" alt="" width="840">
</p>

O diagrama **"A Nova Anatomia da API"** retorna à tela, agora como fechamento da explicação conceitual, reunindo as quatro etapas já detalhadas individualmente: **Áudio -> STT**, **MCP / Tool Calling (Spring AI)**, **Java Use Case** e **TTS -> Áudio**. Ao revisitar essa visão de conjunto, fica evidente o ciclo completo do sistema: um áudio de entrada é transcrito, interpretado para acionar a lógica de negócio correta e, por fim, convertido novamente em áudio como resposta ao usuário.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-01-14h02m20s216.jpg" alt="" width="840">
</p>

O infográfico **"O Assistente de Budgeting: Transformando Voz em Dados Financeiros"** apresenta o estudo de caso do projeto: um sistema que elimina a fricção da entrada manual de dados, permitindo que o usuário registre gastos apenas falando, enquanto a IA processa e categoriza as informações automaticamente. O exemplo mostrado é a frase "Gastei 50 reais no Starbucks agora", que passa por uma etapa de **Extração de Entidades** (Valor, Local, Data/Hora) e **Categorização Automática** (o sistema infere que Starbucks corresponde à categoria Alimentação/Café), resultando em dados estruturados prontos para persistência: Valor = 50.00, Local = Starbucks, Data/Hora = Agora (Hoje).


### 🟩 Vídeo 02 - Spring AI: Setup e Integração com LLMs

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.05-video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvendo-sua-api-inteligente-com-reconhecimento-de-fala-e-spring-boot-1/learning/f6d5285c-3962-41d4-8e19-ca821da8eabf?back=/track/ntt-data-2026-ai-java-back-end

### Anotações

#### Introdução ao Spring AI

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-01-14h09m25s142.jpg" alt="" width="840">
</p>

A imagem mostra a página de documentação oficial do Spring AI aberta no navegador, na seção "Introduction". O texto exibido explica que o projeto Spring AI busca simplificar o desenvolvimento de aplicações com funcionalidades de inteligência artificial, sem complexidade desnecessária, inspirando-se em projetos Python como LangChain e LlamaIndex. Um destaque em nota explica que o Spring AI resolve o desafio fundamental de conectar dados e APIs corporativas aos modelos de IA, ilustrado pelo diagrama "Your Data / Your APIs → Generative AI" presente na página.

#### Provedores de Modelos Suportados

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-01-14h09m43s106.jpg" alt="" width="840">
</p>

A página segue exibindo a lista de funcionalidades do Spring AI, com destaque para o suporte a diversos provedores de modelos de IA — Anthropic, OpenAI (destacado em azul na imagem), Microsoft, Amazon, Google e Ollama. Também são listados os tipos de modelo suportados: Chat Completion, Embedding, Text to Image, Audio Transcription, Text to Speech e Moderation. Isso corresponde diretamente ao momento em que o instrutor comenta que o Spring AI possui interfaces prontas para se comunicar com diferentes Large Language Models, citando esses mesmos provedores.

#### Demais Recursos do Ecossistema

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-01-14h09m48s140.jpg" alt="" width="840">
</p>

Com a rolagem da página, aparecem outros recursos do Spring AI: Structured Outputs (mapeamento da saída do modelo para POJOs), suporte a diversos bancos de dados vetoriais, Tools/Function Calling, Observability, ingestão de documentos via ETL framework, avaliação de modelos de IA, Spring Boot Auto Configuration, ChatClient API, Advisors API e suporte a memória de conversa e RAG (Retrieval Augmented Generation). Esse trecho ilustra o momento em que o instrutor menciona o ChatClient como uma interface que facilita a comunicação com os modelos, além da integração com bancos de dados de vetor e do tool calling.

#### Conceitos de IA — Modelos

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-01-14h09m51s971.jpg" alt="" width="840">
</p>

A navegação avança para a seção "AI Concepts", especificamente para o tópico "Models". A página apresenta uma tabela que categoriza os modelos de IA de acordo com os tipos de entrada (linguagem/código, imagem, áudio) e saída (linguagem/código, imagem, áudio), evidenciando a especialização de cada modelo generativo (como LLMs). Esse conteúdo reforça a fala do instrutor de que o Spring AI disponibiliza uma série de componentes por meio de uma interface comum, permitindo trocar um modelo por outro sem alterar o código da aplicação.

#### Conceitos de IA — Prompts

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-01-14h09m57s683.jpg" alt="" width="840">
</p>

A imagem mostra a seção "Prompts" da documentação, explicando que o prompt é a base da entrada em linguagem natural enviada ao modelo de IA, podendo ser composto por diferentes papéis (como "system" e "user"). O texto também comenta sobre a importância da engenharia de prompt. Isso corresponde à explicação do instrutor de que o prompt é justamente a forma como se comunica ao modelo o que se deseja fazer.

#### Conceitos de IA — Tokens

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-01-14h11m45s900.jpg" alt="" width="840">
</p>

A página avança para a seção "Tokens", explicando que os tokens são as unidades básicas de processamento dos modelos de IA: na entrada, palavras são convertidas em tokens; na saída, tokens são convertidos de volta em palavras. O texto ainda destaca que tokens estão diretamente relacionados ao custo de uso dos modelos hospedados, já que a cobrança é feita com base na quantidade de tokens processados (entrada e saída). Esse trecho corresponde ao momento em que o instrutor esclarece que, diferente do embedding, o token está muito mais relacionado ao custo do uso do modelo de IA.

#### Conceitos de IA — Tool Calling

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-01-14h12m16s629.jpg" alt="" width="840">
</p>

A imagem mostra a seção "Tool Calling" da documentação, acompanhada de um diagrama que ilustra o fluxo entre a aplicação (Chat Request), o Spring AI e a Tool externa, até a geração da Chat Response. O texto explica que os LLMs ficam "congelados" após o treinamento e não conseguem acessar ou modificar dados externos por conta própria, sendo esse o problema que o mecanismo de Tool Calling resolve. Esse conteúdo corresponde exatamente à explicação do instrutor de que o tool calling consiste em informar ao LLM que ele pode acessar dados externos, como dados da própria aplicação, por meio de ferramentas registradas.

#### Criando o Projeto no IntelliJ

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-01-14h12m27s137.jpg" alt="" width="840">
</p>

A tela agora mostra a IDE IntelliJ IDEA aberta na tela de boas-vindas, exibindo uma lista de projetos remotos existentes (via WSL Ubuntu) e o botão "New Project" em destaque. Esse momento corresponde à fala do instrutor informando que utilizará o IntelliJ, por ser a IDE de sua preferência, para criar o novo projeto Spring Boot.

#### Configurando o Novo Projeto Spring Boot

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-01-14h12m43s525.jpg" alt="" width="840">
</p>

A imagem mostra a janela "New Project" do IntelliJ, com o gerador "Spring Boot" selecionado. Os campos exibidos são: Server URL (start.spring.io), Name preenchido como "budgeting", Location apontando para o diretório do usuário via WSL, Language definida como Java, JDK "Eclipse Temurin 25.0.2", Packaging "Jar" e Configuration "Properties". Isso corresponde ao momento em que o instrutor define o nome do projeto como "budgeting" e configura a versão do Java a ser utilizada (25, via SDKMAN).

#### Adicionando o BOM do Spring AI

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-01-14h15m33s006.jpg" alt="" width="840">
</p>

A imagem mostra o arquivo `build.gradle` do projeto "budgeting" já criado, com o plugin de gerenciamento de dependências do Spring configurado e a dependência do BOM (Bill of Materials) do Spring AI recém-adicionada:

```groovy
plugins {
    id 'io.spring.dependency-management' version '1.1.7'
}

group = 'dio'
version = '0.0.1-SNAPSHOT'
description = 'budgeting'

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'

    implementation platform("org.springframework.ai:spring-ai-bom:2.0.0-M4")
}

tasks.named('test') {
    useJUnitPlatform()
}
```

Isso corresponde ao trecho em que o instrutor explica que o BOM (Bill of Materials) disponibiliza um índice de dependências do Spring AI com as versões corretas já resolvidas, evitando a necessidade de declarar manualmente a versão de cada dependência relacionada.

#### Adicionando o Starter do Modelo OpenAI

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-01-14h16m52s851.jpg" alt="" width="840">
</p>

No mesmo arquivo `build.gradle`, uma nova linha é adicionada ao bloco de dependências, referente ao starter do modelo OpenAI do Spring AI (destacada em azul na imagem):

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'

    implementation platform("org.springframework.ai:spring-ai-bom:2.0.0-M4")
    implementation 'org.springframework.ai:spring-ai-starter-model-openai'
}
```

Esse trecho corresponde ao momento em que o instrutor explica que, além do BOM, é necessário adicionar a dependência específica do modelo que será utilizado — nesse caso, o modelo da OpenAI.

#### Configurando a API Key da OpenAI

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-01-14h33m56s454.jpg" alt="" width="840">
</p>

A imagem mostra o arquivo `application.properties` configurado com o nome da aplicação e a propriedade da chave da API da OpenAI referenciando uma variável de ambiente:

```properties
spring.application.name=budgeting
spring.ai.openai.api-key=${OPENAI_API_KEY}
```

Abaixo, no console de execução, é exibido um erro anterior, indicando que a chave da API ainda não havia sido configurada:

```
Caused by: java.lang.IllegalArgumentException: OpenAI API key must be set. Use the connection property: spring.ai.openai.api-key
```

Esse conteúdo corresponde à explicação do instrutor de que a propriedade `spring.ai.openai.api-key` deve receber o valor da chave, mas, por segurança, em vez de colocar o valor diretamente no arquivo, ele optou por referenciá-lo através de uma variável de ambiente chamada `OPENAI_API_KEY`, evitando expor a chave em um eventual commit do `application.properties`.

#### Configurações de Execução da Aplicação

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-01-14h34m04s549.jpg" alt="" width="840">
</p>

A imagem mostra a janela "Run/Debug Configurations" do IntelliJ, referente à configuração "BudgetingApplication", com o menu "Add Run Options" aberto exibindo diversas opções disponíveis, entre elas "Environment variables" (destacada em azul). Isso corresponde ao momento em que o instrutor explica que é possível acessar as opções de configuração da execução (VM) e, entre elas, definir variáveis de ambiente para a aplicação.

#### Cadastrando a Variável de Ambiente

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-01-14h34m37s896.jpg" alt="" width="840">
</p>

A imagem mostra a janela "Environment Variables" aberta, com a variável `OPENAI_API_KEY` cadastrada manualmente e seu respectivo valor (a chave secreta, parcialmente oculta na imagem), além da opção "Include system environment variables" marcada. Esse trecho corresponde à explicação do instrutor sobre como cadastrar manualmente a variável de ambiente contendo a chave da API diretamente na configuração de execução da aplicação no IntelliJ, para que ela seja injetada automaticamente sempre que a aplicação for executada.

#### Aplicação Executada com Sucesso

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-01-14h35m45s430.jpg" alt="" width="840">
</p>

A imagem mostra novamente o `build.gradle` com as dependências do Spring AI (BOM e starter do modelo OpenAI) já configuradas, e o console de execução exibindo o banner padrão do Spring Boot seguido do log de inicialização:

```
:: Spring Boot ::                (v4.0.5)

INFO 1871 --- [budgeting] [           main] dio.budgeting.BudgetingApplication       : Starting BudgetingApplication
INFO 1871 --- [budgeting] [           main] dio.budgeting.BudgetingApplication       : No active profile set, fa...
INFO 1871 --- [budgeting] [           main] dio.budgeting.BudgetingApplication       : Started BudgetingApplicat...

Process finished with exit code 0
```

Isso corresponde ao momento em que o instrutor executa novamente a aplicação e confirma que, com a variável de ambiente `OPENAI_API_KEY` corretamente configurada, a aplicação sobe sem erros, comprovando que a conexão com a OpenAI está pronta para ser utilizada nos próximos vídeos, quando serão abordados o Chat API, o Tool Calling, a Transcription API e a Speech API do Spring AI.
      
#### Material de Apoio Até Esta Etapa

- Arquivos do projeto nesta etapa: [budgeting_ate_o_video02.zip](./000-Midia_e_Anexos/etapas_do_codigo/budgeting_ate_o_video02.zip)
- [001-Tutorial_Budgeting_Spring_AI_Videos01a02.md](./001-Tutorial_Budgeting_Spring_AI_Videos01a02.md)


### 🟩 Vídeo 03 - Explorando o ChatModel e Modelos de Linguagem

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.05-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvendo-sua-api-inteligente-com-reconhecimento-de-fala-e-spring-boot-1/learning/3e985ba4-23c0-4cd5-b114-da91136e4198?back=/track/ntt-data-2026-ai-java-back-end

### Anotações

#### Introdução ao Spring AI e ao Chat Model

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-08h00m32s540.jpg" alt="" width="840">
</p>

A documentação oficial do Spring AI é o ponto de partida da aula. A página de introdução explica que o projeto tem como objetivo simplificar o desenvolvimento de aplicações que incorporam inteligência artificial, conectando os dados e as APIs da empresa aos modelos de IA. É a partir daqui que a integração já feita anteriormente entre um projeto Spring Boot e a Open AI será aprofundada, começando pelo primeiro serviço a ser estudado em detalhe: o Chat Model.

---

#### A página de referência do Chat Model API

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-08h01m10s066.jpg" alt="" width="840">
</p>

Na seção Reference → Models → Chat Models da documentação, o Chat Model API é apresentado como a camada responsável por integrar capacidades de chat com modelos de linguagem pré-treinados. O texto destaca que essa API foi projetada para ser uma interface simples e portátil, permitindo trocar entre diferentes modelos com o mínimo de alteração de código, e que classes auxiliares como `Prompt` e `ChatResponse` cuidam da preparação da requisição e do tratamento da resposta.

---

#### As interfaces `ChatModel` e o início de `StreamingChatModel`

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-08h01m36s604.jpg" alt="" width="840">
</p>

A documentação mostra a definição da interface `ChatModel`, que possui um método simplificado `call(String message)` para uso rápido e uma versão sobrecarregada que recebe um `Prompt` e devolve um `ChatResponse`, mais utilizada em aplicações reais:

```java
public interface ChatModel extends Model<Prompt, ChatResponse>, StreamingChatModel {

    default String call(String message) {...}

    @Override
    ChatResponse call(Prompt prompt);
}
```

---

#### A interface `StreamingChatModel`

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-08h02m00s409.jpg" alt="" width="840">
</p>

Logo abaixo, a documentação apresenta a interface `StreamingChatModel`, usada quando se deseja manter a conexão aberta e receber a resposta aos poucos — um cenário mais próximo de uma conversa de chat em tempo real, no qual o usuário vai enviando e recebendo mensagens continuamente:

```java
public interface StreamingChatModel extends StreamingModel<Prompt, ChatResponse> {

    default Flux<String> stream(String message) {...}

    @Override
    Flux<ChatResponse> stream(Prompt prompt);
}
```

---

#### A classe `Prompt`

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-08h02m06s241.jpg" alt="" width="840">
</p>

Em seguida, a documentação detalha a classe `Prompt`, que encapsula uma lista de objetos `Message` e opcionalmente as opções de configuração do modelo (`ChatOptions`). É essa estrutura que representa, de forma completa, o que será enviado ao modelo de linguagem:

```java
public class Prompt implements ModelRequest<List<Message>> {

    private final List<Message> messages;

    private ChatOptions modelOptions;

    @Override
    public ChatOptions getOptions() {...}

    @Override
    public List<Message> getInstructions() {...}

    // constructors and utility methods omitted
}
```

---

#### O diagrama da Spring AI Message API

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-08h02m23s252.jpg" alt="" width="840">
</p>

O diagrama exibido mostra como as mensagens são estruturadas dentro do Spring AI. A classe `Content` concentra o conteúdo textual principal e metadados; `Message` e `MediaContent` derivam dela; e a partir de `AbstractMessage` surgem os tipos específicos `SystemMessage`, `UserMessage`, `AssistantMessage` e `ToolResponseMessage`, cada um associado a um `MessageType` (SYSTEM, USER, ASSISTANT, TOOL). Esse é o nível mais técnico de como o Spring AI organiza a comunicação entre as partes de uma conversa.

---

#### A documentação do DeepSeek Chat

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-08h02m40s445.jpg" alt="" width="840">
</p>

Para reforçar que cada provedor de LLM tem sua própria página de referência dentro da documentação, é aberta a página do DeepSeek Chat. Ali são explicados os pré-requisitos: é necessário criar uma conta e gerar uma chave de API no DeepSeek, que deve ser configurada através da propriedade `spring.ai.deepseek.api-key`.

---

#### Configuração segura da chave via Spring Expression Language

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-08h02m56s260.jpg" alt="" width="840">
</p>

A documentação recomenda o uso do Spring Expression Language (SpEL) para referenciar uma variável de ambiente customizada em vez de expor a chave diretamente no arquivo de propriedades, aumentando a segurança no tratamento de informações sensíveis como chaves de API:

```yaml
# In application.yml
spring:
  ai:
    deepseek:
      api-key: ${DEEPSEEK_API_KEY}
```

---

#### Auto-configuração e dependência Maven do DeepSeek

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-08h03m01s524.jpg" alt="" width="840">
</p>

A seção de Auto-configuration mostra que o Spring AI fornece auto-configuração do Spring Boot para o modelo de chat do DeepSeek, bastando adicionar a dependência correspondente ao `pom.xml` do projeto Maven:

```xml
<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-starter-model-deepseek</artifactId>
</dependency>
```

---

#### A mesma dependência para projetos Gradle

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-08h03m10s086.jpg" alt="" width="840">
</p>

Para quem utiliza Gradle, a documentação apresenta a forma equivalente de declarar a dependência do starter do DeepSeek, o que evidencia como a integração de cada provedor segue o mesmo padrão de configuração dentro do ecossistema Spring:

```groovy
dependencies {
    implementation 'org.springframework.ai:spring-ai-starter-model-deepseek'
}
```

---

#### Propriedades de retentativa (retry) do DeepSeek

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-08h03m22s154.jpg" alt="" width="840">
</p>

A tabela de propriedades mostra as configurações de retentativa disponíveis com o prefixo `spring.ai.retry`, como número máximo de tentativas, duração inicial do backoff exponencial, multiplicador do intervalo, duração máxima do backoff e se erros de cliente (4xx) devem ou não disparar uma nova tentativa. Essas propriedades evitam que a aplicação sobrecarregue a API do provedor ao tentar novamente sempre no mesmo ritmo.

---

#### Configuração manual das opções de chat do DeepSeek

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-08h03m39s489.jpg" alt="" width="840">
</p>

A documentação mostra como sobrescrever, em tempo de execução, o modelo e a temperatura padrão para uma requisição específica, usando `DeepSeekChatOptions` diretamente na chamada ao `Prompt`:

```java
ChatResponse response = chatModel.call(
    new Prompt(
        "Generate the names of 5 famous pirates. Please provide the JSON response without any code ...",
        DeepSeekChatOptions.builder()
            .withModel(DeepSeekApi.ChatModel.DEEPSEEK_CHAT.getValue())
            .withTemperature(0.8f)
            .build()
    ));
```

---

#### A página de documentação do OpenAI Chat

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-08h03m55s761.jpg" alt="" width="840">
</p>

Agora a navegação passa para a página de referência do OpenAI Chat, o provedor que será efetivamente utilizado no projeto. A seção de pré-requisitos explica que é necessário criar uma conta na OpenAI e gerar um token na página de API Keys, configurando-o através da propriedade `spring.ai.openai.api-key`.

---

#### Configuração da chave de API da OpenAI

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-08h04m01s623.jpg" alt="" width="840">
</p>

Esta é exatamente a configuração já realizada no vídeo anterior: a chave da OpenAI é definida na propriedade `spring.ai.openai.api-key`, podendo ser referenciada de forma segura por meio de uma variável de ambiente com Spring Expression Language:

```properties
spring.ai.openai.api-key=<your-openai-api-key>
```

```yaml
# In application.yml
spring:
  ai:
    openai:
      api-key: ${OPENAI_API_KEY}
```

---

#### Auto-configuração do OpenAI Chat Client

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-08h04m09s772.jpg" alt="" width="840">
</p>

A seção de Auto-configuration do OpenAI explica que o Spring AI oferece auto-configuração do Spring Boot para o Chat Client, bastando adicionar a dependência do starter correspondente ao build do projeto:

```groovy
dependencies {
    implementation 'org.springframework.ai:spring-ai-starter-model-openai'
}
```

---

#### Propriedades de retry específicas do OpenAI

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-08h04m13s804.jpg" alt="" width="840">
</p>

Assim como visto para o DeepSeek, o OpenAI também possui sua própria tabela de propriedades de retentativa sob o prefixo `spring.ai.retry`, controlando número de tentativas, backoff inicial, multiplicador, backoff máximo e o comportamento diante de erros de cliente — demonstrando que, apesar de cada provedor ter particularidades, o padrão de configuração se repete.

---

#### Propriedades específicas do modelo de chat da OpenAI

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-08h05m08s215.jpg" alt="" width="840">
</p>

Aqui aparecem as propriedades mais específicas do LLM da OpenAI, como `spring.ai.openai.chat.options.model`, que aceita valores como `gpt-4o`, `gpt-4o-mini`, `gpt-4-turbo` e `gpt-3.5-turbo`, tendo `gpt-4o-mini` como padrão — provavelmente o modelo que será utilizado no projeto — além da propriedade de `temperature`, que controla o quanto a resposta pode variar ou "criar" em relação ao prompt.

---

#### Visão geral da AI Model API do Spring AI

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-08h05m54s571.jpg" alt="" width="840">
</p>

Voltando à página geral da Spring AI API, o diagrama recapitula que é através do Chat Model que se interage com a LLM, e que esse mesmo modelo pode ser usado com diferentes tipos de conteúdo: texto, imagem, transcrição de áudio, texto-para-fala, entre outros serviços que a LLM disponibiliza. As classes base `Model` e `StreamingModel` dão origem a `ChatModel`, `EmbeddingModel`, `ImageModel`, `StreamingChatModel` e `StreamingSpeechModel`.

---

#### O panorama completo das implementações de Chat Model

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-08h06m20s024.jpg" alt="" width="840">
</p>

O diagrama expandido mostra a árvore completa de implementações de `ChatModel` suportadas pelo Spring AI, com integrações a provedores como OpenAI, Anthropic, Google, Hugging Face, Ollama, Bedrock, Groq, entre muitos outros, todos acessados de forma unificada através do `ChatClient`. Isso encerra a parte de documentação e conceitos antes de o vídeo seguir para a implementação prática no código.

---

#### Abrindo o Run Configuration do projeto no IntelliJ

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-08h07m17s261.jpg" alt="" width="840">
</p>

A aula passa para o código do projeto `budgeting` no IntelliJ IDEA. O primeiro passo prático é acessar o menu de execução no canto superior direito e selecionar "Edit Configurations...", pois será necessário ajustar as variáveis de ambiente também para a execução dos testes, e não apenas para a aplicação.

---

#### A tela de Run/Debug Configurations

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-08h07m21s592.jpg" alt="" width="840">
</p>

A janela de Run/Debug Configurations é aberta, mostrando a configuração já existente `BudgetingApplication`, com sua variável de ambiente da chave da OpenAI já definida para a aplicação. A variável de ambiente configurada aqui, porém, não é automaticamente herdada pelos testes que ainda serão criados.

---

#### Criando uma nova configuração de execução

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-08h08m16s434.jpg" alt="" width="840">
</p>

Ao clicar em "New Configuration", o IntelliJ apresenta uma lista com os diversos tipos de configuração de execução disponíveis (JUnit, Karma, Kotlin, Maven, Mocha, entre outros), incluindo a opção "Spring Boot" já destacada na lista.

---

#### Localizando o tipo de configuração Gradle

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-08h08m44s633.jpg" alt="" width="840">
</p>

A lista de tipos de configuração é rolada para baixo até a opção "Gradle", que é o tipo escolhido para a execução dos testes do projeto — já que, ao rodar cada teste, o IntelliJ cria automaticamente uma configuração individual desse tipo.

---

#### Voltando à tela principal de configurações

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-08h09m46s596.jpg" alt="" width="840">
</p>

Como cada execução de teste cria uma nova configuração de Gradle isolada, seria necessário repetir a definição da variável de ambiente toda vez — o que não é prático. A solução apontada é editar diretamente o template de configuração, através do link "Edit configuration templates..." destacado na parte inferior da janela.

---

#### A janela de templates de configuração

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-08h10m02s595.jpg" alt="" width="840">
</p>

A janela "Run/Debug Configuration Templates" é aberta, listando os diversos templates disponíveis (Database Script, Docker, Gradle, Groovy, JUnit, Maven, entre outros), com o template "Spring Boot" inicialmente selecionado.

---

#### Selecionando o template do Gradle

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-08h10m09s381.jpg" alt="" width="840">
</p>

Dentro da mesma janela de templates, é selecionado especificamente o template "Gradle" — que é o tipo de configuração usado para executar os testes —, exibindo os campos de tarefas, projeto Gradle e variáveis de ambiente que poderão ser editados para valerem por padrão em todos os novos testes criados a partir dele.

---

#### Definindo a variável de ambiente no template

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-08h12m23s370.jpg" alt="" width="840">
</p>

Na janela de edição de variáveis de ambiente do template, é adicionada a variável `OPENAI_API_KEY` com seu valor (mascarado na tela), mantendo também marcada a opção "Include system environment variables", que garante que as demais variáveis do sistema continuem disponíveis.

---

#### Template do Gradle atualizado

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-08h12m47s952.jpg" alt="" width="840">
</p>

Com a variável de ambiente já aplicada ao campo "Environment variables" do template Gradle, resta clicar em "Apply" para confirmar a alteração. A partir de agora, todo novo teste criado a partir desse template herdará automaticamente a chave da OpenAI como variável de ambiente.

---

#### Criando uma nova classe Java para o teste de integração

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-08h13m56s336.jpg" alt="" width="840">
</p>

De volta à árvore de arquivos do projeto, é utilizado o menu de contexto (botão direito) sobre o pacote de testes, escolhendo a opção "New" → "Java Class" para criar a primeira classe de teste de integração com o Chat Model da OpenAI.

---

#### Nomeando a classe de teste de integração

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-08h14m58s460.jpg" alt="" width="840">
</p>

No diálogo de criação de nova classe Java, é digitado o nome `OpenAiChatModelIT`. O sufixo "IT" (Integration Test) é utilizado propositalmente, pois esse padrão de nomenclatura permite maior controle sobre quais testes são executados durante o processo de build da aplicação, diferenciando testes de integração dos testes unitários comuns.

---

#### Estrutura inicial do teste de integração

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-08h21m39s301.jpg" alt="" width="840">
</p>

A classe recém-criada é anotada com `@SpringBootTest` e com `@EnabledIfEnvironmentVariable`, garantindo que o teste só seja executado quando a variável de ambiente da chave da API estiver definida:

```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OPENAPI_API_KEY", matches = ".+")
public class OpenAiChatModelIT {

    @Test
    void should_receiveResponse_when_chatModelIsCalled() {

    }
}
```

---

#### Injetando o `OpenAiApi` via `@Autowired`

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-09h41m46s009.jpg" alt="" width="840">
</p>

Para instanciar o `OpenAiChatModel`, o código utiliza o padrão builder, passando um `OpenAiApi` obtido por injeção de dependência com `@Autowired`. Essa injeção só é possível graças à auto-configuração do Spring Boot, que já monta o `OpenAiApi` com as propriedades definidas no `application.properties`:

```java
@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OPENAPI_API_KEY", matches = ".+")
public class OpenAiChatModelIT {

    @Autowired
    OpenAiApi openAiApi;

    @Test
    void should_receiveResponse_when_chatModelIsCalled() {
        var chatModel = OpenAiChatModel.builder()
                .openAiApi(openAiApi)
                .build();
    }
}
```

---

#### Primeira tentativa de configurar as opções do chat model

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-09h45m18s425.jpg" alt="" width="840">
</p>

Nesse momento intermediário do código, é criada uma variável `options`, ainda em construção, e mantido o método `defaultOptions()` sem parâmetro no builder do chat model — um passo de transição antes de definir corretamente as opções do chat na sequência:

```java
@Test
void should_receiveResponse_when_chatModelIsCalled() {
    var options = OpenAiChatModel.builder().build();

    var chatModel = OpenAiChatModel.builder()
            .openAiApi(openAiApi)
            .defaultOptions()
            .build();
}
```

---

#### Configurando `OpenAiChatOptions` com modelo, temperatura e formato de resposta

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-09h54m13s234.jpg" alt="" width="840">
</p>

Agora a variável `options` é corretamente construída a partir de `OpenAiChatOptions.builder()`, definindo o modelo `gpt-4o-mini`, a temperatura `0.8` e o formato de resposta como texto simples. Em seguida, essas opções são passadas para o builder do `OpenAiChatModel` através de `defaultOptions(options)`:

```java
var options = OpenAiChatOptions.builder()
        .model("gpt-4o-mini")
        .temperature(0.8)
        .responseFormat(ResponseFormat.builder().type(ResponseFormat.Type.TEXT).build())
        .build();

var chatModel = OpenAiChatModel.builder()
        .openAiApi(openAiApi)
        .defaultOptions(options)
        .build();
```

---

#### As mesmas configurações definidas via `application.properties`

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-10h02m19s644.jpg" alt="" width="840">
</p>

Como alternativa à configuração manual feita em código, as mesmas opções podem ser definidas diretamente no arquivo de propriedades da aplicação, deixando a instanciação do chat model totalmente a cargo da auto-configuração do Spring Boot:

```properties
spring.application.name=budgeting
spring.ai.openai.api-key=${OPENAI_API_KEY}
spring.ai.openai.chat.options.model=gpt-4o-mini
spring.ai.openai.chat.options.temperature=0.8
spring.ai.openai.chat.options.response-format.type=TEXT
```

---

#### Injetando o `OpenAiChatModel` já configurado

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-10h02m51s862.jpg" alt="" width="840">
</p>

Com as propriedades definidas no `application.properties`, um novo campo `chatModel`, do tipo `OpenAiChatModel`, é adicionado à classe de teste e injetado via `@Autowired`, demonstrando que o Spring Boot já é capaz de montar o chat model completo automaticamente, sem a necessidade de construí-lo manualmente:

```java
@Autowired
OpenAiApi openAiApi;

@Autowired
OpenAiChatModel chatModel;
```

---

#### Comentando a construção manual do chat model

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-10h03m16s707.jpg" alt="" width="840">
</p>

Como agora o chat model já chega pronto por injeção de dependência a partir das propriedades da aplicação, todo o trecho de código que construía manualmente as `options` e o `chatModel` é comentado, evidenciando que essa etapa deixou de ser necessária:

```java
public class OpenAiChatModelIT {

    @Test
    void should_receiveResponse_when_chatModelIsCalled() {
//        var options = OpenAiChatOptions.builder()
//                .model("gpt-4o-mini")
//                .temperature(0.8)
//                .responseFormat(ResponseFormat.builder().type(ResponseFormat.Type.TEXT).build())
//                .build();
//
//        var chatModel = OpenAiChatModel.builder()
//                .openAiApi(openAiApi)
//                .defaultOptions(options)
//                .build();
    }
}
```

---

#### Chamando o chat model e validando a resposta

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-10h08m17s781.jpg" alt="" width="840">
</p>

Com o chat model já injetado, o teste chama o método `call`, passando como prompt o pedido para gerar um registro de gasto (descrição, valor em reais e local). A resposta é validada com uma asserção simples de que não está vazia, e impressa no console apenas para conferência do resultado gerado pela LLM:

```java
var chatModel = OpenAiChatModel.builder()
        .openAiApi(openAiApi)
        .defaultOptions(options)
        .build();

var response = chatModel.call("Gere um registro de budgeting, com descrição de gasto, valor em reais e local");

assertThat(response).isNotEmpty();
System.out.println(response);
```

---

#### O teste passa e a resposta gerada pela LLM é exibida

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-10h09m49s151.jpg" alt="" width="840">
</p>

A execução do teste é bem-sucedida, confirmando que a integração com a OpenAI está funcionando corretamente. No console de resultados, é possível ver a resposta gerada pela LLM: um registro de budgeting em formato de tabela, contendo itens como almoço com amigos, compra de supermercado, combustível, cinema, roupas, conta de luz e internet, cada um com sua respectiva descrição, valor e local.

---

#### Adicionando o starter web ao projeto

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-10h10m59s628.jpg" alt="" width="840">
</p>

Para avançar e expor o chat model através de um endpoint HTTP, é necessário adicionar a dependência `spring-boot-starter-web` ao `build.gradle` do projeto, complementando as dependências já existentes do Spring Boot e do Spring AI:

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'

    implementation platform("org.springframework.ai:spring-ai-bom:2.0.0-M4")
    implementation 'org.springframework.ai:spring-ai-starter-model-openai'

    implementation 'org.springframework.boot:spring-boot-starter-web'
}
```

---

#### Criando o `ChatModelController`

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-10h13m26s942.jpg" alt="" width="840">
</p>

Com a dependência web já disponível, é criada uma nova classe Java chamada `ChatModelController`, responsável por expor o chat model através de um endpoint REST, permitindo interagir com a LLM diretamente por requisições HTTP.

---

#### Implementação do endpoint que consome o chat model

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-10h18m11s523.jpg" alt="" width="840">
</p>

O controller é anotado com `@RestController` e `@RequestMapping("/api")`, recebendo o `OpenAiChatModel` por injeção de dependência via construtor — o mesmo princípio de injeção usado no teste, agora aplicado à camada web. O método `chat`, mapeado em `@GetMapping("/chat-model")`, recebe um parâmetro `prompt` e simplesmente repassa essa string para o `call` do chat model, devolvendo a resposta da LLM:

```java
@RestController
@RequestMapping("/api")
public class ChatModelController {

    private final OpenAiChatModel openAiChatModel;

    public ChatModelController(OpenAiChatModel openAiChatModel) {
        this.openAiChatModel = openAiChatModel;
    }

    @GetMapping("/chat-model")
    String chat(String prompt) {
        return this.openAiChatModel.call(prompt);
    }
}
```

---

#### Executando a aplicação com o novo endpoint

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-10h23m15s534.jpg" alt="" width="840">
</p>

Antes de testar o endpoint, é selecionada a configuração de execução `BudgetingApplication` no menu superior, e a aplicação é iniciada. O console confirma que o servidor Tomcat foi inicializado e que a aplicação Spring Boot está de pé, pronta para receber requisições.

---

#### Testando o endpoint `/api/chat-model` via HTTP Client

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-03-10h27m41s135.jpg" alt="" width="840">
</p>

Usando a ferramenta de Endpoints do IntelliJ, que identifica automaticamente o endpoint criado no controller, é enviada uma requisição de teste com o prompt "Oi":

```http
GET http://localhost:8080/api/chat-model?prompt=Oi
```

A aplicação responde com sucesso (código 200), retornando a mensagem gerada pela LLM: "Oi! Como posso ajudar você hoje?". Isso confirma, de ponta a ponta, o fluxo completo: a requisição chega ao controller, é repassada ao chat model via injeção de dependência, o chat model se comunica com a API da OpenAI usando as propriedades definidas por auto-configuração, e a resposta retorna até o cliente HTTP.

#### Material de Apoio Até Esta Etapa

- Arquivos do projeto nesta etapa: [budgeting_ate_o_video03.zip](./000-Midia_e_Anexos/etapas_do_codigo/budgeting_ate_o_video03.zip)
- [002-Tutorial_Budgeting_Spring_AI_Video03.md](./002-Tutorial_Budgeting_Spring_AI_Video03.md)


### 🟩 Vídeo 04 - ChatClient: Fluência e Contexto no Spring AI

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.05-video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvendo-sua-api-inteligente-com-reconhecimento-de-fala-e-spring-boot-1/learning/af78f4fc-cd36-4230-90cd-8ce61a3d4395

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-04-08h36m54s703.jpg" alt="" width="840">
</p>

A imagem mostra a documentação oficial do Spring AI (docs.spring.io), na página referente à **Chat Client API**. O texto da página explica que o `ChatClient` oferece uma API fluente para se comunicar com um modelo de IA, com suporte tanto ao modelo síncrono quanto ao modelo reativo (streaming). É destacado que o modelo de IA processa dois tipos principais de mensagens: mensagens de usuário, que são entradas diretas do usuário, e mensagens de sistema, geradas para orientar o comportamento da conversa — essa é justamente a separação entre prompt de sistema e prompt de usuário que caracteriza o `ChatClient` como uma API mais completa que o `ChatModel`.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-04-08h37m43s782.jpg" alt="" width="840">
</p>

Aqui a página de documentação está rolada um pouco mais para baixo, mostrando a seção "Creating a ChatClient". O trecho destaca que o `ChatClient` é criado a partir de um objeto `ChatClient.Builder`, podendo ser obtido de forma autoconfigurada pelo Spring Boot ou criado programaticamente. Isso confirma que o `ChatClient` disponibiliza um builder próprio e reaproveita toda a autoconfiguração de `ChatModel` já existente na aplicação.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-04-08h38m49s955.jpg" alt="" width="840">
</p>

A imagem mostra a IDE (IntelliJ IDEA) com a janela de criação de uma nova classe Java. O nome digitado é `OpenAiChatClientIT`, criada dentro do pacote de testes do projeto `budgeting`, ao lado da classe `OpenAiChatModelIT` já existente. Essa nova classe será usada para testar o `ChatClient` de forma equivalente ao teste de integração já feito anteriormente para o `ChatModel`.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-04-09h16m44s961.jpg" alt="" width="840">
</p>

A imagem mostra o código já escrito na classe `OpenAiChatClientIT`. A classe está anotada com `@SpringBootTest` e `@EnabledIfEnvironmentVariable`, garantindo que o teste só rode quando a variável de ambiente `OPENAI_API_KEY` estiver definida. O `OpenAiChatModel` é injetado via `@Autowired`, e a partir dele o `ChatClient` é construído usando o builder, com um prompt de sistema definindo o papel do modelo como "matemático". Em seguida, um prompt de usuário pede uma operação aritmética, e a resposta é validada com `assertThat(response).contains("0")`, além de ser impressa no console com `System.out.println`.

```java
@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
public class OpenAiChatClientIT {

    @Autowired
    OpenAiChatModel openAiChatModel;

    @Test
    void should_executeSum_when_prompted() {
        var chatClient = ChatClient.builder(openAiChatModel)
                .defaultSystem("Você é um matemático")
                .build();

        var response = chatClient.prompt("Some 10 mais 20. Depois subtraia 30 do resultado anterior. Exiba apenas o resultado final sem explicações.")
                .call()
                .content();

        assertThat(response).contains("0");
        System.out.println(response);
    }
}
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-04-09h19m14s590.jpg" alt="" width="840">
</p>

A imagem mostra o mesmo arquivo de teste, agora com um ícone de sugestão (lâmpada) ao lado da linha do `System.out.println`, indicando uma dica da IDE sobre aquela instrução. O código permanece o mesmo já apresentado na imagem anterior, pronto para ser executado.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-04-09h19m51s142.jpg" alt="" width="840">
</p>

A imagem mostra o painel de resultados de testes da IDE, indicando que o teste `should_executeSum_when_prompted` passou com sucesso ("1 test passed"). No console é possível ver a saída impressa pelo `System.out.println`, mostrando que a resposta do modelo foi "O resultado final é 0", confirmando que a asserção com `contains("0")` foi bem-sucedida — uma escolha mais flexível do que `equals`, já que o modelo pode retornar texto adicional além do número puro.


<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-04-09h35m30s840.jpg" alt="" width="840">
</p>

A imagem mostra a janela "Copy Class" da IDE, usada para duplicar a classe `ChatModelController` já existente no projeto. O novo nome definido é `ChatClientController`, mantendo o mesmo pacote `dio.budgeting`. Essa cópia servirá de ponto de partida para criar um controller equivalente ao anterior, mas utilizando o `ChatClient` em vez do `ChatModel`.


<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-04-09h38m32s227.jpg" alt="" width="840">
</p>

A imagem mostra o resultado final: o código da classe `ChatClientController`, anotada com `@RestController` e `@RequestMapping("/api")`, injetando um `ChatClient` construído a partir de um `ChatClient.Builder` recebido no construtor. O endpoint `/chat`, mapeado com `@GetMapping`, recebe um prompt de usuário e retorna o conteúdo da resposta obtida via `this.chatClient.prompt().user(prompt).call().content()`. Ao lado, o painel de Endpoints da IDE mostra uma requisição de teste para `GET /api/chat?prompt=Bom dia`, cuja resposta retornada pelo modelo foi "Bom dia! Como posso ajudar você hoje?", confirmando que o controller está funcionando corretamente.

```java
package dio.budgeting;

import ...

@RestController
@RequestMapping("/api")
public class ChatClientController {

    private final ChatClient chatClient;

    public ChatClientController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/chat")
    String chat(String prompt) {
        return this.chatClient.prompt().user(prompt).call().content();
    }
}
```
      
#### Material de Apoio Até Esta Etapa

- Arquivos do projeto nesta etapa: [budgeting_ate_o_video04.zip](./000-Midia_e_Anexos/etapas_do_codigo/budgeting_ate_o_video04.zip)
- [003-Tutorial_Budgeting_Spring_AI_Video04.md](./003-Tutorial_Budgeting_Spring_AI_Video04.md)


### 🟩 Vídeo 05 - Tool Calling: Executando Funções Reais com IA

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.05-video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvendo-sua-api-inteligente-com-reconhecimento-de-fala-e-spring-boot-1/learning/d358b84d-acee-4119-a443-cd2c9327ac70

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-04-12h15m13s883.jpg" alt="" width="840">
</p>

Esta tela mostra a página oficial da documentação do Spring AI dedicada a Tool Calling. O texto define o conceito como um padrão comum em aplicações de IA, também conhecido como Function Calling, que permite a um modelo interagir com um conjunto de APIs ou ferramentas para ampliar suas capacidades. A página destaca dois usos principais para as tools: **Information Retrieval** (buscar informações em fontes externas, como bancos de dados, serviços web ou motores de busca, para complementar o conhecimento do modelo) e **Taking Action** (executar ações em um sistema, como enviar um e-mail, criar um registro ou disparar um fluxo de trabalho). Essa introdução marca o início do assunto do dia, que dá sequência ao projeto de API inteligente com reconhecimento de fala, apresentando o Tool Calling como uma forma de trazer mais contexto ao Chat Client em vez de depender apenas da LLM.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-04-12h16m24s035.jpg" alt="" width="840">
</p>

A documentação exibe um exemplo prático de implementação de uma tool para obter a data e hora atuais no fuso horário do usuário, usando a classe `DateTimeTools`. O método é anotado com `@Tool`, cuja `description` explica ao modelo o propósito da ferramenta, permitindo que ele decida quando chamá-la.

```java
class DateTimeTools {

    @Tool(description = "Get the current date and time in the user's timezone")
    String getCurrentDateTime() {
        return LocalDateTime.now().atZone(LocaleContextHolder.getTimeZone().toZoneId()).toString();
    }

}
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-04-12h18m07s703.jpg" alt="" width="840">
</p>

Na sequência da documentação, é mostrado como disponibilizar a tool para o modelo através do `ChatClient`, passando uma instância de `DateTimeTools` pelo método `.tools()`. Quando o modelo precisa saber a data atual, ele solicita a chamada da ferramenta e usa o resultado para gerar a resposta final ao prompt original.

```java
ChatModel chatModel = ...

String response = ChatClient.create(chatModel)
        .prompt("What day is tomorrow?")
        .tools(new DateTimeTools())
        .call()
        .content();

System.out.println(response);
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-04-12h20m33s878.jpg" alt="" width="840">
</p>

A tela agora muda para a IDE, mostrando a caixa de diálogo "Copy Class" sendo usada para duplicar a classe de teste `OpenAiChatClientIT`, criando uma nova classe chamada `ToolCallingIT` dentro do mesmo pacote `dio.budgeting`. Essa é a etapa inicial de criação do teste de integração dedicado a demonstrar o Tool Calling, reaproveitando a estrutura do teste de Chat Client já existente.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-04-12h23m54s799.jpg" alt="" width="840">
</p>

Com a nova classe já criada, o código mostra o teste `ToolCallingIT`, praticamente idêntico ao teste de Chat Client anterior: o `ChatClient` é configurado com um `defaultSystem` instruindo o modelo a se comportar como um matemático, e o prompt pede para somar 10 mais 20 e depois subtrair 30 do resultado anterior.

```java
package dio.budgeting;

import ...

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
public class ToolCallingIT {

    @Autowired
    OpenAiChatModel openAiChatModel;

    @Test
    void should_executeSum_when_prompted() {
        var chatClient = ChatClient.builder(openAiChatModel).defaultSystem("Você é um matemático").build();

        var response = chatClient.prompt("Some 10 mais 20. Depois subtraia 30 do resultado...")
                .call().content();

        assertThat(response).contains("0");
        System.out.println(response);
    }
}
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-04-12h25m36s005.jpg" alt="" width="840">
</p>

Dentro da classe de teste, é criada a classe `MathTools`, com dois métodos anotados com `@Tool`: `sum`, que soma dois números inteiros, e `diff`, que subtrai um do outro. Cada anotação recebe uma `description` explicando o que o método faz, informação usada pelo modelo para decidir qual ferramenta chamar de acordo com o prompt recebido.

```java
static class MathTools {
    @Tool(description = "soma dois números inteiros, a e b")
    public int sum(int a, int b) {
        return a + b;
    }

    @Tool(description = "subtrai dois números inteiros, a e b")
    public int diff(int a, int b) {
        return a - b;
    }
}
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-04-12h32m35s942.jpg" alt="" width="840">
</p>

O painel de debug mostra um erro de execução: `MathTools.Did you mean to pass a ToolCallback or ToolCallbackProvider? No annotated methods found in class...`. O erro ocorreu porque a tool estava sendo passada no método `.tools()` durante a execução do prompt, e não na configuração do `ChatClient`. A correção é usar `.defaultTools(new MathTools())` já na construção do `ChatClient`, garantindo que o modelo reconheça a ferramenta corretamente.

```java
var chatClient = ChatClient.builder(openAiChatModel)
        .defaultSystem("Você é um matemático")
        .defaultTools(new MathTools())
        .build();

var response = chatClient.prompt("Some 10 mais 20. Depois subtraia 30 do resultado anterior. Exiba apenas o resu...")
        .call().content();

assertThat(response).contains("0");
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-04-12h38m27s752.jpg" alt="" width="840">
</p>

Com a configuração corrigida, o debug é executado novamente, desta vez parando em um breakpoint dentro do método `sum`. O painel de variáveis confirma que os valores `a = 10` e `b = 20` foram interpretados corretamente a partir do prompt "Some 10 mais 20", mostrando que o modelo conseguiu identificar a tool certa e extrair os argumentos numéricos corretos para a chamada.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-04-12h38m56s941.jpg" alt="" width="840">
</p>

O painel de resultados de teste mostra "1 test passed", confirmando que a asserção `assertThat(response).contains("0")` foi satisfeita. Diferente da execução anterior via apenas a LLM (que respondia incorretamente), agora o resultado calculado pelas tools de soma e subtração é retornado diretamente na resposta.


<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-04-12h40m16s120.jpg" alt="" width="840">
</p>

O arquivo `application.properties` é editado para adicionar uma configuração extra de logging, elevando o nível de log do pacote `org.springframework.ai` para `DEBUG`. Essa mudança permite visualizar nos logs, com mais clareza, o momento em que a execução passa pelas chamadas de tool calling.

```properties
spring.application.name=budgeting
spring.ai.openai.api-key=${OPENAI_API_KEY}

spring.ai.openai.chat.options.model=gpt-4o-mini
spring.ai.openai.chat.options.response-format.type=TEXT

logging.level.org.springframework.ai=DEBUG
```

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-08-04-12h42m10s376.jpg" alt="" width="840">
</p>

Com o novo nível de log ativado, o teste é executado novamente e o painel de resultados mostra "1 test passed" acompanhado de um log detalhado do Spring AI: são registradas as chamadas ao `DefaultToolCallingManager` e ao `MethodToolCallback`, evidenciando a execução das tools `sum` e `diff` (esta chamada uma segunda vez) e a conversão dos resultados para JSON antes de retornarem ao modelo. Isso confirma visualmente que, desta vez, o resultado foi resolvido pelos métodos Java criados, e não diretamente pela LLM.
     
#### Material de Apoio Até Esta Etapa

- Arquivos do projeto nesta etapa: [budgeting_ate_o_video05.zip](./000-Midia_e_Anexos/etapas_do_codigo/budgeting_ate_o_video05.zip)
- [yyyyyyyyyyyy](./xxxxxxxxxxxxxxxxx.md)


### 🟩 Vídeo 06 - Transcription API: Transformando Áudio em Texto

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.05-video_06.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvendo-sua-api-inteligente-com-reconhecimento-de-fala-e-spring-boot-1/learning/68270c44-f264-4073-b102-a7eb9d2c3284?back=/track/ntt-data-2026-ai-java-back-end

### 🟩 Vídeo 07 - Speech API: Sintetizando Voz com Text-to-Speech

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.05-video_07.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvendo-sua-api-inteligente-com-reconhecimento-de-fala-e-spring-boot-1/learning/ad624205-af61-4e2c-be7e-1bdc188a8f26?back=/track/ntt-data-2026-ai-java-back-end

### 🟩 Vídeo 08 - Integração do Assistente: Orquestrando o Fluxo de Budget

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.05-video_08.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvendo-sua-api-inteligente-com-reconhecimento-de-fala-e-spring-boot-1/learning/afaf83bb-585f-4293-a3f8-09adf880e0af?back=/track/ntt-data-2026-ai-java-back-end

### 🟩 Vídeo 09 - Persistência e Infraestrutura: Configurando o Banco com Docker

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.05-video_09.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvendo-sua-api-inteligente-com-reconhecimento-de-fala-e-spring-boot-1/learning/b7250107-c95a-4bb9-b62b-2dca8d0c29b7?back=/track/ntt-data-2026-ai-java-back-end

### 🟩 Vídeo 10 - Exposição REST: Implementando o TransactionController

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.05-video_10.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvendo-sua-api-inteligente-com-reconhecimento-de-fala-e-spring-boot-1/learning/2aee74d9-9d18-4af8-bd20-53fa862394a6?back=/track/ntt-data-2026-ai-java-back-end

### 🟩 Vídeo 11 - Endpoint de Transcrição: Integrando Áudio ao Controller

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.05-video_11.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvendo-sua-api-inteligente-com-reconhecimento-de-fala-e-spring-boot-1/learning/cffe330b-6114-43cf-8b7d-f79869400c6c?back=/track/ntt-data-2026-ai-java-back-end

### 🟩 Vídeo 12 - Roadmap e Auditoria: Evoluindo a API Inteligente

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.05-video_12.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/desenvolvendo-sua-api-inteligente-com-reconhecimento-de-fala-e-spring-boot-1/learning/c494eb75-1917-43ac-8251-42553137870a?back=/track/ntt-data-2026-ai-java-back-end


## Entendendo o Desafio

Agora é a sua hora de praticar, aprender fazendo e evoluir um projeto completo com **Spring Boot + Inteligência Artificial**. Neste desafio, você vai trabalhar com uma **API inteligente** desenvolvida com **Spring AI**. O projeto mostra como integrar recursos de IA a uma aplicação Java, mantendo uma arquitetura organizada e pronta para evoluir. Antes de começar, acesse os repositórios do projeto:

**Repositório Com Todos Os Conteúdos Sobre Spring:**

[https://github.com/digitalinnovationone/dio-spring-boot-learning-track](https://www.google.com/url?sa=E&source=gmail&q=https://github.com/digitalinnovationone/dio-spring-boot-learning-track)

**Pasta Com Detalhes Sobre O Projeto Final:**

[https://github.com/digitalinnovationone/dio-spring-boot-learning-track/blob/main/06-spring-ai/README.md](https://www.google.com/url?sa=E&source=gmail&q=https://github.com/digitalinnovationone/dio-spring-boot-learning-track/blob/main/06-spring-ai/README.md)

Nas aulas, o expert Puanã construiu os principais pontos junto com você. Por isso, use o projeto como base, revise os conteúdos sempre que precisar e depois evolua a solução com as suas próprias ideias.

---

### O Que Criar

O objetivo é evoluir uma API de orçamento que usa IA para processar comandos de voz relacionados a transações financeiras. Na prática, o fluxo principal é:

* Receber um arquivo de áudio enviado pelo cliente;
* Transformar o áudio em texto;
* Usar IA para entender a intenção do comando;
* Executar uma função real da aplicação;
* Criar ou consultar transações financeiras;
* Gerar uma resposta final para a pessoa usuária.

O foco não é apenas usar IA. O mais importante é entender como conectar IA com uma aplicação real, respeitando as responsabilidades do código e a organização do projeto.

### Como Fazer

Siga o caminho apresentado nas aulas e no repositório. O projeto passa por temas importantes, como:

* Configuração do Spring AI;
* Integração com modelos de linguagem;
* Uso do ChatClient;
* Tool Calling para executar funções reais;
* Transcrição de áudio em texto;
* Geração de voz a partir de texto;
* Persistência de dados;
* Exposição de endpoints REST;
* Evolução e auditoria da API.

Se você ainda está começando, tudo bem. Primeiro, tente executar o projeto como foi apresentado. Depois, escolha uma melhoria simples para implementar.

### Ideias Para Evoluir

Depois de entender o projeto base, você pode evoluir a API de diferentes formas:

* Adicionar novos tipos de consulta financeira;
* Melhorar as respostas geradas pela IA;
* Criar novas ferramentas para o Tool Calling;
* Adicionar validações antes de salvar uma transação;
* Melhorar os endpoints REST;
* Criar testes para os principais fluxos;
* Documentar melhor como usar a API;
* Propor uma nova ideia de assistente usando a mesma base técnica.

Escolha uma evolução compatível com o seu momento. Uma melhoria pequena, bem explicada e funcionando, vale muito mais do que uma ideia grande incompleta.

### Use o DIO Agent Como Apoio

Durante o desafio, você também pode usar o **DIO Agent** como um parceiro de estudos. Ele pode te ajudar a entender o desafio, revisar conceitos de Spring, organizar o raciocínio, melhorar sua documentação e destravar dúvidas sem entregar tudo pronto. Você pode começar com um prompt simples como este:

```text
Repositório da trilha:
https://github.com/digitalinnovationone/dio-spring-boot-learning-track

Pasta do projeto final:
https://github.com/digitalinnovationone/dio-spring-boot-learning-track/blob/main/06-spring-ai/README.md

Quero evoluir o projeto com uma melhoria simples.

Me ajude a entender:
- O objetivo do projeto;
- O fluxo principal da API;
- Onde posso fazer uma melhoria;
- Quais arquivos preciso revisar;
- Como explicar minha entrega no README.

Importante: não quero uma resposta pronta para copiar. Quero entender o processo e construir minha própria solução.

```

Use o **DIO Agent** como apoio, mas lembre-se: a entrega final deve refletir suas escolhas, seus testes e seu aprendizado.

### O Que Entregar

Dê um Fork no repositório do expert ou crie seu próprio repositório no GitHub com a sua versão do projeto. No `README.md`, explique de forma simples:

* O que o projeto faz;
* Como executar a aplicação;
* Qual melhoria você implementou;
* Quais tecnologias foram usadas;
* Como testar o fluxo principal;
* O que você aprendeu durante o desafio.

Se você fez prints, exemplos de requisições, testes ou anotações, inclua também. Isso ajuda a mostrar seu processo de aprendizagem.

### Resultado Esperado

Ao final, você terá um projeto público mostrando como usar **Spring Boot + Spring AI** para criar uma API inteligente com recursos de texto, áudio e execução de funções reais.

Comece pelo projeto base, avance com calma e evolua um passo de cada vez. O importante é entender o fluxo, testar a solução e mostrar claramente o que você construiu.

Bons estudos e bom projeto! 🚀

# Certificado: 

- Link na plataforma: 
- Certificado em pdf: