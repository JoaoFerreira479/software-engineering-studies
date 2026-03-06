package programarcomputadoresideiasedesafios;

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
        System.out.println("\nHistórico de " + nome + ":");
        System.out.println("Vitórias: " + vitorias);
        System.out.println("Derrotas: " + derrotas);
    }
}
