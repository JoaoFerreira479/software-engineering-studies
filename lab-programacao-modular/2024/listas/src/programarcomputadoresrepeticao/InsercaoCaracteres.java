package programarcomputadoresrepeticao;
import lab.listas.infrastructure.io.Console;
import lab.listas.infrastructure.io.EntradaUtil;

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
                        Console.println("Encerrando o programa.");
                    }
                    default -> Console.println("Opção inválida. Tente novamente.");
                }
            }
        } catch (IllegalArgumentException e) {
            Console.println("Erro: " + e.getMessage());
        }
    }

    private static void exibirMenu() {
        Console.println("\n=== Opções ===");
        Console.println("1. Inserir hifens entre caracteres");
        Console.println("2. Inserir um caractere personalizado entre caracteres");
        Console.println("3. Sair");
    }

    private static void processarHifens(final Scanner scanner) {
        final String palavra = EntradaUtil.lerLinha(scanner, "Digite uma palavra: ");
        if (palavra.isEmpty()) {
            Console.println("Erro: A palavra não pode estar vazia.");
            return;
        }
        Console.println("Resultado: " + ManipuladorStrings.inserirHifens(palavra));
    }

    private static void processarCaracterPersonalizado(final Scanner scanner) {
        final String palavra = EntradaUtil.lerLinha(scanner, "Digite uma palavra: ");
        final String entradaChar = EntradaUtil.lerLinha(scanner, "Digite o caractere a ser inserido: ");
        if (palavra.isEmpty()) {
            Console.println("Erro: A palavra não pode estar vazia.");
            return;
        }
        if (entradaChar.length() != 1) {
            Console.println("Erro: Você deve inserir exatamente um caractere.");
            return;
        }
        Console.println("Resultado: " + ManipuladorStrings.inserirCaracter(palavra, entradaChar.charAt(0)));
    }
}
