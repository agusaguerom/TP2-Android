package com.example.tp2.model;


import android.net.Uri;

import java.util.Date;

public class Producto {

    private int id;
    private String nombre;
    private int precio;
    private String descripcion;
    private Date fecha_salida;
    private Date fecha_publicacion;
    private int stock;
    private String url_imagen;
    private Consola consola;

    public Producto(int id, String nombre, int precio, String descripcion, Date fecha_salida, Date fecha_publicacion, int stock,String url_imagen, Consola consola) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.fecha_salida = fecha_salida;
        this.fecha_publicacion = fecha_publicacion;
        this.stock = stock;
        this.url_imagen = url_imagen;
        this.consola = consola;
    }
    public Producto(int id, String nombre, int precio, String descripcion, Date fecha_salida, Date fecha_publicacion, int stock, String url_imagen) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.fecha_salida = fecha_salida;
        this.fecha_publicacion = fecha_publicacion;
        this.stock = stock;
        this.url_imagen = url_imagen;
    }
    public Producto(){

    }

    public String getUrl_imagen() {
        return url_imagen;
    }

    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public Date getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(Date fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Consola getConsola() {
        return consola;
    }

    public void setConsola(Consola consola) {
        this.consola = consola;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", descripcion='" + descripcion + '\'' +
                ", fecha_salida='" + fecha_salida + '\'' +
                ", fecha_publicacion='" + fecha_publicacion + '\'' +
                ", stock=" + stock +
                '}';
    }
}
