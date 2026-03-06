package nivelamento;

import java.util.Scanner;

public final class IdentificarSimbolo {

    private IdentificarSimbolo() {}

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            char simbolo = lerSimbolo(scanner);
            String tipoSimbolo = identificarSimbolo(simbolo);
            System.out.println(tipoSimbolo);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    private static char lerSimbolo(Scanner scanner) {
        System.out.print("Digite um símbolo: ");
        String entrada = scanner.next();
        if (entrada == null || entrada.length() != 1) {
            throw new IllegalArgumentException("A entrada deve ser um único caractere.");
        }
        return entrada.charAt(0);
    }

    public static String identificarSimbolo(char simbolo) {
        return switch (simbolo) {
            case '>' -> "Sinal de maior.";
            case '<' -> "Sinal de menor.";
            case '=' -> "Sinal de igual.";
            default -> "Outro sinal.";
        };
    }
}
