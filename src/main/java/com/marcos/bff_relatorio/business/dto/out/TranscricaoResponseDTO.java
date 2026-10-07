package com.marcos.bff_relatorio.business.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// DTO espelhado do MS2 (anamnese) para o BFF
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TranscricaoResponseDTO {
    private String id;
    private Long criancaId;
    private String textoBruto;
    private LocalDateTime dataCriacao;
}
