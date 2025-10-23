package com.pe.vethope.vethope_backend.dto;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CitaDTO {

    private Long id_cita;
    private LocalDate fecha;
    private LocalTime hora;
    private String motivo;
    private Long id_cliente;
    private Long id_mascota;
    private Long id_usuario;
}
