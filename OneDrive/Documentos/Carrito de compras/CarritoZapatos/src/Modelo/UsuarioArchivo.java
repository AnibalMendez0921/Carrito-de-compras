/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @Josue Gómez y Anibal Mendez
 */
public class UsuarioArchivo {
  public static void guardarUsuario(Usuario usuario) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("usuarios.txt", true))) {
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
}
