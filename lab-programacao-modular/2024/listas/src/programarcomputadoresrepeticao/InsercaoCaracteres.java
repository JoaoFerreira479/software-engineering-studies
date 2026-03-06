package programarcomputadoresrepeticao;

import java.util.Scanner;

public final class InsercaoCaracteres {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean continuar = true;
            while (continuar) {
                exibirMenu();
                final int opcao = EntradaUtil.lerInteiro(scanner, "Escolha uma opção: ");

                switch (opcao) {
                    case 1 -> processarHifens(scanner);
                    case 2 -> processarCaracterPersonalizado(scanner);
                    case 3 -> {
                        continuar = false;
                        System.out.println("Encerrando o programa.");
                    }
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void exibirMenu() {
        System.out.println("\n=== Opções ===");
        System.out.println("1. Inserir hifens entre caracteres");
        System.out.println("2. Inserir um caractere personalizado entre caracteres");
        System.out.println("3. Sair");
    }

    private static void processarHifens(final Scanner scanner) {
        final String palavra = EntradaUtil.lerLinha(scanner, "Digite uma palavra: ");
        if (palavra.isEmpty()) {
            System.out.println("Erro: A palavra não pode estar vazia.");
            return;
        }
        System.out.println("Resultado: " + ManipuladorStrings.inserirHifens(palavra));
    }

    private static void processarCaracterPersonalizado(final Scanner scanner) {
        final String palavra = EntradaUtil.lerLinha(scanner, "Digite uma palavra: ");
        final String entradaChar = EntradaUtil.lerLinha(scanner, "Digite o caractere a ser inserido: ");
        if (palavra.isEmpty()) {
            System.out.println("Erro: A palavra não pode estar vazia.");
            return;
        }
        if (entradaChar.length() != 1) {
            System.out.println("Erro: Você deve inserir exatamente um caractere.");
            return;
        }
        System.out.println("Resultado: " + ManipuladorStrings.inserirCaracter(palavra, entradaChar.charAt(0)));
    }
}
