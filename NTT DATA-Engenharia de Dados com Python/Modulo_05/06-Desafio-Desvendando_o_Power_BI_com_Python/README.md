## Instrutor:

- Juliana Mascarenhas (Tech Education Specialist / Sócia (Content Creator) @SimplificandoRedes / Me Modelagem Computacional / Cientista de dados)
- Contato Linkedin: / [juliana-mascarenhas-ds](https://www.linkedin.com/in/juliana-mascarenhas-ds/)

# Desafio 1 - Fundamentos de Business Intelligence (BI)

## Descrição

Business Intelligence (BI) envolve o uso de ferramentas e técnicas para coletar, processar e analisar dados, com o objetivo de apoiar a tomada de decisões estratégicas em uma organização. Os conceitos fundamentais de BI incluem a integração de dados, armazenamento, análise e visualização. Complete o código associando os fundamentos de BI às suas respectivas descrições para entender melhor cada conceito e sua aplicação prática.

## Entrada

A entrada é um termo ou conceito relacionado aos fundamentos de BI para o qual você deve retornar a descrição. Os termos considerados são:

- ETL
- Data Warehousing
- Dashboard
- Data Mining

## Saída

A saída esperada é a descrição associada ao termo fornecido como entrada. Seguem as saídas possíveis, listadas aleatoriamente, para que você possa analisar e associar corretamente:

- Armazenamento centralizado de dados para análise e relatórios.
- Processo de extrair, transformar e carregar dados de diversas fontes.
- Ferramenta visual para monitoramento e análise de métricas.
- Descoberta de padrões e insights em grandes conjuntos de dados.

## Exemplo

A tabela abaixo apresenta exemplos com alguns dados de entrada e suas respectivas saídas esperadas. Certifique-se de testar seu programa com esses exemplos e com outros casos possíveis.

<p align="center">
  <img src="000-Midia_e_Anexos/2026-01-25-10-48-53.png" alt="" width="480">
</p>

Atenção: É extremamente importante que as entradas e saídas sejam exatamente iguais às descritas na descrição do desafio de código.

Os desafios apresentados aqui têm como objetivo principal exercitar os conceitos aprendidos e proporcionar um primeiro contato com lógica de programação. Caso não tenha experiência em programação, utilize o template disponível e preencha com os conceitos aprendidos. Para resetar o template, basta clicar em “`Restart Code`”.

## Solução:

```python
def identificar_termo_bi(termo):
    if termo == "ETL":
        return "Processo de extrair, transformar e carregar dados de diversas fontes."
    elif termo == "Data Warehousing":
        return "Armazenamento centralizado de dados para análise e relatórios."
    elif termo == "Dashboard":
        return "Ferramenta visual para monitoramento e análise de métricas."
    elif termo == "Data Mining":
        return "Descoberta de padrões e insights em grandes conjuntos de dados."
    else:
        return "Termo não reconhecido"

entrada = input()

print(identificar_termo_bi(entrada))
```



