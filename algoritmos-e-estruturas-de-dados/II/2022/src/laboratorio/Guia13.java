package laboratorio;

import laboratorio.dominio.Contato;
import laboratorio.estruturas.ArvoreBinariaBusca;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Scanner;

public final class Guia13 {

    private static final Path ARQUIVO_AGENDA = Paths.get("agenda.txt");

    private Guia13() {}

    public static void main(String[] args) {
        ArvoreBinariaBusca agenda = new ArvoreBinariaBusca();

        if (!carregarAgenda(ARQUIVO_AGENDA, agenda)) {
            System.err.println("Erro ao carregar agenda. Verifique o arquivo: " + ARQUIVO_AGENDA);
            return;
        }

        try (Scanner scanner = new Scanner(System.in)) {
            int opcao = 0;
            do {
                System.out.println("\n=== Menu Agenda ===");
                System.out.println("1. Buscar contato");
                System.out.println("2. Listar contatos em ordem alfabética");
                System.out.println("3. Remover contato");
                System.out.println("4. Sair");
                System.out.print("Escolha uma opção: ");
                if (!scanner.hasNextInt()) {
                    scanner.nextLine();
                    System.out.println("Opção inválida. Tente novamente.");
                    continue;
                }
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1 -> {
                        System.out.print("Digite o nome para buscar: ");
                        String nomeBusca = scanner.nextLine();
                        Optional<Contato> contato = agenda.buscar(nomeBusca);
                        if (contato.isPresent()) {
                            System.out.println("Telefone encontrado: " + contato.get().getTelefone());
                        } else {
                            System.out.println("Nome não encontrado na agenda.");
                        }
                    }
                    case 2 -> {
                        System.out.println("Contatos em ordem alfabética:");
                        if (agenda.estaVazia()) {
                            System.out.println("A agenda está vazia.");
                        } else {
                            agenda.emOrdem(c -> System.out.println(c.getNome() + " - " + c.getTelefone()));
                        }
                    }
                    case 3 -> {
                        System.out.print("Digite o nome para remover: ");
                        String nomeRemover = scanner.nextLine();
                        agenda.remover(nomeRemover);
                    }
                    case 4 -> System.out.println("Encerrando o programa.");
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            } while (opcao != 4);
        }
    }

    private static boolean carregarAgenda(Path path, ArvoreBinariaBusca agenda) {
        if (!Files.isReadable(path)) {
            System.out.println("Arquivo não encontrado ou não legível: " + path);
            return false;
        }
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" # ", 2);
                if (dados.length == 2) {
                    String nome = dados[0].trim();
                    String telefone = dados[1].trim();
                    if (!nome.isEmpty()) {
                        agenda.inserir(new Contato(nome, telefone));
                    }
                } else if (!linha.isBlank()) {
                    System.out.println("Linha inválida no arquivo: " + linha);
                }
            }
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return false;
        }
    }
}
