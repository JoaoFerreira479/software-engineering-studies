package revisao2.io;

public final class Console {

    private Console() {
    }

    public static void print(final String message) {
        System.out.print(message);
    }

    public static void println(final String message) {
        System.out.println(message);
    }

    public static void printf(final String format, final Object... args) {
        System.out.printf(format, args);
    }

    public static void erro(final String message) {
        System.err.println(message);
    }
}
