/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Carrito;
import Modelo.Producto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class DescripcionProducto1Controller {

    @FXML private ToggleButton talla36;
    @FXML private ToggleButton talla37;
    @FXML private ToggleButton talla38;
    @FXML private ToggleButton talla39;
    @FXML private ToggleButton talla40;


    @FXML
    private void irAlCarrito(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Carrito.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Carrito de compras");
            stage.setScene(new Scene(root));
            stage.show();

            ((Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow()).close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void irAPantallaPrincipal(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/PantallaPrincipal.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Tienda de Zapatos");
            stage.setScene(new Scene(root));
            stage.show();

            ((Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow()).close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String obtenerTallaSeleccionada() {
        if (talla36.isSelected()) return "36";
        if (talla37.isSelected()) return "37";
        if (talla38.isSelected()) return "38";
        if (talla39.isSelected()) return "39";
        if (talla40.isSelected()) return "40";
        return "Sin talla";
    }

    @FXML
    private void agregarAlCarrito(ActionEvent event) {
        String talla = obtenerTallaSeleccionada();
        if (talla.equals("Sin talla")) {
            System.out.println("Por favor selecciona una talla.");
            return;
        }

        Producto producto = new Producto(
            "Tenis hombre Bravé",
            "Fuerza urbana",
            talla,
            "4.5",
            "$169.900",
            new Image(getClass().getResource("/Imagenes/Zapato1.png").toString())
        );

        Carrito.agregarProducto(producto);
        System.out.println("Producto agregado al carrito: " + producto.getNombre());

  
    }
}