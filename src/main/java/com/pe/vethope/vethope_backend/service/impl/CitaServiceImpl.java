package com.pe.vethope.vethope_backend.service.impl;

import com.pe.vethope.vethope_backend.dto.CitaDTO;
import com.pe.vethope.vethope_backend.entity.Cita;
import com.pe.vethope.vethope_backend.entity.Cliente;
import com.pe.vethope.vethope_backend.entity.Mascota;
import com.pe.vethope.vethope_backend.entity.Usuario;
import com.pe.vethope.vethope_backend.mapper.CitaMapper;
import com.pe.vethope.vethope_backend.repository.CitaRepository;
import com.pe.vethope.vethope_backend.repository.ClienteRepository;
import com.pe.vethope.vethope_backend.repository.MascotaRepository;
import com.pe.vethope.vethope_backend.repository.UsuarioRepository;
import com.pe.vethope.vethope_backend.service.CitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;
    private final ClienteRepository clienteRepository;
    private final MascotaRepository mascotaRepository;
    private final UsuarioRepository usuarioRepository;
    private final CitaMapper citaMapper;


    @Override
    public List<CitaDTO> listarTodos() {
        return citaMapper.toDTOList(citaRepository.findByActivoTrue());
    }

    @Override
    public CitaDTO buscarPorId(Long id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        return citaMapper.toDTO(cita);
    }

    @Override
    public CitaDTO crear(CitaDTO citaDTO) {
        Cita cita = citaMapper.toEntity(citaDTO);

        Cliente cliente = clienteRepository.findById(citaDTO.getId_cliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Mascota mascota = mascotaRepository.findById(citaDTO.getId_mascota())
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
        Usuario usuario = usuarioRepository.findById(citaDTO.getId_usuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        cita.setCliente(cliente);
        cita.setMascota(mascota);
        cita.setUsuario(usuario);

        return citaMapper.toDTO(citaRepository.save(cita));
    }

    @Override
    public CitaDTO actualizar(Long id, CitaDTO citaDTO) {
        Cita citaExistente = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        citaExistente.setFecha(citaDTO.getFecha());
        citaExistente.setHora(citaDTO.getHora());
        citaExistente.setMotivo(citaDTO.getMotivo());

        Cliente cliente = clienteRepository.findById(citaDTO.getId_cliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Mascota mascota = mascotaRepository.findById(citaDTO.getId_mascota())
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
        Usuario usuario = usuarioRepository.findById(citaDTO.getId_usuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        citaExistente.setCliente(cliente);
        citaExistente.setMascota(mascota);
        citaExistente.setUsuario(usuario);

        return citaMapper.toDTO(citaRepository.save(citaExistente));
    }

    @Override
    public void eliminar(Long id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        cita.setActivo(false);
        citaRepository.save(cita);
    }
}