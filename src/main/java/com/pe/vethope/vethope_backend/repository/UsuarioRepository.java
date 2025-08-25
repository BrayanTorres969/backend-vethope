package com.pe.vethope.vethope_backend.repository;

import com.pe.vethope.vethope_backend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByCorreo(String correo);
    Boolean existsByUsername(String username);
    Boolean existsByCorreo(String correo);
    Optional<Usuario> findByUsernameAndActivoTrue(String username);
}