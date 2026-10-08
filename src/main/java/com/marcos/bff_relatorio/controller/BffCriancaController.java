package com.marcos.bff_relatorio.controller;

import com.marcos.bff_relatorio.business.dto.in.CriancaRequestDTO;
import com.marcos.bff_relatorio.business.dto.out.CriancaResponseDTO;
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
@RequestMapping("/bff/crianca")
@RequiredArgsConstructor
@SecurityRequirement(name = "BearerAuth")
@Tag(name = "3. Criancas", description = "Gestao de criancas/pacientes")
public class BffCriancaController {

    private final Ms1Client ms1Client;

    @PostMapping("/cadastrar")
    @Operation(
            summary = "Cadastrar crianca",
            description = "Cadastra um novo paciente vinculado a terapeuta autenticada."
    )
    @ApiResponse(responseCode = "201", description = "Crianca cadastrada com sucesso.")
    @ApiResponse(responseCode = "401", description = "Token invalido ou ausente.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")

    public ResponseEntity<CriancaResponseDTO> cadastrarCrianca(
            @RequestBody CriancaRequestDTO request,
            @Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ms1Client.cadastrarCrianca(request, token));
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Listar criancas da terapeuta",
            description = "Retorna todos os pacientes cadastrados pela terapeuta autenticada."
    )
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso.")
    @ApiResponse(responseCode = "401", description = "Token invalido ou ausente.")
    public ResponseEntity<List<CriancaResponseDTO>> listarMinhasCriancas(
            @Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(ms1Client.listarMinhasCriancas(token));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar crianca por ID",
            description = "Retorna os dados de uma crianca especifica pelo seu ID."
    )
    @ApiResponse(responseCode = "200", description = "Crianca encontrada com sucesso.")
    @ApiResponse(responseCode = "401", description = "Token invalido ou ausente.")
    @ApiResponse(responseCode = "404", description = "Crianca nao encontrada.")
    public ResponseEntity<CriancaResponseDTO> buscarCrianca(
            @PathVariable Long id,
            @Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(ms1Client.buscarCrianca(id, token));
    }
}
