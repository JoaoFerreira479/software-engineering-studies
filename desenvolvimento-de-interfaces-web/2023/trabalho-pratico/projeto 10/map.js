(function () {
    "use strict";

    if (typeof mapboxgl === "undefined") return;

    const CENTRO = [-43.9397233, -19.9332786];

    const MUSEUS = [
        { id: 1, descricao: "Museu do Louvre - França", endereco: "Rue de Rivoli, 75001 Paris, França", cidade: "Paris", latlong: [2.337628, 48.860593], url: "https://www.louvre.fr", cor: "red" },
        { id: 2, descricao: "Museu Britânico - Reino Unido", endereco: "Great Russell St, Bloomsbury, London WC1B 3DG, Reino Unido", cidade: "Londres", latlong: [-0.126168, 51.518757], url: "https://www.britishmuseum.org", cor: "red" },
        { id: 3, descricao: "Museu Metropolitano de Arte (Met) - Estados Unidos", endereco: "1000 5th Ave, New York, NY 10028, Estados Unidos", cidade: "Nova York", latlong: [-73.963402, 40.779434], url: "https://www.metmuseum.org", cor: "red" },
        { id: 4, descricao: "Museu Hermitage - Rússia", endereco: "Palace Square, 2, São Petersburgo, Rússia, 190000", cidade: "São Petersburgo", latlong: [30.317846, 59.941065], url: "https://www.hermitagemuseum.org", cor: "red" },
        { id: 5, descricao: "Museu do Vaticano - Cidade do Vaticano", endereco: "Viale Vaticano, 00165 Città del Vaticano, Cidade do Vaticano", cidade: "Cidade do Vaticano", latlong: [12.453641, 41.906487], url: "http://www.museivaticani.va", cor: "red" },
        { id: 6, descricao: "Museu Nacional da China - China", endereco: "16 E Chang an Ave, Dongcheng Qu, Beijing Shi, China, 100006", cidade: "Pequim", latlong: [116.400894, 39.905159], url: "http://en.chnmuseum.cn", cor: "red" },
        { id: 7, descricao: "Museu Nacional de Tóquio - Japão", endereco: "13-9 Uenokoen, Taito City, Tokyo 110-8712, Japão", cidade: "Tóquio", latlong: [139.7722, 35.718], url: "https://www.tnm.jp", cor: "red" },
        { id: 8, descricao: "Museu de Arte Moderna (MoMA) - Estados Unidos", endereco: "11 W 53rd St, New York, NY 10019, Estados Unidos", cidade: "Nova York", latlong: [-73.9777, 40.7615], url: "https://www.moma.org", cor: "red" },
        { id: 9, descricao: "Museu Nacional de Antropologia - México", endereco: "Av. Paseo de la Reforma s/n, Polanco, Bosque de Chapultepec I Secc, 11560 Ciudad de México, CDMX, México", cidade: "Cidade do México", latlong: [-99.18659, 19.42613], url: "https://www.mna.inah.gob.mx", cor: "red" },
        { id: 10, descricao: "Museu de Arte de São Paulo (MASP) - Brasil", endereco: "Av. Paulista, 1578 - Bela Vista, São Paulo - SP, 01310-200, Brasil", cidade: "São Paulo", latlong: [-46.6558, -23.5615], url: "https://masp.org.br", cor: "red" },
        { id: 11, descricao: "Museu Egípcio - Egito", endereco: "Tahrir Square Rd, Tahrir Square, Egypt", cidade: "Cairo", latlong: [31.2333, 30.042], url: "https://www.egyptianmuseum.gov.eg", cor: "red" },
        { id: 12, descricao: "Museu de Arte Contemporânea de Los Angeles (MOCA) - Estados Unidos", endereco: "250 S Grand Ave, Los Angeles, CA 90012, Estados Unidos", cidade: "Los Angeles", latlong: [-118.2505, 34.0536], url: "https://www.moca.org", cor: "red" },
        { id: 13, descricao: "Museu Nacional do Prado - Espanha", endereco: "Calle de Ruiz de Alarcón, 23, 28014 Madrid, Espanha", cidade: "Madrid", latlong: [-3.6921, 40.4138], url: "https://www.museodelprado.es", cor: "red" },
        { id: 14, descricao: "Museu de Arte de Chicago - Estados Unidos", endereco: "111 S Michigan Ave, Chicago, IL, 60603", cidade: "Chicago", latlong: [-87.623177, 41.881832], url: "https://www.artic.edu", cor: "red" },
        { id: 15, descricao: "Museu Nacional de Arte da Catalunha - Espanha", endereco: "Parque de Montjuic, s/n, 08038 Barcelona", cidade: "Barcelona", latlong: [2.15348, 41.36839], url: "https://www.museunacional.cat/en", cor: "red" },
        { id: 16, descricao: "Museu Nacional de Arte de Seul - Coreia do Sul", endereco: "30, Samcheong-ro, Jongno-gu, Seoul, 03062", cidade: "Seul", latlong: [126.98, 37.5785], url: "https://www.mmca.go.kr/eng/", cor: "red" },
        { id: 17, descricao: "Museu de História Natural de Londres - Reino Unido", endereco: "Cromwell Rd, South Kensington, London SW7 5BD, Reino Unido", cidade: "Londres", latlong: [-0.17639, 51.49611], url: "https://www.nhm.ac.uk", cor: "red" },
        { id: 18, descricao: "Museu de Arte de Hong Kong - China", endereco: "10 Salisbury Rd, Tsim Sha Tsui, Hong Kong", cidade: "Hong Kong", latlong: [114.17165277777778, 22.29376388888889], url: "https://hk.art.museum", cor: "red" },
        { id: 19, descricao: "Museu Nacional de Arte Moderna de Tóquio (MOMAT) - Japão", endereco: "3 Chome-1 Kitanomarukoen, Chiyoda City, Tokyo 102-8322, Japão", cidade: "Tóquio", latlong: [139.75472, 35.69056], url: "https://www.momat.go.jp/english/", cor: "red" },
        { id: 20, descricao: "Museu de Arte Moderna de São Francisco (SFMOMA) - Estados Unidos", endereco: "151 3rd St, San Francisco, CA 94103, Estados Unidos", cidade: "São Francisco", latlong: [-122.401, 37.7858], url: "https://www.sfmoma.org", cor: "red" },
        { id: 21, descricao: "Rijksmuseum - Holanda", endereco: "Museumstraat 1, 1071 XX Amsterdam, Holanda", cidade: "Amsterdã", latlong: [4.8852, 52.36], url: "https://www.rijksmuseum.nl/en", cor: "red" },
        { id: 22, descricao: "Museum Island (Ilha dos Museus) - Alemanha", endereco: "Bodestraße 1-3, 10178 Berlin, Alemanha", cidade: "Berlim", latlong: [13.4013, 52.517], url: "https://www.smb.museum/en/", cor: "red" },
        { id: 23, descricao: "Museu da Acrópole de Atenas - Grécia", endereco: "Dionysiou Areopagitou 15, Athens 11742, Grécia", cidade: "Atenas", latlong: [23.7284, 37.9685], url: "https://www.theacropolismuseum.gr/en", cor: "red" }
    ];

    function escapeHtml(str) {
        if (str == null) return "";
        const div = document.createElement("div");
        div.textContent = String(str);
        return div.innerHTML;
    }

    function criarPopupHtml(m) {
        const desc = escapeHtml(m.descricao);
        const end = escapeHtml(m.endereco);
        const cid = escapeHtml(m.cidade);
        const url = escapeHtml(m.url);
        return `<h3><a href="${url}" target="_blank" rel="noopener noreferrer">${desc}</a></h3><br>${end}<br>${cid}`;
    }

    function initMapa() {
        const container = document.getElementById("map");
        if (!container) return;

        const token = typeof window !== "undefined" && window.MAPBOX_ACCESS_TOKEN ? window.MAPBOX_ACCESS_TOKEN : "";
        if (!token) {
            container.innerHTML = "<p class=\"mapa-erro\">Para exibir o mapa, crie o ficheiro <code>config.js</code> (a partir de <code>config.example.js</code>) e defina o seu token Mapbox. Não suba <code>config.js</code> ao GitHub.</p>";
            return;
        }
        mapboxgl.accessToken = token;

        const map = new mapboxgl.Map({
            container: "map",
            style: "mapbox://styles/mapbox/streets-v12",
            center: CENTRO,
            zoom: 9
        });

        map.addControl(new mapboxgl.NavigationControl(), "top-right");

        MUSEUS.forEach(function (m) {
            const popup = new mapboxgl.Popup({ offset: 25 }).setHTML(criarPopupHtml(m));
            new mapboxgl.Marker({ color: m.cor })
                .setLngLat(m.latlong)
                .setPopup(popup)
                .addTo(map);
        });

        function marcarLocalizacaoUsuario(pos) {
            const popup = new mapboxgl.Popup({ offset: 25 }).setHTML("<h3>Esta é a sua localização atual!</h3>");
            new mapboxgl.Marker({ color: "yellow" })
                .setLngLat([pos.coords.longitude, pos.coords.latitude])
                .setPopup(popup)
                .addTo(map);
        }

        function mostrarErroLocalizacao() {
            const el = document.getElementById("mapa-erro");
            if (el) {
                el.textContent = "Não foi possível obter sua localização. Você pode usar o mapa normalmente.";
                el.classList.remove("visually-hidden");
            }
        }

        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(marcarLocalizacaoUsuario, mostrarErroLocalizacao, { timeout: 10000 });
        }
    }

    if (document.readyState === "loading") {
        document.addEventListener("DOMContentLoaded", initMapa);
    } else {
        initMapa();
    }
})();
