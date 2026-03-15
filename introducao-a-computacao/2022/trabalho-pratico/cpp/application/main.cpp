#include <cstdlib>
#include <memory>
#include <vector>
#include "../domain/contador.hpp"
#include "../infrastructure/io.hpp"

static std::unique_ptr<std::vector<int>> ler_numeros(std::istream& in, std::ostream& out)
{
    int quantidade;
    out << "Quantos números deseja inserir? ";
    if (!io_ler_int(in, quantidade) || quantidade <= 0)
        return nullptr;

    auto numeros = std::make_unique<std::vector<int>>();
    numeros->reserve(static_cast<size_t>(quantidade));

    for (int i = 0; i < quantidade; i++) {
        out << "Digite o " << (i + 1) << "º número: ";
        int valor;
        if (!io_ler_int(in, valor))
            return nullptr;
        numeros->push_back(valor);
    }
    return numeros;
}

int main()
{
    std::unique_ptr<std::vector<int>> numeros = ler_numeros(std::cin, std::cout);
    if (!numeros) {
        std::cerr << "Erro: entrada inválida ou quantidade deve ser maior que zero.\n";
        return EXIT_FAILURE;
    }

    ResultadoContagem resultado = contar_pares_impares(*numeros);
    io_exibir_resultado(std::cout, resultado);
    return EXIT_SUCCESS;
}
