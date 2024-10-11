package com.meuprojeto.meuapp.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ObjectInputFilter.Status;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.meuprojeto.meuapp.model.Tarefa;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
public class TarefaRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    private TarefaRepository tarefaRepository;

    @Test
    public void findByTarefa() {
        Tarefa novaTarefa = new Tarefa();
        novaTarefa.setTitulo("Testar tarefa");
        novaTarefa.setStatus(Tarefa.StatusTarefa.EM_ANDAMENTO);
        tarefaRepository.save(novaTarefa);

        Optional<Tarefa> tarefaEncontrada = tarefaRepository.findByTarefa("Testar tarefa");

        assertTrue(tarefaEncontrada.isPresent(), "A tarefa deveria ter sido encontrada");
        assertEquals("Testar tarefa", tarefaEncontrada.get().getTitulo(), "O nome da tarefa não corresponde");
    }

}
