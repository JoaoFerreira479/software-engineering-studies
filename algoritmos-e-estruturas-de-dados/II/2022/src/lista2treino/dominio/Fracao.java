package lista2treino.dominio;

public final class Fracao {

    private final int numerador;
    private final int denominador;

    public Fracao(int numerador, int denominador) {
        if (denominador == 0) {
            throw new ArithmeticException("Denominador não pode ser zero");
        }
        int mdc = mdc(Math.abs(numerador), Math.abs(denominador));
        this.numerador = ajustarSinal(numerador / mdc, denominador);
        this.denominador = Math.abs(denominador / mdc);
    }

    public Fracao adicionar(Fracao outra) {
        int novoNumerador = this.numerador * outra.denominador + outra.numerador * this.denominador;
        int novoDenominador = this.denominador * outra.denominador;
        return new Fracao(novoNumerador, novoDenominador);
    }

    public int getNumerador() { return numerador; }
    public int getDenominador() { return denominador; }

    @Override
    public String toString() {
        return denominador == 1 ? String.valueOf(numerador) : numerador + "/" + denominador;
    }

    private static int ajustarSinal(int numerador, int denominador) {
        return denominador < 0 ? -numerador : numerador;
    }

    private static int mdc(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}
