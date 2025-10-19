package com.pe.vethope.vethope_backend.controller;


import com.pe.vethope.vethope_backend.dto.VentaDTO;
import com.pe.vethope.vethope_backend.service.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ventas")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService ventaService;


    @GetMapping
    public ResponseEntity<List<VentaDTO>> listar() {
        return ResponseEntity.ok(ventaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ventaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<VentaDTO> crear(@RequestBody VentaDTO ventaDTO) {
        VentaDTO nuevaVenta = ventaService.crear(ventaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaDTO> actualizar(@PathVariable Long id, @RequestBody VentaDTO ventaDTO) {
        return ResponseEntity.ok(ventaService.actualizar(id, ventaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ventaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
