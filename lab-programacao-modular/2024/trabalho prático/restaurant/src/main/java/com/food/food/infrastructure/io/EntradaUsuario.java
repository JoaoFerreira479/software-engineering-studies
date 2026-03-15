package com.food.food.infrastructure.io;

import java.util.Objects;
import java.util.Scanner;


public final class EntradaUsuario {

    private final Scanner scanner;

    public EntradaUsuario(Scanner scanner) {
        this.scanner = Objects.requireNonNull(scanner, "scanner");
    }

    public String lerLinha(String prompt) {
        Console.print(prompt);
        return scanner.nextLine();
    }

    /**
     * Lê um inteiro. Retorna null se a entrada não for válida.
     */
    public Integer lerInteiro(String prompt) {
        Console.print(prompt);
        if (!scanner.hasNextInt()) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            return null;
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }
}
