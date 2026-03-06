package programarcomputadoresalternativasdecisao;

public final class PisPasep {

    private static final int[] PESOS = { 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
    private static final int TAMANHO_NUMERO = 10;

    private PisPasep() {
    }

    /**
     * Retorna o dígito verificador (0 a 9). Número deve ter exatamente 10 dígitos.
     */
    public static int calcularDigitoVerificador(final String numero) {
        if (numero == null || numero.length() != TAMANHO_NUMERO || !numero.matches("\\d+")) {
            throw new IllegalArgumentException("O número deve conter exatamente 10 dígitos numéricos.");
        }
        int soma = 0;
        for (int i = 0; i < TAMANHO_NUMERO; i++) {
            soma += Character.getNumericValue(numero.charAt(i)) * PESOS[i];
        }
        final int resto = soma % 11;
        final int digito = 11 - resto;
        return (digito == 10 || digito == 11) ? 0 : digito;
    }
}
