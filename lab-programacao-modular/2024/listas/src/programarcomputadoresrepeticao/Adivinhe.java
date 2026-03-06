package programarcomputadoresrepeticao;

import java.util.Random;
import java.util.Scanner;

public final class Adivinhe {

    private static final int INTERVALO_MIN = 1;
    private static final int INTERVALO_MAX = 10;
    private static final int MAX_TENTATIVAS = 3;

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Bem-vindo ao jogo 'Adivinhe o Número'!");
            System.out.printf("Eu vou pensar em um número entre %d e %d. Você tem %d tentativas para adivinhar.%n",
                INTERVALO_MIN, INTERVALO_MAX, MAX_TENTATIVAS);
            executarJogo(scanner);
        }
    }

    private static void executarJogo(final Scanner scanner) {
        final int numeroSorteado = new Random().nextInt(INTERVALO_MAX - INTERVALO_MIN + 1) + INTERVALO_MIN;
        for (int tentativa = 1; tentativa <= MAX_TENTATIVAS; tentativa++) {
            final int chute = EntradaUtil.lerInteiro(scanner, String.format("Tentativa %d de %d: Digite seu palpite: ", tentativa, MAX_TENTATIVAS));
            if (chute == numeroSorteado) {
                System.out.printf("Parabéns! Você acertou! O número era %d.%n", numeroSorteado);
                return;
            }
            System.out.println(chute < numeroSorteado ? "O número é maior que o seu palpite." : "O número é menor que o seu palpite.");
        }
        System.out.printf("Você perdeu! O número era %d.%n", numeroSorteado);
    }
}
