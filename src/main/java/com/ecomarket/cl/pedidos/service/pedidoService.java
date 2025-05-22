package com.ecomarket.cl.pedidos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomarket.cl.pedidos.model.Pedido;
import com.ecomarket.cl.pedidos.repository.PedidoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> findAll(){
        return pedidoRepository.findAll();
    }

    public Pedido findByidPedido(Long id){
        return pedidoRepository.findByidPedido(id);
    }

    public Pedido gestionarEnvio(Long pedidoId){
        Pedido pedido = pedidoRepository.findByidPedido(pedidoId);
        if (pedido != null) {
            pedido.setEstadoPedido(true); // Cambia el estado del pedido a "optimizado"
            pedidoRepository.save(pedido); // Guarda los cambios en la base de datos
            return pedido;
        } else {
            return null;
        }
    }

    // Metodo para guardar un pedido
    public Pedido guardarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    // Metodo para eliminar un pedido
    public void eliminarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }

}
