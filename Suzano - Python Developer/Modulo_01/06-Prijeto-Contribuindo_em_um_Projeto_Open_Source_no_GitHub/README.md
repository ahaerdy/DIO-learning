# Parte 1 - Aulas

- Instrutor: Elidiana Andrade (Tech Education Analyst at DIO)
- Contato Libkedin: https://www.linkedin.com/in/elidiana/

## Vídeo 01 - Introdução ao Lab

### Objetivo: aprender o básico sobre a contribuição no Github
   
- O que são e como contribuir em Projetos Open Source
- Desenvolver uma contribuição
- Enviar uma contribuição
- Dicas e matreriais de apoio

## Vídeo 02 - Formas de Contribuir num Projeto Open Source

### O que é um Projeto Open Source?

- É um projeto de Código Aberto, livre para ser utilizado, modificado e distribuído. Alguns exemplos são: 
    - [Linux](https://github.com/torvalds/linux), sob a licença GNU General Public License (GPL);
    - [Roadmaps DIO](https://github.com/digitalinnovationone/roadmaps), sob a licença MIT License (MIT). 

### Formas de Contribuir em um Projeto Open Source

- Relatar algum problema ou bug;
- Sugerir melhorias ou adição de novos recursos;
- Escrever ou atualizar a documentação;
- Contribuir com o código;
- Divulgar e/ou doar.

### Links úteis

- Repositório no GitHub: https://github.com/digitalinnovationone/dio-lab-open-source
- Documentação GitHub: https://docs.github.com/

### Links complementares

- Markdown: https://docs.github.com/pt/get-started/writing-on-github
- Conventional Commits: https://github.com/conventional-commits/conventionalcommits.org 
- GitHub Etiquetas: https://docs.github.com/pt/issues/using-labels-and-milestones-to-track-work/managing-labels
- Visual Studio Code: https://code.visualstudio.com/download


### Apresentação do Repositório do Desafio

- Repositório do desafio: https://github.com/digitalinnovationone/dio-lab-open-source
- Utilitários para o profile README: https://github.com/digitalinnovationone/dio-lab-open-source/tree/main/utils

### Criação de uma Issue (sugestão de melhoria)

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


## Vídeo 03 - Desenvolvendo uma Contribuição

- Cria-se um fork do repositório original para efetuar uma contribuição.
- O fork e necessário pois não temos permissão de enviar alterações no repositório original.
- Após o  fork, cria-se um clone deste no repositório local.
- Adiciona-se o repositório original como remote upstream para manter o repositório local atualizado

- o [Guia de Contribuição](https://github.com/digitalinnovationone/dio-lab-open-source/blob/main/CONTRIBUTING.md) apresenta o passo a passo de todo o processo.

<details>
  <summary>🟨🟨🟨 Guia de Contribuição - Clique para expandir 🟨🟨🟨</summary>

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


## Vídeo 04 - Enviando uma Contribuição

- Procedimentos para a criação de uma Pull Request.

## Vídeo 05 - Dicas e Materiais de Apoio

- Cria-se uma branch no Fork para testes.
- A instrutora ensina como editar arquivos markdown diretamente no Github (Addfile/Create New File).
- A instrutira ensina como usar o editor web do Github (apertando a tecla "." estando logado na conta dentro do repositório em que se deseja criar/abrir o documento).

## Vídeo 06 - Resumão e Explicação Sobre a Automação dos PRs

- Instrutor: Venilton Falvo Jr (Doutor em Engenharia de Software, Education Tech Lead na DIO)
- Contato: https://www.linkedin.com/in/falvojr/

- Dicas e insights do Prof. Venilton detalhando das etapas do projeto.

# Parte 2 -Entendendo o Desafio 

- [Entendendo o Desafio](https://github.com/ahaerdy/DIO-learning/blob/main/Suzano%20-%20Python%20Developer/Modulo_01/06-Prijeto-Contribuindo_em_um_Projeto_Open_Source_no_GitHub/sobre_o_desafio/entendendo_o_desafio.md) (texto elaborado pela Prof. Elidiana).

# Certificado: Contribuindo em um Projeto Open Source no GitHub

Link do certificado: https://hermes.dio.me/certificates/OC0JDME3.pdf