package com.pe.vethope.vethope_backend.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;

    private String nombre;

    private String descripcion;

    @Lob
    @Column(name = "imagen",nullable = true, columnDefinition = "bytea")
    private byte[] imagen;

    private String categoria;

    private Double precio;

    private Integer stock;

    @Column(nullable = false)
    private Boolean activo = true;
}
