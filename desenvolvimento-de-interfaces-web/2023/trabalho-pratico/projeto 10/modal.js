(function () {
    "use strict";

    const API_COLECOES = "https://jsonserver-trabalhopratico2.joaovitor479.repl.co/colecoes";
    const API_EXEMPLOS = "https://jsonserver-trabalhopratico2.joaovitor479.repl.co/exemplos";

    function escapeHtml(str) {
        if (str == null) return "";
        const div = document.createElement("div");
        div.textContent = String(str);
        return div.innerHTML;
    }

    function criarCardColecao(colecao) {
        const container = document.getElementById("colecoesContainer");
        if (!container) return;

        const divCol = document.createElement("div");
        divCol.className = "col-md-3";

        const divCard = document.createElement("div");
        divCard.className = "card";

        const img = document.createElement("img");
        img.src = escapeHtml(colecao.foto);
        img.className = "card-img-top";
        img.alt = escapeHtml(colecao.nome);
        img.loading = "lazy";

        const divCardBody = document.createElement("div");
        divCardBody.className = "card-body";

        const h5 = document.createElement("h5");
        h5.className = "card-title";
        h5.textContent = colecao.nome;

        const p = document.createElement("p");
        p.className = "card-text";
        p.textContent = colecao.informacoes;

        const btnVisualizacao = document.createElement("button");
        btnVisualizacao.type = "button";
        btnVisualizacao.className = "btn btn-outline-primary";
        btnVisualizacao.textContent = "Visualização Rápida";
        btnVisualizacao.setAttribute("data-bs-toggle", "modal");
        btnVisualizacao.setAttribute("data-bs-target", "#modal" + colecao.id);

        const btnVejaMais = document.createElement(colecao.nome === "Pinturas" ? "a" : "button");
        if (colecao.nome === "Pinturas") {
            btnVejaMais.href = "https://collections.louvre.fr/en/recherche?typology%5B0%5D=22";
            btnVejaMais.setAttribute("target", "_blank");
            btnVejaMais.setAttribute("rel", "noopener noreferrer");
        } else {
            btnVejaMais.type = "button";
        }
        btnVejaMais.className = "btn btn-outline-primary";
        btnVejaMais.textContent = "Veja Mais";

        divCardBody.appendChild(h5);
        divCardBody.appendChild(p);
        divCardBody.appendChild(btnVisualizacao);
        divCardBody.appendChild(document.createElement("br"));
        divCardBody.appendChild(document.createElement("br"));
        divCardBody.appendChild(btnVejaMais);

        divCard.appendChild(img);
        divCard.appendChild(divCardBody);
        divCol.appendChild(divCard);
        container.appendChild(divCol);
    }

    function criarModal(exemplo, index, total) {
        const id = "modal" + (index + 1);
        const divModal = document.createElement("div");
        divModal.className = "modal fade";
        divModal.id = id;
        divModal.setAttribute("tabindex", "-1");
        divModal.setAttribute("aria-labelledby", id + "Label");
        divModal.setAttribute("aria-hidden", "true");

        const divDialog = document.createElement("div");
        divDialog.className = "modal-dialog modal-dialog-centered";

        const divContent = document.createElement("div");
        divContent.className = "modal-content modal-visualizacao";

        const header = document.createElement("div");
        header.className = "modal-header modal-visualizacao__header";
        const title = document.createElement("h1");
        title.className = "modal-title modal-visualizacao__title";
        title.id = id + "Label";
        title.textContent = exemplo.nome;
        const btnClose = document.createElement("button");
        btnClose.type = "button";
        btnClose.className = "btn-close modal-visualizacao__close";
        btnClose.setAttribute("data-bs-dismiss", "modal");
        btnClose.setAttribute("aria-label", "Fechar");
        header.appendChild(title);
        header.appendChild(btnClose);

        const body = document.createElement("div");
        body.className = "modal-body modal-visualizacao__body";
        const wrapImg = document.createElement("div");
        wrapImg.className = "modal-visualizacao__img-wrap";
        const img = document.createElement("img");
        img.src = escapeHtml(exemplo.foto);
        img.className = "modal-visualizacao__img";
        img.alt = escapeHtml(exemplo.nome);
        wrapImg.appendChild(img);
        body.appendChild(wrapImg);
        const wrapDesc = document.createElement("div");
        wrapDesc.className = "modal-visualizacao__desc";
        const labelDesc = document.createElement("span");
        labelDesc.className = "modal-visualizacao__label";
        labelDesc.textContent = "Descrição";
        const pInfo = document.createElement("p");
        pInfo.className = "modal-visualizacao__texto";
        pInfo.textContent = exemplo.informacoes || "";
        const pFonte = document.createElement("p");
        pFonte.className = "modal-visualizacao__fonte";
        const small = document.createElement("small");
        small.className = "text-body-secondary";
        small.textContent = exemplo.fonte || "";
        pFonte.appendChild(small);
        wrapDesc.appendChild(labelDesc);
        wrapDesc.appendChild(pInfo);
        wrapDesc.appendChild(pFonte);
        body.appendChild(wrapDesc);

        const footer = document.createElement("div");
        footer.className = "modal-footer modal-visualizacao__footer";
        if (index < total - 1) {
            const btnProx = document.createElement("button");
            btnProx.className = "btn btn-primary";
            btnProx.setAttribute("data-bs-target", "#modal" + (index + 2));
            btnProx.setAttribute("data-bs-toggle", "modal");
            btnProx.setAttribute("data-bs-dismiss", "modal");
            btnProx.textContent = "Próximo";
            footer.appendChild(btnProx);
        } else {
            const btnInicio = document.createElement("button");
            btnInicio.className = "btn btn-primary";
            btnInicio.setAttribute("data-bs-target", "#modal1");
            btnInicio.setAttribute("data-bs-toggle", "modal");
            btnInicio.setAttribute("data-bs-dismiss", "modal");
            btnInicio.textContent = "Voltar ao Início";
            footer.appendChild(btnInicio);
        }
        const btnFechar = document.createElement("button");
        btnFechar.type = "button";
        btnFechar.className = "btn btn-secondary";
        btnFechar.setAttribute("data-bs-dismiss", "modal");
        btnFechar.textContent = "Fechar";
        footer.appendChild(btnFechar);

        divContent.appendChild(header);
        divContent.appendChild(body);
        divContent.appendChild(footer);
        divDialog.appendChild(divContent);
        divModal.appendChild(divDialog);
        document.body.appendChild(divModal);
    }

    function adicionarCardsManuais() {
        const container = document.getElementById("colecoesContainer");
        if (!container) return;

        const cards = [
            { nome: "Esculturas", info: "Nesta coleção o Louvre abriga uma impressionante variedade de esculturas que abrangem séculos de história e arte.", img: "imgobra1.jpg" },
            { nome: "Antiguidades Egípcias", info: "Esta coleção é uma das mais extensas e impressionantes de seu tipo, abrangendo uma vasta gama de objetos egípcios fora do Egito.", img: "imgobra2.jpg" },
            { nome: "Arte Grega e Romana", info: "Uma impressionante coleção de arte grega e romana que destaca a excelência artística e a influência duradoura dessas antigas civilizações.", img: "imgobra3.jpg" },
            { nome: "Arte Asiática", info: "O Louvre também é conhecido por sua rica e diversificada coleção de arte asiática.", img: "imgobra13.jpg" },
            { nome: "Arte Islâmica", info: "A coleção de arte islâmica do Louvre abriga uma notável coleção que destaca a rica herança cultural e artística do mundo muçulmano.", img: "imgobra4.jpg" },
            { nome: "Arte Decorativa", info: "O Museu do Louvre possui uma impressionante coleção de arte decorativa que abrange uma ampla variedade de estilos e períodos.", img: "imgobra5.jpg" },
            { nome: "Artes Gráficas", info: "O Louvre também possui uma extensa e significativa coleção de artes gráficas.", img: "imgobra15.jpg" },
            { nome: "Joias da Coroa", info: "O museu exibe uma impressionante e excepcional coleção de Joias da Coroa.", img: "imgobra14.jpg" },
            { nome: "Arte da Idade Média", info: "O Louvre é um tesouro de arte medieval que abriga uma vasta coleção de obras representativas da Idade Média.", img: "imgobra6.jpg" },
            { nome: "Arte Decorativa Francesa", info: "A marca da arte decorativa francesa, que abrange séculos de criação artística e design de interiores na França.", img: "imgobra7.jpg" },
            { nome: "Arte do Oriente Médio e Norte da África", info: "O Louvre possui uma coleção de arte do Oriente Médio e do Norte da África.", img: "imgobra8.jpg" },
            { nome: "Arte Africana, Americana e do Pacífico", info: "O museu também exibe nesta coleção artefatos e obras de arte de culturas indígenas da África, Américas e região do Pacífico.", img: "imgobra9.jpg" },
            { nome: "Numismática", info: "O Museu do Louvre abriga uma notável coleção de Numismática, que é o estudo e a coleção de moedas e medalhas.", img: "imgobra10.jpg" },
            { nome: "Instrumentos Musicais", info: "O Louvre possui uma coleção fascinante de Instrumentos Musicais que oferece uma oportunidade única de explorar a história da música.", img: "imgobra11.jpg" },
            { nome: "Arte Contemporânea", info: "O Louvre também apresenta obras de arte contemporânea em exposições temporárias.", img: "imgobra12.jpg" }
        ];

        cards.forEach(function (c) {
            const col = document.createElement("div");
            col.className = "col-md-3";
            const card = document.createElement("div");
            card.className = "card";
            const img = document.createElement("img");
            img.src = c.img;
            img.className = "card-img-top";
            img.alt = c.nome;
            img.loading = "lazy";
            const body = document.createElement("div");
            body.className = "card-body";
            const title = document.createElement("h5");
            title.className = "card-title";
            title.textContent = c.nome;
            const text = document.createElement("p");
            text.className = "card-text";
            text.textContent = c.info;
            const btnV = document.createElement("button");
            btnV.type = "button";
            btnV.className = "btn btn-outline-primary";
            btnV.setAttribute("data-bs-toggle", "modal");
            btnV.setAttribute("data-bs-target", "#exampleModal");
            btnV.textContent = "Visualização Rápida";
            const btnM = document.createElement("button");
            btnM.type = "button";
            btnM.className = "btn btn-outline-primary";
            btnM.textContent = "Veja Mais";
            body.appendChild(title);
            body.appendChild(text);
            body.appendChild(btnV);
            body.appendChild(document.createElement("br"));
            body.appendChild(document.createElement("br"));
            body.appendChild(btnM);
            card.appendChild(img);
            card.appendChild(body);
            col.appendChild(card);
            container.appendChild(col);
        });
    }

    function isOffline() {
        return window.PortalOffline && window.PortalOffline.isOfflineMode();
    }

    var FALLBACK_COLECOES = [
        { id: 1, nome: "Pinturas", informacoes: "Conhecida principalmente por sua vasta coleção de pinturas, esta é uma das coleções mais famosas do Louvre que abrange séculos de história da arte. As pinturas expostas no Louvre representam uma ampla variedade de estilos, períodos históricos e movimentos artísticos.", foto: "imgobra16.jpg" }
    ];
    var FALLBACK_EXEMPLOS = [
        { index: 0, nome: "Mona Lisa", informacoes: "Mona Lisa (\"Senhora Lisa\") também conhecida como A Gioconda ou Mona Lisa del Giocondo é a mais notável e conhecida obra de Leonardo da Vinci, um dos mais eminentes homens do Renascimento italiano.", fonte: "Fonte: Wikipédia. 24 de set. de 2023", foto: "img1pg2.jpg" },
        { index: 1, nome: "A Liberdade guiando o povo", informacoes: "A Liberdade guiando o povo (em francês: La Liberté guidant le peuple) é uma pintura de Eugène Delacroix em comemoração à Revolução de Julho de 1830. Uma mulher representando a Liberdade guia o povo por cima dos corpos dos derrotados, empunhando a bandeira tricolor da Revolução francesa.", fonte: "Fonte: Wikipédia. 24 de set. de 2023", foto: "img2pg2.jpg" },
        { index: 2, nome: "A Balsa da Medusa", informacoes: "Balsa da Medusa (em francês: Le Radeau de la Méduse) é uma pintura a óleo executada entre 1818 e 1819 pelo pintor Théodore Géricault. Está exposta no Museu do Louvre, Paris, e é considerada um ícone da pintura ocidental.", fonte: "Fonte: Wikipédia. 24 de set. de 2023", foto: "img3pg2.jpg" }
    ];

    function runWithFallback() {
        FALLBACK_COLECOES.forEach(criarCardColecao);
        FALLBACK_EXEMPLOS.forEach(function (ex, i) { criarModal(ex, i, FALLBACK_EXEMPLOS.length); });
        adicionarCardsManuais();
    }

    function init() {
        if (isOffline()) {
            runWithFallback();
            return;
        }
        (async function () {
            try {
                var resCol = await fetch(API_COLECOES);
                var resEx = await fetch(API_EXEMPLOS);
                if (!resCol.ok || !resEx.ok) throw new Error("Falha ao carregar dados.");
                var colecoesData = await resCol.json();
                var exemplosData = await resEx.json();
                if (Array.isArray(colecoesData) && colecoesData.length > 0 && exemplosData) {
                    colecoesData.forEach(criarCardColecao);
                    var exemplos = Array.isArray(exemplosData) ? exemplosData : [exemplosData];
                    var ordenados = exemplos.slice().sort(function (a, b) { return (a.index || 0) - (b.index || 0); });
                    ordenados.forEach(function (ex, i) { criarModal(ex, i, ordenados.length); });
                    adicionarCardsManuais();
                } else {
                    runWithFallback();
                }
            } catch (err) {
                console.error("Erro ao carregar dados:", err);
                runWithFallback();
            }
        })();
    }

    if (document.readyState === "loading") {
        document.addEventListener("DOMContentLoaded", init);
    } else {
        init();
    }
})();
