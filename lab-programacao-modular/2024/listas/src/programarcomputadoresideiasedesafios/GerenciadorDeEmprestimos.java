package programarcomputadoresideiasedesafios;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public final class GerenciadorDeEmprestimos {

    private final List<Emprestimo> emprestimos = new ArrayList<>();

    public void registrarEmprestimo(final String tipoObjeto, final String nomeObjeto, final String nomePessoa) {
        final LocalDate hoje = LocalDate.now();
        emprestimos.add(new Emprestimo(tipoObjeto, nomeObjeto, nomePessoa, hoje, hoje));
    }

    public boolean registrarDevolucao(final int indice1Based) {
        if (indice1Based < 1 || indice1Based > emprestimos.size()) {
            return false;
        }
        final Emprestimo e = emprestimos.get(indice1Based - 1);
        if (e.estaEmprestado()) {
            e.registrarDevolucao(LocalDate.now());
            return true;
        }
        return false;
    }

    public List<Emprestimo> getEmprestimos() {
        return List.copyOf(emprestimos);
    }

    public void exibirEmprestimos() {
        if (emprestimos.isEmpty()) {
            System.out.println("Não há empréstimos registrados.");
        } else {
            for (int i = 0; i < emprestimos.size(); i++) {
                System.out.println((i + 1) + ". " + emprestimos.get(i));
            }
        }
    }

    public void exibirRelatorio(final int dias) {
        final LocalDate hoje = LocalDate.now();
        boolean encontrou = false;
        for (final Emprestimo e : emprestimos) {
            if (e.estaEmprestado() && ChronoUnit.DAYS.between(e.getDataEmprestimo(), hoje) > dias) {
                System.out.println(e);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum objeto foi emprestado há mais de " + dias + " dias.");
        }
    }
}
