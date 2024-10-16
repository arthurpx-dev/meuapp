package com.meuprojeto.meuapp.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.meuprojeto.meuapp.model.Papel;
import com.meuprojeto.meuapp.model.Usuario;
import com.meuprojeto.meuapp.repository.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    com.meuprojeto.meuapp.config.TokenService tokenService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var token = this.recoverToken(request);
        var login = tokenService.validateToken(token);

        if (login != null) {
            Usuario user = usuarioRepository.findByEmail(login)
                    .orElseThrow(() -> new RuntimeException("User Not Found"));
            List<SimpleGrantedAuthority> authorities = this.getAuthoritiesForUser(user);

            var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null)
            return null;
        return authHeader.replace("Bearer ", "");
    }

    private List<SimpleGrantedAuthority> getAuthoritiesForUser(Usuario usuario) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        List<Papel> usuariosPapeis = usuarioRepository.findPapeisByEmail(usuario.getEmail());

        if (usuariosPapeis.stream().anyMatch(papel -> papel.getNome().equals("ROLE_ADMIN"))) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return authorities;
    }
}