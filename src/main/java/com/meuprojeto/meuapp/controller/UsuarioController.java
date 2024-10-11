package com.meuprojeto.meuapp.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meuprojeto.meuapp.dtos.TarefaDTO;
import com.meuprojeto.meuapp.dtos.UsuarioDTO;
import com.meuprojeto.meuapp.model.Usuario;
import com.meuprojeto.meuapp.service.UsuarioService;

@RequestMapping("/api")
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuario")
    public ResponseEntity<List<UsuarioDTO>> buscarTodosUsuarios() {

        try {
            List<Usuario> usuarios = usuarioService.todosUsuarios();
            List<UsuarioDTO> usuariosDTO = usuarios.stream()
                    .map(UsuarioDTO::usuario)
                    .toList();

            return ResponseEntity.ok(usuariosDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {

        try {
            usuarioService.criarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @PutMapping("/usuario/{uuid}")
    public ResponseEntity<Usuario> update(@PathVariable UUID uuid, @RequestBody Usuario usuario) {

        try {
            usuarioService.update(uuid, usuario);
            return ResponseEntity.ok(usuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @DeleteMapping("/usuario/{uuid}")
    public ResponseEntity<?> delete(@PathVariable UUID uuid) {

        try {
            usuarioService.delete(uuid);
            return ResponseEntity.ok("Usuario excluído com sucesso para o Uuid : " + uuid);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado com Uuid : " + uuid);
        }

    }

}
