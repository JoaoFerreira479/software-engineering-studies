package programarcomputadoresarquivos;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public final class CriptografiaDeArquivos {

    private static final int DESLOCAMENTO_CRIPTOGRAFAR = 1;
    private static final int DESLOCAMENTO_DESCRIPTOGRAFAR = -1;

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                exibirMenu();
                final int opcao = validarOpcao(scanner);

                if (opcao == 0) {
                    System.out.println("Encerrando o programa...");
                    break;
                }

                final Path entrada = Path.of(lerLinha(scanner, "Digite o caminho do arquivo de entrada: "));
                final Path saida = Path.of(lerLinha(scanner, "Digite o caminho do arquivo de saída: "));

                switch (opcao) {
                    case 1 -> criptografarArquivo(entrada, saida);
                    case 2 -> descriptografarArquivo(entrada, saida);
                    default -> System.out.println("Opção inválida.");
                }
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n=== Criptografia de Arquivos ===");
        System.out.println("1 - Criptografar Arquivo");
        System.out.println("2 - Descriptografar Arquivo");
        System.out.println("0 - Sair");
    }

    public static void criptografarArquivo(final Path caminhoEntrada, final Path caminhoSaida) {
        processarArquivo(caminhoEntrada, caminhoSaida, DESLOCAMENTO_CRIPTOGRAFAR);
    }

    public static void descriptografarArquivo(final Path caminhoEntrada, final Path caminhoSaida) {
        processarArquivo(caminhoEntrada, caminhoSaida, DESLOCAMENTO_DESCRIPTOGRAFAR);
    }

    private static void processarArquivo(final Path caminhoEntrada, final Path caminhoSaida, final int deslocamento) {
        try (InputStream in = Files.newInputStream(caminhoEntrada);
             OutputStream out = Files.newOutputStream(caminhoSaida)) {
            int b;
            while ((b = in.read()) != -1) {
                out.write((b + deslocamento) & 0xFF);
            }
            System.out.println("Operação concluída com sucesso. Arquivo salvo em: " + caminhoSaida);
        } catch (IOException e) {
            System.out.println("Erro ao processar o arquivo: " + e.getMessage());
        }
    }

    private static int validarOpcao(final Scanner scanner) {
        while (true) {
            System.out.print("Escolha uma opção: ");
            try {
                final int opcao = Integer.parseInt(scanner.nextLine().trim());
                if (opcao >= 0 && opcao <= 2) {
                    return opcao;
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Opção inválida. Digite 0, 1 ou 2.");
        }
    }

    private static String lerLinha(final Scanner scanner, final String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
