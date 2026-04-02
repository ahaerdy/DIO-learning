## Instrutor:

- Juliana Mascarenhas (Tech Education Specialist / Sócia (Content Creator) @SimplificandoRedes / Me Modelagem Computacional / Cientista de dados)
- Contato Linkedin: / [juliana-mascarenhas-ds](https://www.linkedin.com/in/juliana-mascarenhas-ds/)

## Parte 1 - Modelagem de Dados no Power BI

### 🟩 Vídeo 01 - Apresentando a ementa do curso

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.02-video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/modelagem-de-dados-no-power-bi/learning/5ba15e32-7d9f-4a3b-b885-f9707c7c9890?autoplay=1

O vídeo explora os fundamentos da modelagem de dados dentro do ecossistema Power BI, destacando a transição do modelo relacional para o dimensional e como uma estrutura bem definida impacta diretamente a performance e a manutenção de relatórios analíticos.

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-01-13h40m21s730.jpg" alt="" width="840">
</p>

Este slide apresenta o tema "Modelagem de Dados com Power BI", inserido na Formação Power BI Analyst. A instrutora, Juliana Mascarenhas, é apresentada com suas credenciais: Tech Education Specialist na DIO, proprietária dos projetos Simplificandoredes e SimplificandoProgramaço, além de mestre em modelagem computacional e cientista de dados. A imagem introduz o módulo, destacando que o foco será na construção de modelos de dados eficientes dentro da ferramenta Power BI.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-01-13h40m23s427.jpg" alt="" width="840">
</p>

Este slide lista os objetivos gerais da aula. Serão abordados: trabalho com modelagem no Power BI, tratamento de relações (relacionamentos) circulares, aplicação da modelagem dimensional, manipulação de tabelas fato e dimensões, e identificação de desafios comuns na fase de modelagem. Esses pontos orientam o aprendizado prático para estruturar dados de forma analítica e otimizada.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-01-13h40m28s249.jpg" alt="" width="840">
</p>

A imagem exibe um fluxo de dados no Power BI, enfatizando a etapa de modelagem. O diagrama mostra que, após a transformação dos dados (normalmente com Power Query), entra‑se na fase de modelagem, onde são definidas as tabelas e os relacionamentos. A seta indica a posição da modelagem no processo. O instrutor menciona que, embora a transformação geralmente venha antes, a modelagem pode ser aperfeiçoada posteriormente. O slide também referencia o Star Schema, o modelo relacional e o MongoDB para contextualizar o escopo analítico.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-01-13h40m32s372.jpg" alt="" width="840">
</p>

Este slide enumera as vantagens de um bom modelo de dados no Power BI: exploração de dados mais rápida, agregações mais simples de criar, relatórios mais precisos, menor tempo de escrita dos relatórios e facilidade de manutenção futura. O instrutor reforça que, embora a modelagem exija um esforço inicial, ela evita retrabalho e garante desempenho e confiabilidade aos relatórios.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-01-13h40m39s257.jpg" alt="" width="840">
</p>

O slide apresenta o princípio da simplicidade na modelagem. Um modelo menor é executado mais rapidamente, mais fácil de entender e ocupa menos espaço. Apesar de não ser trivial construir um modelo reduzido e eficiente, essa abordagem é essencial para sistemas analíticos, pois melhora a performance e simplifica a manutenção no Power BI.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-01-13h40m44s606.jpg" alt="" width="840">
</p>

A imagem destaca dois componentes fundamentais: o Power Query, utilizado para transformação e preparação dos dados, e o Star Schema (esquema estrela). O Star Schema é a modelagem dimensional mais adequada para sistemas analíticos, pois organiza tabelas fato e dimensões de forma otimizada para consultas e agregações, proporcionando eficiência na recuperação dos dados.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-01-13h40m51s895.jpg" alt="" width="840">
</p>

Este slide conclui que o Power BI e o Star Schema formam um “casamento perfeito”. O Power BI é uma ferramenta analítica que se beneficia diretamente da estrutura dimensional, permitindo agregações rápidas e relatórios performáticos. A imagem reforça que adotar o Star Schema potencializa o uso do Power BI, alinhando a ferramenta às boas práticas de modelagem para análise de dados.      


### 🟩 Vídeo 02 - Trabalhando com Tabelas

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.02-video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/modelagem-de-dados-no-power-bi/learning/938542ec-27cc-4f0f-add0-348e2b0fba3a?autoplay=1

Este segmento aborda a importância da modelagem de dados e da simplificação de tabelas no contexto do Power BI, visando otimizar a legibilidade, a navegabilidade e a qualidade dos relatórios e visuais gerados.

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-01-13h56m01s936.jpg" alt="" width="840">
</p>

A imagem apresenta duas vantagens principais de se trabalhar com tabelas simples em modelagem de dados. A primeira destaca a **navegabilidade**: tanto a navegação entre colunas quanto a navegação geral da tabela tornam-se mais amigáveis para o usuário, facilitando a exploração dos dados. A segunda reforça a importância de se manter **relações de boa qualidade** entre as tabelas, ou seja, conexões claras e bem definidas que fazem sentido para o negócio. Esses pontos são fundamentais para garantir que o modelo seja compreensível e eficiente na prática.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-01-13h56m05s863.jpg" alt="" width="840">
</p>

Aqui a imagem enfatiza outra estratégia para simplificar a estrutura de dados: **mesclar ou acrescentar tabelas**. Ao unir tabelas que possuem relacionamentos simples (especialmente relacionamentos um-para-um), é possível reduzir a quantidade de tabelas no modelo, tornando-o mais enxuto e fácil de gerenciar. Essa prática é incentivada em ambientes analíticos como o Power BI, pois evita complexidade desnecessária e facilita a construção de relatórios com foco na análise, sem perder a integridade dos dados.      

### 🟩 Vídeo 03 - Explorando Recursos das Tabelas no Power BI Desktop

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.02-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/modelagem-de-dados-no-power-bi/learning/cc87fcb8-a586-46fa-a53b-c628f37e6604?autoplay=1

Este vídeo foca na importância da modelagem de dados eficiente dentro do Power BI. O instrutor utiliza um relatório de exemplo para demonstrar como organizar tabelas, criar relacionamentos e a importância de manter o modelo "enxuto". O conteúdo prepara o terreno para o uso avançado de DAX (Data Analysis Expressions) e a transição para modelos dimensionais (Star Schema).

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-01-14h18m00s489.jpg" alt="" width="840">
</p>

A imagem apresenta a visualização do modelo de dados (Model View) no Power BI, mostrando as tabelas do relatório *Report Sample*. É possível identificar as principais entidades: **Sales** (foco das análises), **Time**, **Store**, **District**, **DM** (District Manager) e outras. As setas entre as tabelas indicam os relacionamentos, todos do tipo um‑para‑muitos (1:N), que são os mais comuns em modelos dimensionais. O layout inicial ainda está um pouco desorganizado, destacando a importância de arrumar a disposição das tabelas para facilitar a leitura do modelo.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-01-14h18m11s653.jpg" alt="" width="840">
</p>

Após reorganizar a disposição, o modelo fica mais claro e legível. Aqui vemos as tabelas alinhadas e as relações de forma mais intuitiva. A tabela **Time** (calendário) se relaciona com **Sales** pelo campo de data, permitindo análises temporais. A tabela **Store** está ligada a **Sales** e também a **District**, que por sua vez se conecta a **DM**. Essa estrutura reflete a busca pela simplicidade: manter apenas as tabelas e relacionamentos necessários para o negócio, evitando complexidade desnecessária.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-01-14h18m40s363.jpg" alt="" width="840">
</p>

Agora a atenção se volta para a tabela **Sales**, com seus campos expandidos. Observamos que o Power BI já criou automaticamente medidas de soma (Σ) para colunas numéricas como *Sum Gross Margin Amount*, *Markdown Sales Dollar*, etc. Além disso, há colunas de ID (identificadores), que em geral não são ideais para exibição direta em relatórios – o recomendado é utilizar os nomes descritivos (como *District Name*, *Store Name*) para tornar os visuais mais compreensíveis. Essa seleção de quais colunas manter ou ocultar faz parte do trabalho de enxugamento do modelo.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-01-14h18m47s476.jpg" alt="" width="840">
</p>

Este slide apresenta visuais analíticos (por exemplo Total Sales Variance e Sales per Sq Ft) e rótulos que comparam desempenho por store number e district. É um exemplo de visualização que combina métricas de variação e densidade de vendas por área, útil para identificar lojas com desempenho atípico e para orientar decisões operacionais. A imagem ilustra também a importância de hierarquias e drill‑down para investigar concentrações de vendas.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-01-14h18m49s902.jpg" alt="" width="840">
</p>

Aqui vemos a ferramenta de colunas/tabela com propriedades de campo (nome, tipo de dados, resumo) e uma lista de valores do Distrito (District) — incluindo identificadores e nomes. Também aparece o cartão do DM (District Manager), indicando que o Power BI reconhece campos de pessoa e pode exibir imagens/avatars associados. Esse painel é usado para ajustar metadados das colunas (tipo, formato, categorização) antes de construir relacionamentos e medidas.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-01-14h18m55s090.jpg" alt="" width="840">
</p>

A imagem ilustra o uso de hierarquias e a funcionalidade de *drill down* (aprofundamento) no Power BI. Ao clicar no botão de expandir (seta dupla) em um gráfico ou tabela, é possível navegar pelos níveis hierárquicos – por exemplo, partir de uma visão agregada por distrito e descer até o detalhe por loja ou por gerente. Esse recurso, combinado com as medidas DAX, permite explorar os dados em diferentes granularidades sem a necessidade de criar vários visuais separados, mantendo o relatório enxuto e interativo.      

### 🟩 Vídeo 04 - Gerenciando Relacionamentos no Power BI Desktop

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.02-video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/modelagem-de-dados-no-power-bi/learning/f62de80f-f7ac-4ae1-a69c-0f2289b9f3f1?autoplay=1

O vídeo apresenta as nuances da configuração de relacionamentos entre tabelas no Power BI, focando na interface de gerenciamento, nos tipos de cardinalidade (especialmente o "Muitos para Muitos") e na importância da direção dos filtros. O objetivo é garantir a integridade dos dados e a eficiência das consultas ao modelar bases complexas.

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-01-14h35m20s774.jpg" alt="" width="840">
</p>

A imagem mostra a janela **Gerenciar Relações** do Power BI, onde o instrutor está verificando os relacionamentos existentes entre as tabelas do modelo. Neste momento, ele observa os campos `MonfID` e `ItemID`, indicando a relação entre as tabelas que contêm esses identificadores. A interface permite editar a cardinalidade, a direção do filtro e até mesmo ativar a detecção automática de relacionamentos. É a partir dessa janela que o instrutor começará a explorar as opções de cardinalidade, incluindo a configuração “muitos para muitos”.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-01-14h35m46s926.jpg" alt="" width="840">
</p>

A imagem exibe o diálogo **Editar Relação**, com a opção **Muitos para muitos** selecionada. Abaixo da seleção, aparece o aviso típico do Power BI: “Esta relação tem cardinalidade muitos para muitos. Isso só deve ser usado se for esperado que nenhuma das colunas contenha valores exclusivos e que o comportamento significativamente diferente das relações muitos para muitos seja compreendido”. O instrutor utiliza essa tela para demonstrar quando e por que esse tipo de cardinalidade pode ser aplicado, alertando sobre a necessidade de compreender seu impacto na modelagem dos dados.      

### 🟩 Vídeo 05 - Considerações sobre Relacionamentos e Esquema Company com Power BI Desktop

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.02-video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/modelagem-de-dados-no-power-bi/learning/3959f3d2-16fc-4454-8415-1ec3bbcde659?autoplay=1

O vídeo apresenta o processo de transição de um banco de dados relacional para um modelo dimensional (Star Schema), destacando as melhores práticas de tratamento de dados, resolução de erros e estratégias de análise de processos internos.

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-01-14h52m10s790.jpg" alt="" width="840">
</p>

Nesta imagem vemos a interface do Power BI aberta com um projeto derivado de um banco de dados relacional. O ponto central aqui é que não se trata de uma *sample* padrão do Power BI, mas sim de um projeto próprio, justamente porque as amostras não permitem modificações nos dados. A explicação destaca que, para criar um modelo em estrela, é necessário importar e transformar os dados de forma controlada. O professor alerta que erros podem surgir durante as transformações — como tabelas não importadas — e recomenda desfazer a última ação e salvar frequentemente para identificar o que funcionou ou não.  

O objetivo didático é mostrar como partir de tabelas relacionais (Project, Department, Employee, Dep_Locations, etc.) e reorganizá-las em um esquema dimensional. Esse processo é fundamental para análises de carga de trabalho, projetos e departamentos, evitando problemas de relacionamentos muitos-para-muitos. Assim, a imagem serve como introdução prática ao desafio de transformar dados relacionais em um **Star Schema** dentro do Power BI.
     

### 🟩 Vídeo 06 - Construindo Company Star Schema: Mesclando Colunas e Modificando Campos

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.02-video_06.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/modelagem-de-dados-no-power-bi/learning/dc372358-e1b3-42af-b870-0d34f5378114?autoplay=1

O vídeo descreve o processo técnico de converter um banco de dados relacional (baseado no modelo "Company" do MySQL) em um Star Schema (Esquema Estrela) otimizado para análise no Power BI. O foco está na criação de uma tabela fato consolidada através de mesclagens e limpeza de dados.

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-01-15h32m36s558.jpg" alt="" width="840">
</p>

Esta imagem mostra a interface do **Power Query** durante o processo de *mesclagem* de consultas para construir uma tabela fato a partir das tabelas relacionais da base `company`. O foco visual é a seleção de tabelas/colunas para mesclar (por exemplo, **employee** com **project**) e as opções de junção (orientação à esquerda), indicando que o autor está consolidando informações de colaboradores e projetos para formar uma nova consulta/tabela.  
**Passos visíveis e seu propósito**:  
- **Selecionar tabelas e colunas correspondentes** para criar a mescla (join) que trará os projetos associados a cada colaborador.  
- **Remover colunas irrelevantes** (birth date, middle name, address, salary) para deixar apenas atributos úteis à análise.  
- **Expandir/selecionar** apenas o `pname` (nome do projeto) ao invés de agregar, preservando instâncias redundantes esperadas em uma tabela fato.  
- **Adicionar coluna de índice** para criar uma surrogate key (ID SK) que servirá como chave artificial da nova tabela fato.  

Trecho do material de apoio: "Work zone e department. Ok. Aí o que que eu quero fazer? Eu quero criar uma nova tabela, certo? Então essa nova tabela eu posso gerar a partir de uma mescla." 


<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-01-15h33m02s720.jpg" alt="" width="840">


A imagem apresenta o painel de **Etapas Aplicadas** e anotações das transformações realizadas no Power Query: remoção e renomeação de colunas, mesclas sucessivas (project, department, employee para obter gerente), adição de índice e criação de colunas compostas (por exemplo, mesclar `fname` + `lastname` para formar **gerente**). Também há evidência de limpeza e verificação de inconsistências (colaboradores sem gerente atribuído).  
 
- Ao mesclar o campo de gerente (`super ssn`) com a tabela **employee**, o autor expande apenas `fname` e `lastname` e depois **mescla colunas** com um espaço para criar a coluna **gerente**.  
- É esperado haver **redundância** na tabela fato (várias linhas por colaborador quando associado a múltiplos projetos); por isso a surrogate key e a remoção de IDs numéricos são usadas para tornar a tabela mais legível.  
Trecho do material de apoio: "Eu venho para cá e agora o que que eu tenho, tá gente? Pera aí, primeiro eu tenho que conseguir puxar para cá, né? Agora sim. Eu tenho aqui o ID de cada um deles, o nome, a matrícula e no caso o projeto que ele trabalha." 

      
### 🟩 Vídeo 07 - Construindo Company Star Schema: Trabalhando nas Tabela Fato e Dimensão

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.02-video_07.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/modelagem-de-dados-no-power-bi/learning/4928cdbf-99ab-4516-8f1c-2c764f8f9b93?autoplay=1

Este vídeo descreve a transformação de dados brutos em um modelo relacional eficiente (Star Schema). O objetivo principal é criar uma Tabela Fato (F_Equipe) que consolide informações de horas trabalhadas, projetos e colaboradores, além de organizar as Tabelas de Dimensão.

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-01-15h55m58s144.jpg" alt="" width="840">
</p>

A imagem mostra a interface do Power Query com várias consultas listadas e colunas de uma tabela de colaboradores visíveis.  
**Explicação didática:** aqui o instrutor está preparando as tabelas de referência (employee, project, works_on) para iniciar mesclas; o foco visual é a lista de consultas e a identificação das colunas que serão usadas para relacionar colaboradores e projetos. Essa tela serve para ilustrar a etapa inicial de inspeção das fontes antes de criar a tabela fato.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-01-15h56m02s326.jpg" alt="" width="840">
</p>

A imagem apresenta a caixa de diálogo de mesclagem (merge) onde o usuário seleciona tabelas e colunas correspondentes para combinar dados. O slide evidencia o passo em que se escolhem as chaves de junção (por exemplo, SSN/Essn e Pno/Pnumber) e o tipo de junção (ex.: externa esquerda). É nessa etapa que se define se a mescla será criada como nova consulta — prática recomendada para testar sem alterar as originais.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-01-15h56m09s108.jpg" alt="" width="840">
</p>

A imagem mostra a pré-visualização do resultado da mesclagem, com linhas correspondentes entre colaboradores e projetos e valores numéricos visíveis. O instrutor verifica se a mescla trouxe a coluna desejada (por exemplo, Pname) e confere se os pares colaborador–projeto apresentam os valores esperados (como horas atribuídas). Essa verificação visual é essencial para garantir que as chaves escolhidas produziram correspondências corretas.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-01-15h56m11s614.jpg" alt="" width="840">
</p>

A imagem exibe uma tabela resultante com colunas como colaborador, gerente, nome do projeto, departamento e uma coluna de horas. Este é o momento em que a nova consulta já contém a **quantidade de horas por colaborador por projeto** — a base para construir a tabela fato. O instrutor renomeia a coluna para "horas trabalhadas" e prepara a tabela (F_equipe) para uso posterior como fato no modelo.
 

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-01-15h56m18s742.jpg" alt="" width="840">
</p>

A imagem mostra o painel de etapas aplicadas (Applied Steps) e a organização das consultas, indicando renomeações e mesclas realizadas. O painel evidencia o histórico de transformações (mesclas, remoção/renomeação de colunas, criação de índice). Isso permite rastrear e reverter alterações se necessário; também demonstra a boa prática de criar consultas intermediárias (ex.: distribuição de horas) antes de consolidar a tabela fato.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-01-15h57m13s958.jpg" alt="" width="840">
</p>

A imagem mostra o objetivo final: ter uma **tabela fato** (F_equipe / distribuição de horas) conectada a dimensões (colaborador, projeto, departamento, localizações, dependentes). O próximo passo natural é criar os relacionamentos entre essas tabelas no modelo para possibilitar análises por colaborador, projeto e local.      


### 🟩 Vídeo 08 - Construindo Company Star Schema: Realizando adequações e Estabelecendo Relacionamentos

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.02-video_08.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/modelagem-de-dados-no-power-bi/learning/4ddf4681-85d8-470e-a586-4b4d3638eb19?autoplay=1

Este vídeo foca no processo prático de gerenciamento de relacionamentos e transformação de dados em uma ferramenta de Business Intelligence (como o Power BI). O instrutor demonstra como lidar com inconsistências de nomes, realizar limpezas necessárias e estruturar tabelas para garantir que os dados se conectem de forma lógica e eficiente.

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-02-10h02m12s861.jpg" alt="" width="840">
</p>

A captura apresenta a interface de um projeto Power BI / Power Query com menus de navegação e opções de transformação de dados (por exemplo, *Transformar dados*, *Gerenciar relações*, *Mesclar colunas*). A imagem evidencia o fluxo típico de preparação: identificação de tabelas (equipe, colaboradores), tentativa de criar relacionamentos e ações de transformação (mesclar, dividir colunas) para garantir chaves consistentes entre tabelas.

**Por que é importante:** o slide ilustra um problema prático comum ao modelar dados relacionais: *matching parcial* entre campos de nomes que impede relacionamentos unívocos. A solução demonstrada no vídeo é aplicar transformações (mesclar quando necessário, depois dividir por delimitador) para obter colunas com formato adequado e permitir a criação de relacionamentos corretos entre fatos e dimensões. "[00:00:00] OK, pessoal, então vamos ver aqui, ó. Primeira coisa que eu tenho: equipe, colaboradores, né? E aqui o colaborador." 

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-04-02-10h02m17s312.jpg" alt="" width="840">
</p>

Aqui são mostrados trechos da tela do Editor do Power Query e exemplos de transformações aplicadas (etapas como *Colunas mescladas*, *Colunas reordenadas*, e operações sobre tabelas dependentes). Ela também exibe uma expressão/linha que remete à remoção de colunas ou referência a constraints em uma tabela, indicando manipulação programática das etapas de transformação.

**Observações práticas:** no vídeo o instrutor testa a aplicação das etapas e resolve falhas de carregamento voltando etapa a etapa (por exemplo, aplicar mesclagem, testar, dividir coluna por delimitador quando necessário). Essa abordagem — aplicar transformações incrementalmente e validar o carregamento — evita retrabalho e ajuda a identificar exatamente qual etapa causa erro. "[00:01:30] Venho aqui, aplico. OK, então ele não teve problema. O ideal aqui então, pessoal, seria realmente aqui em equipe que este caso, esse aqui, eu vou dividir a coluna, tá?" 

### 🟩 Vídeo 09 - Construindo Company Star Schema: Mesclando Tabelas e Adicionando Colunas de Exemplos

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.02-video_09.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/modelagem-de-dados-no-power-bi/learning/d3765e14-b696-46a1-9723-c2fced4a70fa?autoplay=1

### 🟩 Vídeo 10 - Considerações sobre Transformação de Dados no Power BI Desktop

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.02-video_10.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 11 - Por que Precisamos Criar uma Tabela Calendário?

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.02-video_11.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 12 - Criando a Tabela Calendário por Medida utilizando Calendar() com Power BI Desktop

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.02-video_12.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 13 - O que é Hierarquia de Dados?

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.02-video_13.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 14 - Criando Hierarquia de Dados com Power BI Desktop

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.02-video_14.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 15 - Criando Hierarquia de Dados com Estrutura de Pais/Filhos com Power BI Desktop

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.02-video_15.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 16 - Granularidade de Dados com Power BI

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.02-video_16.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 17 - Definindo a Granularidade de Dados para Datas com Power BI

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.02-video_17.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 18 - Criando um Relacionamento entre Financials e Tabela Calendário

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.02-video_18.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 19 - Criando uma Coluna Personalizada no Power BI

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.02-video_19.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 20 - Criando um Campo Data com Maior Granularidade

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.02-video_20.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 21 - Relembrando Conceitos

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.02-video_21.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 22 - Resolvendo Desafios de Modelagem no Power BI

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.02-video_22.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 23 - Considerações sobre o curso

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.02-video_23.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:


##  Materiais de Apoio

# Certificado: 

- Link na plataforma: 
- Certificado em pdf: