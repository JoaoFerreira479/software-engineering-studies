package aed2.application;

import aed2.domain.estruturas.ArvoreBinariaBusca;
import aed2.domain.model.Contato;
import aed2.infrastructure.io.Console;
import aed2.infrastructure.io.EntradaUsuario;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Scanner;

public final class Guia13Main {

    private static final Path ARQUIVO_AGENDA = Paths.get("agenda.txt");

    private Guia13Main() {}

    public static void main(String[] args) {
        ArvoreBinariaBusca agenda = new ArvoreBinariaBusca();

        if (!carregarAgenda(ARQUIVO_AGENDA, agenda)) {
            Console.erro("Erro ao carregar agenda. Verifique o arquivo: " + ARQUIVO_AGENDA);
            return;
        }

        try (Scanner scan = new Scanner(System.in)) {
            EntradaUsuario entrada = new EntradaUsuario(scan);
            int opcao = 0;
            do {
                Console.println("\n=== Menu Agenda ===");
                Console.println("1. Buscar contato");
                Console.println("2. Listar contatos em ordem alfabética");
                Console.println("3. Remover contato");
                Console.println("4. Sair");
                opcao = entrada.lerInteiro("Escolha uma opção: ");
                entrada.consumirNovaLinha();

                switch (opcao) {
                    case 1 -> {
                        String nomeBusca = entrada.lerLinha("Digite o nome para buscar: ");
                        Optional<Contato> contato = agenda.buscar(nomeBusca);
                        if (contato.isPresent()) {
                            Console.println("Telefone encontrado: " + contato.get().getTelefone());
                        } else {
                            Console.println("Nome não encontrado na agenda.");
                        }
                    }
                    case 2 -> {
                        Console.println("Contatos em ordem alfabética:");
                        if (agenda.estaVazia()) {
                            Console.println("A agenda está vazia.");
                        } else {
                            agenda.emOrdem(c -> Console.println(c.getNome() + " - " + c.getTelefone()));
                        }
                    }
                    case 3 -> {
                        String nomeRemover = entrada.lerLinha("Digite o nome para remover: ");
                        agenda.remover(nomeRemover);
                    }
                    case 4 -> Console.println("Encerrando o programa.");
                    default -> Console.println("Opção inválida. Tente novamente.");
                }
            } while (opcao != 4);
        }
    }

    private static boolean carregarAgenda(Path path, ArvoreBinariaBusca agenda) {
        if (!Files.isReadable(path)) {
            Console.println("Arquivo não encontrado ou não legível: " + path);
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
                    Console.println("Linha inválida no arquivo: " + linha);
                }
            }
            return true;
        } catch (IOException e) {
            Console.println("Erro ao ler o arquivo: " + e.getMessage());
            return false;
        }
    }
}
