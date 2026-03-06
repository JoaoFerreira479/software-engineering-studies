#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "estadia.h"
#include "arquivo.h"
#include "localeptbr.h"

bool estadia_sobrepoe(const Estadia *estadias, int num_estadias,
                      int numero_quarto, const char *data_entrada, const char *data_saida)
{
    if (estadias == NULL || data_entrada == NULL || data_saida == NULL)
        return false;

    for (int i = 0; i < num_estadias; i++)
    {
        if (estadias[i].numero_quarto != numero_quarto)
            continue;
        int cmp_ent = compararDatas(data_entrada, estadias[i].data_saida);
        int cmp_sai = compararDatas(data_saida, estadias[i].data_entrada);
        if (cmp_ent < 0 && cmp_sai > 0)
            return true;
    }
    return false;
}

static void ler_linha(char *buf, size_t tamanho, const char *prompt)
{
    printf("%s", prompt);
    if (fgets(buf, (int)tamanho, stdin) == NULL)
        buf[0] = '\0';
    else
        buf[strcspn(buf, "\n")] = '\0';
}

void cadastrarEstadia(const Cliente *clientes, int num_clientes,
                     Quarto *quartos, int num_quartos,
                     Estadia *estadias, int *num_estadias, int capacidade_max)
{
    if (clientes == NULL || quartos == NULL || estadias == NULL || num_estadias == NULL)
        return;
    if (*num_estadias >= capacidade_max)
    {
        printf("\nErro: limite de estadias atingido.\n");
        return;
    }

    configurarLocale();

    int codigo_cliente, qtd_hospedes;
    char data_entrada[HDG_TAM_DATA], data_saida[HDG_TAM_DATA];

    printf("\nCodigo do cliente: ");
    if (scanf("%d", &codigo_cliente) != 1)
    {
        while (getchar() != '\n')
            ;
        printf("\nEntrada invalida.\n");
        return;
    }
    while (getchar() != '\n')
        ;

    if (!cliente_existe(clientes, num_clientes, codigo_cliente))
    {
        printf("\nErro: cliente nao encontrado.\n");
        return;
    }

    printf("Quantidade de hospedes: ");
    if (scanf("%d", &qtd_hospedes) != 1 || qtd_hospedes <= 0)
    {
        while (getchar() != '\n')
            ;
        printf("\nErro: quantidade invalida.\n");
        return;
    }
    while (getchar() != '\n')
        ;

    ler_linha(data_entrada, sizeof(data_entrada), "Data de entrada (dd/mm/aaaa): ");
    ler_linha(data_saida, sizeof(data_saida), "Data de saida (dd/mm/aaaa): ");

    if (!validarData(data_entrada) || !validarData(data_saida))
    {
        printf("\nErro: formato de data invalido. Use " DIARIA_FORMATO ".\n");
        return;
    }

    int diarias = calcularDiarias(data_entrada, data_saida);
    if (diarias < 0)
    {
        printf("\nErro: data de saida deve ser posterior a data de entrada.\n");
        return;
    }

    int quarto_escolhido = -1;
    for (int i = 0; i < num_quartos; i++)
    {
        if (quartos[i].qtd_hospedes < qtd_hospedes)
            continue;
        if (quartos[i].status != HDG_QUARTO_DESOCUPADO)
            continue;
        if (estadia_sobrepoe(estadias, *num_estadias, quartos[i].numero, data_entrada, data_saida))
            continue;
        quarto_escolhido = quartos[i].numero;
        break;
    }

    if (quarto_escolhido < 0)
    {
        printf("\nErro: nenhum quarto disponivel para o periodo.\n");
        return;
    }

    Estadia *e = &estadias[*num_estadias];
    e->codigo = *num_estadias + 1;
    e->codigo_cliente = codigo_cliente;
    e->numero_quarto = quarto_escolhido;
    e->qtd_diarias = diarias;
    (void)strncpy(e->data_entrada, data_entrada, HDG_TAM_DATA - 1);
    e->data_entrada[HDG_TAM_DATA - 1] = '\0';
    (void)strncpy(e->data_saida, data_saida, HDG_TAM_DATA - 1);
    e->data_saida[HDG_TAM_DATA - 1] = '\0';

    (*num_estadias)++;

    for (int i = 0; i < num_quartos; i++)
    {
        if (quartos[i].numero == quarto_escolhido)
        {
            quartos[i].status = HDG_QUARTO_OCUPADO;
            break;
        }
    }

    salvar_estadias(estadias, *num_estadias);
    salvar_quartos(quartos, num_quartos);
    printf("\nEstadia cadastrada. Codigo: %d\n", e->codigo);
}

