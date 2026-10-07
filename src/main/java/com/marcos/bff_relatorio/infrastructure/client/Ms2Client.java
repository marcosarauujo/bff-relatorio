package com.marcos.bff_relatorio.infrastructure.client;

import com.marcos.bff_relatorio.business.dto.out.TranscricaoResponseDTO;
import com.marcos.bff_relatorio.infrastructure.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(name = "ms2-client", url = "${ms2.url}", configuration = FeignConfig.class)
public interface Ms2Client {

    @PostMapping(value = "/transcricao/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadAudio(@RequestPart("audio") MultipartFile audio,
                       @RequestParam("criancaId") Long criancaId,
                       @RequestParam("terapeutaId") Long terapeutaId,
                       @RequestHeader("Authorization") String token);

    @GetMapping("/transcricao/crianca/{criancaId}")
    List<TranscricaoResponseDTO> listarTranscricoesPorCrianca(@PathVariable("criancaId") Long criancaId,
                                                              @RequestHeader("Authorization") String token);
}
