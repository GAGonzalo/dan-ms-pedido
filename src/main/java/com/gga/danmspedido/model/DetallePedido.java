package com.gga.danmspedido.model;

public class DetallePedido {
    private Integer id;
    private Integer cantidad;
    private Double precio;
    private Producto producto;


    public DetallePedido() {
    }

    public DetallePedido(Integer id, Integer cantidad, Double precio, Producto producto) {
        this.id = id;
        this.cantidad = cantidad;
        this.precio = precio;
        this.producto= producto;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return this.precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }


    public Producto getProducto() {
        return this.producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }


}
