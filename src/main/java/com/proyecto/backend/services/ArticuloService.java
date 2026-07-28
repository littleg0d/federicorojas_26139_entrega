package com.proyecto.backend.services;

import com.proyecto.backend.dto.ArticuloDTO;
import com.proyecto.backend.exceptions.NoEncontrado;
import com.proyecto.backend.mappers.ArticuloMapper;
import com.proyecto.backend.model.Articulo;
import com.proyecto.backend.model.Categoria;
import com.proyecto.backend.repositories.ArticuloRepository;
import com.proyecto.backend.repositories.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticuloService {
    private final ArticuloRepository articuloRepository;
    private final CategoriaRepository categoriaRepository;
    private final ArticuloMapper articuloMapper;




    public ArticuloDTO buscarPorId(Long id) {
        Articulo articulo = buscarArticulo(id);
        return articuloMapper.toArticuloDTO(articulo);
    }
    private Articulo buscarArticulo(Long id){
        return articuloRepository.findById(id).orElseThrow(() -> new NoEncontrado("Articulo no encontrado"));
    }

    public ArticuloDTO crear(ArticuloDTO dto){
        Categoria categoria = buscarCategoria(dto.getCategoriaId());
        Articulo articulo = articuloMapper.toArticulo(dto);
        articulo.setCategoria(categoria);
        return  articuloMapper.toArticuloDTO(articuloRepository.save(articulo));
    }
    public ArticuloDTO actualizar(Long id, ArticuloDTO dto){
        Articulo articulo = buscarArticulo(id);
        Categoria categoria = buscarCategoria(dto.getCategoriaId());
        articulo.setNombre(dto.getNombre());
        articulo.setCategoria(categoria);
        articulo.setPrecio(dto.getPrecio());
        return articuloMapper.toArticuloDTO(articuloRepository.save(articulo));
    }
    public void eliminar(Long id) {
        Articulo articulo = buscarArticulo(id);
        articuloRepository.delete(articulo);
    }

    public List<ArticuloDTO> listar(){
        return articuloMapper.toArticuloDTOList(articuloRepository.findAll());
    }

    private Categoria buscarCategoria(Long id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new NoEncontrado("Categoria no encontrada"));
    }




}
