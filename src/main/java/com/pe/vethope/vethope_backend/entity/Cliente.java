package com.pe.vethope.vethope_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cliente;

    private String dni;

    private String nombre;

    private String apellido;

    private String email;

    private String telefono;

    @Column(nullable = false)
    private Boolean activo = true;
}
