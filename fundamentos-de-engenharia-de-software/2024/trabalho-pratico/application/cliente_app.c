#include <stdio.h>
#include <string.h>
#include "config.h"
#include "cliente.h"
#include "io.h"
#include "arquivo.h"

void cadastrar_cliente(Cliente *clientes, int *num_clientes, int capacidade_max)
{
    if (clientes == NULL || num_clientes == NULL || capacidade_max <= 0)
        return;
    if (*num_clientes >= capacidade_max)
    {
        io_println("\nErro: limite de clientes atingido.");
        return;
    }

    io_configurar_locale();

    Cliente *c = &clientes[*num_clientes];
    c->codigo = *num_clientes + 1;

    io_ler_linha(c->nome, sizeof(c->nome), "\nNome do cliente: ");
    io_ler_linha(c->endereco, sizeof(c->endereco), "Endereco: ");
    io_ler_linha(c->telefone, sizeof(c->telefone), "Telefone: ");

    (*num_clientes)++;
    salvar_clientes(clientes, *num_clientes);
    printf("\nCliente cadastrado. Codigo: %d\n", c->codigo);
}

void pesquisar_cliente(const Cliente *clientes, int num_clientes)
{
    if (clientes == NULL)
        return;

    io_configurar_locale();

    int opcao;
    io_print("\nPesquisar por: 1=Codigo 2=Nome. Opcao: ");
    if (scanf("%d", &opcao) != 1)
    {
        while (getchar() != '\n')
            ;
        io_println("\nOpcao invalida.");
        return;
    }
    while (getchar() != '\n')
        ;

    if (opcao == 1)
    {
        int codigo;
        io_print("Codigo do cliente: ");
        if (scanf("%d", &codigo) != 1)
        {
            while (getchar() != '\n')
                ;
            io_println("\nEntrada invalida.");
            return;
        }
        while (getchar() != '\n')
            ;

        const Cliente *c = cliente_por_codigo(clientes, num_clientes, codigo);
        if (c != NULL)
            printf("\nCodigo: %d | Nome: %s | Endereco: %s | Telefone: %s\n",
                   c->codigo, c->nome, c->endereco, c->telefone);
        else
            io_println("\nCliente nao encontrado.");
        return;
    }

    if (opcao == 2)
    {
        char nome_busca[HDG_TAM_NOME];
        io_ler_linha(nome_busca, sizeof(nome_busca), "Nome do cliente: ");
        int encontrado = 0;
        for (int i = 0; i < num_clientes; i++)
        {
            if (strcmp(clientes[i].nome, nome_busca) == 0)
            {
                printf("\nCodigo: %d | Nome: %s | Endereco: %s | Telefone: %s\n",
                       clientes[i].codigo, clientes[i].nome, clientes[i].endereco, clientes[i].telefone);
                encontrado = 1;
                break;
            }
        }
        if (!encontrado)
            io_println("\nCliente nao encontrado.");
        return;
    }

    io_println("\nOpcao invalida.");
}
