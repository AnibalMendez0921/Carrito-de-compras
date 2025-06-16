/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Sesion;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class OpcionesUsuariosController implements Initializable {

    @FXML private Label labelNombres;
    @FXML private Label labelDocumento;
    @FXML private Label labelCorreo;
    @FXML private Label labelTelefono;
    @FXML private Label labelDireccion;
    @FXML private Button btnEditar;
    @FXML private Button btnCerrarSesion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void editarInformacionPersonal() {
        labelNombres.setText(mostrarDialogo("Editar Nombres Completos:", labelNombres.getText()));
        labelDocumento.setText(mostrarDialogo("Editar Documento:", labelDocumento.getText()));
        labelCorreo.setText(mostrarDialogo("Editar Correo:", labelCorreo.getText()));
        labelTelefono.setText(mostrarDialogo("Editar Teléfono:", labelTelefono.getText()));
        labelDireccion.setText(mostrarDialogo("Editar Dirección:", labelDireccion.getText()));
    }

    private String mostrarDialogo(String mensaje, String valorAnterior) {
        TextInputDialog dialog = new TextInputDialog(valorAnterior);
        dialog.setTitle("Editar Información");
        dialog.setHeaderText(null);
        dialog.setContentText(mensaje);

        Optional<String> resultado = dialog.showAndWait();
        return resultado.orElse(valorAnterior);
    }

    @FXML
    private void irAPantallaPrincipal(ActionEvent event) {
        cambiarVista("/Vista/PantallaPrincipal.fxml", "Tienda de Zapatos", event);
    }

    private void cambiarVista(String ruta, String titulo, ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(ruta));
            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
private void abrirDialogoEditar(ActionEvent event) {
    System.out.println("Botón Editar presionado"); // Para depurar

    TextField nombreField = new TextField(labelNombres.getText());
    TextField documentoField = new TextField(labelDocumento.getText());
    TextField correoField = new TextField(labelCorreo.getText());
    TextField telefonoField = new TextField(labelTelefono.getText());
    TextField direccionField = new TextField(labelDireccion.getText());

    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(20, 150, 10, 10));

    grid.add(new Label("Nombre:"), 0, 0);
    grid.add(nombreField, 1, 0);
    grid.add(new Label("Documento:"), 0, 1);
    grid.add(documentoField, 1, 1);
    grid.add(new Label("Correo:"), 0, 2);
    grid.add(correoField, 1, 2);
    grid.add(new Label("Teléfono:"), 0, 3);
    grid.add(telefonoField, 1, 3);
    grid.add(new Label("Dirección:"), 0, 4);
    grid.add(direccionField, 1, 4);

    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setTitle("Editar Información Personal");
    dialog.setHeaderText("Modifica los campos y presiona Guardar");
    dialog.getDialogPane().setContent(grid);
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

    dialog.showAndWait().ifPresent(response -> {
        if (response == ButtonType.OK) {
            labelNombres.setText(nombreField.getText());
            labelDocumento.setText(documentoField.getText());
            labelCorreo.setText(correoField.getText());
            labelTelefono.setText(telefonoField.getText());
            labelDireccion.setText(direccionField.getText());
        }
    });
}

@FXML
private void cerrarSesion(ActionEvent event) {
    Sesion.cerrarSesion();
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Login.fxml"));
        Parent loginPane = loader.load(); 
        btnCerrarSesion.getScene().setRoot(loginPane);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}