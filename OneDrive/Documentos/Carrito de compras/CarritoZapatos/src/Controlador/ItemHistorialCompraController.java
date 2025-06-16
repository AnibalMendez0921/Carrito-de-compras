/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Compra;
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

    @FXML private TextArea textAreaDetalle;

    private static final double COSTO_ENVIO = 20000.0;

    public void setCompra(Compra compra) {
        labelFechaCompra.setText(compra.getFecha().toString());


        labelNumeroPedido.setText(compra.getIdPedido());

        labelFechaEntrega.setText(compra.getFecha().plusDays(3).toString());
        textAreaDetalle.setText(compra.getDetalle());
        labelDireccion.setText(compra.getDireccion());
        labelMetodoPago.setText(compra.getMetodoPago());
        labelGuia.setText(generarCodigoGuia());
        labelEstado.setText("En camino");

        double total = compra.getTotal();

        if (total == 0 && compra.getProductos() != null && !compra.getProductos().isEmpty()) {
            total = compra.getProductos().stream()
                    .mapToDouble(p -> {
                        int cantidad = p.getCantidad() > 0 ? p.getCantidad() : 1;
                        return p.getPrecio() * cantidad;
                    }).sum();
            total += COSTO_ENVIO;
        }

        labelTotal.setText(String.format("$%,.2f", total));
    }

    private String generarCodigoGuia() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int index = (int) (Math.random() * caracteres.length());
            sb.append(caracteres.charAt(index));
        }
        return sb.toString();
    }
}
