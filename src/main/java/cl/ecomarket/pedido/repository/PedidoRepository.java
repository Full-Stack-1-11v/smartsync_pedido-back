package cl.ecomarket.pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.ecomarket.pedido.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Pedido findByPedidoId(Long pedidoId);
}
