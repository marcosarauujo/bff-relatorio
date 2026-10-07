package com.marcos.bff_relatorio.business.dto.out;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SessaoResponseDTO {
    private Long id;
    private LocalDate dataSessao;
    private String anotacoes;
    private String nomeCrianca;
}
