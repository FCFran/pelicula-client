package com.dusseldorf.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PeliculaDTO(

        Long idPelicula,

        @NotNull
        @NotBlank
        String titulo,

        @NotBlank
        @NotNull
        String director,
        LocalDate fechaEstreno,
        GeneroDTO genero
) {
}
