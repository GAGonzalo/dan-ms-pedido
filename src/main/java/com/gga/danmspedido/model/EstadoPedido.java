package com.gga.danmspedido.model;

public class EstadoPedido {
    private Integer id;
    private String estado;

    public EstadoPedido() {
    }

    public EstadoPedido(Integer id, String estado) {
        this.id = id;
        this.estado = estado;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
