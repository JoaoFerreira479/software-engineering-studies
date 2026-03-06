package banco;

public final class SistemaBancario {

    public static void main(final String[] args) {
        final Banco banco = new Banco();

        final Cliente cliente = new Cliente("João Silva", "123456789", "Rua A, 123", "123.456.789-00");
        final ContaCorrente cc = new ContaCorrente("001", 1000.0, 500.0);
        final ContaPoupanca cp = new ContaPoupanca("002", 2000.0, 0.02);
        final FundoDeRendaFixa frf = new FundoDeRendaFixa("003", 5000.0, 0.01);

        cliente.adicionarConta(cc);
        cliente.adicionarConta(cp);
        cliente.adicionarConta(frf);

        banco.adicionarCliente(cliente);

        System.out.println("Extratos de Todos os Clientes:");
        System.out.println(banco.gerarExtratos());

        System.out.println("\nInvestimento Total do Cliente:");
        System.out.println(cliente.investimentoTotal());
    }
}
