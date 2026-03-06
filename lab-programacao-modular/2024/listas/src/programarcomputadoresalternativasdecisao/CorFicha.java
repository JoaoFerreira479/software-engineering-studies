package programarcomputadoresalternativasdecisao;

import java.util.Locale;

public enum CorFicha {

    BRANCA,
    PRETA;

    public static CorFicha fromString(final String cor) {
        if (cor == null || cor.isBlank()) {
            throw new IllegalArgumentException("Cor não pode ser vazia. Use 'Branca' ou 'Preta'.");
        }
        return switch (cor.trim().toLowerCase(Locale.ROOT)) {
            case "branca" -> BRANCA;
            case "preta" -> PRETA;
            default -> throw new IllegalArgumentException("Cor inválida. Use 'Branca' ou 'Preta'.");
        };
    }
}
