(function () {
    "use strict";

    var fallback2023 = [
        { Dia: 22, Mes: 10, Ano: 2023, Horario: "00:29", Fase: "Lua Crescente" },
        { Dia: 28, Mes: 10, Ano: 2023, Horario: "17:24", Fase: "Lua Cheia" },
        { Dia: 5, Mes: 11, Ano: 2023, Horario: "05:36", Fase: "Lua Minguante" },
        { Dia: 13, Mes: 11, Ano: 2023, Horario: "06:27", Fase: "Lua Nova" },
        { Dia: 20, Mes: 11, Ano: 2023, Horario: "07:49", Fase: "Lua Crescente" },
        { Dia: 27, Mes: 11, Ano: 2023, Horario: "06:16", Fase: "Lua Cheia" },
        { Dia: 5, Mes: 12, Ano: 2023, Horario: "02:49", Fase: "Lua Minguante" },
        { Dia: 12, Mes: 12, Ano: 2023, Horario: "20:32", Fase: "Lua Nova" },
        { Dia: 19, Mes: 12, Ano: 2023, Horario: "15:39", Fase: "Lua Crescente" },
        { Dia: 26, Mes: 12, Ano: 2023, Horario: "21:33", Fase: "Lua Cheia" }
    ];

    function formataData(d) {
        var dia = d.getDate();
        var mes = d.getMonth() + 1;
        var ano = d.getFullYear();
        var h = d.getHours();
        var m = d.getMinutes();
        var horario = (h < 10 ? "0" : "") + h + ":" + (m < 10 ? "0" : "") + m;
        return { Dia: dia, Mes: mes, Ano: ano, Horario: horario };
    }

    function calcularFasesDoAno(ano) {
        if (typeof SunCalc === "undefined" || !SunCalc.getMoonIllumination) {
            return ano === 2023 ? fallback2023 : [];
        }
        var inicio = new Date(ano, 0, 1, 0, 0, 0);
        var fim = new Date(ano, 11, 31, 23, 59, 59);
        var resultados = [];
        var step = 6 * 60 * 60 * 1000;
        var t = inicio.getTime();
        var fimT = fim.getTime();
        var prev = null;

        while (t <= fimT) {
            var d = new Date(t);
            var ill = SunCalc.getMoonIllumination(d);
            var p = ill.phase;
            if (prev !== null) {
                function cruzou(antes, depois, alvo) {
                    if (alvo === 0) return antes > 0.9 && depois < 0.1;
                    if (alvo === 0.25) return antes < 0.25 && depois >= 0.25;
                    if (alvo === 0.5) return antes < 0.5 && depois >= 0.5;
                    if (alvo === 0.75) return antes < 0.75 && depois >= 0.75;
                    return false;
                }
                var nomes = { 0: "Lua Nova", 0.25: "Lua Crescente", 0.5: "Lua Cheia", 0.75: "Lua Minguante" };
                [0, 0.25, 0.5, 0.75].forEach(function (alvo) {
                    if (cruzou(prev, p, alvo)) {
                        var info = formataData(d);
                        info.Fase = nomes[alvo];
                        resultados.push(info);
                    }
                });
            }
            prev = p;
            t += step;
        }

        resultados.sort(function (a, b) {
            if (a.Ano !== b.Ano) return a.Ano - b.Ano;
            if (a.Mes !== b.Mes) return a.Mes - b.Mes;
            if (a.Dia !== b.Dia) return a.Dia - b.Dia;
            return a.Horario.localeCompare(b.Horario);
        });
        return resultados;
    }

    function montarTabelaFases(dados) {
        var table = document.getElementById("fasesdalua-table");
        if (!table) return;
        if (table.tHead) table.removeChild(table.tHead);
        while (table.tBodies.length) table.removeChild(table.tBodies[0]);
        if (!dados || dados.length === 0) {
            var tbody = table.createTBody();
            var empty = tbody.insertRow();
            var cell = empty.insertCell();
            cell.textContent = "Nenhuma fase encontrada para este ano.";
            cell.colSpan = 5;
            return;
        }
        var keys = ["Dia", "Mes", "Ano", "Horario", "Fase"];
        var thead = table.createTHead();
        var headerRow = thead.insertRow();
        keys.forEach(function (key) {
            var th = document.createElement("th");
            th.textContent = key === "Mes" ? "Mês" : key === "Horario" ? "Horário" : key;
            headerRow.appendChild(th);
        });
        var tbody = table.createTBody();
        dados.forEach(function (rowData) {
            var row = tbody.insertRow();
            keys.forEach(function (key) {
                var cell = row.insertCell();
                cell.textContent = rowData[key];
            });
        });
    }

    function atualizarTabela() {
        var input = document.getElementById("ano-fases");
        var ano = input ? parseInt(input.value, 10) : 2023;
        if (isNaN(ano) || ano < 1900 || ano > 2100) ano = 2023;
        var dados = calcularFasesDoAno(ano);
        montarTabelaFases(dados);
    }

    function init() {
        atualizarTabela();
        var btn = document.getElementById("btn-atualizar-fases");
        var input = document.getElementById("ano-fases");
        if (btn) btn.addEventListener("click", atualizarTabela);
        if (input) input.addEventListener("change", atualizarTabela);
    }

    if (document.readyState === "loading") {
        document.addEventListener("DOMContentLoaded", init);
    } else {
        init();
    }
})();
