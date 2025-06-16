/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.ColaPedidos;
import Modelo.Compra;
import Modelo.NodoCola;
import Modelo.PilaCompras;
import Modelo.Producto;
import Modelo.Sesion;
import Modelo.Usuario;
import java.io.File;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class OpcionesAdministradorController implements Initializable {

    @FXML
    private TextArea pedidosTextArea;

    @FXML
    private VBox vBoxContenedorProductos;
    
     @FXML
    private TextArea clientesTextArea;
     
     @FXML
    private TextArea productoMasVendidoTextArea;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mostrarPedidosPendientes();
        mostrarNombreCliente();
        mostrarProductoMasVendido();
    }
    
    
    @FXML
private Button btnCerrarSesion;

    @FXML
    private void irAPantallaPrincipal(javafx.event.ActionEvent event) {
        cambiarVista("/Vista/PantallaPrincipal.fxml", "Tienda de Zapatos", event);
    }

    private void cambiarVista(String ruta, String titulo, javafx.event.ActionEvent event) {
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

    private void mostrarPedidosPendientes() {
        ColaPedidos cola = ColaPedidos.getInstancia();
        NodoCola actual = cola.getFrente();

        StringBuilder detalles = new StringBuilder();

        while (actual != null) {
            Compra compra = actual.getCompra();
            detalles.append("ID del Pedido: ").append(compra.getIdPedido()).append("\n");
            detalles.append("Nombre Usuario: ").append(compra.getNombreCliente()).append("\n");
            detalles.append("Fecha: ").append(compra.getFecha()).append("\n");
            detalles.append("Estado: ").append(compra.getEstado()).append("\n");
            detalles.append("Direccion: ").append(compra.getDireccion()).append("\n");
            detalles.append("Metodo de pago: ").append(compra.getMetodoPago()).append("\n");
            detalles.append("Detalles de la compra: ").append("\n").append(compra.getDetalle()).append("\n\n");

            actual = actual.getSiguiente();
        }

        pedidosTextArea.setText(detalles.toString());
    }
    
    
private void mostrarNombreCliente() {
    Usuario cliente = Sesion.getUsuarioActual();
    if (cliente != null) {
        Compra compra = PilaCompras.getInstance().peek();
        if (compra != null) {
            System.out.println("Compra encontrada: " + compra.getNombreCliente());
            clientesTextArea.setText("Cliente actual: " + compra.getNombreCliente());
        } else {
            System.out.println("No hay compras registradas");
            clientesTextArea.setText("No hay compras registradas.");
        }
    } else {
        System.out.println("No hay cliente en sesión");
        clientesTextArea.setText("No hay cliente en sesión.");
    }
}

private void mostrarProductoMasVendido() {
    List<Compra> listaCompras = PilaCompras.getInstance().toList();
    Map<String, Integer> conteoProductos = new HashMap<>();

    for (Compra compra : listaCompras) {
        for (Producto producto : compra.getProductos()) {
            String nombre = producto.getNombre(); 
            conteoProductos.put(nombre, conteoProductos.getOrDefault(nombre, 0) + 1);
        }
    }

    String masVendido = null;
    int maxVentas = 0;
    for (Map.Entry<String, Integer> entry : conteoProductos.entrySet()) {
        if (entry.getValue() > maxVentas) {
            masVendido = entry.getKey();
            maxVentas = entry.getValue();
        }
    }

    if (masVendido != null) {
        productoMasVendidoTextArea.setText("Producto más vendido:\n" +
                masVendido + "\nCantidad vendida: " + maxVentas);
    } else {
        productoMasVendidoTextArea.setText("No hay ventas registradas.");
    }
}



@FXML
private void mostrarDialogoAgregarProducto(ActionEvent event) {
    TextField nombreField = new TextField();
    TextField precioField = new TextField();
    TextField tallaField = new TextField();
    TextField stockField = new TextField();
    TextField imagenField = new TextField();
    imagenField.setEditable(false);

    Button imagenButton = new Button("Seleccionar Imagen");
    imagenButton.setOnAction(e -> {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            imagenField.setText(selectedFile.getAbsolutePath());
        }
    });

    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(20, 150, 10, 10));

    grid.add(new Label("Nombre:"), 0, 0);
    grid.add(nombreField, 1, 0);
    grid.add(new Label("Precio:"), 0, 1);
    grid.add(precioField, 1, 1);
    grid.add(new Label("Talla:"), 0, 2);
    grid.add(tallaField, 1, 2);
    grid.add(new Label("Stock:"), 0, 3);
    grid.add(stockField, 1, 3);
    grid.add(new Label("Imagen:"), 0, 4);
    grid.add(imagenField, 1, 4);
    grid.add(imagenButton, 1, 5);

    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setTitle("Agregar Producto");
    dialog.getDialogPane().setContent(grid);
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

    Optional<ButtonType> result = dialog.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        try {
            String nombre = nombreField.getText();
            double precio = Double.parseDouble(precioField.getText());
            String talla = tallaField.getText();
            int stock = Integer.parseInt(stockField.getText());
            String imagen = imagenField.getText();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/ItemPAdministrador.fxml"));
            AnchorPane item = loader.load();

            ItemPAdministradorController controller = loader.getController();
            controller.setDatos(nombre, precio, talla, stock, imagen);

            vBoxContenedorProductos.getChildren().add(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
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

