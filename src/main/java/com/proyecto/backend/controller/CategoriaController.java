package com.proyecto.backend.controller;

import com.proyecto.backend.dto.CategoriaDTO;
import com.proyecto.backend.services.CategoriaService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@Tag(name = "Categorias", description = "CRUD de categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }


    @ApiResponse(responseCode = "200", description = "Categorías obtenidas correctamente")
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> getCategorias(){
        return ResponseEntity
                .status(200)
                .body(categoriaService.mostrarCategorias());
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoría encontrada"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> getCategoria(@PathVariable Long id){
        return ResponseEntity
                .status(200)
                .body(categoriaService.buscarPorId(id));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Categoría creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de la categoría inválidos")
    })
    @PostMapping
    public ResponseEntity<CategoriaDTO> crear(@Valid @RequestBody CategoriaDTO dto){
        return ResponseEntity
                .status(201)
                .body(categoriaService.crear(dto));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoría actualizada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de la categoría inválidos"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> actualizar(@Valid @RequestBody CategoriaDTO dto, @PathVariable Long id){
        return ResponseEntity
                .status(200)
                .body(categoriaService.actualizar(dto, id));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Categoría eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada"),
            @ApiResponse(responseCode = "409", description = "La categoría tiene artículos asociados")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id){
        categoriaService.borrar(id);
        return ResponseEntity
                .status(204)
                .build();
    }
}
