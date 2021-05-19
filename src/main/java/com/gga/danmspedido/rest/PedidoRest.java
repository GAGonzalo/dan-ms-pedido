package com.gga.danmspedido.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import javax.websocket.server.PathParam;

import com.gga.danmspedido.model.DetallePedido;
import com.gga.danmspedido.model.Pedido;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedido")
public class PedidoRest {

    private  List<Pedido> pedidos = new ArrayList();
    private static Integer ID = 1;

    @PostMapping()
    private ResponseEntity<Pedido> crearPedido(@RequestBody Pedido p){
        p.setId(ID++);
        pedidos.add(p);
        return ResponseEntity.ok(p);
    }

    @PostMapping(path = "/{idPedido}/detalle")
    private ResponseEntity<Pedido> agregarDetalle(@PathVariable Integer idPedido, @RequestBody DetallePedido detallePedido){
        for(Pedido p : pedidos){
            if (p.getId().equals(idPedido)){
                ArrayList<DetallePedido> detalles = p.getDetalle();
                detalles.add(detallePedido);
                p.setDetalle(detalles);
                return ResponseEntity.ok(p);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(path = "/{id}")
    private ResponseEntity<Pedido> modificar(@PathVariable Integer id, @RequestBody Pedido p){
        OptionalInt indexOpt =   IntStream.range(0, pedidos.size())
        .filter(i -> pedidos.get(i).getId().equals(id))
        .findFirst();

        if(indexOpt.isPresent()){
            pedidos.set(indexOpt.getAsInt(), p);
            return ResponseEntity.ok(p);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{idPedido}")
    private ResponseEntity<Pedido> borrarPorId(@PathVariable Integer idPedido){
        OptionalInt indexOpt =   IntStream.range(0, pedidos.size())
        .filter(i -> pedidos.get(i).getId().equals(idPedido))
        .findFirst();

        if(indexOpt.isPresent()){
            pedidos.remove(indexOpt.getAsInt());
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{idPedido}/detalle/{idDetalle}")
    private ResponseEntity<Pedido> borrarDetalle(@PathVariable Integer idPedido, @PathVariable Integer idDetalle){
        OptionalInt indexOpt =   IntStream.range(0, pedidos.size())
        .filter(i -> pedidos.get(i).getId().equals(idPedido))
        .findFirst();

        if(indexOpt.isPresent()){
            OptionalInt indexOptDetalle =   IntStream.range(0, pedidos.size())
            .filter(i -> pedidos.get(i).getId().equals(idPedido))
            .findFirst();
            if(indexOptDetalle.isPresent()){
                pedidos.get(indexOpt.getAsInt()).getDetalle().remove(indexOptDetalle.getAsInt());
                return ResponseEntity.ok().build();
            }
            else{
                return ResponseEntity.notFound().build();
            }
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/{idPedido}")
    private ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable Integer idPedido){
        for(Pedido p : pedidos){
            if(p.getId().equals(idPedido)){
                return ResponseEntity.ok(p);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/obra/{idObra}")
    private ResponseEntity<Pedido> buscarPedidoPorIdObra(@PathVariable Integer idObra){
        for(Pedido p : pedidos){
            if(p.getObra().getId().equals(idObra)){
                return ResponseEntity.ok(p);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/{idPedido}/detalle/{idDetalle}")
    private ResponseEntity<DetallePedido> buscarDetallePedidoPorId(@PathVariable Integer idPedido, @PathVariable Integer idDetalle){
        for(Pedido p : pedidos){
            if(p.getId().equals(idPedido)){
                for(DetallePedido d : p.getDetalle()){
                    if(d.getId().equals(idDetalle)){
                        return ResponseEntity.ok(d);
                    }
                    else{
                        return ResponseEntity.notFound().build();
                    }
                }
            }
            
        }
        return ResponseEntity.notFound().build();
    }
}