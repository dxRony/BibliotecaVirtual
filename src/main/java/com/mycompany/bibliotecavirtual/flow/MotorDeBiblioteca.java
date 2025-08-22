package com.mycompany.bibliotecavirtual.flow;

import java.util.Scanner;

import com.mycompany.bibliotecavirtual.Objects.Biblioteca;
//import com.mycompany.bibliotecavirtual.Objects.Reporte;

public class MotorDeBiblioteca {

    private boolean finalizarEjecucion;
    // private Reporte reporte;
    private Biblioteca biblioteca;
    private Scanner scanner;

    public MotorDeBiblioteca() {
        finalizarEjecucion = false;
        this.biblioteca = new Biblioteca();
        // this.reporte = new Reporte();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenuPrincipal() {

        while (!finalizarEjecucion) {
            this.mostrarOpcionesMenuPrincipal();
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
                    biblioteca.registrarLibro();
                    break;
                case 2:
                    biblioteca.realizarPrestamo();
                    break;
                case 3:
                    biblioteca.getPrestamos().forEach(System.out::println);
                    biblioteca.cargarLibrosDesdeArchivo();
                    break;
                case 4:
                    this.mostrarMenuReportes();
                    biblioteca.getLibros().values().forEach(System.out::println);
                    break;
                case 5:
                    finalizarEjecucion = true;
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

    private void mostrarOpcionesMenuPrincipal() {
        System.out.println("\n====== Biblioteca Virtual ======");
        System.out.println("1. Agregar Libro.");
        System.out.println("2. Realizar Prestamo.");
        System.out.println("3. Cargar Libros desde Archivo.csv.");
        System.out.println("4. Ver Reportes.");
        System.out.println("5. Salir.");
    }

    private void mostrarMenuReportes() {
        int opcionMenuReporte = 0;
        do {
            this.mostrarOpcionesMenuReportes();
            try {
                System.out.print("\nSeleccione una opcion: ");
                opcionMenuReporte = scanner.nextInt();
                switch (opcionMenuReporte) {
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

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
            } catch (Exception e) {
                System.out.println("Error al leer la opcion. Intente nuevamente.");
                scanner.nextLine();
                continue;
            }
        } while (opcionMenuReporte != 9);

    }

    private void mostrarOpcionesMenuReportes() {
        System.out.println("\n====== Reportes ======");
        System.out.println("1. Reporte completo de libros.");
        System.out.println("2. Reporte de libros disponibles.");
        System.out.println("3. Reporte de libros prestados.");
        System.out.println("4. Reporte de historial completo.");
        System.out.println("5. Reporte de historial filtrado por libro.");
        System.out.println("6. Reporte de historial filtrado por usuario.");
        System.out.println("7. Reporte de frecuencia de prestamos por libro.");
        System.out.println("8. Reporte de libros nunca prestados.");
        System.out.println("9. Regresar al menu principal.");

    }

}
