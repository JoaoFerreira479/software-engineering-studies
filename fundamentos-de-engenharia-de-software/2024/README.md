# Fundamentos de Engenharia de Software — 2024

## Estrutura do projeto
- **`trabalho-pratico/`** — único projeto do ano
  - **domain/** — config.h, cliente, funcionario, quarto, diaria, estadia (.c/.h)
  - **infrastructure/** — io.c/h, arquivo.c/h
  - **application/** — main.c, cliente_app, funcionario_app, quarto_app, estadia_app (.c/.h)
  - Makefile, README.md

## Organização do código
- **domain** — constantes (HDG_*), structs (Cliente, Funcionario, Quarto, Estadia), validação de datas (validarData, calcularDiarias, compararDatas), regras (estadia_sobrepoe). Sem I/O.
- **infrastructure** — **io**: locale pt-BR, leitura (linha, int, float, opção), menu e mensagens; **arquivo**: carregar e salvar clientes, funcionários, quartos e estadias em binário.
- **application** — **main**: menu principal (Clientes, Funcionários, Estadias, Quartos, Sair), carrega/salva ao iniciar e ao sair; **_app**: cadastro e pesquisa, orquestrando domain + io + arquivo.

Sistema em C (console) para o hotel “Hotel Descanso Garantido”; dados em memória e persistência em arquivos.

## Como compilar e executar
- Na pasta **trabalho-pratico/**:
  - **Com Make:** `make`
  - **Com gcc:** `gcc -Wall -Wextra -std=c11 -Idomain -Iinfrastructure -Iapplication domain/*.c infrastructure/*.c application/*.c -o hdg`
- Executar: `./hdg` (Linux/macOS) ou `hdg.exe` (Windows). Menu no console; dados carregados/salvos em arquivos no diretório de execução. Sair com opção 0.

## Possíveis melhorias
- Testes unitários (CUnit ou Check) para datas, validação e persistência.
- Alvo `make test` e compilação incremental no Makefile.
- Documentar formato dos arquivos de dados (schema/campos) no README.
- Tratar erros de leitura/escrita de arquivo com mensagens claras ao usuário.
