package com.ejemplo.articulos.repository; // Paquete donde se define el repositorio de Articulo

// Importamos la clase Articulo que será gestionada por este repositorio
import com.ejemplo.articulos.model.Articulo;
// Importamos JpaRepository, interfaz de Spring Data que provee operaciones CRUD genéricas
import org.springframework.data.jpa.repository.JpaRepository;
// Importamos la anotación Repository para marcar la interfaz como componente de acceso a datos
import org.springframework.stereotype.Repository;

import java.util.List; // Importamos la interfaz List para manejar colecciones de Articulo

/**
 * Interfaz de repositorio para la entidad Articulo.
 * Extiende JpaRepository para obtener métodos CRUD listos para usar.
 */
@Repository // Indica que esta interfaz es un componente de acceso a datos (DAO/Repository)
public interface ArticuloRepository extends JpaRepository<Articulo, Long> { // Especifica la entidad y el tipo de su ID

    // ==============================
    // Métodos personalizados
    // ==============================

    /**
     * Busca artículos cuyo nombre contenga el texto indicado (búsqueda parcial),
     * ignorando mayúsculas y minúsculas.
     */
    List<Articulo> findByNombreContainingIgnoreCase(String nombre); // Devuelve artículos cuyo nombre contiene el texto

    /**
     * Busca artículos con un precio mayor o igual al valor indicado.
     */
    List<Articulo> findByPrecioGreaterThanEqual(Double precioMinimo); // Devuelve artículos con precio >= precioMinimo

    /**
     * Busca artículos con un precio menor o igual al valor indicado.
     */
    List<Articulo> findByPrecioLessThanEqual(Double precioMaximo); // Devuelve artículos con precio <= precioMaximo

    /**
     * Busca artículos con precio entre dos valores (inclusive).
     */
    List<Articulo> findByPrecioBetween(Double precioMinimo, Double precioMaximo); // Devuelve artículos entre un rango

    /**
     * Busca artículos filtrando simultáneamente por texto en el nombre
     * y por un rango de precios.
     */
    List<Articulo> findByNombreContainingIgnoreCaseAndPrecioBetween( // Combina filtro por nombre y rango de precio
            String nombre,        // Texto que debe aparecer en el nombre
            Double precioMinimo,  // Límite inferior del precio
            Double precioMaximo   // Límite superior del precio
    ); // Fin de la declaración del método
} // Fin de la interfaz ArticuloRepository
