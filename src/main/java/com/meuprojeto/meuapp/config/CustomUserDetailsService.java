package com.meuprojeto.meuapp.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.meuprojeto.meuapp.model.Usuario;
import com.meuprojeto.meuapp.repository.UsuarioRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {
        @Autowired
        private UsuarioRepository usuarioRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Usuario user = this.usuarioRepository.findByEmail(username)
                                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
                return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getSenha(),
                                new ArrayList<>());
        }
}