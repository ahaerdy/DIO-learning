## Instrutor:

- Juliana Mascarenhas (Tech Education Specialist / Sócia (Content Creator) @SimplificandoRedes / Me Modelagem Computacional / Cientista de dados)
- Contato Linkedin: / [juliana-mascarenhas-ds](https://www.linkedin.com/in/juliana-mascarenhas-ds/)

## Parte 1 - Fundamentos de Modelagem Dimensional

### 🟩 Vídeo 01 - Apresentando a ementa do curso

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.01-video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/fundamentos-de-modelagem-dimensional/learning/cfd67c81-3158-4c81-a31f-0a338544aadc?autoplay=1

Este curso, ministrado por Juliana Mascarenhas, aborda a transição fundamental entre a modelagem de dados tradicional (relacional/transacional) e a modelagem dimensional. O foco é capacitar profissionais de dados (analistas de BI, cientistas e engenheiros) a estruturarem informações de forma que facilitem a análise e a tomada de decisão estratégica.

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-30-13h50m42s339.jpg" alt="" width="840">
</p>

Este slide apresenta o tema central da aula: **Introdução à Modelagem Dimensional**. O conteúdo faz parte da **Formação Power BI Analyst** e é ministrado por **Juliana Mascarenhas**, especialista em educação tecnológica, mestre em modelagem computacional e cientista de dados. A imagem introduz o contexto do curso, indicando que os conceitos abordados são fundamentais para profissionais que trabalham com dados, desde analistas até engenheiros, pois a modelagem dimensional é uma prática essencial para estruturar informações de forma analítica e otimizada.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-30-13h50m44s357.jpg" alt="" width="840">
</p>

Neste slide são listados os principais objetivos que serão explorados ao longo do conteúdo:
- **Entender o que é modelagem dimensional** – conceito base para organizar dados voltados à análise.
- **O que é Cubo multifacetado?** – estrutura que permite visualizar dados sob múltiplas perspectivas.
- **Principais modelos dimensionais** – conhecer os esquemas mais utilizados na prática.
- **Sistemas Transacionais e Analíticos** – diferenciar os ambientes de operação (OLTP) e de análise (OLAP).
- **Comparação entre transacional e dimensional** – entender as vantagens e aplicações de cada abordagem.
- **Criação de esquemas transactional e dimensional** – aplicação prática dos conceitos por meio da construção de modelos.

Esses tópicos servem como roteiro para a jornada de aprendizado, destacando a importância da modelagem dimensional para a construção de soluções de Business Intelligence eficientes.

### 🟩 Vídeo 02 - Desmistificando a Modelagem Dimensional

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.01-video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/fundamentos-de-modelagem-dimensional/learning/772fc7ed-b21b-404b-9f0c-045e69a5bd87?autoplay=1

Este vídeo explora os fundamentos da modelagem dimensional, contrastando-a com a modelagem relacional tradicional. O foco principal é entender por que as empresas precisam de estruturas de dados diferentes para operações do dia a dia e para análises estratégicas de grande escala.

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-30-14h16m58s843.jpg" alt="" width="840">
</p>

A modelagem dimensional costuma gerar ruído e dúvidas, especialmente para quem está acostumado com bancos de dados relacionais. Nesta aula, vamos desmistificar esse conceito, mostrando que ele pode ser até mais simples do que a modelagem relacional tradicional. O objetivo é construir uma base sólida para que você entenda por que e como aplicar esse tipo de modelagem em projetos analíticos, especialmente no Power BI.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-30-14h17m03s504.jpg" alt="" width="840">
</p>

A modelagem dimensional surgiu de uma lacuna: o modelo relacional, excelente para transações do dia a dia, mostrou-se insuficiente para atender às demandas de análise de grandes volumes de dados. Por isso, novas necessidades emergiram – **performance**, **escalabilidade** e **disponibilidade**. Em vez de executar consultas complexas diretamente no banco de dados operacional (o que poderia comprometer sua performance), passamos a usar um modelo separado, otimizado para consultas analíticas, garantindo que os dados estejam sempre acessíveis e que as respostas sejam rápidas.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-30-14h17m10s556.jpg" alt="" width="840">
</p>

Para entender a modelagem dimensional, é preciso diferenciar os dois grandes tipos de sistemas de dados:
- **Modelo transacional (tradicional)**: voltado para operações diárias (inserir, atualizar, deletar). Possui estrutura rígida, normalizada, e foco em integridade e concorrência.
- **Modelo dimensional (analítico)**: voltado para consultas e análises. Permite estruturas desnormalizadas, com foco em leitura rápida e facilidade de interpretação para negócios.

Cada um atende a um propósito distinto, e a escolha depende do cenário: operacional ou analítico.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-30-14h17m14s083.jpg" alt="" width="840">
</p>

O **modelo transacional** é desenhado para:
- **Fim específico**: suportar uma aplicação ou processo de negócio.
- **Cenário otimizado**: estrutura bem definida, com chaves, índices e restrições de integridade.
- **Suporte a operação**: lida com as transações do dia a dia (vendas, cadastros, movimentações).
- **SGBDs**: utiliza sistemas de gerenciamento de banco de dados relacionais (como SQL Server, Oracle, PostgreSQL) que garantem ACID (Atomicidade, Consistência, Isolamento, Durabilidade).

É a base dos sistemas operacionais das empresas, mas não é o ambiente ideal para grandes consultas analíticas.

<p align="center">
  <img src="000-Midia_e_Anexos/vlcsnap-2026-03-30-14h17m16s826.jpg" alt="" width="840">
</p>

Já o **modelo analítico** (dimensional) possui características que o tornam poderoso para BI e análises:
- **Permite redundâncias**: a desnormalização é bem-vinda, pois reduz a necessidade de joins complexos e acelera as consultas.
- **Esquema flexível**: estruturas como star schema e snowflake schema facilitam a navegação pelos dados.
- **Foco em análises**: tudo é pensado para responder perguntas de negócio rapidamente.
- **Modelo em cubo**: os dados são organizados em torno de fatos (medidas) e dimensões (atributos descritivos), formando uma visão multidimensional que facilita a exploração e a descoberta de insights.

Esse é o modelo que usamos em ferramentas como Power BI para construir relatórios performáticos e intuitivos.      

### 🟩 Vídeo 03 - O que significa Modelagem em Cubo?

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.01-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/engenharia-dados-python/course/fundamentos-de-modelagem-dimensional/learning/e56fe8c0-7fef-40c1-aca1-92aef529cf35?autoplay=1



### 🟩 Vídeo 04 - Entendendo Modelagem com Start Schema

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.01-video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 05 - Tipos de modelos dimensionais: Start Schema, Snowflake e Constellation

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.01-video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 06 - Explorando Brevemente os Modelos Dimensionais

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.01-video_06.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 07 - O que é Granularidade de dados?

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.01-video_07.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 08 - Chave Artificial com Start Schema

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.01-video_08.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 09 - Modelando o Esquema Relacional Base

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.01-video_09.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 10 - Definindo os Relacionamentos do Modelo Relacional

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.01-video_10.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 11 - Criando o Modelo Dimensional com Base no Relacional

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.01-video_11.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 12 - Criando os Relacionamentos do Star Schema

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.01-video_12.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 13 - Rastreando Modificações no Modelo - Slowly Changing Dimensions

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.01-video_13.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 14 - Modificando o Star Schema para mapear Modificações nos Dados

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data-modulo.08-curso.01-video_14.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

##  Materiais de Apoio

# Certificado: 

- Link na plataforma: 
- Certificado em pdf: