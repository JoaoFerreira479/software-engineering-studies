package desafioemsala;
import lab.listas.infrastructure.io.Console;
import lab.listas.infrastructure.io.EntradaUtil;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public final class RegistroManutencoes {

    private static final String NOME_ARQUIVO = "manutencoes.dat";

    public static void main(final String[] args) {
        final Path caminho = Path.of(NOME_ARQUIVO);
        final GerenciadorManutencoes gerenciador = new GerenciadorManutencoes();

        try {
            gerenciador.carregarDeArquivo(caminho);
        } catch (IOException | ClassNotFoundException e) {
            Console.println("Nenhum dado prévio encontrado. Iniciando novo registro.");
        }

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                exibirMenu();
                final int opcao = lerInteiro(scanner, "Escolha uma opção: ");

                switch (opcao) {
                    case 1 -> registrarManutencao(scanner, gerenciador);
                    case 2 -> listarManutencoes(gerenciador);
                    case 3 -> buscarManutencoes(scanner, gerenciador);
                    case 4 -> removerManutencao(scanner, gerenciador);
                    case 5 -> {
                        salvarESair(gerenciador, caminho);
                        return;
                    }
                    default -> Console.println("Opção inválida! Tente novamente.");
                }
            }
        }
    }

    private static void exibirMenu() {
        Console.println("\n--- Menu de Manutenções ---");
        Console.println("1. Registrar nova manutenção");
        Console.println("2. Listar manutenções registradas");
        Console.println("3. Buscar manutenções por tipo de serviço");
        Console.println("4. Remover manutenção");
        Console.println("5. Sair");
    }

    private static void registrarManutencao(final Scanner scanner, final GerenciadorManutencoes gerenciador) {
        final String tipoServico = lerLinhaNaoVazia(scanner, "Digite o tipo de serviço: ", "Tipo de serviço não pode ser vazio.");
        if (tipoServico == null) return;

        final LocalDate data = lerData(scanner, "Digite a data (yyyy-mm-dd): ");
        if (data == null) return;

        final String nomeResponsavel = lerLinhaNaoVazia(scanner, "Digite o nome do responsável: ", "Nome do responsável não pode ser vazio.");
        if (nomeResponsavel == null) return;

        final int prazoGarantia = lerInteiroPositivo(scanner, "Digite o prazo de garantia (em meses): ", "O prazo de garantia deve ser maior que zero.");
        if (prazoGarantia <= 0) return;

        Console.print("Digite as observações: ");
        final String observacoes = scanner.nextLine();

        try {
            gerenciador.adicionarManutencao(new Manutencao(tipoServico, data, nomeResponsavel, prazoGarantia, observacoes));
            Console.println("Manutenção registrada com sucesso!");
        } catch (IllegalArgumentException e) {
            Console.println("Erro: " + e.getMessage());
        }
    }

    private static void listarManutencoes(final GerenciadorManutencoes gerenciador) {
        if (gerenciador.isEmpty()) {
            Console.println("Nenhuma manutenção registrada.");
            return;
        }
        Console.println("\n--- Manutenções Registradas ---");
        for (final Map.Entry<String, List<Manutencao>> e : gerenciador.getTodasManutencoes().entrySet()) {
            Console.println("Tipo de Serviço: " + e.getKey());
            for (final Manutencao m : e.getValue()) {
                Console.println(m);
            }
        }
    }

    private static void buscarManutencoes(final Scanner scanner, final GerenciadorManutencoes gerenciador) {
        final String tipoServico = lerLinhaNaoVazia(scanner, "Digite o tipo de serviço para busca: ", "Tipo de serviço não pode ser vazio.");
        if (tipoServico == null) return;

        final List<Manutencao> lista = gerenciador.getManutencoesPorTipo(tipoServico);
        if (lista.isEmpty()) {
            Console.println("Nenhuma manutenção encontrada para o tipo de serviço: " + tipoServico);
        } else {
            Console.println("\n--- Manutenções do Tipo: " + tipoServico + " ---");
            for (final Manutencao m : lista) {
                Console.println(m);
            }
        }
    }

    private static void removerManutencao(final Scanner scanner, final GerenciadorManutencoes gerenciador) {
        final String tipoServico = lerLinhaNaoVazia(scanner, "Digite o tipo de serviço da manutenção a ser removida: ", "Tipo de serviço não pode ser vazio.");
        if (tipoServico == null) return;

        final LocalDate data = lerData(scanner, "Digite a data da manutenção a ser removida (yyyy-mm-dd): ");
        if (data == null) return;

        if (gerenciador.removerManutencao(tipoServico, data)) {
            Console.println("Manutenção removida com sucesso.");
        } else {
            Console.println("Nenhuma manutenção encontrada para remoção.");
        }
    }

    private static void salvarESair(final GerenciadorManutencoes gerenciador, final Path caminho) {
        try {
            gerenciador.salvarEmArquivo(caminho);
            Console.println("Dados salvos com sucesso. Até mais!");
        } catch (IOException e) {
            Console.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    private static String lerLinhaNaoVazia(final Scanner scanner, final String prompt, final String msgErro) {
        Console.print(prompt);
        final String linha = scanner.nextLine().trim();
        if (linha.isEmpty()) {
            Console.println(msgErro);
            return null;
        }
        return linha;
    }

    private static LocalDate lerData(final Scanner scanner, final String prompt) {
        Console.print(prompt);
        try {
            return LocalDate.parse(scanner.nextLine().trim());
        } catch (DateTimeParseException e) {
            Console.println("Data inválida. Use o formato yyyy-mm-dd.");
            return null;
        }
    }

    private static int lerInteiro(final Scanner scanner, final String mensagem) {
        while (true) {
            try {
                Console.print(mensagem);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                Console.println("Entrada inválida. Digite um número inteiro.");
            }
        }
    }

    private static int lerInteiroPositivo(final Scanner scanner, final String mensagem, final String msgErro) {
        final int valor = lerInteiro(scanner, mensagem);
        if (valor <= 0) {
            Console.println(msgErro);
            return -1;
        }
        return valor;
    }
}
