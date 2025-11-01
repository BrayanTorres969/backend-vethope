package com.pe.vethope.vethope_backend.entity;

import com.pe.vethope.vethope_backend.enums.Rol;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios", uniqueConstraints = {
        @UniqueConstraint(name = "uk_usuarios_username", columnNames = "username"),
        @UniqueConstraint(name = "uk_usuarios_correo", columnNames = "correo")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    private String username;

    private String nombre;

    private String apellido;

    private String correo;

    private String dni;

    private String telefono;

    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Column(nullable = false)
    private Boolean activo = true;


}
