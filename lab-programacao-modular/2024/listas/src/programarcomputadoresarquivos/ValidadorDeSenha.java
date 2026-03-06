package programarcomputadoresarquivos;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Scanner;

public final class ValidadorDeSenha {

    private static final Path ARQUIVO_USUARIOS = Path.of("usuarios.txt");
    private static final int MAX_TENTATIVAS = 3;
    private static final int MIN_CARACTERES_SENHA = 4;
    private static final int MAX_CARACTERES_SENHA = 8;

    public static void main(final String[] args) {
        Map<String, String> usuarios;
        try {
            usuarios = RepositorioUsuarios.carregar(ARQUIVO_USUARIOS);
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de usuários: " + e.getMessage());
            usuarios = new java.util.HashMap<>();
        }

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                exibirMenu();
                final int opcao = lerOpcao(scanner);

                switch (opcao) {
                    case 1 -> cadastrarUsuario(scanner, usuarios);
                    case 2 -> autenticarUsuario(scanner, usuarios);
                    case 3 -> {
                        salvarESair(usuarios);
                        return;
                    }
                    default -> System.out.println("Opção inválida!");
                }
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1. Cadastrar usuário");
        System.out.println("2. Autenticar usuário");
        System.out.println("3. Sair");
    }

    private static int lerOpcao(final Scanner scanner) {
        while (true) {
            System.out.print("Escolha uma opção: ");
            final String linha = scanner.nextLine().trim();
            try {
                final int n = Integer.parseInt(linha);
                if (n >= 1 && n <= 3) return n;
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Opção inválida. Digite 1, 2 ou 3.");
        }
    }

    private static void cadastrarUsuario(final Scanner scanner, final Map<String, String> usuarios) {
        System.out.print("Digite o nome de usuário: ");
        final String nome = scanner.nextLine().trim();
        if (nome.isEmpty()) {
            System.out.println("Nome não pode ser vazio.");
            return;
        }
        if (usuarios.containsKey(nome)) {
            System.out.println("Usuário já cadastrado.");
            return;
        }
        final String senha = solicitarSenha(scanner);
        usuarios.put(nome, CriptografiaSenha.criptografar(senha));
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private static void autenticarUsuario(final Scanner scanner, final Map<String, String> usuarios) {
        System.out.print("Digite o nome de usuário: ");
        final String nome = scanner.nextLine().trim();
        if (!usuarios.containsKey(nome)) {
            System.out.println("Usuário não encontrado.");
            return;
        }
        final String senhaCriptografada = usuarios.get(nome);
        for (int i = 1; i <= MAX_TENTATIVAS; i++) {
            System.out.print("Digite a senha: ");
            final String senha = scanner.nextLine();
            if (CriptografiaSenha.criptografar(senha).equals(senhaCriptografada)) {
                System.out.println("Autenticação bem-sucedida!");
                return;
            }
            System.out.println("Senha incorreta. Tentativa " + i + " de " + MAX_TENTATIVAS);
        }
        System.out.println("Número máximo de tentativas excedido.");
    }

    private static String solicitarSenha(final Scanner scanner) {
        while (true) {
            System.out.print("Digite a senha (" + MIN_CARACTERES_SENHA + " a " + MAX_CARACTERES_SENHA + " caracteres): ");
            final String senha = scanner.nextLine();
            if (senha.length() < MIN_CARACTERES_SENHA || senha.length() > MAX_CARACTERES_SENHA) {
                System.out.println("A senha deve ter entre " + MIN_CARACTERES_SENHA + " e " + MAX_CARACTERES_SENHA + " caracteres.");
                continue;
            }
            System.out.print("Confirme a senha: ");
            final String confirmacao = scanner.nextLine();
            if (!senha.equals(confirmacao)) {
                System.out.println("As senhas não coincidem. Tente novamente.");
            } else {
                return senha;
            }
        }
    }

    private static void salvarESair(final Map<String, String> usuarios) {
        try {
            RepositorioUsuarios.salvar(ARQUIVO_USUARIOS, usuarios);
            System.out.println("Encerrando o programa...");
        } catch (IOException e) {
            System.err.println("Erro ao salvar os usuários: " + e.getMessage());
        }
    }
}
