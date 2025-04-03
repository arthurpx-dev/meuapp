
package com.meuprojeto.meuapp.config;

import com.meuprojeto.meuapp.model.Papel;
import com.meuprojeto.meuapp.model.Usuario;
import com.meuprojeto.meuapp.repository.PapelRepository;
import com.meuprojeto.meuapp.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@AllArgsConstructor
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private PapelRepository papelRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (usuarioRepository.count() == 0) {
            Papel papelAdmin = new Papel();
            papelAdmin.setNome("ROLE_ADMIN");

            Papel papelUser = new Papel();
            papelUser.setNome("ROLE_USER");

            papelRepository.saveAll(Arrays.asList(papelAdmin, papelUser));

            Usuario usuarioAdmin = new Usuario();
            usuarioAdmin.setNome("ADMIN");
            usuarioAdmin.getPapeis().add(papelAdmin);
            usuarioAdmin.setSenha(passwordEncoder.encode("admin123"));
            usuarioAdmin.setEmail("arthur.paixao@tre-ma.jus.br");

            usuarioRepository.save(usuarioAdmin);

        }
    }
}
