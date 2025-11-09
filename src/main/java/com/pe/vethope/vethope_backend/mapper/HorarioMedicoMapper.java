package com.pe.vethope.vethope_backend.mapper;


import com.pe.vethope.vethope_backend.dto.HorarioMedicoDTO;
import com.pe.vethope.vethope_backend.entity.HorarioMedico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HorarioMedicoMapper {

    @Mapping(source = "usuario.id_usuario", target = "id_usuario")
    HorarioMedicoDTO toDTO(HorarioMedico horario);

    @Mapping(target = "usuario.id_usuario", source = "id_usuario")
    HorarioMedico toEntity(HorarioMedicoDTO horarioDTO);

    List<HorarioMedicoDTO> toDTOList(List<HorarioMedico> horarios);
}
