package programarcomputadoresideiasedesafios;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public final class JogoDaForca {

    private static final String[] PALAVRAS = { "JAVA", "PROGRAMACAO", "LINGUAGEM", "COMPUTADOR", "TECLADO" };
    private static final int MAX_TENTATIVAS = 6;

    private final Jogador jogador;
    private String palavra;
    private char[] palavraEscondida;
    private final Set<Character> letrasTentadas;
    private int tentativasRestantes;

    public JogoDaForca(final Jogador jogador) {
        if (jogador == null) {
            throw new NullPointerException("Jogador não pode ser nulo.");
        }
        this.jogador = jogador;
        this.letrasTentadas = new HashSet<>();
    }

    public void iniciar() {
        final Random random = new Random();
        palavra = PALAVRAS[random.nextInt(PALAVRAS.length)];
        palavraEscondida = new char[palavra.length()];
        Arrays.fill(palavraEscondida, '_');
        tentativasRestantes = MAX_TENTATIVAS;
    }

    public void jogar(final Scanner scanner) {
        while (tentativasRestantes > 0 && new String(palavraEscondida).contains("_")) {
            exibirEstadoAtual();
            final char letra = lerLetra(scanner);
            if (!letrasTentadas.add(letra)) {
                System.out.println("Você já tentou essa letra. Tente novamente.");
                continue;
            }
            processarTentativa(letra);
        }
        finalizarPartida();
    }

    private char lerLetra(final Scanner scanner) {
        while (true) {
            final String linha = EntradaUtil.lerLinha(scanner, "Digite uma letra: ");
            if (linha.length() >= 1 && Character.isLetter(linha.charAt(0))) {
                return Character.toUpperCase(linha.charAt(0));
            }
            System.out.println("Digite uma letra válida.");
        }
    }

    private void exibirEstadoAtual() {
        System.out.println("\nPalavra: " + String.valueOf(palavraEscondida));
        System.out.println("Tentativas restantes: " + tentativasRestantes);
        System.out.println("Letras tentadas: " + letrasTentadas);
    }

    private void processarTentativa(final char letra) {
        if (palavra.contains(String.valueOf(letra))) {
            System.out.println("Boa! A letra " + letra + " está na palavra.");
            for (int i = 0; i < palavra.length(); i++) {
                if (palavra.charAt(i) == letra) {
                    palavraEscondida[i] = letra;
                }
            }
        } else {
            System.out.println("Ops! A letra " + letra + " não está na palavra.");
            tentativasRestantes--;
        }
    }

    private void finalizarPartida() {
        if (new String(palavraEscondida).equals(palavra)) {
            System.out.println("\nParabéns! Você acertou a palavra: " + palavra);
            jogador.registrarVitoria();
        } else {
            System.out.println("\nQue pena! Você perdeu. A palavra era: " + palavra);
            jogador.registrarDerrota();
        }
    }

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Bem-vindo ao Jogo da Forca!");
            final String nome = EntradaUtil.lerLinha(scanner, "Digite seu nome: ");
            final Jogador jogador = new Jogador(nome);

            boolean continuar = true;
            while (continuar) {
                final JogoDaForca jogo = new JogoDaForca(jogador);
                jogo.iniciar();
                jogo.jogar(scanner);
                final String resp = EntradaUtil.lerLinha(scanner, "Deseja jogar novamente? (s/n): ");
                continuar = "s".equalsIgnoreCase(resp);
            }
            jogador.exibirHistorico();
        }
    }
}
