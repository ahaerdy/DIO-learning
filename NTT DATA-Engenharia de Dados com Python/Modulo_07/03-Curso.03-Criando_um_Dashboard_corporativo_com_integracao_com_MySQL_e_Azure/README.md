## Instrutor:

- Juliana Mascarenhas (Tech Education Specialist / Sócia (Content Creator) @SimplificandoRedes / Me Modelagem Computacional / Cientista de dados)
- Contato Linkedin: / [juliana-mascarenhas-ds](https://www.linkedin.com/in/juliana-mascarenhas-ds/)

### 🟩 Vídeo 01 - Descrevendo o desafio de projeto

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.07-curso.03-video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/project/processando-e-transformando-dados-com-power-bi/learning/a23f05bd-2d61-46b8-be0e-8d2ada4ef06a?back=/track/engenharia-dados-python&tab=undefined&moduleId=undefined

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-19-13h03m44s437.jpg" alt="" width="840">
</p>

Este módulo marca o início do desafio de projeto focado na coleta e processamento de dados utilizando o Power BI, inserido na formação de Power BI Analyst. O objetivo central é conduzir o desenvolvimento desde a infraestrutura inicial até a preparação dos dados para análise, sob a orientação técnica para garantir a integridade dos processos de ETL (Extração, Transformação e Carregamento).

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-19-13h03m48s063.jpg" alt="" width="840">
</p>

Os objetivos gerais deste desafio prático estão divididos em quatro pilares fundamentais:

1. **Configuração de setup:** Instanciação e configuração de um banco de dados na plataforma Azure.
2. **População de dados:** Utilização de scripts fornecidos para carregar as tabelas e registros no servidor.
3. **Integração:** Conexão direta entre o MySQL (hospedado na nuvem) e o Power BI Desktop.
4. **Transformação:** Execução de etapas de limpeza e modelagem dos dados, indo além do básico para preparar o ambiente para módulos futuros.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-19-13h03m56s861.jpg" alt="" width="840">
</p>

No cenário real de análise de dados, é comum lidar com fontes heterogêneas. O ecossistema do Power BI, através do Power Query, permite a conexão com diversas origens simultaneamente, como bancos de dados relacionais (SQL Server), arquivos de planilhas (Excel), bancos NoSQL (Cosmos DB), repositórios de colaboração (SharePoint) e serviços analíticos (Azure Analysis Services). Essa capacidade de integração é essencial para consolidar informações de diferentes setores, como Vendas, RH e Finanças, em um único modelo de dados.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-19-13h04m00s244.jpg" alt="" width="840">
</p>

O fluxo de trabalho padrão no Power BI segue uma progressão lógica para transformar dados brutos em insights:

* **Coleta:** A ingestão dos dados a partir das fontes identificadas.
* **Transformação:** Limpeza e tratamento dos dados no Power Query.
* **Modelagem:** Estabelecimento de relacionamentos entre tabelas e criação de métricas.
* **Visualização:** Criação de relatórios e dashboards interativos para o usuário final.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-19-13h04m04s864.jpg" alt="" width="840">
</p>

Ao iniciar a transformação de dados no Power Query, visualizamos a estrutura das tabelas importadas do banco de dados MySQL. A tabela `employee`, por exemplo, apresenta colunas fundamentais como nomes (`Fname`, `Lname`), identificadores (`Ssn`), datas de nascimento (`Bdate`), endereços, salários e chaves de relacionamento como `Super_ssn` (ID do supervisor) e `Dno` (número do departamento). O foco inicial é verificar se a importação ocorreu corretamente e se os cabeçalhos estão devidamente identificados.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-19-13h04m11s899.jpg" alt="" width="840">
</p>

As etapas críticas de verificação na base de dados incluem:

* **Verificação de tipos:** Garantir que colunas de valores financeiros sejam numéricas, datas sejam reconhecidas como tal e textos permaneçam como strings.
* **Tratamento de nulos:** Identificar e tratar valores nulos, especialmente em colunas como o `Super_ssn`, onde um valor nulo pode indicar que o colaborador é o gestor principal.
* **Análise de complexidade:** Avaliar colunas que contenham dados compostos que precisem ser separados para facilitar a análise, como horas de projetos ou endereços detalhados.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-19-13h04m18s270.jpg" alt="" width="840">
</p>

Para enriquecer a tabela de colaboradores, realizamos a mescla de consultas entre `employee` e `department`. O objetivo é associar o nome dos departamentos diretamente aos colaboradores. A base para esta junção deve ser a tabela `employee`, garantindo que nenhum colaborador seja perdido no processo. Após a mescla, é importante eliminar colunas redundantes ou desnecessárias para manter o modelo otimizado e focado nos requisitos do relatório.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-19-13h04m23s323.jpg" alt="" width="840">
</p>

A organização hierárquica e a legibilidade dos dados são aprimoradas através de dois processos:

1. **Identificação de Gerentes:** Realizar a junção para associar cada colaborador ao nome de seu respectivo gerente. Isso pode ser feito via Power BI (interface visual) ou através de queries SQL customizadas no momento da importação.
2. **Consolidação de Nomes:** Mesclar as colunas de "Nome" e "Sobrenome" para criar uma única coluna de nome completo, facilitando a identificação visual nos relatórios e a usabilidade dos filtros.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-19-13h04m28s039.jpg" alt="" width="840">
</p>

Uma etapa essencial para a futura modelagem em esquema estrela (Star Schema) é a criação de chaves únicas baseadas em contexto. Ao mesclar os nomes dos departamentos com suas respectivas localizações, criamos combinações únicas de "departamento-local". Esta técnica resolve ambiguidades caso um departamento opere em múltiplos locais e prepara o terreno para uma distribuição de dados mais eficiente em modelos dimensionais.

<p align="center">
<img src="000-Midia_e_Anexos/vlcsnap-2026-02-19-13h04m33s489.jpg" alt="" width="840">
</p>

Por fim, utiliza-se o recurso de agrupamento de dados para realizar análises quantitativas. Ao agrupar os dados por gerente, é possível verificar rapidamente quantos colaboradores estão associados a cada líder. Além de fornecer uma métrica direta de gestão, esse processo ajuda a identificar inconsistências, como colaboradores que não possuem gerentes atribuídos, validando se a estrutura organizacional refletida nos dados condiz com a realidade da empresa.

### 🟩 Vídeo 02 - Criando uma instância do MySQL na Azure

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.07-curso.03-video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/lab/processando-e-transformando-dados-com-power-bi/learning/5d36411a-b5d9-416c-b759-2dde3cc8348d

### 🟩 Vídeo 03 - Explorando o Recurso - Instância do MySQL

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.07-curso.03-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 04 - Se conectando ao Banco de Dados com Cloud Shell

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.07-curso.03-video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 05 - Criando Regra no Firewall na Azure para Acesso ao banco de dados

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.07-curso.03-video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 06 - Conectando ao MySQL na Azure utilizando Workbench

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.07-curso.03-video_06.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 07 - Integrando Power BI com MySQL na Azure

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.07-curso.03-video_07.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 08 - Entendendo o desafio

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.07-curso.03-video_08.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:


##  Materiais de Apoio

# Certificado: 

- Link na plataforma: 
- Certificado em pdf: