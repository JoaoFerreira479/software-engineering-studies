package programarcomputadoresrepeticao;
import lab.listas.infrastructure.io.Console;
import lab.listas.infrastructure.io.EntradaUtil;

import java.util.Scanner;

public final class Criptografia1 {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int opcao;
            do {
                exibirMenu();
                opcao = EntradaUtil.lerInteiro(scanner, "Escolha uma opção: ");
                switch (opcao) {
                    case 1 -> {
                        final String texto = EntradaUtil.lerLinha(scanner, "Digite a cadeia para codificar: ");
                        Console.println("Texto codificado: " + codificarTexto(texto));
                    }
                    case 2 -> {
                        final String texto = EntradaUtil.lerLinha(scanner, "Digite a cadeia para decodificar: ");
                        Console.println("Texto decodificado: " + decodificarTexto(texto));
                    }
                    case 0 -> Console.println("Encerrando o programa.");
                    default -> Console.println("Opção inválida. Escolha 1, 2 ou 0.");
                }
            } while (opcao != 0);
        }
    }

    private static void exibirMenu() {
        Console.println("\n=== Menu de Criptografia ===");
        Console.println("1 - Codificar");
        Console.println("2 - Decodificar");
        Console.println("0 - Sair");
    }

    public static String codificarTexto(final String texto) {
        if (texto == null) return "";
        final StringBuilder sb = new StringBuilder(texto.length());
        for (final char c : texto.toCharArray()) {
            sb.append((char) (c + 1));
        }
        return sb.toString();
    }

    public static String decodificarTexto(final String texto) {
        if (texto == null) return "";
        final StringBuilder sb = new StringBuilder(texto.length());
        for (final char c : texto.toCharArray()) {
            sb.append((char) (c - 1));
        }
        return sb.toString();
    }
}
