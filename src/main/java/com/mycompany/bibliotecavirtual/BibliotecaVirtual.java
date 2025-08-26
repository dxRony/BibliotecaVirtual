/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bibliotecavirtual;

import java.io.File;

import com.mycompany.bibliotecavirtual.flow.MotorDeBiblioteca;

/**
 *
 * @author ronyrojas
 */
public class BibliotecaVirtual {

    public static void main(String[] args) {
        String baseDir = System.getProperty("user.dir");
        String folderPath = baseDir + File.separator + "persistencia";
        new File(folderPath).mkdirs();

        MotorDeBiblioteca motorDeBiblioteca = new MotorDeBiblioteca(folderPath);
        motorDeBiblioteca.mostrarMenuPrincipal();
    }
}
