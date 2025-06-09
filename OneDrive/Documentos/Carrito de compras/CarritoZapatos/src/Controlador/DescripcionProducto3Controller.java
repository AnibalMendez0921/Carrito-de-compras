/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class DescripcionProducto3Controller implements Initializable {

    @FXML private ToggleButton talla36;
    @FXML private ToggleButton talla37;
    @FXML private ToggleButton talla38;
    @FXML private ToggleButton talla39;
    @FXML private ToggleButton talla40;
    @FXML private ToggleGroup tallasGroup;

    @FXML private Button btnComprar;
    @FXML private Button btnAgregarCarrito;

    @FXML private TitledPane titledDescripcion;
    @FXML private TitledPane titledDetalles;
    @FXML private TitledPane titledCuidados;

    @FXML private ToggleButton btnFavorito;
    @FXML private ImageView imgCorazon;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tallasGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle != null) {
                ToggleButton seleccionada = (ToggleButton) newToggle;
                System.out.println("Talla seleccionada: " + seleccionada.getText());
            }
        });

        btnComprar.setOnAction(e -> {
            String talla = obtenerTallaSeleccionada();
            if (talla != null) {
                System.out.println("Comprando talla: " + talla);
            } else {
                System.out.println("Por favor selecciona una talla.");
            }
        });

        btnAgregarCarrito.setOnAction(e -> {
            String talla = obtenerTallaSeleccionada();
            if (talla != null) {
                System.out.println("Agregando al carrito talla: " + talla);
            } else {
                System.out.println("Por favor selecciona una talla.");
            }
        });

        btnFavorito.setOnAction(e -> {
            if (btnFavorito.isSelected()) {
                imgCorazon.setImage(new Image(getClass().getResource("/Imagenes/corazon_lleno.png").toExternalForm()));
                System.out.println("Añadido a favoritos");
            } else {
                imgCorazon.setImage(new Image(getClass().getResource("/Imagenes/corazon_vacio.png").toExternalForm()));
                System.out.println("Quitado de favoritos");
            }
        });

        hacerTituloTransparente(titledDescripcion);
        hacerTituloTransparente(titledDetalles);
        hacerTituloTransparente(titledCuidados);
    }

    private String obtenerTallaSeleccionada() {
        ToggleButton selected = (ToggleButton) tallasGroup.getSelectedToggle();
        return selected != null ? selected.getText() : null;
    }

    private void hacerTituloTransparente(TitledPane pane) {
        pane.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        pane.skinProperty().addListener((obs, oldSkin, newSkin) -> {
            Region titleRegion = (Region) pane.lookup(".title");
            if (titleRegion != null) {
                titleRegion.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
            }
        });
    }
    
    @FXML
    private void irAPantallaPrincipal(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/PantallaPrincipal.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
