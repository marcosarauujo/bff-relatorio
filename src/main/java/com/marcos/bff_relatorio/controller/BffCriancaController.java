package com.marcos.bff_relatorio.controller;

import com.marcos.bff_relatorio.business.dto.in.CriancaRequestDTO;
import com.marcos.bff_relatorio.business.dto.out.CriancaResponseDTO;
import com.marcos.bff_relatorio.infrastructure.client.Ms1Client;
import io.swagger.v3.oas.annotations.Parameter;
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
@Tag(name = "3. Crianças", description = "Gestão de crianças/pacientes")
public class BffCriancaController {

    private final Ms1Client ms1Client;

    @PostMapping("/cadastrar")
    public ResponseEntity<CriancaResponseDTO> cadastrarCrianca(
            @RequestBody CriancaRequestDTO request,
            @Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ms1Client.cadastrarCrianca(request, token));
    }

    @GetMapping("/minhas-criancas")
    public ResponseEntity<List<CriancaResponseDTO>> listarMinhasCriancas(
            @Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(ms1Client.listarMinhasCriancas(token));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CriancaResponseDTO> buscarCrianca(
            @PathVariable Long id,
            @Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(ms1Client.buscarCrianca(id, token));
    }
}
