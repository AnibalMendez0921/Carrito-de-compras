/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Josue Gómez y Anibal Mendez
 */
public class UsuarioArchivo {

    private static final String ARCHIVO = "usuarios.txt";

 
    public static void guardarUsuario(Usuario usuario) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            bw.write(usuario.getNombre() + "," +
                     usuario.getIdentificacion() + "," +
                     usuario.getCelular() + "," +
                     usuario.getCorreo() + "," +
                     usuario.getContrasena());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar el usuario: " + e.getMessage());
        }
    }


    public static boolean validarCredenciales(String correo, String contrasena) {
        File archivo = new File(ARCHIVO);

        if (!archivo.exists()) {

            System.out.println("Archivo de usuarios no encontrado.");
            return false;
        }

        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] partes = linea.split(",");
                if (partes.length == 5) {
                    String correoGuardado = partes[3].trim();   
                    String contrasenaGuardada = partes[4].trim();   

                    if (correo.equals(correoGuardado) && contrasena.equals(contrasenaGuardada)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return false;
    }
}