package com.proyecto.backend.mappers;

import com.proyecto.backend.dto.ArticuloDTO;
import com.proyecto.backend.model.Articulo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")   // lo carga como componente auto
public interface ArticuloMapper {

    Articulo toArticulo(ArticuloDTO articuloDTO);
    @Mapping(source = "categoria.id", target = "categoriaId")
    ArticuloDTO toArticuloDTO(Articulo articulo);  // categoria.get(id) no se mapea con categoriaId
    List<ArticuloDTO> toArticuloDTOList(List<Articulo> articulos);
}
