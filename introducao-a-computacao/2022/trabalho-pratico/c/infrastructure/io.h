#ifndef IC_IO_H
#define IC_IO_H

#include "resultado.h"
#include <stdbool.h>

bool io_ler_int(int *out);
void io_print(const char *mensagem);
void io_erro(const char *mensagem);
void io_exibir_resultado(const ResultadoContagem *r);

#endif
