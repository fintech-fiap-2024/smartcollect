package com.example.smartcollect.controller;


import com.example.smartcollect.dto.ResiduoDTO;
import com.example.smartcollect.service.ResiduoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/residuos")
public class ResiduoController {

    @Autowired
    private ResiduoService service;

    @PostMapping
    public ResiduoDTO criar(@RequestBody @Valid ResiduoDTO dto) {
        return service.salvar(dto);
    }

    @GetMapping
    public List<ResiduoDTO> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResiduoDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }
}
