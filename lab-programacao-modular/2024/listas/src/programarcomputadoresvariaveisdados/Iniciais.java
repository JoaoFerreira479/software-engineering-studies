package programarcomputadoresvariaveisdados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Iniciais {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            List<Pessoa> pessoas = new ArrayList<>();

            System.out.println("Digite nomes completos (nome e sobrenome). Para encerrar, digite 'sair'.");
            while (true) {
                String entrada = EntradaUtil.lerLinha(scanner, "Digite um nome completo: ");

                if ("sair".equalsIgnoreCase(entrada)) {
                    break;
                }

                try {
                    Pessoa pessoa = new Pessoa(entrada);
                    pessoas.add(pessoa);
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro: " + e.getMessage());
                }
            }

            System.out.println("\nIniciais extraídas:");
            for (Pessoa pessoa : pessoas) {
                System.out.println(pessoa.getNomeCompleto() + " -> " + pessoa.getIniciais());
            }
        }
    }
}
