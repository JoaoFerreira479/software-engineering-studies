(function () {
    "use strict";

    const API_LOUVRE = "https://95879502-68e4-4e1a-b675-0c5f019c1c30-00-1914xl53ww80c.riker.replit.dev/louvre";

    var FALLBACK_LOUVRE = {
        nome: "Museu do Louvre",
        informacoes: "O Museu do Louvre, localizado em Paris, França, é um dos museus mais famosos e visitados do mundo, sendo o maior museu de arte do mundo e um monumento histórico. Fundado em 1793, o Louvre está localizado na margem direita do rio Sena, no 1º arrondissement da cidade. É conhecido por sua vasta coleção de arte e antiguidades, que abrangem desde a Antiguidade até o século XIX. São aproximadamente mais de 38 mil objetos exibidos em uma área de 72.735 metros quadrados. Em 2019, o Louvre recebeu 9,6 milhões de visitantes, o que o torna o museu mais visitado do mundo.",
        fonte: "Fonte: Wikipédia.",
        geolocalizacao: "Geolocalização: 48°51'38.3\"N 2°20'14.7\"E",
        data: "24 de set. de 2023",
        foto: "imglouvre.jpg"
    };

    function exibirDados(item) {
        if (!item) return;

        function setText(id, text) {
            var el = document.getElementById(id);
            if (el) el.textContent = text != null ? String(text) : "";
        }
        function setSrc(id, src) {
            var el = document.getElementById(id);
            if (el && src != null) el.src = String(src);
        }

        setSrc("imagemLouvre", item.foto);
        setText("louvre-titulo", item.nome);
        setText("informacoesLouvre", item.informacoes);
        setText("fonteLouvre", item.fonte);
        setText("geolocalizacaoLouvre", item.geolocalizacao);
        setText("dataLouvre", item.data);
    }

    function mostrarErro() {
        var card = document.querySelector("#sobre .card-body");
        if (!card) return;
        var p = document.createElement("p");
        p.className = "album-sobre__erro";
        p.setAttribute("role", "alert");
        p.textContent = "Não foi possível carregar as informações do Louvre. Ative o modo Offline no canto da tela para ver os dados locais.";
        card.insertBefore(p, card.firstChild);
    }

    function isOffline() {
        return window.PortalOffline && window.PortalOffline.isOfflineMode();
    }

    function init() {
        if (isOffline()) {
            exibirDados(FALLBACK_LOUVRE);
            return;
        }

        fetch(API_LOUVRE)
            .then(function (response) {
                if (!response.ok) throw new Error("Falha ao carregar dados.");
                return response.json();
            })
            .then(function (data) {
                var item = Array.isArray(data) ? data[0] : data;
                exibirDados(item);
            })
            .catch(function (err) {
                console.error("Erro ao carregar os dados:", err);
                mostrarErro();
                exibirDados(FALLBACK_LOUVRE);
            });
    }

    if (document.readyState === "loading") {
        document.addEventListener("DOMContentLoaded", init);
    } else {
        init();
    }
})();
