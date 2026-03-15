#include "cliente.h"

const Cliente *cliente_por_codigo(const Cliente *clientes, int num_clientes, int codigo)
{
    if (clientes == NULL)
        return NULL;
    for (int i = 0; i < num_clientes; i++)
    {
        if (clientes[i].codigo == codigo)
            return &clientes[i];
    }
    return NULL;
}

bool cliente_existe(const Cliente *clientes, int num_clientes, int codigo)
{
    return cliente_por_codigo(clientes, num_clientes, codigo) != NULL;
}
