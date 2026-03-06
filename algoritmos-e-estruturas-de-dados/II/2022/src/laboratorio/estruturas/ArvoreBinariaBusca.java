package laboratorio.estruturas;

import laboratorio.dominio.Contato;

import java.util.Objects;
import java.util.Optional;

public final class ArvoreBinariaBusca {

    private NoArvore raiz;

    public ArvoreBinariaBusca() {
        this.raiz = null;
    }

    public void inserir(Contato contato) {
        Objects.requireNonNull(contato, "contato");
        NoArvore novo = new NoArvore(contato);
        if (raiz == null) {
            raiz = novo;
        } else {
            inserirRecursivo(raiz, novo);
        }
    }

    private void inserirRecursivo(NoArvore atual, NoArvore novo) {
        int cmp = novo.contato.getNome().compareToIgnoreCase(atual.contato.getNome());
        if (cmp < 0) {
            if (atual.esquerdo == null) {
                atual.esquerdo = novo;
            } else {
                inserirRecursivo(atual.esquerdo, novo);
            }
        } else {
            if (atual.direito == null) {
                atual.direito = novo;
            } else {
                inserirRecursivo(atual.direito, novo);
            }
        }
    }

    public Optional<Contato> buscar(String nome) {
        if (nome == null || nome.isBlank()) {
            return Optional.empty();
        }
        NoArvore no = buscarRecursivo(raiz, nome.trim());
        return no == null ? Optional.empty() : Optional.of(no.contato);
    }

    private NoArvore buscarRecursivo(NoArvore atual, String nome) {
        if (atual == null) return null;
        int cmp = nome.compareToIgnoreCase(atual.contato.getNome());
        if (cmp == 0) return atual;
        return cmp < 0 ? buscarRecursivo(atual.esquerdo, nome) : buscarRecursivo(atual.direito, nome);
    }

    public void remover(String nome) {
        if (nome == null || nome.isBlank()) return;
        raiz = removerRecursivo(raiz, nome.trim());
    }

    private NoArvore removerRecursivo(NoArvore atual, String nome) {
        if (atual == null) return null;
        int cmp = nome.compareToIgnoreCase(atual.contato.getNome());
        if (cmp < 0) {
            atual.esquerdo = removerRecursivo(atual.esquerdo, nome);
            return atual;
        }
        if (cmp > 0) {
            atual.direito = removerRecursivo(atual.direito, nome);
            return atual;
        }
        if (atual.esquerdo == null) return atual.direito;
        if (atual.direito == null) return atual.esquerdo;
        NoArvore sucessor = menorNo(atual.direito);
        atual.contato = sucessor.contato;
        atual.direito = removerRecursivo(atual.direito, sucessor.contato.getNome());
        return atual;
    }

    private NoArvore menorNo(NoArvore no) {
        while (no.esquerdo != null) {
            no = no.esquerdo;
        }
        return no;
    }

    public boolean estaVazia() {
        return raiz == null;
    }

    public void emOrdem(ListaSequencial.Consumer<Contato> acao) {
        emOrdemRecursivo(raiz, acao);
    }

    private void emOrdemRecursivo(NoArvore no, ListaSequencial.Consumer<Contato> acao) {
        if (no == null) return;
        emOrdemRecursivo(no.esquerdo, acao);
        acao.aceitar(no.contato);
        emOrdemRecursivo(no.direito, acao);
    }

    private static final class NoArvore {
        private Contato contato;
        private NoArvore esquerdo;
        private NoArvore direito;

        NoArvore(Contato contato) {
            this.contato = contato;
            this.esquerdo = null;
            this.direito = null;
        }
    }
}
