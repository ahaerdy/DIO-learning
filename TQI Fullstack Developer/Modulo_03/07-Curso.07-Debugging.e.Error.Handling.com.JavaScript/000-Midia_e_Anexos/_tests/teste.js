function verificaPalindromo(string) {
  if (!string) throw "String inválida";

  return string === string.split('').reverse().join('');
}

c = verificaPalindromo('omo');
console.log(c);

