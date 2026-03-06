package programarcomputadoresvariaveisdados;

public class CalculadoraNotas {

    private static final int PESO_EXERCICIO_1 = 1;
    private static final int PESO_EXERCICIO_2 = 2;
    private static final double PESO_EXERCICIOS = 2.0;
    private static final double PESO_PROVA = 8.0;

    public double calcularMediaExercicios(int nota1, int nota2) {
        return (nota1 * PESO_EXERCICIO_1 + nota2 * PESO_EXERCICIO_2) / (double) (PESO_EXERCICIO_1 + PESO_EXERCICIO_2);
    }

    public double calcularNotaFinal(double mediaExercicios, double notaProva) {
        return (mediaExercicios * PESO_EXERCICIOS + notaProva * PESO_PROVA) / (PESO_EXERCICIOS + PESO_PROVA);
    }
}
