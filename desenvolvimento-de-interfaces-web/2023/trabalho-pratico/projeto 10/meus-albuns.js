(function () {
    "use strict";

    var STORAGE_KEY = "portalMeusAlbuns";

    function getAlbunsSalvos() {
        try {
            var raw = localStorage.getItem(STORAGE_KEY);
            return raw ? JSON.parse(raw) : [];
        } catch (e) {
            return [];
        }
    }

    function escapeHtml(str) {
        if (str == null) return "";
        var div = document.createElement("div");
        div.textContent = String(str);
        return div.innerHTML;
    }

    function criarCard(album) {
        var col = document.createElement("div");
        col.className = "col";

        var card = document.createElement("div");
        card.className = "card card-museu meus-albuns-card";

        var img = document.createElement("img");
        img.src = escapeHtml(album.foto);
        img.className = "card-img-top";
        img.alt = escapeHtml(album.nome);
        img.loading = "lazy";

        var body = document.createElement("div");
        body.className = "card-body";

        var title = document.createElement("h5");
        title.className = "card-title";
        title.textContent = album.nome;

        var local = document.createElement("p");
        local.className = "card-text card-museu__local meus-albuns-local";
        local.textContent = album.localizacao || "";

        var link = document.createElement("a");
        link.href = escapeHtml(album.link || "album.html");
        link.className = "btn btn-outline-primary btn-museu";
        link.textContent = "Explorar";

        body.appendChild(title);
        body.appendChild(local);
        body.appendChild(link);
        card.appendChild(img);
        card.appendChild(body);
        col.appendChild(card);
        return col;
    }

    function renderizar() {
        var container = document.getElementById("listaAlbuns");
        var vazio = document.getElementById("listaAlbunsVazia");
        if (!container || !vazio) return;

        var albuns = getAlbunsSalvos();
        container.innerHTML = "";

        if (albuns.length === 0) {
            container.classList.add("visually-hidden");
            vazio.classList.remove("visually-hidden");
            return;
        }

        container.classList.remove("visually-hidden");
        vazio.classList.add("visually-hidden");
        albuns.forEach(function (album) {
            container.appendChild(criarCard(album));
        });
    }

    if (document.readyState === "loading") {
        document.addEventListener("DOMContentLoaded", renderizar);
    } else {
        renderizar();
    }
})();
