package programarcomputadoresrepeticao;

import java.util.Scanner;

public final class InversaoDeCadeia {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("=== Programa de Inversão de Cadeia ===");
            System.out.println("Digite uma cadeia de caracteres para invertê-la.");
            System.out.println("Pressione Enter sem digitar nada para encerrar o programa.");

            while (true) {
                final String entrada = EntradaUtil.lerLinha(scanner, "Digite uma cadeia de caracteres: ");
                if (entrada.isEmpty()) {
                    System.out.println("Programa encerrado.");
                    break;
                }
                System.out.println("Cadeia invertida: " + inverterCadeia(entrada));
                System.out.println();
            }
        }
    }

    public static String inverterCadeia(final String cadeia) {
        if (cadeia == null) return "";
        return new StringBuilder(cadeia).reverse().toString();
    }
}
