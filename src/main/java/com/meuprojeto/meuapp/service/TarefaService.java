package com.meuprojeto.meuapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.meuprojeto.meuapp.model.Tarefa;
import com.meuprojeto.meuapp.repository.TarefaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TarefaService {

    private final TarefaRepository tarefaRepo;

    public Tarefa save(Tarefa model) {
        return tarefaRepo.save(model);
    }

    public Tarefa getTarefaId(Long id) {
        return tarefaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada: " + id));
    }

    public Tarefa update(Long id, Tarefa model) {
        Tarefa tarefa = getTarefaId(id);
        tarefa.setDataVencimento(model.getDataVencimento());
        tarefa.setDescricao(model.getDescricao());
        tarefa.setStatus(model.getStatus());
        tarefa.setTitulo(model.getTitulo());
        return tarefaRepo.save(tarefa);
    }

    public void delete(Long id) {
        if (!tarefaRepo.existsById(id)) {
            throw new RuntimeException("Tarefa não encontrada: " + id);
        }
        tarefaRepo.deleteById(id);
    }

    public List<Tarefa> getAll() {
        return tarefaRepo.findAll();
    }

    public void finalizarTarefa() {
        List<Tarefa> tarefa = tarefaRepo.findAll();
        LocalDate dataAtual = LocalDate.now();

        for (Tarefa t : tarefa) {
            if (t.getDataVencimento().isBefore(dataAtual) && t.getStatus() != Tarefa.StatusTarefa.CONCLUIDA) {
                t.setStatus(Tarefa.StatusTarefa.CONCLUIDA);
            }
            tarefaRepo.save(t);
        }
    }

    public double calcularPercentualConcluido() {
        List<Tarefa> tarefa = tarefaRepo.findAll();
        long total = tarefa.size();
        long concluidas = tarefa.stream().filter(t -> t.getStatus().equals(Tarefa.StatusTarefa.CONCLUIDA)).count();
        Double resultado = total > 0 ? (double) concluidas / total * 100 : 0;
        System.out.println(resultado);
        return resultado;

    }

    public List<Tarefa> buscarTarefasPorUsuario(UUID uuid) {
        return tarefaRepo.tarefasPorUsuario(uuid);
    }

    public List<Tarefa> buscarTarefasConcluidasPorUsuario(UUID uuid) {
        return tarefaRepo.tarefasPorUsuarioConcluidas(uuid, Tarefa.StatusTarefa.CONCLUIDA);
    }

    public List<Tarefa> buscarTarefasConcluidas() {
        return tarefaRepo.tarefasConcluidas(Tarefa.StatusTarefa.CONCLUIDA);
    }
}
