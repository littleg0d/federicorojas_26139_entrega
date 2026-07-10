package com.ejemplo.articulos.service; // Paquete donde se implementa la lógica de negocio

// Importamos la entidad Articulo
import com.ejemplo.articulos.model.Articulo;
// Importamos el repositorio que accede a la base de datos
import com.ejemplo.articulos.repository.ArticuloRepository;
// Importamos la anotación Service para marcar esta clase como servicio de Spring
import org.springframework.stereotype.Service;

import java.util.List;     // List se usa para manejar colecciones de Articulo
import java.util.Optional; // Optional se usa para manejar posibles valores nulos de forma segura

/**
 * Implementación concreta de la interfaz ArticuloService.
 * Aquí se orquesta la lógica de negocio usando el repositorio.
 */
@Service // Indica a Spring que esta clase es un servicio y debe ser gestionada como bean
public class ArticuloServiceImpl implements ArticuloService { // La clase implementa la interfaz ArticuloService

    // Atributo privado que referencia al repositorio de Articulo
    private final ArticuloRepository articuloRepository; // Dependencia hacia la capa de acceso a datos

    /**
     * Constructor que recibe el repositorio como dependencia.
     * Spring inyectará automáticamente una instancia de ArticuloRepository.
     */
    public ArticuloServiceImpl(ArticuloRepository articuloRepository) { // Constructor con inyección de dependencias
        this.articuloRepository = articuloRepository; // Asignamos el repositorio recibido al atributo interno
    } // Fin del constructor

    // ======================
    // Implementación de métodos CRUD
    // ======================

    @Override // Indica que estamos sobrescribiendo un método de la interfaz
    public List<Articulo> listarArticulos() { // Devuelve una lista con todos los artículos
        return articuloRepository.findAll(); // Delegamos en el repositorio la búsqueda de todos los registros
    } // Fin de listarArticulos

    @Override // Implementación del método para obtener un artículo por ID
    public Optional<Articulo> obtenerArticuloPorId(Long id) { // Recibe el ID del artículo a buscar
        return articuloRepository.findById(id); // Devuelve un Optional con el artículo si existe
    } // Fin de obtenerArticuloPorId

    @Override // Implementación del método para guardar un nuevo artículo
    public Articulo guardarArticulo(Articulo articulo) { // Recibe el artículo a guardar
        return articuloRepository.save(articulo); // Usa save para insertar el registro en la base de datos
    } // Fin de guardarArticulo

    @Override // Implementación del método para actualizar un artículo existente
    public Articulo actualizarArticulo(Long id, Articulo articulo) { // Recibe el ID y los nuevos datos del artículo
        articulo.setId(id); // Aseguramos que el objeto tenga el ID correcto que queremos actualizar
        return articuloRepository.save(articulo); // save actualiza el registro cuando el ID ya existe
    } // Fin de actualizarArticulo

    @Override // Implementación del método para eliminar un artículo por ID
    public void eliminarArticulo(Long id) { // Recibe el ID del artículo a eliminar
        articuloRepository.deleteById(id); // Delegamos en el repositorio la eliminación del registro
    } // Fin de eliminarArticulo

    // ======================
    // Implementación de métodos de búsqueda con filtros
    // ======================

    @Override // Implementación del filtro por nombre
    public List<Articulo> buscarPorNombre(String nombre) { // Recibe texto a buscar dentro del nombre
        return articuloRepository.findByNombreContainingIgnoreCase(nombre); // Llama al método del repositorio
    } // Fin de buscarPorNombre

    @Override // Implementación del filtro por precio mínimo
    public List<Articulo> buscarPorPrecioMinimo(Double precioMinimo) { // Recibe el valor mínimo de precio
        return articuloRepository.findByPrecioGreaterThanEqual(precioMinimo); // Devuelve artículos con precio >= mínimo
    } // Fin de buscarPorPrecioMinimo

    @Override // Implementación del filtro por precio máximo
    public List<Articulo> buscarPorPrecioMaximo(Double precioMaximo) { // Recibe el valor máximo de precio
        return articuloRepository.findByPrecioLessThanEqual(precioMaximo); // Devuelve artículos con precio <= máximo
    } // Fin de buscarPorPrecioMaximo

    @Override // Implementación del filtro por rango de precios
    public List<Articulo> buscarPorPrecioEntre(Double precioMinimo, Double precioMaximo) { // Recibe límites del rango
        return articuloRepository.findByPrecioBetween(precioMinimo, precioMaximo); // Devuelve artículos dentro del rango
    } // Fin de buscarPorPrecioEntre

    @Override // Implementación del filtro combinado por nombre y rango de precio
    public List<Articulo> buscarPorNombreYPrecioEntre(String nombre, Double precioMinimo, Double precioMaximo) {
        // Llama al método del repositorio que combina el filtro de nombre parcial y el rango de precios
        return articuloRepository.findByNombreContainingIgnoreCaseAndPrecioBetween(nombre, precioMinimo, precioMaximo);
    } // Fin de buscarPorNombreYPrecioEntre
} // Fin de la clase ArticuloServiceImpl
