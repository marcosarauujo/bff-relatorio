package com.marcos.bff_relatorio.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ms3-client", url = "${ms3.url}")
public interface Ms3Client {

    @PostMapping("/ia/queixa-principal")
    String gerarQueixaPrincipal(@RequestParam("criancaId") Long criancaId,
                                @RequestHeader("Authorization") String token);

    @PostMapping("/ia/anamnese")
    String gerarAnamnese(@RequestParam("criancaId") Long criancaId,
                         @RequestHeader("Authorization") String token);

    @PostMapping("/ia/desafios")
    String gerarDesafios(@RequestParam("criancaId") Long criancaId,
                         @RequestHeader("Authorization") String token);

    @PostMapping("/ia/conclusao")
    String gerarConclusao(@RequestParam("criancaId") Long criancaId,
                          @RequestHeader("Authorization") String token);

    @GetMapping("/ia/relatorios/crianca/{criancaId}")
    List<Object> listarRelatoriosPorCrianca(@PathVariable("criancaId") Long criancaId,
                                            @RequestHeader("Authorization") String token);

    @GetMapping("/ia/relatorio/{id}")
    Object buscarRelatorioPorId(@PathVariable("id") String id,
                                @RequestHeader("Authorization") String token);
}
