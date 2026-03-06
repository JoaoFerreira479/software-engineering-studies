package programarcomputadoresrepeticao;

import java.util.Scanner;

public final class LinhaReta {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("=== Desenho de Linha Reta ===");
            final double inclinacao = EntradaUtil.lerDouble(scanner, "Digite a inclinação da reta (a): ");
            final double interceptacao = EntradaUtil.lerDouble(scanner, "Digite a interceptação com o eixo y (b): ");
            final int xInicial = EntradaUtil.lerInteiro(scanner, "Digite o valor inicial de x: ");
            final int xFinal = EntradaUtil.lerInteiro(scanner, "Digite o valor final de x: ");

            if (xFinal < xInicial) {
                System.out.println("Erro: O valor final de x deve ser maior ou igual ao valor inicial.");
                return;
            }
            desenharLinha(inclinacao, interceptacao, xInicial, xFinal);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void desenharLinha(final double inclinacao, final double interceptacao, final int xInicial, final int xFinal) {
        System.out.println("\nPontos da linha reta:");
        System.out.println("x\ty");
        System.out.println("-------------");
        for (int x = xInicial; x <= xFinal; x++) {
            final double y = calcularY(inclinacao, interceptacao, x);
            System.out.printf("%d\t%.2f%n", x, y);
        }
    }

    public static double calcularY(final double inclinacao, final double interceptacao, final int x) {
        return inclinacao * x + interceptacao;
    }
}
