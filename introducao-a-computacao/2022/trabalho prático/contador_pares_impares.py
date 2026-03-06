import sys
from dataclasses import dataclass
from typing import List


@dataclass(frozen=True)
class ResultadoContagem:
    pares: int
    impares: int

    def __post_init__(self) -> None:
        if self.pares < 0 or self.impares < 0:
            raise ValueError("pares e impares devem ser não negativos")


def contar_pares_impares(numeros: List[int]) -> ResultadoContagem:
    pares = sum(1 for n in numeros if n % 2 == 0)
    impares = len(numeros) - pares
    return ResultadoContagem(pares=pares, impares=impares)


def formatar_resultado(r: ResultadoContagem) -> str:
    return (
        "\n===== Resultado =====\n"
        f"Quantidade de números pares: {r.pares}\n"
        f"Quantidade de números ímpares: {r.impares}"
    )


def ler_quantidade() -> int | None:
    try:
        return int(input("Quantos números deseja inserir? ").strip())
    except (ValueError, EOFError):
        return None


def ler_numeros(quantidade: int) -> List[int] | None:
    if quantidade <= 0:
        return None
    numeros: List[int] = []
    for i in range(quantidade):
        try:
            valor = int(input(f"Digite o {i + 1}º número: ").strip())
        except (ValueError, EOFError):
            return None
        numeros.append(valor)
    return numeros


def run() -> int:
    q = ler_quantidade()
    if q is None:
        print("Erro: entrada inválida. Informe um número inteiro.", file=sys.stderr)
        return 1
    if q <= 0:
        print("Erro: a quantidade deve ser maior que zero.", file=sys.stderr)
        return 1

    numeros = ler_numeros(q)
    if numeros is None:
        print("Erro: entrada inválida. Informe um número inteiro.", file=sys.stderr)
        return 1

    resultado = contar_pares_impares(numeros)
    print(formatar_resultado(resultado))
    return 0


def main() -> None:
    sys.exit(run())


if __name__ == "__main__":
    main()
