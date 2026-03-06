package aeds2.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public final class MedalhistaComMedalhas implements Comparable<MedalhistaComMedalhas> {

    private static final int MAX_MEDALHAS = 8;
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final String nome;
    private final String genero;
    private final LocalDate nascimento;
    private final String pais;
    private final Medalha[] medalhas;
    private int quantidadeMedalhas;

    public MedalhistaComMedalhas(String nome, String genero, LocalDate nascimento, String pais) {
        this.nome = Objects.requireNonNull(nome, "nome");
        this.genero = genero != null ? genero : "";
        this.nascimento = nascimento != null ? nascimento : LocalDate.EPOCH;
        this.pais = pais != null ? pais : "";
        this.medalhas = new Medalha[MAX_MEDALHAS];
        this.quantidadeMedalhas = 0;
    }

    public void incluirMedalha(Medalha medalha) {
        Objects.requireNonNull(medalha, "medalha");
        if (quantidadeMedalhas < MAX_MEDALHAS) {
            medalhas[quantidadeMedalhas++] = medalha;
        }
    }

    public String relatorioDeMedalhas(TipoMedalha tipo) {
        StringBuilder sb = new StringBuilder();
        boolean possui = false;
        for (int i = 0; i < quantidadeMedalhas; i++) {
            if (medalhas[i].getTipo() == tipo) {
                sb.append(medalhas[i]).append("\n");
                possui = true;
            }
        }
        if (!possui) {
            sb.append("Nao possui medalha de ").append(tipo).append("\n");
        }
        return sb.toString();
    }

    public String getNome() {
        return nome;
    }

    @Override
    public int compareTo(MedalhistaComMedalhas outro) {
        return nome.compareToIgnoreCase(outro.nome);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return nome.equalsIgnoreCase(((MedalhistaComMedalhas) obj).nome);
    }

    @Override
    public int hashCode() {
        return nome.toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        return nome + ", " + genero + ". Nascimento: " + nascimento.format(FMT) + ". Pais: " + pais;
    }
}
