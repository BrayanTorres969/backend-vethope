package com.pe.vethope.vethope_backend.controller;

import com.pe.vethope.vethope_backend.dto.MascotaDTO;
import com.pe.vethope.vethope_backend.service.MascotaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mascotas")
@Tag(name = "Mascotas", description = "Operaciones relacionadas con la gestión de mascotas")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*")
public class MascotaController {

    private final MascotaService mascotaService;


    @PostMapping
    @Operation(
            summary = "Registrar una nueva mascota",
            description = "Crea una nueva mascota asociada a un cliente, proporcionando sus datos básicos."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Mascota registrada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o incompletos")
    })
    public ResponseEntity<MascotaDTO> registrarMascota(@RequestBody MascotaDTO mascotaDTO) {
        MascotaDTO nuevaMascota = mascotaService.registrarMascota(mascotaDTO);
        return ResponseEntity.ok(nuevaMascota);
    }

    @GetMapping
    @Operation(
            summary = "Listar todas las mascotas",
            description = "Obtiene la lista completa de todas las mascotas registradas en el sistema."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de mascotas obtenida correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<MascotaDTO>> listarMascotas() {
        List<MascotaDTO> mascotas = mascotaService.listartodos();
        return ResponseEntity.ok(mascotas);
    }


    @GetMapping("/{id_mascota}")
    @Operation(
            summary = "Obtener una mascota por ID",
            description = "Recupera los datos de una mascota específica usando su identificador único."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mascota encontrada correctamente"),
            @ApiResponse(responseCode = "404", description = "No se encontró la mascota con el ID especificado")
    })
    public ResponseEntity<MascotaDTO> obtenerPorId(@PathVariable Long id_mascota) {
        MascotaDTO mascota = mascotaService.obtenerMascotaPorId(id_mascota);
        return ResponseEntity.ok(mascota);
    }

    @GetMapping("/cliente/{id_cliente}")
    @Operation(
            summary = "Listar mascotas por cliente",
            description = "Obtiene todas las mascotas registradas pertenecientes a un cliente específico."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de mascotas obtenida correctamente"),
            @ApiResponse(responseCode = "404", description = "No se encontraron mascotas para el cliente indicado")
    })
    public ResponseEntity<List<MascotaDTO>> listarPorCliente(@PathVariable Long id_cliente) {
        List<MascotaDTO> mascotas = mascotaService.listarMascotasPorCliente(id_cliente);
        return ResponseEntity.ok(mascotas);
    }

    @PutMapping("/{id_mascota}")
    @Operation(
            summary = "Actualizar una mascota",
            description = "Modifica los datos de una mascota existente según el ID especificado."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mascota actualizada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud"),
            @ApiResponse(responseCode = "404", description = "No se encontró la mascota a actualizar")
    })
    public ResponseEntity<MascotaDTO> actualizarMascota(@PathVariable Long id_mascota,
                                                        @RequestBody MascotaDTO mascotaDTO) {
        MascotaDTO actualizada = mascotaService.actualizarMascota(id_mascota, mascotaDTO);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id_mascota}")
    @Operation(
            summary = "Eliminar una mascota",
            description = "Elimina una mascota del sistema según su identificador único."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Mascota eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "No se encontró la mascota a eliminar")
    })
    public ResponseEntity<Void> eliminarMascota(@PathVariable Long id_mascota) {
        mascotaService.eliminarMascota(id_mascota);
        return ResponseEntity.noContent().build();
    }
}
