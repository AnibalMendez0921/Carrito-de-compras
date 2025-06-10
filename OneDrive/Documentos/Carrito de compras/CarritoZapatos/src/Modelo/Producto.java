/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import javafx.scene.image.Image;

/**
 *
 * @Josue Gómez y Anibal Mendez
 */
public class Producto {
 private String nombre;
    private String descripcion;
    private String talla;
    private String calificacion;
    private String precio;
    private Image imagen;

    public Producto(String nombre, String descripcion, String talla, String calificacion, String precio, Image imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.talla = talla;
        this.calificacion = calificacion;
        this.precio = precio;
        this.imagen = imagen;
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

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
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

    public String getPrecio() {
        return precio;
    }

    public Image getImagen() {
        return imagen;
    }

   
}
