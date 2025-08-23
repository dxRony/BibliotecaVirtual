package com.mycompany.bibliotecavirtual.Objects;

public class Reporte {

    private Biblioteca biblioteca;

    public Reporte(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public void mostrarReporteCompleto() {
        System.out.println(
                "------------------------------------------- Reporte completo de libros ------------------------------------------------------------");
        System.out.printf("%-37s %-38s %-24s %-6s %-12s %-10s\n",
                "ID", "Título", "Autor", "Año", "Disponible", "Préstamos");
        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------------------------");

        biblioteca.getLibros().values().stream()
                .sorted((l1, l2) -> l1.getTitulo().compareToIgnoreCase(l2.getTitulo()))
                .forEach(libro -> System.out.printf(
                        "%-37s %-38s %-24s %-6d %-12s %-10d\n",
                        libro.getId(),
                        libro.getTitulo(),
                        libro.getAutor(),
                        libro.getAnioPublicacion(),
                        libro.isDisponibilidad() ? "Sí" : "No",
                        libro.getCantidadPrestamos()));

        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------------------------");
    }

    public void mostrarLibrosDisponibles() {
        System.out.println(
                "------------------------------------------- Reporte de libros disponibles ---------------------------------------------------------");
        System.out.printf("%-37s %-38s %-24s %-6s %-12s %-10s\n",
                "ID", "Título", "Autor", "Año", "Disponible", "Préstamos");
        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------------------------");

        biblioteca.getLibros().values().stream()
                .filter(Libro::isDisponibilidad)
                .sorted((l1, l2) -> l1.getTitulo().compareToIgnoreCase(l2.getTitulo()))
                .forEach(libro -> System.out.printf(
                        "%-37s %-38s %-24s %-6d %-12s %-10d\n",
                        libro.getId(),
                        libro.getTitulo(),
                        libro.getAutor(),
                        libro.getAnioPublicacion(),
                        libro.isDisponibilidad() ? "Sí" : "No",
                        libro.getCantidadPrestamos()));

        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------------------------");
    }

    public void mostrarLibrosPrestados() {
        System.out.println(
                "------------------------------------------- Reporte de libros prestados -----------------------------------------------------------");
        System.out.printf("%-37s %-38s %-24s %-6s %-12s %-10s\n",
                "ID", "Título", "Autor", "Año", "Disponible", "Préstamos");
        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------------------------");

        biblioteca.getLibros().values().stream()
                .filter(libro -> !libro.isDisponibilidad())
                .sorted((l1, l2) -> l1.getTitulo().compareToIgnoreCase(l2.getTitulo()))
                .forEach(libro -> System.out.printf(
                        "%-37s %-38s %-24s %-6d %-12s %-10d\n",
                        libro.getId(),
                        libro.getTitulo(),
                        libro.getAutor(),
                        libro.getAnioPublicacion(),
                        libro.isDisponibilidad() ? "Sí" : "No",
                        libro.getCantidadPrestamos()));

        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------------------------");
    }

    public void mostrarHistorialCompleto() {
        System.out.println(
                "------------------------------------------- Reporte de historial completo -----------------------------------------------------------");
        System.out.printf("%-37s %-38s %-24s %-6s\n",
                "ID Libro", "Usuario", "Accion", "Fecha");
        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------------------------");
        biblioteca.getPrestamos().forEach(prestamo -> System.out.printf(
                "%-37s %-38s %-24s %-6s\n",
                prestamo.getIdLibro(),
                prestamo.getUsuario(),
                prestamo.getAccion(),
                prestamo.getFecha().toString()));
        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------------------------");
    }

    public void mostrarOpcionesMenuReportes() {
        System.out.println("\n=================== Reportes ===================");
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
