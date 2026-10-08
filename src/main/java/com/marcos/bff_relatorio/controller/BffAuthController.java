package com.marcos.bff_relatorio.controller;

import com.marcos.bff_relatorio.business.dto.in.LoginRequestDTO;
import com.marcos.bff_relatorio.business.dto.out.LoginResponseDTO;
import com.marcos.bff_relatorio.infrastructure.client.Ms1Client;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bff/auth")
@RequiredArgsConstructor
@Tag(name = "1. Autenticacao", description = "Endpoints de login e autenticacao")
public class BffAuthController {

    private final Ms1Client ms1Client;

    @PostMapping("/login")
    @Operation(
            summary = "Realizar login",
            description = "Autentica a terapeuta e retorna o token JWT para uso nos demais endpoints."
    )
    @ApiResponse(responseCode = "200", description = "Login realizado com sucesso.")
    @ApiResponse(responseCode = "401", description = "Email ou senha invalidos.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(ms1Client.fazerLogin(loginRequestDTO));
    }
}
