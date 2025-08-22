package com.mycompany.bibliotecavirtual.Objects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.mycompany.bibliotecavirtual.utils.Archivo;

public class Biblioteca {

    private Map<String, Libro> libros;
    private List<Prestamo> prestamos;
    private Scanner scanner;

    public Biblioteca() {
        this.libros = new HashMap<>();
        this.prestamos = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void registrarLibro() {
        System.out.println("--- Registro de Libro ---");

        String titulo = obtenerTituloLibro();
        String autor = obtenerAutorLibro();
        int anioPublicacion = obtenerAnioPublicacionLibro();

        Libro libro = new Libro(titulo, autor, anioPublicacion);
        libros.put(libro.getId(), libro);
        System.out.println("\nLibro registrado con exito.");
        System.out.println("--- Fin del Registro de Libro ---");
        System.out.println("presione enter para continuar...");
        scanner.nextLine();

    }

    public void realizarPrestamo() {
        System.out.println("--- Registro de Prestamo ---");

        String idLibro = obtenerUUIDLibro();
        Libro libro = libros.get(idLibro);

        if (libro == null) {
            System.out.println("Libro no encontrado.");
            return;
        }
        String accion = obtenerAccionPrestamo();
        String usuario = "";
        if (accion.equals("Prestado")) {
            if (!libro.isDisponibilidad()) {
                System.out.println("El libro ya esta prestado.");
                return;
            }
            usuario = obtenerUsuarioPrestador();
            libro.setDisponibilidad(false);
            libro.setCantidadPrestamos(libro.getCantidadPrestamos() + 1);

        } else if (accion.equals("Devuelto")) {
            if (libro.isDisponibilidad()) {
                System.out.println("El libro no esta prestado.");
                return;

            }
            libro.setDisponibilidad(true);
        }
        LocalDateTime fecha = LocalDateTime.now();

        Prestamo prestamo = new Prestamo(idLibro, usuario, accion, fecha);
        prestamos.add(prestamo);
        System.out.println("\nPrestamo registrado con exito.");
        System.out.println("--- Fin del Registro de Prestamo ---");
        System.out.println("presione enter para continuar...");
        scanner.nextLine();

    }

    public void cargarLibrosDesdeArchivo() {
        File archivo = Archivo.seleccionarArchivoConsola(scanner);
        if (archivo == null) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int contadorLibros = 0;

            while ((linea = br.readLine()) != null) {

                String[] partes = linea.split(",");

                if (partes.length < 5) {
                    System.out.println("Datos de libro incompletos: " + linea);
                    continue;
                }

                String id = partes[0].trim();
                String titulo = partes[1].trim();
                String autor = partes[2].trim();

                int anio;
                try {
                    anio = Integer.parseInt(partes[3].trim());                   
                    if (anio <= 0) {
                        System.out.println("Año de publicacion negativo: " + partes[3]);
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error en año de publicacion: " + partes[3]);
                    continue;
                }

                boolean disponibilidad;
                try {
                    disponibilidad = Boolean.parseBoolean(partes[4].trim());
                } catch (Exception e) {
                    System.out.println("Disponibilidad no booleana: " + partes[4]);
                    continue;
                }

                if (libros.containsKey(id)) {
                    System.out.println("Libro con ID " + id + " repetido.");
                    continue;
                }

                Libro libro = new Libro(id, titulo, autor, anio, disponibilidad);
                libros.put(libro.getId(), libro);
                contadorLibros++;
            }

            System.out.println("Fin carga de libros, total libros cargados: " + contadorLibros);

        } catch (IOException e) {
            System.out.println("Error leyendo el archivo: " + e.getMessage());
        }

    }

    private String obtenerTituloLibro() {
        String titulo;
        do {
            System.out.println("Ingrese el titulo del libro: ");
            titulo = scanner.nextLine().trim();
            if (titulo.isEmpty()) {
                System.out.println("Debe ingresar el titulo del libro. Intente nuevamente.");

            }
        } while (titulo.isEmpty());
        return titulo;
    }

    private String obtenerAutorLibro() {
        String autor;
        do {
            System.out.println("Ingrese el autor del libro: ");
            autor = scanner.nextLine().trim();
            if (autor.isEmpty()) {
                System.out.println("Debe ingresar el autor del libro. Intente nuevamente.");
            }
        } while (autor.isEmpty());
        return autor;
    }

    private int obtenerAnioPublicacionLibro() {
        int anioPublicacion;
        while (true) {
            System.out.println("Ingrese el año de publicacion del libro: ");
            try {
                anioPublicacion = Integer.parseInt(scanner.nextLine().trim());
                if (anioPublicacion <= 0) {
                    System.out.println("El año de publicacion debe ser un numero positivo. Intente nuevamente.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("El año de publicacion debe ser un numero entero. Intente nuevamente.");
            }
        }
        return anioPublicacion;
    }

    private String obtenerUsuarioPrestador() {
        String usuario;
        do {
            System.out.println("Ingrese el usuario que prestara el libro: ");
            usuario = scanner.nextLine().trim();
            if (usuario.isEmpty()) {
                System.out.println("Debe ingresar el usuario que prestara el libro. Intente nuevamente.");

            }
        } while (usuario.isEmpty());
        return usuario;
    }

    private String obtenerUUIDLibro() {
        String UUID;
        do {
            System.out.println("Ingrese el UUID del libro: ");
            UUID = scanner.nextLine().trim();
            if (UUID.isEmpty()) {
                System.out.println("Debe ingresar el UUID del libro. Intente nuevamente.");

            }
        } while (UUID.isEmpty());
        return UUID;
    }

    private String obtenerAccionPrestamo() {
        int accion = 0;
        do {
            System.out.println("Opciones de accion de prestamo:");
            System.out.println("1. Prestar.");
            System.out.println("2. Devolver.");
            System.out.print("Seleccione una accion: ");
            try {
                accion = Integer.parseInt(scanner.nextLine().trim());
                if (accion != 1 && accion != 2) {
                    System.out.println("Opcion invalida, debe seleccionar 1 o 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida, debe ingresar un numero entre 1 o 2.");
            }

        } while (accion != 1 && accion != 2);
        return accion == 1 ? "Prestado" : "Devuelto";
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
