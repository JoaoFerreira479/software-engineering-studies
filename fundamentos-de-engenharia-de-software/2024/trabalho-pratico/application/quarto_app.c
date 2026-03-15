#include <stdio.h>
#include "config.h"
#include "quarto.h"
#include "io.h"
#include "arquivo.h"

void cadastrar_quarto(Quarto *quartos, int *num_quartos, int capacidade_max)
{
    if (quartos == NULL || num_quartos == NULL || capacidade_max <= 0)
        return;
    if (*num_quartos >= capacidade_max)
    {
        io_println("\nErro: limite de quartos atingido.");
        return;
    }

    io_configurar_locale();

    int numero, qtd_hospedes;
    float valor_diaria;

    io_ler_int("\nNumero do quarto: ", &numero);
    if (numero <= 0)
    {
        io_println("\nErro: numero invalido.");
        return;
    }
    if (quarto_existe(quartos, *num_quartos, numero))
    {
        io_println("\nErro: quarto ja cadastrado.");
        return;
    }

    io_ler_int("Quantidade de hospedes: ", &qtd_hospedes);
    if (qtd_hospedes <= 0)
    {
        io_println("\nErro: quantidade invalida.");
        return;
    }

    io_ler_float("Valor da diaria: ", &valor_diaria);
    if (valor_diaria < 0.0f)
    {
        io_println("\nErro: valor invalido.");
        return;
    }

    Quarto *q = &quartos[*num_quartos];
    q->numero        = numero;
    q->qtd_hospedes  = qtd_hospedes;
    q->valor_diaria  = valor_diaria;
    q->status        = HDG_QUARTO_DESOCUPADO;
    (*num_quartos)++;

    salvar_quartos(quartos, *num_quartos);
    printf("\nQuarto cadastrado. Numero: %d\n", numero);
}
