package com.gga.danmspedido.model;

public class Producto {
    private Integer id;
    private String descripcion;
    private Double precio;

    public Producto() {
    }

    public Producto(Integer id, String descripcion, Double precio) {
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return this.precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }


}
