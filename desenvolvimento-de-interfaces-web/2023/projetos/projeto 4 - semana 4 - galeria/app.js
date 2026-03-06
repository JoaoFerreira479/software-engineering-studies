/**
 * Galeria: abre imagem no modal (dialog) ao clicar ou Enter.
 */
(function () {
    const imagens = ["img1.jpg", "img2.jpg", "img3.jpg", "img4.jpg", "img5.jpg", "img6.jpg"];
    const modal = document.getElementById("modal");
    const imagemModal = document.getElementById("imagem-modal");
    const botaoFechar = document.getElementById("fechar");

    if (!modal || !imagemModal) return;

    function abrirImagem(index) {
        const src = imagens[Number(index)];
        if (src) {
            imagemModal.src = src;
            imagemModal.alt = "Imagem ampliada " + (Number(index) + 1);
            modal.showModal();
        }
    }

    function fecharImagem() {
        modal.close();
        imagemModal.removeAttribute("src");
    }

    document.querySelectorAll(".foto").forEach((foto) => {
        const index = foto.getAttribute("data-index");
        foto.addEventListener("click", () => abrirImagem(index));
        foto.addEventListener("keydown", (e) => {
            if (e.key === "Enter" || e.key === " ") {
                e.preventDefault();
                abrirImagem(index);
            }
        });
    });

    botaoFechar.addEventListener("click", fecharImagem);

    modal.addEventListener("click", (e) => {
        if (e.target === modal) fecharImagem();
    });

    modal.addEventListener("close", () => botaoFechar.focus());

    document.addEventListener("keydown", (e) => {
        if (e.key === "Escape" && modal.open) fecharImagem();
    });
})();
