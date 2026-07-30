package com.proyecto.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "articulos",
        indexes = {
                @Index(
                name = "index_articulo_categoria",
                columnList = "categoria_id"
        )
        })
@NoArgsConstructor

@Getter
@Setter
public class Articulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private double precio;
    @ManyToOne(optional = false)
    @JoinColumn(name="categoria_id", nullable = false)
    private Categoria categoria;
}
