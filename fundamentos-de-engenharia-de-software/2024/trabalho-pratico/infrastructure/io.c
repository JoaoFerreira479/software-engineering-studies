#include <locale.h>
#include <stdio.h>
#include <string.h>
#include "io.h"

void io_configurar_locale(void)
{
    if (setlocale(LC_ALL, "Portuguese_Brazil.1252") == NULL &&
        setlocale(LC_ALL, "Portuguese_Brazil") == NULL &&
        setlocale(LC_ALL, "pt_BR.UTF-8") == NULL)
    {
        (void)fprintf(stderr, "Aviso: locale pt-BR nao disponivel; usando padrao.\n");
    }
}

void io_print(const char *mensagem)
{
    if (mensagem != NULL)
        (void)fputs(mensagem, stdout);
}

void io_erro(const char *mensagem)
{
    if (mensagem != NULL)
        (void)fputs(mensagem, stderr);
}

void io_println(const char *mensagem)
{
    if (mensagem != NULL)
        (void)fputs(mensagem, stdout);
    putchar('\n');
}

void io_ler_linha(char *buf, size_t tamanho, const char *prompt)
{
    if (buf == NULL || tamanho == 0)
        return;
    if (prompt != NULL)
        (void)fputs(prompt, stdout);
    if (fgets(buf, (int)tamanho, stdin) == NULL)
        buf[0] = '\0';
    else
        buf[strcspn(buf, "\n")] = '\0';
}

void io_ler_int(const char *prompt, int *out)
{
    if (out == NULL)
        return;
    if (prompt != NULL)
        (void)fputs(prompt, stdout);
    if (scanf("%d", out) != 1)
        *out = -1;
    {
        int c;
        while ((c = getchar()) != '\n' && c != EOF)
            ;
    }
}

void io_ler_float(const char *prompt, float *out)
{
    if (out == NULL)
        return;
    if (prompt != NULL)
        (void)fputs(prompt, stdout);
    if (scanf("%f", out) != 1)
        *out = -1.0f;
    {
        int c;
        while ((c = getchar()) != '\n' && c != EOF)
            ;
    }
}

int io_ler_opcao(void)
{
    int op;
    if (scanf("%d", &op) != 1)
    {
        int c;
        while ((c = getchar()) != '\n' && c != EOF)
            ;
        return -1;
    }
    {
        int c;
        while ((c = getchar()) != '\n' && c != EOF)
            ;
    }
    return op;
}

void io_exibir_menu(const char *titulo, const char *opcoes[], int n)
{
    if (titulo != NULL)
        printf("\n-------- %s --------\n", titulo);
    if (opcoes != NULL)
        for (int i = 0; i < n; i++)
            if (opcoes[i] != NULL)
                printf("%s\n", opcoes[i]);
    printf("\nOpcao: ");
}
