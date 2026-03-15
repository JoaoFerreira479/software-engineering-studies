#include "estadia.h"

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
