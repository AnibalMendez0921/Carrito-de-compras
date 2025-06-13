/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author aniba
 */
public class ListaDeseos {
private static NodoDeseo primero;

    public static void agregar(Producto producto) {
        NodoDeseo nuevo = new NodoDeseo(producto);
        if (primero == null) {
            primero = nuevo;
        } else {
            NodoDeseo temp = primero;
            while (temp.getSiguiente() != null) {
                temp = temp.getSiguiente();
            }
            temp.setSiguiente(nuevo);
        }
    }

    public static NodoDeseo getPrimero() {
        return primero;
    }    
}
