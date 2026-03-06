package programarcomputadoresalternativasdecisao;

public enum Operador {

    SOMA('+'),
    SUBTRACAO('-'),
    MULTIPLICACAO('*'),
    DIVISAO('/');

    private final char simbolo;

    Operador(final char simbolo) {
        this.simbolo = simbolo;
    }

    public char getSimbolo() {
        return simbolo;
    }

    public static Operador fromChar(final char simbolo) {
        for (final Operador op : values()) {
            if (op.simbolo == simbolo) {
                return op;
            }
        }
        throw new IllegalArgumentException("Operador inválido. Use apenas +, -, * ou /.");
    }
}
