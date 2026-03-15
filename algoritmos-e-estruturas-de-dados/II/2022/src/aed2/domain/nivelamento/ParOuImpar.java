package aed2.domain.nivelamento;

public final class ParOuImpar {

    private ParOuImpar() {}

    public static String classificar(int numero) {
        return numero % 2 == 0 ? "PAR" : "ÍMPAR";
    }
}
