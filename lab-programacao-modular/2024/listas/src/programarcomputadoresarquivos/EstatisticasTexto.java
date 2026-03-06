package programarcomputadoresarquivos;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class EstatisticasTexto {

    private int quantidadeLinhas;
    private int quantidadePalavras;
    private int quantidadeLetras;
    private final Map<Character, Integer> frequenciaLetras;

    public EstatisticasTexto() {
        this.frequenciaLetras = new HashMap<>();
    }

    public void incrementarLinhas() {
        quantidadeLinhas++;
    }

    public void adicionarPalavras(final int palavras) {
        quantidadePalavras += palavras;
    }

    public void adicionarLetras(final int letras) {
        quantidadeLetras += letras;
    }

    public void incrementarFrequenciaLetra(final char letra) {
        frequenciaLetras.merge(Character.toLowerCase(letra), 1, Integer::sum);
    }

    public int getQuantidadeLinhas() {
        return quantidadeLinhas;
    }

    public int getQuantidadePalavras() {
        return quantidadePalavras;
    }

    public int getQuantidadeLetras() {
        return quantidadeLetras;
    }

    public Map<Character, Integer> getFrequenciaLetras() {
        return Collections.unmodifiableMap(new HashMap<>(frequenciaLetras));
    }

    /**
     * Gera o relatório em texto (sem I/O).
     */
    public String gerarRelatorio() {
        final StringBuilder sb = new StringBuilder();
        sb.append("=== Estatísticas do Arquivo ===\n");
        sb.append("Quantidade de linhas: ").append(quantidadeLinhas).append("\n");
        sb.append("Quantidade de palavras: ").append(quantidadePalavras).append("\n");
        sb.append("Quantidade de letras: ").append(quantidadeLetras).append("\n");
        sb.append("Frequência de cada letra:\n");
        frequenciaLetras.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .forEach(e -> sb.append(e.getKey()).append(": ").append(e.getValue()).append("\n"));
        return sb.toString();
    }
}
