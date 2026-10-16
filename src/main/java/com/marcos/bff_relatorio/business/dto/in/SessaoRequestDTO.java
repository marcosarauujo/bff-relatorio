package com.marcos.bff_relatorio.business.dto.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SessaoRequestDTO {

    @NotNull(message = "O ID da criança é obrigatório")
    private Long criancaId;

    @NotNull(message = "A data da sessão é obrigatória")
    private LocalDate dataSessao;

    @NotBlank(message = "As anotações não podem estar vazias")
    private String anotacoes;
}
