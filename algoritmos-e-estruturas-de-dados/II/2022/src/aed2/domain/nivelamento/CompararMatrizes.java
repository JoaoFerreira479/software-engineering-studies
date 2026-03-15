package aed2.domain.nivelamento;

public final class CompararMatrizes {

    private CompararMatrizes() {}

    public static boolean mesmasDimensoes(int[][] a, int[][] b) {
        if (a == null || b == null) return false;
        if (a.length != b.length) return false;
        if (a.length == 0) return true;
        return a[0] != null && b[0] != null && a[0].length == b[0].length;
    }

    public static boolean saoIguais(int[][] a, int[][] b) {
        if (a == null || b == null || a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == null || b[i] == null || a[i].length != b[i].length) return false;
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] != b[i][j]) return false;
            }
        }
        return true;
    }
}
