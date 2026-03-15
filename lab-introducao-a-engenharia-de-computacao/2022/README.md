# Laboratório de Introdução à Engenharia de Computação — 2022

## Estrutura do projeto
- **`tinkercad/`** — relatório 1 a relatório 8 (cada um com PDFs, PNGs, listas CSV e, quando houver, código `_*_codigo_arduino.ino`) e **trabalho-pratico/** (fechadura eletrônica: PDF, PNG, lista de componentes, _tp_codigo_arduino.ino)
- **`logisim/`** — Relatório 1.circ, Relatório 2.circ

Sem build de software; circuitos e sketches para simuladores.

## Organização do código
- **Tinkercad** — sketches Arduino (.ino): setup(), loop(), pinMode, digitalWrite, analogRead, Serial, tone, etc. Relatórios 1–8: LEDs, resistência/tensão, relé, semáforo, LED RGB e botões, serial, monitor de nível (LDR, buzzer), sensor de distância. Trabalho-pratico: fechadura com senha e servo. PDFs e CSVs são registro (descrição, lista de componentes).
- **Logisim** — circuitos digitais (.circ): identidades da álgebra booleana, portas lógicas. Simulação visual (alterar entradas e observar saídas).

## Como compilar e executar
- **Arduino (.ino):** no [Tinkercad](https://www.tinkercad.com/) — criar/abrir circuito com Arduino, colar o código do .ino, rodar a simulação (Play). Ou na Arduino IDE (placa real): abrir o .ino, selecionar placa e porta, upload. Não é preciso compilar à parte no PC.
- **Logisim (.circ):** abrir [Logisim](http://www.cburch.com/logisim/) ou [Logisim Evolution](https://github.com/logisim-evolution/logisim-evolution). File → Open e escolher Relatório 1.circ ou Relatório 2.circ. Poke Tool para alterar entradas e ver saídas.

## Possíveis melhorias
- Documentar pinos e componentes por relatório em uma tabela no README.
- Índice dos arquivos .ino por relatório para localização rápida.
- Links ou instruções para importar circuito Tinkercad (se exportável).
