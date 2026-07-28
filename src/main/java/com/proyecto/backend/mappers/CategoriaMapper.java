package com.proyecto.backend.mappers;

import com.proyecto.backend.dto.CategoriaDTO;
import com.proyecto.backend.model.Categoria;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    Categoria toCategoria(CategoriaDTO categoriaDTO);
    CategoriaDTO toCategoriaDTO(Categoria categoria);
    List<CategoriaDTO> toCategoriaDTOList(List<Categoria> categorias);
}
