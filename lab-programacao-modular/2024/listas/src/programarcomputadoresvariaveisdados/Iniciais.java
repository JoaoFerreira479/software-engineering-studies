package programarcomputadoresvariaveisdados;
import lab.listas.infrastructure.io.Console;
import lab.listas.infrastructure.io.EntradaUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Iniciais {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            List<Pessoa> pessoas = new ArrayList<>();

            Console.println("Digite nomes completos (nome e sobrenome). Para encerrar, digite 'sair'.");
            while (true) {
                String entrada = EntradaUtil.lerLinha(scanner, "Digite um nome completo: ");

                if ("sair".equalsIgnoreCase(entrada)) {
                    break;
                }

                try {
                    Pessoa pessoa = new Pessoa(entrada);
                    pessoas.add(pessoa);
                } catch (IllegalArgumentException e) {
                    Console.println("Erro: " + e.getMessage());
                }
            }

            Console.println("\nIniciais extraídas:");
            for (Pessoa pessoa : pessoas) {
                Console.println(pessoa.getNomeCompleto() + " -> " + pessoa.getIniciais());
            }
        }
    }
}
