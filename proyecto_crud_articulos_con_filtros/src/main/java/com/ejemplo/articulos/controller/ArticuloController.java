package com.ejemplo.articulos.controller; // Paquete donde se define el controlador REST

// Importamos la entidad Articulo para usarla como tipo de dato en las respuestas
import com.ejemplo.articulos.model.Articulo;
// Importamos la interfaz de servicio que contiene la lógica de negocio
import com.ejemplo.articulos.service.ArticuloService;
// Importamos ResponseEntity para construir respuestas HTTP más expresivas
import org.springframework.http.ResponseEntity;
// Importamos anotaciones de Spring MVC para mapear rutas y parámetros
import org.springframework.web.bind.annotation.*; // Importamos todas las anotaciones REST

import java.util.List; // Usamos List para devolver colecciones de artículos

/**
 * Controlador REST que expone endpoints para gestionar Articulos.
 * Recibe las solicitudes HTTP y delega la lógica en la capa de servicio.
 */
@CrossOrigin(origins = "*") // Permite solicitudes desde cualquier origen (útil para pruebas con frontends locales)
@RestController // Indica que esta clase es un controlador REST (retorna JSON por defecto)
@RequestMapping("/api/articulos") // Prefijo común para todas las rutas de este controlador
public class ArticuloController { // Declaración de la clase pública del controlador

    // Atributo que hace referencia al servicio de Articulo
    private final ArticuloService articuloService; // Dependencia hacia la capa de servicio

    /**
     * Constructor que recibe e inyecta la implementación de ArticuloService.
     * Spring se encarga de proporcionar la instancia concreta.
     */
    public ArticuloController(ArticuloService articuloService) { // Constructor con inyección de dependencias
        this.articuloService = articuloService; // Asignamos el servicio recibido al atributo interno
    } // Fin del constructor

    // ======================
    // Endpoints CRUD básicos
    // ======================

    /**
     * Endpoint GET para listar todos los artículos.
     * URL: GET /api/articulos
     */
    @GetMapping // Indica que este método responde a solicitudes HTTP GET en la ruta base
    public List<Articulo> listar() { // Devuelve una lista de Articulo en formato JSON
        return articuloService.listarArticulos(); // Delegamos al servicio la obtención de todos los artículos
    } // Fin de listar

    /**
     * Endpoint GET para obtener un artículo por su ID.
     * URL: GET /api/articulos/{id}
     */
    @GetMapping("/{id}") // Indica que la ruta incluye un parámetro de path llamado id
    public ResponseEntity<Articulo> obtenerPorId(@PathVariable Long id) { // @PathVariable vincula el id de la URL con el parámetro
        return articuloService.obtenerArticuloPorId(id) // Llamamos al servicio para buscar el artículo
                .map(ResponseEntity::ok) // Si el artículo existe, devolvemos 200 OK con el cuerpo
                .orElse(ResponseEntity.notFound().build()); // Si no existe, devolvemos 404 Not Found
    } // Fin de obtenerPorId

    /**
     * Endpoint POST para crear un nuevo artículo.
     * URL: POST /api/articulos
     */
    @PostMapping // Indica que este método responde a solicitudes HTTP POST en la ruta base
    public Articulo crear(@RequestBody Articulo articulo) { // @RequestBody indica que el JSON del cuerpo se mapea a un Articulo
        return articuloService.guardarArticulo(articulo); // Delegamos al servicio la lógica de guardado
    } // Fin de crear

    /**
     * Endpoint PUT para actualizar un artículo existente por ID.
     * URL: PUT /api/articulos/{id}
     */
    @PutMapping("/{id}") // Ruta que incluye el ID del artículo a actualizar
    public ResponseEntity<Articulo> actualizar(@PathVariable Long id, @RequestBody Articulo articulo) {
        // Primero verificamos si el artículo con ese ID existe
        if (articuloService.obtenerArticuloPorId(id).isEmpty()) { // Si no se encuentra, devolvemos 404
            return ResponseEntity.notFound().build(); // Construimos la respuesta 404 Not Found
        }
        // Si existe, actualizamos los datos usando el servicio
        Articulo actualizado = articuloService.actualizarArticulo(id, articulo); // Llamamos al servicio para actualizar
        return ResponseEntity.ok(actualizado); // Devolvemos 200 OK con el artículo actualizado
    } // Fin de actualizar

