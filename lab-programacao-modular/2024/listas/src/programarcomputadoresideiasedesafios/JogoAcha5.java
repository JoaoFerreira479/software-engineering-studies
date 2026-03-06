package programarcomputadoresideiasedesafios;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public final class JogoAcha5 {

    private static final int TAMANHO_PALAVRA = 5;
    private final List<String> bancoPalavras;
    private final Random random;

    public JogoAcha5(final List<String> bancoPalavras) {
        if (bancoPalavras == null || bancoPalavras.isEmpty()) {
            throw new IllegalArgumentException("Banco de palavras não pode ser vazio.");
        }
        this.bancoPalavras = List.copyOf(bancoPalavras);
        this.random = new Random();
    }

    public void iniciar(final Scanner scanner) {
        System.out.println("Bem-vindo ao jogo Acha-5!");

        final JogadorAcha5 jogador1 = new JogadorAcha5("Jogador");
        final JogadorAcha5 jogador2 = new JogadorAcha5("Computador");

        jogador2.setPalavraSecreta(sortearPalavra());
        System.out.println("Computador escolheu sua palavra secreta.");
        jogador1.setPalavraSecreta(obterPalavraSecretaJogador(scanner));
        System.out.println("Que comece o jogo!");

        boolean vezDoJogador = true;
        boolean jogoAtivo = true;

        while (jogoAtivo) {
            if (vezDoJogador) {
                final String tentativa = lerTentativaJogador(scanner);
                jogador1.registrarTentativa(tentativa);
                final int acertos = contarLetrasCorretas(tentativa, jogador2.getPalavraSecreta());
                System.out.printf("A palavra '%s' tem %d letra(s) correta(s)!%n", tentativa, acertos);
                if (acertos == TAMANHO_PALAVRA) {
                    System.out.println("Parabéns! Você descobriu a palavra secreta do computador!");
                    jogoAtivo = false;
                }
            } else {
                final String tentativa = sortearPalavra();
                System.out.printf("%nVez do computador! Ele tentou: '%s'%n", tentativa);
                jogador2.registrarTentativa(tentativa);
                final int acertos = contarLetrasCorretas(tentativa, jogador1.getPalavraSecreta());
                System.out.printf("O computador encontrou %d letra(s) correta(s)!%n", acertos);
                if (acertos == TAMANHO_PALAVRA) {
                    System.out.println("O computador descobriu sua palavra secreta! Você perdeu!");
                    jogoAtivo = false;
                }
            }
            vezDoJogador = !vezDoJogador;
        }
        System.out.println("\nFim de jogo!");
    }

    private String sortearPalavra() {
        return bancoPalavras.get(random.nextInt(bancoPalavras.size()));
    }

    private String obterPalavraSecretaJogador(final Scanner scanner) {
        while (true) {
            final String palavra = EntradaUtil.lerLinha(scanner, "Escolha sua palavra secreta de 5 letras: ").toLowerCase();
            if (validarPalavra(palavra)) return palavra;
            System.out.println("Palavra inválida! Certifique-se de que possui exatamente 5 letras.");
        }
    }

    private String lerTentativaJogador(final Scanner scanner) {
        while (true) {
            final String tentativa = EntradaUtil.lerLinha(scanner, "\nSua vez! Digite uma palavra de 5 letras: ").toLowerCase();
            if (validarPalavra(tentativa)) return tentativa;
            System.out.println("Palavra inválida! Tente novamente.");
        }
    }

    private boolean validarPalavra(final String palavra) {
        return palavra != null && palavra.matches("[a-z]{" + TAMANHO_PALAVRA + "}");
    }

    private int contarLetrasCorretas(final String tentativa, final String palavraSecreta) {
        if (tentativa == null || palavraSecreta == null) return 0;
        int acertos = 0;
        for (final char letra : tentativa.toCharArray()) {
            if (palavraSecreta.indexOf(letra) != -1) acertos++;
        }
        return acertos;
    }
}
