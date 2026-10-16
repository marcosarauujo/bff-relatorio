package com.marcos.bff_relatorio.controller;

import com.marcos.bff_relatorio.business.dto.in.TerapeutaRequestDTO;
import com.marcos.bff_relatorio.business.dto.out.TerapeutaResponseDTO;
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

@RestController
@RequestMapping("/bff/terapeuta")
@RequiredArgsConstructor
@Tag(name = "2. Terapeuta", description = "Cadastro e perfil da terapeuta")
public class BffTerapeutaController {

    private final Ms1Client ms1Client;

    @PostMapping("/criar")
    @Operation(
            summary = "Cadastrar terapeuta",
            description = "Cria uma nova conta de terapeuta no sistema. Nao requer autenticacao."
    )
    @ApiResponse(responseCode = "201", description = "Terapeuta cadastrada com sucesso.")
    @ApiResponse(responseCode = "409", description = "Email ja cadastrado no sistema.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")

    public ResponseEntity<TerapeutaResponseDTO> cadastrarTerapeuta(@RequestBody TerapeutaRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ms1Client.cadastrarTerapeuta(request));
    }

    @GetMapping("/perfil")
    @SecurityRequirement(name = "BearerAuth")
    @Operation(
            summary = "Buscar perfil da terapeuta logada",
            description = "Retorna os dados da terapeuta autenticada com base no token JWT."
    )
    @ApiResponse(responseCode = "200", description = "Perfil retornado com sucesso.")
    @ApiResponse(responseCode = "401", description = "Token invalido ou ausente.")
    @ApiResponse(responseCode = "404", description = "Terapeuta nao encontrada.")
    public ResponseEntity<TerapeutaResponseDTO> buscarPerfil(
            @Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(ms1Client.buscarPerfilTerapeuta(token));
    }
}
