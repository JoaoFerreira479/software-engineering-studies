package laboratorio.dominio;

import java.util.Objects;

public final class Contato {

    private final String nome;
    private final String telefone;

    public Contato(String nome, String telefone) {
        this.nome = Objects.requireNonNull(nome, "nome").trim();
        this.telefone = Objects.requireNonNull(telefone, "telefone").trim();
        if (this.nome.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
    }

    public String getNome() { return nome; }
    public String getTelefone() { return telefone; }
}
