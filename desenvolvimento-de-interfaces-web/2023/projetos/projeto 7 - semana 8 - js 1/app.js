
const formulario = document.getElementById("meuFormulario");

const diaSelect = document.getElementById("dia");
const mesSelect = document.getElementById("mes");
const anoSelect = document.getElementById("ano");

for (let i = 1; i <= 31; i++) {
    const option = document.createElement("option");
    option.value = i;
    option.text = i;
    diaSelect.appendChild(option);
}

const meses = ["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"];
for (let i = 0; i < meses.length; i++) {
    const option = document.createElement("option");
    option.value = i + 1;
    option.text = meses[i];
    mesSelect.appendChild(option);
}

const anoAtual = new Date().getFullYear();
for (let i = anoAtual; i >= anoAtual - 100; i--) {
    const option = document.createElement("option");
    option.value = i;
    option.text = i;
    anoSelect.appendChild(option);
}

formulario.addEventListener("submit", function (event) {
    event.preventDefault();

    const nome = document.getElementById("nome").value;
    const sobrenome = document.getElementById("sobrenome").value;
    const email = document.getElementById("email").value;
    const cidade = document.getElementById("cidade").value;
    const telefone = document.getElementById("telefone").value;
    const estado = document.getElementById("estado").value;
    const dia = document.getElementById("dia").value;
    const mes = document.getElementById("mes").value;
    const ano = document.getElementById("ano").value;
    const sexo = document.querySelector('input[name="sexo"]:checked').value;
    const interesses = [];
    const checkboxes = document.getElementsByName("interesses");

    checkboxes.forEach(function (checkbox) {
        if (checkbox.checked) {
            interesses.push(checkbox.value);
        }
    });

    const textoPersonalizado = `
                Olá, ${nome}. Seja bem-vindo(a)!
                O seu formulário foi enviado com sucesso.
                Clique em 'Ok' para ser redirecionado para o nosso site.
            `;

    alert(textoPersonalizado);

    window.location.href = 'outra_pagina.html';

    formulario.reset();
});

