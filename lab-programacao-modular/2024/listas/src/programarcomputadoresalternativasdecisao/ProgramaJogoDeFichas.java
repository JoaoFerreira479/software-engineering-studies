package programarcomputadoresalternativasdecisao;

import java.util.Scanner;

public final class ProgramaJogoDeFichas {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final CorFicha primeira = CorFicha.fromString(EntradaUtil.lerLinha(scanner, "Digite a cor da primeira ficha (Branca ou Preta): "));
            final CorFicha segunda = CorFicha.fromString(EntradaUtil.lerLinha(scanner, "Digite a cor da segunda ficha (Branca ou Preta): "));
            final double rateio = JogoDeFichas.calcularRateio(primeira, segunda);
            System.out.println("O rateio do jogo é: " + rateio);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
