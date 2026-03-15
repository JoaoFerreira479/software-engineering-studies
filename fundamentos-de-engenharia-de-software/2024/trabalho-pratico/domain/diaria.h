#ifndef HDG_DIARIA_H
#define HDG_DIARIA_H

#include <stdbool.h>

#define DIARIA_FORMATO "dd/mm/aaaa"
#define DIARIA_ANO_MIN 1900
#define DIARIA_ANO_MAX 2100

bool validarData(const char *data);
int calcularDiarias(const char *data_entrada, const char *data_saida);
int compararDatas(const char *a, const char *b);

#endif
