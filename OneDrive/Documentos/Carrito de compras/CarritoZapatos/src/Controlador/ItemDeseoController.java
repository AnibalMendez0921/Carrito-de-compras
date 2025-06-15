/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.ListaDeseos;
import Modelo.Producto;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ItemDeseoController implements Initializable {

    @FXML private ImageView imagenProducto;
    @FXML private Label nombreProducto;
    @FXML private Label precioProducto;
    @FXML private Label calificacionProducto;

    private Producto producto;

    private ListaDeseosController parentController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
        cargarDatos();
    }

    private void cargarDatos() {
        if (producto != null) {
            imagenProducto.setImage(producto.getImagen()); 
            nombreProducto.setText(producto.getNombre());  
            precioProducto.setText(String.format("$ %.2f", producto.getPrecio()));
            calificacionProducto.setText(producto.getCalificacion()); 
        }
    }

    @FXML
    private void moverDelCarrito(ActionEvent event) {
        System.out.println("Producto movido de la lista de deseos al carrito.");
    }

    @FXML
    private void eliminarItem(ActionEvent event) {
        if (producto == null) return;

        ListaDeseos.eliminar(producto);
        System.out.println("Producto eliminado de la lista de deseos.");

        if (parentController != null) {
            parentController.refreshListView();
        }
    }

    public void setParentController(ListaDeseosController parentController) {
        this.parentController = parentController;
    }
}
