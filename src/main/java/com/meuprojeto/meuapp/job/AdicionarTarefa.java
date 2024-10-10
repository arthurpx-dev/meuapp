package com.meuprojeto.meuapp.job;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.meuprojeto.meuapp.model.Tarefa;
import com.meuprojeto.meuapp.repository.TarefaRepository;
import com.meuprojeto.meuapp.service.TarefaService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@Log
@Component
@AllArgsConstructor

public class AdicionarTarefa {

    @Autowired
    private TarefaService tarefaService;

    @Autowired
    private TarefaRepository tarefaRepo;

    @Scheduled(cron = "0 * 6 * * *")
    @Async
    public void enviarTarefa() {
        log.info("Incializando JOB");
        Tarefa tarefa = new Tarefa();

        tarefa.setId(2L);
        tarefa.setDataAtualizacao(LocalDateTime.now());
        tarefa.setDataCriacao(LocalDateTime.now());
        tarefa.setDataVencimento(LocalDate.now());
        tarefa.setStatus(Tarefa.StatusTarefa.CONCLUIDA);
        tarefa.setTitulo("JOB");
        tarefa.setDescricao("Testando JOB");

        tarefaService.save(tarefa);
        log.info("Finalizando JOB");
    }

    @Scheduled(cron = "0 * 6 * * *")
    @Async
    public void finalizarTarefa() {
        log.info("Incializando finalização de tarefas JOB");
        tarefaService.finalizarTarefa();
        log.info("Finalizando JOB de finalização de tarefas");
    }

    @Scheduled(cron = "0 * 6 * * *")
    @Async
    public void calcularPercentualConcluido() {
        log.info("Inicializando calculo de porcentagem de tarefas concluidas");
        tarefaService.calcularPercentualConcluido();
        log.info("Finalizando calculo de porcentagem de tarefas concluidas");

    }

}
