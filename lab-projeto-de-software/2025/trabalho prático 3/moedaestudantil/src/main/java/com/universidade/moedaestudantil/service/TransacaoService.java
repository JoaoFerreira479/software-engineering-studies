package com.universidade.moedaestudantil.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.universidade.moedaestudantil.domain.TipoTransacao;
import com.universidade.moedaestudantil.exception.EntidadeNaoEncontradaException;
import com.universidade.moedaestudantil.exception.RegraNegocioException;
import com.universidade.moedaestudantil.model.Aluno;
import com.universidade.moedaestudantil.model.Professor;
import com.universidade.moedaestudantil.model.Transacao;
import com.universidade.moedaestudantil.model.Vantagem;
import com.universidade.moedaestudantil.repository.AlunoRepository;
import com.universidade.moedaestudantil.repository.ProfessorRepository;
import com.universidade.moedaestudantil.repository.TransacaoRepository;
import com.universidade.moedaestudantil.repository.VantagemRepository;

@Service
@Transactional
public class TransacaoService {

    private static final Logger log = LoggerFactory.getLogger(TransacaoService.class);

    private final TransacaoRepository transacaoRepo;
    private final AlunoRepository alunoRepo;
    private final ProfessorRepository professorRepo;
    private final VantagemRepository vantagemRepo;
    private final EmailService emailService;

    public TransacaoService(TransacaoRepository transacaoRepo,
                            AlunoRepository alunoRepo,
                            ProfessorRepository professorRepo,
                            VantagemRepository vantagemRepo,
                            EmailService emailService) {
        this.transacaoRepo = transacaoRepo;
        this.alunoRepo = alunoRepo;
        this.professorRepo = professorRepo;
        this.vantagemRepo = vantagemRepo;
        this.emailService = emailService;
    }

    @Transactional(readOnly = true)
    public List<Transacao> findAll() {
        return transacaoRepo.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Transacao> findById(Long id) {
        return transacaoRepo.findById(id);
    }

    public Transacao save(Transacao transacao) {
        return transacaoRepo.save(transacao);
    }

    public void deleteById(Long id) {
        transacaoRepo.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Transacao> findByAlunoId(Long alunoId) {
        Aluno aluno = alunoRepo.findById(alunoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Aluno", alunoId));
        return transacaoRepo.findByAluno(aluno);
    }

    @Transactional(readOnly = true)
    public List<Transacao> findByProfessorId(Long professorId) {
        Professor professor = professorRepo.findById(professorId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Professor", professorId));
        return transacaoRepo.findByProfessor(professor);
    }

    public Transacao processarTransacao(Transacao transacao) {
        TipoTransacao tipo = transacao.getTipo();
        if (tipo == null) {
            throw new RegraNegocioException("Tipo da transação é obrigatório");
        }
        return switch (tipo) {
            case ENVIO -> processarEnvio(transacao);
            case RESGATE -> processarResgate(transacao);
            default -> save(transacao);
        };
    }

    private Transacao processarEnvio(Transacao transacao) {
        if (transacao.getProfessor() == null || transacao.getAluno() == null) {
            throw new RegraNegocioException("Professor e aluno são obrigatórios para transação de ENVIO");
        }

        Professor professor = professorRepo.findById(transacao.getProfessor().getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Professor", transacao.getProfessor().getId()));
        Aluno aluno = alunoRepo.findById(transacao.getAluno().getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Aluno", transacao.getAluno().getId()));

        Double quantidade = transacao.getQuantidade();
        if (quantidade == null || quantidade <= 0) {
            throw new RegraNegocioException("Quantidade deve ser positiva");
        }
        if (professor.getSaldo() < quantidade) {
            throw new RegraNegocioException(
                    "Professor não possui saldo suficiente. Saldo atual: " + professor.getSaldo());
        }

        professor.setSaldo(professor.getSaldo() - quantidade);
        aluno.setSaldo(aluno.getSaldo() + quantidade);
        professorRepo.save(professor);
        alunoRepo.save(aluno);

        transacao.setProfessor(professor);
        transacao.setAluno(aluno);
        transacao.setTipo(TipoTransacao.ENVIO);
        save(transacao);

        enviarEmailEnvio(aluno, professor, transacao);
        return transacao;
    }

    private void enviarEmailEnvio(Aluno aluno, Professor professor, Transacao transacao) {
        String assunto = "Notificação de Recebimento de Moeda Estudantil";
        String conteudo = String.format(
                "Olá %s,%n%nVocê recebeu %.2f moedas estudantis do professor %s.%nMotivo: %s%n%nAtenciosamente,%nSistema de Moeda Estudantil",
                aluno.getNome(), transacao.getQuantidade(), professor.getNome(), transacao.getMotivo());
        enviarEmailSeguro(aluno.getEmail(), assunto, conteudo, "aluno (envio)");
    }

    private Transacao processarResgate(Transacao transacao) {
        if (transacao.getAluno() == null || transacao.getVantagem() == null) {
            throw new RegraNegocioException("Aluno e vantagem são obrigatórios para transação de RESGATE");
        }

        Aluno aluno = alunoRepo.findById(transacao.getAluno().getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Aluno", transacao.getAluno().getId()));
        Vantagem vantagem = vantagemRepo.findById(transacao.getVantagem().getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Vantagem", transacao.getVantagem().getId()));

        if (aluno.getSaldo() < vantagem.getCusto()) {
            throw new RegraNegocioException(
                    "Aluno não possui saldo suficiente. Saldo atual: " + aluno.getSaldo() + ", Custo: " + vantagem.getCusto());
        }

        aluno.setSaldo(aluno.getSaldo() - vantagem.getCusto());
        alunoRepo.save(aluno);

        String cupom = "CUPOM-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        transacao.setAluno(aluno);
        transacao.setVantagem(vantagem);
        transacao.setQuantidade(vantagem.getCusto());
        transacao.setTipo(TipoTransacao.RESGATE);
        transacao.setVantagemCupom(cupom);
        save(transacao);

        log.info("Resgate processado. Cupom: {}", cupom);
        enviarEmailsResgate(aluno, vantagem, cupom);
        return transacao;
    }

    private void enviarEmailsResgate(Aluno aluno, Vantagem vantagem, String cupom) {
        String assunto = "Notificação de Resgate de Vantagem";
        String conteudoAluno = String.format(
                "Olá %s,%n%nVocê resgatou: %s.%nCusto: %.2f moedas.%nCupom: %s%n%nAtenciosamente,%nSistema de Moeda Estudantil",
                aluno.getNome(), vantagem.getDescricao(), vantagem.getCusto(), cupom);
        enviarEmailSeguro(aluno.getEmail(), assunto, conteudoAluno, "aluno (resgate)");

        String conteudoEmpresa = String.format(
                "Atenção %s,%n%nO aluno %s resgatou: %s.%nCusto: %.2f moedas.%nCupom: %s%n%nAtenciosamente,%nSistema de Moeda Estudantil",
                vantagem.getEmpresa().getNome(), aluno.getNome(), vantagem.getDescricao(), vantagem.getCusto(), cupom);
        enviarEmailSeguro(vantagem.getEmpresa().getEmail(), assunto, conteudoEmpresa, "empresa (resgate)");
    }

    private void enviarEmailSeguro(String destinatario, String assunto, String conteudo, String contexto) {
        try {
            emailService.sendMail(destinatario, assunto, conteudo);
        } catch (MessagingException e) {
            log.warn("Falha ao enviar email para {}: {}", contexto, e.getMessage());
        }
    }
}
