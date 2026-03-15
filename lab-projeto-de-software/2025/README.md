# Laboratório de Projeto de Software — 2025

## Estrutura do projeto
Pastas com espaço no nome (no Windows use aspas nos caminhos):

- **`trabalho prático 1/`** — **matriculas/** (pom.xml, src/main/java/com/universidade/matriculas, src/test)
- **`trabalho prático 2/`** — **aluguelcarros/** (pom.xml, src/main/java/com/universidade/aluguelcarros)
- **`trabalho prático 3/`** — **moedaestudantil/** (backend API) e **front/** (HTML, JS, api.js, auth.js, login.html, register.html, páginas de envio de moedas, vantagens, extratos, styles.css)

## Organização do código
- **TP1 (matriculas)** — Spring Boot console: MatriculasApplication; ConsoleRunner (menus); MenuLoginHandler, MenuAlunoHandler, MenuProfessorHandler, MenuSecretariaHandler; services, repositories, model (Usuario, Aluno, Professor, Curso, Disciplina, Matricula, etc.); application (RegrasMatricula, NotificadorCobranca); ui; exception; dto. H2 em memória.
- **TP2 (aluguelcarros)** — API REST: controllers (Usuario, Cliente, Empresa, Agente, Automovel, Pedido, Contrato, etc.); services; persistence (JPA); domain. H2; SpringDoc OpenAPI (Swagger).
- **TP3 (moedaestudantil)** — API REST com JWT: AuthController, AlunoController, ProfessorController, InstituicaoEnsinoController, EmpresaParceiraController, VantagemController, TransacaoController; config.security (JWT, TokenService); service; repository; model; dto. MySQL.
- **TP3 (front)** — HTML/JS que consomem a API: login, registro, envio de moedas, cadastro/listagem/resgate de vantagens, extratos (aluno e professor). api.js e auth.js para chamadas HTTP e token; configurar URL da API.

## Como compilar e executar
- **TP1:** `cd "trabalho prático 1/matriculas"` → `mvn spring-boot:run`. Usar o console para os menus.
- **TP2:** `cd "trabalho prático 2/aluguelcarros"` → `mvn spring-boot:run`. Acessar API e Swagger (ex.: /swagger-ui.html) no navegador ou Postman.
- **TP3 backend:** configurar MySQL em moedaestudantil/src/main/resources/application.properties. `cd "trabalho prático 3/moedaestudantil"` → `mvn spring-boot:run`.
- **TP3 front:** abrir os .html em **trabalho prático 3/front/** no navegador (ou servidor local). Ajustar URL da API em api.js/auth.js para o endereço do backend.

## Possíveis melhorias
- Ampliar testes automatizados (unitários e integração) nos três projetos.
- Documentar endpoints do TP3 e fluxo JWT no README.
- Docker Compose para MySQL (e opcionalmente backends) no TP3.
- CORS explícito no backend do TP3 para a origem do front quando servido localmente.
