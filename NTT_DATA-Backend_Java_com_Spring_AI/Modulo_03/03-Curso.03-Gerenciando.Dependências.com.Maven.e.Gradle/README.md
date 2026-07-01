## Instrutor

- José Luiz Abreu Cardoso Junior (Engenheiro de software sênior)
- Contato Linkedin: / [juniorjrjl](https://www.linkedin.com/in/juniorjrjl/)

## Parte 1 - Instalação e Configuração do Maven

### 🟩 Vídeo 01 - Instalando Maven

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.03-curso.03-video_01.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/gerenciando-dependencias-com-maven-e-gradle/learning/d9d9f3b4-84fa-4114-8436-28067fffae34?autoplay=1

### Anotações

<p align="center">
  <img src="000-Midia_e_Anexos/2026-07-01-08-51-23.png" alt="" width="840">
</p>

A aula trata da instalação e uso do Maven no Ubuntu/Linux. O instrutor explica que o Maven é uma ferramenta para gerenciar dependências e automatizar builds em projetos Java, mostrando como instalar via apt (forma convencional e simples) e também mencionando a alternativa manual de baixar diretamente do site oficial do Apache Maven, configurar variáveis de ambiente e usar versões mais atualizadas. O foco é demonstrar como preparar o ambiente para que o Maven esteja disponível no sistema e pronto para ser utilizado em projetos Java.

#### Preparando Ambiente Java + Maven no Kubuntu

#### 1. Verificar instalação do Java
Execute os comandos:
```bash
java -version
javac -version
```
Saída esperada (exemplo):
```
openjdk version "21.0.11"
javac 21.0.11
```

#### 2. Instalar o Maven
Atualize os pacotes e instale:
```bash
sudo apt update
sudo apt install maven
```

#### 3. Confirmar instalação do Maven
Verifique a versão:
```bash
mvn -version
```
Saída esperada (exemplo):
```
Apache Maven 3.9.12
Java version: 21.0.11
```

#### 4. Estrutura do Projeto
O projeto deve conter um arquivo `pom.xml` na raiz, que define:
- Dependências
- Plugins
- Configurações de build

#### 5. Compilar o Projeto
Dentro da pasta do projeto:
```bash
mvn compile
```

#### 6. Executar Testes
```bash
mvn test
```

#### 7. Gerar Arquivo JAR
```bash
mvn package
```
O `.jar` será criado em `target/`.

#### 8. Executar o Programa
```bash
java -jar target/meu-projeto-1.0-SNAPSHOT.jar
```

#### 9. Executar Diretamente sem JAR
```bash
mvn exec:java -Dexec.mainClass="com.seuprojeto.Main"
```

#### 10. Observações
- O IntelliJ IDEA reconhece automaticamente o `pom.xml` e baixa dependências.  
- O terminal usa o mesmo `pom.xml`, garantindo consistência entre IDE e linha de comando.  
- Maven facilita o gerenciamento de **dependências** e a **automatização de builds**.

### 🟩 Vídeo 02 - Instalando Maven com sdkman e criando projetos

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.03-curso.03-video_02.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/gerenciando-dependencias-com-maven-e-gradle/learning/425b429c-c4bf-4bef-937d-4a523c4013ee?autoplay=1


### 🟩 Vídeo 03 - Concluindo o projeto

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.03-curso.03-video_03.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/gerenciando-dependencias-com-maven-e-gradle/learning/e7fcb087-73a3-40e5-8e4d-5a9516441bd6?autoplay=1

## Parte 2 - Instalação e Configuração do Gradle

### 🟩 Vídeo 04 - Instalando Gradle

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.03-curso.03-video_04.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo: https://web.dio.me/track/ntt-data-2026-ai-java-back-end/course/gerenciando-dependencias-com-maven-e-gradle/learning/b7d688f1-cd7a-4678-a786-6978c61eb002?autoplay=1

### 🟩 Vídeo 05 - Criando um projeto Gradle

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.03-curso.03-video_05.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

## Parte 3 - Comparação e Migração entre Ferramentas

### 🟩 Vídeo 06 - Mas afinal porque usar o Maven ou Gradle

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.03-curso.03-video_06.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 07 - Migrando Projeto Maven para Gradle

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.03-curso.03-video_07.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

### 🟩 Vídeo 08 - Migrando projeto Gradle para Maven

<video width="60%" controls>
  <source src="000-Midia_e_Anexos/bootcamp_ntt_data_java_spring_ai-modulo.03-curso.03-video_08.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>

link do vídeo:

##  Materiais de Apoio

# Certificado: 

- Link na plataforma: 
- Certificado em pdf: