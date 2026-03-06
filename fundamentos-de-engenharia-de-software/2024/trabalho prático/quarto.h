#ifndef QUARTO_H
#define QUARTO_H

#include "config.h"
#include <stdbool.h>

typedef struct {
    int numero;
    int qtd_hospedes;
    float valor_diaria;
    QuartoStatus status;
} Quarto;

void cadastrarQuarto(Quarto *quartos, int *num_quartos, int capacidade_max);

bool quarto_existe(const Quarto *quartos, int num_quartos, int numero);

const Quarto *quarto_por_numero(const Quarto *quartos, int num_quartos, int numero);

#endif
