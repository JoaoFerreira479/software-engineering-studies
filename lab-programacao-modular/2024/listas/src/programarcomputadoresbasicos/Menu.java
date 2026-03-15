package programarcomputadoresbasicos;

import lab.listas.infrastructure.io.Console;
import lab.listas.infrastructure.io.EntradaUtil;

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
                    case 0 -> Console.println("Saindo do programa. Até mais!");
                    case 1 -> incluirCliente(scanner, clientes);
                    case 2 -> alterarCliente(scanner, clientes);
                    case 3 -> excluirCliente(scanner, clientes);
                    case 4 -> consultarClientes(clientes);
                    default -> Console.println("Opção inválida! Tente novamente.");
                }
            } while (opcao != 0);
        }
    }

    private static void exibirMenu() {
        Console.println("\nCadastro de Clientes");
        Console.println("0 - Fim");
        Console.println("1 - Inclui");
        Console.println("2 - Altera");
        Console.println("3 - Exclui");
        Console.println("4 - Consulta");
    }

    private static int lerOpcao(final Scanner scanner) {
        return EntradaUtil.lerInteiro(scanner, "Opção: ");
    }

    private static void incluirCliente(final Scanner scanner, final List<String> clientes) {
        Console.print("Digite o nome do cliente para inclusão: ");
        final String nome = scanner.nextLine().trim();
        if (nome.isEmpty()) {
            Console.println("O nome do cliente não pode ser vazio!");
            return;
        }
        clientes.add(nome);
        Console.println("Cliente \"" + nome + "\" incluído com sucesso!");
    }

    private static void alterarCliente(final Scanner scanner, final List<String> clientes) {
        if (clientes.isEmpty()) {
            Console.println("Nenhum cliente cadastrado para alterar.");
            return;
        }
        listarClientes(clientes);
        final int indice = EntradaUtil.lerInteiro(scanner, "Digite o número do cliente que deseja alterar: ");
        if (indice < 0 || indice >= clientes.size()) {
            Console.println("Número inválido!");
            return;
        }
        Console.print("Digite o novo nome para o cliente: ");
        final String novoNome = scanner.nextLine().trim();
        if (novoNome.isEmpty()) {
            Console.println("O nome do cliente não pode ser vazio!");
            return;
        }
        final String nomeAntigo = clientes.set(indice, novoNome);
        Console.println("Cliente \"" + nomeAntigo + "\" alterado para \"" + novoNome + "\".");
    }

    private static void excluirCliente(final Scanner scanner, final List<String> clientes) {
        if (clientes.isEmpty()) {
            Console.println("Nenhum cliente cadastrado para excluir.");
            return;
        }
        listarClientes(clientes);
        final int indice = EntradaUtil.lerInteiro(scanner, "Digite o número do cliente que deseja excluir: ");
        if (indice < 0 || indice >= clientes.size()) {
            Console.println("Número inválido!");
            return;
        }
        final String nome = clientes.remove(indice);
        Console.println("Cliente \"" + nome + "\" excluído com sucesso!");
    }

    private static void consultarClientes(final List<String> clientes) {
        if (clientes.isEmpty()) {
            Console.println("Nenhum cliente cadastrado.");
        } else {
            listarClientes(clientes);
        }
    }

    private static void listarClientes(final List<String> clientes) {
        Console.println("\n--- Clientes Cadastrados ---");
        for (int i = 0; i < clientes.size(); i++) {
            Console.println(i + " - " + clientes.get(i));
        }
    }
}
