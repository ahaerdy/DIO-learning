## Instrutores

- Elidiana Andrade (Tech Education Analyst at DIO)
	- Contato Linkedin: https://www.linkedin.com/in/elidiana/
- Venilton Falvo Jr (Doutor em Engenharia de Software, Education Tech Lead na DIO)
	- Contato Linkedin: https://www.linkedin.com/in/falvojr/

## 🟩 Vídeo 01 - Introdução ao Lab

![[bootcamp-ntt-data_modulo_01-Curso_06-Video_01.webm|vid-60]]

Link do vídeo:  https://web.dio.me/lab/desafio-de-projeto-contribuindo-em-um-projeto-open-source-no-github/learning/913f26fd-1018-4643-b59a-6356ea77dc2e?back=/track/engenharia-dados-python

Esta fonte apresenta um **laboratório prático** conduzido pela desenvolvedora Lidiana, com o objetivo de ensinar o **básico sobre contribuições em projetos de código aberto**. A instrutora orienta os participantes através de um processo simplificado no **GitHub**, visando aumentar a confiança de iniciantes para realizarem sua primeira colaboração. O conteúdo abrange desde a **teoria fundamental** até o desenvolvimento e envio de uma contribuição real, finalizando com **recursos de apoio** para estudos contínuos. Embora existam pré-requisitos sobre o uso de ferramentas de versionamento, a facilitadora revisa conceitos essenciais para garantir a **acessibilidade do aprendizado**. Assim, o material serve como um **guia introdutório** para quem deseja ingressar no ecossistema de desenvolvimento colaborativo.



## 🟩 Vídeo 02 - Formas de Contribuir num Projeto Open Source

![[bootcamp-ntt-data_modulo_01-Curso_06-Video_02.webm|vid-60]]

Link do vídeo:  https://web.dio.me/lab/desafio-de-projeto-contribuindo-em-um-projeto-open-source-no-github/learning/8e9e1f35-5571-40c4-863f-2279ba388aeb?back=/track/engenharia-dados-python

O vídeo consiste em um guia prático sobre como realizar contribuições em projetos de **código aberto** utilizando a plataforma **GitHub**. A fonte explica que softwares open source oferecem liberdade para uso e modificação, destacando a importância de verificar as **licenças** antes de colaborar. O conteúdo detalha procedimentos essenciais, como a abertura de **issues** para relatar bugs ou sugerir melhorias e o uso de **templates** para padronizar comunicações. Além disso, demonstra o processo de edição de arquivos, a aplicação de **commits** vinculados a problemas específicos e a organização de fluxos de trabalho. Por fim, o material introduz o conceito de **Pull Requests** como o caminho definitivo para integrar novas funcionalidades ao repositório original.

### Anotações

#### O que é um Projeto Open Source?

