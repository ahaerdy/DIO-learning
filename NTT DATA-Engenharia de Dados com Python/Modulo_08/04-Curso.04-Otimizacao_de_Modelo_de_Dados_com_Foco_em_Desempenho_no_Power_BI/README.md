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

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-12h39m34s708.jpg" alt="" width="840">
</p>

Slide de abertura da aula ministrada por **Juliana Mascarenhas**, Tech Education Specialist da DIO, Mestre em Modelagem Computacional e Cientista de Dados. O tema central é **"Otimizando Modelos com Foco em Performance"**, inserido na trilha da **Formação Power BI Analyst**. Este módulo dá continuidade aos conteúdos anteriores — criação de visuais, ETL, modelagem de dados e modelagem dimensional — e introduz agora os fundamentos de otimização de desempenho em relatórios do Power BI.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-12h39m39s481.jpg" alt="" width="840">
</p>

Slide de transição que marca o início da **Etapa 1: Fundamentos de Otimização com Power BI**. Não há código nesta imagem. O slide sinaliza que a aula entrará em conceitos fundamentais sobre o que é otimização, por que ela importa e quais são suas consequências práticas no contexto de relatórios Power BI.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-12h39m40s899.jpg" alt="" width="840">
</p>

O slide apresenta a definição formal de **otimização** no contexto de modelos de dados:

> **Realização de modificações no estado atual do modelo de dados a fim de executar suas operações com maior eficiência. Dessa forma, aumentamos seu desempenho.**

Essa definição delimita o escopo do conceito: otimizar não é refazer o relatório do zero, mas intervir cirurgicamente no modelo existente para que ele processe e entregue informações com mais eficiência. O objetivo final é sempre o aumento de desempenho — tanto na atualização dos dados quanto na renderização dos visuais para o usuário final.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-12h39m45s262.jpg" alt="" width="840">
</p>

O slide destaca uma distinção crítica que muitos analistas negligenciam: o **desempenho do relatório em fase de testes** e o **desempenho do relatório em fase de produção** são realidades diferentes.

Durante o desenvolvimento, o relatório roda em ambiente controlado, geralmente com poucos usuários e volume de dados reduzido. Ao ser publicado e consumido por múltiplos usuários simultaneamente, surgem gargalos que não existiam na fase de testes. Compreender essa diferença é o ponto de partida para justificar o investimento em otimização desde o início do projeto.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-12h39m47s148.jpg" alt="" width="840">
</p>

Do ponto de vista do usuário final, o mau desempenho se manifesta de duas formas objetivas:

- **Lentidão no carregamento** — páginas do relatório demoram mais do que o aceitável para abrir.
- **Demora na atualização dos visuais** — gráficos e tabelas levam tempo excessivo para refletir filtros e interações.

Ambas as situações geram uma **experiência negativa**, o que contraria o propósito de qualquer relatório analítico: entregar informação de forma rápida, clara e confiável. Um relatório visualmente impecável perde valor se o usuário precisar esperar para interagir com ele.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-12h39m51s430.jpg" alt="" width="840">
</p>

O slide aponta que, em **90% dos casos**, o mau desempenho de um relatório Power BI tem origem em um (ou na combinação) dos seguintes fatores:

- **Modelagem inadequada** — estrutura de tabelas mal planejada, relações incorretas ou desnecessariamente complexas.
- **Mau uso da linguagem DAX** — funções mal construídas ou escolhas de medidas que consomem mais recursos do que o necessário.
- **Combinação dos dois** — o caso mais comum na prática.

O número 90% reforça que esses dois problemas dominam a maioria dos diagnósticos de performance, tornando-os o foco prioritário de qualquer processo de otimização.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-12h39m56s929.jpg" alt="" width="840">
</p>

O slide elenca os benefícios diretos de um modelo de dados bem otimizado:

- **Melhor experiência do usuário** — navegação fluida e interações responsivas.
- **Desempenho e bons resultados** — relatório robusto, capaz de sustentar o crescimento dos dados ao longo do tempo.
- **Cliente satisfeito** — seja um cliente interno ou externo, a qualidade percebida do relatório está diretamente ligada à sua velocidade e confiabilidade.

Tratar os problemas de desempenho durante o desenvolvimento evita retrabalho custoso na fase de produção e permite manter o relatório otimizado de forma contínua ao longo de sua vida útil.

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

O vídeo mostra como identificar gargalos de desempenho em relatórios de BI, a importância de uma modelagem de dados eficiente e técnicas práticas para reduzir o tamanho do modelo e aumentar a velocidade de resposta para o usuário final.

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-13h53m22s588.jpg" alt="" width="840">
</p>

O slide apresenta a seção **Visuais** dentro do tema *Resolvendo Problemas*, com foco em **onde otimizar** no relatório. Três princípios são destacados:

