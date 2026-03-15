(function () {
    "use strict";

    const API_URL = "https://jsonserver-trabalhopratico2.joaovitor479.repl.co/card";

    var FALLBACK_FIRST_CARD = {
        id: 1,
        nome: "Museu do Louvre",
        localizacao: "Paris, França",
        foto: "imgparis.jpg"
    };

    var OTHER_CARDS = [
        { id: 2, nome: "Museu Britânico", localizacao: "Londres, Reino Unido", foto: "imglondres.jpg", site: "https://www.britishmuseum.org/" },
        { id: 3, nome: "Museu Metropolitano de Arte (Met)", localizacao: "Nova York, EUA", foto: "imgny.jpg", site: "https://www.metmuseum.org/" },
        { id: 4, nome: "Museu Hermitage", localizacao: "São Petersburgo, Rússia", foto: "imgrussia.jpg", site: "https://www.hermitagemuseum.org/" },
        { id: 5, nome: "Museu do Vaticano", localizacao: "Cidade do Vaticano", foto: "imgvaticano.jpg", site: "https://www.museivaticani.va/" },
        { id: 6, nome: "Museu Nacional da China", localizacao: "Pequim, China", foto: "imgchina.jpg", site: "https://en.chmuseum.cn/" },
        { id: 7, nome: "Museu Nacional de Tóquio", localizacao: "Tóquio, Japão", foto: "imgjapao.jpg", site: "https://www.tnm.jp/" },
        { id: 8, nome: "Museu de Arte Moderna (MoMA)", localizacao: "Nova York, EUA", foto: "imgny2.jpg", site: "https://www.moma.org/" },
        { id: 9, nome: "Museu Nacional de Antropologia", localizacao: "Cidade do México, México", foto: "imgmexico.jpg", site: "https://www.inah.gob.mx/museos/museo-nacional-de-antropologia" },
        { id: 10, nome: "Museu de Arte de São Paulo (MASP)", localizacao: "São Paulo, Brasil", foto: "imgsp.jpg", site: "https://masp.org.br/" },
        { id: 11, nome: "Museu Egípcio", localizacao: "Cairo, Egito", foto: "imgegito.jpg", site: "https://egyptianmuseum.gov.eg/" },
        { id: 12, nome: "Museu de Arte Contemporânea de Los Angeles (MOCA)", localizacao: "Los Angeles, EUA", foto: "imglosangeles.jpg", site: "https://moca.org/" },
        { id: 13, nome: "Museu Nacional do Prado", localizacao: "Madri, Espanha", foto: "imgmadri.jpg", site: "https://www.museodelprado.es/" },
        { id: 14, nome: "Museu de Arte de Chicago", localizacao: "Chicago, EUA", foto: "imgchicago.jpg", site: "https://www.artic.edu/" },
        { id: 15, nome: "Museu Nacional de Arte da Catalunha", localizacao: "Barcelona, Espanha", foto: "imgbarcelona.jpg", site: "https://www.museunacional.cat/en" },
        { id: 16, nome: "Museu Nacional de Arte de Seul", localizacao: "Seul, Coreia do Sul", foto: "imgcoreia.jpg", site: "https://www.museum.go.kr/site/eng/home" },
        { id: 17, nome: "Museu de História Natural de Londres", localizacao: "Londres, Reino Unido", foto: "imglondres2.jpg", site: "https://www.nhm.ac.uk/" },
        { id: 18, nome: "Museu de Arte de Hong Kong", localizacao: "Hong Kong, China", foto: "imgchina2.jpg", site: "https://hk.art.museum/en_US/web/ma/home.html" },
        { id: 19, nome: "Museu Nacional de Arte Moderna de Tóquio (MOMAT)", localizacao: "Tóquio, Japão", foto: "imgjapao2.jpg", site: "https://www.momat.go.jp/english/" },
        { id: 20, nome: "Museu de Arte Moderna de São Francisco (SFMOMA)", localizacao: "São Francisco, EUA", foto: "imgsaofran.jpg", site: "https://www.sfmoma.org/" }
    ];

    function escapeHtml(str) {
        if (str == null) return "";
        var div = document.createElement("div");
        div.textContent = String(str);
        return div.innerHTML;
    }

    function createCard(cardData, withLink) {
        var col = document.createElement("div");
        col.className = "col-md-3";

        var card = document.createElement("div");
        card.className = "card card-museu";

        var img = document.createElement("img");
        img.src = escapeHtml(cardData.foto);
        img.className = "card-img-top";
        img.alt = escapeHtml(cardData.nome);
        img.loading = "lazy";

        var body = document.createElement("div");
        body.className = "card-body";

        var title = document.createElement("h5");
        title.className = "card-title";
        title.textContent = cardData.nome;

        var text = document.createElement("p");
        text.className = "card-text card-museu__local";
        text.textContent = cardData.localizacao;

        body.appendChild(title);
        body.appendChild(text);

        var siteUrl = (cardData.site || cardData.url || "").replace(/[<>"']/g, "");
        if (withLink) {
            var link = document.createElement("a");
            link.href = "album.html";
            link.className = "btn btn-outline-primary btn-museu";
            link.textContent = "Explorar";
            body.appendChild(link);
        } else if (siteUrl) {
            var link = document.createElement("a");
            link.href = siteUrl;
            link.className = "btn btn-outline-primary btn-museu";
            link.textContent = "Explorar";
            link.setAttribute("target", "_blank");
            link.setAttribute("rel", "noopener noreferrer");
            body.appendChild(link);
        } else {
            var btn = document.createElement("button");
            btn.type = "button";
            btn.className = "btn btn-outline-primary btn-museu";
            btn.textContent = "Explorar";
            body.appendChild(btn);
        }

        card.appendChild(img);
        card.appendChild(body);
        col.appendChild(card);
        return col;
    }

    function renderCards(data, onlyFirstWithLink) {
        var container = document.getElementById("cardContainer");
        if (!container) return;
        data.forEach(function (cardData, index) {
            var withLink = onlyFirstWithLink ? index === 0 : false;
            container.appendChild(createCard(cardData, withLink));
        });
    }

    function isOffline() {
        return window.PortalOffline && window.PortalOffline.isOfflineMode();
    }

    function init() {
        var container = document.getElementById("cardContainer");
        if (!container) return;

        if (isOffline()) {
            renderCards([FALLBACK_FIRST_CARD], true);
            renderCards(OTHER_CARDS, false);
            return;
        }

        fetch(API_URL)
            .then(function (response) {
                if (!response.ok) throw new Error("Falha ao carregar dados.");
                return response.json();
            })
            .then(function (data) {
                renderCards(data, true);
                renderCards(OTHER_CARDS, false);
            })
            .catch(function (err) {
                console.error("Erro ao carregar os dados:", err);
                renderCards([FALLBACK_FIRST_CARD], true);
                renderCards(OTHER_CARDS, false);
            });
    }

    if (document.readyState === "loading") {
        document.addEventListener("DOMContentLoaded", init);
    } else {
        init();
    }
})();
