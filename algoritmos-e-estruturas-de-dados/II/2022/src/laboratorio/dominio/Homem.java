package laboratorio.dominio;

import java.util.Objects;

public final class Homem implements Falante {

    private final String nome;
    private final String fala;

    public Homem(String nome) {
        this(nome, "Oi");
    }

    public Homem(String nome, String fala) {
        this.nome = Objects.requireNonNull(nome, "nome");
        this.fala = fala != null ? fala : "Oi";
    }

    @Override
    public String getNome() { return nome; }
    @Override
    public String getFala() { return fala; }
}
