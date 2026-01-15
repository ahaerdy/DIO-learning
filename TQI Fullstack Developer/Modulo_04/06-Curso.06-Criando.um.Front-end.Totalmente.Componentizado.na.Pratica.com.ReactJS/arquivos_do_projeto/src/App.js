import React, { useEffect } from "react"; // <-- adiciona useEffect
import Layout from "./components/layout";
import NoSearch from "./components/no-search";
import Profile from "./components/profile";
import Repositories from "./components/repositories";
import useGithub from "./hooks/github-hooks";

const App = () => {
  const { githubState } = useGithub();
  // console.log("Conteúdo de githubState:", githubState);

  // Monitora mudanças no estado global 
  useEffect(() => { console.log("githubState atualizado:", githubState); }, [githubState]);

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
