package lab.listas.infrastructure.io;

import java.util.Objects;
import java.util.Scanner;


public final class EntradaUtil {

    private EntradaUtil() {
    }

    public static int lerInteiro(Scanner scanner, String prompt) {
        Objects.requireNonNull(scanner, "scanner");
        while (true) {
            Console.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                Console.println("Entrada inválida. Digite um número inteiro.");
            }
        }
    }

    public static int lerInteiroNoIntervalo(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            int valor = lerInteiro(scanner, prompt);
            if (valor >= min && valor <= max) {
                return valor;
            }
            Console.println("O valor deve estar entre " + min + " e " + max + ".");
        }
    }

    public static double lerDouble(Scanner scanner, String prompt) {
        Objects.requireNonNull(scanner, "scanner");
        while (true) {
            Console.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));
            } catch (NumberFormatException e) {
                Console.println("Entrada inválida. Digite um número.");
            }
        }
    }

    public static double lerDoublePositivo(Scanner scanner, String prompt) {
        while (true) {
            double valor = lerDouble(scanner, prompt);
            if (valor > 0) {
                return valor;
            }
            Console.println("O valor deve ser maior que zero.");
        }
    }

    public static double lerDoubleNaoNegativo(Scanner scanner, String prompt) {
        while (true) {
            double valor = lerDouble(scanner, prompt);
            if (valor >= 0) {
                return valor;
            }
            Console.println("O valor deve ser maior ou igual a zero.");
        }
    }

    public static String lerLinha(Scanner scanner, String prompt) {
        Objects.requireNonNull(scanner, "scanner");
        Console.print(prompt);
        return scanner.nextLine().trim();
    }

    public static boolean perguntarSimNao(Scanner scanner, String prompt) {
        String resp = lerLinha(scanner, prompt);
        return "s".equalsIgnoreCase(resp) || "sim".equalsIgnoreCase(resp);
    }

    /** Retorna 'V' ou 'F' (maiúsculo). */
    public static char lerRespostaVF(Scanner scanner, String prompt) {
        while (true) {
            String linha = lerLinha(scanner, prompt);
            if (!linha.isEmpty()) {
                char c = Character.toUpperCase(linha.charAt(0));
                if (c == 'V' || c == 'F') {
                    return c;
                }
            }
            Console.println("Resposta inválida! Digite V ou F.");
        }
    }
}
