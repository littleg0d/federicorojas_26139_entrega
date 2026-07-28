package com.proyecto.backend.exceptions;

public class    CategoriaEnUso extends RuntimeException{
    public CategoriaEnUso(String msg){
        super(msg);
    }
}
