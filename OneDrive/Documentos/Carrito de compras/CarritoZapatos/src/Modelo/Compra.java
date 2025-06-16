/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class Compra {

    private String direccion;
    private String metodoPago;
    private List<Producto> productos;
    private LocalDate fecha;
    private String detalle;
    private double total; 
    private String nombreCliente;
    private String estado;
    private String idPedido;

  public Compra(List<Producto> productos, LocalDate fecha) {
    this.productos = productos;
    this.fecha = fecha;
    this.idPedido = generarNumeroPedido();
    
}

    public List<Producto> getProductos() {
        return productos;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public void setNombreCliente(String nombreCliente) {
    this.nombreCliente = nombreCliente;
   }

   public String getNombreCliente() {
    return nombreCliente;
   }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public String getEstado() {
    return estado;
  }

  public void setId(String id) {
    this.idPedido = id;
  }

  public String getId() {
    return idPedido;
   }

    public String getDireccion() {
        return direccion;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

   public String getDetalle() {
    return  detalle;
}


    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }
    
    public String getIdPedido() {
    return idPedido;
}

    @Override
    public String toString() {
        return "Fecha: " + fecha + ", productos: " + productos.toString();
    }
    
    public String obtenerResumenCompleto() {
    StringBuilder resumen = new StringBuilder();
    resumen.append("ID del Pedido: ").append(idPedido).append("\n");
    resumen.append("Cliente: ").append(nombreCliente).append("\n");
    resumen.append("Fecha: ").append(fecha).append("\n");
    resumen.append("Estado: ").append(estado).append("\n");
    resumen.append(String.format("Total: $%,.2f\n", total));
    resumen.append("\nDetalle:\n");
    resumen.append(detalle);

    return resumen.toString();
}
    
    private String generarNumeroPedido() {
    Random random = new Random();
    int numero = 100 + random.nextInt(900); 
    return "PED-" + numero;
}
}
