package com.marcos.bff_relatorio.controller;

import com.marcos.bff_relatorio.infrastructure.client.Ms1Client;
import com.marcos.bff_relatorio.infrastructure.client.Ms3Client;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/bff/paciente")
@RequiredArgsConstructor

@Tag(name = "Dashboard do Paciente")
@SecurityRequirement(name = "BearerAuth")

public class BffPacienteController {
    private final Ms1Client ms1Client;
    private final Ms3Client ms3Client;

    @GetMapping("/{id}/dashboard")

    @Operation(summary = "Montar Dashboard Completo",
            description = "Traz o perfil do paciente e todos os seus relatórios gerados")

    public ResponseEntity<Map<String, Object>> buscarDashboardPaciente(
            @PathVariable("id") Long id,
            @Parameter(hidden = true) @RequestHeader(name = "Authorization", required = false) String token) {

        Object dadosCrianca = ms1Client.buscarCrianca(id, token);
        Object relatorios = ms3Client.listarRelatoriosPorCrianca(id, token);

        Map<String, Object> dashboardCompleto = new HashMap<>();
        dashboardCompleto.put("perfil", dadosCrianca);
        dashboardCompleto.put("historicoRelatorios", relatorios);

        return ResponseEntity.ok(dashboardCompleto);
    }
}
