package com.pe.vethope.vethope_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mascotas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id_mascota;

    public int edad;

    public String nombre;

    public double peso;

    public String raza;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente duenio;

    public double altura;

    @Column(nullable = false)
    private Boolean activo = true;


}
