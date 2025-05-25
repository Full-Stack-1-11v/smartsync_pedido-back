package cl.ecomarket.pedido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ecomarket.pedido.model.Pedido;
import cl.ecomarket.pedido.repository.PedidoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Pedido findByPedidoId(Long pedidoId) {
        return pedidoRepository.findByPedidoId(pedidoId);
    }

    public Pedido gestionarEnvio(Long pedidoId) {
        Pedido pedido = pedidoRepository.findByPedidoId(pedidoId);
        if (pedido != null) {
            pedido.setEstadoPedido(true);
            pedidoRepository.save(pedido);
            return pedido;
        } else {
            return null;
        }
    }

    public Pedido guardarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public void eliminarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}
