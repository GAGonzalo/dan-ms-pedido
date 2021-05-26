package com.gga.danmspedido.repository;

import com.gga.danmspedido.model.Pedido;

import org.springframework.stereotype.Repository;

import frsf.isi.dan.InMemoryRepository;

@Repository
public class PedidoRepository extends InMemoryRepository<Pedido> {

    @Override
    public Integer getId(Pedido arg0) {
        return arg0.getId();
    }

    @Override
    public void setId(Pedido arg0, Integer arg1) {
      arg0.setId(arg1);
        
    }

    

    
    
}
