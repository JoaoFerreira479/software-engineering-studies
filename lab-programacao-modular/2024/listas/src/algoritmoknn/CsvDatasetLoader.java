package algoritmoknn;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class CsvDatasetLoader {

    private static final String SEPARADOR = ";";
    private static final int CAMPOS_ESPERADOS = 3;

    private CsvDatasetLoader() {
    }

    static List<DataPoint> carregar(final Path caminhoArquivo) throws IOException {
        if (caminhoArquivo == null || !Files.isRegularFile(caminhoArquivo)) {
            throw new IOException("Arquivo não encontrado ou não é arquivo regular: " + caminhoArquivo);
        }

        final List<DataPoint> pontos = new ArrayList<>();
        int numeroLinha = 0;

        try (BufferedReader reader = Files.newBufferedReader(caminhoArquivo, StandardCharsets.UTF_8)) {
            String linha = reader.readLine();
            if (linha == null) {
                throw new IllegalArgumentException("Arquivo CSV vazio");
            }
            numeroLinha++;

            while ((linha = reader.readLine()) != null) {
                numeroLinha++;
                final String trimmed = linha.trim();
                if (trimmed.isEmpty()) {
                    continue;
                }
                pontos.add(parseLinha(trimmed, numeroLinha));
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Linha " + numeroLinha + ": " + e.getMessage(), e);
        }

        if (pontos.isEmpty()) {
            throw new IllegalArgumentException("Nenhum dado válido no CSV");
        }

        return List.copyOf(pontos);
    }

    private static DataPoint parseLinha(final String linha, final int numeroLinha) {
        final String[] partes = linha.split(SEPARADOR, -1);
        if (partes.length < CAMPOS_ESPERADOS) {
            throw new IllegalArgumentException(
                "Formato inválido: esperado 'calorias;horasSono;estado', obtido: " + linha);
        }

        final double calorias = parseDouble(partes[0].trim(), "calorias", numeroLinha);
        final double horasSono = parseDouble(partes[1].trim(), "horas de sono", numeroLinha);
        final String estado = partes[2].trim();

        DomainConstants.validarEntrada(calorias, horasSono);
        if (estado.isEmpty()) {
            throw new IllegalArgumentException("Campo 'estado' vazio");
        }

        return new DataPoint(calorias, horasSono, estado);
    }

    private static double parseDouble(final String valor, final String nomeCampo, final int numeroLinha) {
        try {
            return Double.parseDouble(valor);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                "Linha " + numeroLinha + ": valor inválido para " + nomeCampo + ": '" + valor + "'", e);
        }
    }
}
