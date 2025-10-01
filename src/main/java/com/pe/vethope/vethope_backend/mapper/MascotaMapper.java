package com.pe.vethope.vethope_backend.mapper;

import com.pe.vethope.vethope_backend.dto.MascotaDTO;
import com.pe.vethope.vethope_backend.entity.Mascota;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MascotaMapper {

    MascotaDTO toDTO(Mascota mascota);
    Mascota toEntity(MascotaDTO mascotaDTO);
    List<MascotaDTO> toDTOList(List<Mascota> mascotas);
}





