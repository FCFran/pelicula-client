package com.dusseldorf.entity;

import com.dusseldorf.enums.Status;
import com.dusseldorf.enums.converters.StatusConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


import java.time.LocalDate;
@Entity
@Table(name = "TB_PELICULA")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE TB_PELICULA SET status='Inactivo' WHERE id_pelicula = ?")
@Where(clause = "status = 'Activo'")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pelicula")
    private Long idPelicula;
    @Column(unique = true, nullable = false, length = 100)
    @NotNull
    @NotBlank
    private String titulo;
    @Column(nullable = false, length = 100 )
    @NotBlank
    @NotNull
    private String director;

    private LocalDate fechaEstreno;
    @ManyToOne
    @JoinColumn(name = "id_genero")
    private Genero genero;
    @Convert(converter = StatusConverter.class)
    @Builder.Default private Status status = Status.ACTIVO;

}

///@Builder.Default private final long created = System.currentTimeMillis();