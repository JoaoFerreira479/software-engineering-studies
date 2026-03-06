# Refatoração do Laboratório — Decisões e Relatório

## 1. Decisões arquiteturais

### 1.1 Reorganização em pacotes
- **`laboratorio.estruturas`**: todas as estruturas de dados (listas, filas, pilhas, árvore). Responsabilidade única: armazenamento e operações estruturais.
- **`laboratorio.dominio`**: entidades de negócio imutáveis (Pessoa, Livro, Contato) e contratos (Falante). Sem I/O; apenas estado e regras de validação.
- **`laboratorio.algoritmos`**: algoritmos puros (Fibonacci, VetorUtil, Ordenacao). Sem estado; métodos estáticos com entrada/saída explícita.
- **`laboratorio`**: classes Guia (orquestração, I/O, demonstração). As Guias usam as estruturas e o domínio; não implementam estruturas nem regras complexas.

### 1.2 Composição em vez de herança (Guia7)
- **Antes**: Pilha e Fila herdavam de Lista; isso viola Liskov (uma Fila não é um tipo de Lista com remoção no início).
- **Depois**: Pilha e Fila **contêm** uma `ListaSequencial` e delegam push/pop e enqueue/dequeue. Cada tipo tem uma única responsabilidade e comportamento previsível.

### 1.3 Estruturas sem efeitos colaterais de I/O
- **Antes**: FilaEncadeada (Guia8), Lista2/Lista3 (Guia9/10) faziam `System.out.println` em inserir/remover, acoplando estrutura a console.
- **Depois**: estruturas só alteram estado; quem chama decide se e como imprimir. Facilita testes e reuso.

### 1.4 Domínio imutável e validado
- Pessoa, Livro, Contato: campos `final`, construtores que validam (nome não nulo, idade/renda/id não negativos). Evita estados inválidos e NPE.
- Falante: interface com getNome() e getFala(); Homem, Cao, Gato como implementações imutáveis (sem setFala), preferindo composição em vez de herança profunda.

### 1.5 Fail-fast e validação
- Entrada nula ou inválida gera `IllegalArgumentException` ou `IndexOutOfBoundsException` no ponto de uso, com mensagem clara.
- Ex.: `ListaSequencial.inserirNaPosicao` valida posição e null; `VetorUtil.validarVetor` exige vetor não nulo e não vazio; `Fibonacci.gerar` exige quantidade > 0.

### 1.6 Encapsulamento da árvore (Guia13)
- **Antes**: classe `No` pública com campos expostos; `ArvoreBinaria` com métodos que imprimem diretamente.
- **Depois**: `NoArvore` é classe privada estática interna de `ArvoreBinariaBusca`; API retorna `Optional<Contato>` em `buscar`; listagem feita por callback `emOrdem(Consumer<Contato>)`. I/O fica na Guia13.

### 1.7 Sem uso de coleções prontas (restrição do escopo)
- Onde a estrutura é central ao exercício, não se usa ArrayList, LinkedList, HashMap, Queue, Stack, etc.
- Implementações próprias: `ListaSequencial`, `ListaEncadeada`, `ListaEncadeadaSentinela`, `ListaDuplamenteEncadeada`, `FilaEncadeada`, `PilhaEncadeada`, `ListaOrdenada`, `ArvoreBinariaBusca`.
- Uso permitido: String, Scanner, Objects, Optional, OptionalInt, Exception, IOException, Random, Math, Files/BufferedReader para I/O básico.

---

## 2. Principais problemas encontrados na versão anterior

| Problema | Onde | Impacto |
|----------|------|--------|
| **Pilha/Fila por herança de Lista** | Guia7 | Violação de Liskov; exposição de métodos de lista (inserirNaPosicao, etc.) em Pilha/Fila. |
| **somaIterativa() esvaziava a pilha** | Guia11 (Pilha1) | Bug crítico: após somar, a pilha ficava vazia. Comportamento inesperado e não reversível. |
| **Uso de Arrays.stream/toArray** | Guia1 | Foge à restrição de não usar coleções/streams quando a operação é central (filtrar divisíveis, somar). |
| **ArrayList/List em Guia3, Guia4, Guia12** | Guia3, 4, 12 | Uso de listas prontas onde a lista é parte do problema; substituído por estruturas próprias. |
| **Side effects nas estruturas** | Guia8, 9, 10 | `System.out` em enfileirar/desenfileirar, inserir/remover; difícil testar e reutilizar. |
| **Double.parseDouble(toString())** | Guia9 (Lista2) | Frágil e dependente do formato de toString; trocado por tratamento explícito de Number. |
| **Nó público na árvore** | Guia13 | Vazamento de detalhe de implementação; árvore e nós encapsulados na refatoração. |
| **buscar() com void e impressão** | Guia13 | Acoplamento com console; retorno Optional<Contato> deixa a decisão de uso para o chamador. |
| **Matriz jagged não tratada** | Guia2 | transposta assumia matriz retangular; adicionada verificação e falha explícita. |
| **produtoRecursivo com b negativo** | Guia2 | Comportamento indefinido; adicionada validação. |

