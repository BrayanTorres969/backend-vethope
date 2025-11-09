package com.pe.vethope.vethope_backend.controller;

import com.pe.vethope.vethope_backend.dto.UserDTO;
import com.pe.vethope.vethope_backend.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
@CrossOrigin(origins = "*")
@Tag(name = "Usuarios", description = "Endpoints para la gestión de usuarios del sistema")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Listar todos los usuarios", description = "Devuelve una lista de todos los usuarios registrados")
    public ResponseEntity<List<UserDTO>> listarUsuarios() {
        List<UserDTO> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Editar un usuario", description = "Permite modificar los datos de un usuario existente")
    public ResponseEntity<UserDTO> editarUsuario(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        try {
            UserDTO usuarioActualizado = usuarioService.editarUsuario(id, userDTO);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (RuntimeException e) {
            UserDTO error = new UserDTO();
            error.setNombre("Error: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un usuario", description = "Realiza un borrado lógico del usuario (cambia el estado activo a false)")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        try {
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.ok("Usuario eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/inactivos")
    @Operation(summary = "Listar usuarios inactivos", description = "Devuelve una lista de los usuarios eliminados lógicamente (activo = false)")
    public ResponseEntity<List<UserDTO>> listarUsuariosInactivos() {
        List<UserDTO> usuarios = usuarioService.listarUsuariosInactivos();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/{id}/activar")
    @Operation(summary = "Reactivar usuario", description = "Permite restaurar un usuario previamente eliminado (activo = false)")
    public ResponseEntity<UserDTO> activarUsuario(@PathVariable Long id) {
        try {
            UserDTO usuario = usuarioService.activarUsuario(id);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            UserDTO error = new UserDTO();
            error.setNombre("Error: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}
