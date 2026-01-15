# 🗺️ Etapas para entender o projeto tecnicamente

## 1. **Começando pelo `App.js`**
- É o **componente raiz** da aplicação.
- Mostra como os dados são consumidos e como os componentes principais se organizam.
- Neste caso, ele usa o hook `useGithub()` para acessar o estado global e renderizar condicionalmente:
  - `NoSearch` (se não há usuário)
  - `Profile` e `Repositories` (se há usuário e não está carregando)

🔴 Código: /src/App.js

```javascript
import React from "react";
import Layout from "./components/layout";
import NoSearch from "./components/no-search";
import Profile from "./components/profile";
import Repositories from "./components/repositories";
import useGithub from "./hooks/github-hooks";

const App = () => {
  const { githubState } = useGithub();
  return (
    <Layout>
      {githubState.hasUser ? (
        <>
          {githubState.loading ? (
            <p>Loading</p>
          ) : (
            <>
              <Profile />
              <Repositories />
            </>
          )}
        </>
      ) : (
        <NoSearch />
      )}
    </Layout>
  );
};

export default App;
```

🟢 Código: /src/hooks/github-hooks.js

```javascript
import { useContext } from "react";
import { GithubContext } from "../providers/github-provider";

const useGithub = () => {
  const { githubState, getUser, getUserRepos, getUserStarred } = useContext(
    GithubContext
  );

  return { githubState, getUser, getUserRepos, getUserStarred };
};

export default useGithub;
```

Verificar: [Detalhando o componente github-provider.js](./doc_github-provider_e_github_hooks.md)

Perfeito, Arthur! Vamos analisar **linha por linha** esse código para que você entenda exatamente o que está acontecendo.

---

### 📄 Código do Hook github-hooks.js explicado

```js
import { useContext } from "react";
```
- Importa o **hook `useContext`** do React.  
- Esse hook permite que um componente ou função acesse valores de um **Contexto** (dados globais compartilhados) sem precisar passar *props* manualmente.

---

```js
import { GithubContext } from "../providers/github-provider";
```
- Importa o **GithubContext**, que foi criado no arquivo `github-provider.js`.  
- Esse contexto contém:
  - O estado global (`githubState`)  
  - As funções (`getUser`, `getUserRepos`, `getUserStarred`)  

---

```js
const useGithub = () => {
```
- Define um **custom hook** chamado `useGithub`.  
- Custom hooks são funções que encapsulam lógica de hooks do React, facilitando o reuso e simplificando o código.  
- Esse hook será usado em qualquer componente que precise acessar os dados do GitHub.

---

```js
  const { githubState, getUser, getUserRepos, getUserStarred } = useContext(
    GithubContext
  );
```
- Aqui está o coração do hook:  
  - `useContext(GithubContext)` acessa o valor atual do contexto.  
  - Esse valor é um objeto fornecido pelo `GithubProvider`.  
  - O código usa **desestruturação** para extrair:
    - `githubState` → estado global (usuário, repositórios, favoritos, status de carregamento).  
    - `getUser` → função para buscar dados de um usuário.  
    - `getUserRepos` → função para buscar repositórios do usuário.  
    - `getUserStarred` → função para buscar repositórios favoritados.  

---

```js
  return { githubState, getUser, getUserRepos, getUserStarred };
};
```
- O hook retorna um objeto com o estado e as funções.  
- Isso permite que qualquer componente que use `useGithub()` tenha acesso direto a esses recursos.  
- Exemplo de uso:
  ```js
  const { githubState, getUser } = useGithub();
  ```

---

```js
export default useGithub;
```
- Exporta o hook para que possa ser usado em outros arquivos da aplicação.  
- Assim, qualquer componente pode importar `useGithub` e consumir o contexto do GitHub.

---

### 🎯 Resumindo
Esse arquivo define um **hook personalizado** chamado `useGithub`.  
- Ele **consome** o contexto `GithubContext` usando `useContext`.  
- Ele retorna o estado global e funções de manipulação.  
- Ele é usado nos componentes (como `App.js`) para acessar dados do GitHub de forma simples.

---

👉 Quer que eu mostre um **exemplo prático** de como o `App.js` usa esse `useGithub` para decidir se renderiza `Profile`, `Repositories` ou `NoSearch`?

## 2. **Explorar os componentes usados**
A partir do `App.js`, seguir para:
- `Layout` → provavelmente define a estrutura visual (header, container, etc.)
- `NoSearch` → tela inicial ou mensagem quando nenhum usuário foi buscado
- `Profile` → exibe dados do usuário (nome, avatar, localização, etc.)
- `Repositories` → lista os repositórios do usuário

## 3. **Entender o hook `useGithub()`**
- Está em `hooks/github-hooks.js`
- Provavelmente usa `useContext` para acessar o estado global
- Esse estado vem do `GithubProvider` (em `providers/github-provider.js`)

## 4. **Analisar o `GithubProvider`**
- Definir o contexto e o estado compartilhado
- Deve conter lógica de chamada à API do GitHub (via `axios`)
- É onde o estado `hasUser`, `loading`, `user`, `repositories` etc. é definido

## 5. **Verificar estilos e reset**
- `ResetCSS` é usado para limpar estilos padrão do navegador
- `styled-components` provavelmente define os estilos dos componentes

### 📝 Como estudar e comentar
- Abrir cada componentem anotando:
  - O que ele faz
  - Quais props recebe
  - Como se conecta ao estado global
  - Quais responsabilidades tem (UI, lógica, API, etc.)

- Comentar o código com observações como:
  ```js
  // Verifica se o usuário foi buscado antes de renderizar os dados
  ```

- Criar um README explicando:
  - O propósito do projeto
  - Como instalar e rodar
  - Como funciona a busca de usuários
  - Quais componentes principais existem

