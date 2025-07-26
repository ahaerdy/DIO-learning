# Parte 1 - Lidando com Data, Hora e Fuso Horário no Python

## 🟩 Vídeo 01 - Trabalhando com objetos date, datetime e time

### Objetivo Geral

- Aprender a trabalhar com datas, horas e fusos horários em Python, dominando o módulo 'datetime' para manipulações precisas.

### Introdução ao módulo datetime

#### O que é o módulo datetime?

- O módulo 'datetime' em Python é usado para lidar com datas e horas. Ele possui várias classes úteis como date, time e timedelta.

#### Exemplo de código

<p align="center">
    <img src="images/image.png" alt="Diagrama" width="560">
</p>

## 🟩 Vídeo 02 - Manipulando datas com timedelta

### Introdução

- Podemos criar e manipular objetos date, time e datetime de várias maneiras. Por exemplo, podemos adicionar e subtrair datas, verificar a diferença entre datas e muito mais.

### Exemplo de código

<p align="center">
    <img src="images/image-1.png" alt="" width="560">
</p>

### Exercício feito em aula

```
from datetime import datetime, timedelta

tipo_carro = "P"  # P, M, G
tempo_pequeno = 30
tempo_medio = 45
tempo_grande = 60
data_atual = datetime.now()

if tipo_carro == "P":
    data_estimada = data_atual + timedelta(minutes=tempo_pequeno)
    print(f"O carro chegou: {data_atual} e ficará pronto às {data_estimada}")
elif tipo_carro == "M":
    data_estimada = data_atual + timedelta(minutes=tempo_medio)
    print(f"O carro chegou: {data_atual} e ficará pronto às {data_estimada}")
else:
    data_estimada = data_atual + timedelta(minutes=tempo_grande)
    print(f"O carro chegou: {data_atual} e ficará pronto às {data_estimada}")

```

### Saída:

```
O carro chegou: 2025-07-26 14:45:05.077475 e ficará pronto às 2025-07-26 15:15:05.077475
```

## 🟩 Vídeo 03 - Formatando e convertendo datas com strftime e strptime

#### Introdução

- Python também permite converter e formatar datas e horas. Para isso, usamos os métodos 'strftime' (string format time) e 'strptime' (string parse time).

#### Exemplo de código

<p align="center">
    <img src="images/image-2.png" alt="" width="560">
</p>

## 🟩 Vídeo 04 - Trabalhando com timezone

#### Introdução

- Quando trabalhamos com data e hora, lidar com fusos horários é uma necessidade comum. Python facilita isso através do módulo 'pytz'.

#### Exemplo de código

<p align="center">
    <img src="images/image-3.png" alt="" width="560">
</p>

### Trabalhando com tz sem bibliotecas externas

- O Python permite fazer isso com o módulo datetime padrão, embora seja um pouco mais complexo do que usando bibliotecas como 'pytz'.

#### Exemplo de código

<p align="center">
    <img src="images/image-4.png" alt="" width="560">
</p>


# Parte 2 - Hands-On - Lidando com Data, Hora e Fuso Horário no Python 

## 🟩 Vídeo 01 - Explicação do desafio

## 🟩 Vídeo 02 - Resolução do desafio

# Parte 3 - Materiais de apoio e Questionário

## Materiais de apoio

Disponibilizados os seguinte arquivos em formato MS Powerpoint:
- 04-Dio_Dicionarios.pptx

## Certificado: Aprendendo a Utilizar Dicionários em Python

Link do certificado: https://hermes.dio.me/certificates/UZPKD2UH.pdf