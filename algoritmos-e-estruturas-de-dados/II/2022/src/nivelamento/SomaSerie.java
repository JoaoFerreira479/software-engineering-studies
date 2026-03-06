package nivelamento;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class SomaSerie {

    private SomaSerie() {}

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = lerInteiroPositivo(scanner, "Digite um número inteiro e positivo (n): ");
            double soma = calcularSomaSerie(n);
            System.out.printf("Valor final de S: %.4f%n", soma);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.err.println("Erro: Entrada inválida.");
        }
    }

    private static int lerInteiroPositivo(Scanner scanner, String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                int valor = scanner.nextInt();
                if (valor > 0) return valor;
                System.out.println("Erro: O número deve ser maior que zero.");
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Digite um número inteiro válido.");
                scanner.nextLine();
            }
        }
    }

    private static double calcularSomaSerie(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n deve ser positivo: " + n);
        }
        double soma = 0.0;
        System.out.println("Termos gerados:");
        for (int i = 1; i <= n; i++) {
            double termo = 1.0 / i;
            soma += termo;
            System.out.printf("1/%d = %.4f%n", i, termo);
        }
        return soma;
    }
}
