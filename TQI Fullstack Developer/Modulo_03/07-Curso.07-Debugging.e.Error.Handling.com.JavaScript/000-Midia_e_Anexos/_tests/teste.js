function verificaPalindromo(string) {
  if (!string) throw "String inválida";
  return string === string.split('').reverse().join('');
}

function tryCatchExemplo(string) {
  try {
    verificaPalindromo(string)
  }
  catch (e) {      // Captura o erro lançado pela função verificaPalindromo
    console.log(e) // Exibe o erro controlado no console
    throw (e);     // Relança o erro para ser tratado em outro lugar, se necessário
  }
}

tryCatchExemplo('');
