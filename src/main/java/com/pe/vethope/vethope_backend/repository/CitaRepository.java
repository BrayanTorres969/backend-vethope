package com.pe.vethope.vethope_backend.repository;

import com.pe.vethope.vethope_backend.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByActivoTrue();
}
