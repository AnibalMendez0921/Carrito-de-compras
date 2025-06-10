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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CarritoController implements Initializable {

    @FXML
    private VBox listaItemsVBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        NodoProducto actual = Carrito.getPrimero();
        while (actual != null) {
            Producto producto = actual.getProducto();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/ItemCarrito.fxml"));
                Node nodo = loader.load();

                Controlador.ItemCarritoController controller = loader.getController();
                controller.setDatos(
                    producto.getNombre(),
                    producto.getDescripcion(),
                    producto.getTalla(),
                    producto.getCalificacion(),
                    producto.getPrecio()
                );
                controller.setImagen(producto.getImagen());

                listaItemsVBox.getChildren().add(nodo);

            } catch (IOException e) {
                e.printStackTrace();
            }

            actual = actual.getSiguiente();
        }
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