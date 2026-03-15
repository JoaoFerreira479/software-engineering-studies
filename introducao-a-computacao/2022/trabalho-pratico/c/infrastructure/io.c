#include <stdio.h>
#include "io.h"

bool io_ler_int(int *out)
{
    if (out == NULL)
        return false;
    if (scanf("%d", out) != 1) {
        int c;
        while ((c = getchar()) != '\n' && c != EOF)
            ;
        return false;
    }
    return true;
}

void io_print(const char *mensagem)
{
    if (mensagem != NULL)
        fputs(mensagem, stdout);
}

void io_erro(const char *mensagem)
{
    if (mensagem != NULL)
        fputs(mensagem, stderr);
}

void io_exibir_resultado(const ResultadoContagem *r)
{
    if (r == NULL)
        return;
    printf("\n===== Resultado =====\n");
    printf("Quantidade de números pares: %d\n", r->pares);
    printf("Quantidade de números ímpares: %d\n", r->impares);
}
