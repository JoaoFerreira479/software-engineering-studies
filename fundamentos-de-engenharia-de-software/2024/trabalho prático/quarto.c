#include <stdio.h>
#include <stdlib.h>
#include "quarto.h"
#include "arquivo.h"
#include "localeptbr.h"

const Quarto *quarto_por_numero(const Quarto *quartos, int num_quartos, int numero)
{
    if (quartos == NULL)
        return NULL;
    for (int i = 0; i < num_quartos; i++)
    {
        if (quartos[i].numero == numero)
            return &quartos[i];
    }
    return NULL;
}

bool quarto_existe(const Quarto *quartos, int num_quartos, int numero)
{
    return quarto_por_numero(quartos, num_quartos, numero) != NULL;
}

static void ler_int(const char *prompt, int *out)
{
    printf("%s", prompt);
    if (scanf("%d", out) != 1)
        *out = -1;
    while (getchar() != '\n')
        ;
}

static void ler_float(const char *prompt, float *out)
{
    printf("%s", prompt);
    if (scanf("%f", out) != 1)
        *out = -1.0f;
    while (getchar() != '\n')
        ;
}

void cadastrarQuarto(Quarto *quartos, int *num_quartos, int capacidade_max)
{
    if (quartos == NULL || num_quartos == NULL || capacidade_max <= 0)
        return;
    if (*num_quartos >= capacidade_max)
    {
        printf("\nErro: limite de quartos atingido.\n");
        return;
    }

    configurarLocale();

    int numero, qtd_hospedes;
    float valor_diaria;

    ler_int("\nNumero do quarto: ", &numero);
    if (numero <= 0)
    {
        printf("\nErro: numero invalido.\n");
        return;
    }
    if (quarto_existe(quartos, *num_quartos, numero))
    {
        printf("\nErro: quarto ja cadastrado.\n");
        return;
    }

    ler_int("Quantidade de hospedes: ", &qtd_hospedes);
    if (qtd_hospedes <= 0)
    {
        printf("\nErro: quantidade invalida.\n");
        return;
    }

    ler_float("Valor da diaria: ", &valor_diaria);
    if (valor_diaria < 0.0f)
    {
        printf("\nErro: valor invalido.\n");
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
