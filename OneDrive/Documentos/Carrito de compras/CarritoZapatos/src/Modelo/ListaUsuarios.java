/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @Josue Gómez y Anibal Mendez
 */
public class ListaUsuarios {
 private NodoUsuario cabeza;

    public void agregarUsuario(Usuario usuario) {
        NodoUsuario nuevo = new NodoUsuario(usuario);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoUsuario temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevo;
        }
    }

    public boolean existeCorreo(String correo) {
        NodoUsuario temp = cabeza;
        while (temp != null) {
            if (temp.usuario.getCorreo().equalsIgnoreCase(correo)) {
                return true;
            }
            temp = temp.siguiente;
        }
        return false;
    }

    public NodoUsuario getCabeza() {
        return cabeza;
    }
}
