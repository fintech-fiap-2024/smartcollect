package com.example.smartcollect.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class DescarteDTO {
    public Long idDescarte;

    @NotNull
    public Double quantidadeKg;

    @NotNull
    public LocalDate dataDescarte;

    @NotNull
    public Long pontoColetaId;

    @NotNull
    public Long residuoId;
}