void darBaixaEstadia(Estadia *estadias, int *num_estadias,
                     Quarto *quartos, int num_quartos)
{
    if (estadias == NULL || num_estadias == NULL || quartos == NULL)
        return;

    configurarLocale();

    int codigo_estadia;
    printf("\nCodigo da estadia: ");
    if (scanf("%d", &codigo_estadia) != 1)
    {
        while (getchar() != '\n')
            ;
        printf("\nEntrada invalida.\n");
        return;
    }
    while (getchar() != '\n')
        ;

    for (int i = 0; i < *num_estadias; i++)
    {
        if (estadias[i].codigo != codigo_estadia)
            continue;

        int numero_quarto = estadias[i].numero_quarto;
        float valor_total = 0.0f;

        for (int j = 0; j < num_quartos; j++)
        {
            if (quartos[j].numero == numero_quarto)
            {
                valor_total = quartos[j].valor_diaria * (float)estadias[i].qtd_diarias;
                quartos[j].status = HDG_QUARTO_DESOCUPADO;
                break;
            }
        }

        printf("Valor total: R$ %.2f\n", valor_total);

        for (int j = i; j < *num_estadias - 1; j++)
            estadias[j] = estadias[j + 1];
        (*num_estadias)--;

        salvar_estadias(estadias, *num_estadias);
        salvar_quartos(quartos, num_quartos);
        printf("Baixa realizada.\n");
        return;
    }

    printf("\nErro: estadia nao encontrada.\n");
}

void mostrarEstadiasCliente(const Cliente *clientes, int num_clientes,
                            const Estadia *estadias, int num_estadias)
{
    if (clientes == NULL || estadias == NULL)
        return;

    configurarLocale();

    int codigo_cliente;
    printf("\nCodigo do cliente: ");
    if (scanf("%d", &codigo_cliente) != 1)
    {
        while (getchar() != '\n')
            ;
        printf("\nEntrada invalida.\n");
        return;
    }
    while (getchar() != '\n')
        ;

    if (!cliente_existe(clientes, num_clientes, codigo_cliente))
    {
        printf("\nCliente nao encontrado.\n");
        return;
    }

    int nenhuma = 1;
    for (int i = 0; i < num_estadias; i++)
    {
        if (estadias[i].codigo_cliente != codigo_cliente)
            continue;
        nenhuma = 0;
        printf("  Estadia %d | Quarto %d | %s a %s | %d diarias\n",
               estadias[i].codigo, estadias[i].numero_quarto,
               estadias[i].data_entrada, estadias[i].data_saida, estadias[i].qtd_diarias);
    }
    if (nenhuma)
        printf("\nNenhuma estadia para este cliente.\n");
}

void calcularPontosFidelidade(const Cliente *clientes, int num_clientes,
                              const Estadia *estadias, int num_estadias)
{
    if (clientes == NULL || estadias == NULL)
        return;

    configurarLocale();

    int codigo_cliente;
    printf("\nCodigo do cliente: ");
    if (scanf("%d", &codigo_cliente) != 1)
    {
        while (getchar() != '\n')
            ;
        printf("\nEntrada invalida.\n");
        return;
    }
    while (getchar() != '\n')
        ;

    if (!cliente_existe(clientes, num_clientes, codigo_cliente))
    {
        printf("\nCliente nao encontrado.\n");
        return;
    }

    int pontos = 0;
    for (int i = 0; i < num_estadias; i++)
    {
        if (estadias[i].codigo_cliente == codigo_cliente)
            pontos += estadias[i].qtd_diarias * 10;
    }
    printf("\nCliente %d: %d pontos de fidelidade.\n", codigo_cliente, pontos);
}
