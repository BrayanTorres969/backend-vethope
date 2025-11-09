package com.pe.vethope.vethope_backend.dto;


import com.pe.vethope.vethope_backend.enums.DiaSemana;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HorarioMedicoDTO {
    private Long id_horario;
    private Long id_usuario;
    private DiaSemana diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
}
