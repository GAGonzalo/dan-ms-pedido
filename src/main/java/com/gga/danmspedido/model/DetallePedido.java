package com.gga.danmspedido.model;

public class DetallePedido {
    private Integer id;
    private Integer cantidad;
    private Double precio;


    public DetallePedido() {
    }

    public DetallePedido(Integer id, Integer cantidad, Double precio) {
        this.id = id;
        this.cantidad = cantidad;
        this.precio = precio;
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

}
