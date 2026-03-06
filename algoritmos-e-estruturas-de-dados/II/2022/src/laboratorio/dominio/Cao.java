package laboratorio.dominio;

import java.util.Objects;

public final class Cao implements Falante {

    private final String nome;
    private static final String FALA_PADRAO = "Au au";

    public Cao(String nome) {
        this.nome = Objects.requireNonNull(nome, "nome");
    }

    @Override
    public String getNome() { return nome; }
    @Override
    public String getFala() { return FALA_PADRAO; }
}
