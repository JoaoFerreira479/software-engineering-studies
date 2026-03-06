package programarcomputadoresalternativasdecisao;

public final class Triangulo {

    private final double lado1;
    private final double lado2;
    private final double lado3;

    public Triangulo(final double lado1, final double lado2, final double lado3) {
        if (lado1 <= 0 || lado2 <= 0 || lado3 <= 0) {
            throw new IllegalArgumentException("Os lados devem ser maiores que zero.");
        }
        this.lado1 = lado1;
        this.lado2 = lado2;
        this.lado3 = lado3;
    }

    public boolean ehTriangulo() {
        return lado1 < lado2 + lado3 && lado2 < lado1 + lado3 && lado3 < lado1 + lado2;
    }

    /**
     * Tipo do triângulo. Deve ser chamado apenas se ehTriangulo() for true.
     */
    public String determinarTipo() {
        if (!ehTriangulo()) {
            throw new IllegalStateException("Os lados fornecidos não formam um triângulo.");
        }
        if (lado1 == lado2 && lado2 == lado3) {
            return "Equilátero";
        }
        if (lado1 == lado2 || lado2 == lado3 || lado1 == lado3) {
            return "Isósceles";
        }
        return "Escaleno";
    }

    @Override
    public String toString() {
        return String.format("Lados do triângulo: %.2f, %.2f, %.2f", lado1, lado2, lado3);
    }
}
