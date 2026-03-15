# Algoritmos e Estruturas de Dados II

## Estrutura do projeto
Dois anos na mesma pasta **II/**:

- **`2022/`** — src/aed2/, out/, module-info.java
  - **application/** — Guia1Main … Guia13Main, AvaliacaoNotaMain, ParOuImparMain, ContadorDeOperacoesDinamicoNovoMain, SomatoriosNovoMain, etc.
  - **domain/** — algoritmos, estruturas, model, nivelamento, recursao, matrizes, lista1, lista2
  - **infrastructure/io/** — Console, EntradaUsuario, LeituraDados
- **`2024/`** — src/aed2/, out/, **medallists.csv**, module-info.java
  - **application/** — GerenciadorListaEncadeadaMain, GerenciadorFilaMain, BemFormadaMain, ContaOcorrenciasMain, PalindromoMain, AlgebraBooleanaMain, ABB*, GerenciadorEventos*, Ordenacao*, AplicacaoOlimpiadasMain, BubbleSortMain … MergeSortMain, OrdenacaoMainHelper
  - **domain/** — model, estruturas, ordenacao, recursao, bemformada
  - **infrastructure/io/** — Console, EntradaUsuario, LeituraDados

## Organização do código
- **application** — classes com `main`: orquestram I/O (e leitura de CSV em 2024), chamam domain e exibem resultado.
- **domain (2022)** — algoritmos (Fibonacci, VetorUtil, Ordenacao); estruturas (ListaSequencial, ListaEncadeada, Pilha, Fila, ArvoreBinariaBusca, nós); model (Pessoa, Livro, Contato, etc.); nivelamento; recursao; matrizes; lista1 (CatalogoOperacoes); lista2 (Fracao).
- **domain (2024)** — model (TipoMedalha, Medalha, Medalhista, Evento, LinhaMedalhista, etc.); estruturas (listas, ABB, Pilha, Fila, Conjunto, TabelaHash, Mapa); ordenacao (IOrdenator, BubbleSort … MergeSort); recursao (ContaOcorrencias, Palindromo, AlgebraBooleana); bemformada (expressões balanceadas).
- **infrastructure.io** — Console, EntradaUsuario, LeituraDados; ponto único de I/O.

Em ambos os anos: domínio sem I/O; estruturas de dados próprias (sem coleções da JDK).

## Como compilar e executar
- **2022:** na pasta `II/2022`. Compilar: listar todos os `.java` de `src` (ex.: PowerShell `Get-ChildItem -Path src -Recurse -Filter *.java`) e `javac -d out -encoding UTF-8 -sourcepath src $files`. Executar: `java -cp out aed2.application.Guia1Main` (troque pelo Main).
- **2024:** na pasta `II/2024`. Mesmo esquema de compilação. Executar: `java -cp out aed2.application.GerenciadorListaEncadeadaMain` (ou outro Main). O **medallists.csv** deve estar no diretório de execução para Mains que leem CSV.

## Possíveis melhorias
- Testes unitários para domain (algoritmos, estruturas, recursao, ordenacao).
- Gradle ou Maven para compilar e testar.
- Javadoc em métodos públicos das estruturas e algoritmos.
- Manter leitura de arquivo (BufferedReader/Files) em infrastructure ou adapter.
