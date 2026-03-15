#ifndef HDG_ESTADIA_H
#define HDG_ESTADIA_H

#include "config.h"
#include "cliente.h"
#include "quarto.h"
#include "diaria.h"
#include <stdbool.h>

typedef struct {
    int codigo;
    int codigo_cliente;
    int numero_quarto;
    char data_entrada[HDG_TAM_DATA];
    char data_saida[HDG_TAM_DATA];
    int qtd_diarias;
} Estadia;

bool estadia_sobrepoe(const Estadia *estadias, int num_estadias,
                     int numero_quarto, const char *data_entrada, const char *data_saida);

#endif
