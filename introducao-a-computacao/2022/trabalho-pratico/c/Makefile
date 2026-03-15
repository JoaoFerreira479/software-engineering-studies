# Contador pares/ímpares — C (domain / infrastructure / application)
CC = gcc
CFLAGS = -Wall -std=c99 -Idomain -Iinfrastructure -Iapplication
TARGET = contador

all: $(TARGET)

$(TARGET): domain/contador.c infrastructure/io.c application/main.c
	$(CC) $(CFLAGS) -o $@ $^

clean:
	rm -f $(TARGET)

.PHONY: all clean
