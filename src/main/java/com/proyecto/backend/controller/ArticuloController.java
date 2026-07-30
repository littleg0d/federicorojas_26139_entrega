package com.proyecto.backend.controller;

import com.proyecto.backend.dto.ArticuloDTO;
import com.proyecto.backend.services.ArticuloService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/articulos")
@RestController
@Tag(name = "Articulos", description = "CRUD de articulos")
public class ArticuloController {
    private final ArticuloService articuloService;

    public ArticuloController(ArticuloService articuloService) {
        this.articuloService = articuloService;
    }


    @ApiResponse(responseCode = "200", description = "Artículos obtenidos correctamente")
    @GetMapping
    public ResponseEntity<List<ArticuloDTO>> getArticulos(){
        return ResponseEntity
                .status(200)
                .body(articuloService.listar());
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Artículo encontrado"),
            @ApiResponse(responseCode = "404", description = "Artículo no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ArticuloDTO> getArticuloById(@PathVariable Long id){
        return ResponseEntity
                .status(200)
                .body(articuloService.buscarPorId(id));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Artículo creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos del artículo inválidos"),
            @ApiResponse(responseCode = "404", description = "Categoria no encontrada")
    })
    @PostMapping
    public ResponseEntity<ArticuloDTO> crearArticulo(@Valid @RequestBody ArticuloDTO dto){
        return ResponseEntity
                .status(201)
                .body((articuloService.crear(dto)));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Artículo actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos del artículo inválidos"),
            @ApiResponse(responseCode = "404", description = "Artículo o categoria no encontrados")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ArticuloDTO> modificarArticulo(@PathVariable Long id, @Valid @RequestBody ArticuloDTO dto){
        return ResponseEntity
                .status(200)
                .body((articuloService.actualizar(id, dto)));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Artículo eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Artículo no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarArticulo(@PathVariable Long id){
        articuloService.eliminar(id);
        return ResponseEntity
                .status(204)
                .build();
    }

}
