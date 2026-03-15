package lab.listas.banco;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Cliente {

    private final String nome;
    private final String telefone;
    private final String endereco;
    private final String cpfCnpj;
    private final List<Conta> contas;

    public Cliente(String nome, String telefone, String endereco, String cpfCnpj) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        if (cpfCnpj == null || cpfCnpj.isBlank()) {
            throw new IllegalArgumentException("CPF/CNPJ não pode ser nulo ou vazio");
        }
        this.nome = nome;
        this.telefone = telefone != null ? telefone : "";
        this.endereco = endereco != null ? endereco : "";
        this.cpfCnpj = cpfCnpj.trim();
        this.contas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void adicionarConta(Conta conta) {
        if (conta == null) {
            throw new IllegalArgumentException("Conta não pode ser nula");
        }
        contas.add(conta);
    }

    public List<Conta> getContas() {
        return List.copyOf(contas);
    }

    public double investimentoTotal() {
        return contas.stream().mapToDouble(Conta::getSaldo).sum();
    }

    public String gerarExtratoContas() {
        String cabecalho = "Cliente: " + nome + "\nTelefone: " + telefone
            + "\nEndereço: " + endereco + "\nCPF/CNPJ: " + cpfCnpj + "\n";
        String extratos = contas.stream()
            .map(Conta::gerarExtrato)
            .collect(Collectors.joining("\n\n"));
        return cabecalho + extratos;
    }

    @Override
    public String toString() {
        return "Cliente: " + nome + "\nTelefone: " + telefone + "\nEndereço: " + endereco + "\nCPF/CNPJ: " + cpfCnpj;
    }
}
