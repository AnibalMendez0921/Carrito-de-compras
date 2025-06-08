/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Sesion;
import Modelo.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PantallaPrincipalController implements Initializable {

    @FXML private Button btnZapato1;
    @FXML private Button btnZapato2;
    @FXML private Button btnZapato3;
    @FXML private Button btnZapato4;
    @FXML private Button btnZapato5;

    @FXML private Button btnRegistro;
    @FXML private Button btnLogin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (Sesion.haySesionActiva()) {
            Usuario usuario = Sesion.getUsuarioActual();
            System.out.println("Sesión activa: " + usuario.getNombre());
        }

        if (btnZapato1 != null) {
            btnZapato1.setOnAction(e -> abrirDescripcionProducto("/Vista/DescripcionProducto1.fxml", "Descripción Producto 1"));
        }

        if (btnZapato2 != null) {
            btnZapato2.setOnAction(e -> abrirDescripcionProducto("/Vista/DescripcionProducto2.fxml", "Descripción Producto 2"));
        }

        if (btnZapato3 != null) {
            btnZapato3.setOnAction(e -> abrirDescripcionProducto("/Vista/DescripcionProducto3.fxml", "Descripción Producto 3"));
        }

        if (btnZapato4 != null) {
            btnZapato4.setOnAction(e -> abrirDescripcionProducto("/Vista/DescripcionProducto4.fxml", "Descripción Producto 4"));
        }

        if (btnZapato5 != null) {
            btnZapato5.setOnAction(e -> abrirDescripcionProducto("/Vista/DescripcionProducto5.fxml", "Descripción Producto 5"));
        }

        if (btnRegistro != null) {
            btnRegistro.setOnAction(e -> abrirVentana("/Vista/Registro.fxml", "Registrarse"));
        }

        if (btnLogin != null) {
            btnLogin.setOnAction(e -> abrirVentana("/Vista/Login.fxml", "Iniciar Sesión"));
        }
    }

    private void abrirDescripcionProducto(String rutaFXML, String titulo) {
        abrirVentana(rutaFXML, titulo);
    }

    private void abrirVentana(String rutaFXML, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}