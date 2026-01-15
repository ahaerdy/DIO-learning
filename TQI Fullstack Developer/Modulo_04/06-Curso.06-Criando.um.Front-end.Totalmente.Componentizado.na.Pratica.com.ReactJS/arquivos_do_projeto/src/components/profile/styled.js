import styled from "styled-components";

export const Wrapper = styled.div`
  display: flex;
  align-items: flex-start;

  /* Responsividade: avatar em cima e dados abaixo em telas menores */
  @media (max-width: 600px) {
    flex-direction: column;
    align-items: center;
  }
`;

export const WrapperInfoUser = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: flex-start;
  margin-left: 8px;
  min-height: 200px;

  h1 {
    font-size: 32px;
    font-weight: bold;
    margin-bottom: 8px;
  }

  h3 {
    font-size: 18px;
    font-weight: bold;
    margin-right: 8px;
  }

  h4 {
    font-size: 16px;
    font-weight: bold;
  }

  span {
    font-size: 16px;
    color: #333;
  }
`;

export const WrapperStatusCount = styled.div`
  display: flex;
  align-items: center;
  flex-wrap: wrap; /* permite quebrar linha em telas menores */

  div {
    margin: 8px;
    text-align: center;

    h4 {
      margin-bottom: 4px;
    }

    a, span {
      font-size: 16px;
      font-weight: bold;
      color: #0366d6; /* azul padrão GitHub */
      text-decoration: none;
    }

    a:hover {
      text-decoration: underline;
    }
  }
`;

export const WrapperUserGeneric = styled.div`
  display: flex;
  align-items: center;
  margin-top: 8px;

  h3 {
    margin-right: 8px;
  }

  a {
    font-size: 18px;
    color: #0366d6;
    font-weight: bold;
    text-decoration: none;
  }

  a:hover {
    text-decoration: underline;
  }

  span {
    font-size: 16px;
    color: #555;
  }
`;

export const WrapperImage = styled.img`
  border-radius: 50%;
  width: 200px;
  margin: 8px;
  border: 2px solid #ddd;

  @media (max-width: 600px) {
    width: 150px;
  }
`;
