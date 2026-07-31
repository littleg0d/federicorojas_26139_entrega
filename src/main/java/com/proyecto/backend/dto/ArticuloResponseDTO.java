package com.proyecto.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ArticuloResponseDTO {
    private Long id;
    private String nombre;
    private Double precio;
    private Long categoriaId;
}
