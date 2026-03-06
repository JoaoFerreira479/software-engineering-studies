package programarcomputadoresrepeticao;

public final class CPF {

    private static final int TAMANHO_BASE = 9;

    private CPF() {
    }

    public static void validarEntrada(final String cpf) {
        if (cpf == null || cpf.length() != TAMANHO_BASE || !cpf.matches("\\d+")) {
            throw new IllegalArgumentException("CPF inválido. Deve conter exatamente 9 dígitos numéricos.");
        }
    }

    public static int calcularDV1(final int[] cpf) {
        int soma = 0;
        for (int i = 0; i < TAMANHO_BASE; i++) {
            soma += cpf[i] * (10 - i);
        }
        final int resto = soma % 11;
        return resto < 2 ? 0 : 11 - resto;
    }

    public static int calcularDV2(final int[] cpf, final int dv1) {
        int soma = 0;
        for (int i = 0; i < TAMANHO_BASE; i++) {
            soma += cpf[i] * (11 - i);
        }
        soma += dv1 * 2;
        final int resto = soma % 11;
        return resto < 2 ? 0 : 11 - resto;
    }

    public static int calcularNumeroDeControle(final int dv1, final int dv2) {
        return dv1 * 10 + dv2;
    }
}
