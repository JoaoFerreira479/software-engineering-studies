package aed2.domain.nivelamento;

public final class IdentificarSimbolo {

    private IdentificarSimbolo() {}

    public static String identificarSimbolo(char simbolo) {
        return switch (simbolo) {
            case '>' -> "Sinal de maior.";
            case '<' -> "Sinal de menor.";
            case '=' -> "Sinal de igual.";
            default -> "Outro sinal.";
        };
    }
}
