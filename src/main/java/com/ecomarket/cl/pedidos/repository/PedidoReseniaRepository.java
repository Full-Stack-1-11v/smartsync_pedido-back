package com.ecomarket.cl.pedidos.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.ecomarket.cl.pedidos.model.PedidoResenia;

@Repository
public interface PedidoReseniaRepository extends JpaRepository<PedidoResenia, Long>{

    

}
