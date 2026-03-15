#ifndef HDG_CLIENTE_APP_H
#define HDG_CLIENTE_APP_H

#include "cliente.h"

void cadastrar_cliente(Cliente *clientes, int *num_clientes, int capacidade_max);
void pesquisar_cliente(const Cliente *clientes, int num_clientes);

#endif
