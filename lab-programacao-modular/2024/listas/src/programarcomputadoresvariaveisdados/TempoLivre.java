package programarcomputadoresvariaveisdados;

public class TempoLivre {

    private static final int TEMPO_TOTAL_MINUTOS = 100;
    private static final int NUMERO_DISCIPLINAS = 6;
    private static final int MINUTOS_POR_HORA = 60;

    public static void main(String[] args) {
        int tempoPorDisciplina = calcularTempoPorDisciplina(TEMPO_TOTAL_MINUTOS, NUMERO_DISCIPLINAS);
        int tempoLivre = calcularTempoLivre(TEMPO_TOTAL_MINUTOS, NUMERO_DISCIPLINAS);

        exibirResultados(tempoPorDisciplina, tempoLivre);
    }

    private static int calcularTempoPorDisciplina(int tempoTotal, int numeroDisciplinas) {
        return tempoTotal / numeroDisciplinas;
    }

    private static int calcularTempoLivre(int tempoTotal, int numeroDisciplinas) {
        return tempoTotal % numeroDisciplinas;
    }

    private static int[] converterParaHorasEMinutos(int tempoEmMinutos) {
        int horas = tempoEmMinutos / MINUTOS_POR_HORA;
        int minutos = tempoEmMinutos % MINUTOS_POR_HORA;
        return new int[] { horas, minutos };
    }

    private static void exibirResultados(int tempoPorDisciplina, int tempoLivre) {
        int[] tempoDisciplinaFormatado = converterParaHorasEMinutos(tempoPorDisciplina);
        int[] tempoLivreFormatado = converterParaHorasEMinutos(tempoLivre);

        System.out.println("--- Tempo de Estudo ---");
        System.out.printf("Tempo por disciplina: %d horas e %d minutos\n", tempoDisciplinaFormatado[0],
                tempoDisciplinaFormatado[1]);
        System.out.printf("Tempo livre: %d horas e %d minutos\n", tempoLivreFormatado[0], tempoLivreFormatado[1]);
    }
}
