package com.mycompany.bibliotecavirtual.Objetos;

import java.util.LinkedList;

public class Biblioteca {

    private LinkedList<Libro> libros;
    private LinkedList<Prestamo> prestamos;

    public Biblioteca() {
    }

    public Biblioteca(LinkedList<Libro> libros, LinkedList<Prestamo> prestamos) {
        this.libros = libros;
        this.prestamos = prestamos;
    }

    public LinkedList<Libro> getLibros() {
        return libros;
    }

    public void setLibros(LinkedList<Libro> libros) {
        this.libros = libros;
    }

    public LinkedList<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(LinkedList<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

}
