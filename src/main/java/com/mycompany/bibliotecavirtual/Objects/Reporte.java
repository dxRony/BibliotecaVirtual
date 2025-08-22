package com.mycompany.bibliotecavirtual.Objects;

import java.util.LinkedList;

public class Reporte {

    private LinkedList<Libro> reporteLibros;
    private LinkedList<Prestamo> reportePrestamos;

    public Reporte() {
    }

    public Reporte(LinkedList<Libro> reporteLibros, LinkedList<Prestamo> reportePrestamos) {
        this.reporteLibros = reporteLibros;
        this.reportePrestamos = reportePrestamos;
    }

    public LinkedList<Libro> getReporteLibros() {
        return reporteLibros;
    }

    public void setReporteLibros(LinkedList<Libro> reporteLibros) {
        this.reporteLibros = reporteLibros;
    }

    public LinkedList<Prestamo> getReportePrestamos() {
        return reportePrestamos;
    }

    public void setReportePrestamos(LinkedList<Prestamo> reportePrestamos) {
        this.reportePrestamos = reportePrestamos;
    }

}
