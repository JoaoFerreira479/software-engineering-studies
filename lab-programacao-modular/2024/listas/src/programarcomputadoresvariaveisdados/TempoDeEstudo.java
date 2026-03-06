package programarcomputadoresvariaveisdados;

public class TempoDeEstudo {

    private static final int MINUTOS_POR_HORA = 60;

    private final int tempoTotalMinutos;
    private final int numeroDisciplinas;

    public TempoDeEstudo(int tempoTotalMinutos, int numeroDisciplinas) {
        if (numeroDisciplinas <= 0) {
            throw new IllegalArgumentException("O número de disciplinas deve ser maior que zero.");
        }
        this.tempoTotalMinutos = tempoTotalMinutos;
        this.numeroDisciplinas = numeroDisciplinas;
    }

    public int calcularTempoPorDisciplina() {
        return tempoTotalMinutos / numeroDisciplinas;
    }

    public int calcularTempoLivre() {
        return tempoTotalMinutos % numeroDisciplinas;
    }

    public static int[] converterParaHorasEMinutos(int tempoEmMinutos) {
        int horas = tempoEmMinutos / MINUTOS_POR_HORA;
        int minutos = tempoEmMinutos % MINUTOS_POR_HORA;
        return new int[] { horas, minutos };
    }
}
