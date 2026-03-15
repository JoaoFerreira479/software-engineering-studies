# Algoritmos e Estruturas de Dados I

## Sobre
Disciplina AED I (graduação em Engenharia de Software). Códigos em **Java**, organizados por nível e ano:
- **I/** — AED I: lógica, estruturas de controle, vetores, matrizes, recursão (console)
- **II/** — AED II: 2022 e 2024 (estruturas de dados; em II há também `medallists.csv` em 2024)

## Objetivo
- Praticar fundamentos: lógica, condicionais, repetição, vetores, matrizes, recursão
- Exercícios em Java com leitura no teclado e saída no console

## Estrutura do projeto
- **`I/2022/`** (AED I) e **`II/2022/`**, **`II/2024/`** (AED II)
- Em cada ano: **`src/`** com módulo (`aed1` ou `aed2`):
  - **`application/`** — classes `*Main` (ponto de entrada)
  - **`domain/`** — lógica: `basico`, `condicional`, `repeticao`, `flag`, `vetores`, `matrizes`
  - **`infrastructure/io/`** — Console, EntradaUsuario, LeituraDados
- **`out/`** — saída da compilação (gerado)

Cada exercício = uma classe `*Main` executável.

## Tecnologias
- **Java** (JDK 11+)
- Java Platform Module System (módulos `aed1`, `aed2`)
- **Scanner** para entrada
- Sem Maven/Gradle

## Como executar
- **IDE:** abra a pasta do nível/ano (ex.: `I/2022`), marque `2022/src` como Sources Root. Rode a classe `*Main` desejada.
- **Terminal:** dentro da pasta do ano (ex.: `I/2022`), compile com `javac` a partir de `src` (incluindo `module-info.java` e os pacotes em `aed1` ou `aed2`). Exemplo de execução: `java -cp out aed1.application.ConversaoMoedasMain`.

Detalhes e comando completo de compilação (incl. PowerShell) em `I/README.md` e `II/README.md`.

## Autor
João V. Ferreira J. Estudante de Engenharia de Software.
