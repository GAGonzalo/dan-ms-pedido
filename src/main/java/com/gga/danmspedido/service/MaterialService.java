package com.gga.danmspedido.service;

import java.util.ArrayList;

import com.gga.danmspedido.model.DetallePedido;
import com.gga.danmspedido.model.Producto;

import org.springframework.stereotype.Service;

@Service
public interface MaterialService {
    public Integer chequearStock(Producto p);
}
