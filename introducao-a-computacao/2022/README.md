# Introdução à Computação — 2022

## Estrutura do projeto
- **`trabalho-pratico/`** — cinco implementações do mesmo problema (contador de pares e ímpares):
  - **c/** — domain (contador, resultado), infrastructure (io), application (main.c), Makefile
  - **cpp/** — domain (.hpp/.cpp), infrastructure (io), application (main.cpp), Makefile
  - **csharp/** — domain (ContadorParesImpares, ResultadoContagem), infrastructure (ConsoleIO), application (Program.cs), IntroComp.csproj
  - **java/** — src/introcomp: domain, infrastructure/io, application (ContadorMain)
  - **python/** — domain (contador, resultado), infrastructure (io), application (main.py)

## Organização do código
- **domain** — dados (ex.: ResultadoContagem) e lógica pura (contar pares/ímpares, formatar). Sem printf/scanf, Console, input/print.
- **infrastructure** — I/O: leitura de inteiros, exibição de resultado e mensagens, stderr em erro. Ponto único de acesso ao console.
- **application** — orquestração: pergunta quantidade, lê números (via infrastructure), chama domain (contar), exibe resultado. Trata códigos de saída.

Mesma arquitetura em todas as linguagens; entrada: N números; saída: quantidade de pares e de ímpares.

## Como compilar e executar
- **Java:** `cd trabalho-pratico/java` → `javac -d out -encoding UTF-8 src/introcomp/domain/*.java src/introcomp/infrastructure/io/*.java src/introcomp/application/*.java` → `java -cp out introcomp.application.ContadorMain`
- **C:** `cd trabalho-pratico/c` → `make` → `./contador`
- **C++:** `cd trabalho-pratico/cpp` → `make` → `./contador`
- **C#:** `cd trabalho-pratico/csharp` → `dotnet run`
- **Python:** `cd trabalho-pratico/python` → `python application/main.py`

## Possíveis melhorias
- Testes unitários para o domain em cada linguagem.
- Documentar pré/pós-condições dos métodos do domain (Javadoc/docstrings).
- Validação de entrada na application (mensagens amigáveis) antes de chamar domain.
