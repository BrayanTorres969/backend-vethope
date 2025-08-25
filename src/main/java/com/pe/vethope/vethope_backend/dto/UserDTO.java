package com.pe.vethope.vethope_backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
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
