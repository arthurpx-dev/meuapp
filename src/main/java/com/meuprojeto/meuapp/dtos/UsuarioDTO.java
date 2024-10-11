package com.meuprojeto.meuapp.dtos;

import java.time.LocalDate;
import java.util.UUID;

import com.meuprojeto.meuapp.model.Usuario;

import lombok.Data;

@Data
public class UsuarioDTO {

    private UUID uuid;
    private String nome;
    private LocalDate dataNascimento;

    public UsuarioDTO(UUID uuid, String nome, LocalDate dataNascimento) {
        this.uuid = uuid;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public static UsuarioDTO usuario(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getUuid(),
                usuario.getNome(),
                usuario.getDataNascimento());

    }
}
