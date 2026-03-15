# Hotel Descanso Garantido — compilação com gcc/clang
# Uso: make   ou   make hdg

CC = gcc
CFLAGS = -Wall -Wextra -std=c11 -Idomain -Iinfrastructure -Iapplication
TARGET = hdg

DOMAIN_SRC = domain/cliente.c domain/funcionario.c domain/quarto.c domain/estadia.c domain/diaria.c
INFRA_SRC = infrastructure/io.c infrastructure/arquivo.c
APP_SRC = application/main.c application/cliente_app.c application/funcionario_app.c \
          application/quarto_app.c application/estadia_app.c

SRCS = $(DOMAIN_SRC) $(INFRA_SRC) $(APP_SRC)

all: $(TARGET)

$(TARGET): $(SRCS)
	$(CC) $(CFLAGS) -o $@ $(SRCS)

clean:
	rm -f $(TARGET)

.PHONY: all clean
