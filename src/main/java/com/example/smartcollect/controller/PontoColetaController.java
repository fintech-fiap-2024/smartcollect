package com.example.smartcollect.controller;

import com.example.smartcollect.dto.DescarteDTO;
import com.example.smartcollect.dto.PontoColetaDTO;
import com.example.smartcollect.dto.QuantidadeTotalDTO;
import com.example.smartcollect.model.PontoColeta;
import com.example.smartcollect.repository.PontoColetaRepository;
import com.example.smartcollect.service.DescarteService;
import com.example.smartcollect.service.PontoColetaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pontos-coleta")
public class PontoColetaController {
    @Autowired
    private PontoColetaService service;

    @Autowired
    private DescarteService descarteService;

    @GetMapping
    public List<PontoColetaDTO> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public PontoColetaDTO salvar(@RequestBody @Valid PontoColetaDTO dto) {
        return service.salvar(dto);
    }

    // Endpoint: obter todos os descartes de um ponto
    @GetMapping("/{id}/descartes")
    public List<DescarteDTO> listarDescartesPorPonto(@PathVariable Long id) {
        return descarteService.listarPorPonto(id);
    }

    // Endpoint: soma total de descarte por ponto
    @GetMapping("/{id}/descartes/quantidade-total")
    public QuantidadeTotalDTO totalDescartado(@PathVariable Long id) {
        return descarteService.getQuantidadeTotalPorPonto(id);
    }

    // Endpoint: listar pontos com descarte acima de X
    @GetMapping("/descartes/acima-de/{quantidade}")
    public List<QuantidadeTotalDTO> acimaDe(@PathVariable Double quantidade) {
        return descarteService.listarPontosComDescarteAcimaDe(quantidade);
    }

    // Endpoint: criar descarte para um ponto
    @PostMapping("/{id}/descartes")
    public DescarteDTO criarDescarteNoPonto(@PathVariable Long id, @RequestBody @Valid DescarteDTO dto) {
        return descarteService.salvarViaPonto(id, dto);
    }
}