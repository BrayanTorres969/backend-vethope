package com.pe.vethope.vethope_backend.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoDTO {

    private Long id_producto;

    private String nombre;

    private String imagen;

    private String descripcion;

    private String categoria;

    private Double precio;

    private Integer stock;
}
