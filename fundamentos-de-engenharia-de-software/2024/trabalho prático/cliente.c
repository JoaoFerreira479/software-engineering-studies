#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "cliente.h"
#include "arquivo.h"
#include "localeptbr.h"

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

static void ler_linha(char *buf, size_t tamanho, const char *prompt)
{
    printf("%s", prompt);
    if (fgets(buf, (int)tamanho, stdin) == NULL)
        buf[0] = '\0';
    else
        buf[strcspn(buf, "\n")] = '\0';
}

void cadastrarCliente(Cliente *clientes, int *num_clientes, int capacidade_max)
{
    if (clientes == NULL || num_clientes == NULL || capacidade_max <= 0)
        return;
    if (*num_clientes >= capacidade_max)
    {
        printf("\nErro: limite de clientes atingido.\n");
        return;
    }

    configurarLocale();

    Cliente *c = &clientes[*num_clientes];
    c->codigo = *num_clientes + 1;

    ler_linha(c->nome, sizeof(c->nome), "\nNome do cliente: ");
    ler_linha(c->endereco, sizeof(c->endereco), "Endereco: ");
    ler_linha(c->telefone, sizeof(c->telefone), "Telefone: ");

    (*num_clientes)++;
    salvar_clientes(clientes, *num_clientes);
    printf("\nCliente cadastrado. Codigo: %d\n", c->codigo);
}

void pesquisarCliente(const Cliente *clientes, int num_clientes)
{
    if (clientes == NULL)
        return;

    configurarLocale();

    int opcao;
    printf("\nPesquisar por: 1=Codigo 2=Nome. Opcao: ");
    if (scanf("%d", &opcao) != 1)
    {
        while (getchar() != '\n')
            ;
        printf("\nOpcao invalida.\n");
        return;
    }
    while (getchar() != '\n')
        ;

    if (opcao == 1)
    {
        int codigo;
        printf("Codigo do cliente: ");
        if (scanf("%d", &codigo) != 1)
        {
            while (getchar() != '\n')
                ;
            printf("\nEntrada invalida.\n");
            return;
        }
        while (getchar() != '\n')
            ;

        const Cliente *c = cliente_por_codigo(clientes, num_clientes, codigo);
        if (c != NULL)
            printf("\nCodigo: %d | Nome: %s | Endereco: %s | Telefone: %s\n",
                   c->codigo, c->nome, c->endereco, c->telefone);
        else
            printf("\nCliente nao encontrado.\n");
        return;
    }

    if (opcao == 2)
    {
        char nome_busca[HDG_TAM_NOME];
        ler_linha(nome_busca, sizeof(nome_busca), "Nome do cliente: ");
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
            printf("\nCliente nao encontrado.\n");
        return;
    }

    printf("\nOpcao invalida.\n");
}
