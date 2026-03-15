from .resultado import ResultadoContagem
from typing import List


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
