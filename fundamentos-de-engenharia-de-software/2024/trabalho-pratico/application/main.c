#include <stdio.h>
#include "config.h"
#include "cliente.h"
#include "funcionario.h"
#include "estadia.h"
#include "quarto.h"
#include "io.h"
#include "arquivo.h"
#include "cliente_app.h"
#include "funcionario_app.h"
#include "quarto_app.h"
#include "estadia_app.h"

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
    io_configurar_locale();

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
        int opcao = io_ler_opcao();
        if (opcao < 0)
        {
            io_println("\nEntrada invalida. Use um numero.");
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
            io_println("\nEncerrando.");
            return 0;
        default:
            io_println("\nOpcao invalida.");
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
    io_exibir_menu("Hotel Descanso Garantido", opcoes, 5);
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
        io_exibir_menu("Clientes", opcoes, 3);
        int op = io_ler_opcao();
        if (op < 0)
        {
            io_println("\nEntrada invalida.");
            continue;
        }
        switch (op)
        {
        case 1:
            cadastrar_cliente(clientes, num_clientes, HDG_MAX_CLIENTES);
            break;
        case 2:
            pesquisar_cliente(clientes, *num_clientes);
            break;
        case 0:
            return;
        default:
            io_println("\nOpcao invalida.");
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
        io_exibir_menu("Funcionarios", opcoes, 3);
        int op = io_ler_opcao();
        if (op < 0)
        {
            io_println("\nEntrada invalida.");
            continue;
        }
        switch (op)
        {
        case 1:
            cadastrar_funcionario(funcionarios, num_funcionarios, HDG_MAX_FUNCIONARIOS);
            break;
        case 2:
            pesquisar_funcionario(funcionarios, *num_funcionarios);
            break;
        case 0:
            return;
        default:
            io_println("\nOpcao invalida.");
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
        io_exibir_menu("Estadias", opcoes, 5);
        int op = io_ler_opcao();
        if (op < 0)
        {
            io_println("\nEntrada invalida.");
            continue;
        }
        switch (op)
        {
        case 1:
            cadastrar_estadia(clientes, num_clientes, quartos, num_quartos,
                             estadias, num_estadias, HDG_MAX_ESTADIAS);
            break;
        case 2:
            dar_baixa_estadia(estadias, num_estadias, quartos, num_quartos);
            break;
        case 3:
            mostrar_estadias_cliente(clientes, num_clientes, estadias, *num_estadias);
            break;
        case 4:
            calcular_pontos_fidelidade(clientes, num_clientes, estadias, *num_estadias);
            break;
        case 0:
            return;
        default:
            io_println("\nOpcao invalida.");
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
        io_exibir_menu("Quartos", opcoes, 2);
        int op = io_ler_opcao();
        if (op < 0)
        {
            io_println("\nEntrada invalida.");
            continue;
        }
        switch (op)
        {
        case 1:
            cadastrar_quarto(quartos, num_quartos, HDG_MAX_QUARTOS);
            break;
        case 0:
            return;
        default:
            io_println("\nOpcao invalida.");
        }
    }
}
