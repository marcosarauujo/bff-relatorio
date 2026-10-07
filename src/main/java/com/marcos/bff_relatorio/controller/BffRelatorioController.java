package com.marcos.bff_relatorio.controller;

import com.marcos.bff_relatorio.infrastructure.client.Ms3Client;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bff/ia")
@RequiredArgsConstructor
@SecurityRequirement(name = "BearerAuth")
@Tag(name = "6. Relatórios de IA", description = "Geração e consulta dos tópicos do relatório clínico")
public class BffRelatorioController {

    private final Ms3Client ms3Client;

    @PostMapping("/queixa-principal")
    public ResponseEntity<String> gerarQueixaPrincipal(
            @RequestParam("criancaId") Long criancaId,
            @Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ms3Client.gerarQueixaPrincipal(criancaId, token));
    }

    @PostMapping("/anamnese")
    public ResponseEntity<String> gerarAnamnese(
            @RequestParam("criancaId") Long criancaId,
            @Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ms3Client.gerarAnamnese(criancaId, token));
    }

    @PostMapping("/desafios")
    public ResponseEntity<String> gerarDesafios(
            @RequestParam("criancaId") Long criancaId,
            @Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ms3Client.gerarDesafios(criancaId, token));
    }

    @PostMapping("/conclusao")
    public ResponseEntity<String> gerarConclusao(
            @RequestParam("criancaId") Long criancaId,
            @Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ms3Client.gerarConclusao(criancaId, token));
    }

    @GetMapping("/relatorios/crianca/{criancaId}")
    public ResponseEntity<List<Object>> listarRelatoriosPorCrianca(
            @PathVariable Long criancaId,
            @Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(ms3Client.listarRelatoriosPorCrianca(criancaId, token));
    }

    @GetMapping("/relatorio/{id}")
    public ResponseEntity<Object> buscarRelatorioPorId(
            @PathVariable String id,
            @Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(ms3Client.buscarRelatorioPorId(id, token));
    }
}
