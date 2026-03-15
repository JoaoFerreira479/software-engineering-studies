# Arquitetura de Computadores — 2022

## Estrutura do projeto
- **`logisim/`** — Relatório 1.circ, Relatório 2.circ, Relatório 3.circ, Relatório 4.circ
- **`construtor-virtual-097/`** — Circuito 1.txt a Circuito 12.txt
- Sem código em linguagem de programação; só projetos de circuito para simuladores.

## Organização do código
Não há código executável. O conteúdo é:
- **Logisim (.circ)** — circuitos que ilustram identidades da álgebra booleana, portas (NOT, AND, OR), fios, entradas e saídas. Formato XML do Logisim.
- **Construtor Virtual (.txt)** — cada arquivo é um circuito (versão 0.9.7); cabeçalho e dados de componentes/conexões. A “lógica” está na descrição do circuito que o simulador interpreta.

## Como compilar e executar
- **Logisim:** abra [Logisim](http://www.cburch.com/logisim/) ou [Logisim Evolution](https://github.com/logisim-evolution/logisim-evolution). File → Open e escolha um `.circ`. Altere entradas (Poke Tool) e observe as saídas.
- **Construtor Virtual:** abra o simulador na versão 0.9.7. Carregue um dos arquivos `Circuito 1.txt` … `Circuito 12.txt` e simule no próprio software.

Não há compilação; a validação é manual e visual (tabela-verdade, identidades booleanas).

## Possíveis melhorias
- Documentar cada circuito (objetivo, tabela-verdade ou identidade).
- Incluir screenshots ou diagramas no README.
- Exportar/descrever circuitos do Construtor em formato mais aberto se o software deixar de ser disponível.
