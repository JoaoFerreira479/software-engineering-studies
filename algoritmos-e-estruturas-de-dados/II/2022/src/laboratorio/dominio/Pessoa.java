package laboratorio.dominio;

import java.util.Objects;

public final class Pessoa {

    private final String nome;
    private final int idade;
    private final double renda;

    public Pessoa(String nome, int idade, double renda) {
        this.nome = Objects.requireNonNull(nome, "nome");
        if (idade < 0) {
            throw new IllegalArgumentException("Idade não pode ser negativa: " + idade);
        }
        if (renda < 0) {
            throw new IllegalArgumentException("Renda não pode ser negativa: " + renda);
        }
        this.idade = idade;
        this.renda = renda;
    }

    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public double getRenda() { return renda; }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Idade: " + idade + ", Renda: " + renda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return idade == pessoa.idade
            && Double.compare(pessoa.renda, renda) == 0
            && nome.equals(pessoa.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, idade, renda);
    }
}
