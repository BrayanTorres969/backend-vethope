package com.pe.vethope.vethope_backend.repository;

import com.pe.vethope.vethope_backend.entity.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    List<Mascota> findByActivoTrue();

    @Query("SELECT m FROM Mascota m WHERE m.cliente.id_cliente = :idCliente")
    List<Mascota> findByClienteId(@Param("idCliente") Long idCliente);
}
