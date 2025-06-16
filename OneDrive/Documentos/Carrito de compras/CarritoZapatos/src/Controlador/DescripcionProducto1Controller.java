/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Carrito;
import Modelo.ColaPedidos;
import Modelo.Compra;
import Modelo.ListaDeseos;
import Modelo.PilaCompras;
import Modelo.Producto;
import Modelo.Sesion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.scene.Node;

public class DescripcionProducto1Controller implements Initializable {

    @FXML private ToggleButton talla36;
    @FXML private ToggleButton talla37;
    @FXML private ToggleButton talla38;
    @FXML private ToggleButton talla39;
    @FXML private ToggleButton talla40;

    @FXML private TitledPane titledDescripcion;
    @FXML private TitledPane titledDetalles;
    @FXML private TitledPane titledCuidados;
    @FXML private TextArea textAreaResumen;
    @FXML private TextArea pedidosTextArea;
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

    private void aplicarTallaAlProductoActual() {
        productoActual.setTalla(obtenerTallaSeleccionada());
    }

    @FXML
    private void agregarAlCarrito(ActionEvent event) {
        aplicarTallaAlProductoActual();
        if (productoActual.getTalla().equals("Sin talla")) {
            mostrarAlerta("Error", "Por favor selecciona una talla.");
            return;
        }
        Carrito.agregarProducto(productoActual);
        mostrarAlerta("Carrito", "Producto agregado al carrito.");
    }

    @FXML
    private void irAlCarrito(ActionEvent event) {
        cambiarVista("/Vista/Carrito.fxml", "Carrito de compras", event);
    }

    @FXML
    private void irAPantallaPrincipal(ActionEvent event) {
        cambiarVista("/Vista/PantallaPrincipal.fxml", "Tienda de Zapatos", event);
    }

    @FXML
    private void irAListaDeseos(ActionEvent event) {
        cambiarVista("/Vista/ListaDeseos.fxml", "Lista de Deseos", event);
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
    private void toggleFavorito() {
        esFavorito = !esFavorito;
        String imagen = esFavorito ? "/Imagenes/corazon_lleno.png" : "/Imagenes/corazon vacio.png";
        imgCorazon.setImage(new Image(getClass().getResourceAsStream(imagen)));
        aplicarTallaAlProductoActual();
        if (esFavorito) {
            ListaDeseos.agregar(productoActual);
        } else {
            ListaDeseos.eliminar(productoActual);
        }
    }

@FXML
private void comprarAhora(ActionEvent event) {
    aplicarTallaAlProductoActual();
    productoActual.setCantidad(1);

    if (productoActual.getTalla().equals("Sin talla")) {
        mostrarAlerta("Error", "Seleccione una talla antes de comprar.");
        return;
    }

    Dialog<Pair<String, String>> dialog = new Dialog<>();
    dialog.setTitle("Finalizar Compra");
    dialog.setHeaderText("Ingrese los datos de envío y pago");

    ButtonType comprarBtn = new ButtonType("Comprar", ButtonBar.ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(comprarBtn, ButtonType.CANCEL);

    TextField direccionField = new TextField();
    direccionField.setPromptText("Dirección");

    ComboBox<String> metodoPagoBox = new ComboBox<>();
    metodoPagoBox.getItems().addAll("Nequi", "Daviplata", "PSE", "Tarjeta débito o crédito", "transferencia bancaria", "Pago contraentrega");
    metodoPagoBox.setPromptText("Método de pago");

    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.add(new Label("Dirección:"), 0, 0);
    grid.add(direccionField, 1, 0);
    grid.add(new Label("Método de pago:"), 0, 1);
    grid.add(metodoPagoBox, 1, 1);

    dialog.getDialogPane().setContent(grid);

    dialog.setResultConverter(dialogButton -> {
        if (dialogButton == comprarBtn) {
            return new Pair<>(direccionField.getText(), metodoPagoBox.getValue());
        }
        return null;
    });

    Optional<Pair<String, String>> result = dialog.showAndWait();

    result.ifPresent(datos -> {
        String direccion = datos.getKey();
        String metodoPago = datos.getValue();

        if (direccion == null || direccion.isEmpty() || metodoPago == null) {
            mostrarAlerta("Error", "Debe ingresar dirección y método de pago.");
            return;
        }

        List<Producto> productos = new ArrayList<>();
        productos.add(productoActual);

        Compra compra = new Compra(productos, LocalDate.now());
        compra.setDireccion(direccion);
        compra.setMetodoPago(metodoPago);

        if (Sesion.haySesionActiva()) {
            compra.setNombreCliente(Sesion.getUsuarioActual().getNombre());
        } else {
            compra.setNombreCliente("Invitado");
        }

        double subtotal = productoActual.getPrecio() * productoActual.getCantidad();
        double total = subtotal + 20000;
        compra.setTotal(total); 
        compra.setEstado("Pendiente");

        StringBuilder detalle = new StringBuilder();
        detalle.append(String.format("%-25s %-6s %-6s %-12s %-12s\n", "Producto", "Talla", "Cant.", "Unitario", "Subtotal"));
        detalle.append("-------------------------------------------------------------------------\n");
        detalle.append(String.format("%-25s %-6s %-6d $%-11.2f $%-11.2f\n",
                productoActual.getNombre(),
                productoActual.getTalla(),
                productoActual.getCantidad(),
                productoActual.getPrecio(),
                subtotal));
        detalle.append("-------------------------------------------------------------------------\n");
        detalle.append(String.format("Subtotal: $%,.2f\n", subtotal));
        detalle.append("Envío: $20,000.00\n");
        detalle.append(String.format("Total: $%,.2f\n", total));

        compra.setDetalle(detalle.toString());

        ColaPedidos.getInstancia().encolar(compra);
        PilaCompras.getInstance().push(compra);

        mostrarAlerta("Compra Exitosa", "¡Gracias por su compra!");
    });
}

private void mostrarAlerta(String titulo, String mensaje) {
    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
    alerta.setTitle(titulo);
    alerta.setHeaderText(null);
    alerta.setContentText(mensaje);
    alerta.showAndWait();
}


    @FXML
private void irAVistaUsuario(ActionEvent event) {
    try {
        String correo = Sesion.getUsuarioActual().getCorreo();

        String vistaDestino;
        String tituloVentana;

        if (correo != null && correo.endsWith("@zcarpe")) {
            vistaDestino = "/Vista/OpcionesAdministrador.fxml";
            tituloVentana = "Panel Administrador";
        } else {
            vistaDestino = "/Vista/OpcionesUsuarios.fxml";
            tituloVentana = "Panel Usuario";
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource(vistaDestino));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(tituloVentana);
        stage.show();

        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
