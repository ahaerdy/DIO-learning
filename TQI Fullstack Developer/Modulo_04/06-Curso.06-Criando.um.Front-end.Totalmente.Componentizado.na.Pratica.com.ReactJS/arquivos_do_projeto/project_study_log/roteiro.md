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

### Verificar posteriormente:
- [doc_github-hooks_js](doc_github-hooks_js.md)
- [doc_github-provider_js](doc_github-provider_js.md)

### Destrinchando a **chamada ao hook** `useGithub()` dentro do contexto do `App.js`:

```js
const { githubState } = useGithub();
```

#### 🔎 O que acontece aqui

1. **Chamada ao hook personalizado**
   - `useGithub()` é o **custom hook** definido em `github-hooks.js`.
   - Esse hook usa `useContext(GithubContext)` para acessar o **estado global** e as funções fornecidas pelo `GithubProvider`.

2. **Desestruturação**
   - O hook retorna um objeto com várias propriedades:
     ```js
     { githubState, getUser, getUserRepos, getUserStarred }
     ```
   - A linha está usando **desestruturação** para pegar **apenas** a propriedade `githubState` desse objeto.
   - Ou seja, ignora `getUser`, `getUserRepos` e `getUserStarred` porque neste componente (`App.js`) só precisa do estado.

3. **O que é `githubState`**
   - É o **estado global** que contém:
     - `hasUser` → se já foi buscado um usuário.
     - `loading` → se está carregando dados.
     - `user` → objeto com dados do usuário (id, avatar, login, etc.).
     - `repositories` → lista de repositórios.
     - `starred` → lista de repositórios favoritados.

4. **Uso dentro do App.js**
   - O `App.js` usa `githubState` para decidir o que renderizar:
     - Se `hasUser` é `false` → mostra `<NoSearch />`.
     - Se `hasUser` é `true` e `loading` é `true` → mostra `"Loading"`.
     - Se `hasUser` é `true` e `loading` é `false` → mostra `<Profile />` e `<Repositories />`.

---

#### 🎯 Em resumo
A linha:

```js
const { githubState } = useGithub();
```

- **Conecta o `App.js` ao estado global do GitHub** fornecido pelo `GithubProvider`.  
- Permite que o `App.js` saiba se existe usuário, se está carregando e quais dados já foram buscados.  
- É a **ponte** entre o contexto global e a lógica de renderização do `App.js`.

### Analisando o objeto githubState

```javascript
  const { githubState } = useGithub();
  console.log("Conteúdo de githubState:", githubState);
```

Saída no console:

![saída no console](../000-Midia_e_Anexos/snapshot-1.png)


---




















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

