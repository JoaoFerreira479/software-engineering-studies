package lab.listas.banco;


public abstract class Conta {

    private final String numero;
    private double saldo;

    protected Conta(String numero, double saldoInicial) {
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("Número da conta não pode ser nulo ou vazio");
        }
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("Saldo inicial não pode ser negativo: " + saldoInicial);
        }
        this.numero = numero;
        this.saldo = saldoInicial;
    }

    public final String getNumero() {
        return numero;
    }

    public final double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor para depósito deve ser maior que zero: " + valor);
        }
        this.saldo += valor;
    }

    public void sacar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor para saque deve ser maior que zero: " + valor);
        }
        if (saldo < valor) {
            throw new IllegalArgumentException("Saldo insuficiente. Saldo: " + saldo + ", valor: " + valor);
        }
        this.saldo -= valor;
    }

    protected final void setSaldo(double novoSaldo) {
        this.saldo = novoSaldo;
    }

    public abstract String gerarExtrato();
}
