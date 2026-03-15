#ifndef HDG_ESTADIA_APP_H
#define HDG_ESTADIA_APP_H

#include "cliente.h"
#include "funcionario.h"
#include "quarto.h"
#include "estadia.h"

void cadastrar_estadia(const Cliente *clientes, int num_clientes,
                      Quarto *quartos, int num_quartos,
                      Estadia *estadias, int *num_estadias, int capacidade_max);

void dar_baixa_estadia(Estadia *estadias, int *num_estadias,
                      Quarto *quartos, int num_quartos);

void mostrar_estadias_cliente(const Cliente *clientes, int num_clientes,
                              const Estadia *estadias, int num_estadias);

void calcular_pontos_fidelidade(const Cliente *clientes, int num_clientes,
                                const Estadia *estadias, int num_estadias);

#endif
