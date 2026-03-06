package programarcomputadoresbasicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class Menu {

    public static void main(final String[] args) {
        final List<String> clientes = new ArrayList<>();
        try (Scanner scanner = new Scanner(System.in)) {
            int opcao;
            do {
                exibirMenu();
                opcao = lerOpcao(scanner);
                switch (opcao) {
                    case 0 -> System.out.println("Saindo do programa. Até mais!");
                    case 1 -> incluirCliente(scanner, clientes);
                    case 2 -> alterarCliente(scanner, clientes);
                    case 3 -> excluirCliente(scanner, clientes);
                    case 4 -> consultarClientes(clientes);
                    default -> System.out.println("Opção inválida! Tente novamente.");
                }
            } while (opcao != 0);
        }
    }

    private static void exibirMenu() {
        System.out.println("\nCadastro de Clientes");
        System.out.println("0 - Fim");
        System.out.println("1 - Inclui");
        System.out.println("2 - Altera");
        System.out.println("3 - Exclui");
        System.out.println("4 - Consulta");
    }

    private static int lerOpcao(final Scanner scanner) {
        return EntradaUtil.lerInteiro(scanner, "Opção: ");
    }

    private static void incluirCliente(final Scanner scanner, final List<String> clientes) {
        System.out.print("Digite o nome do cliente para inclusão: ");
        final String nome = scanner.nextLine().trim();
        if (nome.isEmpty()) {
            System.out.println("O nome do cliente não pode ser vazio!");
            return;
        }
        clientes.add(nome);
        System.out.println("Cliente \"" + nome + "\" incluído com sucesso!");
    }

    private static void alterarCliente(final Scanner scanner, final List<String> clientes) {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado para alterar.");
            return;
        }
        listarClientes(clientes);
        final int indice = EntradaUtil.lerInteiro(scanner, "Digite o número do cliente que deseja alterar: ");
        if (indice < 0 || indice >= clientes.size()) {
            System.out.println("Número inválido!");
            return;
        }
        System.out.print("Digite o novo nome para o cliente: ");
        final String novoNome = scanner.nextLine().trim();
        if (novoNome.isEmpty()) {
            System.out.println("O nome do cliente não pode ser vazio!");
            return;
        }
        final String nomeAntigo = clientes.set(indice, novoNome);
        System.out.println("Cliente \"" + nomeAntigo + "\" alterado para \"" + novoNome + "\".");
    }

    private static void excluirCliente(final Scanner scanner, final List<String> clientes) {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado para excluir.");
            return;
        }
        listarClientes(clientes);
        final int indice = EntradaUtil.lerInteiro(scanner, "Digite o número do cliente que deseja excluir: ");
        if (indice < 0 || indice >= clientes.size()) {
            System.out.println("Número inválido!");
            return;
        }
        final String nome = clientes.remove(indice);
        System.out.println("Cliente \"" + nome + "\" excluído com sucesso!");
    }

    private static void consultarClientes(final List<String> clientes) {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            listarClientes(clientes);
        }
    }

    private static void listarClientes(final List<String> clientes) {
        System.out.println("\n--- Clientes Cadastrados ---");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println(i + " - " + clientes.get(i));
        }
    }
}
