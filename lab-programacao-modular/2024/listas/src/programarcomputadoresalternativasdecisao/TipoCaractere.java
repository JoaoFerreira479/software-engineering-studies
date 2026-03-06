package programarcomputadoresalternativasdecisao;

public final class TipoCaractere {

    private TipoCaractere() {
    }

    public static String identificarTipo(final char c) {
        if (Character.isLetter(c)) {
            return "Letra";
        }
        if (Character.isDigit(c)) {
            return "Dígito";
        }
        if (ehOperadorAritmetico(c)) {
            return "Operador Aritmético";
        }
        return "Nenhum dos anteriores";
    }

    private static boolean ehOperadorAritmetico(final char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%';
    }
}
