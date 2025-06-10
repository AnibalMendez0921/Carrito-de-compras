/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.util.List;
/**
 *
 * @Josue Gómez y Anibal Mendez
 */

public class Carrito {
     private static NodoProducto cabeza = null;

    public static void agregarProducto(Producto producto) {
        NodoProducto nuevo = new NodoProducto(producto);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoProducto actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
    }

    public static void limpiar() {
        cabeza = null;
    }

    public static NodoProducto getPrimero() {
        return cabeza;
    }

    public static int contarProductos() {
        int contador = 0;
        NodoProducto actual = cabeza;
        while (actual != null) {
            contador++;
            actual = actual.getSiguiente();
        }
        return contador;
    }
}
