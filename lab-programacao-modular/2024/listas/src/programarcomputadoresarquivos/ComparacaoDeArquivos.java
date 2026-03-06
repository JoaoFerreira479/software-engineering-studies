package programarcomputadoresarquivos;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public final class ComparacaoDeArquivos {

    public static int compararArquivos(final Path caminho1, final Path caminho2) throws IOException {
        try (BufferedReader r1 = Files.newBufferedReader(caminho1, StandardCharsets.UTF_8);
             BufferedReader r2 = Files.newBufferedReader(caminho2, StandardCharsets.UTF_8)) {
            int posicao = 1;
            int c1;
            int c2;
            while ((c1 = r1.read()) != -1 && (c2 = r2.read()) != -1) {
                if (c1 != c2) {
                    return posicao;
                }
                posicao++;
            }
            if (r1.read() != -1 || r2.read() != -1) {
                return posicao;
            }
        }
        return -1;
    }

    private static void validarCaminho(final Path caminho) {
        if (caminho == null) {
            throw new IllegalArgumentException("O caminho do arquivo não pode ser nulo.");
        }
        if (!Files.isRegularFile(caminho)) {
            throw new IllegalArgumentException("O arquivo \"" + caminho + "\" não existe.");
        }
        if (!Files.isReadable(caminho)) {
            throw new IllegalArgumentException("O arquivo \"" + caminho + "\" não pode ser lido.");
        }
    }

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Digite o caminho do primeiro arquivo: ");
            final String s1 = scanner.nextLine().trim();
            System.out.print("Digite o caminho do segundo arquivo: ");
            final String s2 = scanner.nextLine().trim();
            if (s1.isEmpty() || s2.isEmpty()) {
                System.out.println("Erro: O caminho do arquivo não pode ser vazio.");
                return;
            }
            final Path caminho1 = Path.of(s1);
            final Path caminho2 = Path.of(s2);
            validarCaminho(caminho1);
            validarCaminho(caminho2);

            final int diferenca = compararArquivos(caminho1, caminho2);

            if (diferenca == -1) {
                System.out.println("Os arquivos são iguais.");
            } else {
                System.out.println("Os arquivos diferem no caractere de número: " + diferenca);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro ao acessar os arquivos: " + e.getMessage());
        }
    }
}
