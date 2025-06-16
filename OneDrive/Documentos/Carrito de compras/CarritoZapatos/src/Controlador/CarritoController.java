/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Carrito;
import Modelo.Compra;
import Modelo.NodoProducto;
import Modelo.PilaCompras;
import Modelo.Producto;
import Modelo.Sesion;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

public class CarritoController implements Initializable {

    @FXML
    private VBox listaItemsVBox;
    @FXML
    private TextArea detalleCompraArea;
    @FXML
    private Label subtotalLabel;
    @FXML
    private Label envioLabel;
    @FXML
    private Label totalLabel;

    private static final double COSTO_ENVIO = 20000.0;
    private PilaCompras pila = PilaCompras.getInstance();
    private double totalCompraActual = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarItemsCarrito();
        actualizarResumen();
    }

    private void cargarItemsCarrito() {
        NodoProducto actual = Carrito.getPrimero();
        while (actual != null) {
            Producto producto = actual.getProducto();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/ItemCarrito.fxml"));
                Node nodo = loader.load();

                ItemCarritoController controller = loader.getController();
                controller.setDatos(
                        producto.getNombre(),
                        producto.getDescripcion(),
                        producto.getTalla(),
                        producto.getCalificacion(),
                        producto.getPrecio()
                );
                controller.setImagen(producto.getImagen());
                controller.setContenedorPadre(listaItemsVBox);
                controller.setProducto(producto);
                controller.setCarritoController(this);

                nodo.getProperties().put("controller", controller);
                listaItemsVBox.getChildren().add(nodo);
            } catch (IOException e) {
                e.printStackTrace();
            }
            actual = actual.getSiguiente();
        }
    }

    public void actualizarResumen() {
        StringBuilder detalle = new StringBuilder();
        double subtotal = 0;
        int totalCantidad = 0;
        DecimalFormat df = new DecimalFormat("#,###.##");

        detalle.append(String.format("%-25s %-6s %-6s %-12s %-12s\n", "Producto", "Talla", "Cant.", "Unitario", "Subtotal"));
        detalle.append("-------------------------------------------------------------------------\n");

        for (Node nodo : listaItemsVBox.getChildren()) {
            ItemCarritoController controller = (ItemCarritoController) nodo.getProperties().get("controller");
            if (controller != null) {
                Producto p = controller.getProducto();
                int cantidad = controller.getCantidad();
                double precio = p.getPrecio();
                double subtotalProducto = precio * cantidad;

                subtotal += subtotalProducto;
                totalCantidad += cantidad;

                detalle.append(String.format("%-25s %-6s %-6d $%-11s $%-11s\n",
                        p.getNombre(),
                        p.getTalla(),
                        cantidad,
                        df.format(precio),
                        df.format(subtotalProducto)
                ));
            }
        }

        double total = subtotal + COSTO_ENVIO;
        totalCompraActual = total; 
        detalleCompraArea.setText(detalle.toString());
        subtotalLabel.setText("Subtotal: $" + df.format(subtotal));
        envioLabel.setText("Envío: $" + df.format(COSTO_ENVIO));
        totalLabel.setText("Total a pagar: $" + df.format(total));
    }

    @FXML
    private void irAVerMas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/VerMas.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onComprar() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Finalizar Compra");
        dialog.setHeaderText("Ingrese los datos para su pedido");

        ButtonType comprarBtn = new ButtonType("Comprar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(comprarBtn, ButtonType.CANCEL);

        TextField direccionField = new TextField();
        direccionField.setPromptText("Dirección de entrega");

        ComboBox<String> metodoPagoBox = new ComboBox<>();
        metodoPagoBox.getItems().addAll("Nequi", "Daviplata", "PSE", "Tarjeta débito o crédito", "transferencia bancaria", "Pago contraentrega" );
        metodoPagoBox.setPromptText("Método de pago");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("Dirección:"), 0, 0);
        grid.add(direccionField, 1, 0);
        grid.add(new Label("Método de pago:"), 0, 1);
        grid.add(metodoPagoBox, 1, 1);

        GridPane.setHgrow(direccionField, Priority.ALWAYS);
        GridPane.setHgrow(metodoPagoBox, Priority.ALWAYS);

        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == comprarBtn) {
                return new Pair<>(direccionField.getText(), metodoPagoBox.getValue());
            }
            return null;
        });

        dialog.showAndWait().ifPresent(datos -> {
            String direccion = datos.getKey();
            String metodoPago = datos.getValue();

            if (direccion == null || direccion.isEmpty() || metodoPago == null) {
                mostrarAlerta("Error", "Debe ingresar la dirección y el método de pago.");
                return;
            }

            List<Producto> productosComprados = new ArrayList<>();
            for (Node nodo : listaItemsVBox.getChildren()) {
                ItemCarritoController controller = (ItemCarritoController) nodo.getProperties().get("controller");
                if (controller != null) {
                    Producto p = controller.getProducto();
                    int cantidad = controller.getCantidad();
                    for (int i = 0; i < cantidad; i++) {
                        productosComprados.add(p);
                    }
                }
            }

            if (productosComprados.isEmpty()) {
                mostrarAlerta("Carrito vacío", "No hay productos para comprar.");
                return;
            }
           
            Compra nuevaCompra = new Compra(productosComprados, LocalDate.now());
            nuevaCompra.setDireccion(direccion);
            nuevaCompra.setMetodoPago(metodoPago);
            nuevaCompra.setDetalle(detalleCompraArea.getText());
            nuevaCompra.setTotal(totalCompraActual); 

            pila.push(nuevaCompra);

            Carrito.limpiar();
            listaItemsVBox.getChildren().clear();
            actualizarResumen();
        });
    }

    @FXML
    private void Historial(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/HistorialCompras.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Historial de Compras");
            stage.show();

            Stage actual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            actual.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
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