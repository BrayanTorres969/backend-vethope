package com.pe.vethope.vethope_backend.controller;

import com.pe.vethope.vethope_backend.dto.DetalleVentaDTO;
import com.pe.vethope.vethope_backend.service.DetalleVentaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detalle-ventas")
@Tag(name = "Detalle de venta", description = "Operaciones relacionadas con la gestión de detalle de venta")
@RequiredArgsConstructor
public class DetalleVentaController {

    private final DetalleVentaService detalleVentaService;


    @GetMapping
    public ResponseEntity<List<DetalleVentaDTO>> listar() {
        return ResponseEntity.ok(detalleVentaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVentaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(detalleVentaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<DetalleVentaDTO> crear(@RequestBody DetalleVentaDTO detalleVentaDTO) {
        DetalleVentaDTO nuevo = detalleVentaService.crear(detalleVentaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleVentaDTO> actualizar(@PathVariable Long id, @RequestBody DetalleVentaDTO detalleVentaDTO) {
        return ResponseEntity.ok(detalleVentaService.actualizar(id, detalleVentaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        detalleVentaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }


}
