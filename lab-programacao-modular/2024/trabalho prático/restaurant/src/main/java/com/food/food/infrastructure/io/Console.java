package com.food.food.infrastructure.io;


public final class Console {

    private Console() {
    }

    public static void print(String message) {
        System.out.print(message);
    }

    public static void println(String message) {
        System.out.println(message);
    }

    public static void erro(String message) {
        System.err.println(message);
    }
}
