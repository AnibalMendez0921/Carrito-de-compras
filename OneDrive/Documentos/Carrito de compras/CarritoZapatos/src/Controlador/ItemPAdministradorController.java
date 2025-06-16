/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItemPAdministradorController implements Initializable {

    @FXML
    private Label labelNombre;

    @FXML
    private Label labelPrecio;

    @FXML
    private Label labelTalla;

    @FXML
    private Label labelStock;

    @FXML
    private ImageView imagenProducto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialización si es necesaria
    }

    /**
     * Método para configurar los datos del item.
     */
    public void setDatos(String nombre, double precio, String categoria, int stock, String rutaImagen) {
        labelNombre.setText(nombre);
        labelPrecio.setText("$" + String.format("%.2f", precio));
        labelTalla.setText(categoria);
        labelStock.setText(String.valueOf(stock));

        if (rutaImagen != null && !rutaImagen.isEmpty()) {
            try {
                imagenProducto.setImage(new Image("file:" + rutaImagen));
            } catch (Exception e) {
                System.out.println("Error al cargar la imagen: " + e.getMessage());
            }
        }
    }
}