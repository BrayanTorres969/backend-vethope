package com.pe.vethope.vethope_backend.controller;

import com.pe.vethope.vethope_backend.dto.LoginRequest;
import com.pe.vethope.vethope_backend.dto.LoginResponse;
import com.pe.vethope.vethope_backend.dto.UserDTO;
import com.pe.vethope.vethope_backend.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
//@CrossOrigin(origins = "*")
@Tag(name = "Autenticación", description = "Endpoints de registro y login de usuarios")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    @Operation(summary = "Login de usuario", description = "Genera token JWT para autenticación")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse response = usuarioService.login(loginRequest);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            LoginResponse errorResponse = new LoginResponse();
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }


    //@PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/register")
    @Operation(summary = "Registrar un nuevo usuario", description = "Permite registrar un usuario con datos básicos y rol")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
        try {
            UserDTO usuarioCreado = usuarioService.registrarUsuario(userDTO);
            return ResponseEntity.ok(usuarioCreado);
        } catch (RuntimeException e) {
            UserDTO errorResponse = new UserDTO();
            errorResponse.setNombre("Error: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}

