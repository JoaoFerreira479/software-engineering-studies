package programarcomputadoresideiasedesafios;

import java.time.LocalDate;

public record Transacao(TipoTransacao tipo, String categoria, double valor, LocalDate data) {

    public Transacao {
        if (tipo == null) {
            throw new NullPointerException("Tipo não pode ser nulo.");
        }
        if (categoria == null || categoria.isBlank()) {
            throw new IllegalArgumentException("Categoria não pode ser vazia.");
        }
        if (data == null) {
            throw new NullPointerException("Data não pode ser nula.");
        }
    }

    public double valorComSinal() {
        return tipo == TipoTransacao.RECEITA ? valor : -valor;
    }

    @Override
    public String toString() {
        return tipo + " - Categoria: " + categoria + ", Valor: " + valor + ", Data: " + data;
    }
}
