package programarcomputadoresideiasedesafios;

import java.util.List;
import java.util.Scanner;

public final class Acha5 {

    private static final List<String> BANCO_PALAVRAS = List.of(
        "zinco", "ossos", "arara", "canto", "valsa", "piano", "campo", "vento", "porto", "risco"
    );

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final JogoAcha5 jogo = new JogoAcha5(BANCO_PALAVRAS);
            jogo.iniciar(scanner);
        }
    }
}
