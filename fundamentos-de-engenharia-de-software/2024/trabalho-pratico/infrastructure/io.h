#ifndef HDG_IO_H
#define HDG_IO_H

#include <stddef.h>

void io_configurar_locale(void);

void io_print(const char *mensagem);
void io_erro(const char *mensagem);
void io_println(const char *mensagem);

void io_ler_linha(char *buf, size_t tamanho, const char *prompt);
void io_ler_int(const char *prompt, int *out);
void io_ler_float(const char *prompt, float *out);

int io_ler_opcao(void);
void io_exibir_menu(const char *titulo, const char *opcoes[], int n);

#endif
