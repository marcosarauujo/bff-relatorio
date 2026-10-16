package com.marcos.bff_relatorio.business.dto.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CriancaRequestDTO {
    @NotBlank(message = "Nome é obrigatório")
    private String nomeCrianca;


    @NotNull(message = "Data de nascimento é obrigatória")
    private LocalDate dataNascimentoCrianca;

    @NotBlank(message = "Diagnóstico é obrigatório")
    private String diagnostico;

}
