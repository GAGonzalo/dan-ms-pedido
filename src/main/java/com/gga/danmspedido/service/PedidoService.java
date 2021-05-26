package com.gga.danmspedido.service;

import com.gga.danmspedido.model.DetallePedido;
import com.gga.danmspedido.model.Pedido;

import org.springframework.stereotype.Service;

@Service
public interface PedidoService {
    public Pedido guardarPedido(Pedido p) throws Exception;
    public Pedido agregarDetalle(Integer id, DetallePedido dp);
    public void borrarPedido(Integer id);
    public void borrarDetalle(Integer id, Integer idDetalle);
    public Pedido buscarPedido(Integer id);
    public Pedido buscarPedidoPorObra(Integer id);
    public DetallePedido buscarDetallePedidoPorId(Integer id, Integer idDetalle);
}
