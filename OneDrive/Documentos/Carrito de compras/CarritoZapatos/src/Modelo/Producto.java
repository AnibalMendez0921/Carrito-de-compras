/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import javafx.scene.image.Image;

/**
 *
 * @author Josue Gómez y Anibal Mendez
 */
public class Producto {
    private String nombre;
    private String descripcion;
    private String talla;
    private String calificacion;
    private double precio;
    private Image imagen;
    private int cantidad; 

    public Producto(String nombre, String descripcion, String talla, String calificacion, double precio, Image imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.talla = talla;
        this.calificacion = calificacion;
        this.precio = precio;
        this.imagen = imagen;
        this.cantidad = 1;
    }


    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTalla() {
        return talla;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public double getPrecio() {
        return precio;
    }

    public Image getImagen() {
        return imagen;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void aumentarCantidad() {
        this.cantidad++;
    }

    public void disminuirCantidad() {
        if (this.cantidad > 1) {
            this.cantidad--;
        }
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }
}
