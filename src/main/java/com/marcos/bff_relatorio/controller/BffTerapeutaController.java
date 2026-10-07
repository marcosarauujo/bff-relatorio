package com.marcos.bff_relatorio.controller;

import com.marcos.bff_relatorio.business.dto.in.TerapeutaRequestDTO;
import com.marcos.bff_relatorio.business.dto.out.TerapeutaResponseDTO;
import com.marcos.bff_relatorio.infrastructure.client.Ms1Client;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bff/terapeuta")
@RequiredArgsConstructor
@Tag(name = "2. Terapeutas", description = "Gestão de terapeutas")
public class BffTerapeutaController {

    private final Ms1Client ms1Client;

    @PostMapping("/criar")
    public ResponseEntity<TerapeutaResponseDTO> cadastrarTerapeuta(@RequestBody TerapeutaRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ms1Client.cadastrarTerapeuta(request));
    }

    @SecurityRequirement(name = "BearerAuth")
    @GetMapping("/perfil")
    public ResponseEntity<TerapeutaResponseDTO> buscarPerfilTerapeuta(
            @Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(ms1Client.buscarPerfilTerapeuta(token));
    }
}
