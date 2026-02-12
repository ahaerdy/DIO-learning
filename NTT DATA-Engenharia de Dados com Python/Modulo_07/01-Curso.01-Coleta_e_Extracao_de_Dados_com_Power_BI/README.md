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

O vídeo aborda o papel fundamental do Power BI como um intermediário capaz de consolidar informações de fontes heterogêneas para análise estratégica. O foco principal é entender como dados de diferentes origens (locais, nuvem, bancos de dados) e formatos (Excel, SQL, CSV) podem ser harmonizados para criar relatórios coesos e eficientes.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-12-08h06m46s277.jpg" alt="" width="840">
</p>

O Power Query é apresentado como o motor primordial para a obtenção e tratamento de dados no ecossistema Microsoft. Uma das formas mais fundamentais de iniciar um projeto é através da conexão com **Ficheiros** (arquivos) locais. Os formatos mais comuns e suportados nativamente incluem arquivos de texto plano como **CSV** e **txt**, além de planilhas do **Excel**.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-12-08h06m52s494.jpg" alt="" width="840">
</p>

A versatilidade do Power Query reside na diversidade de suas fontes de origem. Os dados podem ser extraídos de:

* **Local:** Arquivos armazenados na própria máquina ou rede local.
* **Nuvem:** Serviços de armazenamento como **OneDrive** (pessoal ou corporativo) e **SharePoint**.
* **Estruturas Complexas:** Conexões diretas com **Bancos de Dados**, integração com **Sistemas** de gestão (ERP/CRM) e consumo de dados via **APIs**.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-12-08h06m58s673.jpg" alt="" width="840">
</p>

É comum que, durante o desenvolvimento de um projeto, ocorram mudanças na infraestrutura ou no local onde os dados estão salvos. O Power BI permite lidar com a **Mudança durante projeto** através da **Atualização do local do dataset**. Esse ajuste é realizado nas **Definições da origem de dados**, garantindo que o relatório aponte para o novo caminho correto sem comprometer o trabalho de modelagem já realizado.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-12-08h07m01s283.jpg" alt="" width="840">
</p>

Para integrar informações provenientes de ambientes corporativos robustos, utiliza-se a opção de **Obter Dados** voltada para bancos relacionais. O exemplo destaca a conexão com uma **Base de dados do SQL Server**, onde é possível configurar o acesso ao servidor e ao banco específico para realizar a extração.

```sql
SELECT
  ID,
  NAME,
  SALESAMOUNT
FROM SALES

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-12-08h07m04s405.jpg" alt="" width="840">
</p>

A conexão com um banco de dados SQL funciona como uma ponte direta. Enquanto o **SQL Database** atua como o repositório centralizado de dados brutos, o **Power BI** consome essa fonte para processar os volumes financeiros e operacionais (como demonstrado pelos valores de faturamento e quantidades), transformando registros de banco de dados em indicadores visuais.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-12-08h07m08s028.jpg" alt="" width="840">
</p>

Ao trabalhar com outros sistemas, como o **MySQL**, o Power BI pode apresentar um aviso de que "este conector exige que um ou mais componentes adicionais sejam instalados". Isso ocorre porque drivers específicos de comunicação (como conectores .NET ou ODBC) precisam estar presentes no sistema operacional para que o Power BI consiga "conversar" com o banco de dados. Uma vez estabelecida a conexão, é possível aplicar instruções SQL para refinar a busca:

```sql
SELECT
  ID,
  NAME,
  SALESAMOUNT
FROM SALES

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-12-08h07m11s620.jpg" alt="" width="840">
</p>

Para gerenciar as conexões existentes, o usuário deve navegar até a guia **Home** e selecionar **Definições da origem de dados** (ou *Transformar dados* -> *Definições da origem*). Nesta interface, é possível **Alterar a origem** para apontar para um novo arquivo ou servidor, além de editar permissões de acesso e credenciais de login.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-12-08h07m13s751.jpg" alt="" width="840">
</p>

O uso de scripts SQL (através da opção "Instrução SQL") é recomendado em cenários específicos, como:

* **Volume Histórico:** Quando há uma base de dados muito antiga (ex: desde 2009) e você deseja filtrar apenas o necessário antes de carregar no Power BI.
* **Foco no Negócio:** Para relatórios de metas de vendas onde o processamento de agregação de dados pode ser feito de forma mais eficiente pelo próprio servidor do banco de dados (SQL Server).

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-12-08h07m20s568.jpg" alt="" width="840">
</p>

