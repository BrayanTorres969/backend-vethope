package com.pe.vethope.vethope_backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    @Schema(description = "ID del usuario", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id_usuario;

    private String username;

    private String nombre;

    private String apellido;

    private String correo;

    private String dni;

    private String telefono;

    private String password;

    private String rol;
}
