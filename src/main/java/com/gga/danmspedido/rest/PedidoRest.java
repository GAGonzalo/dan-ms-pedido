package com.gga.danmspedido.rest;

import com.gga.danmspedido.model.DetallePedido;
import com.gga.danmspedido.model.Pedido;
import com.gga.danmspedido.service.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/pedido")
@Api(value = "PedidoRest", description = "Permite gestionar los pedidos")
public class PedidoRest {

    @Autowired PedidoService pedidoService;

    @PostMapping()
    @ApiOperation(value = "Crea un pedido")
    private ResponseEntity<Pedido> crearPedido(@RequestBody Pedido p){
        try {
            p = pedidoService.guardarPedido(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(p!=null){
            return ResponseEntity.ok(p);
        }
        else return ResponseEntity.badRequest().build();
    }


    @PostMapping(path = "/{idPedido}/detalle")
    @ApiOperation(value = "Agrega un detalle a un pedido")
    private ResponseEntity<Pedido> agregarDetalle(@PathVariable Integer idPedido, @RequestBody DetallePedido detallePedido){
        if(idPedido==null || detallePedido==null){
            return ResponseEntity.badRequest().build();
        }
        else{
            Pedido p = pedidoService.agregarDetalle(idPedido, detallePedido);
            if(p!=null){
                return ResponseEntity.ok(p);
            }
            else{
                return ResponseEntity.notFound().build();
            }
        }
    
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Modifica un pedido")
    private ResponseEntity<Pedido> modificar(@PathVariable Integer id, @RequestBody Pedido p){
        if(p != null && id != null){
            p.setId(id);
            try {
                Pedido modificado = pedidoService.guardarPedido(p);
                if(modificado == null){
                    return ResponseEntity.notFound().build();
                }
                else{
                    return ResponseEntity.ok(modificado);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().build();
            }
            
        }
        else{
            return ResponseEntity.badRequest().build();
        }
        
    }

    @DeleteMapping(path = "/{idPedido}")
    @ApiOperation(value = "Borra un pedido")
    private ResponseEntity<Pedido> borrarPorId(@PathVariable Integer idPedido){
        if(idPedido==null){
            return ResponseEntity.badRequest().build();
        }
        else{
            pedidoService.borrarPedido(idPedido);
            return ResponseEntity.ok().build();
        }
        
    }

    @DeleteMapping(path = "/{idPedido}/detalle/{idDetalle}")
    @ApiOperation(value = "Borra un detalle a un pedido")
    private ResponseEntity<Pedido> borrarDetalle(@PathVariable Integer idPedido, @PathVariable Integer idDetalle){
        if(idDetalle == null || idPedido == null){
            return ResponseEntity.badRequest().build();
        }   
        else{
            pedidoService.borrarDetalle(idPedido, idDetalle);
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping(path = "/{idPedido}")
    @ApiOperation(value = "Busca un pedido por ID")
    private ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable Integer idPedido){
        
        if(idPedido == null){
            return ResponseEntity.badRequest().build();
        }
        else{
            Pedido p = pedidoService.buscarPedido(idPedido);
            if(p==null){
                return ResponseEntity.notFound().build();
            }
            else{
                return ResponseEntity.ok(p);
            }
        }

        
    }

    @GetMapping(path = "/obra/{idObra}")
    @ApiOperation(value = "Busca un pedido por ID de OBRA")
    private ResponseEntity<Pedido> buscarPedidoPorIdObra(@PathVariable Integer idObra){
        if(idObra == null){
            return ResponseEntity.badRequest().build();
        }
        else{
            Pedido p = pedidoService.buscarPedidoPorObra(idObra);
            if(p == null){
                return ResponseEntity.notFound().build();
            }
            else{
                return ResponseEntity.ok(p);
            }
        }
       
    }

    @GetMapping(path = "/{idPedido}/detalle/{idDetalle}")
    @ApiOperation(value = "Busca un detalle de pedido por ID")
    private ResponseEntity<DetallePedido> buscarDetallePedidoPorId(@PathVariable Integer idPedido, @PathVariable Integer idDetalle){

        if(idPedido == null || idDetalle == null){
            return ResponseEntity.badRequest().build();
        }
        else{
            DetallePedido d = pedidoService.buscarDetallePedidoPorId(idPedido,idDetalle);
            if(d == null){
                return ResponseEntity.notFound().build();
            }
            else{
                return ResponseEntity.ok(d);
            }
        }
    }
}