package nivelamento;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class SerieRecursiva {

    private SerieRecursiva() {}

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = lerValorInteiro(scanner, "Digite o valor de N (inteiro positivo): ");
            double resultado = calcularSerie(n);
            System.out.printf("O valor da série S para N = %d é: %.4f%n", n, resultado);
        } catch (InputMismatchException e) {
            System.err.println("Erro: Entrada inválida. Certifique-se de digitar um número inteiro positivo.");
        }
    }

    public static double calcularSerie(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("N deve ser positivo: " + n);
        }
        return calcularSerieAuxiliar(n, 1);
    }

    private static double calcularSerieAuxiliar(int n, int atual) {
        if (atual > n) return 0;
        double termo = (atual % 2 == 0) ? -1.0 / atual : 1.0 / atual;
        return termo + calcularSerieAuxiliar(n, atual + 1);
    }

    private static int lerValorInteiro(Scanner scanner, String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                int valor = scanner.nextInt();
                if (valor > 0) return valor;
                System.out.println("Erro: O valor deve ser maior que zero.");
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Digite um número inteiro positivo.");
                scanner.nextLine();
            }
        }
    }
}
