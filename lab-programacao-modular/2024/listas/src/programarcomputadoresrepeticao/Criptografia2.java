package programarcomputadoresrepeticao;

import java.util.Scanner;

public final class Criptografia2 {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int opcao;
            do {
                exibirMenu();
                opcao = EntradaUtil.lerInteiro(scanner, "Escolha uma opção: ");
                switch (opcao) {
                    case 1 -> {
                        final String input = EntradaUtil.lerLinha(scanner, "Digite a cadeia a ser criptografada: ");
                        System.out.println("Cadeia criptografada: " + criptografar(input));
                    }
                    case 2 -> {
                        final String input = EntradaUtil.lerLinha(scanner, "Digite a cadeia a ser decifrada: ");
                        try {
                            System.out.println("Cadeia decifrada: " + decifrar(input));
                        } catch (IllegalArgumentException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                    }
                    case 3 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida. Escolha uma opção entre 1 e 3.");
                }
            } while (opcao != 3);
        }
    }

    private static void exibirMenu() {
        System.out.println("=== MENU ===");
        System.out.println("1. Criptografar");
        System.out.println("2. Decifrar");
        System.out.println("3. Sair");
    }

    public static String criptografar(String texto) {
        texto = texto != null ? texto.replaceAll("\\s+", "") : "";
        final int length = texto.length();
        final int rows = (int) Math.ceil((double) length / 5);
        final char[][] matriz = new char[rows][5];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < 5; j++) {
                matriz[i][j] = index < length ? texto.charAt(index++) : ' ';
            }
        }
        final StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < rows; i++) {
                if (matriz[i][j] != ' ') sb.append(matriz[i][j]);
            }
            if (j < 4) sb.append("/");
        }
        return sb.toString();
    }

    public static String decifrar(final String textoCriptografado) {
        if (textoCriptografado == null) return "";
        final String[] colunas = textoCriptografado.split("/");
        if (colunas.length != 5) {
            throw new IllegalArgumentException("Formato inválido. O texto deve conter 5 colunas separadas por '/'.");
        }
        final int[] tamanhos = new int[5];
        int totalCaracteres = 0;
        for (int i = 0; i < 5; i++) {
            tamanhos[i] = colunas[i].length();
            totalCaracteres += tamanhos[i];
        }
        final int rows = (int) Math.ceil((double) totalCaracteres / 5);
        final char[][] matriz = new char[rows][5];
        for (int j = 0; j < 5; j++) {
            final char[] caracteres = colunas[j].toCharArray();
            for (int i = 0; i < caracteres.length; i++) {
                matriz[i][j] = caracteres[i];
            }
        }
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < 5; j++) {
                if (matriz[i][j] != '\0') sb.append(matriz[i][j]);
            }
        }
        return sb.toString();
    }
}
