# Refatoração lista2treino — Decisões e Relatório

## 1. Escopo

- **SomatoriosNovo.java**: calculadora de somatórios (menu: potências, base^n, x_i^3, alternados, frações).
- **GeradorDeSomatoriosNovo.java** + **SistemaSomatorio**: análise de expressões de somatório (inteiros, frações, potências) e detecção de PA/PG.
- **AnalisadorCodigoAprimorado.java**: análise de código Java (loops simples/aninhados, extração de expressão, fórmulas e complexidade).

---

## 2. Decisões arquiteturais

### 2.1 Pacote dominio e Fracao

- **lista2treino.dominio.Fracao**: fração irredutível imutável (numerador/denominador), com `adicionar`, MDC e normalização no construtor. Denominador sempre positivo; sinal no numerador.
- **Motivo**: Fracao é entidade de domínio reutilizável; extraída de SomatoriosNovo para pacote `dominio`, alinhado ao padrão usado em laboratorio.

### 2.2 SomatoriosNovo

- **Try-with-resources**: `Scanner` no `main` com try-with-resources; fechamento garantido.
- **lerInteiro(Scanner, String rotulo)**: método único para leitura de inteiro (menu, N, x_i), com tratamento de `InputMismatchException` e rótulo opcional para mensagens.
- **Sinal alternado**: uso de `(n % 2 == 0) ? 1 : -1` em vez de `(int) Math.pow(-1, n)` para evitar ponto flutuante e deixar a intenção explícita.
- **Validação de N**: em `calcularSomatorioXPotencia`, N <= 0 rejeitado com mensagem e return.
- **Classe final**: impede extensão e instanciação.

### 2.3 GeradorDeSomatoriosNovo e SistemaSomatorio

- **Sem Arrays.stream**: substituição de `Arrays.stream(numeros).mapToInt(Integer::parseInt).toArray()` por **parseInteiros(String[])** (loop manual) e de `Arrays.stream(valores).sum()` por **somaInteiros(int[])** (loop manual), atendendo à restrição de não usar streams/coleções como parte central do processamento.
- **Try-with-resources**: `Scanner` em `SistemaSomatorio.iniciar()` dentro de try-with-resources.
- **Constante COMANDO_FIM**: centralizada em `GeradorDeSomatoriosNovo` (package-private) para uso em `SistemaSomatorio`.
- **Validação em analisarFracoes**: checagem de `partes.length == 2` e denominador zero; mensagens de erro objetivas.
- **Correção em analisarFracoes**: uso de `antigoComum` antes de atualizar `denominadorComum` no cálculo do numerador da soma, evitando uso incorreto do valor já multiplicado.
- **analisarPotencias**: validação de `partes.length == 2` para cada potência.
- **verificarPG**: comentário explicando que a verificação usa divisão inteira e vale apenas para razões inteiras.

### 2.4 AnalisadorCodigoAprimorado

- **Try-with-resources**: `Scanner` no `main` criado e fechado no try.
- **capturarCodigo(Scanner)**: extração da lógica de captura (FIM ou duas linhas vazias) para método estático testável; tratamento de `linha` nula.
- **Constante COMANDO_FIM**: uso de constante em vez de literal.
- **Classe final**: uso consistente com os demais pontos de entrada.

### 2.5 Restrições

- **Sem coleções/streams como núcleo**: em GeradorDeSomatoriosNovo, parsing e soma feitos com arrays e loops; `String.split` retorna array (permitido).
- **Permitido**: String, Scanner, Objects, Exception, StringBuilder, StringBuffer, Pattern, Matcher, Math, Integer.parseInt.

---

## 3. Principais problemas encontrados na versão anterior

| Problema | Onde | Impacto |
|----------|------|---------|
| **Arrays.stream().mapToInt().toArray() e .sum()** | GeradorDeSomatoriosNovo | Uso de stream como parte central do processamento; substituído por loops manuais. |
| **Scanner não fechado em exceção** | SomatoriosNovo, GeradorDeSomatoriosNovo, AnalisadorCodigoAprimorado | Risco de vazamento de recurso; padronizado try-with-resources. |
| **lerOpcao usado para N e x_i** | SomatoriosNovo | Mesmo método para menu e valores numéricos, com prompt "Opção" inadequado; separado em lerInteiro(scanner, rotulo). |
| **Math.pow(-1, n) para sinal** | SomatoriosNovo | Desnecessário uso de ponto flutuante; trocado por (n % 2 == 0) ? 1 : -1. |
| **Fracao no mesmo arquivo** | SomatoriosNovo | Domínio misturado com orquestração; Fracao movida para lista2treino.dominio. |
| **N sem validação** | SomatoriosNovo | N <= 0 poderia gerar loop vazio ou confusão; adicionada validação. |
| **Cálculo do numerador na soma de frações** | SistemaSomatorio.analisarFracoes | Uso de denominadorComum já atualizado no mesmo passo; corrigido com antigoComum. |
| **Entrada vazia em SistemaSomatorio** | GeradorDeSomatoriosNovo | Linha em branco continuava o loop sem mensagem; tratada com continue. |

---

## 4. Ganhos técnicos

- **Conformidade**: parsing e soma sem stream; Fracao em dominio; recursos fechados de forma segura.
- **Testabilidade**: `lerInteiro`, `parseInteiros`, `somaInteiros`, `capturarCodigo` e lógica de análise podem ser testados com in-memory Scanner ou strings.
- **Robustez**: validação de N, denominador zero e formato de fração/potência; tratamento de null em entrada.
- **Clareza**: sinal alternado explícito; responsabilidades separadas (dominio vs orquestração vs análise).
- **Manutenção**: constantes centralizadas; código de captura e análise mais legível.

---

## 5. Justificativa das estruturas de dados

- **Fracao (dominio)**: imutável, normalizada no construtor (MDC, sinal no numerador). Adequada para somas iterativas sem acumular erros de representação; evita duplicar lógica de fração em mais de um lugar.
- **Arrays de primitivos (int[], String[])**: usados para sequências de valores oriundas de split/parsing; não há necessidade de lista dinâmica ou mapa; acesso por índice e iteração com for são suficientes.
- **StringBuilder / StringBuffer**: apenas para montagem de strings e substituição em regex (appendReplacement); não são coleções de domínio.

---

## 6. Possíveis melhorias futuras

- **Testes unitários**: JUnit para Fracao (construtor, adicionar, MDC), parseInteiros/somaInteiros, verificarPA/verificarPG, capturarCodigo, extrairExpressaoDoLoop e analisarCodigo com trechos fixos.
- **Reuso de captura de código**: lógica de "FIM ou duas linhas vazias" compartilhada com lista1treino (ex.: utilitário em pacote comum) se o projeto crescer.
- **PG com razão não inteira**: verificarPG atual só considera razão inteira; para sequências como 2, 3, 4.5 seria necessário uso de double ou Fracao e comparação com tolerância ou exata.
- **Javadoc**: documentar classes e métodos públicos de dominio e dos pontos de entrada.

---

*Refatoração alinhada aos critérios do laboratório e lista1treino: clareza, responsabilidade única, fail-fast onde aplicável, sem streams/coleções no núcleo do processamento e código preparado para manutenção de longo prazo.*
