package com.proyecto.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloDTO {
    @Positive (message = "El id debe ser un número positivo")
    @Schema(
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY    )
    private Long id;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor que cero")
    private Double precio;


    @NotNull(message = "La categoría es obligatoria")
    @Positive(message = "El ID de la categoría debe ser mayor que cero")
    @Schema(
            example = "3"
    )
    private Long categoriaId;
}
