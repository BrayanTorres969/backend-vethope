package com.pe.vethope.vethope_backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MascotaDTO {

    private Long id_mascota;
    private String nombre;
    private int edad;
    private double peso;
    private String raza;
    private String especie;
    private Long id_cliente;

}
