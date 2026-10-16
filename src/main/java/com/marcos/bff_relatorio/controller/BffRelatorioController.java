package com.marcos.bff_relatorio.controller;

import com.marcos.bff_relatorio.infrastructure.client.Ms3Client;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@Tag(name = "6. Relatorios de IA", description = "Geracao e consulta dos topicos do relatorio clinico")
public class BffRelatorioController {

    private final Ms3Client ms3Client;

    @PostMapping("/queixa-principal")
    @Operation(
            summary = "Gerar topico: Queixa Principal",
            description = "Usa a IA para analisar as transcricoes do paciente e gerar o topico de Queixa Principal do relatorio clinico."
    )
    @ApiResponse(responseCode = "201", description = "Topico gerado e salvo com sucesso.")
    @ApiResponse(responseCode = "401", description = "Token invalido ou ausente.")
    @ApiResponse(responseCode = "404", description = "Nenhuma transcricao encontrada para esta crianca.")

    public ResponseEntity<String> gerarQueixaPrincipal(
            @RequestParam("criancaId") Long criancaId,
            @Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ms3Client.gerarQueixaPrincipal(criancaId, token));
    }

    @PostMapping("/anamnese")
    @Operation(
            summary = "Gerar topico: Anamnese",
            description = "Usa a IA para analisar as transcricoes do paciente e gerar o topico de Anamnese do relatorio clinico."
    )
    @ApiResponse(responseCode = "201", description = "Topico gerado e salvo com sucesso.")
    @ApiResponse(responseCode = "401", description = "Token invalido ou ausente.")
    @ApiResponse(responseCode = "404", description = "Nenhuma transcricao encontrada para esta crianca.")

    public ResponseEntity<String> gerarAnamnese(
            @RequestParam("criancaId") Long criancaId,
            @Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ms3Client.gerarAnamnese(criancaId, token));
    }

    @PostMapping("/desafios")
    @Operation(
            summary = "Gerar topico: Desafios",
            description = "Usa a IA para analisar as transcricoes do paciente e gerar o topico de Desafios do relatorio clinico."
    )
    @ApiResponse(responseCode = "201", description = "Topico gerado e salvo com sucesso.")
    @ApiResponse(responseCode = "401", description = "Token invalido ou ausente.")
    @ApiResponse(responseCode = "404", description = "Nenhuma transcricao encontrada para esta crianca.")

    public ResponseEntity<String> gerarDesafios(
            @RequestParam("criancaId") Long criancaId,
            @Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ms3Client.gerarDesafios(criancaId, token));
    }

    @PostMapping("/conclusao")
    @Operation(
            summary = "Gerar topico: Conclusao",
            description = "Usa a IA para analisar as transcricoes do paciente e gerar o topico de Conclusao do relatorio clinico."
    )
    @ApiResponse(responseCode = "201", description = "Topico gerado e salvo com sucesso.")
    @ApiResponse(responseCode = "401", description = "Token invalido ou ausente.")
    @ApiResponse(responseCode = "404", description = "Nenhuma transcricao encontrada para esta crianca.")

    public ResponseEntity<String> gerarConclusao(
            @RequestParam("criancaId") Long criancaId,
            @Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ms3Client.gerarConclusao(criancaId, token));
    }

    @GetMapping("/relatorios/crianca/{criancaId}")
    @Operation(
            summary = "Listar relatorios de uma crianca",
            description = "Retorna todos os topicos de relatorio ja gerados para um determinado paciente."
    )
    @ApiResponse(responseCode = "200", description = "Lista de relatorios retornada com sucesso.")
    @ApiResponse(responseCode = "401", description = "Token invalido ou ausente.")

    public ResponseEntity<List<Object>> listarRelatoriosPorCrianca(
            @PathVariable Long criancaId,
            @Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(ms3Client.listarRelatoriosPorCrianca(criancaId, token));
    }

    @GetMapping("/relatorio/{id}")
    @Operation(
            summary = "Buscar relatorio por ID",
            description = "Retorna um topico de relatorio especifico pelo seu ID do MongoDB."
    )
    @ApiResponse(responseCode = "200", description = "Relatorio encontrado com sucesso.")
    @ApiResponse(responseCode = "401", description = "Token invalido ou ausente.")
    @ApiResponse(responseCode = "404", description = "Relatorio nao encontrado.")

    public ResponseEntity<Object> buscarRelatorioPorId(
            @PathVariable String id,
            @Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(ms3Client.buscarRelatorioPorId(id, token));
    }
}
