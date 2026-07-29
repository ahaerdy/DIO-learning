## Instrutor

- Thiago Poiani (Principal Engineer at Skip)
- Contato Linkedin: / [thpoiani](https://www.linkedin.com/in/thpoiani/)

## Parte 1 - Introdução ao Consumindo APIs Externas

### 🟩 Vídeo 01 - Introdução ao consumindo APIs Externas

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.04-video_01.webm" type="video/webm">
  Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/consumindo-apis-externas-com-o-spring-cloud-openfeign/learning/ceddcc80-24c5-4e0d-b0a8-45fcbf3d5602?autoplay=1

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-29-10h10m34s052.jpg" alt="" width="840">
</p>

Slide de abertura do curso "Consumindo APIs Externas com o Spring Cloud OpenFeign", parte da Jornada Tech. À direita, a agenda numerada de 01 a 08 antecipa o percurso da aula: introdução ao consumo de APIs externas, setup do projeto de compliance, modelagem de empresas com Spring Data, estruturação de use cases, monitoramento de requisições e respostas, configuração de cenários de exceção, consumo de dados complexos e estratégias de tolerância a falhas. O tópico em destaque, "Introdução ao consumindo APIs Externas", indica que a aula abre com os fundamentos conceituais antes de avançar para a implementação prática.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-29-10h10m54s067.jpg" alt="" width="840">
</p>

Slide "Agenda do Curso", que resume a estrutura macro do conteúdo em três blocos principais: Fundamentos e Conceitos, Estudo de Caso (desdobrado em etapas de desenvolvimento) e Roadmap do Aluno. Essa organização confirma o formato anunciado na abertura: primeiro a base teórica sobre consumo de APIs REST, em seguida a construção prática de uma aplicação de estudo de caso, e por fim um vídeo de encerramento com desafios e sugestões de aprofundamento para quem está acompanhando o curso.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-29-10h10m58s698.jpg" alt="" width="840">
</p>

Slide "De Provedor de Dados a Orquestrador", que contrasta dois papéis de um servidor. Do lado esquerdo, o "Backend Isolado" tradicional apenas recebe requisições de clientes (celular e navegador) e serve dados do próprio banco. Do lado direito, o "Backend Consumidor" mostra o mesmo servidor emitindo requisições ativamente para uma nuvem, um serviço externo e um banco de dados. A ideia central é que um servidor não é apenas um provedor passivo de dados: em um ecossistema distribuído moderno, ele também atua como consumidor, integrando-se a APIs de terceiros ou a outros microsserviços para entregar valor.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-29-10h11m01s220.jpg" alt="" width="840">
</p>

Slide "HTTP: O Idioma dos Sistemas Distribuídos", que ilustra a comunicação entre "Nossa Aplicação" e uma "API Externa" através do protocolo HTTP, representado como um canal com pacotes de dados trafegando nos dois sentidos. A mensagem do slide é que máquinas construídas em tecnologias diferentes precisam de uma semântica rígida, universal e previsível para conversar entre si — e é exatamente esse papel que o protocolo HTTP cumpre. Assim como um site se comunica com um servidor por meio de uma requisição indo pela internet, um servidor também pode fazer requisições para outro servidor ou para uma API externa, usando esse mesmo canal.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-29-10h11m05s714.jpg" alt="" width="840">
</p>

Slide "A Anatomia de uma Integração Perfeita", apresentando uma "Tabela Periódica da Semântica Web" que cruza os verbos HTTP (GET, POST, PUT/PATCH, DELETE) no eixo X com as faixas de código de resposta (2xx de sucesso, 4xx de erro do cliente, 5xx de erro do servidor) no eixo Y. A célula em destaque combina POST com a faixa 2xx, especificamente o código 201, indicando "Recurso Criado com Sucesso". Essa é a essência do REST: usar a semântica do HTTP para expressar intenções e resultados — o verbo indica a ação desejada (por exemplo, GET para buscar um recurso, POST para criar) e o código de status confirma o que de fato aconteceu na requisição, formando um "contrato inquebrável" entre cliente e servidor. A imagem não contém código-fonte, apenas uma tabela conceitual.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-29-10h11m10s964.jpg" alt="" width="840">
</p>

Slide "A Ilusão da Comunicação Simples", que traz um gráfico de rosca mostrando como o tempo do desenvolvedor costuma ser gasto em uma integração manual: 80% em infraestrutura (gerenciamento manual de sockets e parsing arriscado de JSON) contra apenas 20% em lógica real de negócio. Ao lado, uma lista detalha essas fontes de esforço: abertura e fechamento manual de conexões (sockets), configuração imperativa e verbosa, tradução manual de strings JSON para objetos e tratamento de erros de rede sem contexto de domínio. O slide reforça um ponto prático: fazer uma integração HTTP "na unha" consome bastante tempo — criando a conexão, enviando a requisição e convertendo a resposta JSON em objeto — antes mesmo de o desenvolvedor tocar na regra de negócio propriamente dita. A imagem não apresenta código-fonte, apenas um gráfico e uma lista conceitual.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-29-10h11m14s123.jpg" alt="" width="840">
</p>

Slide "A Era Declarativa com Spring Cloud OpenFeign", com o subtítulo "Diga o que você quer, não como fazer". A comparação visual mostra um bloco denso de texto cinza representando a abordagem "Imperativo (Legado)" transformando-se, por uma seta, em um bloco enxuto de poucas linhas representando o "Declarativo (OpenFeign)". Três blocos explicam essa mudança: o framework assume todo o trabalho pesado de roteamento, a implementação é gerada dinamicamente em tempo de execução, e a mecânica de rede torna-se invisível para quem programa. É aqui que entra o OpenFeign como biblioteca do Spring que elimina a necessidade de código imperativo para integrações: não é preciso criar a conexão nem mapear manualmente o JSON para um objeto, pois isso pode ser feito de modo declarativo, por meio de anotações. A imagem é conceitual e não contém código real, apenas uma representação estilizada de blocos de texto.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-29-10h11m16s566.jpg" alt="" width="840">
</p>

Slide "Mudança de Foco Arquitetural", organizado como uma tabela comparativa entre a "Abordagem Manual (Antiga)" e a "Abordagem OpenFeign (Nova)" em quatro dimensões: foco do código (implementação da rede vs. declaração do contrato de negócio), mapeamento HTTP (hardcoded/manipulação de strings vs. anotações nativas com semântica limpa), conversão de dados (parsing manual explícito com Jackson/Gson vs. parsing automático e transparente) e manutenibilidade (altíssima complexidade e refatoração frágil vs. simplicidade absoluta baseada em interfaces). Essa tabela sintetiza o ganho de produtividade discutido na aula: ao delegar o boilerplate de infraestrutura para o OpenFeign, o desenvolvedor passa a dedicar seu tempo à construção das regras de negócio e do domínio da aplicação, em vez de reescrever repetidamente a mecânica de comunicação HTTP. Não há código-fonte na imagem, apenas uma tabela comparativa.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-29-10h11m18s698.jpg" alt="" width="840">
</p>

Slide "O Verdadeiro Valor dos REST Clients", com um gráfico de rosca invertido em relação ao slide anterior sobre a "Ilusão da Comunicação Simples": agora 95% do esforço é atribuído à lógica de negócio e foco no domínio, contra apenas 5% de declaração de contrato e infraestrutura. Ao lado, os textos "Viabilizadores de Escala" e "Isolamento de Domínio" reforçam que REST Clients não são apenas um facilitador de código, mas o alicerce de arquiteturas distribuídas resilientes: ao delegar a complexidade da rede ao Spring Cloud OpenFeign, a aplicação permanece pura, focada no domínio e imune ao caos do ecossistema externo. É a conclusão direta da comparação entre as abordagens manual e declarativa — o tempo do desenvolvedor deixa de ser consumido por integração e passa a ser investido, quase que integralmente, no que realmente importa para o negócio. A imagem não contém código-fonte, apenas um gráfico e blocos de texto explicativo.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-07-29-10h11m21s138.jpg" alt="" width="840">
</p>

Slide "Compliance Digital: O Escudo de KYC e AML", que introduz o estudo de caso do curso por meio de uma ilustração estilo mapa mental. À esquerda, o "Desafio do Risco no Onboarding" reúne os conceitos de KYC (Know Your Customer), descrito como o processo crítico de identificação para validar a identidade do cliente no início do relacionamento, e AML (Anti-Money Laundering), ligado à mitigação de riscos por meio de dados externos vitais para segurança e legalidade. À direita, o slide detalha a "Integração e Resiliência no Mundo Real": provedores de sanções (simulação de consultas a listas globais, como a OpenSanctions), teste de estresse da API (simular latência e falhas para evitar travamentos) e resiliência obrigatória, já que o serviço consumido não é perfeito e a aplicação precisa lidar com respostas lentas e quedas. Isso conecta diretamente com o estudo de caso anunciado: uma aplicação que mantém empresas cadastradas e consulta APIs mocadas para verificar se essas empresas possuem sanções ou diretores classificados como PEP (pessoa politicamente exposta), sinalizando risco de compliance como o exigido, por exemplo, no controle de restaurantes integrados a uma plataforma de delivery.      


### 🟩 Vídeo 02 - Setup do Projeto Compliance

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.04-video_02.webm" type="video/webm">
  Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/consumindo-apis-externas-com-o-spring-cloud-openfeign/learning/2abe0939-7196-418c-9611-67ec341f8179?autoplay=1

### 🟩 Vídeo 03 - Modelando Empresas com Spring Data

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.04-video_03.webm" type="video/webm">
  Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/consumindo-apis-externas-com-o-spring-cloud-openfeign/learning/e480f1e5-fcaa-4a50-9e9b-0cf3f301b652?autoplay=1

### 🟩 Vídeo 04 - Estruturando Use Cases

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.04-video_04.webm" type="video/webm">
  Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/consumindo-apis-externas-com-o-spring-cloud-openfeign/learning/53dd9050-589d-44d6-8f99-1536a9835c86?autoplay=1

### 🟩 Vídeo 05 - Monitorando Requisições e Respostas

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.04-video_05.webm" type="video/webm">
  Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/consumindo-apis-externas-com-o-spring-cloud-openfeign/learning/d8b71ec3-0d63-44ad-a6a9-dbc2bf06e0fb?autoplay=1

### 🟩 Vídeo 06 - Configurando Cenários de Exceção

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.04-video_06.webm" type="video/webm">
  Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 07 - Consumindo Dados Complexos

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.04-video_07.webm" type="video/webm">
  Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 08 - Estratégias de Tolerância a Falhas

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.04-curso.04-video_08.webm" type="video/webm">
  Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

##  Materiais de Apoio

# Certificado: Consumindo APIs Externas com a Spring Cloud OpenFeign

- Link na plataforma: 
- Certificado em pdf: