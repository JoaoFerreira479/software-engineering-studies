package aed2.domain.nivelamento;

public final class OrdenarTresNumeros {

    private OrdenarTresNumeros() {}

    public static int[] ordenarTres(int a, int b, int c) {
        int[] v = { a, b, c };
        if (v[0] > v[1]) { int t = v[0]; v[0] = v[1]; v[1] = t; }
        if (v[1] > v[2]) { int t = v[1]; v[1] = v[2]; v[2] = t; }
        if (v[0] > v[1]) { int t = v[0]; v[0] = v[1]; v[1] = t; }
        return v;
    }
}
