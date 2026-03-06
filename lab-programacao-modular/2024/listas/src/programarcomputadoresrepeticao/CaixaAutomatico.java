package programarcomputadoresrepeticao;

import java.util.Optional;
import java.util.Scanner;

public final class CaixaAutomatico {

    private static final int MULTIPLO_NOTA = 10;

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final Caixa caixa = new Caixa();
            int opcao;
            do {
                exibirMenu();
                opcao = EntradaUtil.lerInteiro(scanner, "Escolha uma opção: ");
                switch (opcao) {
                    case 1 -> realizarRetirada(scanner, caixa);
                    case 2 -> adicionarNotas(scanner, caixa);
                    case 3 -> apresentarRelatorio(caixa);
                    case 0 -> System.out.println("Encerrando o programa. Obrigado!");
                    default -> System.out.println("Opção inválida. Por favor, tente novamente.");
                }
            } while (opcao != 0);
        }
    }

    private static void exibirMenu() {
        System.out.println("\n=== Caixa Automático ===");
        System.out.println("1 - Realizar retirada");
        System.out.println("2 - Adicionar notas ao caixa");
        System.out.println("3 - Apresentar relatório");
        System.out.println("0 - Sair");
    }

    private static void realizarRetirada(final Scanner scanner, final Caixa caixa) {
        final int valor = EntradaUtil.lerInteiro(scanner, "Digite o valor da retirada: ");
        if (valor % MULTIPLO_NOTA != 0) {
            System.out.println("Erro: O valor deve ser múltiplo de " + MULTIPLO_NOTA + ".");
            return;
        }
        final Optional<Caixa.RetiradaResultado> resultado = caixa.realizarRetirada(valor);
        if (resultado.isEmpty()) {
            System.out.println("Erro: Não há notas suficientes para realizar a retirada.");
            return;
        }
        final Caixa.RetiradaResultado r = resultado.get();
        System.out.println("Retirada realizada com sucesso!");
        System.out.printf("Notas de 50: %d%n", r.qtd50());
        System.out.printf("Notas de 10: %d%n", r.qtd10());
    }

    private static void adicionarNotas(final Scanner scanner, final Caixa caixa) {
        final int adiciona50 = EntradaUtil.lerInteiro(scanner, "Digite a quantidade de notas de 50 a adicionar: ");
        final int adiciona10 = EntradaUtil.lerInteiro(scanner, "Digite a quantidade de notas de 10 a adicionar: ");
        if (adiciona50 < 0 || adiciona10 < 0) {
            System.out.println("Erro: A quantidade de notas não pode ser negativa.");
            return;
        }
        caixa.adicionarNotas(adiciona50, adiciona10);
        System.out.println("Notas adicionadas com sucesso!");
    }

    private static void apresentarRelatorio(final Caixa caixa) {
        System.out.println("\n=== Relatório do Caixa ===");
        System.out.printf("Notas de 50 disponíveis: %d%n", caixa.getNotasDe50());
        System.out.printf("Notas de 10 disponíveis: %d%n", caixa.getNotasDe10());
        System.out.printf("Valor total disponível: R$ %d%n", caixa.getValorTotalDisponivel());
        System.out.printf("Valor total de retiradas efetuadas: R$ %d%n", caixa.getTotalRetiradas());
    }
}