- **Melhorar o desempenho** — o objetivo central da otimização é garantir que o relatório carregue e responda de forma ágil.
- **Mínimo impacto na experiência do usuário** — as intervenções de performance não devem degradar a usabilidade nem a clareza visual para quem consome o relatório.
- **Menos visuais → mais desempenho** — cada elemento visual adicionado à tela representa uma consulta extra ao modelo de dados. Reduzir a quantidade de visuais é, portanto, uma das formas mais diretas de ganhar velocidade.

Complementando esses pontos, o slide destaca dois vetores de análise: **Campos no visual** (quais campos estão sendo usados e se são realmente necessários) e **Pense em como representar os Detalhes…** (questionar se um visual dedicado é indispensável ou se a mesma informação pode ser entregue por outro meio, como dicas de ferramenta ou segmentadores já existentes).

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-13h53m26s430.jpg" alt="" width="840">
</p>

O slide trata do segundo eixo de otimização: as **consultas DAX**. Os três pontos apresentados são:

- **Tempo de execução da consulta** — cada medida DAX que alimenta um visual tem um custo de processamento mensurável; identificar os tempos é o ponto de partida para qualquer tuning.
- **Performance Analyzer** — ferramenta nativa do Power BI Desktop utilizada para capturar e inspecionar o tempo gasto por cada visual ao executar sua consulta DAX.
- **Referência: 120 milsseg** — valor de referência considerado aceitável para uma consulta DAX. Tempos muito acima disso indicam que há algo a revisar na lógica da medida ou no modelo.

À direita, o painel do **Performance Analyzer** exibido no slide mostra um exemplo real: o visual *Vendas por Ano* registrou **2754 ms** apenas na etapa de *Consulta DAX* — um tempo muito superior ao parâmetro de referência, sinalizando claramente a necessidade de revisão.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-13h53m29s997.jpg" alt="" width="840">
</p>

O slide lista os **Pontos de atenção** relacionados à modelagem de dados, acompanhado de um diagrama esquemático que representa a estrutura de tabelas e seus relacionamentos. São quatro diretrizes práticas:

- **Verifique as relações e cardinalidades** — relações incorretas ou com cardinalidade inadequada impactam diretamente o resultado das medidas DAX e o desempenho das consultas.
- **Delete as colunas desnecessárias** — colunas que não são utilizadas por visuais, medidas ou filtros ocupam espaço no modelo sem agregar valor, aumentando o custo de memória e processamento.
- **Preferência exclusão: na importação** — sempre que possível, a remoção de colunas deve ocorrer na origem, via SQL ou durante o processo de importação, antes que os dados entrem no modelo.
- **Use o Power Query quando necessário** — nos casos em que a exclusão na fonte não é viável, o Power Query é o ambiente correto para eliminar colunas e linhas antes que os dados sejam carregados.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-13h53m33s045.jpg" alt="" width="840">
</p>

O slide apresenta a seção **Modelo de dados** e contextualiza por que esse é o elemento central de toda a estratégia de desempenho. Três consequências diretas de um modelo bem estruturado são listadas:

- **Visuais com bom desempenho** — um modelo eficiente permite que os visuais consultem e renderizem dados com rapidez, independentemente da complexidade do relatório.
- **DAX com execução rápida** — medidas calculadas sobre um modelo otimizado executam em menos iterações, reduzindo o tempo de resposta.
- **Provável: relações, colunas ou metadados** — quando há lentidão, a causa mais provável está em algum desses três elementos: relações mal configuradas, colunas desnecessárias presentes no modelo ou metadados incorretos.

O diagrama à direita reforça visualmente a ideia de um modelo relacional com tabelas em diferentes níveis hierárquicos, conectadas por relacionamentos.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-13h53m34s472.jpg" alt="" width="840">
</p>

O slide retoma os **Pontos de atenção** do modelo de dados — os mesmos apresentados anteriormente — reforçando-os como checklist de revisão prática antes de qualquer publicação do relatório:

- **Verifique as relações e cardinalidades**
- **Delete as colunas desnecessárias**
- **Preferência exclusão: na importação**
- **Use o Power Query quando necessário**

A repetição do slide no contexto da aula funciona como consolidação: após entender *por que* o modelo impacta o desempenho, o aluno é relembrado de *como* agir diretamente no modelo para corrigir os problemas identificados.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-13h53m37s148.jpg" alt="" width="840">
</p>

O slide enumera as principais **Técnicas para redução de dados** aplicáveis a modelos de importação no Power BI:

