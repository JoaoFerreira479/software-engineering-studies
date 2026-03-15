package lab.listas.banco;

import lab.listas.infrastructure.io.Console;


public final class SistemaBancario {

    public static void main(String[] args) {
        Banco banco = new Banco();

        Cliente cliente = new Cliente("João Silva", "123456789", "Rua A, 123", "123.456.789-00");
        ContaCorrente cc = new ContaCorrente("001", 1000.0, 500.0);
        ContaPoupanca cp = new ContaPoupanca("002", 2000.0, 0.02);
        FundoDeRendaFixa frf = new FundoDeRendaFixa("003", 5000.0, 0.01);

        cliente.adicionarConta(cc);
        cliente.adicionarConta(cp);
        cliente.adicionarConta(frf);

        banco.adicionarCliente(cliente);

        Console.println("Extratos de Todos os Clientes:");
        Console.println(banco.gerarExtratos());

        Console.println("\nInvestimento Total do Cliente:");
        Console.println(String.valueOf(cliente.investimentoTotal()));
    }
}
