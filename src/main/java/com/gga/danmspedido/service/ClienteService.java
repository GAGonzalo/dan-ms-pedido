package com.gga.danmspedido.service;

import com.gga.danmspedido.model.Obra;

import org.springframework.stereotype.Service;

@Service
public interface ClienteService {

	public Double deudaCliente(Obra id);
	public Double maximoSaldoNegativo(Obra id);
	public Integer situacionCrediticiaBCRA(Obra id);

}
