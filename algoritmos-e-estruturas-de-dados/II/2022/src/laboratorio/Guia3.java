package laboratorio;

import laboratorio.dominio.Livro;
import laboratorio.dominio.Pessoa;
import laboratorio.estruturas.ListaSequencial;

import java.util.Scanner;

public final class Guia3 {

    private static final int QUANTIDADE_PADRAO = 5;

    private Guia3() {}

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Pessoa pessoa = lerPessoa(scanner);
            System.out.println("\nDados da pessoa:");
            System.out.println(pessoa);

            ListaSequencial<Pessoa> pessoas = lerListaPessoas(scanner, QUANTIDADE_PADRAO);
            System.out.println("\nDados do vetor de pessoas:");
            pessoas.percorrer(p -> System.out.println(p));

            ListaSequencial<Livro> livros = lerListaLivros(scanner, QUANTIDADE_PADRAO);
            System.out.println("\nDados do vetor de livros:");
            livros.percorrer(l -> System.out.println(l));
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    private static Pessoa lerPessoa(Scanner scanner) {
        System.out.println("Exercício 1: Pessoa única");
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a idade: ");
        int idade = scanner.nextInt();
        System.out.print("Digite a renda: ");
        double renda = scanner.nextDouble();
        consumirNovaLinha(scanner);
        return new Pessoa(nome, idade, renda);
    }

    private static ListaSequencial<Pessoa> lerListaPessoas(Scanner scanner, int quantidade) {
        System.out.println("\nExercício 2: Vetor de Pessoas");
        ListaSequencial<Pessoa> pessoas = new ListaSequencial<>(quantidade);
        for (int i = 0; i < quantidade; i++) {
            System.out.println("\nPessoa " + (i + 1) + ":");
            System.out.print("Digite o nome: ");
            String nome = scanner.nextLine();
            System.out.print("Digite a idade: ");
            int idade = scanner.nextInt();
            System.out.print("Digite a renda: ");
            double renda = scanner.nextDouble();
            consumirNovaLinha(scanner);
            pessoas.inserirUltimaPosicao(new Pessoa(nome, idade, renda));
        }
        return pessoas;
    }

    private static ListaSequencial<Livro> lerListaLivros(Scanner scanner, int quantidade) {
        System.out.println("\nExercício 3: Vetor de Livros");
        ListaSequencial<Livro> livros = new ListaSequencial<>(quantidade);
        for (int i = 0; i < quantidade; i++) {
            System.out.println("\nLivro " + (i + 1) + ":");
            System.out.print("Digite o título: ");
            String titulo = scanner.nextLine();
            System.out.print("Digite o autor: ");
            String autor = scanner.nextLine();
            System.out.print("Digite o assunto: ");
            String assunto = scanner.nextLine();
            System.out.print("Digite o ID: ");
            int id = scanner.nextInt();
            consumirNovaLinha(scanner);
            livros.inserirUltimaPosicao(new Livro(titulo, autor, assunto, id));
        }
        return livros;
    }

    private static void consumirNovaLinha(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
}
