## Instrutor:

- Juliana Mascarenhas (Tech Education Specialist / Sócia (Content Creator) @SimplificandoRedes / Me Modelagem Computacional / Cientista de dados)
- Contato Linkedin: / [juliana-mascarenhas-ds](https://www.linkedin.com/in/juliana-mascarenhas-ds/)

## Parte 1 - Otimização de Modelo de Dados com Foco em Desempenho no Power BI

### 🟩 Vídeo 01 - O que é otimização no Power BI?

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.03-video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/otimizacao-de-modelo-de-dados-com-foco-em-desempenho-no-power-bi/learning/6d6d7864-64e2-4c92-86e2-1c2eb847ebe4?autoplay=1

O vídeo aborda a transição entre a criação de relatórios visualmente atraentes e a entrega de soluções de alta performance. O foco central é a otimização de modelos de dados, garantindo que os relatórios não apenas forneçam insights valiosos, mas o façam de maneira rápida, eficiente e escalável, evitando gargalos comuns quando os projetos saem do ambiente de teste para a produção.

### Anotações

#### Otimizando Modelos com Foco em Performance — Formação Power BI Analyst

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-12h39m34s708.jpg" alt="" width="840">
</p>

Slide de abertura da aula ministrada por **Juliana Mascarenhas**, Tech Education Specialist da DIO, Mestre em Modelagem Computacional e Cientista de Dados. O tema central é **"Otimizando Modelos com Foco em Performance"**, inserido na trilha da **Formação Power BI Analyst**. Este módulo dá continuidade aos conteúdos anteriores — criação de visuais, ETL, modelagem de dados e modelagem dimensional — e introduz agora os fundamentos de otimização de desempenho em relatórios do Power BI.

#### Etapa 1 — Fundamentos de Otimização com Power BI

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-12h39m39s481.jpg" alt="" width="840">
</p>

Slide de transição que marca o início da **Etapa 1: Fundamentos de Otimização com Power BI**. Não há código nesta imagem. O slide sinaliza que a aula entrará em conceitos fundamentais sobre o que é otimização, por que ela importa e quais são suas consequências práticas no contexto de relatórios Power BI.

#### O que é otimização?

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-12h39m40s899.jpg" alt="" width="840">
</p>

O slide apresenta a definição formal de **otimização** no contexto de modelos de dados:

> **Realização de modificações no estado atual do modelo de dados a fim de executar suas operações com maior eficiência. Dessa forma, aumentamos seu desempenho.**

Essa definição delimita o escopo do conceito: otimizar não é refazer o relatório do zero, mas intervir cirurgicamente no modelo existente para que ele processe e entregue informações com mais eficiência. O objetivo final é sempre o aumento de desempenho — tanto na atualização dos dados quanto na renderização dos visuais para o usuário final.

#### Desempenho? Pra que?

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-12h39m45s262.jpg" alt="" width="840">
</p>

O slide destaca uma distinção crítica que muitos analistas negligenciam: o **desempenho do relatório em fase de testes** e o **desempenho do relatório em fase de produção** são realidades diferentes.

Durante o desenvolvimento, o relatório roda em ambiente controlado, geralmente com poucos usuários e volume de dados reduzido. Ao ser publicado e consumido por múltiplos usuários simultaneamente, surgem gargalos que não existiam na fase de testes. Compreender essa diferença é o ponto de partida para justificar o investimento em otimização desde o início do projeto.

#### O que impacta na visão do usuário?

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-12h39m47s148.jpg" alt="" width="840">
</p>

Do ponto de vista do usuário final, o mau desempenho se manifesta de duas formas objetivas:

- **Lentidão no carregamento** — páginas do relatório demoram mais do que o aceitável para abrir.
- **Demora na atualização dos visuais** — gráficos e tabelas levam tempo excessivo para refletir filtros e interações.

Ambas as situações geram uma **experiência negativa**, o que contraria o propósito de qualquer relatório analítico: entregar informação de forma rápida, clara e confiável. Um relatório visualmente impecável perde valor se o usuário precisar esperar para interagir com ele.

#### Qual o motivo do mau desempenho?

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-12h39m51s430.jpg" alt="" width="840">
</p>

O slide aponta que, em **90% dos casos**, o mau desempenho de um relatório Power BI tem origem em um (ou na combinação) dos seguintes fatores:

- **Modelagem inadequada** — estrutura de tabelas mal planejada, relações incorretas ou desnecessariamente complexas.
- **Mau uso da linguagem DAX** — funções mal construídas ou escolhas de medidas que consomem mais recursos do que o necessário.
- **Combinação dos dois** — o caso mais comum na prática.

O número 90% reforça que esses dois problemas dominam a maioria dos diagnósticos de performance, tornando-os o foco prioritário de qualquer processo de otimização.

#### Quais as vantagens da otimização?

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-12h39m56s929.jpg" alt="" width="840">
</p>

O slide elenca os benefícios diretos de um modelo de dados bem otimizado:

- **Melhor experiência do usuário** — navegação fluida e interações responsivas.
- **Desempenho e bons resultados** — relatório robusto, capaz de sustentar o crescimento dos dados ao longo do tempo.
- **Cliente satisfeito** — seja um cliente interno ou externo, a qualidade percebida do relatório está diretamente ligada à sua velocidade e confiabilidade.

Tratar os problemas de desempenho durante o desenvolvimento evita retrabalho custoso na fase de produção e permite manter o relatório otimizado de forma contínua ao longo de sua vida útil.

#### Processo de otimização

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-12h39m59s384.jpg" alt="" width="840">
</p>

O slide lista as principais diretrizes práticas do processo de otimização de um modelo de dados no Power BI:

- **Garantir que os tipos de dados corretos sejam usados** — tipos inadequados aumentam o consumo de memória e tornam os cálculos mais lentos.
- **Excluir colunas e linhas desnecessárias** — importar apenas o que será efetivamente utilizado nos relatórios.
- **Evitar valores repetidos** — redundâncias aumentam o tamanho do modelo sem agregar valor analítico.
- **Substituir colunas numéricas por medidas** — medidas são calculadas sob demanda, reduzindo o volume de dados armazenados no modelo.
- **Reduzir cardinalidades** — colunas com muitos valores únicos exigem mais recursos para processar relacionamentos e filtros.
- **Analisar metadados do modelo** — assim como bancos de dados relacionais, o Power BI expõe metadados que permitem identificar gargalos.
- **Resumir dados sempre que possível** — trabalhar com dados agregados em vez de dados granulares reduz o volume processado e acelera as consultas.

#### Processo de otimização — Fluxo de dados no Power BI

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-12h40m09s479.jpg" alt="" width="840">
</p>

O slide ilustra o **fluxo completo de dados** no ecossistema Power BI, destacando um princípio central de otimização:

> **Diminua o tamanho dos dados para otimizar o desempenho.**

O fluxo representado segue a seguinte sequência:

1. **Fonte de dados** (tabelas externas) → a seleção e filtragem dos dados deve acontecer **na origem**, antes de qualquer importação.
2. **Power BI Desktop** → os dados já filtrados são transformados e servem de base para a criação dos relatórios.
3. **Serviço do Power BI** → o relatório é publicado na nuvem.
4. **Navegador e aplicativos móveis** → os usuários finais consomem o relatório por diferentes canais.

A recomendação prática é: sempre que possível, aplicar filtros, remover colunas e agregar dados **antes** de carregar no Power BI Desktop, reduzindo o volume que precisa ser processado em todas as etapas seguintes.

#### Armazenamento de dados

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-12h40m15s298.jpg" alt="" width="840">
</p>

O slide aborda as **três modalidades de armazenamento de dados** disponíveis no Power BI e o trade-off central entre elas:

| Modalidade | Características |
|---|---|
| **Import** | Dados copiados para o modelo; consultas rápidas; atualizações agendadas; sem necessidade de permissão contínua à fonte. |
| **DirectQuery** | Dados permanecem na fonte; menor uso de disco; consultas em tempo real; maior latência conforme o volume de dados cresce. |
| **Composto (Dual)** | Combinação das duas abordagens; tabelas podem operar em Import ou DirectQuery conforme a necessidade. |

Os três fatores que determinam qual modalidade escolher são: **acesso aos dados**, **tempo de carregamento** e **permissão de acesso**. A escolha entre facilidade de uso (Import) e disponibilidade em tempo real (DirectQuery) é um dos principais pontos de decisão arquitetural na construção de um modelo otimizado no Power BI.

### 🟩 Vídeo 02 - Exemplificando um cenário com problemas e otimização

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.03-video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/otimizacao-de-modelo-de-dados-com-foco-em-desempenho-no-power-bi/learning/944308ac-a504-4111-aab2-5062b78bab08?autoplay=1

O vĩdeo explica como transformar relatórios visualmente atraentes em ferramentas de alta performance, focando em usabilidade, eficiência de dados e experiência do usuário (UX).

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-13h12m44s927.jpg" alt="" width="840">
</p>

O slide apresenta um **cenário hipotético** para contextualizar os problemas de desempenho em relatórios Power BI. À direita, é exibido um exemplo de relatório com visual elaborado, destacado pelas etiquetas *"Resultados significativos"* e *"Relatório com visual agradável"* — indicando que o produto entrega valor visual e analítico para os usuários. À esquerda, no entanto, uma caixa aponta para o ponto crítico do cenário: o modelo de dados recebeu **comentários negativos por conta do mau desempenho**. A seta ligando os dois elementos evidencia a contradição central: um relatório visualmente satisfatório, mas tecnicamente comprometido — situação agravada pelo fato de o colaborador que o criou já ter saído da organização.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-13h12m52s589.jpg" alt="" width="840">
</p>

O slide enumera os **problemas identificados** no cenário hipotético, respondendo à pergunta *"Quais os problemas?"*. São listados dois sintomas principais enfrentados pelos usuários: o **carregamento lento das páginas** do relatório e o fato de que as **tabelas não são atualizadas com rapidez em seleções** — o que compromete a visualização de métricas com dados atualizados. Ao fundo, uma terceira evidência vem da área de TI: o **arquivo está muito grande**, consumindo armazenamento de forma desnecessária no ambiente Power BI. Esses três pontos delimitam o escopo técnico do problema a ser resolvido.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-13h12m54s264.jpg" alt="" width="840">
</p>

O slide responde à pergunta *"O que fazer para resolver?"*, listando as cinco estratégias de otimização aplicáveis ao cenário:

- **Examinar o desempenho de medidas, relações e visuais** — analisar cada componente individualmente para identificar onde estão as ineficiências.
- **Usar variáveis para aprimorar o desempenho e solucionar problemas** — ao armazenar em variável um cálculo reutilizado em uma medida DAX, o Power BI evita reexecutá-lo a cada avaliação, ganhando eficiência.
- **Aprimorar o desempenho reduzindo os níveis de cardinalidade** — substituir relacionamentos muitos-para-muitos (N:M) por relacionamentos um-para-muitos (1:N), preferidos pelo motor do Power BI.
- **Otimizar modelos do DirectQuery com o armazenamento no nível de tabela** — configurar o modo de armazenamento adequado para cada tabela, reduzindo consultas desnecessárias à fonte de dados.
- **Criar e gerenciar agregações** — utilizar tabelas de agregação para acelerar consultas sobre grandes volumes de dados.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-13h13m00s354.jpg" alt="" width="840">
</p>

O slide ilustra, de forma esquemática, **como os elementos internos do relatório afetam diretamente a experiência do usuário final**. O diagrama apresenta dois blocos de entrada — **Relações** (representadas por um modelo de tabelas interligadas) e **Visuais e medidas** (representados pelo painel de visualizações do Power BI) — que convergem para o **Desempenho do relatório do Power BI**. Este, por sua vez, determina a qualidade da **Experiência do usuário final**. A leitura do fluxo reforça que relações mal configuradas e medidas ineficientes impactam o desempenho do relatório e, consequentemente, a percepção de qualidade do produto pelo usuário.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-13h13m05s983.jpg" alt="" width="840">
</p>

O slide encerra a sequência do cenário hipotético apresentando os **três passos práticos para iniciar a resolução do problema**, sob o mesmo questionamento *"Como resolver?"*:

- **Identificação de gargalos de desempenho no relatório** — primeiro passo, que consiste em localizar exatamente onde o relatório perde eficiência.
- **Analisar o desempenho com o Performance Analyser** — ferramenta nativa do Power BI que será explorada na sequência da aula para medir o tempo de resposta de cada visual e consulta.
- **Examinar os resultados** — avaliar as métricas coletadas e determinar quais ajustes são realmente necessários.

Esses três pontos definem a metodologia de diagnóstico que será aprofundada nos próximos vídeos do curso.      


### 🟩 Vídeo 03 - Quando ocorrem os problemas de otimização?

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.03-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/otimizacao-de-modelo-de-dados-com-foco-em-desempenho-no-power-bi/learning/ce4f06e5-8686-4e3a-8b0c-515b092fb294?autoplay=1

### 🟩 Vídeo 04 - Primeiro contato com Performance Analyzer

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.03-video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 05 - Explorando e Analisando os dados de análise de performance exportada

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.03-video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 06 - Instalando e utilizando o DAX Studio na avaliação das medidas DAX

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.03-video_06.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 07 - Falando sobre cache na avaliação de desempenho dos relatórios

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.03-video_07.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 08 - Como avaliar o desempenho do relatório sem a influência do cache?

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.03-video_08.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 09 - Verificando desempenho com relação a atualização dos dados

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.03-video_09.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 10 - Considerações finais sobre otimização de relatórios

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.03-video_10.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:


##  Materiais de Apoio

# Certificado: 

- Link na plataforma: 
- Certificado em pdf: