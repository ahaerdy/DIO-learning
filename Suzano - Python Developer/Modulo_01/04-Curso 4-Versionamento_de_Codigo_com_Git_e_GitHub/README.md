# Parte 1: Visão Geral do Curso e Ferramentas

## Vídeo 01 - Introdução ao Curso

- Instrutor: Elidiana Andrade (Tech Education Analyst at DIO)
- Contato Libkedin: https://www.linkedin.com/in/elidiana/
 
- A autora se apresenta e dá uma visão geral do curso:
    - Conhecer as ferramentas.
    - Instalar, configurar e autenticar
    - Primeiros passos com Git e Github
    - Dicas e materiais de apoio 

## Vídeo 02 - O que é Versionamento de Código
 
- Sistemas de controle de versão:
    - Registrar o histórico de atualizações de um arquivo; 
    - Gerenciar quais foram as alterações, a data, autor, etc.;
    - Organizar, controlar e garantir a segurança.

- Tipos de Sistemas de Controle de Versão:
    - VCS Centralizado (CVCS) - Ex.: CVS, Subversion.
    - VCS Distribuído (DVCS) - Ex: Git, Mercurial.

- VCS Distribuído (DVCS)
    - Clona o repositório completo, o que inclui o histórico de versões.
    - Cada clone é como um backup;
    - Possibilita um fluxo de trabalho flexível;
    - Possibilidade de trabalhar sem conexão à rede.

## Vídeo 03 - O que é Git

- Sistema de Controle de Versão Distribuído
    - Gratuito e Open Source (Código Aberto);
    - Ramificações (branching) e fusões (merging) eficientes;
    - Leve e rápido.

- Referência: https://git-scm.com/ (site oficial do Git)
    - __Principais recursos__:
        - Download do Git para Windows, macOS e Linux
        - Documentação oficial com tutoriais e manuais
        - Livro gratuito "Pro Git" (em português também)
        - Referência de comandos Git com exemplos
        - Comunidade e traduções

- Breve histórico do Git
    - 2002: O projeto do núcleo (kernel) do Linux, que é open source, começa a utilizar o BitKeeper, um DVCS proprietário;
    - 2005: Após conflitos com a comunidade, o BitKeeper rescinde a licença gratuita. O que leva a Linus Torvalds, o criador do Linux, e sua equipe, a desenvolverem sua própria ferramenta, o Git.

- Fluxo Básico no Git:
    - git clone → clona um repositório Git existente para um novo diretório (pasta) local.
    - git commit → grava alterações no repositório.
    - git pull → “puxa” as alterações do repositório remoto para o local (busca e mescla).
    - git push → “empurra” as alterações do repositório local para o remoto.
 
## Vídeo 04 - O que é Github

- Plataforma de hospedagem de código para controle de versão com Git, e colaboração.
    - Comunidade ativa;
    - Utilizado mundialmente;
    - Mascote “Octocat”.
        - É uma criatura híbrida entre um gato e um polvo (cat + octopus).
        - Foi criado por Simon Oxley, um ilustrador japonês, e adotado pelo GitHub como símbolo da marca.
        - Representa o espírito divertido e amigável do GitHub.
        - É usado em material promocional, documentação, eventos, e como ícone da cultura open source.

- Breve Histórico do GitHub:
    - 2008: Desenvolvido por Chris Wanstrath, J. Hyett, Tom Preston-Werner e Scott Chacon.
    - 2018: Vítima de um dos maiores ataques de DDoS (ataque distribuído de negação de serviço); Comprado pela Microsoft Corporation por US $ 7,5 bilhões.

- Git ≠ GitHub (apresentação das diferenças)

- Autenticação de Dois Fatores
    - Acesse sua conta do GitHub e vá em Settings > Password and authentication > Two-factor authentication > Authenticator app
        - Leia o QR Code através do aplicativo autenticador (ex.: Microsoft Authenticator) e insira o código no GitHub;
        - Salve os códigos de recuperação;
        - Autenticação ativada!

- Referência: https://github.com/ (site oficial do Github)
    - Plataforma de hospedagem de código-fonte e controle de versão baseada no sistema Git.
    - __Principais recursos__:
        - Armazenar projetos de software (código-fonte, documentação, scripts etc.); - Controlar versões dos arquivos (ver o histórico de alterações);
        - Colaborar com outras pessoas desenvolvedoras (em equipes ou em projetos open source);
        - Publicar portfólios técnicos;
        - Automatizar processos (como testes e deploys com GitHub Actions).


# Parte 2: Instalação, Configuração e Autenticação
 
## Vídeo 05 - Instalando o Git no Windows
 
## Vídeo 06 - Instalando o Git no Linux (Ubuntu)
 
## Vídeo 07 - Configurando o Git
 
## Vídeo 08 - Autenticando via Token
 
## Vídeo 09 - Autenticando via Chave SSH

# Parte 3: Primeiros Passos com Git e GitHub
 
## Vídeo 10 - Criando e Clonando Repositórios
 
## Vídeo 11 - Salvando Alterações no Repositório Local
 
## Vídeo 12 - Desfazendo Alterações no Repositório Local
 
## Vídeo 13 - Enviando e Baixando Alterações com o Repositório Remoto
 
## Vídeo 14 - Trabalhando com Branches - Criando, Mesclando, Deletando e Tratando Conflitos
 
## Vídeo 15 - Trabalhando com Branches - Comandos Úteis no Dia a Dia
 
## Vídeo 16 - Dicas e Materiais de Apoio

### 📚 Recursos Úteis
#### 🔗 Repositório

- [elidianaandrade/git-github-learning-quest](https://github.com/elidianaandrade/git-github-learning-quest) — Repositório com material de apoio gamificado.

#### 🖥️ Slide

- Versionamento de Código com Git e GitHub.pptx — Apresentação utilizada no curso.

#### 🌐 Links Úteis

- [GitHub Quick Start](https://github.com/githubtraining/hellogitworld) — Repositório com link para aulas de Git e GitHub.
- [GitBook](https://github.com/github/certification): Formação GitHub Certification — Material textual sobre GitHub.
- [Documentação do GitHub](https://docs.github.com/pt) — Guia completo para uso do GitHub.
- [GitHub Markdown](https://guides.github.com/features/mastering-markdown/) — Guia específico para Markdown no GitHub.

#### 💡 Dicas

- Artigos/Fórum: você pode compartilhar conteúdos técnicos através de Artigos (visíveis globalmente na plataforma da DIO). Por outro lado, você também pode compartilhar suas conquistas e dúvidas usando os Fóruns (que são específicos para cada experiência educacional na DIO, como um Bootcamp por exemplo).
- Pesquise na Web: pode parecer óbvio, mas é importante frisar a importância das engines de busca no dia a dia de um profissional de TI. Caso não encontre o que procura dentro da DIO, pesquise sobre o assunto (conceito, dúvida, erro etc) na Internet
