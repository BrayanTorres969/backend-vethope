package com.pe.vethope.vethope_backend.mapper;

import com.pe.vethope.vethope_backend.dto.MascotaDTO;
import com.pe.vethope.vethope_backend.entity.Mascota;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MascotaMapper {

    @Mapping(source = "cliente.id_cliente", target = "id_cliente")
    MascotaDTO toDTO(Mascota mascota);

    @Mapping(target = "cliente.id_cliente", source = "id_cliente")
    @Mapping(target = "activo", constant = "true")
    Mascota toEntity(MascotaDTO mascotaDTO);

    List<MascotaDTO> toDTOList(List<Mascota> mascotas);
}





