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

### 🟩 Vídeo 03 - Explorando o ChatModel e Modelos de Linguagem

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.05-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 04 - ChatClient: Fluência e Contexto no Spring AI

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.05-video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 05 - Tool Calling: Executando Funções Reais com IA

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.05-video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 06 - Transcription API: Transformando Áudio em Texto

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.05-video_06.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 07 - Speech API: Sintetizando Voz com Text-to-Speech

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.05-video_07.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 08 - Integração do Assistente: Orquestrando o Fluxo de Budget

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.05-video_08.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 09 - Persistência e Infraestrutura: Configurando o Banco com Docker

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.05-video_09.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 10 - Exposição REST: Implementando o TransactionController

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.05-video_10.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 11 - Endpoint de Transcrição: Integrando Áudio ao Controller

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.05-video_11.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 12 - Roadmap e Auditoria: Evoluindo a API Inteligente

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.05-video_12.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 13 - Entendendo o Desafio

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.05-video_13.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

##  Materiais de Apoio

# Certificado: 

- Link na plataforma: 
- Certificado em pdf: