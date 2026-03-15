package com.universidade.aluguelcarros.application;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PedidoRequest(
    @NotNull(message = "ID do cliente é obrigatório")
    Long clienteId,
    @NotNull(message = "ID do agente é obrigatório")
    Long agenteId,
    @NotNull(message = "ID do automóvel é obrigatório")
    Long automovelId,
    @Size(max = 50)
    String situacao,
    @Size(max = 1000)
    String observacoesCliente,
    @Size(max = 1000)
    String observacoesAgente
) {}
