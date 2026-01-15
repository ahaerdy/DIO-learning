# 2. Hook github-hooks.js (explicado)

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

Detalhando cada parte:

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
