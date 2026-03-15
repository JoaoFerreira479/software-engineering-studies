package lab.listas.banco;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


public final class Banco {

    private final Map<String, Cliente> clientesPorCpfCnpj;

    public Banco() {
        this.clientesPorCpfCnpj = new HashMap<>();
    }

    public void adicionarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo");
        }
        String cpfCnpj = cliente.getCpfCnpj();
        if (clientesPorCpfCnpj.containsKey(cpfCnpj)) {
            throw new IllegalArgumentException("Cliente com este CPF/CNPJ já está cadastrado: " + cpfCnpj);
        }
        clientesPorCpfCnpj.put(cpfCnpj, cliente);
    }

    public Optional<Cliente> buscarCliente(String cpfCnpj) {
        if (cpfCnpj == null || cpfCnpj.isBlank()) {
            return Optional.empty();
        }
        return Optional.ofNullable(clientesPorCpfCnpj.get(cpfCnpj.trim()));
    }

    public Collection<Cliente> getClientes() {
        return List.copyOf(clientesPorCpfCnpj.values());
    }

    public String gerarExtratos() {
        return clientesPorCpfCnpj.values().stream()
            .map(Cliente::gerarExtratoContas)
            .collect(Collectors.joining("\n\n"));
    }
}
