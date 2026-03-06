package revisao2.io;

import java.util.Objects;
import java.util.Scanner;

public final class EntradaUsuario {

    private final Scanner scanner;

    public EntradaUsuario(final Scanner scanner) {
        this.scanner = Objects.requireNonNull(scanner, "scanner");
    }

    public int lerInteiro(final String mensagem) {
        while (true) {
            try {
                Console.print(mensagem);
                return scanner.nextInt();
            } catch (Exception e) {
                Console.erro("Entrada inválida. Digite um número inteiro válido.");
                scanner.nextLine();
            }
        }
    }

    public void fechar() {
        scanner.close();
    }
}
