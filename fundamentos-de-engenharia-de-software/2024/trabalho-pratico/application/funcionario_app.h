#ifndef HDG_FUNCIONARIO_APP_H
#define HDG_FUNCIONARIO_APP_H

#include "funcionario.h"

void cadastrar_funcionario(Funcionario *funcionarios, int *num_funcionarios, int capacidade_max);
void pesquisar_funcionario(const Funcionario *funcionarios, int num_funcionarios);

#endif
