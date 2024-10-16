package com.meuprojeto.meuapp.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meuprojeto.meuapp.config.TokenService;
import com.meuprojeto.meuapp.dtos.LoginRequestDTO;
import com.meuprojeto.meuapp.dtos.RegisterRequestDTO;
import com.meuprojeto.meuapp.dtos.ResponseDTO;
import com.meuprojeto.meuapp.model.Usuario;
import com.meuprojeto.meuapp.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

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

}
