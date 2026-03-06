package conceitosbasicosprogramacao.domain;

public final class ConversaoTempo {

    private ConversaoTempo() {
    }

    public static String paraHorasMinutosSegundos(final int totalMinutos) {
        if (totalMinutos < 0) {
            throw new IllegalArgumentException("Minutos não podem ser negativos.");
        }
        int horas = totalMinutos / 60;
        int minutos = totalMinutos % 60;
        return String.format("%02d:%02d:00", horas, minutos);
    }
}
