#ifndef ESTADIA_H
#define ESTADIA_H

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

void cadastrarEstadia(const Cliente *clientes, int num_clientes,
                     Quarto *quartos, int num_quartos,
                     Estadia *estadias, int *num_estadias, int capacidade_max);

void darBaixaEstadia(Estadia *estadias, int *num_estadias,
                     Quarto *quartos, int num_quartos);

void mostrarEstadiasCliente(const Cliente *clientes, int num_clientes,
                            const Estadia *estadias, int num_estadias);

void calcularPontosFidelidade(const Cliente *clientes, int num_clientes,
                              const Estadia *estadias, int num_estadias);

bool estadia_sobrepoe(const Estadia *estadias, int num_estadias,
                      int numero_quarto, const char *data_entrada, const char *data_saida);

#endif
