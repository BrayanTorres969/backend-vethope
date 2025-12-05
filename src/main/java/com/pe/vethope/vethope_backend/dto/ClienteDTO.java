package com.pe.vethope.vethope_backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTO {

    private Long id_cliente;

    private String dni;

    private String nombre;

    private String apellido;

    private String email;

    private String telefono;
}
