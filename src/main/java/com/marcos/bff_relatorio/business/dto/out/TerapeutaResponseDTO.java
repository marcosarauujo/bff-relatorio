package com.marcos.bff_relatorio.business.dto.out;

import lombok.Data;

@Data
public class TerapeutaResponseDTO {

    private Long id;
    private String nomeTerapeuta;
    private String email;

}
