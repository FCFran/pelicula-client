package com.dusseldorf.service;

import com.dusseldorf.dto.GeneroDTO;
import com.dusseldorf.dto.PeliculaDTO;
import com.dusseldorf.entity.Genero;
import com.dusseldorf.entity.Pelicula;

import com.dusseldorf.mapper.GeneroMapper;
import com.dusseldorf.mapper.PeliculaMapper;
import com.dusseldorf.repository.PeliculaRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

class PeliculaServiceTest {


    //declarare las dependencias -> de PeliculaService
    @Mock // ANOTACION QUE SIMULA
    private PeliculaRepository peliculaRepository;
    @Mock // LE DECIMOS A MOCKITO QUE QUEREMOS CREAR UN SIMULACRO DE ESTA DEPENDENCIA
    private PeliculaMapper peliculaMapper;
    @Mock
    private GeneroMapper generoMapper;

    //which service we want to test
    /**
     * cuando se trata de objetos que tienen dependencias revisamos, la interfaz para ver que que clases o interfaz estan
     * injectados(el este caso PeliculaService: TIENE PELICULAREPOSITORIO, PELICULAMAPPER, GENEROMAPPER)
     */
    //COMO INJECTAMOS ESTAS PROPIEDADES O DEPENDENCIAS EN PELICULASERVICO -> @INJECTMOCKS
    @InjectMocks
    private PeliculaService peliculaService;


   /* @BeforeEach
    void setUp() {
        //QUEREMOS INICIAR EL SIMULACRO
        //openMocks -> toma la clase actual de prueba por eso se pone this
        MockitoAnnotations.openMocks(this);
    }*/


    @Test
    public void PeliculaService_SavePelicula_ReturnPeliculaDto() {
        GeneroDTO generoDTO = new GeneroDTO( 1L, "Pelicula","Pelicula1");
        Genero genero = Genero.builder().id(1L).name("Pelicula").description("Pelicual1").build();

        Pelicula pelicula = Pelicula.builder()
                .idPelicula(1L)
                .titulo("HOLA MUNDO")
                .director("STEVEN")
                .fechaEstreno(LocalDate.now())
                .genero(genero)
                .build();

        PeliculaDTO peliculaDTO = new PeliculaDTO(
                1L,
                "HOLA MUNDO",
                "STEVEN",
                LocalDate.now(),
                generoDTO
        );

        Mockito.when(peliculaMapper.toPeliculaEntity(peliculaDTO)).thenReturn(pelicula);
        Mockito.when(peliculaRepository.save(Mockito.any(Pelicula.class))).thenReturn(pelicula);
        Mockito.when(peliculaMapper.toPeliculaDTO(pelicula)).thenReturn(peliculaDTO);

        PeliculaDTO peliculaReturn = peliculaService.savePelicula(peliculaDTO);

        //System.out.println(peliculaReturn);

        Assertions.assertThat(peliculaReturn).isNotNull();
        Assertions.assertThat(peliculaReturn.titulo()).isEqualTo(peliculaDTO.titulo());

        // verificar que solo una vez fue llamado el repository , maperr en nuestro aplicativo

        Mockito.verify(peliculaMapper, Mockito.times(1)).toPeliculaEntity(peliculaDTO);
        Mockito.verify(peliculaRepository, Mockito.times(1)).save(pelicula);
        Mockito.verify(peliculaMapper, Mockito.times(1)).toPeliculaDTO(pelicula);
    }

    @Test
    public void PeliculaService_FindByIdPelicula_ReturnPelicualDto(){

        //Given
        Genero genero = Genero.builder().id(1L).name("Pelicula").description("Pelicual1").build();
        GeneroDTO generoDTO = new GeneroDTO( 1L, "Pelicula","Pelicula1");
        Pelicula pelicula = Pelicula.builder()
                .titulo("HOLA MUNDO")
                .director("STEVEN")
                .fechaEstreno(LocalDate.now())
                .genero(genero)
                .build();

       /* PeliculaDTO peliculaDTO = new PeliculaDTO(
                1L,
                "HOLA MUNDO",
                "STEVEN",
                LocalDate.now(),
                generoDTO
        );*/

        //Mock calls -> simular llamdas
        //otra forma de realizarlo
        Mockito.when(peliculaRepository.findById(1L)).thenReturn(Optional.of(pelicula));
       // Mockito.when(peliculaRepository.findById(1L)).thenReturn(Optional.ofNullable(pelicula));
        Mockito.when(peliculaMapper.toPeliculaDTO(Mockito.any(Pelicula.class))).thenReturn(new PeliculaDTO(
                1L,
                "HOLA MUNDO",
                "STEVEN",
                LocalDate.now(),
                generoDTO
        ));

        //when
        PeliculaDTO peliculaReturn = peliculaService.findById(1L);

        //Then
        Assertions.assertThat(peliculaReturn).isNotNull();
        Assertions.assertThat(peliculaReturn.titulo()).isEqualTo(pelicula.getTitulo());
        Assertions.assertThat(peliculaReturn.director()).isEqualTo(pelicula.getDirector());
        Mockito.verify(peliculaRepository, Mockito.times(1)).findById(1L);

    }

