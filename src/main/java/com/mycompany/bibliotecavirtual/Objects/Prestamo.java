package com.mycompany.bibliotecavirtual.Objects;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Prestamo implements Serializable {

    private String idLibro;
    private String usuario;
    private String accion;
    private LocalDateTime fecha;

    public Prestamo() {
    }

    public Prestamo(String idLibro, String usuario, String accion, LocalDateTime fecha) {
        this.idLibro = idLibro;
        this.usuario = usuario;
        this.accion = accion;
        this.fecha = fecha;
    }

    public String getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(String idLibro) {
        this.idLibro = idLibro;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Prestamo [idLibro=" + idLibro + ", usuario=" + usuario + ", accion=" + accion + ", fecha=" + fecha
                + "]";
    }

}
