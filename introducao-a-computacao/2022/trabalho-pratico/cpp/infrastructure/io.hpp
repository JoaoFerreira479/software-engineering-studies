#ifndef IC_IO_HPP
#define IC_IO_HPP

#include "resultado.hpp"
#include <iostream>

bool io_ler_int(std::istream& in, int& out);
void io_exibir_resultado(std::ostream& out, const ResultadoContagem& r);

#endif
