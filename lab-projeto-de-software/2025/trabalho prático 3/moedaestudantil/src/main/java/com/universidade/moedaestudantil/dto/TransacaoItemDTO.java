package com.universidade.moedaestudantil.dto;

import java.time.LocalDateTime;

import com.universidade.moedaestudantil.domain.TipoTransacao;

public record TransacaoItemDTO(
    Long id,
    Double quantidade,
    String motivo,
    LocalDateTime data,
    TipoTransacao tipo,
    Long professorId,
    String professorNome,
    Long alunoId,
    String alunoNome,
    Long vantagemId,
    String vantagemDescricao,
    String vantagemCupom
) {
    public static TransacaoItemDTO from(com.universidade.moedaestudantil.model.Transacao t) {
        if (t == null) return null;
        return new TransacaoItemDTO(
            t.getId(),
            t.getQuantidade(),
            t.getMotivo(),
            t.getData(),
            t.getTipo(),
            t.getProfessor() != null ? t.getProfessor().getId() : null,
            t.getProfessor() != null ? t.getProfessor().getNome() : null,
            t.getAluno() != null ? t.getAluno().getId() : null,
            t.getAluno() != null ? t.getAluno().getNome() : null,
            t.getVantagem() != null ? t.getVantagem().getId() : null,
            t.getVantagem() != null ? t.getVantagem().getDescricao() : null,
            t.getVantagemCupom()
        );
    }
}
