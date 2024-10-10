package com.meuprojeto.meuapp.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.meuprojeto.meuapp.model.Usuario;
import com.meuprojeto.meuapp.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService {

    private UsuarioRepository usuarioRepo;

    public List<Usuario> todosUsuarios() {

        List<Usuario> usuarios = usuarioRepo.findAll();
        if (usuarios.isEmpty()) {
            throw new NoSuchElementException("Não encontrado");
        }
        return usuarios;
    }

    public Usuario criarUsuario(Usuario usuario) {
        return usuarioRepo.save(usuario);
    }

    public Usuario update(UUID uuid, Usuario usuarioNovo) {
        Usuario usuario = usuarioRepo.findByUuid(uuid);
        if (usuario != null) {
            usuario.setNome(usuarioNovo.getNome());
            usuario.setDataNascimento(usuarioNovo.getDataNascimento());
            return usuarioRepo.save(usuario);

        } else {
            return null;
        }

    }

    public void delete(UUID uuid) {
        if (!usuarioRepo.existsById(uuid)) {
            throw new RuntimeException("Usuário não encontrado: " + uuid);
        }
        usuarioRepo.deleteById(uuid);

    }

}
