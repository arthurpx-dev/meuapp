package com.meuprojeto.meuapp.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meuprojeto.meuapp.model.Tarefa;
import com.meuprojeto.meuapp.model.Tarefa.StatusTarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    @Query("SELECT t FROM Tarefa t WHERE t.usuario.uuid = :usuarioUuid")
    List<Tarefa> tarefasPorUsuario(@Param("usuarioUuid") UUID usuarioUuid);

    @Query("SELECT t FROM Tarefa t WHERE t.usuario.uuid = :usuarioUuid AND t.status = :status")
    List<Tarefa> tarefasPorUsuarioConcluidas(@Param("usuarioUuid") UUID usuarioUuid,
            @Param("status") StatusTarefa status);

    @Query("SELECT t FROM Tarefa t WHERE t.status = :status")
    List<Tarefa> tarefasConcluidas(@Param("status") StatusTarefa status);

    @Query("SELECT t FROM Tarefa t WHERE t.titulo = :titulo")
    Optional<Tarefa> findByTarefa(@Param("titulo") String titulo);

}
