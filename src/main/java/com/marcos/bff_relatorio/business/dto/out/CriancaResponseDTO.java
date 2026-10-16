package com.marcos.bff_relatorio.business.dto.out;

import lombok.Data;

import java.time.LocalDate;

@Data

public class CriancaResponseDTO {

    private Long id;
    private String nomeCrianca;
    private LocalDate dataNascimentoCrianca;
    private String diagnostico;

}
