package programarcomputadoresbasicos;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class PalavraGrande {

    private static final int ALTURA_LETRA = 7;
    private static final Map<Character, String[]> PADROES = criarPadroes();

    private static Map<Character, String[]> criarPadroes() {
        final Map<Character, String[]> m = new HashMap<>();
        m.put('S', new String[] {
            " ******** ", " *        ", " *        ", " ******** ", "        * ", "        * ", " ******** "
        });
        m.put('O', new String[] {
            " ******** ", " *      * ", " *      * ", " *      * ", " *      * ", " *      * ", " ******** "
        });
        m.put('L', new String[] {
            " *        ", " *        ", " *        ", " *        ", " *        ", " *        ", " ******** "
        });
        return Collections.unmodifiableMap(m);
    }

    public static void main(final String[] args) {
        imprimirPalavra("SOL");
    }

    /**
     * Imprime a palavra em letras grandes (apenas caracteres com padrão definido: S, O, L).
     */
    public static void imprimirPalavra(final String palavra) {
        if (palavra == null || palavra.isBlank()) {
            System.out.println("Erro: Palavra não pode ser vazia.");
            return;
        }
        final char[] letras = palavra.toUpperCase().toCharArray();
        for (final char letra : letras) {
            if (!PADROES.containsKey(letra)) {
                System.out.println("Erro: Padrão não definido para o caractere '" + letra + "'.");
                return;
            }
        }
        for (int linha = 0; linha < ALTURA_LETRA; linha++) {
            final StringBuilder sb = new StringBuilder();
            for (final char letra : letras) {
                sb.append(PADROES.get(letra)[linha]).append("   ");
            }
            System.out.println(sb);
        }
    }
}
