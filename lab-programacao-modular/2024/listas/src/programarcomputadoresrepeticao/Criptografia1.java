package programarcomputadoresrepeticao;

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
                        System.out.println("Texto codificado: " + codificarTexto(texto));
                    }
                    case 2 -> {
                        final String texto = EntradaUtil.lerLinha(scanner, "Digite a cadeia para decodificar: ");
                        System.out.println("Texto decodificado: " + decodificarTexto(texto));
                    }
                    case 0 -> System.out.println("Encerrando o programa.");
                    default -> System.out.println("Opção inválida. Escolha 1, 2 ou 0.");
                }
            } while (opcao != 0);
        }
    }

    private static void exibirMenu() {
        System.out.println("\n=== Menu de Criptografia ===");
        System.out.println("1 - Codificar");
        System.out.println("2 - Decodificar");
        System.out.println("0 - Sair");
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
