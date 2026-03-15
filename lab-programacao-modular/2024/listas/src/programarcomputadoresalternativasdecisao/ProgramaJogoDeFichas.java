package programarcomputadoresalternativasdecisao;
import lab.listas.infrastructure.io.Console;
import lab.listas.infrastructure.io.EntradaUtil;

import java.util.Scanner;

public final class ProgramaJogoDeFichas {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final CorFicha primeira = CorFicha.fromString(EntradaUtil.lerLinha(scanner, "Digite a cor da primeira ficha (Branca ou Preta): "));
            final CorFicha segunda = CorFicha.fromString(EntradaUtil.lerLinha(scanner, "Digite a cor da segunda ficha (Branca ou Preta): "));
            final double rateio = JogoDeFichas.calcularRateio(primeira, segunda);
            Console.println("O rateio do jogo é: " + rateio);
        } catch (IllegalArgumentException e) {
            Console.println("Erro: " + e.getMessage());
        }
    }
}
