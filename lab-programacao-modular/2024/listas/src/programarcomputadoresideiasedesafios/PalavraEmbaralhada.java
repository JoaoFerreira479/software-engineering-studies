package programarcomputadoresideiasedesafios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PalavraEmbaralhada {

    private final String palavraOriginal;
    private final String palavraEmbaralhada;

    public PalavraEmbaralhada(final String palavra) {
        if (palavra == null || palavra.isBlank()) {
            throw new IllegalArgumentException("Palavra não pode ser vazia.");
        }
        this.palavraOriginal = palavra;
        this.palavraEmbaralhada = embaralhar(palavra);
    }

    public String getPalavraOriginal() {
        return palavraOriginal;
    }

    public String getPalavraEmbaralhada() {
        return palavraEmbaralhada;
    }

    private static String embaralhar(final String palavra) {
        final List<Character> letras = new ArrayList<>();
        for (final char c : palavra.toCharArray()) {
            letras.add(c);
        }
        Collections.shuffle(letras);
        final StringBuilder sb = new StringBuilder(letras.size());
        for (final char c : letras) {
            sb.append(c);
        }
        return sb.toString();
    }
}
