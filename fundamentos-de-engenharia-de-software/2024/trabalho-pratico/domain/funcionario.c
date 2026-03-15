#include "funcionario.h"

const Funcionario *funcionario_por_codigo(const Funcionario *funcionarios, int num_funcionarios, int codigo)
{
    if (funcionarios == NULL)
        return NULL;
    for (int i = 0; i < num_funcionarios; i++)
    {
        if (funcionarios[i].codigo == codigo)
            return &funcionarios[i];
    }
    return NULL;
}

bool funcionario_existe(const Funcionario *funcionarios, int num_funcionarios, int codigo)
{
    return funcionario_por_codigo(funcionarios, num_funcionarios, codigo) != NULL;
}
