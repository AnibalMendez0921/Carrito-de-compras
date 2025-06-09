/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Usuario;
import Modelo.UsuarioArchivo;
import Modelo.Sesion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.Node;

public class LoginController implements Initializable {

    @FXML private ImageView backgroundImage;
    @FXML private StackPane rootPane;
    @FXML private TextField txtCorreo;
    @FXML private PasswordField txtContrasena;
    @FXML private Button roundButton;
    @FXML private Button btnCerrarLogin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        backgroundImage.fitWidthProperty().bind(rootPane.widthProperty());
        backgroundImage.fitHeightProperty().bind(rootPane.heightProperty().divide(2));
        backgroundImage.translateYProperty().bind(rootPane.heightProperty().divide(-4));
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String correo = txtCorreo.getText();
        String contrasena = txtContrasena.getText();

        if (correo == null || correo.isEmpty()) {
            mostrarAlerta("Error", "Por favor, ingrese el correo.");
            return;
        }

        if (!UsuarioArchivo.validarCredenciales(correo, contrasena)) {
            mostrarAlerta("Error", "Correo o contraseña incorrectos.");
            txtCorreo.clear();
            txtContrasena.clear();
            return;
        }

        Usuario usuarioLogueado = UsuarioArchivo.buscarPorCorreo(correo);
        if (usuarioLogueado != null) {
            Sesion.setUsuarioActual(usuarioLogueado);
        }

        String rol = correo.toLowerCase().endsWith("@zcarpe") ? "admin" : "usuario";

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/PantallaPrincipal.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) roundButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Bienvenido - " + rol.toUpperCase());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirRegistro(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Registro.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Registro de Usuario");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
   private void cerrarVentana(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/PantallaPrincipal.fxml"));
        Parent root = loader.load();
        Stage stagePrincipal = new Stage();
        stagePrincipal.setScene(new Scene(root));
        stagePrincipal.setTitle("Pantalla Principal");
        stagePrincipal.show();

        Stage stageLogin = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageLogin.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}