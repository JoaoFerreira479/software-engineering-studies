package programarcomputadoresvariaveisdados;

import java.util.Scanner;

public class SorteioDaLBV {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            try {
                Premio primeiroPremio = lerPremio(scanner, "primeiro");
                Premio segundoPremio = lerPremio(scanner, "segundo");

                String numeroSorteado = SorteioLBV.gerarNumeroSorteado(primeiroPremio, segundoPremio);

                System.out.println("O número sorteado da LBV é: " + numeroSorteado);
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static Premio lerPremio(Scanner scanner, String descricao) {
        while (true) {
            try {
                int numero = EntradaUtil.lerInteiro(scanner,
                        "Digite o número do " + descricao + " prêmio (6 dígitos): ");
                return new Premio(numero);
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
}
