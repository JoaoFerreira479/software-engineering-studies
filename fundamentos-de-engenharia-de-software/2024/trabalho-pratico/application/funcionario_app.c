#include <stdio.h>
#include <string.h>
#include "config.h"
#include "funcionario.h"
#include "io.h"
#include "arquivo.h"

void cadastrar_funcionario(Funcionario *funcionarios, int *num_funcionarios, int capacidade_max)
{
    if (funcionarios == NULL || num_funcionarios == NULL || capacidade_max <= 0)
        return;
    if (*num_funcionarios >= capacidade_max)
    {
        io_println("\nErro: limite de funcionarios atingido.");
        return;
    }

    io_configurar_locale();

    Funcionario *f = &funcionarios[*num_funcionarios];
    f->codigo = *num_funcionarios + 1;

    io_ler_linha(f->nome, sizeof(f->nome), "\nNome do funcionario: ");
    io_ler_linha(f->telefone, sizeof(f->telefone), "Telefone: ");
    io_ler_linha(f->cargo, sizeof(f->cargo), "Cargo: ");
    io_print("Salario: ");
    if (scanf("%f", &f->salario) != 1 || f->salario < 0.0f)
        f->salario = 0.0f;
    while (getchar() != '\n')
        ;

    (*num_funcionarios)++;
    salvar_funcionarios(funcionarios, *num_funcionarios);
    printf("\nFuncionario cadastrado. Codigo: %d\n", f->codigo);
}

void pesquisar_funcionario(const Funcionario *funcionarios, int num_funcionarios)
{
    if (funcionarios == NULL)
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
        io_print("Codigo do funcionario: ");
        if (scanf("%d", &codigo) != 1)
        {
            while (getchar() != '\n')
                ;
            io_println("\nEntrada invalida.");
            return;
        }
        while (getchar() != '\n')
            ;

        const Funcionario *f = funcionario_por_codigo(funcionarios, num_funcionarios, codigo);
        if (f != NULL)
            printf("\nCodigo: %d | Nome: %s | Telefone: %s | Cargo: %s | Salario: R$%.2f\n",
                   f->codigo, f->nome, f->telefone, f->cargo, f->salario);
        else
            io_println("\nFuncionario nao encontrado.");
        return;
    }

    if (opcao == 2)
    {
        char nome_busca[HDG_TAM_NOME];
        io_ler_linha(nome_busca, sizeof(nome_busca), "Nome do funcionario: ");
        int encontrado = 0;
        for (int i = 0; i < num_funcionarios; i++)
        {
            if (strcmp(funcionarios[i].nome, nome_busca) == 0)
            {
                printf("\nCodigo: %d | Nome: %s | Telefone: %s | Cargo: %s | Salario: R$%.2f\n",
                       funcionarios[i].codigo, funcionarios[i].nome, funcionarios[i].telefone,
                       funcionarios[i].cargo, funcionarios[i].salario);
                encontrado = 1;
                break;
            }
        }
        if (!encontrado)
            io_println("\nFuncionario nao encontrado.");
        return;
    }

    io_println("\nOpcao invalida.");
}
