package com.gga.danmspedido.service.impl;

import com.gga.danmspedido.model.Obra;
import com.gga.danmspedido.service.ClienteService;

import org.springframework.stereotype.Component;

@Component
public class ClienteServiceImpl implements ClienteService {

    @Override
    public Double deudaCliente(Obra id) {
        return 0.0;
    }

    @Override
    public Double maximoSaldoNegativo(Obra id) {
        return 0.0;
    }

    @Override
    public Integer situacionCrediticiaBCRA(Obra id) {
        return 2;
    }
    
}
