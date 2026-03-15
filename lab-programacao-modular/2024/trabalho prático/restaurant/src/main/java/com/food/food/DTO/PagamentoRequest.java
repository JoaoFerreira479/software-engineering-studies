package com.food.food.DTO;

public class PagamentoRequest {
    private Long mesaId;
    private String metodo;
    private String nomeEmissorPix;
    private String nomeBancoDebito;
    private String bandeiraCartao;

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getNomeEmissorPix() {
        return nomeEmissorPix;
    }

    public void setNomeEmissorPix(String nomeEmissorPix) {
        this.nomeEmissorPix = nomeEmissorPix;
    }

    public String getNomeBancoDebito() {
        return nomeBancoDebito;
    }

    public void setNomeBancoDebito(String nomeBancoDebito) {
        this.nomeBancoDebito = nomeBancoDebito;
    }

    public String getBandeiraCartao() {
        return bandeiraCartao;
    }

    public void setBandeiraCartao(String bandeiraCartao) {
        this.bandeiraCartao = bandeiraCartao;
    }

    public Long getMesaId() {
        return this.mesaId;
    }

    public void setMesaId(Long mesaId) {
        this.mesaId = mesaId;
    }

}
