package com.dusseldorf.service;

import com.dusseldorf.dto.GeneroDTO;
import com.dusseldorf.entity.Genero;
import com.dusseldorf.mapper.GeneroMapper;
import com.dusseldorf.repository.GeneroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class GeneroServiceTest {

    @InjectMocks
    private GeneroService generoService;
    @Mock
    private GeneroRepository generoRepository;

    @Mock
    private GeneroMapper generoMapper;


    @Test
    public void generoService_getGeneroAll_returnsGeneroDTOAll(){
        //Given
        List<Genero> generoList = new ArrayList<>();
        generoList.add(Genero.builder().id(1L).name("musica").description("música2").build());

        List<GeneroDTO> generoDTOList = new ArrayList<>();
        generoDTOList.add(new GeneroDTO(1L,"musica","música2"));
        //System.out.println(generoList);


        // Mock the calls -> marque las llamadas

        Mockito.when(generoRepository.findAll()).thenReturn(generoList);

        //any class -> de modo que cuando recibamos cualquier clase  de tipo estudiante devolver un objeto nuevo
        //ArgumentMatchers.any() -> ya que podemos probar cualquier genero, tenemos el comparador de argumentos, cuando
        //recibamos cualquier clase  haga la clase y devolver un objeto nuevo como respuesta
        // cuando solo utilizo en el aplicativo el toGenerateDTO
         /*Mockito.when(generoMapper.toGeneroDTO(ArgumentMatchers.any(Genero.class)))
                 .thenReturn(new GeneroDTO(1L,"musica","música2"));*/

        Mockito.when(generoMapper.toGeneroDTOList(generoList)).thenReturn(generoDTOList);

         //when
        List<GeneroDTO> generoReturn = generoService.listAll();
        System.out.println(generoReturn);

        //then
        Assertions.assertEquals(generoList.size(), generoReturn.size());
        Mockito.verify(generoRepository, Mockito.times(1)).findAll();

    }

}