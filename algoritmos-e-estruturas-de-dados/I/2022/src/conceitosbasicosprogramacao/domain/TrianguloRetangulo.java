package conceitosbasicosprogramacao.domain;

public final class TrianguloRetangulo {

    private final double catetoA;
    private final double catetoB;
    private final double hipotenusa;

    private TrianguloRetangulo(final double catetoA, final double catetoB, final double hipotenusa) {
        this.catetoA = catetoA;
        this.catetoB = catetoB;
        this.hipotenusa = hipotenusa;
    }

    public static TrianguloRetangulo comCatetos(final double catetoA, final double catetoB) {
        if (catetoA <= 0 || catetoB <= 0) {
            throw new IllegalArgumentException("Catetos devem ser positivos.");
        }
        double h = Math.sqrt(catetoA * catetoA + catetoB * catetoB);
        return new TrianguloRetangulo(catetoA, catetoB, h);
    }

    public double getHipotenusa() {
        return hipotenusa;
    }

    public double getSeno() {
        return catetoB / hipotenusa;
    }

    public double getCosseno() {
        return catetoA / hipotenusa;
    }

    public double getTangente() {
        return catetoB / catetoA;
    }
}
