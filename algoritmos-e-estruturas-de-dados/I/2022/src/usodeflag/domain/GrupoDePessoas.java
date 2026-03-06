package usodeflag.domain;

public final class GrupoDePessoas {

    private static final char MASCULINO = 'M';
    private static final char FEMININO = 'F';

    private GrupoDePessoas() {
    }

    public static ResultadoGrupo calcular(final int[] idades, final char[] generos, final int n) {
        if (idades == null || generos == null || n < 0 || n > idades.length || n > generos.length) {
            throw new IllegalArgumentException("Argumentos inválidos.");
        }
        int homens = 0;
        int mulheres = 0;
        int somaIdadesHomens = 0;
        int maiorIdade = Integer.MIN_VALUE;
        int menorIdade = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int idade = idades[i];
            char g = Character.toUpperCase(generos[i]);
            if (idade > maiorIdade) maiorIdade = idade;
            if (idade < menorIdade) menorIdade = idade;
            if (g == MASCULINO) {
                homens++;
                somaIdadesHomens += idade;
            } else if (g == FEMININO) {
                mulheres++;
            }
        }

        double mediaIdadeHomens = homens > 0 ? (double) somaIdadesHomens / homens : 0;
        boolean temDados = n > 0;
        return new ResultadoGrupo(
                temDados,
                homens,
                mulheres,
                mediaIdadeHomens,
                temDados ? maiorIdade : 0,
                temDados ? menorIdade : 0,
                n
        );
    }

    public record ResultadoGrupo(
            boolean temDados,
            int quantidadeHomens,
            int quantidadeMulheres,
            double mediaIdadeHomens,
            int maiorIdade,
            int menorIdade,
            int totalPessoas
    ) {
    }
}
