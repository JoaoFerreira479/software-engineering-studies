# Bancos de Dados — 2024

## Estrutura do projeto
- **`listas/`** — PostgreSQL (modelo veículo/proprietário)
  - schema_veiculo_proprietario.sql
  - lista6.sql, lista7.sql, lista8.sql
- **`trabalho-pratico/`** — SQL Server (Jogos Olímpicos)
  - criacao_banco_tabelas.sql
  - indices_views.sql
  - consultas_olimpiadas.sql

## Organização do código
- **listas** — DDL (proprietario, veiculo com PKs, FKs, CHECKs, índices); lista6 = consultas básicas e DML; lista7 = JOINs e agregações; lista8 = subconsultas, CTEs, funções de janela (ROW_NUMBER). Ordem: schema → lista6 → lista7 → lista8.
- **trabalho-pratico** — DDL do banco JogosOlimpicos e tabelas (comitês, estádios, atletas, árbitros, esportes, eventos, etc.); índices e views (v_Atletas_ComiteNacional, v_Eventos_Estadio, v_Eventos_ComiteInternacional); consultas sobre o modelo. Ordem: criacao_banco_tabelas → indices_views → consultas_olimpiadas.

## Como compilar e executar
- **Listas (PostgreSQL):** conecte no banco (ex.: psql, DBeaver, pgAdmin). Execute na ordem: schema_veiculo_proprietario.sql, lista6.sql, lista7.sql, lista8.sql.
- **Trabalho prático (SQL Server):** conecte na instância (SSMS, Azure Data Studio, VS Code). Execute criacao_banco_tabelas.sql, depois indices_views.sql, depois consultas_olimpiadas.sql. Se as tabelas estiverem vazias, os SELECTs podem retornar 0 linhas.

Não há compilação; só execução de scripts SQL no cliente do SGBD.

## Possíveis melhorias
- Scripts de carga de dados de exemplo para listas e TP.
- Documentar modelo ER ou diagrama das tabelas.
- Testes automatizados (diff de resultados ou testes contra o banco).
- Portar um contexto para o outro SGBD para comparação.
