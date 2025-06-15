/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDate;
import java.util.List;

public class Compra {

    private String direccion;
    private String metodoPago;
    private List<Producto> productos;
    private LocalDate fecha;
    private String detalle;
    private double total; 

    public Compra(List<Producto> productos, LocalDate fecha) {
        this.productos = productos;
        this.fecha = fecha;
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
        return detalle;
    }


    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Fecha: " + fecha + ", productos: " + productos.toString();
    }
}

