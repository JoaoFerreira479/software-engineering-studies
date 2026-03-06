package banco;

public final class ContaCorrente extends Conta {

    private final double limiteChequeEspecial;

    public ContaCorrente(final String numero, final double saldoInicial, final double limiteChequeEspecial) {
        super(numero, saldoInicial);
        if (limiteChequeEspecial < 0) {
            throw new IllegalArgumentException("Limite do cheque especial não pode ser negativo: " + limiteChequeEspecial);
        }
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    public double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    @Override
    public void sacar(final double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor para saque deve ser maior que zero: " + valor);
        }
        final double saldoAposSaque = getSaldo() - valor;
        if (saldoAposSaque < -limiteChequeEspecial) {
            throw new IllegalArgumentException(
                "Saldo insuficiente mesmo com cheque especial. Saldo: " + getSaldo()
                    + ", limite: " + limiteChequeEspecial + ", valor: " + valor);
        }
        setSaldo(saldoAposSaque);
    }

    @Override
    public String gerarExtrato() {
        return "Conta Corrente: " + getNumero() + "\nSaldo: " + getSaldo()
            + "\nLimite Cheque Especial: " + limiteChequeEspecial;
    }
}
