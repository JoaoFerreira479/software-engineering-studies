package programarcomputadoresideiasedesafios;
import lab.listas.infrastructure.io.Console;
import lab.listas.infrastructure.io.EntradaUtil;

public final class Jogador {

    private final String nome;
    private int vitorias;
    private int derrotas;

    public Jogador(final String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do jogador não pode ser vazio.");
        }
        this.nome = nome.trim();
    }

    public String getNome() {
        return nome;
    }

    public void registrarVitoria() {
        vitorias++;
    }

    public void registrarDerrota() {
        derrotas++;
    }

    public void exibirHistorico() {
        Console.println("\nHistórico de " + nome + ":");
        Console.println("Vitórias: " + vitorias);
        Console.println("Derrotas: " + derrotas);
    }
}
