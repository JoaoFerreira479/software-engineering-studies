package programarcomputadoresideiasedesafios;

import java.util.ArrayList;
import java.util.List;

public final class JogadorAcha5 {

    private final String nome;
    private String palavraSecreta;
    private final List<String> tentativas;

    public JogadorAcha5(final String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        this.nome = nome.trim();
        this.tentativas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getPalavraSecreta() {
        return palavraSecreta;
    }

    public void setPalavraSecreta(final String palavraSecreta) {
        this.palavraSecreta = palavraSecreta;
    }

    public void registrarTentativa(final String tentativa) {
        tentativas.add(tentativa);
    }

    public List<String> getTentativas() {
        return List.copyOf(tentativas);
    }
}
