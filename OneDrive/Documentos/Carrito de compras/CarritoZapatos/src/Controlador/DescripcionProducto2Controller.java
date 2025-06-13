/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Carrito;
import Modelo.ListaDeseos;
import Modelo.Producto;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.image.ImageView;

public class DescripcionProducto2Controller implements Initializable {

    @FXML private ToggleButton talla36;
    @FXML private ToggleButton talla37;
    @FXML private ToggleButton talla38;
    @FXML private ToggleButton talla39;
    @FXML private ToggleButton talla40;

    @FXML private TitledPane titledDescripcion;
    @FXML private TitledPane titledDetalles;
    @FXML private TitledPane titledCuidados;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hacerTituloTransparente(titledDescripcion);
        hacerTituloTransparente(titledDetalles);
        hacerTituloTransparente(titledCuidados);
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

    @FXML
    private void agregarAlCarrito(ActionEvent event) {
        String talla = obtenerTallaSeleccionada();
        if (talla.equals("Sin talla")) {
            System.out.println("Por favor selecciona una talla.");
            return;
        }

        Producto producto = new Producto(
            "Tenis mujer Nébula",
            "Elegancia etérea",
            talla,
            "4.6",
            154900,
            new Image(getClass().getResource("/Imagenes/Zapato2.png").toString())
        );

        Carrito.agregarProducto(producto);
        System.out.println("Producto agregado al carrito: " + producto.getNombre());
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
    
    public void irAListaDeseos(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Vista/ListaDeseos.fxml")); // Usa la ruta correcta según tu estructura
            Stage stage = new Stage();
            stage.setTitle("Lista de Deseos");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
        @FXML
private ImageView imgCorazon;

private boolean esFavorito = false;

@FXML
    private void toggleFavorito() {
        esFavorito = !esFavorito;
        String imagen = esFavorito ? "/Imagenes/corazon_lleno.png" : "/Imagenes/corazon vacio.png";
        imgCorazon.setImage(new Image(getClass().getResourceAsStream(imagen)));

        if (esFavorito) {
            String talla = obtenerTallaSeleccionada();
            Producto producto = new Producto(
                "Tenis mujer Nébula",
                "Elegancia etérea",
                talla.equals("Sin talla") ? "Sin talla" : talla,
                "4.6",
                154900,
                new Image(getClass().getResource("/Imagenes/Zapato2.png").toString())
            );
            ListaDeseos.agregar(producto);
            System.out.println("Producto agregado a la lista de deseos: " + producto.getNombre());
        }
    }
}