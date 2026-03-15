package aed2.domain.recursao;

public final class RestoRecursivo {

    private RestoRecursivo() {}

    public static int calcularResto(int numerador, int denominador) {
        if (denominador <= 0) {
            throw new IllegalArgumentException("Denominador deve ser positivo: " + denominador);
        }
        if (numerador < denominador) return numerador;
        return calcularResto(numerador - denominador, denominador);
    }
}
