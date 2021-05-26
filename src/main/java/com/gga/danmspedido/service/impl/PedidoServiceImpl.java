package com.gga.danmspedido.service.impl;

import java.util.ArrayList;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import com.fasterxml.jackson.databind.ser.std.IterableSerializer;
import com.gga.danmspedido.model.DetallePedido;
import com.gga.danmspedido.model.EstadoPedido;
import com.gga.danmspedido.model.Pedido;
import com.gga.danmspedido.model.Producto;
import com.gga.danmspedido.repository.PedidoRepository;
import com.gga.danmspedido.service.ClienteService;
import com.gga.danmspedido.service.MaterialService;
import com.gga.danmspedido.service.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoServiceImpl implements PedidoService{

    @Autowired MaterialService materialService;
    @Autowired ClienteService clienteService;
    @Autowired PedidoRepository pedidoRepository;


    


    @Override
    public Pedido guardarPedido(Pedido p) throws RuntimeException {

        boolean hayStock = p.getDetalle().stream().allMatch(dp-> verificarStock(dp.getProducto(),dp.getCantidad()));
        Double totalOrden = p.getDetalle()
				.stream()
				.mapToDouble( dp -> dp.getCantidad() * dp.getPrecio())
				.sum();
        Double saldoCliente = clienteService.deudaCliente(p.getObra());
        Double nuevoSaldo = saldoCliente-totalOrden;
        Boolean generaDeuda = nuevoSaldo<0;
        Integer bcra = clienteService.situacionCrediticiaBCRA(p.getObra());
    
        if(p.getId()==null){
            if(hayStock){
                if(generaDeuda || bcra>2){
                    p.setEstadoPedido(new EstadoPedido(null, "ACEPTADO"));
                    return altaPedido(p);
                }
                else{
                    throw new RuntimeException("No cumple las condiciones de deuda y BCRA");
                }
            }
            else{
                p.setEstadoPedido(new EstadoPedido(1, "PENDIENTE"));
                return altaPedido(p);
            }
            
        }
        else{
            return modificarPedido(p);
        }
    }

    private Pedido modificarPedido(Pedido p) {
        return pedidoRepository.save(p);
    }

    private Pedido altaPedido(Pedido p) {
        return pedidoRepository.save(p);
    }

    @Override
    public Pedido agregarDetalle(Integer id, DetallePedido dp) {
        Iterable<Pedido> pedidos = pedidoRepository.findAll();
        for(Pedido p : pedidos){
            if (p.getId().equals(id)){
                ArrayList<DetallePedido> detalles = p.getDetalle();
                detalles.add(dp);
                p.setDetalle(detalles);
                return p;
            }
        }
        return null;
    }

    @Override
    public void borrarPedido(Integer id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    public void borrarDetalle(Integer id, Integer idDetalle) {
        Pedido pedido = pedidoRepository.findById(id).get();
        if(pedido != null){
            OptionalInt indexOptDetalle =   IntStream.range(0, pedido.getDetalle().size())
            .filter(i -> pedido.getDetalle().get(i).getId().equals(idDetalle))
            .findFirst();
            if(indexOptDetalle.isPresent()){
                pedido.getDetalle().remove(indexOptDetalle.getAsInt());
            }
        }     
    }

    @Override
    public Pedido buscarPedido(Integer id) {
        return pedidoRepository.findById(id).get();
    }

    @Override
    public Pedido buscarPedidoPorObra(Integer id) {
        Iterable<Pedido> pedidos = pedidoRepository.findAll();
        for(Pedido p : pedidos){
            if(p.getObra().getId().equals(id)){
                return p;
            }
        }
        return null;
    }

    @Override
    public DetallePedido buscarDetallePedidoPorId(Integer id,Integer idDetalle) {
        Pedido p = pedidoRepository.findById(id).get();
        if(p!=null){
            for(DetallePedido dp : p.getDetalle()){
                if(dp.getId().equals(idDetalle)){
                    return dp;
                }
            }
        }
        return null;
    }

    boolean verificarStock(Producto p,Integer cantidad) {
		return materialService.chequearStock(p) >= cantidad;
	}
    
}
