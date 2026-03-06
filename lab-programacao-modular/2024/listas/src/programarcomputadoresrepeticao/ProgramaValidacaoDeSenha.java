package programarcomputadoresrepeticao;

import java.util.Scanner;

public final class ProgramaValidacaoDeSenha {

    private static final int MIN_CARACTERES = 4;
    private static final int MAX_CARACTERES = 8;
    private static final int MAX_TENTATIVAS = 3;

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final Autenticador autenticador = new Autenticador();
            System.out.println("Bem-vindo ao sistema de autenticação.");

            for (int tentativa = 1; tentativa <= MAX_TENTATIVAS; tentativa++) {
                final String nome = EntradaUtil.lerLinha(scanner, "Digite seu nome de usuário: ");
                if (!validarTamanho(nome, MIN_CARACTERES, MAX_CARACTERES)) {
                    System.out.println("Erro: O nome deve ter entre " + MIN_CARACTERES + " e " + MAX_CARACTERES + " caracteres.");
                    continue;
                }
                final String senha = EntradaUtil.lerLinha(scanner, "Digite sua senha: ");
                if (!validarTamanho(senha, MIN_CARACTERES, MAX_CARACTERES)) {
                    System.out.println("Erro: A senha deve ter entre " + MIN_CARACTERES + " e " + MAX_CARACTERES + " caracteres.");
                    continue;
                }
                if (autenticador.validarCredenciais(nome, senha)) {
                    System.out.println("Autenticação bem-sucedida. Bem-vindo, " + nome + "!");
                    return;
                }
                System.out.println("Erro: Nome de usuário ou senha incorretos.");
                if (tentativa < MAX_TENTATIVAS) {
                    System.out.println("Você ainda tem " + (MAX_TENTATIVAS - tentativa) + " tentativa(s).");
                }
            }
            System.out.println("Número máximo de tentativas atingido. Acesso bloqueado.");
        }
    }

    private static boolean validarTamanho(final String entrada, final int min, final int max) {
        return entrada != null && entrada.length() >= min && entrada.length() <= max;
    }
}
