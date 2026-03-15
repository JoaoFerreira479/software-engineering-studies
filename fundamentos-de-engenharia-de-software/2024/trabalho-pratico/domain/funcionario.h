#ifndef HDG_FUNCIONARIO_H
#define HDG_FUNCIONARIO_H

#include "config.h"
#include <stdbool.h>

typedef struct {
    int codigo;
    char nome[HDG_TAM_NOME];
    char telefone[HDG_TAM_TELEFONE];
    char cargo[HDG_TAM_CARGO];
    float salario;
} Funcionario;

bool funcionario_existe(const Funcionario *funcionarios, int num_funcionarios, int codigo);
const Funcionario *funcionario_por_codigo(const Funcionario *funcionarios, int num_funcionarios, int codigo);

#endif
