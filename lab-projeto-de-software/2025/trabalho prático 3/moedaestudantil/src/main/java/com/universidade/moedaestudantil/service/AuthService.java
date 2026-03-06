package com.universidade.moedaestudantil.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.universidade.moedaestudantil.config.security.TokenService;
import com.universidade.moedaestudantil.domain.TipoUsuario;
import com.universidade.moedaestudantil.dto.AuthResponse;
import com.universidade.moedaestudantil.dto.LoginRequest;
import com.universidade.moedaestudantil.dto.RegisterRequest;
import com.universidade.moedaestudantil.exception.RegraNegocioException;
import com.universidade.moedaestudantil.model.Aluno;
import com.universidade.moedaestudantil.model.EmpresaParceira;
import com.universidade.moedaestudantil.model.Professor;
import com.universidade.moedaestudantil.model.Usuario;
import com.universidade.moedaestudantil.repository.UsuarioRepository;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final AlunoService alunoService;
    private final EmpresaParceiraService empresaService;
    private final ProfessorService professorService;
    private final InstituicaoEnsinoService instituicaoService;

    public AuthService(UsuarioRepository usuarioRepository,
                       PasswordEncoder passwordEncoder,
                       TokenService tokenService,
                       AlunoService alunoService,
                       EmpresaParceiraService empresaService,
                       ProfessorService professorService,
                       InstituicaoEnsinoService instituicaoService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.alunoService = alunoService;
        this.empresaService = empresaService;
        this.professorService = professorService;
        this.instituicaoService = instituicaoService;
    }

    public Optional<AuthResponse> login(LoginRequest req) {
        Usuario user = usuarioRepository.findByEmail(req.email()).orElse(null);
        if (user == null || !passwordEncoder.matches(req.senha(), user.getSenha())) {
            return Optional.empty();
        }
        String token = tokenService.generateToken(user);
        return Optional.of(buildAuthResponse(token, user.getId(), user));
    }

    public AuthResponse register(RegisterRequest req) {
        String tipo = req.tipoNormalizado();
        if (usuarioRepository.findByEmail(req.email()).isPresent()) {
            throw new RegraNegocioException("Email já cadastrado");
        }
        return switch (tipo) {
            case "EMPRESA" -> registrarEmpresa(req);
            case "ALUNO" -> registrarAluno(req);
            case "PROFESSOR" -> registrarProfessor(req);
            default -> throw new RegraNegocioException("Tipo inválido. Use 'ALUNO', 'EMPRESA' ou 'PROFESSOR'");
        };
    }

    public AuthResponse getCurrentUser(String bearerToken) {
        String token = bearerToken != null ? bearerToken.replace("Bearer ", "").trim() : "";
        if (token.isEmpty()) {
            throw new RegraNegocioException("Token inválido");
        }
        String userEmail = tokenService.validateToken(token);
        if (userEmail == null) {
            throw new RegraNegocioException("Token inválido");
        }
        Usuario user = usuarioRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado"));
        return buildAuthResponse(token, user.getId(), user);
    }

    private AuthResponse registrarEmpresa(RegisterRequest req) {
        EmpresaParceira empresa = new EmpresaParceira();
        empresa.setNome(req.nome());
        empresa.setEmail(req.email());
        empresa.setCnpj(req.cnpj() != null ? req.cnpj() : "");
        empresa.setSenha(passwordEncoder.encode(req.senha()));
        EmpresaParceira saved = empresaService.save(empresa);
        String token = tokenService.generateToken(empresa);
        return buildAuthResponse(token, saved.getId(), saved);
    }

    private AuthResponse registrarAluno(RegisterRequest req) {
        Aluno aluno = new Aluno();
        aluno.setNome(req.nome());
        aluno.setEmail(req.email());
        aluno.setCpf(req.cpf() != null ? req.cpf() : "");
        aluno.setRg(req.rg() != null ? req.rg() : "");
        aluno.setEndereco(req.endereco() != null ? req.endereco() : "");
        aluno.setCurso(req.curso() != null ? req.curso() : "");
        aluno.setSenha(passwordEncoder.encode(req.senha()));
        if (req.instituicaoId() != null) {
            aluno.setInstituicao(instituicaoService.findById(req.instituicaoId())
                    .orElseThrow(() -> new RegraNegocioException("Instituição não encontrada")));
        }
        Aluno saved = alunoService.save(aluno);
        String token = tokenService.generateToken(aluno);
        return buildAuthResponse(token, saved.getId(), saved);
    }

    private AuthResponse registrarProfessor(RegisterRequest req) {
        Professor professor = new Professor();
        professor.setNome(req.nome());
        professor.setEmail(req.email());
        professor.setCpf(req.cpf() != null ? req.cpf() : "");
        professor.setDepartamento(req.departamento() != null ? req.departamento() : "");
        professor.setSenha(passwordEncoder.encode(req.senha()));
        if (req.instituicaoId() != null) {
            professor.setInstituicao(instituicaoService.findById(req.instituicaoId())
                    .orElseThrow(() -> new RegraNegocioException("Instituição não encontrada")));
        }
        Professor saved = professorService.save(professor);
        String token = tokenService.generateToken(professor);
        return buildAuthResponse(token, saved.getId(), saved);
    }

    private static AuthResponse buildAuthResponse(String token, Long id, Usuario user) {
        return new AuthResponse(token, id, TipoUsuario.from(user), user.getNome(), user.getEmail());
    }
}
