# Refatoração nivelamento — Decisões e Relatório

## 1. Escopo

Pasta **nivelamento**: 14 classes de exercícios (recursividade, condicionais, repetição, vetores/matrizes, modularização). Todas refatoradas com os mesmos critérios de clareza, fail-fast, try-with-resources e, onde aplicável, ausência de uso de `Arrays` como parte central do problema.

---

## 2. Decisões arquiteturais

### 2.1 Try-with-resources em todos os pontos de entrada

- **Antes**: `Scanner` criado no `main` e fechado em `finally`.
- **Depois**: `try (Scanner scanner = new Scanner(System.in)) { ... }` em todos os arquivos que usam `Scanner`. Garante fechamento mesmo em exceção e reduz boilerplate.

### 2.2 Tratamento de exceção mais específico

- **Antes**: `catch (Exception e)` genérico, às vezes com mensagem única.
- **Depois**: onde faz sentido, `InputMismatchException` para entrada inválida e `IllegalArgumentException` para regra de negócio (valor fora do intervalo, denominador zero, etc.). Facilita testes e diagnóstico.

### 2.3 Fail-fast em validações de domínio

- **RestoRecursivo**: denominador ≤ 0 passa a lançar `IllegalArgumentException` em vez de imprimir mensagem e dar `return`.
- **SerieRecursiva**, **SomaRecursivaVetor**, **SomaSerie**: N (ou tamanho) ≤ 0 rejeitado com exceção ou loop de leitura até valor válido.
- **RemoverElementoVetor**: tamanho do vetor ≤ 0 lança exceção; índice inválido apenas exibe mensagem e continua (comportamento interativo preservado).
- **CompararMatrizes**: `mesmasDimensoes` e `saoIguais` com checagem de null e matriz vazia para evitar NPE.

### 2.4 OrdenarTresNumeros sem Arrays.sort / Arrays.toString

- **Antes**: `Arrays.sort(numeros)` e `Arrays.toString(numeros)`.
- **Depois**: ordenação por comparações (três trocas) em **ordenarTres(int a, int b, int c)** e formatação em **formatarVetor(int[])**. Mantém o foco em “ordenar três números” sem depender de `Arrays` como parte central da solução.

### 2.5 Constantes e nomenclatura

- **ParOuImpar**: constantes `QUANTIDADE_NUMEROS` e `LIMITE_SUPERIOR` em vez de literais no `main`.
- **RemoverElementoVetor**: constante `INDICE_ENCERRAR = -1`.
- **CompararMatrizes**: `validarDimensoes` renomeado para **mesmasDimensoes**; **compararMatrizes** para **saoIguais** (intenção mais clara).

### 2.6 Classes finais e construtor privado

- Todas as classes de aplicação são **final** e têm **construtor privado**, evitando extensão e instanciação indevida.

### 2.7 Guardas de null e pré-condições

- **ContarDigitos**: `contarDigitos` exige número não negativo; 0 continua retornando 1 dígito.
- **IndicesParesRecursivo**: `imprimirIndicesPares` e `exibirVetor` verificam `vetor == null`.
- **SomaDiagonalPrincipal**: valida matriz não nula, quadrada e linhas consistentes; elementos da matriz podem ser qualquer inteiro (leitura com `lerInteiro`), dimensão N com `lerInteiroPositivo`.
- **VetoresOperacoesM**: `calcularESomarVetores` e `calcularEProdutoVetores` validam vetores não nulos e mesmo tamanho; **preencherVetor** trata `InputMismatchException` e repete a leitura do elemento.
- **AvaliacaoNota**: `classificarNota` valida intervalo e pode ser reutilizada com fail-fast.

### 2.8 RemoverElementoVetor: critério de “já removido”

- **Antes**: condição `vetor[indice] == 0` para “elemento já removido”, o que confunde valor 0 com estado.
- **Depois**: apenas índice dentro dos limites; remoção sempre gera novo vetor sem aquele índice. Comportamento mais previsível.

---

## 3. Principais problemas encontrados na versão anterior

| Problema | Onde | Impacto |
|----------|------|---------|
| **Scanner sem try-with-resources** | Todos os 14 arquivos | Risco de não fechar recurso em exceção. |
| **catch (Exception e)** | Maioria | Oculta tipo de falha; difícil testar e manter. |
| **Arrays.sort e Arrays.toString** | OrdenarTresNumeros | Uso de API de ordenação/formação como núcleo do exercício; substituído por comparações e formatação manual. |
| **Validação com return em vez de exceção** | RestoRecursivo, RemoverElementoVetor | Fluxo silencioso; fail-fast preferível onde é erro de uso. |
| **N/tamanho sem validação** | SomaRecursivaVetor (n=0 ou negativo) | Pode levar a vetor de tamanho negativo ou lógica confusa. |
| **validarDimensoes sem null/empty** | CompararMatrizes | Possível NPE ou índice 0 inválido. |
| **preencherVetor sem tratamento de entrada** | VetoresOperacoesM | InputMismatchException não tratada; dado inválido no vetor. |
| **vetor[indice] == 0 como “removido”** | RemoverElementoVetor | Semântica ambígua com o valor 0. |
| **Elementos da matriz só positivos** | SomaDiagonalPrincipal | Restrição desnecessária para elementos; mantida apenas para dimensão N. |

---

## 4. Ganhos técnicos

- **Recursos**: fechamento garantido do `Scanner` em todos os fluxos.
- **Previsibilidade**: validações com exceção onde é erro de contrato; mensagens objetivas.
- **Testabilidade**: métodos públicos (contarDigitos, ordenarTres, calcularResto, calcularSoma, classificarNota, etc.) com pré-condições claras e sem dependência de I/O.
- **Consistência**: padrão único (try-with-resources, final, exceções específicas, constantes nomeadas).
- **Conformidade**: OrdenarTresNumeros deixa de depender de `Arrays.sort`/`Arrays.toString` como parte central.

---

## 5. Justificativa das estruturas de dados

- **Arrays de primitivos (int[], int[][])**: usados em todo o nivelamento para vetores e matrizes; são a estrutura natural do exercício e não substituem coleções da JDK. Sem uso de `ArrayList`, `List`, etc.
- **Ordenação de três números**: três comparações e trocas em array de 3 posições; O(1); evita `Arrays.sort` e mantém o exercício focado em lógica de comparação.
- **Formatação de vetor**: `StringBuilder` e loop para produzir `[a, b, c]` em vez de `Arrays.toString`.

---

## 6. Possíveis melhorias futuras

- **Utilitário de leitura**: extrair `lerInteiro`, `lerInteiroPositivo`, `lerNota` para uma classe compartilhada (ex.: `nivelamento.util.Leitura`) para reduzir duplicação entre os 14 arquivos.
- **Testes unitários**: JUnit para métodos públicos (contarDigitos, ordenarTres, calcularResto, calcularSoma, calcularSerie, classificarNota, removerElemento, mesmasDimensoes, saoIguais, etc.).
- **Javadoc**: documentar classes e métodos públicos para contrato e exceções.

---

*Refatoração alinhada aos critérios das pastas laboratorio, lista1treino e lista2treino: try-with-resources, fail-fast, exceções específicas, sem uso de Arrays como núcleo onde o exercício é a lógica (OrdenarTresNumeros), classes final e código preparado para manutenção.*
