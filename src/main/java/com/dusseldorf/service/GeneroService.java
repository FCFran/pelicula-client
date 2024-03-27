package com.dusseldorf.service;

import com.dusseldorf.dto.GeneroDTO;

import com.dusseldorf.mapper.GeneroMapper;
import com.dusseldorf.repository.GeneroRepository;
import jakarta.validation.Valid;

import jakarta.validation.constraints.NotNull;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
@RequiredArgsConstructor
public class GeneroService {

    private final GeneroRepository generoRepository;
    private final GeneroMapper generoMapper;


    public List<GeneroDTO> listAll(){
       //return generoRepository.findAll().stream().map(generoMapper::toGeneroDTO).collect(Collectors.toList());
        return generoMapper.toGeneroDTOList(generoRepository.findAll());
    }
    public GeneroDTO saveGeneroDTO(@Valid @NotNull GeneroDTO generoDTO){
        return generoMapper.toGeneroDTO(generoRepository.save(generoMapper.toGeneroEntity(generoDTO)));
    }


}
