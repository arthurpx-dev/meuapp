// package com.meuprojeto.meuapp.service;

// import static org.mockito.Mockito.*;
// import static org.junit.jupiter.api.Assertions.*;

// import java.util.Optional;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;

// import com.meuprojeto.meuapp.model.Tarefa;
// import com.meuprojeto.meuapp.repository.TarefaRepository;

// public class TarefaServiceTest {

    // @Mock
    // private TarefaRepository tarefaRepo;

    // @InjectMocks
    // private TarefaService tarefaService;

    // @BeforeEach
    // public void setUp() {
    //     MockitoAnnotations.openMocks(this);
    // }

    // @Test
    // public void testGetTarefaId_Success() {
    //     Tarefa tarefa = new Tarefa();
    //     tarefa.setId(1L);
    //     when(tarefaRepo.findById(1L)).thenReturn(Optional.of(tarefa));

    //     Tarefa resultado = tarefaService.getTarefaId(1L);
    //     assertNotNull(resultado);
    //     assertEquals(1L, resultado.getId());
    // }

    // @Test
    // public void testGetTarefaId_NotFound() {
    //     when(tarefaRepo.findById(1L)).thenReturn(Optional.empty());

    //     Exception exception = assertThrows(RuntimeException.class, () -> {
    //         tarefaService.getTarefaId(1L);
    //     });

    //     assertEquals("Tarefa nã encontrada1", exception.getMessage());
    // }

    // @Test
    // public void testUpdate_Success() {
    //     Tarefa tarefaExistente = new Tarefa();
    //     tarefaExistente.setId(1L);
    //     tarefaExistente.setTitulo("Tarefa Antiga");

    //     Tarefa tarefaAtualizada = new Tarefa();
    //     tarefaAtualizada.setTitulo("Tarefa Atualizada");

    //     when(tarefaRepo.findById(1L)).thenReturn(Optional.of(tarefaExistente));
    //     when(tarefaRepo.save(any(Tarefa.class))).thenReturn(tarefaAtualizada);

    //     Tarefa resultado = tarefaService.update(1L, tarefaAtualizada);
    //     assertEquals("Tarefa Atualizada", resultado.getTitulo());
    // }

    // @Test
    // public void testDelete_Success() {
    //     Tarefa tarefaExistente = new Tarefa();
    //     tarefaExistente.setId(1L);
    //     when(tarefaRepo.findById(1L)).thenReturn(Optional.of(tarefaExistente));

    //     tarefaService.delete(1L);
    //     verify(tarefaRepo, times(1)).deleteById(1L);
    // }

    // @Test
    // public void testDelete_NotFound() {
    //     when(tarefaRepo.findById(1L)).thenReturn(Optional.empty());

    //     Exception exception = assertThrows(RuntimeException.class, () -> {
    //         tarefaService.delete(1L);
    //     });

    //     assertEquals("Tarefa nã encontrada1", exception.getMessage());
    // }
// }
