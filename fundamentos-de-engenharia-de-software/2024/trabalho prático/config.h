#ifndef CONFIG_H
#define CONFIG_H

#define HDG_MAX_CLIENTES    100
#define HDG_MAX_FUNCIONARIOS 100
#define HDG_MAX_ESTADIAS    100
#define HDG_MAX_QUARTOS     100

#define HDG_TAM_NOME        100
#define HDG_TAM_ENDERECO    200
#define HDG_TAM_TELEFONE    15
#define HDG_TAM_CARGO       50
#define HDG_TAM_DATA        11

typedef enum {
    HDG_QUARTO_DESOCUPADO = 0,
    HDG_QUARTO_OCUPADO    = 1
} QuartoStatus;

#endif
