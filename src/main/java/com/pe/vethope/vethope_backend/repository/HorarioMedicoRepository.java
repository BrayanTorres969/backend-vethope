package com.pe.vethope.vethope_backend.repository;

import com.pe.vethope.vethope_backend.entity.HorarioMedico;
import com.pe.vethope.vethope_backend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HorarioMedicoRepository extends JpaRepository<HorarioMedico, Long> {

    List<HorarioMedico> findByUsuario(Usuario medico);
}
