package programarcomputadoresalternativasdecisao;

import java.util.Scanner;

public final class ProgramaTipoTriangulo {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final double lado1 = EntradaUtil.lerDoublePositivo(scanner, "Digite o primeiro lado: ");
            final double lado2 = EntradaUtil.lerDoublePositivo(scanner, "Digite o segundo lado: ");
            final double lado3 = EntradaUtil.lerDoublePositivo(scanner, "Digite o terceiro lado: ");

            final Triangulo triangulo = new Triangulo(lado1, lado2, lado3);
            System.out.println("\n" + triangulo);

            if (triangulo.ehTriangulo()) {
                System.out.println("O triângulo formado é do tipo: " + triangulo.determinarTipo());
            } else {
                System.out.println("Os lados fornecidos não podem formar um triângulo.");
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
