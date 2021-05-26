package com.gga.danmspedido.service.impl;

import java.util.ArrayList;

import com.gga.danmspedido.model.DetallePedido;
import com.gga.danmspedido.model.Producto;
import com.gga.danmspedido.service.MaterialService;

import org.springframework.stereotype.Component;

@Component
public class MaterialServiceImpl implements MaterialService{

    @Override
    public Integer chequearStock(Producto p) {
        return 50;
    }
    
}
