package programarcomputadoresideiasedesafios;

import java.util.Scanner;

public final class JogoPalavraEmbaralhada {

    private static final int LIMITE_SEGUNDOS = 30;

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final BancoDePalavras banco = new BancoDePalavras();
            System.out.println("Bem-vindo ao jogo de Palavra Embaralhada!");

            boolean continuar = true;
            while (continuar) {
                final String palavraEscolhida = banco.obterPalavraAleatoria();
                final PalavraEmbaralhada jogo = new PalavraEmbaralhada(palavraEscolhida);

                System.out.println("\nA palavra embaralhada é: " + jogo.getPalavraEmbaralhada());
                System.out.println("Você tem " + LIMITE_SEGUNDOS + " segundos para adivinhar!");

                final long inicio = System.currentTimeMillis();
                final String resposta = EntradaUtil.lerLinha(scanner, "Digite sua resposta: ");
                final long tempoDecorridoSeg = (System.currentTimeMillis() - inicio) / 1000;

                exibirResultado(jogo, resposta, tempoDecorridoSeg);

                final String deNovo = EntradaUtil.lerLinha(scanner, "Deseja jogar novamente? (s/n): ");
                continuar = "s".equalsIgnoreCase(deNovo);
            }
            System.out.println("Obrigado por jogar!");
        }
    }

    private static void exibirResultado(final PalavraEmbaralhada jogo, final String resposta, final long tempoDecorridoSeg) {
        if (tempoDecorridoSeg > LIMITE_SEGUNDOS) {
            System.out.println("\nTempo esgotado! A palavra era: " + jogo.getPalavraOriginal());
        } else if (jogo.getPalavraOriginal().equalsIgnoreCase(resposta != null ? resposta.trim() : "")) {
            System.out.println("\nParabéns! Você acertou em " + tempoDecorridoSeg + " segundos.");
        } else {
            System.out.println("\nQue pena! Resposta errada. A palavra era: " + jogo.getPalavraOriginal());
        }
    }
}
