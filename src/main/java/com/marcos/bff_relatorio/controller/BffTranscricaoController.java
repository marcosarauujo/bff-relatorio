package com.marcos.bff_relatorio.controller;

import com.marcos.bff_relatorio.business.dto.out.TranscricaoResponseDTO;
import com.marcos.bff_relatorio.infrastructure.client.Ms2Client;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@Tag(name = "5. Transcricoes", description = "Upload de audio e consulta de transcricoes")
public class BffTranscricaoController {

    private final Ms2Client ms2Client;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Fazer upload de audio",
            description = "Envia um arquivo de audio (MP3) para ser transcrito pela IA (Whisper). " +
                    "O texto gerado ficara salvo e sera usado na geracao dos relatorios clinicos."
    )
    @ApiResponse(responseCode = "201", description = "Audio transcrito e salvo com sucesso.")
    @ApiResponse(responseCode = "401", description = "Token invalido ou ausente.")
    @ApiResponse(responseCode = "500", description = "Erro ao processar o audio. Verifique se o arquivo e valido.")
    public ResponseEntity<String> uploadAudio(
            @RequestPart("audio") MultipartFile audio,
            @RequestParam("criancaId") Long criancaId,
            @RequestParam("terapeutaId") Long terapeutaId,
            @Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ms2Client.uploadAudio(audio, criancaId, terapeutaId, token));
    }

    @GetMapping("/crianca/{criancaId}")
    @Operation(
            summary = "Listar transcricoes de uma crianca",
            description = "Retorna todas as transcricoes de audio ja realizadas para um determinado paciente."
    )
    @ApiResponse(responseCode = "200", description = "Lista de transcricoes retornada com sucesso.")
    @ApiResponse(responseCode = "401", description = "Token invalido ou ausente.")
    @ApiResponse(responseCode = "404", description = "Nenhuma transcricao encontrada para esta crianca.")
    public ResponseEntity<List<TranscricaoResponseDTO>> listarTranscricoesPorCrianca(
            @PathVariable Long criancaId,
            @Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(ms2Client.listarTranscricoesPorCrianca(criancaId, token));
    }
}
