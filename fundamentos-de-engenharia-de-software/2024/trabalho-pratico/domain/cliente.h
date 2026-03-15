#ifndef HDG_CLIENTE_H
#define HDG_CLIENTE_H

#include "config.h"
#include <stdbool.h>

typedef struct {
    int codigo;
    char nome[HDG_TAM_NOME];
    char endereco[HDG_TAM_ENDERECO];
    char telefone[HDG_TAM_TELEFONE];
} Cliente;

bool cliente_existe(const Cliente *clientes, int num_clientes, int codigo);
const Cliente *cliente_por_codigo(const Cliente *clientes, int num_clientes, int codigo);

#endif
