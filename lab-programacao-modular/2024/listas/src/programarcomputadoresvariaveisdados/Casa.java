package programarcomputadoresvariaveisdados;

public class Casa {

    private static final double CENTIMETROS_POR_METRO = 100.0;

    private final double ladoA;
    private final double ladoB;

    public Casa(double ladoA, double ladoB) {
        if (ladoA <= 0 || ladoB <= 0) {
            throw new IllegalArgumentException("Os lados da casa devem ser valores positivos.");
        }
        this.ladoA = ladoA;
        this.ladoB = ladoB;
    }

    public double calcularDiagonal() {
        return Math.sqrt(Math.pow(ladoA, 2) + Math.pow(ladoB, 2));
    }

    public double calcularDiagonalEmCentimetros() {
        return calcularDiagonal() * CENTIMETROS_POR_METRO;
    }
}
