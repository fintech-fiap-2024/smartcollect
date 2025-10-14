package com.example.smartcollect.dto;

public class QuantidadeTotalDTO {
    public Long pontoColetaId;
    public Double totalKg;

    public QuantidadeTotalDTO(Long pontoColetaId, Double totalKg) {
        this.pontoColetaId = pontoColetaId;
        this.totalKg = totalKg;
    }
}
