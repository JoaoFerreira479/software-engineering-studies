import sys
from pathlib import Path


sys.path.insert(0, str(Path(__file__).resolve().parent.parent))

from domain.contador import contar_pares_impares, formatar_resultado
from infrastructure.io import ler_quantidade, ler_numeros, print_erro


def run() -> int:
    q = ler_quantidade()
    if q is None:
        print_erro("Erro: entrada inválida. Informe um número inteiro.")
        return 1
    if q <= 0:
        print_erro("Erro: a quantidade deve ser maior que zero.")
        return 1

    numeros = ler_numeros(q)
    if numeros is None:
        print_erro("Erro: entrada inválida. Informe um número inteiro.")
        return 1

    resultado = contar_pares_impares(numeros)
    print(formatar_resultado(resultado))
    return 0


def main() -> None:
    sys.exit(run())


if __name__ == "__main__":
    main()
