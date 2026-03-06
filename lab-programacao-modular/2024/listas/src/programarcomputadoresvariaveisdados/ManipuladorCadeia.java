package programarcomputadoresvariaveisdados;

public final class ManipuladorCadeia {

    public static final int TAMANHO_MAXIMO = 255;

    private ManipuladorCadeia() {
    }

    public static String[] dividirCadeia(String cadeia) {
        if (cadeia == null || cadeia.isEmpty()) {
            throw new IllegalArgumentException("Cadeia não pode ser nula ou vazia.");
        }
        int pontoMedio = cadeia.length() / 2;
        String primeiraMetade = cadeia.substring(0, pontoMedio);
        String segundaMetade = cadeia.substring(pontoMedio);
        return new String[] { primeiraMetade, segundaMetade };
    }

    public static boolean validarCadeia(String cadeia) {
        return cadeia != null && !cadeia.isEmpty() && cadeia.length() <= TAMANHO_MAXIMO;
    }
}
