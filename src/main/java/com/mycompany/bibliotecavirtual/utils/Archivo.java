package com.mycompany.bibliotecavirtual.utils;

import java.io.File;
import java.util.Scanner;

public class Archivo {
    public static File seleccionarArchivoConsola(Scanner scanner) {
        System.out.print("Ingrese la ruta del archivo CSV: ");
        String ruta = scanner.nextLine();
        File archivo = new File(ruta);

        if (!archivo.exists() || !archivo.isFile()) {
            System.out.println("archivo invalido.");
            return null;
        }
        return archivo;
    }
}
