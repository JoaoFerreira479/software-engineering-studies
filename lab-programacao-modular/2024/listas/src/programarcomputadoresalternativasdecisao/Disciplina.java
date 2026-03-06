package programarcomputadoresalternativasdecisao;

public final class Disciplina {

    private final String nome;
    private final double nota;
    private final int aulasMinistradas;
    private final int faltas;

    public Disciplina(final String nome, final double nota, final int aulasMinistradas, final int faltas) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome da disciplina não pode ser vazio.");
        }
        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException("A nota deve estar entre 0 e 10.");
        }
        if (!Aprovacao.validaNotaMultipla(nota)) {
            throw new IllegalArgumentException("A nota deve ser múltipla de 0,5.");
        }
        if (aulasMinistradas <= 0) {
            throw new IllegalArgumentException("A quantidade de aulas ministradas deve ser maior que zero.");
        }
        if (faltas < 0 || faltas > aulasMinistradas) {
            throw new IllegalArgumentException("A quantidade de faltas deve estar entre 0 e o total de aulas ministradas.");
        }
        this.nome = nome.trim();
        this.nota = nota;
        this.aulasMinistradas = aulasMinistradas;
        this.faltas = faltas;
    }

    public String getNome() {
        return nome;
    }

    public double getNota() {
        return nota;
    }

    public int getAulasMinistradas() {
        return aulasMinistradas;
    }

    public int getFaltas() {
        return faltas;
    }

    public double getFrequencia() {
        return (aulasMinistradas - faltas) / (double) aulasMinistradas * 100;
    }
}
