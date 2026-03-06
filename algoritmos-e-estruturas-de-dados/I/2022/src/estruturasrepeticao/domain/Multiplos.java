package estruturasrepeticao.domain;

public final class Multiplos {

    private Multiplos() {
    }

    public static String multiplosDeXAteY(final int x, final int y) {
        if (x <= 0 || y <= 0) {
            throw new IllegalArgumentException("X e Y devem ser inteiros positivos.");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= y; i++) {
            if (i % x == 0) {
                if (sb.length() > 0) {
                    sb.append(' ');
                }
                sb.append(i);
            }
        }
        return sb.toString();
    }
}
