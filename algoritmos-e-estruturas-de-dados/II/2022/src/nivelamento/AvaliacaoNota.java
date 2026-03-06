package nivelamento;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class AvaliacaoNota {

    private static final double NOTA_MAXIMA = 10.0;
    private static final double NOTA_MINIMA = 0.0;

    private AvaliacaoNota() {}

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            double nota = lerNota(scanner);
            String classificacao = classificarNota(nota);
            System.out.printf("A classificação da nota %.1f é: %s%n", nota, classificacao);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.err.println("Erro: Entrada inválida. Certifique-se de digitar um número decimal válido.");
        }
    }

    private static double lerNota(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Digite a nota (entre 0 e 10): ");
                double nota = scanner.nextDouble();
                if (nota < NOTA_MINIMA || nota > NOTA_MAXIMA) {
                    throw new IllegalArgumentException("Nota inválida! Certifique-se de digitar uma nota entre 0 e 10.");
                }
                return nota;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Digite um número decimal válido.");
                scanner.nextLine();
            }
        }
    }

    public static String classificarNota(double nota) {
        if (nota < NOTA_MINIMA || nota > NOTA_MAXIMA) {
            throw new IllegalArgumentException("Nota fora do intervalo [0, 10]: " + nota);
        }
        if (nota >= 8.0) return "Ótimo";
        if (nota >= 7.0) return "Bom";
        if (nota >= 5.0) return "Regular";
        return "Insatisfatório";
    }
}
