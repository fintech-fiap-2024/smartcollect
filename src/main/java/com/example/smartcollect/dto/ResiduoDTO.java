package com.example.smartcollect.dto;

import jakarta.validation.constraints.NotBlank;

public class ResiduoDTO {
    public Long idResiduo;

    @NotBlank(message = "A descrição do resíduo é obrigatória.")
    public String descricao;

    @NotBlank(message = "O tipo do resíduo é obrigatório.")
    public String tipo;


}

