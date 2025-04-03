package com.meuprojeto.meuapp.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meuprojeto.meuapp.config.TokenService;
import com.meuprojeto.meuapp.dtos.AlteraSenhaDTO;
import com.meuprojeto.meuapp.dtos.EsqueceuSenhaRequestDTO;
import com.meuprojeto.meuapp.dtos.LoginRequestDTO;
import com.meuprojeto.meuapp.dtos.RegisterRequestDTO;
import com.meuprojeto.meuapp.dtos.ResponseDTO;
import com.meuprojeto.meuapp.model.Usuario;
import com.meuprojeto.meuapp.repository.UsuarioRepository;
import com.meuprojeto.meuapp.service.EmailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final EmailService emailService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequestDTO body) {
        Usuario usuario = this.usuarioRepository.findByEmail(body.email())
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        if (passwordEncoder.matches(body.senha(), usuario.getSenha())) {
            String token = this.tokenService.generateToken(usuario);
            return ResponseEntity.ok(new ResponseDTO(usuario.getNome(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody RegisterRequestDTO body) {
        Optional<Usuario> usuario = this.usuarioRepository.findByEmail(body.email());

        if (usuario.isEmpty()) {
            Usuario novoUsuario = new Usuario();
            novoUsuario.setSenha(passwordEncoder.encode(body.senha()));
            novoUsuario.setEmail(body.email());
            novoUsuario.setNome(body.nome());
            this.usuarioRepository.save(novoUsuario);

            String token = this.tokenService.generateToken(novoUsuario);
            return ResponseEntity.ok(new ResponseDTO(novoUsuario.getNome(), token));

        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/esqueceu-senha")
    public ResponseEntity<String> forgotPassword(@RequestBody EsqueceuSenhaRequestDTO body) {
        Usuario usuario = this.usuarioRepository.findByEmail(body.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        String resetToken = tokenService.generateResetToken(usuario);
        String resetLink = "http://localhost:4200/altera-senha?token=" + resetToken;
        emailService.envioEmailAlteraSenha(usuario.getEmail(), resetLink);

        return ResponseEntity.ok("Link de redefinição de senha enviado para o e-mail.");
    }

    @PostMapping("/altera-senha")
    public ResponseEntity<String> resetPassword(@RequestBody AlteraSenhaDTO body) {
        String email = tokenService.validateToken(body.token());
        if (email == null) {
            return ResponseEntity.badRequest().body("Token inválido ou expirado.");
        }

        Usuario usuario = this.usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setSenha(passwordEncoder.encode(body.novaSenha()));
        this.usuarioRepository.save(usuario);

        return ResponseEntity.ok("Senha redefinida com sucesso.");
    }

}
