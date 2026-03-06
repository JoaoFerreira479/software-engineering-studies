# Refatoração lista1treino — Decisões e Relatório

## 1. Escopo

- **ExerciciosPotLogFpFtCompleto.java**: exercícios de potências, logaritmos, piso e teto.
- **ContadorDeOperacoesDinamicoNovo.java**: análise de código Java (entrada padrão) com contagem de operadores e estruturas.

---

## 2. Decisões arquiteturais

### 2.1 ExerciciosPotLogFpFtCompleto

- **Classe final e construtor privado**: impede extensão e instanciação; uso apenas estático.
- **Validação fail-fast**: `potencia(base, expoente)` exige valores finitos; `log10Seguro(x)` exige x > 0 e finito, evitando NaN/Infinity silenciosos.
- **Delegação explícita**: cálculos encapsulados em `potencia` e `log10Seguro`, permitindo testes unitários e reuso.
- **Sem coleções**: dados em arrays de primitives/double[][]; apenas `Math` (permitido) e formatação com `printf`.

### 2.2 ContadorDeOperacoesDinamicoNovo e CatalogoOperacoes

- **Substituição de Map por estrutura própria**: o contador usava `LinkedHashMap<String, String>` para (padrão regex → descrição). Como o domínio central é “catálogo de operações” e a restrição proíbe uso de HashMap/LinkedHashMap como estrutura principal, foi criada a classe **CatalogoOperacoes**:
  - Armazena pares (padrão, descrição) em array de tamanho fixo.
  - Ordem de inserção preservada (equivalente ao LinkedHashMap).
  - API mínima: `tamanho()`, `padrao(i)`, `descricao(i)`, `paraCada(Consumer)`.
- **Sem System.exit(0)**: entrada vazia é tratada com `return` no `main`; o fluxo termina de forma previsível e testável.
- **Try-with-resources**: `Scanner` criado e fechado no `main` com try-with-resources, garantindo fechamento mesmo em exceção.
- **Separação de responsabilidades**: `ContadorDeOperacoesDinamicoNovo` cuida de I/O, captura, limpeza de linhas e validação; **AnalisadorOperacoes** recebe o catálogo e o código e apenas conta e imprime. `CatalogoOperacoes` só guarda e percorre os pares.
- **Métodos estáticos testáveis**: `capturarCodigo`, `removerNumerosDeLinha`, `validarCodigoJava`, `contarOcorrencias` são package-private ou públicos onde faz sentido, permitindo testes unitários sem I/O.
- **Remoção de números de linha**: reimplementada com `substring` e `proximaQuebra` para não depender de `String.split("\n")` em loops (menos alocações para entradas grandes); uso de `split` continua permitido, mas a versão atual evita array intermediário de linhas.

### 2.3 Restrições respeitadas

- **Sem ArrayList, LinkedList, HashMap, HashSet, Queue, Deque, Stack, TreeMap, TreeSet** no domínio central: catálogo de operações implementado com array de pares.
- **Uso permitido**: String, Scanner, Objects, Optional (não usado aqui), Exception, StringBuilder, regex via `String.matches`/`split`, Math.

---

## 3. Principais problemas encontrados na versão anterior

| Problema | Onde | Impacto |
|----------|------|---------|
| **Uso de LinkedHashMap** | ContadorDeOperacoesDinamicoNovo | Viola restrição de não usar coleções prontas como estrutura central do problema. |
| **System.exit(0)** | capturarCodigo | Dificulta testes e integração; encerramento brusco do processo. |
| **Scanner não fechado em cenário de exceção** | main | Uso de try-finally manual; try-with-resources é mais seguro e legível. |
| **Ausência de validação em Math** | ExerciciosPotLogFpFtCompleto | Log de zero ou negativo gera NaN; potência com infinito pode gerar resultados pouco claros. |
| **Classe OperacoesAnalise acoplada a Map** | OperacoesAnalise | Dificulta troca da estrutura de armazenamento e testes com catálogos diferentes. |
| **contarOcorrencias sem guard para null/vazio** | OperacoesAnalise | texto.split(regex) com texto null lança NPE; texto vazio retorna 1 e length-1=0, mas contrato não estava explícito. |

---

## 4. Ganhos técnicos

- **Conformidade com restrições**: catálogo de operações sem Map; apenas estruturas permitidas.
- **Testabilidade**: métodos estáticos e AnalisadorOperacoes injetando catálogo permitem testes sem Scanner nem System.in/out.
- **Robustez**: validação em potência e log; `contarOcorrencias` com cheque para null/vazio; Scanner fechado corretamente.
- **Clareza**: fluxo único de saída no main (sem System.exit); responsabilidades bem delimitadas (captura, limpeza, validação, análise).
- **Manutenção**: adicionar nova operação é apenas mais um `Par` em `CatalogoOperacoes.preencherOperacoes`.

---

## 5. Justificativa da estrutura de dados (CatalogoOperacoes)

- **Array de pares (Par[])**: o catálogo é fixo após construção, só leitura e iteração. Não é necessário acesso por chave (regex), apenas ordem fixa para relatório. Array com pares (padrao, descricao) atende com O(1) por índice e iteração O(n).
- **Sem Map**: evita HashMap/TreeMap conforme restrição; ordem de inserção preservada com índice de inserção no array.
- **Classe interna Par**: encapsula o par (padrão, descrição); não exposta na API pública do catálogo.

---

## 6. Possíveis melhorias futuras

- **Testes unitários**: JUnit para `potencia`, `log10Seguro`, `removerNumerosDeLinha`, `validarCodigoJava`, `contarOcorrencias`, `AnalisadorOperacoes` com catálogo mock.
- **Configuração do catálogo**: ler padrões/descrições de arquivo ou constantes em classe separada, em vez de hard-coded em `preencherOperacoes`.
- **Regex mais robustas**: alguns padrões (ex.: `-`, `<`) podem dar falsos positivos em literais ou comentários; considerar ignorar comentários e strings antes de contar.
- **Encoding na entrada**: garantir charset (ex.: UTF-8) ao ler do console, se necessário.
- **Javadoc**: documentar todas as classes e métodos públicos.

---

*Refatoração alinhada aos mesmos critérios do laboratório: clareza, responsabilidade única, fail-fast, sem coleções prontas na estrutura central e código preparado para manutenção de longo prazo.*
