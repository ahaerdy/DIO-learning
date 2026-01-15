# 🗺️ Etapas para entender o projeto tecnicamente

## 1. **Começar pelo `App.js`**
- É o **componente raiz** da aplicação.
- Mostra como os dados são consumidos e como os componentes principais se organizam.
- Neste caso, ele usa o hook `useGithub()` para acessar o estado global e renderizar condicionalmente:
  - `NoSearch` (se não há usuário)
  - `Profile` e `Repositories` (se há usuário e não está carregando)

🟡 /src/App.js

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

