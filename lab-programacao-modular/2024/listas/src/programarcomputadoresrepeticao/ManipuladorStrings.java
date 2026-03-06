package programarcomputadoresrepeticao;

public final class ManipuladorStrings {

    private ManipuladorStrings() {
    }

    public static String inserirHifens(final String palavra) {
        return inserirCaracter(palavra, '-');
    }

    public static String inserirCaracter(final String palavra, final char caractere) {
        if (palavra == null || palavra.isEmpty()) {
            throw new IllegalArgumentException("A palavra não pode estar vazia.");
        }
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < palavra.length(); i++) {
            sb.append(palavra.charAt(i));
            if (i < palavra.length() - 1) {
                sb.append(caractere);
            }
        }
        return sb.toString();
    }
}
