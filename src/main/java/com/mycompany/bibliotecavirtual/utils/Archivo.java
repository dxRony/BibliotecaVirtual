package com.mycompany.bibliotecavirtual.utils;

import java.io.File;
import java.util.Scanner;

public class Archivo {
    private static Scanner scanner;
    
    public static File seleccionarArchivoConsola() {
        scanner = new Scanner(System.in);
        System.out.print("Ingrese la ruta del archivo CSV: ");
        String ruta = scanner.nextLine();
        File archivo = new File(ruta);

        if (!archivo.exists()) {
            System.out.println("archivo invalido.");
            return null;
        }
        return archivo;
    }
}
