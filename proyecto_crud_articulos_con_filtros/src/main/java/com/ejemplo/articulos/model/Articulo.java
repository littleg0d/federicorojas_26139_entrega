package com.ejemplo.articulos.model; // Paquete donde se encuentra la entidad Articulo

// Importamos todas las anotaciones de JPA con jakarta.persistence
import jakarta.persistence.Entity;       // Marca la clase como entidad JPA
import jakarta.persistence.Table;        // Permite personalizar el nombre de la tabla
import jakarta.persistence.Id;           // Indica el campo que será clave primaria
import jakarta.persistence.GeneratedValue; // Indica que el valor será generado automáticamente
import jakarta.persistence.GenerationType; // Tipo de estrategia de generación de la clave primaria
import jakarta.persistence.Column;       // Permite personalizar el nombre y propiedades de columnas

/**
 * Clase de dominio que representa a un Articulo en el sistema.
 * Esta clase se mapea a una tabla de base de datos usando JPA.
 */
@Entity // Indica que esta clase es una entidad persistente
@Table(name = "articulo") // Nombre de la tabla asociada en la base de datos
public class Articulo { // Declaración de la clase pública Articulo

    @Id // Indica que el campo id es la clave primaria de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Usa autoincremental de la base de datos (IDENTITY)
    @Column(name = "id") // Nombre de la columna en la tabla (opcional, aquí solo lo explicitamos)
    private Long id; // Identificador único del articulo

    @Column(name = "nombre", nullable = false, length = 200) // Columna para el nombre, obligatorio, largo máximo 200
    private String nombre; // Nombre o descripción corta del artículo

    @Column(name = "precio", nullable = false) // Columna para el precio, obligatorio
    private Double precio; // Precio del artículo

    @Column(name = "imagen", nullable = true, length = 500) // Columna para la URL o ruta de la imagen (puede ser null)
    private String imagen; // URL o ruta de la imagen representativa del artículo

    /**
     * Constructor vacío requerido por JPA.
     * Es obligatorio para que el framework pueda instanciar la entidad.
     */
    public Articulo() { // Constructor por defecto sin parámetros
        // No hace nada explícitamente, pero es necesario que exista
    } // Fin del constructor vacío

    /**
     * Constructor completo para crear instancias de Articulo fácilmente en el código.
     * No es usado por JPA directamente, pero es útil para nuestra lógica de negocio.
     */
    public Articulo(Long id, String nombre, Double precio, String imagen) { // Constructor con todos los campos
        this.id = id;           // Asignamos el parámetro id al atributo id de la clase
        this.nombre = nombre;   // Asignamos el parámetro nombre al atributo nombre
        this.precio = precio;   // Asignamos el parámetro precio al atributo precio
        this.imagen = imagen;   // Asignamos el parámetro imagen al atributo imagen
    } // Fin del constructor con parámetros

    // =========================
    // Getters y Setters
    // =========================

    // Getter del campo id
    public Long getId() { // Devuelve el valor actual de id
        return id; // Retorna el valor almacenado en el atributo id
    } // Fin del getter de id

    // Setter del campo id
    public void setId(Long id) { // Recibe un nuevo valor para el atributo id
        this.id = id; // Asigna el valor recibido al atributo id
    } // Fin del setter de id

    // Getter del campo nombre
    public String getNombre() { // Devuelve el valor actual del atributo nombre
        return nombre; // Retorna el valor almacenado en nombre
    } // Fin del getter de nombre

    // Setter del campo nombre
    public void setNombre(String nombre) { // Recibe un nuevo valor para el atributo nombre
        this.nombre = nombre; // Asigna el valor recibido al atributo nombre
    } // Fin del setter de nombre

    // Getter del campo precio
    public Double getPrecio() { // Devuelve el valor actual del atributo precio
        return precio; // Retorna el valor almacenado en precio
    } // Fin del getter de precio

    // Setter del campo precio
    public void setPrecio(Double precio) { // Recibe un nuevo valor para el atributo precio
        this.precio = precio; // Asigna el valor recibido al atributo precio
    } // Fin del setter de precio

    // Getter del campo imagen
    public String getImagen() { // Devuelve el valor actual del atributo imagen
        return imagen; // Retorna la URL o ruta de la imagen del artículo
    } // Fin del getter de imagen

    // Setter del campo imagen
    public void setImagen(String imagen) { // Recibe un nuevo valor para el atributo imagen
        this.imagen = imagen; // Asigna el valor recibido al atributo imagen
    } // Fin del setter de imagen
} // Fin de la clase Articulo
