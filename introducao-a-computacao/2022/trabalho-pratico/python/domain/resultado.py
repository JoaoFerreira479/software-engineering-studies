from dataclasses import dataclass


@dataclass(frozen=True)
class ResultadoContagem:
    pares: int
    impares: int

    def __post_init__(self) -> None:
        if self.pares < 0 or self.impares < 0:
            raise ValueError("pares e impares devem ser não negativos")
