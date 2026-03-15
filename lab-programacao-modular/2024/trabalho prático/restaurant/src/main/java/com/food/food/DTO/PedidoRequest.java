package com.food.food.DTO;

import java.util.List;

public class PedidoRequest {
    private List<Long> itensIds;
    private Long requisicaoId;

    public List<Long> getItensIds() {
        return itensIds;
    }

    public void setItensIds(List<Long> itensIds) {
        this.itensIds = itensIds;
    }


    public Long getRequisicaoId() {
        return this.requisicaoId;
    }

    public void setRequisicaoId(Long requisicaoId) {
        this.requisicaoId = requisicaoId;
    }


}
