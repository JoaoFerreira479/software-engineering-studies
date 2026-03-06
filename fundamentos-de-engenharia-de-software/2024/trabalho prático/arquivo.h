#ifndef ARQUIVO_H
#define ARQUIVO_H

#include "config.h"
#include "cliente.h"
#include "funcionario.h"
#include "estadia.h"
#include "quarto.h"

void carregar_clientes(Cliente *clientes, int *num_clientes, int capacidade_max);
void salvar_clientes(const Cliente *clientes, int num_clientes);

void carregar_funcionarios(Funcionario *funcionarios, int *num_funcionarios, int capacidade_max);
void salvar_funcionarios(const Funcionario *funcionarios, int num_funcionarios);

void carregar_estadias(Estadia *estadias, int *num_estadias, int capacidade_max);
void salvar_estadias(const Estadia *estadias, int num_estadias);

void carregar_quartos(Quarto *quartos, int *num_quartos, int capacidade_max);
void salvar_quartos(const Quarto *quartos, int num_quartos);

#endif
