package programarcomputadoresarquivos;
import lab.listas.infrastructure.io.Console;
import lab.listas.infrastructure.io.EntradaUtil;

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
            Console.erro("Erro ao ler o arquivo de usuários: " + e.getMessage());
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
                    default -> Console.println("Opção inválida!");
                }
            }
        }
    }

    private static void exibirMenu() {
        Console.println("\n=== MENU ===");
        Console.println("1. Cadastrar usuário");
        Console.println("2. Autenticar usuário");
        Console.println("3. Sair");
    }

    private static int lerOpcao(final Scanner scanner) {
        while (true) {
            Console.print("Escolha uma opção: ");
            final String linha = scanner.nextLine().trim();
            try {
                final int n = Integer.parseInt(linha);
                if (n >= 1 && n <= 3) return n;
            } catch (NumberFormatException ignored) {
            }
            Console.println("Opção inválida. Digite 1, 2 ou 3.");
        }
    }

    private static void cadastrarUsuario(final Scanner scanner, final Map<String, String> usuarios) {
        Console.print("Digite o nome de usuário: ");
        final String nome = scanner.nextLine().trim();
        if (nome.isEmpty()) {
            Console.println("Nome não pode ser vazio.");
            return;
        }
        if (usuarios.containsKey(nome)) {
            Console.println("Usuário já cadastrado.");
            return;
        }
        final String senha = solicitarSenha(scanner);
        usuarios.put(nome, CriptografiaSenha.criptografar(senha));
        Console.println("Usuário cadastrado com sucesso!");
    }

    private static void autenticarUsuario(final Scanner scanner, final Map<String, String> usuarios) {
        Console.print("Digite o nome de usuário: ");
        final String nome = scanner.nextLine().trim();
        if (!usuarios.containsKey(nome)) {
            Console.println("Usuário não encontrado.");
            return;
        }
        final String senhaCriptografada = usuarios.get(nome);
        for (int i = 1; i <= MAX_TENTATIVAS; i++) {
            Console.print("Digite a senha: ");
            final String senha = scanner.nextLine();
            if (CriptografiaSenha.criptografar(senha).equals(senhaCriptografada)) {
                Console.println("Autenticação bem-sucedida!");
                return;
            }
            Console.println("Senha incorreta. Tentativa " + i + " de " + MAX_TENTATIVAS);
        }
        Console.println("Número máximo de tentativas excedido.");
    }

    private static String solicitarSenha(final Scanner scanner) {
        while (true) {
            Console.print("Digite a senha (" + MIN_CARACTERES_SENHA + " a " + MAX_CARACTERES_SENHA + " caracteres): ");
            final String senha = scanner.nextLine();
            if (senha.length() < MIN_CARACTERES_SENHA || senha.length() > MAX_CARACTERES_SENHA) {
                Console.println("A senha deve ter entre " + MIN_CARACTERES_SENHA + " e " + MAX_CARACTERES_SENHA + " caracteres.");
                continue;
            }
            Console.print("Confirme a senha: ");
            final String confirmacao = scanner.nextLine();
            if (!senha.equals(confirmacao)) {
                Console.println("As senhas não coincidem. Tente novamente.");
            } else {
                return senha;
            }
        }
    }

    private static void salvarESair(final Map<String, String> usuarios) {
        try {
            RepositorioUsuarios.salvar(ARQUIVO_USUARIOS, usuarios);
            Console.println("Encerrando o programa...");
        } catch (IOException e) {
            Console.erro("Erro ao salvar os usuários: " + e.getMessage());
        }
    }
}
