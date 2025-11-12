package com.pe.vethope.vethope_backend.controller;

import com.pe.vethope.vethope_backend.dto.HorarioMedicoDTO;
import com.pe.vethope.vethope_backend.service.HorarioMedicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/horarios")
@Tag(name = "Horarios Médicos", description = "Operaciones relacionadas con la gestión de horarios de los médicos")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyAuthority('ADMIN', 'RECEPCIONISTA', 'MEDICO')")
public class HorarioMedicoController {

    private final HorarioMedicoService horarioMedicoService;

    @GetMapping
    @Operation(
            summary = "Listar todos los horarios médicos",
            description = "Obtiene una lista completa de los horarios registrados para todos los médicos."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de horarios obtenida correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<HorarioMedicoDTO>> listarTodos() {
        List<HorarioMedicoDTO> horarios = horarioMedicoService.listarTodos();
        return ResponseEntity.ok(horarios);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar horario por ID",
            description = "Obtiene la información de un horario médico específico según su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Horario encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encontró un horario con el ID proporcionado")
    })
    public ResponseEntity<HorarioMedicoDTO> buscarPorId(@PathVariable Long id) {
        HorarioMedicoDTO horario = horarioMedicoService.buscarPorId(id);
        return ResponseEntity.ok(horario);
    }

    @PostMapping
    @Operation(
            summary = "Registrar un nuevo horario médico",
            description = "Crea un nuevo horario para un médico especificando día, hora de inicio y hora fin."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Horario creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public ResponseEntity<HorarioMedicoDTO> crear(@RequestBody HorarioMedicoDTO horarioDTO) {
        HorarioMedicoDTO nuevoHorario = horarioMedicoService.crear(horarioDTO);
        return ResponseEntity.ok(nuevoHorario);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar un horario médico existente",
            description = "Modifica los datos de un horario existente según el ID especificado."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Horario actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "No se encontró el horario a actualizar"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public ResponseEntity<HorarioMedicoDTO> actualizar(@PathVariable Long id, @RequestBody HorarioMedicoDTO horarioDTO) {
        HorarioMedicoDTO horarioActualizado = horarioMedicoService.actualizar(id, horarioDTO);
        return ResponseEntity.ok(horarioActualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar un horario médico",
            description = "Elimina un horario médico del sistema según el ID especificado."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Horario eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "No se encontró el horario a eliminar")
    })
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        horarioMedicoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/medico/{idUsuario}")
    @Operation(
            summary = "Listar horarios por médico",
            description = "Obtiene todos los horarios registrados para un médico específico, identificado por su ID de usuario."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de horarios obtenida correctamente"),
            @ApiResponse(responseCode = "404", description = "No se encontraron horarios para el médico indicado")
    })
    public ResponseEntity<List<HorarioMedicoDTO>> listarPorMedico(@PathVariable Long idUsuario) {
        List<HorarioMedicoDTO> horarios = horarioMedicoService.listarPorMedico(idUsuario);
        return ResponseEntity.ok(horarios);
    }
}
