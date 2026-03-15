# Laboratório de Projeto de Software

## Sobre
Materiais da disciplina **Laboratório de Projeto de Software**, por ano. Conteúdo:
- Três trabalhos práticos em **Java** com **Spring Boot**:
  1. **Sistema de Matrículas** (console)
  2. **Sistema de Aluguel de Carros** (API REST)
  3. **Sistema de Moeda Estudantil** (API REST com JWT + front-end estático)

## Objetivo
- Aplicar práticas de projeto de software em sistemas progressivamente mais complexos
- Do console à API REST e integração com front-end

## Estrutura do projeto
- **`2025/`** — pasta do ano (única presente)
  - **`trabalho prático 1/`** — matrículas (console): **`matriculas/`** (pom.xml, src/main com com.universidade.matriculas)
  - **`trabalho prático 2/`** — aluguel de carros (API REST): **`aluguelcarros/`**
  - **`trabalho prático 3/`** — moeda estudantil: **`moedaestudantil/`** (API) e **`front/`** (front-end)
  - **`README.md`** — objetivo, TPs, tecnologias, como executar

## Tecnologias
- **Java**, **Spring Boot**
- **JPA**, **PostgreSQL** (TP1, TP2), **MySQL** (TP3)
- **JWT** (TP3), front estático (TP3)

## Como executar
- **TP1:** `cd 2025/trabalho prático 1/matriculas` → `mvn spring-boot:run` (no Windows: use aspas no caminho se precisar).
- **TP2:** `cd 2025/trabalho prático 2/aluguelcarros` → `mvn spring-boot:run`.
- **TP3:** configurar MySQL em `trabalho prático 3/moedaestudantil`, depois `mvn spring-boot:run`; front em `trabalho prático 3/front/`.

Detalhes no `2025/README.md`.

## Autor
João V. Ferreira J. Estudante de Engenharia de Software.
