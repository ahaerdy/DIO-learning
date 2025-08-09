- Instrutor: Karina Kato (Machine Learning Engineer)
    - Linkedin: https://www.linkedin.com/in/karinakato/
    -  Github: https://github.com/tiemi/


- Neste projeto você aprenderá a criar o seu primeiro pacote de processamento de imagens em Python e disponibilizá-lo no repositório Pypi. Assim você poderá reutilizá-lo facilmente e compartilhá-lo com outras pessoas. A especialista também vai mostrar um exemplo de pacote para processamento de imagens.

## 🟩 Vídeo 01 - Parte 1 - Introdução e Conceitos

### Objetivos

- Entender conceitos relacionados aos pacotes
- Atualizar o projeto e gerar as distribuições
- Publicar o pacote

### Módulo vs Pacote

- Módulo: objeto que serve como unidade organizacional do código que é carregado pelo comando de import.
- Pacote: coleção de módulos com hierarquia.

### Modularização

- Vantagens da modularização:
    - Legibilidade
    - Manutenção
    - Reaproveitamento de código

### Pacote em Python

- Vantagens de criar um pacote:
    - Facilidade de compartilhamento
    - Facilidade de instalação

### Conceitos

- **Pypi**: repositório público oficial de pacotes
- **Wheel e Sdist**: dois tipos de distribuições
- **Setuptools**: pacote usado em setup.py para gerar as distribuições
- **Twine**: pacote usado para subir as distribuições no repositório Pypi

### Passos

<p align="center">
    <img src="images/image.png" alt="" width="840">
</p>

## 🟩 Vídeo 02 - Parte 2 - Criar o projeto e gerar as distribuições

### Exemplos de estruturas

<p align="center">
    <img src="images/image-2.png" alt="" width="840">
</p>

### Estrutura de pacote simples

<p align="center">
    <img src="images/image-3.png" alt="" width="840">
</p>

### Estrutura de pacote com vários módulos

<p align="center">
    <img src="images/image-4.png" alt="" width="840">
</p>

### Repositórios disponíveis

<p align="center">
    <img src="images/image-5.png" alt="" width="840">
</p>

- Repositórios com os templates:
    - https://github.com/tiemi/simple-package-template
    - https://github.com/tiemi/package-template
    - https://github.com/tiemi/image-processing-package

### Passos

<p align="center">
    <img src="images/image-6.png" alt="" width="840">
</p>

### Passos para criar o projeto

<p align="center">
    <img src="images/image-7.png" alt="" width="840">
</p>

### Exemplo de pacote com vários módulos

<p align="center">
    <img src="images/image-8.png" alt="" width="840">
</p>

- `image-processing-package` ➡️ gerado a partir do `package-template`

<p align="center">
    <img src="images/image-9.png" alt="" width="840">
</p>

### Arquivos do projeto image-processing

<p align="center">
    <img src="images/image-10.png" alt="" width="1024">
</p>

- Mostrando em mais detalhes o que esse pacote faz:

    - Aqui tem um exemplo de resultado na saída.
    - Este é um exemplo que tem deentro de `combinatio.py`.
    - Em `combination.py` uma das operações que voc6e pode fazer é o matching de histogramas.
    - Em match de histogramas basicamente a ideia é você passar duas imagens, onde a primeira é uma imagem de referência e a segunda é uma de referência de histograma. 
    - A terceira gerada é o matching de histograma da segunda, com o conteúdo da primeira.

### Arquivos do projeto image-processing

<p align="center">
    <img src="images/image-11.png" alt="" width="1024">
</p>

### Arquivos do projeto image-processing

<p align="center">
    <img src="images/image-12.png" alt="" width="1024">
</p>

- Verificação de similaridade (a imagem resultante mostra a diferença).
- Para tanto, as imagens são, antes, convertidas em tons de cinza.
- O nível de similaridade é quantizado (entre 0 e 1).
- No processamento em questão o resultado foi 0.95.

## 🟩 Vídeo 03 - Parte 3 - Arquivos do projeto image-processing

### Apresentando os arquivos da estrutura real

<p align="center">
    <img src="images/image-17.png" alt="" width="1024">
</p>


## 🟩 Vídeo 04 - Parte 4 -  Arquivos do pacote

- Detalhes dos arquivos de configuração (e se devemos usar todos).

### Arquivo setup.py

- É o arquivo principal de configuração.
- Usado para especificar como o pacote deve ser construído.
- Documentação (caso sejam necessárias configurações extras):
    - https://setuptools.readthedocs.io/en/latest/setuptools.html
- Através do parâmetro `packages` você vai especificar todos os módulos e submódulos do seu projeto. A função `find_modules()`é usada para encontrá-los automaticamente.
- O parâmetro `install_requires` deve ser utilizado se o seu pacote tiver dependência de outros pacotes.

<p align="center">
    <img src="images/image-13.png" alt="" width="1024">
</p>

### Arquivo requirements.txt

