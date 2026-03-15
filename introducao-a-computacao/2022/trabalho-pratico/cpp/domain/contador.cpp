#include "contador.hpp"

ResultadoContagem contar_pares_impares(const std::vector<int>& numeros)
{
    ResultadoContagem r;
    for (int n : numeros) {
        if (n % 2 == 0)
            r.pares++;
        else
            r.impares++;
    }
    return r;
}
