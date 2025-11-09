package com.pe.vethope.vethope_backend.entity;


import com.pe.vethope.vethope_backend.enums.DiaSemana;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "horarios_medicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HorarioMedico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_horario;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario; // Debe tener rol = MEDICO

    @Enumerated(EnumType.STRING)
    private DiaSemana diaSemana; // LUNES, MARTES, etc.

    private LocalTime horaInicio;

    private LocalTime horaFin;
}
