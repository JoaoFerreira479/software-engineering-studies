package estruturascondicionais.domain;

public final class OrdenarNumeros {

    private OrdenarNumeros() {
    }

    public static int[] ordenarTres(final int a, final int b, final int c) {
        int x = a;
        int y = b;
        int z = c;
        if (x > y) { int t = x; x = y; y = t; }
        if (y > z) { int t = y; y = z; z = t; }
        if (x > y) { int t = x; x = y; y = t; }
        return new int[] { x, y, z };
    }
}
