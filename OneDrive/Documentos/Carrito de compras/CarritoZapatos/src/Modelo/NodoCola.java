/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author aniba
 */
public class NodoCola {
    
    private Compra compra;
    private NodoCola siguiente;

    public NodoCola(Compra compra) {
        this.compra = compra;
        this.siguiente = null;
    }

    public Compra getCompra() {
        return compra;
    }

    public NodoCola getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoCola siguiente) {
        this.siguiente = siguiente;
    }
}