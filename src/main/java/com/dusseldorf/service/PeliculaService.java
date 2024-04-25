package com.dusseldorf.service;

import com.dusseldorf.dto.PeliculaDTO;
import com.dusseldorf.dto.PeliculaPageDTO;
import com.dusseldorf.entity.Pelicula;
import com.dusseldorf.exception.PeliculaNotFoundException;
import com.dusseldorf.mapper.GeneroMapper;
import com.dusseldorf.mapper.PeliculaMapper;
import com.dusseldorf.repository.PeliculaRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;


@Validated
@Service
@RequiredArgsConstructor
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;
    private final PeliculaMapper peliculaMapper;
    private final GeneroMapper generoMapper;

    public PeliculaPageDTO listAll(@PositiveOrZero int page, @Positive @Max(100) int size){
        Page<Pelicula> peliculaPage = peliculaRepository.findAll(PageRequest.of(page, size));
        List<PeliculaDTO> peliculaDTOList = peliculaPage.get().map(peliculaMapper::toPeliculaDTO).toList();
        return new PeliculaPageDTO(peliculaDTOList, peliculaPage.getTotalElements(),peliculaPage.getTotalPages());
    }
    public PeliculaDTO savePelicula(@Valid @NotNull PeliculaDTO peliculaDTO){
        return peliculaMapper.toPeliculaDTO(
                peliculaRepository.save(
                        peliculaMapper.toPeliculaEntity(peliculaDTO)
                )
        );
    }

    public PeliculaDTO findById(@Positive @NotNull Long id){
        return peliculaMapper.toPeliculaDTO(
                peliculaRepository.findById(id).
                        orElseThrow(()-> new PeliculaNotFoundException(id)));
    }

    public PeliculaDTO updatePelicula(@NotNull @Positive Long id,
                                      @Valid @NotNull PeliculaDTO peliculaDTO){

        return peliculaRepository.findById(id)
                .map(pelicula ->{
                    pelicula.setTitulo(peliculaDTO.titulo());
                    pelicula.setDirector(peliculaDTO.director());
                    pelicula.setFechaEstreno(peliculaDTO.fechaEstreno());
                    pelicula.setGenero(generoMapper.toGeneroEntity(peliculaDTO.genero()));

                    return peliculaMapper.toPeliculaDTO(
                            peliculaRepository.save(pelicula));

                }).orElseThrow(() -> new PeliculaNotFoundException(id));

    }

    public void delete(@NotNull @Positive Long id){
        peliculaRepository.delete(peliculaRepository.findById(id)
                .orElseThrow(()-> new PeliculaNotFoundException(id)));
    }

    public List<PeliculaDTO> findPelicualByTitulo(String titulo){

        return peliculaRepository.findByTitulo(titulo).map(peliculaMapper::toPelicualDTOList)
                .orElseThrow(() -> new RuntimeException("Not found titulo"));


    }


}
