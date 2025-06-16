/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

public class ColaPedidos {

    private NodoCola frente;
    private NodoCola fin;

   
    private static ColaPedidos instancia;

    private ColaPedidos() {
        frente = null;
        fin = null;
    }

    public static ColaPedidos getInstancia() {
        if (instancia == null) {
            instancia = new ColaPedidos();
        }
        return instancia;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public void encolar(Compra compra) {
        NodoCola nuevo = new NodoCola(compra);
        if (estaVacia()) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
    }

    public Compra desencolar() {
        if (estaVacia()) return null;

        Compra compra = frente.getCompra();
        frente = frente.getSiguiente();
        if (frente == null) fin = null;
        return compra;
    }

    public NodoCola getFrente() {
        return frente;
    }

    public Compra verFrente() {
        return estaVacia() ? null : frente.getCompra();
    }

    public void vaciar() {
        frente = null;
        fin = null;
    }
}