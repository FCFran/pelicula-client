package com.dusseldorf.mapper;

import com.dusseldorf.dto.PeliculaDTO;
import com.dusseldorf.entity.Pelicula;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {GeneroMapper.class})
public interface PeliculaMapper {

    @Mappings({
            @Mapping(source = "idPelicula", target = "idPelicula"),
            @Mapping(source = "titulo", target = "titulo"),
            @Mapping(source = "director", target = "director"),
            @Mapping(source = "fechaEstreno", target = "fechaEstreno"),
            @Mapping(source = "genero", target = "genero")
    })
    PeliculaDTO toPeliculaDTO(Pelicula pelicula);
    @InheritInverseConfiguration
    Pelicula toPeliculaEntity(PeliculaDTO peliculaDTO);

    List<PeliculaDTO> toPelicualDTOList(List<Pelicula> peliculaList);

}
