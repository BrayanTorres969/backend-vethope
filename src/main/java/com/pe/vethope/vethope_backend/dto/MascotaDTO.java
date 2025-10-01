package com.pe.vethope.vethope_backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MascotaDTO {

    private Long id;

    private String nombre;

    private int edad;

    private String raza;

    private double altura;

    private ClienteDTO duenioId;

}
