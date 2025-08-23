package com.mycompany.bibliotecavirtual.Objects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mycompany.bibliotecavirtual.utils.Archivo;

public class Biblioteca implements Serializable {

    private Map<String, Libro> libros;
    private List<Prestamo> prestamos;

    public Biblioteca() {
        this.libros = new HashMap<>();
        this.prestamos = new ArrayList<>();
    }

    public void registrarLibro(Libro libro) {

        libros.put(libro.getId(), libro);

    }

    public void realizarPrestamo(Prestamo prestamo) {

        prestamos.add(prestamo);

    }

    public void cargarLibrosDesdeArchivo() {
        File archivo = Archivo.seleccionarArchivoConsola();
        if (archivo == null) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int contadorLibros = 0;
            System.out.println();
            while ((linea = br.readLine()) != null) {

                String[] partes = linea.split(",");

                if (partes.length < 5) {
                    System.out.println("Datos incompletos, en la linea: " + linea);
                    continue;
                }

                String id = partes[0].trim();
                String titulo = partes[1].trim();
                String autor = partes[2].trim();

                int anio;
                try {
                    anio = Integer.parseInt(partes[3].trim());
                    if (anio <= 0) {
                        System.out.println("Año de publicacion negativo, en la linea: " + linea);
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error en año de publicacion, en la linea: " + linea);
                    continue;
                }

                boolean disponibilidad;
                try {
                    disponibilidad = Boolean.parseBoolean(partes[4].trim());
                } catch (Exception e) {
                    System.out.println("Disponibilidad no booleana, en la linea: " + linea);
                    continue;
                }

                if (libros.containsKey(id)) {
                    System.out.println("Libro con ID repetido, en la linea: " + linea);
                    continue;
                }

                Libro libro = new Libro(id, titulo, autor, anio, disponibilidad);
                libros.put(libro.getId(), libro);
                contadorLibros++;
            }
            System.out.println("\nFin de carga de libros, total de libros cargados: " + contadorLibros);

        } catch (IOException e) {
            System.out.println("Error leyendo el archivo: " + e.getMessage());
        }

    }

    public Libro obtenerLibroID(String idLibro) {

        Libro libro = libros.get(idLibro);
        if (libro == null) {
            System.out.println("Libro no encontrado.");
            return null;
        }
        return libro;

    }

    public Map<String, Libro> getLibros() {
        return libros;
    }

    public void setLibros(Map<String, Libro> libros) {
        this.libros = libros;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

}
