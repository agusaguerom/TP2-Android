package com.example.tp2.model;

public class Consola {

    private int id;
    private String nombre;

    public Consola(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Consola(){

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
}
