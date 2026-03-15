#ifndef HDG_QUARTO_H
#define HDG_QUARTO_H

#include "config.h"
#include <stdbool.h>

typedef struct {
    int numero;
    int qtd_hospedes;
    float valor_diaria;
    QuartoStatus status;
} Quarto;

bool quarto_existe(const Quarto *quartos, int num_quartos, int numero);
const Quarto *quarto_por_numero(const Quarto *quartos, int num_quartos, int numero);

#endif
