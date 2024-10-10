package com.meuprojeto.meuapp.repository;

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

    @Test
    public void findByTarefa() {

    }

    // private Tarefa criarTarefa(Tarefa tarefa) {
    // Tarefa newTarefa = new Tarefa(tarefa);
    // }

}
