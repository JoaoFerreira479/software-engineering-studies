package auladenivelamento;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public final class PesquisaConsumoApp {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final double precoKWh = lerPrecoKWh(scanner);
            final PesquisaConsumo pesquisa = new PesquisaConsumo(precoKWh);
            lerConsumidores(scanner, pesquisa);
            pesquisa.exibirResultados(System.out);
        }
    }

    private static double lerPrecoKWh(final Scanner scanner) {
        while (true) {
            System.out.print("Digite o preço do kWh: ");
            final Optional<Double> valor = lerDouble(scanner);
            if (valor.isPresent() && valor.get() > 0) {
                return valor.get();
            }
            if (valor.isPresent()) {
                System.out.println("O preço deve ser positivo. Tente novamente.");
            } else {
                System.out.println("Valor inválido. Digite um número.");
            }
        }
    }

    private static void lerConsumidores(final Scanner scanner, final PesquisaConsumo pesquisa) {
        while (true) {
            final Optional<Integer> numeroOpt = lerInteiroComPrompt(
                scanner, "Digite o número do consumidor (ou 0 para encerrar): ");
            if (numeroOpt.isEmpty()) {
                System.out.println("Valor inválido. Digite um número inteiro.");
                continue;
            }
            final int numero = numeroOpt.get();
            if (numero == 0) {
                break;
            }

            final Optional<Double> consumoOpt = lerDoubleComPrompt(
                scanner, "Digite a quantidade de kWh consumida: ");
            if (consumoOpt.isEmpty()) {
                System.out.println("Valor inválido. Digite um número.");
                continue;
            }
            final double consumoKWh = consumoOpt.get();
            if (consumoKWh < 0) {
                System.out.println("Consumo não pode ser negativo. Tente novamente.");
                continue;
            }

            System.out.print("Digite o tipo do consumidor (residencial, comercial ou industrial): ");
            final String tipoStr = scanner.next().trim();
            final Optional<TipoConsumidor> tipoOpt = TipoConsumidor.fromString(tipoStr);
            if (tipoOpt.isEmpty()) {
                System.out.println("Tipo inválido! Use: residencial, comercial ou industrial.");
                continue;
            }

            try {
                pesquisa.adicionarConsumidor(numero, consumoKWh, tipoOpt.get());
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage() + " Tente novamente.");
            }
        }
    }

    private static Optional<Double> lerDouble(final Scanner scanner) {
        try {
            if (scanner.hasNextDouble()) {
                return Optional.of(scanner.nextDouble());
            }
            scanner.next();
            return Optional.empty();
        } catch (InputMismatchException e) {
            scanner.next();
            return Optional.empty();
        }
    }

    private static Optional<Double> lerDoubleComPrompt(final Scanner scanner, final String prompt) {
        System.out.print(prompt);
        return lerDouble(scanner);
    }

    private static Optional<Integer> lerInteiroComPrompt(final Scanner scanner, final String prompt) {
        System.out.print(prompt);
        try {
            if (scanner.hasNextInt()) {
                return Optional.of(scanner.nextInt());
            }
            scanner.next();
            return Optional.empty();
        } catch (InputMismatchException e) {
            scanner.next();
            return Optional.empty();
        }
    }
}
