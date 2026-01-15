import React from "react";
import ReactDOM from "react-dom/client"; // mudou aqui

import Providers from "./providers";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  // Comentando a linha abaixo apra evitar o duplo render em dev
  // <React.StrictMode>
    <Providers />
  // </React.StrictMode>
);
