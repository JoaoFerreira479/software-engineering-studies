import sys
from typing import List, Optional


def ler_quantidade() -> Optional[int]:
    try:
        return int(input("Quantos números deseja inserir? ").strip())
    except (ValueError, EOFError):
        return None


def ler_numeros(quantidade: int) -> Optional[List[int]]:
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


def print_msg(msg: str) -> None:
    print(msg, end="")


def print_erro(msg: str) -> None:
    print(msg, file=sys.stderr)
