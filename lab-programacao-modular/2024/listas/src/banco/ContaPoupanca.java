package banco;

public final class ContaPoupanca extends Conta {

    private final double rendimentoMensal;

    public ContaPoupanca(final String numero, final double saldoInicial, final double rendimentoMensal) {
        super(numero, saldoInicial);
        if (rendimentoMensal < 0 || rendimentoMensal > 1) {
            throw new IllegalArgumentException(
                "Rendimento mensal deve estar entre 0 e 1 (ex.: 0,02 para 2%): " + rendimentoMensal);
        }
        this.rendimentoMensal = rendimentoMensal;
    }

    public double getRendimentoMensal() {
        return rendimentoMensal;
    }

    @Override
    public String gerarExtrato() {
        return "Conta Poupança: " + getNumero() + "\nSaldo: " + getSaldo()
            + "\nRendimento Mensal: " + rendimentoMensal;
    }
}
