# Laboratório de Programação Modular — 2024

## Estrutura do projeto
- **`listas/`** — exercícios Java (sem Maven): **src/** (module-info.java + pacotes), README, .gitignore. Pacotes: programarcomputadoresbasicos, programarcomputadoresalternativasdecisao, programarcomputadoresvariaveisdados, programarcomputadoresrepeticao, programarcomputadoresideiasedesafios, programarcomputadoresarquivos, algoritmoknn, auladenivelamento, desafioemsala, lab/listas (infrastructure/io, banco).
- **`trabalho prático/`** — pasta com espaço no nome: **restaurant/** (Spring Boot: pom.xml, src/main, src/test, target)
- **feliz.csv**, **300 ideias.pdf** na raiz de 2024

## Organização do código
- **listas** — cada pacote tem classes com `main` para rodar no console. programarcomputadores* = básicos, condicionais, variáveis, repetição, ideias/desafios, arquivos; lab.listas.infrastructure.io = Console, EntradaUtil (I/O único); lab.listas.banco = SistemaBancario (Conta, Cliente, Banco); algoritmoknn = KnnExample, dataset CSV; auladenivelamento = PesquisaConsumoApp; desafioemsala = RegistroManutencoes. Sem pom.xml; compilar/rodar pela IDE (source root = listas/src) ou javac/java.
- **restaurant (trabalho prático)** — Spring Boot 3, API REST: FoodApplication; controllers (Restaurante, Mesa, Cardapio, Pedido, Pagamento, Cliente, FilaDeEspera, Requisicao); services; repositories; models; DTO; DataInitializer. PostgreSQL (ou H2 em perfil). Testes em src/test.

## Como compilar e executar
- **Listas:** marque **listas/src** como Sources Root na IDE e execute a classe com `main` do exercício (ex.: programarcomputadoresbasicos.Menu, lab.listas.banco.SistemaBancario, algoritmoknn.KnnExample). Ou no terminal a partir de listas/src, compilando e rodando por pacote (incluir lab.listas.infrastructure.io no classpath quando necessário).
- **Trabalho prático:** em **trabalho prático/restaurant** (no Windows use aspas: `cd "trabalho prático/restaurant"`) executar `mvn spring-boot:run`. JDK 17, Maven e PostgreSQL configurados (ou H2 no application.properties).

## Possíveis melhorias
- Testes automatizados (JUnit) para exercícios selecionados das listas.
- Perfil H2 ou Docker Compose para o PostgreSQL do TP.
- Documentar endpoints da API do restaurant (OpenAPI/Swagger) ou lista de URLs no README.
