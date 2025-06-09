/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

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
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Josue Gómez y Anibal Mendez
 */
public class VerMasController implements Initializable {

    @FXML private Button btnZapato1;
    @FXML private Button btnZapato2;
    @FXML private Button btnZapato3;
    @FXML private Button btnZapato4;
    @FXML private Button btnZapato5;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (btnZapato1 != null) {
            btnZapato1.setOnAction(e -> abrirVentana("/Vista/DescripcionProducto1.fxml", "Descripción Producto 1", e));
        }

        if (btnZapato2 != null) {
            btnZapato2.setOnAction(e -> abrirVentana("/Vista/DescripcionProducto2.fxml", "Descripción Producto 2", e));
        }

        if (btnZapato3 != null) {
            btnZapato3.setOnAction(e -> abrirVentana("/Vista/DescripcionProducto3.fxml", "Descripción Producto 3", e));
        }

        if (btnZapato4 != null) {
            btnZapato4.setOnAction(e -> abrirVentana("/Vista/DescripcionProducto4.fxml", "Descripción Producto 4", e));
        }

        if (btnZapato5 != null) {
            btnZapato5.setOnAction(e -> abrirVentana("/Vista/DescripcionProducto5.fxml", "Descripción Producto 5", e));
        }
    }

    private void abrirVentana(String rutaFXML, String titulo, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void irAPantallaPrincipal(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/PantallaPrincipal.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Pantalla Principal");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