- Usado para passar as dependências que devem ser instaladas com o seu pacote. Opcionalmente, podem ser especificadas as versões.
- Ele identifica os requisitos do seu pacote (não do seu projeto).

<p align="center">
    <img src="images/image-18.png" alt="" width="1024">
</p>

### Arquivo README.md

- Será exibido como documentação na página do Pypi do seu pacote. Foi usado markdown.

<p align="center">
    <img src="images/image-14.png" alt="" width="1024">
</p>

### Passos

<p align="center">
    <img src="images/image-15.png" alt="" width="1024">
</p>

## 🟩 Vídeo 05 - Parte 5 - Distribuições

### Distribuições

- Para subir o pacote, criar uma distribuição binária ou distribuição de código fonte. 
- As versões mais recentes do pip instalam primeiramente a binária e usam a distribuição de código fonte, apenas se necessário. 
- De qualquer forma, iremos criar ambas distribuições.

<p align="center">
    <img src="images/image-19.png" alt="" width="1024">
</p>

### Passos para gerar as distribuições

<p align="center">
    <img src="images/image-16.png" alt="" width="1024">
</p>

### Comandos de Instalação

#### Primeiramente:

```bash
python -m pip install --upgrade pip         # Upgrade do pip, bom fazer antes de subir.
python -m pip install --user twine          # Instalando o twine, ferramenta que vai nos
                                            # auxiliar a subir as distribuições
python -m pip install --user setuptools     
```

- Esses comandos fazem o seguinte:

    - Atualizam o pip para a versão mais recente.
    - Instalam o Twine, uma ferramenta para publicar pacotes Python.
    - Instalam o Setuptools, que ajuda na criação e distribuição de pacotes Python.

#### Em seguida:

```bash
python setup.py sdist bdist_wheel
```

- Esse comando é usado para gerar distribuições do seu pacote Python:
    
    - sdist: cria um pacote fonte (um arquivo .tar.gz).
    - bdist_wheel: cria um pacote binário no formato .whl (Wheel), que é mais rápido de instalar.


## 🟩 Vídeo 06 - Parte 6 - Publicando o Pacote

### Passos

<p align="center">
    <img src="images/image-21.png" alt="" width="1024">
</p>

### Passos para Subir o Pacote

- Passos do 1 ao 4 são opcionais mas muito recomendados 
- São relativos à criação da conta no Test PyPi (uma boa prática)
- As contas no Teste Pypi e PyPi são separadas.
- Recomenda-se usar a mesma senha para facilitar.
- Se preferir, pode ir para os passos 5, 6 e 7 diretamente (mas é recomendado testar o pacote localmente)

<p align="center">
    <img src="images/image-23.png" alt="" width="1024">
</p>

### Criando contas no Pypi

- https://pypi.org/account/register/
- https://test.pypi.org/account/register/

- É demonstrada a criação as contas (tela de abertura no test.pypi)... 👇

<p align="center">
    <img src="images/image-24.png" alt="" width="1024">
</p>

### Comando para publicar no Test Pypi

```bash
python -m twine upload --repository-url https://test.pypi.org/legacy/ dist/*
```

- A instrutora mostra o pacote do image-processo no repositorio (depois do upload):

<p align="center">
    <img src="images/image-26.png" alt="" width="1024">
</p>

### Comando para instalar o pacote de teste

```python
pip install –-index-url https://test.pypi.org/simple/ image-processing
```

- A instrutora executa o comando de instalação acima:

<p align="center">
    <img src="images/image-27.png" alt="" width="1024">
</p>

- Procedendo 1a instalação:
    - Lembrando que dentro do requirements.txt estão sendo passadas as dependências.

<p align="center">
    <img src="images/image-28.png" alt="" width="1024">
</p>

### Comando para publicar no Pypi

```bash
python -m twine upload --repository-url https://upload.pypi.org/legacy/ dist/*
```

- Exxecutando o comando ...

<p align="center">
    <img src="images/image-29.png" alt="" width="1024">
</p>

- Verificando se o pacote subiu ...

<p align="center">
    <img src="images/image-30.png" alt="" width="1024">
</p>

### Comando para instalar o pacote

```python
python -m pip install package_name
```

- Fazendo a instalação ...

<p align="center">
    <img src="images/image-31.png" alt="" width="1024">
</p>

## 🟩 Vídeo 07 - Parte 7

### Resumo

<p align="center">
    <img src="images/image-32.png" alt="" width="840">
</p>

<p align="center">
    <img src="images/image-33.png" alt="" width="840">
</p>

<p align="center">
    <img src="images/image-34.png" alt="" width="840">
</p>


### Exercício prático

<p align="center">
    <img src="images/image-35.png" alt="" width="840">
</p>

<p align="center">
    <img src="images/image-37.png" alt="" width="840">
</p>

<p align="center">
    <img src="images/image-38.png" alt="" width="840">
</p>

- Aqui a instrutora mostra que o pacote image_processing está funcionando e que os alunos podem testá-lo depois que terminarem.

<p align="center">
    <img src="images/image-39.png" alt="" width="840">
</p>

