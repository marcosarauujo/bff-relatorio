package com.marcos.bff_relatorio.controller;

import com.marcos.bff_relatorio.infrastructure.client.Ms1Client;
import com.marcos.bff_relatorio.infrastructure.client.Ms3Client;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@SecurityRequirement(name = "BearerAuth")
@Tag(name = "7. Dashboard do Paciente",
        description = "Visao consolidada do paciente com perfil e historico de relatorios")
public class BffPacienteController {

    private final Ms1Client ms1Client;
    private final Ms3Client ms3Client;

    @GetMapping("/{id}/dashboard")
    @Operation(
            summary = "Buscar dashboard completo do paciente",
            description = "Retorna em uma unica chamada os dados cadastrais da crianca " +
                    "e todo o historico de relatorios clinicos gerados. "

    )
    @ApiResponse(responseCode = "200", description = "Dashboard montado com sucesso.")
    @ApiResponse(responseCode = "401", description = "Token invalido ou ausente.")
    @ApiResponse(responseCode = "404", description = "Paciente nao encontrado.")

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
