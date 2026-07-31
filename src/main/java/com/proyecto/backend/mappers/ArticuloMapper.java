package com.proyecto.backend.mappers;

import com.proyecto.backend.dto.ArticuloRequestDTO;
import com.proyecto.backend.dto.ArticuloResponseDTO;
import com.proyecto.backend.model.Articulo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")   // lo carga como componente auto
public interface ArticuloMapper {

    Articulo toArticulo(ArticuloRequestDTO articuloRequestDTO);
    @Mapping(source = "categoria.id", target = "categoriaId")




    ArticuloResponseDTO toArticuloResponseDTO(Articulo articulo);  // categoria.get(id) no se mapea con categoriaId
    List<ArticuloResponseDTO> toArticuloResponseDTOList(List<Articulo> articulos);

}
