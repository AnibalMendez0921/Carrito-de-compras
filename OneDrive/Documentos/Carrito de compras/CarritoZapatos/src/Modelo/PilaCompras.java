/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

public class PilaCompras {

  private NodoPila cima;

    private static PilaCompras instance = new PilaCompras();

    private PilaCompras(){
        cima = null;
    }

    public static PilaCompras getInstance(){
        return instance;
    }

    public void push(Compra compra){
        NodoPila nuevo = new NodoPila(compra);
        nuevo.setSiguiente(cima);
        cima = nuevo;
    }

    public Compra pop(){
        if (isEmpty()) {
            return null;
        }
        Compra toReturn = cima.getCompra();
        cima = cima.getSiguiente();
        return toReturn;
    }

    public Compra peek(){
        return isEmpty() ? null : cima.getCompra();
    }

    public boolean isEmpty(){
        return cima == null;
    }

    public java.util.List<Compra> toList(){
        java.util.List<Compra> lista = new java.util.ArrayList<>();
        NodoPila actual = cima;
        while (actual != null) {
            lista.add(actual.getCompra()); 
            actual = actual.getSiguiente();
        }
        return lista;
    }
    
    
}