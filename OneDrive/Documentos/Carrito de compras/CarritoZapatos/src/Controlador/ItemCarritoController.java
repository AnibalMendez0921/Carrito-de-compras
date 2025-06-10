/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItemCarritoController {

    @FXML
    private ImageView imagenProducto;
    @FXML
    private Label nombreProducto;
    @FXML
    private Label descripcionProducto;
    @FXML
    private Label tallaProducto;
    @FXML
    private Label calificacionProducto;
    @FXML
    private Label precioProducto;
    @FXML
    private Label cantidadLabel;

    private int cantidad = 1;

    @FXML
    private void sumarCantidad() {
        cantidad++;
        cantidadLabel.setText(String.valueOf(cantidad));
    }

    @FXML
    private void restarCantidad() {
        if (cantidad > 1) {
            cantidad--;
            cantidadLabel.setText(String.valueOf(cantidad));
        }
    }

    public void setDatos(String nombre, String descripcion, String talla, String calificacion, String precio) {
        nombreProducto.setText(nombre);
        descripcionProducto.setText(descripcion);
        tallaProducto.setText(talla);
        calificacionProducto.setText(calificacion);
        precioProducto.setText(precio);
    }

    public void setImagen(Image imagen) {
        imagenProducto.setImage(imagen);
    }
}
