#include "contador.h"

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
