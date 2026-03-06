package programarcomputadoresideiasedesafios;

import java.util.ArrayList;
import java.util.List;

public final class ListaDeCompras {

    private final List<ItemDeCompra> itens = new ArrayList<>();

    public void adicionarItem(final String nome, final int quantidade) {
        if (nome == null || nome.isBlank() || quantidade < 1) return;
        final String nomeNorm = nome.trim();
        for (final ItemDeCompra item : itens) {
            if (item.getNome().equalsIgnoreCase(nomeNorm)) {
                item.setQuantidade(item.getQuantidade() + quantidade);
                return;
            }
        }
        itens.add(new ItemDeCompra(nomeNorm, quantidade));
    }

    public boolean removerItem(final int indice1Based) {
        if (indice1Based < 1 || indice1Based > itens.size()) {
            return false;
        }
        itens.remove(indice1Based - 1);
        return true;
    }

    public boolean isVazia() {
        return itens.isEmpty();
    }

    public List<ItemDeCompra> getItens() {
        return List.copyOf(itens);
    }

    public void exibirLista() {
        if (itens.isEmpty()) {
            System.out.println("A lista de compras está vazia.");
        } else {
            System.out.println("Itens na lista de compras:");
            for (int i = 0; i < itens.size(); i++) {
                System.out.println((i + 1) + ". " + itens.get(i));
            }
        }
    }

    public void imprimirLista() {
        if (itens.isEmpty()) {
            System.out.println("A lista de compras está vazia.");
        } else {
            System.out.println("Lista de compras:");
            itens.forEach(item -> System.out.println("- " + item));
        }
    }
}
