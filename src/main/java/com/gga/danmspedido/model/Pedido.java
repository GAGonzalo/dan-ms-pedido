package com.gga.danmspedido.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private Integer id;
    private Instant fechaPedido; 
    private EstadoPedido estadoPedido;
    private Obra obra;
    private ArrayList<DetallePedido> detalle;

    public Pedido() {
    }


    public Pedido(Integer id, Instant fechaPedido, EstadoPedido estadoPedido, Obra obra, ArrayList<DetallePedido> detalle) {
        this.id = id;
        this.fechaPedido = fechaPedido;
        this.estadoPedido = estadoPedido;
        this.obra = obra;
        this.detalle = detalle;
    }

    public EstadoPedido getEstadoPedido() {
        return this.estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public Obra getObra() {
        return this.obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public ArrayList<DetallePedido> getDetalle() {
        return this.detalle;
    }

    public void setDetalle(ArrayList<DetallePedido> detalle) {
        this.detalle = detalle;
    }
   

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getFechaPedido() {
        return this.fechaPedido;
    }

    public void setFechaPedido(Instant fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

}
