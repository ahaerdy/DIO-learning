## Instrutor:

- ROtávio Reis Perkles (Desenvolvedor Full-stack Sênior (Python - Java) / Líder técnico)
- Contato Linkedin: / [operkles](https://www.linkedin.com/in/operkles/)

# Parte 1 - Introdução ao Git

## 🟩 Vídeo 01 - Entendendo o que é Git e sua importância

![[bootcamp_tqi_fullstack-modulo_01-Curso_05-Video_01.webm|vid-60]]

Link do vídeo:  https://web.dio.me/track/tqi-fullstack-developer/course/introducao-ao-git-e-ao-github/learning/75b9fe49-6ed4-4480-83a7-7e37fc356aa9?autoplay=1

O vídeo apresenta uma introdução ao curso de **Git e GitHub** ministrado por Otávio Reis, que utiliza a analogia de um jornalista revisando textos para explicar a importância do **controle de versão**. O instrutor detalha que o **Git** foi criado por **Linus Torvalds** para gerenciar o desenvolvimento colaborativo do kernel do Linux de forma eficiente e organizada. É enfatizada a distinção fundamental entre o Git, que é a ferramenta local de versionamento, e o **GitHub**, uma plataforma em nuvem da Microsoft para hospedagem de código e interação social. O curso promete benefícios como o aprimoramento do **trabalho em equipe**, reconhecimento na comunidade e armazenamento seguro de projetos. Por fim, a metodologia une **teoria e prática** através da criação de um repositório de receitas para facilitar a compreensão de conceitos abstratos por iniciantes.


# Parte 2 - Navegação via command line interface e instalação

## 🟩 Vídeo 02 - Comandos básicos para um bom desempenho no terminal

![[bootcamp_tqi_fullstack-modulo_01-Curso_05-Video_02.webm|vid-60]]

Link do vídeo:  https://web.dio.me/track/tqi-fullstack-developer/course/introducao-ao-git-e-ao-github/learning/5008950d-a42b-4fc3-813b-fdaeebc86529?autoplay=1

Esta fonte consiste em uma aula técnica que ensina **comandos básicos de terminal** para navegação e manipulação de arquivos em diferentes sistemas operacionais. O instrutor justifica o uso da **interface de linha de comando (CLI)**, em vez de interfaces gráficas, para proporcionar uma compreensão mais profunda do funcionamento do **Git**. São apresentadas as equivalências de comandos entre o **Windows** e sistemas baseados em **Unix**, como Linux e macOS, para listar, criar, acessar e remover diretórios. Além disso, o conteúdo aborda o uso de **atalhos e flags** que otimizam o fluxo de trabalho e a limpeza do ambiente virtual. O objetivo final é capacitar o aluno a gerenciar pastas e arquivos com segurança antes de iniciar a instalação do controle de versão.


## 🟩 Vídeo 03 - Realizando a instalação do GIT

![[bootcamp_tqi_fullstack-modulo_01-Curso_05-Video_03.webm|vid-60]]

Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/introducao-ao-git-e-ao-github/learning/014fe14a-dc5a-41ec-9965-755a79694f27?autoplay=1

Este guia tutorial ensina como realizar a **instalação do Git** nos sistemas operacionais **Windows, Linux e macOS**, enfatizando a importância de manter o software atualizado. O autor destaca que versões a partir da **2.30** trazem mudanças críticas, como a transição para o **Git Credential Manager Core** e a nova nomenclatura da branch padrão para **"main"**. No **Windows**, o processo é feito via instalador oficial, garantindo que ferramentas como o **Git Bash** sejam integradas ao sistema. Para usuários de **Linux**, a recomendação é utilizar repositórios **PPA** para obter a versão mais recente, enquanto no **macOS** sugere-se o gerenciador de pacotes **Homebrew**. O objetivo central é padronizar o ambiente de desenvolvimento para evitar erros de compatibilidade e permissão durante o uso de repositórios remotos.

# Parte 3 - Entendendo como o Git funciona por baixo dos panos

## 🟩 Vídeo 04 - Tópicos fundamentais para entender o funcionamento do Git

![[bootcamp_tqi_fullstack-modulo_01-Curso_05-Video_04.webm|vid-60]]

Link do vídeo:  https://web.dio.me/track/tqi-fullstack-developer/course/introducao-ao-git-e-ao-github/learning/0f9c0907-c8dd-4cf4-b9f5-05f5dd486875?autoplay=1

Esta fonte consiste em uma aula técnica que explora o **funcionamento interno do Git**, focando especialmente em como a tecnologia gerencia dados de forma invisível ao usuário. O instrutor destaca a importância do **algoritmo SHA-1**, uma função de encriptação que gera identificadores únicos de 40 caracteres para garantir a **integridade e segurança** dos arquivos. Através de exemplos práticos no **Git Bash**, demonstra-se que qualquer alteração mínima em um documento resulta em uma assinatura digital completamente nova. Além disso, o conteúdo orienta sobre a personalização da interface do terminal e a utilização de comandos de navegação para facilitar o fluxo de trabalho. O objetivo central é capacitar o aluno a entender a **lógica estrutural** do sistema antes de avançar para comandos práticos complexos.


## 🟩 Vídeo 05 - Objetos internos do Git


Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/introducao-ao-git-e-ao-github/learning/02d99abe-e83c-4800-9100-a4258119a781?autoplay=1

O vídeo explica os fundamentos técnicos que tornam o **Git** um sistema de versionamento **seguro** e **distribuído**, focando em seus três objetos principais. As **blobs** armazenam o conteúdo bruto dos arquivos e seus metadados, enquanto as **trees** organizam a estrutura de diretórios e nomes. Os **commits** consolidam essas informações, vinculando o estado do projeto a autores, mensagens e registros temporais por meio de identificadores únicos. Toda essa arquitetura é protegida pela criptografia **SHA-1**, que gera um código específico para cada alteração, garantindo que qualquer modificação mínima invalide o histórico anterior. Essa interdependência assegura a integridade total dos dados, permitindo que cada cópia do repositório funcione como um backup **confiável** e autêntico do projeto original.

### Anotações

#### Objetos Fundamentais do Git

O Git baseia seu funcionamento em três tipos básicos de objetos responsáveis pelo versionamento do código: **blobs**, **trees** e **commits**. Entender esses componentes é essencial para compreender por que o Git é um sistema distribuído e seguro.

![[vlcsnap-2025-12-23-13h56m32s682.jpg|w60]]

#### Blobs (Binary Large Objects)

O primeiro objeto fundamental é a **blob**. No Git, as blobs são usadas para armazenar o conteúdo dos arquivos. Para gerar um identificador único para esse conteúdo, o Git utiliza a função `hash-object`, que retorna um **SHA-1** (um identificador de 40 caracteres).

![[vlcsnap-2025-12-23-13h56m58s772.jpg|w60]]

```bash
echo 'conteudo' | git hash-object --stdin
> fc31e91b26cf85a55e072476de7f263c89260eb1

echo -e 'conteudo' | openssl sha1
> 65b0d0dda479cc03cce59528e28961e498155f5c
```

Como demonstrado acima, o hash gerado pelo Git difere de um hash SHA-1 comum aplicado apenas à string. Isso ocorre porque o Git adiciona metadados ao objeto antes de realizar a criptografia.

#### Estrutura Interna de uma Blob

Uma blob não contém apenas o texto ou binário do arquivo; ela inclui um cabeçalho com o tipo do objeto, o tamanho do conteúdo, um caractere nulo (`\0`) e, finalmente, o conteúdo de fato.

![[vlcsnap-2025-12-23-13h59m32s279.jpg|w60]]

```bash
echo 'conteudo' | git hash-object --stdin
> fc31e91b26cf85a55e072476de7f263c89260eb1

echo -e 'blob 9\0conteudo' | openssl sha1
> fc31e91b26cf85a55e072476de7f263c89260eb1
```

Ao incluirmos manualmente os metadados no comando `openssl`, o hash gerado torna-se idêntico ao do Git.

#### Árvores (Trees)

Enquanto as blobs armazenam o conteúdo, as **trees** (árvores) organizam esses conteúdos, funcionando como diretórios. Uma tree armazena referências para blobs ou para outras trees (permitindo recursividade/pastas dentro de pastas) e, crucialmente, é nela que o **nome do arquivo** fica guardado.

![[vlcsnap-2025-12-23-14h01m08s404.jpg|w60]]

Diferente da blob, que só conhece o conteúdo, a tree é responsável por montar a estrutura de onde os arquivos estão localizados no sistema operacional.

![[vlcsnap-2025-12-23-14h04m01s796.jpg|w60]]

Como as trees também possuem hashes baseados em seus metadados e referências, qualquer alteração mínima em um arquivo (blob) alterará o hash da tree que o contém, criando uma reação em cadeia que garante a integridade dos dados.

#### Commits

O **commit** é o objeto que une todas as informações e dá sentido às alterações. Ele aponta para uma tree específica e contém metadados vitais: o commit "pai" (referência ao estado anterior), o autor, a mensagem explicativa e o *timestamp* (data e hora).

![[vlcsnap-2025-12-23-14h04m38s073.jpg|w60]]

A geração do SHA-1 do commit é baseada em todas essas informações. Portanto, se você mudar uma vírgula em um arquivo, o hash da blob muda, o que altera o hash da tree e, consequentemente, gera um hash de commit totalmente novo.

#### Integridade e o Sistema Distribuído

A relação hierárquica entre esses objetos (Commit -> Tree -> Blob) é o que torna o Git extremamente seguro e confiável.

![[vlcsnap-2025-12-23-14h08m08s986.jpg|w60]]

Por causa dessa estrutura, é praticamente impossível alterar o histórico de um código de forma maliciosa sem que isso quebre a cadeia de hashes e fique evidente.

![[vlcsnap-2025-12-23-14h08m52s927.jpg|w60]]

Isso permite que o Git seja um sistema distribuído: como cada colaborador possui uma cópia local completa com todos esses objetos e hashes íntegros, qualquer máquina pode servir como um backup de segurança caso o servidor central (como o GitHub) falhe.

## 🟩 Vídeo 06 - Chave SSH e Token


Link do vídeo: https://web.dio.me/track/tqi-fullstack-developer/course/introducao-ao-git-e-ao-github/learning/7410b862-1989-421a-a48d-500db5857f53?autoplay=1



# Parte 4 - Primeiros comandos com Git

## 🟩 Vídeo 07 - Iniciando o Git e criando um commit


Link do vídeo:  


# Parte 5 - Ciclo de vida dos arquivos no Git

## 🟩 Vídeo 08 - Passo a passo no ciclo de vida


Link do vídeo:  


# Parte 6 - Introdução ao GitHub

## 🟩 Vídeo 09 - Trabalhando com o GitHub


Link do vídeo:  


# Parte 7 - Resolvendo conflitos

## 🟩 Vídeo 10 - Como os conflitos acontecem no GitHub e como resolvê-los


Link do vídeo:  


##  Materiais de Apoio


# Certificado: 

- Link na plataforma: 
- Certificado em pdf: 
