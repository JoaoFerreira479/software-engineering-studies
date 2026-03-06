#ifndef FUNCIONARIO_H
#define FUNCIONARIO_H

#include "config.h"
#include <stdbool.h>

typedef struct {
    int codigo;
    char nome[HDG_TAM_NOME];
    char telefone[HDG_TAM_TELEFONE];
    char cargo[HDG_TAM_CARGO];
    float salario;
} Funcionario;

void cadastrarFuncionario(Funcionario *funcionarios, int *num_funcionarios, int capacidade_max);

bool funcionario_existe(const Funcionario *funcionarios, int num_funcionarios, int codigo);

const Funcionario *funcionario_por_codigo(const Funcionario *funcionarios, int num_funcionarios, int codigo);

void pesquisarFuncionario(const Funcionario *funcionarios, int num_funcionarios);

#endif