- **Remover colunas desnecessárias** — eliminar atributos que não contribuem para análises ou filtros.
- **Remover linhas desnecessárias** — filtrar registros irrelevantes antes da carga (ex.: dados históricos fora do escopo do relatório).
- **Otimizar tipos de dados de coluna** — usar o tipo mais enxuto possível (ex.: inteiro em vez de texto para códigos numéricos), o que reduz o espaço ocupado no mecanismo VertiPaq.
- **Colunas personalizadas** — preferir colunas calculadas criadas no Power Query (M) em vez de colunas DAX quando a lógica for simples, pois o VertiPaq compacta de forma mais eficiente dados originados do Power Query.
- **Desabilitar data/hora automática** — o recurso automático cria tabelas de calendário ocultas para cada coluna de data, inflando o modelo; desativá-lo e criar uma tabela de datas única é a prática recomendada.
- **…** — o slide indica que há outras técnicas documentadas, remetendo à **Documentação oficial** da Microsoft (link exibido no canto inferior direito).

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-13h53m39s900.jpg" alt="" width="840">
</p>

A imagem exibe uma captura de tela do **Microsoft Learn**, na página oficial de diretrizes do Power BI intitulada **"Técnicas de redução de dados para Modelagem de importação"**, acessível em:

```
learn.microsoft.com/pt-br/power-bi/guidance/import-modeling-data-reduction
```

Tópicos cobertos pela documentação, incluindo: *Remover colunas desnecessárias*, *Remover linhas desnecessárias*, *Agrupar por e resumir* e *Otimizar tipos de dados de coluna*, entre outros.

A professora recorre a essa página para demonstrar que as técnicas apresentadas no slide anterior têm respaldo na documentação oficial da Microsoft, servindo como referência de consulta para aprofundamento. Não se trata de código Python — a classificação automática foi incorreta; a imagem contém exclusivamente uma página web de documentação.      


### 🟩 Vídeo 04 - Primeiro contato com Performance Analyzer

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.03-video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/otimizacao-de-modelo-de-dados-com-foco-em-desempenho-no-power-bi/learning/43d8bce6-4d00-4161-9ff1-77d514663d33?autoplay=1

O vídeo mostra como utilizar o Performance Analyzer no Power BI Desktop para diagnosticar e melhorar o desempenho de relatórios, garantindo carregamentos rápidos e uma experiência de usuário fluida.

### Anotações

Aqui está o documento revisado, agora sem as linhas que iniciavam com `####`:

