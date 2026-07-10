package com.ejemplo.articulos.service; // Paquete donde se define la capa de servicio

// Importamos la entidad Articulo que será manejada por el servicio
import com.ejemplo.articulos.model.Articulo;

import java.util.List;    // List se usa para devolver listas de artículos
import java.util.Optional; // Optional se usa para evitar null al buscar por ID

/**
 * Interfaz que define el contrato del servicio de Articulo.
 * Aquí solo declaramos qué operaciones ofrece la capa de negocio.
 */
public interface ArticuloService { // Declaración de la interfaz pública

    // ======================
    // Métodos CRUD básicos
    // ======================

    /**
     * Devuelve la lista completa de artículos almacenados.
     */
    List<Articulo> listarArticulos(); // Lista todos los registros de Articulo

    /**
     * Busca un artículo por su identificador único.
     */
    Optional<Articulo> obtenerArticuloPorId(Long id); // Devuelve un Optional con el artículo si existe

    /**
     * Guarda un nuevo artículo en la base de datos.
     */
    Articulo guardarArticulo(Articulo articulo); // Inserta un nuevo artículo

    /**
     * Actualiza un artículo existente identificado por su ID.
     */
    Articulo actualizarArticulo(Long id, Articulo articulo); // Actualiza los datos de un artículo existente

    /**
     * Elimina un artículo por su ID.
     */
    void eliminarArticulo(Long id); // Elimina un artículo dado su identificador

    // ======================
    // Métodos de búsqueda con filtros
    // ======================

    /**
     * Busca artículos cuyo nombre contenga el texto indicado.
     */
    List<Articulo> buscarPorNombre(String nombre); // Filtro por nombre parcial

    /**
     * Busca artículos con un precio mayor o igual al valor mínimo.
     */
    List<Articulo> buscarPorPrecioMinimo(Double precioMinimo); // Filtro por precio mínimo

    /**
     * Busca artículos con un precio menor o igual al valor máximo.
     */
    List<Articulo> buscarPorPrecioMaximo(Double precioMaximo); // Filtro por precio máximo

    /**
     * Busca artículos con precio dentro de un rango dado.
     */
    List<Articulo> buscarPorPrecioEntre(Double precioMinimo, Double precioMaximo); // Filtro por rango de precios

    /**
     * Busca artículos que coincidan con un nombre parcial y además estén dentro de un rango de precios.
     */
    List<Articulo> buscarPorNombreYPrecioEntre(String nombre, Double precioMinimo, Double precioMaximo); // Filtro combinado
} // Fin de la interfaz ArticuloService
