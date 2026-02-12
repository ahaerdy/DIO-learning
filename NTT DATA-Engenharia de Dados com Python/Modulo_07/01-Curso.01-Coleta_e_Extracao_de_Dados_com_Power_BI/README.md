## Instrutor:

- Juliana Mascarenhas (Tech Education Specialist / Sócia (Content Creator) @SimplificandoRedes / Me Modelagem Computacional / Cientista de dados)
- Contato Linkedin: / [juliana-mascarenhas-ds](https://www.linkedin.com/in/juliana-mascarenhas-ds/)

## Parte 1 - Coleta e Extração de Dados com Power BI

### 🟩 Vídeo 01 - Power BI Suporta Dados de fontes distintas com Power BI

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.07-curso.01-video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/coleta-e-extracao-de-dados-com-power-bi/learning/0ed1f1c5-601d-402b-9230-eccb791a184d?autoplay=1

Este vídeo serve como introdução ao curso de Coleta e Extração de Dados utilizando o Power BI. O instrutor desmistifica a ideia de que o Power BI é apenas uma ferramenta de visualização, enfatizando que a base de qualquer relatório ou dashboard de alta qualidade reside na capacidade de conectar, extrair e integrar dados de fontes diversas e complexas.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-12-07h03m03s564.jpg" alt="" width="840">
</p>

Esta introdução marca o início do curso focado em **Coleta e Extração de Dados com Power BI**. A formação, denominada **Power BI Analyst**, é conduzida por **Juliana Mascarenhas**, especialista em educação tecnológica e mestre em modelagem computacional. O conteúdo aborda a necessidade fundamental de coletar informações de diversas fontes para sustentar a criação de relatórios e dashboards integrados a diferentes sistemas. A instrutora destaca a versatilidade da ferramenta, que permite conexões com:

* **Bancos de dados heterogêneos**, incluindo relacionais e NoSQL.
* **Arquivos locais** nos formatos Excel, CSV e ficheiros SQL.
* **Serviços de nuvem**, como sistemas de analytics na Azure e instâncias Cloud em plataformas como AWS.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-12-07h03m07s401.jpg" alt="" width="840">
</p>

O **Objetivo Geral** desta etapa do treinamento é capacitar o analista a dominar o fluxo inicial de dados dentro do ecossistema Microsoft. Os tópicos principais incluem:

* **Identificação e conexão** eficiente a qualquer origem de dados.
* **Obtenção de dados** provenientes de bases de dados de diferentes fontes e do **Azure Analysis Services**.
* **Seleção de modos de armazenamento**, como o método de "importar", compreendendo as vantagens técnicas de cada escolha.
* **Otimização e suporte**, focando em corrigir problemas de desempenho e resolver erros comuns de importação.      


### 🟩 Vídeo 02 - Conversando sobre Integração do Power Bi com outros Sistemas

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.07-curso.01-video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/coleta-e-extracao-de-dados-com-power-bi/learning/687b5b43-185f-493a-b575-a39620d71ba5?autoplay=1

Este vídeo aborda a versatilidade do Power BI em conectar-se a uma infinidade de fontes de dados heterogêneas. O foco principal é entender como realizar a integração desses dados, o papel fundamental do Power Query no processo de ETL (Extração, Transformação e Carregamento) e o fluxo de trabalho padrão, desde a coleta bruta até a publicação de relatórios e dashboards no Power BI Service.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-12-07h34m20s411.jpg" alt="" width="840">
</p>

O cenário atual de análise de dados é caracterizado pela geração constante de uma infinidade de informações. Diversas fontes de dados, cada uma com estruturas peculiares e contextos distintos, criam uma necessidade crescente de integração para que esses dados brutos possam ser devidamente processados e transformados em conhecimento.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-12-07h34m23s950.jpg" alt="" width="840">
</p>

Na prática, as organizações lidam com fontes heterogêneas de dados que precisam ser integradas ao Microsoft Power BI para análise. Essas fontes podem variar significativamente em formato e origem:

* **Microsoft SQL Server:** Utilizado geralmente para armazenar dados estruturados de vendas em bancos de dados relacionais.
* **Microsoft Excel:** Ainda vastamente utilizado por setores como RH e Marketing devido à sua simplicidade e flexibilidade.
* **Cosmos DB:** Um banco de dados NoSQL orientado a documentos, onde os dados são comumente tratados em formato JSON.
* **Azure Analysis Services:** Focado em finanças e modelagem dimensional, trabalhando com cubos de servidor.
* **SharePoint:** Ambiente corporativo para compartilhamento de documentos e controle de objetivos de vendas.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-12-07h34m35s207.jpg" alt="" width="840">
</p>

O **Power Query** é o motor de conectividade e preparação de dados que permite ao Power BI Desktop se comunicar com esses sistemas distintos. Ele atua na organização dos dados, oferecendo recursos para:

* **Manipular dados:** Realizar ajustes estruturais e transformações necessárias.
* **Remover erros:** Limpar a base de dados de inconsistências e valores nulos.
* **Combinar resultados:** Integrar tabelas e fontes diferentes para criar um conjunto de dados unificado.

Essa ferramenta permite realizar operações complexas sem a necessidade de escrever código, embora suporte o uso de **DAX (Data Analysis Expressions)** para a criação de medidas e colunas calculadas mais avançadas.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-12-07h34m46s690.jpg" alt="" width="840">
</p>

O fluxo de trabalho dentro do Power BI segue uma lógica sequencial que garante a integridade da informação:

1. **Coleta:** A fase inicial onde os dados são extraídos das fontes originais.
2. **Transformação (Power Query):** Onde os dados brutos são limpos e tratados.
3. **Criação do Dashboard/Relatório:** Desenvolvimento das visualizações a partir dos dados já tratados.
4. **Publicação do relatório:** Envio do trabalho para o Power BI Serviço para compartilhamento.
5. **Inserção em um Dashboard:** Consolidação de visuais importantes em uma visão única de alto nível.

Atualmente, o foco principal do aprendizado está concentrado nas etapas de **Coleta** e **Transformação**, fundamentais para garantir que os relatórios subsequentes sejam precisos.      

### 🟩 Vídeo 03 - Como podemos importar os dados de diferentes fontes?

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.07-curso.01-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/coleta-e-extracao-de-dados-com-power-bi/learning/51d8922c-5934-4dca-aa6b-bb059c775040?autoplay=1

### 🟩 Vídeo 04 - Substituindo Origem do Dataset local para Onedrive no Power BI Desktop

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.07-curso.01-video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 05 - Obtendo dados do excel com Power BI no Onedrive (Outra maneira)

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.07-curso.01-video_05.webm" type="video/webm">
    Seu navegador não suporte vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 06 - Conectando ao MySQL Local Utilizando o Power BI Desktop

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.07-curso.01-video_06.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 07 - Analisando os Dados Importados do MySQL com Power Query

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.07-curso.01-video_07.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 08 - Quando utilizar o SQL para Importar Dados de um Banco de Dados?

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.07-curso.01-video_08.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 09 - Criando um Relatório Simples para Entender os Dados Recuperados do MySQL

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.07-curso.01-video_09.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 10 - Utilizando SQL para Restringir os Dados Recuperados do MySQL com Power BI

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.07-curso.01-video_10.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 11 - Instanciando um Banco de Dados com SQL Server na Azure

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.07-curso.01-video_11.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 12 - Analisando as Configurações da nossa Instância do SQL Server na Azure

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.07-curso.01-video_12.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 13 - Considerações sobre Integração com NoSQL e Armazenamento de Dados do Power BI

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.07-curso.01-video_13.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 14 - Como lidar com Problemas de Desempenho no Power BI

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.07-curso.01-video_14.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 15 - Integrando Power BI com SQL Server na Azure com DirectQuery

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.07-curso.01-video_15.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

##  Materiais de Apoio

# Certificado: 

- Link na plataforma: 
- Certificado em pdf: