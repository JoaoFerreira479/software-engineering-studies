package auladenivelamento;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

public enum TipoConsumidor {

    RESIDENCIAL("residencial"),
    COMERCIAL("comercial"),
    INDUSTRIAL("industrial");

    private final String valor;

    TipoConsumidor(final String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static Optional<TipoConsumidor> fromString(final String texto) {
        if (texto == null || texto.isBlank()) {
            return Optional.empty();
        }
        final String normalizado = texto.trim().toLowerCase(Locale.ROOT);
        return Arrays.stream(values())
            .filter(t -> t.valor.equals(normalizado))
            .findFirst();
    }

    public static boolean isValid(final String texto) {
        return fromString(texto).isPresent();
    }
}
