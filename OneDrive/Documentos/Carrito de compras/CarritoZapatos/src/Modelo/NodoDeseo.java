/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author aniba
 */
public class NodoDeseo {
 private Producto producto;
    private NodoDeseo siguiente;

    public NodoDeseo(Producto producto) {
        this.producto = producto;
        this.siguiente = null;
    }

    public Producto getProducto() {
        return producto;
    }

    public NodoDeseo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoDeseo siguiente) {
        this.siguiente = siguiente;
    }    
}
