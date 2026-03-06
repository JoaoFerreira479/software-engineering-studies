/**
 * Receitas Halloween - Listagem e detalhes em modal.
 * Carrega data.json, renderiza cards com escape HTML (XSS) e usa <dialog> acessível.
 */

(function () {
    "use strict";

    const SELECTORS = {
        container: "#card-container",
        erro: "#erro-carregamento",
        loading: "#estado-carregando",
        modal: "#modal-receita",
        modalTitulo: "#modal-titulo",
        modalDescricao: "#modal-descricao",
        modalIngredientes: "#modal-ingredientes",
        modalPreparo: "#modal-preparo",
        modalFechar: "#modal-fechar",
    };

    /** Escapa texto para uso seguro em atributos HTML (prevenção de XSS). */
    function escapeHtml(text) {
        if (text == null) return "";
        const div = document.createElement("div");
        div.textContent = String(text);
        return div.innerHTML;
    }

    /** Adiciona item à lista usando textContent (seguro). */
    function appendListItem(parent, text) {
        const li = document.createElement("li");
        li.textContent = text == null ? "" : String(text);
        parent.appendChild(li);
    }

    /** Cria elemento de card; dados já validados como objeto. */
    function criarCard(card, index) {
        const article = document.createElement("article");
        article.className = "card";
        article.setAttribute("role", "listitem");
        article.dataset.index = String(index);

        const img = document.createElement("img");
        img.className = "card__img";
        img.src = escapeHtml(card.imagem);
        img.alt = escapeHtml(card.titulo);
        img.loading = "lazy";
        article.appendChild(img);

        const body = document.createElement("div");
        body.className = "card__body";

        const title = document.createElement("h2");
        title.className = "card__title";
        title.textContent = card.titulo;
        body.appendChild(title);

        const desc = document.createElement("p");
        desc.className = "card__text";
        desc.textContent = card.descricao;
        body.appendChild(desc);

        const fonte = document.createElement("p");
        fonte.className = "card__fonte";
        fonte.textContent = `Fonte: ${card.fonte}`;
        body.appendChild(fonte);

        const btn = document.createElement("button");
        btn.type = "button";
        btn.className = "btn btn--primary";
        btn.textContent = "Detalhes";
        btn.setAttribute("data-index", String(index));
        body.appendChild(btn);

        article.appendChild(body);
        return article;
    }

    /** Abre o modal com os dados da receita (conteúdo sempre via textContent). */
    function abrirModal(receita) {
        const modal = document.querySelector(SELECTORS.modal);
        const tituloEl = document.querySelector(SELECTORS.modalTitulo);
        const descricaoEl = document.querySelector(SELECTORS.modalDescricao);
        const ingredientesEl = document.querySelector(SELECTORS.modalIngredientes);
        const preparoEl = document.querySelector(SELECTORS.modalPreparo);

        if (!modal || !tituloEl || !descricaoEl || !ingredientesEl || !preparoEl) return;

        tituloEl.textContent = receita.titulo;
        descricaoEl.textContent = receita.descricao;

        ingredientesEl.innerHTML = "";
        (receita.ingredientes || []).forEach((item) => appendListItem(ingredientesEl, item));

        preparoEl.innerHTML = "";
        (receita.modopreparo || []).forEach((item) => appendListItem(preparoEl, item));

        modal.showModal();
    }

    function fecharModal() {
        const modal = document.querySelector(SELECTORS.modal);
        if (modal && typeof modal.close === "function") modal.close();
    }

    function mostrarErro() {
        const container = document.querySelector(SELECTORS.container);
        const erro = document.querySelector(SELECTORS.erro);
        const loading = document.querySelector(SELECTORS.loading);
        if (container) container.innerHTML = "";
        if (loading) loading.classList.add("hidden");
        if (erro) erro.classList.remove("hidden");
    }

    /** Carrega JSON, armazena em memória, renderiza cards. */
    async function carregarReceitas() {
        const container = document.querySelector(SELECTORS.container);
        if (!container) return;

        try {
            const response = await fetch("data.json", {
                headers: { Accept: "application/json" },
            });
            if (!response.ok) throw new Error(`HTTP ${response.status}`);

            const data = await response.json();
            if (!Array.isArray(data)) throw new Error("Dados inválidos");

            window.__receitasHalloween = data;
            container.innerHTML = "";
            data.forEach((card, index) => container.appendChild(criarCard(card, index)));

            document.querySelector(SELECTORS.loading)?.classList.add("hidden");
        } catch (err) {
            console.error("Erro ao carregar receitas:", err);
            mostrarErro();
        }
    }

    /** Delegação: um único listener no container. */
    function onContainerClick(event) {
        const btn = event.target.closest("button[data-index]");
        if (!btn) return;

        const index = parseInt(btn.getAttribute("data-index"), 10);
        if (Number.isNaN(index)) return;

        const receitas = window.__receitasHalloween;
        if (!Array.isArray(receitas) || receitas[index] == null) return;

        abrirModal(receitas[index]);
    }

    function init() {
        carregarReceitas().then(() => {
            const container = document.querySelector(SELECTORS.container);
            const btnFechar = document.querySelector(SELECTORS.modalFechar);
            const modal = document.querySelector(SELECTORS.modal);

            container?.addEventListener("click", onContainerClick);
            btnFechar?.addEventListener("click", fecharModal);
            modal?.addEventListener("close", () => btnFechar?.focus());
        });
    }

    if (document.readyState === "loading") {
        document.addEventListener("DOMContentLoaded", init);
    } else {
        init();
    }
})();
