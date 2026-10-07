package com.marcos.bff_relatorio.controller;

import com.marcos.bff_relatorio.business.dto.in.LoginRequestDTO;
import com.marcos.bff_relatorio.business.dto.out.LoginResponseDTO;
import com.marcos.bff_relatorio.infrastructure.client.Ms1Client;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bff/auth")
@RequiredArgsConstructor
@Tag(name = "1. Autenticação", description = "Login no sistema")
public class BffAuthController {
    private final Ms1Client ms1Client;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        return ResponseEntity.ok(ms1Client.fazerLogin(loginRequest));
    }
}
