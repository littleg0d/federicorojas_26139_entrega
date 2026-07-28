package com.proyecto.backend.repositories;

import com.proyecto.backend.model.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Long> {

     boolean existsByCategoriaId(Long id);
     List<Articulo> findAllByCategoriaId(Long id);
}
