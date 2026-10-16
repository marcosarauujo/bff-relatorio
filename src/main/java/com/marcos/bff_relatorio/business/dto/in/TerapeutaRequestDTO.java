package com.marcos.bff_relatorio.business.dto.in;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TerapeutaRequestDTO {
    @NotBlank(message = "Nome é obrigatório")
    private String nomeTerapeuta;

    @Email(message = "E-mail inválido")
    @NotBlank(message = "E-mail é obrigatório")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    private String senha;

}
