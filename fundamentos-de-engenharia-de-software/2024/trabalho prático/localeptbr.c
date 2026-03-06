#include <locale.h>
#include <stdio.h>
#include "localeptbr.h"

void configurarLocale(void)
{
    if (setlocale(LC_ALL, "Portuguese_Brazil.1252") == NULL &&
        setlocale(LC_ALL, "Portuguese_Brazil") == NULL &&
        setlocale(LC_ALL, "pt_BR.UTF-8") == NULL)
    {
        (void)fprintf(stderr, "Aviso: locale pt-BR nao disponivel; usando padrao.\n");
    }
}
