package com.meuprojeto.meuapp.controller;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meuprojeto.meuapp.model.Tarefa;
import com.meuprojeto.meuapp.service.TarefaService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

    private final TarefaService tarefaService;

    public RelatorioController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping("/xls")
    public ResponseEntity<ByteArrayResource> gerarRelatorio() throws IOException {
        List<Tarefa> tarefasConcluidas = tarefaService.buscarTarefasConcluidas();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Tarefas Concluídas");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Titulo tarefa");
        header.createCell(1).setCellValue("Status");
        header.createCell(2).setCellValue("Usuário");

        int rowNum = 1;
        for (Tarefa tarefa : tarefasConcluidas) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(tarefa.getTitulo());
            row.createCell(1).setCellValue(tarefa.getStatus().toString());
            row.createCell(2).setCellValue(tarefa.getUsuario().getNome());
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray()) {
            @Override
            public String getFilename() {
                return "relatorio_tarefas_concluidas.xlsx";
            }
        };

        // headers da resposta para download
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename())
                .body(resource);
    }
}
