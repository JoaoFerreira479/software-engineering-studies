(function () {
    "use strict";

    const STORAGE_KEY = "portalMeusAlbuns";
    const API_DESTAQUES = "https://jsonserver-trabalhopratico2.joaovitor479.repl.co/destaques";

    function getAlbunsSalvos() {
        try {
            var raw = localStorage.getItem(STORAGE_KEY);
            return raw ? JSON.parse(raw) : [];
        } catch (e) {
            return [];
        }
    }

    function setAlbunsSalvos(arr) {
        try {
            localStorage.setItem(STORAGE_KEY, JSON.stringify(arr));
        } catch (e) {
            console.warn("Erro ao salvar em localStorage:", e);
        }
    }

    function albumJaSalvo(id) {
        return getAlbunsSalvos().some(function (a) { return a.id === id; });
    }

    document.addEventListener("DOMContentLoaded", function () {
        var btn = document.getElementById("salvarNoCarrossel");
        if (!btn) return;

        function atualizarEstado() {
            var favorito = albumJaSalvo(1);
            btn.textContent = favorito ? "Remover este Álbum" : "Salvar este Álbum";
            btn.classList.toggle("favorito", favorito);
        }

        function favoritarApi(id, status) {
            return fetch(API_DESTAQUES + "/" + id, {
                method: "PATCH",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ favorito: status })
            }).then(function (r) {
                if (!r.ok) throw new Error("Erro na operação.");
            });
        }

        function salvarNoCarrossel() {
            var img = document.getElementById("imagemLouvre");
            var titulo = document.getElementById("louvre-titulo");
            var favorito = btn.classList.contains("favorito");

            var album = {
                id: 1,
                nome: (titulo && titulo.textContent) ? titulo.textContent.trim() : "Museu do Louvre",
                foto: (img && img.src) ? img.src : "imglouvre.jpg",
                localizacao: "Paris, França",
                link: "album.html"
            };

            var list = getAlbunsSalvos();
            if (favorito) {
                list = list.filter(function (a) { return a.id !== album.id; });
                setAlbunsSalvos(list);
                favoritarApi(album.id, false).catch(function () {});
            } else {
                list = list.filter(function (a) { return a.id !== album.id; });
                list.push(album);
                setAlbunsSalvos(list);
                favoritarApi(album.id, true).catch(function () {});
            }
            atualizarEstado();
        }

        atualizarEstado();
        btn.addEventListener("click", salvarNoCarrossel);
    });
})();
