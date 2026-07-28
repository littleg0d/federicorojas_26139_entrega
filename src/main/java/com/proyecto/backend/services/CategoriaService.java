package com.proyecto.backend.services;


import com.proyecto.backend.dto.CategoriaDTO;
import com.proyecto.backend.exceptions.CategoriaEnUso;
import com.proyecto.backend.exceptions.NoEncontrado;
import com.proyecto.backend.mappers.CategoriaMapper;
import com.proyecto.backend.model.Categoria;
import com.proyecto.backend.repositories.ArticuloRepository;
import com.proyecto.backend.repositories.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaMapper categoriaMapper;
    private final ArticuloRepository  articuloRepository;
    private final CategoriaRepository categoriaRepository;


    private Categoria buscarCategoria(Long id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new NoEncontrado("Categoria no encontrada"));
    }
    public CategoriaDTO buscarPorId(Long id) {
        Categoria categoria = buscarCategoria(id);
        return categoriaMapper.toCategoriaDTO(categoria);
    }
    public List<CategoriaDTO> mostrarCategorias() {
        return categoriaMapper.toCategoriaDTOList(categoriaRepository.findAll());
    }


    public CategoriaDTO crear(CategoriaDTO dto) {
        Categoria categoria = categoriaMapper.toCategoria(dto);
        Categoria guardado =categoriaRepository.save(categoria);
        return categoriaMapper.toCategoriaDTO(guardado);
    }
    public CategoriaDTO actualizar(CategoriaDTO dto, Long id) {
        Categoria categoria = buscarCategoria(id);
        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());
        Categoria guardado =  categoriaRepository.save(categoria);

        return  categoriaMapper.toCategoriaDTO(guardado);
    }

    public void borrar(Long id) {
        Categoria categoria = buscarCategoria(id);
        if (articuloRepository.existsByCategoriaId(id)) {
            throw new CategoriaEnUso("Categoria con articulos asociados, no se puede eliminar");
        }

        categoriaRepository.deleteById(id);
    }

}
