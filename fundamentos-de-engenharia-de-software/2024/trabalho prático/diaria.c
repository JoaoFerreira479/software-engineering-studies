#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "diaria.h"

static const int dias_no_mes[] = {
    31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
};

static bool ano_bissexto(int ano)
{
    return (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
}

static int dias_do_mes(int mes, int ano)
{
    if (mes < 1 || mes > 12)
        return 0;
    if (mes == 2 && ano_bissexto(ano))
        return 29;
    return dias_no_mes[mes - 1];
}

bool validarData(const char *data)
{
    if (data == NULL)
        return false;
    size_t len = strlen(data);
    if (len != 10 || data[2] != '/' || data[5] != '/')
        return false;

    int dia, mes, ano;
    if (sscanf(data, "%d/%d/%d", &dia, &mes, &ano) != 3)
        return false;

    if (ano < DIARIA_ANO_MIN || ano > DIARIA_ANO_MAX)
        return false;
    if (mes < 1 || mes > 12)
        return false;
    if (dia < 1 || dia > dias_do_mes(mes, ano))
        return false;

    return true;
}

static void data_para_tm(const char *data, struct tm *out)
{
    int dia, mes, ano;
    (void)sscanf(data, "%d/%d/%d", &dia, &mes, &ano);
    memset(out, 0, sizeof(*out));
    out->tm_mday = dia;
    out->tm_mon  = mes - 1;
    out->tm_year = ano - 1900;
}

int calcularDiarias(const char *data_entrada, const char *data_saida)
{
    if (data_entrada == NULL || data_saida == NULL)
        return -1;
    if (!validarData(data_entrada) || !validarData(data_saida))
        return -1;

    struct tm entrada_tm;
    struct tm saida_tm;
    data_para_tm(data_entrada, &entrada_tm);
    data_para_tm(data_saida, &saida_tm);

    time_t t_entrada = mktime(&entrada_tm);
    time_t t_saida   = mktime(&saida_tm);
    if (t_entrada == (time_t)-1 || t_saida == (time_t)-1)
        return -1;
    if (t_saida <= t_entrada)
        return -1;

    double diff = difftime(t_saida, t_entrada) / (24.0 * 60.0 * 60.0);
    int dias = (int)diff;
    return dias;
}

int compararDatas(const char *a, const char *b)
{
    if (a == NULL || b == NULL)
        return 0;
    if (!validarData(a) || !validarData(b))
        return 0;
    struct tm ta, tb;
    data_para_tm(a, &ta);
    data_para_tm(b, &tb);
    time_t ta_t = mktime(&ta);
    time_t tb_t = mktime(&tb);
    if (ta_t == (time_t)-1 || tb_t == (time_t)-1)
        return 0;
    if (ta_t < tb_t) return -1;
    if (ta_t > tb_t) return  1;
    return 0;
}
