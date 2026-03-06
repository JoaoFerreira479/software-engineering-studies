package estruturascondicionais.domain;

public final class MediaAproveitamento {

    private MediaAproveitamento() {
    }

    public static double media(final double nota1, final double nota2, final double nota3, final double nota4) {
        validarNotas(nota1, nota2, nota3, nota4);
        return (nota1 + nota2 * 2 + nota3 + nota4 * 3) / 7.0;
    }

    public static String conceito(final double media) {
        if (media >= 90) return "A";
        if (media >= 75) return "B";
        if (media >= 60) return "C";
        if (media >= 40) return "D";
        return "E";
    }

    public static String status(final String conceito) {
        if ("A".equals(conceito) || "B".equals(conceito) || "C".equals(conceito)) {
            return "Aprovado";
        }
        return "Reprovado";
    }

    private static void validarNotas(final double n1, final double n2, final double n3, final double n4) {
        validarUmaNota(n1);
        validarUmaNota(n2);
        validarUmaNota(n3);
        validarUmaNota(n4);
    }

    private static void validarUmaNota(final double nota) {
        if (nota < 0 || nota > 100) {
            throw new IllegalArgumentException("A nota deve estar entre 0 e 100.");
        }
    }
}
