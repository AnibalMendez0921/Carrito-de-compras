/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Carrito;
import Modelo.ListaDeseos;
import Modelo.Producto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DescripcionProducto1Controller implements Initializable {

    @FXML private ToggleButton talla36;
    @FXML private ToggleButton talla37;
    @FXML private ToggleButton talla38;
    @FXML private ToggleButton talla39;
    @FXML private ToggleButton talla40;

    @FXML private TitledPane titledDescripcion;
    @FXML private TitledPane titledDetalles;
    @FXML private TitledPane titledCuidados;

    @FXML private ImageView imgCorazon;

    private boolean esFavorito = false;

 
    private Producto productoActual;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hacerTituloTransparente(titledDescripcion);
        hacerTituloTransparente(titledDetalles);
        hacerTituloTransparente(titledCuidados);

        productoActual = new Producto(
                "Tenis hombre Bravé",
                "Fuerza urbana",
                "Sin talla",
                "4.5",
                169900,
                new Image(getClass().getResource("/Imagenes/Zapato1.png").toString()) 
        );
    }

    private void hacerTituloTransparente(TitledPane pane) {
        pane.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        pane.skinProperty().addListener((obs, oldSkin, newSkin) -> {
            Region titleRegion = (Region) pane.lookup(".title");

            if (titleRegion != null) {
                titleRegion.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
            }

            Region contentRegion = (Region) pane.lookup(".content");

            if (contentRegion != null) {
                contentRegion.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
            }
        });
    }

    private String obtenerTallaSeleccionada() {
        if (talla36.isSelected()) return "36";
        if (talla37.isSelected()) return "37";
        if (talla38.isSelected()) return "38";
        if (talla39.isSelected()) return "39";
        if (talla40.isSelected()) return "40";

        return "Sin talla";
    }

    private void aplicarTallaAlProductoActual(){
        productoActual.setTalla(obtenerTallaSeleccionada()); 
    }

    @FXML
    private void agregarAlCarrito(ActionEvent event) {
        aplicarTallaAlProductoActual();

        if (productoActual.getTalla().equals("Sin talla")) {
            System.out.println("Por favor selecciona una talla.");
            return;
        }

        Carrito.agregarProducto(productoActual);
        System.out.println("Producto agregado al carrito: " + productoActual.getNombre()); 
    }

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

        } catch (IOException e) {
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
private void irAListaDeseos(ActionEvent event) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/ListaDeseos.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Lista de Deseos");

        stage.setScene(new Scene(root));

        stage.show();


        ((Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow()).close();

    } catch (IOException e) {
        e.printStackTrace();
    }
}


    @FXML
    private void toggleFavorito() {
        esFavorito = !esFavorito;

        String imagen = esFavorito ? "/Imagenes/corazon_lleno.png" : "/Imagenes/corazon vacio.png";

        imgCorazon.setImage(new Image(getClass().getResourceAsStream(imagen)));

        aplicarTallaAlProductoActual();

        if (esFavorito) {
            ListaDeseos.agregar(productoActual);
            System.out.println("Producto agregado a la lista de deseos.");
        } else {
            ListaDeseos.eliminar(productoActual);
            System.out.println("Producto eliminado de la lista de deseos.");
        }
    }
}