```markdown
<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-14h18m21s288.jpg" alt="" width="840">
</p>

A imagem exibe a página de documentação oficial da Microsoft Learn intitulada **"Use Performance Analyzer to examine report element performance"**. A página está enquadrada na seção *Create reports and dashboards* do Power BI e indica que o recurso se aplica ao **Power BI Desktop** (marcado com ✔) e **não** ao Power BI Service (marcado com ✘). O sumário lateral à direita antecipa as seções do artigo: *Displaying the Performance Analyzer pane*, *Using Performance Analyzer*, *Refreshing visuals* e *Saving performance information*. Esse material é indicado como leitura complementar para aprofundamento no tema.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-14h18m28s874.jpg" alt="" width="840">
</p>

A imagem mostra a seção **"Displaying the Performance Analyzer pane"** da mesma documentação da Microsoft. O texto instrui o usuário a acessar a aba **View** (Exibição) no Power BI Desktop e clicar em **Performance Analyzer** para abrir o painel. Abaixo da instrução, há uma captura de tela da barra de ferramentas do Power BI destacando o botão *Performance Analyzer* em vermelho, ao lado de *Filters*, *Bookmarks* e *Selection*. Uma vez ativado, o painel é exibido à direita do canvas do relatório.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-14h18m38s566.jpg" alt="" width="840">
</p>

A imagem apresenta a parte da documentação que descreve as **categorias de tempo** registradas pelo Performance Analyzer para cada visual. São quatro categorias:

- **DAX query** — tempo decorrido entre o envio da consulta pelo visual e o retorno dos resultados pelo Analysis Services.
- **Visual display** — tempo necessário para o visual ser renderizado na tela, incluindo carregamento de imagens web ou geocodificação.
- **Other** (Outro) — tempo que o visual precisa para preparar queries, aguardar outros visuais concluírem ou executar processos em segundo plano (*background*).
- **Evaluated parameters** (preview) — tempo gasto para avaliar parâmetros de campo dentro de um visual.

A coluna **Duration (ms)** registra a diferença entre os timestamps de *início* e *fim* de cada operação, e a maioria das operações é executada sequencialmente em uma única *thread* de interface do usuário.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-14h18m45s409.jpg" alt="" width="840">
</p>

A imagem exibe o **Power BI Desktop** com o relatório "Retail Analysis – Sample Modificada" aberto na **Página 1** e o painel do **Performance Analyzer** visível à direita. A gravação já foi iniciada (indicada pelo carimbo *"Gravação iniciada (22/11/2022 08:25:28)"*) e a primeira interação — um clique no filtro *Lindsey* — foi registrada. O painel lista os visuais da página com suas durações em milissegundos:

| Visual | Duração (ms) |
|---|---|
| Caixa de texto | 40 |
| Forma | 39 |
| This Year Sales, This Year Sales Meta e Regular... | 273 |
| Cartão de linha múltipla | 271 |
| Vendas por Categoria | 271 |
| ChicletSlicer 1.6.3 | 269 |
| Total Sales Variance %, Sales Per Sq Ft e This Y... | 201 |
| This Year Sales por Territory | 56 |

Essa listagem permite identificar quais visuais consomem mais tempo de carregamento em resposta à interação do usuário.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-14h18m47s325.jpg" alt="" width="840">
</p>

A imagem mostra o resultado de um clique de expansão (*drill-through*) no visual do **medidor (gauge)** "This Year Sales, This Year Sales Meta e Regular_Sales_Units". O visual ocupa toda a área do canvas e exibe o valor **$6 Mi**, com a barra do medidor preenchida predominantemente em azul-petróleo. O botão **"Voltar ao relatório"** aparece no canto superior esquerdo. O painel do Performance Analyzer à direita permanece com os mesmos registros da gravação anterior, pois a expansão de visual não gera uma nova interação de consulta — apenas redimensiona a exibição.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-14h19m03s610.jpg" alt="" width="840">
</p>

A imagem registra o painel do Performance Analyzer com um dos itens expandido — a entrada **"Forma"** — revelando suas subcategorias de tempo:

| Subcategoria | Duração (ms) |
|---|---|
| Exibição de visual | 15 |
| Outro | 25 |
| *Copiar consulta* | *(ação disponível)* |

Isso ilustra na prática o que a documentação descreveu: o tempo total de um visual é composto por diferentes camadas. A categoria **"Outro"** (25 ms neste caso) representa o processamento realizado em *background* — preparação de queries, espera por outros visuais ou processamentos paralelos — enquanto a **"Exibição de visual"** (15 ms) é o tempo puro de renderização na tela. O relatório também mostra uma segunda rodada de eventos (com realce cruzado) acumulada logo abaixo na lista.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-14h19m19s229.jpg" alt="" width="840">
</p>

A imagem exibe o **Bloco de Notas (Notepad)** com a **consulta DAX** copiada a partir do Performance Analyzer via a opção *"Copiar consulta"*. O código revela exatamente quais medidas o visual "This Year Sales, This Year Sales Meta e Regular Sales Units" executou internamente para exibir seus dados:

```dax
// DAX Query
EVALUATE
  ROW(
    "V_This_Year_Sales_Goal", 'Sales'[_This Year Sales Goal],
    "This_Year_Sales", 'Sales'[This Year Sales],
    "Regular_Sales_Units", 'Sales'[Regular_Sales_Units]
  )
```

A função `EVALUATE` executa a expressão e a função `ROW` retorna uma tabela de linha única com as três medidas mapeadas: a meta de vendas do ano atual, as vendas do ano atual e as unidades de vendas regulares. Essa funcionalidade é útil para depurar e entender o que cada visual está consultando no modelo de dados.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-12-14h19m28s691.jpg" alt="" width="840">
</p>

A imagem mostra o arquivo **PowerBIPerformanceData.json** — exportado pelo Performance Analyzer — aberto no **Firefox**, que oferece uma visualização estruturada do JSON. O arquivo registra todos os eventos capturados durante a sessão de gravação. Cada evento contém campos como `name`, `component`, `start`, `end`, `id` e `metrics`. Por exemplo:

- **Evento 0** (`User Action` / `Report Canvas`): `sourceLabel: "userAction_StartedMonitoring"` — marca o início da gravação.
- **Evento 1** (`User Action` / `Report Canvas`): `sourceLabel: "userAction_CrossHighlight"` — registra uma interação de realce cruzado.
- **Evento 2** (`Visual Container Lifecycle` / `Report Canvas`): métricas incluem `visualTitle: "Caixa de texto"`, `visualType: "textbox"`, `initialLoad: false` — indicando o carregamento do visual de caixa de texto.

Esse formato JSON permite análises programáticas externas, exportação para ferramentas de BI ou comparações de desempenho ao longo do tempo. A visualização no navegador (em vez do VS Code) facilita a leitura hierárquica dos dados.
```

Todas as linhas que começavam com `####` foram removidas, mantendo apenas imagens e explicações.      

### 🟩 Vídeo 05 - Explorando e Analisando os dados de análise de performance exportada

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.03-video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/otimizacao-de-modelo-de-dados-com-foco-em-desempenho-no-power-bi/learning/2ea1fe17-1b79-40cf-885b-73b58a8f8202?autoplay=1

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