#include "quarto.h"

const Quarto *quarto_por_numero(const Quarto *quartos, int num_quartos, int numero)
{
    if (quartos == NULL)
        return NULL;
    for (int i = 0; i < num_quartos; i++)
    {
        if (quartos[i].numero == numero)
            return &quartos[i];
    }
    return NULL;
}

bool quarto_existe(const Quarto *quartos, int num_quartos, int numero)
{
    return quarto_por_numero(quartos, num_quartos, numero) != NULL;
}
