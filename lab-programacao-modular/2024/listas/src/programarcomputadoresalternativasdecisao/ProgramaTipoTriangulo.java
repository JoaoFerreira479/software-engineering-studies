package programarcomputadoresalternativasdecisao;
import lab.listas.infrastructure.io.Console;
import lab.listas.infrastructure.io.EntradaUtil;

import java.util.Scanner;

public final class ProgramaTipoTriangulo {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final double lado1 = EntradaUtil.lerDoublePositivo(scanner, "Digite o primeiro lado: ");
            final double lado2 = EntradaUtil.lerDoublePositivo(scanner, "Digite o segundo lado: ");
            final double lado3 = EntradaUtil.lerDoublePositivo(scanner, "Digite o terceiro lado: ");

            final Triangulo triangulo = new Triangulo(lado1, lado2, lado3);
            Console.println("\n" + triangulo);

            if (triangulo.ehTriangulo()) {
                Console.println("O triângulo formado é do tipo: " + triangulo.determinarTipo());
            } else {
                Console.println("Os lados fornecidos não podem formar um triângulo.");
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            Console.println("Erro: " + e.getMessage());
        }
    }
}