---

## 3. Ganhos técnicos obtidos

- **Testabilidade**: estruturas sem I/O e com contratos claros permitem testes unitários sem mocks de System.out.
- **Reuso**: ListaSequencial, FilaEncadeada, ListaOrdenada, etc. podem ser usadas em outros contextos sem alteração.
- **Previsibilidade**: validação e fail-fast reduzem estados inválidos e erros silenciosos.
- **Manutenção**: pacotes e responsabilidades claras (estruturas vs domínio vs algoritmos vs Guias).
- **Correção de bugs**: soma na pilha sem esvaziar; produto recursivo com b negativo; matriz jagged.
- **Consistência**: nomenclatura (tamanho(), estaVazia(), obter(i)), uso de Optional onde “pode não existir”, imutabilidade onde faz sentido.
- **Extensibilidade**: novas estruturas ou novos algoritmos podem ser adicionados nos pacotes correspondentes sem invadir as Guias.

---

## 4. Justificativa técnica das estruturas de dados

- **ListaSequencial (array)**: capacidade fixa, acesso O(1) por índice, inserção/remoção no meio O(n). Adequada para Guia7 (lista, pilha, fila por composição) e para Guia3/4 (quantidade limitada de elementos).
- **ListaEncadeada (singly linked)**: crescimento limitado só pela memória; inserção no início/fim O(1); remoção no fim O(n) sem referência dupla. Usada em Guia9 para exercitar encadeamento e soma iterativa/recursiva.
- **ListaEncadeadaSentinela**: nó sentinela no início elimina caso especial na inserção/remoção no início; simplifica código e reduz erros em listas encadeadas (Guia10).
- **ListaDuplamenteEncadeada**: percurso em ambas as direções e remoção no fim O(1); sentinela no início unifica tratamento (Guia11).
- **FilaEncadeada**: FIFO com frente e traseira; enfileirar/desenfileirar O(1); sem limite de tamanho (Guia8).
- **PilhaEncadeada**: LIFO com topo; empilhar/desempilhar O(1); soma sem desempilhar (percurso por referência) preserva estado (Guia11).
- **ListaOrdenada (array + inserção ordenada)**: mantém ordem natural; busca binária O(log n); inserção O(n) por deslocamento; evita ArrayList/Collections (Guia12).
- **ArvoreBinariaBusca**: busca/inserção/remoção por nome em O(h); ordem por nome; encapsulamento do nó e retorno Optional na busca (Guia13).

---

## 5. Possíveis melhorias futuras

- **Testes unitários**: JUnit para cada estrutura (inserir/remover/obter, casos de borda e exceções) e para algoritmos (Fibonacci, VetorUtil, Ordenacao).
- **ListaSequencial expansível**: hoje capacidade fixa; poderia dobrar o array quando cheio (como em ListaOrdenada), com fator de crescimento configurável.
- **Iterator/Stream nas estruturas**: métodos `iterator()` ou `stream()` para integração com for-each e APIs que consomem Iterable, sem expor nós.
- **Contrato de igualdade em Contato**: implementar `equals`/`hashCode` em Contato para uso em estruturas que dependam de igualdade (ex.: evitar duplicatas por nome).
- **Caminho do arquivo agenda configurável**: Guia13 usar propriedade ou argumento de linha de comando para o path do arquivo, em vez de path fixo.
- **Tratamento de encoding**: especificar charset (ex.: UTF-8) na leitura de `agenda.txt` para nomes com acentos.
- **Documentação Javadoc**: cobrir todas as classes e métodos públicos com descrição, parâmetros e exceções.

---

*Refatoração realizada com foco em clareza, responsabilidade única, fail-fast, imutabilidade onde aplicável e ausência de coleções prontas nas estruturas centrais.*