O **Armazenamento de dados** (Storage Mode) define como o Power BI lida com as informações. Existem três modalidades principais que equilibram facilidade de uso e disponibilidade:

1. **Importar:** Os dados são carregados para a memória do Power BI (mais rápido para análises, mas exige atualização manual/agendada).
2. **DirectQuery:** O Power BI consulta o banco de dados em tempo real (ideal para grandes volumes que não cabem na memória).
3. **Composto:** Uma combinação dos dois métodos anteriores.

É importante ressaltar que o desenvolvimento e a execução plena dessas funcionalidades estão atrelados ao sistema operacional Windows, ambiente nativo da ferramenta.      


### 🟩 Vídeo 04 - Substituindo Origem do Dataset local para Onedrive no Power BI Desktop

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.07-curso.01-video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/coleta-e-extracao-de-dados-com-power-bi/learning/68ece6ae-b2f4-4f47-bc46-eb4376eeb671?autoplay=1

Este tutorial foca em uma tarefa essencial para analistas de dados: migrar a fonte de um relatório do Power BI de um caminho local (computador) para um caminho na nuvem (OneDrive Business ou SharePoint), garantindo maior colaboração e disponibilidade dos dados.

### Anotações

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-12-08h19m56s433.jpg" alt="" width="840">
</p>

A aula inicia com a abertura do Power BI Desktop e o carregamento do "Relatório de Vendas Considerando Produtos e Segmento". O dashboard exibe visualizações preliminares de vendas por produto e segmento, preparando o ambiente para a demonstração de como alterar a origem desses dados.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-12-08h19m58s967.jpg" alt="" width="840">
</p>

Ao acessar as configurações da fonte de dados, identifica-se que o arquivo de exemplo (`Financial Sample.xlsx`) está localizado localmente no diretório de instalação. O objetivo central é modificar esse caminho para uma fonte baseada em nuvem para facilitar a colaboração.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-12-08h20m01s423.jpg" alt="" width="840">
</p>

A interface do Editor do Power Query é apresentada, exibindo as etapas de transformação já aplicadas aos dados, como classificação de linhas e ajustes relacionados aos dados. Este ambiente é utilizado para gerenciar consultas e parâmetros antes de consolidar as alterações na fonte.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-12-08h20m06s599.jpg" alt="" width="840">
</p>

Dentro do Power Query, a janela de "Configurações da fonte de dados" é aberta novamente para realizar a troca da origem local. É a partir desta etapa que o usuário inicia o processo de substituição do caminho físico pelo endereço de rede.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-12-08h20m10s253.jpg" alt="" width="840">
</p>

Para realizar a conexão com a nuvem, acessa-se o OneDrive for Business. O procedimento envolve localizar o arquivo desejado na pasta "dataset_powerbi" para obter suas informações de caminho detalhadas.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-12-08h20m12s589.jpg" alt="" width="840">
</p>

No painel de detalhes do arquivo no OneDrive, deve-se copiar o link direto que aponta para a localização do arquivo na estrutura do SharePoint. Esse link, encontrado na seção de caminho dos detalhes, servirá como o novo endereço de origem.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-12-08h20m17s435.jpg" alt="" width="840">
</p>

O endereço URL copiado é colado na configuração de origem do arquivo no Power BI. O sistema processa o caminho do OneDrive e permite a importação dos dados de forma automatizada via web.

```bash
https://academiapme-my.sharepoint.com/personal/juliana_mascarenhas_dio_me/Documents/dataset_powerbi/Financial%20Sample.xlsx

```

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-12-08h20m25s229.jpg" alt="" width="840">
</p>

Após aplicar as mudanças, as configurações da fonte de dados confirmam que o relatório agora está consumindo informações diretamente do OneDrive. Isso garante a disponibilidade total dos dados e permite atualizações automáticas sem dependência de arquivos locais no computador.      


### 🟩 Vídeo 05 - Obtendo dados do excel com Power BI no Onedrive (Outra maneira)

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.07-curso.01-video_05.webm" type="video/webm">
    Seu navegador não suporte vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/coleta-e-extracao-de-dados-com-power-bi/learning/81595e7f-22d5-438f-aff7-96a9c6a47ae2?autoplay=1



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