package com.example.smartcollect.controller;

import com.example.smartcollect.dto.DescarteDTO;
import com.example.smartcollect.service.DescarteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/descartes")
public class DescarteController {
    @Autowired
    private DescarteService service;

    @GetMapping
    public List<DescarteDTO> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public DescarteDTO salvar(@RequestBody @Valid DescarteDTO dto) {
        return service.salvar(dto);
    }
}