    /**
     * Endpoint DELETE para eliminar un artículo por ID.
     * URL: DELETE /api/articulos/{id}
     */
    @DeleteMapping("/{id}") // Ruta que incluye el ID del artículo a eliminar
    public ResponseEntity<Void> eliminar(@PathVariable Long id) { // Usamos ResponseEntity<Void> porque no devolvemos cuerpo
        // Verificamos si el artículo existe antes de eliminarlo
        if (articuloService.obtenerArticuloPorId(id).isEmpty()) { // Si no lo encontramos, devolvemos 404
            return ResponseEntity.notFound().build(); // Respuesta 404 Not Found
        }
        // Si existe, lo eliminamos usando el servicio
        articuloService.eliminarArticulo(id); // Delegamos al servicio la lógica de borrado
        return ResponseEntity.noContent().build(); // Devolvemos 204 No Content indicando que se eliminó correctamente
    } // Fin de eliminar

    // ======================
    // Endpoint de búsqueda con filtros
    // ======================

    /**
     * Endpoint GET para buscar artículos aplicando filtros opcionales.
     * URL base: GET /api/articulos/buscar
     *
     * Ejemplos de uso:
     * - /api/articulos/buscar?nombre=mouse
     * - /api/articulos/buscar?minPrecio=1000&maxPrecio=5000
     * - /api/articulos/buscar?nombre=monitor&minPrecio=20000&maxPrecio=80000
     */
    @GetMapping("/buscar") // Ruta específica para la búsqueda con filtros
    public List<Articulo> buscar(
            @RequestParam(required = false) String nombre,   // Parámetro de consulta opcional para filtrar por nombre
            @RequestParam(required = false) Double minPrecio, // Parámetro opcional para precio mínimo
            @RequestParam(required = false) Double maxPrecio  // Parámetro opcional para precio máximo
    ) { // Inicio del método buscar

        // Caso 1: nombre + rango de precios (los tres parámetros presentes)
        if (nombre != null && minPrecio != null && maxPrecio != null) { // Verificamos que los tres filtros tengan valor
            return articuloService.buscarPorNombreYPrecioEntre(nombre, minPrecio, maxPrecio); // Llamamos al servicio con ambos filtros
        }

        // Caso 2: solo nombre presente
        if (nombre != null && minPrecio == null && maxPrecio == null) { // Solo hay filtro de nombre
            return articuloService.buscarPorNombre(nombre); // Delegamos al servicio la búsqueda por nombre
        }

        // Caso 3: solo rango de precios presente (sin nombre)
        if (nombre == null && minPrecio != null && maxPrecio != null) { // Solo hay rango de precios
            return articuloService.buscarPorPrecioEntre(minPrecio, maxPrecio); // Llamamos al servicio con el rango
        }

        // Caso 4: solo precio mínimo presente
        if (minPrecio != null && maxPrecio == null && nombre == null) { // Solo hay filtro de precio mínimo
            return articuloService.buscarPorPrecioMinimo(minPrecio); // Llamamos al servicio con el mínimo
        }

        // Caso 5: solo precio máximo presente
        if (maxPrecio != null && minPrecio == null && nombre == null) { // Solo hay filtro de precio máximo
            return articuloService.buscarPorPrecioMaximo(maxPrecio); // Llamamos al servicio con el máximo
        }

        // Caso 6: si no se pasa ningún parámetro, devolvemos todos los artículos
        return articuloService.listarArticulos(); // Llamamos al servicio para listar todo
    } // Fin del método buscar
} // Fin de la clase ArticuloController
