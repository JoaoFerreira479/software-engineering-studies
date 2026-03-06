package programarcomputadoresalternativasdecisao;

import java.util.Optional;

public enum DiaSemana {

    DOMINGO(1, "Domingo"),
    SEGUNDA(2, "Segunda-feira"),
    TERCA(3, "Terça-feira"),
    QUARTA(4, "Quarta-feira"),
    QUINTA(5, "Quinta-feira"),
    SEXTA(6, "Sexta-feira"),
    SABADO(7, "Sábado");

    private final int numero;
    private final String nome;

    DiaSemana(final int numero, final String nome) {
        this.numero = numero;
        this.nome = nome;
    }

    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public static Optional<String> obterNomeDia(final int numero) {
        for (final DiaSemana dia : values()) {
            if (dia.numero == numero) {
                return Optional.of(dia.nome);
            }
        }
        return Optional.empty();
    }
}
