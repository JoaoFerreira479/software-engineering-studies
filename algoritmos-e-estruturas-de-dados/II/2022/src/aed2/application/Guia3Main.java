package aed2.application;

import aed2.domain.estruturas.ListaSequencial;
import aed2.domain.model.Livro;
import aed2.domain.model.Pessoa;
import aed2.infrastructure.io.Console;
import aed2.infrastructure.io.EntradaUsuario;

import java.util.Scanner;

public final class Guia3Main {

    private static final int QUANTIDADE_PADRAO = 5;

    private Guia3Main() {}

    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            EntradaUsuario entrada = new EntradaUsuario(scan);
            Pessoa pessoa = lerPessoa(entrada);
            Console.println("\nDados da pessoa:");
            Console.println(pessoa.toString());

            ListaSequencial<Pessoa> pessoas = lerListaPessoas(entrada, QUANTIDADE_PADRAO);
            Console.println("\nDados do vetor de pessoas:");
            pessoas.percorrer(p -> Console.println(p.toString()));

            ListaSequencial<Livro> livros = lerListaLivros(entrada, QUANTIDADE_PADRAO);
            Console.println("\nDados do vetor de livros:");
            livros.percorrer(l -> Console.println(l.toString()));
        } catch (Exception e) {
            Console.erro("Erro: " + e.getMessage());
        }
    }

    private static Pessoa lerPessoa(EntradaUsuario entrada) {
        Console.println("Exercício 1: Pessoa única");
        String nome = entrada.lerLinha("Digite o nome: ");
        int idade = entrada.lerInteiro("Digite a idade: ");
        double renda = entrada.lerDouble("Digite a renda: ");
        entrada.consumirNovaLinha();
        return new Pessoa(nome, idade, renda);
    }

    private static ListaSequencial<Pessoa> lerListaPessoas(EntradaUsuario entrada, int quantidade) {
        Console.println("\nExercício 2: Vetor de Pessoas");
        ListaSequencial<Pessoa> pessoas = new ListaSequencial<>(quantidade);
        for (int i = 0; i < quantidade; i++) {
            Console.println("\nPessoa " + (i + 1) + ":");
            String nome = entrada.lerLinha("Digite o nome: ");
            int idade = entrada.lerInteiro("Digite a idade: ");
            double renda = entrada.lerDouble("Digite a renda: ");
            entrada.consumirNovaLinha();
            pessoas.inserirUltimaPosicao(new Pessoa(nome, idade, renda));
        }
        return pessoas;
    }

    private static ListaSequencial<Livro> lerListaLivros(EntradaUsuario entrada, int quantidade) {
        Console.println("\nExercício 3: Vetor de Livros");
        ListaSequencial<Livro> livros = new ListaSequencial<>(quantidade);
        for (int i = 0; i < quantidade; i++) {
            Console.println("\nLivro " + (i + 1) + ":");
            String titulo = entrada.lerLinha("Digite o título: ");
            String autor = entrada.lerLinha("Digite o autor: ");
            String assunto = entrada.lerLinha("Digite o assunto: ");
            int id = entrada.lerInteiro("Digite o ID: ");
            entrada.consumirNovaLinha();
            livros.inserirUltimaPosicao(new Livro(titulo, autor, assunto, id));
        }
        return livros;
    }
}
