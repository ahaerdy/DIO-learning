import React from "react";
import useGithub from "../../hooks/github-hooks";
import * as S from "./styled";

const Profile = () => {
  const { githubState } = useGithub();

  return (
    <S.Wrapper>
      <S.WrapperImage 
        src={githubState.user.avatar} 
        alt={`Avatar de ${githubState.user.login}`} 
      />
      <S.WrapperInfoUser>
        <div>
          <h1>{githubState.user.name || "não informado"}</h1>

          <S.WrapperUserGeneric>
            <h3>Username:</h3>
            <a
              href={githubState.user.html_url}
              target="_blank"
              rel="noreferrer"
            >
              {githubState.user.login}
            </a>
          </S.WrapperUserGeneric>

          <S.WrapperUserGeneric>
            <h3>Company:</h3>
            <span>{githubState.user.company || "não informado"}</span>
          </S.WrapperUserGeneric>

          <S.WrapperUserGeneric>
            <h3>Location:</h3>
            <span>{githubState.user.location || "não informado"}</span>
          </S.WrapperUserGeneric>

          <S.WrapperUserGeneric>
            <h3>Blog:</h3>
            {githubState.user.blog ? (
              <a 
                href={githubState.user.blog} 
                target="_blank" 
                rel="noreferrer"
              >
                {githubState.user.blog}
              </a>
            ) : (
              <span>não informado</span>
            )}
          </S.WrapperUserGeneric>

          <S.WrapperUserGeneric>
            <h3>Bio:</h3>
            <span>{githubState.user.bio || "não informado"}</span>
          </S.WrapperUserGeneric>

          <S.WrapperUserGeneric>
            <h3>Desde:</h3>
            <span>
              {githubState.user.created_at 
                ? new Date(githubState.user.created_at).toLocaleDateString() 
                : "não informado"}
            </span>
          </S.WrapperUserGeneric>
        </div>

        <S.WrapperStatusCount>
          <div>
            <h4>Followers</h4>
            <a 
              href={`https://github.com/${githubState.user.login}?tab=followers`} 
              target="_blank" 
              rel="noreferrer"
            >
              {githubState.user.followers}
            </a>
          </div>
          <div>
            <h4>Followings</h4>
            <a 
              href={`https://github.com/${githubState.user.login}?tab=following`} 
              target="_blank" 
              rel="noreferrer"
            >
              {githubState.user.following}
            </a>
          </div>
          <div>
            <h4>Gists</h4>
            <a 
              href={`https://gist.github.com/${githubState.user.login}`} 
              target="_blank" 
              rel="noreferrer"
            >
              {githubState.user.public_gists}
            </a>
          </div>
          <div>
            <h4>Repos</h4>
            <a 
              href={`https://github.com/${githubState.user.login}?tab=repositories`} 
              target="_blank" 
              rel="noreferrer"
            >
              {githubState.user.public_repos}
            </a>
          </div>
        </S.WrapperStatusCount>
      </S.WrapperInfoUser>
    </S.Wrapper>
  );
};

export default Profile;
