package programarcomputadoresideiasedesafios;

import java.util.List;
import java.util.Random;

public final class BancoDePalavras {

    private static final List<String> PALAVRAS = List.of("JAVA", "PROGRAMACAO", "LINGUAGEM", "COMPUTADOR", "TECLADO");
    private final Random random;

    public BancoDePalavras() {
        this.random = new Random();
    }

    public BancoDePalavras(final Random random) {
        this.random = random;
    }

    public String obterPalavraAleatoria() {
        return PALAVRAS.get(random.nextInt(PALAVRAS.size()));
    }
}
