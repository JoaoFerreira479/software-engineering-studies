package programarcomputadoresrepeticao;

public final class ValidadorCPF {

    private static final int TAMANHO = 9;

    private ValidadorCPF() {
    }

    public static void validarCPF(final String cpf) {
        if (cpf == null || cpf.length() != TAMANHO || !cpf.matches("\\d+")) {
            throw new IllegalArgumentException("O CPF deve conter exatamente 9 dígitos numéricos.");
        }
    }

    public static int calcularPrimeiroDigito(final String cpf) {
        int soma = calcularSoma(cpf, 10);
        final int resto = soma % 11;
        return resto < 2 ? 0 : 11 - resto;
    }

    public static int calcularSegundoDigito(final String cpf, final int dv1) {
        int soma = calcularSoma(cpf, 11) + dv1 * 2;
        final int resto = soma % 11;
        return resto < 2 ? 0 : 11 - resto;
    }

    private static int calcularSoma(final String cpf, int pesoInicial) {
        int soma = 0;
        for (int i = 0; i < cpf.length(); i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * pesoInicial;
            pesoInicial--;
        }
        return soma;
    }
}
