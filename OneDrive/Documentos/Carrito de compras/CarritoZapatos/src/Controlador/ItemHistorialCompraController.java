/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Compra;
import Modelo.Producto;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ItemHistorialCompraController {

    @FXML private Label labelFechaCompra;
    @FXML private Label labelNumeroPedido;
    @FXML private Label labelFechaEntrega;
    @FXML private Label labelDireccion;
    @FXML private Label labelMetodoPago;
    @FXML private Label labelGuia;
    @FXML private Label labelTotal;
    @FXML private Label labelEstado;
    
    
    @FXML
    private TextArea textAreaDetalle;

    public void setCompra(Compra compra) {
        labelFechaCompra.setText(compra.getFecha().toString());
        labelNumeroPedido.setText("001");
        Producto p = compra.getProductos().get(0); 
        
        labelFechaEntrega.setText(compra.getFecha().plusDays(3).toString());
        labelDireccion.setText("Mi dirección");
        labelMetodoPago.setText("Tarjeta");
        labelGuia.setText("123ABC");
        labelTotal.setText(String.format("$%,.2f", (p.getPrecio() * p.getCantidad())+20000));
        labelEstado.setText("Entregado");
    }
    
    public void setDetalle(String detalle) {
    textAreaDetalle.setText(detalle);
}
}
