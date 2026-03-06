package programarcomputadoresideiasedesafios;

public final class ItemDeCompra {

    private final String nome;
    private int quantidade;

    public ItemDeCompra(final String nome, final int quantidade) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do item não pode ser vazio.");
        }
        if (quantidade < 1) {
            throw new IllegalArgumentException("Quantidade deve ser positiva: " + quantidade);
        }
        this.nome = nome.trim();
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(final int quantidade) {
        if (quantidade < 1) {
            throw new IllegalArgumentException("Quantidade deve ser positiva: " + quantidade);
        }
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return nome + " (Quantidade: " + quantidade + ")";
    }
}
