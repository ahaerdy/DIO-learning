## 🟢  ./src/providers/`github-provider.js`


```javascript
import React, { createContext, useCallback, useState } from "react";
import api from "../services/api";

export const GithubContext = createContext({
  loading: false,
  user: {},
  repositories: [],
  starred: [],
});

const GithubProvider = ({ children }) => {
  const [githubState, setGithubState] = useState({
    hasUser: false,
    loading: false,
    user: {
      id: undefined,
      avatar: undefined,
      login: undefined,
      name: undefined,
      html_url: undefined,
      blog: undefined,
      company: undefined,
      location: undefined,
      followers: 0,
      following: 0,
      public_gists: 0,
      public_repos: 0,
    },
    repositories: [],
    starred: [],
  });

  const getUser = (username) => {
    setGithubState((prevState) => ({
      ...prevState,
      loading: !prevState.loading,
    }));

    api
      .get(`users/${username}`)
      .then(({ data }) => {
        setGithubState((prevState) => ({
          ...prevState,
          hasUser: true,
          user: {
            id: data.id,
            avatar: data.avatar_url,
            login: data.login,
            name: data.name,
            html_url: data.html_url,
            blog: data.blog,
            company: data.company,
            location: data.location,
            followers: data.followers,
            following: data.following,
            public_gists: data.public_gists,
            public_repos: data.public_repos,
          },
        }));
      })
      .finally(() => {
        setGithubState((prevState) => ({
          ...prevState,
          loading: !prevState.loading,
        }));
      });
  };

  const getUserRepos = (username) => {
    api.get(`users/${username}/repos`).then(({ data }) => {
      console.log("data: " + JSON.stringify(data));
      setGithubState((prevState) => ({
        ...prevState,
        repositories: data,
      }));
    });
  };

  const getUserStarred = (username) => {
    api.get(`users/${username}/starred`).then(({ data }) => {
      console.log("data: " + JSON.stringify(da
```

### Comentário linha a linha:

```js
import React, { createContext, useCallback, useState } from "react";
import api from "../services/api";
```
- Importa funções do React:
  - `createContext` → cria um **Contexto** para compartilhar dados globalmente.
  - `useState` → gerencia o estado interno do provider.
  - `useCallback` → memoriza funções para evitar recriações desnecessárias.
- Importa `api`, isto é, um **axios configurado** para acessar a API do GitHub.

---

```js
export const GithubContext = createContext({
  loading: false,
  user: {},
  repositories: [],
  starred: [],
});
```
- Cria o **GithubContext** com valores iniciais.
- Esse contexto será usado por `useGithub` e pelos componentes que precisam acessar dados do GitHub.

---

```js
const GithubProvider = ({ children }) => {
```
- Define o **Provider**, que vai envolver toda a aplicação (`<GithubProvider> ... </GithubProvider>`).
- Recebe `children` (os componentes que estarão dentro dele).

<details>
<summary> 🟥🟥🟥 Detalhamento sobre o papel do Provider em relação à aplicação inteira 🟥🟥🟥 </summary>
<hr style="border: none; border-top: 2px dashed #888; margin: 10px 0;">

####  O que o Provider faz
Um **Provider** no React é como um **“distribuidor de informações”**.  
Ele envolve a aplicação (ou parte dela) e **torna disponíveis dados e funções para todos os componentes filhos**, sem precisar passar *props* manualmente em cada nível.

#### No projeto
- O **`GithubProvider`** envolve toda a aplicação.  
- Ele fornece:
  - O estado global (`githubState`) → dados do usuário, repositórios, favoritos, status de carregamento.  
  - Funções (`getUser`, `getUserRepos`, `getUserStarred`) → para buscar informações na API do GitHub.  

Qualquer componente dentro de `<GithubProvider> ... </GithubProvider>` pode acessar esses dados e funções usando o hook `useGithub`.

#### Estrutura visual
```jsx
<GithubProvider>
  <App />
</GithubProvider>
```

➡️ Aqui, o `App` e todos os componentes dentro dele (Profile, Repositories, NoSearch, etc.) têm acesso ao contexto fornecido pelo `GithubProvider`.

#### Em resumo
- O **Provider** é como um **“cérebro central”** que guarda informações e funções.  
- Ele **distribui** esses recursos para todos os componentes filhos.  
- Isso evita o problema de *prop drilling* (passar props manualmente por vários níveis).  
- Neste caso, o `GithubProvider` garante que qualquer parte da aplicação possa acessar os dados do GitHub sem complicação.

</details>

```js
  const [githubState, setGithubState] = useState({
    hasUser: false,
    loading: false,
    user: { ... },
    repositories: [],
    starred: [],
  });
```
- Cria o estado global `githubState`.
- Contém:
  - `hasUser`: indica se já foi buscado um usuário.
  - `loading`: indica se está carregando dados.
  - `user`: objeto com informações do usuário (id, avatar, login, etc.).
  - `repositories`: lista de repositórios.
  - `starred`: lista de repositórios favoritados.

---

