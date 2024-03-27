package com.dusseldorf.controller;

import com.dusseldorf.dto.GeneroDTO;

import com.dusseldorf.service.GeneroService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/genero")
@RequiredArgsConstructor
public class GeneroController {

    private final GeneroService generoService;

    @GetMapping
    public List<GeneroDTO> listAll(){
        return generoService.listAll();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GeneroDTO save(@RequestBody @Valid @NotNull GeneroDTO generoDTO){
        return  generoService.saveGeneroDTO(generoDTO);
    }
}
