// package com.meuprojeto.meuapp.job;

// import com.meuprojeto.meuapp.model.Tarefa;
// import com.meuprojeto.meuapp.repository.TarefaRepository;
// import com.meuprojeto.meuapp.service.EmailService;

// import lombok.AllArgsConstructor;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.scheduling.annotation.Async;
// import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.stereotype.Component;

// import java.util.List;

// @Component
// @AllArgsConstructor
// public class EnviarEmail {

// @Autowired
// private TarefaRepository tarefaRepository;

// @Autowired
// private EmailService emailService;

// @Scheduled(cron = "0 0/3 * * * *")
// @Async
// public void enviarEmailComTarefas() {
// try {
// List<Tarefa> tarefas = tarefaRepository.findAll();
// StringBuilder emailBody = new StringBuilder("Lista de Tarefas:\n");

// if (tarefas.isEmpty()) {
// emailService.enviarEmail("artpx33@gmail.com", "Lista de Tarefas", "Não há
// tarefas cadastradas.");
// return;
// }

// for (Tarefa tarefa : tarefas) {
// emailBody.append("Título: ").append(tarefa.getTitulo())
// .append(", Descrição: ").append(tarefa.getDescricao())
// .append(", Status: ").append(tarefa.getStatus())
// .append(", Data de Vencimento: ").append(tarefa.getDataVencimento())
// .append("\n");
// }

// emailService.enviarEmail("artpx33@gmail.com", "Lista de Tarefas",
// emailBody.toString());
// } catch (Exception e) {

// e.printStackTrace();
// }
// }

// }
