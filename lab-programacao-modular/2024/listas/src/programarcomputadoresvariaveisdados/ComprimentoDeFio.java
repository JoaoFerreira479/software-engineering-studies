package programarcomputadoresvariaveisdados;

import java.util.Scanner;

public class ComprimentoDeFio {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            double ladoA = EntradaUtil.lerDouble(scanner,
                    "Digite o comprimento da casa (lado A em metros): ");
            double ladoB = EntradaUtil.lerDouble(scanner,
                    "Digite a largura da casa (lado B em metros): ");

            try {
                Casa casa = new Casa(ladoA, ladoB);

                double diagonal = casa.calcularDiagonal();
                double diagonalEmCentimetros = casa.calcularDiagonalEmCentimetros();

                exibirResultados(diagonal, diagonalEmCentimetros);
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void exibirResultados(double diagonal, double diagonalEmCentimetros) {
        System.out.println("\n--- Cálculo do Comprimento de Fio ---");
        System.out.printf("Diagonal da casa (mínimo de fio necessário): %.2f metros\n", diagonal);
        System.out.printf("Diagonal em centímetros: %.2f cm\n", diagonalEmCentimetros);
    }
}
