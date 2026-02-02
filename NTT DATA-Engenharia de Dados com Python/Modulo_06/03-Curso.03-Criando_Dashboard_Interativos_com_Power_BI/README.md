## Instrutor:

- Juliana Mascarenhas (Tech Education Specialist / Sócia (Content Creator) @SimplificandoRedes / Me Modelagem Computacional / Cientista de dados)
- Contato Linkedin: / [juliana-mascarenhas-ds](https://www.linkedin.com/in/juliana-mascarenhas-ds/)

## Parte 1 - Criando Dashboard Interativos com Power BI

### 🟩 Vídeo 01 - Apresentando o curso e definindo o que é um Dashboard?

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.06-curso.03-video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/criando-dashboard-interativos-com-power-bi/learning/0679b050-def6-46a1-8f15-9b5f050f32b3?autoplay=1

Este guia explora a criação e gestão de dashboards no Power BI Service. Diferente de um relatório denso e detalhado, o dashboard funciona como uma "vitrine" estratégica, consolidando informações cruciais de diversas fontes em uma única página. O conteúdo aborda desde a definição conceitual da Microsoft até aspectos técnicos de personalização, visualização mobile e governança de dados.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-31-16h52m45s300.jpg" alt="" width="840">
</p>

Este slide introduz o curso focado na criação de dashboards com Power BI, sob orientação da especialista Juliana Mascarenhas. O objetivo central é proporcionar uma experiência prática na construção de painéis que, diferentemente de relatórios comuns, priorizam a consolidação de métricas estratégicas para a tomada de decisão.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-31-16h52m47s977.jpg" alt="" width="840">
</p>

Os objetivos gerais do treinamento incluem o aprendizado de configurações fundamentais para a entrega de valor através de dashboards, tais como:

* Definição de modos de exibição mobile para acessibilidade em dispositivos móveis.
* Aplicação de temas personalizados aos elementos visuais do dashboard.
* Configuração da classificação de dados para melhor organização das informações.
* Adição de visuais de conjuntos de dados em tempo real, garantindo que o painel reflita o estado atual das operações.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-31-16h52m58s812.jpg" alt="" width="840">
</p>

Conceitualmente, um dashboard é definido como uma ferramenta que permite aos consumidores de relatórios criar artefatos de dados direcionados e personalizados. O foco está na personalização para o usuário final, permitindo que cada perfil visualize as métricas que são mais relevantes para suas necessidades específicas.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-31-16h53m03s086.jpg" alt="" width="840">
</p>

As principais características que distinguem um dashboard incluem o uso de visuais fixados e a capacidade de consolidar elementos provenientes de relatórios distintos. Isso significa que um único dashboard pode servir como uma camada de visualização unificada para diferentes conjuntos de dados e análises prévias.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-31-16h53m14s397.jpg" alt="" width="840">
</p>

A imagem apresenta um exemplo prático de um "Dashboard de Vendas Fantasia" no serviço do Power BI. Nele, observa-se a disposição de diversos indicadores, como a soma de vendas (118.73 Mi), lucro (16.89 M) e gráficos de desempenho por país e segmento. Este ambiente demonstra como os resultados são apresentados de forma direta para facilitar a verificação dos fatores que impulsionam o negócio.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-31-16h53m18s947.jpg" alt="" width="840">
</p>

Diferentemente dos relatórios (reports), os dashboards possuem características estruturais específicas:

* Podem conter vários conjuntos de dados ou diversos relatórios associados.
* Não possuem painéis de Filtro, Visualizações ou Campos (comuns no Power BI Desktop).
* São limitados a apenas uma única página de exibição.
* Permitem a manipulação e atualização de blocos individuais.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-31-16h53m26s415.jpg" alt="" width="840">
</p>

Nesta visualização, destaca-se a interface de interação com o dashboard, incluindo o recurso "Faça uma pergunta sobre seus dados", que utiliza linguagem natural para explorar os indicadores. A imagem reforça a ideia do dashboard como um conjunto de "blocos" fixados, onde cada visual pode ser movido ou redimensionado de forma independente para otimizar o layout de acordo com o objetivo de comunicação.      


### 🟩 Vídeo 02 - Criando nosso Primeiro Dashboard

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.06-curso.03-video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/criando-dashboard-interativos-com-power-bi/learning/86f55f0a-0d00-4185-b2b0-19f939e4369f?autoplay=1

Este tutorial foca na transição entre relatórios detalhados e a criação de um Painel (Dashboard) no ambiente online do Power BI. O objetivo é consolidar informações críticas de diferentes fontes em uma única página estratégica.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-31-17h15m07s568.jpg" alt="" width="840">
</p>

Nesta tela inicial do Power BI Service (ambiente online), visualizamos o "Meu Workspace", que funciona como a área de trabalho pessoal do usuário. Na parte central, há um menu de acesso rápido para a criação de novos conteúdos, permitindo iniciar um **Relatório**, um **Relatório paginado** ou um **Scorecard** para acompanhamento de métricas. Abaixo, é apresentada uma lista dos itens trabalhados recentemente, facilitando a navegação entre os diferentes painéis, relatórios e conjuntos de dados já existentes.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-31-17h15m18s097.jpg" alt="" width="840">
</p>

Ao acessar um painel (dashboard) existente, como o "Dash de Vendas - Fantasia", a barra de ferramentas superior oferece opções de gerenciamento. Ao clicar no menu **Arquivo**, o usuário tem acesso a funções administrativas e de organização, como **Salvar uma cópia** do painel, gerenciar permissões de acesso, imprimir a página ou acessar as configurações específicas desse dashboard.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-31-17h15m27s756.jpg" alt="" width="840">
</p>

A imagem demonstra o processo de personalização de um dashboard através da opção **Editar** > **Adicionar um bloco**. Este menu lateral permite inserir elementos que não vêm diretamente de relatórios, divididos em:

* **Mídia**: Inserção de conteúdo externo (Web), Imagens, Caixas de texto ou Vídeos.
* **Dados em tempo real**: Adição de blocos de Streaming personalizados, permitindo que o painel exiba informações atualizadas instantaneamente.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-31-17h15m39s302.jpg" alt="" width="840">
</p>

Nesta etapa, visualizamos um relatório aberto ("sample financial") onde o usuário utiliza o ícone de **fixar** (representado por um percevejo) em um visual específico. Ao acionar essa função, abre-se a janela "Fixar no painel", permitindo que o gráfico seja enviado para um **Dashboard existente** (neste caso, selecionando o "Dash de Vendas - Fantasia") ou para um novo painel. Essa é a forma padrão de construir dashboards, consolidando visuais importantes de diferentes relatórios em uma única tela.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-31-17h15m47s026.jpg" alt="" width="840">
</p>

Após a fixação dos elementos, vemos o dashboard resultante com diversos blocos organizados. O painel agora consolida indicadores de diferentes naturezas, como o total de vendas (**Soma de Sales**), distribuição por produto, lucro por segmento (**Soma de Profit**) e uma análise geográfica por país. Esta visualização é otimizada para consumo rápido de informações estratégicas.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-31-17h15m50s724.jpg" alt="" width="840">
</p>

A imagem destaca a interatividade nativa dos blocos no dashboard. Ao passar o mouse sobre os pontos de dados no gráfico de área ("Soma de Sales por Segmento e Mês"), o sistema exibe um *tooltip* com informações detalhadas, como o valor exato da soma de vendas para um determinado segmento e mês específico (ex: Julho de 2014), demonstrando como o painel permite explorar os dados sem sair da visão consolidada.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-31-17h15m56s095.jpg" alt="" width="840">
</p>

A interface mostra a capacidade de filtragem e detalhamento. Ao interagir com o gráfico de "Soma de Sales", é possível observar o destaque para o segmento **SMALL BUSINESS** no mês de agosto de 2014, onde o valor da soma de vendas atingiu **2.454.816,00**. Isso evidencia como o dashboard responde às seleções do usuário para isolar métricas de interesse.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-01-31-17h16m00s080.jpg" alt="" width="840">
</p>

Finalmente, visualizamos o dashboard em seu estado atualizado após as edições. O layout exibe de forma clara o KPI principal de **Soma de Sales** no valor de **118,73 Mi**, juntamente com gráficos de barras e áreas que distribuem o lucro e as vendas ao longo do tempo (meses de janeiro a dezembro) e entre as regiões geográficas (América do Norte e Europa).      


### 🟩 Vídeo 03 - Modificando Tema do Dashboard no Power BI Service

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.06-curso.03-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/criando-dashboard-interativos-com-power-bi/learning/577e5a3e-5bd7-4268-a6e0-59590840acac?autoplay=1

Este guia explora as diversas maneiras de modificar a estética e a funcionalidade visual de um dashboard no Power BI, desde ajustes simples de cores até a importação de temas complexos via arquivos JSON e o uso de recursos da comunidade.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-02-09h51m35s084.jpg" alt="" width="840">
</p>

O processo de customização avançada do dashboard é iniciado através da opção de carregar um arquivo de tema em formato JSON. Ao selecionar "Carregar tema JSON", o sistema permite navegar pelos arquivos locais para aplicar configurações visuais pré-definidas, como o arquivo selecionado "Nowalls Analytics Theme". Esta funcionalidade possibilita a importação de paletas de cores e estilos complexos que não estão disponíveis nativamente na interface básica.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-02-09h51m41s097.jpg" alt="" width="840">
</p>

A interface de edição permite alternar rapidamente entre os modos de exibição "Claro" e "Escuro". A escolha pelo tema escuro é frequentemente preferida por profissionais devido ao menor esforço visual proporcionado pelo contraste suave. Ao aplicar e salvar esta configuração, o fundo do dashboard é alterado enquanto os elementos visuais mantêm as cores específicas definidas para os dados.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-02-09h51m45s860.jpg" alt="" width="840">
</p>

É possível encontrar resistência na aplicação do tema se houver personalizações manuais anteriores que influenciem o resultado final. Para que o arquivo JSON externo reflita exatamente o design planejado, recomenda-se limpar personalizações prévias ou garantir que o modo base (claro ou escuro) esteja alinhado com o esperado pelo arquivo de estilo.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-02-09h51m54s412.jpg" alt="" width="840">
</p>

Após a importação bem-sucedida, o dashboard assume uma estética profissional com fundo totalmente preto e elementos em alto contraste. Os gráficos, como o treemap de vendas, passam a utilizar a paleta de cores importada, destacando automaticamente os dados conforme as regras contidas no arquivo JSON.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-02-09h51m56s757.jpg" alt="" width="840">
</p>

Embora temas personalizados tragam sofisticação visual, é necessário validar se a ênfase dada às cores faz sentido analítico. Por vezes, o tema pode destacar itens de menor valor, como o segmento "Channel Partners" no treemap, o que pode confundir a interpretação imediata dos resultados se o objetivo for priorizar os maiores volumes.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-02-09h51m59s331.jpg" alt="" width="840">
</p>

Para obter novos estilos, os usuários podem acessar a galeria de temas oficial da comunidade Power BI. Esse espaço funciona como um repositório onde criadores compartilham diversos temas, permitindo que outros usuários visualizem e baixem gratuitamente designs diferenciados para seus próprios dashboards.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-02-09h52m11s870.jpg" alt="" width="840">
</p>

A galeria oferece visualizações interativas que mostram como o tema se aplica a diferentes tipos de gráficos, botões e menus. Alguns temas avançados incluem até texturas de fundo e estilizações de bordas arredondadas, o que ajuda a transformar um relatório técnico em uma ferramenta de visualização mais engajadora e intuitiva.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-02-09h52m39s085.jpg" alt="" width="840">
</p>

O download de um tema é realizado diretamente na página da galeria ao localizar o ícone ou link para o arquivo JSON. É recomendável manter uma padronização entre diferentes relatórios que tratam do mesmo contexto de negócio, garantindo que o usuário tenha uma experiência visual coerente ao navegar entre eles.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-02-09h52m50s539.jpg" alt="" width="840">
</p>

Ao abrir o arquivo JSON em um editor de texto convencional, observa-se que o conteúdo está em uma linha contínua, o que dificulta a leitura e edição manual dos parâmetros. Esse arquivo contém todas as definições técnicas de cores e estilos que o motor de renderização do Power BI interpreta para construir a interface visual.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-02-09h52m55s318.jpg" alt="" width="840">
</p>

Para facilitar a compreensão do código, pode-se utilizar ferramentas online de formatação e validação de JSON. Ao processar o texto bruto nessas ferramentas, o código é organizado em uma estrutura hierárquica legível, permitindo identificar as seções responsáveis por cores de dados e estilos de visuais específicos.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-02-09h53m36s782.jpg" alt="" width="840">
</p>

A estrutura formatada do arquivo revela as chaves principais utilizadas pelo Power BI, como `visualStyles` para comportamentos globais e `dataColors` para a sequência de cores aplicadas às séries de dados. A lista de códigos hexadecimais em `dataColors` define exatamente quais tons serão usados em cada gráfico do dashboard.

Arquivo JSON com a formatação de estilo do painel apresentado acima:

```json
{
  "name": "My Theme",
  "visualStyles": {
    "*": {
      "*": {
        "color": {
          "solid": {
          }
        }
      }
    }
  },
  "dataColors": [
    "#5BBEFF",
    "#0A5FB4",
    "#2AB6CA",
    "#2DCCCD",
    "#004481",
    "#8AD4EB",
    "#D8BE75",
    "#F7893B",
    "#359988",
    "#DFBFBF",
    "#4AC5BB",
    "#5F6B6D",
    "#FB8281",
    "#F4D25A",
    "#7F898A",
    "#A4DDEE",
    "#FDA889",
    "#8687AC",
    "#28738A",
    "#A78F8F",
    "#168980",
    "#293537",
    "#BB4A4A",
    "#859525",
    "#475052",
    "#6A9FB0",
    "#BD7150",
    "#7B4F71",
    "#184050",
    "#706060",
    "#0F5C55",
    "#1C2325"
  ]
}

```      


### 🟩 Vídeo 04 - Criando Blocos com P e R no Power BI

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.06-curso.03-video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/criando-dashboard-interativos-com-power-bi/learning/1be94240-82bb-401d-9c03-288aae9bfe7a?autoplay=1

### 🟩 Vídeo 05 - Criando Alertas em Dashboards do Power BI

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.06-curso.03-video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 06 - Explorando Submenus dos Blocos contidos no Dashboard

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.06-curso.03-video_06.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 07 - Recursos Disponíveis no Free e no Power BI Pro e Configurações

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.06-curso.03-video_07.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 08 - O que são Live Pages? Quando utilizar um Dashboard?

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.06-curso.03-video_08.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 09 - Editando Layout Mobile para Acesso ao Dashboard via Celular

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.06-curso.03-video_09.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 10 - Streaming no Power BI

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.06-curso.03-video_10.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:


##  Materiais de Apoio

# Certificado: 

- Link na plataforma: 
- Certificado em pdf: