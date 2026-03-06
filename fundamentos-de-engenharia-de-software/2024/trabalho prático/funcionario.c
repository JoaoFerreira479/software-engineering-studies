#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "funcionario.h"
#include "arquivo.h"
#include "localeptbr.h"

const Funcionario *funcionario_por_codigo(const Funcionario *funcionarios, int num_funcionarios, int codigo)
{
    if (funcionarios == NULL)
        return NULL;
    for (int i = 0; i < num_funcionarios; i++)
    {
        if (funcionarios[i].codigo == codigo)
            return &funcionarios[i];
    }
    return NULL;
}

bool funcionario_existe(const Funcionario *funcionarios, int num_funcionarios, int codigo)
{
    return funcionario_por_codigo(funcionarios, num_funcionarios, codigo) != NULL;
}

static void ler_linha(char *buf, size_t tamanho, const char *prompt)
{
    printf("%s", prompt);
    if (fgets(buf, (int)tamanho, stdin) == NULL)
        buf[0] = '\0';
    else
        buf[strcspn(buf, "\n")] = '\0';
}

void cadastrarFuncionario(Funcionario *funcionarios, int *num_funcionarios, int capacidade_max)
{
    if (funcionarios == NULL || num_funcionarios == NULL || capacidade_max <= 0)
        return;
    if (*num_funcionarios >= capacidade_max)
    {
        printf("\nErro: limite de funcionarios atingido.\n");
        return;
    }

    configurarLocale();

    Funcionario *f = &funcionarios[*num_funcionarios];
    f->codigo = *num_funcionarios + 1;

    ler_linha(f->nome, sizeof(f->nome), "\nNome do funcionario: ");
    ler_linha(f->telefone, sizeof(f->telefone), "Telefone: ");
    ler_linha(f->cargo, sizeof(f->cargo), "Cargo: ");
    printf("Salario: ");
    if (scanf("%f", &f->salario) != 1 || f->salario < 0.0f)
        f->salario = 0.0f;
    while (getchar() != '\n')
        ;

    (*num_funcionarios)++;
    salvar_funcionarios(funcionarios, *num_funcionarios);
    printf("\nFuncionario cadastrado. Codigo: %d\n", f->codigo);
}

void pesquisarFuncionario(const Funcionario *funcionarios, int num_funcionarios)
{
    if (funcionarios == NULL)
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
        printf("Codigo do funcionario: ");
        if (scanf("%d", &codigo) != 1)
        {
            while (getchar() != '\n')
                ;
            printf("\nEntrada invalida.\n");
            return;
        }
        while (getchar() != '\n')
            ;

        const Funcionario *f = funcionario_por_codigo(funcionarios, num_funcionarios, codigo);
        if (f != NULL)
            printf("\nCodigo: %d | Nome: %s | Telefone: %s | Cargo: %s | Salario: R$%.2f\n",
                   f->codigo, f->nome, f->telefone, f->cargo, f->salario);
        else
            printf("\nFuncionario nao encontrado.\n");
        return;
    }

    if (opcao == 2)
    {
        char nome_busca[HDG_TAM_NOME];
        ler_linha(nome_busca, sizeof(nome_busca), "Nome do funcionario: ");
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
            printf("\nFuncionario nao encontrado.\n");
        return;
    }

    printf("\nOpcao invalida.\n");
}
