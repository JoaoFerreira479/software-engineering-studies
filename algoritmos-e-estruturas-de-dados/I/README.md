# Algoritmos e Estruturas de Dados I (2022)

## Estrutura do projeto
- **`2022/`** — raiz do ano
  - **`src/`** — fontes; **`out/`** — compilação (gerado)
  - **`src/aed1/`** — pacote único do projeto
    - **`application/`** — dezenas de classes `*Main.java` (uma por exercício)
    - **`domain/`** — subpacotes: basico, condicional, repeticao, flag, vetores, matrizes
    - **`infrastructure/io/`** — Console.java, EntradaUsuario.java, LeituraDados.java
  - **`src/module-info.java`** — módulo Java

## Organização do código
- **application** — cada classe tem `main`: lê teclado (via infrastructure), chama domain e imprime resultado. Sem regra de negócio.
- **domain** — só lógica: cálculos, validação, algoritmos. Sem Scanner nem System.out.
  - **basico** — área, distância, conversões (moedas, medidas, tempo), PA, triângulo, compra de pães
  - **condicional** — categoria natação, equação 2º grau, média, menor de três, ordenar três, pagamento, tipo de triângulo
  - **repeticao** — sequência, múltiplos, números aleatórios
  - **flag** — série de números, consumo água/energia, grupo de pessoas, lançamentos moeda
  - **vetores** — combinar, intercalar, remover elemento, duplicados, segundo maior, posições pares (recursivo)
  - **matrizes** — soma de matrizes, iguais, diagonal principal, terceiro menor, preencher com vetor, soma por coluna
- **infrastructure.io** — Console (saída), EntradaUsuario (leitura com retry), LeituraDados (vetor/matriz). Único ponto de I/O.

## Como compilar e executar
- **IDE:** abra a pasta `2022` (ou `I`), marque `2022/src` como Sources Root. Rode a classe `*Main` desejada.
- **Terminal** (na pasta `2022`):
  - Compilar: incluir `module-info.java` e todos os `.java` de `src/aed1`. Ex.: `javac -d out -encoding UTF-8 -sourcepath src src/module-info.java src/aed1/infrastructure/io/*.java src/aed1/domain/basico/*.java ...` (ou por pacote).
  - No PowerShell, se `**` não expandir: listar os `.java` com `Get-ChildItem -Path src -Recurse -Filter *.java` e passar para `javac`.
  - Executar: `java -cp out aed1.application.ConversaoMoedasMain` (troque pelo Main que quiser).

## Possíveis melhorias
- Testes unitários (JUnit) para as classes de domain.
- Gradle ou Maven para compilar e rodar testes.
- Javadoc nos métodos públicos do domain e da infrastructure.
- Constantes compartilhadas se limites iguais aparecerem em vários Mains.
