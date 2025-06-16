/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.ColaPedidos;
import Modelo.Compra;
import Modelo.NodoCola;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

public class OpcionesAdministradorController implements Initializable {

    @FXML
    private TextArea pedidosTextArea;

    @FXML
    private VBox vBoxContenedorProductos;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mostrarPedidosPendientes();
    }

    @FXML
    private void irAPantallaPrincipal(javafx.event.ActionEvent event) {
        cambiarVista("/Vista/PantallaPrincipal.fxml", "Tienda de Zapatos", event);
    }

    private void cambiarVista(String ruta, String titulo, javafx.event.ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(ruta));
            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarPedidosPendientes() {
        ColaPedidos cola = ColaPedidos.getInstancia();
        NodoCola actual = cola.getFrente();

        StringBuilder detalles = new StringBuilder();

        while (actual != null) {
            Compra compra = actual.getCompra();
            detalles.append("ID del Pedido: ").append(compra.getIdPedido()).append("\n");
            detalles.append("Nombre Usuario: ").append(compra.getNombreCliente()).append("\n");
            detalles.append("Fecha: ").append(compra.getFecha()).append("\n");
            detalles.append("Estado: ").append(compra.getEstado()).append("\n");
            detalles.append("Direccion: ").append(compra.getDireccion()).append("\n");
            detalles.append("Metodo de pago: ").append(compra.getMetodoPago()).append("\n");
            detalles.append("Detalles de la compra: ").append("\n").append(compra.getDetalle()).append("\n\n");

            actual = actual.getSiguiente();
        }

        pedidosTextArea.setText(detalles.toString());
    }



@FXML
private void mostrarDialogoAgregarProducto(ActionEvent event) { {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/ItemPAdministrador.fxml"));
        AnchorPane item = loader.load();

        ItemPAdministradorController controller = loader.getController();
        controller.setDatos("Zapato1", 123.0, "40", 10, "ruta/a/imagen.png");

        vBoxContenedorProductos.getChildren().add(item);

    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
}

