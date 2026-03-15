# Laboratório de Programação Modular

## Sobre
Materiais da disciplina **Laboratório de Programação Modular**, por ano. Conteúdo:
- **Listas de exercícios** em Java (console): pacotes por tema (variáveis, decisão, repetição, arquivos, KNN, banco, etc.)
- **Trabalho prático** — aplicação **Spring Boot** (API REST) para gestão de restaurante, com JPA e PostgreSQL

## Objetivo
- Praticar programação modular em Java (listas)
- Desenvolver API REST com Spring Boot, JPA e banco relacional (TP)

## Estrutura do projeto
- **`2024/`** — pasta do ano (única presente)
  - **`listas/`** — exercícios Java: `src/` (module-info.java + pacotes como programarcomputadoresbasicos, programarcomputadoresrepeticao, algoritmoknn, lab/listas/banco, etc.), README, .gitignore
  - **`trabalho prático/`** — projeto Spring Boot: **`restaurant/`** (pom.xml, src/main, src/test, target)
  - **`feliz.csv`**, **`300 ideias.pdf`**, **`README.md`**
- **`2024/README.md`** — descrição das listas e do TP

## Tecnologias
- **Java** (listas e TP)
- **Spring Boot**, **JPA**, **PostgreSQL** (trabalho prático)
- **Maven** (apenas no TP)

## Como executar
- **Listas:** marque `2024/listas/src` como Sources Root na IDE e rode a classe com `main` do exercício; ou use `javac`/`java` a partir de `listas/src`.
- **Trabalho prático:** em `2024/trabalho prático/restaurant` executar `mvn spring-boot:run` (JDK 17, Maven e PostgreSQL configurados). No Windows, use aspas no caminho se necessário: `cd "trabalho prático/restaurant"`.

## Autor
João V. Ferreira J. Estudante de Engenharia de Software.
