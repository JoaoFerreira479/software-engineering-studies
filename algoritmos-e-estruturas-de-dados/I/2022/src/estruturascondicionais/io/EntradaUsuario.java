package estruturascondicionais.io;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public final class EntradaUsuario {

    private final Scanner scanner;

    public EntradaUsuario(final Scanner scanner) {
        this.scanner = Objects.requireNonNull(scanner, "scanner");
    }

    public double lerDouble(final String mensagem) {
        while (true) {
            try {
                Console.print(mensagem);
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                Console.erro("Entrada inválida. Digite um número decimal válido.");
                scanner.nextLine();
            }
        }
    }

    public int lerInteiro(final String mensagem) {
        while (true) {
            try {
                Console.print(mensagem);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                Console.erro("Entrada inválida. Digite um número inteiro válido.");
                scanner.nextLine();
            }
        }
    }

    public void fechar() {
        scanner.close();
    }
}
