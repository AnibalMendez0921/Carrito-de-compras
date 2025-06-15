/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Compra;
import Modelo.PilaCompras;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;

public class HistorialComprasController implements Initializable {

    @FXML
    private VBox vBoxCompras;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCompras();
    }

    private void cargarCompras() {
    List<Compra> compras = PilaCompras.getInstance().toList();

    for (Compra compra : compras) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/ItemHistorialCompra.fxml"));
            Parent item = loader.load();


            ItemHistorialCompraController controller = loader.getController();
            controller.setCompra(compra); 
            vBoxCompras.getChildren().add(item);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    @FXML
private void onSeguirComprando(javafx.event.ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/PantallaPrincipal.fxml"));
        Parent root = loader.load();
        javafx.stage.Stage stage = (javafx.stage.Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new javafx.scene.Scene(root));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}