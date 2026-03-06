/**
 * Atualiza aria-expanded do botão de menu mobile para acessibilidade.
 */
(function () {
    const toggle = document.getElementById("menuToggle");
    if (!toggle) return;

    function updateAria() {
        toggle.setAttribute("aria-expanded", toggle.checked ? "true" : "false");
    }

    toggle.addEventListener("change", updateAria);
    updateAria();
})();
