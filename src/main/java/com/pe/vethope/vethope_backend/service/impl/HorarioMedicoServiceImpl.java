package com.pe.vethope.vethope_backend.service.impl;

import com.pe.vethope.vethope_backend.dto.HorarioMedicoDTO;
import com.pe.vethope.vethope_backend.entity.HorarioMedico;
import com.pe.vethope.vethope_backend.entity.Usuario;
import com.pe.vethope.vethope_backend.exception.BusinessException;
import com.pe.vethope.vethope_backend.exception.NotFoundException;
import com.pe.vethope.vethope_backend.mapper.HorarioMedicoMapper;
import com.pe.vethope.vethope_backend.repository.HorarioMedicoRepository;
import com.pe.vethope.vethope_backend.repository.UsuarioRepository;
import com.pe.vethope.vethope_backend.service.HorarioMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HorarioMedicoServiceImpl implements HorarioMedicoService {

    @Autowired
    private HorarioMedicoRepository horarioMedicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private HorarioMedicoMapper horarioMedicoMapper;

    @Override
    public List<HorarioMedicoDTO> listarTodos() {
        return horarioMedicoMapper.toDTOList(horarioMedicoRepository.findAll());
    }

    @Override
    public HorarioMedicoDTO buscarPorId(Long id) {
        HorarioMedico horario = horarioMedicoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Horario no encontrado con ID: " + id));
        return horarioMedicoMapper.toDTO(horario);
    }

    @Override
    public HorarioMedicoDTO crear(HorarioMedicoDTO horarioDTO) {
        Usuario usuario = usuarioRepository.findById(horarioDTO.getId_usuario())
                .orElseThrow(() -> new NotFoundException("Médico no encontrado con ID: " + horarioDTO.getId_usuario()));

        if (usuario.getRol() == null || !usuario.getRol().name().equals("MEDICO")) {
            throw new BusinessException("El usuario con ID " + usuario.getId_usuario() + " no tiene rol MEDICO");
        }

        HorarioMedico horario = horarioMedicoMapper.toEntity(horarioDTO);
        horario.setUsuario(usuario);

        HorarioMedico guardado = horarioMedicoRepository.save(horario);
        return horarioMedicoMapper.toDTO(guardado);
    }

    @Override
    public HorarioMedicoDTO actualizar(Long id, HorarioMedicoDTO horarioDTO) {
        HorarioMedico horarioExistente = horarioMedicoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Horario no encontrado con ID: " + id));

        horarioExistente.setDiaSemana(horarioDTO.getDiaSemana());
        horarioExistente.setHoraInicio(horarioDTO.getHoraInicio());
        horarioExistente.setHoraFin(horarioDTO.getHoraFin());

        HorarioMedico actualizado = horarioMedicoRepository.save(horarioExistente);
        return horarioMedicoMapper.toDTO(actualizado);
    }

    @Override
    public void eliminar(Long id) {
        if (!horarioMedicoRepository.existsById(id)) {
            throw new NotFoundException("No se puede eliminar. Horario no encontrado con ID: " + id);
        }
        horarioMedicoRepository.deleteById(id);
    }

    @Override
    public List<HorarioMedicoDTO> listarPorMedico(Long idUsuario) {
        Usuario medico = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new NotFoundException("Médico no encontrado con ID: " + idUsuario));

        List<HorarioMedico> horarios = horarioMedicoRepository.findByUsuario(medico);
        return horarios.stream()
                .map(horarioMedicoMapper::toDTO)
                .collect(Collectors.toList());
    }
}
