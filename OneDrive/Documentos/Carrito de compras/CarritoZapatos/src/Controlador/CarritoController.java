/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Carrito;
import Modelo.NodoProducto;
import Modelo.Producto;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CarritoController implements Initializable {

    @FXML
    private VBox listaItemsVBox;

    @FXML
    private TextArea detalleCompraArea;

    @FXML
    private Label subtotalLabel;

    @FXML
    private Label envioLabel;

    @FXML
    private Label totalLabel;

    private static final double COSTO_ENVIO = 20000.0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        NodoProducto actual = Carrito.getPrimero();

        while (actual != null) {
            Producto producto = actual.getProducto();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/ItemCarrito.fxml"));
                Node nodo = loader.load();

                ItemCarritoController controller = loader.getController();
                controller.setDatos(
                        producto.getNombre(),
                        producto.getDescripcion(),
                        producto.getTalla(),
                        producto.getCalificacion(),
                        producto.getPrecio()
                );
                controller.setImagen(producto.getImagen());
                controller.setContenedorPadre(listaItemsVBox);
                controller.setProducto(producto);
                controller.setCarritoController(this);
                
      
                nodo.getProperties().put("controller", controller);

                listaItemsVBox.getChildren().add(nodo);

            } catch (IOException e) {
                e.printStackTrace();
            }

            actual = actual.getSiguiente();
        }

        actualizarResumen();
    }

    public void actualizarResumen() {
        StringBuilder detalle = new StringBuilder();
        double subtotal = 0;
        int totalCantidad = 0;

        DecimalFormat df = new DecimalFormat("#,###.##");

        detalle.append(String.format("%-25s %-6s %-6s %-12s %-12s\n", "Producto", "Talla", "Cant.", "Unitario", "Subtotal"));
        detalle.append("-------------------------------------------------------------------------\n");

        for (Node nodo : listaItemsVBox.getChildren()) {
            ItemCarritoController controller = (ItemCarritoController) nodo.getProperties().get("controller");
            if (controller != null) {
                Producto p = controller.getProducto();
                int cantidad = controller.getCantidad();
                double precio = p.getPrecio();
                double subtotalProducto = precio * cantidad;

                subtotal += subtotalProducto;
                totalCantidad += cantidad;

                detalle.append(String.format("%-25s %-6s %-6d $%-11s $%-11s\n",
                        p.getNombre(),
                        p.getTalla(),
                        cantidad,
                        df.format(precio),
                        df.format(subtotalProducto)
                ));
            }
        }

        double total = subtotal + COSTO_ENVIO;

        detalleCompraArea.setText(detalle.toString());
        subtotalLabel.setText("Subtotal: $" + df.format(subtotal));
        envioLabel.setText("Envío: $" + df.format(COSTO_ENVIO));
        totalLabel.setText("Total a pagar: $" + df.format(total));
    }

    @FXML
    private void irAVerMas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/VerMas.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
