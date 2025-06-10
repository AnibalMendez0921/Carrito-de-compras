/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Carrito;
import Modelo.Producto;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

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
    @FXML
    private Button eliminarBtn;

    private int cantidad = 1;
    private double precioUnitario;

    private VBox contenedorPadre;
    private Producto producto;
    private CarritoController carritoController;

    public void setContenedorPadre(VBox contenedorPadre) {
        this.contenedorPadre = contenedorPadre;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setDatos(String nombre, String descripcion, String talla, String calificacion, double precio) {
        this.precioUnitario = precio;
        nombreProducto.setText(nombre);
        descripcionProducto.setText(descripcion);
        tallaProducto.setText(talla);
        calificacionProducto.setText(calificacion);
        actualizarPrecioTotal();
    }

    public void setImagen(Image imagen) {
        imagenProducto.setImage(imagen);
    }

    public void setCarritoController(CarritoController carritoController) {
        this.carritoController = carritoController;
    }

   @FXML
private void sumarCantidad() {
    cantidad++;
    cantidadLabel.setText(String.valueOf(cantidad));
    if (carritoController != null) 
        carritoController.actualizarResumen();  
}

@FXML
private void restarCantidad() {
    if (cantidad > 1) {
        cantidad--;
        cantidadLabel.setText(String.valueOf(cantidad));
        if (carritoController != null) 
            carritoController.actualizarResumen();
    }
}

    private void actualizarPrecioTotal() {
        double total = precioUnitario * cantidad;
        precioProducto.setText("$" + String.format("%,.0f", total).replace(",", "."));
    }

    @FXML
    private void eliminarItem() {
        if (contenedorPadre != null) {
            Node item = eliminarBtn.getParent().getParent().getParent();
            contenedorPadre.getChildren().remove(item);
        }

        if (producto != null) {
            Carrito.eliminarProducto(producto);
        }

        if (carritoController != null) {
            carritoController.actualizarResumen();
        }
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getSubtotal() {
        return precioUnitario * cantidad;
    }

   public Producto getProducto() {
    return producto;
   }
}
