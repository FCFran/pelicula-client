package com.dusseldorf.controller;

import com.dusseldorf.dto.PeliculaDTO;
import com.dusseldorf.dto.PeliculaPageDTO;
import com.dusseldorf.service.PeliculaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/pelicula")
@RequiredArgsConstructor
public class PeliculaController {

    private final PeliculaService peliculaService;

    @GetMapping
    public PeliculaPageDTO listAll(
            @RequestParam(defaultValue = "0") @PositiveOrZero int page,
            @RequestParam(defaultValue = "10") @Positive @Max(100) int size){
        return peliculaService.listAll(page, size);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PeliculaDTO savePelicual(@RequestBody @Valid @NotNull PeliculaDTO peliculaDTO){
        return peliculaService.savePelicula(peliculaDTO);
    }

    @GetMapping("/{id}")
    public PeliculaDTO findById(@PathVariable @NotNull @Positive Long id){
        return peliculaService.findById(id);
    }

    @PutMapping("/{id}")
    public PeliculaDTO update(@PathVariable @NotNull @Positive Long id,
                              @RequestBody @Valid @NotNull PeliculaDTO peliculaDTO){
        return peliculaService.updatePelicula(id, peliculaDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id){
        peliculaService.delete(id);
    }
}
