package com.pe.vethope.vethope_backend.controller;

import com.pe.vethope.vethope_backend.dto.CitaDTO;
import com.pe.vethope.vethope_backend.service.CitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/citas")
@Tag(name = "Citas", description = "Operaciones relacionadas con la gestión de citas médicas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CitaController {

    private final CitaService citaService;


    @GetMapping
    @Operation(
            summary = "Listar todas las citas",
            description = "Obtiene una lista completa de todas las citas médicas registradas."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de citas obtenida correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<CitaDTO>> listarTodas() {
        List<CitaDTO> citas = citaService.listarTodos();
        return ResponseEntity.ok(citas);
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar una cita por ID",
            description = "Obtiene los detalles de una cita específica según su identificador único."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cita encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encontró una cita con el ID proporcionado")
    })
    public ResponseEntity<CitaDTO> buscarPorId(@PathVariable Long id) {
        CitaDTO cita = citaService.buscarPorId(id);
        return ResponseEntity.ok(cita);
    }

    @PostMapping
    @Operation(
            summary = "Registrar una nueva cita",
            description = "Crea una nueva cita médica con la información proporcionada en el cuerpo de la solicitud."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cita creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public ResponseEntity<CitaDTO> crear(@RequestBody CitaDTO citaDTO) {
        CitaDTO nuevaCita = citaService.crear(citaDTO);
        return ResponseEntity.ok(nuevaCita);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar una cita existente",
            description = "Modifica los datos de una cita existente según el ID especificado."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cita actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "No se encontró la cita a actualizar"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public ResponseEntity<CitaDTO> actualizar(@PathVariable Long id, @RequestBody CitaDTO citaDTO) {
        CitaDTO citaActualizada = citaService.actualizar(id, citaDTO);
        return ResponseEntity.ok(citaActualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar una cita",
            description = "Elimina una cita existente del sistema según el ID especificado."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cita eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "No se encontró la cita a eliminar")
    })
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        citaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
