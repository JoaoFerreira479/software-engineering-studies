package programarcomputadoresrepeticao;
import lab.listas.infrastructure.io.Console;
import lab.listas.infrastructure.io.EntradaUtil;

import java.util.Scanner;

public final class LinhaReta {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Console.println("=== Desenho de Linha Reta ===");
            final double inclinacao = EntradaUtil.lerDouble(scanner, "Digite a inclinação da reta (a): ");
            final double interceptacao = EntradaUtil.lerDouble(scanner, "Digite a interceptação com o eixo y (b): ");
            final int xInicial = EntradaUtil.lerInteiro(scanner, "Digite o valor inicial de x: ");
            final int xFinal = EntradaUtil.lerInteiro(scanner, "Digite o valor final de x: ");

            if (xFinal < xInicial) {
                Console.println("Erro: O valor final de x deve ser maior ou igual ao valor inicial.");
                return;
            }
            desenharLinha(inclinacao, interceptacao, xInicial, xFinal);
        } catch (IllegalArgumentException e) {
            Console.println("Erro: " + e.getMessage());
        }
    }

    public static void desenharLinha(final double inclinacao, final double interceptacao, final int xInicial, final int xFinal) {
        Console.println("\nPontos da linha reta:");
        Console.println("x\ty");
        Console.println("-------------");
        for (int x = xInicial; x <= xFinal; x++) {
            final double y = calcularY(inclinacao, interceptacao, x);
            Console.printf("%d\t%.2f%n", x, y);
        }
    }

    public static double calcularY(final double inclinacao, final double interceptacao, final int x) {
        return inclinacao * x + interceptacao;
    }
}