    @Test
    public void PeliculaService_FindPeliculaByTitulo_ReturnsPeliculasDTO(){
        String titulo = "titulo";

        Genero genero = Genero.builder().id(1L).name("Pelicula").description("Pelicual1").build();
        GeneroDTO generoDTO = new GeneroDTO( 1L, "Pelicula","Pelicula1");

        List<Pelicula> peliculaList = new ArrayList<>();

        peliculaList.add(Pelicula.builder()
                .titulo("titulo")
                .director("STEVEN")
                .fechaEstreno(LocalDate.now())
                .genero(genero)
                .build());
        List<PeliculaDTO> peliculaDTO = new ArrayList<>();

         peliculaDTO.add(new PeliculaDTO(
                 1L,
                 "titulo",
                 "STEVEN",
                 LocalDate.now(),
                 generoDTO
         )) ;

        //mock calls
        Mockito.when(peliculaRepository.findByTitulo(titulo)).thenReturn(Optional.of(peliculaList));
        Mockito.when(peliculaMapper.toPelicualDTOList(peliculaList)).thenReturn(peliculaDTO);
        //when
        List<PeliculaDTO> peliculaDTOReturn = peliculaService.findPelicualByTitulo(titulo);
        //Then
        org.junit.jupiter.api.Assertions.assertEquals(peliculaList.size(), peliculaDTOReturn.size());
        Mockito.verify(peliculaRepository,Mockito.times(1)).findByTitulo(titulo);

    }

    @Test
    public void PeliculaService_UpdatePelicula_ReturnPeliculaDto(){
        //Given
        Genero genero = Genero.builder().id(1L).name("Pelicula").description("Pelicual1").build();
        GeneroDTO generoDTO = new GeneroDTO( 1L, "Pelicula","Pelicula1");
        Pelicula pelicula = Pelicula.builder()
                .titulo("HOLA MUNDO")
                .director("STEVEN")
                .fechaEstreno(LocalDate.now())
                .genero(genero)
                .build();

        PeliculaDTO peliculaDTO = new PeliculaDTO(
                1L,
                "HOLA MUNDO",
                "STEVEN",
                LocalDate.now(),
                generoDTO);

        //mock calls
        Mockito.when(peliculaRepository.findById(1L)).thenReturn(Optional.ofNullable(pelicula));
        Mockito.when(peliculaRepository.save(Mockito.any(Pelicula.class))).thenReturn(pelicula);
        Mockito.when(generoMapper.toGeneroEntity(Mockito.any(GeneroDTO.class))).thenReturn(genero);
        Mockito.when(peliculaMapper.toPeliculaDTO(Mockito.any(Pelicula.class))).thenReturn(peliculaDTO);


        //when

        PeliculaDTO peliculaReturn = peliculaService.updatePelicula(1L, peliculaDTO);

        //then

        Assertions.assertThat(peliculaReturn).isNotNull();
    }

   /* @Test
    public void should_successfully_save_a_pelicula() {
        //GIVEN
        //-> en el metodo save pelicula ingresa un objetodto

        PeliculaDTO peliculaDTO = new PeliculaDTO(
                1L, "Hola Mundo", "steven", null, new GeneroDTO(2L, "pelicula",
                "pelicula")
        );
        //-> tambien necesitaremos un objeto de tipo pelicula para ser guardado

        Pelicula pelicula = Pelicula.builder()
                .idPelicula(1L)
                .titulo("Hola Mundo")
                .director("steven")
                .fechaEstreno(null)
                .genero(new Genero(2L, "pelicula", "pelicula"))
                .status(Status.ACTIVO)
                .build();

        Pelicula peliculaSaved = Pelicula.builder()
                .idPelicula(1L)
                .titulo("Hola Mundo")
                .director("steven")
                .fechaEstreno(null)
                .genero(new Genero(2L, "pelicula", "pelicula"))
                .status(Status.ACTIVO)
                .build();

        // Mock the calls -> simular las llamadas.
        Mockito.when(peliculaMapper.toPeliculaEntity(peliculaDTO)).thenReturn(pelicula);
        Mockito.when(peliculaRepository.save(pelicula)).thenReturn(peliculaSaved);
        Mockito.when(peliculaMapper.toPeliculaDTO(peliculaSaved)).thenReturn(new PeliculaDTO(
                1L, "Hola Mundo", "steven", null, new GeneroDTO(2L, "pelicula",
                "pelicula")
        ));

        //WHEN
        PeliculaDTO peliculaReturn = peliculaService.savePelicula(peliculaDTO);

        //THEN
        Assertions.assertEquals(peliculaDTO, peliculaReturn);
    }
*/




}