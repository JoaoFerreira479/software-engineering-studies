package programarcomputadoresarquivos;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public final class EstatisticaDeTexto {

    public static void main(final String[] args) {
        if (args.length == 0) {
            System.err.println("Erro: Nenhum arquivo especificado.");
            return;
        }

        final Path caminho = Path.of(args[0].trim());
        if (!Files.isRegularFile(caminho)) {
            System.err.println("Erro: Arquivo não encontrado ou não é arquivo regular: " + caminho);
            return;
        }

        try {
            final EstatisticasTexto stats = processarArquivo(caminho);
            System.out.print(stats.gerarRelatorio());
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
    }

    static EstatisticasTexto processarArquivo(final Path caminho) throws IOException {
        final EstatisticasTexto stats = new EstatisticasTexto();
        try (BufferedReader reader = Files.newBufferedReader(caminho, StandardCharsets.UTF_8)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                stats.incrementarLinhas();
                stats.adicionarPalavras(contarPalavras(linha));
                contarLetras(linha, stats);
            }
        }
        return stats;
    }

    private static int contarPalavras(final String linha) {
        if (linha == null || linha.isBlank()) {
            return 0;
        }
        return linha.trim().split("\\s+").length;
    }

    private static void contarLetras(final String linha, final EstatisticasTexto stats) {
        if (linha == null) return;
        for (final char c : linha.toCharArray()) {
            if (Character.isLetter(c)) {
                stats.adicionarLetras(1);
                stats.incrementarFrequenciaLetra(c);
            }
        }
    }
}
