package com.marcos.bff_relatorio.controller;

import com.marcos.bff_relatorio.business.dto.in.SessaoRequestDTO;
import com.marcos.bff_relatorio.business.dto.out.SessaoResponseDTO;
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
@RequestMapping("/bff/sessao")
@RequiredArgsConstructor
@SecurityRequirement(name = "BearerAuth")
@Tag(name = "4. Sessoes", description = "Gestao de sessoes terapeuticas")
public class BffSessaoController {

    private final Ms1Client ms1Client;

    @PostMapping("/registrar")
    public ResponseEntity<SessaoResponseDTO> registrarSessao(
            @RequestBody SessaoRequestDTO request,
            @Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ms1Client.iniciarSessao(request, token));
    }

    @GetMapping("/listar-mes")
    public ResponseEntity<List<SessaoResponseDTO>> listarSessoesMes(
            @RequestParam("criancaId") Long criancaId,
            @RequestParam("mes") Integer mes,
            @RequestParam("ano") Integer ano,
            @Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(ms1Client.listarSessoesMes(criancaId, mes, ano, token));
    }
}
