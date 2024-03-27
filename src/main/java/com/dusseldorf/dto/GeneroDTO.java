package com.dusseldorf.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GeneroDTO(
        Long id,

        @NotNull
        @NotBlank
        String name,

        @NotNull
        @NotBlank
        String description
) {
}
