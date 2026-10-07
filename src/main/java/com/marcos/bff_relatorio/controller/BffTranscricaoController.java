package com.marcos.bff_relatorio.controller;

import com.marcos.bff_relatorio.business.dto.out.TranscricaoResponseDTO;
import com.marcos.bff_relatorio.infrastructure.client.Ms2Client;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/bff/transcricao")
@RequiredArgsConstructor
@SecurityRequirement(name = "BearerAuth")
@Tag(name = "5. Transcrições", description = "Upload de áudio e consulta de transcrições")
public class BffTranscricaoController {

    private final Ms2Client ms2Client;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAudio(
            @RequestPart("audio") MultipartFile audio,
            @RequestParam("criancaId") Long criancaId,
            @RequestParam("terapeutaId") Long terapeutaId,
            @Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ms2Client.uploadAudio(audio, criancaId, terapeutaId, token));
    }

    @GetMapping("/crianca/{criancaId}")
    public ResponseEntity<List<TranscricaoResponseDTO>> listarTranscricoesPorCrianca(
            @PathVariable Long criancaId,
            @Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(ms2Client.listarTranscricoesPorCrianca(criancaId, token));
    }
}
