#include <iostream>
#include <limits>
#include <memory>
#include <vector>
#include <cstdlib>

struct ResultadoContagem {
    int pares   = 0;
    int impares = 0;
};

ResultadoContagem contar_pares_impares(const std::vector<int>& numeros)
{
    ResultadoContagem r;
    for (int n : numeros) {
        if (n % 2 == 0)
            r.pares++;
        else
            r.impares++;
    }
    return r;
}

void exibir_resultado(const ResultadoContagem& r)
{
    std::cout << "\n===== Resultado =====\n";
    std::cout << "Quantidade de números pares: " << r.pares << '\n';
    std::cout << "Quantidade de números ímpares: " << r.impares << '\n';
}

bool ler_int(std::istream& in, int& out)
{
    if (!(in >> out)) {
        in.clear();
        in.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
        return false;
    }
    return true;
}

std::unique_ptr<std::vector<int>> ler_numeros_do_usuario(std::istream& in, std::ostream& out)
{
    int quantidade;
    out << "Quantos números deseja inserir? ";
    if (!ler_int(in, quantidade) || quantidade <= 0)
        return nullptr;

    auto numeros = std::make_unique<std::vector<int>>();
    numeros->reserve(static_cast<size_t>(quantidade));

    for (int i = 0; i < quantidade; i++) {
        out << "Digite o " << (i + 1) << "º número: ";
        int valor;
        if (!ler_int(in, valor))
            return nullptr;
        numeros->push_back(valor);
    }
    return numeros;
}

int main()
{
    std::unique_ptr<std::vector<int>> numeros = ler_numeros_do_usuario(std::cin, std::cout);
    if (!numeros) {
        std::cerr << "Erro: entrada inválida ou quantidade deve ser maior que zero.\n";
        return EXIT_FAILURE;
    }

    ResultadoContagem resultado = contar_pares_impares(*numeros);
    exibir_resultado(resultado);
    return EXIT_SUCCESS;
}
