# Desenvolvimento de Interfaces Web — 2023

## Estrutura do projeto
- **`projetos/`** — projeto 1 (primeiro site) a projeto 9 (js 3); cada pasta com index.html, CSS e, a partir do 4, app.js quando aplicável. Projeto 2 tem subpastas Flex/, Grid/, Menu Mobile/.
- **`trabalho-pratico/`** — projeto 10 (portal de álbuns geolocalizados): pasta **projeto 10/** com index.html, meus-albuns.html, album.html, styles.css, carrossel.js, card.js, modal.js, map.js, meus-albuns.js, album.js, config.example.js, imagens, etc.; e README.md do TP.

## Organização do código
- **projetos 1–9** — front estático por semana: HTML semântico, CSS (Flexbox/Grid no 2), Bootstrap no 3, galeria com lightbox no 4, currículo no 6, formulário e validação JS no 7, fases da Lua (SunCalc) no 8, JS no 9. Cada um abre pelo index.html da pasta (no projeto 2, abrir o index de Flex, Grid ou Menu Mobile).
- **projeto 10** — multipágina: carrossel, mapa Mapbox, cards, modais; scripts separados (carrossel, card, modal, map, meus-albuns, album, salvo, offline). Token Mapbox em config; servidor local recomendado para fetch e mapa.

## Como compilar e executar
- Não há build. Abra o **index.html** da pasta do projeto no navegador (duplo clique ou “Abrir com”). Projeto 2: abra o index dentro de Flex/, Grid/ ou Menu Mobile/.
- **Projeto 10:** configurar token Mapbox (config.example.js). Preferir servidor local (Live Server no VS Code, `python -m http.server 8080` ou `npx serve .` na pasta projeto 10) para evitar CORS e restrições com file://.

## Possíveis melhorias
- Testes automatizados (Jest ou ferramentas de acessibilidade).
- Documentar formato do JSON do carrossel e variáveis de config (Mapbox, API).
- Build mínimo (bundler) se o número de scripts do projeto 10 crescer.
- Modo escuro ou tema configurável.
