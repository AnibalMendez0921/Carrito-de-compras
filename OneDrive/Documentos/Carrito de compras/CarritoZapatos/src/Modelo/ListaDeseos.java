/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListaDeseos {

    private static final ObservableList<Producto> listaDeseos = FXCollections.observableArrayList();

    public static void agregar(Producto producto) {
        if (!listaDeseos.contains(producto)) {
            listaDeseos.add(producto);
        }
    }

    public static void eliminar(Producto producto) {
        listaDeseos.remove(producto);
    }

    public static ObservableList<Producto> getListaDeseos(){
        return listaDeseos;
    }
}