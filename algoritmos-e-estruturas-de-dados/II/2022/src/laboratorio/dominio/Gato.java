package laboratorio.dominio;

import java.util.Objects;

public final class Gato implements Falante {

    private final String nome;
    private static final String FALA_PADRAO = "Miau";

    public Gato(String nome) {
        this.nome = Objects.requireNonNull(nome, "nome");
    }

    @Override
    public String getNome() { return nome; }
    @Override
    public String getFala() { return FALA_PADRAO; }
}
