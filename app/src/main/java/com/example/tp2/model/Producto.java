package com.example.tp2.model;

public class Producto {

    private int id;
    private String nombre;
    private int precio;
    private String descripcion;
    private String fecha_salida;
    private String fecha_publicacion;
    private int stock;
    private Consola consola;

    public Producto(int id, String nombre, int precio, String descripcion, String fecha_salida, String fecha_publicacion, int stock, Consola consola) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.fecha_salida = fecha_salida;
        this.fecha_publicacion = fecha_publicacion;
        this.stock = stock;
        this.consola = consola;
    }
    public Producto(int id, String nombre, int precio, String descripcion, String fecha_salida, String fecha_publicacion, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.fecha_salida = fecha_salida;
        this.fecha_publicacion = fecha_publicacion;
        this.stock = stock;
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

    public String getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(String fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public String getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(String fecha_publicacion) {
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
