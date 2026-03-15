#include "io.hpp"
#include <limits>

bool io_ler_int(std::istream& in, int& out)
{
    if (!(in >> out)) {
        in.clear();
        in.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
        return false;
    }
    return true;
}

void io_exibir_resultado(std::ostream& out, const ResultadoContagem& r)
{
    out << "\n===== Resultado =====\n";
    out << "Quantidade de números pares: " << r.pares << '\n';
    out << "Quantidade de números ímpares: " << r.impares << '\n';
}
