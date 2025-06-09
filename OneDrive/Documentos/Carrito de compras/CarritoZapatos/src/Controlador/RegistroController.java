/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import Modelo.Usuario;
import Modelo.ListaUsuarios;
import Modelo.UsuarioArchivo;
import Modelo.Sesion;

import java.io.IOException;

public class RegistroController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtIdentificacion;
    @FXML private TextField txtCelular;
    @FXML private TextField txtCorreo;
    @FXML private PasswordField txtContrasena;
    @FXML private PasswordField txtConfirmarContrasena;
    @FXML private Button btnRegistrarse;
    @FXML private Button btnCerrar;

    private static ListaUsuarios listaUsuarios = new ListaUsuarios();

    @FXML
    public void initialize() {
        btnRegistrarse.setOnAction(event -> {
            try {
                registrarUsuario(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        if (btnCerrar != null) {
            btnCerrar.setOnAction(event -> cerrarVentana(event));
        }
    }

    private void registrarUsuario(javafx.event.ActionEvent event) throws IOException {
        String nombre = txtNombre.getText();
        String identificacion = txtIdentificacion.getText();
        String celular = txtCelular.getText();
        String correo = txtCorreo.getText();
        String contrasena = txtContrasena.getText();
        String confirmarContrasena = txtConfirmarContrasena.getText();

        if (nombre.isEmpty() || identificacion.isEmpty() || celular.isEmpty() || correo.isEmpty()
                || contrasena.isEmpty() || confirmarContrasena.isEmpty()) {
            mostrarAlerta("Campos vacíos", "Por favor, completa todos los campos antes de continuar.");
            return;
        }

        if (!contrasena.equals(confirmarContrasena)) {
            mostrarAlerta("Error", "Las contraseñas no coinciden");
            return;
        }

        if (listaUsuarios.existeCorreo(correo)) {
            mostrarAlerta("Error", "Ya existe un usuario con ese correo");
            return;
        }

        Usuario nuevoUsuario = new Usuario(nombre, identificacion, celular, correo, contrasena);
        listaUsuarios.agregarUsuario(nuevoUsuario);
        UsuarioArchivo.guardarUsuario(nuevoUsuario);

        Sesion.setUsuarioActual(nuevoUsuario);

        Parent root = FXMLLoader.load(getClass().getResource("/Vista/PantallaPrincipal.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

        mostrarAlerta("Éxito", "Usuario registrado correctamente");
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void cerrarVentana(javafx.event.ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Login.fxml"));
            Parent root = loader.load();
            Stage loginStage = new Stage();
            loginStage.setScene(new Scene(root));
            loginStage.setTitle("Login");
            loginStage.show();

            Stage registroStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            registroStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}