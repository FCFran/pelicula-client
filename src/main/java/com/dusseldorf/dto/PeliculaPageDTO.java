package com.dusseldorf.dto;

import java.util.List;

public record PeliculaPageDTO(
        List<PeliculaDTO> pelicula,
        Long totalElements,
        int totalpages
){
}
