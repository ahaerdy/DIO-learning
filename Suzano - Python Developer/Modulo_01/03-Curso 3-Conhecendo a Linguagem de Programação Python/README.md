## Vídeo 01 - Tipos de Dados

- Instrutor: Guilherme Arthur de Carvalho (Analista de Sistemas)
- Contato Libkedin: https://www.linkedin.com/in/decarvalhogui/

O autor apresenta os principais tipos de dados e porque devemos utilizá-los. Eles servem para definir as caracteristicas e comportamentos de um valor (objeto) para o interpretador. 

Os tipos built-in apresewntados foram:

| Categoria  | Tipos                                |
|------------|---------------------------------------|
| Texto      | `str`                                 |
| Numérico   | `int`, `float`, `complex`             |
| Sequência  | `list`, `tuple`, `range`              |
| Mapa       | `dict`                                |
| Coleção    | `set`, `frozenset`                    |
| Booleano   | `bool`                                |
| Binário    | `bytes`, `bytearray`, `memoryview`    |



link do vídeo: https://web.dio.me/track/suzano-python-developer/course/conhecendo-a-linguagem-de-programacao-python/learning/81ce773a-b004-43b8-bdc1-1f150df40f96?autoplay=1

## Vídeo 02 - Modo Interativo

- Instrutor: Guilherme Arthur de Carvalho (Analista de Sistemas)
- Contato Libkedin: https://www.linkedin.com/in/decarvalhogui/

1. O autor ensina como usar o modo interativo do interpretador Python.
    - Existem duas formas de iniciar o modo interativo, chamando apenas o interpretador (python) ou executando o script com a flag -i (python -i app.py).
2. Explica os comandos dir e help
    - dir: sem argumentos, retorna a lista de nomes no escopo local atual. Com um argumento, retorna uma lista de atributos válidos para o objeto. 
    - help: Invoca o sistema de ajuda integrado. É possível fazer buscas em modo interativo ou informar por parâmetro qual o nome do módulo, função, classe, método ou variável.

- Links úteis: https://github.com/guicarvalho/trilha-python-dio
- Referência: https://wiki.python.org.br/ModoInterativo

## Vídeo 03 - Variáveis e Constantes

- Instrutor: Guilherme Arthur de Carvalho (Analista de Sistemas)
- Contato Libkedin: https://www.linkedin.com/in/decarvalhogui/

1. Variáveis: em linguagens de programação podemos definir valores que podem sofrer alterações no decorrer da execução do programa. Esses valores recebem o nome de variáveis, pois eles nascem com um valor e não necessariamente devem permanecer com o mesmo durante a execução do programa.

2. Alterando os valores: não precisamos definir o tipo de dados da variável, o Python faz isso automaticamente para nós. Por isso não podemos simplesmente criar uma variável sem atribuir um valor. Para alterar o valor da variável basta fazer uma atribuição de um novo valor.

3. Constantes: assim como as variáveis, constantes são utilizadas para armazenar valores. Uma constante nasce com um valor e permanece com ele até o final da execução do programa, ou seja, o valor é imutável.

4. Python não tem constantes
    - Não existe uma palavra reservada para informar ao interpretador que o valor é constante. Em algumas linguagens por exemplo: Java e C utilizamos final e const, respectivamente para declarar uma constante.
    - Em Python usamos a convenção que diz ao programador  que a variável é uma constante. Para fazer isso, você deve criar a variável com o nome todo em letras maíusculas: 
        - BS_PATH = '/home/guilherme/Documents/python_course/'
        - DEBUG = True
        - STATES = ['SP','RJ','MG']
        - AMOUNT = 30.2




