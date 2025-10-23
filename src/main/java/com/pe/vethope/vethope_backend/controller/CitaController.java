package com.pe.vethope.vethope_backend.controller;

import com.pe.vethope.vethope_backend.dto.CitaDTO;
import com.pe.vethope.vethope_backend.service.CitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/citas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CitaController {

    private final CitaService citaService;


    @GetMapping
    public ResponseEntity<List<CitaDTO>> listarTodas() {
        List<CitaDTO> citas = citaService.listarTodos();
        return ResponseEntity.ok(citas);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CitaDTO> buscarPorId(@PathVariable Long id) {
        CitaDTO cita = citaService.buscarPorId(id);
        return ResponseEntity.ok(cita);
    }

    @PostMapping
    public ResponseEntity<CitaDTO> crear(@RequestBody CitaDTO citaDTO) {
        CitaDTO nuevaCita = citaService.crear(citaDTO);
        return ResponseEntity.ok(nuevaCita);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaDTO> actualizar(@PathVariable Long id, @RequestBody CitaDTO citaDTO) {
        CitaDTO citaActualizada = citaService.actualizar(id, citaDTO);
        return ResponseEntity.ok(citaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        citaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
