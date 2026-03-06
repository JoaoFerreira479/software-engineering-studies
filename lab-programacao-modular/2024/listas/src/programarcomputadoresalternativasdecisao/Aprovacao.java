package programarcomputadoresalternativasdecisao;

public final class Aprovacao {

    private static final double FREQUENCIA_MINIMA = 75.0;
    private static final double NOTA_APROVADO = 5.0;
    private static final double NOTA_SEGUNDA_EPOCA_MIN = 4.0;
    private static final double NOTA_SEGUNDA_EPOCA_MAX = 4.5;

    private Aprovacao() {
    }

    public static String verificarAprovacao(final Disciplina disciplina) {
        if (disciplina == null) {
            throw new NullPointerException("Disciplina não pode ser nula.");
        }
        if (disciplina.getFrequencia() < FREQUENCIA_MINIMA) {
            return "Reprovado por frequência (Frequência: " + String.format("%.2f", disciplina.getFrequencia()) + "%)";
        }
        final double nota = disciplina.getNota();
        if (nota >= NOTA_APROVADO && nota <= 10) {
            return "Aprovado";
        }
        if (nota >= NOTA_SEGUNDA_EPOCA_MIN && nota <= NOTA_SEGUNDA_EPOCA_MAX) {
            return "Segunda Época";
        }
        return "Reprovado por nota";
    }

    /**
     * Verifica se a nota é múltipla de 0,5 (para evitar erro de ponto flutuante).
     */
    public static boolean validaNotaMultipla(final double nota) {
        return Math.abs((nota * 10) % 5) < 1e-9;
    }
}
