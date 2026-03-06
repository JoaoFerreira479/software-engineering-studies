package programarcomputadoresvariaveisdados;

public class Aluno {

    private final int notaExercicio1;
    private final int notaExercicio2;
    private final double notaProva;

    public Aluno(int notaExercicio1, int notaExercicio2, double notaProva) {
        this.notaExercicio1 = notaExercicio1;
        this.notaExercicio2 = notaExercicio2;
        this.notaProva = notaProva;
    }

    public int getNotaExercicio1() {
        return notaExercicio1;
    }

    public int getNotaExercicio2() {
        return notaExercicio2;
    }

    public double getNotaProva() {
        return notaProva;
    }
}
