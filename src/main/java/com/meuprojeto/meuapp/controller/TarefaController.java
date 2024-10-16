package com.meuprojeto.meuapp.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meuprojeto.meuapp.dtos.TarefaDTO;
import com.meuprojeto.meuapp.model.Tarefa;
import com.meuprojeto.meuapp.service.TarefaService;

@RestController
@RequestMapping("/api")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping("/tarefa")
    public ResponseEntity<List<TarefaDTO>> todasTarefas() {
        List<Tarefa> tarefas = tarefaService.getAll();

        List<TarefaDTO> tarefasDTO = tarefas.stream()
                .map(TarefaDTO::tarefa)
                .toList();

        return ResponseEntity.ok(tarefasDTO);

    }

    @GetMapping("/tarefa/{id}")
    public ResponseEntity<?> getTarefaId(@PathVariable Long id) {

        try {

            Tarefa tarefa = tarefaService.getTarefaId(id);

            return ResponseEntity.ok(tarefa);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Tarefa não encontrada para Id : " + id);
        }
    }

    @GetMapping("/tarefa/usuario/{uuid}")
    public ResponseEntity<?> getTarefaId(@PathVariable UUID uuid) {

        try {

            List<Tarefa> tarefa = tarefaService.buscarTarefasPorUsuario(uuid);

            return ResponseEntity.ok(tarefa);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Sem tarefas para usuário : " + uuid);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/tarefa")
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa model) {
        try {
            tarefaService.save(model);
            return ResponseEntity.status(HttpStatus.CREATED).body(model);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @PutMapping("/tarefa/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Tarefa tarefaAtualizada) {

        try {
            Tarefa tarefa = tarefaService.update(id, tarefaAtualizada);
            return ResponseEntity.ok(tarefa);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada para Id: " + id);
        }
    }

    @DeleteMapping("/tarefa/{id}")
    public ResponseEntity<?> criarTarefa(@PathVariable Long id) {

        try {
            tarefaService.delete(id);
            return ResponseEntity.ok("Tarefa excluída com id : " + id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada para Id: " + id);
        }
    }
}
