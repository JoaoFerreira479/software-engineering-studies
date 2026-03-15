(function () {
    "use strict";

    const JSON_URL = "https://jsonserver-trabalhopratico2.joaovitor479.repl.co/carrossel";

    var FALLBACK_CARROSSEL = [
        { nome: "Rijksmuseum", localizacao: "Amsterdã, Holanda", foto: "img1carrossel.jpg", site: "https://www.rijksmuseum.nl/en" },
        { nome: "Museum Island", localizacao: "Berlim, Alemanha", foto: "img2carrossel.jpg", site: "https://www.smb.museum/en/" },
        { nome: "Museu da Acrópole de Atenas", localizacao: "Atenas, Grécia", foto: "img3carrossel.jpg", site: "https://www.theacropolismuseum.gr/en" }
    ];

    function escapeHtml(str) {
        if (str == null) return "";
        var div = document.createElement("div");
        div.textContent = String(str);
        return div.innerHTML;
    }

    function populateCarousel(data) {
        var indicatorsContainer = document.getElementById("carousel-indicators");
        var innerContainer = document.getElementById("carousel-inner");
        if (!indicatorsContainer || !innerContainer) return;

        data.forEach(function (item, index) {
            var indicator = document.createElement("button");
            indicator.type = "button";
            indicator.setAttribute("data-bs-target", "#carouselExampleCaptions");
            indicator.setAttribute("data-bs-slide-to", String(index));
            if (index === 0) indicator.classList.add("active");
            indicator.setAttribute("aria-label", "Slide " + (index + 1));
            indicatorsContainer.appendChild(indicator);

            var carouselItem = document.createElement("div");
            carouselItem.className = "carousel-item" + (index === 0 ? " active" : "");

            var img = document.createElement("img");
            img.src = escapeHtml(item.foto);
            img.className = "d-block w-100";
            img.alt = escapeHtml(item.nome) + " - " + escapeHtml(item.localizacao);

            var caption = document.createElement("div");
            caption.className = "carousel-caption carousel-caption--custom";
            var h5 = document.createElement("h5");
            h5.textContent = item.nome;
            var p = document.createElement("p");
            p.className = "carousel-caption__local";
            p.textContent = item.localizacao;
            caption.appendChild(h5);
            caption.appendChild(p);
            var link = document.createElement("a");
            link.href = (item.site || item.url || "album.html").replace(/[<>"']/g, "");
            link.className = "carousel-caption__btn";
            link.textContent = "Explorar";
            if (item.site || item.url) {
                link.setAttribute("target", "_blank");
                link.setAttribute("rel", "noopener noreferrer");
            }
            caption.appendChild(link);

            carouselItem.appendChild(img);
            carouselItem.appendChild(caption);
            innerContainer.appendChild(carouselItem);
        });
    }

    function isOffline() {
        return window.PortalOffline && window.PortalOffline.isOfflineMode();
    }

    function init() {
        if (isOffline()) {
            populateCarousel(FALLBACK_CARROSSEL);
            return;
        }
        fetch(JSON_URL)
            .then(function (response) {
                if (!response.ok) throw new Error("Falha ao carregar carrossel.");
                return response.json();
            })
            .then(function (data) { populateCarousel(data); })
            .catch(function (err) {
                console.error("Erro ao carregar dados do carrossel:", err);
                populateCarousel(FALLBACK_CARROSSEL);
            });
    }

    if (document.readyState === "loading") {
        document.addEventListener("DOMContentLoaded", init);
    } else {
        init();
    }
})();
