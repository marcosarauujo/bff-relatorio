package com.marcos.bff_relatorio.controller;

import com.marcos.bff_relatorio.business.dto.in.SessaoRequestDTO;
import com.marcos.bff_relatorio.business.dto.out.SessaoResponseDTO;
import com.marcos.bff_relatorio.infrastructure.client.Ms1Client;
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
@RequestMapping("/bff/sessao")
@RequiredArgsConstructor
@SecurityRequirement(name = "BearerAuth")
@Tag(name = "4. Sessoes", description = "Gestao de sessoes terapeuticas")
public class BffSessaoController {

    private final Ms1Client ms1Client;

    @PostMapping("/registrar")
    @Operation(
            summary = "Registrar sessao",
            description = "Registra uma nova sessao terapeutica para um paciente."
    )
    @ApiResponse(responseCode = "201", description = "Sessao registrada com sucesso.")
    @ApiResponse(responseCode = "401", description = "Token invalido ou ausente.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")

    public ResponseEntity<SessaoResponseDTO> registrarSessao(
            @RequestBody SessaoRequestDTO request,
            @Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ms1Client.iniciarSessao(request, token));
    }

    @GetMapping("/listar-mes")
    @Operation(
            summary = "Listar sessoes por mes",
            description = "Retorna todas as sessoes de um paciente em um determinado mes e ano."
    )
    @ApiResponse(responseCode = "200", description = "Lista de sessoes retornada com sucesso.")
    @ApiResponse(responseCode = "401", description = "Token invalido ou ausente.")
    @ApiResponse(responseCode = "404", description = "Nenhuma sessao encontrada para o periodo informado.")
    public ResponseEntity<List<SessaoResponseDTO>> listarSessoesMes(
            @RequestParam("criancaId") Long criancaId,
            @RequestParam("mes") Integer mes,
            @RequestParam("ano") Integer ano,
            @Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(ms1Client.listarSessoesMes(criancaId, mes, ano, token));
    }
}
