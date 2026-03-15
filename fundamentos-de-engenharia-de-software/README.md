# Fundamentos de Engenharia de Software

## Sobre
Materiais da disciplina **Fundamentos de Engenharia de Software**, por ano. Conteúdo:
- Sistema em **C** (console): gestão do hotel fictício “Hotel Descanso Garantido”
- Cadastro de clientes, funcionários, quartos e estadias; persistência em arquivo

## Objetivo
- Aplicar conceitos de engenharia de software em um sistema em C
- Trabalho prático com camadas: domain, infrastructure, application

## Estrutura do projeto
- **`2024/`** — pasta do ano (única presente)
  - **`trabalho-pratico/`** — projeto em C:
    - **`domain/`** — cliente, funcionário, quarto, estadia, diária, config
    - **`infrastructure/`** — arquivo.c/h, io.c/h
    - **`application/`** — main.c, cliente_app, funcionario_app, quarto_app, estadia_app
    - **`Makefile`**, **`README.md`**
- **`2024/README.md`** — objetivo, módulos, compilação e execução

## Tecnologias
- **C** (compilador **gcc**)
- **Make** (Makefile)

## Como executar
- Em `2024/trabalho-pratico/`: `make` (ou comando `gcc` indicado no README do TP).
- Executar: `./hdg` (Linux/macOS) ou `hdg.exe` (Windows). Dados em arquivos no diretório de execução.

## Autor
João V. Ferreira J. Estudante de Engenharia de Software.