- É um projeto de Código Aberto, livre para ser utilizado, modificado e distribuído. Alguns exemplos são: 
    - [Linux](https://github.com/torvalds/linux), sob a licença GNU General Public License (GPL);
    - [Roadmaps DIO](https://github.com/digitalinnovationone/roadmaps), sob a licença MIT License (MIT). 

#### Formas de Contribuir em um Projeto Open Source

- Relatar algum problema ou bug;
- Sugerir melhorias ou adição de novos recursos;
- Escrever ou atualizar a documentação;
- Contribuir com o código;
- Divulgar e/ou doar.

#### Links úteis

- Repositório no GitHub: https://github.com/digitalinnovationone/dio-lab-open-source
- Documentação GitHub: https://docs.github.com/

#### Links complementares

- Markdown: https://docs.github.com/pt/get-started/writing-on-github
- Conventional Commits: https://github.com/conventional-commits/conventionalcommits.org 
- GitHub Etiquetas: https://docs.github.com/pt/issues/using-labels-and-milestones-to-track-work/managing-labels
- Visual Studio Code: https://code.visualstudio.com/download


#### Apresentação do Repositório do Desafio

- Repositório do desafio: https://github.com/digitalinnovationone/dio-lab-open-source
- Utilitários para o profile README: https://github.com/digitalinnovationone/dio-lab-open-source/tree/main/utils

#### Criação de uma Issue (sugestão de melhoria)

- Clicar em "Issues", depois em "New Issue" 
- Abrirá um caixa de seleção com os templates.
- Lista de templates de issue:
    - Bug report (Crie um relatório para nos ajudar a melhorar)
    - Community Challenge (Crie uma issue sobre o Desafio proposto à comunidade)
    - Feature request (Sugira uma ideia para este repositório)
    - Blank issue (Create a new issue from scratch)
- Escolher "Feature request" e "Get Started"
- Haverá um template com os campos a serem preenchidos.
- O vídeo segue com as ações propostas pela instrutora.


## 🟩 Vídeo 03 - Desenvolvendo uma Contribuição

![[bootcamp-ntt-data_modulo_01-Curso_06-Video_03.webm|vid-60]]

Link do vídeo: https://web.dio.me/lab/desafio-de-projeto-contribuindo-em-um-projeto-open-source-no-github/learning/4945958e-0c25-4a04-9d86-29cc7ea82b6a

O tutorial detalha o processo técnico para realizar contribuições em projetos de **código aberto** no GitHub, utilizando como exemplo um desafio da plataforma **DIO**. O procedimento inicia com a criação de um **fork** e o clone do repositório para o ambiente local, estabelecendo conexões entre os repositórios **origin** e **upstream** para manter o código sincronizado. A instrutora demonstra como criar uma **branch específica** e desenvolver um arquivo de perfil em **Markdown**, utilizando ferramentas como o VS Code e comandos de terminal. As etapas finais abrangem a adição das mudanças à **staging area**, a criação de um **commit** seguindo convenções de mensagens e o envio dos dados via **push**. O conteúdo serve como um guia prático para que desenvolvedores pratiquem o fluxo de trabalho colaborativo e personalizem suas apresentações na comunidade.

### Anotações

- Cria-se um fork do repositório original para efetuar uma contribuição.
- O fork e necessário pois não temos permissão de enviar alterações no repositório original.
- Após o  fork, cria-se um clone deste no repositório local.
- Adiciona-se o repositório original como remote upstream para manter o repositório local atualizado
- o [Guia de Contribuição](https://github.com/digitalinnovationone/dio-lab-open-source/blob/main/CONTRIBUTING.md) apresenta o passo a passo de todo o processo.

<details>
  <summary>🟨🟨🟨 Guia de Contribuição&nbsp 🟨🟨🟨</summary>

<h1>
    <a href="https://www.dio.me/">
     <img align="center" width="40px" src="https://hermes.digitalinnovation.one/assets/diome/logo-minimized.png"></a>
    <span> Guia de Contribuição</span>
</h1>

[![Star](https://img.shields.io/github/stars/digitalinnovationone/dio-lab-open-source?style=social)](https://github.com/digitalinnovationone/dio-lab-open-source/stargazers)
[![Forks](https://img.shields.io/github/forks/digitalinnovationone/dio-lab-open-source?style=social)](https://github.com/digitalinnovationone/dio-lab-open-source/forks)
[![GitHub Issues](https://img.shields.io/github/issues/digitalinnovationone/dio-lab-open-source?style=social)](https://github.com/digitalinnovationone/dio-lab-open-source/issues/)

 Este é um projeto feito para a comunidade, então sinta-se livre para contribuir. Algumas formas de contribuição além do seu exemplo de Profile README, é inserir outros utilitários na pasta [`utils`](https://github.com/digitalinnovationone/dio-lab-open-source/tree/main/utils), ou melhorar a página de pesquisa dos READMEs fazendo modificações nos arquivos da pasta [`docs`](https://github.com/digitalinnovationone/dio-lab-open-source/tree/main/docs). <br>
 Além disso, você também pode contribuir:
 
⚠️ Resolvendo, respondendo ou indicando **issues**

⭐ Adicionando aos favoritos (**star**) 

##  Contribuindo no diretório "Community" 
 A contribuição no diretório "Community" é uma das formas de completar o Desafio do lab "**Contribuindo em um Projeto Open Source no GitHub**" da [Digital Innovation One](https://www.dio.me/). Você pode colaborar criando um Profile README contendo informações sobre você que deseje compartilhar com a comunidade. <br>
 Para isso, você pode inserir: badges indicando suas habilidades; cards com suas estatísticas no GitHub e projetos que criou, colaborou ou que deseje que outras pessoas colaborem. Além disso, você pode inserir também links para seus desafios de projeto e artigos na plataforma da [Digital Innovation One](https://www.dio.me/). <br>
 Inspire-se consultando os exemplos na pasta [`community`](https://github.com/digitalinnovationone/dio-lab-open-source/tree/main/community), confira alguns utilitários na pasta [`utils`](https://github.com/digitalinnovationone/dio-lab-open-source/tree/main/utils) e use sua criatividade para criar o seu 😊💙.

### 1) Faça um **Fork** deste Repositório
Acesse a página principal do repositório e clique no botão "Fork" no canto superior direito da página.
> [!NOTE]  
> Um "fork" no GitHub é uma cópia de um repositório que pode ser criada por qualquer usuário. <br>
> Para mais detalhes, reveja a aula ou acesse a documentação do GitHub: [Criar fork de um repositório](https://docs.github.com/pt/pull-requests/collaborating-with-pull-requests/working-with-forks/fork-a-repo).

### 2) Clone localmente
Abra o seu Git Bash e digite o comando `git clone` seguido da URL do seu fork para clonar o seu repositório localmente. Por exemplo:
```bash
git clone https://github.com/SEU_USERNAME/dio-lab-open-source.git
```
Pressione enter, e uma cópia do seu fork no GitHub será criada localmente.

### 3) Crie uma nova **branch** 
Utilize o comando `git checkout -b` para criar e alternar para a nova branch e nomeie-a como `feat/community/SEU_USERNAME`
> Exemplo: `git checkout -b feat/community/falvojr`

### 4) Crie o seu Profile README
 Dentro da pasta [`community`](https://github.com/digitalinnovationone/dio-lab-open-source/tree/main/community), crie um arquivo em Markdown (extensão `.md`) e nomeie com o mesmo nome do seu usuário no GitHub:

> Exemplo: `community/falvojr.md`

#### 4.1) Desenvolva o seu Profile README
Para isso, você pode se inspirar nos exemplos no diretório [`community`](https://github.com/digitalinnovationone/dio-lab-open-source/tree/main/community) e adicionar alguns dos utilitários presentes na pasta [`utils`](https://github.com/digitalinnovationone/dio-lab-open-source/tree/main/utils)

### 5) Adicione suas alterações à "staging area" 
Utilize o comando `git add community/SEU_USERNAME.md` para adicionar sua alteração (nesse caso o arquivo markdown criado)  à "staging area" no Git.

### 6) Crie um Commit
Crie um commit e adicione a mensagem indicando a adição do seu perfil:
```bash
git commit -m"feat: add SEU_USERNAME profile"
```
>[!IMPORTANT]
> Verifique a [`Convenção de Commits`](https://github.com/digitalinnovationone/dio-lab-open-source/blob/main/CONTRIBUTING.md#conven%C3%A7%C3%A3o-de-commits) para escrever a mensagem do seu commit de forma clara e padronizada.

### 7) Envie as Alterações para o seu Repositório Remoto
Envie as alterações realizadas no seu repositório local para a branch `feat/community/SEU_USERNAME` no seu repositório remoto com o comando:
```bash
git push origin feat/community/SEU_USERNAME
```
>[!WARNING]
> Caso você tenha criado seu arquivo diretamente no repositório remoto no GitHub, esse processo não será necessário.

### 8) Crie um **Pull Request**.

Atente-se para a seguir as orientações para a contribuição, principalmente:
- Seu PR deve modificar apenas o arquivo community/SEU_USERNAME.md (dê uma olhadinha na aba "Files changed");
- O nome desse arquivo deve ser exatamente igual ao nome de usuário no GitHub (nossa validação é case-sensitive).

>[!NOTE]
> Caso não saiba como criar uma solicitação de pull, reveja o lab ou acesse a documentação do GitHub: [Como criar uma solicitação de pull
](https://docs.github.com/pt/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/creating-a-pull-request)

Após criar o seu Pull Request, nossa automação irá validar a sua submissão. Caso esteja tudo certo, será retornada uma mensagem indicado que seu PR foi aprovado. Do contrário, leia atentamente as orientações e verifique os arquivos modificados para saber se atende as instruções para contribuição.
    
    
## Convenção de Commits 

| Tipo de Commit |Descrição                                                            | Exemplo
| ---------------|----------------------------------------------------------------------|-----------
| `feat`         | Adiciona uma nova funcionalidade ao projeto.                         | `feat: add USENAME.md profile`
| `fix`          | Corrige um bug ou problema no projeto.                               | `fix: fixed issue fix#IssueNumber`
| `docs`         | Altera a documentação do projeto.| `docs: update README.md`
| `style`        | Realiza mudanças na aparência, sem alterar a funcionalidade.         | `style: add EFFECTNAME to COMPONENT`
| `refactor`     | Realiza mudanças no código que não alteram a funcionalidade.         | `refactor: refactor at CLASSNAME`
| `test`         | Adiciona ou modifica testes no projeto.                              | `test: add unit test for UserService`


## Referências
- [ANGULAR. Contributing to Angular](https://github.com/angular/angular/blob/22b96b9/CONTRIBUTING.md)
- [CONVENTIONAL COMMITS. Summary](https://www.conventionalcommits.org/en/v1.0.0/)
- [GITHUB. Configurar diretrizes para os contribuidores do repositório](https://docs.github.com/pt/communities/setting-up-your-project-for-healthy-contributions/setting-guidelines-for-repository-contributors)

</details>

Profile README enviado para o projeto: [ahaerdy.md](https://github.com/ahaerdy/DIO-learning/blob/main/Suzano%20-%20Python%20Developer/Modulo_01/06-Prijeto-Contribuindo_em_um_Projeto_Open_Source_no_GitHub/contribuicao/ahaerdy.md)


## 🟩 Vídeo 04 - Exercício - Enviando uma Contribuição

![[bootcamp-ntt-data_modulo_01-Curso_06-Video_04.webm|vid-60]]

Link do vídeo: https://web.dio.me/lab/desafio-de-projeto-contribuindo-em-um-projeto-open-source-no-github/learning/4945958e-0c25-4a04-9d86-29cc7ea82b6a?back=/track/engenharia-dados-python

O vídeo detalha o procedimento prático para realizar uma **contribuição em um projeto de código aberto** via GitHub. O autor demonstra como **abrir um pull request**, preencher corretamente o **template de descrição** e selecionar as categorias de alteração realizadas. O conteúdo explica o processo de **revisão e aceitação do código**, mostrando como o repositório original incorpora as mudanças após a verificação de conflitos. Além disso, destaca-se a importância de **sincronizar o repositório local** com as atualizações feitas no projeto principal. Por fim, o guia incentiva a **interação com a comunidade** por meio de desafios práticos e melhorias contínuas na documentação.

## 🟩 Vídeo 05 - Dicas e Materiais de Apoio

![[bootcamp-ntt-data_modulo_01-Curso_06-Video_05.webm|vid-60]]

Link do vídeo: https://web.dio.me/lab/desafio-de-projeto-contribuindo-em-um-projeto-open-source-no-github/learning/9e6f7eb2-86cc-4a66-b31c-80d3d774667d

O material fornece orientações essenciais para desenvolvedores que desejam **fortalecer seu portfólio profissional** por meio da colaboração em projetos de código aberto. A instrutora enfatiza a importância de participar ativamente da **comunidade DIO** e de seguir rigorosamente as **documentações e padrões de commit** ao contribuir. O conteúdo detalha métodos práticos para manipular arquivos diretamente na interface do **GitHub**, incluindo o uso do editor web acionado pela tecla de atalho do ponto. Além das técnicas operacionais, o recurso destaca como o trabalho em equipe demonstra **habilidades comportamentais** valiosas para o mercado de trabalho. Por fim, são disponibilizados diversos links de apoio que abrangem desde guias de **Markdown** até ferramentas para a personalização de perfis e READMEs.


## 🟩 Vídeo 06 - Resumão e Explicação Sobre a Automação dos PRs



Link do vídeo: https://web.dio.me/lab/desafio-de-projeto-contribuindo-em-um-projeto-open-source-no-github/learning/32153d6a-91fe-42d0-9b18-0d2be2e35197


## 🟩 Vídeo 07 - Entendendo o Desafio


Link do vídeo: 


##  Materiais de Apoio

### Materiais Complementares

Nossos materiais complementares e de apoio têm como objetivo apresentar informações para facilitar e enriquecer a sua jornada de aprendizado. Para isso, links úteis (como slides, repositórios e páginas oficiais) serão disponibilizados, além de dicas sobre como se destacar na DIO e no mercado de trabalho.

#### Dúvidas sobre como funciona o Node?

Assista esse curso com o expert Felipe Aguiar: [Clique aqui](https://web.dio.me/lives/como-criar-e-publicar-seu-primeiro-servidor-com-nodejs-getting-started-7)

#### Dicas / Links Úteis

Disponibilizamos alguns links úteis para que você possa se desenvolver ainda mais através de referências oficiais das tecnologias, páginas de documentação e/ou fóruns de discussão relevantes. Nesse contexto, seguem algumas sugestões:

##### Artigos / Fórum

- Compartilhe conteúdos técnicos através de **Artigos** (visíveis globalmente na plataforma da DIO).
- Compartilhe conquistas e dúvidas nos **Fóruns**, específicos para cada experiência educacional (como Bootcamps).

##### Rooms

- Se estiver inscrito(a) em uma experiência educacional na DIO (como um Bootcamp), você terá acesso ao **Rooms**.
- O Rooms é uma ferramenta de bate-papo em tempo real onde todos os inscritos podem interagir, compartilhando dúvidas e dicas (inclusive com imagens e trechos de código).

##### Pesquise na Web

- Pode parecer óbvio, mas é importante frisar a importância das **ferramentas de busca** no dia a dia de um profissional de TI.
- Se não encontrar o que procura dentro da DIO, pesquise na Internet (dê um Google).
- Muitas vezes você será levado a páginas incríveis como o **StackOverflow**, que podem salvar o seu dia.


# Certificado: 

- Link na plataforma: 
- Certificado em pdf: 
