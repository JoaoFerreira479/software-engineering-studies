package programarcomputadoresvariaveisdados;

public class Premio {

    private static final int MIN_NUMERO = 100_000;
    private static final int MAX_NUMERO = 999_999;

    private final int numero;

    public Premio(int numero) {
        if (!validarNumero(numero)) {
            throw new IllegalArgumentException("O número do prêmio deve ter exatamente 6 dígitos.");
        }
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public String getTresUltimosDigitos() {
        return String.format("%03d", numero % 1000);
    }

    private static boolean validarNumero(int numero) {
        return numero >= MIN_NUMERO && numero <= MAX_NUMERO;
    }
}
