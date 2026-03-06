package estruturascondicionais.domain;

public final class CategoriaNatacao {

    private CategoriaNatacao() {
    }

    public static String categoria(final int idade) {
        if (idade < 0) {
            throw new IllegalArgumentException("A idade deve ser um valor não negativo.");
        }
        if (idade < 8) return "INFANTIL";
        if (idade < 11) return "JUVENIL";
        if (idade < 16) return "ADOLESCENTE";
        if (idade < 30) return "ADULTO";
        return "SÊNIOR";
    }
}
