package introcomp.infrastructure.io;

import java.util.Objects;
import java.util.Scanner;


public final class EntradaUsuario {

    private final Scanner scanner;

    public EntradaUsuario(Scanner scanner) {
        this.scanner = Objects.requireNonNull(scanner, "scanner");
    }

    /** Retorna null se a entrada não for um inteiro válido. */
    public Integer lerInteiro(String prompt) {
        Console.print(prompt);
        if (!scanner.hasNextInt()) {
            if (scanner.hasNext()) {
                scanner.next();
            }
            return null;
        }
        return scanner.nextInt();
    }
}
