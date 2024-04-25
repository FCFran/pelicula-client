package com.dusseldorf.repository;

import com.dusseldorf.entity.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    Optional<List<Pelicula>> findByTitulo(String titulo);
}
