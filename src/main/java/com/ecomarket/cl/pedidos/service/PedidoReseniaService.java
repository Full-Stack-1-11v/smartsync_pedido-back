package com.ecomarket.cl.pedidos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomarket.cl.pedidos.model.Pedido;
import com.ecomarket.cl.pedidos.model.PedidoResenia;
import com.ecomarket.cl.pedidos.repository.PedidoReseniaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoReseniaService {
    @Autowired
    private PedidoReseniaRepository pedidoReseniaRepository;

    public List<PedidoResenia> findAll(){return pedidoReseniaRepository.findAll();};

    public String dejarResenia(Pedido pedido, Long idPedido){
        return "Reseña dejada para el pedido" + idPedido;
    }

}
