package com.example.smartcollect.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PontoColetaDTO {
    @NotBlank
    public String nome;

    @NotBlank
    public String endereco;

    @NotNull
    public Double capacidadeTotal;

    public Long idPonto;
}