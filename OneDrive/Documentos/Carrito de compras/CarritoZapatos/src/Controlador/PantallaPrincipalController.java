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
import javafx.event.ActionEvent;
import javafx.scene.Node;

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

       
        if (btnRegistro != null) {
            btnRegistro.setOnAction(this::irARegistro);
        }

        if (btnLogin != null) {
            btnLogin.setOnAction(this::irALogin);
        }
    }

    private void abrirVentana(String rutaFXML, String titulo, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = loader.load();
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void irARegistro(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Registro.fxml"));
            Parent root = loader.load();

            Stage stageRegistro = new Stage();
            stageRegistro.setScene(new Scene(root));
            stageRegistro.setTitle("Registro");
            stageRegistro.show();

            Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageActual.hide();

            stageRegistro.setOnCloseRequest(e -> {
                try {
                    FXMLLoader loaderPrincipal = new FXMLLoader(getClass().getResource("/Vista/PantallaPrincipal.fxml"));
                    Parent rootPrincipal = loaderPrincipal.load();
                    Stage stagePrincipal = new Stage();
                    stagePrincipal.setScene(new Scene(rootPrincipal));
                    stagePrincipal.setTitle("Pantalla Principal");
                    stagePrincipal.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void irALogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Login.fxml"));
            Parent root = loader.load();

            Stage stageLogin = new Stage();
            stageLogin.setScene(new Scene(root));
            stageLogin.setTitle("Iniciar Sesión");
            stageLogin.show();

            Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageActual.hide();

            stageLogin.setOnCloseRequest(e -> {
                try {
                    FXMLLoader loaderPrincipal = new FXMLLoader(getClass().getResource("/Vista/PantallaPrincipal.fxml"));
                    Parent rootPrincipal = loaderPrincipal.load();
                    Stage stagePrincipal = new Stage();
                    stagePrincipal.setScene(new Scene(rootPrincipal));
                    stagePrincipal.setTitle("Pantalla Principal");
                    stagePrincipal.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}