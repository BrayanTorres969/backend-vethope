package com.pe.vethope.vethope_backend.service.impl;

import com.pe.vethope.vethope_backend.dto.MascotaDTO;
import com.pe.vethope.vethope_backend.entity.Cliente;
import com.pe.vethope.vethope_backend.entity.Mascota;
import com.pe.vethope.vethope_backend.exception.NotFoundException;
import com.pe.vethope.vethope_backend.mapper.MascotaMapper;
import com.pe.vethope.vethope_backend.repository.ClienteRepository;
import com.pe.vethope.vethope_backend.repository.MascotaRepository;
import com.pe.vethope.vethope_backend.service.MascotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class  MascotaServiceImpl implements MascotaService {

    private final MascotaRepository mascotaRepository;
    private final ClienteRepository clienteRepository;
    private final MascotaMapper mascotaMapper;


    @Override
    public MascotaDTO registrarMascota(MascotaDTO mascotaDTO) {
        Cliente cliente = clienteRepository.findById(mascotaDTO.getId_cliente())
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));

        Mascota mascota = mascotaMapper.toEntity(mascotaDTO);
        mascota.setCliente(cliente);
        mascota.setActivo(true);

        Mascota guardada = mascotaRepository.save(mascota);
        return mascotaMapper.toDTO(guardada);
    }

    @Override
    public MascotaDTO obtenerMascotaPorId(Long idMascota) {
        Mascota mascota = mascotaRepository.findById(idMascota)
                .orElseThrow(() -> new NotFoundException("Mascota no encontrada"));
        return mascotaMapper.toDTO(mascota);
    }

    @Override
    public List<MascotaDTO> listartodos() {
        return mascotaMapper.toDTOList(mascotaRepository.findByActivoTrue());
    }

    @Override
    public List<MascotaDTO> listarMascotasPorCliente(Long idCliente) {
        List<Mascota> mascotas = mascotaRepository.findByClienteId(idCliente);
        return mascotaMapper.toDTOList(mascotas);
    }

    @Override
    public MascotaDTO actualizarMascota(Long idMascota, MascotaDTO mascotaDTO) {
        Mascota mascotaExistente = mascotaRepository.findById(idMascota)
                .orElseThrow(() -> new NotFoundException("Mascota no encontrada"));

        mascotaExistente.setNombre(mascotaDTO.getNombre());
        mascotaExistente.setEdad(mascotaDTO.getEdad());
        mascotaExistente.setPeso(mascotaDTO.getPeso());
        mascotaExistente.setRaza(mascotaDTO.getRaza());
        mascotaExistente.setEspecie(mascotaDTO.getEspecie());

        Mascota actualizada = mascotaRepository.save(mascotaExistente);
        return mascotaMapper.toDTO(actualizada);
    }

    @Override
    public void eliminarMascota(Long idMascota) {
        Mascota mascota = mascotaRepository.findById(idMascota)
                .orElseThrow(() -> new NotFoundException("Mascota no encontrada"));
        mascota.setActivo(false);
        mascotaRepository.save(mascota);
    }
}
