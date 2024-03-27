package com.dusseldorf.mapper;

import com.dusseldorf.dto.GeneroDTO;
import com.dusseldorf.entity.Genero;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GeneroMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "description", target = "description")
    })
    GeneroDTO toGeneroDTO(Genero genero);
    @InheritInverseConfiguration
    Genero toGeneroEntity(GeneroDTO generoDTO);

    //para Listar

    List<Genero> toGeneroList(List<GeneroDTO> generoDTOList);
    List<GeneroDTO> toGeneroDTOList(List<Genero> generoList);

}
