package programarcomputadoresalternativasdecisao;

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

    public static int lerInteiroNoIntervalo(final Scanner scanner, final String prompt, final int min, final int max) {
        while (true) {
            final int valor = lerInteiro(scanner, prompt);
            if (valor >= min && valor <= max) {
                return valor;
            }
            System.out.println("O valor deve estar entre " + min + " e " + max + ".");
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

    public static double lerDoublePositivo(final Scanner scanner, final String prompt) {
        while (true) {
            final double valor = lerDouble(scanner, prompt);
            if (valor > 0) {
                return valor;
            }
            System.out.println("O valor deve ser maior que zero.");
        }
    }

    public static double lerDoubleNaoNegativo(final Scanner scanner, final String prompt) {
        while (true) {
            final double valor = lerDouble(scanner, prompt);
            if (valor >= 0) {
                return valor;
            }
            System.out.println("O valor deve ser maior ou igual a zero.");
        }
    }

    public static String lerLinha(final Scanner scanner, final String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
