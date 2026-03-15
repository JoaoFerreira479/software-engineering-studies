package programarcomputadoresrepeticao;
import lab.listas.infrastructure.io.Console;
import lab.listas.infrastructure.io.EntradaUtil;

import java.util.Scanner;

public final class InversaoDeCadeia {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Console.println("=== Programa de Inversão de Cadeia ===");
            Console.println("Digite uma cadeia de caracteres para invertê-la.");
            Console.println("Pressione Enter sem digitar nada para encerrar o programa.");

            while (true) {
                final String entrada = EntradaUtil.lerLinha(scanner, "Digite uma cadeia de caracteres: ");
                if (entrada.isEmpty()) {
                    Console.println("Programa encerrado.");
                    break;
                }
                Console.println("Cadeia invertida: " + inverterCadeia(entrada));
                Console.println();
            }
        }
    }

    public static String inverterCadeia(final String cadeia) {
        if (cadeia == null) return "";
        return new StringBuilder(cadeia).reverse().toString();
    }
}