```js
  const getUser = (username) => {
    setGithubState((prevState) => ({
      ...prevState,
      loading: !prevState.loading,
    }));

    api.get(`users/${username}`)
      .then(({ data }) => {
        setGithubState((prevState) => ({
          ...prevState,
          hasUser: true,
          user: { ...dados do usuário... },
        }));
      })
      .finally(() => {
        setGithubState((prevState) => ({
          ...prevState,
          loading: !prevState.loading,
        }));
      });
  };
```
- Função para buscar **dados do usuário** no GitHub.
- Atualiza `loading` antes e depois da requisição.
- Se a requisição dá certo, atualiza `user` com os dados recebidos.
- Marca `hasUser: true`.

---

```js
  const getUserRepos = (username) => {
    api.get(`users/${username}/repos`).then(({ data }) => {
      setGithubState((prevState) => ({
        ...prevState,
        repositories: data,
      }));
    });
  };
```
- Busca os **repositórios** do usuário.
- Atualiza `repositories` no estado global.

---

```js
  const getUserStarred = (username) => {
    api.get(`users/${username}/starred`).then(({ data }) => {
      setGithubState((prevState) => ({
        ...prevState,
        starred: data,
      }));
    });
  };
```
- Busca os **repositórios favoritados** pelo usuário.
- Atualiza `starred` no estado global.

---

```js
  const contextValue = {
    githubState,
    getUser: useCallback((username) => getUser(username), []),
    getUserRepos: useCallback((username) => getUserRepos(username), []),
    getUserStarred: useCallback((username) => getUserStarred(username), []),
  };
```
- Cria o objeto `contextValue` que será fornecido pelo contexto.
- Contém:
  - O estado `githubState`.
  - As funções `getUser`, `getUserRepos`, `getUserStarred`.
- Usa `useCallback` para memorizar as funções e evitar recriações desnecessárias.

---

```js
  return (
    <GithubContext.Provider value={contextValue}>
      {children}
    </GithubContext.Provider>
  );
};
```
- Retorna o **Provider** do contexto.
- Qualquer componente dentro de `<GithubProvider>` terá acesso ao `contextValue`.

---

```js
export default GithubProvider;
```
- Exporta o provider para ser usado em `providers.js`.

---

### 🧠 Resumindo
- **`GithubProvider`** é o **cérebro** do projeto: gerencia estado global e chamadas à API.  
- **`useGithub`** é o **atalho** para consumir esse estado e funções.  
- **Fluxo**:  
  - Componente chama `useGithub()` → acessa `getUser` ou `getUserRepos`.  
  - Função faz requisição via `api` → atualiza `githubState`.  
  - Componentes que usam `githubState` são re-renderizados com os novos dados.

---

Ótimo, Arthur! Vamos montar um **diagrama textual** que mostra claramente como os principais blocos do projeto se conectam. Assim você terá uma visão hierárquica e de fluxo de dados.

---

### 🗺️ Fluxo de Hierarquia e Dados

```
index.js
 └── Providers
      └── GithubProvider (Contexto Global)
           ├── githubState (estado global)
           │     ├── hasUser
           │     ├── loading
           │     ├── user { id, avatar, login, ... }
           │     ├── repositories []
           │     └── starred []
           │
           ├── getUser(username)        -> chama API /users/:username
           ├── getUserRepos(username)   -> chama API /users/:username/repos
           └── getUserStarred(username) -> chama API /users/:username/starred
           
App.js
 └── useGithub() (hook que consome GithubContext)
      ├── acessa githubState
      ├── acessa getUser, getUserRepos, getUserStarred
      │
      └── Renderização condicional:
           ├── Se !hasUser → <NoSearch />
           ├── Se hasUser && loading → "Loading"
           └── Se hasUser && !loading → 
                 ├── <Profile /> (dados do usuário)
                 └── <Repositories /> (lista de repositórios)
```

---

### 🔎 Explicação do fluxo

1. **`index.js`**  
   - Inicializa a aplicação e envolve tudo em `<Providers />`.

2. **`Providers.js`**  
   - Aplica `<GithubProvider>` (estado global) e `<ResetCSS>` (estilos).

3. **`GithubProvider`**  
   - Cria o **estado global** (`githubState`).  
   - Define funções para buscar dados da API do GitHub.  
   - Expõe tudo via `GithubContext`.

4. **`useGithub`**  
   - Hook que consome o contexto.  
   - Facilita o acesso ao estado e às funções sem precisar usar `useContext` diretamente.

5. **`App.js`**  
   - Usa `useGithub` para acessar o estado.  
   - Decide o que renderizar:
     - Nenhum usuário → `NoSearch`.  
     - Carregando → mensagem "Loading".  
     - Usuário carregado → `Profile` + `Repositories`.

6. **Componentes de UI**  
   - `Profile`: mostra dados do usuário.  
   - `Repositories`: mostra lista de repositórios.  
   - `NoSearch`: mensagem inicial quando nada foi buscado.  
   - `Layout`: organiza a estrutura visual.

---

### 🎯 Resumindo
- **Provider** = cérebro (estado + funções).  
- **Hook (`useGithub`)** = atalho para acessar o cérebro.  
- **App.js** = coração da interface, decide o que mostrar.  
- **Componentes** = partes visuais que exibem os dados.
