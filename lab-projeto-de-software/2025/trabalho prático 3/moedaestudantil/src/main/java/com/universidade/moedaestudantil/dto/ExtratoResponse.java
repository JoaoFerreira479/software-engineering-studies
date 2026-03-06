package com.universidade.moedaestudantil.dto;

import java.util.List;

public record ExtratoResponse(Double saldo, List<TransacaoItemDTO> transacoes) {}
