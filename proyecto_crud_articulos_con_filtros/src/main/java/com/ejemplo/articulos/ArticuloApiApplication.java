package com.ejemplo.articulos; // Paquete base de la aplicación

// Importamos la clase SpringApplication, que permite arrancar la app
import org.springframework.boot.SpringApplication;
// Importamos la anotación SpringBootApplication, que marca el punto de entrada
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Spring Boot.
 * Es el punto de entrada del programa y contiene el método main.
 */
@SpringBootApplication // Indica a Spring Boot que esta es la clase de configuración principal
public class ArticuloApiApplication { // Declaración de la clase pública

    /**
     * Método main: punto de entrada estándar de una aplicación Java.
     * Desde aquí se inicia el contexto de Spring y el servidor embebido.
     */
    public static void main(String[] args) { // Declaración del método main con argumentos de línea de comandos
        // Llamamos a run para iniciar la aplicación Spring Boot
        SpringApplication.run(ArticuloApiApplication.class, args); // Ejecuta la app levantando el contexto y el servidor
    } // Fin del método main
} // Fin de la clase ArticuloApiApplication
