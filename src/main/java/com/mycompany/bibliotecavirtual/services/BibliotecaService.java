package com.mycompany.bibliotecavirtual.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.mycompany.bibliotecavirtual.Objects.Biblioteca;

public class BibliotecaService {

    private Biblioteca biblioteca;
    private final String filePath;

    public BibliotecaService(String folderPath) {
        this.filePath = folderPath + File.separator + "biblioteca.rr";
        this.cargarBiblioteca();
    }

    private void cargarBiblioteca() {
        String msj = "Creando nueva biblioteca.";
        final File file = new File(filePath);
        if (file.exists()) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
                final Object object = objectInputStream.readObject();
                if (object instanceof Biblioteca) {
                    this.biblioteca = (Biblioteca) object;
                    System.out.println("Datos de biblioteca recuperada");
                } else {
                    System.out.println("Los datos de la biblioteca no se pudieron recuperar.\n" + msj);
                    this.biblioteca = new Biblioteca();
                }
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al cargar el archivo: " + e.getMessage()+ "\n" + msj);
                this.biblioteca = new Biblioteca();
            }
        } else {
            System.out.println("No existe un archivo de persistencia.\n" + msj);
            this.biblioteca = new Biblioteca();
        }
    }

    public void guardarBiblioteca() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            objectOutputStream.writeObject(this.biblioteca);            
        } catch (IOException e) {
            System.err.println("Error guardando archivo: \n" + e.getMessage());
        }
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

}
