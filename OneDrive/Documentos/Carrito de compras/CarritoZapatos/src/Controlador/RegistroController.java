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
import Modelo.Usuario;
import Modelo.ListaUsuarios;
import Modelo.UsuarioArchivo;

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
        btnRegistrarse.setOnAction(event -> registrarUsuario());

        if (btnCerrar != null) {
            btnCerrar.setOnAction(event -> cerrarVentana());
        }
    }

    private void registrarUsuario() {
        String nombre = txtNombre.getText();
        String identificacion = txtIdentificacion.getText();
        String celular = txtCelular.getText();
        String correo = txtCorreo.getText();
        String contrasena = txtContrasena.getText();
        String confirmarContrasena = txtConfirmarContrasena.getText();

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

        mostrarAlerta("Éxito", "Usuario registrado correctamente");
        limpiarCampos();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtIdentificacion.clear();
        txtCelular.clear();
        txtCorreo.clear();
        txtContrasena.clear();
        txtConfirmarContrasena.clear();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }
}