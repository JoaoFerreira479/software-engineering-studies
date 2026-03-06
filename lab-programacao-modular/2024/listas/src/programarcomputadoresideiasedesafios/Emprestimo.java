package programarcomputadoresideiasedesafios;

import java.time.LocalDate;

public final class Emprestimo {

    private final String tipoObjeto;
    private final String nomeObjeto;
    private final String nomePessoa;
    private final LocalDate dataEmprestimo;
    private final LocalDate ultimaCobranca;
    private LocalDate dataDevolucao;

    public Emprestimo(final String tipoObjeto, final String nomeObjeto, final String nomePessoa,
                      final LocalDate dataEmprestimo, final LocalDate ultimaCobranca) {
        if (tipoObjeto == null || nomeObjeto == null || nomePessoa == null || dataEmprestimo == null || ultimaCobranca == null) {
            throw new NullPointerException("Campos obrigatórios não podem ser nulos.");
        }
        this.tipoObjeto = tipoObjeto;
        this.nomeObjeto = nomeObjeto;
        this.nomePessoa = nomePessoa;
        this.dataEmprestimo = dataEmprestimo;
        this.ultimaCobranca = ultimaCobranca;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void registrarDevolucao(final LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public boolean estaEmprestado() {
        return dataDevolucao == null;
    }

    @Override
    public String toString() {
        return tipoObjeto + " - " + nomeObjeto + " (Emprestado para: " + nomePessoa + ", Data de empréstimo: "
            + dataEmprestimo + ", Última cobrança: " + ultimaCobranca
            + (dataDevolucao != null ? ", Devolvido em: " + dataDevolucao : ", Ainda não devolvido") + ")";
    }
}
