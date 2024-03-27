package com.dusseldorf.exception;

public class PeliculaNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public PeliculaNotFoundException(Long id){
        super("Registro de Pelicula no encontrado: " + id);
    }
}
