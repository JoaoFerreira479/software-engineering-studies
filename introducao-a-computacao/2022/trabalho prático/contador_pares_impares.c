#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct {
    int pares;
    int impares;
} ResultadoContagem;

ResultadoContagem contar_pares_impares(const int *numeros, int n)
{
    ResultadoContagem r = {0, 0};
    if (numeros == NULL || n <= 0)
        return r;

    for (int i = 0; i < n; i++) {
        if (numeros[i] % 2 == 0)
            r.pares++;
        else
            r.impares++;
    }
    return r;
}

void exibir_resultado(const ResultadoContagem *r)
{
    if (r == NULL)
        return;
    printf("\n===== Resultado =====\n");
    printf("Quantidade de números pares: %d\n", r->pares);
    printf("Quantidade de números ímpares: %d\n", r->impares);
}

static bool ler_int(int *out)
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

static int *alocar_numeros(int n)
{
    if (n <= 0)
        return NULL;
    return (int *)malloc((size_t)n * sizeof(int));
}

int main(void)
{
    int quantidade;

    printf("Quantos números deseja inserir? ");
    fflush(stdout);
    if (!ler_int(&quantidade)) {
        fprintf(stderr, "Erro: entrada inválida. Informe um número inteiro.\n");
        return EXIT_FAILURE;
    }
    if (quantidade <= 0) {
        fprintf(stderr, "Erro: a quantidade deve ser maior que zero.\n");
        return EXIT_FAILURE;
    }

    int *numeros = alocar_numeros(quantidade);
    if (numeros == NULL) {
        fprintf(stderr, "Erro: memória insuficiente.\n");
        return EXIT_FAILURE;
    }

    for (int i = 0; i < quantidade; i++) {
        printf("Digite o %dº número: ", i + 1);
        fflush(stdout);
        if (!ler_int(&numeros[i])) {
            fprintf(stderr, "Erro: número inválido na posição %d.\n", i + 1);
            free(numeros);
            return EXIT_FAILURE;
        }
    }

    ResultadoContagem resultado = contar_pares_impares(numeros, quantidade);
    free(numeros);

    exibir_resultado(&resultado);
    return EXIT_SUCCESS;
}
