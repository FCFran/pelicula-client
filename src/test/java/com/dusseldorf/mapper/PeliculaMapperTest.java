package com.dusseldorf.mapper;

import com.dusseldorf.dto.GeneroDTO;
import com.dusseldorf.dto.PeliculaDTO;
import com.dusseldorf.entity.Genero;
import com.dusseldorf.entity.Pelicula;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;


import java.time.LocalDate;

//@SpringBootTest
class PeliculaMapperTest {


   @Autowired
   PeliculaMapper mapper;


    @BeforeEach
    void setUp() {
    }

    @Test
    public void PeliculaMapper_ToPeliculaDto_ReturnPeliculaDto(){
        //Given -> lo que he dado
        Pelicula pelicula = Pelicula.builder()
                .idPelicula(1L)
                .titulo("Hola mundo")
                .director("steven")
                .fechaEstreno(LocalDate.now())
                .genero(new Genero(2L, "Pelicula", "Pelicula"))
                .build();

        //when // asignamos un un objeto a un dto de respuesta
        PeliculaDTO peliculaDTO = mapper.toPeliculaDTO(pelicula);

        //then -> espero este resultado
        Assertions.assertEquals(pelicula.getTitulo(), peliculaDTO.titulo());
        Assertions.assertEquals(pelicula.getDirector(), peliculaDTO.director());
        Assertions.assertNotNull(peliculaDTO.genero());
        Assertions.assertEquals(pelicula.getGenero().getId(), peliculaDTO.genero().id());
    }

    @Test
    public void PeliculaMapper_ToPelicula_ReturnPeliculaEntity(){

        PeliculaDTO peliculaDTO = new PeliculaDTO(
                1L,"Hola Mundo", "steven", LocalDate.now(), new GeneroDTO(2L, "pelicula",
                "pelicula")
        );
        Pelicula pelicula = mapper.toPeliculaEntity(peliculaDTO);
        System.out.println(pelicula);
        Assertions.assertEquals(peliculaDTO.titulo(), pelicula.getTitulo());
        Assertions.assertEquals(peliculaDTO.director(), pelicula.getDirector());
        Assertions.assertEquals(peliculaDTO.fechaEstreno(), pelicula.getFechaEstreno());
        Assertions.assertNotNull(pelicula.getGenero()); // no nulo
        Assertions.assertEquals(peliculaDTO.genero().id(), pelicula.getGenero().getId());

        //Assertions.assertThat(pelicula).isNotNull();


    }

    //este metodo se realiza cuando el mapper es un clase y se pone if cuando el parametro es nulo y lanzamos un exception
    @Test
    public void should_throw_null_pointer_exception_when_peliculaDto_is_null(){
       var exp = Assertions.assertThrows(NullPointerException.class,() -> mapper.toPeliculaEntity(null));
       //verificar si el mensaje es el mismo
       Assertions.assertEquals("mensaje de exception", exp.getMessage());
    }

}