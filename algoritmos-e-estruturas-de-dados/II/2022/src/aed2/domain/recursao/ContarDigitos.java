package aed2.domain.recursao;

public final class ContarDigitos {

    private ContarDigitos() {}

    public static int contarDigitos(int numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("Número deve ser não negativo: " + numero);
        }
        if (numero < 10) {
            return 1;
        }
        return 1 + contarDigitos(numero / 10);
    }
}
