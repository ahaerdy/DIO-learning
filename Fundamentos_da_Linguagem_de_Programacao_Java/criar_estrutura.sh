#!/usr/bin/env bash
#
# criar_estrutura.sh
#
# Cria a estrutura de pastas do repositório de estudos da
# Formação DIO: Fundamentos da Linguagem de Programação Java
#
# Uso:
#   chmod +x criar_estrutura.sh
#   ./criar_estrutura.sh
#
# Execute este script na RAIZ do projeto.

set -e

echo "Criando estrutura de diretórios..."

# ---------- Módulo 1 ----------
mkdir -p "Modulo_01/01-Curso.01-Introducao_ao_Java_e_seu_Ambiente_de_Desenvolvimento"
mkdir -p "Modulo_01/02-Curso.02-Fundamentos_da_Sintaxe_da_Linguagem_Java"
mkdir -p "Modulo_01/03-Curso.03-Estruturas_de_Controle_em_Java"
mkdir -p "Modulo_01/04-Desafio-Fundamentos_da_Linguagem_de_Programacao_Java"

# ---------- Módulo 2 ----------
mkdir -p "Modulo_02/01-Curso.01-Java.e.a.Arte.da.Abstracao.com.Classes.e.Encapsulamento"
mkdir -p "Modulo_02/02-Curso.02-Heranca.e.Polimorfismo.em.Java"
mkdir -p "Modulo_02/03-Curso.03-Dominando.Interfaces.e.Lambda.em.Java"
mkdir -p "Modulo_02/04-Curso.04-Imersao.Pratica.com.Collections.e.Outras.Classes.Uteis.do.Java"
mkdir -p "Modulo_02/05-Desafio-Criando.um.Jogo.do.Sudoku.em.Java"
mkdir -p "Modulo_02/06-Desafio-Estruturas.de.Controle.em.Java"

# ---------- Módulo 3 ----------
mkdir -p "Modulo_03/01-Curso.01-Debugging.e.o.Tratamento.de.Excecoes.em.Java"
mkdir -p "Modulo_03/02-Curso.02-Simplificando.IO.de.Arquivos.e.Diretorios.com.Java"
mkdir -p "Modulo_03/03-Curso.03-Gerenciando.Dependencias.com.Maven.e.Gradle"
mkdir -p "Modulo_03/04-Desafio-Criando.seu.Board.de.Tarefas.com.Java"

# ---------- Módulo 4 ----------
mkdir -p "Modulo_04/01-Curso.01-Annotations.em.Java.Marcando.o.Seu.Codigo.de.Maneira.Inteligente"
mkdir -p "Modulo_04/02-Curso.02-SOLID.e.Clean.Code.em.Java.Escrevendo.Codigo.de.Alta.Qualidade"
mkdir -p "Modulo_04/03-Curso.03-JDBC.Explorando.Persistencia.de.Dados.com.Java"
mkdir -p "Modulo_04/04-Curso.04-Avalie.a.Formacao.Java.Fundamentals"

# ---------- Subpasta 000-Midias_e_Anexos em cada curso/desafio ----------
for pasta in Modulo_0*/*/; do
    mkdir -p "${pasta}000-Midias_e_Anexos"
done

# ---------- Arquivo .gitkeep em cada subpasta (para versionar diretórios vazios no Git) ----------
find Modulo_0* -type d -empty -exec touch {}/.gitkeep \;

echo "Estrutura criada com sucesso!"
echo ""
find Modulo_0* -type d | sort