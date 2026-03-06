package programarcomputadoresarquivos;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public final class ContadorDeCaracteres {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Digite o caminho do arquivo: ");
            final String caminhoStr = scanner.nextLine().trim();
            if (caminhoStr.isEmpty()) {
                System.out.println("Erro: O caminho não pode ser vazio.");
                return;
            }

            final Path caminho = Path.of(caminhoStr);
            if (!Files.isRegularFile(caminho)) {
                System.out.println("Erro: O caminho especificado não existe ou não é um arquivo.");
                return;
            }

            final int total = contarCaracteres(caminho);
            System.out.println("A quantidade de caracteres no arquivo é: " + total);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo.");
        }
    }

    /**
     * Soma o comprimento de cada linha (quebras de linha não entram na contagem).
     */
    static int contarCaracteres(final Path caminho) throws IOException {
        int total = 0;
        try (BufferedReader reader = Files.newBufferedReader(caminho, StandardCharsets.UTF_8)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                total += linha.length();
            }
        }
        return total;
    }
}
