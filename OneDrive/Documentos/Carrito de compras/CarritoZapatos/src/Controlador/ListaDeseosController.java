/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.ListaDeseos;
import Modelo.NodoDeseo;
import Modelo.Producto;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class ListaDeseosController implements Initializable {

    @FXML
    private VBox listaItemsVBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarListaDeseos();
    }

    private void cargarListaDeseos() {
        NodoDeseo actual = ListaDeseos.getPrimero();

        while (actual != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/ItemDeseo.fxml"));
                StackPane item = (StackPane) loader.load(); 


                ItemDeseoController controller = loader.getController();
                controller.setProducto(actual.getProducto());

                listaItemsVBox.getChildren().add(item);
            } catch (IOException e) {
                e.printStackTrace();
            }

            actual = actual.getSiguiente();
        }
    }

    @FXML
    private void regresar(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/PantallaPrincipal.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}