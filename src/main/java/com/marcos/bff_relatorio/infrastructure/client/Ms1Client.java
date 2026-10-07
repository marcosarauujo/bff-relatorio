package com.marcos.bff_relatorio.infrastructure.client;

import com.marcos.bff_relatorio.business.dto.in.*;
import com.marcos.bff_relatorio.business.dto.out.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ms1-client", url = "${ms1.url}")
public interface Ms1Client {

    // Auth & Terapeuta
    @PostMapping("/auth/login")
    LoginResponseDTO fazerLogin(@RequestBody LoginRequestDTO loginRequest);

    @PostMapping("/terapeuta/criar")
    TerapeutaResponseDTO cadastrarTerapeuta(@RequestBody TerapeutaRequestDTO request);

    @GetMapping("/terapeuta/perfil")
    TerapeutaResponseDTO buscarPerfilTerapeuta(@RequestHeader("Authorization") String token);

    // Crianca
    @PostMapping("/crianca/cadastrar")
    CriancaResponseDTO cadastrarCrianca(@RequestBody CriancaRequestDTO request,
                                        @RequestHeader("Authorization") String token);

    @GetMapping("/crianca/listar")
    List<CriancaResponseDTO> listarMinhasCriancas(@RequestHeader("Authorization") String token);

    @GetMapping("/crianca/{id}")
    CriancaResponseDTO buscarCrianca(@PathVariable("id") Long id,
                                     @RequestHeader("Authorization") String token);

    // Sessao
    @PostMapping("/sessao/registrar")
    SessaoResponseDTO iniciarSessao(@RequestBody SessaoRequestDTO request,
                                    @RequestHeader("Authorization") String token);

    @GetMapping("/sessao/listar-mes")
    List<SessaoResponseDTO> listarSessoesMes(@RequestParam("criancaId") Long criancaId,
                                             @RequestParam("mes") Integer mes,
                                             @RequestParam("ano") Integer ano,
                                             @RequestHeader("Authorization") String token);
}
