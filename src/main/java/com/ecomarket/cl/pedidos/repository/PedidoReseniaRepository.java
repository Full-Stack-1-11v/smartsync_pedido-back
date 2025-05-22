package com.ecomarket.cl.pedidos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecomarket.cl.pedidos.model.PedidoResenia;

@Repository
public interface PedidoReseniaRepository extends JpaRepository<PedidoResenia, Long>{

    @Query("SELECT pr FROM pedido_resenia WHERE pr.id = :id")
    List<PedidoResenia> findByIdResenia(Long idReseniaLong);

}
