package programarcomputadoresideiasedesafios;

import java.util.Scanner;

public final class EntradaUtil {

    private EntradaUtil() {
    }

    public static int lerInteiro(final Scanner scanner, final String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }
    }

    public static double lerDouble(final Scanner scanner, final String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
            }
        }
    }

    public static String lerLinha(final Scanner scanner, final String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    /** Retorna 'V' ou 'F' (maiúsculo). */
    public static char lerRespostaVF(final Scanner scanner, final String prompt) {
        while (true) {
            final String linha = lerLinha(scanner, prompt);
            if (linha.length() >= 1) {
                final char c = Character.toUpperCase(linha.charAt(0));
                if (c == 'V' || c == 'F') return c;
            }
            System.out.println("Resposta inválida! Digite V ou F.");
        }
    }
}
