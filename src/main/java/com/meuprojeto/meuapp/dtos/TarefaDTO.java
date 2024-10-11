package com.meuprojeto.meuapp.dtos;

import java.time.LocalDate;

import com.meuprojeto.meuapp.model.Usuario;
import com.meuprojeto.meuapp.model.Tarefa;
import com.meuprojeto.meuapp.model.Tarefa.StatusTarefa;

import lombok.Data;

@Data
public class TarefaDTO {

    private Long id;

    private String titulo;

    private String descricao;

    private StatusTarefa status;

    private LocalDate dataVencimento;

    private UsuarioDTO usuario;

    public TarefaDTO(
            Long id, String titulo, String descricao, StatusTarefa status, LocalDate dataVencimento,
            UsuarioDTO usuario) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.dataVencimento = dataVencimento;
        this.usuario = usuario;
    }

    public static TarefaDTO tarefa(Tarefa tarefa) {
        Usuario usuario = tarefa.getUsuario();
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                usuario.getUuid(),
                usuario.getNome(),
                usuario.getDataNascimento());

        return new TarefaDTO(
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getStatus(),
                tarefa.getDataVencimento(),
                usuarioDTO);
    }
}
