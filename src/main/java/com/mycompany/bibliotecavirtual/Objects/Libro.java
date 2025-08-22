package com.mycompany.bibliotecavirtual.Objects;

import java.util.UUID;

public class Libro {

    private String id;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private boolean disponibilidad;
    private int cantidadPrestamos;

    public Libro() {
    }

    //constructor para libros cargados desde archivo
    public Libro(String id, String titulo, String autor, int anioPublicacion, boolean disponibilidad) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.disponibilidad = disponibilidad;
        this.cantidadPrestamos = 0;
    }
    
    //constructor para libros guardados manualmente
    public Libro(String titulo, String autor, int anioPublicacion) {
        this.id = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.disponibilidad = true;
        this.cantidadPrestamos = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public int getCantidadPrestamos() {
        return cantidadPrestamos;
    }

    public void setCantidadPrestamos(int cantidadPrestamos) {
        this.cantidadPrestamos = cantidadPrestamos;
    }

    @Override
    public String toString() {
        return "Libro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", anioPublicacion=" + anioPublicacion
                + ", disponibilidad=" + disponibilidad + ", cantidadPrestamos=" + cantidadPrestamos + "]";
    }

    

}
