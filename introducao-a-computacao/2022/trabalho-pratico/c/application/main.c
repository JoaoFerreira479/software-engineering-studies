#include <stdlib.h>
#include "resultado.h"
#include "contador.h"
#include "io.h"

static int *alocar_numeros(int n)
{
    if (n <= 0)
        return NULL;
    return (int *)malloc((size_t)n * sizeof(int));
}

int main(void)
{
    int quantidade;

    io_print("Quantos números deseja inserir? ");
    fflush(stdout);
    if (!io_ler_int(&quantidade)) {
        io_erro("Erro: entrada inválida. Informe um número inteiro.\n");
        return EXIT_FAILURE;
    }
    if (quantidade <= 0) {
        io_erro("Erro: a quantidade deve ser maior que zero.\n");
        return EXIT_FAILURE;
    }

    int *numeros = alocar_numeros(quantidade);
    if (numeros == NULL) {
        io_erro("Erro: memória insuficiente.\n");
        return EXIT_FAILURE;
    }

    for (int i = 0; i < quantidade; i++) {
        printf("Digite o %dº número: ", i + 1);
        fflush(stdout);
        if (!io_ler_int(&numeros[i])) {
            io_erro("Erro: número inválido na posição.\n");
            free(numeros);
            return EXIT_FAILURE;
        }
    }

    ResultadoContagem resultado = contar_pares_impares(numeros, quantidade);
    free(numeros);

    io_exibir_resultado(&resultado);
    return EXIT_SUCCESS;
}
