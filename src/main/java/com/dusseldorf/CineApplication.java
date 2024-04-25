package com.dusseldorf;

import com.dusseldorf.dto.GeneroDTO;
import com.dusseldorf.dto.PeliculaDTO;
import com.dusseldorf.entity.Pelicula;
import com.dusseldorf.mapper.PeliculaMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class CineApplication {


	public static void main(String[] args) {
		SpringApplication.run(CineApplication.class, args);

	}

	@Bean
	public CommandLineRunner commandLineRunner(PeliculaMapper peliculaMapper){
		return args -> {
			PeliculaDTO peliculaDTO = new PeliculaDTO(
					1L,"Hola Mundo", "steven", LocalDate.now(), new GeneroDTO(2L, "pelicula",
					"pelicula")
			);
			Pelicula pelicula =  peliculaMapper.toPeliculaEntity(peliculaDTO);
			System.out.println(pelicula);
		};
	}

}
