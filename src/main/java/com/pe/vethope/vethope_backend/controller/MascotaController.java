package com.pe.vethope.vethope_backend.controller;

import com.pe.vethope.vethope_backend.dto.MascotaDTO;
import com.pe.vethope.vethope_backend.service.MascotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mascotas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MascotaController {

    private final MascotaService mascotaService;


    @PostMapping
    public ResponseEntity<MascotaDTO> registrarMascota(@RequestBody MascotaDTO mascotaDTO) {
        MascotaDTO nuevaMascota = mascotaService.registrarMascota(mascotaDTO);
        return ResponseEntity.ok(nuevaMascota);
    }

    @GetMapping
    public ResponseEntity<List<MascotaDTO>> listarMascotas() {
        List<MascotaDTO> mascotas = mascotaService.listartodos();
        return ResponseEntity.ok(mascotas);
    }


    @GetMapping("/{id_mascota}")
    public ResponseEntity<MascotaDTO> obtenerPorId(@PathVariable Long id_mascota) {
        MascotaDTO mascota = mascotaService.obtenerMascotaPorId(id_mascota);
        return ResponseEntity.ok(mascota);
    }

    @GetMapping("/cliente/{id_cliente}")
    public ResponseEntity<List<MascotaDTO>> listarPorCliente(@PathVariable Long id_cliente) {
        List<MascotaDTO> mascotas = mascotaService.listarMascotasPorCliente(id_cliente);
        return ResponseEntity.ok(mascotas);
    }

    @PutMapping("/{id_mascota}")
    public ResponseEntity<MascotaDTO> actualizarMascota(@PathVariable Long id_mascota,
                                                        @RequestBody MascotaDTO mascotaDTO) {
        MascotaDTO actualizada = mascotaService.actualizarMascota(id_mascota, mascotaDTO);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id_mascota}")
    public ResponseEntity<Void> eliminarMascota(@PathVariable Long id_mascota) {
        mascotaService.eliminarMascota(id_mascota);
        return ResponseEntity.noContent().build();
    }
}
