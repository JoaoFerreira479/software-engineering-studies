# Introdução à Computação

## Sobre
Materiais da disciplina **Introdução à Computação**, por ano. Conteúdo:
- Trabalho prático **Contador de pares e ímpares**
- Mesma arquitetura em camadas (domain, infrastructure, application) em **C**, **C++**, **C#**, **Java** e **Python**

## Objetivo
- Implementar o mesmo problema em várias linguagens
- Comparar sintaxe e ferramentas mantendo a mesma estrutura

## Estrutura do projeto
- **`2022/`** — pasta do ano (única presente)
  - **`trabalho-pratico/`** — cinco implementações:
    - **`c/`** — C (C99): application/main.c, domain (contador, resultado), infrastructure/io, Makefile
    - **`cpp/`** — C++ (C++14): application/main.cpp, domain (.cpp/.hpp), infrastructure, Makefile
    - **`csharp/`** — C# (.NET 6): IntroComp.csproj, application/Program.cs, domain, infrastructure
    - **`java/`** — Java: src/introcomp (application/ContadorMain, domain, infrastructure/io)
    - **`python/`** — Python 3: application/main.py, domain (contador, resultado), infrastructure/io.py
- **`2022/README.md`** — objetivo, estrutura, compilação por linguagem

## Tecnologias
- **C** (gcc), **C++** (g++), **C#** (.NET 6), **Java**, **Python 3**
- **Make** (C e C++)

## Como executar
- **Java:** `cd trabalho-pratico/java` → compilar com `javac`, depois `java -cp out introcomp.application.ContadorMain`
- **C / C++:** `cd trabalho-pratico/c` ou `cpp` → `make` → `./contador`
- **C#:** `cd trabalho-pratico/csharp` → `dotnet run`
- **Python:** `cd trabalho-pratico/python` → `python application/main.py`

Detalhes no `2022/README.md`.

## Autor
João V. Ferreira J. Estudante de Engenharia de Software.
