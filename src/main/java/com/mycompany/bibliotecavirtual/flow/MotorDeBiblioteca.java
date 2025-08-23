package com.mycompany.bibliotecavirtual.flow;

import java.time.LocalDateTime;
import java.util.Scanner;

import com.mycompany.bibliotecavirtual.Objects.Biblioteca;
import com.mycompany.bibliotecavirtual.Objects.Libro;
import com.mycompany.bibliotecavirtual.Objects.Prestamo;
import com.mycompany.bibliotecavirtual.Objects.Reporte;
import com.mycompany.bibliotecavirtual.services.BibliotecaService;

public class MotorDeBiblioteca {

    private boolean finalizarEjecucion;
    private Reporte reporte;
    private BibliotecaService bibliotecaService;
    private Biblioteca biblioteca;
    private Scanner scanner;

    public MotorDeBiblioteca(String folderPath) {
        finalizarEjecucion = false;
        this.bibliotecaService = new BibliotecaService(folderPath);

        this.biblioteca = bibliotecaService.getBiblioteca();
        this.reporte = new Reporte(this.biblioteca);
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenuPrincipal() {

        while (!finalizarEjecucion) {
            biblioteca.mostrarOpcionesMenuPrincipal();
            System.out.print("\nSeleccione una opcion: ");
            String opcionStr = scanner.nextLine();
            int opcionMenuPrincipal;
            try {
                opcionMenuPrincipal = Integer.parseInt(opcionStr);
            } catch (NumberFormatException e) {
                System.out.println("Entrada no valida. Por favor, ingrese un numero.");
                continue;
            }

            switch (opcionMenuPrincipal) {
                case 1:
                    this.registrarLibro();
                    bibliotecaService.guardarBiblioteca();
                    break;
                case 2:
                    this.realizarPrestamo();
                    bibliotecaService.guardarBiblioteca();
                    break;
                case 3:
                    biblioteca.cargarLibrosDesdeArchivo();
                    bibliotecaService.guardarBiblioteca();
                    break;
                case 4:
                    this.mostrarMenuReportes();
                    break;
                case 5:
                    finalizarEjecucion = true;
                    bibliotecaService.guardarBiblioteca();
                    System.out.println("Saliendo de la biblioteca...");
                    System.out.println("Bye");
                    break;
                default:
                    System.out.println("Opcion incorrecta.");
                    break;
            }
        }
        scanner.close();
    }

    

    private void mostrarMenuReportes() {
        int opcionMenuReporte = 0;
        while (opcionMenuReporte != 9) {
            reporte.mostrarOpcionesMenuReportes();
            System.out.print("\nSeleccione una opcion: ");
            String opcionStr = scanner.nextLine();

            try {
                opcionMenuReporte = Integer.parseInt(opcionStr);
            } catch (NumberFormatException e) {
                System.out.println("Entrada no valida. Por favor, ingrese un numero.");
                continue;
            }
            switch (opcionMenuReporte) {
                case 1:
                    reporte.mostrarReporteCompleto();
                    break;
                case 2:
                    reporte.mostrarLibrosDisponibles();
                    break;
                case 3:
                    reporte.mostrarLibrosPrestados();
                    break;
                case 4:
                    reporte.mostrarHistorialCompleto();
                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

                    break;
                case 9:
                    System.out.println("Regresando al menu principal...");
                    break;
                default:
                    System.out.println("Opcion incorrecta.");
                    break;
            }
        }
    }

    private void registrarLibro() {
        System.out.println("--- Registro de Libro ---");

        String titulo = obtenerTituloLibro();
        String autor = obtenerAutorLibro();
        int anioPublicacion = obtenerAnioPublicacionLibro();

        Libro libro = new Libro(titulo, autor, anioPublicacion);
        biblioteca.registrarLibro(libro);
        System.out.println("\nLibro registrado con exito.");
        System.out.println("--- Fin del Registro de Libro ---");
        System.out.println("presione enter para continuar...");
        scanner.nextLine();

    }

    private void realizarPrestamo() {
        System.out.println("--- Registro de Prestamo ---");

        String idLibro = obtenerUUIDLibro();
        Libro libro = biblioteca.obtenerLibroID(idLibro);

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
        biblioteca.realizarPrestamo(prestamo);
        System.out.println("\nPrestamo registrado con exito.");
        System.out.println("--- Fin del Registro de Prestamo ---");
        System.out.println("presione enter para continuar...");
        scanner.nextLine();
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

}
