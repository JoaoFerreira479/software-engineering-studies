#include <stdio.h>
#include <stdlib.h>
#include "config.h"
#include "localeptbr.h"
#include "cliente.h"
#include "funcionario.h"
#include "estadia.h"
#include "quarto.h"
#include "arquivo.h"

static void exibir_menu(const char *titulo, const char *opcoes[], int n)
{
    printf("\n-------- %s --------\n", titulo);
    for (int i = 0; i < n; i++)
        printf("%s\n", opcoes[i]);
    printf("\nOpcao: ");
}

static int ler_opcao(void)
{
    int op;
    if (scanf("%d", &op) != 1)
    {
        int c;
        while ((c = getchar()) != '\n' && c != EOF)
            ;
        return -1;
    }
    int c;
    while ((c = getchar()) != '\n' && c != EOF)
        ;
    return op;
}

static void menu_principal(void);
static void menu_clientes(Cliente *clientes, int *num_clientes);
static void menu_funcionarios(Funcionario *funcionarios, int *num_funcionarios);
static void menu_estadias(Cliente *clientes, int num_clientes,
                          Funcionario *funcionarios, int num_funcionarios,
                          Quarto *quartos, int num_quartos,
                          Estadia *estadias, int *num_estadias);
static void menu_quartos(Quarto *quartos, int *num_quartos);

int main(void)
{
    configurarLocale();

    Cliente clientes[HDG_MAX_CLIENTES];
    int num_clientes = 0;
    Funcionario funcionarios[HDG_MAX_FUNCIONARIOS];
    int num_funcionarios = 0;
    Estadia estadias[HDG_MAX_ESTADIAS];
    int num_estadias = 0;
    Quarto quartos[HDG_MAX_QUARTOS];
    int num_quartos = 0;

    carregar_clientes(clientes, &num_clientes, HDG_MAX_CLIENTES);
    carregar_funcionarios(funcionarios, &num_funcionarios, HDG_MAX_FUNCIONARIOS);
    carregar_estadias(estadias, &num_estadias, HDG_MAX_ESTADIAS);
    carregar_quartos(quartos, &num_quartos, HDG_MAX_QUARTOS);

    for (;;)
    {
        menu_principal();
        int opcao = ler_opcao();
        if (opcao < 0)
        {
            printf("\nEntrada invalida. Use um numero.\n");
            continue;
        }

        switch (opcao)
        {
        case 1:
            menu_clientes(clientes, &num_clientes);
            break;
        case 2:
            menu_funcionarios(funcionarios, &num_funcionarios);
            break;
        case 3:
            menu_estadias(clientes, num_clientes, funcionarios, num_funcionarios,
                          quartos, num_quartos, estadias, &num_estadias);
            break;
        case 4:
            menu_quartos(quartos, &num_quartos);
            break;
        case 0:
            salvar_clientes(clientes, num_clientes);
            salvar_funcionarios(funcionarios, num_funcionarios);
            salvar_estadias(estadias, num_estadias);
            salvar_quartos(quartos, num_quartos);
            printf("\nEncerrando.\n");
            return 0;
        default:
            printf("\nOpcao invalida.\n");
        }
    }
}

static void menu_principal(void)
{
    static const char *opcoes[] = {
        "1. Clientes",
        "2. Funcionarios",
        "3. Estadias",
        "4. Quartos",
        "0. Sair"
    };
    exibir_menu("Hotel Descanso Garantido", opcoes, 5);
}

static void menu_clientes(Cliente *clientes, int *num_clientes)
{
    static const char *opcoes[] = {
        "1. Cadastrar",
        "2. Pesquisar",
        "0. Voltar"
    };
    for (;;)
    {
        exibir_menu("Clientes", opcoes, 3);
        int op = ler_opcao();
        if (op < 0)
        {
            printf("\nEntrada invalida.\n");
            continue;
        }
        switch (op)
        {
        case 1:
            cadastrarCliente(clientes, num_clientes, HDG_MAX_CLIENTES);
            break;
        case 2:
            pesquisarCliente(clientes, *num_clientes);
            break;
        case 0:
            return;
        default:
            printf("\nOpcao invalida.\n");
        }
    }
}

static void menu_funcionarios(Funcionario *funcionarios, int *num_funcionarios)
{
    static const char *opcoes[] = {
        "1. Cadastrar",
        "2. Pesquisar",
        "0. Voltar"
    };
    for (;;)
    {
        exibir_menu("Funcionarios", opcoes, 3);
        int op = ler_opcao();
        if (op < 0)
        {
            printf("\nEntrada invalida.\n");
            continue;
        }
        switch (op)
        {
        case 1:
            cadastrarFuncionario(funcionarios, num_funcionarios, HDG_MAX_FUNCIONARIOS);
            break;
        case 2:
            pesquisarFuncionario(funcionarios, *num_funcionarios);
            break;
        case 0:
            return;
        default:
            printf("\nOpcao invalida.\n");
        }
    }
}

static void menu_estadias(Cliente *clientes, int num_clientes,
                          Funcionario *funcionarios, int num_funcionarios,
                          Quarto *quartos, int num_quartos,
                          Estadia *estadias, int *num_estadias)
{
    (void)funcionarios;
    (void)num_funcionarios;
    static const char *opcoes[] = {
        "1. Cadastrar",
        "2. Dar baixa",
        "3. Estadias por cliente",
        "4. Pontos de fidelidade",
        "0. Voltar"
    };
    for (;;)
    {
        exibir_menu("Estadias", opcoes, 5);
        int op = ler_opcao();
        if (op < 0)
        {
            printf("\nEntrada invalida.\n");
            continue;
        }
        switch (op)
        {
        case 1:
            cadastrarEstadia(clientes, num_clientes, quartos, num_quartos,
                             estadias, num_estadias, HDG_MAX_ESTADIAS);
            break;
        case 2:
            darBaixaEstadia(estadias, num_estadias, quartos, num_quartos);
            break;
        case 3:
            mostrarEstadiasCliente(clientes, num_clientes, estadias, *num_estadias);
            break;
        case 4:
            calcularPontosFidelidade(clientes, num_clientes, estadias, *num_estadias);
            break;
        case 0:
            return;
        default:
            printf("\nOpcao invalida.\n");
        }
    }
}

static void menu_quartos(Quarto *quartos, int *num_quartos)
{
    static const char *opcoes[] = {
        "1. Cadastrar",
        "0. Voltar"
    };
    for (;;)
    {
        exibir_menu("Quartos", opcoes, 2);
        int op = ler_opcao();
        if (op < 0)
        {
            printf("\nEntrada invalida.\n");
            continue;
        }
        switch (op)
        {
        case 1:
            cadastrarQuarto(quartos, num_quartos, HDG_MAX_QUARTOS);
            break;
        case 0:
            return;
        default:
            printf("\nOpcao invalida.\n");
        }
    }
}
