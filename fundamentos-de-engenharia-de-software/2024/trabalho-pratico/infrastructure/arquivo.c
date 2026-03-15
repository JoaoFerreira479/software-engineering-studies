#include <stdio.h>
#include <stdlib.h>
#include "arquivo.h"

#define ARQUIVO_CLIENTES     "clientes.bin"
#define ARQUIVO_FUNCIONARIOS "funcionarios.bin"
#define ARQUIVO_ESTADIAS     "estadias.bin"
#define ARQUIVO_QUARTOS      "quartos.bin"

static FILE *abrir(const char *nome, const char *modo)
{
    FILE *f = fopen(nome, modo);
    if (f == NULL && modo[0] == 'r')
        return NULL;
    if (f == NULL)
        perror("Erro ao abrir arquivo");
    return f;
}

static int ler_contagem(FILE *f, int capacidade_max)
{
    int n = 0;
    if (fread(&n, sizeof(n), 1, f) != 1)
        return 0;
    if (n < 0 || n > capacidade_max)
        return 0;
    return n;
}

void carregar_clientes(Cliente *clientes, int *num_clientes, int capacidade_max)
{
    *num_clientes = 0;
    if (clientes == NULL || capacidade_max <= 0)
        return;

    FILE *f = abrir(ARQUIVO_CLIENTES, "rb");
    if (f == NULL)
        return;

    int n = ler_contagem(f, capacidade_max);
    if (n > 0 && fread(clientes, sizeof(Cliente), (size_t)n, f) == (size_t)n)
        *num_clientes = n;
    fclose(f);
}

void salvar_clientes(const Cliente *clientes, int num_clientes)
{
    if (clientes == NULL && num_clientes != 0)
        return;

    FILE *f = abrir(ARQUIVO_CLIENTES, "wb");
    if (f == NULL)
        return;

    fwrite(&num_clientes, sizeof(num_clientes), 1, f);
    if (num_clientes > 0)
        fwrite(clientes, sizeof(Cliente), (size_t)num_clientes, f);
    fclose(f);
}

void carregar_funcionarios(Funcionario *funcionarios, int *num_funcionarios, int capacidade_max)
{
    *num_funcionarios = 0;
    if (funcionarios == NULL || capacidade_max <= 0)
        return;

    FILE *f = abrir(ARQUIVO_FUNCIONARIOS, "rb");
    if (f == NULL)
        return;

    int n = ler_contagem(f, capacidade_max);
    if (n > 0 && fread(funcionarios, sizeof(Funcionario), (size_t)n, f) == (size_t)n)
        *num_funcionarios = n;
    fclose(f);
}

void salvar_funcionarios(const Funcionario *funcionarios, int num_funcionarios)
{
    if (funcionarios == NULL && num_funcionarios != 0)
        return;

    FILE *f = abrir(ARQUIVO_FUNCIONARIOS, "wb");
    if (f == NULL)
        return;

    fwrite(&num_funcionarios, sizeof(num_funcionarios), 1, f);
    if (num_funcionarios > 0)
        fwrite(funcionarios, sizeof(Funcionario), (size_t)num_funcionarios, f);
    fclose(f);
}

void carregar_estadias(Estadia *estadias, int *num_estadias, int capacidade_max)
{
    *num_estadias = 0;
    if (estadias == NULL || capacidade_max <= 0)
        return;

    FILE *f = abrir(ARQUIVO_ESTADIAS, "rb");
    if (f == NULL)
        return;

    int n = ler_contagem(f, capacidade_max);
    if (n > 0 && fread(estadias, sizeof(Estadia), (size_t)n, f) == (size_t)n)
        *num_estadias = n;
    fclose(f);
}

void salvar_estadias(const Estadia *estadias, int num_estadias)
{
    if (estadias == NULL && num_estadias != 0)
        return;

    FILE *f = abrir(ARQUIVO_ESTADIAS, "wb");
    if (f == NULL)
        return;

    fwrite(&num_estadias, sizeof(num_estadias), 1, f);
    if (num_estadias > 0)
        fwrite(estadias, sizeof(Estadia), (size_t)num_estadias, f);
    fclose(f);
}

void carregar_quartos(Quarto *quartos, int *num_quartos, int capacidade_max)
{
    *num_quartos = 0;
    if (quartos == NULL || capacidade_max <= 0)
        return;

    FILE *f = abrir(ARQUIVO_QUARTOS, "rb");
    if (f == NULL)
        return;

    int n = ler_contagem(f, capacidade_max);
    if (n > 0 && fread(quartos, sizeof(Quarto), (size_t)n, f) == (size_t)n)
        *num_quartos = n;
    fclose(f);
}

void salvar_quartos(const Quarto *quartos, int num_quartos)
{
    if (quartos == NULL && num_quartos != 0)
        return;

    FILE *f = abrir(ARQUIVO_QUARTOS, "wb");
    if (f == NULL)
        return;

    fwrite(&num_quartos, sizeof(num_quartos), 1, f);
    if (num_quartos > 0)
        fwrite(quartos, sizeof(Quarto), (size_t)num_quartos, f);
    fclose(f);
}
