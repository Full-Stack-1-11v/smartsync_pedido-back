package com.ecomarket.cl.pedidos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecomarket.cl.pedidos.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

    @Query("SELECT p FROM pedido p WHERE p.idPedido = :idPedido")
    List<Pedido> findBypedidoId(Long pedidoId);

    public Pedido findByidPedido (Long id);

    @Query(value = "SELECT * FROM pedido WHERE estado_pedido = :estadoPedido", nativeQuery = true)
    List<Pedido> findByEstadoPedido(boolean estadoPedido);
    
    

}
