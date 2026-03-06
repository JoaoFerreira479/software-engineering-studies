package algoritmoknn;

public final class DomainConstants {

    public static final double MAX_CALORIAS = 3000.0;

    public static final double MAX_HORAS_SONO = 10.0;

    private DomainConstants() {
        throw new UnsupportedOperationException("Classe de constantes não instanciável");
    }

    public static void validarEntrada(final double calorias, final double horasSono) {
        if (calorias < 0 || calorias > MAX_CALORIAS) {
            throw new IllegalArgumentException(
                "Calorias fora do intervalo permitido (0 a " + MAX_CALORIAS + "): " + calorias);
        }
        if (horasSono < 0 || horasSono > MAX_HORAS_SONO) {
            throw new IllegalArgumentException(
                "Horas de sono fora do intervalo permitido (0 a " + MAX_HORAS_SONO + "): " + horasSono);
        }
    }
}
