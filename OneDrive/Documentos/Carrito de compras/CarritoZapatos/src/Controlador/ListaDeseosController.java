package Controlador;

import Modelo.ListaDeseos;
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

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

import javafx.event.ActionEvent;

public class ListaDeseosController implements Initializable {

    @FXML
    private VBox listaItemsVBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarListaDeseos();
    }

    private void cargarListaDeseos() {
        listaItemsVBox.getChildren().clear();

        for (Producto producto : ListaDeseos.getListaDeseos()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/ItemDeseo.fxml"));
                StackPane item = loader.load();

                ItemDeseoController controller = loader.getController();
                controller.setProducto(producto);
                controller.setParentController(this);

                listaItemsVBox.getChildren().add(item);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

  
    public void refreshListView(){
        cargarListaDeseos();
    }

    @FXML
    private void regresar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/PantallaPrincipal.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root));

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}